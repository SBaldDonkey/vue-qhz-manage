package com.cainew.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.cainew.util.NumberUtil;
import com.cainew.util.TextUtils;

public class YXDate {
	private static Calendar getCalendar() {
		TimeZone tz = TimeZone.getDefault();
		return Calendar.getInstance(tz);
	}

	public static String getYear() {
		return String.valueOf(getCalendar().get(1));
	}

	public static String getMonth() {
		return strFormat(getCalendar().get(2) + 1);
	}

	public static String getDay() {
		return strFormat(getCalendar().get(5));
	}

	public static String getFormatDate(String separator) {
		return getYear() + separator + getMonth() + separator + getDay();
	}

	public static String getHour() {
		return strFormat(getCalendar().get(11));
	}

	public static String getMinute() {
		return strFormat(getCalendar().get(12));
	}

	public static String getSecond() {
		return strFormat(getCalendar().get(13));
	}

	public static String getDateTime(String s1, String s2) {
		return getYear() + s1 + getMonth() + s1 + getDay() + " " + getHour()
				+ s2 + getMinute() + s2 + getSecond();
	}

	public static String getDateTime2(String s1, String s2) {
		return getYear() + s1 + getMonth() + s1 + getDay() + getHour() + s2
				+ getMinute() + s2 + getSecond();
	}

	public static String getTime(String s1) {
		return getHour() + s1 + getMinute() + s1 + getSecond();
	}

	public static String getTime() {
		return String.valueOf(System.currentTimeMillis() / 1000L);
	}

	public static String getTime(Date date) {
		return String.valueOf(date.getTime() / 1000L);
	}

	public static String getTimeToday() {
		long now = System.currentTimeMillis() / 1000L;

		return String.valueOf(now);
	}

	private static String strFormat(int data) {
		String str = String.valueOf(data);
		if (data <= 9) {
			str = "0" + str;
		}
		return str;
	}

	/**
	 * 根据时间获取时间戳
	 * @param date
	 * @return
	 */
	public static String getDateTime(String date) {
		return getDateTimeFormat(date,"");
	}

