<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/resources/common-base.jsp" %>
<%@include file="/resources/boot-header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
        <c:forEach items="${list}" var="s" varStatus="ss">
     <li>${s.logAuthor} &nbsp;&nbsp;&nbsp;&nbsp; ${s.logDesc}  </li>
     </c:forEach>
</body>
</html>