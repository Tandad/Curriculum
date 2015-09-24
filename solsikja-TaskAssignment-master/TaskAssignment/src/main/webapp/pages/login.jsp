<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="common/head.jspf" %>
</head>
<body id="login">
<div class="container">
    <form class="form-signin" method="post" action="./login" role="form">
        <h2>请登录</h2>
        <div class="form-group">
            <input type="text" class="form-control" name="name" placeholder="用户名" required autofocus/>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password" placeholder="密码" required/>
        </div>
        <div class="form-group">
            <button class="btn btn-block btn-primary" type="submit" id="submitbtn">登录</button>
        </div>
    </form>
</div>
</body>
</html>