<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="/resources/common-base.jsp" %>
<%@include file="/resources/boot-header.jsp" %>
<title>登 录</title>

<!-- Custom styles for this template -->
<link href="${cssPath}/signin.css" rel="stylesheet">

</head>
<body>
<div class="index_01">
    <img src="/resources/images/index/index_01.png">
</div>
<div class="index_02" style="height: 450px;">
    <div class="col-md-4 index_02_01">
        <form class="form-signin" action="${ctxPath}/logIn.action">
            <div class="form-group">
                <label for="userName" class="sr-only">用户名</label>
                <input type="text" id="userName" class="form-control" name="userName" placeholder="用户名" required/>
            </div>
            <div class="form-group">
                <label for="password" class="sr-only">密码</label>
                <input type="password" id="password" class="form-control" name="password" placeholder="密码" required/>
            </div>
            <div class="form-group">
                <button class="btn btn-lg btn-block" type="submit">登 录</button>
            </div>
            <span id="LoginpasswordTip" style="display:none;color:red;"></span>
        </form>
    </div>

</div>
<div class="index_03">
    <img src="/resources/images/index/index_03.png">
</div>
<div class="index_04">
    <img src="/resources/images/index/index_04.png">
</div>
<div class="index_05">
    <img src="/resources/images/index/index_05.png">
</div>
<div class="index_06">
        <p class="text-center">&nbsp;</p>
    <p class="text-center">客服软妹纸微信：maitongxue2 | ＱＱ：2308820137 | 邮箱：service@maitongxue.com | 电话：400-880-0757</p>
        <p class="text-center">&nbsp;</p>
        <p class="text-center">Copyright © 2015 www.maitongxue.com All rights reserved | 京ICP备 15006788号-1</p>
        <p class="text-center">&nbsp;</p>
</div>

<%@include file="/resources/footer-js.jsp" %>
</body>
</html>
<script language="JavaScript">
    $(function(){
        $(".form-signin").bootstrapValidator();
        <c:choose>
        <c:when test="loginerror != null && loginerror!=''">
        $("#LoginpasswordTip").css({"display":"none"});
        </c:when>
        <c:otherwise>
        showError('Loginpassword', '${loginerror}');
        </c:otherwise>
        </c:choose>

        if (!window.applicationCache) {
            alert("你的浏览器不支持HTML5");
            $(".form-signin").attr("action",window.location.href);

        }
    });
</script>
