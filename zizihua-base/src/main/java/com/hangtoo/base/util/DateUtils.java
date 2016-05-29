package com.hangtoo.base.util;

import java.sql.Timestamp;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils{
	public static Timestamp now(){
		return getTimestamp(System.currentTimeMillis());
	}
	
	public static Timestamp getTimestamp(long currentTime){
		return new java.sql.Timestamp(currentTime);
	}
	
}
