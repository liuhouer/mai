<%--
  Created by IntelliJ IDEA.
  User: denghao
  Date: 15/10/3
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.mai.activity.entity.Sponsor"%>

<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<c:set var="MEMBERSTATUS_CHECKKED" value="<%=Sponsor.STATUS_CHECKED%>" />
<c:set var="MEMBERSTATUS_ISVALID_NOT" value="<%=Sponsor.STATUS_CLOSE%>" />
<c:set var="MEMBERSTATUS_NORMAL" value="<%=Sponsor.STATUS_NORMAL%>" />

<div class="jumbotron container-fluid">
    <div class="panel panel-default">
        <form action="/sponsor/SponsorList.action" method="post" id="stform">
            <input type="hidden" name="spid" id="spid" value=""/>
            <input type="hidden" name="page" id="page" value="${page}"/>
            <div class="panel-heading"></div>
            <div class="panel-body">
                <div class="btn-group" style="margin: auto" role="group" aria-label="...">
                    <div class="input-group" style="margin: auto">
                        <button class="btn btn-default" type="button" onclick="window.location.href='/sponsor/newSponsor.action';">新建赞助信息</button>
                    </div>
                </div>
            </div>
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                <tr>
                    <th>赞助商名称</th>
                    <th>活动名称</th>
                    <th>赞助内容</th>
                    <th>申请人联系电话</th>
                    <th>申请时间</th>
                    <th>常见操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach  items="${sponsorList}" var="sponsor">
                    <tr>
                        <td><c:out value="${sponsor.sponsorName}"></c:out></td>
                        <td><c:out value="${sponsor.activityTitle}"></c:out></td>
                        <td><c:out value="${sponsor.sponsorNote}"></c:out></td>
                        <td><c:out value="${sponsor.phoneNum}"></c:out></td>
                        <td><c:out value="${sponsor.showAppTime}"></c:out></td>
                        <td>
                          <button class="btn btn-default" type="button" id="editActBtn" onclick="window.location.href='/sponsor/newSponsor.action?spid=${sponsor.sponsorID}';">修改</button>
                          <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="deletemodal(this,'${sponsor.sponsorID}');">删除</button>
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
        $("#myModalBtn").click(function(){
            $("#stform").attr("action","/sponsor/deleteSponsor.action");
            $("#stform").submit();
        });
    });
    function deletemodal(event,spid){
            $("#myModalp").empty();
            $("#myModalp").text("确定删除吗？");
            $("#spid").val(spid);
            $("#myModalLabel").text($(event).text());
            $("#myModalBtn").text($(event).text());
            $("#myModalBtn").show();
    }
</script>
</body>
</html>
