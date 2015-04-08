package com.sunrise.base.jdbc.dialect;

public interface Dialect {

	/**
	 * 获取分页Sql
	 * 
	 * @param sql
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public String getLimitString(String sql, int pageNum, int pageSize);

	/**
	 * 获取总是Sql
	 * 
	 * @param sql
	 * @return
	 */
	public abstract String getCountString(String sql);

}
