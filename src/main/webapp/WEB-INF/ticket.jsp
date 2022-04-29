<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
  <meta charset="UTF-8">
  <title> Ticket Details </title>
  <link rel="stylesheet" href="/CSS/ticket.css">
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
  <!--********************END SIDE NAV BAR*****************************-->
  <section class="home-section">
  <!--********************TOP NAV BAR*****************************-->  
    <nav>
      <div class="sidebar-button">
        <i class='bx bx-menu sidebarBtn'></i>
        <span class="dashboard">Ticket Details</span>
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
        <!-- <i class='bx bx-chevron-down' ></i> -->
      </div>
    </nav>
  <!--********************END TOP NAV BAR*****************************-->
    
  <div class="home-content">
      <div class="sales-boxes">
        <div class="recent-sales box">
          <div class="title">Details for ticket <!--add session--></div> 
          <div class="sales-details">
            <form:form action="/ticket/${ticket.id}" method="post" modelAttribute="ticket">
            	<input type="hidden" name="_method" value="put">
            	<div class="user-details">
                    <div class="input-box">
                      	<p>
                      		Title: <c:out value = "${ticket.title}"/>
                      		<form:hidden path="title" value="${ticket.title}"/>
                  	  	</p>
                  	</div>
                    <div class="input-box">
                        <p>
                            Project: <c:out value = "${ticket.project.name}"/>
                            <form:hidden path="project" value="${ticket.project.id}"/>
                            <form:hidden path="submitter" value="${ticket.submitter.id}"/>
                        </p>
                    </div>
                    		<div class="input-box">
                        		<p>
                           		
                           		 <c:choose>
                    				<c:when test="${user_role < 3}"> 
                    					Ticket Priority:
		                            	<form:errors path="priority" />
		                            	<form:select path="priority">
		                            		<form:option value="0">None</form:option>
		                            		<form:option value="1">Low</form:option>
		                            		<form:option value="2">Medium</form:option>
		                            		<form:option value="3">High</form:option>
		                           	 </form:select>
		                           	 </c:when>
		                    		<c:otherwise>
		                    			 Ticket Priority:
		                    			 <form:hidden path="priority" value="${ticket.priority}"/>
		                    			 <c:choose>
			          						<c:when test="${ticket.priority == 0}">
			          							 None
			          						</c:when>
			          						<c:when test="${ticket.priority == 1}">
			          							Low
			          						</c:when>
			          						<c:when test="${ticket.priority == 2}">
			          							Medium
			          						</c:when>
			          						<c:when test="${ticket.priority == 3}">
			          							High
			          						</c:when>
          								</c:choose>
		                    		</c:otherwise>
		                    	</c:choose>
		                        </p>
		                    </div>
                    <div class="input-box">
                        <p>
                            Ticket Type: <c:out value="${ticket.ticketType}"/>
                            <form:hidden path="ticketType" value="${ticket.ticketType}"/>
                        </p>
                    </div>
                    <div class="input-box">
                        <p>
                            Description: <c:out value="${ticket.description}"/>
                            <form:hidden path="description" value="${ticket.description}"/>
                        </p>
                    </div>
                    <div class="input-box">
                    	<p>
                    		Ticket Status: 
                    		<c:choose>
	                    		<c:when test="${user_role < 3}">
		                    		<form:errors path="ticketStatus" />
		                            <form:select path="ticketStatus">
		                            	<c:forEach var="status" items="${status}">
		                            		<form:option value="${status}"><c:out value="${status}"></c:out></form:option>
		                            	</c:forEach>
		                            </form:select>
	                            </c:when>
	                            <c:otherwise>
	                            	<c:out value="${ticket.ticketStatus}"/>
	                            	<form:hidden path="ticketStatus" value="${ticket.ticketStatus}"/>
	                            </c:otherwise>
                            </c:choose>
                    	</p>
                    </div>
                   
		            <div class="input-box">
		                <p>
		                 	<c:choose> 
		                   		<c:when test="${user_role < 2}">
		                    		Assign Developer:
		                            <form:select path="assignedDev" required="required">
		                            	<form:option value="" disabled="">Please assign ticket to Dev</form:option>
		                            	<c:forEach var="dev" items="${devs}">
		                            		<c:choose>
		                            			<c:when test="${dev.id == ticket.assignedDev.id}">
		                            				<form:option value="${dev.id}" selected="true"><c:out value="${dev.name}"></c:out></form:option>
		                            			</c:when>
		                            			<c:otherwise>
		                            				<form:option value="${dev.id}"><c:out value="${dev.name}"></c:out></form:option>
		                            			</c:otherwise>
		                            		</c:choose>
		                            		
		                            	</c:forEach>
		                            </form:select>
		                    	 </c:when>
	                    		<c:otherwise>
	                    			Assigned Developer: 
	                    			<c:choose>
	                    				<c:when test="${ticket.assignedDev == null}">
	                    					UNASSIGNED
	                    				</c:when>
	                    				<c:otherwise>
	                    					<c:out value="${ticket.assignedDev.name}"/>
	                    					<form:hidden path="assignedDev" value="${ticket.assignedDev.id}"/>
	                    				</c:otherwise>
	                    			</c:choose>
	                    	 	</c:otherwise> 
	                    	 </c:choose>
	                   	</p>
		            </div> 
                </div>
                <c:if test="${user_role < 3}">
	                <div class="button">
	                   	<input type="submit" value="Edit">
	                </div>
                </c:if>
            </form:form>
          </div>
        </div>
        <div class="top-sales box">
          <div class="table-header">
          <div class="title">Ticket Comments</div>
          
          <div class="search-box">
            <i class='bx bx-search' ></i>
            <input id='myInput' onkeyup='searchTable()' type='text' placeholder="Search">
          </div>
        </div>
          <div style="overflow-x:auto;" class="ticket-comments">
            <table style="width: 100%;" class="table-content"  id="commenttable">
                <thead>
                    <tr class="table100-head">
                        <th style="width:25%;"class="column1">Commenter</th>
                        <th style="width:50%;" class="column2">Message</th>
                        <th style="width:25%;" class="column3">Created</th>
                    </tr>  
                </thead>
                <tbody>
                <tr>
                	<td>
                		Test
                	</td>
                	
                </tr>

                </tbody>
            </table>
            
          </div> <!-- END OF TICKETS-COMMENTS -->
        </div> <!-- END OF TOP SALES BOX -->
       
      </div> <!-- END OF SALES BOXES -->
    </div> <!-- END OF HOME CONTENT -->
    
    
    
    <div class="bottomarea">
    	<div class="bottombox1">
      		<div class="top-sales box">
        		<div class="table-header">
       				<div class="title">Ticket History</div>
	        		<div class="search-box">
	          		<i class='bx bx-search' ></i>
	          		<input id='myInput' onkeyup='searchTable()' type='text' placeholder="Search">
	        		</div>
      			</div>
        		<div style="overflow-x:auto;" class="ticket-comments">
          		<table style="width: 100%;" class="table-content"  id="history-table">
             		 <thead>
		                 <tr class="table100-head">
		                     <th style="width:25%;"class="column1">Property</th>
		                     <th style="width:25%;" class="column2">Old Value</th>
		                     <th style="width:25%;" class="column3">New Value</th>
		                     <th style="width:25%;"class="column1">Date Changed</th>
		                 </tr>  
              		</thead>
              		<tbody>
                		<c:forEach var="comment" items="${comments}"> -->
		                <tr>
					        <td ><c:out value="${comment.name }"/> </td>
					        <td >Demo Dev </td>
					       	<td><c:out value="${comment.comment }" /></td>
					        <td>Some comment goes here</td>
					       	<td><c:out value="${comment.created_at }" /></td>
					        <td>20210426</td>
					        <td>
					        <div class="edit-action"> <div class="edit-button"><a class="edit-action2" href="/expenses/edit/${expense.id }">Edit</a> </div></div>
					        <form action="/expenses/delete/${expense.id}" method="post">
					          <input type="hidden" name="_method" value="delete"> 
					            <div class="button-small">
					            	<input type="submit" value="Delete">
					           	</div>
					        </form>
					        </td>
		      			</tr>
                		</c:forEach>
             		 </tbody>
          		</table>
        </div>
      </div>
    </div>
    <div class="bottombox2">
      
      <div class="top-sales box">
        <div class="table-header">
        	<div class="title">Add Comment</div>
      	</div>
        <form:form action="/comment/new" method="post" modelAttribute="comment">
        <form:hidden path="commentingUser" value=""/>
        	<div class="input-box">
            	<p>
                 <form:errors path="comment" />
                 <form:textarea rows="5" cols="40" wrap="soft" path="comment" />
            	 </p>
        	 </div>
        	<div class="button">
	           <input type="submit" value="Submit">
	        </div>
        </form:form>   
      </div>
    </div>
  </div>
  </section>
  
  
  <script>
              function searchTable() {
                  var input, filter, found, table, tr, td, i, j;
                  input = document.getElementById("myInput");
                  filter = input.value.toUpperCase();
                  table = document.getElementById("commenttable");
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
          table = document.getElementById("history-table");
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