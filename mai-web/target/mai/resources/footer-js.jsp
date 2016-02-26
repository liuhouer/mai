<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${jsPath}/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${jsPath}/bootstrap/bootstrap.min.js"></script>
<script src="${jsPath}/bootstrap/bootstrapValidator.min.js"></script>
<script src="${jsPath}/bootstrap/locales/bootstrapValidator.zh_CN.js"></script>
<script src="${jsPath}/bootstrap/ie10-viewport-bug-workaround.js"></script>
<script src="${jsPath}/bootstrap/bootstrap-datetimepicker.min.js"></script>
<script src="${jsPath}/bootstrap/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${jsPath}/bootstrap/bootstrap-wysiwyg.js"></script>
<script src="${jsPath}/jquery/jquery.hotkeys.js" type="text/javascript"></script>
<script language="JavaScript">
    var pass_error = false;
    $(function(){
        <shiro:authenticated>
        $.post("${ctxPath}/nav/viewRoleP.action",function(data){
            if(data!=""){
                $("#navlist").append(data);
                $("#navlist li a").each(function(){
                    if(window.location.href.indexOf($(this).attr("href"))>-1){
                        $(this).parent().addClass("active");
                        $(this).text();
                        $(document).attr("title",$(this).text());
                        $(this).append("<span class='sr-only'>(current)</span>");
                    }
                });
            }
        });
        </shiro:authenticated>



        $("#oldpass").blur(function(){
            var oldpass = $("#oldpass").val();
            if(oldpass =='') {
                showError('oldpass', '密码不能为空');
                pass_error = true;
                return;
            }
            $.post("${ctxPath}/nav/checkPassW.action", {oldpass:oldpass}, function(data){
                if(data) {
                    $("#oldpass").css({"border-color":"green"});
                    $("#oldpassTip").css({"display":"none"});
                    pass_error = false;
                } else {
                    showError('oldpass', '密码错误');
                    pass_error = true;
                }
            });
        });

        $("#newpass").blur(function(){
            var newpass = $("#newpass").val();
            if(newpass == '') {
                showError('newpass', '新密码不能为空');
                pass_error = true;
            }
            else {
                $("#newpass").css({"border-color":"green"});
                $("#newpassTip").css({"display":"none"});
                pass_error = false;
            }
        });

        $("#newpassAgain").blur(function(){
            var newpass = $("#newpass").val();
            if(newpass == '') {
                showError('newpass', '新密码不能为空');
                pass_error = true;
                return;
            }

            var newpassAgain = $("#newpassAgain").val();
            if(newpassAgain != newpass) {
                showError('newpassAgain', '与输入的新密码不一致');
                pass_error = true;
            }
            else {
                $("#newpassAgain").css({"border-color":"green"});
                $("#newpassAgainTip").css({"display":"none"});
                pass_error = false;
            }
        });



        $("#myPwordModalBtn").click(function(){
            $("#oldpass").blur();
            $("#newpass").blur();
            $("#newpassAgain").blur();
            if(!pass_error) {
                var oldpass = $("#oldpass").val();
                var newpass = $("#newpassAgain").val();
                $.post('${ctxPath}/nav/updatePassW.action', {oldpass:oldpass, newpass:newpass}, function(data) {
                    if(data){
                        window.location.href = "${ctxPath}/logOut.action";
                    }else{
                        showError('oldpass', '密码错误');
                        pass_error = true;
                    }
                });
            }
            return false;
        });
    });

    function showError(formSpan, errorText) {
        $("#" + formSpan).css({"border-color":"red"});
        $("#" + formSpan + "Tip").empty();
        $("#" + formSpan + "Tip").append(errorText);;
        $("#" + formSpan + "Tip").css({"display":"inline"});
    }

        function initPagination(page){
            $("#page").val(page);
            $("#page").closest('form').submit();
        }



</script>