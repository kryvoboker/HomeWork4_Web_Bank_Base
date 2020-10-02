<%--
  Created by IntelliJ IDEA.
  User: kamaz
  Date: 28.09.2020
  Time: 1:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All transactions</title>
</head>
<body>

<h1>All transactions</h1>

<table border="1">

    <th>Id</th>
    <th>Name</th>
    <th>cashOut</th>
    <th>currencyOut</th>
    <th>cashTake</th>
    <th>currencyTake</th>
    <th>date</th>
    <th>cashConversionOut</th>
    <th>currencyConversionOut</th>
    <th>currencyConversionIn</th>
    <th>cashConversionIn</th>

    <c:forEach items="${transaction}" var="trans">
        <tr>
            <td>
                <c:out value="${trans.getId()}"/>
            </td>
            <td>
                <c:out value="${trans.getUser()}"/>
            </td>
            <td>
                <c:out value="${trans.getCashOut()}"/>
            </td>
            <td>
                <c:out value="${trans.getCurrencyOut()}"/>
            </td>
            <td>
                <c:out value="${trans.getCashTake()}"/>
            </td>
            <td>
                <c:out value="${trans.getCurrencyTake()}"/>
            </td>
            <td>
                <c:out value="${trans.getDate()}"/>
            </td>
            <td>
                <c:out value="${trans.getCashConversionOut()}"/>
            </td>
            <td>
                <c:out value="${trans.getCurrencyConversionOut()}"/>
            </td>
            <td>
                <c:out value="${trans.getCurrencyConversionIn()}"/>
            </td>
            <td>
                <c:out value="${trans.getCashConversionIn()}"/>
            </td>

        </tr>
    </c:forEach>
</table>

<a href="/HomeWork4_Web_Bank_Base_war/index.html">Back to menu</a>

  </body>
</html>
