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
			<label class="ui-label">:</label><input name="p_year" class="easyui-box ui-text" style="width:100px;">
			<label class="ui-label">数值:</label><input name="p_max" class="easyui-box ui-text" style="width:100px;">
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
		     	   <div class="ftitle">年股票平均市盈率</div>
					<div class="fitem">
						<label></label>
						<input name="p_year" type="text" maxlength="4" class="easyui-validatebox" data-options="" missingMessage="请填写">
					</div>
					<div class="fitem">
						<label>数值</label>
						<input name="p_max" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写数值">
					</div>
					<div class="fitem">
						<label>数值</label>
						<input name="p_min" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写数值">
					</div>
					<div class="fitem">
						<label>日期</label>
						<input name="p_mindate" type="text" maxlength="" class="easyui-datetimebox" data-options="" missingMessage="请填写日期">
					</div>
					<div class="fitem">
						<label>日期</label>
						<input name="p_maxdate" type="text" maxlength="" class="easyui-datetimebox" data-options="" missingMessage="请填写日期">
					</div>
  			</div>
     	</form>
  	 </div>
  	 <script type="text/javascript" src="<%=basePath%>/view/hangtoo/stock/page-vYpeeasy.js"></script>
  </body>
</html>
