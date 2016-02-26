<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  import="com.mai.util.ConfigUtil"%>

<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<c:set var="domain" value="<%=ConfigUtil.getPicdomain()%>" />
<c:set var="ThumbnailParam" value="<%=ConfigUtil.getThum()%>" />
<script type="text/javascript" src="/resources/js/jquery/1.11.3/jquery.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>照片管理</title>
<style type="text/css">
#div1 { 
width: 450px; 
border-top: #0066cc 1px hidden; 
border-left: #0066cc 1px hidden; 
} 
#div1 ul { 
width: 450px; 
margin: 0px; 
} 
#div1 li { 
float: left; 
width: 200px; 
height: 50px; 
list-style-type: none; 
border-right:#0066cc 1px hidden; 
border-bottom: #0066cc 1px hidden; 
text-align: left; 
line-height: 50px; 
} 

#fileList img{
	max-width: 100px;
	max-height: 100px;
}
</style>
</head>
<body>




<div class="jumbotron container-fluid">
</div>
<div style="padding-left: 20px;padding-right: 20px;">
<form id="J_ser_form" action="/running/wall.action" class="form-inline" method="post">
         <input type="hidden" name="page" id="page" value="${page}"/>
        <div class="btn-group" style="margin: auto" role="group" aria-label="...">
            
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
          <th>图片上传时间</th>
          <th>标签数量</th>
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
          <td>${s.showTime }</td>
          <td>${s.tagsize }</td>
          <td>
             <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myTag" onclick="topTag('${s.photoID}')">标签管理</button>&nbsp;
             <button class="btn btn-default" type="button"  onclick="delPic('${s.photoID}')">删除图片</button>&nbsp;
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
            		<form class="form-inline" method="post" action="/running/upload.action" id="aform" name="aform" enctype="multipart/form-data">
            		<div class="modal-body" id="myModalBody" style="height: 450px;overflow: scroll;">
            		
						<div class="form-group">
							<label for="fileElem" class="form-control">选择图片</label> 
							<input type="file"  multiple style="opacity: 0;"  id="fileElem"  required name="files" onchange="handleFiles(this)"
                       aria-describedby="basic-addon1">
                            <label >支持jpg，png等格式的图片，最多5张</label> 
						</div>
						<div id="fileList" style="width:100%;height:100px;">
						

						</div>
						
                      </div>
                      
			            <div class="modal-footer">
						 <button type="submit" id="mysubmit2" class="btn btn-default" id="J_save_btn">保存</button>
			              <button type="button" class="btn btn-default" id="J_close"
			                      data-dismiss="modal">关闭
			              </button>
			            </div>
						</form>
            </div>
          </div><!-- /.modal-content -->
        </div><!-- /.modal -->
        
        
       <!-- 编辑Tag模态框（Modal） -->
      <div class="modal fade" id="myTag" tabindex="-1" role="dialog"
           aria-labelledby="myModalLabel2" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
	            <div class="modal-header">
	              <button type="button" class="close"
	                      data-dismiss="modal" aria-hidden="true">
	                &times;
	              </button>
	              <h4 class="modal-title" id="myModalLabel2">
					编辑标签
	              </h4>
	            </div>
            		<form  method="post" action="/running/newTag.action" id="tagform" name="tagform" enctype="multipart/form-data">
            		<input  type="hidden" name="photoID" id="J_pid"/>
            		<div class="modal-body" id="J_main_body" >
            		    <div class="input-group">
            		        <button type="button" id="J_add_tag" class="btn btn-default" >添加</button>
						</div>
						
						<div id="div1" class="input-group" >
            		      <ul class="list-unstyled  spaced" id="J_ul_tag">
								<!-- <li style="margin-top: 20px;"><span style="margin-right: 10px;" class="label label-success">90后</span> <span   id="" class="glyphicon glyphicon-remove"  ></span></li>
								<li style="margin-top: 20px;"><span style="margin-right: 10px;" class="label label-success">活泼</span> <span   id="" class="glyphicon glyphicon-remove"  ></span></li>
								 -->
								
							</ul>
							
						</div>
						
						<!-- <div class="input-group"  id="J_def_tag">
            		       <input type="text"  maxlength="10" required  name="newtag" placeholder="NewTag [长度少于10个字]"  style="margin-right: 10px;width: 200px;"  />
            		       <span   name="J_del_ipt" class="glyphicon glyphicon-remove"  onclick="delInput(this)"></span>
						</div> -->
						<div id="J_ipt_div">
						</div>
                      </div>
                      
			            <div class="modal-footer">
						 <button type="submit"  class="btn btn-default" id="J_tag_btn">保存</button>
			              <button type="button" class="btn btn-default" id="J_close"
			                      data-dismiss="modal"  >关闭
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

//清除未保存的标签
function clearTag(){
	$("input[name='newtag']").each(function(){
		$(this).parent().remove();
	})   
}

