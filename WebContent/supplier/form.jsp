<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@ page isELIgnored="false" %>
<%@ page import="models.Supplier,helpers.Constants" %>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	body {
		color: #fff;
		background: #344a71;
		font-family: 'Roboto', sans-serif;
	}
	.form-control {		
		min-height: 41px;
		box-shadow: none;
		border-color: #e1e4e5;
	}
	.form-control:focus {
		border-color: #49c1a2;
	}
	.form-control, .btn {        
        border-radius: 3px;
    }
	.layout-form {
		width: 600px;
		margin: 0 auto;
		padding: 30px 0;
	}
	.layout-form form {
		color: #9ba5a8;
		border-radius: 3px;
    	margin-bottom: 15px;
        background: #fff;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
	.layout-form h2 {
		color: #333;
		font-weight: bold;
        margin-top: 0;
    }
    .layout-form hr {
        margin: 0 -30px 20px;
    }    
	.layout-form .form-group {
		margin-bottom: 20px;
	}
	.layout-form label {
		font-weight: normal;
		font-size: 13px;
	}
	.layout-form .btn {        
        font-size: 16px;
        font-weight: bold;
		background: #49c1a2;
		border: none;
		min-width: 140px;
    }
	.layout-form .btn:hover, .signup-form .btn:focus {
		background: #3cb094;
        outline: none !important;
	}
	.layout-form a {
		color: #fff;
		text-decoration: underline;
	}
	.layout-form a:hover {
		text-decoration: none;
	}
	.layout-form form a {
		color: #49c1a2;
		text-decoration: none;
	}	
	.layout-form form a:hover {
		text-decoration: underline;
	}
	</style>
	<meta charset="UTF-8">
	<title>Supplier ${requestScope.action} Form</title>
</head>
<body>
	<%@ include file="/_shared/LeftBar.jsp"%>
	<%@ include file="/_shared/message.jsp"%>
	
	
	<div class="layout-form">
	<form action="${requestScope.action}" method="POST" class="rmdT">
		<h2>${requestScope.action} Supplier</h2>
		<p>${requestScope.action} Supplier details below</p>
	
		<input type="hidden" name="id" value="${requestScope.model.id}" />
		<div class="form-group">
			<label for="name">Supplier Name:</label>
			<input type="text" class="form-control" id="name" name="name" placeholder="Supplier Name" value="${requestScope.model.name}"/>
			<c:if test="${not empty requestScope.errName}">
				<br/><span class="err rmdT">${requestScope.errName}</span>
			</c:if>
		</div>
		<div class="form-group">
			<label for="contact">Contact Name:</label>
			<input type="text"  class="form-control" id="contact" name="contact" placeholder="Contact" value="${requestScope.model.contact}"/>
			<c:if test="${not empty requestScope.errContact}">
				<br/><span class="err">${requestScope.errContact}</span>
			</c:if>
		</div>
		<div class="form-group">
			<label for="telephone">Contact Telephone:</label>
			<input type="text" class="form-control" id="telephone" name="telephone" placeholder="telephone" value="${requestScope.model.telephone}"/>
			<c:if test="${not empty requestScope.errTelephone}">
				<br/><span class="err">${requestScope.errTelephone}</span>
			</c:if>
		</div>
		<div class="form-group">
			<label for="comments">Comments:</label> <br />
			<c:if test="${not empty requestScope.errComments}">
				<br/><span class="err">${requestScope.errComments}</span>
			</c:if>
			<textarea id="comments"  class="form-control" name="comments" placeholder="Add extra details" >${fn:trim(requestScope.model.comments)}</textarea>
		</div>
		<input type="submit" value="${requestScope.action}" class="btn btn-primary btn-block btn-lg"/>
	</form>
	</div>

	<footer class="page-footer font-small" style="background-color: #f5f5f5;">
		<div class="footer-copyright text-center py-4" style="align-items: center;">
			© 2021 <a>Internet Explorers </a>
		</div>
	</footer>
</body>
</html>