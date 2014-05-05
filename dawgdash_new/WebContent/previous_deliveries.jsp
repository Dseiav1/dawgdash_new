<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Previous Deliveries</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>




<style>
table {
	border: 1px solid black;
}

th,td {
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

	
	<!-- TODO BOOTSTRAP -->
	<c:choose>
		<c:when test="${not empty past_deliveries}">
			<h1>Previous Deliveries</h1>
			<table>
				<tr>
					<th>Date</th>
					<th>Description</th>
				</tr>
				<c:forEach var="delivery" items="${past_deliveries}">
					<tr>
						<td>${delivery.date}</td>
						<td><a
							href="Delivery?task=CUSTOMER_PAST_DELIVERY&delivery_id=${delivery.id}">${delivery.description}</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<h1>No previous deliveries</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>