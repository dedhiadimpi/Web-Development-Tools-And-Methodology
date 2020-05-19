<%-- 
    Document   : edit-answer
    Created on : Apr 13, 2020, 5:50:01 PM
    Author     : dedhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <title>Edit Answer</title>
    </head>
    <body>
        <jsp:include page="user-header.jsp" >
            <jsp:param name="myAnswer" value="active" />
        </jsp:include>
        <h5 style="color: red; margin-top: 65px; margin-left: 20px;">${error}</h5>
        <div class="d-flex justify-content-center" style="margin-top: 20px">
            <form action="edit-answer.htm" method="POST">
                <h4 style="text-decoration: underline;">Edit Answer</h4>
                <div class="form-group">
                <label for="desc">Enter Answer</label>
                <textarea class="form-control" id="desc" rows="5" cols="25" name="desc" minlength="2" required>${answer_desc}</textarea>
                </div>
                 <input type="hidden" name="option" value="editAnswer"/>
                 <input type="hidden" name="answer_id" value="${answer_id}">
                 <input type="submit" value="Edit Answer" class="btn btn-primary mb-2"/>
            </form>
        </div>
    </body>
</html>
