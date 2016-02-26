<%--
  Created by IntelliJ IDEA.
  User: denghao
  Date: 15/10/1
  Time: 上午10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.mai.activity.entity.Activity" %>
<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<c:set var="ACTIVITY_STATUS_NON_CHECKED" value="<%=Activity.ACTIVITY_STATUS_NON_CHECKED%>" />
<c:set var="ACTIVITY_STATUS_READY" value="<%=Activity.ACTIVITY_STATUS_READY%>" />
<c:set var="ACTIVITY_STATUS_NOTPASS" value="<%=Activity.ACTIVITY_STATUS_NOTPASS%>" />
<c:set var="ACTIVITY_STATUS_OFFLINE" value="<%=Activity.ACTIVITY_STATUS_OFFLINE%>" />
<c:set var="ACTIVITY_STATUS_DRAFT" value="<%=Activity.ACTIVITY_STATUS_DRAFT%>" />
<c:set var="ACTIVITY_STATUS_DELETE" value="<%=Activity.ACTIVITY_STATUS_DELETE%>" />

<div class="jumbotron container-fluid">
    <div class="panel panel-default">
            <input type="hidden" name="page" id="page" value="${page}"/>
            <div class="panel-heading"></div>
            <div class="panel-body">
                <div class="row">
                        <div class="col-lg-2 pull-right" style="margin: auto">
                            <div class="input-group pull-right" style="margin: auto">
                                <button class="btn btn-default" type="button" onclick="window.location.href='/society/societyInfo.action?societyID=${societyID}';">返回</button>
                            </div>
                        </div>
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
                    <th>状态</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach  items="${actlist}" var="activity">
                    <tr>
                        <td><c:out value="${activity.showStartTime}"></c:out></td>
                        <td><c:out value="${activity.activityTitle}"></c:out></td>
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
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="panel-footer">
                <pagination:pagination param="max=${pageMaxSize}&total=${totalsize}&page=${page}&stitle=${stitle}&ssName=${ssName}"/>

            </div>
    </div>
</div>
<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>
