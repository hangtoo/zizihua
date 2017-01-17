<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ page isELIgnored="false" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>

<!-- bootstrap - css -->
<link href="<%=basePath%>/static/B-JUI/themes/css/bootstrap.css" rel="stylesheet">
<!-- core - css -->
<link href="<%=basePath%>/static/B-JUI/themes/css/style.css" rel="stylesheet">
<link href="<%=basePath%>/static/B-JUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet">
<link href="<%=basePath%>/static/B-JUI/themes/css/fontsize.css" id="bjui-link-theme" rel="stylesheet">
<!-- plug - css -->
<link href="<%=basePath%>/static/B-JUI/plugins/kindeditor_4.1.11/themes/default/default.css" rel="stylesheet">
<link href="<%=basePath%>/static/B-JUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
<link href="<%=basePath%>/static/B-JUI/plugins/nice-validator-1.0.7/jquery.validator.css" rel="stylesheet">
<link href="<%=basePath%>/static/B-JUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
<link href="<%=basePath%>/static/B-JUI/plugins/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=basePath%>/static/B-JUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">
<!-- Favicons -->
<link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-precomposed.png">
<link rel="shortcut icon" href="assets/ico/favicon.png">
<!--[if lte IE 7]>
<link href="<%=basePath%>/static/B-JUI/themes/css/ie7.css" rel="stylesheet">
<![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 9]>
    <script src="<%=basePath%>/static/B-JUI/other/html5shiv.min.js"></script>
    <script src="<%=basePath%>/static/B-JUI/other/respond.min.js"></script>
<![endif]-->
<!-- jquery -->
<script src="<%=basePath%>/static/B-JUI/js/jquery-1.11.3.js"></script>
<script src="<%=basePath%>/static/B-JUI/js/jquery.cookie.js"></script>
<!--[if lte IE 9]>
<script src="<%=basePath%>/static/B-JUI/other/jquery.iframe-transport.js"></script>
<![endif]-->
<!-- B-JUI -->
<script src="<%=basePath%>/static/B-JUI/js/bjui-all.min.js"></script>
<!-- plugins -->
<!-- swfupload for kindeditor -->
<script src="<%=basePath%>/static/B-JUI/plugins/swfupload/swfupload.js"></script>
<!-- Webuploader -->
<script src="<%=basePath%>/static/B-JUI/plugins/webuploader/webuploader.js"></script>
<!-- kindeditor -->
<script src="<%=basePath%>/static/B-JUI/plugins/kindeditor_4.1.11/kindeditor-all-min.js"></script>
<script src="<%=basePath%>/static/B-JUI/plugins/kindeditor_4.1.11/lang/zh-CN.js"></script>
<!-- colorpicker -->
<script src="<%=basePath%>/static/B-JUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- ztree -->
<script src="<%=basePath%>/static/B-JUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
<!-- nice validate -->
<script src="<%=basePath%>/static/B-JUI/plugins/nice-validator-1.0.7/jquery.validator.js"></script>
<script src="<%=basePath%>/static/B-JUI/plugins/nice-validator-1.0.7/jquery.validator.themes.js"></script>
<!-- bootstrap plugins -->
<script src="<%=basePath%>/static/B-JUI/plugins/bootstrap.min.js"></script>
<script src="<%=basePath%>/static/B-JUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
<script src="<%=basePath%>/static/B-JUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>
<!-- icheck -->
<script src="<%=basePath%>/static/B-JUI/plugins/icheck/icheck.min.js"></script>
<!-- HighCharts -->
<script src="<%=basePath%>/static/B-JUI/plugins/highcharts/highcharts.js"></script>
<script src="<%=basePath%>/static/B-JUI/plugins/highcharts/highcharts-3d.js"></script>
<script src="<%=basePath%>/static/B-JUI/plugins/highcharts/themes/gray.js"></script>
<!-- other plugins -->
<script src="<%=basePath%>/static/B-JUI/plugins/other/jquery.autosize.js"></script>
<link href="<%=basePath%>/static/B-JUI/plugins/uploadify/css/uploadify.css" rel="stylesheet">
<script src="<%=basePath%>/static/B-JUI/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
<script src="<%=basePath%>/static/B-JUI/plugins/download/jquery.fileDownload.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<%=basePath%>/static/B-JUI/other/ie10-viewport-bug-workaround.js"></script>
