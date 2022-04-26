<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/CSS/reg.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<div class="main-container">     
        <div class="container">
            <div class="title">Login</div>
            <div class="content">
                <form:form action="/login" method="post" modelAttribute="newLogin">
                    <div class="user-details">
                        <div class="input-box">
                            <p class="details">
	                            Email:
	                            <form:input path="email" placeholder="Enter your email" />
								<form:errors path="email" />
                            </p>
                        </div>
                        <div class="input-box">
                            <p class="details">
	                            Password:
	                            <form:input path="password" placeholder="Enter your password" />
								<form:errors path="password" />
                            </p>
                        </div>
                    </div>
                    <div class="button">
                        <input type="submit" value="Login">
                    </div>
                </form:form>
 			</div>
 				<div class="actions1">
	            	<div class="button2">
	                	<input type="submit" value="Demo Admin">
	               </div>
	               <div class="button2">
	               		<input type="submit" value="Demo PM">
	               </div>
	               <div class="button2">
	                    <input type="submit" value="Demo Dev">
	               </div>
                </div>
                <div class="actions2">
                    <div class="button2">
                        <input type="submit" value="Demo New User">
                    </div>
                    <div class="button2">
                        <input type="submit" value="Demo Submitter">
                    </div>
                </div>
   		</div>
	</div>
</body>
</html>