<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nikhrom
  Date: 07.10.2021
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head> 
<body>

    <img src="${pageContext.request.contextPath}/images/users/42.jpg" alt="User image">

    <form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
        <label for="name">Name:
            <input type="text" name="name" id="name">
        </label><br>
        <label for="birthday">Birthday:
            <input type="date" name="birthday" id="birthday">
        </label><br>
        <label for="imageId">Image:
            <input type="file" name="image" id="imageId"><br>
        </label>
        <label for="email">email:
            <input type="text" name="email" id="email">
        </label><br>
        <label for="password">password:
            <input type="password" name="password" id="password">
        </label><br>
        <label for="role">role:
            <select name="role" id="role">
                <c:forEach var="role" items="${requestScope.roles}">
                    <option value="${role}">${role}</option>
                </c:forEach>
            </select>
        </label></br>
        <c:forEach var="gender" items="${requestScope.genders}">
            <input type="radio" name="gender" value="${gender}"> ${gender}
        </c:forEach>
        <br>
        <button type="submit">Send</button>
    </form>
    <c:choose>
        <c:when test="${empty requestScope.errors && requestScope.errors ne null}">
            <div>
                <span style="color: green">Успешно</span>
            </div>
        </c:when>
        <c:otherwise>
            <div>
                <c:forEach var="error" items="${requestScope.errors}">
                    <span style="color: red">${error.message}</span> <br>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>


</body>
</html>
