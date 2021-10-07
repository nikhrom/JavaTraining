<%@ page import="com.github.nikhrom.javatraining.http.practice.service.TicketService" %>
<%@ page import="com.github.nikhrom.javatraining.http.practice.dto.TicketFilter" %>
<%@ page import="com.github.nikhrom.javatraining.http.practice.dto.TicketDto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nikhrom
  Date: 06.10.2021
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:choose>
        <c:when test="${requestScope.tickets.size() == 0}">
            <h1>Пассажиры у перелёта №${paramValues["flightId"][0]} отсутствуют!</h1>
        </c:when>
        <c:otherwise>
            <h1>Пассажиры перелёта №${paramValues["flightId"][0]} :</h1>
            <ul>
                <c:forEach var="ticket" items="${requestScope.tickets}">
                    <li>${fn:toLowerCase(ticket.description)}</li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>

</body>
</html>
