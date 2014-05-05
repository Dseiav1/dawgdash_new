<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>User Scheduling</title>

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
				<a class="navbar-brand" href="User?task=ADMINISTRATION">Administration</a>
				<a class="navbar-brand" href="User?task=SCHEDULE">Scheduling</a>
			</div>
		</div>
	</div>



	<!-- TODO BOOTSTRAP -->
	<c:if test="${empty access_error}">
		<h1>User Scheduling</h1>
		<table>
			<tr>
				<th>Worker</th>
			</tr>
			<c:forEach var="worker" items="${workers}">
				<tr>
					<td><a
						href="User?task=INDIVIDUAL_SCHEDULE&worker_id=${worker.id}">${worker.name}</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${not empty access_error}">
		<h1>Error: Not authorized</h1>
	</c:if>
</body>
</html>