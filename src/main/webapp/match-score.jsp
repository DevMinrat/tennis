<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Match Score</title>
</head>
<body>
<h1><%= "Match Score" %>
</h1>
<br/>
<h1>Match between ${match.player1.getName()} and ${match.player2.getName()}</h1>
<p>Player 1: ${match.matchScore.player1Sets} sets</p>
<p>Player 2: ${match.matchScore.player2Sets} sets</p>
<br>
<p>Player 1: ${match.matchScore.player1Games} games</p>
<p>Player 2: ${match.matchScore.player2Games} games</p>
<br>
<p>Player 1: ${match.matchScore.player1Points} points</p>
<p>Player 2: ${match.matchScore.player2Points} points</p>

<br>
<form method="post" action="match-score">
    <button type="submit" name="winner" value="player1">Player 1 won point</button>
</form>
<form method="post" action="match-score">
    <button type="submit" name="winner" value="player2">Player 2 won point</button>
</form>
</body>
</html>