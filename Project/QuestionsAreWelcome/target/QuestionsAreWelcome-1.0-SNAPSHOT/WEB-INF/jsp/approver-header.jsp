<%-- 
    Document   : approver-header
    Created on : Apr 18, 2020, 2:33:02 PM
    Author     : dedhi
--%>

<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">QuestionsAreWelcome</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link <%=request.getParameter("home")%>" href="redirect-approver-home.htm">Home <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link <%=request.getParameter("getQuestion")%>" href="redirect-get-approver-question-list.htm">Search Questions</a>
      <a class="nav-item nav-link <%=request.getParameter("approveQuestion")%>" href="redirect-approve-questions.htm">Approve Question</a>
      <a class="nav-item nav-link <%=request.getParameter("approveAnswer")%>" href="redirect-approve-answers.htm">Approve Answers</a>
      <a class="nav-item nav-link <%=request.getParameter("myProfile")%>" href="redirect-my-profile.htm">My Profile</a>
      </div>
      <div class="nav navbar-nav ml-auto">
      <a class="nav-item nav-link" href="logout-user.htm?option=logout">
          <i class="fa fa-sign-out"></i> Logout ${sessionScope.user.getFirstname()} ${sessionScope.user.getLastname()}</a>
    </div>
  </div>
</nav>
