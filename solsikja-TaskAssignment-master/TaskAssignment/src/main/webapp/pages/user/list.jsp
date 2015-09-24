<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../common/head.jspf" %>
    <script src="./js/page/user/list.js"></script>
    <script id="item-tmpl" type="text/template">
        <tr>
            <td><a class="user-detail" href="javascript:void(0);" data-id="{{=id}}">{{=name}}</a></td>
            <td>{{=realname}}</td>
            <td>{{=title}}</td>
            <td>
                {{ if (department != null) { }}
                    <a class="dept-detail" href="javascript:void(0);" data-id="{{=department.id}}">{{=department.title}}</a>
                {{ } }}
            </td>
            <td>
                <button class="leader btn btn-primary btn-xs" data-id="{{=id}}">职务任免</button>
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
                <h1 class="page-header">用户列表</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>用户名</th>
                        <th>真实姓名</th>
                        <th>职务</th>
                        <th>所属部门</th>
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

<%@ include file="../common/dialog.jspf"%>

<div class="modal fade" id="leader-add-dialog" tabindex="-1" role="dialog"
     aria-labelledby="leader-add-dialog-title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="leader-add-dialog-title">
                    职务任免
                </h4>
            </div>
            <div class="modal-body">
                <h5>现任职务</h5>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>部门</th>
                        <th>职务</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="lead-dept-list"></tbody>
                </table>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-xs-2">部门名称</label>
                        <div class="col-xs-3">
                            <select class="form-control" id="did">
                                <c:forEach items="${depts}" var="d">
                                    <option value="${d.id}">${d.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="control-label col-xs-2">头衔</label>
                        <div class="col-xs-3">
                            <input class="form-control" type="text" id="title">
                        </div>
                        <div class="col-xs-2">
                            <button class="btn btn-primary pull-right" id="leader-add" type="button">任命</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<script id="lead-item-tmpl" type="text/template">
    <tr>
        <td>{{=department.title}}</td>
        <td>{{=title}}</td>
        <td>
            <button class="del-leader btn btn-danger" type="button" data-id="{{=department.id}}">删除</button>
        </td>
    </tr>
</script>

</body>
</html>