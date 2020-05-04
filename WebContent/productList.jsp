<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products</title>
<link rel="stylesheet" type="text/css" href="resources/css/table.css">
<link rel="stylesheet" type="text/css" href="resources/css/insertUser.css">
</head>
<body>
	<a href="index.jsp"><img src="resources/img/home.png" alt="Logoff" title="Back home" width="50px" height="50px"></a>
	<h3 style="color: red; text-align: center;">${logMessage}</h3>
	<div class="tableUsers">
		<div class="limiter">
			<div class="container-table100">
				<div class="wrap-table100">
					<h1>Current products:</h1>
					<div class="table100">
						<table>
							<thead>
								<tr class="table100-head">
									<th class="column1">NAME</th>
									<th class="column1">AMOUNT</th>
									<th class="column1">PRICE</th>
									<th class="column1">CATEGORY</th>
									<th class="column1">DELETE</th>
									<th class="column1">UPDATE</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${productList}" var="product">
									<tr>
										<td class="column1">${product.name}</td>
										<td class="column1">${product.amount}</td>
										<td class="column1">${product.price}</td>
										<td class="column1"><c:forEach items="${categories}" var="category">
												<c:if test="${category.id eq product.categoryId}"> ${category.name} </c:if>
											</c:forEach></td>
										<td class="column1"><a href="productServlet?action=delete&productid=${product.id}"> <img
												src="resources/img/delete.png" title="delete" alt="Delete" width=20px height=20px></a></td>
										<td class="column1"><a href="productServlet?action=update&productid=${product.id}"> <img
												src="resources/img/edit.png" title="update" alt="Update" width=20px height=20px></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br>
						<form action="productServlet" id="form" method="post">
							<button class="contact100-form-btn"
								onclick="document.getElementById('form').action='productServlet?action=insert'">Add product</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>