<%-- 
    Document   : add-approver
    Created on : Apr 16, 2020, 4:29:36 AM
    Author     : dedhi
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <title>Add Approver</title>
    </head>
    <body>
        <jsp:include page="admin-header.jsp" >
            <jsp:param name="addApprover" value="active" />
        </jsp:include>
        <h5 style="color: red; margin-top: 65px; margin-left: 20px;">${error}</h5>
        <div class="d-flex justify-content-center" style="margin-top: 20px">
            
                <form method="POST" action="register-user.htm">
<!--                    <h4 style="text-decoration: underline;">Add Approver</h4>-->
                <div class="form-group">
                  <label for="firstname">First Name</label>
                  <input type="text" name="firstname" minlength="3" maxlength="49" pattern="^[a-zA-Z]+$" title="Only letters" class="form-control" id="firstname" placeholder="Enter First Name" value="${firstname}" required/>
                </div>
                    
                <div class="form-group">
                  <label for="lastname">Last Name</label>
                  <input type="text" minlength="3" pattern="^[a-zA-Z]+$" maxlength="49" title="Only letters" name="lastname" class="form-control" id="lastname" placeholder="Enter Last Name" value="${lastname}" required/>
                </div>
                    
                Gender<br>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="gender" id="F" value="F"/>
                <label class="form-check-label" for="F">Female</label>
              </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="gender" id="M" value="M"/>
                <label class="form-check-label" for="M">Male</label>
              </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="gender" id="O" value="O"/>
                <label class="form-check-label" for="O">Other</label>
              </div>
                <br><br>
                
                <div class="form-group">
                <label for="date">Enter Date of Birth</label>
                  <input class="form-control" max="<%= new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" type="date" value="${date}" id="date" name="dateofbirth" required/>
              </div>
                
                <div class="form-group">
                <label for="address">Enter Address</label>
                <textarea class="form-control" minlength="8" maxlength="100" id="address" rows="5" cols="25" name="address" required>${address}</textarea>
                </div>
                
                <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" class="form-control" maxlength="30" id="email" placeholder="Enter Email" value="${email}" name="email" required/>
              </div>
                
                
                <div class="form-group">
                <label for="phone">Phone Number</label>
                <input type="phone" class="form-control" id="phone" pattern="^\+[0-9]{3}-[0-9]{3}-[0-9]{4}$" maxlength="20" value="${phone}" title="+XXX-XXX-XXXX" placeholder="+888-888-8888" name="phone" required/>
              </div>
                
                <div class="form-group">
                  <label for="username">Username</label>
                  <input type="text" name="username" class="form-control" id="username" minlength="5" value="${username}" maxlength="10" pattern="^[0-9A-Za-z]*$" title="Username can contain only alphabets and digits" placeholder="Enter Username" required/>
                </div>
                
                <div class="form-group">
                  <label for="password">Password</label>
                  <input type="password" name="password" class="form-control" id="password" minlength="6" value="${password}" maxlength="10" pattern="(?=^.{6,10}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\s).*$" title="Password should have 1 small-case letter, 1 Capital letter, 1 digit, 1 special character and the length should be between 6-10 characters" placeholder="Enter Password" required/>
                </div>

                <input type="hidden" name="option" value="addApprover"/>
                <input type="submit" value="Add Approver" class="btn btn-primary mb-2"/>
            </form>
        </div>
            </form>
        <div>
    </body>
</html>
