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

<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="name">Name:
        <input type="text" name="name" id="name">
    </label><br>
    <label for="birthday">Birthday:
        <input type="date" name="birthday" id="birthday">
    </label><br>
    <label for="email">email:
        <input type="text" name="email" id="email">
    </label><br>
    <label for="password">password:
        <input type="password" name="password" id="password">
    </label><br>
    <button type="submit">Send</button>
</form>

</body>
</html>
