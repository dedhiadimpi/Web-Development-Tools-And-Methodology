<%-- 
    Document   : view-my-answers
    Created on : Apr 13, 2020, 12:26:13 PM
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
        <title>My Answers</title>
    </head>
    <body>
        <jsp:include page="user-header.jsp" >
            <jsp:param name="myAnswer" value="active" />
        </jsp:include>
        <h5 style="color: red; margin-top: 65px; margin-left: 20px;">${error}</h5>
<!--        <h5 style="margin-top: 2%; margin-left: 43%; text-decoration: underline;">My Answer List</h5>-->
<c:if test="${answerList.size() < 1}">
            <h5 style="color: red;margin-left: 10px;">No Answers found</h5>
        </c:if>
        <c:forEach var="element" items="${answerList}">
            <div class="card" style="margin: 3%;">
                <div class="card-header">
                    ${element.getKey().getDomain().getDomain_name()}
                    <c:choose>
                  <c:when test="${element.getKey().getUpdated_on() != null}">
                      <span style="float: right;font-size: small;" class="text-muted">updated on: <fmt:formatDate type="date" value="${element.getKey().getUpdated_on()}" /></span>
                  </c:when>
                  <c:otherwise>
                      <span style="float: right;font-size: small;" class="text-muted">created on: <fmt:formatDate type="date" value="${element.getKey().getCreated_on()}" /></span>
                  </c:otherwise>
              </c:choose>
                </div>
                <div class="card-body">
                    <h5 class="card-title">${element.getKey().getQuestion_title()}</h5>
                    <p class="card-text">${element.getKey().getQuestion_description()}</p>
                    <c:forEach var="answer" items="${element.getValue()}">
                        <div class="card">
                            <div class="card-body">
                             <p class="card-text" style="font-weight: bold">${answer.getAnswer_description()}</p>
                             <c:if test="${answer.getReject_comment() == null}">
                             <a href="redirect-edit-answer.htm?answer_id=${answer.getAnswer_id()}">[Edit Answer]</a>&nbsp;&nbsp;
                             </c:if>
                             <a href="redirect-delete-answer.htm?answer_id=${answer.getAnswer_id()}">[Delete Answer]</a>
                            </div>
                            <div class="card-footer text-muted" style="font-size: smaller">
                                Likes: ${answer.getNo_of_likes()} &nbsp;&nbsp; Dislikes: ${answer.getNo_of_dislikes()}    
                               
                                <span style="float: right;">answered by: ${answer.getUser().getFirstname()} ${answer.getUser().getLastname()}
                                    <c:choose>
                                        <c:when test="${answer.getUpdated_on() != null}">
                                            | updated on: <fmt:formatDate type="date" value="${answer.getUpdated_on()}" />
                                        </c:when>
                                        <c:otherwise>
                                            | created on: <fmt:formatDate type="date" value="${answer.getCreated_on()}" />
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${answer.getReject_comment() != null}"> | Rejected</c:when>
                                        <c:when test="${answer.getApprover().getFirstname() == 'DUMMY'}"> | Not Approved</c:when>
                                        <c:otherwise> | Approved</c:otherwise>
                                    </c:choose>
                                </span>
                            
                            </div>
                        </div>
                            <br>
                    </c:forEach>
                </div>
                <div class="card-footer text-muted" style="font-size: smaller">
                Likes: ${element.getKey().getNo_of_likes()} &nbsp;&nbsp; Dislikes: ${element.getKey().getNo_of_dislikes()}
                <span style="float: right;">asked by: ${element.getKey().getUser().getFirstname()} ${element.getKey().getUser().getLastname()}</span>
            </div>
            </div>
        </c:forEach>
    </body>
</html>
