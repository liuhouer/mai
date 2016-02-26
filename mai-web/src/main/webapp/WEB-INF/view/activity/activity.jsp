<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.mai.activity.entity.Activity"%>
<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<c:set var="ACTIVITY_STATUS_NON_CHECKED" value="<%=Activity.ACTIVITY_STATUS_NON_CHECKED%>" />
<c:set var="ACTIVITY_STATUS_READY" value="<%=Activity.ACTIVITY_STATUS_READY%>" />
<c:set var="ACTIVITY_STATUS_NOTPASS" value="<%=Activity.ACTIVITY_STATUS_NOTPASS%>" />
<c:set var="ACTIVITY_STATUS_OFFLINE" value="<%=Activity.ACTIVITY_STATUS_OFFLINE%>" />
<c:set var="ACTIVITY_STATUS_DRAFT" value="<%=Activity.ACTIVITY_STATUS_DRAFT%>" />

<!-- Main component for a primary marketing message or call to action -->
<form method="post" action="${ctxPath}/activity/saveOrUpdateActivity.action" id="aform" name="aform" enctype="multipart/form-data">
<div class="jumbotron">
    <input type="hidden" id="activityID" name="activityID" value="${activity.activityID}">
    <input type="hidden" id="activityDetailID" name="activityDetailID" value="${activity.activityDetailID}"/>
    <input type="hidden" id="joinPersonNum" name="joinPersonNum" value="${activity.joinPersonNum}"/>
    <input type="hidden" id="coverURL" name="coverURL" value="${activity.coverURL}"/>
    <input type="hidden" id="recommendNo" name="recommendNo" value="${activity.recommendNo}"/>
    <input type="hidden" id="societyID" name="societyID" value="${activity.societyID}"/>
    <input type="hidden" id="societyName" name="societyName" value="${activity.societyName}"/>
    <input type="hidden" id="hotNum" name="hotNum" value="${activity.hotNum}"/>
    <input type="hidden" id="followNum" name="followNum" value="${activity.followNum}"/>
    <input type="hidden" id="activityDetail" name="activityDetail" value="${activity.activityDetail}"/>
    <input type="hidden" id="schoolID" name="schoolID" value="${activity.schoolID}"/>
    <input type="hidden" id="acttagRefsstr" name="acttagRefsstr" value="${acttagRefsstr}"/>
    <input type="hidden" id="isInner" name="isInner" value="${activity.isInner}"/>
    <input type="hidden" id="isLog" name="isLog" value="${activity.isLog}"/>
    <input type="hidden" id="activityStatus" name="activityStatus" value="${activity.activityStatus}"/>
    <c:choose>
        <c:when test="${activity.needCheck}">
            <input type="hidden" id="oldAuditMethod" name="oldAuditMethod" value="1"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" id="oldAuditMethod" name="oldAuditMethod" value="0"/>
        </c:otherwise>
    </c:choose>

    <div class="form-group">
        <div class="input-group">
            <span class="input-group-addon" id="aID">活动名称</span>
            <input type="text" class="form-control" name="activityTitle" placeholder="活动名称"
                   value="${activity.activityTitle}" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写活动名称">
        </div>
    </div>

    <div class="form-group">
        <div class="input-group">
            <span class="input-group-addon" id="aMNum">活动人数</span>
            <input type="number" class="form-control" placeholder="活动人数" value="${activity.maxPersonNum}"
                   name="maxPersonNum" min="1" max="99999999" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写活动人数">
        </div>
    </div>

  <div class="form-group">
      <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
        <span class="input-group-addon" id="aBTime">活动开始时间</span>
        <input class="form-control" size="16" type="text" value="${activity.showStartTime}" id="_showStartTime" name="_showStartTime" readonly required data-bv-notempty-message="请填写开始时间">
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
      </div>
      <input type="hidden" id="dtp_input1" value="${activity.showStartTime}" name="showStartTime"/>
  </div>

    <div class="form-group">
        <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input2">
            <span class="input-group-addon" id="aETime">活动结束时间</span>
            <input class="form-control" size="16" type="text" value="${activity.showEndTime}" id="_showEndTime" name="_showEndTime" readonly required data-bv-notempty-message="请填写结束时间">
            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
        </div>
        <input type="hidden" id="dtp_input2" value="${activity.showEndTime}" name="showEndTime"/>
    </div>


    <div class="form-group">
        <div class="input-group">
            <span class="input-group-addon">活动地点</span>
            <input type="text" id="aAddress" class="form-control" placeholder="活动地点" name="address" value="${activity.address}"
                   aria-describedby="basic-addon1" required data-bv-notempty-message="请填写活动地点">
        </div>
    </div>


    <div class="form-group">
        <div class="input-group">
            <span class="input-group-addon" id="alocation">区域</span>
            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择区域...<span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu4" id="ulddm4">
                </ul>
                <input id="location" name="location" type="hidden" value="${activity.location}"/>
            </div>
        </div>
        <small style="display: none" class="help-block input-group" id="location_error"></small>
    </div>

  <div class="form-group">
      <div class="input-group">
          <span class="input-group-addon">地图选点</span>
          <input type="hidden" name="gpsLongitude" id="gpsLongitude" value="${activity.gpsLongitude}" />
          <input type="hidden" name="gpsLatitude" id="gpsLatitude" value="${activity.gpsLatitude}" />
          <button data-toggle="modal" data-target="#myModal" id="createMap" type="button">
              <c:choose>
                  <c:when test="${gps_map_static_str!=null && gps_map_static_str != '北京'}">
                      <img src="http://api.map.baidu.com/staticimage?center=${gps_map_static_str}&markers=${gps_map_static_str}&zoom=15" id="show_map_img"/>
                  </c:when>
                  <c:otherwise>
                      <img src="http://api.map.baidu.com/staticimage?center=${gps_map_static_str}&zoom=15" id="show_map_img"/>
                  </c:otherwise>
              </c:choose>
          </button>
      </div>
      <small style="display: none" class="help-block input-group" id="gps_error"></small>
  </div>

    <div class="form-group">
        <div class="input-group">
            <span class="input-group-addon" id="aPNum">联络方式</span>
            <input type="text" class="form-control" placeholder="邮箱" name="phoneNum" value="${activity.phoneNum}"
                   aria-describedby="basic-addon1">
        </div>
    </div>

    <div class="form-group">
        <div class="input-group">
            <span class="input-group-addon" id="acID">类型</span>
            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择类型...<span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" id="ulddm1">
                </ul>
                <input id="categoryID" name="categoryID" type="hidden" value="${activity.categoryID}"/>
                <input id="categoryName" name="categoryName" type="hidden" value="${activity.categoryName}"/>
            </div>
        </div>
        <small style="display: none" class="help-block input-group" id="category_error"></small>
    </div>

    <div class="form-group">
        <div class="input-group" data-toggle="buttons" id="tagcb">
            <span class="input-group-addon" id="aTag">热门标签</span>
        </div>
        <small style="display: none" class="help-block input-group" id="tagids_error"></small>
    </div>


  <div class="form-group">
    <div class="input-group">
      <span class="input-group-addon" id="c3">封面图片</span>
        <c:choose>
            <c:when test="${activity.coverURL != null && activity.coverURL != ''}">
                <a href="${activity.coverURL}" target="_blank" class="thumbnail col-lg-2">
                    <img src="${activity.coverURL}"/>
                </a>
                <input type="file" class="form-control" id="file_obj1" placeholder="封面图片" name="Filedata"
                       aria-describedby="basic-addon1">
            </c:when>
            <c:otherwise>
                <input type="file" class="form-control" id="file_obj1" placeholder="封面图片" name="Filedata"
                       aria-describedby="basic-addon1" required data-bv-notempty-message="请上传封面图片">
            </c:otherwise>
        </c:choose>
    </div>
  </div>

    <div class="form-group">
        <div class="input-group" data-toggle="buttons">
            <span class="input-group-addon" id="aNCheck">是否审核报名</span>
            <label class="btn btn-default ${activity.needCheck?"active":""}" onclick="radioView(this);">
                <input type="radio" name="needCheck" id="needCheck1" autocomplete="off" ${activity.needCheck?"checked":""} value="1">
                <span>是</span><c:if test="${activity.needCheck}"><span class="badge">&radic;</span></c:if>
            </label>
            <label class="btn btn-default ${!activity.needCheck?"active":""}" onclick="radioView(this);">
                <input type="radio" name="needCheck" id="needCheck2" autocomplete="off" ${!activity.needCheck?"checked":""} value="0">
                <span>否</span><c:if test="${!activity.needCheck}"><span class="badge">&radic;</span></c:if>
            </label>
        </div>
    </div>

    <div class="form-group">
        <div class="input-group">
            <span class="input-group-addon" id="aNotes">注意事项</span>
        <textarea class="form-control" placeholder="注意事项"
                  rows="3" name="notes">${activity.notes}</textarea>
        </div>
    </div>

    <div class="form-group">

        <%@include file="/resources/editor.jsp" %>
        </div>

    <div class="form-group">
        <div class="btn-group" role="group" aria-label="...">
            <c:choose>
                <c:when test="${activity.activityStatus == ACTIVITY_STATUS_READY}">
                    <button type="submit" id="mysubmit1" class="btn btn-default" onclick="subActForm(${activity.activityStatus});">保存修改</button>
                </c:when>
                <c:when test="${activity.activityStatus != ACTIVITY_STATUS_NON_CHECKED}">
                    <button type="submit" id="mysubmit1" class="btn btn-default" onclick="subActForm(${ACTIVITY_STATUS_DRAFT});">保存草稿</button>
                    <button type="submit" id="mysubmit2" class="btn btn-default" onclick="subActForm(${ACTIVITY_STATUS_NON_CHECKED});">发布活动</button>
                </c:when>
            </c:choose>
            <button type="button" class="btn btn-default" onclick="window.location.href='${ctxPath}/activity/ActivityList.action'">取消</button>
        </div>
    </div>
