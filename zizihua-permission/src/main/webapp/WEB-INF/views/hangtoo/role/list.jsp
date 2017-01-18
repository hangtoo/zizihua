<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table class="table table-bordered" data-toggle="datagrid" data-options="{
    height: '100%',
    gridTitle : '权限管理',
    showToolbar: true,
    toolbarItem: 'add, |, edit, |, cancel, |, save, |, del, |, refresh',
    local: 'local',
    inlineEditMult: false,
    dataUrl: '/sysRole/dataList.do',
    editUrl: '/sysRole/save.do',
    delUrl: '/sysRole/delete.do',
    paging: {pageSize:50, pageCurrent:1},
    linenumberAll: true
}">
    <thead>
        <tr>
                                                    <th data-options="{name:'rolename'}">角色名称</th>
                                          <th data-options="{name:'createtime'}">创建时间</th>
                                          <th data-options="{name:'createby'}">创建人</th>
                                          <th data-options="{name:'updatetime'}">修改时间</th>
                                          <th data-options="{name:'updateby'}">修改人</th>
                                          <th data-options="{name:'state'}">状态0=可用 1=禁用</th>
                                          <th data-options="{name:'descr'}">角色描述</th>
                            </tr>
    </thead>
</table>
