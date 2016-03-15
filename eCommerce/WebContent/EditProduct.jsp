		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Edit product</title>
	</head>
	
	
<body>

						<%-- LOGO --%>
	<div id="logo">
			<a href="index.jsp"><img src="img/PCSHOP.png" /></a>
	</div>

						<%-- TOOLS --%>
	<div id="tools">
		<ul>
			<li><a href="admin.jsp">New Product</a>
				<ul>
					<li><a href="add_case.jsp">Case</a></li>
					<li><a href="add_cpu.jsp">CPU</a></li>
					<li><a href="add_gpu.jsp">GPU</a></li>
					<li><a href="add_hd.jsp">HD</a></li>
					<li><a href="add_monitor.jsp">Monitor</a></li>
					<li><a href="add_mb.jsp">Motherboard</a></li>
					<li><a href="add_ram.jsp">RAM</a></li>	
				</ul>
			</li>
			<li><a href="EditProduct.jsp">Edit Product</a></li>
			<li><a href="EditAdminProfile.html">Edit Profile</a></li>
			<li><a href="index.jsp">Home</a></li>
		</ul>
	</div>

	<div id="search_product" >
		<form id="search" action="SearchProductServlet" method="GET">
			<input type="text" name="search_word" value="enter model to update"/>
			<input type="submit" value="search"/>
		</form>
	</div>

	<c:if test="${requestScope.not_found == true}">
		<h4 style=color:red>No product with model <c:out value="${requestScope.search_word}"/> in the database!</h4>
	</c:if>

	<c:if test="${sessionScope.last_updated_product != null}">
		<table >
  			<tr>
   		 		<td>Model:</td>
    	 		<td><c:out value="${sessionScope.last_updated_product.model}"/></td>
    		</tr>
  			<tr>
   		 		<td>Producer:</td>
    	 		<td><c:out value="${sessionScope.last_updated_product.producer}"/></td>		
    		</tr>
    		<tr>
   		 		<td>Price:</td>
    			 <td><c:out value="${sessionScope.last_updated_product.price}"/></td>
    		 	<td> 
    			 	<form id="update_price" action="UpdatePriceServlet" method="POST">
						<input type="text" name="new_price"/>
						<input type="submit" value="update"/>
					</form>
    		 	</td>			
    		</tr>
    		<tr>
   		 		<td>Quantity:</td>
    	 		<td><c:out value="${sessionScope.last_updated_product.quantity}"/></td>
    			<td>	
    			 	<form id="update_quantity" action="UpdateQuantityServlet" method="POST">
						<input type="text" name="new_quantity"/>
						<input type="submit" value="update"/>
					</form>	
				</td>	
    		</tr>
	</table>
	</c:if>



</body>
</html>