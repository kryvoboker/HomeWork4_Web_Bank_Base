<%--
  Created by IntelliJ IDEA.
  User: kamaz
  Date: 27.09.2020
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>

<h2>All users</h2>

<jsp:include page="all_users.jsp"></jsp:include>

<h1>Enter name of new user</h1>

<form method="post" action="/HomeWork4_Web_Bank_Base_war/add_user" name="addUser">
    <label><input type="text" name="nameUser"></label>Имя<br>
    <label><input type="submit" value="OK" name="OK"></label><br>
</form>

<a href="/HomeWork4_Web_Bank_Base_war/index.html">Back to menu</a>
<br>

<div>
    <c:if test="${error eq true}">
        <h1><c:out value="${error_msg}"/></h1>
    </c:if>
</div>

</body>
</html>
