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
    <title>rn热更新文件上传</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${basePath}/resources/layui/css/layui.css"  media="all">
    <script type="text/javascript" src="${basePath}/resources/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${basePath}/resources/md5.min.js"></script>
    <style>
        body,div {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="layui-form" >
    <div class="layui-form-item">
        <label class="layui-form-label">versionName:</label>
        <div class="layui-input-block">
            <input id="app_vname" type="text" name="username" lay-verify="title" autocomplete="off" placeholder="请输入versionName" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">versionCode:</label>
        <div class="layui-input-block">
            <input id="app_vcode" type="number" name="username" lay-verify="title" autocomplete="off" placeholder="请输入versionCode" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">jsbundleName:</label>
        <div class="layui-input-block">
            <input id="jsbundle_name" type="text" name="username" lay-verify="title" autocomplete="off" placeholder="请输入jsbundleName" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">灰度测试总数:</label>
        <div class="layui-input-block">
            <input id="test_num" type="number" name="username" lay-verify="title" autocomplete="off" placeholder="灰度测试总数" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="submit" class="layui-btn" lay-submit="" lay-filter="login">提交</button>
        </div>
    </div>
</div>

<script src="${basePath}/resources/layui/layui.js" charset="utf-8"></script>
<script>

    layer = layui.layer;
    $('#submit').click(function () {
        $.ajax({
            url: "${basePath}/user/login",
            data: {
                username: $('#username').val(),
                password: $('#password').val()
            },
            type: 'post',
            success: function (result) {
                var s = JSON.stringify(result);
                alert(s);
                alert(result.status)
                if (result.status == 0) {
                    location.href = "${basePath}/file/fileUpload";
                } else {
                    layer.open({
                        title: '错误提示',
                        content: result.msg
                    });
                }
            },
            error: function () {
                layer.open({
                    title: '',
                    content: '${basePath}草，出错了~'
                });
                location.href = "${basePath}/file/fileUpload";
            }
        });
    });
</script>
</body>
</html>
