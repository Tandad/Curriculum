<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jspf" %>
    <script src="/js/ueditor/ueditor.config.js"></script>
    <script src="/js/ueditor/ueditor.all.min.js"></script>
    <script src="/js/page/news/add.js"></script>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">发布公告</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form class="form-horizontal" id="form" method="post">

                    <div class="form-group">
                        <label class="control-label col-md-2">文章标题</label>
                        <div class="col-md-8">
                            <input class="form-control" id="title" name="title" type="text">
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-success btn-block" type="button" id="submitbtn">发布</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12">
                            <script id="container" id="content" name="content" type="text/plain"></script>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
