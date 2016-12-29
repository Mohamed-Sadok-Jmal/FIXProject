<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Registration Confirmation Page</title>
		<!-- css -->
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<div class="row"><span class="text-success">message : ${success}</span></div>
			<div class="row">
				<a href="<c:url value='/list' />">Go back to List of All Clients</a>
			</div>
		</div>
		
		<!-- scripts -->
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
		<script src="<c:url value="/resources/js/main.js" />"></script>
	</body>
</html>