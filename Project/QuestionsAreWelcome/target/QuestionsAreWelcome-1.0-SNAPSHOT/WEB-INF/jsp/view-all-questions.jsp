<%-- 
    Document   : view-all-questions
    Created on : Mar 29, 2020, 8:05:30 PM
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
        <title>View Questions</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${action == 'myQuestions' && sessionScope.role == 'user'}">
                <jsp:include page="user-header.jsp" >
                    <jsp:param name="myQuestion" value="active" />
                </jsp:include>
            </c:when>
            <c:when test="${action == 'allQuestions' && sessionScope.role == 'user'}">
                <jsp:include page="user-header.jsp" >
                    <jsp:param name="getQuestion" value="active" />
                </jsp:include>
            </c:when>
            <c:when test="${action == 'approverQuestions' && sessionScope.role == 'approver'}">
                <jsp:include page="approver-header.jsp" >
                    <jsp:param name="getQuestion" value="active" />
                </jsp:include>
            </c:when>
        </c:choose>
        <h5 style="color: red; margin-top: 65px; margin-left: 20px;">${error}</h5>
        <div style="margin-top: 2%;margin-left: 43%">
            <br>
            </div>
            <div style="margin-left: 26%;">
            <form action="redirect-search-list.htm" method="post" class="form-inline">
                <div class="form-group mx-sm-3 mb-2">
                <select class="form-control" id="domain" name="domain">
                  <c:forEach var="domain" items="${domainList}">
                    <option value="${domain.getDomain_id()}">${domain.getDomain_name()}</option>
                </c:forEach>
                    <option value="0">All</option>
                </select>
                </div>
                <div class="form-group mx-sm-3 mb-2">
                      <input type="text" name="keyword" class="form-control" id="keyword" placeholder="Enter Keyword"/>
                </div>
                <input type="hidden" name="action" value="${action}"/>
                <input type="submit" value="Search" class="btn btn-primary mb-2"/>
            </form>
            </div>
                <c:if test="${questionList.size() < 1}">
            <h5 style="color: red;margin-left: 10px;">No Questions found</h5>
        </c:if>
        <c:forEach var="question" items="${questionList}">
            <div class="card" style="margin: 3%;">
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
              <c:choose>
                  <c:when test="${sessionScope.role == 'user'}">
                  <c:forEach var="answer" items="${question.getAnswerList()}">
                  <c:if test="${answer.getApprover().getFirstname() != 'DUMMY' && answer.getReject_comment() == null}">
                    <div class="card">
                        <div class="card-body">
                         <p class="card-text">${answer.getAnswer_description()}</p>
                         <c:if test="${action != 'myQuestions' && sessionScope.role == 'user'}">
                             <a href="like-answer.htm?option=like_answer&answer_id=${answer.getAnswer_id()}">[Like]</a>&nbsp;&nbsp;
                          <a href="dislike-answer.htm?option=dislike_answer&answer_id=${answer.getAnswer_id()}">[Dislike]</a>
                      </c:if>
                        </div>
                        <div class="card-footer text-muted" style="font-size: smaller">
                            Likes: ${answer.getNo_of_likes()} &nbsp;&nbsp; Dislikes: ${answer.getNo_of_dislikes()}    

                            <c:choose>
                              <c:when test="${answer.getUpdated_on() != null}">
                                  <span style="float: right;">answered by: ${answer.getUser().getFirstname()} ${answer.getUser().getLastname()} | updated on: <fmt:formatDate type="date" value="${answer.getUpdated_on()}" /></span>
                              </c:when>
                              <c:otherwise>
                                  <span style="float: right;">answered by: ${answer.getUser().getFirstname()} ${answer.getUser().getLastname()} | created on: <fmt:formatDate type="date" value="${answer.getCreated_on()}" /></span>
                              </c:otherwise>
                           </c:choose>
                        </div>
                    </div><br>
                </c:if>
              </c:forEach>
                  </c:when>
                  <c:when test="${sessionScope.role == 'approver'}">
                      <c:forEach var="answer" items="${question.getAnswerList()}">
                       <div class="card">
                        <div class="card-body">
                         <p class="card-text">${answer.getAnswer_description()}</p>
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
                    </div><br> 
                    </c:forEach>
                  </c:when>
                    <c:otherwise></c:otherwise>
              </c:choose>
              
              
              <c:if test="${sessionScope.role == 'user' && action != 'myQuestions'}">
                  <a href="redirect-add-answer.htm?id=${question.getQuestion_id()}">[Add Answer]</a>&nbsp;&nbsp;
                </c:if>
                <c:if test="${action == 'myQuestions' && question.getAnswerList().size() == 0}">
                    <c:if test="${question.getReject_comment() == null}">
                        <a href="redirect-edit-question.htm?question_id=${question.getQuestion_id()}">[Edit Question]</a>&nbsp;&nbsp;
                    </c:if>
                    
                    <a href="redirect-delete-question.htm?question_id=${question.getQuestion_id()}">[Delete Question]</a>
                </c:if>
                    
                <c:if test="${action != 'myQuestions' && sessionScope.role == 'user'}">
                    <a href="like-question.htm?option=like_question&question_id=${question.getQuestion_id()}">[Like]</a>&nbsp;&nbsp;
                    <a href="dislike-question.htm?option=dislike_question&question_id=${question.getQuestion_id()}">[Dislike]</a>
                </c:if>
            </div>
            <div class="card-footer text-muted" style="font-size: smaller">
                Likes: ${question.getNo_of_likes()} &nbsp;&nbsp; Dislikes: ${question.getNo_of_dislikes()}
                <span style="float: right;">
                    asked by: ${question.getUser().getFirstname()} ${question.getUser().getLastname()}
                    <c:if test="${(action == 'myQuestions' && sessionScope.role == 'user') || (sessionScope.role == 'approver')}">
                        <c:choose>
                            <c:when test="${question.getReject_comment() != null}"> | Rejected </c:when>
                            <c:when test="${question.getApprover().getFirstname() == 'DUMMY'}">
                                | Not Approved
                            </c:when>
                            <c:otherwise>| Approved</c:otherwise>
                        </c:choose>
                    </c:if>
                </span>
            </div>
            </div>
        </c:forEach>
    </body>
</html>
