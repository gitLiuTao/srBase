package com.sunrise.base;

/**
 * 系统相关定义
 * 
 * @author Daiming
 * 
 */
public class SystemConstants {

	// 日期类型定义
	public static final String DATE_FORMAT_SECONDS = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT_CN = "yy年MM月dd日HH:mm:ss";

	public static final String CONTEXTPATH = "html\\portlet\\scjd\\resource\\";
	public static final String XLSX = ".xlsx";
	public static final String XLS = ".xls";
	public static final String APPLICATIONEXCEL = "application/vnd.ms-excel";
	public static final String UTF = "UTF-8";
	public static final String APPLICATIONEXCELUTF = "application/vnd.ms-excel;charset=UTF-8";

	/**
	 * 是否开启短信
	 */
	public static Boolean IS_OPEN_SMS = false;

	/**
	 * 是否调试模式
	 */
	public static Boolean IS_DEBUG = true;

	public static Integer CODE_TIMEOUT = 30;// 单位秒

}
