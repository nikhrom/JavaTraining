<%--
  Created by IntelliJ IDEA.
  User: nikhrom
  Date: 26.10.2021
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="emailId"> Email:
            <input aria-label="email" name="email" id="emailId" type="text">
        </label><br>
        <label for="passwordId"> Password:
            <input aria-label="password" name="password" id="passwordId" type="password">
        </label><br>

        <button type="submit">Login</button>
        <a href="${pageContext.request.contextPath}/registration">
            <button type="button">Registration</button>
        </a>
    </form>

</body>
</html>
