<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib uri="/WEB-INF/pagination.tld" prefix="pagination"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.mai.util.CurrentUser" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<c:set var="cssPath" value="${ctxPath}/resources/css"/>
<c:set var="jsPath" value="${ctxPath}/resources/js"/>