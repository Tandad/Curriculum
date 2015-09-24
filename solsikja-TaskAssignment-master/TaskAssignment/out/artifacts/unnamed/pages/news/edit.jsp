<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jspf" %>
    <script src="./js/ueditor/ueditor.config.js"></script>
    <script src="./js/ueditor/ueditor.all.min.js"></script>
    <script src="./js/page/news/edit.js"></script>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">公告编辑</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form class="form-horizontal" id="form" action="/hot/pubArt" method="post">
                    <input type="hidden" name="id" value="${id}"/>

                    <div class="form-group">
                        <label class="control-label col-sm-2">文章标题</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="title" name="title" type="text" value="${title}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <script id="container" id="content" name="content" type="text/plain">${content}</script>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
