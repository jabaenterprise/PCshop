	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Client profile</title>
	</head>
	
	
<body>
			

					<%-- PRODUCT --%>
	<div id="product">
			
			
			<img id="product_image" src="${requestScope.selected_product.key.imageUrl}" width=300/>
			
			
			<table>
				<tr>
					<td>Product ID:</td>
					<td><c:out value="${requestScope.selected_product.key.id}"/></td>
				</tr>
								
				<tr>
					<td>Producer:</td>
					<td><c:out value="${requestScope.selected_product.key.producer}"/></td> <%-- when iterating on HashMap it iterates over entries, so you need to say .key.theField --%>
				</tr>
				
				<tr>
					<td>Model:</td>
					<td><c:out value="${requestScope.selected_product.key.model}"/></td>
				</tr>
				
				<tr>
					<td>Price:</td>
					<td><c:out value="${requestScope.selected_product.key.price}"/></td>
				</tr>
				
				<tr>
					<td>Info:</td>
					<td><c:out value="${requestScope.selected_product.key.info}"/></td>
				</tr>
				<tr>
					<td>Quantity:</td>
					<td><c:out value="${requestScope.selected_product.value}"/></td>
					<td>
						<form id="set_quantity_in_cart" action="SetQuantityInCartServlet" method="POST">
							<input type="text" name="new_quantity"/>
							<input type="hidden" name="product_to_set_quantity" value="${requestScope.selected_product.key.model}">
							<input type="submit" value="update"/>
						</form>
					</td>
				</tr>
				
			</table>
			
			
			
						
				
	</div>


</body>
</html>