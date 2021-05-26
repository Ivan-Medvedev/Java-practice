<%--
  Created by IntelliJ IDEA.
  User: ivan_medvedev
  Date: 24.05.2021
  Time: 0:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.io.*, logic.*, dao.*, java.util.Collection, java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add contract page</title>
</head>

    <body>
        <p> <a href="/">To main page</a> </p>
        <h2>Choose service</h2>
        <table>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Internet</th>
                <th>Call</th>
                <th>Sms</th>
                <th>Price</th>
            </tr>
            <c:forEach var="service" items="${services}">
                <tr>
                    <td>${service.getId()}</td>
                    <td>${service.getName()}</td>
                    <td>${service.getInternetAmount()}</td>
                    <td>${service.getCallMinutes()}</td>
                    <td>${service.getSmsAmount()}</td>
                    <td>${service.getMonthPrice()}</td>
                </tr>
            </c:forEach>
        </table>
        <form:form method="post" modelAttribute="contract" action="/addContract/${id}">
            <b>Choose id</b><br>
            <form:input path="id"/><br>
            <b>Start date yyyy-mm-dd</b><br>
            <form:input path="startDate"/><br>
            <b>Finish date yyyy-mm-dd</b><br>
            <form:input path="finishDate"/><br>
            <b>Price</b><br>
            <form:input path="price"/><br>
            <br>
            <form:button value="addContract">Add</form:button>
        </form:form>

    </body>
</html>