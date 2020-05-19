<%-- 
    Document   : login-success
    Created on : Mar 26, 2020, 5:23:21 PM
    Author     : dedhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Successful</title>
    </head>
    <body>
        <h1>Hello ${role.getUsername()}</h1>
        <h1>${domain}</h1>
    </body>
</html>
