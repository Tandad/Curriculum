<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../common/head.jspf" %>
    <script src="./js/page/evaluation/list.js"></script>
    <script id="item-tmpl" type="text/template">
        <tr>
            <td><a href="/evaluation/report?id={{=id}}">
                {{ if (type == 0) { }}
                    {{=startDate}} ~ {{=endDate}} 周绩效考核结果
                {{ } else { }}
                    {{=endDate.substring(0,7)}} 月绩效考核结果
                {{ } }}
            </a></td>
            <td>{{ if (type == 0) { }} 周 {{ } else { }} 月 {{ } }}</td>
            <td>{{=startDate}}</td>
            <td>{{=endDate}}</td>
        </tr>
    </script>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">绩效考核公告列表</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>绩效考核公告</th>
                        <th>类型</th>
                        <th>起始时间</th>
                        <th>终止时间</th>
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