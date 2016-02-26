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
function topDiv(id,status){
    if(status && status == '1'){
      $("#J_cont").hide();
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

                  }else if(msg=="repeat"){
                	  alert('该社长已有审核通过的社团，不允许再新建社团了');
                	  window.location.href =  window.location.href ;
                  }else if(msg=="less"){
                	  alert('社团信息不完善，不允许新建');
                	  window.location.href =  window.location.href ;
                  }else{
                	  alert('审核异常');
                  }            

              }

          });
      })
      
      
      
      
    }else if(status == '-1'){//--审核bu通过
      $("#myModalp").empty();
      $("#myModalp").text("审核不通过");
      $("#J_cont").show();
      
      $("#myModalBtn").click(function(){
      var con  = $("#J_cont").val();
		if(con.trim().length>0){
			 $.ajax({
	
	            url:"/society/dispass.action",
	
	            type:"post",
	
	            data:{"tid":id,"con":con,"status":status,"camefrom":'0'},
	
	            success:function(msg){
	
	                if(msg=="success"){
	
	
	                    window.location.href = window.location.href;
	
	                }            
	
	            }
	
	        });
		}else{
			alert('请填写审核意见');
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
          <th>申请时间</th>
          <th>社团名称</th>
          <th>社长</th>
          <th>联系电话</th>
          <th>申请理由</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="s" varStatus="ss">
         <tr>
          <th scope="row">${s.showTime }</th>
          <td><a href="/society/view.action?id=${s.societyID }">  ${s.societyName }</a> </td>
          <td>${s.adminName }</td>
          <td>${s.phoneNum }</td>
          <td>${s.applyNote }</td>
          <td>
          	<%-- <a class="J_pass"    tid="${s.societyID }" tname="${s.societyName }">审核通过</a>
           	<a class="J_dispass"  tid="${s.societyID }" tname="${s.societyName }">审核不通过</a>
            <a class="J_history"  tid="${s.societyID }" tname="${s.societyName }">审核历史</a> --%>
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${s.societyID}','1');">审核通过</button>&nbsp;
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${s.societyID}','-1');">审核不通过</button>&nbsp;
             <c:if test="${s.isLog==1}">
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="topDiv('${s.societyID}','log');">审核历史</button>&nbsp;
             </c:if>
          </td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    
    <div class="panel-footer">
        <pagination:pagination param="max=${pageMaxSize}&total=${totalsize}&page=${page}&stitle=${stitle}"/>

      </div>
      



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
                  <textarea class="form-control" placeholder="审核意见" required data-bv-notempty-message="请填写审核意见" rows="3" name="J_cont" id="J_cont"></textarea>
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


</div>





<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>