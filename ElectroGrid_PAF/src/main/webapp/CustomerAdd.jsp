<%@page import="com.customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="bootstrap.min.css">
<title>Customer Details Management</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/index-styles.css" rel="stylesheet" />
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/customer.js"></script>
</head>
<body id="page-top">
	<!-- Navigation-->
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="#page-top"></a>
			<button
				class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="#">Inquires</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="CustomerAdd.jsp">Customer Details</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="billing.jsp">Billing Details</a></li>
					<li class="nav-item mx-0 mx-lg-1">	<a
					class="nav-link py-3 px-0 px-lg-3 rounded" href="AdminLogout">Logout</a>
						<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href=""><%= session.getAttribute("name") %></a></li>
					
				</ul>
			</div>
		</div>
	</nav>
	<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		
	
<div class="container">
	<div class="row">
		<div class="col-6"> 
			<h1>Customer Details Management</h1>
				<form id="formItem" name="formItem" method="post" action="CustomerAdd.jsp">
 					Account Number: 
 						<input id="AccNo" name="AccNo" type="text" class="form-control" pattern="[0-9]{9}" placeholder="123 xxx xxx " required><br> 
 					Customer Name: 
 						<input id="CusName" name="CusName" type="text" class="form-control" placeholder="John Doe" required><br> 
 					Customer Moblie:
 						<input id="Moblie" name="Moblie" type="text" class="form-control" pattern="[0-9]{10}" placeholder="071 2304 xxx " required><br>
 					Password: 
 						<input id="Password" name="Password" type="text" class="form-control"  required>
 						<br>
 						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
						<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
		</div>
	</div>
</div>
<div id="divItemsGrid">
<br>
	<%
		customer cusObj = new customer(); 
		out.print(cusObj.readCusDetail()); 
	%>
</div>

	<br>
		<br>
		<br>
		
</body>
</html> 