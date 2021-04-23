<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.io.*, logic.*, dao.*, java.util.Collection, java.util.Iterator"%>
<html>
    <head>
        <title>Clients page</title>
    </head>

    <body>
        <p> <h1 align="center"> <a href="/">Main</a> </p>
            <form method="get" action="/">
                Name:<input type="text" name="name">
                <input type="submit" value="Filter">
            </form>
        </h1>
        <%
            Collection clients = Factory.getInstance().getClientDAO().findAll();
            Iterator iterator = clients.iterator();
            while (iterator.hasNext()) {
                Client client = (Client) iterator.next();
                out.println("<br>" + "<a href=/?id=" + client.getId() + ">" + client.getName() + "</a>");
            }
        %>
    </body>
</html>