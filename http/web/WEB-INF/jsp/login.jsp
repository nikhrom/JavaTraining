<%--
  Created by IntelliJ IDEA.
  User: nikhrom
  Date: 26.10.2021
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <fmt:setBundle basename="translations"/>
    <fmt:setLocale value="ru_RU"/>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="emailId"> <fmt:message key="page.login.email"/>:
            <input aria-label="email" name="email" id="emailId" type="text" value="${param.email}">
        </label><br>
        <label for="passwordId"> <fmt:message key="page.login.password"/>:
            <input aria-label="password" name="password" id="passwordId" type="password">
        </label><br>

        <button type="submit"><fmt:message key="page.login.submit.button"/></button>
        <a href="${pageContext.request.contextPath}/registration">
            <button type="button"><fmt:message key="page.login.register.button"/></button>
        </a>
    </form>

    <c:if test="${param.error != null}">
        <div style="color: red">
            <p><fmt:message key="page.login.error"/></p>
        </div>
    </c:if>

</body>
</html>
