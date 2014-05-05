<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Account Administration</title>
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
				<a class="navbar-brand" href="User?task=ADMINISTRATION">Administration</a>
				<a class="navbar-brand" href="User?task=SCHEDULE">Scheduling</a>
			</div>
		</div>
	</div>

	
	
	
	<!-- TODO PLACEMENT -->
	<c:if test="${empty access_error}">
		
		
		
		
	<h1>Account Administration</h1>


		<div class="container">
			
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3>Change Someone's Password</h3>
				
	<!-- TODO PLACEMENT -->			
	<c:if test="${not empty password_error}">
				${password_error}
	</c:if>
							<div class="well">
								<form action="User" class="form-horizontal" method="POST">
									<fieldset>
										<input type="hidden" name="task" value="ADMIN_CHANGE_PASSWORD" />
										
										
										<div class="form-group">
											<label class="col-sm-4 control-label">User:</label>
											<div class="col-sm-8">
												<select name="account" class="form-horizontal">
													<option value="none">Select a user...</option>
													<c:forEach var="user" items="${users}">
														<option value="${user.id}">${user.name};${user.role}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										
										
										<div class="form-group">
											<label class="col-sm-4 control-label">Manager password:</label>
											<div class="col-sm-8">
												<input type="password" name="manager_password" /><br>
											</div>
										</div>

										
										
										<div class="form-group">
											<label class="col-sm-4 control-label">New password:</label>
											<div class="col-sm-8">
												<input type="password" name="new_password" /><br>
											</div>
										</div>
										
										
										
										<div class="form-group">
											<label class="col-sm-4 control-label">Verify new
												password:</label>
											<div class="col-sm-8">
												<input type="password" name="verify_password" /><br>
											</div>
										</div>
										
										
										
										<div class="form-actions">
											<div class="col-md-12">
												<input type="submit" class="btn btn-primary btn-md"
													value="Change password" />
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

		
		
		
		
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3>Add New Worker</h3>
		
		<!-- TODO PLACEMENT -->
		<c:if test="${not empty new_worker_error}">
				${new_worker_error}<br>
		</c:if>

							<div class="well">
								<form action="User" class="form-horizontal" method="POST">
									<fieldset>
										<input type="hidden" name="task" value="ADMIN_CREATE_USER" />
										
										
										
										<div class="form-group">
											<label class="col-sm-4 control-label">Worker name:</label>
											<div class="col-sm-8">
												<input type="text" name="name" /><br>
											</div>
										</div>

										
										
										<div class="form-group">
											<label class="col-sm-4 control-label">Worker email
												address:</label>
											<div class="col-sm-8">
												<input type="text" name="email" /><br>
											</div>
										</div>

										
										
										<div class="form-group">
											<label class="col-sm-4 control-label">Worker
												username: </label>
											<div class="col-sm-8">
												<input type="text" name="username" /><br>
											</div>
										</div>

										
										
										<div class="form-group">
											<label class="col-sm-4 control-label">Worker
												password: </label>
											<div class="col-sm-8">
												<input type="password" name="password" /><br>
											</div>
										</div>
										
										
										
										<div class="form-group">
											<label class="col-sm-4 control-label">Verify worker
												password:</label>
											<div class="col-sm-8">
												<input type="password" name="verify_password" /><br>
											</div>
										</div>
										
										
										
										<div class="form-group">
											<label class="col-sm-4 control-label"></label>
											<div class="col-sm-8">
												<select name="transportation">
													<option value="0">Select largest available
														transportation</option>
													<option value="1">Bike</option>
													<option value="2">Car</option>
													<option value="3">Truck</option>
												</select>
											</div>
										</div>
										
										
										
										
										<div class="form-actions">
											<div class="col-md-12">
												<input type="submit" value="Create worker" />
											</div>
										</div>
				
				
				<!-- TODO PLACEMENT -->
				<c:if test="${not empty access_error}">
				<h1>Error: Not authorized</h1>
				</c:if>					
									
									
									
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