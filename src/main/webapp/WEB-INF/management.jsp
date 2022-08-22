<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="UTF-8">
<title> Dashboard </title>
<link rel="stylesheet" href="/CSS/project.css">
<link rel="stylesheet" href="/CSS/management.css">
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
            <i class='bx bx-grid-alt' ></i>
            <span class="links_name">Dashboard Home</span>
        </a>
        </li>
        <!-- <li>
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
        </li> -->
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
        <!-- <li>
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
 -->        <li class="log_out">
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
            <span class="dashboard">Manage User Roles </span>
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
            <form:form action="#" method="post" modelAttribute="users">
                <div class="box">
                    <div class="right-side">
                    <div class="box-topic">Select User</div>
                    	<form:select path="id" required="required">
                    	<c:forEach var="user" items="${users}">
                    		<form:option value="${user.id}"><c:out value="{user.name}"></c:out></form:option> 
                    	</c:forEach>
                    	</form:select>
                    <div class="input-box">
                        <p>
                            <form:errors path="ticketType" />
                            <form:select path="ticketType">
                            <c:forEach var="type" items="${types}">
                                <form:option value="${type}"><c:out value="${type}"></c:out></form:option>
                            </c:forEach>
                            </form:select>
                        </p>
                    </div>

                    </div>
                </div>
                <div class="box">
                    <div class="right-side">
                    <div class="box-topic">Assign Role</div>
                        <div class="input-box">
                            <p>
                                <form:errors path="insertpath" />
                                <form:select path="insertpath">
                                    <form:option value="0">Developer</form:option>
                                    <form:option value="1">Project Manager</form:option>
                                    <form:option value="2">Submitter</form:option>
                                    <form:option value="3">Demo</form:option>
                                </form:select>
                            </p>
                        </div>
                        <div class="button">
                            <input type="submit" value="Submit">
                            </div>
                         
                    </div>
                    </div>
                </form:form>
                </div>
                
            </div>
        <div class="bottom-boxes">
            <div class="top-sales">
            <div class="table-header">
                <div class="title">Personnel</div>
                <div class="search-box">
                <i class='bx bx-search' ></i>
                <input id='myInput' onkeyup='searchTable()' type='text' placeholder="Search">
                </div>
            </div>
            <div style="overflow-x:auto;" class="ticket-comments">
                <table style="width: 100%;" class="table-content"  id="personnel">
                    <thead>
                        <tr class="table100-head">
                            <th class="column1">Name</th>
                            <th  class="column2">Email</th>
                            <th  class="column3">Role</th>
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
            </div>
        </div>
        </div> <!--bottom boxes end -->
        </div> <!--home content end -->
    </section>
<!--************************JAVASCRIPT***************************-->

<!-- SIDE NAV BAR -->
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
<!-- PERSONNEL SEARCH BOX -->
<script>
    function searchTable() {
        var input, filter, found, table, tr, td, i, j;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("personnel");
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