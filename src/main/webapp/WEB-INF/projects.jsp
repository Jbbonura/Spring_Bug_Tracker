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
  <link rel="stylesheet" href="/CSS/projects.css">
  <!-- Boxicons CDN Link -->
  <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
  <div class="sidebar">
    <div class="logo-details">
      <i class='bx bx-user' ></i>
      <span class="logo_name">Welcome,</span>  <!--add session name-->
    </div>
    <ul class="nav-links">
      <li>
        <a href="#" class="active">
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
        <a href="#">
          <i class='bx bx-pie-chart-alt-2' ></i>
          <span class="links_name">My Projects</span>
        </a>
      </li>
      <li>
        <a href="#">
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
        <span class="dashboard">Projects</span>
      </div>
      
      <div class="profile-details">
        <!--<img src="images/profile.jpg" alt="">-->
        <span class="admin_name">Demo Dev</span>
      </div>
    </nav>

    <div class="home-content">
      <div class="ticket-display">
        <div class="top-sales box">
          <div class="table-header">
          <div class="title">Projects List</div>
          
          <div class="search-box">
            <i class='bx bx-search' ></i>
            <input id='myInput' onkeyup='searchTable()' type='text' placeholder="Search">
          </div>
        </div>
          <div style="overflow-x:auto;" class="ticket-comments">
            <table style="width: 100%;" class="table-content"  id="projects-table">
                <thead>
                    <tr class="table100-head">
                        <th style="width:10%;"class="column1">Title</th>
                        <th style="width:15%;" class="column2">Progress</th>
                        <th style="width:10%;" class="column3">Project Manager</th>
                        <th style="width:10%;"class="column4">Team</th>
                        <th style="width:10%;"class="column5">Status</th>
                        <th style="width:10%;"class="column6">Completion Date</th>
                        <th style="width:15%;"class="column7">Actions</th>
                    </tr>  
                </thead>
                <tbody>
                  <!-- <c:forEach var="comment" items="${comments}"> -->
                  <tr>
          <!-- <td ><c:out value="${comment.name }"/> </td> -->
          <td >Demo Dev </td>
          <!-- <td><c:out value="${comment.comment }" /></td> -->
          <td>Do we have a way to track progress?</td>
          <!-- <td><c:out value="${comment.created_at }" /></td> -->
          <td>20210426</td>
          <!-- <td>
          <div class="edit-action"> <div class="edit-button"><a class="edit-action2" href="/expenses/edit/${expense.id }">Edit</a> </div></div>
          
          <form action="/expenses/delete/${expense.id}" method="post">
            <input type="hidden" name="_method" value="delete"> 
              <div class="button-small">
                        <input type="submit" value="Delete">
                      </div>
          </form></td> -->
        </tr>
                  </c:forEach>
                </tbody>
            </table>
            <script>
              function searchTable() {
                  var input, filter, found, table, tr, td, i, j;
                  input = document.getElementById("myInput");
                  filter = input.value.toUpperCase();
                  table = document.getElementById("projects-table");
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
          </div>
        </div>
      </div>
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