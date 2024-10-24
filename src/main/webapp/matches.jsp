<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Matches Page</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<%@ include file="WEB-INF/pages/header.jsp" %>

<div class="matches">
    <h1 class="matches_title">Matches</h1>

    <form class="matches_form" method="post" action="matches">
        <input type="text" name="filter_by_player_name" placeholder="Player name" value="${playerName}">
        <button type="submit">Send</button>
        <button type="button" onclick="document.getElementsByName('filter_by_player_name')[0].value = '';">Reset
        </button>
    </form>

    <table class="matches_table">
        <tr>
            <th>Match ID</th>
            <th>Player 1</th>
            <th>Player 2</th>
            <th>Winner</th>
        </tr>
        <c:forEach var="match" items="${matches}">
            <tr>
                <td>${match.id}</td>
                <td>${match.player1.name}</td>
                <td>${match.player2.name}</td>
                <td>${match.winner.name}</td>
            </tr>
        </c:forEach>
    </table>

    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <c:choose>
                <c:when test="${not empty playerName}">
                    <a href="?page_number=${currentPage - 1}&filter_by_player_name=${playerName}">Prev</a>
                </c:when>
                <c:otherwise>
                    <a href="?page_number=${currentPage - 1}">Prev</a>
                </c:otherwise>
            </c:choose>
        </c:if>
        <span class="page-info">Page ${currentPage} of ${totalPages}</span>
        <c:if test="${currentPage < totalPages}">
            <c:choose>
                <c:when test="${not empty playerName}">
                    <a href="?page_number=${currentPage + 1}&filter_by_player_name=${playerName}">Next</a>
                </c:when>
                <c:otherwise>
                    <a href="?page_number=${currentPage + 1}">Next</a>
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>
</div>
</body>
</html>
