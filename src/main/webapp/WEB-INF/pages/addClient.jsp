<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add client</title>
    </head>

    <body>
        <p> <a href="/">To main page</a> </p>

        <br><br>

        <input type="button" onclick="history.back();" value="Back"/>

        <br><br>

        <b>Add client</b><br><br>
        <form:form method="post" modelAttribute="client" action="/addClient" title="Add client">
            <form:hidden path="id"/>
            <b>Name</b><br>
            <form:input path="name"/><br>
            <b>Type</b><br>
            <form:input path="type"/><br>
            <b>Address</b><br>
            <form:input path="address"/><br>
            <b>Email</b><br>
            <form:input path="e_mail"/><br>
            <b>Phone</b><br>
            <form:input path="phone"/><br>
            <b>Balance</b><br>
            <form:input path="balance"/><br>
            <b>Max loan</b><br>
            <form:input path="maxLoan"/><br>
            <b>Max loan time</b><br>
            <form:input path="maxLoanTime"/><br>
            <br>
            <form:button value="Add client">Add</form:button>
        </form:form>
    </body>
</html>