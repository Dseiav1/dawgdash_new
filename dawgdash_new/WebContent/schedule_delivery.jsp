<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Schedule a Delivery</title>
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






	<h1>Schedule a Delivery</h1>
	
	
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
									<input type="hidden" name="task" value="SCHEDULE_DELIVERY" />

									
									
									<div class="form-group">
										<label class="col-sm-4 control-label">Pick-up address:
										</label>
										<div class="col-sm-8">
											<input type="text" placeholder ="(Leave blank to
											use your default address)" name="pickup_address" />
										</div>
									</div>
									
									
									
									
									<div class="form-group">
										<label class="col-sm-4 control-label">Destination
											address:</label>
										<div class="col-sm-8">
											<input type="text" name="destination_address" /><br>
										</div>
									</div>

									
									
									<div class="form-group">
										<label class="col-sm-4 control-label">Number of
											packages (between 1 and 10):</label>
										<div class="col-sm-8">
											<input type="number" min="1" max="10" name="quantity" /><br>
										</div>
									</div>
									
									
									
									<div class="form-group">
										<label class="col-sm-4 control-label">Largest package
											size:</label>
										<div class="col-sm-8">
											<select name="size" class ="form-control">
												<option value="0">Select below...</option>
												<option value="1">Letter/Document</option>
												<option value="2">Small package (~1 sq foot)</option>
												<option value="3">Large package</option>
											</select>
										</div>
									</div>

									
									
									<div class="form-group">
										<label class="col-sm-4 control-label">Brief
											description: </label>
										<div class="col-sm-8">
											<input type="text" name="description" /><br>
										</div>
									</div>

									
									
									<div class="form-group">
										<label class="col-sm-4 control-label">Special
											instructions: </label>
										<div class="col-sm-8">
											<input type="text" name="instructions" /><br>
										</div>
									</div>

									
									
									<div class="form-group">
										<label class="col-sm-4 control-label">Pick-up time
											(for today only):</label>
										<div class="col-sm-8">
											<select name="time" class="form-contorl">
												<option value="0000">Select below...</option>
												<option value="0800">08:00 am</option>
												<option value="0830">08:30 am</option>
												<option value="0900">09:00 am</option>
												<option value="0930">09:30 am</option>
												<option value="1000">10:00 am</option>
												<option value="1030">10:30 am</option>
												<option value="1100">11:00 am</option>
												<option value="1130">11:30 am</option>
												<option value="1200">12:00 pm</option>
												<option value="1230">12:30 pm</option>
												<option value="1300">01:00 pm</option>
												<option value="1330">01:30 pm</option>
												<option value="1400">02:00 pm</option>
												<option value="1430">02:30 pm</option>
												<option value="1500">03:00 pm</option>
												<option value="1530">03:30 pm</option>
												<option value="1600">04:00 pm</option>
												<option value="1630">04:30 pm</option>
												<option value="1700">05:00 pm</option>
												<option value="1730">05:30 pm</option>
											</select><br>
										</div>
									</div>
									
									
									
									<div class="form-actions">
										<div class="col-md-12">
											<input type="submit" value="Schedule delivery" />
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