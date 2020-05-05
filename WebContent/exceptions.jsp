<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exceptions</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h1>Exceptions with JQuery</h1>
	<br>
	<input type="text" id="textField">
	<input type="button" onclick="myFunc()" value="Submit">
</body>

<script type="text/javascript">
	function myFunc() {
		$.ajax({
			method : "POST",
			url : "exceptionServlet",
			data : {
				textField : $('#textField').val()
			}
		})
		.fail(function(xhr, status, error) {
			alert("error: " + error.toString());
		});
	}
</script>

</html>