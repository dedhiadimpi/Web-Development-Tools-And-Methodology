<%-- 
    Document   : browseMovie
    Created on : Feb 26, 2020, 3:54:41 PM
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
        <form action="movieBrowse.htm">
            Keyword: <input type="text" name="keyword"/><br><br>
            <input type="radio" name="search" value="title"/>Search By Title<br><br>
            <input type="radio" name="search" value="actor"/>Search By Actor<br><br>
            <input type="radio" name="search" value="actress"/>Search By Actress<br><br>
            <input type="submit" value="Search Movies"><br><br>
            <input type="hidden" name="option" value="getMovies"/>
        </form>
    </body>
</html>
