<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert new user</title>
<link rel="stylesheet" type="text/css" href="resources/css/insertUser.css">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<link rel="stylesheet" type="text/css" href="resources/css/table.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body style="background-color: #f2f2f2">
	<a href="index.jsp"><img src="resources/img/home.png" alt="Logoff" title="Back home" width="50px" height="50px"></a>
	<a href="productServlet"><img src="resources/img/products.png" alt="Products" title="Products page" width="50px"
		height="50px"></a>
	<div class="container-contact100">
		<div class="wrap-contact100">
			<span class="contact100-form-title"> Insert new user: </span>
			<h3 style="color: red">${logMessage}</h3>
			<br>
			<form action="insertUser" method="post" class="contact100-form validate-form" enctype="multipart/form-data">
				<table>
					<tr>
						<td><input type="hidden" name="userid" value="${user.id}">
							<div class="wrap-input100 validate-input" data-validate="Please enter your name">
								<input type="text" placeholder="Username" name="userName" class="input100" value="${user.login}">
								<span class="focus-input100"></span>
							</div></td>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter your password">
								<input type="password" placeholder="Password" name="userPassword" class="input100" value="${use.password}">
								<span class="focus-input100"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter your cep">
								<input type="number" placeholder="CEP" name="userCep" class="input100" value="${user.cep}" id="userCep"
									onblur="findCep()">
								<span class="focus-input100"></span>
							</div>
						</td>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter your city">
								<input type="text" placeholder="City" name="userCity" class="input100" value="${user.city}" id="userCity">
								<span class="focus-input100"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter your street">
								<input type="text" placeholder="Street" name="userStreet" class="input100" value="${user.street}"
									id="userStreet">
								<span class="focus-input100"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>Upload file: <input type="file" name="filename" value="user.filename"></td>
					</tr>
				</table>
				<div class="container-contact100-form-btn">
					<button class="contact100-form-btn">Save</button>
				</div>
			</form>
		</div>
	</div>
	<h1>Current users:</h1>
	<div class="tableUsers">
		<div class="limiter">
			<div class="container-table100">
				<div class="wrap-table100">
					<div class="table100">
						<table>
							<thead>
								<tr class="table100-head">
									<th class="column1">FILE</th>
									<th class="column1">NAME</th>
									<th class="column1">PASSWORD</th>
									<th class="column1">CEP</th>
									<th class="column1">CITY</th>
									<th class="column1">STREET</th>
									<th class="column1">DELETE</th>
									<th class="column1">UPDATE</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userList}" var="user">
									<tr>
										<c:choose>
											<c:when test="${not empty user.filename}">
												<td class="column1"><a href="insertUser?action=filedownload&userid=${user.id}"><img
														src=${user.filename } alt="file" width=20px height=20px></a></td>
											</c:when>
											<c:otherwise>
												<td class="column1"><img src="resources/img/file.jpg" alt="file" width=20px height=20px></td>
											</c:otherwise>
										</c:choose>
										<td class="column1">${user.login}</td>
										<td class="column1">${user.password}</td>
										<td class="column1">${user.cep}</td>
										<td class="column1">${user.city}</td>
										<td class="column1">${user.street}</td>
										<td class="column1"><a href="insertUser?action=delete&userid=${user.id}"
											onclick="return confirm('Confirmar?')"> <img src="resources/img/delete.png" title="delete" alt="Delete"
												width=20px height=20px>
										</a></td>
										<td class="column1"><a href="updateUser?action=update&userid=${user.id}"> <img
												src="resources/img/edit.png" title="update" alt="Update" width=20px height=20px>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function findCep() {
			var cep = $("#userCep").val();
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {
						if (!("erro" in dados)) {
							$("#userStreet").val(dados.logradouro);
							$("#userCity").val(dados.localidade);
						} else {
							alert("CEP não encontrado.");
						}
					});
		}
	</script>
</body>
</html>