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
			<label class="ui-label">创建时间:</label><input name="p_createtime" class="easyui-box ui-text" style="width:100px;">
			<label class="ui-label">修改时间:</label><input name="p_modifytime" class="easyui-box ui-text" style="width:100px;">
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
		     	   <div class="ftitle">黄金行情</div>
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
						<label>合约</label>
						<input name="p_name" type="text" maxlength="255" class="easyui-validatebox" data-options="" missingMessage="请填写合约">
					</div>
					<div class="fitem">
						<label>开盘价</label>
						<input name="p_opendata" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写开盘价">
					</div>
					<div class="fitem">
						<label>最高价</label>
						<input name="p_highdata" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写最高价">
					</div>
					<div class="fitem">
						<label>最低价</label>
						<input name="p_lowdata" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写最低价">
					</div>
					<div class="fitem">
						<label>收盘价</label>
						<input name="p_closedata" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写收盘价">
					</div>
					<div class="fitem">
						<label>涨跌（元）</label>
						<input name="p_add" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写涨跌（元）">
					</div>
					<div class="fitem">
						<label>涨跌幅</label>
						<input name="p_rate" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写涨跌幅">
					</div>
					<div class="fitem">
						<label>加权平均价</label>
						<input name="p_data" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写加权平均价">
					</div>
					<div class="fitem">
						<label>成交量</label>
						<input name="p_volume" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写成交量">
					</div>
					<div class="fitem">
						<label>成交金额</label>
						<input name="p_amount" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写成交金额">
					</div>
					<div class="fitem">
						<label>持仓量</label>
						<input name="p_openinterest" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写持仓量">
					</div>
					<div class="fitem">
						<label>交收量</label>
						<input name="p_settlement" type="text" maxlength="" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" missingMessage="请填写交收量">
					</div>
					<div class="fitem">
						<label>日期</label>
						<input name="p_date" type="text" maxlength="" class="easyui-datetimebox" data-options="" missingMessage="请填写日期">
					</div>
  			</div>
     	</form>
  	 </div>
  	 <script type="text/javascript" src="<%=basePath%>/view/hangtoo/gold/page-tGold.js"></script>
  </body>
</html>
