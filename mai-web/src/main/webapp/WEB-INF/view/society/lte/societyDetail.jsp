<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mai.util.CurrentUser" %>         
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.mai.society.entity.Society" %>
<%--<%@include file="/WEB-INF/view/admin/navbar.jsp" %>--%>
<%@include file="/resources/boot-header-lte.jsp" %>
<link href="${cssPath}/mybootstrap.css" rel="stylesheet" type="text/css" />

<c:set var="STATUS_RELEASED" value="<%=Society.STATUS_RELEASED%>" />
</head>
<body class="skin-blue">
<!-- header logo: style can be found in header.less -->
<header class="header">
    <a href="index.html" class="logo">
        <!-- Add the class icon to your logo image or logo icon to add the margining -->
        麦同学
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>
        <div class="navbar-right navbar-right-temp">
            <ul class="nav navbar-nav">
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="glyphicon glyphicon-user"></i>
                        <span><%=CurrentUser.getPersonName()%><i class="caret"></i></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header bg-light-blue">
                            <img src="/resources/images/default.png" class="img-circle" alt="User Image" />
                            <p>
                                <%=CurrentUser.getPersonName()%>(<%=CurrentUser.getRoleName()%>)
                                <!-- <small>Member since Nov. 2012</small> -->
                            </p>
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="#" class="btn btn-default btn-flat">修改密码</a>
                            </div>
                            <div class="pull-right">
                                <a href="#" class="btn btn-default btn-flat">退出</a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div class="wrapper row-offcanvas row-offcanvas-left">
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="left-side sidebar-offcanvas">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="/resources/images/default.png" class="img-circle" alt="User Image" />
                </div>
                <div class="pull-left info">
                    <p>Hello, <%=CurrentUser.getPersonName()%></p>

                </div>
            </div>
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="treeview active">
                    <a href="#">
                        <i class="fa fa-bar-chart-o"></i>
                        <span>社长</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu" id="navlist">
                    <!-- 菜单 -->
                    </ul>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Right side column. Contains the navbar and content of the page -->
    <aside class="right-side">
        <!-- Content Header (Page header) -->
        <!-- <section class="content-header">
            <h1>
                Blank page
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Blank page</li>
            </ol>
        </section> -->

        <!-- Main content -->
        <section class="content">
			
			<div class="col-md-12" >
                            <!-- general form elements disabled -->
                            <div class="box box-warning">
                                <div class="box-header">
                                    <h3 class="box-title">General Elements</h3>
                                </div><!-- /.box-header -->
                                <div class="box-body">
                                	<form method="post" action="${ctxPath}/society/updateSocietyDeatil.action" id="aform" name="aform" enctype="multipart/form-data">
			    <input type="hidden" id="societyID" name="societyID" value="${society.societyID}">
			    <input type="hidden" id="coverURL" name="coverURL" value="${society.coverURL}"/>
			    <input type="hidden" id="societyLOGO" name="societyLOGO" value="${society.societyLOGO}"/>
			    <input type="hidden" id="stagRefsstr" name="stagRefsstr" value="${stagRefsstr}"/>
			    <input type="hidden" id="recommendNo" name="recommendNo" value="${society.recommendNo}"/>
			    <input type="hidden" id="applyNote" name="applyNote" value="${society.applyNote}"/>
			    <input type="hidden" id="societyName" name="societyName" value="${society.societyName}">
			    <input id="schoolID" name="schoolID" type="hidden" value="${society.schoolID}"/>
			    <input id="schoolName" name="schoolName" type="hidden" value="${society.schoolName}"/>
			    <input id="phoneNum" name="phoneNum" type="hidden" value="${society.phoneNum}"/>
			    <input id="isLog" name="isLog" type="hidden" value="${society.isLog}"/>
			    <input id="level" name="level" type="hidden" value="${society.level}"/>
			    <input id="adminID" name="adminID" type="hidden" value="${society.adminID}"/>
			    <input id="adminName" name="adminName" type="hidden" value="${society.adminName}"/>
			    <div class="form-group">
			        <%-- <div class="input-group">
			            <span class="input-group-addon" id="scID">类别</span>
			            <div class="dropdown">
			                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			                    请选择类别...<span class="caret"></span>
			                </button>
			                <ul class="dropdown-menu" aria-labelledby="dropdownMenu2" id="ulddm2">
			                </ul>
			                <input id="societyCategoryID" name="societyCategoryID" type="hidden" value="${society.societyCategoryID}"/>
			                <input id="societyCategoryName" name="societyCategoryName" type="hidden" value="${society.societyCategoryName}"/>
			            </div>
			        </div> --%>
			         <input id="societyCategoryID" name="societyCategoryID" type="hidden" value="${society.societyCategoryID}"/>
			         <input id="societyCategoryName" name="societyCategoryName" type="hidden" value="${society.societyCategoryName}"/>
			         <div class="form-group">
                                            <label>类别</label>

                                            <select class="form-control">
												<c:forEach var="s" varStatus="ss" items="${clist }">
												
												<option value="${s.categoryID }" <c:if test="${society.societyCategoryID == s.categoryID}">selected="selected"</c:if>>${s.categoryName}</option>
												
												</c:forEach>

                                            </select>
                     </div>
			        <small style="display: none" class="help-block input-group" id="category_error"></small>
			    </div>
			
			    <%-- <div class="form-group">
			        <div class="input-group">
			            <span class="input-group-addon" id="smNum">社团最大人数</span>
			            <input type="text" class="form-control" name="memberNum" placeholder="社团最大人数" value="${society.memberNum}"
			                   aria-describedby="basic-addon1" required data-bv-notempty-message="请填写社团最大人数" min="1" data-bv-greaterthan-message="请输入大于0的整数">
			        </div>
			    </div> --%>
			      <div class="form-group">

                                            <label>社团最大人数</label>

                                            <input type="text" class="form-control" placeholder="社团最大人数"  value="${society.memberNum}">

                 </div>
			
			    <%-- <div class="form-group">
			        <div class="input-group">
			            <span class="input-group-addon" id="sjPNum">社团成员数</span>
			            <input type="text" class="form-control" id="joinPersonNum" name="joinPersonNum" placeholder="社团成员数" value="${society.joinPersonNum}"
			                   aria-describedby="basic-addon1" readonly>
			        </div>
			    </div> --%>
			    <div class="form-group">

                                            <label>社团成员数</label>

                                            <input type="text" class="form-control" placeholder="社团成员数"  value="${society.joinPersonNum}">

                 </div>
			
			   <%--  <div class="form-group">
			        <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1"  data-link-format="yyyy-mm-dd">
			            <span class="input-group-addon" id="aBTime">成立时间</span>
			            <input class="form-control" size="16" type="text" value="${society.buildDate}" name="_buildDate" id="_buildDate" required readonly data-bv-notempty-message="请填写成立时间">
			            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
			        </div>
			        <input type="hidden" id="dtp_input1" value="${society.buildDate}" name="buildDate"/>
			    </div>
			     --%>
			    <div class="form-group">

                                            <label>成立时间</label>
                                            
						                     <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1"  data-link-format="yyyy-mm-dd">
									            <input class="form-control" size="16" type="text" value="${society.buildDate}" name="_buildDate" id="_buildDate" required readonly data-bv-notempty-message="请填写成立时间">
									       		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
									        </div>
									        <input type="hidden" id="dtp_input1" value="${society.buildDate}" name="buildDate"/>

                 </div>
			
			    <%-- <div class="form-group">
			        <div class="input-group">
			            <span class="input-group-addon" id="slogon">口号</span>
			            <input type="text" class="form-control" name="slogan" placeholder="口号" value="${society.slogan}"
			                   aria-describedby="basic-addon1" required data-bv-notempty-message="请填写社团口号">
			        </div>
			    </div> --%>
			    
			    <div class="form-group">

                                            <label>口号</label>

                                            <input type="text" class="form-control" placeholder="口号"  value="${society.slogan}">

                 </div>
			
			    <%-- <div class="form-group">
			        <div class="input-group">
			            <span class="input-group-addon" id="spnum">点赞数</span>
			            <input type="text" class="form-control" name="praiseNum" placeholder="点赞数" value="${society.praiseNum}"
			                   aria-describedby="basic-addon1" readonly>
			        </div>
			    </div> --%>
			    
			    <div class="form-group">

                                            <label>点赞数</label>

                                            <input type="text" class="form-control" placeholder="点赞数"  value="${society.praiseNum}">

                 </div>
			
			    <%-- <div class="form-group">
			        <div class="input-group">
			            <span class="input-group-addon" id="alocation">区域</span>
			            <div class="dropdown">
			                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			                    请选择区域...<span class="caret"></span>
			                </button>
			                <ul class="dropdown-menu" aria-labelledby="dropdownMenu3" id="ulddm3">
			                </ul>
			                <input id="locationID" name="locationID" type="hidden" value="${society.locationID}"/>
			            </div>
			        </div>
			        <small style="display: none" class="help-block input-group" id="location_error"></small>
			    </div> --%>
			    <input id="locationID" name="locationID" type="hidden" value="${society.locationID}"/>
			     <div class="form-group">
                                            <label>区域</label>

                                            <select class="form-control">
												<c:forEach var="s" varStatus="ss" items="${lolist }">
												
												<option value="${s.locationID }" <c:if test="${society.locationID == s.locationID}">selected="selected"</c:if>>${s.locationName}</option>
												
												</c:forEach>

                                            </select>
                     </div>
			
			
			    <%-- <div class="form-group">
			        <div class="input-group">
			            <span class="input-group-addon" id="sld">具体地址</span>
			            <input type="text" class="form-control" id="slocationDetail" name="locationDetail" placeholder="具体地址" value="${society.locationDetail}"
			                   aria-describedby="basic-addon1" required data-bv-notempty-message="请填写具体地址">
			        </div>
			    </div> --%>
			    
			    <div class="form-group">

                                            <label>具体地址</label>

                                            <input type="text" class="form-control" placeholder="具体地址"  value="${society.locationDetail}">

                 </div>
			
			
			 	<div class="form-group">
			 	        <label>地图选点</label>
					 	<div>
					            <button data-toggle="modal" data-target="#myModal" id="createMap" type="button"    >
					                <img src="http://api.map.baidu.com/staticimage?center=${gps_map_static_str}&markers=${gps_map_static_str}&zoom=15" id="show_map_img"/>
					            </button>
					            <small style="display: none" class="help-block input-group" id="gps_error"></small>
					    </div>
			 	</div>       
			 	        <input type="hidden" name="gpsLongitude" id="gpsLongitude" value="${society.gpsLongitude}" />
			            <input type="hidden" name="gpsLatitude" id="gpsLatitude" value="${society.gpsLatitude}" />
			    <%-- <div class="form-group">
			        <div class="input-group">
			            <span class="input-group-addon">地图选点</span>
			            <input type="hidden" name="gpsLongitude" id="gpsLongitude" value="${society.gpsLongitude}" />
			            <input type="hidden" name="gpsLatitude" id="gpsLatitude" value="${society.gpsLatitude}" />
			            <button data-toggle="modal" data-target="#myModal" id="createMap" type="button">
			                <img src="http://api.map.baidu.com/staticimage?center=${gps_map_static_str}&markers=${gps_map_static_str}&zoom=15" id="show_map_img"/>
			            </button>
			        </div>
			        <small style="display: none" class="help-block input-group" id="gps_error"></small>
			    </div> --%>
			
			    <!-- <div class="form-group">
			        <div class="input-group" data-toggle="buttons" id="tagcb">
			            <span class="input-group-addon" id="aTag">热门标签</span>
			
			        </div>
			        <small style="display: none" class="help-block input-group" id="tagids_error"></small>
			    </div> -->
			    
			    <div class="form-group">
			 	        <label>热门标签</label>
			 	        
			 	         <div class="input-group" data-toggle="buttons" id="tagcb">
			            	<!-- <span class="input-group-addon" id="aTag">热门标签</span> -->
			
			        	 </div>
			        <small style="display: none" class="help-block input-group" id="tagids_error"></small>
			 	</div>   
			
			
			   <%--  <div class="form-group">
			        <div class="input-group">
			            <span class="input-group-addon" id="scurl">封面图片</span>
			            <c:choose>
			                <c:when test="${society.coverURL != null && society.coverURL != ''}">
			                        <a href="${society.coverURL}" target="_blank" class="thumbnail col-lg-2">
			                            <img src="${society.coverURL}"/>
			                        </a>
			                    <input type="file" class="form-control" id="file_obj1" placeholder="封面图片" name="Filedatas"
			                           aria-describedby="basic-addon1">
			                </c:when>
			                <c:otherwise>
			                    <input type="file" class="form-control" id="file_obj1" placeholder="封面图片" name="Filedatas"
			                           aria-describedby="basic-addon1" required data-bv-notempty-message="请上传封面图片">
			                </c:otherwise>
			            </c:choose>
			        </div>
			    </div> --%>
			    
			    
			    <div class="form-group">
			        <div class="input-group">
			            <label>封面图片</label>
			            <c:choose>
			                <c:when test="${society.coverURL != null && society.coverURL != ''}">
			                        <a href="${society.coverURL}" target="_blank" class="thumbnail col-lg-2">
			                            <img src="${society.coverURL}"/>
			                        </a>
			                    <input type="file" class="form-control" id="file_obj1" placeholder="封面图片" name="Filedatas"
			                           aria-describedby="basic-addon1">
			                </c:when>
			                <c:otherwise>
			                    <input type="file" class="form-control" id="file_obj1" placeholder="封面图片" name="Filedatas"
			                           aria-describedby="basic-addon1" required data-bv-notempty-message="请上传封面图片">
			                </c:otherwise>
			            </c:choose>
			        </div>
			    </div>

			<%-- <div class="form-group">
			        <div class="input-group">
			            <span class="input-group-addon" id="scurl">社团LOGO</span>
			            <c:choose>
			                <c:when test="${society.societyLOGO != null && society.societyLOGO != ''}">
			                    <a href="${society.societyLOGO}" target="_blank" class="thumbnail col-lg-2">
			                        <img src="${society.societyLOGO}"/>
			                    </a>
			                    <input type="file" class="form-control" id="file_obj2" placeholder="社团LOGO" name="Filedatas"
			                           aria-describedby="basic-addon1">
			                </c:when>
			                <c:otherwise>
			                    <input type="file" class="form-control" id="file_obj2" placeholder="社团LOGO" name="Filedatas"
			                           aria-describedby="basic-addon1" required data-bv-notempty-message="请上传社团LOGO">
			                </c:otherwise>
			            </c:choose>
			        </div>
			    </div> --%>
                <div class="form-group">
			 	        <label>社团LOGO</label>
			 	        
			 	         <c:choose>
			                <c:when test="${society.societyLOGO != null && society.societyLOGO != ''}">
			                    <a href="${society.societyLOGO}" target="_blank" class="thumbnail col-lg-2">
			                        <img src="${society.societyLOGO}"/>
			                    </a>
			                    <input type="file" class="form-control" id="file_obj2" placeholder="社团LOGO" name="Filedatas"
			                           aria-describedby="basic-addon1">
			                </c:when>
			                <c:otherwise>
			                    <input type="file" class="form-control" id="file_obj2" placeholder="社团LOGO" name="Filedatas"
			                           aria-describedby="basic-addon1" required data-bv-notempty-message="请上传社团LOGO">
			                </c:otherwise>
			            </c:choose>
			        <small style="display: none" class="help-block input-group" id="tagids_error"></small>
			 	</div>   
			    
			
			    <%-- <div class="form-group">
			        <div class="input-group">
			            <span class="input-group-addon" id="sNotes">社团介绍</span>
			        <textarea class="form-control" placeholder="社团介绍"
			                  rows="3" name="societyNote" required data-bv-notempty-message="请填写社团介绍">${society.societyNote}</textarea>
			        </div>
			    </div> --%>
			    
			     <div class="form-group">
			            <label>社团介绍</label>
			        <textarea class="form-control" placeholder="社团介绍"
			                  rows="3" name="societyNote" required data-bv-notempty-message="请填写社团介绍">${society.societyNote}</textarea>
			    </div>
			
			
			    <div class="btn-group" role="group" aria-label="...">
			        <c:choose>
			            <c:when test="${society.status == STATUS_RELEASED}">
			                <button type="submit" id="mysubmit" class="btn btn-default" onclick="subActForm();">保存修改</button>
			                <input type="hidden" id="status" name="status" value="${society.status}"/>
			            </c:when>
			            <c:otherwise>
			                <button type="submit" id="mysubmit" class="btn btn-default" onclick="subActForm();">发布社团</button>
			                <input type="hidden" id="status" name="status" value="${STATUS_RELEASED}"/>
			            </c:otherwise>
			        </c:choose>
			        <button type="reset" class="btn btn-default">取消</button>
			    </div>
			
			</form>
                                
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
						
			<!-- 模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			     aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close"
			                        data-dismiss="modal" aria-hidden="true">
			                    &times;
			                </button>
			                <h4 class="modal-title" id="myModalLabel">
			                    选择社团地点
			                </h4>
			                <div class="input-group">
			                    <input type="text" class="form-control col-lg-12" placeholder="Search for..." id="suggestId">
			                        <span class="input-group-btn">
			                          <button id="mysearch" class="btn btn-default" type="button" style="height: 34px;">搜索</button>
			                        </span>
			                </div>
			                <span class="input-group" style="margin: auto; display:none;color:red;" id="select_map_error"></span>
			            </div>
			            <div class="modal-body" id="maps" style="height:400px;">
			                地图加载中...
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default"
			                        data-dismiss="modal">关闭
			                </button>
			                <button type="button" class="btn btn-primary" id="save_map">
			                    提交更改
			                </button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal -->
			</div>
			
			<div class="modal fade" id="myPresidentModal" tabindex="-1" role="dialog"
			     aria-labelledby="myPresidentModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close"
			                        data-dismiss="modal" aria-hidden="true">
			                    &times;
			                </button>
			                <h4 class="modal-title" id="myPresidentModalLabel">
			                    社长换届
			                </h4>
			            </div>
			            <div class="modal-body" id="myPresidentModalBody">
			                <p class="text-danger text-center">修改新社长成功后，当前社长账号会强制退出！！！</p>
			                <div class="container">
			                    <form class="form-horizontal" action="${ctxPath}/nav/updatePassW.action" role="form" id="pass_form">
			                        <div class="form-group">
			                            <label class="col-sm-2 control-label">社长</label>
			                            <div class="col-sm-10">
			                                <input type="text" class="form-control" style="width:250px;" value="<%=CurrentUser.getPersonName()%>" placeholder="Old President Name" readonly>
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-2 control-label">社长电话</label>
			                            <div class="col-sm-10">
			                                <input type="text" class="form-control" style="width:250px;" value="<%=CurrentUser.getPersonPhoneNum()%>" placeholder="Old President PhoneNum" readonly>
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label for="newPresidentPNum" class="col-sm-2 control-label">新社长电话</label>
			                            <div class="col-sm-10">
			                                <input type="text" class="form-control" style="width:250px;" id="newPresidentPNum" placeholder="New President PhoneNum">
			                                <span id="newPresidentPNumTip" style="display:none;color:red;"></span>
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label for="newPresidentName" class="col-sm-2 control-label">新社长姓名</label>
			                            <div class="col-sm-10">
			                                <input type="text" class="form-control" style="width:250px;" id="newPresidentName" placeholder="New President Name" readonly>
			                            </div>
			                        </div>
			                    </form>
			                </div>
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default"
			                        data-dismiss="modal">关闭
			                </button>
			                <button type="button" class="btn btn-primary" id="myPresidentModalBtn">
			                    确认换届
			                </button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal -->
			</div>
        </section><!-- /.content -->
    </aside><!-- /.right-side -->
