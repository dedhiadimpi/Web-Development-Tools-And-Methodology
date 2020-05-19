<%-- 
    Document   : part6BrowseMovie
    Created on : Feb 13, 2020, 9:42:09 PM
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
        <h3> Searching Movies </h3>
        <form method="GET" action="part6BrowseMovie">
            Keyword: <input type="text" name="keyword"/><br><br>
            <input type="radio" name="search" value="title"/>Search By Title<br><br>
            <input type="radio" name="search" value="actor"/>Search By Actor<br><br>
            <input type="radio" name="search" value="actress"/>Search By Actress<br><br>
            <input type="submit" value="Search Movies"><br><br>
        </form>
    </body>
</html>
