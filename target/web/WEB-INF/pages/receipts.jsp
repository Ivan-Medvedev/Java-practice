<%--
  Created by IntelliJ IDEA.
  User: ivan_medvedev
  Date: 23.05.2021
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.io.*, logic.*, dao.*, java.util.Collection, java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Receipts page</title>
    </head>

    <body>
        <p> <a href="/">To main page</a> </p>

        <h2>Receipts</h2>
        <table>
            <tr>
                <th>Date</th>
                <th>Value</th>
            </tr>
            <c:forEach var="receipt" items="${receiptsList}">
                <tr>
                    <td>${receipt.getReceiptDate()}</td>
                    <td>${receipt.getAmount()}</td>
                </tr>
            </c:forEach>
        </table>

        <h2>Add</h2>
        <a href="/addReceipt/${id}">Add new receipt</a>
    </body>
</html>