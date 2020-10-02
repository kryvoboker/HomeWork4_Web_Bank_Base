<%--
  Created by IntelliJ IDEA.
  User: kamaz
  Date: 01.10.2020
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Total amount money in UAH</title>
</head>
<body>

<jsp:include page="table_of_currencys.jsp"></jsp:include>
<br>

<table border="1">

    <th>Id</th>
    <th>Name</th>
    <th>Money</th>

<c:forEach var="list" items="${totalList}">

    <tr>
        <td>
            <c:out value="${list.getId()}"/>
        </td>
        <td>
            <c:out value="${list.getName()}"/>
        </td>
        <td>
            <c:out value="${list.getMoney()}"/>
        </td>
    </tr>

</c:forEach>

</table>

<form name="totalMoney" action="totalMoney" method="post">

    <input type="text" name="userId">Enter user Id for show total money<br>
    <input type="submit" value="Ok">

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