</div>

</div> <!-- /container -->
</form>
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
                    选择活动地点
                </h4>
                <div class="input-group">
                    <input type="text" class="form-control col-lg-12" placeholder="Search for..." id="suggestId">
            <span class="input-group-btn">
              <button id="mysearch" class="btn btn-default" type="button" style="height: 34px;">搜索</button>
            </span>
                </div>
                <span class="input-group" style="margin: auto; display:none;color:red;" id="select_map_error"></span>
            </div>
            <div class="modal-body" id="maps" style="height:400px;">
                地图加载中...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" id="save_map">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%@include file="/resources/footer-js.jsp" %>
<script language="JavaScript">
    var p = null;
    var map = null;
    var local="北京市";
    var check_error = false;

  $(function(){
      $("#aform").bootstrapValidator({fields: {
          activityTitle: {
              validators: {
                  stringLength: {
                      max: 200,
                      message: '活动名称不能超过200个汉字'
                  }
              }
          },
          Filedata: {
              validators: {
                  file: {
                      extension: 'jpg,png,gif',
                      type: 'image/jpeg,image/png,image/gif,',
                      maxSize: 2*1024*1024,
                      message: '请上传“jpg,png,gif”格式的小于2M的文件.'
                  }
              }
          },
          _showEndTime:{
              validators:{
                  callback: {
                      message: '结束时间必须大于开始时间',
                      callback: function(value, validator){
                            if(value == ''){
                                return true;
                            }
                            if($("#_showStartTime").val() > value){
                                return false;
                            }else{
                                validator.updateStatus('_showEndTime', 'VALID');
                                return true;
                            }
                      }
                  }
              }
          }
      }}).on('success.form.bv', function(e) {
            if(check_error){
                check_error = false;
                e.preventDefault();
                $(e.target).data('bootstrapValidator').disableSubmitButtons(false);
            }
      });

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
          $('#aform').data('bootstrapValidator').updateStatus('_showStartTime', 'NOT_VALIDATED', null).validateField('_showStartTime');
          $('#aform').data('bootstrapValidator').updateStatus('_showEndTime', 'NOT_VALIDATED', null).validateField('_showEndTime');
      });

    $("#createMap").click(function(){
      setTimeout(function() {//添加延时加载。解决问题
          if(map == null) {
              map = new BMap.Map("maps", {enableMapClick: false});
          }
        var _plng = $("#gpsLongitude").attr('value');
        var _plat = $("#gpsLatitude").attr('value');
        if(_plng == "" || _plat == ""){
            var _address = $.trim($("#aAddress").val());
            if(_address == ""){
                var myCity = new BMap.LocalCity();
                myCity.get(function(res){
                    map.centerAndZoom(res.center,16);
                    var marker = new BMap.Marker(res.center,{enableDragging:true});// 创建标注
                    map.addOverlay(marker);
                    marker.addEventListener("dragend",function(e){
                        p = e.point;//p 就是marker当前的坐标;
                    });
                });
            }else{
                $("#select_map_error").hide();
                $("#select_map_error").text("");
                $("#suggestId").val(_address);
                var myGeo = new BMap.Geocoder(); // 创建地址解析器实例
                myGeo.getPoint(_address, function(point){
                    if (point) {                    //如果地址能解析，标记
                        map.centerAndZoom(point, 18);
                        map.clearOverlays();    //清除地图上所有覆盖物
                        var marker=new BMap.Marker(point,{enableDragging:true});
                        map.addOverlay(marker);
                        p = point;
//                        var addComp = point.addressComponents;
                        marker.addEventListener("dragend",function(e){
                            p = e.point;//p 就是marker当前的坐标;
                        });
                    }else{
                        var ls = new BMap.LocalSearch(local);
                        ls.search(_address);
                        ls.setSearchCompleteCallback(function(rs){
                            if (ls.getStatus() == BMAP_STATUS_SUCCESS){
                                var poi = rs.getPoi(0); //取第1个查询结果
                                if(poi){
                                    var pt2 = poi.point;
                                    map.centerAndZoom(pt2, 18);
                                    map.clearOverlays();    //清除地图上所有覆盖物
                                    var newmarker=new BMap.Marker(pt2);
                                    map.addOverlay(newmarker);
                                    newmarker.enableDragging();//开启拖动
                                    p = pt2;
//                                        var addComp = point.addressComponents
                                    newmarker.addEventListener("dragend",function(e){
                                        p = e.point;//p 就是marker当前的坐标;
                                    });
                                }else{
                                    $("#select_map_error").text("地址无法找到");
                                    $("#select_map_error").show();
                                    return false;
                                }
                            }else{
                                $("#select_map_error").text("地址无法找到");
                                $("#select_map_error").show();
                                return false;
                            }
                        });
                    }
                },local);
            }
        }else{
            var _point = new BMap.Point(_plng, _plat);
            map.centerAndZoom(_point,16);
            var marker = new BMap.Marker(_point,{enableDragging:true});// 创建标注
            map.addOverlay(marker);
            marker.addEventListener("dragend",function(e){
                p = e.point;//p 就是marker当前的坐标;
            });
        }

          var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
          var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
          var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL});
          map.addControl(top_left_control);
          map.addControl(top_left_navigation);
          map.addControl(top_right_navigation);

          $("#mysearch").click(function(){
              $("#select_map_error").hide();
              $("#select_map_error").text("");
              var address = $.trim($("#suggestId").val());
                  var myGeo = new BMap.Geocoder(); // 创建地址解析器实例
                  myGeo.getPoint(address, function(point){
                      if (point) {                    //如果地址能解析，标记
                          map.clearOverlays();    //清除地图上所有覆盖物
                          map.centerAndZoom(point, 18);
                          var newmarker=new BMap.Marker(point,{enableDragging:true});
                          map.addOverlay(newmarker);
                          p = point;
//                          var addComp = point.addressComponents
                          newmarker.addEventListener("dragend",function(e){
                              p = e.point;//p 就是marker当前的坐标;
                          });
                      }else{
                          var ls = new BMap.LocalSearch(local);
                          ls.search(address);
                          ls.setSearchCompleteCallback(function(rs){
                              if (ls.getStatus() == BMAP_STATUS_SUCCESS){
                                  var poi = rs.getPoi(0); //取第1个查询结果
                                  if(poi){
                                      var pt2 = poi.point;
                                      map.centerAndZoom(pt2, 18);
                                      map.clearOverlays();    //清除地图上所有覆盖物
                                      var newmarker=new BMap.Marker(pt2);
                                      map.addOverlay(newmarker);
                                      newmarker.enableDragging();//开启拖动
                                      p = pt2;
//                                        var addComp = point.addressComponents
                                      newmarker.addEventListener("dragend",function(e){
                                          p = e.point;//p 就是marker当前的坐标;
                                      });
                                  }else{
                                      $("#select_map_error").text("地址无法找到");
                                      $("#select_map_error").show();
                                      return false;
                                  }
                              }else{
                                  $("#select_map_error").text("地址无法找到");
                                  $("#select_map_error").show();
                                  return false;
                              }
                          });
                      }
                  },local);
          });

      },300);

    });

      $("#save_map").click(function(){
          if(p){
              $("#gpsLongitude").attr('value',p.lng);
              $("#gpsLatitude").attr('value',p.lat);
              $("#show_map_img").attr('src','http://api.map.baidu.com/staticimage?zoom=15&center='+p.lng+","+p.lat+'&markers='+p.lng+","+p.lat);
              $("#myModal").modal('hide');
          }
      });

      $.post("${ctxPath}/category/getCategoryList.action",function(data){
          if(data && data.length>0){
              $("#ulddm1").empty();
              $.each(data,function(I,N){
                  if($('#categoryID').val() == N.categoryID){
                      $("#ulddm1").prev().val($('#categoryID').val());
                      $("#ulddm1").prev().text($('#categoryName').val());
                      $("#ulddm1").prev().append("<span class='caret'>");
                  }
                  $("#ulddm1").append('<li><a title="'+N.categoryID+'" href="JavaScript:void(0);">'+N.categoryName+'</a></li>');
              });
              $("#ulddm1 li a").bind("click", function () {
                  var $this = $(this);
                  var $a = $this.parent().parent().prev();
                  var t = $this.text();
                  $a.val($this.attr('title'));
                  $('#categoryID').val($this.attr('title'));
                  $('#categoryName').val($this.text());
                  $a.text($this.text());
                  $a.append("<span class='caret'>");
              });
          }
      },"json");

      $.post("${ctxPath}/location/getLocationList.action",function(data){
          if(data && data.length>0){
              $("#ulddm4").empty();
              $.each(data,function(I,N){
                  if($('#location').val() == N.locationID){
                      $("#ulddm4").prev().val($('#location').val());
                      $("#ulddm4").prev().text(N.locationName);
                      $("#ulddm4").prev().append("<span class='caret'>");
                  }
                  $("#ulddm4").append('<li><a title="'+N.locationID+'" href="JavaScript:void(0);">'+N.locationName+'</a></li>');
              });
              $("#ulddm4 li a").bind("click", function () {
                  var $this = $(this);
                  var $a = $this.parent().parent().prev();
                  var t = $this.text();
                  $a.val($this.attr('title'));
                  $('#location').val($this.attr('title'));
                  $a.text($this.text());
                  $a.append("<span class='caret'>");
              });
          }
      },"json");

      $.post("${ctxPath}/tag/getTagList.action",function(data){
          if(data && data.length>0){
              $("#aTag").nextAll().remove();
              $.each(data,function(I,N){
                  if($("#acttagRefsstr").val().indexOf(","+ N.tagID+",")>-1){
                      $("#tagcb").append('<label class="btn btn-default active" onClick="checkboxView(this);"><input type="checkbox" name="tagids" id="tag_id_'+ N.tagID+'" autocomplete="off" value="'+ N.tagID+'" checked><span>'+ N.tagName+'</span><span class="badge">&radic;</span></label>');
                  }else{
                      $("#tagcb").append('<label class="btn btn-default" onClick="checkboxView(this);"><input type="checkbox" name="tagids" id="tag_id_'+ N.tagID+'" autocomplete="off" value="'+ N.tagID+'"><span>'+ N.tagName+'</span></label>');
                  }
              });
          }
      },"json");

      function initToolbarBootstrapBindings() {
          var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier',
                      'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
                      'Times New Roman', 'Verdana'],
                  fontTarget = $('[title=Font]').siblings('.dropdown-menu');
          $.each(fonts, function (idx, fontName) {
              fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
          });
          $('a[title]').tooltip({container:'body'});
          $('.dropdown-menu input').click(function() {return false;})
                  .change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
                  .keydown('esc', function () {this.value='';$(this).change();});

          $('[data-role=magic-overlay]').each(function () {
              var overlay = $(this), target = $(overlay.data('target'));
              overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
          });
          if ("onwebkitspeechchange"  in document.createElement("input")) {
              var editorOffset = $('#editor').offset();
              $('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
          } else {
              $('#voiceBtn').hide();
          }
      };
      function showErrorAlert (reason, detail) {
          var msg='';
          if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
          else {
              console.log("error uploading file", reason, detail);
          }
          $('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+
                  '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
      };
      initToolbarBootstrapBindings();
      $('#editor').wysiwyg({ fileUploadError: showErrorAlert} );


      $('#editor').focus(function(){
          if($.trim($(this).html()) == '输入内容...'){
              $(this).empty();
          }
      });
      $('#editor').blur(function(){
          if($.trim($(this).html())==''){
              $(this).html('输入内容...');
          }
      });


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
    function radioView(event){
        setTimeout(function(){
            var _obj = $(event).children(":first-child");
            if(_obj.is(":checked")){
                $(event).parent().find(".badge").remove();
                _obj.next().after('<span class="badge">&radic;</span>');
            }
        },0);
    }


    function subActForm(_activityStatus){
        $("#activityStatus").attr("value",_activityStatus);
        $("#fileContent").attr("value",$('#editor').cleanScript());
        if($.trim($("#fileContent").val()) == '' || $.trim($("#fileContent").val()) == '输入内容...' || $.trim($("#fileContent").val()) == '<br>'){
            $("#editor_alert").text("活动详情不能为空");
            $("#editor_alert").show();
            check_error = true;
        }else{
            $("#editor_alert").text("");
            $("#editor_alert").hide();
        }


        if($.trim($("#location").val()) == ''){
            $("#location_error").text("请选择区域");
            $("#location_error").show();
            check_error = true;
            $("#location_error").parent().removeClass('has-success').addClass('has-error');
        }else{
            $("#location_error").text("");
            $("#location_error").hide();
            $("#location_error").parent().removeClass('has-error').addClass('has-success');
        }

        if($.trim($("#categoryID").val()) == ''){
            $("#category_error").text("请选择类型");
            $("#category_error").show();
            check_error = true;
            $("#category_error").parent().removeClass('has-success').addClass('has-error');
        }else{
            $("#category_error").text("");
            $("#category_error").hide();
            $("#category_error").parent().removeClass('has-error').addClass('has-success');
        }

        if(!$("input[name='tagids']").is(':checked')){
            $("#tagids_error").text("请选择标签");
            $("#tagids_error").show();
            check_error = true;
            $("#tagids_error").parent().removeClass('has-success').addClass('has-error');
        }else if($("input[name='tagids']:checked").length > 5){
            $("#tagids_error").text("标签最多选择5个");
            $("#tagids_error").show();
            check_error = true;
            $("#tagids_error").parent().removeClass('has-success').addClass('has-error');
        }else{
            $("#tagids_error").text("");
            $("#tagids_error").hide();
            $("#tagids_error").parent().removeClass('has-error').addClass('has-success');
        }



        if($.trim($("#gpsLongitude").val()) == '' || $.trim($("#gpsLatitude").val()) == ''){
            $("#gps_error").text("请创建地图位置");
            $("#gps_error").show();
            check_error = true;
            $("#gps_error").parent().removeClass('has-success').addClass('has-error');
        }else{
            $("#gps_error").text("");
            $("#gps_error").hide();
            $("#gps_error").parent().removeClass('has-error').addClass('has-success');
        }

    }

  window.onload=function(){
    fileInputAdapt($("#file_obj1"));
  }

</script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=401bd088908399c67499f012745d5ba9"></script>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>