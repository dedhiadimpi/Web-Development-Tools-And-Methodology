<%-- 
    Document   : edit-question
    Created on : Apr 15, 2020, 10:43:45 PM
    Author     : dedhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <title>Edit Question</title>
    </head>
    <body>
        <jsp:include page="user-header.jsp" >
            <jsp:param name="myQuestion" value="active" />
        </jsp:include>
        <h5 style="color: red; margin-top: 65px; margin-left: 20px;">${error}</h5>
        <div class="d-flex justify-content-xl-center" style="margin-top: 20px">
        <form action="edit-question.htm" method="POST">
            <h4 style="text-decoration: underline;margin-bottom: 20px;">Edit Question</h4>
            
            <div class="form-group">
                <label for="domin">Select Domain</label>
                <select class="form-control" id="domain" name="domain">
                  <c:forEach var="domain" items="${domainList}">
                    <c:if test="${question.getDomain().getDomain_id() == domain.getDomain_id()}">
                        <option value="${domain.getDomain_id()}" selected>${domain.getDomain_name()}</option>
                    </c:if>
                    <c:if test="${question.getDomain().getDomain_id() != domain.getDomain_id()}">
                        <option value="${domain.getDomain_id()}">${domain.getDomain_name()}</option>
                    </c:if>
                </c:forEach>
                </select>
              </div>
            
            <div class="form-group">
                  <label for="title">Enter Title</label>
                  <input type="text" name="title" class="form-control" id="title" minlength="5" maxlength="500" pattern="(.)*([A-Za-z])(.)*" title="Please enter valid Title" placeholder="Enter Title" required value="${question.getQuestion_title()}"/>
            </div>
            
            <div class="form-group">
                <label for="desc">Enter Description</label>
                <textarea class="form-control" id="desc" rows="5" cols="25" minlength="10" maxlength="4000" name="desc" required>${question.getQuestion_description()}</textarea>
            </div>
            <input type="hidden" name="option" value="editMyQuestion"/>
            <input type="hidden" name="question_id" value="${question.getQuestion_id()}"/>
            <input type="submit" value="Edit Question" class="btn btn-primary mb-2"/>
        </form>
        </div>
    </body>
</html>
