$package('jeecg.tStock');
jeecg.tStock = function(){
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
  				title:'深证成指',
	   			url:'dataList.do',
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'p_createtime',title:'创建时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_createtime;
							}
						},
					{field:'p_modifytime',title:'修改时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_modifytime;
							}
						},
					{field:'p_creator',title:'创建人',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_creator;
							}
						},
					{field:'p_modifier',title:'修改人',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_modifier;
							}
						},
					{field:'p_remark',title:'备注',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_remark;
							}
						},
					{field:'p_deleted',title:'是否删除',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_deleted;
							}
						},
					{field:'p_name',title:'指数名称',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_name;
							}
						},
					{field:'p_data',title:'比上日增减',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_data;
							}
						},
					{field:'p_date',title:'日期',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_date;
							}
						},
					{field:'p_add',title:'比上日增减',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_add;
							}
						},
					{field:'p_rate',title:'幅度%',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_rate;
							}
						},
					{field:'p_highdata',title:'本年最高',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_highdata;
							}
						},
					{field:'p_highdate',title:'最高值日期',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_highdate;
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
	jeecg.tStock.init();
});