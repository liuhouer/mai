/**
 * 移动端组件！！！！！！！！！！！！！！！！！！！
 * 计数器：浏览、转发、赞
 * yjw 2014-12-24 21:50:35
 * @param options
 * @returns {MobilePortalCounter}
 */
;(function(root,factory){
    if (typeof define === "function" && define.amd) {
        // AMD模式
        define(["jquery"], function ($) {
            root.MobilePortalCounter = factory(root,$);
        });
    } else {
        // 全局模式
        root.MobilePortalCounter = factory(root,jQuery);
    }
}(window, function( root, $ ) {
    function MobilePortalCounter(options) {
        //这里的参数全部是控件必须的！！！！！！！！！！！！！
        this.setting = {
                pageID:'', //页面编号，一般为页面对象（MobilePortal MobilePortalContent）的主键   转发统计用
                pageType:'', //页面类型，首页or详情页     转发统计用
                isPreview:false,//是否是预览      转发统计用
                pageUrl:'', //页面url
                logoImgUrl:'', //页面logo
                pageTitle:'', //页面标题
                pageDesc:'', //页面摘要
                isNeedCounter:true //是否统计转发数
                    
        };
        options = options || {};
        $.extend(true, this.setting,options);
        this.init();
    }
    
    $.extend(true,MobilePortalCounter.prototype, {
        visitPage:function(callback){
            $.ajax({
                url : _ctxPath + "/m/visitPage.json",
                data : {
                    pageID : this.setting.pageID,
                    pageType : this.setting.pageType,
                    isPreview : this.setting.isPreview
                },
                success : function(d) {
                    callback && callback.call(self, d);
                }
            });
        },
        deliverPage:function(targetType,callback){
            var self = this;
            $.ajax({
                url : _ctxPath + "/m/deliverPage.json",
                data : {
                    pageID : this.setting.pageID,
                    pageType : this.setting.pageType,
                    targetType : targetType,
                    isPreview : this.setting.isPreview
                },
                success : function(d) {
                    callback && callback.call(self, d);
                }
            });
        },
        getMicroMessengerInfo : function(callback){
            $.ajax({
                url : _ctxPath + "/m/getMicroMessengerInfo.json",
                data : {
                    url : location.href.split('#')[0]
                },
                success : function(d) {
                    callback && callback.call(self, d);
                }
            });
        },
        init:function(){
            var self = this;
            function shareFriend() {
                WeixinJSBridge.invoke('sendAppMessage',{
                    "img_url": self.setting.logoImgUrl,
                    "img_width": "200",
                    "img_height": "200",
                    "link": self.setting.pageUrl,
                    "desc": self.setting.pageDesc,
                    "title": self.setting.pageTitle
                }, function(resp) {
                    if(self.setting.isNeedCounter && (resp.err_msg == 'send_app_msg:confirm' || resp.err_msg == 'send_app_msg:ok')){
                        self.deliverPage('WEIXINPENGYOU');
                    }
                    //_report('send_msg', res.err_msg);
                });
            }
            function shareTimeline() {
                WeixinJSBridge.invoke('shareTimeline',{
                    "img_url": self.setting.logoImgUrl,
                    "img_width": "200",
                    "img_height": "200",
                    "link": self.setting.pageUrl,
                    "desc": self.setting.pageDesc,
                    "title": self.setting.pageTitle
                }, function(resp) {
                    if(self.setting.isNeedCounter && (resp.err_msg == 'share_timeline:confirm' || resp.err_msg == 'share_timeline:ok')){
                        self.deliverPage('WEIXINPENGYOUQUAN');
                    }
                       //_report('timeline', res.err_msg);
                });
            }

            function wxReadyFunc(){
                // 发送给好友
                WeixinJSBridge.on('menu:share:appmessage', function(argv){
                    shareFriend();
                });
                // 分享到朋友圈
                WeixinJSBridge.on('menu:share:timeline', function(argv){
                    shareTimeline();
                });
            }
            if (typeof root.WeixinJSBridge == "undefined"){
                if (document.addEventListener) {
                    document.addEventListener('WeixinJSBridgeReady', wxReadyFunc, false);
                } else if (document.attachEvent) {
                    document.attachEvent('WeixinJSBridgeReady', wxReadyFunc);
                    document.attachEvent('onWeixinJSBridgeReady', wxReadyFunc);
                }
            }else{
                wxReadyFunc();
            }

            self.getMicroMessengerInfo(function(a){
                //var msgs = [];
                //msgs.push("\nappid：" , a.appid);
                //msgs.push("\ntimestamp：" , a.timestamp);
                //msgs.push("\nnonceStr：" , a.nonceStr);
                //msgs.push("\nsignature：" , a.signature);
                //alert(msgs.join(''));
                wx && wx.config({debug: false,appId: a.appid,timestamp: a.timestamp,nonceStr: a.nonceStr,signature: a.signature,jsApiList: ["onMenuShareTimeline", "onMenuShareAppMessage", "onMenuShareQQ", "onMenuShareWeibo"]});
            });
            
        }
        
    });
    //window.onerror = function(errorMessage, scriptURI, lineNumber,columnNumber) {
    //    // 有callback的情况下，将错误信息传递到options.callback中
    //    if(typeof callback === 'function'){
    //        callback({
    //            message : errorMessage,
    //            script : scriptURI,
    //            line : lineNumber,
    //            column : columnNumber
    //        });
    //    }else{
    //        // 其他情况，都以alert方式直接提示错误信息
    //        var msgs = [];
    //        msgs.push("额，代码有错。。。");
    //        msgs.push("\n错误信息：" , errorMessage);
    //        msgs.push("\n出错文件：" , scriptURI);
    //        msgs.push("\n出错位置：" , lineNumber + '行，' + columnNumber + '列');
    //        alert(msgs.join(''));
    //    }
    //}
    //define('components/weixin',['jquery'],function (require, exports, module) {
    //    // 获取模块 a 的接口
    //    var $ = require(('__proto__' in {} ? 'zepto' : 'jquery');
    //    var logo = $("#_share_logo").val();
    //    var mobilePortalID = $("#_weiXinShareID").val();
    //    var portalName = $("#_share_protalName").val();
    //    var imgUrl =_ctxPath + '/file/loadPic.img?id='+logo;
    //    var lineLink =_ctxPath + '/m/site.action?id='+mobilePortalID;
    //    var descContent = portalName;
    //    var shareTitle = portalName;
    //    var appid = '';
    //     
    //    
    //});
    //    
    return MobilePortalCounter;
}));