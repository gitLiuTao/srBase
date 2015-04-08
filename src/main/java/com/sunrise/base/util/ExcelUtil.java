package com.sunrise.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sunrise.base.SystemConstants;

public class ExcelUtil {

	/**
	 * 获取导出excel的路径和文件名
	 * 
	 * @param request
	 * @param fileName
	 * @return
	 */
	public static String getExportExcelFilePath(HttpServletRequest request, String fileName) {
		if ("".equals(fileName) || fileName == null) {
			return "";
		} else {
			return request.getSession().getServletContext().getRealPath("/") + SystemConstants.CONTEXTPATH + fileName
					+ SystemConstants.XLSX;
		}
	}

	/**
	 * 创建XSSFWorkbook
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static XSSFWorkbook getXSSFWorkbook(File file, String filePath) throws IOException {
		XSSFWorkbook workbook = null;
		if (!file.exists()) {
			workbook = new XSSFWorkbook();
		} else {
			workbook = new XSSFWorkbook(filePath);
		}
		return workbook;
	}

	/**
	 * 保存Excel 2003报表到指定目录
	 * 
	 * @param path
	 *            指定保存目录
	 * @param book
	 *            报表文件
	 * @param fileName
	 *            报表名称
	 * @throws IOException
	 */
	public static void saveExcel2003ToTargetPath(String path, HSSFWorkbook book, String fileName) throws IOException {
		File file = new File(path + fileName);
		OutputStream out = new FileOutputStream(file);
		book.write(out);
		out.flush();
		out.close();
	}

	/**
	 * 保存Excel 2003报表到指定目录
	 * 
	 * @param path
	 *            指定保存目录
	 * @param book
	 *            报表文件
	 * @param fileName
	 *            报表名称
	 * @throws IOException
	 */
	public static void saveExcel2007ToTargetPath(String path, XSSFWorkbook book, String fileName) throws IOException {
		File file = new File(path + "/" + fileName);
		OutputStream out = new FileOutputStream(file);
		book.write(out);
		out.flush();
		out.close();
	}

	/**
	 * 
	 * 动态组装Excel 2003 Sheet
	 * 
	 * @param book
	 *            Excel报表对象
	 * @param dataSet
	 *            报表数据，不包含表头
	 * @param header
	 *            报表表头
	 * @param sheetName
	 *            sheet名称
	 */
	public static void saveSheet2003DataToExcel(HSSFWorkbook book, List<HashMap<String, Object>> dataSet,
			String[] header, String sheetName) {
		if (header == null || header.length <= 0) {
			return;
		}
		HSSFSheet sheet = book.createSheet(sheetName);
		Map<String, HSSFCellStyle> styleMap = getMyHssFcellStyle(book);
		HSSFRow titlerow = sheet.createRow(0);
		for (int i = 0; i < header.length; i++) {// 创建Sheet头
			HSSFCell cell = titlerow.createCell(i);
			cell.setCellValue(header[i]);
			cell.setCellStyle(styleMap.get("defaultStyle"));// 设置样式
			// 添加表头样式
		}

		if (dataSet == null || dataSet.isEmpty()) {
			return;
		}
		for (int i = 0; i < dataSet.size(); i++) {// 插入Sheet数据
			Map<String, Object> datamap = dataSet.get(i);
			HSSFRow row = sheet.createRow(i + 1);
			for (int j = 0; j < header.length; j++) {// 创建Sheet头
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(String.valueOf(datamap.get(header[j])));
				// 添加单元格表样式

			}
		}
		for (int i = 0; i < header.length; i++) {
			sheet.setColumnWidth(i, 5500);// 宽
		}

	}

