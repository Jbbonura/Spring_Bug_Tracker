<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    <title> Projects </title>
    <link rel="stylesheet" href="/CSS/project.css">
    <!-- Boxicons CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
   </head>
<body>
  <div class="sidebar">
    <div class="logo-details">
        <i class='bx bx-user' ></i>
      <span class="logo_name">Welcome, <c:out value="${user_name}"/></span>  <!--add session name-->
    </div>
      <ul class="nav-links">
        <li>
          <a href="/">
            <i class='bx bx-grid-alt' ></i>
            <span class="links_name">Dashboard Home</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class='bx bx-box' ></i>
            <span class="links_name">Manage Role Assignment</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class='bx bx-list-ul' ></i>
            <span class="links_name">Manage Project Users</span>
          </a>
        </li>
        <li>
          <a href="/projects">
            <i class='bx bx-pie-chart-alt-2' ></i>
            <span class="links_name">My Projects</span>
          </a>
        </li>
        <li>
          <a href="/tickets">
            <i class='bx bx-coin-stack' ></i>
            <span class="links_name">My Tickets</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class='bx bx-book-alt' ></i>
            <span class="links_name">User Profile</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class='bx bx-user' ></i>
            <span class="links_name">Team</span>
          </a>
        </li>
        <li class="log_out">
          <a href="/logout">
            <i class='bx bx-log-out'></i>
            <span class="links_name">Log out</span>
          </a>
        </li>
      </ul>
  </div>
  <section class="home-section">
    <nav>
      <div class="sidebar-button">
        <i class='bx bx-menu sidebarBtn'></i>
        <span class="dashboard">Details for ${project.name} </span>
      </div>
      <div class="profile-details">
      	<c:if test="${user_role == 3}">
        	<span class="admin_name">Logged in as a Submitter</span>
        </c:if>
  		<c:if test="${user_role == 2}">
        	<span class="admin_name">Logged in as a Developer</span>
        </c:if>
        <c:if test="${user_role == 1}">
        	<span class="admin_name">Logged in as a Project Manager</span>
        </c:if>
        <c:if test="${user_role == 0}">
        	<span class="admin_name">Logged in as an Admin</span>
        </c:if>
      </div>
    </nav>

    <div class="home-content">
      <div class="overview-boxes">
        <div class="box">
          <div class="right-side">
          <div class="box-topic">Project Name</div>
            <span class="text"><c:out value="${project.name}"/></span>
          </div>
        </div>
        <div class="box">
          <div class="right-side">
            <div class="box-topic">Project Description</div>
            <span class="text"><c:out value="${project.description}"/></span>
          </div>
        </div>
      </div>
      <div class="bottom-boxes">
        <div class="top-sales">
          <div class="table-header">
            <div class="title">Assigned Personnel</div>
            <div class="search-box">
              <i class='bx bx-search' ></i>
              <input id='myInput' onkeyup='searchTable()' type='text' placeholder="Search">
            </div>
          </div>
          <div style="overflow-x:auto;" class="ticket-comments">
            <table style="width: 100%;" class="table-content"  id="assigned">
                <thead>
                    <tr class="table100-head">
                        <th class="column1">Title</th>
                        <th  class="column2">Project Name</th>
                        <th  class="column3">Developer Assigned</th>
                    </tr>  
                </thead>
                <tbody>
                 
                </tbody>
            </table>  
          </div>
        </div>
          <div class="top-sales">
            <div class="table-header">
              <div class="title">Tickets for this Project</div>
              <div class="search-box">
                <i class='bx bx-search' ></i>
                <input id='myInput' onkeyup='searchTable()' type='text' placeholder="Search">
              </div>
            </div>
            <div style="overflow-x:auto;" class="ticket-comments">
              <table style="width: 100%;" class="table-content"  id="project-tickets">
                  <thead>
                      <tr class="table100-head">
                          <th style="width:15%;"class="column1">Title</th>
                          <th style="width:15%;" class="column2">Submitter</th>
                          <th style="width:15%;" class="column3">Assigned Developer</th>
                          <th style="width:15%;"class="column4">Status</th>
                          <th style="width:15%;"class="column5">Created</th>
                      </tr>  
                  </thead>
                  <tbody>
                    	<c:forEach var="ticket" items="${tickets}">
                    		<tr>
                    			<td><c:out value="${ticket.title}"/></td>
                    			<td><c:out value="${ticket.submitter.name}"/></td>
                    			<td><c:out value="${ticket.assignedDev.name}"/></td>
                    			<td><c:out value="${ticket.ticketStatus}"/></td>
                    			<td><fmt:formatDate type="date" value="${ticket.createdAt}"/></td>
                    		</tr>
                    	</c:forEach>
                  </tbody>
              </table>  
            </div>
          </div>
      </div> <!--bottom boxes end -->
    </div> <!--home content end -->
  </section> 

<script>
  let sidebar = document.querySelector(".sidebar");
  let sidebarBtn = document.querySelector(".sidebarBtn");
  sidebarBtn.onclick = function() {
    sidebar.classList.toggle("active");
    if(sidebar.classList.contains("active")){
    sidebarBtn.classList.replace("bx-menu" ,"bx-menu-alt-right");
  }else
    sidebarBtn.classList.replace("bx-menu-alt-right", "bx-menu");
  }
</script>


 <script>
              function searchTable() {
                  var input, filter, found, table, tr, td, i, j;
                  input = document.getElementById("myInput");
                  filter = input.value.toUpperCase();
                  table = document.getElementById("project-tickets");
                  tr = table.getElementsByTagName("tr");
                  for (i = 0; i < tr.length; i++) {
                      td = tr[i].getElementsByTagName("td");
                      for (j = 0; j < td.length; j++) {
                          if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
                              found = true;
                          }
                      }
                      if (found) {
                          tr[i].style.display = "";
                          found = false;
                      } else {
                          tr[i].style.display = "none";
                      }
                  }
              }
              </script>
              
<script>
              function searchTable() {
                  var input, filter, found, table, tr, td, i, j;
                  input = document.getElementById("myInput");
                  filter = input.value.toUpperCase();
                  table = document.getElementById("assigned");
                  tr = table.getElementsByTagName("tr");
                  for (i = 0; i < tr.length; i++) {
                      td = tr[i].getElementsByTagName("td");
                      for (j = 0; j < td.length; j++) {
                          if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
                              found = true;
                          }
                      }
                      if (found) {
                          tr[i].style.display = "";
                          found = false;
                      } else {
                          tr[i].style.display = "none";
                      }
                  }
              }
              </script>

</body>
</html>