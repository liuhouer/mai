<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<script type="text/javascript" src="/resources/js/jquery/1.11.3/jquery.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社团管理</title>
</head>
<body>
<script type="text/javascript">
     
	  
</script>



<div class="jumbotron container-fluid">


<div style="padding-left: 20px;padding-right: 20px;">

 <form id="J_ser_form" action="/feed/list.action" class="form-inline">
  <input type="hidden" name="page" id="page" value="${page}"/>
	<table class="table table-bordered table-hover table-responsive">
      <thead>
        <tr>
          <th>建议内容</th>
          <th>建议人</th>
          <th>提交时间</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="s" varStatus="ss">
         <tr>
          <th scope="row">${s.notes }</th>
          <td>${s.name }</td>
          <td>${s.showTime }</td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    
    <div class="panel-footer">
        <pagination:pagination param="max=${pageMaxSize}&total=${totalsize}&page=${page}&stitle=${stitle}"/>

      </div>
      
      </form>
</div>
 <!-- /container -->


</div>








<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>