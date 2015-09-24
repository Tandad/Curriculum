<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../common/head.jspf" %>
</head>

<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">员工考核记分结果</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="alert alert-info"><c:out value="${info}"/></div>
            </div>
        </div>
        <c:if test="${!empty list}">
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>姓名</th>
                        <th>所在部门</th>
                        <th>评价类型</th>
                        <th>任务完成</th>
                        <th>得分</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="e" varStatus="s">
                            <tr>
                                <td><c:out value="${s.count}"/></td>
                                <td><c:out value="${e.user.realname}"/></td>
                                <td><c:out value="${e.user.department.title}"/></td>
                                <td><c:out value="${e.type.title}"/></td>
                                <td><c:out value="${e.complete!=0?\"是\":\"否\"}"/></td>
                                <td><c:out value="${e.score}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 text-right">
                评价日期：<c:out value="${date}"/>
            </div>
        </div>
        </c:if>
    </div>
</div>

</body>
</html>
