<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<title>User Management Application</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<style><%@include file="/resources/css/bootstrap.min.css"%></style>
	<script><%@include file="/resources/js/jquery-3.4.1.min.js" %></script>
	<script><%@include file="/resources/js/bootstrap.min.js" %></script>
	
	<style type="text/css">
		.home-main{
			background: rgb(2,0,36);
			background: linear-gradient(90deg, rgba(122,127,133,1) 0%, rgba(228,232,237,1) 49%, rgba(122,127,133,1) 100%, rgba(88,95,102,0.975249474789916) 100%);
			padding: 13%;
			text-align: center;
		}
		.blinker{
			animation: blinker 1.5s linear infinite;
		}
		@keyframes blinker {
		  50% {
			opacity: 0;
		  }
		}
		.home-main button{
			background: #fff;
			color: #5812c5;
			border-radius: 0;
			font-weight: 700;
			width: 16%;
			height: 50px;
			top: 4%;
			margin-top: 3%;
		}
		.home-main button:hover{
			transition: 1s ease;
			color:#5812c5;
			width: 19%;
		}
		.content1-left{
			padding:9%;
		}
		.content1-right{
			padding:9%;
			background:#e4e4e4;
		}
		.home-content2{
			background: #adb5bd;
			padding: 1.4%;
			text-align: center;	
		}
		.home-content2 p{
			font-size: 18px;
			line-height: 50px;
		}
		.home-content2 p span{
			font-weight: 500;
		}
	</style>
</head>
<%@include file="event/table.jsp" %>
<%-- <body>
	<!-- NAVBAR -->
	<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
	  <div class="collapse navbar-collapse" id="navbarNavDropdown">
	    <ul class="navbar-nav w-100">
	    <li class="nav-item dropdown"><a class="nav-link "
					id="navbarItemLink" aria-haspopup="true" aria-expanded="false">
						<c:out value="Hello ${user.name}"/><span class="blinker"> .</span></a></li>
	      <li class="nav-item dropdown ml-auto">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          Account
	        </a>
	        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
	          <a class="dropdown-item" href="./">Home</a>
	          <!-- <a class="dropdown-item" href="#editModal" data-toggle="modal">Edit User</a>-->
	          <a class="dropdown-item" href="#editModalName" data-toggle="modal">Change Name</a>
	          <a class="dropdown-item" href="#editModal" data-toggle="modal">Change Password</a>
	          <a class="dropdown-item" href="logout">Logout</a>
	        </div>
	      </li>
	    </ul>  
	  </div>
	</nav>
	
	<!-- Edit Modal HTML -->
	<div id="editModalName" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Edit User</h4>	
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<form action="changePassGenUsersName" class="form-horizontal" role="form" method="post">
	                  <div class="form-group">
	                  
	                     <label  class="col-sm-2 control-label">Name</label>
	               
	                      	<div class="col-sm-12">
	                        	<input type="text" class="form-control" name="name" value="${user.name}"/>
	                    	</div>
	                  </div>
 
 					
	                  <input type="hidden" name="id" value="${user.id}"/>
	                  <input type="hidden" name="email" value="${user.email}"/>
	                  <input type="hidden" name="password" value="${pass.password}"/>
	                  <input type="hidden" name="role" value="${user.role}"/>
	                  <input type="hidden" name="status" value="${user.status}"/>
	                  
	                  <div class="form-group">
	                    <div class="col-sm-12">
	                      <input type="submit" class="btn btn-success" value="Update" />
	                    </div> 
	                  </div>
	                </form>
           	 	</div>
           	 </div>
		</div>
	</div>

	<!-- Edit Modal HTML -->
	<div id="editModal" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Edit User</h4>	
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<form action="changePassGenUsers" class="form-horizontal" role="form" method="post">
	                  <div class="form-group">
	                  
	                     <!--  <label  class="col-sm-2 control-label">Name</label>
	               
	                      	<div class="col-sm-12">
	                        	<input type="text" class="form-control" name="name" value="${user.name}" />
	                    	</div>-->
	                   
	                    
	                   <label  class="col-sm-12 control-label">Current Password</label>
	                      <div class="col-sm-12">
	                        <input type="password" class="form-control" name="currentPassword" required="required"  />
	                    </div>  
	                  </div>
	                   <div class="form-group">
	                    <label class="col-sm-2 control-label">Password</label>
	                    <div class="col-sm-12">
	                        <input type="password" class="form-control" name="password" required="required"  />
	                    </div>
	                  </div>
	                  
	                  
	                  <input type="hidden" name="id" value="${user.id}"/>
	                  <input type="hidden" name="name" value="${user.name}"/>
	                  <input type="hidden" name="email" value="${user.email}"/>
	                  <input type="hidden" name="role" value="${user.role}"/>
	                  <input type="hidden" name="status" value="${user.status}"/>
	                  
	                  <div class="form-group">
	                    <div class="col-sm-12">
	                      <input type="submit" class="btn btn-success" value="Update" />
	                    </div> 
	                  </div>
	                </form>
           	 	</div>
           	 </div>
		</div>
	</div>
	
  	<div class="container-fluid home-main">
  	<%@include file="event/table.jsp" %>
		<!-- <h2><c:out value="${user.name}" /> <span class="blinker">.</span></h2>
		<form action="edit">
			<input type="hidden" name="id" value="${user.id}"/>
			<a href="#editModal" class="btn btn-primary trigger-btn" title="Edit" data-toggle="modal"><i class="material-icons">settings</i>Edit User</a>
		</form> -->
	</div>
	<!--  <div class="container-fluid home-content1">
		<div class="row">
			<div class="col-md-6 content1-left">
				<h3> <span class="blinker">?</span></h3>
				<p></p>
				<div class="content1-left"></div> 
			</div>
			<div class="col-md-6 content1-right">
				<p></p>
			</div>
		</div>
	</div>-->
	
</body> --%>
</html>