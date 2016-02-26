<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<script src="${jsPath}/jquery/2.0.2/jquery.min.js"></script>
<script src="${jsPath}/bootstrap/bootstrap.min.js"></script>
<script src="${jsPath}/bootstrap/bootstrapValidator.min.js"></script>
<script src="${jsPath}/bootstrap/locales/bootstrapValidator.zh_CN.js"></script>
<script src="${jsPath}/bootstrap/bootstrap-datetimepicker.min.js"></script>
<script src="${jsPath}/bootstrap/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${jsPath}/AdminLTE/app.js"></script>
<script language="JavaScript">

<shiro:authenticated>
$.post("${ctxPath}/nav/viewRolePLte.action",function(data){
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
        $("#navlist").append("<li><a href=\"#\"><i class=\"fa fa-angle-double-right\"></i> 社长换届</a></li>");
        
    }
});
</shiro:authenticated>

    var pass_error = false;
    $(function() {
        function initPagination(page) {
            $("#page").val(page);
            $("#page").closest('form').submit();
        }
    });
</script>