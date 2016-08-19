package hangtoo.job.gold;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
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
	//http://www.sge.com.cn/xqzx/mrxq/
	//获取索引页
	//getUrlByDate 根据翻页规则获取指定日期的页面 ，每页15行，第1页第1行为最近一个工作日，据此计算指定日期的链接，需要分析该标题来确定链接，如果不是则前后找找，找上一条或上一页
	//getDatafromUrl 需要适配不同的标题，同一个数据有多个标题
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

				System.out.println(day);
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
    
    private String formatData(String data){
    	return data.replaceAll(",", "");
    }
    
    private String getUrlByDate(String indexUrl,String day){
    	//urltemplate;
    	//"zl_list"
    	try {
			List<Element> as=htmlDecoderFacade.getTargetAttr(urltemplate,"zl_list",EnumHeaderStyle.TOP,Constants.A);
			Date s;
			Date e;
			if(!as.isEmpty()){
				s=DateUtils.parseDate(as.get(0).child(0).text(), DateUtils.pattern_d);
				e=DateUtils.parseDate(as.get(as.size()-1).child(0).text(), DateUtils.pattern_d);
			}
			for(int i=0;i<as.size();i++){
				Element a=as.get(i);
				System.out.println(a.attr(Constants.HREF));
				///xqzx/mrxq/539858.shtml
				System.out.println(a.child(0).text());
				//2016-08-19
				System.out.println(a.child(1).text());
				//上海黄金交易所2016年8月3日交易行情
				
				if(day.equals(a.child(0).text())){
					return domain+a.attr(Constants.HREF);
				}
				
			}
			
			Date d=DateUtils.parseDate(day, DateUtils.pattern_d);
			//TODO 对于没找到的这种情况，待处理
			
			//urltemplate_page
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return "http://www.sge.com.cn/xqzx/mrxq/539598.shtml";
    }
    
    private void batchSave(Date day) throws Exception{
		try {

			String shortdate=DateUtils.DateToShort(day);
			
			String url=getUrlByDate(urltemplate,shortdate);
			
			List<Map<String,String>> data=htmlDecoderFacade.getTableData(url,tableID,EnumHeaderStyle.TOP);
			
			TGold entity;
			String tmp=null;
			for(Map<String,String> ele:data){
				entity=new TGold();
				entity.setP_date(day);
				
				tmp=ele.get("合约");
				if(tmp==null||tmp.equals("")){
					continue;
				}
				entity.setP_name(tmp);
				
				tmp=ele.get("开盘价");
				if(tmp!=null&&!tmp.equals("")){
					tmp=formatData(tmp);
					entity.setP_opendata(new BigDecimal(tmp));
				}

				tmp=ele.get("最高价");
				if(tmp!=null&&!tmp.equals("")){
					tmp=formatData(tmp);
					entity.setP_highdata(new BigDecimal(tmp));
				}
				
				tmp=ele.get("最低价");
				if(tmp!=null&&!tmp.equals("")){
					tmp=formatData(tmp);
					entity.setP_lowdata(new BigDecimal(tmp));
				}
				
				tmp=ele.get("收盘价");
				if(tmp!=null&&!tmp.equals("")){
					tmp=formatData(tmp);
					entity.setP_closedata(new BigDecimal(tmp));
				}
				
				tmp=ele.get("涨跌（元）");
				if(tmp!=null&&!tmp.equals("")){
					tmp=formatData(tmp);
					entity.setP_add(new BigDecimal(tmp));
				}
				
				tmp=ele.get("涨跌幅");
				if(tmp!=null&&!tmp.equals("")){
					tmp=formatData(tmp);
					entity.setP_rate(new BigDecimal(tmp));
				}
				
				tmp=ele.get("加权平均价");
				if(tmp!=null&&!tmp.equals("")){
					tmp=formatData(tmp);
					entity.setP_data(new BigDecimal(tmp));
				}
				
				tmp=ele.get("成交量");
				if(tmp!=null&&!tmp.equals("")){
					tmp=formatData(tmp);
					entity.setP_volume(new BigDecimal(tmp));
				}
				
				tmp=ele.get("成交金额");
				if(tmp!=null&&!tmp.equals("")){
					tmp=formatData(tmp);
					entity.setP_amount(new BigDecimal(tmp));
				}
				
				tmp=ele.get("持仓量");
				if(tmp!=null&&!tmp.equals("")){
					tmp=formatData(tmp);
					entity.setP_openinterest(new BigDecimal(tmp));
				}
				
				tmp=ele.get("交收量");
				if(tmp!=null&&!tmp.equals("")){
					tmp=formatData(tmp);
					entity.setP_settlement(new BigDecimal(tmp));
				}
				
				tmp=ele.get("交收方向");
				if(tmp!=null&&!tmp.equals("")){
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

    
}
