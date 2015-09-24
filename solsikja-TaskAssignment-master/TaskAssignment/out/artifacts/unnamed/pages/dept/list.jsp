<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../common/head.jspf" %>
    <script src="/js/page/dept/list.js"></script>
    <script id="item-tmpl" type="text/template">
        <tr>
            <td><a class="dept-detail" href="javascript:void(0);" data-id="{{=id}}">{{=title}}</a></td>
            <td>{{=eva}}</td>
            <td>{{=evausername}}</td>
            <td>
                {{ if (evaluate == 0) { }}
                <button class="btn btn-primary btn-xs add-eva" data-id="{{=id}}">参与考核</button>
                {{ } else { }}
                <button class="btn btn-info btn-xs del-eva" data-id="{{=id}}">不参与考核</button>
                {{ } }}
                <button class="btn btn-success btn-xs eva-user" data-id="{{=id}}">设置考核人</button>
                <button class="delete btn btn-danger btn-xs" data-id="{{=id}}">删除</button>
            </td>
        </tr>
    </script>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">部门列表</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>部门名称</th>
                        <th>成员参与考核</th>
                        <th>评分人</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="list"></tbody>
                </table>
            </div>
            <div id="page"></div>
        </div>
    </div>
</div>

<div class="modal fade" id="eva-dialog" tabindex="-1" role="dialog"
     aria-labelledby="eva-dialog-title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="eva-dialog-title">请选择考核人</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>职务</th>
                        <th>选择</th>
                    </tr>
                    </thead>
                    <tbody id="eva-list"></tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-danger" id="clear-eva-user">清空考核人</button>
            </div>
        </div>
    </div>
</div>

<script id="eva-item" type="text/template">
    <tr>
        <td>{{=realname}}</td>
        <td>{{=title}}</td>
        <td>
            <button class="btn btn-primary btn-xs select-eva-user" data-id="{{=id}}">选择</button>
        </td>
    </tr>
</script>

<%@ include file="../common/dialog.jspf"%>

</body>
</html>