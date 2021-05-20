<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@ page isELIgnored="false" %>
<%@ page import="models.Event,helpers.Constants" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Event ${requestScope.action} Details</title>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
		
</head>
<body class="rmdB">
	<%@ include file="/_shared/LeftBar.jsp"%>
	<%@ include file="/_shared/message.jsp"%>
	
	<form class="toolBox" id="topbox"> 			
		<input type="hidden" name="id" value="${requestScope.model.id}" />
			<c:if test="${sessionScope.urole == 'Administrator' or sessionScope.urole == 'Manager'}">
				<div>
					<button type="submit" formaction="Edit" formmethod="get"><i class="fas fa-pen tablebtn" style="color:green;"> Edit</i></button>
					<button type="button"  onClick="javascript:confirmGo('Are you sure you want to delete?','./Delete?id=${requestScope.model.id}')"><i class="fa fa-close tablebtn" style="color:red;"> Delete</i></button>
				</div>
			</c:if>	
				<button type="submit" formaction="./." formmethod="get"><i class="fas fa-arrow-left"> Back to List</i></button>
	</form>
	
	
	
	<main class="rmdT">
		<section class="elements" id="topopen">
			<p><strong>Event Name:</strong> ${requestScope.model.name}</p>

			<c:if test="${not empty requestScope.model.eventDate}">
				<p><strong>Event Date:</strong> ${requestScope.model.eventDate}</p>
			</c:if>
			<c:if test="${not empty requestScope.model.location}">
				<p><strong>Event Location:</strong> ${requestScope.model.location}</p>
			</c:if>
			<c:if test="${not empty requestScope.model.client}">
				<p><strong>Client Name:</strong> ${requestScope.model.client}</p>
			</c:if>
			<c:if test="${not empty requestScope.model.telephone}">
				<p><strong>Contact Telephone:</strong> ${requestScope.model.telephone}</p>
			</c:if>
			
			<c:if test="${sessionScope.urole == 'Administrator'}">
				<c:if test="${not empty requestScope.totalCost}">
					<p><strong>Total Cost Involved:</strong> ${requestScope.totalCost} CAD</p>
				</c:if>
			</c:if>
			
			<c:if test="${not empty requestScope.model.comments}">
				<p id="comments"><strong>Comments:</strong> ${fn:trim(requestScope.model.comments)}</p>
			</c:if>
			
		

			
			
			
		</section>

		<section class="rmdT">
		<script>let item;</script>
			<c:forEach var="item" items="${requestScope.listLinkedItems}">
				<c:if test="${empty item.dateBack}"> 
					<script>
						item = {
							quantity: ${item.quantity},
							multibarcode: ${item.multibarcode},
							name: "${item.name}",
							itemID: ${item.itemID},
						}
						loadedItems.set("${item.itemID}", item)
						
					</script>
				</c:if>
			</c:forEach>
			
			<c:forEach var="item" items="${requestScope.listAllItems}">
				<c:set var="someleft" value="${!multibarcode and item.qtyLeft != 0}"/>
				<c:if test="${someleft or item.multibarcode}"> 
					<script>
						item = {
							quantityLeft: ${item.qtyLeft},
							quantity: ${item.quantity},
							multibarcode: ${item.multibarcode},
							name: "${item.name}",
							itemID: ${item.itemID}
						}
						allItems.set("${item.itemID}", item)
					</script>
				</c:if>
			</c:forEach>
			<script>
				console.log(loadedItems)
				console.log(allItems)
			</script>
	<form method="POST">   
	
	
		<input type="hidden" value="${requestScope.model.id}" name="eventID"/>
        <div class="hidden scanTable" id="loadItems">
          <button class="btnSave" type="submit">Save All</button>
          <h2>Loading Items</h2>
          <table class="rmdT table-striped table-hover">
              <tr>
                <th>Barcode</th>
                <th>Item Name</th>
                <th>Quantity</th>
                <th></th>
              </tr>
              
            </table> 
        </div>

        <div  class="hidden scanTable" id="returnItems">
          <button class="btnSave" type="submit">Save All</button>
          <h2>Returning Items</h2>
          <table class="rmdT table-striped table-hover">
              <tr>
                <th>Barcode</th>
                <th>Item Name</th>
                <th>Quantity</th>
                <th></th>
              </tr>
              
            </table> 
        </div>
    </form>
			
			<div class="table-wrapper">
	<div class="table-title">
				<div class="row">
					<div class="col-sm-5">
						<h1>Supplier Details</h1>
					</div>
					<div class="col-sm-7">
						<div class="toolBox" id="searchBox"> 		
						<div id="barcodeInput" class="searchItem">
							<label for="barcode">Enter barcode</label>
							<input id="barcode" type="text" value="" placeholder="barcode" onkeypress="listen(event)"/>
							<button onclick="findbarcode()" type="button">Insert</button>
							<span style="color:red" id="error"></span>
						</div>
			          <div id="actionSwitch">
			            <span>Return</span>
			          <label class="switch">
			            <input id="action" type="checkbox" checked>
			            <span class="slider"></span>
			          </label>
			          <span>Load</span>
			        </div>
			        </div>

					</div>
				</div>
			</div>
				<table class="rmdT table-striped table-hover">				
						<tr>
							<th><span>Item</span></th>
							<th><span>Qty Out(Total)</span></th>
							<c:if test="${sessionScope.urole == 'Administrator'}">
								<th><span>Cost</span></th>
							</c:if>
							<th><span>Date Taken</span></th>
							<th><span>User Taken</span></th>
							<th><span>Date Returned</span></th>
							<th><span>User Returned</span></th>
						</tr>

		
			<c:choose>
			<c:when test="${not empty requestScope.listLinkedItems}">
						<c:forEach var="item" items="${requestScope.listLinkedItems}">
						<c:set var="userTaken">${item.userTaken}</c:set>
						<c:set var="userBack">${item.userBack}</c:set>	
									
						<tr>											
							<td><a href="${pageContext.request.contextPath}/UpdateBarcode?barcodeId=${item.itemID}"><span>${item.name}</span></a></td>
							<td><a href="${pageContext.request.contextPath}/UpdateBarcode?barcodeId=${item.itemID}"><span>
								<c:if test="${item.quantity != 0 or item.quantityHistoric != 0}">
									${item.quantity} (${item.quantityHistoric})
								</c:if>
							</span></a></td>
							
							<c:if test="${sessionScope.urole == 'Administrator'}">
								<td><a href="${pageContext.request.contextPath}/UpdateBarcode?barcodeId=${item.itemID}"><span>
									<c:if test="${item.cost != 0}">${item.cost}</c:if></span>
								</a></td>
							</c:if>
							<td><a href="${pageContext.request.contextPath}/UpdateBarcode?barcodeId=${item.itemID}"><span>${item.dateTaken}</span></a></td>
							<td><a href="${pageContext.request.contextPath}/UpdateBarcode?barcodeId=${item.itemID}"><span>${requestScope.listhmUsers[userTaken]}</span></a></td>
							<td class="<c:if test="${empty item.dateBack}">returnable" onclick="insertReturn(${item.itemID})</c:if>">
								<c:choose>
									<c:when test="${empty item.dateBack}">
										<a href="#"><span>${item.dateBack}</span></a>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/UpdateBarcode?barcodeId=${item.itemID}"><span>${item.dateBack}</span></a>
									</c:otherwise>
								</c:choose>
							</td>
							<td class="<c:if test="${empty item.dateBack}">returnable" onclick="insertReturn(${item.itemID})</c:if> " >
								<c:choose>
									<c:when test="${empty item.dateBack}">
										<a href="#"><span>${requestScope.listhmUsers[userBack]}</span></a>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/UpdateBarcode?barcodeId=${item.itemID}"><span>${requestScope.listhmUsers[userBack]}</span></a>
									</c:otherwise>
								</c:choose>
									
							</td>
						</tr>
					</c:forEach>
				
				
			</c:when>
			<c:otherwise>
				<tr><td colspan="6"><div>No Items Linked.</div></td></tr>
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
