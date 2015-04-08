package com.sunrise.base.signUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	public final static SimpleDateFormat dataTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
	
	/**
	 *(yyyy-MM-dd HH:mm:ss)
	 */
	public static DateFormat getDateTimeFormat() {
		dataTime.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return dataTime;
	}
	
	/**
	 *(yyyy-MM-dd)
	 */
	public static DateFormat getDateFormat() {
		date.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return date;
	}
	
	/**
	 *(HH:mm:ss)
	 */
	public static DateFormat getTimeFormat() {
		time.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return time;
	}
	
	/**
	 *自定义日期格式数据
	 */
	public static DateFormat getSelfFormat(String format) {
		SimpleDateFormat dfTemp = new SimpleDateFormat(format);
		dfTemp.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return dfTemp;
	}
	
	/**
	 * now datetime(yyyy-MM-dd HH:mm:ss)
	 */
	public static String getFullDateTime() {
		SimpleDateFormat dfTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dfTemp.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Date dateTemp = new Date();
		return dfTemp.format(dateTemp);
	}

	/**
	 * datetime(yyyy-MM-dd HH:mm:ss) of datePara
	 */
	public static String getFullDateTime(Date datePara) {
		if(datePara==null){
			return "";
		}
		SimpleDateFormat dfTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dfTemp.format(datePara);
	}

	/**
	 * datetime(yyyy-MM-dd HH:mm:ss) of datePara
	 */
	public static String getKenDateTime(Date datePara) {
		SimpleDateFormat dfTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return dfTemp.format(datePara);
	}

	// 例如:输入realateDate(2002-08-09,-2)将返回"2002-08-07"的字符串
		public static String relateDate(java.util.Date date, int day) {
			long millisec = 0;
			final long oneDayMillises = 24 * 60 * 60 * 1000;
			millisec = date.getTime() + oneDayMillises * day;
			java.util.Date date2 = new java.util.Date(millisec);
			Calendar date1 = Calendar.getInstance();
			date1.setTime(date2);
			String year = String.valueOf(date1.get(Calendar.YEAR));
			String month = String.valueOf(date1.get(Calendar.MONTH) + 1);
			String day1 = String.valueOf(date1.get(Calendar.DAY_OF_MONTH));
			if (month.length() == 1)
				month = "0" + month;
			if (day1.length() == 1)
				day1 = "0" + day1;
			String weekStartDay = year + "-" + month + "-" + day1;
			return weekStartDay;
		}

		
	/**
	 * now date(yyyy-MM-dd)
	 */
	public static String getDate() {
		SimpleDateFormat dfTemp = new SimpleDateFormat("yyyy-MM-dd");
		dfTemp.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Date dateTemp = new Date();
		return dfTemp.format(dateTemp);
	}

	/**
	 * date(yyyy-MM-dd) of datePara
	 */
	public static String getDate(Date datePara) {
		SimpleDateFormat dfTemp = new SimpleDateFormat("yyyy-MM-dd");
		return dfTemp.format(datePara);
	}

	/**
	 * now time(HH:mm:ss)
	 */
	public static String getTime() {
		SimpleDateFormat dfTemp = new SimpleDateFormat("HH:mm:ss");
		dfTemp.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Date dateTemp = new Date();
		return dfTemp.format(dateTemp);
	}

	/**
	 * time(HH:mm:ss) of datePara
	 */
	public static String getTime(Date datePara) {
		SimpleDateFormat dfTemp = new SimpleDateFormat("HH:mm:ss");
		return dfTemp.format(datePara);
	}
	
	/**
	 * now time(yyyyMMddHHmmss)
	 */	
	public static String getTimes() {
		SimpleDateFormat dfTemp = new SimpleDateFormat("yyyyMMddHHmmss");
		dfTemp.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Date dateTemp = new Date();
		return dfTemp.format(dateTemp);
	}	
	
	/**
	 * datetime(yyyy-MM-dd HH:mm:ss) of datePara
	 */
	public static String getShowStdTime(Date datePara) {
		if(datePara==null){
			return "";
		}
		SimpleDateFormat dfTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return dfTemp.format(datePara);
	}

	/**
	 * format date time from string.
	 * 
	 * @param dateString
	 *            string of the date. yyyyMMddHHmmss
	 * @return date
	 */
	public static Date formatDateyyyyMMddHHmmss(String dateString) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dt = null;
		try {
			dt = format.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
		return dt;
	}
	
	/**
	 * format date time from string.
	 * 
	 * @param dateString
	 *            string of the date yyyy-MM-dd HH:mm:ss
	 * @return date
	 */
	public static Date formatDateTimeFull(String dateString) {
		Date dt = null;
		try {
			dt = dataTime.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
		return dt;

	}
	
	/**
	 * format date time from string.
	 * 
	 * @param dateString
	 *            string of the date.
	 * @return date
	 */
	public static Date formatDateTimeKen(String dateString) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dt = null;
		try {
			dt = format.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
		return dt;

	}
	
	/**
	 * format date to integer
	 * 
	 * @param date
	 * 	
	 * @return Integer:
	 */
	public static Integer formatDate2Int(Date date){
	   SimpleDateFormat sp=new SimpleDateFormat("yyyyMMdd");
	   Integer dayInt = null;
	   try{
		   String dayStr = sp.format(date);//获取昨天日期
		   dayInt = Integer.parseInt(dayStr);    
	   }catch(Exception e){
		   return null;
	   }
	   return dayInt;
	}
	
	/**
	 * format date to integer
	 * 
	 * @param date
	 * 	
	 * @return Integer:
	 */
	public static Integer formatDateStr2Int(String dateStr){
	   Integer dayInt = null;
	   try{
		   dateStr = dateStr.replaceAll("-", "");
		   dayInt = Integer.parseInt(dateStr);   
	   }catch(Exception e){
		   return null;
	   }
	   return dayInt;
	}
	

	/**
	 * @Description：获取昨天的日期（格式：yyyyMMdd）
	 * @return String:
	 */
	public static String getYesterday(){
		//昨天的信息
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		return yesterday;
	}
	
	/**
	 * @Description：获取昨天的日期（格式：yyyy-MM-dd）
	 * @return String:
	 */
	public static String getYesterday(String fmt){
		if(StringUtils.isBlank(fmt)){
			fmt = "yyyy-MM-dd";
		}
		//昨天的信息
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat(fmt).format(cal.getTime());
		return yesterday;
	}
	
	/**
	 * format date time from string.
	 * 
	 * @param dateString
	 *            string of the date.
	 * @return date
	 */
	public static Date formatDate(String dateString) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = format.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
		return dt;

	}
	
	/**
	 * 
	 * @Description：计算两个时间段时间差
	 * @param d1	开始时间
	 * @param d2	结束时间
	 * @return long:
	 */
	public static long diffDate(Date d1,Date d2){
		long diff = d2.getTime() - d1.getTime();
	    long days = diff / (1000 * 60 * 60 * 24);
	    return days;
	}
	
	public static final Integer DATE_FIELD_YEAR = 1;
	public static final Integer DATE_FIELD_MONTH = 2;
	public static final Integer DATE_FIELD_WEEK = 3;
	public static final Integer DATE_FIELD_DAY = 5;
	
	/**
	 * 时间加减法
	 * gc.add(1,-1)表示年份减一.
	 * gc.add(2,-1)表示月份减一.
	 * gc.add(3.-1)表示周减一.
	 * gc.add(5,-1)表示天减一.
	 * @Description：
	 * @param date
	 * @param day
	 * @return: 
	 * @return Date:
	 */
	public static Date plusOrMinusDate(Date date,Integer field,int day) {
		GregorianCalendar gc=new GregorianCalendar(); 
		gc.setTime(date); 
		gc.add(field,day); 
		return gc.getTime();
	}
	
	/**
	 * 根据秒数，获取显示tip
	 * @Description：
	 * @param timeDiff
	 * @return: 
	 * @return String:
	 */
	public static String getEndTips(long timeDiff){
		String tipStr = "";
		long day = timeDiff/( 24  * 60 *  60);
		tipStr += day > 0 ? (day + "天") : "";
		timeDiff = timeDiff % ( 24  * 60 *  60);
		long hour = timeDiff/( 60 *  60);
		tipStr += hour > 0 ? (hour + "时") : "";
		timeDiff = timeDiff % (60 * 60);
		long minuts = timeDiff / 60;
		tipStr += minuts > 0 ? (minuts + "分") : "";
		long second = timeDiff - (minuts * 60);
		tipStr += second > 0 ? (second + "秒") : "";
		return tipStr;
	}
	/*
	 * 
	 */
	public static String getDefFormatDate(String inputDate,String inputDateFormat,String outputDateFormat) throws ParseException {
		SimpleDateFormat inDF = new SimpleDateFormat(inputDateFormat);
		Date d = inDF.parse(inputDate);
		SimpleDateFormat outDF = new SimpleDateFormat(outputDateFormat);
		String outDate = outDF.format(d);
		return outDate;
	} 
	
	/**
	 * 得到两个日期之间的天数差，包括开始和结束日期(如：beginCalender=2007-10-01，endCalendar=2007-10-20
	 * 结果为：20)
	 * 
	 * @param beginCalender
	 *            开始日期(小的)
	 * @param endCalendar
	 *            结束日期(大的)
	 * @return
	 */
	public static Long getDifferenceDays(Date beginDay, Date endDay) {
		Calendar beginCalender = Calendar.getInstance();
		beginCalender.setTime(beginDay);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDay);
		
		Long days = (endCalendar.getTimeInMillis() - beginCalender
				.getTimeInMillis())
				/ (24 * 60 * 60 * 1000);
		days = days + 1;
		return days;
	}
	
	/**
	 * 得到两个日期之间的分钟差，包括开始和结束日期(如：beginCalender=2007-10-01，endCalendar=2007-10-20
	 * 结果为：20)
	 * 
	 * @param beginCalender
	 *            开始日期(小的)
	 * @param endCalendar
	 *            结束日期(大的)
	 * @return
	 */
	public static Long getDifferenceMinute(Date beginDay, Date endDay) {
		Calendar beginCalender = Calendar.getInstance();
		beginCalender.setTime(beginDay);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDay);
		
		Long minute = (endCalendar.getTimeInMillis() - beginCalender
				.getTimeInMillis())
				/ (60 * 1000);
		//minute = minute + 1;
		return minute;
	}
	
	/**
	 * 得到当前日期前或后N分钟的日期(minute为正数为后n分钟，为负数表示前n分钟)
	 * 
	 * @param months
	 *            月数
	 * @return
	 */
	public static Date getPreviousOrNextMinuteOfDateTime(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}
	
	/**
	 * 得到当前日期前或后N月的日期(months为正数为后n月，为负数表示前n月)
	 * 
	 * @param months
	 *            月数
	 * @return
	 */
	public static Date getPreviousOrNextMonthsOfDateTime(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	
	public static void main(String[] args) {
		//时间加减算法
//		Date da = plusOrMinusDate(new Date(),DATE_FIELD_DAY, 3);
//		System.out.println(da);
		
		
		//System.out.println(getEndTips(60));  
		
		DateFormat dateFormat = DateUtil.getSelfFormat("yyyyMMdd");
		DateFormat monthFormat = DateUtil.getSelfFormat("yyyyMM");
		
		Calendar calendar = Calendar.getInstance();
		//如果是1号，就显示上个月的1-31
		/*if(calendar.get(Calendar.DATE)==1){
			calendar.add(Calendar.MONTH, -1);
		}
		calendar.set(Calendar.DATE, 1);
		System.out.println(dateFormat.format(calendar.getTime()));
		*/
		
		calendar = Calendar.getInstance();
		//calendar.add(Calendar.MONTH, -1);//上个月
		//System.out.println(monthFormat.format(calendar.getTime()));
		
		//calendar.set(Calendar.MONTH, 0);//1月
		//System.out.println(monthFormat.format(calendar.getTime()));
		
		
		System.out.println(getDifferenceMinute(calendar.getTime(),new Date() ));
		
		
	}
	
	
}
