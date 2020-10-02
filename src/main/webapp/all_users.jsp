<%--
  Created by IntelliJ IDEA.
  User: kamaz
  Date: 27.09.2020
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="ru.live.kamaz_cs.buildTables.Users" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All users</title>
</head>
<body>

<table border="1">

    <th>Id</th>
    <th>Name</th>

<c:forEach items="${allUsers}" var="user">
<tr>
    <td>
        <c:out value="${user.getId()}"/>
    </td>
    <td>
        <c:out value="${user.getName()}"/>
    </td>

</tr>
</c:forEach>
</table>

  </body>
</html>
