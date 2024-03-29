<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Schedule</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<style>
table {
	border: 1px solid black;
}

td,th {
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
					href="Delivery?task=WORKER_PENDING_DELIVERIES">Pending
					Deliveries</a> <a class="navbar-brand"
					href="Delivery?task=CUSTOMER_PAST_DELIVERIES">Past Deliveries</a> <a
					class="navbar-brand" href="User?task=INDIVIDUAL_SCHEDULE">Schedule</a>
				<a class="navbar-brand" href="User?task=WORKER_MODIFY_ACCOUNT">Account
					Info</a>
			</div>
		</div>
	</div>


	<!-- TODO PLACEMENT -->
	<!-- BOOTSTRAP -->
	<c:if test="${not empty schedule}">
		<h1>Schedule for ${schedule.name}</h1>
		<table>
			<tr>
				<td>Sunday</td>
				<td>${schedule.sunday}</td>
			</tr>
			<tr>
				<td>Monday</td>
				<td>${schedule.monday}</td>
			</tr>
			<tr>
				<td>Tuesday</td>
				<td>${schedule.tuesday}</td>
			</tr>
			<tr>
				<td>Wednesday</td>
				<td>${schedule.wednesday}</td>
			</tr>
			<tr>
				<td>Thursday</td>
				<td>${schedule.thursday}</td>
			</tr>
			<tr>
				<td>Friday</td>
				<td>${schedule.friday}</td>
			</tr>
			<tr>
				<td>Saturday</td>
				<td>${schedule.saturday}</td>
			</tr>
		</table>
		<h3>Modify schedule for ${schedule.name}:</h3>
		<form action="User" method="POST">
			<input type="hidden" name="task" value="CHANGE_WORKER_SCHEDULE" /> <input
				type="hidden" name="worker_id" value="${schedule.workerID}" />
			<table>
				<tr>
					<td>Sunday</td>
					<td><select name="sunday_shift">
							<option value="none">Select below...</option>
							<option value="0000-0000">Off</option>
							<option value="0800-1200">08:00 am - 12:00 pm</option>
							<option value="1000-1400">10:00 am - 02:00 pm</option>
							<option value="1200-1600">12:00 pm - 04:00 pm</option>
							<option value="1400-1800">02:00 pm - 06:00 pm</option>
					</select></td>
				</tr>
				<tr>
				<tr>
					<td>Monday</td>
					<td><select name="monday_shift">
							<option value="none">Select below...</option>
							<option value="0000-0000">Off</option>
							<option value="0800-1200">08:00 am - 12:00 pm</option>
							<option value="1000-1400">10:00 am - 02:00 pm</option>
							<option value="1200-1600">12:00 pm - 04:00 pm</option>
							<option value="1400-1800">02:00 pm - 06:00 pm</option>
					</select></td>
				</tr>
				<tr>
					<td>Tuesday</td>
					<td><select name="tuesday_shift">
							<option value="none">Select below...</option>
							<option value="0000-0000">Off</option>
							<option value="0800-1200">08:00 am - 12:00 pm</option>
							<option value="1000-1400">10:00 am - 02:00 pm</option>
							<option value="1200-1600">12:00 pm - 04:00 pm</option>
							<option value="1400-1800">02:00 pm - 06:00 pm</option>
					</select></td>
				</tr>
				<tr>
					<td>Wednesday</td>
					<td><select name="wednesday_shift">
							<option value="none">Select below...</option>
							<option value="0000-0000">Off</option>
							<option value="0800-1200">08:00 am - 12:00 pm</option>
							<option value="1000-1400">10:00 am - 02:00 pm</option>
							<option value="1200-1600">12:00 pm - 04:00 pm</option>
							<option value="1400-1800">02:00 pm - 06:00 pm</option>
					</select></td>
				</tr>
				<tr>
					<td>Thursday</td>
					<td><select name="thursday_shift">
							<option value="none">Select below...</option>
							<option value="0000-0000">Off</option>
							<option value="0800-1200">08:00 am - 12:00 pm</option>
							<option value="1000-1400">10:00 am - 02:00 pm</option>
							<option value="1200-1600">12:00 pm - 04:00 pm</option>
							<option value="1400-1800">02:00 pm - 06:00 pm</option>
					</select></td>
				</tr>
				<tr>
					<td>Friday</td>
					<td><select name="friday_shift">
							<option value="none">Select below...</option>
							<option value="0000-0000">Off</option>
							<option value="0800-1200">08:00 am - 12:00 pm</option>
							<option value="1000-1400">10:00 am - 02:00 pm</option>
							<option value="1200-1600">12:00 pm - 04:00 pm</option>
							<option value="1400-1800">02:00 pm - 06:00 pm</option>
					</select></td>
				</tr>
				<tr>
					<td>Saturday</td>
					<td><select name="saturday_shift">
							<option value="none">Select below...</option>
							<option value="0000-0000">Off</option>
							<option value="0800-1200">08:00 am - 12:00 pm</option>
							<option value="1000-1400">10:00 am - 02:00 pm</option>
							<option value="1200-1600">12:00 pm - 04:00 pm</option>
							<option value="1400-1800">02:00 pm - 06:00 pm</option>
					</select></td>
				</tr>
			</table>
			<input type="submit" value="Change schedule" />
		</form>
	</c:if>
	<c:if test="${empty schedule}">
		<h1>Error: selected ID does not correspond to a worker or not
			authorized</h1>
	</c:if>
</body>
</html>