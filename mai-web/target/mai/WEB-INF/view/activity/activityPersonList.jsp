<%--
  Created by IntelliJ IDEA.
  User: denghao
  Date: 15/10/3
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.mai.activity.entity.ActivityMember,com.mai.util.ConfigUtil,com.mai.notification.entity.Notification,com.mai.society.entity.Society"%>

<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<c:set var="MEMBERSTATUS_CHECKKED" value="<%=ActivityMember.MEMBERSTATUS_CHECKKED%>" />
<c:set var="MEMBERSTATUS_ISVALID_NOT" value="<%=ActivityMember.MEMBERSTATUS_ISVALID_NOT%>" />
<c:set var="MEMBERSTATUS_NORMAL" value="<%=ActivityMember.MEMBERSTATUS_NORMAL%>" />
<c:set var="NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS" value="<%=Notification.NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS%>" />
<c:set var="STATUS_RELEASED" value="<%=Society.STATUS_RELEASED%>" />


<div class="jumbotron container-fluid">
  <div class="panel panel-default">
    <form action="/activity/ActivityMemberList.action" method="post" id="stform">
      <input type="hidden" name="actid" id="actid" value="${actid}"/>
      <input type="hidden" name="actmemberid" id="actmemberid" value=""/>
      <input type="hidden" name="newstatus" id="newstatus" value=""/>
      <input type="hidden" name="oldstatus" id="oldstatus" value=""/>
      <input type="hidden" name="autodelPerson" id="autodelPerson" value="0"/>
      <input type="hidden" name="personid" id="personid" value=""/>
      <input type="hidden" name="page" id="page" value="${page}"/>
      <div class="panel-heading"></div>
      <div class="panel-body">
        <div class="row">
          <div class="col-lg-1">
            <div class="input-group" style="margin: auto">
                <button class="btn btn-default" id="reActListBtn" type="button" onclick="window.location.href='/activity/ActivityList.action'">返回活动列表</button>
            </div>
          </div>
          <c:if test="${societyStatus == STATUS_RELEASED}">
            <div class="col-lg-5 pull-right" style="margin: auto">
              <div class="input-group pull-right" style="margin: auto">
                <button class="btn btn-default" type="button" onclick="window.location.href='/notice/toAdd.action?objID=${actid}&objType=${NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS}'">发通知</button>
              </div>
            </div>
          </c:if>
        </div>
      </div>
      <table class="table table-bordered table-hover table-responsive">
        <thead>
        <tr>
          <th>头像</th>
          <th>名称</th>
          <th>
            <div class="dropdown">
              <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                性别
                <span class="caret"></span>
              </button>
              <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" id="ulddm1">
                <li><a href="JavaScript:void(0);" title="">性别</a></li>
                <li><a href="JavaScript:void(0);" title="1">男</a></li>
                <li><a href="JavaScript:void(0);" title="2">女</a></li>
                <li><a href="JavaScript:void(0);" title="3">保密</a></li>
              </ul>
              <input id="apgenderid" name="apgenderid" type="hidden" value="${apgenderid}"/>
              <input id="apgendername" name="apgendername" type="hidden" value="${apgendername}"/>
            </div>
          </th>
          <th>
              <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                  全部学校<span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" id="ulddm2">
                </ul>
                <input id="apsid" name="apsid" type="hidden" value="${apsid}"/>
              </div>
          </th>
          <th>电话</th>
          <th>留言</th>
          <th>
            <div class="dropdown">
              <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                全部状态
                <span class="caret"></span>
              </button>
              <ul class="dropdown-menu" aria-labelledby="dropdownMenu3" id="ulddm3">
                <li><a href="JavaScript:void(0);" title="">全部状态</a></li>
                <li><a href="JavaScript:void(0);" title="${MEMBERSTATUS_NORMAL}">待审核</a></li>
                <li><a href="JavaScript:void(0);" title="${MEMBERSTATUS_CHECKKED}">审核通过</a></li>
                <li><a href="JavaScript:void(0);" title="${MEMBERSTATUS_ISVALID_NOT}">审核未通过</a></li>
              </ul>
              <input id="aptype" name="aptype" type="hidden" value="${aptype}"/>
            </div>
          </th>
          <th>常见操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach  items="${actmlist}" var="actm">
          <tr>
            <td>
              <c:choose>
                <c:when test="${actm.headerURL != null && actm.headerURL != ''}">
                <a href="<%=ConfigUtil.getProperty("qiniu.qiniuDomainURL.APP")%>/${actm.headerURL}" class="thumbnail col-xs-4" target="_blank">
                  <img src="<%=ConfigUtil.getProperty("qiniu.qiniuDomainURL.APP")%>/${actm.headerURL}" width="200" height="100">
                </a>
                </c:when>
                <c:otherwise>
                  <a href="${ctxPath}/resources/images/default.png" class="thumbnail col-xs-4" target="_blank">
                    <img src="${ctxPath}/resources/images/default.png" width="200" height="100">
                  </a>
                </c:otherwise>
              </c:choose>
            </td>
            <td><c:out value="${actm.name}"></c:out></td>
            <td>
              <c:choose>
                <c:when test="${actm.gender==1}">男</c:when>
                <c:when test="${actm.gender==2}">女</c:when>
                <c:when test="${actm.gender==3}">保密</c:when>
                <c:otherwise>未知</c:otherwise>
              </c:choose>
            </td>
            <td><c:out value="${actm.schoolName}"></c:out></td>
            <td><c:out value="${actm.phoneNum}"></c:out></td>
            <td><c:out value="${actm.applayNote}"></c:out></td>
            <td id="mstatus_${actm.activitymemberID}">
              <c:choose>
                <c:when test="${actm.memberStatus==MEMBERSTATUS_NORMAL}">待审核</c:when>
                <c:when test="${actm.memberStatus==MEMBERSTATUS_CHECKKED}">审核通过</c:when>
                <c:when test="${actm.memberStatus==MEMBERSTATUS_ISVALID_NOT}">审核未通过</c:when>
                <c:otherwise>未知</c:otherwise>
              </c:choose>
            </td>
            <td id="operation_${actm.activitymemberID}">
              <c:choose>
                <c:when test="${actm.memberStatus==MEMBERSTATUS_NORMAL}">
                  <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="auditmodal(this,'${actm.activitymemberID}','${actm.personID}',${actm.memberStatus},1,1);">审核通过</button>&nbsp;

                  <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="auditmodal(this,'${actm.activitymemberID}','${actm.personID}',${actm.memberStatus},-1);">审核不通过</button>
                </c:when>
                <c:when test="${actm.memberStatus==MEMBERSTATUS_CHECKKED}">
                  <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="auditmodal(this,'${actm.activitymemberID}','${actm.personID}',${actm.memberStatus},-1);">审核不通过</button>
                </c:when>
                <c:when test="${actm.memberStatus==MEMBERSTATUS_ISVALID_NOT}">
                  <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="showLogList(this,'${actm.activitymemberID}');">查看原因</button>
                </c:when>
                <c:otherwise>无</c:otherwise>
              </c:choose>
            </td>
          </tr>
        </c:forEach>

        </tbody>
      </table>
      <div class="panel-footer">
        <pagination:pagination param="max=${pageMaxSize}&total=${totalsize}&page=${page}"/>

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
              <textarea class="form-control" placeholder="审核意见" rows="3" name="mysug" id="mysug"></textarea>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default"
                      data-dismiss="modal">关闭
              </button>
              <button type="button" class="btn btn-primary" id="myModalBtn">
              </button>
            </div>
          </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </form>
  </div>
