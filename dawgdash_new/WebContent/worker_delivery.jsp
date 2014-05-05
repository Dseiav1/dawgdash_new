<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Delivery</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
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


	<!--  TODO PLACEMENT -->
	<c:if test="${empty delivery}">
			<h1>Error: delivery does not exist or not authorized</h1>
	</c:if>
	
	
	<!-- TODO PLACEMENT RUNS TILL END OF FORM -->
	<c:if test="${not empty delivery}">
		
		
		
	<h1>Delivery ${delivery.id}</h1>
	<h3>Mark delivery as complete:</h3>
	
	
	<!-- TODO PLACEMENT -->
	<c:if test="${not empty error}">
				${error}
	</c:if>
	
	
	
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-body">
					
					
					
						<div class="well">
							<form action="Delivery" class="form-horizontal" method="POST">
								<fieldset>
									<input type="hidden" name="task" value="WORKER_CLOSE_DELIVERY" />
									<input type="hidden" name="delivery_id" value="${delivery.id}" />
									
									
									
									<div class="form-group">
										<div class="col-sm-8">
											<input type="text" name="comment" />
										</div>
									</div>
									
									
									
									<div class="form-actions">
										<div class="col-md-12">
											<input type="submit" value="Complete Delivery" />
										</div>
									</div>
								
								
								
								</fieldset>
							</form>
						</div>
						
						
						
						<div class="well">
							<h3>Mark delivery as uncompletable:</h3>
							<form action="Delivery" method="POST">
								<fieldset>
									<input type="hidden" name="task" value="UNCOMPLETABLE"  /> 
									<input type="hidden" name="delivery_id" value="${delivery.iD}" />
									
									
									
									<div class="form-actions">
										<div class="col-md-12">
											<input type="submit" value="Report failed delivery" />
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
	
	</c:if>

</body>
</html>