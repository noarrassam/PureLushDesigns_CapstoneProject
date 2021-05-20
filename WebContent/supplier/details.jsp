<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page isELIgnored="false" %>
<%@ page import="models.Supplier,helpers.Constants" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Supplier ${requestScope.action} Details</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
	
</head>
<body class="rmdB">
	<%@ include file="/_shared/LeftBar.jsp"%>
	<%@ include file="/_shared/message.jsp"%>
	
	
	
	<main class="rmdT">
		<section class="elements">
			<p><strong>Supplier Name:</strong> ${requestScope.model.name}</p>
			<c:if test="${not empty requestScope.model.contact}">
				<p><strong>Contact Name:</strong> ${requestScope.model.contact}</p>
			</c:if>
			<c:if test="${not empty requestScope.model.telephone}">
				<p><strong>Contact Telephone:</strong> ${requestScope.model.telephone}</p>
			</c:if>
			<c:if test="${not empty requestScope.model.comments}">
				<p id="comments"><strong>Comments:</strong> ${fn:trim(requestScope.model.comments)}</p>
			</c:if>
		</section>
		<section class="rmdT">
<div class="table-wrapper">
	<div class="table-title">
				<div class="row">
					<div class="col-sm-5">
						<h1>Supplier Details</h1>
					</div>
					<div class="col-sm-7">
					
	<form class="toolBox" action="details" id="searchBox"> 			
		<input type="hidden" name="id" value="${requestScope.model.id}" />
			<c:if test="${sessionScope.urole == 'Administrator' or sessionScope.urole == 'Manager'}">
				<div class="searchItem">
					<button type="submit" formaction="Edit" formmethod="get"><i class="fas fa-pen" style="color:green;"> Edit</i></button>
					<button type="button"  onClick="javascript:confirmGo('Are you sure you want to delete?','./Delete?id=${requestScope.model.id}')"><i class="fa fa-close" style="color:red;"> Delete</i></button>
				</div>
			</c:if>
				<button type="submit" formaction="./." formmethod="get"><i class="fas fa-arrow-left"> Back to List</i></button>
		</form>
					</div>
				</div>
			</div>
				<table class="rmdT table-striped table-hover">				
						<tr>
							<th><span>Item</span></th>
							<th><span>Category</span></th>
							<th><span>Description</span></th>
						</tr>
			<c:choose>
			<c:when test="${not empty requestScope.list}">
						<c:forEach var="item" items="${requestScope.list}">		
						<tr>											
							<td><a href="${pageContext.request.contextPath}/ItemDetails?itemGroupId=${item.id}"><span>${item.name}</span></a></td>
							<td><a href="${pageContext.request.contextPath}/ItemDetails?itemGroupId=${item.id}"><span>${item.category}</span></a></td>
							<td><a href="${pageContext.request.contextPath}/ItemDetails?itemGroupId=${item.id}"><span>${item.description}</span></a></td>
						</tr>
					</c:forEach>	
			</c:when>
			<c:otherwise>
				<tr><td colspan="3"><div>No Items Linked.</div></td></tr>
			</c:otherwise>
		</c:choose>
			</table>
			</div>
	
		</section>
		</main>
		
		<footer class="page-footer font-small" style="background-color: #f5f5f5;">
		<div class="footer-copyright text-center py-4" style="align-items: center;">
			<a> © 2021 Internet Explorers </a>
		</div>
	</footer>
	
</body>
</html>