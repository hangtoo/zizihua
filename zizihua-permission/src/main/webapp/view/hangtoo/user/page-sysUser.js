$package('jeecg.sysUser');
jeecg.sysUser = function(){
	var _box = null;
	var _this = {
		config:{
			event:{
				add:function(){
					$('#typeIds_combobox').combobox('reload');
					_box.handler.add();
				},
				edit:function(){
					$('#typeIds_combobox').combobox('reload');
					_box.handler.edit();
				}
			},
  			dataGrid:{
  				title:'用户管理',
	   			url:'dataList.do',
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'email',title:'邮箱也是登录帐号',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.email;
							}
						},
					{field:'pwd',title:'登录密码',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.pwd;
							}
						},
					{field:'nickname',title:'昵称',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.nickname;
							}
						},
					{field:'state',title:'状态 0=可用,1=禁用',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.state;
							}
						},
					{field:'logincount',title:'登录总次数',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.logincount;
							}
						},
					{field:'logintime',title:'最后登录时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.logintime;
							}
						},
					{field:'deleted',title:'删除状态 0=未删除,1=已删除',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.deleted;
							}
						},
					{field:'createtime',title:'创建时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.createtime;
							}
						},
					{field:'updatetime',title:'修改时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.updatetime;
							}
						},
					{field:'createby',title:'创建人',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.createby;
							}
						},
					{field:'updateby',title:'修改人',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.updateby;
							}
						},
					{field:'superadmin',title:'是否超级管理员 0= 不是，1=是',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.superadmin;
							}
						},
					]]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	jeecg.sysUser.init();
});