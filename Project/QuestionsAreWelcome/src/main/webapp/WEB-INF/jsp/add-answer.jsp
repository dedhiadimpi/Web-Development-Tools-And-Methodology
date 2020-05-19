<%-- 
    Document   : add-answer
    Created on : Apr 12, 2020, 5:09:08 PM
    Author     : dedhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <title>Add Answer</title>
    </head>
    <body>
        <jsp:include page="user-header.jsp" >
            <jsp:param name="getQuestion" value="active" />
        </jsp:include>
        <h5 style="color: red; margin-top: 65px; margin-left: 20px;">${error}</h5>
        <h4 style="margin-left: 45%;margin-top: 20px; text-decoration: underline;">Add Answer</h4>
        <div class="d-flex justify-content-center" style="margin-top: 25px">
        <div class="card w-75">
        <div class="card-body">
          <h5 class="card-title">${question.getQuestion_title()}</h5>
          <p class="card-text">${question.getQuestion_description()}</p>
          <form action="/QuestionsAreWelcome/add-answer.htm" method="POST">
              <div class="form-group">
                <label for="answer">Enter Answer</label>
                <textarea class="form-control" id="answer" minlength="2" maxlength="4000" rows="5" cols="25" name="desc" required>${desc}</textarea>
                </div>

            <input type="hidden" name="option" value="addAnswer"/>
            <input type="hidden" name="question_id" value="${question.getQuestion_id()}"/>
            
            <input type="submit" value="Add Answer" class="btn btn-primary mb-2"/>
        </form>
        </div>
      </div>
        </div>

    </body>
</html>
