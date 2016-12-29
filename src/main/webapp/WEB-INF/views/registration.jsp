<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Client Registration Form</title>
		<!-- css -->
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	</head>
	<body>
		<div class="container">
		<h2>Registration Form</h2>
	
		<form:form method="POST" modelAttribute="client">
			<form:input type="hidden" path="id" id="id" />
			<div class="form-group row">
				<label for="name">Name: </label>
				<form:input path="name" id="name" class="form-control" />
				<form:errors path="name" cssClass="error" />
			</div>
			<div class="form-group row">
				<label for="joiningDate">Joining Date: </label>
				<form:input path="joiningDate" id="joiningDate" class="form-control" />
				<form:errors path="joiningDate" cssClass="error" />
			</div>
			<div class="form-group row">
				<label for="salary">Salary: </label>
					<form:input path="salary" id="salary" class="form-control" />
					<form:errors path="salary" cssClass="error" />
			</div>
			<div class="form-group row">
				<label for="ssn">SSN: </label>
					<form:input path="ssn" id="ssn" class="form-control" />
					<form:errors path="ssn" cssClass="error" />
			</div>
			<div class="form-group row">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-info" />
					</c:when>
					<c:otherwise>
						<input type="submit" value="Register" class="btn btn-info" />
					</c:otherwise>
				</c:choose>
			</div>
		</form:form>
		<br />
		<br /> Go back to
		<a href="<c:url value='/list' />">List of All Clients</a>
		</div>
		
		<!-- scripts -->
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
		<script src="<c:url value="/resources/js/main.js" />"></script>
	</body>
</html>