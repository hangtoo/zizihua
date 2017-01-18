<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table class="table table-bordered" data-toggle="datagrid" data-options="{
    height: '100%',
    gridTitle : '${codeName}',
    showToolbar: true,
    toolbarItem: 'add, |, edit, |, cancel, |, save, |, del, |, refresh',
    local: 'local',
    inlineEditMult: false,
    dataUrl: '/${lowerName}/dataList.do',
    editUrl: '/${lowerName}/save.do',
    delUrl: '/${lowerName}/delete.do',
    paging: {pageSize:50, pageCurrent:1},
    linenumberAll: true
}">
    <thead>
        <tr>
          #foreach($po in $!{columnDatas})
          #if  ($po.columnName !='id')
            <th data-options="{name:'${po.columnName}'}">${po.columnComment}</th>
          #end
          #end
        </tr>
    </thead>
</table>
