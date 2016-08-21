package hangtoo.job.gold;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.hangtoo.html.EnumHeaderStyle;
import com.hangtoo.html.decode.IHtmlDecoderFacade;
import com.hangtoo.html.decode.impl.HtmlDecoderFacade;
import com.hangtoo.util.Constants;
import com.hangtoo.util.DateUtils;
import com.mysql.jdbc.StringUtils;

import hangtoo.entity.gold.TGold;
import hangtoo.service.gold.TGoldService;


public class Job {
	
	private final static Logger log= Logger.getLogger(Job.class);
	
	@Autowired
	private TGoldService tGoldService; 
	IHtmlDecoderFacade htmlDecoderFacade=new HtmlDecoderFacade();
	String urltemplate="http://www.sge.com.cn/xqzx/mrxq/";
	String urltemplate_page="http://www.sge.com.cn/xqzx/mrxq/index_#PAGE#.shtml";
	String domain="http://www.sge.com.cn";
	Date now;
	String tableID="page_con";
	int pageSize=15;
	
	final int MAXPAGE=128;//http://www.sge.com.cn/xqzx/mrxq/index_128.shtml
	
	final static Map<String,Integer> dayPage=new HashMap<>();//日期 和页码
	final static List<Integer> mapedPage=new ArrayList<>();//已经取过数据的页面
	final static List<Integer> mapedFailPage=new ArrayList<>();//已经取过数据的页面,但是没拿到
	
	final static Map<String,String> dayUrl=new HashMap<>();//日期 和链接
	
	//http://www.sge.com.cn/xqzx/mrxq/
	//获取索引页
	//根据翻页规则获取指定日期的页面 ，每页15行，第1页第1行为最近一个工作日，据此计算指定日期的链接，需要分析该标题来确定链接，如果不是则前后找找，找上一条或上一页
	//需要适配不同的标题，同一个数据有多个标题
	//storeData 
	
