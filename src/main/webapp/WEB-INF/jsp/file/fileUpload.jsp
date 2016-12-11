<%--
  Created by IntelliJ IDEA.
  User: zzl
  Date: 2016/12/10
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>

<div align="center">
    <h1>上传附件</h1>
    <form method="post" action="/file/doUpload" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit"/>
    </form>
</div>

</body>
</html>
