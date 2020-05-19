<%-- 
    Document   : view-dashboard
    Created on : Apr 19, 2020, 1:38:45 PM
    Author     : dedhi
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>p{margin-bottom: 2px;}</style>
    </head>
    <body>
        <jsp:include page="admin-header.jsp" >
            <jsp:param name="viewDashboard" value="active" />
        </jsp:include>
        <a class="btn btn-primary" style="margin-top: 70px;margin-left: 10px;" href="view-dashboard.htm?option=pdfview">View/Download PDF</a>
        <h5 style="text-decoration: underline;text-align: center;">Questions Overview</h5>
        <c:choose>
            <c:when test="${questionMap.size() < 1}">
                <h3>Nothing to display</h3>
            </c:when>
            <c:otherwise>
                <table class="table table-striped">
                <thead><tr>
            <th>Username</th>
<!--            <th scope="col">User First name</th>
            <th scope="col">User Last name</th>-->
            <th scope="col">Total Number of Questions Posted</th>
            <th scope="col">Total Number of Questions Not Approved</th>
            <th scope="col">Total Number of Questions Approved</th>
            <th scope="col">Total Number of Questions Rejected</th>
            <th scope="col">Total likes</th>
            <th scope="col">Total Dislikes</th>
            </tr></thead>
            <tbody>
            <c:forEach var="element" items="${questionMap}">
                <tr>
                <td>
                    <details><summary>${element.getKey()}</summary>
                        <p>First Name: ${element.getValue().get(6).getFirstname()}</p>
                        <p>Last Name: ${element.getValue().get(6).getLastname()}</p>
                        <p>Gender: ${element.getValue().get(6).getGender()}</p>
                        <p>Date of Birth:  <fmt:formatDate type="date" value="${element.getValue().get(6).getDate_of_birth()}" /></p>
                        <p>Address: ${element.getValue().get(6).getAddress()}</p>
                        <p>Email: ${element.getValue().get(6).getEmail()}</p>
                        <p>Phone Number: ${element.getValue().get(6).getPhone_number()}</p>
                    </details>
                </td>
                <td>${element.getValue().get(0)}</td>
                <td>${element.getValue().get(1)}</td>
                <td>${element.getValue().get(2)}</td>
                <td>${element.getValue().get(3)}</td>
                <td>${element.getValue().get(4)}</td>
                <td>${element.getValue().get(5)}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
            </c:otherwise>
        </c:choose>
                
                
                
                
        <h5 style="margin-top: 30px;text-decoration: underline;text-align: center;">Answer Overview</h5>
        <c:choose>
            <c:when test="${answerMap.size() < 1}">
                <h3>Nothing to display</h3>
            </c:when>
            <c:otherwise>
            <table class="table table-striped">
            <thead><tr>
            <th scope="col">Username</th>
            <th scope="col">Total Number of Answers Submitted</th>
            <th scope="col">Total Number of Answers Not Approved</th>
            <th scope="col">Total Number of Answers Approved</th>
            <th scope="col">Total Number of Answers Rejected</th>
            <th scope="col">Total likes</th>
            <th scope="col">Total Dislikes</th>
            </tr></thead>
            <tbody>
            <c:forEach var="element" items="${answerMap}">
            <tr>
                <td>
                    <details><summary>${element.getKey()}</summary>
                        <p>First Name: ${element.getValue().get(6).getFirstname()}</p>
                        <p>Last Name: ${element.getValue().get(6).getLastname()}</p>
                        <p>Gender: ${element.getValue().get(6).getGender()}</p>
                        <p>Date of Birth:  <fmt:formatDate type="date" value="${element.getValue().get(6).getDate_of_birth()}" /></p>
                        <p>Address: ${element.getValue().get(6).getAddress()}</p>
                        <p>Email: ${element.getValue().get(6).getEmail()}</p>
                        <p>Phone Number: ${element.getValue().get(6).getPhone_number()}</p>
                    </details>
                </td>
                <td>${element.getValue().get(0)}</td>
                <td>${element.getValue().get(1)}</td>
                <td>${element.getValue().get(2)}</td>
                <td>${element.getValue().get(3)}</td>
                <td>${element.getValue().get(4)}</td>
                <td>${element.getValue().get(5)}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
            </c:otherwise>
        </c:choose>
                
                
        <h5 style="margin-top: 30px;text-decoration: underline;text-align: center;">Domain Overview</h5>
        <c:choose>
            <c:when test="${domainMap.size() < 1}">
                <h3>Nothing to display</h3>
            </c:when>
            <c:otherwise>
            <table class="table table-striped">
            <thead><tr>
            <th scope="col">Domain Name</th>
            <th scope="col">Total Number of Questions</th>
            <th scope="col">Total Number of Answers</th>
            </tr></thead>
            <tbody>
            <c:forEach var="element" items="${domainMap}">
            <tr>
                <th scope="row">${element.getKey()}</th>
                <td>${element.getValue().get(0)}</td>
                <td>${element.getValue().get(1)}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
            </c:otherwise>
        </c:choose>
        
    </body>
</html>
