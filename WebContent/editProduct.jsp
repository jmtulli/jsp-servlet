<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit product</title>
<link rel="stylesheet" type="text/css" href="resources/css/insertUser.css">
</head>
<body>
	<div class="container-contact100">
		<div class="wrap-contact100">
			<br>
			<span class="contact100-form-title">${msg}</span>
			<form action="productServlet" method="post" id="form" class="contact100-form validate-form">
				<input type="hidden" name="id" value="${product.id}">
				<table>
					<tr>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter product name">
								<input type="text" placeholder="Name" name="name" value="${product.name}" class="input100">
								<span class="focus-input100"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter product quantity">
								<input type="number" placeholder="Quantity" name="amount" value="${product.amount}" class="input100" min="0"
									max="99">
								<span class="focus-input100"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter product price">
								<input type="number" placeholder="Price $" name="price" value="${product.price}" class="input100">
								<span class="focus-input100"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="wrap-input100 validate-input" data-validate="Please enter product category">
								<select name="categoryId" class="input100">
									<option value="select">[--- SELECT ---]</option>
									<c:forEach items="${categories}" var="category">
										<option value="${category.id}" <c:if test="${category.id eq product.categoryId}"> selected="selected" </c:if>>${category.name}</option>
									</c:forEach>
								</select> <span class="focus-input100"></span>
							</div>
						</td>
					</tr>
				</table>
				<div class="container-contact100-form-btn">
					<button class="contact100-form-btn">Save</button>
					<button class="contact100-form-btn"
						onclick="document.getElementById('form').action='productServlet?action=refresh'">Cancel</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>