	/**
	 * 保存数据到Excel 2003,并存放到指定目录,包含数字格式化千分符
	 * 
	 * @param dataSet
	 *            报表数据，不包含表头
	 * @param header
	 *            报表表头
	 */
	public static void saveToExcel(HSSFWorkbook book, List<HashMap<String, Object>> dataSet, String[] header,
			String sheetName) {
		if (header == null || header.length <= 0) {
			return;
		}
		HSSFSheet sheet = book.createSheet(sheetName);
		HSSFRow titlerow = sheet.createRow(0);
		Map<String, HSSFCellStyle> styleMap = getMyHssFcellStyle(book);
		for (int i = 0; i < header.length; i++) {// 创建Sheet头
			HSSFCell cell = titlerow.createCell(i);
			cell.setCellValue(header[i]);
			// 添加表头样式
			cell.setCellStyle(styleMap.get("defaultStyle"));
		}
		for (int i = 0; i < dataSet.size(); i++) {// 插入Sheet数据
			Map<String, Object> datamap = dataSet.get(i);
			HSSFRow row = sheet.createRow(i + 1);
			for (int j = 0; j < header.length; j++) {// 创建Sheet头
				HSSFCell cell = row.createCell(j);
				if (datamap.get(header[j]) != null) {
					String value = String.valueOf(datamap.get(header[j]));
					if (isNumberic(value) && j > 0 && !StringUtil.isNullOrBlank(value)) {
						if (isNum(value)) {
							cell.setCellValue(Double.parseDouble(value));
							cell.setCellStyle(styleMap.get("numStyle"));
						} else {
							cell.setCellValue(value);
							cell.setCellStyle(styleMap.get("feeStyle"));
						}
					} else {
						if (value.contains("%")) {
							try {
								cell.setCellValue(Double.parseDouble(value.replace("%", "")) + "%");
								cell.setCellStyle(styleMap.get("numStyle"));
							} catch (Exception e) {
								if (value.contains(",")) { // 超过1000%又对之前的数据格式化了的字符串型百分数，比如1，000%需要这样处理。
									cell.setCellValue(value);
									cell.setCellStyle(styleMap.get("defaultStyle"));
								} else {
									cell.setCellValue(Double.parseDouble("0"));
									cell.setCellStyle(styleMap.get("percentStyle"));
								}
							}

						} else {
							value = value.replaceAll("--", " --");
							cell.setCellValue(value);
							cell.setCellStyle(styleMap.get("defaultStyle"));
						}
					}
				} else {
					cell.setCellValue(" --");
					cell.setCellStyle(styleMap.get("defaultStyle"));
				}
			}
		}
		for (int i = 0; i < header.length; i++) {
			sheet.setColumnWidth(i, 5500);// 宽
		}
	}

	/**
	 * 导出大数据量excel文件
	 * 
	 * @param list
	 * @param chnName
	 * @param reportTitle
	 * @throws IOException
	 */
	public static void saveToExcelSXSS(List list, String sheetName, String reportTitle, SXSSFWorkbook book)
			throws IOException {
		if (reportTitle == null || reportTitle.length() <= 0) {
			return;
		}
		String[] header = null;
		// String fileName = URLDecoder.decode(chnName,"UTF-8");
		header = reportTitle.split(",");
		List<String[]> recordList = new ArrayList<String[]>();
		/*
		 * String[] headerArray = new String[header.length]; for (int m = 0; m <
		 * header.length; m++) { headerArray[m] = header[m]; }
		 */
		// recordList.add(headerArray);
		// String sheetName = request().getParameter("sheetName");
		// SXSSFWorkbook book = new SXSSFWorkbook(100);
		Sheet sheet = book.createSheet(sheetName);
		Row titlerow = sheet.createRow(0);
		// HSSFSheet sheet = book.createSheet(sheetName);
		// HSSFRow titlerow = sheet.createRow(0);
		// Map<String,CellStyle> myHssFcellStyle =getMyHssFcellStyle(book);
		for (int i = 0; i < header.length; i++) {// 创建Sheet头
			Cell cell = titlerow.createCell(i);
			cell.setCellValue(header[i]);
			sheet.setColumnWidth(i, 5000);
			// cell.setCellStyle(myHssFcellStyle.get("defaultStyle"));
		}
		// List<HashMap<String, Object>> dataSet = new ArrayList<HashMap<String,
		// Object>>();
		List dataSet = list;
		if (dataSet == null || dataSet.isEmpty()) {
			return;
		}
		for (int i = 0; i < dataSet.size(); i++) {// 插入Sheet数据
			Object[] datamap = (Object[]) dataSet.get(i);
			Row row = sheet.createRow(i + 1);
			for (int j = 0; j < header.length; j++) {// 创建Sheet头
				Cell cell = row.createCell(j);
				cell.setCellValue(String.valueOf(datamap[j]));

			}
		}

	}

	/**
	 * 获取Excel样式
	 * 
	 * @param book
	 * @return
	 */
	public static Map<String, HSSFCellStyle> getMyHssFcellStyle(HSSFWorkbook book) {
		Map<String, HSSFCellStyle> styleMap = new HashMap<String, HSSFCellStyle>();
		// 一般样式
		HSSFCellStyle cellStyle = book.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleMap.put("defaultStyle", cellStyle);
		// 数字像是
		HSSFCellStyle cellStyleNum = book.createCellStyle();
		cellStyleNum.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleNum.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleNum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyleNum.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStyleNum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyleNum.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStyleNum.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyleNum.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		styleMap.put("numStyle", cellStyleNum);
		// 收入样式
		HSSFCellStyle cellStyleFee = book.createCellStyle();
		cellStyleFee.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleFee.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleFee.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyleFee.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStyleFee.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyleFee.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStyleFee.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyleFee.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
		styleMap.put("feeStyle", cellStyleFee);

		// 百分比样式
		HSSFCellStyle cellStylePercent = book.createCellStyle();
		cellStylePercent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStylePercent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStylePercent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStylePercent.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStylePercent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStylePercent.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStylePercent.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStylePercent.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
		styleMap.put("percentStyle", cellStylePercent);
		return styleMap;
	}

