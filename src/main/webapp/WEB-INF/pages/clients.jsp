<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.io.*, logic.*, dao.*, java.util.Collection, java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Clients page</title>
    </head>

    <body>
        <p> <a href="/">To main page</a> </p>
<%--        <p> <h1 align="center"> Clients </a> </p>
            <form method="get" action="/">
                Name:<input type="text" name="name">
                <input type="submit" value="Find">
            </form>
        </h1>--%>

        <h2>Clients</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Name</th>
                <th>Address</th>
                <th>Phone</th>
                <th>E-mail</th>
                <th>Balance</th>
                <th>MaxLoan</th>
                <th>MaxLoanTime</th>
                <th>Receipts</th>
                <th>Services</th>
                <th>Action</th>
            </tr>
            <c:forEach var="client" items="${clientsList}">
                <tr>
                    <td>${client.getId()}</td>
                    <td>${client.getType()}</td>
                    <td>${client.getName()}</td>
                    <td>${client.getAddress()}</td>
                    <td>${client.getPhone()}</td>
                    <td>${client.getE_mail()}</td>
                    <td>${client.getBalance()}</td>
                    <td>${client.getMaxLoan()}</td>
                    <td>${client.getMaxLoanTime()}</td>
                    <td><a href="<c:url value="/receipts/${client.id}"/>">look & add</a></td>
                    <td><a href="<c:url value="/serviceHistory/${client.id}"/>">look & edit</a></td>
                    <td>
                        <a href="<c:url value="/editClient/${client.id}"/>">edit</a>
                        <a href="/deleteClient/${client.id}">delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <h2>Add</h2>
        <a href="/addClient">Add new client</a>
    </body>
</html>