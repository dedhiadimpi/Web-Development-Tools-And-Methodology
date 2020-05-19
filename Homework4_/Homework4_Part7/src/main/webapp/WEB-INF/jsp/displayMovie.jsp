<%-- 
    Document   : displayMovie
    Created on : Feb 26, 2020, 3:54:59 PM
    Author     : dedhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h4>You searched for '${keyword}'</h4>
        <h3>Here are your search results</h3>
        <table border="1">
            <tr>
                <th>Movie Id</th>
                <th>Title</th>
                <th>Actor</th>
                <th>Actress</th>
                <th>Genre</th>
                <th>Year</th>
            </tr>
            <c:forEach var="movie" items="${movieList}">
                <tr>
                    <td>${movie.getMovieId()}</td>
                    <td>${movie.getTitle()}</td>
                    <td>${movie.getActor()}</td>
                    <td>${movie.getActress()}</td>
                    <td>${movie.getGenre()}</td>
                    <td>${movie.getYear()}</td>
                    <td><a href="delete.htm?option=delete&movieId=${movie.getMovieId()}">Delete</a></td>
                    <td><a href="update.htm?option=update&movieId=${movie.getMovieId()}">Update</a></td>
                </tr>
            </c:forEach>             
        </table>
        <br>
        <br>
        <a href='index.htm'>Click here to go back to the main page</a>
    </body>
</html>
