<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>

    <script src="https://kit.fontawesome.com/61fea81e5a.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="../../resources/css/newprofile.css" type="text/css"/>
    <link rel="stylesheet" href="../../resources/css/index.css" type="text/css"/>

    <script src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>

    <script type="text/javascript" src="../../resources/js/index.js"></script>

</head>

<body>

<header>
    <jsp:include page="header.html"/>
</header>

<div class="profile" id="profile">
    <div class="card" style="width: 18rem;">
        <img class="card-img-top" src="../../resources/img/ava.jpg" alt="Card image cap">
        <div class="card-body">
            <div id="row-names">

            </div>
        </div>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createProject">
            New project
        </button>
    </div>
</div>

<div class="container-posts content-row">
    <div class="row">
        <div class="card-columns" id="posts">

        </div>
    </div>
</div>

<div content="modal">

    <!-- Modal -->
    <div class="modal" id="createProject" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">

                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Создать проект</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">

                    <form method="post" action="/profile/newProject" enctype="multipart/form-data">

                        <div class="form-group">
                            <label for="title">Название</label>
                            <input required type="text" name="name" class="form-control" id="title">
                        </div>

                        <div class="form-group">
                            <label for="description">Описание</label>
                            <input required type="text" name="description" class="form-control" id="description">
                        </div>

                        <div class="form-group">
                            <p><input name="tag" type="radio" value="illustration" checked> Иллюстрация
                                <input name="tag" type="radio" value="advertising"> Реклама
                                <input name="tag" type="radio" value="photo"> Фотография</p>
                            <p><input name="tag" type="radio" value="graph design"> Графический дизайн
                                <input name="tag" type="radio" value="architecture"> Архитектура
                                <input name="tag" type="radio" value="ind design"> Промышленный дизайн</p>
                            <p><input name="tag" type="radio" value="fashion"> Мода
                                <input name="tag" type="radio" value="craft"> Ремесло</p>
                        </div>

                        <div class="form-group">
                            <input required type="file" name="file">
                        </div>

                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                        <button type="submit" class="btn btn-primary">Создать</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>