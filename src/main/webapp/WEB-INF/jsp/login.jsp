<%--
  Created by IntelliJ IDEA.
  User: zzl
  Date: 2017/6/8
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basePath = request.getContextPath(); %>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${basePath}/resources/layui/css/layui.css"  media="all">
    <script type="text/javascript" src="${basePath}/resources/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${basePath}/resources/md5.min.js"></script>
</head>
<body>
<form style="margin-top: 200px;" class="layui-form" method="post" action="${basePath}/user/login" >
    <div class="layui-form-item">
        <label class="layui-form-label">用户名:</label>
        <div class="layui-input-block">
            <input type="text" name="username" lay-verify="title" autocomplete="off" placeholder="请输入用户名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码:</label>
        <div class="layui-input-inline">
            <input type="password" name="password" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="submit" class="layui-btn" lay-submit="" lay-filter="login">登录</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="${basePath}/resources/layui/layui.js" charset="utf-8"></script>
<script>

    <%--$('#submit').click(function () {--%>
        <%--$.ajax({--%>
            <%--url: "${basePath}/user/login",--%>
            <%--data: {--%>
                <%--username: data.field.username,--%>
                <%--password: data.field.password--%>
            <%--},--%>
            <%--type: 'post',--%>
            <%--success: function (result) {--%>
                <%--alert(result);--%>
                <%--location.href = "${basePath}/file/fileUpload";--%>
            <%--},--%>
            <%--error: function () {--%>
                <%--alert('草，出错了~');--%>
                <%--location.href = "${basePath}/file/fileUpload";--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>

    layui.use(['form', 'laydate'], function(){
        var form = layui.form() ,layer = layui.layer

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length <= 0){
                    return '请输入用户名';
                }
            }
            ,pass: function(value){
                if(value.length <= 0){
                    return '请输入密码';
                }
            }
        });

        //监听提交
        <%--form.on('submit(login)', function(data) {--%>
            <%--var flag = false;--%>
            <%--$.ajax({--%>
                <%--async: false,--%>
                <%--url: "${basePath}/user/login",--%>
                <%--data: {--%>
                    <%--username: data.field.username,--%>
                    <%--password: data.field.password--%>
                <%--},--%>
                <%--type: 'post',--%>
                <%--success: function (result) {--%>
                    <%--if (result.status == 0) {--%>
                        <%--flag = true;--%>
                    <%--}--%>
                    <%--layer.alert(result);--%>
                <%--},--%>
                <%--error: function () {--%>
                    <%--layer.alert('草，出错了~');--%>
                <%--}--%>
            <%--});--%>
            <%--return flag;--%>
        <%--});--%>
    });
</script>
</body>
</html>
