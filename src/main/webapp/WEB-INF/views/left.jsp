<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<c:if test="${menus ne null&&fn:length(menus) ne 0 }">
	<ul>
		<c:forEach var="menu" items="${menus}" varStatus="index">
			<li><c:choose>
					<c:when test="${menu.subMenus ne null&&fn:length(menu.subMenus) ne 0}">
						<div class="nav01" target="leftTuck">${menu.menuName}</div>
						<div class="nav02">
							<c:forEach var="subMenu" items="${menu.subMenus}">
								<p>
									<a target="leftMenu" data-id="${subMenu.menuId}" href="${base }${subMenu.urlPath}">${subMenu.menuName}</a>
								</p>
							</c:forEach>
						</div>
					</c:when>
					<c:otherwise>
						<div class="nav">
							<a target="leftMenu" data-id="${menu.menuId}" href="${base }${menu.urlPath}">${menu.menuName}</a>
						</div>
					</c:otherwise>
				</c:choose></li>
		</c:forEach>
	</ul>
</c:if>