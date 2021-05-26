<%--
  Created by IntelliJ IDEA.
  User: ivan_medvedev
  Date: 23.05.2021
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add service</title>
</head>

<body>
<p> <a href="/">To main page</a> </p>

<br><br>

<input type="button" onclick="history.back();" value="Back"/>

<br><br>

<b>Add service</b><br><br>
<form:form method="post" modelAttribute="receipt" action="/addReceipt/${id}">
    <form:hidden path="id"/>
    <b>Date yyyy-mm-dd</b><br>
    <form:input path="receiptDate"/><br>
    <b>Value</b><br>
    <form:input path="amount"/><br>
    <br>
    <form:button value="Add receipt">Add</form:button>
</form:form>
</body>
</html>