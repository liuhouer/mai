<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  import="com.mai.util.ConfigUtil"%>

<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<c:set var="domain" value="${domain }" />
<c:set var="ThumbnailParam" value="<%=ConfigUtil.getThum()%>" />
<script type="text/javascript" src="/resources/js/jquery/1.11.3/jquery.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>明星管理</title>
</head>
<body>


<div class="jumbotron container-fluid">
</div>
<div style="padding-left: 20px;padding-right: 20px;">
<form id="J_ser_form" action="/card/list.action" class="form-inline" method="post">
         <input type="hidden" name="page" id="page" value="${page}"/>
        <div class="btn-group" style="margin: auto" role="group" aria-label="...">
            
	      <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" >
	        <span class="input-group-addon" id="aBTime">开始时间</span>
	        <input class="form-control" size="16" type="text" value="${startTime_}" name="startTime_" readonly>
	      <span class="input-group-addon"><span class="glyphicon glyphicon-remove" ></span></span>
	        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
	      </div>
	      
	       <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" >
	          <span class="input-group-addon" id="aCTime">结束时间</span>
	        <input class="form-control" size="16" type="text" value="${endTime_}" name="endTime_" readonly>
	        
	        <span class="input-group-addon"><span class="glyphicon glyphicon-remove" ></span></span>
	        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
	      </div>
	       <div class="input-group" style="margin: auto">
	        <input class="form-control" size="16" type="text" placeholder="明星名" value="${keyword}" name="keyword" >
	       </div>
	        
	        
		      <div class="input-group" style="margin: auto">
		         <span class="input-group-btn">
	                <button class="btn btn-default" id="J_ser_btn" type="submit">搜索</button>
	              </span>
	          </div>
	          
	          <div class="input-group" style="margin: auto">
				<button class="btn btn-default" type="button"
					data-toggle="modal" data-target="#myModal">新增</button>
			  </div>
	        </div>
          
        </div>
         <!-- 搜索区域  -->
        </form>

		<!-- <div class="btn-group pull-left" style="margin: auto;padding-bottom: 30px;" role="group" aria-label="...">
			


		</div> -->
	<table class="table table-bordered table-hover table-responsive">
      <thead>
        <tr>
          <th>图片</th>
          <th>明星名字</th>
          <th>默认位置</th>
          <th>图片上传时间</th>
          <th>状态</th>
          <th>操作  </th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="s" varStatus="ss">
         <tr>
          <td>
	          <div class="col-xs-6 col-md-6">
	    		<a href="${domain}/${s.photoURL }" target="_blank" class="thumbnail">
	      			<img src="${domain}/${s.photoURL }${ThumbnailParam}" alt="" style="width: 200px;height: 200px">
	    		</a>
	  		  </div>
          </td>
          <td>${s.cardStarName }</td>
          <td>
          <c:if test="${s.dx == -1}">居左</c:if>
          <c:if test="${s.dx == 0}">居中</c:if>
          <c:if test="${s.dx == 1}">居右</c:if>
          </td>
          <td>${s.showTime }</td>
          <td> <c:if test="${s.isForbidden ==0}">启用</c:if> <c:if test="${s.isForbidden ==1}">禁用</c:if>  </td>
          <td>
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="" onclick="ban('${s.cardStarPhotoID}','${s.isForbidden}')"><c:if test="${s.isForbidden ==0}">禁用</c:if> <c:if test="${s.isForbidden ==1}">启用</c:if></button>&nbsp;
             <button class="btn btn-default" type="button"  onclick="del('${s.cardStarPhotoID}')">删除图片</button>&nbsp;
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
        <div class="modal-dialog">
          <div class="modal-content">
	            <div class="modal-header">
	              <button type="button" class="close"
	                      data-dismiss="modal" aria-hidden="true">
	                &times;
	              </button>
	              <h4 class="modal-title" id="myModalLabel">
					新增图片
	              </h4>
	            </div>
            		<form class="form-inline" method="post" action="/card/upload.action" id="aform" name="aform" enctype="multipart/form-data">
            		<div class="modal-body" id="myModalBody" style="height: 450px;overflow: scroll;">
            		

						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon">明星名字</span> <input type="text"
									class="form-control" placeholder="明星名字" value="" name="cardStarName"
									aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="form-group">
							<div class="dropdown">
								<button class="btn btn-default dropdown-toggle" type="button"
									id="J_bbtn_st" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="true">
									图片位置 <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" aria-labelledby="dropdownMenu1"
									id="J_st_btn">
									<li><a href="JavaScript:void(0);" title="-1">居左</a></li>
									<li><a href="JavaScript:void(0);" title="0">居中</a></li>
									<li><a href="JavaScript:void(0);" title="1">居右</a></li>
								</ul>
								 <input type="hidden" id="J_dx" name="dx" value="-1">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon" id="c3">图片</span>
										<input type="file" class="form-control" id="J_photo"
											placeholder="图片" name="photo" accept=".png"
											aria-describedby="basic-addon1" required
											data-bv-notempty-message="图片不能为空">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon" id="c3">语音</span>
										<input type="file" class="form-control" id="J_audio"
											placeholder="语音|mp3" name="audio" accept=".amr"
											aria-describedby="basic-addon1" required
											data-bv-notempty-message="语音不能为空">
							</div>
						</div>

					</div>
                      
			            <div class="modal-footer">
						 <button type="submit"  class="btn btn-default" id="J_save_btn">保存</button>
			              <button type="button" class="btn btn-default" id="J_close"
			                      data-dismiss="modal">关闭
			              </button>
			            </div>
						</form>
            </div>
          </div><!-- /.modal-content -->
        </div><!-- /.modal -->
        
        




