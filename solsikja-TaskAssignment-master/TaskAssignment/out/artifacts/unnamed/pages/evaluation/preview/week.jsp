<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../../common/head.jspf" %>
    <script src="/js/page/evaluation/preview/week.js"></script>
</head>

<body>
<div id="wrapper">
    <%@include file="../../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">周考核结果预览</h1>
            </div>
        </div>
        <c:choose>
        <c:when test="${empty start}">
        <div class="row">
            <div class="col-lg-12">
                <div class="alert alert-info">没有待发布的考核结果</div>
            </div>
        </div>
        </c:when>
        <c:otherwise>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-md-offset-6 col-md-2 text-right">
                    <label class="control-label">考核时间：</label>
                </div>
                <div class="col-md-4">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <c:out value="${start}"/> ~ <c:out value="${end}"/> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu" style="min-width: inherit">
                            <c:forEach items="${datelist}" var="d">
                                <li><a href="/evaluation/preview/week?date=<c:out value="${d[0]}"/>"><c:out value="${d[0]}"/> ~ <c:out value="${d[1]}"/></a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </form>
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>排名</th>
                        <th>姓名</th>
                        <th>所在部门</th>
                        <th>工作是否完成</th>
                        <th>成绩</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="s">
                        <tr>
                            <td>
                                <c:out value="${s.count}"/>
                            </td>
                            <td><c:out value="${e.user.realname}"/></td>
                            <td><c:out value="${e.user.department.title}"/></td>
                            <td><c:out value="${e.complete !=0 ? \"是\":\"否\"}"/></td>
                            <td><c:out value="${e.score}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
        <c:choose>
            <c:when test="${enable}">
                <div class="col-md-offset-10 col-md-2">
                    <button class="btn btn-success btn-block" id="publish" data-date="<c:out value="${start}"/>">发布考核结果</button>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-lg-12">
                    <div class="alert alert-info">尚在考核期内，暂时不能发布结果</div>
                </div>
            </c:otherwise>
        </c:choose>
        </div>
        </c:otherwise>
        </c:choose>
    </div>
</div>

</body>
</html>