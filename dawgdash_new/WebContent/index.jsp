<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<title>Dawg Dash Deliveries</title>
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
				<a class="navbar-brand" href="index.jsp">Dawg Dash Deliveries</a>
			</div>
			<div class="navbar-collapse collapse">
				
				
				
			
				
				<c:if test="${not empty login_error}">
					<p>${login_error}</p>
				</c:if>
				
				
				
				<form class="navbar-form navbar-right" method="POST" action="User">
					<fieldset>
						<input type="hidden" name="task" value="LOGIN" />

						
						
						<div class="form-group">
							<input type="text" placeholder="Username" class="form-control"
								name="username" />
						</div>
						
						
						
						<div class="form-group">
							<input type="password" placeholder="Password"
								class="form-control" name="password" />
						</div>
						
						
						
						<input type="submit" class="btn btn-red btn-sm" value="Login" />
					</fieldset>
				</form>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</div>

	<!-- Prints out create account completed, between the navbar and jumbotron -->
	<c:if test="${not empty confirmation}">
		<p>${confirmation}</p>
	</c:if>

	
	
	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<img src="Images/DawgDashLogo.png" class="img-responsive"
				alt="Responsive image">
			<p>Providing the local Athens Area Community, with fast reliable
				delivery services</p>
			<p>
				<a class="btn btn-redblack btn-lg"
					href="User?task=NEW_CUSTOMER_ACCOUNT">Create an Account<span
					class="glyphicon glyphicon-chevron-right"></span></a>
			</p>
		</div>
	</div>

	
	
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-custom">
					<div class="panel-body">
						
						
						<h3>Estimate:</h3>
						
						
						
						<div class="well-custom">
							<form method="GET" class="form-horizontal" action="Delivery">
								<input type="hidden" name="task" value="ESTIMATE" />
								
								
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Size of item:</label>
									<div class="col-sm-8">
										<select name="size" class="form-control">
											<option value="none">Select size...</option>
											<option value="letter">Letter/Document</option>
											<option value="small">Small package (~1 sq foot)</option>
											<option value="large">Large package</option>
										</select>
									</div>
								</div>
								
								
								
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Quantity:</label>
									<div class="col-sm-8">
										<input type="text" placeholder="Number of packages"
											class="form-control" name="quantity" /><br> <input
											type="submit" class="btn btn-red btn-lg" value="Get Estimate" />
									</div>
								</div>
								
							
							
							
							</form>
							
							<!-- TODO PLACEMENT -->		
							<c:if test="${not empty estimate_error}">
								<h3 class="text-center">${estimate_error}</h3>
							</c:if>
							<c:if test="${not empty estimate}">
								<h3 class="text-center">${estimate}</h3>
							</c:if>
						
						
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
