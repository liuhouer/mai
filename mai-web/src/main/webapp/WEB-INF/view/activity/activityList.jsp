<%--
  Created by IntelliJ IDEA.
  User: denghao
  Date: 15/10/1
  Time: 上午10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.mai.activity.entity.Activity,com.mai.society.entity.Society" %>
<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<c:set var="ACTIVITY_STATUS_NON_CHECKED" value="<%=Activity.ACTIVITY_STATUS_NON_CHECKED%>" />
<c:set var="ACTIVITY_STATUS_READY" value="<%=Activity.ACTIVITY_STATUS_READY%>" />
<c:set var="ACTIVITY_STATUS_NOTPASS" value="<%=Activity.ACTIVITY_STATUS_NOTPASS%>" />
<c:set var="ACTIVITY_STATUS_OFFLINE" value="<%=Activity.ACTIVITY_STATUS_OFFLINE%>" />
<c:set var="ACTIVITY_STATUS_DRAFT" value="<%=Activity.ACTIVITY_STATUS_DRAFT%>" />
<c:set var="ACTIVITY_STATUS_DELETE" value="<%=Activity.ACTIVITY_STATUS_DELETE%>" />
<c:set var="STATUS_RELEASED" value="<%=Society.STATUS_RELEASED%>" />

<div class="jumbotron container-fluid">
  <div class="panel panel-default">
    <form action="/activity/ActivityList.action" method="post" id="stform">
      <input type="hidden" name="actid" id="actid" value=""/>
      <input type="hidden" name="newstatus" id="newstatus" value=""/>
      <input type="hidden" name="page" id="page" value="${page}"/>
    <div class="panel-heading"></div>
    <div class="panel-body">
      <div class="row">
        <div class="col-lg-4">
            <div style="margin: auto">
              <input type="text" class="form-control" name="stitle" placeholder="活动名称" value="${stitle}">
            </div>
        </div>
        <div class="col-lg-4">
          <div style="margin: auto">
            <input type="text" class="form-control" name="ssName" placeholder="活动主办方名称" value="${ssName}">
          </div>
        </div>
        <div class="col-lg-1">
          <div style="margin: auto">
            <button class="btn btn-default" id="sbtn" type="button">搜索</button>
          </div>
        </div>
<shiro:hasRole name="president">
        <c:if test="${societyStatus == STATUS_RELEASED}">
          <div class="col-lg-2 pull-right" style="margin: auto">
            <div class="input-group pull-right" style="margin: auto">
              <button class="btn btn-default" type="button" onclick="window.location.href='/activity/newActivity.action';">新建活动</button>
            </div>
          </div>
        </c:if>
  </shiro:hasRole>
      </div>
    </div>

<table class="table table-bordered table-hover table-responsive">
  <thead>
  <tr>
    <th>活动时间</th>
    <th>活动名称</th>
    <th>活动主办方名称</th>
    <th>活动人数</th>
    <th>活动关注数</th>
    <th>所属学校</th>
    <th>
      <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          全部状态
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" id="ulddm1">
          <li><a href="JavaScript:void(0);" title="">全部状态</a></li>
          <li><a href="JavaScript:void(0);" title="${ACTIVITY_STATUS_NON_CHECKED}">待审核</a></li>
          <li><a href="JavaScript:void(0);" title="${ACTIVITY_STATUS_READY}">审核通过</a></li>
          <li><a href="JavaScript:void(0);" title="${ACTIVITY_STATUS_NOTPASS}">审核未通过</a></li>
          <li><a href="JavaScript:void(0);" title="${ACTIVITY_STATUS_OFFLINE}">已下架</a></li>
          <li><a href="JavaScript:void(0);" title="${ACTIVITY_STATUS_DRAFT}">草稿</a></li>
        </ul>
        <input id="stype" name="stype" type="hidden" value="${stype}"/>
        <input id="stypename" name="stypename" type="hidden" value="${stypename}"/>
      </div>
    </th>
    <th>常见操作</th>
  </tr>
  </thead>
  <tbody>

