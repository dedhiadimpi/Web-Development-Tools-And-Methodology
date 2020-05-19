<%-- 
    Document   : updateMovie
    Created on : Feb 26, 2020, 6:09:19 PM
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
        <h3>Please update the details below:</h3>
        <form action="updateMovie.htm">
            Movie Id: <input type="text" name="movieId" value="${movie.getMovieId()}" readonly/><br><br>
            Movie Title: <input type="text" name="title" value= "${movie.getTitle()}"/><br><br>
            Lead Actor: <input type="text" name="actor" value= "${movie.getActor()}"/><br><br>
            Lead Actress: <input type="text" name="actress" value= "${movie.getActress()}"/><br><br>
            Genre: <input type="text" name="genre" value= "${movie.getGenre()}"/><br><br>
            Year: <input type="text" name="year" value= "${movie.getYear()}"/><br><br>
            <input type="submit" value="Update Movie"/>
            <input type="hidden" name="option" value="updateMovie"/>
        </form>
    </body>
</html>
