<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:forEach var="curMenu" items="${menulist}" varStatus="status">
    <c:choose>
        <c:when test="${fn:length(curMenu.childMenus) > 0}">
            <div class="ui dropdown link item">
                <c:if test="${not empty curMenu.strmenuicon}"><i class="${curMenu.strmenuicon} icon"></i></c:if><span
                    class="text">${curMenu.strmenuname}</span><i class="dropdown icon"></i>

                <div class="menu">
                    <c:set var="menulist" value="${curMenu.childMenus}" scope="request"/>
                    <c:import url="common/menudropdown.jsp"/>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <a class="item linked" href="${curMenu.strmenupath}"><c:if test="${not empty curMenu.strmenuicon}"><i
                    class="${curMenu.strmenuicon} icon"></i></c:if>${curMenu.strmenuname}</a>
        </c:otherwise></c:choose>
</c:forEach>
