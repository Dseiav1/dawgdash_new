<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Modify Account Info</title>
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


	<!-- TODOPLACEMENT -->
	<c:if test="${empty access_error}">
		<h1>Modify Account Info</h1>



		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3>Change Password:</h3>




							<!-- TODOPLACEMENT -->
							<c:if test="${not empty password_error}">
							${password_error}
							</c:if>


							
							<div class="well">
								<form action="User" class="form-horizontal" method="POST">
									<fieldset>
										<input type="hidden" name="task"
											value="WORKER_CHANGE_PASSWORD" />



										<div class="form-group">
											<label class="col-sm-4 control-label">Current
												password:</label>
											<div class="col-sm-8">
												<input type="password" name="old_password" />
											</div>
										</div>



										<div class="form-group">
											<label class="col-sm-4 control-label">New password:</label>
											<div class="col-sm-8">
												<input type="password" name="new_password" />
											</div>
										</div>



										<div class="form-group">
											<label class="col-sm-4 control-label">Verify new
												password:</label>
											<div class="col-sm-8">
												<input type="password" name="verify_password" />
											</div>
										</div>



										<div class="form-actions">
											<div class="col-md-12">
												<input type="submit" value="Change password" />
											</div>
										</div>
									</fieldset>
								</form>
							</div>



							<h3>Change Email Address:</h3>


							<!-- TODOPLACEMENT -->
							<c:if test="${not empty email_error}">
									${email_error}
							</c:if>
							
							
							
							
							<div class="well">
								<form action="User" class="form-horizontal" method="POST">
									<fieldset>
										<input type="hidden" name="task" value="WORKER_CHANGE_EMAIL" />




										<div class="form-group">
											<label class="col-sm-4 control-label">Password:</label>
											<div class="col-sm-8">
												<input type="password" name="password" />
											</div>
										</div>



										<div class="form-group">
											<label class="col-sm-4 control-label">New email
												address:</label>
											<div class="col-sm-8">
												<input type="text" name="new_email" />
											</div>
										</div>




										<div class="form-group">
											<label class="col-sm-4 control-label">Verify new
												email address:</label>
											<div class="col-sm-8">
												<input type="text" name="verify_email" />
											</div>
										</div>




										<div class="form-actions">
											<div class="col-md-12">
												<input type="submit" value="Change email address" />
											</div>
										</div>
									</fieldset>
								</form>
							</div>



							<h3>Change Vehicle Type:</h3>


							<!-- TODOPLACEMENT -->
							<c:if test="${not empty vehicle_error}">
									${vehicle_error}
							</c:if>

							
							
							
							
							<div class="well">
								<form action="User" class="form-horizontal" method="POST">
									<fieldset>
										<input type="hidden" name="task" value="WORKER_CHANGE_VEHICLE" />


							
										<div class="form-group">
											<label class="col-sm-4 control-label">Password:</label>
											<div class="col-sm-8">
												<input type="password" name="password" /><br>
											</div>
										</div>



										<div class="form-group">
											<label class="col-sm-4 control-label">Select largest
												available vehicle: </label>
											<div class="col-sm-8">
												<select name="vehicle" class="form-control">
													<option value="none">Select below...</option>
													<option value="bike">Bike</option>
													<option value="car">Car</option>
													<option value="truck">Truck</option>
												</select>
											</div>
										</div>


							
										<div class="form-actions">
											<div class="col-md-12">
												<input type="submit" value="Change vehicle type" />
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



	<!-- TODOPLACEMENT -->
	<c:if test="${not empty access_error}">
		<h1>Error: Not authorized</h1>
	</c:if>
</body>
</html>