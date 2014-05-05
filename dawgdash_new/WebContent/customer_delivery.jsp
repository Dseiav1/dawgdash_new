<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Delivery</title>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
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
					<a class="navbar-brand" href="Delivery?task=NEW_DELIVERY&id=${client.id}">Schedule Delivery</a>
					<a class="navbar-brand" href="Delivery?task=CUSTOMER_PENDING_DELIVERIESid=${client.id}">Pending Deliveries</a>
					<a class="navbar-brand" href="Delivery?task=CUSTOMER_PAST_DELIVERIES&id=${client.id}">Past Deliveries</a>
					<a class="navbar-brand" href="User?task=CUSTOMER_MODIFY_ACCOUNT&id=${client.id}">Account Info</a>
				</div>
			</div>
		</div>
	
	
		<!-- TODOPLACEMENT -->
		<c:if test="${empty delivery}">
			<h1>Error: No such delivery or not authorized</h1>
		</c:if>
		
		<!-- PLACE INTO BOOTSTRAP -->
		<c:if test="${not empty delivery}">
			<h1>Delivery ${delivery.id}</h1>
			<p>Description: ${delivery.description}</p><br>
			<p>Date: ${delivery.date}</p><br>
			<p>Price: ${delivery.price}</p><br>
			<p>Carrier comments: ${delivery.workerComments}</p>
		</c:if>
	</body>
</html>