</div>
<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
<script>
  $(function(){
    $('#ulddm1 li a').each(function(i){
      if($(this).attr("title") == $("#apgenderid").val()){
        $("#ulddm1").prev().val($(this).attr("title"));
        $("#ulddm1").prev().text($(this).text());
        $("#ulddm1").prev().append("<span class='caret'>");
      }
    });

    $('#ulddm3 li a').each(function(i){
      if($(this).attr("title") == $("#aptype").val()){
        $("#ulddm3").prev().val($(this).attr("title"));
        $("#ulddm3").prev().text($(this).text());
        $("#ulddm3").prev().append("<span class='caret'>");
      }
    });
    $("#ulddm3 li a").bind("click", function () {
      var $this = $(this);
      var $a = $this.parent().parent().prev();
      var t = $this.text();
      $a.val($this.attr('title'));
      $('#aptype').val($this.attr('title'));
      $a.text($this.text());
      $a.append("<span class='caret'>");
      $("#stform").submit();
    });

    $("#ulddm1 li a").bind("click", function () {
      var $this = $(this);
      var $a = $this.parent().parent().prev();
      var t = $this.text();
      $a.val($this.attr('title'));
      $('#apgenderid').val($this.attr('title'));
      $('#apgendername').val($this.text());
      $a.text($this.text());
      $a.append("<span class='caret'>");
      $("#stform").submit();
    });

    $.post("${ctxPath}/school/getSchoolList.action",function(data){
      if(data && data.length>0){
        $("#ulddm2").empty();
        $("#ulddm2").append('<li><a title="" href="JavaScript:void(0);">请选择学校...</a></li>');
        $.each(data,function(I,N){
          if($('#apsid').val() == N.schoolID){
            $("#ulddm2").prev().val($("#apsid").val());
            $("#ulddm2").prev().text(N.schoolName);
            $("#ulddm2").prev().append("<span class='caret'>");
          }
          $("#ulddm2").append('<li><a title="'+N.schoolID+'" href="JavaScript:void(0);">'+N.schoolName+'</a></li>');
        });
        $("#ulddm2 li a").bind("click", function () {
          var $this = $(this);
          var $a = $this.parent().parent().prev();
          var t = $this.text();
          $a.val($this.attr('title'));
          $('#apsid').val($this.attr('title'));
          $a.text($this.text());
          $a.append("<span class='caret'>");
          $("#stform").submit();
        });
      }
    },"json");

    $("#myModalBtn").click(function(){

//      $("#stform").submit();

      $("#myModalBtn").addClass("disabled");
      $("#stform").attr("action","/activity/ActivityMemberStatus.action");
      var $form = $("#stform");
      $.post($form.attr('action'), $form.serialize(), function(result) {
        var result_json = jQuery.parseJSON(result);
        if(result_json.errorcode == 0){
          $("#stform").attr("action","/activity/ActivityMemberList.action");
          $("#operation_"+result_json.amemberid).empty();
          if(result_json.member_status == ${MEMBERSTATUS_NORMAL}){
            $("#mstatus_"+result_json.amemberid).text('待审核');
            $("#operation_"+result_json.amemberid).append("<button class=\"btn btn-default\" type=\"button\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"auditmodal(this,'"+result_json.amemberid+"','"+result_json.personid+"',"+result_json.member_status+",${MEMBERSTATUS_CHECKKED},1);\">审核通过</button>&nbsp;");
            $("#operation_"+result_json.amemberid).append("<button class=\"btn btn-default\" type=\"button\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"auditmodal(this,'"+result_json.amemberid+"','"+result_json.personid+"',"+result_json.member_status+",${MEMBERSTATUS_ISVALID_NOT});\">审核不通过</button>");
          }else if(result_json.member_status == ${MEMBERSTATUS_CHECKKED}){
            $("#mstatus_"+result_json.amemberid).text('审核通过');
            $("#operation_"+result_json.amemberid).append("<button class=\"btn btn-default\" type=\"button\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"auditmodal(this,'"+result_json.amemberid+"','"+result_json.personid+"',"+result_json.member_status+",${MEMBERSTATUS_ISVALID_NOT});\">审核不通过</button>");
          }else if(result_json.member_status == ${MEMBERSTATUS_ISVALID_NOT}){
            $("#mstatus_"+result_json.amemberid).text('审核未通过');
            $("#operation_"+result_json.amemberid).append("<button class=\"btn btn-default\" type=\"button\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"showLogList(this,'"+result_json.amemberid+"');\">查看原因</button>");
          }else{
            $("#mstatus_"+result_json.amemberid).text('未知');
          }
          $('#myModal').modal('hide');
        }
      }, 'json');
    });
  });
  function auditmodal(event,actmemberid,personid,oldstatus,newstatus,_status){
    $("#autodelPerson").val(0);
    $("#myModalBody dl").remove();
    $("#mysug").text("");
    console.log($("#mysug").text());
    $("#myModalBtn").removeClass("disabled");
    if(_status && _status == 1){
      $("#mysug").hide();
      $.post("${ctxPath}/activity/checkActPersonNum.action?actid=${actid}",function(data){
        if(data.code == 1){
          if(data.num && data.num > 0){
            $("#myModalp").text("活动即将报满，剩余"+data.num+"个报名申请将会自动被拒绝!");
            $("#autodelPerson").val(1);
            $("#myModalBtn").text("确定");
          }else{
            $("#autodelPerson").val(0);
            $("#myModalp").text("确定审核通过吗？");
            $("#myModalBtn").text($(event).text());
          }
          $("#myModalBtn").show();
        }else{
            if(data.code == -1){
              $("#myModalp").text("活动时间已开始,剩余"+data.num+"个未审核报名申请将会自动被拒绝!");
              $("#autodelPerson").val(-1);
              $("#myModalBtn").show();
              $("#myModalBtn").text("确定");
            }else if(data.code == -2){
              $("#myModalp").text("活动已报满,剩余"+data.num+"个未审核报名申请将会自动被拒绝!");
              $("#autodelPerson").val(-1);
              $("#myModalBtn").show();
              $("#myModalBtn").text("确定");
            }else{
              $("#myModalp").text("数据异常，请联系管理员！");
              $("#myModalBtn").hide();
            }
        }
        $("#myModalp").show();
        $("#newstatus").val(newstatus);
        $("#personid").val(personid);
        $("#actmemberid").val(actmemberid);
        $("#myModalLabel").text($(event).text());

      },"json");
    }else{
      $("#autodelPerson").val(0);
      $("#myModalp").empty();
      $("#myModalp").hide();
      $("#mysug").show();
      $("#personid").val(personid);
      $("#newstatus").val(newstatus);
      $("#actmemberid").val(actmemberid);
      $("#oldstatus").val(oldstatus);
      $("#myModalLabel").text($(event).text());
      $("#myModalBtn").text($(event).text());
      $("#myModalBtn").show();
    }

  }

  function showLogList(event,actmemberid){
    $("#mysug").hide();
    $("#myModalBtn").hide();
    $("#myModalLabel").text($(event).text());
    $.post("${ctxPath}/activity/ActivityMemberLogList.action?actmemberid="+actmemberid,function(data){
      if(data && data.length>0){
        $("#myModalp").hide();
        $("#myModalBody dl").remove();
        $("#myModalBody").append("<dl class='dl-horizontal'></dl>");
        $.each(data,function(I,N){
          $("#myModalBody dl").append("<dt>" + N.showCreateTime + "</dt><dd>"+ N.logDesc+"</dd>");
        });
      }else{
        $("#myModalp").text("未审核");
        $("#myModalp").show();
      }
    },"json");
  }
</script>
</body>
</html>
