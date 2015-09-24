<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <%@include file="../common/head.jspf" %>
    <script src="/js/page/evaluation/mark.js"></script>
</head>

<body>
<div id="wrapper">
    <%@include file="../common/navbar.jspf" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">员工考核记分表</h1>
            </div>
        </div>
        <form id="main-form">
            <div class="row">
                <div class="col-lg-12">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>姓名</th>
                            <th>所在部门</th>
                            <th>评价类型</th>
                            <th>评价</th>
                            <th>得分</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="index" value="${0}"/>

                        <c:forEach begin="1" end="3" step="1" varStatus="s">

                            <c:choose>
                                <c:when test="${s.current == 1}">
                                    <c:set var="evatype" value="分管领导打分"/>
                                    <c:set var="list" value="${underling}"/>
                                </c:when>
                                <c:when test="${s.current == 2}">
                                    <c:set var="evatype" value="其他领导打分"/>
                                    <c:set var="list" value="${otherUnderling}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="evatype" value="外部门打分"/>
                                    <c:set var="list" value="${colleague}"/>
                                </c:otherwise>
                            </c:choose>

                            <c:forEach items="${list}" var="u">
                                <tr>
                                    <td>
                                        <c:out value="${u.id}"/>
                                        <input type="hidden" name="data[${index}].user.id" value="${u.id}"/>
                                        <input type="hidden" name="data[${index}].type.id" value="${s.current}"/>
                                    </td>
                                    <td><c:out value="${u.realname}"/></td>
                                    <td><c:out value="${u.department.title}"/></td>
                                    <td><c:out value="${evatype}"/></td>
                                    <td>
                                        <div class="form-group">
                                            <label class="control-label">任务完成情况：</label>
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="data[${index}].complete" value="1">完成工作任务
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="data[${index}].complete" value="0">未完成工作任务
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group complete">
                                            <label class="control-label">任务完成程度：</label>
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="data[${index}].score" value="100">非常满意
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="data[${index}].score" value="90">满意
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="data[${index}].score" value="80">一般
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group incomplete">
                                            <label class="control-label">任务完成程度：</label>
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="data[${index}].score" value="70">非常努力
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="data[${index}].score" value="60">努力
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="data[${index}].score" value="50">一般
                                                </label>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="score"></td>
                                </tr>
                                <c:set var="index" value="${index + 1}"/>
                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-offset-10 col-lg-2">
                    <button class="btn btn-success btn-block" id="btn-submit" type="button">提交</button>
                </div>
            </div>
        </form>

        <input type="hidden" id="max-user" value="${index}"/>
    </div>
</div>

</body>
</html>