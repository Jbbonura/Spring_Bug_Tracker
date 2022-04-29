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
        <li>
<<<<<<< Updated upstream
          <a href="/user/roles">
            <i class='bx bx-box' ></i>
=======
          <a href="#">
            <i class='bx bxs-user-detail' ></i>
>>>>>>> Stashed changes
            <span class="links_name">Manage Role Assignment</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class='bx bxs-user-badge' ></i>
            <span class="links_name">Manage Project Users</span>
          </a>
        </li>
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
        <li>
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
        </li>
        <!-- <li>
          <a href="#">
            <i class='bx bx-message' ></i>
            <span class="links_name">Messages</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class='bx bx-heart' ></i>
            <span class="links_name">Favrorites</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class='bx bx-cog' ></i>
            <span class="links_name">Setting</span>
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
      <c:if test = "${user_role == 0}">
      <div class="new-ticket">
      	<a href="/project/new">New Project</a>
      </div>
      	
      </c:if>
      <div class="new-ticket">
      	<a href="/ticket/new">New Ticket</a>
      </div>
      <div class="profile-details">
        <!--<img src="images/profile.jpg" alt="">-->
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
	            <div class="box-topic">Unresolved Tickets Submitted By You</div>
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
	            <div class="box-topic">My Projects</div>
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
		
      <div class="sales-boxes">
        <div class="top-sales box">
            <div class="title">Whatever we call this</div>
            <ul class="top-sales-details">
              <li>
              <a href="#">
                <!--<img src="images/sunglasses.jpg" alt="">-->
                <span class="product">New Users</span>
              </a>
              <span class="price">0</span>
            </li>
            <li>
              <a href="#">
                 <!--<img src="images/jeans.jpg" alt="">-->
                <span class="product">Total Users </span>
              </a>
              <span class="price">1</span>
            </li>
            <li>
              <a href="#">
               <!-- <img src="images/nike.jpg" alt="">-->
                <span class="product">Tickets in Development</span>
              </a>
              <span class="price">2</span>
            </li>
            <li>
              <a href="#">
                <!--<img src="images/scarves.jpg" alt="">-->
                <span class="product">Total Developers</span>
              </a>
              <span class="price">0</span>
            </li>
            
            </ul>
          </div>
        <!--<div class="recent-sales box">
          <div class="title"></div>
          <div class="sales-details">
            <ul class="details">
              <li class="topic">Date</li>
              <li><a href="#">New Users</a></li>
              <li><a href="#">Total Users</a></li>
              <li><a href="#">02 Jan 2021</a></li>
              <li><a href="#">02 Jan 2021</a></li>
              <li><a href="#">02 Jan 2021</a></li>
              <li><a href="#">02 Jan 2021</a></li>
              <li><a href="#">02 Jan 2021</a></li>
            </ul>
           
          </div>
          <div class="button">
            <a href="#">See All</a>
          </div>
        </div>-->
        <div class="top-sales box">
          <div class="title">Company Data</div>
          <ul class="top-sales-details">
            <li>
            <a href="#">
              <!--<img src="images/sunglasses.jpg" alt="">-->
              <span class="product">Members</span>
            </a>
            <span class="price">1</span>
          </li>
          <li>
            <a href="#">
               <!--<img src="images/jeans.jpg" alt="">-->
              <span class="product">Projects </span>
            </a>
            <span class="price">2</span>
          </li>
          <li>
            <a href="#">
             <!-- <img src="images/nike.jpg" alt="">-->
              <span class="product">Tickets</span>
            </a>
            <span class="price">8</span>
          </li>
          <li>
            <a href="#">
              <!--<img src="images/scarves.jpg" alt="">-->
              <span class="product">Notifications</span>
            </a>
            <span class="price">56</span>
          </li>
         
          </ul>
        </div>
        <div class="top-sales box">
            <div class="title">Priority Projects</div>
            <ul class="top-sales-details">
              <li>
              <a href="#">
                <!--<img src="images/sunglasses.jpg" alt="">-->
                <span class="product">Some data chart here</span>
              </a>
              <span class="price"></span>
            </li>
            <li>
              <a href="#">
                 <!--<img src="images/jeans.jpg" alt="">-->
                <span class="product">Metric 1 </span>
              </a>
              <span class="price"></span>
            </li>
            <li>
              <a href="#">
               <!-- <img src="images/nike.jpg" alt="">-->
                <span class="product">Metric 2</span>
              </a>
              <span class="price"></span>
            </li>
            <li>
              <a href="#">
                <!--<img src="images/scarves.jpg" alt="">-->
                <span class="product">Metric 3</span>
              </a>
              <span class="price"></span>
            </li>
            
            </ul>
          </div>
          <div class="top-sales box">
            <div class="title">Whatever we call this</div>
            <ul class="top-sales-details">
              <li>
              <a href="#">
                <!--<img src="images/sunglasses.jpg" alt="">-->
                <span class="product">Some data chart here</span>
              </a>
              <span class="price"></span>
            </li>
            <li>
              <a href="#">
                 <!--<img src="images/jeans.jpg" alt="">-->
                <span class="product">Metric 1 </span>
              </a>
              <span class="price"></span>
            </li>
            <li>
              <a href="#">
               <!-- <img src="images/nike.jpg" alt="">-->
                <span class="product">Metric 2</span>
              </a>
              <span class="price"></span>
            </li>
            <li>
              <a href="#">
                <!--<img src="images/scarves.jpg" alt="">-->
                <span class="product">Metric 3</span>
              </a>
              <span class="price"></span>
            </li>
            
            </ul>
          </div>
      </div>
    </div>
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

</body>
</html>