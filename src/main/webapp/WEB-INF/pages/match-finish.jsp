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
<h1><%= "Match Finished" %>
</h1>
<br/>
<h2>${match.getWinner().getName()} won!</h2>
<p>Player 1: ${match.player1.getName()}</p>
<p>Player 2: ${match.player2.getName()}</p>
<br>
<p>Player 1: ${player1Sets} sets</p>
<p>Player 2: ${player2Sets} sets</p>
</body>
</html>