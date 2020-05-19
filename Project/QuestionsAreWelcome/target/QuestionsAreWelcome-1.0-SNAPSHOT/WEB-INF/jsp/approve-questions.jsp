<%-- 
    Document   : approve-questions
    Created on : Apr 15, 2020, 7:25:50 AM
    Author     : dedhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <title>Approve Questions</title>
    </head>
    <body>
        <jsp:include page="approver-header.jsp" >
            <jsp:param name="approveQuestion" value="active" />
        </jsp:include>
        <h5 style="color: red; margin-top: 65px; margin-left: 20px;">${error}</h5>
        <c:forEach var="question" items="${questionList}">
            <div class="card" style="margin: 20px;">
            <div class="card-header">
              ${question.getDomain().getDomain_name()}
              <c:choose>
                  <c:when test="${question.getUpdated_on() != null}">
                      <span style="float: right;font-size: small;" class="text-muted">updated on: <fmt:formatDate type="date" value="${question.getUpdated_on()}" /></span>
                  </c:when>
                  <c:otherwise>
                      <span style="float: right;font-size: small;" class="text-muted">created on: <fmt:formatDate type="date" value="${question.getCreated_on()}" /></span>
                  </c:otherwise>
              </c:choose>
            </div>
            
            <div class="card-body">
              <h5 class="card-title">${question.getQuestion_title()}</h5>
              <p class="card-text">${question.getQuestion_description()}</p>
              <form method="POST" action="approve-questions.htm">
                  <div class="form-group">
                  <label for="reject_comment">Reject Comment</label>
                  <input type="text" name="reject_comment" minlength="5" maxlength="4000" pattern="(.)*[A-Za-z](.)*" title="Reject comment is invalid" class="form-control" id="reject_comment" placeholder="Enter Reject Comment" value="${reject}"/>
                </div>
                <input type="hidden" name="question_id" value="${question.getQuestion_id()}"/>
                <input type="hidden" name="option" value="question"/>
                <input type="submit" name="action" value="Approve" class="btn btn-primary"/>
                <input type="submit" name="action" value="Reject" class="btn btn-primary"/>
            </form>
            </div>
                <div class="card-footer text-muted" style="font-size: smaller">
                    <span style="float: right;">asked by: ${question.getUser().getFirstname()} ${question.getUser().getLastname()}</span>
            </div>
            </div>
        </c:forEach>

    </body>
</html>
