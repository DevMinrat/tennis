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
<h1><%= "Start page!" %>
</h1>
<br/>
<a href="match-score">Hello Servlet</a>
<br>
<br/>
<a href="matches">Matches</a>
<br>
<a href="${pageContext.request.contextPath}/new-match.jsp">New Match</a>

</form>


</body>
</html>