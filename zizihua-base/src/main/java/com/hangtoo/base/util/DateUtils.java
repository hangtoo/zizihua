package com.hangtoo.base.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils{
	
	/**
	 * 日期格式
	 */
	public static String pattern_d = "yyyy-MM-dd";

	public static String pattern_t = "HH:mm:ss";
	
	public static String pattern_full = "yyyy-MM-dd HH:mm:ss";
	
	public static String pattern_yyyyMM="yyyyMM";

	public static SimpleDateFormat shortDateFormat = new SimpleDateFormat(pattern_d);
	
	public static SimpleDateFormat shortTimeFormat = new SimpleDateFormat(pattern_t);

	public static SimpleDateFormat fullDateFormat = new SimpleDateFormat(pattern_full);
	
	public static SimpleDateFormat yyyyMMDateFormat = new SimpleDateFormat(pattern_yyyyMM);
	
	public static Timestamp now(){
		return getTimestamp(System.currentTimeMillis());
	}
	
	public static Timestamp getTimestamp(long currentTime){
		return new java.sql.Timestamp(currentTime);
	}
	
	public static Date dateNow(){
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}
	
	/**
	 * 将所给日期转换为时间(yy-MM-dd)字符串
	 * 
	 * @param source
	 *            要转换的日期对象
	 * @return 时间字符串
	 */
	public static String DateToShort(Date source) {
		String result = "";
		result = shortDateFormat.format(source);
		return result;
	}
	
	
	public static String convertDateToShortString(Date date){
        String datestring = "";
        int year = 0,month = 0,day = 0;
      
        if(date==null) return datestring;
        
        year = date.getYear()+1900;
        month = date.getMonth()+1;
        day = date.getDate();
        datestring = year+"-";
        if(month>9){
            datestring += month+"-";
        }else{
            datestring += "0"+month+"-";            
        }
        if(day>9){
            datestring += day;
        }else{
            datestring += "0"+day;            
        }        
        return datestring;
    }
	
    //1~7分别表示周一到周日
	public static int getWeekday(Date date){
       Calendar gc = Calendar.getInstance();
       gc.setTime(date);
       int week=gc.get(Calendar.DAY_OF_WEEK);//"","星期日","星期一","星期二","星期三","星期四","星期五","星期六"
       week--;
		if(week==0){
			week=7;
		}
		
		return week;
	}
}
