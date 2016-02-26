<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  import="com.mai.util.ConfigUtil"%>

<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<c:set var="domain" value="<%=ConfigUtil.getPicdomain()%>" />
<script type="text/javascript" src="/resources/js/jquery/1.11.3/jquery.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>照片管理</title>
</head>
<body>
<script type="text/javascript">

function pushST(id,status){
	if(id && status){
		 $.ajax({

             url:"/photo/pushST.action",

             type:"post",

             data:{"photoID":id,"status":status},

             success:function(msg){

                 if(msg=="success"){
					

                     window.location.href =  window.location.href;

                 }            

             }

         });
	}
}
  
  
function popDiv(url,st,pid){
	
	var url2 = "${domain}/"+url;
	$("#J_pic_div").find("img").attr("src",url2);
	
	if(st==2){           //已忽略
		$("#J_op_div").hide();
		$("#J_del_div").hide();
		$("#J_ignore_div").show();
	}else if(st==1){     //已删除
		$("#J_op_div").hide();
		$("#J_ignore_div").hide();
		$("#J_del_div").show();
	}else if(st==0){     //代操作
		$("#J_ignore_btn").click(function(){
			pushST(pid,'2');
		});
		$("#J_delete_btn").click(function(){
			pushST(pid,'1');
		});
		$("#J_op_div").show();
		$("#J_ignore_div").hide();
		$("#J_del_div").hide();
	}
	
}

$(function(){
	
	 
	 $("#J_up_btn").click(function(){
		 
		 var file = $("#J_file").val();
		 //alert(file);
		 if(file){
	      
	      $("#J_file_form").attr("action","/user/importEX.action").submit();
		 }

	 });
}); 

</script>



<div class="jumbotron container-fluid">
</div>
<div style="padding-left: 20px;padding-right: 20px;">
		<form id="J_ser_form" action="/photo/list.action" class="form-inline">
		 <input type="hidden" name="page" id="page" value="${page}"/>
					<div class="row">
        				<c:forEach items="${list}" var="s" varStatus="ss">
							<div class="col-sm-6 col-md-3">
								<div class="thumbnail">
								    <a class="btn btn-default" data-toggle="modal" data-target="#myModal" onclick="popDiv('${s.photoURL }','${s.status}','${s.photoID }');">
										<img style="height: 200px; width: 100%; display: block;" src="${domain}/${s.photoURL }" alt="${domain}/${s.photoURL }">
									</a>
									<div class="caption">
										<h3>举报人：${s.personName }</h3>
										<p>举报内容：${s.notes }</p>
										<p>
											    <c:if test="${s.status == 0}">
								         		<button class="btn btn-default" type="button"   onclick="pushST('${s.photoID}','2');">忽略</button>
								          		<button class="btn btn-default" type="button"   onclick="pushST('${s.photoID}','1');">删除</button>
								          		</c:if> 
								          		<c:if test="${s.status == 1}">
												<button class="btn button-danger" type="button" disabled>已删除</button>
								          		</c:if>
								          		<c:if test="${s.status == 2}">
												<button class="btn button-default" type="button" disabled>已忽略</button>
								          		</c:if>
										</p>
									</div>
								</div>
							</div>
        				</c:forEach>
					</div>
    
      <div class="panel-footer">
        <pagination:pagination param="max=${pageMaxSize}&total=${totalsize}&page=${page}&stitle=${stitle}"/>

      </div>
      </form>
</div>
 <!-- /container -->

   <!-- 模态框（Modal） -->
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
           aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
	            <div class="modal-header">
	              <button type="button" class="close"
	                      data-dismiss="modal" aria-hidden="true">
	                &times;
	              </button>
	              <h4 class="modal-title" id="myModalLabel">
					预览图片
	              </h4>
	            </div>
            		<div class="modal-body" id="myModalBody">
            		    <div id="J_pic_div" >
            				<img class="img-responsive" src="${domain}/${s.photoURL }" >
            			</div>
            			<div id="J_op_div">
	            			<button class="btn btn-default" type="button"   id="J_ignore_btn">忽略</button>
							<button class="btn btn-default" type="button"   id="J_delete_btn">删除</button>
            			</div>
            			<div id="J_del_div">
            			      <span class="label label-danger">已删除</span>
            			</div>
            			<div id="J_ignore_div">
						     <span class="label label-default">已忽略</span>
						</div>
                      </div>
                      
			            <div class="modal-footer">
			              <button type="button" class="btn btn-default"
			                      data-dismiss="modal">关闭
			              </button>
			            </div>
            </div>
          </div><!-- /.modal-content -->
        </div><!-- /.modal -->




<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>