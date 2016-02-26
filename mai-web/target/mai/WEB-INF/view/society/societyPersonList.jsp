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
            <input type="hidden" name="personid" id="personid" value=""/>
            <input type="hidden" name="newstatus" id="newstatus" value=""/>
            <input type="hidden" name="oldstatus" id="oldstatus" value=""/>
            <input type="hidden" name="page" id="page" value="${page}"/>
            <div class="panel-heading"></div>
            <div class="panel-body">
                <%--<div class="btn-group pull-right" style="margin: auto" role="group" aria-label="...">--%>
                    <%--<div class="input-group" style="margin: auto">--%>
                        <%--<button class="btn btn-default" type="button">社团预览</button>--%>
                    <%--</div>--%>
                <%--</div>--%>
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
                            <input id="spgenderid" name="spgenderid" type="hidden" value="${spgenderid}"/>
                            <input id="spgendername" name="spgendername" type="hidden" value="${spgendername}"/>
                        </div>
                    </th>
                    <th>
                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                全部学校<span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" id="ulddm2">
                            </ul>
                            <input id="spsid" name="spsid" type="hidden" value="${spsid}"/>
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
                            <input id="sptype" name="sptype" type="hidden" value="${sptype}"/>
                        </div>
                    </th>
                    <th>常见操作</th>
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
                        <td><c:out value="${sm.applayNote}"></c:out></td>
                        <td id="mstatus_${sm.societyMemberID}">
                            <c:choose>
                                <c:when test="${sm.memberStatus==MEMBERSTATUS_NORMAL}">待审核</c:when>
                                <c:when test="${sm.memberStatus==MEMBERSTATUS_CHECKKED}">审核通过</c:when>
                                <c:when test="${sm.memberStatus==MEMBERSTATUS_ISVALID_NOT}">审核未通过</c:when>
                                <c:otherwise>未知</c:otherwise>
                            </c:choose>
                        </td>
                        <td id="operation_${sm.societyMemberID}">
                            <c:choose>
                                <c:when test="${sm.memberStatus==MEMBERSTATUS_NORMAL}">
                                    <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="auditmodal(this,'${sm.societyMemberID}','${sm.personID}',${sm.memberStatus},${MEMBERSTATUS_CHECKKED},1);">审核通过</button>&nbsp;

                                    <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="auditmodal(this,'${sm.societyMemberID}','${sm.personID}',${sm.memberStatus},${MEMBERSTATUS_ISVALID_NOT});">审核不通过</button>
                                </c:when>
                                <c:when test="${sm.memberStatus==MEMBERSTATUS_CHECKKED}">
                                    <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="auditmodal(this,'${sm.societyMemberID}','${sm.personID}',${sm.memberStatus},${MEMBERSTATUS_ISVALID_NOT});">审核不通过</button>
                                </c:when>
                                <c:when test="${sm.memberStatus==MEMBERSTATUS_ISVALID_NOT}">
                                    <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="showLogList(this,'${sm.societyMemberID}');">查看原因</button>
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
            </div>
        </form>
    </div>
