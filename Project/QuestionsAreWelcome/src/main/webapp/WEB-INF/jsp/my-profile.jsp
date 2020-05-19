<%-- 
    Document   : my-profile
    Created on : Apr 13, 2020, 12:41:38 PM
    Author     : dedhi
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <title>My Profile</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.role == 'user'}">
                <jsp:include page="user-header.jsp" >
                    <jsp:param name="myProfile" value="active" />
                </jsp:include>
            </c:when>
            <c:when test="${sessionScope.role == 'approver'}">
                <jsp:include page="approver-header.jsp" >
                    <jsp:param name="myProfile" value="active" />
                </jsp:include>
            </c:when>
        </c:choose>
        
        <h5 style="color: red; margin-top: 65px; margin-left: 20px;">${error}</h5>
        <div class="d-flex justify-content-center">
            <c:if test="${view}">
                <form method="POST" action="redirect-edit-myProfile.htm">
            </c:if>
            <c:if test="${!view}">
                <form method="POST" action="redirect-save-myProfile.htm">
            </c:if>
                    <!--<h4 style="text-decoration: underline;">My Profile</h4>-->
                    
                    <div class="form-group">
                  <label for="firstname">First Name</label>
                  <input type="text" name="firstname" minlength="3" maxlength="50" pattern="^[a-zA-Z]+$" title="Only letters" class="form-control" id="firstname" placeholder="Enter First Name" required value="${myProfile.getFirstname()}" ${disabled}/>
                </div>
                    
               <div class="form-group">
                  <label for="lastname">Last Name</label>
                  <input type="text" name="lastname" minlength="3" maxlength="50" pattern="^[a-zA-Z]+$" title="Only letters" class="form-control" id="lastname" placeholder="Enter Last Name" required value="${myProfile.getLastname()}" ${disabled}/>
                </div>
                    
                Gender<br>
                <c:choose>
                    <c:when test="${myProfile.getGender() == 'M'}">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="F" value="F" ${disabled}/>
                            <label class="form-check-label" for="F">Female</label>
                        </div>
                            <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="M" value="M" checked ${disabled}/>
                            <label class="form-check-label" for="M">Male</label>
                        </div>
                            <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="O" value="O" ${disabled}/>
                            <label class="form-check-label" for="O">Other</label>
                        </div>
                    </c:when>   
                    <c:when test="${myProfile.getGender() == 'F'}">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="F" value="F" checked ${disabled}/>
                            <label class="form-check-label" for="F">Female</label>
                        </div>
                            <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="M" value="M"  ${disabled}/>
                            <label class="form-check-label" for="M">Male</label>
                        </div>
                            <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="O" value="O" ${disabled}/>
                            <label class="form-check-label" for="O">Other</label>
                        </div>
                    </c:when>
                    <c:otherwise>
                       <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="F" value="F"  ${disabled}/>
                            <label class="form-check-label" for="F">Female</label>
                        </div>
                            <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="M" value="M"  ${disabled}/>
                            <label class="form-check-label" for="M">Male</label>
                        </div>
                            <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="O" value="O" checked ${disabled}/>
                            <label class="form-check-label" for="O">Other</label>
                        </div>
                    </c:otherwise>
                </c:choose>
                <br><br>
                <div class="form-group">
                <label for="date">Enter Date of Birth</label>
                <input class="form-control" max="<%= new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" type="date" id="date" name="dateofbirth" required value="${date}" ${disabled}/>
              </div>
                
                <div class="form-group">
                <label for="address">Enter Address</label>
                <textarea class="form-control" id="address" minlength="8" maxlength="100" rows="5" cols="25" name="address" required ${disabled}>${myProfile.getAddress()}</textarea>
                </div>
                
                <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" class="form-control" id="email" maxlength="30" placeholder="Enter Email" name="email" required value="${myProfile.getEmail()}" ${disabled}/>
              </div>

                <div class="form-group">
                <label for="phone">Phone Number</label>
                <input type="phone" class="form-control" id="phone" placeholder="+888-888-8888" pattern="^\+[0-9]{3}-[0-9]{3}-[0-9]{4}$" maxlength="20" title="+XXX-XXX-XXXX" name="phone" required value="${myProfile.getPhone_number()}" ${disabled}/>
              </div>
                
                <div class="form-group">
                  <label for="username">Username</label>
                  <input type="text" name="username" class="form-control" id="username" placeholder="Enter Username" minlength="5" maxlength="10" required value="${myProfile.getUsername()}" disabled/>
                </div>
                <c:if test="${view}">
                <input type="submit" value="Edit Profile" class="btn btn-primary mb-2"/>
                </c:if>
                <c:if test="${!view}">
                <input type="submit" value="Save Profile" class="btn btn-primary mb-2"/>
                </c:if>
                <c:if test="${sessionScope.role == 'approver'}">
                    <input type="hidden" name="user_id" value="${myProfile.getApprover_id()}">
                </c:if>
                <c:if test="${sessionScope.role == 'user'}">
                    <input type="hidden" name="user_id" value="${myProfile.getUser_id()}">
                </c:if>
                
            </form>
        </div>
                <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>
