<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../common/head.jspf" %>
    <script src="/js/page/task/member.js"></script>
    <script id="pub-head" type="text/template">
        <tr>
            <th>任务名称</th>
            <th>类别</th>
            <th>发布人</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>发布时间</th>
            <th>操作</th>
        </tr>
    </script>

    <script id="finished-head" type="text/template">
        <tr>
            <th>任务名称</th>
            <th>类别</th>
            <th>发布人</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>发布时间</th>
            <th>提交时间</th>
        </tr>
    </script>

    <script id="evaluate-head" type="text/template">
        <tr>
            <th>任务名称</th>
            <th>类别</th>
            <th>发布人</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>评价时间</th>
            <th>操作</th>
        </tr>
    </script>


    <script id="pub-item-tmpl" type="text/template">
        <tr>
            <td><a class="task-detail" href="javascript:void(0);" data-id="{{=task.id}}">{{=task.title}}</a></td>
            <td>{{=task.type.title}}</td>
            <td><a class="user-detail" href="javascript:void(0);" data-id="{{=task.leader.id}}">{{=task.leader.realname}}</a></td>
            <td>{{=task.startDate}}</td>
            <td>{{=task.endDate}}</td>
            <td>{{=task.pubDate}}</td>
            <td>
                {{      if (status == 0) { }}
                <button class="btn btn-primary btn-xs task-commit" data-id="{{=id}}">任务提交</button>
                {{      } else { }}
                <button class="btn btn-xs" data-id="{{=id}}">已经提交</button>
                {{      } }}
            </td>
        </tr>
    </script>

    <script id="finished-item-tmpl" type="text/template">
        <tr>
            <td><a class="task-detail" href="javascript:void(0);" data-id="{{=task.id}}">{{=task.title}}</a></td>
            <td>{{=task.type.title}}</td>
            <td><a class="user-detail" href="javascript:void(0);" data-id="{{=task.leader.id}}">{{=task.leader.realname}}</a></td>
            <td>{{=task.startDate}}</td>
            <td>{{=task.endDate}}</td>
            <td>{{=task.pubDate}}</td>
            <td>{{=subdate}}</td>
        </tr>
    </script>

    <script id="evaluate-item-tmpl" type="text/template">
        <tr>
            <td><a class="task-detail" href="javascript:void(0);" data-id="{{=task.id}}">{{=task.title}}</a></td>
            <td>{{=task.type.title}}</td>
            <td><a class="user-detail" href="javascript:void(0);" data-id="{{=task.leader.id}}">{{=task.leader.realname}}</a></td>
            <td>{{=task.startDate}}</td>
            <td>{{=task.endDate}}</td>
            <td>{{=evadate}}</td>
            <th>
                <button class="btn btn-info btn-xs tu-info" data-id="{{=id}}">详情</button>
            </th>
        </tr>
    </script>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">我执行的任务</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="javascript:void(0);" id="pub">进行中</a></li>
                    <li><a href="javascript:void(0);" id="finished">已完成</a></li>
                    <li><a href="javascript:void(0);" id="evaluate">已评价</a></li>
                </ul>
                <table class="table table-bordered table-striped">
                    <thead id="head">
                    <tr>
                        <th>任务名称</th>
                        <th>类别</th>
                        <th>发布人</th>
                        <th>开始时间</th>
                        <th>结束时间</th>
                        <th>发布时间</th>
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

<div class="modal fade" id="commit-dialog" tabindex="-1" role="dialog"
     aria-labelledby="commit-dialog-title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="commit-dialog-title">任务提交</h4>
            </div>
            <div class="modal-body">
                <form id="commit-form">
                    <input type="hidden" name="tuid" id="tuid">
                    <div class="form-group">
                        <label class="control-label">任务完成报告</label>
                        <textarea class="form-control" name="report" id="report" rows="5"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" id="commit-btn">&nbsp;&nbsp;提交&nbsp;&nbsp;</button>
                <button class="btn btn-default">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="info-dialog" tabindex="-1" role="dialog"
     aria-labelledby="info-dialog-title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="info-dialog-title">任务提交</h4>
            </div>
            <div class="modal-body">
                <form id="info-form">
                    <div class="form-group">
                        <label class="control-label">任务完成报告：</label>
                        <textarea class="form-control" name="report" readonly></textarea>
                    </div>
                    <div class="form-group">
                        <label class="control-label">领导评语：</label>
                        <textarea class="form-control" name="comment" readonly></textarea>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../common/dialog.jspf"%>

</body>
</html>