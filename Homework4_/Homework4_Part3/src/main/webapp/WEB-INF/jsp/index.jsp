<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movies</title>
    </head>
    <body>
        <h1>Welcome to our Movie Store</h1>
        <h5>Please make your selection below</h5>
        <form action="movieSelection.htm">
            <select name="option">
                <option value="browse">Browse Movies</option>
                <option value="add">Add new Movie</option>
            </select>
            <input type="submit" value="Send"/>
        </form>
    </body>
</html>
