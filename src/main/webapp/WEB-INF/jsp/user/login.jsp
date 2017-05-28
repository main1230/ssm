<%--
  Created by IntelliJ IDEA.
  User: zzl
  Date: 2017/5/22
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basePath = request.getContextPath();%>
<html>
<head>
    <title>登录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${basePath}/resources/layui/css/layui.css"  media="all">
</head>
<body>

<form class="layui-form" action="${basePath}/user/login">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名：</label>
        <div class="layui-input-block">
            <input type="text" name="username" lay-verify="title" autocomplete="off" maxlength="10" placeholder="请输入用户名" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">密码：</label>
        <div class="layui-input-block">
            <input type="text" name="password" lay-verify="title" autocomplete="off" maxlength="11" placeholder="请输入密码" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <button class="layui-btn" lay-submit="" lay-filter="demo2">登录</button>
    </div>
</form>
</form>

<script src="${basePath}/resources/layui/layui.js"></script>
<script>
    layui.use('form', function(){
        var form = layui.form();

        //各种基于事件的操作，下面会有进一步介绍
    });
</script>

</body>
</html>
