<%-- 
    Document   : admin-header
    Created on : Apr 18, 2020, 9:21:35 PM
    Author     : dedhi
--%>

<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">QuestionsAreWelcome</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link <%=request.getParameter("home")%>" href="redirect-admin-home.htm">Home <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link <%=request.getParameter("addApprover")%>" href="redirect-add-approver.htm">Add Approver</a>
      <a class="nav-item nav-link <%=request.getParameter("viewDashboard")%>" href="view-dashboard.htm?option=view">View Dashboards</a>
      </div>
      <div class="nav navbar-nav ml-auto">
      <a class="nav-item nav-link" href="logout-user.htm?option=logout">
          <i class="fa fa-sign-out"></i> Logout ${sessionScope.user.getUsername()}</a>
    </div>
  </div>
</nav>
