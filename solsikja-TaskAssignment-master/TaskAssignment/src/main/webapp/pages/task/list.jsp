<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../common/head.jspf" %>
    <script src="/js/page/task/list.js"></script>
    <script id="item-tmpl" type="text/template">
        <tr>
            <td><a class="task-detail" href="javascript:void(0);" data-id="{{=id}}">{{=title}}</a></td>
            <td>{{=type.title}}</td>
            <td><a class="user-detail" href="javascript:void(0);" data-id="{{=leader.id}}">{{=leader.realname}}</a></td>
            <td>{{=startDate}}</td>
            <td>{{=endDate}}</td>
            <td>{{=pubDate}}</td>
        </tr>
    </script>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">全部任务列表</h1>
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

</body>
</html>