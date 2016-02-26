<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.mai.notification.entity.Notification,com.mai.society.entity.Society"%>
<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<c:set var="NOTIFICATIONTYPE_TYPE_SOCIETY_NEWS" value="<%=Notification.NOTIFICATIONTYPE_TYPE_SOCIETY_NEWS%>" />
<c:set var="STATUS_RELEASED" value="<%=Society.STATUS_RELEASED%>" />
<script type="text/javascript" src="/resources/js/jquery/1.11.3/jquery.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通知管理</title>
</head>
<body>
<script type="text/javascript">
function  pub(id){//发布
	if(id){
		
		$.ajax({
			
	  	    url:"/notice/pub.action",

	        type:"post",

	        data:{"id":id},// 要提交的表单
	        

	        success:function(msg){

	            if(msg=="success"){
					alert('发布成功');
						 
	                window.location.href = '/notice/list.action';
	                

	            }else{
	          	    alert('发布异常');
	            }            

	        },
	        error:function(e){
	      	  alert(e);
	        }

	    });
		
	}
	
}


function  removes(id){//删除
	if(id){
		
		$.ajax({
			
	  	    url:"/notice/remove.action",

	        type:"post",

	        data:{"id":id},// 要提交的表单
	        

	        success:function(msg){

	            if(msg=="success"){
					alert('删除成功');
						 
	                window.location.href = '/notice/list.action';
	                

	            }else{
	          	    alert('删除异常');
	            }            

	        },
	        error:function(e){
	      	  alert(e);
	        }

	    });
		
	}
}
$(function(){
	
	//搜索bind
	 $("#J_ser_btn").click(function(){
		 $("#J_ser_form").submit();
	 })
	 
	 //下拉事件绑定
	 var J_status = "${J_status}";
	 var J_role   = "${J_role}";
	 $("#J_st_btn").find("li").each(function(){
		 //查询条件保留
		 var tid = $(this).find("a").attr("title");
		 if(tid == J_status){
			 $("#J_bbtn_st").text($(this).find("a").text());
			 $("#J_bbtn_st").append("<span class='caret'>");
		 }
		 $(this).click(function(){
			 //设值
			 var st = $(this).find("a").attr("title");
			 var text =  $(this).find("a").text();
			 $("#J_bbtn_st").text(text);
			 $("#J_bbtn_st").append("<span class='caret'>");
			 $("#J_status").val(st);
			 //触动查询
			 $("#J_ser_btn").click();
		 })
	 })
	 
	 
}); 

</script>



<div class="jumbotron container-fluid">
      <%--<div class="row">--%>
      <!-- 搜索区域  -->
        <form id="J_ser_form" action="/notice/list.action" class="form-inline">
        
        <input type="hidden" id="J_ser_btn" >
         <input type="hidden" name="page" id="page" value="${page}"/>
			<c:if test="${societyStatus == STATUS_RELEASED}">
				<div class="btn-group pull-right" style="margin: auto" role="group" aria-label="...">
					<div class="input-group" style="margin: auto">
						<button class="btn btn-default" type="button" onclick="window.location.href='/notice/toAdd.action?objID=<%=CurrentUser.getSocietyID()%>&objType=${NOTIFICATIONTYPE_TYPE_SOCIETY_NEWS}'" >发通知</button>
					</div>
				</div>
			</c:if>

        <input type="hidden" id="J_status" name="J_status" value="${J_status}"/>
         <!-- 搜索区域  -->
        </form>
        
        
        
        
    </div>
<div style="padding-left: 20px;padding-right: 20px;">
	<table class="table table-bordered table-hover table-responsive">
      <thead>
        <tr>
          <th>创建时间</th>
          <th>消息内容</th>
          <th>发送对象</th>
          <th>
			  <div class="dropdown">
		        <button class="btn btn-default dropdown-toggle" type="button" id="J_bbtn_st" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
		                      所有状态
		          <span class="caret"></span>
		        </button>
		        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" id="J_st_btn">
		          <li><a href="JavaScript:void(0);" title="">所有状态</a></li>
		          <li><a href="JavaScript:void(0);" title="0">未发布</a></li>
		          <li><a href="JavaScript:void(0);" title="1">已发布</a></li>
		        </ul>
		        
		      </div>

		  </th>
		  <th>
			  操作
		  </th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="s" varStatus="ss">
         <tr>
          <th scope="row">${s.showTime }</th>
          <td>${s.notificationContent } </td>
          <td>
          ${s.objName }
          </td>
          <td>
 			<c:if test="${s.status == 1 }">
             		已发布
             </c:if>
             <c:if test="${s.status == 0 }">
             		<font color="red">未发布</font>
             </c:if>
		  </td>
          <td>
             <!-- 已发布 -->
             <c:if test="${s.status == 1 }">
             		无
             </c:if>
             <!-- 未发布 -->
             <c:if test="${s.status == 0 }">
				 <c:if test="${societyStatus == STATUS_RELEASED}">
					 <button class="btn btn-default" type="button" onclick="pub('${s.notificationMainID }')">发布</button>&nbsp;
				 </c:if>
             <button class="btn btn-danger" type="button"  onclick="removes('${s.notificationMainID }')">删除</button>&nbsp;
             </c:if>
          </td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    
      <div class="panel-footer">
        <pagination:pagination param="max=${pageMaxSize}&total=${totalsize}&page=${page}&stitle=${stitle}"/>

      </div>
</div>
 <!-- /container -->





<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>