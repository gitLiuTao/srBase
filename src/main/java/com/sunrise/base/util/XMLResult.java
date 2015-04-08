package com.sunrise.base.util;

import java.util.Hashtable;

/*******************************************************************************
 * convert result date to xml
 * 
 * @author James Chen
 * @email chenwu920@gmail.com
 * @version 1.0 2013-10-19
 * @since JDK1.5
 ******************************************************************************/
public class XMLResult {
	// page size
	public static int PAGE_SIZE = 10;
	
	private static int buf_size_short = 256;
	private static int buf_size = 1024;
	public static int buf_size_long = 20480;

	public XMLResult() {
	}

	/**
	 * operate success?
	 * 
	 * @param prmSuccess
	 *          success(ture or false)
	 * @param prmErrorInfo
	 *          error info
	 * @return XML result
	 */
	public static String getInputResult(boolean prmSuccess, String prmErrorInfo) {
		StringBuffer xml = new StringBuffer(buf_size_short);
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		xml.append("<result>\n");
		xml.append("  <code>");
		xml.append(prmSuccess ? "1" : "0");
		xml.append("</code>\n");
		xml.append("  <info>");
		xml.append(StringUtil.convertXMLSpecChar(prmErrorInfo));
		xml.append("</info>\n");
		xml.append("</result>\n");
		return xml.toString();
	}

	/**
	 * timeout
	 * 
	 * @return XML result
	 */
	public static String getTimeoutResult() {
		StringBuffer xml = new StringBuffer(buf_size_short);
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		xml.append("<result>\n");
		xml.append("  <code>9</code>\n");
		xml.append("  <info>登录超时</info>\n");
		xml.append("</result>\n");
		return xml.toString();
	}

	/**
	 * no access
	 * 
	 * @return XML result
	 */
	public static String getAccessResult() {
		StringBuffer xml = new StringBuffer(buf_size_short);
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		xml.append("<result>\n");
		xml.append("  <code>8</code>\n");
		xml.append("  <info>没有操作权限</info>\n");
		xml.append("</result>\n");
		return xml.toString();
	}

	/**
	 * detail info of record
	 * 
	 * @param strContent
	 *          date set(<type,field alias,field value>)
	 * @return XML result
	 */
	public static String getDetailResult(String strContent[][]) {
		StringBuffer xml = new StringBuffer(buf_size);
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		xml.append("<result>\n");
		xml.append("  <code>1</code>\n");
		xml.append("  <info></info>\n");
		xml.append("  <detail>\n");
		for (int i = 0; i < strContent.length; i++) {
			xml.append("    <field>\n");
			xml.append("      <type>");
			xml.append(strContent[i][0]);
			xml.append("</type>\n");
			xml.append("      <name>");
			xml.append(StringUtil.convertXMLSpecChar(strContent[i][1]));
			xml.append("</name>\n");
			xml.append("      <value>");
			xml.append(StringUtil.convertXMLSpecChar(strContent[i][2]));
			xml.append("</value>\n");
			xml.append("    </field>\n");
		}
		xml.append("  </detail>\n");
		xml.append("</result>\n");
		return xml.toString();
	}

	/**
	 * record set by page
	 * 
	 * @param htbPageInfo
	 *          page info(record count,current page)
	 * @param strList
	 *          head title(alias,width,align)
	 * @param tbDatas
	 *          data set(array of AbstractTableBean)
	 * @return XML result
	 */
	
