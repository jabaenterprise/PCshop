<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
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
					<li><a href="add_hd.jsp">HDD</a></li>
					<li><a href="add_monitor.jsp">Monitor</a></li>
					<li><a href="add_mb.jsp">Motherboard</a></li>
					<li><a href="add_ram.jsp">RAM</a></li>	
				</ul>
			</li>
			<li><a href="EditProduct.jsp">Edit Product</a></li>
			<li><a href="EditAdminProfile.html">Edit Profile</a></li>
		</ul>
	</div>


<div id="product_properties">
		
	<form id="properties" action="AddProductServlet" method="POST" >
		<h2>Enter cpu properties:</h2>	
		<p class="producer">
			<input type="text" name="producer" id="producer" value="enter producer" />
		</p>
	
		<p class="model">
			<input type="text" name="model" id="model" value="enter model" />
		</p>
		
		<p class="price">
			<input type="text" name="price" id="price" value="1" />
		</p>
		
		<p class="quantity">
			<input type="text" name="quantity" id="quantity" value="1" />
		</p>
		<p class="img_url">
			<input type="text" name="imgUrl" id="imgUrl" value="enter image url" />
		</p>
		
		<p class="info">
			<input type="text" name="info" id="info" value="enter product info" />
		</p>
		
		<p class="number_of_cores">
			<input type="text" name="number_of_cores" id="number_of_cores" value="enter number of cores" />
		</p>
		
		<p class="clock_speed">
			<input type="text" name="clock_speed" id="clock_speed" value="enter clock speed" />
		</p>
		
		<p class="socket">
			<input type="text" name="socket" id="socket" value="enter socket type" />
		</p>
		
		<p class="type">
			<input type="hidden" name="type" id="type" value="cpu" />
		</p>
		
		<p class="add">
			<input type="submit" id="add" value="add to shop"/>
		</p>
	</form>
	</div>

	<c:if test="${requestScope.has_such_product == true}">
			<h4 style=color:red>This product is already in the database!</h4>
	</c:if>

	<c:if test="${requestScope.has_such_product == false}">
			<h4 style=color:green>New product successfully added to the database!</h4>
	</c:if>



</body>
</html>