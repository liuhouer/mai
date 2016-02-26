<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/admin/navbar.jsp" %>


<!-- Main component for a primary marketing message or call to action -->
<form method="post" action="${ctxPath}/sponsor/saveOrUpdateSponsor.action" id="spform" name="spform">
    <div class="jumbotron">
        <input type="hidden" id="sponsorID" name="sponsorID" value="${sponsor.sponsorID}">
        <input type="hidden" id="sponsorStatus" name="sponsorStatus" value="${sponsor.sponsorStatus}"/>
        <input type="hidden" id="activityID" name="activityID" value="${sponsor.activityID}"/>
        <input type="hidden" id="createTime" name="createTime" value="${sponsor.createTime}"/>
        <input type="hidden" id="updateTime" name="updateTime" value="${sponsor.updateTime}"/>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon" id="spid">赞助商名称</span>
                <input type="text" class="form-control" name="sponsorName" placeholder="赞助商名称"
                       value="${sponsor.sponsorName}" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写赞助商名称">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
            <span class="input-group-addon" id="atitle">活动名称</span>
            <input type="text" class="form-control" placeholder="活动名称" value="${actTitle}"
                   name="actTitle" id="actTitle" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写活动名称">
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon" id="aNotes">赞助内容</span>
        <textarea class="form-control" placeholder="赞助内容"
                  rows="3" name="sponsorNote" required data-bv-notempty-message="请填写资助内容">${sponsor.sponsorNote}</textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon" id="aPNum">申请人联系电话</span>
                <input type="text" class="form-control" placeholder="申请人联系电话" name="phoneNum" value="${sponsor.phoneNum}"
                       aria-describedby="basic-addon1" required data-bv-notempty-message="请填写申请人联系电话">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                <span class="input-group-addon" id="aBTime">申请时间</span>
                <input class="form-control" size="16" name="_showAppTime" data-bv-notempty-message="请填写申请时间" type="text" value="${sponsor.showAppTime}" readonly required>
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
            <input type="hidden" id="dtp_input1" value="${sponsor.showAppTime}" name="showAppTime"/>
        </div>

        <div class="form-group btn-group" role="group" aria-label="...">
            <button type="submit" id="mysubmit" class="btn btn-default">确定</button>
            <button type="button" class="btn btn-default" onclick="window.location.href='${ctxPath}/sponsor/SponsorList.action'">取消</button>
        </div>
    </div>

    </div> <!-- /container -->


</form>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <%--<button type="button" class="close"--%>
                        <%--data-dismiss="modal" aria-hidden="true">--%>
                    <%--&times;--%>
                <%--</button>--%>
                <h4 class="modal-title" id="myModalLabel">
                    活动提示
                </h4>
            </div>
            <div class="modal-body" id="myModalBody">
                <p class="text-center" id="myModalp">您输入的活动不存在，请再次核对！</p>

                <div class="input-group" data-toggle="buttons" id="act_selects">
                    <span class="input-group-addon" id="act_head">活动选择</span>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="myModalBtn" data-dismiss="modal">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%@include file="/resources/footer-js.jsp" %>
<script language="JavaScript">
    var is_select_act = false;
    var is_select_act_submit = false;
    $(function(){
        $('.form_datetime').datetimepicker({
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            language:'zh-CN'
        }).on('changeDate show', function(e) {
            $('#spform')
                    .data('bootstrapValidator')
                    .updateStatus('_showAppTime', 'NOT_VALIDATED', null)
                    .validateField('_showAppTime');   //start_time为input的name值
        });

        $("#spform").bootstrapValidator({fields: {
            phoneNum: {
                validators: {
                    phone: {
                        country: 'CN'
                    }
                }
            }
        }}).on('success.form.bv', function(e) {
            e.preventDefault();
            var _actTitle = $("#actTitle").val();
            $.post("${ctxPath}/activity/ActivityInfoList.action?actTitle="+encodeURI(encodeURI(_actTitle)),function(data){
                if(data && data.length > 0){
                    if(data.length == 1 && data[0]["activityID"]!=null){
                        is_select_act = false;
                        $("#activityID").attr("value",data[0].activityID);
                        var $form = $(e.target);
                        var bv = $form.data('bootstrapValidator');
                        $.post($form.attr('action'), $form.serialize(), function(result) {
                            window.location.href = "${ctxPath}"+result;
                        }, 'json');
                    }else if(is_select_act_submit){
                        is_select_act = false;
                        var $form = $(e.target);
                        var bv = $form.data('bootstrapValidator');
                        $.post($form.attr('action'), $form.serialize(), function(result) {
                            window.location.href = "${ctxPath}"+result;
                        }, 'json');
                    }else {
                        is_select_act = true;
                        $("#act_head").nextAll().remove();
                        $.each(data, function (I,N) {
                            if($("#activityID").val()!=""){
                                if($("#activityID").val() == N.activityID){
                                    $("#act_selects").append('<label class="btn btn-default active" onClick="radioboxView(this);"><input type="radio" name="actids" id="act_id_'+ N.activityID+'" autocomplete="off" value="'+ N.activityID+'" checked><span>社团名：'+ N.societyName+'</span><span class="badge">&radic;</span></label>');
                                }else{
                                    $("#act_selects").append('<label class="btn btn-default" onClick="radioboxView(this);"><input type="radio" name="actids" id="act_id_'+ N.activityID+'" autocomplete="off" value="'+ N.activityID+'"><span>社团名：'+ N.societyName+'</span></label>');
                                }
                            }else{
                                if(I == 0){
                                    $("#act_selects").append('<label class="btn btn-default active" onClick="radioboxView(this);"><input type="radio" name="actids" id="act_id_'+ N.activityID+'" autocomplete="off" value="'+ N.activityID+'" checked><span>社团名：'+ N.societyName+'</span><span class="badge">&radic;</span></label>');
                                    $("#activityID").attr("value",N.activityID);
                                }else{
                                    $("#act_selects").append('<label class="btn btn-default" onClick="radioboxView(this);"><input type="radio" name="actids" id="act_id_'+ N.activityID+'" autocomplete="off" value="'+ N.activityID+'"><span>社团名：'+ N.societyName+'</span></label>');
                                }
                            }

                        });
                        $(e.target).data('bootstrapValidator').disableSubmitButtons(false);
                        $("#myModalp").text("您输入的活动名称有重复，请再次确认！");
                        $('#myModal').modal();
                    }
                }else{
                    is_select_act = false;
                    $("#act_selects").hide();
                    $("#myModalp").text("您输入的活动不存在，请再次核对！");
                    $('#myModal').modal();
                    $(e.target).data('bootstrapValidator').disableSubmitButtons(false);
                }
            },"json");
        });

        $("#myModalBtn").click(function(){
            if(is_select_act){
                is_select_act_submit = true;
                $("#spform").submit();
            }
        });

    });
    function radioboxView(event){
        setTimeout(function(){
            var _obj = $(event).children(":first-child");
            if(_obj.is(":checked")){
                _obj.siblings().remove(".badge");
                _obj.next().after('<span class="badge">&radic;</span>');
                $("#activityID").attr("value",_obj.val());
            }
            $('input[name="actids"]').each(function(){
                if(!$(this).is(":checked")){
                    $(this).siblings().remove(".badge");
                }
            });
        },0);

    }
</script>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>