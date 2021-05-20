<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ page import="models.ItemSupplier" %>
   <%
   	ItemSupplier supplier = (ItemSupplier) request.getAttribute("Supplier");
   %>
	
	 <%@ page isELIgnored="false" %>
	 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
  
   <%@ page import="models.Item,helpers.Constants" %>
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>Insert title here</title>
<script type="text/javascript">
function toggleSidebar(){
	document.getElementById("sidebar").classList.toggle('active');
}
</script>
<link href="${contextPath}/resource/bootstrap.min.css" rel="stylesheet">
 --%>
</head>
<body>
<%--<%@ include file="/_shared/message.jsp"%>  --%>
<form  action="ListItem" method="get" >
	<button type="submit" class="btn btn-primary"><i class="fas fa-arrow-left"> Back to List</i></button>
	</form>
 

<div class="layout-form">

<h1 >Add Items</h1>



	
	

	<form  action="${requestScope.action}" method="POST">
	<table align ="center">
	
	
	 <label>Item Name:</label>
	      <div class="form-group"> 
			<input type="text" name="itemName" required="required" value="${requestScope.model.itemName}"> <BR>
			   <c:if test="${not empty requestScope.errName}">
				<br/><span>${requestScope.errName}</span>
			   </c:if>
			</div>
			
			
		
		<label class="col-sm-2 col-form-label">Category:</label>	
			
		
		 <div class=" form-group">	
	<select name="category" id="category"  >
  <option  value="Master">Master</option>
  <option value="Maint $ Supply">Maint $ Supply</option>
  <option value="serveware catering">serveware catering</option>
  <option value="indian statues and props">indian statues and props</option>
  <option value="florals">florals</option>
  <option value="Centerpiece & Glass Vase">Centerpiece & Glass Vase	</option>
    <option value="Furniture & Misc">Furniture & Misc</option>
     <option value="Kissing Balls">Kissing Balls</option>
     <option value="Charger Plate and Misc">Charger Plate and Misc</option>
     <option value="Sashes Runners Chair Cover">Sashes Runners Chair Cover</option>
     <option value="tablecloth">tablecloth</option>
     <option value="Skirting">Skirting</option>
     <option value="Backdrop Fabrics">Backdrop Fabrics</option>
     <option value="Pipe and Drape">Pipe and Drape</option>
</select>
<c:if test="${not empty requestScope.errCategory}">
				<br/><span>${requestScope.errCategory}</span>
			   </c:if>
</div>
</div>
    <div class=" form-group">

			<label  >Description:</label>
			<div class="col-sm-7">	
			<textarea  rows="4" cols="30" required="required" placeholder="Enter text here..." name="description"  value="${requestScope.model.description}" ></textarea>
			<!--  <td><input type="text" name="description"> <BR></td> -->
			<c:if test="${not empty requestScope.errDescription}">
				<br/><span>${requestScope.errDescription}</span>
			   </c:if>
			</div>
			</div>
			<div class=" form-group">
		  <label class="col-sm-2 col-form-label" > Initial Cost:</label>
		  <div class="col-sm-7">
			<input type="text"required="required"  name="initialCost" value="${requestScope.model.initialCost}" > <BR>
			<c:if test="${not empty requestScope.errCost}">
				<br/><span>${requestScope.errCost}</span>
			   </c:if>
			</div>
			</div>
			<div class=" form-group">
			<label  class="col-sm-2 col-form-label" >Size:</label>
			<div class="col-sm-7">
			<input type="text" required="required" name="size" value="${requestScope.model.size}"> <BR>
			<c:if test="${not empty requestScope.errSize}">
				<br/><span>${requestScope.errSize}</span>
			   </c:if>
			</div>
			</div>
			<div class=" form-group">
			<label class="col-sm-2 col-form-label" >Colour:</label>
			<div class="col-sm-7">
			 <input type="text" required="required" name="color" value="${requestScope.model.colour}"> <BR>
			 <c:if test="${not empty requestScope.errColor}">
				<br/><span>${requestScope.errColor}</span>
			   </c:if>
			</div>
			</div>
			<div class=" form-group">
			<label  class="col-sm-2 col-form-label" >Location:</label>
			<div class="col-sm-7">
			<input type="text" required="required" name="Location" value="${requestScope.model.location}"> <BR>
			<c:if test="${not empty requestScope.errLocation}">
				<br/><span>${requestScope.errLocation}</span>
			   </c:if>
			</div>
			</div>
			<div class=" form-group">
			<label  class="col-sm-2 col-form-label" >Items Countable:</label>
			<div class="col-sm-7">
			yes<input type="radio" id="yes" name="multiBarcode" value="yes">
			 no<input type="radio" id="no" name="multiBarcode" value="no"> <BR>
			 <c:if test="${not empty requestScope.errMultibarCode}">
				<br/><span>${requestScope.errMultibarCode}</span>
			   </c:if>
			</div>
			</div>
			<div class=" form-group">
			<label  class="col-sm-2 col-form-label" >Quantity:</label>
			<div class="col-sm-7">
			<input type="text" required="required" name="quantity" value="${requestScope.model.quantity}"> <BR>
			<c:if test="${not empty requestScope.errQuantity}">
				<br/><span>${requestScope.errQuantity}</span>
			   </c:if>
			</div>
			</div>
		
       <!-- Supplier -->
			<c:choose>
        <c:when test="${not empty requestScope.list }">
       <div class=" form-group">
			<label  class="col-sm-2 col-form-label" >Select Supplier:</label>
			<div class="col-sm-7">
        <select name="supplierList" >
        <c:forEach var="supplier" items="${requestScope.list}">
       	 <option value="${supplier.id}">${supplier.name}</option>
        </c:forEach>
        </select>
       </div>
	</div>

        </c:when>
        <c:otherwise>
        	<p>You dont have any suppliers yet</p>
        	<a href="/Supplier/Add">Add new Supplier</a>
        	<!-- <button type="button">Add new Supplier</button> -->
        </c:otherwise>
        </c:choose> 
        
        
       	<!-- category -->
       	
       	
<%-- 			<c:choose>
			<c:when test="${not empty requestScope.Categorylist }">
       <div class=" form-group">
			<label  class="col-sm-2 col-form-label" >Select Category:</label>
			<div class="col-sm-7">
        <select name="categoryList" >
        <c:forEach var="category" items="${requestScope.Categorylist}">
       	 <option value="${category.id}">${category.categoryType}</option>
        </c:forEach>
        </select>
       </div>
			</div>
        </c:when>
        <c:otherwise>
        	<p>You dont have any category yet</p>
        	<a href="/category/Add">Add new Category</a>
        	<!-- <button type="button">Add new Supplier</button> -->
        </c:otherwise>
        </c:choose> --%> 
			
        

		<td><input class="btn btn-primary"  type="submit" value="Add Item" id="addItem"></td>
		
		
		  

	</table> 	
	</form>
	</div>
	</div>
	</div>
</body>
</html>	