    public void taskCycle(){
    	//每天执行，接口日使用统计，
    	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    	
    	now=DateUtils.dateNow();
    	
		synchronized(Job.class){
			Date day=DateUtils.addDay(now,0,-3);//TODO
			
			String sday=tGoldService.getLastDate();
			if(!StringUtils.isNullOrEmpty(sday)){
				try {
					day=DateUtils.parseDate(sday, DateUtils.pattern_full_S);
					day=DateUtils.addDay(day,0,1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(;day.before(now);day=DateUtils.addDay(day,0,1)){//||day.equals(now)

				System.out.printf("target day:%s",day);
				if(!DateUtils.isWorkingDay(day)){
					continue;
				}
				
				while(true){
					try{
						this.batchSave(day);
						Thread.sleep(60*1000);
						break;
					}catch(Exception e){
						try {
							Thread.sleep(180*1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
				}
			}
			
    	}
    }
    
    /**
     * 扫描指定页面，并缓存该页面
     * @param TPAGE
     * @throws Exception
     */
    private void mapIndexPage(Integer TPAGE) throws Exception{
    	if(mapedPage.contains(TPAGE)){
    		return;
    	}
    	List<Element> as=htmlDecoderFacade.getTargetAttr(urltemplate_page.replace("#PAGE#", String.valueOf(TPAGE)),"zl_list",EnumHeaderStyle.TOP,Constants.A);
    	
    	if(!as.isEmpty()){
	    	for(Element a:as){
	    		dayPage.put(a.child(0).text(), TPAGE);
	    	}
    		mapedPage.add(TPAGE);
    	}else{
    		mapedFailPage.add(TPAGE);
    	}
    }
    
    
    private String getUrlByAs(List<Element> as,String day){
		for(int i=0;i<as.size();i++){
			Element a=as.get(i);
			//System.out.println(a.attr(Constants.HREF));
			///xqzx/mrxq/539858.shtml
			//System.out.println(a.child(0).text());
			//2016-08-19
			//System.out.println(a.child(1).text());
			//上海黄金交易所2016年8月3日交易行情
			dayUrl.put(a.child(0).text(), a.attr(Constants.HREF));//缓存下
			if(day.equals(a.child(0).text())){
				return domain+a.attr(Constants.HREF);
			}
		}
		return null;
    }
    
    /**
     * 根据翻页规则获取指定日期的页面 ，每页15行，第1页第1行为最近一个工作日，据此计算指定日期的链接，
     * 需要分析该标题来确定链接，如果不是则前后找找，找上一条或上一页
     * @param day
     * @return
     */
    private String getUrlByDate(String day){
    	//urltemplate;
    	//"zl_list"
    	try {
    		
    		//直接从缓存中取
    		String cachurl=dayUrl.get(day);
    		if(!StringUtils.isNullOrEmpty(cachurl)){
    			return domain+cachurl;
    		}
    		
    		List<Element> as=null;
    		Integer indexPage=dayPage.get(day);
    		if(indexPage==null){//缓存索引页
    			for(int i=MAXPAGE;i>1;i--){
    				mapIndexPage(i);
    				indexPage=dayPage.get(day);
    				if(indexPage!=null){
    					break;
    				}
    			}
    		}
    		indexPage=dayPage.get(day);
    		as=htmlDecoderFacade.getTargetAttr(urltemplate_page.replace("#PAGE#", String.valueOf(indexPage)),"zl_list",EnumHeaderStyle.TOP,Constants.A);
    		String ret=getUrlByAs(as,day);
    		if(ret!=null){
    			return ret;
    		}
    		
			//否则认为索引页缓存失效了
			mapedPage.remove(indexPage);
			dayPage.remove(day);
			
			for(int i=MAXPAGE;i>1;i--){//最新的索引页，如果还不成功就跳过
				mapIndexPage(i);
				indexPage=dayPage.get(day);
				if(indexPage!=null){
					break;
				}
			}
			as=htmlDecoderFacade.getTargetAttr(urltemplate_page.replace("#PAGE#", String.valueOf(indexPage)),"zl_list",EnumHeaderStyle.TOP,Constants.A);
			return getUrlByAs(as,day);
			
			//urltemplate_page
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//return "http://www.sge.com.cn/xqzx/mrxq/539598.shtml";
    	return null;
    }
    
    private void batchSave(Date day) throws Exception{
		try {

			String shortdate=DateUtils.DateToShort(day);
			
			String url=getUrlByDate(shortdate);
			System.out.println(url);
			if(url==null){
				return;
			}
			List<Map<String,String>> data=htmlDecoderFacade.getTableData(url,tableID,EnumHeaderStyle.TOP);
			
			TGold entity;
			String tmp=null;
			for(Map<String,String> ele:data){
				entity=new TGold();
				entity.setP_date(day);
				
				tmp=ele.get("合约");
				
				if(StringUtils.isNullOrEmpty(tmp)){
					tmp=ele.get("品种");
					if(StringUtils.isNullOrEmpty(tmp)){
						tmp=ele.get("交易品种");
						if(StringUtils.isNullOrEmpty(tmp)){
							continue;
						}
					}
				}
				entity.setP_name(tmp);
				
				tmp=ele.get("开盘价");
				if(!StringUtils.isNullOrEmpty(tmp)){
					tmp=formatData(tmp);
					try{
						entity.setP_opendata(new BigDecimal(tmp));
					}catch(Exception e){
						System.out.println(tmp);
						log.error(e);
					}
				}

				tmp=ele.get("最高价");
				if(!StringUtils.isNullOrEmpty(tmp)){
					tmp=formatData(tmp);
					try{
						entity.setP_highdata(new BigDecimal(tmp));
					}catch(Exception e){
						System.out.println(tmp);
						log.error(e);
					}
				}
				
				tmp=ele.get("最低价");
				if(!StringUtils.isNullOrEmpty(tmp)){
					tmp=formatData(tmp);
					try{
						entity.setP_lowdata(new BigDecimal(tmp));
					}catch(Exception e){
						System.out.println(tmp);
						log.error(e);
					}
				}
				
				tmp=ele.get("收盘价");
				if(!StringUtils.isNullOrEmpty(tmp)){
					tmp=formatData(tmp);
					try{
						entity.setP_closedata(new BigDecimal(tmp));
					}catch(Exception e){
						System.out.println(tmp);
						log.error(e);
					}
				}
				
				tmp=ele.get("涨跌（元）");
				if(!StringUtils.isNullOrEmpty(tmp)){
					tmp=formatData(tmp);
					try{
						entity.setP_add(new BigDecimal(tmp));
					}catch(Exception e){
						System.out.println(tmp);
						log.error(e);
					}
				}
				
				tmp=ele.get("涨跌幅");
				if(!StringUtils.isNullOrEmpty(tmp)){
					tmp=formatData(tmp);
					try{
						entity.setP_rate(new BigDecimal(tmp));
					}catch(Exception e){
						System.out.println(tmp);
						log.error(e);
					}
				}
				
				tmp=ele.get("加权平均价");
				if(!StringUtils.isNullOrEmpty(tmp)){
					tmp=formatData(tmp);
					try{
						entity.setP_data(new BigDecimal(tmp));
					}catch(Exception e){
						System.out.println(tmp);
						log.error(e);
					}
				}
				
				tmp=ele.get("成交量");
				if(!StringUtils.isNullOrEmpty(tmp)){
					tmp=formatData(tmp);
					try{
						entity.setP_volume(new BigDecimal(tmp));
					}catch(Exception e){
						System.out.println(tmp);
						log.error(e);
					}
				}
				
				tmp=ele.get("成交金额");
				if(!StringUtils.isNullOrEmpty(tmp)){
					tmp=formatData(tmp);
					try{
						entity.setP_amount(new BigDecimal(tmp));
					}catch(Exception e){
						System.out.println(tmp);
						log.error(e);
					}
				}else{
					tmp=ele.get("成交额   (元)");
					if(!StringUtils.isNullOrEmpty(tmp)){
						tmp=formatData(tmp);
						try{
							entity.setP_amount(new BigDecimal(tmp));
						}catch(Exception e){
							System.out.println(tmp);
							log.error(e);
						}
					}
				}
				
				tmp=ele.get("持仓量");
				if(!StringUtils.isNullOrEmpty(tmp)){
					tmp=formatData(tmp);
					try{
						entity.setP_openinterest(new BigDecimal(tmp));
					}catch(Exception e){
						System.out.println(tmp);
						log.error(e);
					}
				}
				
				tmp=ele.get("交收量");
				if(!StringUtils.isNullOrEmpty(tmp)){
					tmp=formatData(tmp);
					try{
						entity.setP_settlement(new BigDecimal(tmp));
					}catch(Exception e){
						System.out.println(tmp);
						log.error(e);
					}
				}
				
				tmp=ele.get("交收方向");
				if(!StringUtils.isNullOrEmpty(tmp)){
					entity.setP_remark(tmp);
				}				
				entity.setP_createtime(now);
				tGoldService.add(entity);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
    }
    
    private String formatData(String data){
    	return data.replaceAll(",", "");
    }

    
}
