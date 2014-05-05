<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Create an Account</title>
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
		</div>
	</div>



	<h1>Create an Account</h1>

	<!-- TODO PLACEMENT -->
	<c:if test="${not empty error}">
		<p>${error}</p>
	</c:if>



	<div class="well">
		<form method="POST" class="form-horizontal" action="User">
			<fieldset>
				<input type="hidden" name="task" value="CREATE_CUSTOMER_ACCOUNT" />



				<div class="form-group">
					<label class="col-sm-4 control-label">Name:</label>
					<div class="col-sm-8">
						<input type="text" placeholder="Name" class="form-control"
							name="name" />
					</div>
				</div>



				<div class="form-group">
					<label class="col-sm-4 control-label">User name:</label>
					<div class="col-sm-8">
						<input type="text" placeholder="Username" class="form-control"
							name="username" />
					</div>
				</div>



				<div class="form-group">
					<label class="col-sm-4 control-label">Email Address:</label>
					<div class="col-sm-8">
						<input type="email" placeholder="Email Address"
							class="form-control" name="email" />
					</div>
				</div>



				<div class="form-group">
					<label class="col-sm-4 control-label">Street Address 1:</label>
					<div class="col-sm-8">
						<input type="text" placeholder="Street Line 1"
							class="form-control" name="address" />
					</div>
				</div>



				<div class="form-group">
					<label class="col-sm-4 control-label">Password:</label>
					<div class="col-sm-8">
						<input type="password" placeholder="Password" class="form-control"
							name="password" />
					</div>
				</div>



				<div class="form-group">
					<label class="col-sm-4 control-label">Verify Password:</label>
					<div class="col-sm-8">
						<input type="password" placeholder="Verify Password"
							class="form-control" name="verify_password" />
					</div>
				</div>



				<div class="form-actions">
					<div class="col-md-12">
						<input type="submit" class="btn btn-primary btn-md"
							value="Create account" />
					</div>
				</div>



			</fieldset>
		</form>
	</div>


</body>
</html>