<%--
  Created by IntelliJ IDEA.
  User: kamaz
  Date: 28.09.2020
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Do transfer</title>
</head>
<body>

<jsp:include page="money_accounts.jsp"></jsp:include>

<h1>Transfare money</h1>

<form name="transfareMoney" method="post" action="/HomeWork4_Web_Bank_Base_war/transfare">

    <input name="userIdOut" type="text">Choose user id for out transfer<br>
    <input name="userIdIn" type="text">Choose user id for take money<br>
    <input name="currencyTransfare" type="text">Choose currency for transfer<br>
    <input name="amountTransfare" type="text">Enter amount money for transfer<br>
    <input name="ok" value="GO!" type="submit">

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
