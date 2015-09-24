<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../common/head.jspf" %>
    <script src="./js/page/news/list.js"></script>
    <script id="item-tmpl" type="text/template">
        <tr>
            <td><a href="/news/{{=id}}">{{=title}}</a></td>
            <td>{{=new Date(pubtime).format("yyyy-MM-dd hh:mm")}}</td>
            <c:if test="${user.admin == 1}">
            <td>
                <button class="edit btn btn-primary btn-xs" data-id="{{=id}}">编辑</button>
                <button class="delete btn btn-danger btn-xs" data-id="{{=id}}">删除</button>
            </td>
            </c:if>
        </tr>
    </script>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">公告列表</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>公告标题</th>
                        <th>发布日期</th>
                        <c:if test="${user.admin == 1}">
                        <th>操作</th>
                        </c:if>
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