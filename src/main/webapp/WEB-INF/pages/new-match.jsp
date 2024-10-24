<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>New Match Page</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<%@ include file="header.jsp" %>

<main>
    <h1>Create New Match</h1>
    <form class="new-match_form" action="new-match" method="post">
        <label>
            Player 1:
            <input type="text" name="player1" id="player1" required>
        </label>
        <label>
            Player 2:
            <input type="text" name="player2" id="player2" required>
        </label>
        <button type="submit">Start Match</button>
    </form>
</main>

</body>
</html>
