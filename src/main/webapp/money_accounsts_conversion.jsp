<%--
  Created by IntelliJ IDEA.
  User: kamaz
  Date: 29.09.2020
  Time: 17:10
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

    <c:forEach items="${monAcc}" var="accr">
        <tr>
            <td>
                <c:out value="${accr.getId()}"/>
            </td>
            <td>
                <c:out value="${accr.getNameOfUser()}"/>
            </td>
            <td>
                <c:out value="${accr.getCashInUAH()}"/>
            </td>
            <td>
                <c:out value="${accr.getCashInUSD()}"/>
            </td>
            <td>
                <c:out value="${accr.getCashInEUR() }"/>
            </td>

        </tr>
    </c:forEach>
</table>

</body>
</html>