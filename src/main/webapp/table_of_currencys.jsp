<%--
  Created by IntelliJ IDEA.
  User: kamaz
  Date: 29.09.2020
  Time: 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Table of currencys</title>
</head>
<body>

<table border="1">

    <th>Id</th>
    <th>uahForBuyinUSD</th>
    <th>usdForSaleinUAH</th>
    <th>uahForBuyEUR</th>
    <th>eurForSaleinUAH</th>
    <th>eurForBuyinUSD</th>
    <th>usdForBuyinEUR</th>

    <c:forEach items="${currencyExc}" var="accc">
        <tr>
            <td>
                <c:out value="${accc.getId()}"/>
            </td>
            <td>
                <c:out value="${accc.getUahForBuyinUSD()}"/>
            </td>
            <td>
                <c:out value="${accc.getUsdForSaleinUAH()}"/>
            </td>
            <td>
                <c:out value="${accc.getUahForBuyEUR()}"/>
            </td>
            <td>
                <c:out value="${accc.getEurForSaleinUAH()}"/>
            </td>
            <td>
                <c:out value="${accc.getEurForBuyinUSD()}"/>
            </td>
            <td>
                <c:out value="${accc.getUsdForBuyinEUR()}"/>
            </td>
        </tr>
    </c:forEach>
</table>

  </body>
</html>
