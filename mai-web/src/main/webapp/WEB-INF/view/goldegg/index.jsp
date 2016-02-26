<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.mai.util.ConfigUtil"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/resources/css/egg/main.css" media="all">
    <title>砸金蛋</title>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <!-- Mobile Devices Support @begin -->
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
    <meta content="no-cache" http-equiv="pragma">
    <meta content="0" http-equiv="expires">
    <meta content="telephone=no, address=no" name="format-detection">
    <meta name="apple-mobile-web-app-capable" content="yes"> <!-- apple devices fullscreen -->
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    <!-- Mobile Devices Support @end -->
    <script src="/resources/js/egg/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="/resources/js/egg/my.marquee.js" type="text/javascript"></script>
</head>
<body onselectstart="return true;" ondragstart="return false;">
<div id="mask" class="mask"></div>
<div id="btn_bg" class="btn1_bg">
    <div class="btn_tit"></div>
    <p class="btn_p"></p>
    <!-- 修改标记 -->
    <div id="btn_btn" class="btn1_btn"><p id="btn_btn_txt">我知道了</p></div>
</div>
<div id="btn_zj" class="btn_zj">
    <p class="btn_p_zj"></p>
    <div id="btn_btn_zj_2" class="btn_btn_zj_2"><p id="btn_btn_zj_1_txt">关闭</p></div>
</div>
<div class="body">
    <section class="stage">
        <img src="/resources/images/egg/bg.jpg">
        <div class="tit"></div>
        <div id="shape">
            <div class="plane one">
                <img id="egg_one" src="/resources/images/egg/egg1.png">
                <img id="egg_one2" src="/resources/images/egg/egg2.png" style="display: none">
            </div>
            <div class="plane two">
                <img id="egg_two" src="/resources/images/egg/egg1.png">
                <img id="egg_two2" src="/resources/images/egg/egg2.png" style="display: none">
            </div>
            <div class="plane three">
                <img id="egg_three" src="/resources/images/egg/egg1.png">
                <img id="egg_three2" src="/resources/images/egg/egg2.png" style="display: none">
            </div>
            <div id="hit" class="hit"></div>
            <div id="egg_broken" class="broken"></div>
        </div>
        <div class="lable_point"><lable id="show_username">${username}</lable>：您还有<lable id="show_point"><%=Integer.parseInt(request.getAttribute("point").toString())/10%></lable>次机会</div>
        <div class="button1"><p>活动说明</p></div>
        <div class="div_marquee scrollDiv">
            <ul class="mulitline" id="marquee" style="list-style: none">
            </ul>
        </div>
    </section>
