<%-- 
    Document   : add-question
    Created on : Mar 26, 2020, 6:21:19 PM
    Author     : dedhi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Add Question</title>
    </head>
    
    <body>
        <jsp:include page="user-header.jsp" >
            <jsp:param name="addQuestion" value="active" />
        </jsp:include>
        <h5 style="color: red; margin-top: 65px; margin-left: 20px;">${error}</h5>
        <div class="d-flex justify-content-xl-center" style="margin-top: 20px">
        <form action="add-question.htm" method="POST">
<!--            <h4 style="text-decoration: underline;">Add Question</h4>-->
              <div class="form-group">
                <label for="domin">Select Domain</label>
                <select class="form-control" id="domain" name="domain">
                  <c:forEach var="domain" items="${domainList}">
                    <option value="${domain.getDomain_id()}">${domain.getDomain_name()}</option>
                </c:forEach>
                </select>
              </div>
            
            <div class="form-group">
                  <label for="title">Enter Title</label>
                  <input type="text" name="title" minlength="5" maxlength="500" pattern="(.)*([A-Za-z])(.)*" title="Please enter valid Title" value="${title}" class="form-control" id="title" placeholder="Enter Title" required/>
            </div>
            
            <div class="form-group">
                <label for="desc">Enter Description</label>
                <textarea class="form-control" id="desc" rows="5" cols="25" name="desc" minlength="10" maxlength="4000" value="${desc}" required></textarea>
            </div>

            <input type="hidden" name="option" value="addQuestion"/>
            <input type="submit" value="Add Question" class="btn btn-primary mb-2"/>
        </form>
        </div>
    </body>
</html>
