<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
    <%@include file="common/head.jsp"%>
    <%@include file="common/tag.jsp"%>
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-condensed table-hover table-bordered">
                <thead>
                <tr>
                    <th>
                        id
                    </th>
                    <th>
                        姓名
                    </th>
                    <th>
                        手机号
                    </th>
                    <th>
                        注册时间
                    </th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="user" items="${list}">
                    <tr class="success">
                        <td>
                            ${user.id}
                        </td>
                        <td>
                            ${user.name}
                        </td>
                        <td>
                            ${user.phone}
                        </td>
                        <td>
                            <fmt:formatDate value="${user.addTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/resources/js/jquery.min.js" type="text/javascript"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/resources/js/user/user.js" type="text/javascript"></script>
</body>
</html>
