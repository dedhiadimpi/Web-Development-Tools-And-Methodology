<%-- 
    Document   : addMovie
    Created on : Feb 26, 2020, 3:54:50 PM
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
        <form action="addMovie.htm">
            Movie Title: <input type="text" name="title"/><br><br>
            Lead Actor: <input type="text" name="actor"/><br><br>
            Lead Actress: <input type="text" name="actress"/><br><br>
            Genre: <input type="text" name="genre"/><br><br>
            Year: <input type="text" name="year"/><br><br>
            <input type="submit" value="Add Movie"/>
            <input type="hidden" name="option" value="addMovie"/>
        </form>
    </body>
</html>
