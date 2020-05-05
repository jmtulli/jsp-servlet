<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Titulo JSP</title>
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
</head>
<body>
	<a href="ajax.jsp"><img src="resources/img/ajax.png" alt="Ajax" title="Ajax" width="50px" height="50px"></a>
	<div class="contact1">
		<div class="container-contact1">
			<span class="contact1-form-title"> Testes em JSP com Servlets </span>
			<form action="loginServlet" method="post" class="contact1-form validate-form" onsubmit="return validateFields()">
				<br>
				Login:
				<div class="wrap-input1 validate-input">
					<input type="text" placeholder="Type the login here." name="login" class="input1" id="name">
					<span class="shadow-input1"></span>
				</div>
				<br>
				Password:
				<div class="wrap-input1 validate-input">
					<input type="text" placeholder="Type the password here." name="password" class="input1" id="password">
					<span class="shadow-input1"></span>
				</div>
				<br>
				<div class="container-contact1-form-btn">
					<button class="contact1-form-btn">
						<span>Send</span>
					</button>
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		function validateFields() {
			return (document.getElementById("name").value != '' && document
					.getElementById("password").value != '');
		}
	</script>

</body>
</html>
