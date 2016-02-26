<%--
  Created by IntelliJ IDEA.
  User: denghao
  Date: 15/10/1
  Time: 上午10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.mai.activity.entity.Activity"%>
<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<c:set var="ACTIVITY_STATUS_READY" value="<%=Activity.ACTIVITY_STATUS_READY%>" />
<c:set var="ACTIVITY_STATUS_NON_CHECKED" value="<%=Activity.ACTIVITY_STATUS_NON_CHECKED%>" />
<c:set var="ACTIVITY_STATUS_OFFLINE" value="<%=Activity.ACTIVITY_STATUS_OFFLINE%>" />
<c:set var="ACTIVITY_STATUS_NOTPASS" value="<%=Activity.ACTIVITY_STATUS_NOTPASS%>" />
<c:set var="ACTIVITY_STATUS_DRAFT" value="<%=Activity.ACTIVITY_STATUS_DRAFT%>" />

<div class="jumbotron container-fluid">
    <div class="panel panel-default">
        <form action="/activityRunning/ActivityTagDetail.action" method="post" id="tform" name="tform">
            <input type="hidden" name="tid" id="tid" value="${tid}"/>
            <input type="hidden" name="page" id="page" value="${page}"/>
            <div class="panel-heading"></div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-5">
                        <div class="input-group" style="margin: auto">
                            <input type="text" class="form-control" name="sName" placeholder="Search for..." value="${sName}">
                              <span class="input-group-btn">
                                <button class="btn btn-default" id="sbtn" type="button">搜索</button>
                              </span>
                        </div>
                    </div>
                    <div class="col-lg-5 pull-right" style="margin: auto">
                        <div class="input-group pull-right" style="margin: auto">
                            <button class="btn btn-default" type="button" onclick="window.location.href='/activityRunning/ActivityTagList.action'">返回</button>
                        </div>
                    </div>
                </div>
            </div>

            <table class="table table-bordered table-hover table-responsive table-condensed">
                <thead>
                <tr>
                    <th>活动开始时间</th>
                    <th>社团名称</th>
                    <th>活动名称</th>
                    <th>
                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                全部状态
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" id="ulddm1">
                                <li><a href="JavaScript:void(0);" title="">全部状态</a></li>
                                <li><a href="JavaScript:void(0);" title="${ACTIVITY_STATUS_NON_CHECKED}">未审核</a></li>
                                <li><a href="JavaScript:void(0);" title="${ACTIVITY_STATUS_READY}">审核通过</a></li>
                                <li><a href="JavaScript:void(0);" title="${ACTIVITY_STATUS_NOTPASS}">审核不通过</a></li>
                                <li><a href="JavaScript:void(0);" title="${ACTIVITY_STATUS_OFFLINE}">已下架</a></li>
                                <li><a href="JavaScript:void(0);" title="${ACTIVITY_STATUS_DRAFT}">草稿</a></li>
                            </ul>
                            <input id="aStatus" name="aStatus" type="hidden" value="${aStatus}"/>
                        </div>
                    </th>
                </tr>
                </thead>
                <tbody>

                <c:forEach  items="${tlist}" var="activity">
                    <tr>
                        <td><c:out value="${activity.showStartTime}"></c:out></td>
                        <td><c:out value="${activity.societyName}"></c:out></td>
                        <td><c:out value="${activity.activityTitle}"></c:out></td>
                        <td>
                            <c:choose>
                                <c:when test="${activity.activityStatus==ACTIVITY_STATUS_NON_CHECKED}">
                                    未审核
                                </c:when>
                                <c:when test="${activity.activityStatus==ACTIVITY_STATUS_NOTPASS}">
                                    审核不通过
                                </c:when>
                                <c:when test="${activity.activityStatus==ACTIVITY_STATUS_READY}">
                                    审核通过
                                </c:when>
                                <c:when test="${activity.activityStatus==ACTIVITY_STATUS_DRAFT}">
                                    草稿
                                </c:when>
                                <c:when test="${activity.activityStatus==ACTIVITY_STATUS_OFFLINE}">
                                    已下架
                                </c:when>
                                <c:otherwise>
                                    未知
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="panel-footer">
                <pagination:pagination param="max=${pageMaxSize}&total=${totalsize}&page=${page}&sName=${sName}"/>

            </div>
        </form>
    </div>
</div>
<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
<script>
    $(function(){
        $('#ulddm1 li a').each(function(i){
            if($(this).attr("title") == $("#aStatus").val()){
                $("#ulddm1").prev().val($(this).attr("title"));
                $("#ulddm1").prev().text($(this).text());
                $("#ulddm1").prev().append("<span class='caret'>");
            }
        });

        $("#ulddm1 li a").bind("click", function () {
            var $this = $(this);
            var $a = $this.parent().parent().prev();
            var t = $this.text();
            $a.val($this.attr('title'));
            $('#aStatus').val($this.attr('title'));
            $a.text($this.text());
            $a.append("<span class='caret'>");
            $("#tform").submit();
        });
    });
</script>
</body>
</html>
