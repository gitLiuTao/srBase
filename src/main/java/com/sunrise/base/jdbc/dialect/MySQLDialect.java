package com.sunrise.base.jdbc.dialect;

import org.apache.log4j.Logger;

public class MySQLDialect implements Dialect {

	private final static Logger logger = Logger.getLogger(MySQLDialect.class);

	@Override
	public String getLimitString(String sql, int pageNum, int pageSize) {
		sql = sql.trim();

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect.append(sql);

		if (pageNum > 0) {
			pagingSelect.append(" limit ").append((pageNum - 1) * pageSize).append(',').append(pageSize);
		} else {
			pagingSelect.append(" limit ").append(pageSize);
		}

		if (logger.isTraceEnabled()) {
			logger.trace("Generated Pager Sql is " + pagingSelect.toString().replaceAll("\r|\n", ""));
		}
		return pagingSelect.toString();
	}

	@Override
	public String getCountString(String sql) {
		StringBuffer countSql = new StringBuffer(sql.length() + 100);
		countSql.append("select count(1) from (").append(sql).append(") t");
		if (logger.isTraceEnabled()) {
			logger.trace("Generated Count Sql is " + countSql.toString().replaceAll("\r|\n", ""));
		}
		return countSql.toString();
	}

}