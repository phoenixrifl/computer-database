<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
<style type='text/css'>
/* CSS Document */
#error {
	display: none;
}

#insertionOk {
	display: none;
}
</style>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> <spring:message code="Application.name" text="Application - Computer Database"></spring:message> </a>
			<a class="nav navbar-brand navbar-right" href="addComputer?lang=FR">
					<spring:message code="application.langFR" text="French"></spring:message>
				</a> 
				<a class="nav navbar-brand navbar-right" href="addComputer?lang=EN">
					<spring:message code="application.langEN" text="English"></spring:message>
				</a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="alert alert-danger" role="alert" id="error"></div>
					<c:out value="${error}" />

					<div class="alert alert-light" role="alert" id="insertionOk"></div>
					<c:out value="${insertionOk}" />
					<h1><spring:message code="Add.title" text="Add Computer"></spring:message></h1>
					<form action="addComputer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message code="computer.name" text="Computer Name"></spring:message></label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" placeholder="<spring:message code="computer.name" text="Computer Name"></spring:message>">
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="introduced.date" text="Introduced date"></spring:message></label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="<spring:message code="introduced.date" text="Introduced date"></spring:message>"
									pattern=(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}>
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="discontinued.date" text="Discontinued date"></spring:message></label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="<spring:message code="discontinued.date" text="Discontinued date"></spring:message>"
									pattern=(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}>
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="company" text="Company"></spring:message></label> <select
									class="form-control" id="companyId" name="companyId">
									<c:forEach items="${listCompany}" var="companies">

										<option value="${companies.getId()}">${companies.getName()}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="add" text="Add"></spring:message>" class="btn btn-primary" id="submit">
							<spring:message code="Or" text="or"></spring:message> <a href="dashboard" class="btn btn-default"><spring:message code="cancel" text="Cancel"></spring:message></a>
						</div>
					</form>
					<c:out value="${request.getContextPath}"></c:out>
				</div>
			</div>
		</div>
	</section>
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/validatorFront.js"></script>
</body>
</html>