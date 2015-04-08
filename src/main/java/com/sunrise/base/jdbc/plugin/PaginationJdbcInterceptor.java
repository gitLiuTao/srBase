package com.sunrise.base.jdbc.plugin;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.logging.jdbc.ConnectionLogger;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.sunrise.base.domain.Pager;
import com.sunrise.base.jdbc.PreparedStatementLogger;
import com.sunrise.base.jdbc.dialect.Dialect;
import com.sunrise.base.jdbc.dialect.OracleDialect;

@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class }) })
public class PaginationJdbcInterceptor implements Interceptor {

	private Dialect dialect = new OracleDialect();

	public PaginationJdbcInterceptor() {
		super();
	}

	public Object intercept(Invocation invocation) throws Throwable {

		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = invocation.getArgs()[1];
		RowBounds bounds = (RowBounds) invocation.getArgs()[2];
		if (!(bounds instanceof Pager)) {
			return invocation.proceed();
		}
		Pager pager = (Pager) bounds;
		if (pager.getPageType() == 0) {
			return invocation.proceed();
		}
		Integer pageNum = pager.getPageNum();
		Integer pageSize = pager.getPageSize();

		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		String originalSql = boundSql.getSql().trim();
		Configuration configuration = mappedStatement.getConfiguration();
		if (boundSql == null || boundSql.getSql() == null || "".equals(boundSql.getSql())) {
			return null;
		}

		String countSql = dialect.getCountString(originalSql);
		pager.setSql(originalSql);

		Connection connection = ConnectionLogger.newInstance(configuration.getEnvironment().getDataSource()
				.getConnection(), mappedStatement.getStatementLog());

		PreparedStatement countStmt = PreparedStatementLogger.newInstance(connection.prepareStatement(countSql), pager,
				mappedStatement.getStatementLog());

		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(),
				getPageBoundSql(boundSql, countSql));
		parameterHandler.setParameters(countStmt);
		ResultSet rs = null;
		try {
			rs = countStmt.executeQuery();
			if (rs.next()) {
				pager.setTotalCount(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new SQLException("execute sql error: " + removeBreakingWhitespace(pager.getSql()) + e.getMessage());
		} finally {
			if (rs != null) {
				rs.close();
			}
			countStmt.close();
			connection.close();
		}

		String pageSql = dialect.getLimitString(originalSql, pageNum, pageSize);

		MappedStatement newMs = copyFromMappedStatement(mappedStatement,
				new PageSqlSource(getPageBoundSql(boundSql, pageSql)));

		invocation.getArgs()[0] = newMs;
		try {
			return invocation.proceed();
		} catch (Exception e) {
			throw new Exception("execute sql error: " + removeBreakingWhitespace(pager.getSql()) + e.getMessage());
		}
	}

	private class PageSqlSource implements SqlSource {

		private BoundSql boundSql;

		public PageSqlSource(BoundSql boundSql) {
			super();
			this.boundSql = boundSql;
		}

		@Override
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}

	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties arg0) {

	}

	private BoundSql getPageBoundSql(BoundSql boundSql, String sql) {
		Field field;
		try {
			field = boundSql.getClass().getDeclaredField("sql");
			field.setAccessible(true);
			field.set(boundSql, sql);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return boundSql;
	}

	public String removeBreakingWhitespace(String original) {
		StringTokenizer whitespaceStripper = new StringTokenizer(original);
		StringBuilder builder = new StringBuilder();
		while (whitespaceStripper.hasMoreTokens()) {
			builder.append(whitespaceStripper.nextToken());
			builder.append(" ");
		}
		return builder.toString();
	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource sqlSource) {
		Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), sqlSource,
				ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		MappedStatement newMs = builder.build();
		return newMs;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

}