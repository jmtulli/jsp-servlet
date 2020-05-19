<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Load Jquery</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h1>Load jquery</h1>
	<br>
	<button onclick="loadSubPage()">Load</button>
	<br>
	<div id="loading"></div>
</body>
<script type="text/javascript">
	function loadSubPage() {
		$('#loading').load('loadSub.jsp');
	}
</script>
</html>