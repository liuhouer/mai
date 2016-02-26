<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/admin/navbar.jsp" %>

<!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <input type="hidden" id="activityID" name="activityID" value="${activity.activityID}">
        <input type="hidden" id="activityDetailID" name="activityDetailID" value="${activity.activityDetailID}"/>
        <input type="hidden" id="recommendNo" name="recommendNo" value="${activity.recommendNo}"/>
        <input type="hidden" id="societyID" name="societyID" value="${activity.societyID}"/>
        <input type="hidden" id="societyName" name="societyName" value="${activity.societyName}"/>
        <input type="hidden" id="hotNum" name="hotNum" value="${activity.hotNum}"/>
        <input type="hidden" id="followNum" name="followNum" value="${activity.followNum}"/>
        <input type="hidden" id="acttagRefsstr" name="acttagRefsstr" value="${acttagRefsstr}"/>
        <div class="input-group">
            <span class="input-group-addon" id="aID">活动名称</span>
            <span class="form-control">${activity.activityTitle}</span>
        </div>
        <div class="input-group">
            <span class="input-group-addon" id="aMNum">活动人数</span>
            <span class="form-control">${activity.maxPersonNum}</span>
        </div>
        <div class="input-group">
            <span class="input-group-addon">活动加入人数</span>
            <span class="form-control">${activity.joinPersonNum}</span>
        </div>

        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon" id="aBTime">活动开始时间</span>
                <span class="form-control">${activity.showStartTime}</span>
            </div>
            <div class="input-group">
                <span class="input-group-addon" id="aETime">活动结束时间</span>
                <span class="form-control">${activity.showEndTime}</span>
            </div>
        </div>
        <div class="input-group">
            <span class="input-group-addon" id="aAddress">活动地点</span>
            <span class="form-control">${activity.address}</span>
        </div>

        <div class="input-group">
            <span class="input-group-addon">区域</span>
            <span class="form-control" id="alocation"></span>
        </div>

        <div class="input-group">
            <span class="input-group-addon">地图选点</span>
            <button id="createMap" type="button" class="disabled">
                <c:choose>
                    <c:when test="${gps_map_static_str!=null && gps_map_static_str != '北京'}">
                        <img src="http://api.map.baidu.com/staticimage?center=${gps_map_static_str}&markers=${gps_map_static_str}&zoom=15" id="show_map_img"/>
                    </c:when>
                    <c:otherwise>
                        <img src="http://api.map.baidu.com/staticimage?center=${gps_map_static_str}&zoom=15" id="show_map_img"/>
                    </c:otherwise>
                </c:choose>
            </button>
        </div>


        <div class="input-group">
            <span class="input-group-addon" id="aPNum">联络电话</span>
            <span class="form-control">${activity.phoneNum}</span>
        </div>


        <div class="input-group">
            <span class="input-group-addon" id="acID">类型</span>
            <span class="form-control">${activity.categoryName}</span>
        </div>

        <div class="input-group" data-toggle="buttons" id="tagcb">
            <span class="input-group-addon" id="aTag">热门标签</span>
        </div>

            <div class="input-group">
                <span class="input-group-addon" id="c3">封面图片</span>
                <c:choose>
                    <c:when test="${activity.coverURL != null && activity.coverURL != ''}">
                        <button type="button" onclick="window.open('${activity.coverURL}')">
                            <img src="${activity.coverURL}" width="250" height="150"/>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <span class="form-control">无</span>
                    </c:otherwise>
                </c:choose>
            </div>

        <div class="input-group" data-toggle="buttons">
            <span class="input-group-addon" id="aNCheck">是否审核报名</span>
            <label class="btn btn-default active">
            <c:choose>
                <c:when test="${activity.needCheck}">
                    是
                </c:when>
                <c:otherwise>
                    否
                </c:otherwise>
            </c:choose>
            </label>
        </div>

        <div class="input-group">
            <span class="input-group-addon" id="aNotes">注意事项</span>
            <textarea class="form-control" placeholder="注意事项"
                      rows="3" name="notes" disabled>${activity.notes}</textarea>
        </div>

        <div class="form-group">
            <link href="${cssPath}/bootstrap-combined.no-icons.min.css" rel="stylesheet">
            <link href="${cssPath}/bw.css" rel="stylesheet">
            <link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
            <style>
                #editor {overflow:scroll; max-height:300px}
                #editor img{
                    max-width:100%;height:auto;
                }
            </style>
            <div class="container">
                <div class="hero-unit">
                    <div id="editor">
                        ${fileContent!=null?fileContent:"输入内容&hellip;"}
                    </div>
                </div>
            </div>
        </div>

        <div class="btn-group" role="group" aria-label="...">
            <%--<button type="button" id="mysubmit" class="btn btn-default">确定</button>--%>
            <button type="button" class="btn btn-default" onclick="window.location.href='/activity/ActivityList.action'">返回</button>
        </div>
    </div>

    </div> <!-- /container -->
<%@include file="/resources/footer-js.jsp" %>
<script language="JavaScript">
    $(function(){
        $.post("${ctxPath}/location/getLocationList.action",function(data){
            if(data && data.length>0){
                $.each(data,function(I,N){
                    if('${activity.location}' == N.locationID){
                        $('#alocation').text(N.locationName);
                        return false;
                    }
                });
            }
        },"json");

        $.post("${ctxPath}/tag/getTagList.action",function(data){
            if(data && data.length>0){
                $("#aTag").nextAll().remove();
                $.each(data,function(I,N){
                    if($("#acttagRefsstr").val().indexOf(","+ N.tagID+",")>-1){
                        $("#tagcb").append('<label class="btn btn-default active"><span>'+ N.tagName+'</span><span class="badge">&radic;</span></label>');
                    }else{
                        $("#tagcb").append('<label class="btn btn-default"><span>'+ N.tagName+'</span></label>');
                    }
                });
            }
        },"json");

        $('#editor').wysiwyg();
        $('#editor').attr('contenteditable','false');
    });
</script>
<script src="${jsPath}/bootstrap/holder.min.js"></script>
</body>
</html>