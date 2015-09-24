<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../common/head.jspf" %>
    <script src="./js/page/dept/add.js"></script>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">添加部门</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form class="form-horizontal" method="post">
                    <div class="form-group">
                        <lable class="control-label col-md-2">部门名称</lable>
                        <div class="col-md-8">
                            <input class="form-control" type="text" name="title" id="title"/>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-success btn-block" type="button" id="submitbtn">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>