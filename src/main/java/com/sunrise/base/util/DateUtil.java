package com.sunrise.base.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public final class  DateUtil {
	public final static String DATEFORMAT_DAY = "yyyy-MM-dd";
	public final static String DATEFORMAT_MONTH = "yyyy-MM";
	public final static String STRING_DATEFORMAT_MONTH = "yyyyMM";
	public final static String STRING_DATEFORMAT_YEAR = "yyyy";

	public final static String STRING_DATEFORMAT_DAY = "yyyyMMdd";
	public final static String DATEFORMAT_MINUTE = "yyyy-MM-dd HH:mm";
	public final static String DATEFORMAT_SECOND = "yyyyMMddHHmmss";

	public final static String FOC_DATEFORMAT_MINUTE = "yyyy-MM-dd HH.mm";

	public final static String HOUR_MIN = "HHmm";

	public final static String FOC_TEMP_DATA_DATE_FORMAT = "yyyy-MM-dd HH.mm.ss";

	public final static String PSRML_DATE_FORMATE = "ddMMMyy";

	public final static String LEG_TIMESTAMP_FORMATE = "yyyy-MM-dd HH:mm:ss";


	/**
	 * the the date now to string
	 * 
	 * @return the string date.
	 */
	public static String getStringNow() {
		try {
			Date date = new Date();
			return new SimpleDateFormat(STRING_DATEFORMAT_DAY).format(date);
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * get now date.
	 * 
	 * @return now date string.
	 */
	public static String getNow() {
		try {
			Date date = new Date();
			return new SimpleDateFormat(DATEFORMAT_DAY).format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * get tomorrow date.
	 * 
	 * @return tomorrow date.
	 */
	public static String getMorrow() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 1);
			return new SimpleDateFormat(DATEFORMAT_DAY).format(calendar
					.getTime());
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * get the date of before yesterday.
	 * 
	 * @return the string of date of before yesterday.
	 */
	public static String getBeforeYesterDay() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -2);
			return new SimpleDateFormat(DATEFORMAT_DAY).format(calendar
					.getTime());
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 昨天
	 * 
	 * @return
	 */
	public static String getYesterDay() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
			return new SimpleDateFormat(DATEFORMAT_DAY).format(calendar
					.getTime());
		} catch (Exception e) {
			return "";
		}
	}
	public static Date getDateBefor(Date date, int field, int before) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(field, before);
			return calendar.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * get now time.
	 * 
	 * @return now time.
	 */
	public static String getNowTime() {
		try {
			Date date = new Date();
			return new SimpleDateFormat(DATEFORMAT_MINUTE).format(date);
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * get now time second.
	 * 
	 * @return the second of now time.
	 */
	public static String getNowTimeSecond() {
		try {
			Date date = new Date();
			return new SimpleDateFormat(DATEFORMAT_SECOND).format(date);
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * get the string from date.
	 * 
	 * @param date
	 *            date.
	 * @param formatStr
	 *            string date.
	 * @return
	 */
	public static String getDateString(Date date, String formatStr) {
		if (date == null) {
			return null;
		} else {
			SimpleDateFormat des = new SimpleDateFormat(formatStr);
			String result = des.format(date);
			return result;
		}
	}

	/**
	 * get the moth string.
	 * 
	 * @param date
	 *            specified date.
	 * @return string of moth.
	 */
	public static String getMothString(Date date) {
		String result = null;
		if (date == null) {
			result = null;
		} else {
			SimpleDateFormat des = new SimpleDateFormat("yyyy-MM");
			result = des.format(date);
		}
		return result;
	}

	/**
	 * get string date from date.
	 * 
	 * @param date
	 *            specified date.
	 * @return string
	 */
	public static String getDateString(Date date) {
		return getDateString(date, DATEFORMAT_DAY);
	}

	/**
	 * compare date time.
	 * 
	 * @param firstDateTime
	 *            first date time.
	 * @param secondDateTime
	 *            second date time.
	 * @return the result of compare.
	 * @throws ParseException
	 */
	public static long compareDateTime(String firstDateTime,
			String secondDateTime) throws ParseException {
		return format(firstDateTime).getTime()
				- format(secondDateTime).getTime();
	}

	/**
	 * format date time from string.
	 * 
	 * @param dateString
	 *            string of the date.
	 * @return date
	 */
	public static Date formatDateTime(String dateString) {

		SimpleDateFormat format = new SimpleDateFormat(DATEFORMAT_MINUTE);
		Date dt = null;
		try {
			dt = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;

	}
	
	/**
	 * format string to date.
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date formatDateHHMM(String dateString) {

		SimpleDateFormat format = new SimpleDateFormat(FOC_DATEFORMAT_MINUTE);
		Date dt = null;
		try {
			dt = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;

	}
	
	/**
	 * format string to date.
	 * 
	 * @param dateString
	 * @return
	 */
	public static Timestamp formatTimestamp(String dateString) {
		Date date = formatDateHHMM(dateString);
		if (date != null) {
			return new Timestamp(date.getTime());
		} else {
			return null;
		}
	}
	
	public static String getCurrTimestamp() {
		SimpleDateFormat format = new SimpleDateFormat(LEG_TIMESTAMP_FORMATE);
		String dt = null;
		try {
			dt = format.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	/**
	 * format string to date.
	 * 
	 * @param dateString
	 * @return
	 */
	public static Timestamp formatTimestampDateTime(String dateString) {
		Date date = formatDateTime(dateString);
		if (date != null) {
			return new Timestamp(date.getTime());
		} else {
			return null;
		}
	}

	/**
	 * format string to date.
	 * 
	 * @param dateString
	 * @return
	 */
	public static Long formateLegTime(String dateString) {
		Long result = null;
		SimpleDateFormat format = new SimpleDateFormat(FOC_DATEFORMAT_MINUTE);
		Date dt = null;
		if (dateString != null) {
			try {
				dt = format.parse(dateString);
				result = Long.valueOf(dt.getTime());
			} catch (ParseException e) {
			}
		}
		return result;
	}

	/**
	 * format string to date.
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date formatDate(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat(DATEFORMAT_DAY);
		Date dt = null;
		try {
			dt = format.parse(dateString);
		} catch (ParseException e) {
			dt = null;
		}
		return dt;

	}

	/**
	 * format string to date.
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date formatML(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat(PSRML_DATE_FORMATE,
				Locale.US);
		Date dt = null;
		try {
			dt = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			dt = null;
		}
		return dt;
	}
	

	/**
	 * format string to date.
	 * 
	 * @param dateString
	 *            date string
	 * @return date.
	 */
	public static Date format(String dateString) {

		SimpleDateFormat format = new SimpleDateFormat(
				FOC_TEMP_DATA_DATE_FORMAT);
		Date dt = null;
		try {
			dt = format.parse(dateString);
		} catch (Exception e) {
			return null;
		}
		return dt;

	}

	

	/**
	 * get date string
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String getDateString4Transfer(Date date, String formatStr) {
		if (date == null) {
			return null;
		} else {
			SimpleDateFormat des = new SimpleDateFormat(formatStr, Locale.US);
			String result = des.format(date);
			return result;
		}
	}

	/**
	 * format string to date.
	 * 
	 * @param dateString
	 * @param dateFormat
	 * @return
	 */
	public static Date formatDate(String dateString, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date dt = null;
		try {
			dt = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;

	}

	/**
	 * format string to date.
	 * 
	 * @param dateString
	 * @param dateFormat
	 * @return
	 */
	public static String formatDateStr(Object dateString, String dateFormat) {
		String dateStr = "";
		if (dateString != null) {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			Date dt = null;
			try {
				dt = format.parse(dateString.toString());
				dateStr = format.format(dt);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dateStr;

	}

	/**
	 * format string to date.
	 * 
	 * @param dateString
	 * @param dateFormat
	 * @return
	 */
	public static Timestamp formatTimestamp(String dateString, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date dt = null;
		try {
			dt = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return new Timestamp(dt.getTime());
	}

	/**
	 * 将时间字符串从原格式转化为目标格式
	 * 
	 * @param dateTimeStr
	 *            ,时间字符串
	 * @param srcPattern
	 *            ，原格式
	 * @param destPattern
	 *            ，目标格式
	 * @return
	 */
	public static String dateTimeStrChangePattern(String dateTimeStr,
			String srcPattern, String destPattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(srcPattern);
		Date date;

		try {
			date = sdf.parse(dateTimeStr);
			sdf.applyPattern(destPattern);

			return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();

			return null;
		}
	}
	
	/**
	 * format string to date.
	 * 
	 * @param src
	 * @return
	 */
	public static Date secondFormatDate(String src) {
		SimpleDateFormat format = new SimpleDateFormat(
				FOC_TEMP_DATA_DATE_FORMAT);
		Date dt = null;
		try {
			if (src != null)
				dt = format.parse(src);
			else
				dt = null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
	/**
	 * get day of week.
	 * 
	 * @param SUN_FST_DAY_OF_WEEK
	 * @return
	 */
	public static int getDayOfWeek(int SUN_FST_DAY_OF_WEEK) {
		if (SUN_FST_DAY_OF_WEEK > 7 || SUN_FST_DAY_OF_WEEK < 1)
			return 0;
		if (SUN_FST_DAY_OF_WEEK == 1)
			return 7;
		return SUN_FST_DAY_OF_WEEK - 1;
	}

	/**
	 * to eterm date.
	 * 
	 * @param date
	 * @return
	 */
	public static String toEtermDate(Date date) {

		String format = "ddMMMyy";

		DateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);

		String str = df.format(date);

		return str;
	}
	
	public static String getNowTime(String dateTime) {
		try {
			DateFormat df = new SimpleDateFormat(DATEFORMAT_MINUTE);
			return df.format(df.parse(dateTime));
		} catch (Exception e) {
			return "";
		}

	}
	
	/**
	 * 取得标准时间
	 * @param aData
	 * @return
	 */
	public static String getShowStdTime(Date aData) {
		if(aData==null){
			return "";
		}
		try {
			DateFormat df = new SimpleDateFormat(DATEFORMAT_MINUTE);
			return df.format(aData);
		} catch (Exception e) {
			return "";
		}

	}
	
	
	/**
	 * exchange time zone
	 * 
	 * @param origin
	 * @param offset
	 * @return
	 */
	public static String exchangeTimeZone(String origin, int offset) {
		if (origin == null)
			return null;

		Date date = format(origin);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, offset);

		date = cal.getTime();

		SimpleDateFormat formatter = new SimpleDateFormat(
				FOC_TEMP_DATA_DATE_FORMAT);

		String result = formatter.format(date);

		return result;
	}
	
	/**
	 * compare date.
	 * 
	 * @param flightDate
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static long compareDate(String flightDate, String date)
			throws ParseException {
		return (formatDate(flightDate).getTime() - formatDate(date).getTime())
				/ (1000 * 60 * 60 * 24);
	}
	/**
	 * 构造上周开始时间， 从零点零分开始 例如：今天为2012年10月30日 则返回为：2012年10月22日零时零分零秒
	 * 
	 * @return
	 */
	public static Date getPreWeekStartDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) + 5;
		calendar.add(Calendar.DATE, -day_of_week);
		Date start = calendar.getTime();// 上周开始时间
		return start;
	}

	/**
	 * 按照传入的格式构造周的开始时间和结束时间字符串 例如： 20121105-20121111
	 * 
	 * @return
	 */
	public static String createPreWeekStr(Date date, String split, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) + 5;
		calendar.add(Calendar.DATE, -day_of_week);
		Date start = calendar.getTime();// 上周开始时间
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.add(Calendar.DATE, 6);
		Date end = calendar.getTime();// 上周结束时间
		return DateUtil.getDateString(start, format) + split
				+ DateUtil.getDateString(end, format);
	}

	/**
	 * 按照传入的格式构造本周的开始时间和结束时间字符串 例如： 20121105-20121111
	 * 
	 * @return
	 */
	public static String createWeekStr(Date date, String split, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 2;
		calendar.add(Calendar.DATE, -day_of_week);
		Date start = calendar.getTime();// 上周开始时间
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.add(Calendar.DATE, 6);
		Date end = calendar.getTime();// 上周结束时间
		return DateUtil.getDateString(start, format) + split
				+ DateUtil.getDateString(end, format);
	}

	/**
	 * 构造上月开始时间， 从零点零分开始 例如：今天为2012年10月30日 则返回为：2012年9月1日零时零分零秒
	 * 
	 * @return
	 */
	public static Date getCurrentMonthFirstDay() {
		// 设置结束时间为当月的第一天 ，零点零分零秒
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 读取上月第一天
	 * 
	 * @return
	 */
	public static Date getPreMonthFirstDay() {
		// 设置结束时间为上月的第一天 ，零点零分零秒
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	// 获得当前日期与本周日相差的天数
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// 获取当天日期
	public static String getToday() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");// 可以方便地修改日期格式
		String today = sdf.format(now);
		return today;
	}

	// 获取当天日期 带-
	public static String getToday_() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 可以方便地修改日期格式
		String today = sdf.format(now);
		return today;
	}

	// 获取本周一日期
	public static String getCurrentMondayOfWeek() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	// 获取本周日日期
	public static String getCurrentWeekOfSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	// 获得上周星期一的日期
	public static String getPreviousMondayOFWeek() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * -1);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	// 获取上周日日期
	public static String getPreviousWeekOfSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + -1);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	// 获取本月
	public static String getCurrentMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获取上月
	public static String getPrevioustMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获取上月
	// 返回 yyyy-MM
	public static String getPrevioustFormatMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 上周
	// 返回 yyyy-ww
	public static String getPreviousFormatWeekOfYear() {
		Calendar c = Calendar.getInstance();
		int yeer = c.get(Calendar.YEAR);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		return yeer + "-" + String.format("%02d", week - 1);
	}

	// 获取本月
	public static String getCurrentMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	public static String getCurrentMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 减一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获取上月
	public static String getPrevioustMonth8() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获取本年
	public static String getCurrentYear() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		return years;
	}

	// 获取本年的第一月
	public static String getCurrentYearMonthFirst() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		return years + "01";
	}

	// 获取去年
	public static String getPreviousYear() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		years_value--;
		return years_value + "";
	}

	// 获取去年最后一月
	public static String getPreviousYearMonthEnd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		years_value--;
		return years_value + "12";
	}

	// 获取本年第几周6位
	public static String getCurrentWeekOfYear() {
		Calendar c = Calendar.getInstance();
		int yeer = c.get(Calendar.YEAR);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		return yeer + "" + String.format("%02d", week);
	}

	public static String getCurrentMonthOfYear() {
		Calendar c = Calendar.getInstance();
		int yeer = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		if (month < 10) {
			return yeer + "0" + month;
		} else {
			return yeer + "" + month;
		}
	}

	// 获取本年第几周的前一周6位
	public static String getPreviousWeekOfYear() {
		Calendar c = Calendar.getInstance();
		int yeer = c.get(Calendar.YEAR);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		return yeer + "" + String.format("%02d", week - 1);
	}

	public static long getYesterdayLong() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -1);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return Long.parseLong(dateString.replaceAll("-", ""));
	}

	public static Date getYesterdayDate() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -1);
		date = calendar.getTime();
		return date;
	}

	public static String getYesterdayStr() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -1);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	public static long[] getWeekLong() {
		long[] week = new long[2];
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int weekday = c.get(7) - 2;
		c.add(5, -weekday);
		week[0] = Long.parseLong(formatter.format(c.getTime()).replaceAll("-",
				""));
		c.add(5, 6);
		week[1] = Long.parseLong(formatter.format(c.getTime()).replaceAll("-",
				""));
		return week;
	}



	/**
	 * 
	 * @param weekStr
	 *            201309 第九周
	 * @return
	 * @throws ParseException
	 */
	public static long[] getWeekLong(String weekStr) throws ParseException {
		long[] week = new long[2];
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat weekformatter = new SimpleDateFormat("yyyyww");
		Date date = weekformatter.parse(weekStr);
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		int weekday = c.get(7) - 2;
		c.add(5, -weekday);
		week[0] = Long.parseLong(formatter.format(c.getTime()).replaceAll("-",
				""));
		c.add(5, 6);
		week[1] = Long.parseLong(formatter.format(c.getTime()).replaceAll("-",
				""));
		return week;
	}
	
	public static long[] getMonthLong() {
		long[] month = new long[2];
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, 1);// 设为当前月的1号
		month[0] = Long.parseLong(formatter.format(c.getTime()).replaceAll("-",
				""));
		c.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		c.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		month[1] = Long.parseLong(formatter.format(c.getTime()).replaceAll("-",
				""));
		return month;
	}

	public static long[] sevenDaysBeforeToToday() {
		long[] day = new long[2];
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		day[1] = getYesterdayLong();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -7);
		date = calendar.getTime();
		String dateString = formatter.format(date);
		day[0] = Long.parseLong(dateString.replaceAll("-", ""));
		return day;
	}

	public static long getMonthEndDate(String month) throws ParseException {
		long dateLong;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(month);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		String dateString = day.format(cal.getTime());
		dateLong = Long.parseLong(dateString.replaceAll("-", ""));
		return dateLong;

	}

	public static String getMonthEndDateStr(String month) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(month);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		String dateString = day.format(cal.getTime());
		return dateString;

	}

	public static String getMonthForEndDate(String date) throws ParseException {
		String year = date.substring(0, 4);
		String month = date.substring(5);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return date + "-" +String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号
	}

	public static String[] sevenDaysBeforeToTodayStr() {
		String[] day = new String[2];
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		day[1] = getToday_();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -6);
		date = calendar.getTime();
		String dateString = formatter.format(date);
		day[0] = dateString;
		return day;
	}

	public static long sevenDayBefore() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -7);
		date = calendar.getTime();

		return Long.parseLong(formatter.format(date));
	}

	public static long[] thirtyDaysBeforeToToday() {
		long[] day = new long[2];
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		day[1] = getYesterdayLong();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -30);
		date = calendar.getTime();
		String dateString = formatter.format(date);
		day[0] = Long.parseLong(dateString.replaceAll("-", ""));
		return day;
	}

	public static String[] thirtyDaysBeforeToTodayStr() {
		String[] day = new String[2];
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		day[1] = getYesterdayStr();
		day[1] = getToday_();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -29);
		date = calendar.getTime();
		String dateString = formatter.format(date);
		day[0] = dateString;
		return day;
	}

	public static long[] lastThreeMonthLong() {
		Date date = new Date();
		long[] day = new long[2];
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		day[1] = Long.parseLong(formatter.format(date).replaceAll("-", ""));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 2);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		day[0] = Long.parseLong(formatter.format(calendar.getTime()).replace(
				"-", ""));
		return day;
	}

	public static String[] lastThreeMonthStr() {
		Date date = new Date();
		String[] day = new String[2];
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		day[1] = formatter.format(date);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 2);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		day[0] = formatter.format(calendar.getTime());
		return day;
	}

	public static long[] lastSixMonthLong() {
		Date date = new Date();
		long[] day = new long[2];
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		day[1] = Long.parseLong(formatter.format(date).replaceAll("-", ""));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 5);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		day[0] = Long.parseLong(formatter.format(calendar.getTime()).replace(
				"-", ""));
		return day;
	}

	public static String[] lastSixMonthStr() {
		Date date = new Date();
		String[] day = new String[2];
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		day[1] = formatter.format(date);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 5);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		day[0] = formatter.format(calendar.getTime());
		return day;
	}

	public static String getDateStr(long dateId, String dateStyle,
			String resultDateStyle) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(dateStyle);
		Date date = formatter.parse(String.valueOf(dateId));
		SimpleDateFormat formatter2 = new SimpleDateFormat(resultDateStyle);
		return formatter2.format(date);
	}

	/**
	 * 根据日期活的本年的第几周
	 * 
	 * @param startDate
	 * @return
	 */
	public static String getWeekOfYearByStartDate(String startDate) {
		SimpleDateFormat format = new SimpleDateFormat(STRING_DATEFORMAT_DAY);
		Date dt = null;
		try {
			dt = format.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		int year = calendar.get(Calendar.YEAR);
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		return year + "" + String.format("%02d", week);
	}

	/**
	 * 根据周一日期活的前一个周一日期
	 * 
	 * @param startDate
	 * @return
	 */
	public static String getDateByStartDate(String startDate, int days) {
		SimpleDateFormat format = new SimpleDateFormat(DATEFORMAT_DAY);
		Date dt = null;
		try {
			dt = format.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar cd = Calendar.getInstance();
		cd.setTime(dt);
		cd.add(Calendar.DATE, days);
		int year = cd.get(Calendar.YEAR);
		int month = cd.get(Calendar.MONTH) + 1;
		int day = cd.get(Calendar.DAY_OF_MONTH);
		return year + "" + month + "" + day;
	}
	public static String getPreDay(String date) {
		SimpleDateFormat format = new SimpleDateFormat(STRING_DATEFORMAT_DAY);
		Date dt = null;
		try {
			dt = format.parse(date);
		} catch (ParseException e) {
			dt = null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, -1);
		return String.valueOf(new SimpleDateFormat(STRING_DATEFORMAT_DAY)
				.format(calendar.getTime()));
	}

	public static String getNewDay(String date, int day) {
		SimpleDateFormat format = new SimpleDateFormat(STRING_DATEFORMAT_DAY);
		Date dt = null;
		try {
			dt = format.parse(date);
		} catch (ParseException e) {
			dt = null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, day);
		return String.valueOf(new SimpleDateFormat(STRING_DATEFORMAT_DAY)
				.format(calendar.getTime()));
	}

	public static String getNextDay(String date) {
		SimpleDateFormat format = new SimpleDateFormat(DATEFORMAT_DAY);
		Date dt = null;
		try {
			dt = format.parse(date);
		} catch (ParseException e) {
			dt = null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, +1);
		return String.valueOf(new SimpleDateFormat(DATEFORMAT_DAY)
				.format(calendar.getTime()));
	}
	public static String getWeek(String value) {
		String resultDay = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = df.parse("2013-03-22");
			java.util.Calendar c = new java.util.GregorianCalendar();
			c.setTime(date);
			c.add(c.DATE, -84);
			String month = "";
			String day = "";
			if ((c.get(c.MONTH) + 1) < 10) {
				month = "0" + (c.get(c.MONTH) + 1);
			} else {
				month = (c.get(c.MONTH) + 1) + "";
			}

			if (c.get(c.DATE) < 10) {
				day = "0" + c.get(c.DATE);
			} else {
				day = c.get(c.DATE) + "";
			}
			resultDay = c.get(c.YEAR) + "" + c.get(Calendar.WEEK_OF_YEAR);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultDay;
	}
	
	public static int getWeeksOfYear(int year) {
		int week = 0;
		int days = 365;
		int day = 0;
		// 判断是否闰年，闰年366天
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			days = 366;
		}
		// 得到一年所有天数然后除以7
		day = days % 7 > 0 ? week += 1 : week;
		// 得到余下几天如果有余则周+1，否则不加
		week += days / 7;
		// 得到多少周
		return week;
	}
	
	/**
	 * format date time from string.
	 * 
	 * @param dateString
	 *            string of the date.
	 * @return date
	 */
	public static Date formatLEGDateTime(String dateString) {

		SimpleDateFormat format = new SimpleDateFormat(LEG_TIMESTAMP_FORMATE);
		Date dt = null;
		try {
			dt = format.parse(dateString+":00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;

	}
	
	/**
	 * 前推几天
	 * @param dateStr
	 * @return
	 */
	public static int getDayTrend(String dateStr, int subtractDay){
        int preDay = -1;
        try {
        	DateFormat format = new SimpleDateFormat(STRING_DATEFORMAT_DAY);
            Calendar cal = Calendar.getInstance();
            cal.setTime(format.parse(dateStr));
            cal.add(Calendar.DAY_OF_YEAR, subtractDay);
            preDay = Integer.valueOf(format.format(cal.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preDay;
    }
	
	private static String weekFormat(int week){
		String newWeek = week + "";
		if(String.valueOf(week).length() == 1){
			newWeek = "0" + week;
		}
		return newWeek;
	}
	
	/**
	 * 前推几周
	 * @param dateStr
	 * @return
	 */
	public static int getWeekTrend(String dateStr, int subtractWeek){
		int preWeek = -1;
        try {
        	int year = Integer.valueOf(dateStr.substring(0, 4));
        	int week = Integer.valueOf(dateStr.substring(dateStr.length() - 2));
        	Calendar cal = new GregorianCalendar(year, Calendar.JANUARY, 1);
        	cal.add(Calendar.DATE, 7 * (week - 1));
        	cal.add(Calendar.WEEK_OF_YEAR, subtractWeek);
        	year = cal.get(Calendar.YEAR);
        	String newWeek = weekFormat(cal.get(Calendar.WEEK_OF_YEAR));
        	preWeek = Integer.valueOf((year + "") + newWeek); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preWeek;
    }
	
	/**
	 * 前推几月
	 * @param dateStr
	 * @return
	 */
	public static int getMonthTrend(String dateStr, int subtractMonth){
        int preMonth = -1;
        try {
        	DateFormat format = new SimpleDateFormat(STRING_DATEFORMAT_MONTH);
            Calendar cal = Calendar.getInstance();
            cal.setTime(format.parse(dateStr));
            cal.add(Calendar.MONTH, subtractMonth);
            preMonth = Integer.valueOf(format.format(cal.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preMonth;
    }
	
	/**
	 * 前推几年
	 * @param dateStr
	 * @return
	 */
	public static int getYearTrend(String dateStr, int subtractMonth){
        int preYear = -1;
        try {
        	DateFormat format = new SimpleDateFormat(STRING_DATEFORMAT_YEAR);
            Calendar cal = Calendar.getInstance();
            cal.setTime(format.parse(dateStr));
            cal.add(Calendar.YEAR, subtractMonth);
            preYear = Integer.valueOf(format.format(cal.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preYear;
    }
}
