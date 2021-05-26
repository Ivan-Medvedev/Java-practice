<%--
  Created by IntelliJ IDEA.
  User: ivan_medvedev
  Date: 23.05.2021
  Time: 3:23
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
        <form:form method="post" modelAttribute="service" action="/addService">
            <form:hidden path="id"/>
            <b>Name</b><br>
            <form:input path="name"/><br>
            <b>Internet amount (Gb)</b><br>
            <form:input path="InternetAmount"/><br>
            <b>Amount minutes of call</b><br>
            <form:input path="callMinutes"/><br>
            <b>SMS amount</b><br>
            <form:input path="smsAmount"/><br>
            <b>Month Price</b><br>
            <form:input path="monthPrice"/><br>
            <br>
            <form:button value="Add service">Add</form:button>
        </form:form>
    </body>
</html>