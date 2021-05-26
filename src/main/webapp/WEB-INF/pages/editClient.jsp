<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit client</title>
    </head>

    <body>
    <p> <a href="/">To main page</a> </p>

    <br><br>

    <input type="button" onclick="history.back();" value="Back"/><br>
    <br>
    <b>Edit client</b><br><br>
        <form:form method="post" modelAttribute="client" action="/editClient">
            <form:hidden path="id"/>
            <b>Name</b><br>
            <form:input path="name" value="${client.name}"/><br>
            <b>Type</b><br>
            <form:input path="type" value="${client.type}"/><br>
            <b>Address</b><br>
            <form:input path="address" value="${client.address}"/><br>
            <b>Email</b><br>
            <form:input path="e_mail" value="${client.e_mail}"/><br>
            <b>Phone</b><br>
            <form:input path="phone" value="${client.phone}"/><br>
            <b>Balance</b><br>
            <form:input path="balance" value="${client.balance}"/><br>
            <b>Max loan</b><br>
            <form:input path="maxLoan" value="${client.maxLoan}"/><br>
            <b>Max loan time</b><br>
            <form:input path="maxLoanTime" value="${client.maxLoanTime}"/><br>
            <br>
            <form:button value="Edit client">Edit</form:button>
        </form:form>
    </body>
</html>