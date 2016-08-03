package hangtoo.job.stock;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hangtoo.base.util.DateUtils;
import com.hangtoo.base.util.LunarDateUtil;
import com.hangtoo.html.EnumHeaderStyle;
import com.hangtoo.html.decode.IHtmlDecoderFacade;
import com.hangtoo.html.decode.impl.HtmlDecoderFacade;
import com.mysql.jdbc.StringUtils;

import hangtoo.entity.stock.TStock;
import hangtoo.service.stock.TStockService;


public class Job {
	
	private final static Logger log= Logger.getLogger(Job.class);
	
	@Autowired
	private TStockService tStockService; 
	IHtmlDecoderFacade htmlDecoderFacade=new HtmlDecoderFacade();
	String url="http://www.szse.cn/szseWeb/FrontController.szse?ACTIONID=7&AJAX=AJAX-TRUE&CATALOGID=1803&TABKEY=tab1&txtQueryDate=#QUERYDATE#&REPORT_ACTION=search";
	String tableID="REPORTID_tab1";
	Date now;
	
    public void taskCycle(){
    	//每天执行，接口日使用统计，
    	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    	
    	now=DateUtils.dateNow();
    	
    	int week=0;
		synchronized(Job.class){
			Date day=DateUtils.addDay(now,0,-1);//TODO
			
			String sday=tStockService.getLastDate();
			if(!StringUtils.isNullOrEmpty(sday)){
				try {
					day=DateUtils.parseDate(sday, DateUtils.pattern_full_divide);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(;day.before(now)||day.equals(now);day=DateUtils.addDay(day,0,1)){

				log.info(day);
				week=DateUtils.getWeekday(day);
				if(week>5){//周末跳过
					continue;
				}
				
				if(day.getMonth()==0&&day.getDate()==1){//1.1跳过
					continue;
				}
				if(day.getMonth()==4&&(day.getDate()>=1&&day.getDate()<=3)){//5.1跳过
					continue;
				}
				if(day.getMonth()==9&&(day.getDate()>=1&&day.getDate()<=7)){//10.1跳过
					continue;
				}
				
				Date d=LunarDateUtil.solarTolunar(day);
				if(d.getMonth()==0&&(d.getDate()>=1&&d.getDate()<=7)){
					continue;
				}
				
				while(true){
					try{
						this.batchSave(day);
						Thread.sleep(15000);
						break;
					}catch(Exception e){
						try {
							Thread.sleep(50000);
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
    
    private void batchSave(Date day) throws Exception{
		try {

			String shortdate=DateUtils.DateToShort(day);
			
			url=url.replaceAll("#QUERYDATE#",shortdate);
			List<Map<String,String>> data=htmlDecoderFacade.getTableData(url,tableID,EnumHeaderStyle.TOP);
			
			TStock entity;
			String tmp=null;
			for(Map<String,String> ele:data){
				entity=new TStock();
				entity.setP_date(day);
				entity.setP_name(ele.get("指标名称"));
				tmp=ele.get("比上日增减");
				if(tmp!=null){
					tmp=formatData(tmp);
					entity.setP_add(new BigDecimal(tmp));
				}
				tmp=ele.get("本日数值");
				if(tmp!=null){
					tmp=formatData(tmp);
					entity.setP_data(new BigDecimal(tmp));
				}
				tmp=ele.get("最高值日期");
				if(tmp!=null){
					tmp=formatData(tmp);
					entity.setP_highdate(DateUtils.parseDate(tmp, DateUtils.pattern_d));
				}
				tmp=ele.get("本年最高");
				if(tmp!=null){
					tmp=formatData(tmp);
					entity.setP_highdata(new BigDecimal(tmp));
				}
				tmp=ele.get("幅度%");
				if(tmp!=null){
					tmp=formatData(tmp);
					entity.setP_rate(new BigDecimal(tmp));
				}
				entity.setP_createtime(now);
				tStockService.add(entity);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
    }

    
}
