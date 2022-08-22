<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 


<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    
    <title> Dashboard </title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js" ></script>
    <link rel="stylesheet" href="/CSS/dashboard.css">
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
          <a href="/" class="active">
            <i class='bx bx-home-alt bx-tada' ></i>
            <span class="links_name">Dashboard Home</span>
          </a>
        </li>
        <!-- <li> 
          <a href="/user/roles">
            <i class='bx bxs-user-detail' ></i>
            <span class="links_name">Manage Role Assignment</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class='bx bxs-user-badge' ></i>
            <span class="links_name">Manage Project Users</span>
          </a>
        </li> -->
        <li>
          <a href="/projects">
            <i class='bx bx-list-ol' ></i>
            <span class="links_name">My Projects</span>
          </a>
        </li>
        <li>
          <a href="/tickets">
            <i class='bx bx-book-alt' ></i>
            <span class="links_name">My Tickets</span>
          </a>
        </li>
        <!-- <li>
          <a href="#">
            <i class='bx bx-user' ></i>
            <span class="links_name">User Profile</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class='bx bxl-microsoft-teams' ></i>
            <span class="links_name">Team</span>
          </a>
        </li> -->
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
        <span class="dashboard">Dashboard</span>
      </div>
      <div class="top-links">
      <c:if test = "${user_role == 0}">
      <div class="new-ticket">
      	<a href="/project/new">New Project</a>
      </div>
      </c:if>
      <div class="new-ticket">
      	<a href="/ticket/new">New Ticket</a>
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
      </div>
    </nav>
    
    <div class="home-content">
      <div class="overview-boxes">
      <c:choose>
	      <c:when test="${user_role == 0 || user_role == 3}">
	      	<div class="box">
	          <div class="right-side">
	            <div class="box-topic">Total Projects</div>
	            <div class="number"><c:out value="${projectCount}"/></div> <!--NEED TO ADD COUNTER, ADD NEW ICON-->
	            <div class="indicator">
	              <i class='bx bx-up-arrow-alt'></i>
	              <span class="text">Some performance metric</span>
	            </div>
	          </div>
	          <!-- <i class='bx bx-cart-alt cart'></i> -->
	        </div>
	        <div class="box">
	          <div class="right-side">
	            <div class="box-topic">Total Unresolved Tickets</div>
	            <div class="number"><c:out value="${openTicketCount}"/></div>
	            <div class="indicator">
	              <i class='bx bx-up-arrow-alt'></i>
	              <span class="text">Some performance metric</span>
	            </div>
	          </div>
	          <!-- <i class='bx bxs-cart-add cart two' ></i> -->
	        </div>
	        <div class="box">
	          <div class="right-side">
	            <div class="box-topic">Total Unassigned Tickets</div>
	            <div class="number"><c:out value="${unassignedTicketCount}"/></div>
	            <div class="indicator">
	              <i class='bx bx-up-arrow-alt'></i>
	              <span class="text">Some performance metric</span>
	            </div>
	          </div>
	          <!-- <i class='bx bx-cart cart three' ></i> -->
	        </div>
	        <div class="box">
	          <div class="right-side">
	            <div class="box-topic">Your Unresolved Tickets</div>
	            <div class="number"><c:out value="${submittedTicketCount}"/></div>
	            <div class="indicator">
	              <i class='bx bx-down-arrow-alt down'></i>
	              <span class="text">Some performance metric</span>
	            </div>
	          </div>
	          <!-- <i class='bx bxs-cart-download cart four' ></i> -->
	        </div>
	      </c:when>
	      <c:otherwise>
	        <div class="box">
	          <div class="right-side">
	            <div class="box-topic">My Unresolved Tickets</div>
	            <div class="number"><c:out value="${openTicketCount}"/></div>
	            <div class="indicator">
	              <i class='bx bx-up-arrow-alt'></i>
	              <span class="text">Some performance metric</span>
	            </div>
	          </div>
	          <!-- <i class='bx bxs-cart-add cart two' ></i> -->
	        </div>
	        <div class="box">
	          <div class="right-side">
	            <div class="box-topic">My Resolved Tickets</div>
	            <div class="number"><c:out value="${resolvedTicketCount}"/></div>
	            <div class="indicator">
	              <i class='bx bx-up-arrow-alt'></i>
	              <span class="text">Some performance metric</span>
	            </div>
	          </div>
	          <!-- <i class='bx bx-cart cart three' ></i> -->
	        </div>
	        <div class="box">
	          <div class="right-side">
	            <div class="box-topic">My Submitted Unresolved Tickets</div>
	            <div class="number"><c:out value="${submittedTicketCount}"/></div>
	            <div class="indicator">
	              <i class='bx bx-down-arrow-alt down'></i>
	              <span class="text">Some performance metric</span>
	            </div>
	          </div>
	          <!-- <i class='bx bxs-cart-download cart four' ></i> -->
	        </div>
	      </c:otherwise>
	   </c:choose>
       
        
      </div>
	<!-- CHARTS -->
      <div class="sales-boxes">
        <div class="top-sales box">
        	<div class="title">Tickets By Status</div>
        		<canvas id="statusChart" width="600" height="400"></canvas>
          	</div>
       
        <div class="top-sales box">
          <div class="title">Tickets By Priority</div>
          		<canvas id="priorityChart" width="600" height="400"></canvas>
        </div>
        <div class="top-sales box">
        	<div class="title">Tickets By Type</div>
            	<canvas id="typeChart" width="600" height="400"></canvas>
        </div>
       
      </div>
    </div>
  </section>

 <script>
 	var newTicketStatusCount = [[${newTicketStatusCount}]];
 	var openTicketStatusCount = [[${openTicketStatusCount}]];
 	var	inProgressTicketStatusCount = [[${inProgressTicketStatusCount}]];
 	var resolvedTicketStatusCount = [[${resolvedTicketStatusCount}]];
 	var infoRequiredTicketStatusCount = [[${infoRequiredTicketStatusCount}]];
 	
 	var nonePriorityCount = [[${nonePriorityCount}]];
 	var lowPriorityCount = [[${lowPriorityCount}]];
 	var medPriorityCount = [[${medPriorityCount}]];
 	var highPriorityCount = [[${highPriorityCount}]];
 	
 	var requestTypeCount = [[${requestTypeCount}]];
 	var otherTypeCount = [[${otherTypeCount}]];
 	var bugTypeCount = [[${bugTypeCount}]];
 	
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
 <script src="JS/graphs.js"></script>


</body>
</html>