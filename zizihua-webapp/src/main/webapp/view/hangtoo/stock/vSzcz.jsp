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
			<label class="ui-label">日期:</label>
			<input id="p_date" name="p_date" type="text" maxlength="" class="easyui-datebox" 
			data-options="formatter:getDateTime"  missingMessage="日期" >
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
		     	   <div class="ftitle">深证成指</div>
					<div class="fitem">
						<label>创建时间</label>
						<input name="p_createtime" type="text" maxlength="" class="easyui-datetimebox" data-options="" missingMessage="请填写创建时间">
					</div>
					<div class="fitem">
						<label>修改时间</label>
						<input name="p_modifytime" type="text" maxlength="" class="easyui-datetimebox" data-options="" missingMessage="请填写修改时间">
					</div>
					<div class="fitem">
						<label>创建人</label>
						<input name="p_creator" type="text" maxlength="255" class="easyui-validatebox" data-options="" missingMessage="请填写创建人">
					</div>
					<div class="fitem">
						<label>修改人</label>
						<input name="p_modifier" type="text" maxlength="255" class="easyui-validatebox" data-options="" missingMessage="请填写修改人">
					</div>
					<div class="fitem">
						<label>备注</label>
						<input name="p_remark" type="text" maxlength="255" class="easyui-validatebox" data-options="" missingMessage="请填写备注">
					</div>
					<div class="fitem">
						<label>是否删除</label>
						<input name="p_deleted" type="text" maxlength="" class="easyui-validatebox" data-options="" missingMessage="请填写是否删除">
					</div>
					<div class="fitem">
						<label>指数名称</label>
						<input name="p_name" type="text" maxlength="255" class="easyui-validatebox" data-options="" missingMessage="请填写指数名称">
					</div>
					<div class="fitem">
						<label>数值</label>
						<input name="p_data" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写数值">
					</div>
					<div class="fitem">
						<label>日期</label>
						<input name="p_date" type="text" maxlength="" class="easyui-datetimebox" data-options="" missingMessage="请填写日期">
					</div>
					<div class="fitem">
						<label>比上日增减</label>
						<input name="p_add" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写比上日增减">
					</div>
					<div class="fitem">
						<label>幅度%</label>
						<input name="p_rate" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写幅度%">
					</div>
					<div class="fitem">
						<label>本年最高</label>
						<input name="p_highdata" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写本年最高">
					</div>
					<div class="fitem">
						<label>最高值日期</label>
						<input name="p_highdate" type="text" maxlength="" class="easyui-datetimebox" data-options="" missingMessage="请填写最高值日期">
					</div>
  			</div>
     	</form>
  	 </div>
  	 <script type="text/javascript" src="<%=basePath%>/view/hangtoo/stock/page-vSzcz.js"></script>
  </body>
</html>
