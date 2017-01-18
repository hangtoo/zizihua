<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table id="j_datagrid_tree" data-toggle="datagrid" data-options="{
    width: '100%',
    height: '100%',
    gridTitle: '树状datagrid 示例 ',
    showToolbar: true,
    toolbarItem:'add, cancel, del, save',
    local: 'local',
    dataUrl: '/sysMenu/dataTree.do?rows=100',
    inlineEditMult: false,
    editUrl: '/sysMenu/save.do',
    delUrl: '/sysMenu/delete.do',
    isTree: 'name',
    addLocation: 'last',
    fieldSortable: false,
    columnMenu: false,
    paging: false,
    editMode:'dialog',//dialog
    doSaveEditRow:function(row){
    	console.log('%o',row);
    },
    treeOptions: {
        expandAll: false,
        add: false,
        simpleData: true,
        keys: {
        	key       : 'id',        // id
            parentKey: 'parentid',
            childKey  : 'children',  // 子列表数据key值
        }
    },
    dropOptions: {
        drop: false
    }
}">
    <thead>
        <tr>
            <th data-options="{name:'name', align:'center', width:300, rule:'required'}">菜单名称</th>
            <th data-options="{name:'url', align:'center', width:300, rule:'required'}">链接</th>
            <th data-options="{name:'rank', align:'center', width:300, type:'hidden'}">排序</th>
            <th data-options="{name:'createtime',align:'center',type:'date',pattern:'yyyy-MM-dd HH:mm:ss'}">创建时间</th>
            <th data-options="{name:'updatetime',align:'center',type:'date',pattern:'yyyy-MM-dd HH:mm:ss'}">修改时间</th>
            <th data-options="{render:datagrid_tree_operation}">操作列</th>
        </tr>
    </thead>
</table>
<script type="text/javascript">

// 操作列
function datagrid_tree_operation() {
    var html = '<button type="button" class="btn-green" data-toggle="edit.datagrid.tr">编辑</button>'
        + '<button type="button" class="btn-red" data-toggle="del.datagrid.tr">删除</button>'
    
    return html
}
//不能拖动一级父节点
function datagrid_tree_beforeDrag(tr, data) {
    if (data && data.level == 0) {
        return false
    }
    
    return true
}
// 不能将子节点拖为一级父节点
function datagrid_tree_beforeDrop(data, targetData, position) {
    if (targetData && targetData.level == 0 && position !== 'append') {
        return false
    }
    
    return true
}
</script>
