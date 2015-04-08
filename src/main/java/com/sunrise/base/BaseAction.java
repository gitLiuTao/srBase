/**
 * 
 */
package com.sunrise.base;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

import com.sunrise.base.dataEntity.Data;
import com.sunrise.base.dataEntity.IData;
import com.sunrise.base.util.ExcelUtil;
import com.sunrise.base.util.JsonUtil;
import com.sunrise.base.util.StringUtil;

/**
 * @author zhanggk
 * 
 */
/**
 * @author Bird Lari
 * 
 */
public class BaseAction {

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected HttpSession session;

	private static final String CODE = "code";

	private static final String MESSAGE = "message";

	private static final String TIME = "time";

	public IData getParam(WebRequest request) {
		Iterator<String> it = request.getParameterNames();
		IData result = new Data();
		while (it.hasNext()) {
			String key = it.next();
			result.put(key, request.getParameter(key));
		}
		return result;
	}

	public String uriDecode(String param) {
		String result = "";
		try {
			result = URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return result;
	}

	public IData returnSuccess() {
		return returnMsg(true, "执行成功");
	}

	public IData returnException() {
		return returnMsg(false, "系统忙，请稍后再试");
	}

	public IData returnException(String msg) {
		return returnMsg(false, msg);
	}

	public IData returnMsg(boolean bl, String msg) {
		IData result = new Data();
		result.put("success", bl);
		result.put("msg", msg);
		return result;
	}

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
	

	/**
	 * 将数据输出为Excel文件
	 * 
	 * @param response
	 * @param dataSet
	 *            原始数据列表
	 * @param headers
	 *            数据键与列头的字符串数组，格式需要严格按照“key=名称”方式填写，如，String[]
	 *            headers={"DATE_ID=日期","ALL_USR=用户量",…………}
	 * @param sheetName
	 *            sheet名称
	 * @param fileName
	 *            导出的文件名
	 */
	public void outToExcel(HttpServletResponse response, List<Map<String, Object>> dataSet, String[] headers,
			String sheetName, String fileName) {
		if (headers == null || headers.length < 1 ) {
			return;
		}
		
		if(dataSet == null){
			dataSet = new ArrayList<Map<String,Object>>();
		}
		
		HSSFWorkbook book = new HSSFWorkbook();

		String[] headerNames = new String[headers.length];// 列对应的中文名称
		String[] headerKeys = new String[headers.length];// 列对应dataSet中的key
		for (int i = 0; i < headers.length; i++) { // 将传入的头列表拆分为key和name的两个字符串数组
			String header = headers[i];
			String[] kv = header.split("=");// 根据等号做拆分
			headerKeys[i] = kv[0];
			headerNames[i] = kv[1];
		}

		HSSFSheet sheet = book.createSheet(sheetName);// 创建sheet
		HSSFRow titlerow = sheet.createRow(0);// 创建标题行
		Map<String, HSSFCellStyle> styleMap = ExcelUtil.getMyHssFcellStyle(book);
		for (int i = 0; i < headerNames.length; i++) {// 创建Sheet头
			HSSFCell cell = titlerow.createCell(i);
			cell.setCellValue(headerNames[i]); 
			// 添加表头样式
			cell.setCellStyle(styleMap.get("defaultStyle"));
		}

		for (int i = 0; i < dataSet.size(); i++) {// 插入Sheet数据
			Map<String, Object> datamap = dataSet.get(i);
			HSSFRow row = sheet.createRow(i + 1);
			for (int j = 0; j < headerKeys.length; j++) {// 创建Sheet头
				HSSFCell cell = row.createCell(j);
				if (datamap.get(headerKeys[j]) != null) {
					String value = String.valueOf(datamap.get(headerKeys[j]));
					if (ExcelUtil.isNumberic(value) && j > 0 && !StringUtil.isNullOrBlank(value)) {
						if (ExcelUtil.isNum(value)) {
							cell.setCellValue(Double.parseDouble(value));
							cell.setCellStyle(styleMap.get("numStyle"));
						} else {
							cell.setCellValue(value);
							cell.setCellStyle(styleMap.get("feeStyle"));
						}
					} else {
						value = value.replaceAll("--", " --");
						cell.setCellValue(value);
						cell.setCellStyle(styleMap.get("defaultStyle"));
					}
				} else {
					cell.setCellValue(" --");
					cell.setCellStyle(styleMap.get("defaultStyle"));
				}
			}
		}
		for (int i = 0; i < headers.length; i++) {
			sheet.setColumnWidth(i, 5500);// 宽
		}
		fileName = fileName + ".xls";
		try {
			// 判断浏览器版本，进行对应的转码
			if (isIE()) {// IE
				fileName = URLEncoder.encode(fileName, SystemConstants.UTF);
			} else {//
				fileName = new String(fileName.getBytes(SystemConstants.UTF), "ISO-8859-1");
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		OutputStream myout = null;
		try {
			response.setContentType(SystemConstants.APPLICATIONEXCEL);
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
			try {
				myout = response.getOutputStream();
				book.write(myout);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				myout.flush();
				myout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断是否为AJAX请求
	 * 
	 * @return
	 */
	protected boolean isAjax() {
		String xRequestedWith = request.getHeader("x-requested-with");
		return StringUtils.endsWithIgnoreCase(xRequestedWith, "XMLHttpRequest");
	}

	/**
	 * 判断是否为IE浏览器
	 * 
	 * @return
	 */
	protected boolean isIE() {
		if (request.getHeader("USER-AGENT") == null) {// 如果不存在这个字段，则直接返回false，表示不是IE浏览器
			return false;
		} else {
			String userAgent = request.getHeader("USER-AGENT").toLowerCase();
			return (userAgent.indexOf("msie") > 0 || userAgent.indexOf("trident") > 0) ? true : false;
		}
	}

	/**
	 * AJAX输出，返回null
	 * 
	 * @param content
	 * @param type
	 * @return
	 */
	public String ajax(String content, String type) {
		try {
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Connection", "keep-alive");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AJAX输出文本，返回null
	 * 
	 * @param text
	 * @return
	 */
	public String ajaxText(String text) {
		return ajax(text, "text/plain");
	}

	/**
	 * AJAX输出HTML，返回null
	 * 
	 * @param html
	 * @return
	 */
	public String ajaxHtml(String html) {
		return ajax(html, "text/html");
	}

	/**
	 * AJAX输出XML，返回null
	 * 
	 * @param xml
	 * @return
	 */
	public String ajaxXml(String xml) {
		return ajax(xml, "text/xml");
	}

	/**
	 * 根据字符串输出JSON，返回null
	 * 
	 * @param jsonString
	 * @return
	 */
	public String ajaxJson(String jsonString) {
		return ajax(jsonString, "text/html");
	}

	/**
	 * 根据Map输出JSON，返回null
	 * 
	 * @param jsonMap
	 * @return
	 */
	public String ajaxJson(Map<String, Object> jsonMap) {
		return ajaxJson(JsonUtil.writeValueAsString(jsonMap));
	}

	public String ajaxText(Map<String, Object> jsonMap) {
		return ajaxText(JsonUtil.writeValueAsString(jsonMap));
	}

	public String ajaxJsonArray(Collection<?> array) {
		return ajax(JsonUtil.writeValueAsString(array), "text/html");
	}

	/**
	 * 输出JSON,成功消息
	 * 
	 * @param message
	 * @return
	 */
	public String ajaxSuccess(String message) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(CODE, 200);
		jsonMap.put(MESSAGE, message);
		return ajaxJson(jsonMap);
	}

	/**
	 * 输出JSON,错误消息
	 * 
	 * @param message
	 * @return
	 */
	public String ajaxError(String message) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(CODE, 300);
		jsonMap.put(MESSAGE, message);
		return ajaxJson(jsonMap);
	}

	/**
	 * 输出JSON,错误消息
	 * 
	 * @param message
	 * @return
	 */
	public String ajaxTimeOut(String message) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(CODE, 301);
		jsonMap.put(MESSAGE, message);
		return ajaxJson(jsonMap);
	}

	/**
	 * 输出JSON,无访问权限
	 * 
	 * @param message
	 * @return
	 */
	public String ajaxUnauthorized(String message) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(CODE, 302);
		jsonMap.put(MESSAGE, message);
		return ajaxJson(jsonMap);
	}

	/**
	 * 输出JSON,未获取短信验证码
	 * 
	 * @param message
	 * @return
	 */
	public String ajaxSendCode(String message) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(CODE, 201);
		jsonMap.put(MESSAGE, message);
		return ajaxJson(jsonMap);
	}

	/**
	 * 输出JSON,短信验证码为空
	 * 
	 * @param message
	 * @return
	 */
	public String ajaxEmptyCode(String message) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(CODE, 202);
		jsonMap.put(MESSAGE, message);
		return ajaxJson(jsonMap);
	}

}
