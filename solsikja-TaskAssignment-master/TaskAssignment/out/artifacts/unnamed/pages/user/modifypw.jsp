<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jspf"%>
    <script src="js/page/user/modifypw.js"></script>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">密码修改</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form method="post" class="form-horizontal">
                    <input type="hidden" name="id" value="${sessionScope.user.id}"/>
                    <div class="form-group">
                        <lable class="control-label col-md-3">请输入原密码</lable>
                        <div class="col-md-9">
                        <input class="form-control" type="password" name="oldpw" id="oldpw"/></div>
                    </div>
                    <div class="form-group">
                        <lable class="control-label col-md-3">请输入新密码</lable>
                        <div class="col-md-9">
                            <input class="form-control" type="password" name="newpw" id="newpw"/></div>
                    </div>
                    <div class="form-group">
                        <lable class="control-label col-md-3">再次输入新密码</lable>
                        <div class="col-md-9">
                            <input class="form-control" type="password" name="reppw" id="reppw"/></div>
                    </div>
                    <button class="btn btn-success btn-block" type="button" id="submitbtn">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
