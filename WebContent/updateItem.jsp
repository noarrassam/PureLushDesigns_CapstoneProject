<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="models.Item" %>
     <%@ page import="models.ItemSupplier" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ page import="models.ItemSupplier" %>
   <%
   	ItemSupplier supplier = (ItemSupplier) request.getAttribute("Supplier");
   %>
    
   <% Item item = (Item) request.getAttribute("item"); %>
    
<!DOCTYPE html>
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
<%-- <meta charset="ISO-8859-1">
<title>Update a Book</title>
<link href="style.css" rel="stylesheet" type="text/css">
 <style><%@include file="/resources/css/main.css"%></style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script type="text/javascript">
function toggleSidebar(){
	document.getElementById("sidebar").classList.toggle('active');
}
</script>
<link href="${contextPath}/resource/bootstrap.min.css" rel="stylesheet"> --%>
</head>
<body>


<form action="ListItem" method="get">
	<input class="btn btn-primary"  type="submit" value="Back To List" id="addItem">
	</form>

<div class="layout-form">

<h1 >Update Items</h1>
	<form  action="UpdateItem" method="get">
	
	
	
	


	
	<div class="form-group" style="display:none;">
	 <label>Item Id:</label>
	         <div class="col-sm-7">
			<input type="hidden" name="itemGroupID" value="<%= item.getitemGroupId()%>"  > 
			</div>
			 </div>
	<div class="form-group">
	 <label >Item Name:</label>
	         <div class="col-sm-7">
		<input type="text" name="itemName" required="required" placeholder="Item Name" value="<%= item.getName()%>"> 
		</div>
		</div>
			<div class="form-group">
	 <label class="col-sm-2 col-form-label">Category:</label>
	         <div class="col-sm-7">
	<select name="category" id="category"  >
	<option ><%= item.getCategory()%></option>
	
  <option value="<%= item.getCategory()%>">Master</option>
  <option value="<%= item.getCategory()%>"> Maint $ Supply</option>
  <option value="<%= item.getCategory()%>"> serveware catering</option>
  <option value="<%= item.getCategory()%>"> indian statues and props</option>
  <option value="<%= item.getCategory()%>"> florals</option>
  <option value="<%= item.getCategory()%>">Centerpiece & Glass Vase	</option>
    <option value="<%= item.getCategory()%>">Furniture & Misc</option>
     <option value="<%= item.getCategory()%>">Kissing Balls</option>
     <option value="<%= item.getCategory()%>">Charger Plate and Misc</option>
     <option value="<%= item.getCategory()%>">Sashes Runners Chair Cover</option>
     <option value="<%= item.getCategory()%>">tablecloth</option>
     <option value="<%= item.getCategory()%>">Skirting</option>
     <option value="<%= item.getCategory()%>">Backdrop Fabrics</option>
     <option value="<%= item.getCategory()%>">Pipe and Drape</option>
</select>
</div>
</div>

<div class="form-group">
	 <label class="col-sm-2 col-form-label">Description:</label>
	         <div class="col-sm-7">
			<textarea  rows="4" cols="30" required="required" textareaObject.value="<%= item.getdescription()%>" name="description" ><%= item.getdescription()%></textarea>
						<!--  <td><input type="text" name="description"> <BR></td> -->
			</div>
			</div>
			<div class="form-group">
	 <label class="col-sm-2 col-form-label">Initial Cost:</label>
	         <div class="col-sm-7">
			
			 <input type="text" required="required" value="<%= item.getinitialCost()%>" name="initialCost" > 
			</div>
			</div>
			<div class="form-group">
	 <label class="col-sm-2 col-form-label">Size:</label>
	         <div class="col-sm-7">
			 <input type="text" required="required"  value="<%= item.getsize()%>" name="size" > 
			</div>
			</div>
			<div class="form-group">
	 <label class="col-sm-2 col-form-label">Color:</label>
	         <div class="col-sm-7">
			 <input type="text" required="required" value="<%= item.getColour()%>" name="color" > 
			</div>
			</div>
			<div class="form-group">
	 <label class="col-sm-2 col-form-label">Location:</label>
	         <div class="col-sm-7">
			 <input type="text" required="required"  value="<%= item.getLocation()%>" name="Location" >
			</div>
			</div>
			<div class="form-group">
	 <label class="col-sm-2 col-form-label">Multibarcode Group:</label>
	         <div class="col-sm-7">
			 yes<input type="radio" id="yes"  value="<%= item.getmultiBarcode()%>" name="multiBarcode"  >
			     no<input type="radio" id="no"   value="<%= item.getmultiBarcode()%>" name="multiBarcode">
			</div>
			</div>
			<div class="form-group">
	 <label class="col-sm-2 col-form-label">Quantity:</label>
	         <div class="col-sm-7">
			 <td><input type="text" required="required" value="<%= item.getQuantity()%>" name="quantity" > <BR></td>
			</div>
			</div>
		<c:choose>
        <c:when test="${not empty requestScope.list }">
       <div class=" form-group">
			<label  class="col-sm-2 col-form-label" >Select Supplier:</label>
			<div class="col-sm-7">
        <select name="supplierList" >
        <c:forEach var="supplier" items="${requestScope.list}">
       	 <option value="${supplier.name}">${supplier.name}</option>
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
			
				
		<input class="btn btn-primary" type="submit" value="Update Item" id="addItem">
		
		
	</form>
	</div>
	</div>
	</div>
	</div>


</body>
</html>