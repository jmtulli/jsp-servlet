<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exceptions</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<h1>Exceptions with JQuery</h1>
	<br>
	<input type="text" id="textField">
	<input type="button" onclick="myFunc()" value="Submit">
</body>

<script type="text/javascript">
	function myFunc() {
		alert($('#textField').val());
	}
</script>

</html>