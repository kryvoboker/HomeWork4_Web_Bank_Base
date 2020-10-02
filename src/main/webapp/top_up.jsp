<%--
  Created by IntelliJ IDEA.
  User: kamaz
  Date: 27.09.2020
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Top up account</title>
</head>
<body>

<jsp:include page="money_accounts.jsp"></jsp:include>

<h1>Top up account</h1>

<form method="post" action="/HomeWork4_Web_Bank_Base_war/top_up" name="moneyAccount">

    <input name="userId" type="text">Enter id of user<br>
    <input name="currency" type="text">Choose currency (USD, EUR or UAH)<br>
    <input name="amount" type="text">Enter amount<br>
    <input name="ok" type="submit" value="OK">

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
