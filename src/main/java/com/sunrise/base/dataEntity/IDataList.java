/**
 * 
 */
package com.sunrise.base.dataEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zhanggk
 *
 */
public interface IDataList extends List<Map<String,Object>>,Serializable{
	public IData getData(int index);
	public String toString();
}