</div>
<script>
    var _number = 1;
    var _point = ${point};
    var _username = "${username}";

    function showMask(){
        $("#mask").css("height",$(document).height());
        $("#mask").css("width",$(document).width());
        $("#mask").show();
    }
    function letDivCenter(divName){
        var top = ($(window).height() - $(divName).height())/2;
        var left = ($(window).width() - $(divName).width())/2;
        var scrollTop = $(document).scrollTop();
        var scrollLeft = $(document).scrollLeft();
        $(divName).css( { position : 'absolute', 'top' : top + scrollTop, left : left + scrollLeft } ).show();
    }

    $(".button1").click(function(){
        showMask();
        $(".btn_p").empty();
        $("#btn_bg").addClass("btn1_bg");
        $(".btn_tit").html("&nbsp;");
        var sb = new StringBuffer();
        sb.append("活动规则：<br />");
        sb.append("1、每转发十名好友，即可参加“砸蛋见明星”一次，转发越多机会越多！<br />");
        sb.append("2、活动奖品<br />");
        sb.append("一等奖-1名，包机明星面对面2016岁月友情（陈小春、郑伊健）演唱会门票2张，同时包往返机票（价值8000元）<br />");
        sb.append("二等奖-3名，明星礼遇：专属司机，mini countryman ，北京巡游1天（价值3000元）<br />");
        sb.append("三等奖-30名，明星礼盒：新年礼盒套装（价值200元）<br />");
        sb.append("四等奖-500名，明星配饰：运动腕表（价值50元）<br />");
        sb.append("3、本次抽奖截至2016年1月6日0点。<br />");
        sb.append("4、本次活动解释权归麦活无限所有<br />");
        sb.append("客服电话：400-880-0757<br /><br />");
        sb.append("---- 大麦悄悄话 ----<br />");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;大麦说：没中吗？抽奖结束了吗？不用灰心，分享到微信，分享越多颜值越高，您就有更多机会赢得春节超爆大奖！<br />");
        $(".btn_p").css("text-align","left").html(sb.toString());
        $("#btn_btn").removeClass().addClass("btn1_btn");
        $("#btn_btn_txt").text("我知道了");
        letDivCenter("#btn_bg");
    });

    $("#btn_btn").click(function(){
            $("#mask").hide();
            $("#btn_bg").hide();
    });
    $(".btn_btn_zj_2").click(function(){
        $("#mask").hide();
        $("#btn_zj").hide();
        reEgg();
    });

    $(document).ready(function(){
        var shape = document.getElementById("shape");
        var hitObj = {
            handleEvent: function(evt){
                if(evt.target.className.indexOf("plane")>-1){

                    //begin
                    $("#btn_btn_zj_1_txt").text("关闭");
                    //end
                    if(_point < 10){
                        showMask();
                        letDivCenter("#btn_zj");
                        $(".btn_p_zj").html("机会已用完~~~转发10人可以再抽一次哟");
                        _point = 0;
                        $("#show_point").text(_point);
                        return;
                    }
                    shape.removeEventListener("click", hitObj, false);


                    $(evt.target).children().eq(0).hide();
                    $(evt.target).children().eq(1).show();

                    var _left = $(evt.target).position().left + 75;
                    var _top = $(evt.target).position().top + 60;
                    $("#hit").addClass("on").css('left', _left);
                    $("#hit").css('top', _top);
                    switch($(evt.target).children().attr("id")){
                        case 'egg_two':
                            $("#egg_broken").css('left','22%');
                            $("#egg_broken").css('top','-12%');
                            break;
                        case 'egg_three':
                            $("#egg_broken").css('left','51%');
                            $("#egg_broken").css('top','-10%');
                            break;
                        default :
                            $("#egg_broken").css('left','-9%');
                            $("#egg_broken").css('top','-10%');
                            break;
                    }
                    $("#egg_broken").css('display','block');



                        $.getJSON("/game/drawLottery.action?personID=${personID}&pnum=${pnum}&username="+encodeURI(encodeURI('${username}'))+"&rand="+Math.random(),function(data) {
                            if(data.status == 0) {
                                $("#btn_btn_zj_1_txt").text("关闭");
                                showMask();
                                letDivCenter("#btn_zj");
                                $(".btn_p_zj").html("机会已用完~~~转发10人可以再抽一次哟");
                                shape.addEventListener("click", hitObj, false);
                            }else{
                                if(data.data.result == 0){
                                    showMask();
                                    letDivCenter("#btn_zj");
                                    $(".btn_p_zj").html("很遗憾没有中奖~~~请再接再厉！");
                                    $("#btn_btn_zj_1_txt").text("再砸一次");
                                    shape.addEventListener("click", hitObj, false);
                                }else{
                                    showMask();
                                    letDivCenter("#btn_zj");
                                    $(".btn_p_zj").html("恭喜您获得"+data.data.gift+"！<br /><lable style=\"font-size:12px\">请截图当前页面，并拨打客服电话：<br />400-880-0757领取奖品</lable>");
                                    $("#btn_btn_zj_1_txt").text("再砸一次");
                                    shape.addEventListener("click", hitObj, false);
                                }
                                _point = data.data.point;
                                $("#show_point").text(parseInt(_point/10));
                            }
                        });

                }

            }
        };
        shape.addEventListener("click", hitObj, false);

        _timer = setInterval("showDIV()", 2000);

        $.getJSON("/game/getLatestWiningPrizes.action?rand="+Math.random(),function(data) {
            if(data.status > 0) {
                var _prizes = data.data;
                for(var i=0;i<_prizes.length;i++){
                    var _user = _prizes[i]['user'];
                    $("#marquee").append("<li style=\"color:#FFFFFF\">"+_user['phone']+"<lable style=\"margin-left:10px;margin-right:10px;\">" + _user['username']+"</lable>"+_prizes[i].name+"</li>");
                }
                if(_prizes.length>1){
                    ul_mulitline();
                }
            }
        });
    });

    function StringBuffer() {
        this.buffer = [];
        if(arguments[0]) this.append(arguments[0]);
    }
    StringBuffer.prototype.append = function() {
        this.buffer.push(arguments[0]);
        return this;
    }
    StringBuffer.prototype.toString = function(){
        return this.buffer.join("");
    }
    StringBuffer.prototype.release = function(){
        this.buffer = [];
    }
    function reEgg(){
        $("#egg_one").show();
        $("#egg_two").show();
        $("#egg_three").show();
        $("#egg_one2").hide();
        $("#egg_two2").hide();
        $("#egg_three2").hide();
        $("#hit").removeClass("on").removeAttr("style");
        $("#egg_broken").removeAttr("style");
        _number = 1;
    }
</script></body></html>