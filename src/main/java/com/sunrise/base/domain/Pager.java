package com.sunrise.base.domain;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;

//@JsonSerialize(using = PagerSerializer.class)
public class Pager extends RowBounds {

	private Pattern pattern = Pattern.compile("[?]");

	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制

	private Integer pageNum = 0;// 当前页码

	private Integer pageSize = 10;// 每页记录数

	private Integer totalCount = new Integer(0);// 总记录数

	private Object rows;

	private Integer pageType = 1;// 0不分页 1前台分页2后台分页

	private String sql;

	public Pager() {
		super();
	}

	public Pager(Integer pageType) {
		super();
		this.pageType = pageType;
	}

	public Pager(Integer pageType, Integer pageSize) {
		super();
		this.pageType = pageType;
		this.pageSize = pageSize;
	}

	public Pager(Integer pageNum, Integer pageSize, Integer pageType) {
		super();
		if (pageNum < 1) {
			pageNum = 1;
		}
		if (pageSize < 1) {
			pageSize = 1;
		} else if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public Integer getPageType() {
		return pageType;
	}

	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setParam(List<Object> params) {
		if (StringUtils.isBlank(sql)) {
			return;
		}
		Matcher matcher = pattern.matcher(sql);
		StringBuffer sb = new StringBuffer();
		int i = 0;
		while (matcher.find()) {
			matcher.appendReplacement(sb, params.get(i).toString());
			i++;
		}
		matcher.appendTail(sb);
		setSql(sb.toString());
	}

	@Override
	public String toString() {
		return "Pager [pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalCount=" + totalCount + ", rows=" + rows
				+ ", pageType=" + pageType + ", sql=" + sql + "]";
	}
}