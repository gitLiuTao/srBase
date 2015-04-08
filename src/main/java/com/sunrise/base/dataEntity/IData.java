/**
 * 
 */
package com.sunrise.base.dataEntity;

import java.io.Serializable;
import java.util.Map;

/**
 * @author zhanggk
 *
 */
public interface IData extends Map<String,Object>,Serializable{
	public Object get(String key) ;
	public Object get(String key, Object def) ;
	public String[] getKeys() ;
	public String[] getKeys(boolean sort) ;
	public String getString(String key) ;
	public String getString(String key, String def);
	public int getInt(String key) ;
	public int getInt(String key, int def) ;
	public double getDouble(String key) ;
	public double getDouble(String key, double def);
	public boolean getBoolean(String key);
	public boolean getBoolean(String key, boolean def);
	public IData getData(String key) ;
	public IDataList getDataList(String key);
	public String toString();
}
