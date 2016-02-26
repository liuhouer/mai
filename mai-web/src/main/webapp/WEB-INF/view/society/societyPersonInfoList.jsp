<%--
  Created by IntelliJ IDEA.
  User: denghao
  Date: 15/10/3
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.mai.society.entity.SocietyMember,com.mai.util.ConfigUtil" %>

<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<c:set var="MEMBERSTATUS_CHECKKED" value="<%=SocietyMember.MEMBERSTATUS_CHECKKED%>" />
<c:set var="MEMBERSTATUS_ISVALID_NOT" value="<%=SocietyMember.MEMBERSTATUS_ISVALID_NOT%>" />
<c:set var="MEMBERSTATUS_NORMAL" value="<%=SocietyMember.MEMBERSTATUS_NORMAL%>" />


<div class="jumbotron container-fluid">
    <div class="panel panel-default">
        <form action="/society/SocietyMemberList.action" method="post" id="stform">
            <input type="hidden" name="sid" id="sid" value="${sid}"/>
            <input type="hidden" name="smemberid" id="smemberid" value=""/>
            <input type="hidden" name="newstatus" id="newstatus" value=""/>
            <input type="hidden" name="oldstatus" id="oldstatus" value=""/>
            <input type="hidden" name="page" id="page" value="${page}"/>
            <div class="panel-heading"></div>
            <div class="panel-body">
                <div class="btn-group pull-right" style="margin: auto" role="group" aria-label="...">
                    <div class="input-group" style="margin: auto">
                        <button class="btn btn-default" type="button" onclick="window.location.href='/society/societyInfo.action?societyID=${sid}'">返回</button>
                    </div>
                </div>
            </div>
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                <tr>
                    <th>头像</th>
                    <th>名称</th>
                    <th>性别</th>
                    <th>学校</th>
                    <th>电话</th>
                    <th>状态</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach  items="${smlist}" var="sm">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${sm.headerURL != null && sm.headerURL != ''}">
                                    <a href="<%=ConfigUtil.getProperty("qiniu.qiniuDomainURL.APP")%>/${sm.headerURL}" class="thumbnail col-xs-4" target="_blank">
                                        <img src="<%=ConfigUtil.getProperty("qiniu.qiniuDomainURL.APP")%>/${sm.headerURL}" width="200" height="100">
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${ctxPath}/resources/images/default.png" class="thumbnail col-xs-4" target="_blank">
                                        <img src="${ctxPath}/resources/images/default.png" width="200" height="100">
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${sm.name}"></c:out></td>
                        <td>
                            <c:choose>
                                <c:when test="${sm.gender==1}">男</c:when>
                                <c:when test="${sm.gender==2}">女</c:when>
                                <c:when test="${sm.gender==3}">保密</c:when>
                                <c:otherwise>未知</c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${sm.schoolName}"></c:out></td>
                        <td><c:out value="${sm.phoneNum}"></c:out></td>
                        <td>
                            <c:choose>
                                <c:when test="${sm.memberStatus==MEMBERSTATUS_NORMAL}">待审核</c:when>
                                <c:when test="${sm.memberStatus==MEMBERSTATUS_CHECKKED}">审核通过</c:when>
                                <c:when test="${sm.memberStatus==MEMBERSTATUS_ISVALID_NOT}">审核未通过</c:when>
                                <c:otherwise>未知</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="panel-footer">
                <pagination:pagination param="max=${pageMaxSize}&total=${totalsize}&page=${page}"/>
            </div>
        </form>
    </div>
</div>
<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>
