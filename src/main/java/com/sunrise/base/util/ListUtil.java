/**
 * 
 */
package com.sunrise.base.util;





import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.Map;


/**
 * @author guocf
 * 
 */
public class ListUtil {

	public static int getIntValue(String s, int errorValue) {
		int ok = 0;
		try {
			ok = Integer.parseInt(s.trim());
		} catch (NumberFormatException e) {
			ok = errorValue;
		}
		return ok;
	}

	public static long getLongValue(String s, int errorValue) {
		long ok = 0;
		try {
			ok = Long.parseLong(s.trim());
		} catch (NumberFormatException e) {
			ok = errorValue;
		}
		return ok;
	}

	public static String getStrFromArray(String[] s, String splite) {
		String ok = "";
		if (s == null) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s.length; k++) {
			buffer.append(s[k] + splite);
		}
		ok = buffer.toString();
		if (ok.endsWith(splite)) {
			ok = ok.substring(0, ok.length() - splite.length());
		}
		return ok;
	}
	
	/**
	 * 将集合中的字符传进行链接
	 * @param s
	 * @param splite
	 * @return
	 */
	public static String getChainInfo(Set<String> s, String splite) {
		String ok = "";
		if (ListUtil.isNull(s)) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		Iterator It=s.iterator();
		while (It.hasNext()) {
			buffer.append(It.next() + splite);
		}
		ok = buffer.toString();
		if (ok.endsWith(splite)) {
			ok = ok.substring(0, ok.length() - splite.length());
		}
		return ok;
	}
	

	public static String getInCharSql(String s1, String splite) {
		if (isNull(s1)) {
			return "";
		}
		String[] s = s1.split(splite);
		if (isNull(s)) {
			return "";
		}
		String ok = "";
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s.length; k++) {
			buffer.append("'" + s[k] + "',");
		}
		ok = buffer.toString();
		if (ok.endsWith(",")) {
			ok = ok.substring(0, ok.length() - 1);
		}
		if (ok.startsWith(",")) {
			ok = ok.substring(1);
		}
		return ok;
	}
	
	public static String getInCharSql(String[] s) {
		if (isNull(s)) {
			return "";
		}
		String ok = "";
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s.length; k++) {
			buffer.append("'" + s[k] + "',");
		}
		ok = buffer.toString();
		if (ok.endsWith(",")) {
			ok = ok.substring(0, ok.length() - 1);
		}
		if (ok.startsWith(",")) {
			ok = ok.substring(1);
		}
		return ok;
	}
	
	public static String getInCharSql(List s1) {
		if (isNull(s1)) {
			return "";
		}
		
		String ok = "";
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s1.size(); k++) {
			buffer.append("'" + s1.get(k) + "',");
		}
		ok = buffer.toString();
		if (ok.endsWith(",")) {
			ok = ok.substring(0, ok.length() - 1);
		}
		if (ok.startsWith(",")) {
			ok = ok.substring(1);
		}
		return ok;
	}
	

	public static String getInCharSql(String s1, String splite, String dataType) {
		if (isNull(s1)) {
			return "";
		}
		String[] s = s1.split(splite);
		if (isNull(s)) {
			return "";
		}
		String ok = "";
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s.length; k++) {
			if (SunMobileConst.DATA_TYPE_VARCHAR.equals(dataType)) {
				buffer.append("'" + s[k] + "',");
			} else {
				buffer.append(s[k] + ",");
			}
		}
		ok = buffer.toString();
		if (ok.endsWith(",")) {
			ok = ok.substring(0, ok.length() - 1);
		}
		if (ok.startsWith(",")) {
			ok = ok.substring(1);
		}
		return ok;
	}

	/**
	 * 将字符串列表转化为字符串数组
	 * @param list
	 * @return
	 */
	public static String[] toStrArray(List list){
		if(ListUtil.isNull(list)){
			return null;
		}
		String[] s=new String[list.size()];
		for(int k=0;k<list.size();k++){
			s[k]=String.valueOf(list.get(k));
		}
		return s;
	}
	/**
	 * 得到字符串的IN类型where查询条件
	 * 
	 * @param s
	 * @return
	 */
	public static String getVarcharInSQL(String[] s) {

		if (s == null) {
			return "";
		}

		if (s.length < 1) {
			return "";
		}
		if (s.length == 1 && ListUtil.isNull(s[0])) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s.length; k++) {
			buffer.append("'" + s[k] + "',");
		}
		String ok = buffer.toString();
		if (ok.endsWith(",")) {
			ok = ok.substring(0, ok.length() - 1);
		}
		return ok;
	}

	/**
	 * 得到数字类型的IN类型where查询条件
	 * 
	 * @param s
	 * @return
	 */
	public static String getNumberInSQL(String[] s) {

		if (s == null) {
			return "";
		}

		if (s.length < 1) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s.length; k++) {
			buffer.append(s[k] + ",");
		}
		String ok = buffer.toString();
		if (ok.endsWith(",")) {
			ok = ok.substring(0, ok.length() - 1);
		}
		return ok;
	}

	public static List<String> getSpliteList(String s1, String splite) {
		if (ListUtil.isNull(s1)) {
			return new ArrayList<String>();
		}
		String[] s = s1.split(splite);
		if (s == null) {
			return new ArrayList<String>();
		}
		List<String> list = new ArrayList<String>();
		for(int k=0;k<s.length;k++){
			if(!StringUtil.isNullOrBlank(s[k])){
				list.add(s[k]);
			}
		}
		//List list = Arrays.asList(s);
		return list;
	}
	
	/**
	 * 拆解成数字的
	 * @param s1
	 * @param splite
	 * @return
	 */
	public static List<Integer> getSpliteIntegers(String s1, String splite) {
		if (ListUtil.isNull(s1)) {
			return new ArrayList<Integer>();
		}
		String[] s = s1.split(splite);
		if (s == null) {
			return new ArrayList<Integer>();
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int k=0;k<s.length;k++){
			if(!StringUtil.isNullOrBlank(s[k])){
				list.add(new Integer(s[k].trim()));
			}
		}
		return list;
	}
	
	/**
	 * 拆解成数字的
	 * @param s1
	 * @param splite
	 * @return
	 */
	public static List<BigDecimal> getSpliteBigDecimals(String s1, String splite) {
		if (ListUtil.isNull(s1)) {
			return new ArrayList<BigDecimal>();
		}
		String[] s = s1.split(splite);
		if (s == null) {
			return new ArrayList<BigDecimal>();
		}
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		for(int k=0;k<s.length;k++){
			if(!StringUtil.isNullOrBlank(s[k])){
				list.add(new BigDecimal(s[k].trim()));
			}
		}
		return list;
	}
	
	
	/**
	 * 拆解成数字数组
	 * @param s1
	 * @param splite
	 * @return
	 */
	public static Integer[] getSpliteIntegerArray(String s1, String splite) {
		if (ListUtil.isNull(s1)) {
			return null;
		}
		String[] s = s1.split(splite);
		if (s == null) {
			return null;
		}
		Integer[] arrInt=new Integer[s.length];
		for(int k=0;k<s.length;k++){
				arrInt[k]=new Integer(s[k].trim());
		}
		return arrInt;
	}

	public static String getStrFromList(List<String> s, String splite) {
		String ok = "";
		if (ListUtil.isNull(s)) {
			return ok;
		}
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s.size(); k++) {
			String curValue = (String) s.get(k);
			if (!ListUtil.isNull(curValue)) {
				buffer.append(curValue.trim() + splite);
			}
		}
		ok = buffer.toString();
		if (ok.endsWith(splite)) {
			ok = ok.substring(0, ok.length() - splite.length());
		}
		return ok;
	}
	
	public static String getStrFromList(Set<String> set, String splite) {
		String ok = "";
		if (ListUtil.isNull(set)) {
			return ok;
		}
		StringBuffer buffer = new StringBuffer();
		Iterator<String> It=set.iterator();
		while(It.hasNext()){
			String curValue =It.next();
			if (!ListUtil.isNull(curValue)) {
				buffer.append(curValue.trim() + splite);
			}
		}
		ok = buffer.toString();
		if (ok.endsWith(splite)) {
			ok = ok.substring(0, ok.length() - splite.length());
		}
		return ok;
	}
	

	
	
	/**
	 * 判断字串符是空串或NULL,"null"
	 * 
	 * @param _str
	 *            字符串
	 * @return 是否是空串或null,"null",其返回结果是"";
	 */
	public static String toNull(String s) {
		if (ListUtil.isNull(s)) {
			return "";
		}
		return s;
	}

	/**
	 * 判断字串符是空串或NULL,"null"
	 * @param s 字符串
	 * @return 是否是空串或null,"null",其返回结果是"";
	 */
	public static String toNull(String s, String repacle) {
		if (ListUtil.isNull(s)) {
			return repacle;
		}
		return s;
	}
	/**
	 * 判断字串符是空串或NULL,"null"
	 * @param s 字符串
	 * @return 是否是空串或null,"null",其返回结果是"";
	 */
	public static String toZero(int s, String repacle) {
		if (s==0) {
			return repacle;
		}
		return String.valueOf(s);
	}
	

	/**
	 * 判断字串符是空串或NULL,"null"
	 * 
	 * @param _str
	 *            字符串
	 * @return 是否是空串或null,"null",其返回结果是"";
	 */
	public static String getObjStr(Object s1, String repacle) {
		if (s1 == null) {
			return repacle;
		}
		String s = String.valueOf(s1);
		if (ListUtil.isNull(s)) {
			return repacle;
		}
		return s;
	}

	/**
	 * 判断字串符是空串或NULL,"null"
	 * 
	 * @param _str
	 *            字符串
	 * @return 是否是空串或null,"null",其返回结果是"";
	 */
	public static String toObjNull(Object s1) {
		return getObjStr(s1, "");
	}

	public static String toNullHtml(String s) {
		if (ListUtil.isNull(s)) {
			return "&nbsp;";
		}
		return s;
	}

	public static String getTelNoSplit(String tel) {
		String telOk = "";
		if (tel == null || "".equals(tel) || "-".equals(tel)) {
			return "";
		}
		if (tel.indexOf("-") > -1) {
			String[] s = tel.split("-");
			if (s != null) {
				if (s.length == 2) {
					String pre = s[0];
					String fre = s[1];
					telOk = pre + fre;
				}
			}
		}
		return telOk;
	}


	/**
	 * 判断字串符是空串或NULL
	 * 
	 * @param _str
	 *            字符串
	 * @return 是否是空串或null
	 */
	public static boolean isNull(String src) {
		if (src == null) {
			return true;
		}
		String s = src;
		s = s.trim();
		if (s.equals("")) {
			return true;
		}
		if (s.equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}
	

	/**
	 * 判断对象数组是否为空
	 * 
	 * @param obj
	 *            字符串
	 * @return 是否是空串或null
	 */
	public static boolean isNull(Object[] obj) {
		if (obj == null) {
			return true;
		}
		if (obj.length == 0) {
			return true;
		}

		return false;
	}

	/**
	 * 判断集合是否为空
	 * @param
	 * @return
	 */
	public static boolean isNull(Map list) {
		if (list == null) {
			return true;
		}
		if (list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param
	 * @return
	 */
	public static boolean isNull(Collection list) {
		if (list == null) {
			return true;
		}
		if (list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 复选框或单选按钮选中的操作
	 * 
	 * @param
	 * @return
	 */
	public static String isChecked(String src, String standardValue) {
		if (isNull(standardValue)) {
			return "";
		}
		if (isNull(src)) {
			return "";
		}
		src = src.trim();
		if (src.equals(standardValue)) {
			return "checked";
		}
		return "";
	}

	public static void main(String[] args) {
		
		Set set=new HashSet();
		set.add("1");
		set.add("2");
		set.add("1");
		set.add("3");
		set.add("2");
		
		String[] s=new String[]{"ssd","dasds"};
		String dd=ListUtil.getStrFromArray(s, "->");
		
		List a=new ArrayList();
		// a.add("2");
		// a.add("22");
		//	  
		// // String ss=StringUtil.getStrFromArray(s,",");
		// String ss=ListUtil.getStrFromList(a,",");
		//	  
		// //ListUtil.Encoding("专业");
		
	}

	public static String subString(String str, int length) {
		if (str != null) {
			if (str.length() > length) {
				str = str.substring(0, length);
			}
		} else {
			str = "";
		}
		return str;
	}

	public static int getMaxIntValue() {
		return 999999999;
	}

	public static int getMaxIntValue(int digit) {
		if (digit > 9) {
			return getMaxIntValue();
		}
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < digit; k++) {
			buffer.append("9");
		}
		return Integer.parseInt(buffer.toString());
	}

	public static int getMinIntValue() {
		return -999999999;
	}

	public static String getExpress(String varName, String value) {
		return " " + varName + "='" + value + "' ";
	}

	public static String getChangeLine(String value) {
		return value + "\n";
	}

	

	public static String getText(Map map, String key) {
		if (map == null) {
			return key;
		}
		String rtv = String.valueOf(map.get(key));
		if (ListUtil.isNull(rtv)) {
			return key;
		}
		return rtv;
	}

	/**
	 * 计算日历的语言显示
	 * @param langCode
	 * @return
	 */
	public static String getCalendarLang(String langCode) {
		if ("1".equals(langCode)) {
			return "lang:'zh-cn',";
		}
		return "lang:'en',";
	}

	public static String insertInfo(String s, String needReplace,
			String insertStr) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(s);
		int startIndex = buffer.indexOf(needReplace);
		if (startIndex > -1) {
			buffer.replace(startIndex, startIndex + needReplace.length(), " ");
			buffer.insert(startIndex, insertStr);
		}
		return buffer.toString();
	}
	
	/**
	 * 将集合串接成字符串
	 */
	public static String getListToString(List list){
		String str = "";
		if(list==null){
			return "";
		}
		for (int i = 0; i < list.size(); i++) {
			str+=list.get(i);
		}
		return str;
	}
}
