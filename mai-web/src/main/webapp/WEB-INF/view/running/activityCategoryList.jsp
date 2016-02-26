<%--
  Created by IntelliJ IDEA.
  User: denghao
  Date: 15/10/3
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/view/admin/navbar.jsp" %>
<style>
    .selected-img{
        border-color: #a9302a;
    }
</style>

<div class="jumbotron container-fluid">
    <div class="panel panel-default">
        <form action="/activityRunning/ActivityList.action" method="post" id="stform">
            <input type="hidden" name="page" id="page" value="${page}"/>
            <div class="panel-heading"></div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-5 pull-right" style="margin: auto">
                        <div class="input-group pull-right" style="margin: auto">
                            <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="setValueModal(this,'',3);">新建类别</button>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                <tr>
                    <th>图标</th>
                    <th>类别名称</th>
                    <th>活动个数</th>
                    <th>常见操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach  items="${clist}" var="category">
                    <tr id="${category.categoryID}">
                        <td id="${category.categoryID}_img">
                            <c:choose>
                                <c:when test="${category.imageURL != null && category.imageURL != ''}">
                                    <a href="javaScript:void(0)" class="thumbnail col-md-3">
                                        <img src="${category.imageURL}" />
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    无
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td id="${category.categoryID}_name"><c:out value="${category.categoryName}"></c:out></td>
                        <td><c:out value="${category.categoryCount}"></c:out></td>
                        <td>
                            <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="setValueModal(this,'${category.categoryID}',1);">编辑名称</button>&nbsp;
                            <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="setValueModal(this,'${category.categoryID}',4);">编辑图标</button>&nbsp;
                            <button class="btn btn-default" type="button" onclick="window.location.href='/activityRunning/ActivityCategoryDetail.action?cid=${category.categoryID}'">查看详情</button>&nbsp;
                            <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="setValueModal(this,'${category.categoryID}',2);">删除</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="panel-footer">
            </div>
        </form>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" action="/activityRunning/updateActivityCategory.action" method="post" role="form" id="newvalue_form">
                <input type="hidden" name="cid" id="cid" value=""/>
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
                                <input type="text" class="form-control" style="width:250px;" name="newvalue" id="newvalue" placeholder="请填写名称" required>
                            </div>
                        </div>
                    </div>
                    <div class="container" id="newvalue_containers">
                        <div class="form-group">
                            <div class="col-sm-10" style="margin:0 auto;float:none;" id="newvalues_div">
                                <div class="input-group" style="width:350px;"><input type="text" class="form-control" style="width:350px;" name="newvalues" id="newvalues0" placeholder="请填写名称"></div>
                            </div>
                        </div>
                    </div>
                    <div class="container-fluid" id="newimage_containers">
                        <div class="form-group row">
                                    <c:forEach  items="${imageUrls}" var="imageUrl">
                                        <div class="col-md-3">
                                        <a href="javaScript:void(0)" class="thumbnail">
                                            <img src="${imageUrl}" />
                                        </a>
                                        </div>
                                    </c:forEach>
                            <input type="hidden" name="cimgurl" id="cimgurl" value=""/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="submit" class="btn btn-primary" id="myModalBtn">
                    </button>
                    <button type="button" class="btn btn-primary" id="delModalBtn">
                    </button>
                    <button type="button" class="btn btn-primary" id="addModalBtn">
                    </button>
                    <button type="submit" class="btn btn-primary" id="saveModalBtn">
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
        $("a.thumbnail").click(function(){
            $("a.thumbnail").removeClass("selected-img");
            $(this).addClass("selected-img");
            $("#cimgurl").val($(this).children().attr("src"));
        });

        $("#newvalue_form").bootstrapValidator({fields: {
            newvalue: {
                validators: {
                    notEmpty: {
                        message: '类别名称不能为空'
                    }
                }
            }
        }});

        $("#delModalBtn").click(function(){
            $("#delModalBtn").addClass("disabled");
            var delcid = $("#cid").val();
            $.post("${ctxPath}/activityRunning/delActivityCategory.action?cid="+delcid,function(data){
                if(data){
                    $('#myModal').modal('hide');
                    $("#delModalBtn").removeClass("disabled");
                    $("#"+delcid).remove();
                }else{
                    $("#myModalp").text("删除失败，类型已使用！");
                    $("#delModalBtn").hide();
                    $("#delModalBtn").removeClass("disabled");
                    return false;
                }
            },"json");
        });
        $("#addModalBtn").click(function(){
            var _obj = $("#newvalues_div");
//            var _index = $("input[name=newvalues]").length;
            _obj.append('<div class="input-group" style="width:350px;"><input type="text" class="form-control" name="newvalues" placeholder="请填写名称"><span class="input-group-btn"><button class="btn btn-default" type="button" onclick="delInput(this)">删除</button></span></div>');
        });
    });
    function setValueModal(event,cid,_type){
        $("#newvalue_form").data('bootstrapValidator').disableSubmitButtons(false);
        $("#newvalue").val("");
        if(_type == 1){
            $("#newvalue_containers").hide();
            $("#newimage_containers").hide();
            $("#newvalue_colname").text("编辑名称");
            $("#newvalue").val($("#"+cid+"_name").text());
            $("#myModalp").hide();
            $("#myModalp").empty();
            $("#newvalue_container").show();
            $("#newvalue").show();
            $("#myModalLabel").text($(event).text());
            $("#myModalBtn").text($(event).text());
            $("#newvalue_form").attr("action","/activityRunning/updateActivityCategory.action");
            $("#delModalBtn").hide();
            $("#addModalBtn").hide();
            $("#saveModalBtn").hide();
            $("#myModalBtn").show();
        }else if(_type==2){
            $("#newvalue_containers").hide();
            $("#newimage_containers").hide();
            $("#myModalp").text("确认要删除“"+$("#"+cid+"_name").text()+"”类别吗？");
            $("#myModalp").show();
            $("#newvalue_container").hide();
            $("#newvalue").hide();
            $("#myModalLabel").text($(event).text());
            $("#delModalBtn").text("确认删除");
            $("#myModalBtn").hide();
            $("#addModalBtn").hide();
            $("#saveModalBtn").hide();
            $("#delModalBtn").show();
        }else if(_type == 3){
            $("#myModalp").hide();
            $("#myModalp").empty();
            $("#myModalLabel").text("新建类别");
            $("#newvalue_container").hide();
            $("#newimage_containers").hide();
            $("#newvalue_containers").show();
            $("#myModalBtn").hide();
            $("#delModalBtn").hide();
            $("#saveModalBtn").text("保存");
            $("#saveModalBtn").show();
            $("#addModalBtn").text("添加");
            $("#addModalBtn").show();
            $("#newvalues0").parent().siblings().remove();
            $("#newvalue_form").attr("action","/activityRunning/addActivityCategory.action");
        }else if(_type == 4){
            $("#myModalp").hide();
            $("#myModalp").empty();
            $("#newvalue_container").hide();
            $("#newvalue_containers").hide();
            $("#newimage_containers").show();
            $("#delModalBtn").hide();
            $("#saveModalBtn").hide();
            $("#addModalBtn").hide();
            $("#myModalLabel").text($(event).text());
            $("#myModalBtn").text($(event).text());
            $("#newvalue_form").attr("action","/activityRunning/ActivityCategoryImg.action");
        }
        $("#cid").val(cid);
    }

    function delInput(event){
        $(event).parent().parent().remove();
    }
</script>
</body>
</html>