<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
<script type="text/javascript">
$(function(){
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
})


$(function(){
	
	 $("#J_st_btn").find("li").each(function(){
		 $(this).click(function(){
			 //设值
			 var st = $(this).find("a").attr("title");
			 var text =  $(this).find("a").text();
			 $("#J_bbtn_st").text(text);
			 $("#J_bbtn_st").append("<span class='caret'>");
			 $("#J_dx").val(st);
		 })
	 })

	 $("#aform").bootstrapValidator({fields: {
		 cardStarName: {
             validators: {
            	 notEmpty: {
                     message: '明星名字不为空'
                 },
             }
         },
         photo: {
             validators: {
            	 notEmpty: {
                     message: '图片不为空'
                 },
                 file: {
                     extension: 'png',
                     type: 'image/png',
                     maxSize: 2*1024*1024,
                     message: '请上传“png”格式的小于2M的文件.'
                 }
             }
         },
         audio: {
             validators: {
            	 notEmpty: {
                     message: '语音不为空'
                 }
             }
         }
     }}).on('success.form.bv', function(e) {
          /*  if(check_error){
               check_error = false;
               e.preventDefault();
               $(e.target).data('bootstrapValidator').disableSubmitButtons(false);
           } */
     });
	
	
})

//删除一个标签
function delTag(tagID){


}


function del(pID){
	if(confirm("确定要删除吗?")){
		 $.ajax({

             url:"/card/remove.action",

             type:"post",

             data:{"cardStarPhotoID":pID},

             success:function(msg){

                 if(msg=="success"){

                     alert('删除成功');

                     window.location.href = window.location.href;

                 }            

             }

         });
		
	}
}
//编辑标签
function  ban(pID,pST){
	
	var hua = "";
	if(pST=='1'){
		hua = "启用";
	}else{
		hua = "禁用";
	}
	if(confirm("确定要"+hua+"吗?")){
		
		$.ajax({

            url:"/card/ban.action",

            type:"post",

            data:{"cardStarPhotoID":pID,"isForbidden":pST},

            success:function(msg){

                if(msg=="success"){

                	alert(hua+'成功');

                    window.location.href = window.location.href;

                }            

            }

        });
		

	}


}



</script>
</body>
</html>