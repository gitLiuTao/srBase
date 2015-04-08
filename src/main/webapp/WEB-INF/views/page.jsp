<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="formId" value="${param.formId}" />
<c:set var="paramId" value="${param.paramId}" />
<c:set var="tbodyId" value="${param.tbodyId}" />
<div class="fy" id="page">

<form>
    <input id="_formId" type="hidden" value="${formId }" param="${paramId }"  />
    <input type="hidden" id="_tbodyId" value="${tbodyId }" />
    <input type="hidden" id="_recordCount" />
    <input type="hidden" id="_maxResult" /> 
    <label>
	每页显示:
	</label>
	                <select class="pageSizeSel" id="maxResult">
	                        <option value="10" selected="selected">10</option>
	                        <option value="15">15</option>
	                        <option value="20">20</option>
	                </select>
	                <label>&nbsp;条&nbsp;
	                	第&nbsp;<span id="current">--</span>&nbsp;页&nbsp;共&nbsp;
		<span id="pageCount">--</span>&nbsp;页 &nbsp;共
		<span id="recordCount">--</span>&nbsp;条&nbsp;
	</label>
</form>
    <ul class="page" style="padding-top: 0px;">
		<input type="hidden" id="totalPageNum" />
		<li style="padding-right:30px;">
			<input class="btn_pageGo" type="button" value="确定" />
		</li>
		<li class="pagenumber">
			<span>到第</span>
			<span><input class="input_pageNumber" id="pageNumber" type="text" />
			</span>
			<span>页</span>
			<a class="_page" id="prev">上一页</a>
			<label>
				<a class="currt" id="currentPage">1</a>
			</label>
			<a class="_page" id="next">下一页</a>
			
		</li>
	</ul>
</div> 
					

