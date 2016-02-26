<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.mai.society.entity.Society" %>
<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<c:set var="STATUS_RELEASED" value="<%=Society.STATUS_RELEASED%>" />

    <div class="jumbotron">
        <div class="panel">
            <form method="post" action="${ctxPath}/society/updateSocietyDeatil.action" id="aform" name="aform" enctype="multipart/form-data">
                <input type="hidden" id="societyID" name="societyID" value="${society.societyID}">
                <input type="hidden" id="coverURL" name="coverURL" value="${society.coverURL}"/>
                <input type="hidden" id="societyLOGO" name="societyLOGO" value="${society.societyLOGO}"/>
                <input type="hidden" id="stagRefsstr" name="stagRefsstr" value="${stagRefsstr}"/>

                <input type="hidden" id="applyNote" name="applyNote" value="${society.applyNote}"/>
                <input type="hidden" id="societyName" name="societyName" value="${society.societyName}">
                <input id="schoolID" name="schoolID" type="hidden" value="${society.schoolID}"/>
                <input id="schoolName" name="schoolName" type="hidden" value="${society.schoolName}"/>
                <input id="isLog" name="isLog" type="hidden" value="${society.isLog}"/>

                <div class="panel-heading"></div>
                <div class="panel-body">
                    <div class="btn-group pull-right" style="margin: auto" role="group" aria-label="...">
                    <div class="input-group" style="margin: auto">
                        <button class="btn btn-default" type="button" onclick="window.location.href='/society/societyPersonInfoList.action?societyID=${society.societyID}'">社团成员</button>
                        <button class="btn btn-default" type="button" onclick="window.location.href='/society/societyActivityInfoList.action?societyID=${society.societyID}'">社团活动</button>
                        <button class="btn btn-default" type="button" onclick="window.location.href='/society/list.action?status=1'">返回</button>
                    </div>
                    </div>
                </div>
                <nav class="navbar navbar-default text-center">
                    <div class="container">
                        <div class="row">
                            <div class="form-inline">
                                <div class="input-group navbar-text col-lg-4">
                                    <span class="input-group-addon">社团名称</span>
                                    <span class="form-control">${society.societyName}</span>
                                </div>
                                <div class="input-group navbar-text col-lg-3">
                                    <span class="input-group-addon">所属单位</span>
                                    <span class="form-control">${society.schoolName}</span>
                                </div>
                                <div class="input-group navbar-text col-lg-2">
                                    <span class="input-group-addon">社长</span>
                                    <span class="form-control">${society.adminName}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </nav>
                <div class="input-group">
                    <span class="input-group-addon" id="scID">类别</span>
                    <span class="form-control">${society.societyCategoryName}</span>
                </div>

                <div class="input-group">
                    <span class="input-group-addon" id="smNum">社团最大人数</span>
                    <span class="form-control">${society.memberNum}</span>
                </div>

                <div class="input-group">
                    <span class="input-group-addon" id="sjPNum">社团成员数</span>
                    <span class="form-control">${society.joinPersonNum}</span>
                </div>

                <div class="input-group">
                    <span class="input-group-addon" id="sbdate">成立时间</span>
                    <span class="form-control">${society.buildDate}</span>
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="slogon">口号</span>
                    <span class="form-control">${society.slogan}</span>
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="spnum">点赞数</span>
                    <span class="form-control">${society.praiseNum}</span>
                </div>

                <div class="input-group">
                    <span class="input-group-addon">区域</span>
                    <span class="form-control" id="slocationID"></span>
                </div>

                <div class="input-group">
                    <span class="input-group-addon" id="sld">具体地址</span>
                    <span class="form-control">${society.locationDetail}</span>
                </div>

                <div class="input-group">
                    <span class="input-group-addon">地图选点</span>
                    <button id="createMap" type="button" class="disabled">
                        <c:choose>
                            <c:when test="${gps_map_static_str!=null && gps_map_static_str != '北京'}">
                                <img src="http://api.map.baidu.com/staticimage?center=${gps_map_static_str}&markers=${gps_map_static_str}&zoom=15"/>
                            </c:when>
                            <c:otherwise>
                                <img src="http://api.map.baidu.com/staticimage?center=${gps_map_static_str}&zoom=15"/>
                            </c:otherwise>
                        </c:choose>
                    </button>
                </div>

                <div class="input-group">
                    <span class="input-group-addon" id="spnum1">联系电话</span>
                    <span class="form-control">${society.phoneNum}</span>
                </div>

                <div class="input-group" data-toggle="buttons" id="tagcb">
                    <span class="input-group-addon" id="aTag">热门标签</span>
                </div>


                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon" id="scurl">封面图片</span>
                        <c:choose>
                            <c:when test="${society.coverURL != null && society.coverURL != ''}">
                                <button type="button" onclick="window.open('${society.coverURL}')">
                                    <img src="${society.coverURL}" width="250" height="150"/>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <span class="form-control">无</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon" id="scurl">社团LOGO</span>
                        <c:choose>
                            <c:when test="${society.societyLOGO != null && society.societyLOGO != ''}">
                                <button type="button" onclick="window.open('${society.societyLOGO}')">
                                    <img src="${society.societyLOGO}" width="250" height="150"/>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <span class="form-control">无</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="input-group">
                    <span class="input-group-addon" id="sNotes">社团介绍</span>
                <textarea class="form-control" placeholder="社团介绍"
                          rows="3" name="societyNote" disabled>${society.societyNote}</textarea>
                </div>
                <div class="btn-group" role="group" aria-label="...">
                    <c:if test="${society.isLog == 1}">
                        <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myLogModal" onclick="showLogList(this,'${society.societyID}');">查看日志</button>
                    </c:if>
                </div>
            </form>
        </div>
    </div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myLogModal" tabindex="-1" role="dialog"
     aria-labelledby="myLogModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myLogModalLabel">

                </h4>
            </div>
            <div class="modal-body" id="myLogModalBody">
                <p class="text-center" id="myLogModalp"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
<script language="JavaScript">
    $(function(){
        $.post("${ctxPath}/location/getLocationList.action",function(data){
            if(data && data.length>0){
                $.each(data,function(I,N){
                    if('${society.locationID}' == N.locationID){
                        $("#slocationID").text(N.locationName);
                        return;
                    }
                });
            }
        },"json");

        $.post("${ctxPath}/stag/getSTagList.action",function(data){
            if(data && data.length>0){
                $("#aTag").nextAll().remove();
                $.each(data,function(I,N){
                    if($("#stagRefsstr").val().indexOf(","+ N.tagID+",")>-1){
                        $("#tagcb").append('<label class="btn btn-default active">'+ N.tagName+'<span class="badge">&radic;</span></label>');
                    }else{
                        $("#tagcb").append('<label class="btn btn-default">'+ N.tagName+'</label>');
                    }
                });
            }
        },"json");
    });

    function showLogList(event,societyID){
        $.post("${ctxPath}/society/SocietyLogListByPresident.action?societyID="+societyID,function(data){
            $("#myLogModalLabel").text($(event).text());
            if(data && data.length>0){
                $("#myLogModalp").hide();
                $("#myLogModalBody dl").remove();
                $("#myLogModalBody").append("<dl class='dl-horizontal'></dl>");
                $.each(data,function(I,N){
                    $("#myLogModalBody dl").append("<dt>" + N.showCreateTime + "</dt><dd>"+ N.logDesc+"</dd>");
                });
            }else{
                $("#myLogModalp").text("未审核");
                $("#myLogModalp").show();
            }
        },"json");
    }
</script>
</body>
</html>