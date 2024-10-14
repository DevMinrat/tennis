<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        body {
            background-color: #1b1b1b;
            color: white;
        }
    </style>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form action="new-match" method="post">
    <label>
        Player 1:
        <input type="text" name="player1" id="player1">
    </label>
    <br>
    <label>
        Player 2:
        <input type="text" name="player2" id="player2">
    </label>
    <br>
    <button type="submit">Start match</button>
</form>

</body>
</html>