</div><!-- ./wrapper -->

<%@include file="/resources/footer-js-lte.jsp" %>
<script language="JavaScript">
    var p = null;
    var map = null;
    var president_error = false;
    var local="北京市";
    var check_error = false;

    $(function(){
        $("#aform").bootstrapValidator({fields: {
            slogan: {
                validators: {
                    stringLength: {
                        max: 50,
                        message: '口号不能超过50个汉字'
                    }
                }
            },
            Filedatas: {
                validators: {
                    file: {
                        extension: 'jpg,png,gif',
                        type: 'image/jpeg,image/png,image/gif,',
                        maxSize: 2*1024*1024,
                        message: '请上传“jpg,png,gif”格式的小于2M的文件.'
                    }
                }
            },
            _buildDate:{
                validators:{
                    callback: {
                        message: '成立日期不能大于当前日期',
                        callback: function(value, validator){
                            if(value == ''){
                                return true;
                            }
                            var myDate = new Date();
                            var nowDate = myDate.getFullYear() + "/" + (myDate.getMonth()+1) +"/" + myDate.getDate();
                            if(new Date(nowDate) < new Date(value.replace(/-/g, "/"))){
                                return false;
                            }else{
                                validator.updateStatus('_buildDate', 'VALID');
                                return true;
                            }
                        }
                    }
                }
            }
        }}).on('success.form.bv', function(e) {
            if(check_error){
                check_error = false;
                e.preventDefault();
                $(e.target).data('bootstrapValidator').disableSubmitButtons(false);
            }
        });

        $('.form_datetime').datetimepicker({
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            minView: 2,
            language:'zh-CN'
        }).on('changeDate show', function(e) {
            $('#aform').data('bootstrapValidator').updateStatus('_buildDate', 'NOT_VALIDATED', null).validateField('_buildDate');
        });

        $("#createMap").click(function(){
            setTimeout(function() {//添加延时加载。解决问题
                if(map == null) {
                    map = new BMap.Map("maps", {enableMapClick: false});
                }
                var _plng = $("#gpsLongitude").attr('value');
                var _plat = $("#gpsLatitude").attr('value');
                if(_plng == "" || _plat == ""){
                    var _address = $.trim($("#slocationDetail").val());
                    if(_address == ""){
                        var myCity = new BMap.LocalCity();
                        myCity.get(function(res){
                            map.centerAndZoom(res.center,16);
                            var marker = new BMap.Marker(res.center,{enableDragging:true});// 创建标注
                            map.addOverlay(marker);
                            marker.addEventListener("dragend",function(e){
                                p = e.point;//p 就是marker当前的坐标;
                            });
                        });
                    }else{
                        $("#select_map_error").hide();
                        $("#select_map_error").text("");
                        $("#suggestId").val(_address);
                        var myGeo = new BMap.Geocoder(); // 创建地址解析器实例
                        myGeo.getPoint(_address, function(point){
                            if (point) {                    //如果地址能解析，标记
                                map.centerAndZoom(point, 18);
                                map.clearOverlays();    //清除地图上所有覆盖物
                                var marker=new BMap.Marker(point,{enableDragging:true});
                                map.addOverlay(marker);
                                p = point;
//                                var addComp = point.addressComponents;
                                marker.addEventListener("dragend",function(e){
                                    p = e.point;//p 就是marker当前的坐标;
                                });
                            }else{
                                var ls = new BMap.LocalSearch(local);
                                ls.search(_address);
                                ls.setSearchCompleteCallback(function(rs){
                                    if (ls.getStatus() == BMAP_STATUS_SUCCESS){
                                        var poi = rs.getPoi(0); //取第1个查询结果
                                        if(poi){
                                            var pt2 = poi.point;
                                            map.centerAndZoom(pt2, 18);
                                            map.clearOverlays();    //清除地图上所有覆盖物
                                            var newmarker=new BMap.Marker(pt2);
                                            map.addOverlay(newmarker);
                                            newmarker.enableDragging();//开启拖动
                                            p = pt2;
//                                        var addComp = point.addressComponents
                                            newmarker.addEventListener("dragend",function(e){
                                                p = e.point;//p 就是marker当前的坐标;
                                            });
                                        }else{
                                            $("#select_map_error").text("地址无法找到");
                                            $("#select_map_error").show();
                                            return false;
                                        }
                                    }else{
                                        $("#select_map_error").text("地址无法找到");
                                        $("#select_map_error").show();
                                        return false;
                                    }
                                });
                            }
                        },local);
                    }

                }else{
                    var _point = new BMap.Point(_plng, _plat);
                    map.centerAndZoom(_point,16);
                    var marker = new BMap.Marker(_point,{enableDragging:true});// 创建标注
                    map.addOverlay(marker);
                    marker.addEventListener("dragend",function(e){
                        p = e.point;//p 就是marker当前的坐标;
                    });
                }

                var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
                var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
                var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL});
                map.addControl(top_left_control);
                map.addControl(top_left_navigation);
                map.addControl(top_right_navigation);

                $("#mysearch").click(function(){
                    $("#select_map_error").hide();
                    $("#select_map_error").text("");
                    var address = $("#suggestId").val();
                    var myGeo = new BMap.Geocoder(); // 创建地址解析器实例
                    myGeo.getPoint(address, function(point){
                        if (point) {                    //如果地址能解析，标记
                            map.centerAndZoom(point, 18);
                            map.clearOverlays();    //清除地图上所有覆盖物
                            var newmarker=new BMap.Marker(point);
                            map.addOverlay(newmarker);
                            newmarker.enableDragging();//开启拖动
                            p = point;
                            var addComp = point.addressComponents
                            newmarker.addEventListener("dragend",function(e){
                                p = e.point;//p 就是marker当前的坐标;
                            });
                        }else{
                            var ls = new BMap.LocalSearch(local);
                            ls.search(address);
                            ls.setSearchCompleteCallback(function(rs){
                                if (ls.getStatus() == BMAP_STATUS_SUCCESS){
                                    var poi = rs.getPoi(0); //取第1个查询结果
                                    if(poi){
                                        var pt2 = poi.point;
                                        map.centerAndZoom(pt2, 18);
                                        map.clearOverlays();    //清除地图上所有覆盖物
                                        var newmarker=new BMap.Marker(pt2);
                                        map.addOverlay(newmarker);
                                        newmarker.enableDragging();//开启拖动
                                        p = pt2;
//                                        var addComp = point.addressComponents
                                        newmarker.addEventListener("dragend",function(e){
                                            p = e.point;//p 就是marker当前的坐标;
                                        });
                                    }else{
                                        $("#select_map_error").text("地址无法找到");
                                        $("#select_map_error").show();
                                        return false;
                                    }
                                }else{
                                    $("#select_map_error").text("地址无法找到");
                                    $("#select_map_error").show();
                                    return false;
                                }
                            });
                        }
                    },local);
                });
            },300);
        });

        $.post("${ctxPath}/school/getSchoolList.action",function(data){
            if(data && data.length>0){
                $("#ulddm1").empty();
                $.each(data,function(I,N){
                    if($('#schoolID').val() == N.schoolID){
                        $("#ulddm1").prev().val($('#schoolID').val());
                        $("#ulddm1").prev().text($('#schoolName').val());
                        $("#ulddm1").prev().append("<span class='caret'>");
                    }
                    $("#ulddm1").append('<li><a title="'+N.schoolID+'" href="JavaScript:void(0);">'+N.schoolName+'</a></li>');
                });
                $("#ulddm1 li a").bind("click", function () {
                    var $this = $(this);
                    var $a = $this.parent().parent().prev();
                    var t = $this.text();
                    $a.val($this.attr('title'));
                    $('#schoolID').val($this.attr('title'));
                    $('#schoolName').val($this.text());
                    $a.text($this.text());
                    $a.append("<span class='caret'>");
                });
            }
        },"json");

        $.post("${ctxPath}/scategory/getSCategoryList.action",function(data){
            if(data && data.length>0){
                $("#ulddm2").empty();
                $.each(data,function(I,N){
                    if($('#societyCategoryID').val() == N.categoryID){
                        $("#ulddm2").prev().val($('#societyCategoryID').val());
                        $("#ulddm2").prev().text($('#societyCategoryName').val());
                        $("#ulddm2").prev().append("<span class='caret'>");
                    }
                    $("#ulddm2").append('<li><a title="'+N.categoryID+'" href="JavaScript:void(0);">'+N.categoryName+'</a></li>');
                });
                $("#ulddm2 li a").bind("click", function () {
                    var $this = $(this);
                    var $a = $this.parent().parent().prev();
                    var t = $this.text();
                    $a.val($this.attr('title'));
                    $('#societyCategoryID').val($this.attr('title'));
                    $('#societyCategoryName').val($this.text());
                    $a.text($this.text());
                    $a.append("<span class='caret'>");
                });
            }
        },"json");

        $.post("${ctxPath}/location/getLocationList.action",function(data){
            if(data && data.length>0){
                $("#ulddm3").empty();
                $.each(data,function(I,N){
                    if($('#locationID').val() == N.locationID){
                        $("#ulddm3").prev().val($('#locationID').val());
                        $("#ulddm3").prev().text(N.locationName);
                        $("#ulddm3").prev().append("<span class='caret'>");
                    }
                    $("#ulddm3").append('<li><a title="'+N.locationID+'" href="JavaScript:void(0);">'+N.locationName+'</a></li>');
                });
                $("#ulddm3 li a").bind("click", function () {
                    var $this = $(this);
                    var $a = $this.parent().parent().prev();
                    var t = $this.text();
                    $a.val($this.attr('title'));
                    $('#locationID').val($this.attr('title'));
                    $a.text($this.text());
                    $a.append("<span class='caret'>");
                });
            }
        },"json");

        $.post("${ctxPath}/stag/getSTagList.action",function(data){
            if(data && data.length>0){
                $("#aTag").nextAll().remove();
                $.each(data,function(I,N){
                    if($("#stagRefsstr").val().indexOf(","+ N.tagID+",")>-1){
                        $("#tagcb").append('<label class="btn btn-default active" onClick="checkboxView(this);"><input type="checkbox" name="tagids" id="tag_id_'+ N.tagID+'" autocomplete="off" value="'+ N.tagID+'" checked><span>'+ N.tagName+'</span><span class="badge">&radic;</span></label>');
                    }else{
                        $("#tagcb").append('<label class="btn btn-default" onClick="checkboxView(this);"><input type="checkbox" name="tagids" id="tag_id_'+ N.tagID+'" autocomplete="off" value="'+ N.tagID+'"><span>'+ N.tagName+'</span></label>');
                    }
                });
            }
        },"json");

        $("#save_map").click(function(){
            if(p){
                $("#gpsLongitude").attr('value',p.lng);
                $("#gpsLatitude").attr('value',p.lat);
                $("#show_map_img").attr('src','http://api.map.baidu.com/staticimage?zoom=15&center='+p.lng+","+p.lat+'&markers='+p.lng+","+p.lat);
                $("#myModal").modal('hide');
            }
        });







        $("#myPresidentModalBtn").click(function(){
            $("#newPresidentPNum").blur();
            if(!pass_error) {
                var newPresidentPNum = $("#newPresidentPNum").val();
                var societyID = $("#societyID").val();
                $.post('${ctxPath}/society/updatePresident.action', {newPresidentPNum:newPresidentPNum,sid:societyID}, function(data) {
                    if(data){
                        window.location.href = "${ctxPath}/logOut.action";
                    }else{
                        showError('newPresidentPNum', '变更社长失败');
                        pass_error = true;
                    }
                });
            }
            return false;

        });

        $("#newPresidentPNum").blur(function(){
            var newPresidentPNum = $("#newPresidentPNum").val();
            if($.trim(newPresidentPNum) == '') {
                showError('newPresidentPNum', '新社长电话不能为空');
                president_error = true;
                return;
            }
            $.post("${ctxPath}/user/checkUserPhoneNum.action", {phoneNum:newPresidentPNum}, function(data){
                if(data) {
                    if(data.errorcode == 0){
                        $("#newPresidentName").val(data.user.personName);
                        $("#newPresidentPNum").css({"border-color":"green"});
                        $("#newPresidentPNumTip").css({"display":"none"});
                    }else if(data.errorcode == 1){
                        showError('newPresidentPNum', '无此电话，请重新输入');
                        pass_error = true;
                    }else if(data.errorcode == 2){
                        showError('newPresidentPNum', '此电话为其他社团社长，请重新输入');
                        pass_error = true;
                    }else if(data.errorcode == 3){
                        showError('newPresidentPNum', '此电话为后台管理员，请重新输入');
                        pass_error = true;
                    }
                    else{
                        showError('newPresidentPNum', '未知错误，请联系系统管理员');
                        pass_error = true;
                    }
                } else {
                    showError('newPresidentPNum', '未知错误，请联系管理员');
                    pass_error = true;
                }
            });
        });
    });

    var fileInputAdapt=function(file_obj){//style="padding-top: 0px; border: none;box-shadow:none"
        if(/firefox/.test(navigator.userAgent.toLowerCase())){//Firefox
            file_obj.css({"border":"none","boxShadow":"none","paddingTop":"0px"});
        }
    };
    function checkboxView(event){
        setTimeout(function(){
            var _obj = $(event).children(":first-child");
            if(_obj.is(":checked")){
                _obj.next().after('<span class="badge">&radic;</span>');
            }else{
                _obj.siblings().remove(".badge");
            }
        },0);

    }
    window.onload=function(){
        fileInputAdapt($("#file_obj1"));
        fileInputAdapt($("#file_obj2"));
    }

    function subActForm(){

        if($.trim($("#societyCategoryID").val()) == ''){
            $("#category_error").text("请选择类型");
            $("#category_error").show();
            check_error = true;
            $("#category_error").parent().removeClass('has-success').addClass('has-error');
        }else{
            $("#category_error").text("");
            $("#category_error").hide();
            $("#category_error").parent().removeClass('has-error').addClass('has-success');
        }

        if($.trim($("#locationID").val()) == ''){
            $("#location_error").text("请选择区域");
            $("#location_error").show();
            check_error = true;
            $("#location_error").parent().removeClass('has-success').addClass('has-error');
        }else{
            $("#location_error").text("");
            $("#location_error").hide();
            $("#location_error").parent().removeClass('has-error').addClass('has-success');
        }

        if(!$("input[name='tagids']").is(':checked')){
            $("#tagids_error").text("请选择标签");
            $("#tagids_error").show();
            check_error = true;
            $("#tagids_error").parent().removeClass('has-success').addClass('has-error');
        }else if($("input[name='tagids']:checked").length > 5){
            $("#tagids_error").text("标签最多选择5个");
            $("#tagids_error").show();
            check_error = true;
            $("#tagids_error").parent().removeClass('has-success').addClass('has-error');
        }else{
            $("#tagids_error").text("");
            $("#tagids_error").hide();
            $("#tagids_error").parent().removeClass('has-error').addClass('has-success');
        }



        if($.trim($("#gpsLongitude").val()) == '' || $.trim($("#gpsLatitude").val()) == ''){
            $("#gps_error").text("请创建地图位置");
            $("#gps_error").show();
            check_error = true;
            $("#gps_error").parent().removeClass('has-success').addClass('has-error');
        }else{
            $("#gps_error").text("");
            $("#gps_error").hide();
            $("#gps_error").parent().removeClass('has-error').addClass('has-success');
        }

    }
</script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=401bd088908399c67499f012745d5ba9"></script>
</body>
</html>