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
  <link rel="stylesheet" href="/CSS/add.css">
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
      <c:if test= "${user_role == 0}">
	        <li> 
	          <a href="/user/roles">
	            <i class='bx bxs-user-detail' ></i>
	            <span class="links_name">Manage Role Assignment</span>
	          </a>
	        </li>
        </c:if>
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
      <li class="log_out">
        <a href="/logout">
          <i class='bx bx-log-out'></i>
          <span class="links_name">Log out</span>
        </a>
      </li>
    </ul>
  </div>
  <!--********************END SIDE NAV BAR*****************************-->
  <section class="home-section">
  <!--********************TOP NAV BAR*****************************-->  
    <nav>
      <div class="sidebar-button">
        <i class='bx bx-menu sidebarBtn'></i>
        <span class="dashboard">New Project</span>
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
      <div class="container">
        <div class="title">Create a Project</div>
        <div class="content">
            <form:form action="/project/new" method="post" modelAttribute="newProject">
                <div class="user-details">
                    <div class="input-box">
                      	<p>
                      		Name:
                      		<form:errors path="name"/>
                      		<form:input path="name"/>
                  	  	</p>
                  	</div>
                    
                    <div class="input-box">
                        <p>
                            Description:
                            <form:errors path="description" />
                            <form:textarea rows="5" cols="40" wrap="soft" path="description" />
                        </p>
                    </div>
                    <div class="input-box">
                        <p>
                            Manager:
                            <form:errors path="manager" />
                            <form:select path="manager">
                            	<c:forEach var="user" items="${users}">
                            		<form:option value="${user.id}"><c:out value="${user.name}"></c:out></form:option>
                            	</c:forEach>
                            </form:select>
                         
                        </p>
                    </div>
                </div>
                    <div class="button">
                    <input type="submit" value="Submit">
                    <a href="/">Cancel</a>   
                </div>
            </form:form>  
            
            
            
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

