<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Assigned Deliveries</title>


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
					href="Delivery?task=WORKER_PENDING_DELIVERIES">Pending
					Deliveries</a> <a class="navbar-brand"
					href="Delivery?task=CUSTOMER_PAST_DELIVERIES">Past Deliveries</a> <a
					class="navbar-brand" href="User?task=INDIVIDUAL_SCHEDULE">Schedule</a>
				<a class="navbar-brand" href="User?task=WORKER_MODIFY_ACCOUNT">Account
					Info</a>
			</div>
		</div>
	</div>



	<h1>Assigned Deliveries</h1>
	
	
	<!-- TODO PLACEMENT -->
	<!-- TODO BOOTSTRAP -->
	<c:if test="${empty pending_deliveries}">
		<h3>No pending deliveries</h3>
	</c:if>
	<c:if test="${not empty pending_deliveries}">
		<table>
			<tr>
				<th>Description</th>
				<th>Pick-up time</th>
				<th>Pick-up address</th>
				<th>Destination address</th>
			</tr>
			<c:forEach var="delivery" items="${pending_deliveries}">
				<tr>
					<td><a
						href="Delivery?task=VIEW_DELIVERY&delivery_id=${delivery.id}">
							${delivery.description} </a></td>
					<td>${delivery.pickUpTime}</td>
					<td>${delivery.pickupAddress}</td>
					<td>${delivery.destinationAddress}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<h3>
		<a href="User?task=WORKER_MODIFY_ACCOUNT">Modify account info</a>
	</h3>
</body>
</html>