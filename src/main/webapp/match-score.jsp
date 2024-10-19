<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Match Score</title>
    <style>
        body {
            background-color: #1b1b1b;
            color: white;
        }
    </style>
</head>
<body>
<h1><%= "Match Score" %>
</h1>
<br/>
<h2>Match between ${match.player1.getName()} and ${match.player2.getName()}</h2>
<p>Player 1: ${player1Sets} sets</p>
<p>Player 2: ${player2Sets} sets</p>
<br>
<p>Player 1: ${player1Games} games</p>
<p>Player 2: ${player2Games} games</p>
<br>
<p>Player 1: ${player1PointsDisplay} points</p>
<p>Player 2: ${player2PointsDisplay} points</p>

<br>
<form method="post" action="match-score">
    <button type="submit" name="winner" value="player1">Player 1 won point</button>
</form>
<form method="post" action="match-score">
    <button type="submit" name="winner" value="player2">Player 2 won point</button>
</form>
</body>
</html>