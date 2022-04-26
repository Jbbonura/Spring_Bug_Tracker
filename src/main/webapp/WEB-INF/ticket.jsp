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
      <!--********************ADDITIONAL LINKS IF NEEDED*****************************-->
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
        <a href="#">
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
        <span class="admin_name">Demo Admin</span>
        <!-- <i class='bx bx-chevron-down' ></i> -->
      </div>
    </nav>
  <!--********************END TOP NAV BAR*****************************-->
    
  <div class="home-content">
      <div class="sales-boxes">
        <div class="recent-sales box">
          <div class="title">Details for ticket <!--add session--></div> 
          <div class="sales-details">
            <ul class="details">
              <li class="topic">Ticket Title</li>
              <li><a href="#">Great Work</a></li>
              <li class="topic">Assigned Developer</li>
              <li><a href="#">DemoD Dev</a></li>
              <li class="topic">Project</li>
              <li><a href="#">Demo Project 1</a></li>
              <li class="topic">Ticket Status</li>
              <li><a href="#">Open</a></li>
              <li class="topic">Created</li>
              <li><a href="#">createdat data from DB</a></li>
            </ul>
            
            <ul class="details">
              <li class="topic">Ticket Description</li>
              <li><a href="#">You're getting there, keep pushing</a></li>
              <li class="topic">Submitter</li>
              <li><a href="#">DemoS Submit</a></li>
              <li class="topic">Ticket Priority</li>
              <li><a href="#">Medium</a></li>
              <li class="topic">Ticket Type</li>
              <li><a href="#">Bugs Errors</a></li>
              <li class="topic">Upated</li>
              <li><a href="#">*Date from DB*</a></li>
          </ul>
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
                  <!-- <c:forEach var="comment" items="${comments}"> -->
                  <tr>
          <!-- <td ><c:out value="${comment.name }"/> </td> -->
          <td >Demo Dev </td>
          <!-- <td><c:out value="${comment.comment }" /></td> -->
          <td>Some comment goes here</td>
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
          </div>
        </div>
        
      </div>
    </div>
    <div class="bottomarea">
    <div class="bottombox1">
      <div class="top-sales box">
        <div class="table-header">
        <div class="title">Ticket Ticket History</div>
        
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
                <!-- <c:forEach var="comment" items="${comments}"> -->
                <tr>
        <!-- <td ><c:out value="${comment.name }"/> </td> -->
        <td >Demo Dev </td>
        <!-- <td><c:out value="${comment.comment }" /></td> -->
        <td>Some comment goes here</td>
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
        </div>
      </div>
    </div>
    <div class="bottombox2">
      
      <div class="top-sales box">
        <div class="table-header">
        <div class="title">Ticket Attachements</div>
        
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
                <!-- <c:forEach var="comment" items="${comments}"> -->
                <tr>
        <!-- <td ><c:out value="${comment.name }"/> </td> -->
        <td >Demo Dev </td>
        <!-- <td><c:out value="${comment.comment }" /></td> -->
        <td>Some comment goes here</td>
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
            <div class="upload-box">
              <form method="post" enctype="multipart/form-data">
                <div>
                  <label for="image_uploads">Choose file to upload</label>
                  <input type="file" id="file" name="file" multiple>
                </div>
                <div class="preview">
                  <p>No files currently selected for upload</p>
                </div>
                <div>
                  <button>Submit</button>
                </div>
              </form>
            </div>
        </div>
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