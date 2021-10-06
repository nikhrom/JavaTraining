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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        out.write("<h1> Пассажиры перелёта №%s: </h1>".formatted(flightId));
        out.write("<ul>");

        List<TicketDto> tickets = TicketService.getInstance()
                .findAll(TicketFilter.builder().flightId(flightId).build());

        for(TicketDto ticket: tickets){
            out.write("<li> %s </li>".formatted(ticket.getDescription()));
        }

        out.write("</ul>");
    %>

</body>
</html>
