<%-- 
    Document   : part6DisplayMovies
    Created on : Feb 13, 2020, 9:57:49 PM
    Author     : dedhi
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.part6.model.Movies"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table,th,td{
                border: 1px solid black;
            }
        </style>
    </head>
    <body>
        <h4>You searched for "${requestScope.keyword}"</h4>
        <h3>Here are your search results</h3>
        <table>
            <tr>
                <th>Title</th>
                <th>Actor</th>
                <th>Actress</th>
                <th>Genre</th>
                <th>Year</th>
            </tr>
            <c:forEach var="movie" items="${requestScope.movieList}">
                <tr>
                    <td>${movie.getTitle()}</td>
                    <td>${movie.getActor()}</td>
                    <td>${movie.getActress()}</td>
                    <td>${movie.getGenre()}</td>
                    <td>${movie.getYear()}</td>
                </tr>
            </c:forEach>
            <%--
                ArrayList<Movies> movieList = (ArrayList<Movies>) request.getAttribute("movieList");
                for(Movies m : movieList){
                    out.println("<tr>");
                    out.println("<td>"+m.getTitle()+"</td>");
                    out.println("<td>"+m.getActor()+"</td>");
                    out.println("<td>"+m.getActress()+"</td>");
                    out.println("<td>"+m.getGenre()+"</td>");
                    out.println("<td>"+m.getYear()+"</td>");
                    out.println("</tr>");
                }
                
            --%>                
        </table>
        <br>
        <br>
        <a href='part6-welcome.jsp'>Click here to go back to the main page</a>
    </body>
</html>
