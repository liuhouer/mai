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
        <form action="/societyRunning/SocietyList.action" method="post" id="srform" name="srform">

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
                    <th>LOGO</th>
                    <th>社团名称</th>
                    <th>关注数</th>
                    <th>点赞数</th>
                    <th>等级</th>
                    <th>推荐位</th>
                    <th>常见操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach  items="${srlist}" var="societyrunning">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${societyrunning.societyLOGO != null && societyrunning.societyLOGO != ''}">
                                    <a href="${societyrunning.societyLOGO}" target="_blank">
                                    <img src="${societyrunning.societyLOGO}" style="max-width: 100px;max-height: 100px"/>
                                        </a>
                                </c:when>
                                <c:otherwise>
                                    无
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                        
                        <button class="btn btn-link" type="button" data-toggle="modal" data-target="#J_label_modal"
                         onclick="topLabel('${societyrunning.societyID}','${societyrunning.societyName}');">
                        <c:out value="${societyrunning.societyName}"></c:out>
						</button>                        
                        </td>
                        <td>
                            <button class="btn btn-link" type="button" data-toggle="modal" data-target="#myModal" onclick="setValueModal(this,'${societyrunning.societyID}',3,'${societyrunning.societyFollowNum}');">
                            <c:choose>
                                <c:when test="${societyrunning.followNum != null}">
                                    ${societyrunning.followNum}
                                </c:when>
                                <c:otherwise>
                                    无
                                </c:otherwise>
                            </c:choose>
                            </button>
                        </td>
                        <td>
                            <button class="btn btn-link" type="button" data-toggle="modal" data-target="#myModal" onclick="setValueModal(this,'${societyrunning.societyID}',4);">
                                <c:choose>
                                    <c:when test="${societyrunning.praiseNum != null}">
                                        ${societyrunning.praiseNum}
                                    </c:when>
                                    <c:otherwise>
                                        无
                                    </c:otherwise>
                                </c:choose>
                            </button>
                            <input type="hidden" name="${societyrunning.societyID}_syspraise" id="${societyrunning.societyID}_syspraise" value="${societyrunning.societyPraiseNum}"/>
                        </td>
                        <td><c:out value="${societyrunning.level}"></c:out></td>
                        <td>
                            <c:choose>
                                <c:when test="${societyrunning.recommendNo!=null && societyrunning.recommendNo > 0}">
                                    <c:out value="${societyrunning.recommendNo}"></c:out>
                                </c:when>
                                <c:otherwise>
                                    无
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${societyrunning.recommendNo!=null && societyrunning.recommendNo > 0}">
                                    <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="setValueModal(this,'${societyrunning.societyID}',2);">取消推荐</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" onclick="setValueModal(this,'${societyrunning.societyID}',1);">推荐</button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="panel-footer">
                <pagination:pagination param="max=${pageMaxSize}&total=${totalsize}&page=${page}&stitle=${stitle}"/>

            </div>
        </form>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" action="/societyRunning/SocietyList.action" role="form" id="newvalue_form">
                <input type="hidden" name="sid" id="sid" value=""/>
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


<!-- label拟态框 -->

<div class="modal fade" id="J_label_modal" tabindex="-1" role="dialog"
     aria-labelledby="J_label_modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" action="/societyRunning/mdName.action" role="form" id="J_label_form" method="post">
                <input type="hidden" id="J_sid" name="J_sid" value=""/>
                <input type="hidden" name="page" id="J_jump_page" value="${page}"/>
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel1">
                    </h4>
                </div>
                <div class="modal-body" >
                    <p class="text-center"></p>
                    <div class="container">
                            <div class="form-group">
                                <label for="newvalue" class="col-sm-2 control-label" >社团名称</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" style="width:250px;"  id="J_label_name" name="J_label_name" placeholder="请填写你想修改的社团名称" required>
                                </div>
                            </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="submit" class="btn btn-primary" id="J_ok_btn">
                                                       确定
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
            if($form.attr('action').indexOf("SocietySysPraise") > -1){
                e.preventDefault();
                var bv = $form.data('bootstrapValidator');
                $.post($form.attr('action'), $form.serialize(), function(result) {
                    var result_json = jQuery.parseJSON(result);
                    if(result_json.errorcode == 0){
                        $("#"+result_json.sid+"_syspraise").val(result_json.value);
                        $('#myModal').modal('hide');
                    }
                }, 'json');
            }
        });

        $("#sbtn").bind("click",function(){
            $("#page").val(1);
            $("#srform").submit();
        });
        $("#J_label_form").bootstrapValidator({fields: {
        	J_label_name: {
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    }
                }
            }
        }});
    });
    /*弹出label  */
    function topLabel(id,name){
    	$("#J_label_name").val(name);
    	$("#J_sid").val(id);
    }
    function setValueModal(event,sid,_type,oldvalue){
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
                $("#newvalue_form").attr("action","/societyRunning/SocietyRecommendNo.action");
            }else if(_type == 2){
                $("#newvalue_container").hide();
                $("#myModalp").text("确定取消推荐吗？");
                $("#myModalp").show();
                $("#newvalue").hide();
                $("#myModalLabel").text($(event).text());
                $("#myModalBtn").text($(event).text());
                $("#newvalue_form").attr("action","/societyRunning/SocietyRecommendNo.action");
            }
            else if(_type == 3){
                if(!oldvalue || $.trim(oldvalue) == '无'){
                    oldvalue = 0;
                }
                $("#newvalue_colname").text("关注数:"+$(event).text()+"(合计),其中手工更新值为"+oldvalue+",更改为");
                $("#myModalp").hide();
                $("#myModalp").empty();
                $("#newvalue_container").show();
                $("#newvalue").show();
                $("#myModalLabel").text("设置手工更新值");
                $("#myModalBtn").text("更新");
                $("#newvalue_form").attr("action","/societyRunning/SocietySysFollow.action");
            }
            else if(_type == 4){
                if(!oldvalue || $.trim(oldvalue) == '无'){
                    oldvalue = 0;
                }
                $("#newvalue_colname").text("点赞数:"+$(event).text()+"(合计),其中手工更新值为"+$("#"+sid+"_syspraise").val()+",更改为");
                $("#myModalp").hide();
                $("#myModalp").empty();
                $("#newvalue_container").show();
                $("#newvalue").show();
                $("#myModalLabel").text("设置手工更新值");
                $("#myModalBtn").text("更新");
                $("#newvalue_form").attr("action","/societyRunning/SocietySysPraise.action");
            }
        $("#sid").val(sid);
        $("#myModalBtn").show();
    }
</script>
</body>
</html>
