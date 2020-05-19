<%-- 
    Document   : login
    Created on : Feb 17, 2020, 8:16:11 PM
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
        <form method="POST" action="loginController">
            Username: <input type="text" name="username"/><br>
            Password: <input type="password" name="password"/>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