function delPic(photoID){
	if(confirm("确定要删除吗?")){
		if(photoID){
			$.ajax({

				url : "/running/delPic.action",

				type : "post",

				data : {
					"photoID" : photoID
				},

				success : function(data) {
					console.log(data);
					window.location.href = window.location.href;
				}

			});
		}
	}
}

$(function(){
	//添加标签按钮
	$("#J_add_tag").click(function(){
		
		//判断超过100条的限制
		var limit = $("#J_limit").val();
		if(limit=='yes'){
			alert('每张图片限制100个标签哦！');
			return;
		}
		
		var str = "<div  class=\"input-group\" >"
	       	     +"<input type=\"text\"     maxlength=\"10\" required  name=\"newtag\" placeholder=\"NewTag [长度少于10个字]\"  style=\"margin-right: 10px;width: 200px;\"  />"
	             +"<span  name=\"J_del_ipt\" class=\"glyphicon glyphicon-remove\"  onclick=\"delInput(this)\"></span>"
		         +"</div>";
		         
		var flag  = true;         
		$("input[name='newtag']").each(function(){
			var con = $(this).val();
		    if(!con){
		    	flag = false;
		    	$(this).focus();
		    }
		})   
		if(flag){
			$("#J_ipt_div").before(str);
		}else{
			alert('请填写完未填写的标签再添加哦！');
		}
		
	});
	
	//保存按钮
	/* $("#J_tag_btn").click(function(){
		
	}); */
	
	
})

function delInput(obj){
	$(obj).parent().remove();
}
//删除一个标签
function delTag(tagID){
	 //数据删除li————————tag
	if(tagID){
		$.ajax({

			url : "/running/delTag.action",

			type : "post",

			data : {
				"tagID" : tagID
			},

			success : function(data) {
				console.log(data);
			}

		});
	}
		//删除li行
		$("#li" + tagID).remove();
}

//编辑标签
function  topTag(photoid){
	
	//清除未保存的标签
	clearTag();
	//点击添加事件初始化一个newtag
	$("#J_add_tag").click();
	
    if(photoid){
    	 //设值
    	 $("#J_pid").val(photoid);
    	 $.ajax({
    			
    	        url:"/running/taglist.action",

    	        type:"post",

    	        data:{"photoID":photoid},

    	        success:function(data){
    	        	$("#J_ul_tag").html("");
    	        	$("#J_ul_tag").append(data);
    				
    	        }

    	    });
    	 
    	 
    	
		}

}

