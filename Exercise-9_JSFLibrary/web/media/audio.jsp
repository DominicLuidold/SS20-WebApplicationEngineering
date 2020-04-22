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
        <title>SimpleLibrary</title>

        <!-- CSS -->
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/custom.css">
        <link rel="stylesheet" href="../css/print.css">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
              crossorigin="anonymous">

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
                integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8=" crossorigin="anonymous"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/cookieHandler.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a class="navbar-brand" href="../home.html"><i class="fa fa-book"></i> SimpleLibrary</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar"
                    aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbar">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="../home.html">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="books">Bücher</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="newspapers.html">Zeitungen</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Hörspiele <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../guestbook">Gästebuch</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../choose-rating-type.xhtml">Bewertung</a>
                    </li>
                </ul>
                <ul class="navbar-nav right">
                    <li class="nav-item">
                        <a class="nav-link" href="../history.jsp">Verlauf</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../account.jsp">Account beantragen</a>
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
                        <h2>Hörspiele</h2>
                        <hr>
                        <!-- Informationen -->
                        <p>
                            <span class="lead font-weight-bold">Informationen</span><br>
                            Alle Hörspiele sind im Raum W205 bzw. W206 zu finden und haben eine Ausleihfrist von max.
                            drei Wochen am Stück.
                        </p>

                        <!-- Hörspiel-Liste -->
                        <div class="media-content">
                            <h5>Folgende Hörspiele sind vorhanden:</h5>
                            <table class="table table-striped media-table">
                                <thead>
                                <tr>
                                    <th scope="col">Index</th>
                                    <th scope="col">Titel</th>
                                    <th scope="col">Audiolänge</th>
                                    <th scope="col">Verliehen bis</th>
                                    <th scope="col">Details</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${requestScope.audios != null}">
                                        <c:forEach items="${requestScope.audios}" var="audio">
                                            <tr>
                                                <th scope="row"><c:out value="${audio.id}"/></th>
                                                <td><c:out value="${audio.title}"/></td>
                                                <td><c:out value="${audio.length}"/></td>
                                                <td><c:out value="${audio.lentThrough}"/></td>
                                                <td>
                                                    <a href="./detail/audio.html">
                                                        <i class="fa fa-book" aria-hidden="true"></i> Details
                                                    </a>
                                                </td>
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

        <footer class="container">
            <hr>
            <p>&copy; SimpleLibrary 2020</p>
        </footer>

    </body>
</html>