	public static String getListResult(Hashtable<String,Integer> htbPageInfo, String strList[][],int recCount,String tbDatas) {
		StringBuffer xml = new StringBuffer(buf_size_long);
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		xml.append("<result>\n");
		xml.append("  <code>1</code>\n");
		xml.append("  <info></info>\n");

		// page info
		int PageSize = 0;
		if (htbPageInfo.get("PageSize") == null)
			PageSize = PAGE_SIZE;
		else
			PageSize = (htbPageInfo.get("PageSize")).intValue();
		int RecCount = 0;
		int PageCount = 1;
		int ColCount = strList.length;
		int Index = 1;
		int Front = 1;
		int After = PageCount;
		if (PageSize > 0) {
			RecCount = (htbPageInfo.get("RecCount")).intValue();
			if (RecCount > 0) {
				if ((RecCount % PageSize) > 0)
					PageCount = RecCount / PageSize + 1;
				else
					PageCount = RecCount / PageSize;
			}
			Index = (htbPageInfo.get("Index")).intValue();
			if (Index > 1)
				Front = Index - 1;
			if (Index < PageCount)
				After = Index + 1;
		} else {
			RecCount = recCount;
		}

		xml.append("  <page>\n");
		xml.append("    <pagesize>");
		xml.append(PageSize);
		xml.append("</pagesize>\n");
		xml.append("    <reccount>");
		xml.append(RecCount);
		xml.append("</reccount>\n");
		xml.append("    <pagecount>");
		xml.append(PageCount);
		xml.append("</pagecount>\n");
		xml.append("    <colcount>");
		xml.append(ColCount);
		xml.append("</colcount>\n");
		xml.append("    <index>");
		xml.append(Index);
		xml.append("</index>\n");
		xml.append("    <front>");
		xml.append(Front);
		xml.append("</front>\n");
		xml.append("    <after>");
		xml.append(After);
		xml.append("</after>\n");
		xml.append("  </page>\n");

		// head title
		xml.append("  <col>\n");
		for (int i = 0; i < strList.length; i++) {
			xml.append("    <field width=\"");
			xml.append(strList[i][1]);
			xml.append("\">");
			xml.append(StringUtil.convertXMLSpecChar(strList[i][0]));
			xml.append("</field>\n");
		}
		xml.append("  </col>\n");

		// data
		xml.append("  <list>\n");
		xml.append(tbDatas);
		xml.append("  </list>\n");
		xml.append("</result>\n");
		return xml.toString();
	}	
	
	/**
	 * sub tree (sub menu)
	 * 
	 * @param strList
	 *          data set〈Key,name,have child,parentid,linkpage,backgroud pic,icon〉
	 * @return XML result
	 */
	public static String getTreeResult(String strList[][]) {
		StringBuffer xml = new StringBuffer(buf_size_long);
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		xml.append("<result>\n");
		xml.append("  <code>1</code>\n");
		xml.append("  <info></info>\n");
		xml.append("  <sub>\n");
		for (int i = 0; i < strList.length; i++) {
			xml.append("    <leaf>\n");
			xml.append("      <num>");
			xml.append(i);
			xml.append("</num>\n");
			xml.append("      <key>");
			xml.append(StringUtil.convertXMLSpecChar(strList[i][0]));
			xml.append("</key>\n");
			xml.append("      <name>");
			xml.append(StringUtil.convertXMLSpecChar(strList[i][1]));
			xml.append("</name>\n");
			xml.append("      <child>");
			xml.append(strList[i][2]);
			xml.append("</child>\n");
			xml.append("      <parent>");
			xml.append(StringUtil.convertXMLSpecChar(strList[i][3]));
			xml.append("</parent>\n");
			xml.append("      <link>");
			xml.append(StringUtil.convertXMLSpecChar(strList[i][4]));
			xml.append("</link>\n");
			xml.append("      <image>");
			xml.append(StringUtil.convertXMLSpecChar(strList[i][5]));
			xml.append("</image>\n");
			xml.append("      <icon>");
			xml.append(StringUtil.convertXMLSpecChar(strList[i][6]));
			xml.append("</icon>\n");
			if (i == strList.length - 1)
				xml.append("      <last>1</last>\n");
			else
				xml.append("      <last>0</last>\n");
			xml.append("    </leaf>\n");
		}
		xml.append("  </sub>\n");
		xml.append("</result>\n");
		return xml.toString();
	}
}
