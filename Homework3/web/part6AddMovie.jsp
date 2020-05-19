<%-- 
    Document   : part6AddMovie
    Created on : Feb 13, 2020, 8:25:52 PM
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
        <h3>Please enter the details below:</h3>
        <form method="GET" action="part6AddMovie">
            Movie Title: <input type="text" name="title"/><br><br>
            Lead Actor: <input type="text" name="actor"/><br><br>
            Lead Actress: <input type="text" name="actress"/><br><br>
            Genre: <input type="text" name="genre"/><br><br>
            Year: <input type="text" name="year"/><br><br>
            <input type="submit" value="Add Movie"/>
        </form>
    </body>
</html>
