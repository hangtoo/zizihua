<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
  </head>
  <body class="easyui-layout">
 	 <!-- Search panel start -->
 	 <div class="ui-search-panel" region="north" style="height: 80px;" title="过滤条件" data-options="striped: true,collapsible:false,iconCls:'icon-search',border:false" >  
 	 <form id="searchForm">
        <p class="ui-fields">
			<label class="ui-label">邮箱也是登录帐号:</label><input name="email" class="easyui-box ui-text" style="width:100px;">
			<label class="ui-label">登录密码:</label><input name="pwd" class="easyui-box ui-text" style="width:100px;">
	    </p>
	    <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
      </form>  
     </div> 
     <!--  Search panel end -->

     <!-- Data List -->
     <div region="center" border="false" >
     <table id="data-list"></table>
	 </div>
	 
     <!-- Edit Win&Form -->
     <div id="edit-win" class="easyui-dialog" title="Basic window" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:380px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		 <div class="ui-edit">
		     	   <div class="ftitle">用户管理</div>
					<div class="fitem">
						<label>邮箱也是登录帐号</label>
						<input name="email" type="text" maxlength="50" class="easyui-validatebox" data-options="required:true" missingMessage="请填写邮箱也是登录帐号">
					</div>
					<div class="fitem">
						<label>登录密码</label>
						<input name="pwd" type="text" maxlength="50" class="easyui-validatebox" data-options="" missingMessage="请填写登录密码">
					</div>
					<div class="fitem">
						<label>昵称</label>
						<input name="nickname" type="text" maxlength="50" class="easyui-validatebox" data-options="" missingMessage="请填写昵称">
					</div>
					<div class="fitem">
						<label>状态 0=可用,1=禁用</label>
						<input name="state" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写状态 0=可用,1=禁用">
					</div>
					<div class="fitem">
						<label>登录总次数</label>
						<input name="logincount" type="text" maxlength="" class="easyui-numberbox" data-options="" missingMessage="请填写登录总次数">
					</div>
					<div class="fitem">
						<label>最后登录时间</label>
						<input name="logintime" type="text" maxlength="" class="easyui-datetimebox" data-options="" missingMessage="请填写最后登录时间">
					</div>
					<div class="fitem">
						<label>删除状态 0=未删除,1=已删除</label>
						<input name="deleted" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写删除状态 0=未删除,1=已删除">
					</div>
					<div class="fitem">
						<label>创建时间</label>
						<input name="createtime" type="text" maxlength="" class="easyui-datetimebox" data-options="" missingMessage="请填写创建时间">
					</div>
					<div class="fitem">
						<label>修改时间</label>
						<input name="updatetime" type="text" maxlength="" class="easyui-datetimebox" data-options="" missingMessage="请填写修改时间">
					</div>
					<div class="fitem">
						<label>创建人</label>
						<input name="createby" type="text" maxlength="" class="easyui-numberbox" data-options="" missingMessage="请填写创建人">
					</div>
					<div class="fitem">
						<label>修改人</label>
						<input name="updateby" type="text" maxlength="" class="easyui-numberbox" data-options="" missingMessage="请填写修改人">
					</div>
					<div class="fitem">
						<label>是否超级管理员 0= 不是，1=是</label>
						<input name="superadmin" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写是否超级管理员 0= 不是，1=是">
					</div>
  			</div>
     	</form>
  	 </div>
  	 <script type="text/javascript" src="<%=basePath%>/view/hangtoo/user/page-sysUser.js"></script>
  </body>
</html>
