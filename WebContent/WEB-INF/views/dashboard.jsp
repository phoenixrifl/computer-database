<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard?search="> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${pagination.getNbTotalComputers()} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" /> 
						<input type="submit" id="searchsubmit" value="Filter by name" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="deleteComputer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>

						<th>Computer name<a
							href="dashboard?search=${pagination.getSearch()}&orderbycolumn=computer.name&asc=ASC">
								<i class="fa fa-arrow-circle-o-down"></i>
						</a> <a
							href="dashboard?search=${pagination.getSearch()}&orderbycolumn=computer.name&asc=DESC">
								<i class="fa fa-arrow-circle-o-up"></i>
						</a></th>
						<th>Introduced date<a
							href="dashboard?search=${pagination.getSearch()}&orderbycolumn=computer.introduced&asc=ASC">
								<i class="fa fa-arrow-circle-o-down"></i>
						</a><a
							href="dashboard?search=${pagination.getSearch()}&orderbycolumn=computer.introduced&asc=DESC">
								<i class="fa fa-arrow-circle-o-up"></i>
						</a></th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date<a
							href="dashboard?search=${pagination.getSearch()}&orderbycolumn=computer.discontinued&asc=ASC">
								<i class="fa fa-arrow-circle-o-down"></i>
						</a><a
							href="dashboard?search=${pagination.getSearch()}&orderbycolumn=computer.discontinued&asc=DESC">
								<i class="fa fa-arrow-circle-o-up"></i>
						</a></th>
						<!-- Table header for Company -->
						<th>Company<a
							href="dashboard?search=${pagination.getSearch()}&orderbycolumn=company.name&asc=ASC">
								<i class="fa fa-arrow-circle-o-down"></i>
						</a><a
							href="dashboard?search=${pagination.getSearch()}&orderbycolumn=company.name&asc=DESC">
								<i class="fa fa-arrow-circle-o-up"></i>
						</a></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computers}" var="computer_carac">

						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer_carac.getId()}"></td>
							<td><a
								href="editComputer?id=${computer_carac.getId()}&computerName=${computer_carac.getName()}&introduced=${computer_carac.getIntroduced()}&discontinued=${computer_carac.getDiscontinued()}"
								onclick="">${computer_carac.getName()}</a></td>
							<td>${computer_carac.getIntroduced()}</td>
							<td>${computer_carac.getDiscontinued()}</td>
							<td>${computer_carac.getCompany_name()}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a href="dashboard?page=${pagination.getPage()-1}&PCparPage=${pagination.getLimit()}&search=${pagination.getSearch()}&orderbycolumn=${pagination.getByColumn()}&asc=${pagination.getByMode()}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach begin="${pagination.getBegin()}" end="${pagination.getEnd()}" varStatus="loop">
					<li><a href="dashboard?page=${loop.index}&limit=${pagination.getLimit()}&search=${pagination.getSearch()}&orderbycolumn=${pagination.getByColumn()}&asc=${pagination.getByMode()}">${loop.index}</a></li>
				</c:forEach>
				<li><a href="dashboard?page=${pagination.getPage()+1}&PCparPage=${pagination.getLimit()}&search=${pagination.getSearch()}&orderbycolumn=${pagination.getByColumn()}&asc=${pagination.getByMode()}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a href="dashboard?page=1&limit=10&search=${pagination.getSearch()}&orderbycolumn=${pagination.getByColumn()}&asc=${pagination.getByMode()}" class="btn btn-default">10</a> 
				<a href="dashboard?page=1&limit=50&search=${pagination.getSearch()}&orderbycolumn=${pagination.getByColumn()}&asc=${pagination.getByMode()}" class="btn btn-default">50</a> 
				<a href="dashboard?page=1&limit=100&search=${pagination.getSearch()}&orderbycolumn=${pagination.getByColumn()}&asc=${pagination.getByMode()}" class="btn btn-default">100</a>
			</div>
		</div>
	</footer>
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/dashboard.js"></script>

</body>
</html>