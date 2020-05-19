<%-- 
    Document   : part6
    Created on : Feb 4, 2020, 10:25:37 AM
    Author     : dedhi
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>table,th,td{border: 1px solid black} th{text-align:left} th,td{padding: 5px}</style>
    </head>
    <body>
        <h4>JSTL Core, Function and Formatting Tags</h4>
        <c:set var="heading" value="Student List of Northeastern University"/>  
        <h3><c:out value="${fn:substringBefore(heading, 'of')} - ${fn:toUpperCase(fn:substringAfter(heading,'of'))}"/></h3>
<table>
    <tr>
    <th>NUID</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Date of Birth</th>
    <th>Phone Number</th>
    <th>Status</th>
    </tr>
    <c:forEach var="i" begin="1" end="5" step="1">
        <jsp:useBean id="student" class="com.dimpi.Student"></jsp:useBean>
        <jsp:setProperty property="NUID" name="student" value="${i}" />
        <jsp:setProperty property="firstName" name="student" value="First Name${i}"/>
        <jsp:setProperty property="lastName" name="student" value="Last Name${i}" />
        <fmt:parseDate value = "${i}-${i}-200${i}" var = "parsedDate" pattern = "dd-MM-yyyy" />
        <jsp:setProperty property="dob" name="student" value="${parsedDate}" />
        <jsp:setProperty property="phone" name="student" value="857800336${i}" />
        <jsp:setProperty property="status" name="student" value="false" />
        <c:if test="${i > 2}">  
        <jsp:setProperty property="status" name="student" value="true" />
        </c:if>
        <tr>
            <td><c:out value="${student.NUID}"/></td>
            <td><c:out value="${student.firstName}"/></td>
            <td><c:out value="${fn:trim(student.lastName)}"/></td>
            <td><fmt:formatDate type="date" value="${student.dob}" /></td>
            <td><fmt:formatNumber type="number" maxIntegerDigits="10" groupingUsed="false" value="${student.phone}" /></td>
            <c:choose>
                <c:when test="${student.status == true}">
                    <td>Active</td>
                </c:when>
                <c:otherwise>
                    <td>Inactive</td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
<h4>List of Countries of International Students of Northeastern University</h4>
<fmt:bundle basename="com.dimpi.Bundle" prefix="country.">  
   <fmt:message key="India"/><br/>  
   <fmt:message key="China"/><br/>  
  <fmt:message key="USA"/><br/>  
  
</fmt:bundle> 
  <h4>JSTL XML Tags</h4>
<h4>Course Information</h4>
<c:set var="courses">  
<courses>  
    <course>  
      <name>Computer Science</name>  
      <totalstudents>10</totalstudents>
      <fee>1000</fee>
      <totalcredits>32</totalcredits>
    </course>
    <course>  
      <name>Mechanical</name>  
      <totalstudents>20</totalstudents>  
      <fee>2100</fee>
      <totalcredits>32</totalcredits>
    </course>  
    <course>  
      <name>Information Technology</name>  
      <totalstudents>30</totalstudents> 
      <fee>1000</fee>
      <totalcredits>32</totalcredits>
    </course>  
    <course>  
      <name>Electronics</name>  
      <totalstudents>8</totalstudents>
      <fee>3000</fee>
      <totalcredits>32</totalcredits>
    </course>  
</courses>
</c:set>
<x:parse xml="${courses}" var="output"/> 
    <p>
    Course Name: <x:out select="$output/courses/course[3]/name" /><br>
    Total Number of Students: <x:out select="$output/courses/course[3]/totalstudents" /><br>
    Total Credits: <x:out select="$output/courses/course[3]/totalcredits" /><br>
    Fee: <x:out select="$output/courses/course[3]/fee" /><br>
    </p>
<h4>Is any course fee less than $2000?</h4>    
<x:parse xml="${courses}" var="output"/>  
<x:if select="$output/courses/course/fee < 2000">  
Yes, courses are less than $2000
</x:if> 
<h4>JSTL SQL Tags</h4>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  
     url="jdbc:mysql://localhost/test"  
     user="root"  password="root"/> 

<sql:query dataSource="${db}" var="rs">  
SELECT * from Students;  
</sql:query>  

<h5>Select Tag</h5>
<table>  
<tr>  
<th>Student ID</th>  
<th>First Name</th>  
<th>Last Name</th>  
<th>Age</th>  
</tr>  
<c:forEach var="table" items="${rs.rows}">  
<tr>  
<td><c:out value="${table.id}"/></td>  
<td><c:out value="${table.First_Name}"/></td>  
<td><c:out value="${table.Last_Name}"/></td>  
<td><c:out value="${table.Age}"/></td>  
</tr>  
</c:forEach>  
</table>  

<h5>Update Tag</h5>
        
<sql:update dataSource="${db}" var="count">  
INSERT INTO Students VALUES (3,'Nidhi', 'Patel', 24);  
</sql:update>  
  
<sql:query dataSource="${db}" var="rs">  
SELECT * from Students;  
</sql:query>  
   
<table>  
<tr>  
<th>Student ID</th>  
<th>First Name</th>  
<th>Last Name</th>  
<th>Age</th>  
</tr>  
<c:forEach var="table" items="${rs.rows}">  
<tr>  
<td><c:out value="${table.id}"/></td>  
<td><c:out value="${table.First_Name}"/></td>  
<td><c:out value="${table.Last_Name}"/></td>  
<td><c:out value="${table.Age}"/></td>  
</tr>  
</c:forEach>  
</table> 
<h5>Delete Tag</h5>

<c:set var="StudentId" value="3"/>  
<sql:update dataSource="${db}" var="count">  
DELETE FROM Students WHERE Id = ?  
<sql:param value="${StudentId}" />  
</sql:update>

<sql:query dataSource="${db}" var="rs">  
SELECT * from Students;  
</sql:query>  
   
<table>  
<tr>  
<th>Student ID</th>  
<th>First Name</th>  
<th>Last Name</th>  
<th>Age</th>  
</tr>  
<c:forEach var="table" items="${rs.rows}">  
<tr>  
<td><c:out value="${table.id}"/></td>  
<td><c:out value="${table.First_Name}"/></td>  
<td><c:out value="${table.Last_Name}"/></td>  
<td><c:out value="${table.Age}"/></td>  
</tr>  
</c:forEach>  
</table> 

 </body>
</html>
