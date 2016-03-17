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
			<form action="ShowInfo">
			
			<%--<img id="product_image" src="${requestScope.selected_product.imageUrl}" width=300/>--%>
			<c:set var="selected_product" value="${requestScope.selected_product}" scope="request"/>
			<!-- <input type="image" src="${requestScope.selected_product.imageUrl}" width=300 value="${requestScope.selected_product}"  alt="Submit" >-->
			<a href="ShowInfo?prodID=<c:out value="${requestScope.selected_product.id}"></c:out>">
				<img src="${requestScope.selected_product.imageUrl}" width="300">
			
			</a>
			</form>
			<table>
				<tr>
					<td>Producer:</td>
					<td><c:out value="${requestScope.selected_product.producer}"/></td>
				</tr>
				
				<tr>
					<td>Model:</td>
					<td><c:out value="${requestScope.selected_product.model}"/></td>
				</tr>
				
				<tr>
					<td>Price:</td>
					<td><c:out value="${requestScope.selected_product.price}"/></td>
				</tr>
				
				<tr>
					<td>Info:</td>
					<td><c:out value="${requestScope.selected_product.info}"/></td>
				</tr>
			</table>
			
						<%-- ADD TO CART OPTION --%>
			<c:if test="${sessionScope.client != null}"> <%-- only the logged in clients have this option --%>
				<form action="addProduct" method="POST">
					<input type="hidden" value="${requestScope.selected_product.id}" name="prodID">
					<input type="submit" value="Add to cart">
				</form>
			</c:if>
			
						
				
	</div>


</body>
</html>