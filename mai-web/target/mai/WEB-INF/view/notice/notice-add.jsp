<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/admin/navbar.jsp" %>

<!-- Main component for a primary marketing message or call to action -->
<form method="post" action="/ad/add.action" id="f2" name="f2" enctype="multipart/form-data">


<div class="jumbotron">

<div class="row">

    <div class="col-md-3 col-md-offset-3">
     		<input id="J_obj_id" name="objID"    type="hidden"  value="${objID }"/>
     		<input id="J_obj_type" name="objType" type="hidden"  value="${objType }"/>
            <div class="modal-body" id="myModalBody">
                  <p class="text-center" >请输入通知内容</p>
                  <c:if test="${objType =='121'}"><!--活动通知  -->
                   <textarea class="form-control" rows="3" placeholder="发消息给所有该活动已报名成功的同学" rows="3" name="J_cont" id="J_cont"></textarea>
                  </c:if>
                  <c:if test="${objType =='221'}"><!--社团通知  -->
                  
                  <textarea class="form-control" rows="3" placeholder="将发送给所有社员" rows="3" name="J_cont" id="J_cont"></textarea>
                  </c:if>
            </div>
         <div class="modal-body" >
            <button type="button" id="J_draft" class="btn btn-default" >保存草稿</button>
            <button type="button" id="J_publish" class="btn btn-default">发布</button>
        </div>
    </div>


</div>

</div>
</form>
<%@include file="/resources/footer-js.jsp" %>
<script language="JavaScript">
$(function(){
	//保存草稿
	$("#J_draft").click(function(){
		var con = $("#J_cont").val();
		if(con){
			$.ajax({
	  			
	        	  url:"/notice/draft.action",

	              type:"post",

	              data:$('#f2').serialize(),// 要提交的表单
	              

	              success:function(msg){

	                  if(msg=="success"){
						 alert('保存草稿成功');
							 
	                      window.location.href = '/notice/list.action';
	                      

	                  }else{
	                	  alert('保存异常');
	                  }            

	              },
	              error:function(e){
	            	  alert(e);
	              }

	          });
		}
	});
	
	//发布消息
	$("#J_publish").click(function(){
		var con = $("#J_cont").val();
		if(con){
			$.ajax({
	  			
	        	  url:"/notice/publish.action",

	              type:"post",

	              data:$('#f2').serialize(),// 要提交的表单
	              

	              success:function(msg){

	                  if(msg=="success"){
						  alert('发布成功');
							 
	                      window.location.href = '/notice/list.action';
	                      

	                  }else{
	                	  alert('保存异常');
	                  }            

	              },
	              error:function(e){
	            	  alert(e);
	              }

	          });
		}
	});
})


</script>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>