<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Generate reports</title>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<h1>Generate reports</h1>
	<br>
	<div id="datatable"></div>
	<br>
	<form action="datatableServlet" id="form" method="post">
		<button onclick="document.getElementById('form').action='datatableServlet?action=report'">Generate</button>
	</form>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#datatable').load('datatable.jsp');
	});
</script>
</html>
