<%-- 
    Document   : part6-welcome
    Created on : Feb 13, 2020, 7:41:49 PM
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
        <h1>Welcome to our Movie Store</h1>
        <h5>Please make your selection below</h5>
        <form method="GET" action="part6">
            <select name="select">
                <option value="1">Browse Movies</option>
                <option value="2">Add new Movie</option>
            </select>
            <input type="submit" value="Send"/>
        </form>
    </body>
</html>
