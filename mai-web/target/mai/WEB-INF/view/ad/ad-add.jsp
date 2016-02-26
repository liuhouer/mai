<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/admin/navbar.jsp" %>

<!-- Main component for a primary marketing message or call to action -->
<form method="post" action="/ad/add.action" id="aform" name="aform" enctype="multipart/form-data">
<div class="jumbotron">

    <div class="form-group"></div>





    <div class="form-group">
        <div class="input-group">
            <span class="input-group-addon" id="aID">广告页名称</span>
            <input type="text" class="form-control" name="title" placeholder="广告页名称"
                   value="${model.title}" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写广告页名称">
        </div>
    </div>

    <div class="form-group">
        <div class="input-group">
            <span class="input-group-addon" >广告页链接</span>
            <input type="text" class="form-control" placeholder="广告页链接" value="${model.toURL}"
                   name="toURL" aria-describedby="basic-addon1" >
        </div>
    </div>

  <div class="form-group">
      <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input1">
        <span class="input-group-addon" id="aBTime">广告页开始时间</span>
        <input class="form-control" size="16" type="text" value="${model.showStartTime}" name="startTime_" readonly required data-bv-notempty-message="请填写开始时间">
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
      </div>
      <input type="hidden" id="dtp_input1" value="${model.showStartTime}" name="showStartTime"/>
  </div>

    <div class="form-group">
        <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input2">
            <span class="input-group-addon" id="aETime">广告页结束时间</span>
            <input class="form-control" size="16" type="text" value="${model.showEndTime}" name="endTime_" readonly required data-bv-notempty-message="请填写结束时间">
            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
        </div>
        <input type="hidden" id="dtp_input2" value="${model.showEndTime}" name="showEndTime"/>
    </div>




  <div class="form-group">
    <div class="input-group">
      <span class="input-group-addon" id="c3">广告页图片</span>
        <c:choose>
            <c:when test="${model.imageURL != null && model.imageURL != ''}">
                <a href="${model.imageURL}" target="_blank" class="thumbnail col-lg-2">
                    <img src="${model.imageURL}"/>
                </a>
                <input type="file" class="form-control" id="file_obj1" placeholder="广告页图片" name="files"
                       aria-describedby="basic-addon1">
            </c:when>
            <c:otherwise>
                <input type="file" class="form-control" id="file_obj1" placeholder="广告页图片" name="files"
                       aria-describedby="basic-addon1" required data-bv-notempty-message="广告页图片">
            </c:otherwise>
        </c:choose>
    </div>
  </div>


    <div class="form-group">

        </div>

    <div class="form-group">
        <div class="btn-group" role="group" aria-label="...">
            <button type="submit" id="mysubmit2" class="btn btn-default" id="J_save_btn">保存</button>
            <button type="button" class="btn btn-default" onclick="window.location.href='/ad/list.action'">取消</button>
        </div>
    </div>
</div>

</div> <!-- /container -->
</form>
<%@include file="/resources/footer-js.jsp" %>
<script language="JavaScript">



  $(function(){
      $("#aform").bootstrapValidator({fields: {
          phoneNum: {
              validators: {
                  phone: {
                      country: 'CN'
                  }
              }
          },
          files: {
              validators: {
                  file: {
                      extension: 'jpg,png,gif',
                      type: 'image/jpeg,image/png,image/gif,',
                      maxSize: 2*1024*1024,
                      message: '请上传“jpg,png,gif”格式的小于2M的文件.'
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


  });
  
  $("#J_save_btn").click(function(){
	  $("#aform").submit();
  })

  $('.form_datetime').datetimepicker({
      weekStart: 1,
      todayBtn:  1,
      autoclose: 1,
      todayHighlight: 1,
      startView: 2,
      forceParse: 0,
      showMeridian: 1,
      language:'zh-CN'
  }).on('changeDate show', function(e) {
      $('#aform').data('bootstrapValidator').updateStatus('startTime_', 'NOT_VALIDATED', null).validateField('startTime_');
      $('#aform').data('bootstrapValidator').updateStatus('endTime_', 'NOT_VALIDATED', null).validateField('endTime_');
  });
  
  var fileInputAdapt=function(file_obj){//style="padding-top: 0px; border: none;box-shadow:none"
    if(/firefox/.test(navigator.userAgent.toLowerCase())){//Firefox
      file_obj.css({"boxShadow":"none","paddingTop":"0px"});
    }
  };
    function checkboxView(event){
        setTimeout(function(){
            var _obj = $(event).children(":first-child");
            if(_obj.is(":checked")){
                _obj.next().after('<span class="badge">&radic;</span>');
            }else{
                _obj.siblings().remove(".badge");
            }
        },0);

    }



  window.onload=function(){
    fileInputAdapt($("#file_obj1"));
  }

</script>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>