<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Match Finished Page</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<%@ include file="header.jsp" %>

<main>
    <h1>Match Finished</h1>

    <div class="result">
        <h2>${match.getWinner().getName()} won!</h2>
        <div class="player">
            <p>${match.player1.getName()}: ${player1Sets} sets</p>
        </div>
        <div class="player">
            <p>${match.player2.getName()}: ${player2Sets} sets</p>
        </div>
    </div>

    <a class="back-link" href="matches">Back to Matches</a>
</main>

</body>
</html>
