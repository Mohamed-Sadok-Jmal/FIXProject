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
<link href="<c:url value="/resources/css/toastr.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/popup.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>

<body ng-app="fix" ng-controller="quotationCtrl">
	<jsp:include page="../templates/popup_quotation_buy.jsp"></jsp:include>
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
							<li><a href="#"> <span>Foulen Fouleni</span> <img
									class="img-reponsive img-profile" alt="profile"
									src="<c:url value="/resources/img/profile-picture.jpg" />">
							</a></li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
	</header>
	<div class="container">
		<!-- Quotations -->
		<div class="row">
			<h2>List of Quotations</h2>
		</div>
		<div class="row">
			<div class="pull-right">
				<label for="search" class="control-label"> Search : <input
					type="search" id="search" ng-model="query" />
				</label>
			</div>
		</div>
		<div class="row">
			<div class="pull-left">
				<label for="pageSize" class="control-label"> Display <select
					ng-model="pageSize" id="pageSize">
						<option class="form-control" value="5">5</option>
						<option class="form-control" value="10" ng-selected="true">10</option>
						<option class="form-control" value="25">25</option>
						<option class="form-control" value="50">50</option>
						<option class="form-control" value="100">100</option>
				</select> Elements per page
				</label>
			</div>
			<div class="pull-right">
				<button class="btn btn-info" ng-disabled="currentPage == 0"
					ng-click="currentPage=currentPage-1">Previous</button>
				<span>{{currentPage+1}}/{{numberOfPages()}}</span>
				<button class="btn btn-info"
					ng-disabled="currentPage >= quotations.length/pageSize - 1"
					ng-click="currentPage=currentPage+1">Next</button>
			</div>
		</div>
		<div class="row" id="dvContents">
			<table
				class="table table-striped table-hover table-responsive sticky-header">
				<thead>
					<tr>
						<th></th>
						<th class="th-sort" ng-click="sort('name')">Nom
							<div class="t-top"></div>
							<div class="t-bottom"></div>
						</th>
						<th class="th-sort" ng-click="sort('opening')">Ouverture
							<div class="t-top"></div>
							<div class="t-bottom"></div>
						</th>
						<th class="th-sort" ng-click="sort('top')">+Haut
							<div class="t-top"></div>
							<div class="t-bottom"></div>
						</th>
						<th class="th-sort" ng-click="sort('low')">+Bas
							<div class="t-top"></div>
							<div class="t-bottom"></div>
						</th>
						<th class="th-sort" ng-click="sort('volumeTitles')">Volume
							(Titres)
							<div class="t-top"></div>
							<div class="t-bottom"></div>
						</th>
						<th class="th-sort" ng-click="sort('volumeDT')">Volume (DT)
							<div class="t-top"></div>
							<div class="t-bottom"></div>
						</th>
						<th class="th-sort" ng-click="sort('latest')">Dernier
							<div class="t-top"></div>
							<div class="t-bottom"></div>
						</th>
						<th class="th-sort" ng-click="sort('variation')">Variation
							<div class="t-top"></div>
							<div class="t-bottom"></div>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="quotation in quotations | filter:query | orderBy:orderProp:reverse | startFrom:currentPage*pageSize | limitTo:pageSize">
						<td><button ng-click="getQuotationToBuy(quotation.id)" class="open-popup btn-buy">Acheter</button></td>
						<td>{{quotation.name}}</td>
						<td>{{quotation.opening}}</td>
						<td>{{quotation.top}}</td>
						<td>{{quotation.low}}</td>
						<td>{{quotation.volumeTitles}}</td>
						<td>{{quotation.volumeDT}}</td>
						<td>{{quotation.latest}}</td>
						<td>{{quotation.variation}}</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="row">
			<div class="pull-left">
				<label for="pageSize" class="control-label"> Display <select
					ng-model="pageSize" id="pageSize">
						<option class="form-control" value="5">5</option>
						<option class="form-control" value="10" ng-selected="true">10</option>
						<option class="form-control" value="25">25</option>
						<option class="form-control" value="50">50</option>
						<option class="form-control" value="100">100</option>
				</select> Elements per page
				</label>
			</div>
			<div class="pull-right">
				<button class="btn btn-info" ng-disabled="currentPage == 0"
					ng-click="currentPage=currentPage-1">Previous</button>
				<span>{{currentPage+1}}/{{numberOfPages()}}</span>
				<button class="btn btn-info"
					ng-disabled="currentPage >= cities.length/pageSize - 1"
					ng-click="currentPage=currentPage+1">Next</button>
			</div>
		</div>
		<!-- end test angular -->
	</div>

	<!-- scripts -->
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/libraries/toastr.js" />"></script>
	<script src="<c:url value="/resources/js/libraries/popup.js" />"></script>
	<script src="<c:url value="/resources/js/main.js" />"></script>
	<script src="<c:url value="/resources/js/angular.min.js" />"></script>
	<script src="<c:url value="/resources/js/libraries/Chart.min.js" />"></script>
	<script
		src="<c:url value="/resources/js/libraries/angular-chart.min.js" />"></script>
	<script src="<c:url value="/resources/js/app.js" />"></script>
	<script
		src="<c:url value="/resources/js/services/notificationService.js" />"></script>
	<script
		src="<c:url value="/resources/js/controllers/quotationController.js" />"></script>
</body>
</html>