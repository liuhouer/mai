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
function topDiv(id,name,status){
    if(status && status == '1'){
      $("#J_cont").hide();
      $("#myModalp").show();
      $("#J_log_ul").hide();
      $("#myModalp").text("确定审核通过吗？");
      //bind事件
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
      
      
      
      
    }else if(status == '-1'){
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
	
	            data:{"tid":id,"con":con,"status":status,"camefrom":'2'},//来源-审核通过
	
	            success:function(msg){
	
	                if(msg=="success"){
	
	
	                    window.location.href = window.location.href;
	
	                }            
	
	            }
	
	        });
		}
      })
      
      
    }else if(status == '-2'){//下架
        $("#myModalp").empty();
        $("#myModalp").text("下架");
        $("#J_log_ul").hide();
        $("#J_cont").show();
        $("#myModalBtn").click(function(){
        var con  = $("#J_cont").val();
  		if(con.trim().length>0){
  			 $.ajax({
  	
  	            url:"/society/dispass.action",
  	
  	            type:"post",
  	
  	            data:{"tid":id,"con":con,"status":"3","camefrom":'2'},
  	
  	            success:function(msg){
  	
  	                if(msg=="success"){
  	
  	
  	                    window.location.href = window.location.href;
  	
  	                }else{
  	                	alert('发生了异常');
  	                	window.location.href = window.location.href;
  	                }
  	                
  	
  	            }
  	
  	        });
  		}
        })
        
        
      }else if(status == 'log'){
    	$("#myModalp").empty();
        $("#myModalp").text("审核历史");
        $("#J_cont").hide();
        $("#J_log_ul").show();
        $.ajax({
			
            url:"/society/loglist.action",

            type:"post",

            data:{"tid":id,"path":'1'},
            
            

            success:function(data){
            	$("#J_log_ul").html("");
            	$("#J_log_ul").append(data);
				
            }

        });
    }else if(status == 'log2'){
    	$("#myModalp").empty();
        $("#myModalp").text("审核历史");
        $("#J_cont").hide();
        $("#J_log_ul").show();
        $.ajax({
			
            url:"/society/loglist.action",

            type:"post",

            data:{"tid":id,"path":'2'},
            
            

            success:function(data){
            	$("#J_log_ul").html("");
            	$("#J_log_ul").append(data);
				
            }

        });
    }
  }

$(function(){
	//初始化选中tag事件 
	var status = "${status}";
	if(status){
		$("#J_tag").find("li").each(function(){
				var tid = $(this).attr("tid");
				if(status==tid){
					//移除所有active
					$("#J_tag").find(".active").removeClass();
					$(this).addClass("active");
				}
				
		})
	}
	
	
	//处理tag点击事件
	$("#J_tag").find("li").each(function(){
		$(this).click(function(){
			//移除所有active
			$("#J_tag").find(".active").removeClass();
			$(this).addClass("active");
			//重新加载数据
			var status = $(this).attr("tid");
			window.location.href="/society/list.action?status="+status;
		});
	})
	
	
	
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
	
	
	
	
});

</script>



<div class="jumbotron container-fluid">

<div class="panel">


<div class="btn-group" role="group" aria-label="...">
<ul class="nav nav-pills" id="J_tag">
  <li role="presentation" class="active" tid="0"><a >待审核</a></li>
  <li role="presentation"  tid="2"><a >审核通过</a></li>
  <li role="presentation"  tid="-1"><a >审核不通过</a></li>
  <li role="presentation"  tid="1"><a >已发布</a></li>
  <li role="presentation"  tid="3"><a >已下架</a></li>
</ul>
</div>


<div class="panel-body">
   <form id="J_ser_form" action="/society/list.action" class="" method="post">
            <div class="input-group" style="">
              <input type="text" class="form-control col-lg-5" id="J_keyword" name="keyword" placeholder="社团名称／社长名称／社长电话搜索" value="${keyword }">
              <span class="input-group-btn">
                <button class="btn btn-default" id="J_ser_btn" type="submit">搜索</button>
              </span>
            </div>
    <input type="hidden" name="page" id="page" value="${page}"/>
    <input type="hidden" name="status" id="J_status" value="${status}"/>
	
      </form>



</div>

	<table class="table table-bordered table-hover table-responsive">
      <thead>
        <tr>
          <th>LOGO</th>
          <th>社团名称</th>
          <th>关注数</th>
          <th>点赞数</th>
          <th>星级</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="s" varStatus="ss">
         <tr>
            <td width="500px"> <%-- <img alt="" src="${s.coverURL }"> --%>
          <div class="col-sm-6 col-md-3">
    		<a href="${s.coverURL }" class="thumbnail">
      			<img style="height: 100px; width: 100%; display: block;" src="${s.coverURL }" alt="">
    		</a>
  		  </div>
           </td> 
          <td>
              <c:choose>
                  <c:when test="${s.status==1}">
                      <a href="/society/societyInfo.action?societyID=${s.societyID }">  ${s.societyName }</a>
                  </c:when>
                  <c:otherwise>
                      <a href="/society/view.action?id=${s.societyID }">  ${s.societyName }</a>
                  </c:otherwise>
              </c:choose>
              </td>
          <td>${s.followNum }</td>
          <td>${s.praiseNum }</td>
          <td>
          
          <c:if test="${ s.level !='' && s.level !=NULL} ">
          ${s.level }星
          </c:if>
          </td>
          <td>
           	<%-- <a class="J_dispass" tid="${s.societyID }" tname="${s.societyName }">审核不通过</a>
            <a class="J_history" tid="${s.societyID }" tname="${s.societyName }">审核历史</a> --%>
            <c:if test="${s.status==2}"><!-- 审核通过 对应 【审核不通过 】-->
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${s.societyID}','${s.societyName}','-1');">审核不通过</button>&nbsp;
             </c:if>
             <c:if test="${s.status==1}"><!-- 已发布 对应 【下架 】-->
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${s.societyID}','${s.societyName}','-2');">下架</button>&nbsp;
             <c:if test="${s.isLog==1}">
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${s.societyID}','${s.societyName}','log');">审核历史</button>&nbsp;
             </c:if>
             </c:if>
             
             <c:if test="${s.status==3}"><!-- 下架对应 【审核不通过和历史】-->
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${s.societyID}','${s.societyName}','-1');">审核不通过</button>&nbsp;
              <c:if test="${s.isLog==1}">
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${s.societyID}','${s.societyName}','log2');">审核历史</button>&nbsp;
             </c:if>
             </c:if>
          </td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    
    
     <div class="panel-footer">
        <pagination:pagination param="max=${pageMaxSize}&total=${totalsize}&page=${page}&stitle=${stitle}"/>

      </div>

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
            	 <ul id="J_log_ul" style="display: none" >
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


</div>

<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>