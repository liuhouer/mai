<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  import="com.mai.util.ConfigUtil"%>

<%@include file="/resources/common-base.jsp" %>
<c:set var="photo_domain" value="<%=ConfigUtil.getCardPhotoDomain()%>" />
<c:set var="audio_domain" value="<%=ConfigUtil.getCardAudioDomain()%>" />

<script name="appParamJson" id="appParamJson" type="text/plain">${appParamJson}</script>
<!Doctype html>
<html>
<head>
    <title>${personCard.cardStarName}和${personCard.personName}么么哒啦！</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${cssPath}/bootstrap.min.css" rel="stylesheet">
    <style>
        .nav {
            width:20%;
            margin:0 auto;
            background-color:rgba(255,255,255,0);
            /*position:fixed;*/
            /*top:20%;*/
            position:absolute; left:75%;
            bottom:3%;
        }

        .nav1 {
            width:20%;
            margin:0 auto;
            background-color:rgba(255,255,255,0);
            position:absolute; left:5%;
            bottom:5%;
            z-index: 1001;
        }

        .nav2 {
            width:20%;
            margin:0 auto;
            background-color:rgba(255,255,255,0);
            /*position:fixed;*/
            /*top:35%;*/
            position:absolute; left:50%;
            bottom:3%;
        }

        .nav3{
            width:100%;
            margin:0 auto;
            background-color:rgba(255,255,255,0);
            position:fixed;
            top:1%;
            color: #FFFFFF;
        }

        .container-selt{
            margin-bottom:0;
            padding-top: 0;
        }
        .div1{
            display:inline ;
            background: url(${ctxPath}/resources/images/weiguanshu.png) no-repeat center center;
            background-position:50% 50%;
            background-size:contain;
            border: none;
            position: absolute;
            width:40%;
            height:10%;
            left:7%;
            padding-top: 19%;
        }
        .div2{
            display:inline ;
            background: url(${ctxPath}/resources/images/yanzhi.png) no-repeat center center;
            background-position:50% 50%;
            background-size:contain;
            border: none;
            position: absolute;
            width:40%;
            height:10%;
            left:53%;
            padding-top: 19%;
        }

        .div1>p{
            width: 100%;
            height: 24px;
            line-height: 24px;
            position: absolute;
            top: 46%;
            left: 14%;
            margin-top: -12px;
            font-size: 16px;
            text-align: center;
            color: #FFFFFF;
            font-weight:bold;
        }

        .div2>p{
            color: #FFFFFF;
            width: 100%;
            height: 24px;
            line-height: 24px;
            position: absolute;
            top: 46%;
            left: 10%;
            margin-top: -12px;
            font-size: 16px;
            font-weight:bold;
            text-align: center;
        }
    </style>

    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?56c93f3ce98e5332bac0817d8d3bda0a";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</head>
<body style="background-image: url(${ctxPath}/resources/images/egg/back.png);background-size:auto;">
<div class="container-fluid container-selt">

    <div class="row panel panel-default" style="position:relative;">
        <img src="${photo_domain}/${personCard.finalPhotoURL}" class="img-responsive" style="width: 100%"/>
        <c:if test="${personCard.finalAudioURL != null && personCard.finalAudioURL != ''}">
            <img src="${ctxPath}/resources/images/audio1.png" class="nav1" id="playaudio"/>
            <audio id="audio" autoplay="autoplay" preload="auto" src="${audio_domain}/${personCard.finalAudioURL}" style="pointer-events:none;display:none;width:0!important;height:0!important;"></audio>
        </c:if>
        <a href="http://dwz.cn/2aSLQS" target="_blank" class="nav">
            <img src="${ctxPath}/resources/images/myplay.png" style="width:100%;"/>
        </a>
        <a href="http://dwz.cn/2aSLQS" target="_blank" class="nav2">
            <img src="${ctxPath}/resources/images/goto.png" style="width:100%;"/>
        </a>
    </div>
    </div>
</div>
<div class="nav3 text-center">
    <div class="div1"><p>${personCard.viewNum}</p></div>
    <div class="div2"><p>${personCard.wxShareNum+personCard.circleShareNum+personCard.shareInWxNum+personCard.shareInCircleNum}</p></div>
</div>


<script src="${jsPath}/jquery/1.11.3/jquery.min.js"></script>
<script src="${jsPath}/micromessage/jweixin-1.min.js"></script>
<script src="${jsPath}/module/mobileweb/mobilePortalCounter.js"></script>
<script type="text/javascript">
    var _ctxPath = '${ctxPath}';
    $(document).ready(function(){
        <c:if test="${personCard.finalAudioURL != null && personCard.finalAudioURL != ''}">
        var audioObj = document.getElementById("audio");
        $("#playaudio").click(function(){
            <%--audioObj.src = '${audio_domain}/${personCard.finalAudioURL}';--%>
            audioObj.currentTime = 0;
            audioObj.play();
        });
        audioObj.addEventListener('ended', function () {
            $("#playaudio").attr("src","${ctxPath}/resources/images/audio1.png");
        }, false);
        audioObj.addEventListener("playing", function(){
            $("#playaudio").attr("src","${ctxPath}/resources/images/audio1.gif");
        },false);
        </c:if>

    });


</script>
<script type="text/javascript">
    //阅读、转发、计数器
    var appParam = eval('(' + $('#appParamJson').html() + ')');
    var img = new Image();
    img.src = '${photo_domain}'+'/'+appParam.picID ;
    var option={
        logoImgUrl:img.src,
        pageID : appParam.pageID, //页面编号，一般为页面对象的主键
        pageType:appParam.pageType, //页面类型，首页or详情页
        isPreview: appParam.isPreview,//是否是预览
        pageUrl:location.href.split('#')[0], //页面url
        pageTitle:appParam.title, //页面标题
        pageDesc:appParam.description //页面摘要
    }
    var mobilePortalCounter = new MobilePortalCounter(option);
</script>
</body>
</html>
