<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/resources/common-base.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--<meta name="viewport"content="width=320,maximum-scale=1.3,user-scalable=no">--%>
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<link href="${cssPath}/bootstrap.min.css" rel="stylesheet">
<style>
    .nav {
        width:100%;
        height:60px;
        margin:0 auto;
        background-color:rgba(255,255,255,0.6);
        position:fixed;
        bottom:0;
    }

    .font-stx{
        font-family: STXihei, "华文细黑";
    }
    .thumbnail-border-none{
        margin-bottom:0;
        border: none;
    }
    .color-orange{
        color: rgba(255, 161, 1, 1);
    }
    .panel-body-wb{
        word-break:break-all;overflow:hidden;
    }
    .container-selt{
        margin-bottom:0;
        padding-bottom: 60px;
        padding-top: 20px;
    }
</style>
<title>${society.societyName}</title>
</head>
<body>
<div class="jumbotron container-fluid container-selt">
    <p class="text-center font-stx"><strong>${society.societyName}</strong></p>
    <div class="row panel panel-default">
        <img src="${society.coverURL}" class="img-responsive" style="width: 100%"/>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading" style="background-color:rgba(255, 255, 255, 1);color: rgba(255, 161, 1, 1);border-left: 3px solid rgba(255, 161, 1, 1);">社团说</div>
        <div class="panel-body panel-body-wb">
            ${society.societyNote}
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading" style="background-color:rgba(255, 255, 255, 1);color: rgba(255, 161, 1, 1);border-left: 3px solid rgba(255, 161, 1, 1);">扫描二维码</div>
        <div class="panel-body thumbnail thumbnail-border-none">
            <img src="${ctxPath}/resources/images/maitongxue.png" class="img-responsive"/>
            <h4 class="text-center font-stx">扫描二维码 下载<span class="color-orange">麦同学App</span></h4>
        </div>
    </div>
</div>
<div class="nav text-center">
        <a href="http://dwz.cn/2aSLQS" target="_blank">
            <img src="${ctxPath}/resources/images/followSoc.png" class="btn" style="width:80%; height:100%"/>
        </a>

</div>
</body>
</html>
