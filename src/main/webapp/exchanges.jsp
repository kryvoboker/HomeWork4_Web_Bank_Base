<%--
  Created by IntelliJ IDEA.
  User: kamaz
  Date: 29.09.2020
  Time: 2:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Do currency conversion</title>
</head>
<body>

<jsp:include page="table_of_currencys.jsp"></jsp:include>
<br>
<jsp:include page="money_accounsts_conversion.jsp"></jsp:include>


<h1>Oparations of currency conversion</h1>

<form name="conversionsMoney" method="post" action="/HomeWork4_Web_Bank_Base_war/exchanges">

    <input name="userId" type="text">Enter user id for conversion<br>
    <input name="currencyOut" type="text">Enter currency for out conversion<br>
    <input name="currencyIn" type="text">Enter currency for in conversion<br>
    <input name="amountExchange" type="text">Enter amount money for conversion<br>
    <input name="submitConversion" type="submit" value="GO!">

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
