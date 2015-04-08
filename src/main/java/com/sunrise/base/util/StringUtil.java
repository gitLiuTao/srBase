package com.sunrise.base.util;

import java.io.UnsupportedEncodingException;

/*******************************************************************************
 * some function about string
 * 
 * @author James Chen
 * @email chenwu920@gmail.com
 * @version 1.0 2013-10-12
 * @since JDK1.5
 ******************************************************************************/
public class StringUtil {
	public static final int SQL_BUFFER_SIZE = 1024;

	public static final int FIELDS_BUFFER_SIZE = 256;

	public static final int VALUES_BUFFER_SIZE = 1024;	
	/**
	 * convert ISO-8859-1 to gb2312
	 * 
	 * @param strSrc
	 *          source string
	 * @return result string
	 */
	public static String convert88591ToGB(String strSrc) {
		try {
			return new String(strSrc.getBytes("ISO-8859-1"), "gb2312");
		}// try
		catch (UnsupportedEncodingException e) {
			return null;
		}// catch
	}

	/**
	 * convert gb2312 to ISO-8859-1
	 * 
	 * @param strSrc
	 *          source string
	 * @return result string
	 */
	public static String convertGBTo88591(String strSrc) {
		try {
			return new String(strSrc.getBytes("gb2312"), "ISO-8859-1");
		}// try
		catch (UnsupportedEncodingException e) {
			return null;
		}// catch
	}

	/**
	 * replace string
	 * 
	 * @param strSource
	 *          source string
	 * @param strFrom
	 *          old string
	 * @param strTo
	 *          new string
	 * @return result string
	 */
	public static String replaceString(String strSource, String strFrom,
			String strTo) {
		if (null == strSource || null == strFrom || null == strTo) {
			return strSource;
		}
		String strDest = "";
		int intFromLen = strFrom.length();
		int intPos;
		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			strDest = strDest + strSource.substring(0, intPos);
			strDest = strDest + strTo;
			strSource = strSource.substring(intPos + intFromLen);
		}
		strDest = strDest + strSource;
		return strDest;
	}

	/**
	 * convert long string to html lines by <br>
	 * 
	 * @param strSource
	 *          source string
	 * @param intLength
	 *          char count on the line
	 * @return result string
	 */
	public static String convertStrToHtmlLines(String strSource, int intLength) {
		if (strSource.equals("")) {
			return "";
		}
		String strDest = "";
		int intStrLen = strSource.length();
		int intPos = intLength;
		int intLineCount = 1;
		if (intStrLen > 0) {
			intLineCount = intStrLen / intLength;
		}
		for (int i = 0; i < intLineCount; i++) {
			if (i == intLineCount - 1) {
				strDest = strDest + strSource.substring(i * intLength, intPos);
			} else {
				strDest = strDest + strSource.substring(i * intLength, intPos) + "<br>";
			}
			intPos = (i + 2) * intLength;
		}
		if ((intPos - intLength) < intStrLen) {
			strDest = strDest + "<br>"
					+ strSource.substring((intPos - intLength), intStrLen);
		}
		return strDest;
	}

	/**
	 * sub string from head
	 * 
	 * @param strSrc
	 *          source string
	 * @param intLen
	 *          sub string length
	 * @return result string
	 */
	public static String subStringHead(String strSrc, int intLen) {
		int intSrcLen = strSrc.length();
		if (intSrcLen <= intLen) {
			return strSrc;
		} else {
			String strTemp = "";
			for (int i = 0; i < intLen; i++) {
				strTemp = strTemp + String.valueOf(strSrc.charAt(i));
			}
			return strTemp;
		}
	}

	/**
	 * get file name from file path
	 * 
	 * @param prmPath
	 *          file path
	 * @return file name
	 */
	public static String getFileNameFromPath(String prmPath) {
		String strFileName = "";
		String strTemp = prmPath;
		int intIndex = 0;
		while (true) {
			intIndex = strTemp.indexOf("/");
			if (intIndex != -1) {
				strTemp = strTemp.substring(intIndex + 1, strTemp.length());
			} else {
				strFileName = strTemp;
				break;
			}
		}
		return strFileName;
	}

	public static String convertXMLSpecChar(String s) {
		if (s == null)
			return "";

		StringBuffer sb = new StringBuffer(1024);

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '\r':
				break;
			case '\n':
				sb.append("<br/>");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '\'':
				sb.append("&apos;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			default:
				sb.append(ch);
			}
		}

		return sb.toString();
	}
	

	/**
	 * 判断string是否为null 或者是blank。
	 * 
	 * @param value
	 *            string value
	 * @return value
	 */
	public static boolean isNullOrBlank(String value) {
		return value == null || "".equals(value.trim());
	}
}