	/**
	 * 根据时间获取时间戳
	 * @param date：时间
	 * @param simpleDateFormat：时间格式
	 * @return
	 */
	public static String getDateTimeFormat(String date,String simpleDateFormat){
		if("".equals(TextUtils.dealNull(simpleDateFormat,""))){
			simpleDateFormat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(simpleDateFormat);
		Date date2 = null;
		try {
			date2 = dateFormat.parse(date);
		} catch (ParseException e) {
//			e.printStackTrace();
			simpleDateFormat = "yyyy-MM-dd";
			dateFormat = new SimpleDateFormat(simpleDateFormat);
			try {
				date2 = dateFormat.parse(date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}

		return String.valueOf(date2.getTime() / 1000L);
	}
	
	public static String TimeStamp2DateTime(String timestampString) {
		Long timestamp = Long.valueOf(Long.parseLong(timestampString));
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(timestamp.longValue()));
		return date;
	}
	
	
	public static String TimeStamp2Date(String timestampString,String formatStr) {
		Long timestamp = Long.valueOf(timestampString.length() < 13 ? Long
				.parseLong(timestampString) * 1000L : Long
				.parseLong(timestampString));
		String date = new SimpleDateFormat(formatStr).format(new Date(
				timestamp.longValue()));
		return date;
	}
	

	public static String TimeStamp2Date(String timestampString) {
		
		return YXDate.TimeStamp2Date(timestampString,"yyyy-MM-dd");
		
	}
	
	
	
	

	public static String TimeStamp2Date_month(String timestamp2String) {
		Long timestamp = Long.valueOf(Long.parseLong(timestamp2String) * 1000L);
		String date = new SimpleDateFormat("yyyy-MM").format(new Date(timestamp
				.longValue()));
		return date;
	}

	public static String date2TimeStamp(String date) {
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date s = dateformat.parse(date);
			return String.valueOf(s.getTime() / 1000L);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dateTime2TimeStamp(String date) {
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date s = dateformat.parse(date);
			return String.valueOf(s.getTime() / 1000L);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getTimeDayLastSecond() {
		return dateTime2TimeStamp(TimeStamp2Date(String.valueOf(System
				.currentTimeMillis())) + " 23:59:59");
	}

	public static String getMonthTime(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(2, month);
		String timeStamp = String.valueOf(cal.getTimeInMillis() / 1000L);
		return timeStamp;
	}

	public static String getDayTime(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(6, days);
		String timeStamp = String.valueOf(cal.getTimeInMillis() / 1000L);
		return timeStamp;
	}

	public static String getYearTime(int years) {
		Calendar cal = Calendar.getInstance();
		cal.add(1, years);
		String timeStamp = String.valueOf(cal.getTimeInMillis() / 1000L);
		return timeStamp;
	}

	public static String getYearTime(long timeStamp1, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeStamp1 * 1000L);
		cal.add(1, years);
		String timeStamp = String.valueOf(cal.getTimeInMillis() / 1000L);
		return timeStamp;
	}

	public static int lateDays(long time) {
		Calendar c = Calendar.getInstance();
		Calendar nowTime = Calendar.getInstance();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(format.format(Long.valueOf(String.valueOf(
					time).length() < 13 ? time * 1000L : time)));
			c.setTime(date);
			long now = System.currentTimeMillis();
			Date date2 = format.parse(format.format(Long.valueOf(now)));
			nowTime.setTime(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return countDays(c, nowTime);
	}

	public static int lateDays(long time1, long time2) {
		Calendar c = Calendar.getInstance();
		Calendar nowTime = Calendar.getInstance();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(format.format(Long.valueOf(String.valueOf(
					time1).length() < 13 ? time1 * 1000L : time1)));
			c.setTime(date);

			Date date2 = format.parse(format.format(Long.valueOf(String
					.valueOf(time2).length() < 13 ? time2 * 1000L : time2)));
			nowTime.setTime(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return countDays(c, nowTime);
	}

	public static int countDays(Calendar c_b, Calendar c_e) {
		int days = 0;
		while (c_b.before(c_e)) {
			days++;
			c_b.add(6, 1);
		}
		return days;
	}

	public static String getOffsetMonthDate(Date protoDate, int monthOffset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(protoDate);
		cal.add(2, -monthOffset);
		Date date = cal.getTime();
		return new SimpleDateFormat("yyyy-MM").format(date);
	}

	public static String getLastMonth(int month) {
		return getYear() + "-" + strFormat(getCalendar().get(2) + 1 - month);
	}

	

	/**
	 * 
	 * @param date
	 * @param addMonth
	 * @return
	 */
	public static Date addMonths(Date date, int addMonth) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.MONTH, addMonth);

		return gc.getTime();

	}

	public static Date addMonths(String dateStr, int addMonth) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return YXDate.addMonths(sdf.parse(dateStr), addMonth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return YXDate.addMonths(addMonth);

	}

	/**
	 * 添加月
	 * 
	 * @param addMonth
	 *            添加或减少月数
	 * @return
	 */

	public static Date addMonths(int addMonth) {
		return YXDate.addMonths(new Date(), addMonth);
	}

	/**
	 * 一天的最后一刻
	 * 
	 * @param date
	 *            日期源
	 * @return 处理后的日期
	 */
	public static Date getTimeDayLastSecond(Date date) {
		
		if(date==null)
			return null;
		
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {

			return sdf.parse(dateStr + " 23:59:59");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;

	}

	/**
	 * 一天的第一刻
	 * 
	 * @param date
	 *            日期源
	 * @return 处理后的日期
	 */
	public static Date getTimeDayFirstSecond(Date date) {
		
		if(date==null)
			return null;

		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {

			return sdf.parse(dateStr + " 00:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	

	
	
	
	
	public static Date addSeconds(int second){
		return YXDate.addSeconds(new Date(), second);
		
	}

	/**
	 * 日期加秒数
	 * @param date
	 * @param second
	 * @return
	 */
	
	public static Date addSeconds(Date date,int second){
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.SECOND, second);

		return gc.getTime();
		
	}

	/**
	 * 日期加天数
	 * 
	 * @param date
	 *            日期源
	 * @param dayNum
	 *            需要变动的天数
	 * @return 处理后的日期
	 */
	public static Date addDay(Date date, int dayNum) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.DATE, dayNum);

		return gc.getTime();
	}

	/**
	 * 日期加天数
	 * 
	 * @param dayNum
	 *            需要变动的天数
	 * @return 处理后的日期
	 */
	public static Date addDay(int dayNum) {
		return YXDate.addDay(new Date(), dayNum);

	}

	/**
	 * 两个日期的相差天数
	 * 
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            截止日期
	 * @return
	 */
	public static int getDaySub(Date beginDate, Date endDate) {
		int day = 0;
		try {
			day = NumberUtil.parseInt((endDate.getTime() - beginDate.getTime())
					/ (24 * 60 * 60 * 1000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day + 1;
	}

	public static int getMonthSub(Date startDate, Date endDate) {
		Calendar calStart = new GregorianCalendar();
		calStart.setTime(startDate);
		Calendar calEnd = new GregorianCalendar();
		calEnd.setTime(endDate);
		int c = (calEnd.get(Calendar.YEAR) - calStart.get(Calendar.YEAR)) * 12
				+ calEnd.get(Calendar.MONTH) - calStart.get(Calendar.MONTH);
		return c;
	}

	public static Date getDate2TimeStamp(String timestampString) {

		Long timestamp = Long.valueOf(Long.parseLong(timestampString));
		return new Date(timestamp.longValue());

	}

	public static String getFormatDate2String(Date date) {

		return getFormatDate2String(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getFormatDate2String(Date date, String formatStr) {
		if (date == null)
			return "";

		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);

		return sdf.format(date);

	}
	
	
	
	public static Date getFormatDate(Date date,String formatStr){
		
		
		   	return YXDate.getString2Date(YXDate.getFormatDate2String(date),formatStr);
		
		
	}
	
	
	
	
	public static Date getString2Date(String dateString,String formatStr){
		
		try  
		{  
		    SimpleDateFormat sdf = new SimpleDateFormat(formatStr);  
		   	return sdf.parse(dateString);  
		}  
		catch (ParseException e)  
		{  
		   
		}  
		return new Date();
		
	}
	
	public static int compareDay(Date startDate,Date endDate){
		
		String startDateStr = new SimpleDateFormat("yyyy-MM-dd").format(startDate);		
		String endDateStr = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 try {
			startDate=sdf.parse(startDateStr);
			endDate=sdf.parse(endDateStr);
			
			return startDate.compareTo(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	
		return -1;
		
	}
	
	/**
	 * 获取前几天的时间
	 * @param before：前几天
	 * @return
	 */
	public static Date getBeforeDate(int before){
		Calendar   c   =   Calendar.getInstance(); 
		c.add(Calendar.DAY_OF_MONTH, before);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mDateTime=formatter.format(c.getTime());
		Date date = null;
		try {
			date = formatter.parse(mDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 获取当前月的第一天
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String getFisrtDayOfMonth() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String firstDay = sdf.format(new Date());
		return firstDay+"-01";
	}
	/**
	 * 获取当前日期
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String getNowOfMonth() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	
}
