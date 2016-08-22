$package('jeecg.tGold');
jeecg.tGold = function(){
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
  				title:'黄金行情',
	   			url:'dataList.do',
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'p_name',title:'合约',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_name;
							}
						},
					{field:'p_opendata',title:'开盘价',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_opendata;
							}
						},
					{field:'p_highdata',title:'最高价',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_highdata;
							}
						},
					{field:'p_lowdata',title:'最低价',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_lowdata;
							}
						},
					{field:'p_closedata',title:'收盘价',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_closedata;
							}
						},
					{field:'p_add',title:'涨跌（元）',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_add;
							}
						},
					{field:'p_rate',title:'涨跌幅',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_rate;
							}
						},
					{field:'p_data',title:'加权平均价',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_data;
							}
						},
					{field:'p_volume',title:'成交量',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_volume;
							}
						},
					{field:'p_amount',title:'成交金额',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_amount;
							}
						},
					{field:'p_openinterest',title:'持仓量',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_openinterest;
							}
						},
					{field:'p_settlement',title:'交收量',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_settlement;
							}
						},
					{field:'p_date',title:'日期',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.p_date;
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
	jeecg.tGold.init();
});