<c:forEach  items="${actlist}" var="activity">
<tr>
  <td><c:out value="${activity.showStartTime}"></c:out></td>
  <td>
    <a href="/activity/viewActivity.action?actid=${activity.activityID}">
      <c:out value="${activity.activityTitle}"></c:out>
    </a>
    </td>
  <td><c:out value="${activity.societyName}"></c:out></td>
  <td><c:out value="${activity.maxPersonNum}"></c:out></td>
  <td><c:out value="${activity.followNum}"></c:out></td>
  <td><c:out value="${activity.schoolName}"></c:out></td>
  <td>
    <c:choose>
      <c:when test="${activity.activityStatus==ACTIVITY_STATUS_NON_CHECKED}">待审核</c:when>
      <c:when test="${activity.activityStatus==ACTIVITY_STATUS_READY}">审核通过</c:when>
      <c:when test="${activity.activityStatus==ACTIVITY_STATUS_NOTPASS}">审核未通过</c:when>
      <c:when test="${activity.activityStatus==ACTIVITY_STATUS_OFFLINE}">已下架</c:when>
      <c:when test="${activity.activityStatus==ACTIVITY_STATUS_DRAFT}">草稿</c:when>
      <c:otherwise>未知</c:otherwise>
    </c:choose>
  </td>
  <td>
      <shiro:hasRole name="president">
        <c:if test="${activity.activityStatus != ACTIVITY_STATUS_NON_CHECKED}">
          <button class="btn btn-default" type="button" id="editActBtn" onclick="window.location.href='/activity/newActivity.action?actid=${activity.activityID}';">编辑</button>
        </c:if>
        <c:if test="${activity.activityStatus == ACTIVITY_STATUS_READY}">
          <button class="btn btn-default" type="button" id="editActBtn" onclick="window.location.href='/activity/ActivityMemberList.action?actid=${activity.activityID}';">报名审核(${activity.joinPersonNum}/${activity.maxPersonNum})</button>
        </c:if>
        <c:if test="${activity.activityStatus==ACTIVITY_STATUS_NON_CHECKED || activity.activityStatus==ACTIVITY_STATUS_READY}">
          <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="auditmodal(this,'${activity.activityID}',${ACTIVITY_STATUS_DRAFT},2);">取消发布</button>
        </c:if>
        <c:if test="${activity.activityStatus==ACTIVITY_STATUS_DRAFT}">
          <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="auditmodal(this,'${activity.activityID}',${ACTIVITY_STATUS_NON_CHECKED},3);">发布</button>
        </c:if>
        <c:if test="${activity.activityStatus==ACTIVITY_STATUS_DRAFT || activity.activityStatus==ACTIVITY_STATUS_NOTPASS || activity.activityStatus==ACTIVITY_STATUS_OFFLINE}">
          <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="auditmodal(this,'${activity.activityID}',${ACTIVITY_STATUS_DELETE},4);">删除</button>
        </c:if>

        <c:if test="${activity.isLog == 1}">
        <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="showLogList(this,'${activity.activityID}',${activity.activityStatus});">查看原因</button>
        </c:if>
      </shiro:hasRole>
      <shiro:hasRole name="activity">
        <c:choose>
        <c:when test="${activity.activityStatus==ACTIVITY_STATUS_NON_CHECKED}">
          <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="auditmodal(this,'${activity.activityID}',${ACTIVITY_STATUS_READY},1);">审核通过</button>
          <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="auditmodal(this,'${activity.activityID}',${ACTIVITY_STATUS_NOTPASS});">审核不通过</button>
        </c:when>
        <c:when test="${activity.activityStatus==ACTIVITY_STATUS_READY}">
          <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="auditmodal(this,'${activity.activityID}',${ACTIVITY_STATUS_OFFLINE});">下架</button>
        </c:when>
        <c:otherwise></c:otherwise>
      </c:choose>
        <c:if test="${activity.isLog == 1}">
          <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="showLogList(this,'${activity.activityID}',${activity.activityStatus});">审核历史</button>
        </c:if>
      </shiro:hasRole>
    </td>
</tr>
  </c:forEach>
  </tbody>
</table>
      <div class="panel-footer">
        <pagination:pagination param="max=${pageMaxSize}&total=${totalsize}&page=${page}&stitle=${stitle}&ssName=${ssName}"/>

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
        </div>
      </form>
  </div>
</div>
<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
<script>
  $(function(){
    $('#ulddm1 li a').each(function(i){
      if($(this).attr("title") == $("#stype").val()){
        $("#ulddm1").prev().val($(this).attr("title"));
        $("#ulddm1").prev().text($(this).text());
        $("#ulddm1").prev().append("<span class='caret'>");
      }
    });

    $("#sbtn").bind("click",function(){
      $("#page").val(1);
      $("#stform").submit();
    });
    $("#ulddm1 li a").bind("click", function () {
      var $this = $(this);
      var $a = $this.parent().parent().prev();
      var t = $this.text();
      $a.val($this.attr('title'));
      $('#stype').val($this.attr('title'));
      $('#stypename').val($this.text());
      $a.text($this.text());
      $a.append("<span class='caret'>");
      $("#stform").submit();
    });

    $("#myModalBtn").click(function(){
      $("#stform").attr("action","/activity/ActivityStatus.action");
      $("#stform").submit();
    });
  });
  function auditmodal(event,actid,newstatus,_status){
    $("#myModalBody dl").remove();
    if(_status){
      $("#mysug").hide();
      $("#myModalp").show();
      if(_status == 1){
        $("#myModalp").text("确定审核通过吗？");
      }else if(_status == 2){
        $("#myModalp").text("确定取消发布吗？");
      }else if(_status == 3){
        $("#myModalp").text("确定发布吗？");
      }else if(_status == 4){
        $("#myModalp").text("确定删除吗？");
      }
    }else{
      $("#myModalp").empty();
      $("#myModalp").hide();
      $("#mysug").show();
    }
    $("#newstatus").val(newstatus);
    $("#actid").val(actid);
    $("#myModalLabel").text($(event).text());
    $("#myModalBtn").text($(event).text());
    $("#myModalBtn").show();
  }

  function showLogList(event,actid,actstatus){
    $("#mysug").hide();
    $("#myModalBtn").hide();
    $("#myModalLabel").text($(event).text());
    $.post("${ctxPath}/activity/ActivityLogList.action?actid="+actid,function(data){
      if(data && data.length>0){
        $("#myModalp").hide();
        $("#myModalBody dl").remove();
        $("#myModalBody").append("<dl class='dl-horizontal'></dl>");
        $.each(data,function(I,N){
          $("#myModalBody dl").append("<dt>" + N.showCreateTime + "</dt><dd>"+ N.logDesc+"</dd>");
          <shiro:hasRole name="president">
              return false;
          </shiro:hasRole>
        });
      }else{
        if(actstatus && actstatus == 1){
          $("#myModalp").text("审核通过");
        }else{
          $("#myModalp").text("未审核");
        }

        $("#myModalp").show();
      }
    },"json");
  }
</script>
</body>
</html>
