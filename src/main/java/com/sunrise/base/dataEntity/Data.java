/**
 * 
 */
package com.sunrise.base.dataEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zhanggk
 * 
 */

public class Data extends HashMap<String, Object> implements IData {

	
	/**
	 * 编号
	 */
	private static final long serialVersionUID = -2658350970937733375L;
	public Data(){
		System.out.println("分支1添加内容*******************");
	}
	public Data(Map<String,Object> map){
		this.putAll(map);
	}
	public Object get(String key) {
		Object value = super.get(key);
		return value;
	}

	public Object get(String key, Object def) {
		Object value = get(key);
		return value == null ? def : value;
	}

	public String[] getKeys() {
		return getKeys(false);
	}

	public String[] getKeys(boolean sort) {
		String[] result = keySet().toArray(new String[0]);
		if (sort)
			Arrays.sort(result);
		return result;
	}

	public String getString(String key) {
		Object result = get(key);
		return result == null ? null : result.toString();
	}

	public String getString(String key, String def) {
		String result = getString(key);
		return result == null ? def : result;
	}

	public int getInt(String key) {
		return getInt(key, 0);
	}

	public int getInt(String key, int def) {
		Object result = get(key);
		if (null == result) {
			return def;
		}
		return Integer.parseInt(result.toString());
	}

	public double getDouble(String key) {
		return getDouble(key, 0.0d);
	}

	public double getDouble(String key, double def) {
		Object result = get(key);
		if (null == result) {
			return def;
		}
		return Double.parseDouble(result.toString());
	}

	public boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	public boolean getBoolean(String key, boolean def) {
		Object result = get(key);
		if (null == result) {
			return def;
		}
		return Boolean.valueOf(result.toString());
	}

	public IData getData(String key) {
		return (IData) get(key);
	}

	public IDataList getDataList(String key) {
		return (IDataList) get(key);
	}


	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("{");
		Iterator<Map.Entry<String, Object>> it = entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<?, ?> entity = (Map.Entry<?, ?>) it.next();
			Object key = entity.getKey();
			Object value = entity.getValue();
			str.append("\"" + key + "\":");

			if (value == null)
				str.append("\"\"");
			else if (((value instanceof Map)) || ((value instanceof List)))
				str.append(value);
			else {
				str.append("\"" + value + "\"");
			}
			if(it.hasNext()){
				str.append(",");
			}
		}
		str.append("}");
		return str.toString();
	}
}
