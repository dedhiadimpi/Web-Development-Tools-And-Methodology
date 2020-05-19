<%-- 
    Document   : newjsppart7-table
    Created on : Feb 14, 2020, 2:05:38 PM
    Author     : dedhi
--%>

<%@page import="javax.websocket.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <form method="GET" action="part7">
            <table>
                <tr>
                    <th>ISBN</th>
                    <th>Book Title</th>
                    <th>Authors</th>
                    <th>Price</th>
                </tr>
                <%
                    int books = Integer.parseInt(request.getParameter("books"));
                    int i=1;
                    while(i <= books){
                        out.println("<tr>");
                        out.println("<td><input type='text' name='isbn"+i+"'></td>");
                        out.println("<td><input type='text' name='title"+i+"'></td>");
                        out.println("<td><input type='text' name='authors"+i+"'></td>");
                        out.println("<td><input type='text' name='price"+i+"'></td>");
                        out.println("</tr>");
                        i++;
                    }
                    session.setAttribute("books", books);
                %>
            </table><br><br>
            <input type="submit" value="Add Books">
        </form>
    </body>
</html>
