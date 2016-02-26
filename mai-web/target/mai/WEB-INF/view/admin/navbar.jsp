<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mai.util.CurrentUser" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="/resources/common-base.jsp" %>
<%@include file="/resources/boot-header.jsp" %>
<title>麦同学</title>

<!-- Custom styles for this template -->
<link href="${cssPath}/navbar.css" rel="stylesheet">
<style type="text/css">
    .input-group {
        margin: 20px;
    }

    .btn-group {
        margin: 20px;
    }
</style>

</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">麦同学</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" id="navlist">
                <%--<% List<Map<String,String>> plist = CurrentUser.getCurrentUser().getPermissionList();--%>
                    <%--if(plist!=null&&plist.size()>0)--%>
                    <%--for(Map<String,String> permission : plist){%>--%>
                        <%--<li><a href="<%=permission.get("actionURL")%>"><%=permission.get("actionName")%></a></li>--%>
                <%--<%}%>--%>
                <%--<li><a href="/society/societyDeatil.action">社团基本信息</a></li>--%>
                <%--<li class="active"><a href="/activity/ActivityList.action">社团活动<span class="sr-only">(current)</span></a></li>--%>
                <%--<li><a href="/society/SocietyMemberList.action">社团成员</a></li>--%>
                <%--<li><a href="JavaScript:alert('开发中')">社团通知</a></li>--%>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><%=CurrentUser.getPersonName()%>(<%=CurrentUser.getRoleName()%>) <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="JavaScript:void(0);" data-toggle="modal" data-target="#myPwordModal">修改密码</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/logOut.action">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myPwordModal" tabindex="-1" role="dialog"
     aria-labelledby="myPwordModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myPwordModalLabel">
                    修改密码
                </h4>
            </div>
            <div class="modal-body" id="myPwordModalBody">
                <div class="container">
                    <form class="form-horizontal" action="${ctxPath}/nav/updatePassW.action" role="form" id="pass_form">
                        <div class="form-group">
                            <label for="oldpass" class="col-sm-2 control-label">旧密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" style="width:250px;" id="oldpass" placeholder="Old Password"><span id="oldpassTip" style="display:none;color:red;"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="newpass" class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" style="width:250px;" id="newpass" placeholder="New Password"><span id="newpassTip" style="display:none;color:red;"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="newpassAgain" class="col-sm-2 control-label">再次确认新密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" style="width:250px;" id="newpassAgain" placeholder="Again New Password"><span id="newpassAgainTip" style="display:none;color:red;"></span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" id="myPwordModalBtn">
                    确认修改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
