<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/resources/common-base.jsp" %>
<%@include file="/resources/boot-header.jsp" %>
     <c:forEach items="${list}" var="s" varStatus="ss">
     	<li id="li${s.tagID }" ><span style="font-size: 90%"  class="label label-success">${s.showtagContent64 }</span>
     	 <span   cid="${s.tagID }" class="glyphicon glyphicon-remove"  onclick="delTag('${s.tagID }')"></span></li>
     </c:forEach>
     <input type="hidden" id="J_limit" value="${limit}"/>
