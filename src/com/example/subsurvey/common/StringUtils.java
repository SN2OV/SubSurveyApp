package com.example.subsurvey.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtils {
	
	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串
	 * 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}
	
	
	public static String getSystemTime(){
		//获取系统时间
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");       
		Date curTime = new Date(System.currentTimeMillis());//获取当前时间
		String time = formatter.format(curTime).toString();
		return time;
	}

	public static String getCurDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间
		String date = formatter.format(curDate);
		return date;
	}

	/**
	 * @return 单路径、换乘一次、换乘两次分别对应0、1、2
	 */
	public static int getSurveyTypeNo(String surveyType,String pathTypeInfo[]){
		int surveyTypeNo;
		for(surveyTypeNo=0;surveyTypeNo<3;surveyTypeNo++){
			if(pathTypeInfo[surveyTypeNo].equals(surveyType)){
						break;
					} 
				}
		return surveyTypeNo;
	}
	
	//String转换为Calendar
	public static Calendar StringToCalendar(String time){
		SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");

		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		
		return calendar;
	}
	
	public static Calendar getSystemCalendar(){
		Calendar rightNow = Calendar.getInstance();
		rightNow.get(Calendar.HOUR_OF_DAY); 
		rightNow.get(Calendar.MINUTE); 
		rightNow.get(Calendar.SECOND); 
		return rightNow;
	}
	
	
}
