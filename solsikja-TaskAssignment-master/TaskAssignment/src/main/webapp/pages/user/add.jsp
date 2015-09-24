<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../common/head.jspf" %>
    <script src="./js/page/user/add.js"></script>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">添加用户</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form class="form-horizontal" method="post">
                    <div class="form-group">
                        <lable class="control-label col-md-2">用户名</lable>
                        <div class="col-md-10">
                            <input class="form-control" type="text" name="name" id="name"/></div>
                    </div>
                    <div class="form-group">
                        <lable class="control-label col-md-2">真实名称</lable>
                        <div class="col-md-10">
                            <input class="form-control" type="text" name="realname" id="realname"/></div>
                    </div>
                    <div class="form-group">
                        <lable class="control-label col-md-2">默认密码</lable>
                        <div class="col-md-10">
                            <input class="form-control" type="text" value="123456" readonly></div>
                    </div>
                    <div class="form-group">
                        <lable class="control-label col-md-2">管理员</lable>
                        <div class="col-md-10">
                            <select class="form-control" name="admin" id="admin">
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                    </div>
                    <button class="btn btn-success btn-block" type="button" id="submitbtn">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>