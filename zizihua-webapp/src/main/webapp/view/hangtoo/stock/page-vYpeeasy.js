$package('jeecg.vYpeeasy');
jeecg.vYpeeasy = function(){
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
  				title:'年股票平均市盈率',
	   			url:'dataList.do',
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'p_year',title:'年份',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_year;
							}
						},
					{field:'p_max',title:'最大值',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_max;
							}
						},
					{field:'p_min',title:'最小值',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_min;
							}
						},
					{field:'p_mindate',title:'最小日期',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_mindate;
							}
						},
					{field:'p_maxdate',title:'最大日期',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_maxdate;
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
	jeecg.vYpeeasy.init();
});