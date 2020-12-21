<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 08.12.2020
  Time: 7:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<form enctype="multipart/form-data" action="/upload" method="post">
    <input type="text" name="title">
    <input type="file" name="file">
    <input type="submit" value="Отправить">
</form>
</body>
</html>
