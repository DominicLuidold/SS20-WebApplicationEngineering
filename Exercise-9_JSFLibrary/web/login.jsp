<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="SimpleLibrary - Ihre Anlaufstelle für Bücher, Zeitungen und Hörspiele!">
        <meta name="keywords" content="SimpleLibrary, Bibliothek, Bücher, Zeitungen, Hörspiele">
        <meta name="robots" content="index, follow">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
        <title>SimpleLibrary - Login</title>

        <!-- CSS -->
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/custom.css">
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="./css/print.css">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
              crossorigin="anonymous">

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
                integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8=" crossorigin="anonymous"></script>
        <script src="./js/bootstrap.min.js"></script>

        <c:if test="${sessionScope.login != null && !sessionScope.login.validLogin}">
            <script>
                window.addEventListener('load', function () {
                    alert('Login-Daten stimmen nicht!');
                });
            </script>
        </c:if>
    </head>
    <body>
        <form class="form-signin" action="loginHandler" method="post">
            <h1 class="h3 mb-3 font-weight-normal">Anmelden</h1>

            <label for="userID" class="sr-only">User-ID</label>
            <input type="text" name="userID" id="userID" class="form-control" placeholder="User-ID" required autofocus>

            <label for="Password" class="sr-only">Passwort</label>
            <input type="password" name="password" id="Password" class="form-control" placeholder="Passwort" required>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Anmelden</button>
            <a href="home.html" class="btn btn-secondary btn-block">Zurück zur Übersicht</a>
            <p class="mt-5 mb-3 text-muted">&copy; SimpleLibrary 2020</p>
        </form>
    </body>
</html>
