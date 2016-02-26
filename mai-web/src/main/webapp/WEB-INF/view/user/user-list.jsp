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
var _error = false;
function subActForm(){
	 var sid =  $("#J_schoolID").val();
     if(!sid){
    	 _error = true ; 
    	 $("#school_error").text("请选择学校");
            $("#school_error").show();
            $("#school_error").parent().removeClass('has-success').addClass('has-error');
     }else{
    	 _error = false ; 
            $("#school_error").parent().removeClass('has-error').addClass('has-success');
            $("#school_error").hide();
     }
	
}

function pushST(id,status){
	if(id && status){
		 $.ajax({

             url:"/user/pushST.action",

             type:"post",

             data:{"id":id,"status":status},

             success:function(msg){

                 if(msg=="success"){
					

                     window.location.href = "/user/list.action";

                 }            

             }

         });
	}
}
  
function expData(){
	$("#J_ser_form").attr("action","/user/export.action").submit();
	$("#J_ser_form").attr("action","/user/list.action");
}

function expRank(){
	$("#J_ser_form").attr("action","/user/expRank.action").submit();
	$("#J_ser_form").attr("action","/user/list.action");
}

$(function(){
	
	//导入消息提示
	var msg = "${msg}";
	if(msg){
		alert(msg);
	}
	
	//搜索bind
	 $("#J_ser_btn").click(function(){
		 $("#J_ser_form").attr("action","/user/list.action").submit();
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
	 
	 //学校下拉事件
	 
	 $("#J_xx_sel_btn").find("li").each(function(){
		 //查询条件保留
		
		 $(this).click(function(){
			 //设值
			 var tid = $(this).find("a").attr("tid");
			 var text =  $(this).find("a").text();
			 $("#J_xx_span").text(text);
			 $("#J_xx_span").append("<span class='caret'>");
			 $("#J_schoolID").val(tid);
			 $("#J_schoolName").val(text);
		 })
	 })
	 
	 $("#J_role_btn").find("li").each(function(){
		 //查询条件保留
		 var tid = $(this).find("a").attr("title");
		 if(tid == J_role){
			 $("#J_bbtn_role").text($(this).find("a").text());
			 $("#J_bbtn_role").append("<span class='caret'>");
		 }
		 $(this).click(function(){
			 //设值
			 var role =$(this).find("a").attr("title");
			 var text =  $(this).find("a").text();
			 $("#J_bbtn_role").text(text);
			 $("#J_bbtn_role").append("<span class='caret'>");
			 $("#J_role").val(role);
			//触动查询
			 $("#J_ser_btn").click();
		 })
	 })
	 
	 
	 //选择角色时的事件
	 $("#J_add_r_btn").find("li").each(function(){
		 $(this).click(function(){
			 //设值
			 var role =$(this).find("a").attr("title");
			 var text =  $(this).find("a").text();
			 $("#J_add_btn_role_btn").text(text);
			 $("#J_add_btn_role_btn").append("<span class='caret'>");
			 $("#J_add_role").val(role);
			 if(role=='0'){		 //普通成员
				 
				 $("#J_show_other").hide();
			 }else if(role=='1'){//社长
				 $("#J_show_other").show();
				 
			 }
		 })
	 })
	 
	 
	 
	 $("#J_up_btn").click(function(){
		 
		 var file = $("#J_file").val();
		 //alert(file);
		 if(file){
		  $("#myModal1").modal('show');  
	      
	      $("#J_file_form").attr("action","/user/importEX.action").submit();
		 }

	 });

	 //验证
	 $('#f2')
	    .bootstrapValidator({
	    		
	    		 message: 'This value is not valid',
	             feedbackIcons: {
	                 valid: 'glyphicon glyphicon-ok',
	                 invalid: 'glyphicon glyphicon-remove',
	                 validating: 'glyphicon glyphicon-refresh'
	             },
	             fields: {
	            	 J_schoolName: {
	                     validators: {
	                         notEmpty: {
	                             message: '请填写学校名称'
	                         }
	                         
	                     }
	                 }
	             }
	         })
	    .on('success.form.bv', function(e) {
	    	
	    	if(_error){
                _error = false;
                e.preventDefault();
                $(e.target).data('bootstrapValidator').disableSubmitButtons(false);
            }else{
            	 // Prevent form submission
    	        e.preventDefault();

    	        // Get the form instance
    	        var $form = $(e.target);

    	        // Get the BootstrapValidator instance
    	        var bv = $form.data('bootstrapValidator');

    	        // Use Ajax to submit form data

    	        $.ajax({

    	              url:"/user/add.action",

    	              type:"post",

    	              data:$('#f2').serialize(),// 要提交的表单 

    	              success:function(msg){

    	                  if(msg=="success"){
    						

    	                      window.location.href = "/user/list.action";

    	                  }else{
    	                	  alert('添加失败，请检查手机号是否已注册');
    	                  }            

    	              }

    	          });
            }
	    	
	       
	    });
		 
		 
	 
	 //时间
	  $('.form_datetime').datetimepicker({
	      weekStart: 1,
	      todayBtn:  1,
	      autoclose: 1,
	      todayHighlight: 1,
	      startView: 2,
	      forceParse: 0,
	      showMeridian: 1,
	      minView: 2,
	      language:'zh-CN'
	  }).on('changeDate show', function(e) {
	      
	  });
	}); 

</script>



<div class="jumbotron container-fluid">
<div class="panel-body">
      <%--<div class="row">--%>
      <!-- 搜索区域  -->
        <form id="J_ser_form" action="/user/list.action" class="form-inline" method="post">
         <input type="hidden" name="page" id="page" value="${page}"/>
        <div class="btn-group" style="margin: auto" role="group" aria-label="...">
            <div class="input-group" style="margin: auto;width: 300px;">
              <input type="text" class="form-control col-lg-12" id="J_keyword" name="keyword" placeholder="手机号或姓名" value="${keyword}">
             
              
            </div>
            
	      <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" style="width: 350px;">
	        <span class="input-group-addon" id="aBTime">注册开始时间</span>
	        <input class="form-control" size="16" type="text" value="${startTime_}" name="startTime_" readonly>
	      <span class="input-group-addon"><span class="glyphicon glyphicon-remove" ></span></span>
	        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
	      </div>
	      
	       <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" style="width: 350px;">
	          <span class="input-group-addon" id="aCTime">注册结束时间</span>
	        <input class="form-control" size="16" type="text" value="${endTime_}" name="endTime_" readonly>
	        
	        <span class="input-group-addon"><span class="glyphicon glyphicon-remove" ></span></span>
	        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
	      </div>
	    
	        
	        
		      <div class="input-group" style="margin: auto">
		         <span class="input-group-btn">
	                <button class="btn btn-default" id="J_ser_btn" type="button">搜索</button>
	              </span>
	          </div>
		      <div class="input-group" style="margin: auto">
	            <button class="btn btn-default" type="button" onclick="expData()" >导出</button>
	          </div>
	        </div>
       <!--  <div class="btn-group pull-left" style="margin: auto" role="group" aria-label="...">
          <div class="input-group" style="margin: auto">
            <button class="btn btn-default" type="button" data-toggle="modal"  data-target="#myModal" >新增人员</button>
          </div> -->
          
          
        </div>
        <input type="hidden" id="J_role" name="J_role" value="${J_role}"/>
        <input type="hidden" id="J_status" name="J_status" value="${J_status}"/>
         <!-- 搜索区域  -->
        </form>
        
        
        
        
        
           <form id="J_file_form" class="form-inline"  action="/user/importEX.action"   method="POST"  enctype="multipart/form-data" accept-charset="UTF-8" >
        
        		<div class="input-group" style="margin: auto">
          
           		<!-- <span class="input-group-addon" id="scurl">导入成员</span> -->
            	<input type="file" class="form-control col-lg-12" id="J_file" name="file" placeholder="导入" name="Filedata" aria-describedby="basic-addon1" />
           
          			<span class="input-group-btn">
            		<button class="btn btn-default" type="button" id="J_up_btn">点击上传</button>
            		</span>
          		</div>
          		
          		
	       <div class="btn-group pull-right" style="margin: auto" role="group" aria-label="...">
	          <div class="input-group" style="margin: auto">
	            <button class="btn btn-default" type="button" data-toggle="modal"  data-target="#myModal" >新增人员</button>
	          </div>
	          
	          
	        </div>
	        <div class="btn-group pull-right" style="margin: auto" role="group" aria-label="...">
	          <div class="input-group" style="margin: auto">
	            <button class="btn btn-default" type="button" onclick="expRank()" >导出社团排名统计</button>
	          </div>
	          
	          
	        </div>
           </form>
           
      
           </div>
<div style="padding-left: 20px;padding-right: 20px;">
	<table class="table table-bordered table-hover table-responsive">
      <thead>
        <tr>
          <th>
			
			<div class="dropdown">
		        <button class="btn btn-default dropdown-toggle" type="button" id="J_bbtn_role" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
		                      所有人
		          <span class="caret"></span>
		        </button>
		        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" id="J_role_btn">
		          <li><a href="JavaScript:void(0);" title="">所有人</a></li>
		          <li><a href="JavaScript:void(0);" title="0">普通用户</a></li>
		          <li><a href="JavaScript:void(0);" title="1">社长</a></li>
		        </ul>
		        
		      </div>

		  </th>
          <th>手机</th>
          <th>身份</th>
          <th>注册时间</th>
          <th>最后操作时间</th>
          <th>
			  <div class="dropdown">
		        <button class="btn btn-default dropdown-toggle" type="button" id="J_bbtn_st" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
		                      所有状态
		          <span class="caret"></span>
		        </button>
		        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" id="J_st_btn">
		          <li><a href="JavaScript:void(0);" title="">所有状态</a></li>
		          <li><a href="JavaScript:void(0);" title="1">启用</a></li>
		          <li><a href="JavaScript:void(0);" title="0">停用</a></li>
		        </ul>
		        
		      </div>

		  </th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="s" varStatus="ss">
         <tr>
          <th scope="row">${s.name }</th>
          <td>${s.phoneNum } </td>
          <td>${s.rolename } </td>
          <td>${s.createTime }</td>
          <td>${s.operateTime }</td>
          <td>
          	<%-- <a class="J_pass"    tid="${s.societyID }" tname="${s.societyName }">审核通过</a>
           	<a class="J_dispass"  tid="${s.societyID }" tname="${s.societyName }">审核不通过</a>
            <a class="J_history"  tid="${s.societyID }" tname="${s.societyName }">审核历史</a> --%>
             <c:if test="${s.isValid == 1 }">
             <button class="btn btn-info" type="button" data-toggle="modal"  >启用</button>&nbsp;
             <button class="btn btn-default" type="button" data-toggle="modal"  onclick="pushST('${s.userid}','0')">停用</button>&nbsp;
             </c:if>
             <c:if test="${s.isValid == 0 }">
             <button class="btn btn-default" type="button" data-toggle="modal"  onclick="pushST('${s.userid}','1')">启用</button>&nbsp;
             <button class="btn btn-danger" type="button" data-toggle="modal" >停用</button>&nbsp;
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

   <!-- 模态框（Modal） -->
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
           aria-labelledby="myModalLabel" aria-hidden="true">
        <form id="f2"  role="form" method="post" action="" class="form-horizontal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close"
                      data-dismiss="modal" aria-hidden="true">
                &times;
              </button>
              <h4 class="modal-title" id="myModalLabel">
				新建用户
              </h4>
            </div>
            <div class="modal-body" id="myModalBody">
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
                      <div class="col-sm-10">
                        <input type="J_name" class="form-control" id="J_name" name="J_name" placeholder="name" required data-bv-notempty-message="请填写姓名">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="inputPassword3" class="col-sm-2 control-label">手机</label>
                      <div class="col-sm-10">
                        <input type="J_phone" class="form-control" id="J_phone" name="J_phone" required data-bv-notempty-message="请填写手机" placeholder="mobile">
                      </div>
                    </div>
                   <div class="form-group">
                      <label for="inputPassword3" class="col-sm-2 control-label">身份</label>
                      <div class="col-sm-offset-2 col-sm-10">
                            <div class="dropdown" >
					  	      <button class="btn btn-default dropdown-toggle" type="button" id="J_add_btn_role_btn" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					  	        身份
					  	        <span class="caret"></span>
					  	      </button>
					  	      <ul class="dropdown-menu " aria-labelledby="dropdownMenu1" id="J_add_r_btn">
					  	        <li><a href="#" title="0">普通成员</a></li>
					  	        <li><a href="#" title="1">社长</a></li>
					  	      </ul>
					        </div>
					  </div>
                      <input type="hidden" name="J_add_role" id="J_add_role" value=""/>
                      
                      
                      </div> 
                      <div id="J_show_other" style="display: none" >
	                      <div class="form-group">
	                      <label for="inputPassword3" class="col-sm-2 control-label">社团名称</label>
	                      <div class="col-sm-10">
	                        <input type="text" class="form-control" id="J_societyName" name="J_societyName" required data-bv-notempty-message="请填写社团名称" placeholder="社团名称">
	                      </div>
	                    </div>
	                  
                    </div>
                      <div class="form-group">
	                      <label for="inputPassword3" class="col-sm-2 control-label">学校</label>
	                        <input id="J_schoolID" name="J_schoolID" value="" type="hidden">
	                        <input id="J_schoolName" name="J_schoolName" value="" type="hidden">
								 <div class="col-sm-offset-2 col-sm-10">
	                            <div class="dropdown" >
						  	      <button class="btn btn-default dropdown-toggle" type="button" id="J_xx_span" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
						  	              学校
						  	        <span class="caret"></span>
						  	      </button>
						  	      <small style="display: none" class="help-block input-group" id="school_error"></small>
						  	      <ul class="dropdown-menu " aria-labelledby="dropdownMenu1" id="J_xx_sel_btn" style="overflow:scroll;height: 100px;">
								                      <c:forEach var="s" items="${schoolList }">
								                      	
								                  	     <li><a href="#" tid="${s.schoolID }">${s.schoolName }</a></li>
								  					  </c:forEach>
						  	      </ul>
						        </div>
						  		</div>
	                    </div>

						<!-- <div class="form-group">
    						<div class="col-sm-offset-2 col-sm-10">
      							<button type="button" id="J_sub_btn" class="btn btn-default">提交</button>
    					    </div>
  						</div> -->
                    </div>
                      
            <div class="modal-footer">
              <button type="submit" id="J_sub_btn" onclick="subActForm()" class="btn btn-default">提交</button>
              <button type="button" class="btn btn-default"
                      data-dismiss="modal">关闭
              </button>
            </div>
            </div>
          </div><!-- /.modal-content -->
          </form>
        </div><!-- /.modal -->



<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"   
   aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" style="width:500px;margin-left:20%;padding-top: 20%">   
   
   <div class="modal-dialog">  
       <div class="modal-content" >  
           <span style="text-align:center;color:red">文件正在上传请勿刷新页面！</span><br />  
            
			 <div class="progress progress-striped active">
			   <div class="progress-bar progress-bar-success" role="progressbar" 
			      aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" 
			      style="width: 90%;">
			      <span class="sr-only">90% 完成</span>
			   </div>
			</div>
       </div>  
   </div>  
</div>  




<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>