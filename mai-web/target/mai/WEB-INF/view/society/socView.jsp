<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社团管理</title>
<style> 
  .black_overlay{  display: none;  position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 100%;  background-color: black;  
  z-index:1001;  -moz-opacity: 0.8;  opacity:.80;  filter: alpha(opacity=80);  } 
   .white_content {  display: none;  position: absolute;  top: 25%;  left: 25%;  width: 50%;  height: 50%; 
    padding: 16px;  border: 16px solid orange;  background-color: white;  z-index:1002;  overflow: auto;  }  
</style> 
</head>
<body>
<script type="text/javascript" src="/resources/js/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">

function topDiv(id,name,status){
    if(status && status == '1'){
      $("#J_cont").hide();
      $("#J_log_ul").hide();
      $("#myModalp").show();
      $("#myModalp").text("确定审核通过吗？");
      
      //bind事件--审核通过
      $("#myModalBtn").click(function(){
    	  $.ajax({

              url:"/society/pass.action",

              type:"post",

              data:{"tid":id},

              success:function(msg){

                  if(msg=="success"){


                      window.location.href = window.location.href;

                  }            

              }

          });
      })
      
      
      
      
    }else if(status == '-1'){//--审核bu通过
      $("#myModalp").empty();
      $("#myModalp").text("审核不通过");
      $("#J_log_ul").hide();
      $("#J_cont").show();
      $("#myModalBtn").click(function(){
      var con  = $("#J_cont").val();
		if(con.trim().length>0){
			 $.ajax({
	
	            url:"/society/dispass.action",
	
	            type:"post",
	
	            data:{"tid":id,"con":con},
	
	            success:function(msg){
	
	                if(msg=="success"){
	
	
	                    window.location.href = window.location.href;
	
	                }            
	
	            }
	
	        });
		}
      })
      
      
    }else if(status == 'log'){//审核历史
    	$("#myModalp").empty();
        $("#myModalp").text("审核历史");
        $("#J_cont").hide();
        $("#J_log_ul").show();
        
        $.ajax({
			
            url:"/society/loglist.action",

            type:"post",

            data:{"tid":id},
            
            

            success:function(data){
            	$("#J_log_ul").html("");
            	$("#J_log_ul").append(data);
				
            }

        });
    }
  }
$(function(){
	//通过
	$(".J_pass").click(function(){
		 var tid   = $(this).attr("tid");
		 var tname = $(this).attr("tname");
		 if(confirm("确定要通过"+tname+"社团的审核吗?")){
			 $.ajax({

	             url:"/society/pass.action",

	             type:"post",

	             data:{"tid":tid},

	             success:function(msg){

	                 if(msg=="success"){

	                     //art.dialog.tips('评论成功');

	                     window.location.href = window.location.href;

	                 }            

	             }

	         });
		 }
	});
	
	//不通过
	$(".J_dispass").click(function(){
		 var tid   = $(this).attr("tid");
		 var tname = $(this).attr("tname");
		 $("#J_socid").val(tid);
		 $("#J_dis_div").css("display","block");
			$("#fade").css("display","block");
	});
	
	//不通过确认按钮
	$("#J_dis_btn").click(function(){
		var con  = $("#J_con").val();
		var tid  = $("#J_socid").val();
		if(con.trim().length>0){
			 $.ajax({
	
	            url:"/society/dispass.action",
	
	            type:"post",
	
	            data:{"tid":tid,"con":con},
	
	            success:function(msg){
	
	                if(msg=="success"){
	
	                    //art.dialog.tips('评论成功');
	
	                    window.location.href = window.location.href;
	
	                }            
	
	            }
	
	        });
		}else{
			alert("请填写理由。");
		}
	})
	
	//历史
	$(".J_history").click(function(){
		$("#light").css("display","block");
		$("#fade").css("display","block");
		
	});
	
	//确认---历史弹层
	$("#J_ok_btn").click(function(){
		$("#light").css("display","none");
		$("#fade").css("display","none");
	});
	
	
	//关闭--审核不通过的弹层
	$("#J_dis_close").click(function(){
		$("#J_dis_div").css("display","none");
		$("#fade").css("display","none");
	});
	
	
})

</script>
<!-- Main component for a primary marketing message or call to action -->
<div class="jumbotron container-fluid">
<!-- <nav class="navbar navbar-inverse">
 <p class="navbar-text">社团详情</p>
</nav> -->
<div class="jumbotron">
<div id="J_views" style="padding-left: 20%;padding-right: 20px;">

<h5>社长姓名：${society.adminName}</h5>
<h5>社长电话：${society.phoneNum}</h5>
<h5>社团名称：${society.societyName}</h5>
<h5>社员人数：${society.memberNum}</h5>
<h5>所属学校：${society.schoolName}</h5>
<h5>申请理由：${society.applyNote}</h5>
</div>

    <div class="btn-group" role="group" aria-label="..." style="padding-left: 20%;padding-right: 20px;">
        <p></p>
            <c:if test="${society.status =='0'}">
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${society.societyID}','${society.societyName}','1');">审核通过</button>
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${society.societyID}','${society.societyName}','-1');">审核不通过</button>
              <c:if test="${society.isLog==1}">
               <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${society.societyID}','${society.societyName}','log');">审核历史</button>
              </c:if>
            </c:if>
            <c:if test="${society.status =='1'}">
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${society.societyID}','${society.societyName}','-1');">审核不通过</button>
              <c:if test="${society.isLog==1}">
             	<button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${society.societyID}','${society.societyName}','log');">审核历史</button>
           	  </c:if>
            </c:if>
            <c:if test="${society.status =='-1'}">
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${society.societyID}','${society.societyName}','1');">审核通过</button>
             <c:if test="${society.isLog==1}">
             	<button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${society.societyID}','${society.societyName}','log');">审核历史</button>
           	 </c:if>
            </c:if>
            
            <button class="btn btn-default" type="button" onclick="window.location.href = '/society/list.action'">返回社团列表</button>
    </div>
</div>

</div> <!-- /container -->


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

              </h4>
            </div>
            <div class="modal-body" id="myModalBody">
                  <p class="text-center" id="myModalp"></p>
                  <textarea class="form-control" placeholder="审核意见" rows="3" name="J_cont" id="J_cont"></textarea>
            </div>
            <div>
            	 <ul id="J_log_ul" style="display: none;" >
   				 </ul>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default"
                      data-dismiss="modal">关闭
              </button>
              <button type="button" class="btn btn-primary" id="myModalBtn">确定
              </button>
            </div>
          </div><!-- /.modal-content -->
        </div><!-- /.modal -->
        </div>
</div>

<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>