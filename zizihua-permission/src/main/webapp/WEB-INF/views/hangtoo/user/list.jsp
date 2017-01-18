<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table class="table table-bordered" data-toggle="datagrid" data-options="{
    height: '100%',
    gridTitle : 'datagrid 完整示例 - Data属性 API',
    showToolbar: true,
    toolbarItem: 'add, |, edit, |, cancel, |, save, |, del, |, refresh',
    local: 'local',
    inlineEditMult: false,
    dataUrl: '/sysUser/dataList.do',
    editUrl: '/sysUser/save.do',
    delUrl: '/sysUser/delete.do',
    paging: {pageSize:50, pageCurrent:1},
    linenumberAll: true
}">
    <thead>
        <tr>
            <th data-options="{name:'nickname'}">姓名</th>
            <th data-options="{name:'email'}">邮箱</th>
            <th data-options="{name:'state'}">状态</th>
            <th data-options="{name:'superadmin'}">是否管理员</th>
            <th data-options="{name:'updatetime',align:'center',width:60}">修改时间</th>
        </tr>
    </thead>
</table>
