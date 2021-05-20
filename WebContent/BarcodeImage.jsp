<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@ page isELIgnored="false" %>
      <%@ page import="models.Item" %>
       <%@ page import="models.ItemsBarcode" %>
        <% ItemsBarcode listBarcode = (ItemsBarcode) request.getAttribute("barcode"); %>
       <% Item item = (Item) request.getAttribute("item"); %>
<!DOCTYPE html>
<html>
<head>
    <style><%@include file="/resources/css/bootstrap.min.css"%></style>
	<script><%@include file="/resources/js/jquery-3.4.1.min.js" %></script>
	<script><%@include file="/resources/js/bootstrap.min.js" %></script>

	
	<style type="text/css">
    body {
        color: #566787;
		background: linear-gradient(90deg, rgba(122,127,133,1) 0%, rgba(228,232,237,1) 49%, rgba(122,127,133,1) 100%, rgba(88,95,102,0.975249474789916) 100%);
		font-family: 'Varela Round', sans-serif;
		font-size: 13px;
	}
	.table-wrapper {
        background: #fff;
        padding: 20px 25px;
        margin: 0px 0;
		border-radius: 3px;
        box-shadow: 0 1px 1px rgba(0,0,0,.05);
    }
	.table-title {
		padding-bottom: 15px;
		background: black;
		color: #fff;
		padding: 16px 30px;
		margin: -20px -25px 10px;
		border-radius: 3px 3px 0 0;
    }
    .table-title h2 {
		margin: 1px 0 0;
		font-size: 24px;
	}
	.table-title .btn {
		color: #566787;
		float: right;
		font-size: 13px;
		background: #fff;
		border: none;
		min-width: 50px;
		border-radius: 2px;
		border: none;
		outline: none !important;
		margin-left: 10px;
	}
	.table-title .btn:hover, .table-title .btn:focus {
        color: #566787;
		background: #f2f2f2;
	}
	.table-title .btn i {
		float: left;
		font-size: 21px;
		margin-right: 5px;
	}
	.table-title .btn span {
		float: left;
		margin-top: 2px;
	}
    table.table tr th, table.table tr td {
        border-color: #e9e9e9;
		padding-left:0px;
	
		vertical-align: middle;
    }
	table.table tr th:first-child {
		width: 6px;
	}
	table.table tr th:last-child {
		width: 10px;
	}
    table.table-striped tbody tr:nth-of-type(odd) {
    	background-color: #fcfcfc;
	}
	table.table-striped.table-hover tbody tr:hover {
		background: #f5f5f5;
	}
    table.table th i {
        font-size: 13px;
        margin: 0 5px;
        cursor: pointer;
    }	
    table.table td:last-child i {
		opacity: 0.9;
		font-size: 22px;
        margin: 0 5px;
    }
	table.table td a {
		font-weight: bold;
		color: #566787;
		display: inline-block;
		text-decoration: none;
	}
	table.table td a:hover {
		color: #2196F3;
	}
	table.table td a.settings {
        color: #2196F3;
    }
    table.table td a.delete {
        color: #F44336;
    }
    table.table td i {
        font-size: 19px;
    }
	table.table .avatar {
		border-radius: 50%;
		vertical-align: middle;
		margin-right: 10px;
	}
	.status {
		font-size: 30px;
		margin: 2px 2px 0 0;
		display: inline-block;
		vertical-align: middle;
		line-height: 10px;
	}
    .text-success {
        color: #10c469;
    }
    .text-info {
        color: #62c9e8;
    }
    .text-warning {
        color: #FFC107;
    }
    .text-danger {
        color: #ff5b5b;
    }
    #text{
    color:black;
    }
    </style>
