<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jspf" %>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header"><c:out value="${news.title}"/></h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <c:out value="${news.content}" escapeXml="false"/>
            </div>
        </div>
    </div>
</div>

</body>
</html>
