<%--
  Created by IntelliJ IDEA.
  User: ivan_medvedev
  Date: 23.05.2021
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.io.*, logic.*, dao.*, java.util.Collection, java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Service history</title>
</head>

<body>
<p> <a href="/">To main page</a> </p>

<h2>Service history</h2>
<table>
    <tr>
        <th>Start date</th>
        <th>Finish date</th>
        <th>Price</th>
        <th>Service</th>
    </tr>
    <c:forEach var="serviceHistory" items="${serviceHistoryList}">
        <tr>
            <td>${serviceHistory.getStartDate()}</td>
            <td>${serviceHistory.getFinishDate()}</td>
            <td>${serviceHistory.getPrice()}</td>
            <td>${serviceHistory.getService().getName()}</td>
        </tr>
    </c:forEach>
</table>

<h2>Add</h2>
<a href="/addContract/${id}">Add new service to history</a>
</body>
</html>