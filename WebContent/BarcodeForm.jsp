<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	
	 <%@ page isELIgnored="false" %>
	 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
  
   <%@ page import="models.ItemsBarcode,helpers.Constants" %>
    <% ItemsBarcode itemBarcode = (ItemsBarcode) request.getAttribute("itemBarcode"); %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<style><%@include file="/resources/css/bootstrap.min.css"%></style>
	<script><%@include file="/resources/js/jquery-3.4.1.min.js" %></script>
	<script><%@include file="/resources/js/bootstrap.min.js" %></script>
	
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
<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
<style><%@include file="/resources/css/main.css"%></style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>Insert title here</title> --%>
<script type="text/javascript">

</script>
<link href="${contextPath}/resource/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="layout-form">

<h1 >Update </h1>

	<form  action="${requestScope.action}" method="POST">
	<table align ="center">
	
	 <div>
	<input type="hidden" name="barcodeId" value="${requestScope.model.id}" />
	</div> 
	
	<div class="form-group">
	 <label class="col-sm-2 col-form-label">Item Name:</label>
	         <div class="col-sm-7">
			<input type="text" name="itemName" placeholder="Item Name" value="${requestScope.model.itemName}"> <BR>
			   <c:if test="${not empty requestScope.errName}">
				<br/><span>${requestScope.errName}</span>
			   </c:if>
			</div>
			 </div>
			
		
    <div class=" form-group">

			<label  class="col-sm-2 col-form-label" >Description:</label>
			<div class="col-sm-7">	
			<textarea  rows="4" cols="30" placeholder="Enter text here..." name="description" >${requestScope.model.comments}</textarea>
			<!--  <td><input type="text" name="description"> <BR></td> -->
			<c:if test="${not empty requestScope.errComments}">
				<br/><span >${requestScope.errComments}</span>
			</c:if>
			</div>
			</div>
			<div class=" form-group">
		  <label class="col-sm-2 col-form-label" > Condition:</label>
		  <div class="col-sm-7">
			<input type="text" name="condition" placeholder="Condition"  value="${requestScope.model.condition}" > <BR>
			<c:if test="${not empty requestScope.errCondition}">
				<br/><span >${requestScope.errCondition}</span>
			</c:if>
			</div>
			</div>
			
			
        
		<td><input type="submit" value="Update Item" class="btn btn-primary"></td><span class="SucCtlMsg">${requestScope.SucCtlMsg}</span>
		  
		
	</table> 	
	</form>
		
		
	
	</div>
	</div>
	</div>
</body>
</html>	