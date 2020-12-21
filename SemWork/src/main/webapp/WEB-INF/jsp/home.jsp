<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <script src="https://kit.fontawesome.com/61fea81e5a.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="../../resources/css/index.css" type="text/css"/>

    <script src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>

    <script type="text/javascript" src="../../resources/js/home.js"></script>

    <style>
        h2{
            text-align: center;
        }
    </style>
</head>
<body id="home">
<header>
    <jsp:include page="header.html"/>
</header>
<h2>New</h2>
<div class="container-posts content-row">
    <div class="row">
        <div class="card-columns" id="posts">

        </div>
    </div>
</div>
</body>
</html>
