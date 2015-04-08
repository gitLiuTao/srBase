package com.sunrise.base.util;

import org.apache.log4j.Logger;




/*******************************************************************************
 * logger
 *
 * @author James Chen
 * @email chenwu920@gmail.com
 * @version 1.0 2013-10-17
 * @since JDK1.5
 ******************************************************************************/
public class LogInvoker {
	private static boolean isInit = false;

	private static void init() {
		if (!LogInvoker.isInit) {
			LogInvoker.isInit = true;
		}
	}

	/**
	 * request logger
	 */
	public static Logger getRequestLogger(Class arg0) {
		init();
		Logger logger = Logger.getLogger("REQUEST." + arg0);
		return logger;
	}

	/**
	 * action logger
	 */
	public static Logger getActionLogger(Class arg0) {
		init();
		Logger logger = Logger.getLogger("ACTION." + arg0);
		return logger;
	}

	/**
	 * db logger
	 */
	public static Logger getDBLogger(Class arg0) {
		init();
		Logger logger = Logger.getLogger("DB." + arg0);
		return logger;
	}

	/**
	 * download logger
	 */
	public static Logger getDownloadLogger(Class arg0) {
		init();
		Logger logger = Logger.getLogger("DOWNLOAD." + arg0);
		return logger;
	}

	/**
	 * error logger
	 */
	public static Logger getErrorLogger(Class arg0) {
		init();
		Logger logger = Logger.getLogger("ERROR." + arg0);
		return logger;
	}

	/**
	 * system logger
	 */
	public static Logger getSystemLogger(Class arg0) {
		init();
		Logger logger = Logger.getLogger("SYSTEM." + arg0);
		return logger;
	}

	/**
	 * user-defined logger
	 */
	public static Logger getLogger(String prefix, Class arg0) {
		init();
		Logger logger = Logger.getLogger(prefix + "." + arg0);
		return logger;
	}
}
