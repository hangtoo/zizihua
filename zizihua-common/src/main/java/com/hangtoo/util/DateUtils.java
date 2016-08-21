package com.hangtoo.util;

import java.sql.Timestamp;
import java.text.ParseException;
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
	public static String pattern_full_S = "yyyy-MM-dd HH:mm:ss.S";
	public static String pattern_full_divide= "yyyy/MM/dd HH:mm:ss";
	public static String pattern_full_divide_S= "yyyy/MM/dd HH:mm:ss.S";
	
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
	
	/**
	 * 将所给日期转换为时间(yyyy-MM-dd HH:mm:ss)字符串
	 * 
	 * @param source
	 *            要转换的日期对象
	 * @return 时间字符串
	 */
	public static String DateToFull(Date source) {
		String result = "";
		result = fullDateFormat.format(source);
		return result;
	}
	
    /**
     * 日期加N天
     * @param Sring 时间
     * @return 加后的日期
     */
	public static Date addDay(Date date, int m, int d) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern_d);

			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			cd.add(Calendar.DATE, d);// 添加一天
			cd.add(Calendar.MONTH, m);//添加一个月

			return cd.getTime();

		} catch (Exception e) {
			return null;
		}
	}
	@Deprecated
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
	
    public static Boolean isWorkingDay(Date day){
		int week=DateUtils.getWeekday(day);
		if(week>5){//周末跳过
			return false;
		}
		
		if(day.getMonth()==0&&day.getDate()==1){//1.1跳过
			return false;
		}
		if(day.getMonth()==4&&(day.getDate()>=1&&day.getDate()<=3)){//5.1跳过
			return false;
		}
		if(day.getMonth()==9&&(day.getDate()>=1&&day.getDate()<=7)){//10.1跳过
			return false;
		}
		
		Date d=LunarDateUtil.solarTolunar(day);
		if(d.getMonth()==0&&(d.getDate()>=1&&d.getDate()<=7)){
			return false;
		}
		return true;
    }
	
	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern_d);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static void main(String[] args) {
		/** 此处修改成你的 表名 和 中文注释 ***/
		try {
			System.out.println(DateUtils.addDay(DateUtils.now(),0,-1));
			System.out.println(DateUtils.parseDate("2016/8/2 20:49:41", DateUtils.pattern_full_divide));
			System.out.println(DateUtils.parseDate("2016-03-25 00:00:00.0", DateUtils.pattern_full_S));
			
			
			System.out.println(DateUtils.parseDate("2016-01-01", DateUtils.pattern_d));
			
			System.out.println(DateToFull(new Date(1471038696811l)));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
