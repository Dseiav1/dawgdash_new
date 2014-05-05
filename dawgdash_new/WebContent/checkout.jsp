<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Checkout</title>
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
					<a class="navbar-brand" href="Delivery?task=NEW_DELIVERY&id=${client.iD}">Schedule Delivery</a>
					<a class="navbar-brand" href="Delivery?task=CUSTOMER_PENDING_DELIVERIESid=${client.iD}">Pending Deliveries</a>
					<a class="navbar-brand" href="Delivery?task=CUSTOMER_PAST_DELIVERIES&id=${client.iD}">Past Deliveries</a>
					<a class="navbar-brand" href="User?task=CUSTOMER_MODIFY_ACCOUNT&id=${client.iD}">Account Info</a>
				</div>
			</div>
		</div>
	
	
	
	
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-body">
						<h3>Checkout</h3>
						
						
						
						<div class="well">
							<form action="Delivery" class="form-horizontal" method="POST">
								<fieldset>
									<input type="hidden" name="task" value="SUBMIT_PAYMENT" /> 
									<input type="hidden" name="user_id" value="${user.iD}" />
									
									
									
									<p>Price: ${price}</p>
									
									
									
									<div class="form-group">
										<label class="col-sm-4 control-label">Credit card
											number:</label>
										<div class="col-sm-8">
											<input type="text" name="credit_card" />
										</div>
									</div>

									
									
									<div class="form-group">
										<label class="col-sm-4 control-label">Billing address:</label>
										<div class="col-sm-8">
											<input type="text" name="billing_address" />
										</div>
									</div>
									
									
									
									<div class="form-actions">
										<div class="col-md-12">
											<input type="submit" value="Submit payment" />
										</div>
									</div>
								
								
								
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>