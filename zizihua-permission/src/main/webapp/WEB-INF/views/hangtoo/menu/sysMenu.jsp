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
			<label class="ui-label">菜单名称:</label><input name="name" class="easyui-box ui-text" style="width:100px;">
			<label class="ui-label">系统url:</label><input name="url" class="easyui-box ui-text" style="width:100px;">
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
		     	   <div class="ftitle">菜单管理</div>
					<div class="fitem">
						<label>菜单名称</label>
						<input name="name" type="text" maxlength="50" class="easyui-validatebox" data-options="" missingMessage="请填写菜单名称">
					</div>
					<div class="fitem">
						<label>系统url</label>
						<input name="url" type="text" maxlength="100" class="easyui-validatebox" data-options="" missingMessage="请填写系统url">
					</div>
					<div class="fitem">
						<label> 父id 关联sys_menu.id</label>
						<input name="parentid" type="text" maxlength="" class="easyui-numberbox" data-options="" missingMessage="请填写 父id 关联sys_menu.id">
					</div>
					<div class="fitem">
						<label>是否删除,0=未删除，1=已删除</label>
						<input name="deleted" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写是否删除,0=未删除，1=已删除">
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
						<label>排序</label>
						<input name="rank" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写排序">
					</div>
					<div class="fitem">
						<label>注册Action 按钮|分隔</label>
						<input name="actions" type="text" maxlength="500" class="easyui-validatebox" data-options="" missingMessage="请填写注册Action 按钮|分隔">
					</div>
  			</div>
     	</form>
  	 </div>
  	 <script type="text/javascript" src="<%=basePath%>/view/hangtoo/menu/page-sysMenu.js"></script>
  </body>
</html>
