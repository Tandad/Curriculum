<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="common/head.jspf" %>
</head>
<body>
<div id="wrapper">
    <%@include file="common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">仪表盘</h1>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-info">
                    <div class="panel-heading">最新公告</div>
                    <ul class="list-group">
                        <c:forEach items="${news}" var="n">
                        <li class="list-group-item">
                            <a href="/news/${n.id}">${n.title}</a>
                            <%--<div class="pull-right"><fmt:formatDate value="${n.pubtime}" type="both" pattern="yyyy/MM/dd" /></div>--%>
                        </li>
                        </c:forEach>
                        <c:forEach begin="${fn:length(news)}" end="4" step="1">
                        <li class="list-group-item">[暂无]</li>
                        </c:forEach>
                    </ul>
                    <a href="/news/list">
                        <div class="panel-footer">
                            <span class="pull-left">查看详情</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>

            <div class="col-md-6">
                <div class="panel panel-success">
                    <div class="panel-heading">绩效考核公告</div>
                    <ul class="list-group">
                        <c:forEach items="${evaluation}" var="e">
                            <li class="list-group-item">
                                <a href="/evaluation/report?id=${e.id}">
                                    <c:choose>
                                        <c:when test="${e.type == 0}">
                                            <c:out value="${e.startDate}"/> ~ <c:out value="${e.endDate}"/>周绩效考核结果
                                        </c:when>
                                        <c:otherwise><fmt:formatDate value="${e.endDate}" pattern="yyyy年MM月"/>绩效考核结果</c:otherwise>
                                    </c:choose>
                                </a>
                            </li>
                        </c:forEach>
                        <c:forEach begin="${fn:length(evaluation)}" end="4" step="1">
                            <li class="list-group-item">[暂无]</li>
                        </c:forEach>
                    </ul>
                    <a href="/evaluation/list">
                        <div class="panel-footer">
                            <span class="pull-left">查看详情</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-green">
                    <div class="panel-heading">进度任务</div>
                    <ul class="list-group">
                        <c:forEach items="${progress}" var="t">
                            <li class="list-group-item">
                                <a class="task-detail" href="javascript:void(0);" data-id="${t.id}"><c:out value="${t.title}"/></a>
                            </li>
                        </c:forEach>
                        <c:forEach begin="${fn:length(progress)}" end="4" step="1">
                            <li class="list-group-item">[暂无]</li>
                        </c:forEach>
                    </ul>
                    <a href="/task/list">
                        <div class="panel-footer">
                            <span class="pull-left">查看详情</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>

            <div class="col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">质量任务</div>
                    <ul class="list-group">
                    <c:forEach items="${quality}" var="t">
                        <li class="list-group-item">
                            <a class="task-detail" href="javascript:void(0);" data-id="${t.id}"><c:out value="${t.title}"/></a>
                        </li>
                    </c:forEach>
                    <c:forEach begin="${fn:length(quality)}" end="4" step="1">
                        <li class="list-group-item">[暂无]</li>
                    </c:forEach>
                    </ul>
                    <a href="/task/list">
                        <div class="panel-footer">
                            <span class="pull-left">查看详情</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-red">
                    <div class="panel-heading">安全任务</div>
                    <ul class="list-group">
                    <c:forEach items="${safety}" var="t">
                        <li class="list-group-item">
                            <a class="task-detail" href="javascript:void(0);" data-id="${t.id}"><c:out value="${t.title}"/></a>
                        </li>
                    </c:forEach>
                    <c:forEach begin="${fn:length(safety)}" end="4" step="1">
                        <li class="list-group-item">[暂无]</li>
                    </c:forEach>
                    </ul>
                    <a href="/task/list">
                        <div class="panel-footer">
                            <span class="pull-left">查看详情</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>

            <div class="col-md-6">
                <div class="panel panel-yellow">
                    <div class="panel-heading">成本任务</div>
                    <ul class="list-group">
                        <c:forEach items="${cost}" var="t">
                            <li class="list-group-item">
                                <a class="task-detail" href="javascript:void(0);" data-id="${t.id}"><c:out value="${t.title}"/></a>
                            </li>
                        </c:forEach>
                        <c:forEach begin="${fn:length(cost)}" end="4" step="1">
                            <li class="list-group-item">[暂无]</li>
                        </c:forEach>
                    </ul>
                    <a href="/task/list">
                        <div class="panel-footer">
                            <span class="pull-left">查看详情</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="common/dialog.jspf"%>

</body>
</html>