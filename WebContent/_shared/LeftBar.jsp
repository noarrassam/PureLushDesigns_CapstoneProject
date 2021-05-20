
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style><%@include file="/resources/css/style.css"%></style>
<script><%@include file="/resources/js/scripts.js" %></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<style><%@include file="/resources/css/bootstrap.min.css"%></style>
	<script><%@include file="/resources/js/jquery-3.4.1.min.js" %></script>
	<script><%@include file="/resources/js/bootstrap.min.js" %></script>
	<style type="text/css">
		body{
	/* width : 80%; */
	color: #566787;
	background: linear-gradient(90deg, rgba(122,127,133,1) 0%, rgba(228,232,237,1) 49%, rgba(122,127,133,1) 100%, rgba(88,95,102,0.975249474789916) 100%);
	font-family: 'Varela Round', sans-serif;
/* 	font-size: 13px; */
	margin-left: auto;
	margin-right : auto;
	}
	.navbar{
	width : 100%;
	background-color:white;
	color:rgba(0,0,0,.7); 
	position: relative;
	margin-left: auto;
	margin-right : auto;
	}
	
	footer {
		position:fixed;
		bottom:0;
		width:100%;
		}
	</style>
<%@ page import="helpers.Constants" %>
</head>
<body>
<nav class="navbar fixed-top navbar-expand-lg navbar-light h6" style="" >
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNavDropdown">
	    <ul class="navbar-nav w-100">
	    <li class="nav-item dropdown"><a class="nav-link "
					id="navbarItemLink" aria-haspopup="true" aria-expanded="false">
						<c:out value="Hello ${sessionScope.uname}" /><span class="blinker">.</span> </a></li>
		<c:if test="${sessionScope.urole == 'Administrator'}">				
		  <li class="nav-item dropdown"><a class="nav-link " href="${pageContext.request.contextPath}"
					id="navbarItemLink" aria-haspopup="true" aria-expanded="false">
						Users </a></li>
		</c:if>
		
		<c:if test="${sessionScope.urole == 'Administrator'}">				
		  <li class="nav-item dropdown"><a class="nav-link " href="${pageContext.request.contextPath}/viewLogs"
					id="navbarItemLink" aria-haspopup="true" aria-expanded="false">
						Logs </a></li>
		</c:if>
		
		<c:if test="${sessionScope.urole == 'Administrator' or sessionScope.urole == 'Manager'}">				
		  <li class="nav-item dropdown"><a class="nav-link " href="${pageContext.request.contextPath}/Supplier/"
					id="navbarItemLink" aria-haspopup="true" aria-expanded="false">
						Supplier </a></li>
		</c:if>
		<c:if test="${sessionScope.urole == 'Administrator' or sessionScope.urole == 'Manager' or sessionScope.urole == 'General User'}">				
		  <li class="nav-item dropdown"><a class="nav-link " href="${pageContext.request.contextPath}/Event/"
					id="navbarItemLink" aria-haspopup="true" aria-expanded="false">
						Events </a></li>
		</c:if>
		
		  <li class="nav-item dropdown"><a class="nav-link " href="${pageContext.request.contextPath}/ListItem"
					id="navbarItemLink" aria-haspopup="true" aria-expanded="false">
						Item </a></li>					
	      <li class="nav-item dropdown ml-auto">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          Account
	        </a>
	        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
	          <a class="dropdown-item" href="${pageContext.request.contextPath}">Home</a>
	          <!-- <a class="dropdown-item" href="#editModal" data-toggle="modal">Edit User</a>-->
	           <a class="dropdown-item" href="${pageContext.request.contextPath}/#editModalName" data-toggle="modal">Change Name</a>
	          <a class="dropdown-item" href="${pageContext.request.contextPath}/#editModal" data-toggle="modal">Change Password</a> 
	          <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
	        </div>
	      </li>
	    </ul>  
	  </div>
	</nav>
</body>
</html>