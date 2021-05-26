<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.io.*, logic.*, dao.*, java.util.Collection, java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Clients page</title>
</head>

    <body>
    <p> <a href="/">To main page</a> </p>
    <h2>Services</h2>
        <table>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Internet</th>
                <th>Call</th>
                <th>Sms</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            <c:forEach var="service" items="${servicesList}">
                <tr>
                    <td>${service.getId()}</td>
                    <td>${service.getName()}</td>
                    <td>${service.getInternetAmount()}</td>
                    <td>${service.getCallMinutes()}</td>
                    <td>${service.getSmsAmount()}</td>
                    <td>${service.getMonthPrice()}</td>
                    <td>
                        <a href="/deleteService/${service.id}">delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <h2>Add</h2>
        <a href="/addService">Add new service</a>
    </body>
</html>