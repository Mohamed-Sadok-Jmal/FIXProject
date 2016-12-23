<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>University Enrollments</title>
		<!-- css -->
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	</head>

	<body>
		<div class="container">
			<div class="row"><h2>List of Employees</h2></div>
			<div class="row">	
				<table>
					<tr>
						<td>NAME</td><td>Joining Date</td><td>Salary</td><td>SSN</td><td></td>
					</tr>
					<c:forEach items="${employees}" var="employee">
						<tr>
						<td>${employee.name}</td>
						<td>${employee.joiningDate}</td>
						<td>${employee.salary}</td>
						<td><a href="<c:url value='/edit-${employee.ssn}-employee' />">${employee.ssn}</a></td>
						<td><a href="<c:url value='/delete-${employee.ssn}-employee' />">delete</a></td>
						</tr>
					</c:forEach>
				</table>
				<a href="<c:url value='/new' />">Add New Employee</a>
			</div>
		</div>
		
		<!-- scripts -->
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
		<script src="<c:url value="/resources/js/main.js" />"></script>
	</body>
</html>