</div>
<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
<script>
    $(function(){
        $('#ulddm1 li a').each(function(i){
            if($(this).attr("title") == $("#spgenderid").val()){
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
            $('#spgenderid').val($this.attr('title'));
            $('#spgendername').val($this.text());
            $a.text($this.text());
            $a.append("<span class='caret'>");
            $("#stform").submit();
        });

        $.post("${ctxPath}/school/getSchoolList.action",function(data){
            if(data && data.length>0){
                $("#ulddm2").empty();
                $("#ulddm2").append('<li><a title="" href="JavaScript:void(0);">请选择学校...</a></li>');
                $.each(data,function(I,N){
                    if($('#spsid').val() == N.schoolID){
                        $("#ulddm2").prev().val($("#spsid").val());
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
                    $('#spsid').val($this.attr('title'));
                    $a.text($this.text());
                    $a.append("<span class='caret'>");
                    $("#stform").submit();
                });
            }
        },"json");

        $('#ulddm3 li a').each(function(i){
            if($(this).attr("title") == $("#sptype").val()){
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
            $('#sptype').val($this.attr('title'));
            $a.text($this.text());
            $a.append("<span class='caret'>");
            $("#stform").submit();
        });

        $("#myModalBtn").click(function(){
            $("#myModalBtn").addClass("disabled");
            $("#stform").attr("action","/society/SocietyMemberStatus.action");
            var $form = $("#stform");
//            $("#stform").submit();
            $.post($form.attr('action'), $form.serialize(), function(result) {
                var result_json = jQuery.parseJSON(result);
                if(result_json.errorcode == 0){
                    $("#stform").attr("action","/society/SocietyMemberList.action");
                    $("#operation_"+result_json.smemberid).empty();
                    if(result_json.member_status == ${MEMBERSTATUS_NORMAL}){
                            $("#mstatus_"+result_json.smemberid).text('待审核');
                            $("#operation_"+result_json.smemberid).append("<button class=\"btn btn-default\" type=\"button\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"auditmodal(this,'"+result_json.smemberid+"','"+result_json.personid+"',"+result_json.member_status+",${MEMBERSTATUS_CHECKKED},1);\">审核通过</button>&nbsp;");
                            $("#operation_"+result_json.smemberid).append("<button class=\"btn btn-default\" type=\"button\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"auditmodal(this,'"+result_json.smemberid+"','"+result_json.personid+"',"+result_json.member_status+",${MEMBERSTATUS_ISVALID_NOT});\">审核不通过</button>");
                    }else if(result_json.member_status == ${MEMBERSTATUS_CHECKKED}){
                            $("#mstatus_"+result_json.smemberid).text('审核通过');
                            $("#operation_"+result_json.smemberid).append("<button class=\"btn btn-default\" type=\"button\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"auditmodal(this,'"+result_json.smemberid+"','"+result_json.personid+"',"+result_json.member_status+",${MEMBERSTATUS_ISVALID_NOT});\">审核不通过</button>");
                    }else if(result_json.member_status == ${MEMBERSTATUS_ISVALID_NOT}){
                            $("#mstatus_"+result_json.smemberid).text('审核未通过');
                            $("#operation_"+result_json.smemberid).append("<button class=\"btn btn-default\" type=\"button\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"showLogList(this,'"+result_json.smemberid+"');\">查看原因</button>");
                    }else{
                        $("#mstatus_"+result_json.smemberid).text('未知');
                    }
                    $('#myModal').modal('hide');
                }
            }, 'json');
        });
    });
    function auditmodal(event,smemberid,personid,oldstatus,newstatus,_status){
        $("#mysug").text("");
        $("#myModalBtn").removeClass("disabled");
        if(_status && _status == 1){
            $.post("${ctxPath}/society/checkSPersonNum.action?sid=${sid}",function(data){
                if(data == 1){
                    $("#myModalp").text("确定审核通过吗？");
                    $("#myModalBtn").show();
                }else{
                    $("#myModalp").text("社团人数已满！");
                    $("#myModalBtn").hide();
                }
                $("#mysug").hide();
                $("#myModalp").show();
                $("#newstatus").val(newstatus);
                $("#smemberid").val(smemberid);
                $("#personid").val(personid);
                $("#myModalLabel").text($(event).text());
                $("#myModalBtn").text($(event).text());
            },"json");
        }else{
            $("#myModalp").empty();
            $("#myModalp").hide();
            $("#mysug").show();
            $("#newstatus").val(newstatus);
            $("#smemberid").val(smemberid);
            $("#personid").val(personid);
            $("#oldstatus").val(oldstatus);
            $("#myModalLabel").text($(event).text());
            $("#myModalBtn").text($(event).text());
            $("#myModalBtn").show();
        }

    }

    function showLogList(event,smemberid){
        $("#mysug").hide();
        $("#myModalBtn").hide();
        $("#myModalLabel").text($(event).text());
        $.post("${ctxPath}/society/SocietyMemberLogList.action?smemberid="+smemberid,function(data){
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
