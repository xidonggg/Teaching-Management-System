package com.ssh.Util.timeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {
	public static String stampToTime(Date stamp) {
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd kk:mm");	 
		String temp = f.format(stamp);
		return temp;	
	}
	public static String stampToTime(String stamp) {
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd kk:mm");
		try {
			Date date=f.parse(stamp);
			String temp = f.format(date);
			return temp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

}
