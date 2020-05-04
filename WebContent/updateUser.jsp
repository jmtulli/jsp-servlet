<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update user</title>
<link rel="stylesheet" type="text/css" href="resources/css/insertUser.css">
</head>
<body>
	<div class="container-contact100">
		<div class="wrap-contact100">
			<br>
			<span class="contact100-form-title"> Edit user: </span>
			<form action="updateUser" method="post" id="form" class="contact100-form validate-form">
				<input type="hidden" name="userId" value="${user.id}">
				<table>
					<tr>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter your name">
								<input type="text" placeholder="Username" name="userName" value="${user.login}" class="input100">
								<span class="focus-input100"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter your password">
								<input type="password" placeholder="Password" name="userPassword" value="${user.password}" class="input100">
								<span class="focus-input100"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter your cep">
								<input type="text" placeholder="CEP" name="userCep" value="${user.cep}" class="input100">
								<span class="focus-input100"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter your city">
								<input type="text" placeholder="City" name="userCity" value="${user.city}" class="input100">
								<span class="focus-input100"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter your street">
								<input type="text" placeholder="Street" name="userStreet" value="${user.street}" class="input100">
								<span class="focus-input100"></span>
							</div>
						</td>
					</tr>
				</table>
				<div class="container-contact100-form-btn">
					<button class="contact100-form-btn">Save</button>
					<button class="contact100-form-btn" onclick="document.getElementById('form').action='updateUser?action=cancel'">Cancel</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>