<%-- <meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link href="style.css" rel="stylesheet" type="text/css">
           
            <link rel="stylesheet" href="/${Constants.URL_PREFIX}style.css" />
            <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
            <style><%@include file="/resources/css/main.css"%></style> --%>

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
</head>
<body>
<form>
<h1 id="text">${requestScope.model.name} Item Details</h1>
	<form class="toolBox"> 			
		<input type="hidden" name="id" value="${requestScope.model.itemGroupId}" />
				<%-- <div>
					<button type="submit" formaction="Edit" formmethod="get"><i class="fas fa-pen tablebtn" style="color:green;"> Edit</i></button>
					<button type="button"  onClick="javascript:confirmGo('Are you sure you want to delete?','./Delete?id=${requestScope.model.itemGroupId}')"><i class="fa fa-close tablebtn" style="color:red;"> Delete</i></button>
				</div> --%>
				
				
	</form>
	<form  action="ListItem" method="get" >
	<button type="submit" class="btn btn-primary"><i class="fas fa-arrow-left"> Back to List</i></button>
	</form>
	<main >
	 <div class="col-sm-5">
			<h3 id="text">Item Name: ${requestScope.model.name}</h3>
			<c:if test="${not empty requestScope.model.description}">
				<h5 id="text">Description: ${requestScope.model.description}</h5>
			</c:if>
			</div>
			
	<c:choose>
		<c:when test="${not empty requestScope.count}">
		<div class="col-sm-5">
			<h5 id="text">The number of barcodes linked with ${requestScope.model.name}: ${requestScope.count}</h5>
	   </div>
		</c:when>
		<c:otherwise>
		<div class="col-sm-5">
			<h5 id="text">Quantity: ${requestScope.model.quantity}</h5>
		</div>
		</c:otherwise> 	
	 </c:choose>
			
<%-- 			<c:if test="${not empty requestScope.model.quantity}">
				<p>Quantity: ${requestScope.model.quantity}</p>
			</c:if> --%>
			<div class="col-sm-5">
			<c:if test="${not empty requestScope.model.multiBarcode}">
				<h5 id="text">Items Countable: ${requestScope.model.multiBarcode}</h5>
			</c:if>
			</div>
		<section>
		
		
		<div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-5">
						<h2>Barcode Item Detail</h2>
					</div>
					
                </div>
            </div>
            <table class="table table-striped table-hover">
                
		 <c:choose>
		<c:when test="${not empty requestScope.listBarcode}">
			<!-- <table border=1 id="items">	 -->	
			<thead>		
					<tr>
						<th>Barcode Id</th>
						<th>Item name</th>
						<th>comments</th>
						
					</tr>
			</thead>
				          <c:forEach var="barcode" items="${requestScope.listBarcode}">		
					      <tr> 	
					        <td>${barcode.id}</td>						
							<td>${barcode.itemName}</td>
							<td>${barcode.comments}</td>
					      </tr>
				          </c:forEach> 
			
		</c:when>
		<c:otherwise>
			<p >No Barcode  Found!</p>
		</c:otherwise> 	
	 </c:choose>
	 </table>
			<div class="clearfix">
                <div class="hint-text"></div>
            </div>
        </div>
    </div> 
<c:if test="${sessionScope.urole == 'Administrator' or sessionScope.urole == 'Manager'}">
 <c:choose>
        	 <c:when test="${requestScope.model.quantity > 0}">  
			<a class="btn btn-primary"  href="./GenerateBarcode?ItemGroupID=${requestScope.model.itemGroupId}">Add barcode number for ${requestScope.model.name} </a>
			<a class="btn btn-primary"  href="./ReadBarcode?ItemGroupID=${requestScope.model.itemGroupId}">List Barcode linked with ${requestScope.model.name} </a>
			</c:when>
      <c:otherwise>
          <p id="addItem">Cannot add barcode with quantity 0, Please update quantity </p>  
         </c:otherwise>
      </c:choose>
</c:if>
		</section>
	</main>
<div>


<!-- <img alt="my Image" src="CreateBarCode"> -->

</div>
</body>
</html>