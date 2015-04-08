package com.sunrise.base.jdbc.dialect;

import org.apache.log4j.Logger;

public class OracleDialect implements Dialect {

	private final static Logger logger = Logger.getLogger(OracleDialect.class);

	public String getLimitString(String sql, int pageNum, int pageSize) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);

		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");

		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ ) where rownum_ > ");
		pagingSelect.append(pageNum * pageSize);
		pagingSelect.append(" and rownum_ <= ");
		pagingSelect.append(pageNum * pageSize + pageSize);

		if (isForUpdate) {
			pagingSelect.append(" for update");
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
