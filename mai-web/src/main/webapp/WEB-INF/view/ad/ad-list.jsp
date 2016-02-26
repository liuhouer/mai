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
     
	  function edit(id){
		  window.location.href = "/ad/toEdit.action?id="+id;
	  }
	  
	  
	  function deleteIt(id){
			
		  if(confirm("确定要删除吗?")){
          $.ajax({
  			
        	  url:"/ad/removes.action",

              type:"post",

              data:{"id":id},
              
              

              success:function(msg){

                  if(msg=="success"){
				      alert('删除成功');
						 
                      window.location.href = window.location.href;

                  }else{
                	  alert('删除失败');
                  }            

              },
              error:function(e){
            	  alert(e);
              }

          });
		  }
		  
	  }
	  
	  
</script>



<div class="jumbotron container-fluid">


<div style="padding-left: 20px;padding-right: 20px;">

 		<div class="btn-group pull-left" style="margin: auto" role="group" aria-label="...">
          <div class="input-group" style="margin: auto">
            <button class="btn btn-default" type="button"  onclick="window.location.href='/ad/toAdd.action'">新增广告页</button>
          </div>
          
          
        </div>
 <form id="J_ser_form" action="/ad/list.action" class="form-inline">
    <input type="hidden" name="page" id="page" value="${page}"/>
    <input type="hidden" name="status" id="J_status" value="${status}"/>
	<table class="table table-bordered table-hover table-responsive">
      <thead>
        <tr>
          <th>广告页名称</th>
          <th>广告页</th>
          <th>展示开始时间</th>
          <th>展示结束时间</th>
          <th>广告链接</th>
          <th>操作  </th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="s" varStatus="ss">
         <tr>
          <th scope="row">${s.title }</th>
          <td>
	          <div class="col-xs-6 col-md-6">
	    		<a href="${s.imageURL }" class="thumbnail">
	      			<img src="${s.imageURL }" alt="" style="width: 50%">
	    		</a>
	  		  </div>
          </td>
          <td>${s.showStartTime }</td>
          <td>${s.showEndTime }</td>
          <td>${s.toURL }</td>
          <td>
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="edit('${s.advertisementID }')">编辑</button>&nbsp;
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="deleteIt('${s.advertisementID }')">删除</button>&nbsp;
          </td>
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