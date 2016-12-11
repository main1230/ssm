<%--
  Created by IntelliJ IDEA.
  User: zzl
  Date: 2016/12/10
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>多文件上传</title>
</head>
<body>
<div align="center">
    <form method="post" action="/file/doUploads" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="file" name="file"/>
        <input type="file" name="file"/>
        <input type="file" name="file"/>
        <input type="file" name="file"/>
        <input type="submit"/>
    </form>
</div>
</body>
</html>