//图片处理的js----------------------------------------------------------------
	window.URL = window.URL || window.webkitURL;
	var fileElem = document.getElementById("fileElem"), fileList = document
			.getElementById("fileList");
	var fileArray = new Array();//创建一个数组
	function handleFiles(obj) {
		var J_add_div_length = $("div[id^='J_add_div']").length;
		var files = obj.files;
		if((J_add_div_length + files.length)>5){
			alert('最多选择5个文件哦');
			return;
		}
		var had_ = 0;
		//计算上次已有的个数
		$("#fileList").find("div").each(function() {
			if ($(this).attr('cname') == 'J_aped') {//绑定删除按钮
				had_++;

			}
		})
		for (var _i = 0; _i < files.length; _i++) {
			var reindex = _i + had_;
			var img = new Image(100);
			var str = "<div cname=\"J_aped\" id=\"J_add_div"+reindex+"\" style=\"background: #fff;height:100px;float: left;clear: left;\" ></div>";
			var str2 = "&nbsp;&nbsp;"
					+ "<input type=\"text\" style=\"width:100px;\" class=\"form-control\" id=\"tag1_"+reindex+"\" name=\"tag1@"+reindex+"\" placeholder=\"tag1\">&nbsp;&nbsp;"
					+ "<input type=\"text\" style=\"width:100px;\" class=\"form-control\" id=\"tag2_"+reindex+"\" name=\"tag2@"+reindex+"\" placeholder=\"tag2\">&nbsp;&nbsp;"
					+ "<input type=\"text\" style=\"width:100px;\" class=\"form-control\" id=\"tag3_"+reindex+"\" name=\"tag3@"+reindex+"\" placeholder=\"tag3\">&nbsp;&nbsp;"
					+ "<span   id=\"J_del"+reindex+"\"class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>"
			if (window.URL) {
				img.src = window.URL.createObjectURL(files[_i]);
				//img.width = 200;
				img.onload = function(e) {
					window.URL.revokeObjectURL(this.src);
				}

				//添加到数组里
				fileArray.push(files[_i]);

				$("#fileList").append(str);
				var str_ = $("#J_add_div" + reindex).append(img).append(str2);
				console.log(str_);
				$("#fileList").append(str_);
			} else if (window.FileReader) {
				//opera不支持createObjectURL/revokeObjectURL方法。我们用FileReader对象来处理
				var reader = new FileReader();
				reader.readAsDataURL(files[_i]);
				reader.onload = function(e) {
					img.src = this.result;

					//添加到数组里
					fileArray.push(files[_i]);

					//img.width = 200;
					$("#fileList").append(str);
					var str_ = $("#J_add_div" + _i).append(img).append(str2);
					$("#fileList").append(str_);
				}
			} else {
				//ie
				obj.select();
				obj.blur();
				var nfile = document.selection.createRange().text;
				document.selection.empty();
				img.src = nfile;

				//添加到数组里
				fileArray.push(files[_i]);
				//img.width = 200;		
				$("#fileList").append(str);
				var str_ = $("#J_add_div" + _i).append(img).append(str2);
				$("#fileList").append(str_);
			}
		}

		//$("#J_filearr").val(fileArray);

		//依次重新计算下标


//		$("#fileList").find("div").each(function() {
//			if ($(this).attr('cname') == 'J_aped') {//绑定删除按钮
//				$(this).find(".glyphicon").click(function() {
//					var id = $(this).attr('id');
//					var index_ = id.substr(id.length - 1, id.length - 1);
//
//					$("#J_add_div" + index_).remove();
//
//					//从数组删除
//					fileArray.splice(index_, 1);
//
//					//重新计算下标
//					changeIndex(index_);
//
//				})
//
//			}
//		})

	}

	$(function() {
		$("#fileList").on("click","span[id^='J_del']",function(){
			var id = $(this).attr('id');
			var index_ = id.substr(id.length - 1, id.length - 1);
			$("#J_add_div" + index_).remove();
			//从数组删除
			fileArray.splice(index_, 1);
			//重新计算下标
			changeIndex(index_);
		});

		function changeIndex(i) {
			$("#fileList").find("div").each(
					function() {
						if ($(this).attr('cname') == 'J_aped') {//@1 div
							var id = $(this).attr('id');
							var index_ = id
									.substr(id.length - 1, id.length - 1);

							if (index_ > i) {
								//更改div id下标属性
								$(this).attr('id', "J_add_div" + (index_ - 1));
								//更改删除下标
								$(this).find('span').attr("id",
										"J_del" + (index_ - 1));
								//更改标签下标
								$(this).find("input[id='tag1_" + index_ + "']")
										.attr("name", "tag1@" + (index_ - 1));
								$(this).find("input[id='tag1_" + index_ + "']")
										.attr("id", "tag1_" + (index_ - 1));
								$(this).find("input[id='tag2_" + index_ + "']")
										.attr("name", "tag2@" + (index_ - 1));
								$(this).find("input[id='tag2_" + index_ + "']")
										.attr("id", "tag2_" + (index_ - 1));
								$(this).find("input[id='tag3_" + index_ + "']")
										.attr("name", "tag3@" + (index_ - 1));
								$(this).find("input[id='tag3_" + index_ + "']")
										.attr("id", "tag3_" + (index_ - 1));

							}

						}
					})
		}

		/* //验证
		$("#tagform").bootstrapValidator({
			fields : {
				files : {
					validators : {
						newtag : {
							extension : 'jpg,png,gif',
							type : 'image/jpeg,image/png,image/gif,',
							maxSize : 2 * 1024 * 1024,
							message : '请上传“jpg,png,gif”格式的小于2M的文件.'
						}
					}
				}
			}
		}).on('success.form.bv', function(e) {
			e.preventDefault();
		}
	); */
		//验证
		$("#aform").bootstrapValidator({
			fields : {
				files : {
					validators : {
						file : {
							extension : 'jpg,png,gif',
							type : 'image/jpeg,image/png,image/gif,',
							maxSize : 2 * 1024 * 1024,
							message : '请上传“jpg,png,gif”格式的小于2M的文件.'
						}
					}
				}
			}
		}).on('success.form.bv', function(e) {
			/*  if(check_error){
			     check_error = false;
			     $(e.target).data('bootstrapValidator').disableSubmitButtons(false);
			 } */
			e.preventDefault();
			var form_data = new FormData();

			for (var j = 0; j < fileArray.length; j++) {
				form_data.append('filearr', fileArray[j]);
				form_data.append('tag1@' + j, $("#tag1_" + j).val());
				form_data.append('tag2@' + j, $("#tag2_" + j).val());
				form_data.append('tag3@' + j, $("#tag3_" + j).val());
			}

			$.ajax({
				url : '/running/upload.action',
				type : 'POST',
				data : form_data,
				cache : false,
				contentType : false, //不可缺
				processData : false, //不可缺
				success : function(msg) {
					if (msg == 'success') {
						window.location.href = window.location.href;
					} else if (msg = 'exception') {
						alert('上传异常');
						$("#J_close").click();
					} else {
						$("#J_close").click();
					}
				}
			});

		});

	});
	
	//图片处理的js----------------------------------------------------------------
</script>
</body>
</html>