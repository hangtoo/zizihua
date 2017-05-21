<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.hangtoo.auth.util.AuthUtils"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	boolean isadmin=AuthUtils.isAdmin(request);
	Integer userId=AuthUtils.getUserId(request);
	String Jsonapitype="[{'id':'0','text':'api'},{'id':'1','text':'pos'},{'id':'2','text':'cfn'},{'id':'3','text':'status'}]";
	String Jsonapitypeall="[{'id':'','text':'全部'},{'id':'0','text':'api'},{'id':'1','text':'pos'},{'id':'2','text':'cfn'},{'id':'3','text':'status'}]";
%>

<!-- 公共资源CSS,JS  -->
<!--Css -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/jquery-easyui-1.3.2/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/base.css">
<!-- ** Javascript ** -->
<script type="text/javascript" src="<%=basePath%>/js/commons/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/commons/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/commons/package.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/commons/util.js?v=11"></script>
<script type="text/javascript" src="<%=basePath%>/js/commons/urls.js?v=11"></script>
<script type="text/javascript" src="<%=basePath%>/js/commons/base.js?v=11"></script>
<script type="text/javascript" src="<%=basePath%>/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
jeecg.common = {
	Jsonapitype: <%=Jsonapitype%>
}
</script>