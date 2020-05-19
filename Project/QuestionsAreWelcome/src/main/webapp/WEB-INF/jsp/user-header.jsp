<%-- 
    Document   : header
    Created on : Apr 17, 2020, 7:12:45 PM
    Author     : dedhi
--%>

<!--<nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#">QuestionsAreWelcome</a>
          </div>
          <ul class="nav navbar-nav">
            <li class=<%=request.getParameter("home")%>><a href="#">Home</a></li>
            <li class=<%=request.getParameter("getQuestion")%>><a href="redirect-get-question-list.htm"> Search Questions</a></li>
            <li class=<%=request.getParameter("myQuestion")%>><a href="redirect-add-question.htm"> Add Question</a></li>
            <li class=<%=request.getParameter("myAnswer")%>><a href="redirect-get-my-question-list.htm"> My Questions</a></li>
            <li><a href="redirect-get-my-answer-list.htm"> My Answers</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="logout-user.htm?option=logout"><span class="glyphicon glyphicon-log-in">
    
        </span> Logout ${sessionScope.user.getFirstname()} ${sessionScope.user.getLastname()}</a></li>
          </ul>
        </div>
      </nav>-->

<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">QuestionsAreWelcome</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link <%=request.getParameter("home")%>" href="redirect-user-home.htm">Home <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link <%=request.getParameter("getQuestion")%>" href="redirect-get-question-list.htm">Search Questions</a>
      <a class="nav-item nav-link <%=request.getParameter("addQuestion")%>" href="redirect-add-question.htm">Add Question</a>
      <a class="nav-item nav-link <%=request.getParameter("myQuestion")%>" href="redirect-get-my-question-list.htm">My Questions</a>
      <a class="nav-item nav-link <%=request.getParameter("myAnswer")%>" href="redirect-get-my-answer-list.htm">My Answers</a>
      <a class="nav-item nav-link <%=request.getParameter("myProfile")%>" href="redirect-my-profile.htm">My Profile</a>
      </div>
      <div class="nav navbar-nav ml-auto">
      <a class="nav-item nav-link" href="logout-user.htm?option=logout">
          <i class="fa fa-sign-out"></i> Logout ${sessionScope.user.getFirstname()} ${sessionScope.user.getLastname()}</a>
    </div>
  </div>
</nav>
   
  