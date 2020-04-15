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
        <link rel="stylesheet" href="./css/print.css">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
              crossorigin="anonymous">

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
                integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8=" crossorigin="anonymous"></script>
        <script src="./js/bootstrap.min.js"></script>
        <script src="./js/cookieHandler.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a class="navbar-brand" href="home.html"><i class="fa fa-book"></i> SimpleLibrary</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar"
                    aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbar">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="home.html">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="media/books.html">Bücher</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="media/newspapers.html">Zeitungen</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="media/audio.html">Hörspiele</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="guestbook">Gästebuch</a>
                    </li>
                </ul>
                <ul class="navbar-nav right">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Verlauf <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="account.jsp">Account beantragen</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="mailto:donot@sendan.email">
                            <i class="fa fa-envelope-o" aria-hidden="true"></i> Kontakt via E-Mail
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <main class="main-content" role="main">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h2>Verlauf von <c:out value="${sessionScope.login.userID}"/></h2>
                        <!-- Verlauf-Liste -->
                        <div class="media-content">
                            <table class="table table-striped media-table">
                                <thead>
                                <tr>
                                    <th scope="col">Titel</th>
                                    <th scope="col">URL</th>
                                    <th scope="col">Uhrzeit</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${sessionScope.history != null}">
                                        <c:forEach items="${sessionScope.history}" var="historyItem">
                                            <tr>
                                                <td><c:out value="${historyItem.title}"/></td>
                                                <td><c:out value="${historyItem.url}"/></td>
                                                <td><c:out value="${historyItem.timeVisited}"/></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>

