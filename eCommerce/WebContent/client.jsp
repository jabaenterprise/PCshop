	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Client profile</title>
	</head>
	
	
<body>

						<%-- LOGO --%>
	<div id="logo">
			<a href="index.jsp"><img src="img/PCSHOP.png" /></a>
	</div>

						<%-- CLIENT OPTIONS --%>
	<div id="client_options">
		<ul>
			<li><a href="edit_client.jsp">Edit profile</a></li>
		</ul>
	</div>

					<%-- PRODUCTS IN CART --%>
	<div id="product-container">
				
			<h4 style=color:red>Products in your cart:</h4>
								
			<table border=1>
				<c:forEach var="product" items="${sessionScope.client.cart}">
					<c:set var="selected_product" value="${product}" scope="request"/> <%-- session keeps the last selected product --%>
						<tr>
							<td>
								<%@ include file="product_in_cart.jsp" %>
							</td>
						</tr>
				</c:forEach>
			</table>
				
	</div>


</body>
</html>