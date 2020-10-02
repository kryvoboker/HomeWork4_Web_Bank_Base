<%--
  Created by IntelliJ IDEA.
  User: kamaz
  Date: 27.09.2020
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All money accounts</title>
</head>
<body>

<table border="1">

    <th>Id</th>
    <th>Name</th>
    <th>UAH</th>
    <th>USD</th>
    <th>EUR</th>

    <c:forEach items="${allMonAcc}" var="acc">
        <tr>
            <td>
                <c:out value="${acc.getId()}"/>
            </td>
            <td>
                <c:out value="${acc.getNameOfUser()}"/>
            </td>
            <td>
                <c:out value="${acc.getCashInUAH()}"/>
            </td>
            <td>
                <c:out value="${acc.getCashInUSD()}"/>
            </td>
            <td>
                <c:out value="${acc.getCashInEUR() }"/>
            </td>

        </tr>
    </c:forEach>
</table>

  </body>
</html>
