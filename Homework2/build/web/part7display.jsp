<%-- 
    Document   : part7display
    Created on : Feb 4, 2020, 3:52:47 PM
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
        <h1>My Profile</h1>
        Email: <%= request.getParameter("email")%><br>
        Password: <%= request.getParameter("password")%><br>
        Confirm Password:<%= request.getParameter("confirmpassword")%><br>
        Gender:<%= request.getParameter("gender")%><br>
        <%
            String hobbycricket = request.getParameter("hobbycricket");
            String hobbyfootball = request.getParameter("hobbyfootball");
            if(hobbycricket != null && hobbyfootball != null){
            out.println("Hobby: Cricket, Football <br>");
            }else if(hobbyfootball != null){
                out.println("Hobby: Football <br>");
            }else if(hobbycricket != null){
                out.println("Hobby: Cricket <br>");
            }
        %>
        Country: <%= request.getParameter("country")%><br>
        Address: <%= request.getParameter("address")%><br>
    </body>
</html>
