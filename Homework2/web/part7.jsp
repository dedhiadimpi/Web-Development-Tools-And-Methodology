<%-- 
    Document   : part7
    Created on : Feb 4, 2020, 3:47:25 PM
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
        <form action="part7display.jsp" method="get">
            <fieldset>
                <legend>This is my form</legend>
                Email: <input type="email" name="email"><br>
                Password: <input type="password" name="password"><br>
                Confirm Password: <input type="password" name="confirmpassword"><br>
                Upload Your Picture: <input type="file" name="picture" accept="image/*"><br>
                Gender: <input type="radio" name="gender" value="Male">Male
                <input type="radio" name="gender" value="Female">Female<br>
                Select Country: <select name="country">
                    <option value="India">India</option>
                    <option value="United States">United States</option>
                    <option value="China">China</option>
                    </select><br>
                Hobby: <input type="checkbox" name="hobbycricket" value="Cricket">Cricket
                <input type="checkbox" name="hobbyfootball" value="Football">Football<br>
                Address: <textarea name="address" rows="7" cols="50"></textarea><br>
                <input type="submit" value="Submit">
            </fieldset>
        </form>
    </body>
</html>