	/**
	 * 设置Excel样式
	 * 
	 * @param book
	 * @return
	 */
	public static HSSFCellStyle getHssFcellStyle(HSSFWorkbook book) {

		HSSFCellStyle cellStyle = book.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
		cellStyle.setWrapText(true);
		return cellStyle;
	}

	public static boolean isNumberic(String str) {
		if (str == null) {
			return false;
		}

		if (str.split("\\.").length > 2)
			return false;
		if (str.split("-").length > 2)
			return false;

		str = str.replace(".", "").replaceAll("-", "");
		if (str.equals("") || str.length() == 0) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	// 判断是否为整型数字
	public static boolean isNum(String s) {
		// for (int index = 0; index < s.length(); index++) {
		// if (48 > (int) s.charAt(index) || (int) s.charAt(index) > 57) {
		// return false;
		// }
		// }
		if (s.contains(".") || s.indexOf("-") > 0) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("-2.0".split("-").length);
	}

	/**
	 * 
	 * 动态组装Excel 2007/2010 Sheet
	 * 
	 * @param book
	 *            Excel报表对象
	 * @param dataSet
	 *            报表数据，不包含表头
	 * @param header
	 *            报表表头
	 * @param sheetName
	 *            sheet名称
	 */
	public static void saveSheet2010DataToExcel(XSSFWorkbook book, List<HashMap<String, Object>> dataSet,
			String[] header, String sheetName) {
		if (dataSet == null || dataSet.isEmpty() || header == null || header.length <= 0) {
			return;
		}

		XSSFSheet sheet = book.createSheet(sheetName);
		XSSFRow titlerow = sheet.createRow(0);
		for (int i = 0; i < header.length; i++) {// 创建Sheet头
			XSSFCell cell = titlerow.createCell(i);
			cell.setCellValue(header[i]);
			// 添加表头样式
		}
		for (int i = 0; i < dataSet.size(); i++) {// 插入Sheet数据
			Map<String, Object> datamap = dataSet.get(i);
			XSSFRow row = sheet.createRow(i + 1);
			for (int j = 0; j < header.length; j++) {// 创建Sheet头
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(String.valueOf(datamap.get(header[j])));
				// 添加单元格表样式

			}
		}
	}

	/**
	 * 通过递归,查找最后一个last Row Number 的下标,(而不是长度)
	 * 
	 * @param hsheet
	 *            exce12003 sheet
	 * @param xsheet
	 *            exce12007 sheet
	 * @param num
	 *            row.lastRowNum() 最后一个row
	 * @param columnIdSet
	 *            要查询cell 的下标
	 * @return
	 */
	public static int getLastRowByfirstCell(HSSFSheet hsheet, XSSFSheet xsheet, int num, Set<Integer> columnIdSet) {
		if (num <= 0)
			return num;
		boolean falg = false;
		if (xsheet != null)// excel 2007
		{
			XSSFRow row = xsheet.getRow(num);
			if (row == null) {
				return getLastRowByfirstCell(null, xsheet, num - 1, columnIdSet);
			} else {
				for (Integer columnId : columnIdSet) {
					if (row.getCell(columnId) != null && !"".equals(row.getCell(columnId).toString().trim())) {
						falg = true;
						break;
					}
				}
				if (!falg) {
					return getLastRowByfirstCell(null, xsheet, num - 1, columnIdSet);
				}
				return num;
			}
		} else // excel 2003
		{
			HSSFRow row = hsheet.getRow(num);
			if (row == null) {
				return getLastRowByfirstCell(hsheet, null, num - 1, columnIdSet);
			} else {
				for (Integer columnId : columnIdSet) {
					if (row.getCell(columnId) != null && !"".equals(row.getCell(columnId).toString().trim())) {
						falg = true;
						break;
					}
				}
				if (!falg) {
					return getLastRowByfirstCell(hsheet, null, num - 1, columnIdSet);
				}
				return num;
			}
		}
	}

	/**
	 * 创建Excel 默认样式
	 */
	public static void createDefaultSheetStyles() {

	}
}
