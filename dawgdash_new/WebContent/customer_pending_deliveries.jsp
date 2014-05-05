<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Pending Deliveries</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

<style>
table {
	border: 1px solid black;
}

tr,th {
	text-align: center;
	border: 1px solid black;
}
</style>
</head>
<body>


	
	<div class="navbar navbar-custom navbar-static-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">Home</a>
			</div>
			<div class="navbar-collapse collapse">
				<a class="navbar-brand"
					href="Delivery?task=NEW_DELIVERY&id=${client.id}">Schedule
					Delivery</a> <a class="navbar-brand"
					href="Delivery?task=CUSTOMER_PENDING_DELIVERIESid=${client.id}">Pending
					Deliveries</a> <a class="navbar-brand"
					href="Delivery?task=CUSTOMER_PAST_DELIVERIES&id=${client.id}">Past
					Deliveries</a> <a class="navbar-brand"
					href="User?task=CUSTOMER_MODIFY_ACCOUNT&id=${client.id}">Account
					Info</a>
			</div>
		</div>
	</div>




	<h1>Pending Deliveries</h1>
	
	
	<!-- TODO BOOTSTRAP -->
	
	<c:choose>
		<c:when test="${not empty pending_deliveries}">
			<table>
				<tr>
					<th>Description</th>
					<th>Special instructions</th>
					<th>Pick-up time</th>
					<th>Price</th>
					<th>Cancel delivery</th>
				</tr>
				<c:forEach var="delivery" items="${pending_deliveries}">
					<tr>
						<td>${delivery.description}</td>
						<td>${delivery.instructions}</td>
						<td>${delivery.pickupTime}</td>
						<td>${delivery.price}</td>
						<td>
							<form action="Delivery" method="POST">
								<input type="hidden" name="task" value="CANCEL_DELIVERY" /> <input
									type="hidden" name="delivery_id" value="${delivery.iD}" /> <input
									type="submit" value="Cancel delivery" />
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			<h3>Total price: ${price}</h3>
			<a href="Delivery?task=CHECKOUT">Checkout</a>
		</c:when>
		<c:otherwise>
			<h3>No pending deliveries</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>