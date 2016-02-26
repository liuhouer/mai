<%--
  Created by IntelliJ IDEA.
  User: denghao
  Date: 15/10/1
  Time: 上午10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/admin/navbar.jsp" %>


<div class="jumbotron container-fluid">
    <div class="panel panel-default">
        <form action="/activityRunning/ActivityList.action" method="post" id="arform" name="arform">

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
                </div>
            </div>

            <table class="table table-bordered table-hover table-responsive table-condensed">
                <thead>
                <tr>
                    <th>活动开始时间</th>
                    <th>社团名称</th>
                    <th>活动名称</th>
                    <th>关注数</th>
                    <th>推荐位</th>
                    <th>常见操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach  items="${arlist}" var="activityrunning">
                    <tr>
                        <td><c:out value="${activityrunning.showStartTime}"></c:out></td>
                        <td><c:out value="${activityrunning.societyName}"></c:out></td>
                        <td><c:out value="${activityrunning.activityTitle}"></c:out></td>
                        <td>
                            <button class="btn btn-link" type="button" data-toggle="modal" data-target="#myModal" onclick="setValueModal(this,'${activityrunning.activityID}',3,'${activityrunning.activityFollowNum}');">
                                <c:choose>
                                    <c:when test="${activityrunning.followNum != null}">
                                        ${activityrunning.followNum}
                                    </c:when>
                                    <c:otherwise>
                                        无
                                    </c:otherwise>
                                </c:choose>
                            </button>
                            <input type="hidden" name="${activityrunning.activityID}_sysfollow" id="${activityrunning.activityID}_sysfollow" value="${activityrunning.activityFollowNum}"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${activityrunning.recommendNo!=null && activityrunning.recommendNo > 0}">
                                    <c:out value="${activityrunning.recommendNo}"></c:out>
                                </c:when>
                                <c:otherwise>
                                    无
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${activityrunning.recommendNo!=null && activityrunning.recommendNo > 0}">
                                    <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="setValueModal(this,'${activityrunning.activityID}',2);">取消推荐</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="setValueModal(this,'${activityrunning.activityID}',1);">推荐</button>
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
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" action="/activityRunning/ActivityList.action" role="form" id="newvalue_form">
                <input type="hidden" name="aid" id="aid" value=""/>
                <input type="hidden" name="jump_page" id="jump_page" value="${page}"/>
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
                    <div class="container" id="newvalue_container">
                        <div class="form-group">
                            <label for="newvalue" class="col-sm-2 control-label" id="newvalue_colname"></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" style="width:250px;" name="newvalue" id="newvalue" placeholder="请填写整数" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="submit" class="btn btn-primary" id="myModalBtn">
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%@include file="/resources/footer-js.jsp" %>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
<script>
    $(function(){
        $("#newvalue_form").bootstrapValidator({fields: {
            newvalue: {
                validators: {
                    notEmpty: {
                        message: '数值不能为空'
                    },
                    digits: {
                        message: '请输入整数'
                    }
                }
            }
        }}).on('success.form.bv', function(e) {
            var $form = $(e.target);
            if($form.attr('action').indexOf("ActivitySysFollow") > -1){
                e.preventDefault();
                var bv = $form.data('bootstrapValidator');
                $.post($form.attr('action'), $form.serialize(), function(result) {
                    var result_json = jQuery.parseJSON(result);
                    if(result_json.errorcode == 0){
                        $("#"+result_json.aid+"_sysfollow").val(result_json.value);
                        $('#myModal').modal('hide');
                    }
                }, 'json');
            }
        });

        $("#sbtn").bind("click",function(){
            $("#page").val(1);
            $("#arform").submit();
        });

    });
    function setValueModal(event,aid,_type,oldvalue){
        $("#newvalue_form").data('bootstrapValidator').disableSubmitButtons(false);
        $("#newvalue").val("");
        if(_type == 1){
            $("#newvalue_colname").text("推荐位");
            $("#myModalp").hide();
            $("#myModalp").empty();
            $("#newvalue_container").show();
            $("#newvalue").show();
            $("#myModalLabel").text($(event).text());
            $("#myModalBtn").text($(event).text());
            $("#newvalue_form").attr("action","/activityRunning/ActivityRecommendNo.action");
        }else if(_type == 2){
            $("#newvalue_container").hide();
            $("#myModalp").text("确定取消推荐吗？");
            $("#myModalp").show();
            $("#newvalue").hide();
            $("#myModalLabel").text($(event).text());
            $("#myModalBtn").text($(event).text());
            $("#newvalue_form").attr("action","/activityRunning/ActivityRecommendNo.action");
        }
        else if(_type == 3){
//            if(!oldvalue || $.trim(oldvalue) == '无'){
//                oldvalue = 0;
//            }
            $("#newvalue_colname").text("关注数:"+$(event).text()+"(合计),其中手工更新值为"+$("#"+aid+"_sysfollow").val()+",更改为");
            $("#myModalp").hide();
            $("#myModalp").empty();
            $("#newvalue_container").show();
            $("#newvalue").show();
            $("#myModalLabel").text("设置手工更新值");
            $("#myModalBtn").text("更新");
            $("#newvalue_form").attr("action","/activityRunning/ActivitySysFollow.action");
        }
        $("#aid").val(aid);
        $("#myModalBtn").show();
    }
</script>
</body>
</html>
