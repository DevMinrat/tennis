<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Match Score Page</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<%@ include file="WEB-INF/pages/header.jsp" %>

<main>
    <h1>Match Score</h1>
    <h2>Match between ${match.player1.getName()} and ${match.player2.getName()}</h2>

    <table class="match-score_table">
        <tr>
            <th></th>
            <th>${match.player1.getName()}</th>
            <th>${match.player2.getName()}</th>
        </tr>
        <tr>
            <td>Sets</td>
            <td>${player1Sets}</td>
            <td>${player2Sets}</td>
        </tr>
        <tr>
            <td>Games</td>
            <td>${player1Games}</td>
            <td>${player2Games}</td>
        </tr>
        <tr>
            <td>Points</td>
            <td>${player1PointsDisplay}</td>
            <td>${player2PointsDisplay}</td>
        </tr>
    </table>

    <div class="score-buttons">
        <form method="post" action="match-score">
            <button type="submit" name="winner" value="player1">
                ${match.player1.getName()} won point
            </button>
        </form>

        <form method="post" action="match-score">
            <button type="submit" name="winner" value="player2">
                ${match.player2.getName()} won point
            </button>
        </form>
    </div>
</main>

</body>
</html>
