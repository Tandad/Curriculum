<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../common/head.jspf" %>
    <script src="/js/page/task/my.js"></script>
    <script id="item-tmpl" type="text/template">
        <tr>
            <td><a class="task-detail" href="javascript:void(0);" data-id="{{=id}}">{{=title}}</a></td>
            <td>{{=type.title}}</td>
            <td><a class="user-detail" href="javascript:void(0);" data-id="{{=leader.id}}">{{=leader.realname}}</a></td>
            <td>{{=startDate}}</td>
            <td>{{=endDate}}</td>
            <td>{{=pubDate}}</td>
            <td>
                {{ if(status == 1) { }}
                <button class="btn btn-xs btn-primary task-evaluate" data-id="{{=id}}">评价</button>
                {{ } else if (status == 2) { }}
                <button class="btn btn-xs btn-info task-evaluate" data-id="{{=id}}">详情</button>
                {{ } }}
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
                <h1 class="page-header">我发布的任务</h1>
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
                    <thead>
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

<div class="modal fade" id="evaluate-dialog" tabindex="-1" role="dialog"
     aria-labelledby="evaluate-dialog-title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="commit-dialog-title">任务评价</h4>
            </div>
            <div class="modal-body" id="tu-list">
            </div>
        </div>
    </div>
</div>

<script id="tu-item" type="text/template">
    <div class="panel panel-default">
        <div class="panel-heading">
            {{=user.realname}}
        </div>
        <div class="panel-body">
            <form id="tu-form-{{=id}}">
                <input type="hidden" name="tuid" value="{{=id}}"/>
                <div class="form-group">
                    <label class="control-label">任务完成报告：</label>
                    <p class="form-control-static">{{=report}}</p>
                </div>
                <div class="form-group">
                    <label class="control-label">领导评语：</label>
                    <textarea class="form-control" name="comment">{{=comment}}</textarea>
                </div>
            </form>
        </div>
        <div class="panel-footer text-right" >
            <button class="btn btn-success evaluate-btn" data-id="{{=id}}" data-name="{{=user.realname}}">评价</button>
        </div>
    </div>
</script>

<script id="tu-eva-item" type="text/template">
    <div class="panel panel-default">
        <div class="panel-heading">
            {{=user.realname}}
        </div>
        <div class="panel-body">
            <form>
                <div class="form-group">
                    <label class="control-label">任务完成报告：</label>
                    <p class="form-control-static">{{=report}}</p>
                </div>
                <div class="form-group">
                    <label class="control-label">领导评语：</label>
                    <p class="form-control-static">{{=comment}}</p>
                </div>
            </form>
        </div>
    </div>
</script>

<%@ include file="../common/dialog.jspf"%>

</body>
</html>