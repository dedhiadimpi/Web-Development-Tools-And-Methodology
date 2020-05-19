<%-- 
    Document   : part7-welcome
    Created on : Feb 14, 2020, 2:02:28 PM
    Author     : dedhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="GET" action="part7-table.jsp">
            <h2>How many books do you wish to add?</h2>
            <input type="text" name="books"/>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
