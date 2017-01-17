$package('jeecg.sysMenu');
jeecg.sysMenu = function(){
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
  				title:'菜单管理',
	   			url:'dataList.do',
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'name',title:'菜单名称',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.name;
							}
						},
					{field:'url',title:'系统url',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.url;
							}
						},
					{field:'parentid',title:' 父id 关联sys_menu.id',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.parentid;
							}
						},
					{field:'deleted',title:'是否删除,0=未删除，1=已删除',align:'center',sortable:true,
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
					{field:'rank',title:'排序',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.rank;
							}
						},
					{field:'actions',title:'注册Action 按钮|分隔',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.actions;
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
	jeecg.sysMenu.init();
});