<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../../common/head.jspf" %>
</head>

<body>
<div id="wrapper">
    <%@include file="../../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    <c:out value="${report.startDate}"/> ~ <c:out value="${report.startDate}"/>周绩效考核结果
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>排名</th>
                        <th>姓名</th>
                        <th>所在部门</th>
                        <th>考核成绩</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="d" varStatus="s">
                        <tr>
                            <td>
                                <c:out value="${s.count}"/>
                            </td>
                            <td><c:out value="${d.user.realname}"/></td>
                            <td><c:out value="${d.user.department.title}"/></td>
                            <td><c:out value="${d.total}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>