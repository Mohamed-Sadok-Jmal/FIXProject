<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FIX</title>
<!-- css -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>

<body>
	<header>
		<div class="row top-header"></div>
		<div class="row bottom-header">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#bs-navbar-collapse"
							aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">GL5-Finance</a>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse" id="bs-navbar-collapse">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">Cotations</a></li>
							<li><a href="#">Portefeuille</a></li>
							<li><a href="#">Passer un ordre</a></li>
							<li><a href="#">Mouvements</a></li>
						</ul>
						<form class="navbar-form navbar-left">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search">
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</form>
						<ul class="nav navbar-nav navbar-right">
							<li>
								<a href="#">
									<span>Foulen Fouleni</span>
									<img class="img-reponsive img-profile" alt="profile" src="<c:url value="/resources/img/profile-picture.jpg" />">
								</a>
							</li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
	</header>
	<div class="container">
		<div class="row">
			<h2>List of Employees</h2>
		</div>
		<div class="row">
			<table>
				<tr>
					<td>NAME</td>
					<td>Joining Date</td>
					<td>Salary</td>
					<td>SSN</td>
					<td></td>
				</tr>
				<c:forEach items="${employees}" var="employee">
					<tr>
						<td>${employee.name}</td>
						<td>${employee.joiningDate}</td>
						<td>${employee.salary}</td>
						<td><a
							href="<c:url value='/edit-${employee.ssn}-employee' />">${employee.ssn}</a></td>
						<td><a
							href="<c:url value='/delete-${employee.ssn}-employee' />">delete</a></td>
					</tr>
				</c:forEach>
			</table>
			<a href="<c:url value='/new' />">Add New Employee</a>
		</div>
		<!-- Quotations -->
		<div class="row">
			<h2>List of Quotations</h2>
		</div>
		<div class="row">
			<table class="table table-striped">
				<tr>
					<td>Nom</td>
					<td>Ouverture</td>
					<td>+Haut</td>
					<td>+Bas</td>
					<td>Volume (Titres)</td>
					<td>Volume (DT)</td>
					<td>Dernier</td>
					<td>Variation</td>
					<td></td>
					<td></td>
				</tr>
				<c:forEach items="${quotations}" var="quotation">
					<tr>
						<td>${quotation.name}</td>
						<td>${quotation.opening}</td>
						<td>${quotation.top}</td>
						<td>${quotation.low}</td>
						<td>${quotation.volumeTitles}</td>
						<td>${quotation.volumeDT}</td>
						<td>${quotation.variation}</td>
						<td>${quotation.latest}</td>
						<td><a
							href="<c:url value='/edit-${quotation.name}-quotation' />">${quotation.name}</a></td>
						<td><a
							href="<c:url value='/delete-${quotation.name}-quotation' />">delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<!-- scripts -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/main.js" />"></script>
</body>
</html>