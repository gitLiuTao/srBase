/**
 * 
 */
package com.sunrise.base.dataEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zhanggk
 * 
 */
public class DataList extends ArrayList<Map<String,Object>> implements IDataList {

	private static final long serialVersionUID = 6155710843582006086L;
	public DataList() {
		System.out.println("============开发提交内容========");
		System.out.println("=========release测试提交内容==========");
		System.out.println("***************");
	}

	public DataList(List<Map<String, Object>> param){
		if(param.size()!=0)
		this.addAll(param);
	}
	public IData getData(int index){
		Map<String,Object> temp=this.get(index);
		return new Data(temp);
	}

	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("[");

		Iterator<Map<String, Object>> it = iterator();
		while (it.hasNext()) {
			Object value = it.next();
			if (value == null)
				str.append("\"\"");
			else if (((value instanceof Map)) || ((value instanceof List)))
				str.append(value);
			else {
				str.append("\"" + value + "\"");
			}
			if (it.hasNext())
				str.append(",");
		}
		str.append("]");
		return str.toString();
	}
}
