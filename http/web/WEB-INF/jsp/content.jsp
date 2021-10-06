<%--
  Created by IntelliJ IDEA.
  User: nikhrom
  Date: 06.10.2021
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="header.jsp"%>
    <div>
        <span>Content. Русский</span>
        <p>Size: ${requestScope.flights.size()}</p>
        <p>Id: ${requestScope.flights[1].id}</p>
        <p>description: ${sessionScope.flightsMap["1"]}</p>
        <p>JSESSION ID: ${cookie["JSESSIONID"]}</p>
        <p>Cookie: ${header["Cookie"]}</p>
        <p>Param id: ${param.id}</p>
        <p>Param test: ${param.test}</p>
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>
