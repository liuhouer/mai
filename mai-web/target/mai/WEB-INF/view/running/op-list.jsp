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
     //判断字符串是否为数字     //判断正整数 /^[1-9]+[0-9]*]*$/  
	  function isNum(val){
	       var re = /^[0-9]+.?[0-9]*$/;   
	       var nubmer = val;
	      
	       if (!re.test(nubmer)){
	          return false;
	       }else{
	    	   return true;
	       }
	  }
     
	  function topDiv(id,level,praiseNum,followNum){
		    if(id == '1'){
		      $("#J_id_div").hide();
		      $("#myModalLabel").text("新增星级");
		    //新增
			  /* $("#J_sub_btn").click(function(){
				  var J_level = $("#J_level").val();
				  var J_praiseNum = $("#J_praiseNum").val();
				  var J_followNum = $("#J_followNum").val();
				  if(J_praiseNum && J_followNum && isNum(J_praiseNum) &&  isNum(J_followNum)){
				  
				  $.ajax({

		              url:"/level/add.action",

		              type:"post",

		              data:$('#f2').serialize(),// 要提交的表单 

		              success:function(msg){

		                  if(msg=="success"){
							

		                      window.location.href = "/level/list.action";

		                  }else{
		                	  alert('添加失败，请检查信息是否正确');
		                  }            

		              }

		          });
				  
				  }else{
					  alert('请填写正确的信息');
				  }
			 }) */
		      
		    }else{
		    	$("#J_id_div").show();
		    	
		    	//设置编辑的数值----start
		    	$("#J_ruleid").val(id);
		    	$("#J_level_panel").text(level);
		    	$("#J_level").val(level);
		    	$("#J_praiseNum").val(praiseNum);
				$("#J_followNum").val(followNum);
				//设置编辑的数值----end
				
			    $("#myModalLabel").text("编辑星级");
			    //编辑
				 /* $("#J_sub_btn").click(function(){ 
					  var J_level = $("#J_level").val();
					  var J_praiseNum = $("#J_praiseNum").val();
					  var J_followNum = $("#J_followNum").val();
					  if(J_praiseNum && J_followNum && isNum(J_praiseNum) &&  isNum(J_followNum)){
					  
					  $.ajax({

			              url:"/level/add.action",

			              type:"post",

			              data:$('#f2').serialize(),// 要提交的表单 

			              success:function(msg){

			                  if(msg=="success"){
								

			                      window.location.href = "/level/list.action";

			                  }else{
			                	  alert('编辑失败，请检查信息是否正确');
			                  }            

			              }

			          });
					  
					  }else{
						  alert('请填写正确的信息');
					  }
				 }) */
		    }
	  }
$(function(){
	$('#f2')
    .bootstrapValidator({
    		
    		 message: 'This value is not valid',
             feedbackIcons: {
                 valid: 'glyphicon glyphicon-ok',
                 invalid: 'glyphicon glyphicon-remove',
                 validating: 'glyphicon glyphicon-refresh'
             },
             fields: {
            	 followNum: {
                     validators: {
                         notEmpty: {
                             message: '关注数不能为空'
                         },
                         digits: {
                             message: '请输入整数'
                         }
                     }
                 },
                 praiseNum: {
                     validators: {
                         notEmpty: {
                             message: '点赞数不能为空'
                         },
                         digits: {
                             message: '请输入整数'
                         }
                     }
                 }
             }
         })
    .on('success.form.bv', function(e) {
        // Prevent form submission
        e.preventDefault();

        // Get the form instance
        var $form = $(e.target);

        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

        // Use Ajax to submit form data

		  $.ajax({

            url:"/level/add.action",

            type:"post",

            data:$('#f2').serialize(),// 要提交的表单 

            success:function(msg){

                if(msg=="success"){
					

                    window.location.href = "/level/list.action";

                }else{
              	  	alert('编辑失败，请检查信息是否正确');
                }            

            }

        });
    });
	 
	 
}); 

</script>



<div class="jumbotron container-fluid">


<div style="padding-left: 20px;padding-right: 20px;">

 		<div class="btn-group pull-right" style="margin: auto" role="group" aria-label="...">
          <div class="input-group" style="margin: auto">
            <button class="btn btn-default" type="button" data-toggle="modal"  data-target="#myModal" onclick="topDiv('1','','','')">新增星级</button>
          </div>
          
          
        </div>
 <form id="J_ser_form" action="/society/list.action" class="form-inline">
    <input type="hidden" name="page" id="page" value="${page}"/>
    <input type="hidden" name="status" id="J_status" value="${status}"/>
	<table class="table table-bordered table-hover table-responsive">
      <thead>
        <tr>
          <th>级别</th>
          <th>点赞数</th>
          <th>关注数</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="s" varStatus="ss">
         <tr>
          <th scope="row">${s.level }</th>
          <td>${s.praiseNum }</td>
          <td>${s.followNum }</td>
          <td>
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${s.ruleID}','${s.level }','${s.praiseNum }','${s.followNum }')">编辑</button>&nbsp;
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

<!-- 审核历史弹层 -->
 
<!-- <div id="light" class="white_content"> 
<p>审核历史. </p>
    <ul id="J_log_ul">
    </ul>
    <p style="margin-bottom:10px;">
	<a id="J_ok_btn" >确认</a>
	</p>
</div>  -->

<!-- 阴影区域 -->
<!-- <div id="fade" class="black_overlay"> 
</div>  -->


<!-- 审核不通过弹层 -->

<!-- <div id="J_dis_div" class="white_content"> 
<p>审核未通过，理由是:</p>
   <input type="hidden" id="J_socid"/>
   <textarea id="J_con">
   </textarea>
    <p style="margin-bottom:10px;">
	<a id="J_dis_btn" >确认</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a id="J_dis_close" >关闭</a>
	</p>
</div>  -->






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
				新建星级
              </h4>
            </div>
            <div class="modal-body" id="myModalBody">
                 <form  id="f2"  role="form" method="post" action="" class="form-horizontal">
                 <div class="form-group">
                      <label for="inputEmail3" class="col-sm-2 control-label">星级</label>
                      <label for="inputEmail3" class="col-sm-2 control-label" id="J_level_panel" >${max }</label>
                      <input type="hidden" name="level" id="J_level" value="${max }"/>
                      <div id="J_id_div">
                         <input type="hidden" name="ruleid" id="J_ruleid" value=""/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-2 control-label">点赞数</label>
                      <div class="col-sm-10">
                        <input type="J_name" class="form-control" id="J_praiseNum" name="praiseNum" placeholder="praiseNum">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="inputPassword3" class="col-sm-2 control-label">关注数</label>
                      <div class="col-sm-10">
                        <input type="J_phone" class="form-control" id="J_followNum" name="followNum"  placeholder="followNum">
                      </div>
                    </div>

						<div class="form-group">
    						<div class="col-sm-offset-2 col-sm-10">
      							<button type="submit" id="J_sub_btn" class="btn btn-default">提交</button>
    					    </div>
  						</div>
                  </form>
                    </div>
                    
                    
                    
                    <div class="modal-footer">
		              <button type="button" class="btn btn-default"
		                      data-dismiss="modal">关闭
		              </button>
            		</div>  
            </div>
            
          </div><!-- /.modal-content -->
        </div><!-- /.modal -->
</div>








<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>