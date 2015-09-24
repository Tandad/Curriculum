<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../common/head.jspf" %>
    <link rel="stylesheet" href="./styles/datetimepicker.css" />
    <script src="/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="/js/page/task/add.js"></script>

    <script id="task-user-item" type="text/template">
        <tr data-idx="{{=idx}}">
            <td>{{=realname}}</td>
            <td>{{=department.title}}</td>
            <td>
                <input type="hidden" id="uid-{{=idx}}" value="{{=id}}"/>
                <textarea class="form-control" id="detail-{{=idx}}"></textarea>
            </td>
            <td>
                <button type="button" class="unselect-mem btn btn-danger" data-idx="{{=idx}}">删除</button>
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
                <h1 class="page-header">任务发布</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form class="form-horizontal" id="task-form">
                    <div class="form-group">
                        <label class="control-label col-md-2">任务名称</label>
                        <div class="col-md-8">
                            <input class="form-control" type="text" name="title" id="title">
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-success btn-block" type="button" id="pub-task">
                                <i class="icon-file-alt"></i>
                                发布
                            </button>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">任务类型</label>
                        <div class="col-md-10">
                            <label class="radio-inline">
                                <input type="radio" name="type.id" value="1" checked>进度
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="type.id" value="2">质量
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="type.id" value="3">安全
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="type.id" value="4">成本
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">开始时间</label>
                        <div class="col-md-4">
                            <input class="form-control datetime-picker" type="text" name="sd" id="sd" readonly>
                        </div>
                        <label class="control-label col-md-2">结束时间</label>
                        <div class="col-md-4">
                            <input class="form-control datetime-picker" type="text" name="ed" id="ed" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">任务描述</label>
                        <div class="col-md-10">
                            <textarea class="form-control" type="text" name="content" rows="4"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-10 col-md-2">
                            <button class="btn btn-primary btn-block" type="button" id="add-task-user">
                                <i class="icon-user"></i>
                                添加执行人
                            </button>
                        </div>
                    </div>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>所在部门</th>
                            <th width="50%">任务描述</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="task-user-list"></tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="follower-list-dialog" tabindex="-1" role="dialog"
     aria-labelledby="follower-list-dialog-title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="follower-list-dialog-title">
                    请选择执行人
                </h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>职务</th>
                        <th>所在部门</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="follower-list"></tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script id="follower-item-tmpl" type="text/template">
    <tr>
        <td>{{=realname}}</td>
        <td>{{=title}}</td>
        <td>{{=department.title}}</td>
        <td>
            <button class="select-mem btn btn-primary btn-sm" type="button" data-idx="{{=idx}}">选择</button>
        </td>
    </tr>
</script>

</body>
</html>