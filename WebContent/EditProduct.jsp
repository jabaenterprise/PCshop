

<%@page import="products.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Edit product</title>
	<link rel="stylesheet" href="style/New.css"/>


</head>
<body>

	<div id = "logo">
		<a href="index.html">
			<img src = "img/PCSHOP.png"/>
		</a>
	</div>



	<div id="top_nav">
		<ul>
			<li><a href="AdminHome.html">New Product</a>
				<ul>
					<li><a href="NewCase.html">Case</a></li>
					<li><a href="NewCPU.html">CPU</a></li>
					<li><a href="NewGPU.html">GPU</a></li>
					<li><a href="NewHDD.html">HDD</a></li>
					<li><a href="NewMonitor.html">Monitor</a></li>
					<li><a href="NewMB.html">Motherboard</a></li>
					<li><a href="NewRAM.html">RAM</a></li>	
				</ul>
			</li>
			<li><a href="SearchProductToEdit.jsp">Edit Product</a></li>
			<li><a href="EditAdminProfile.html">Edit Profile</a></li>
			<li><a href="index.html">Home</a></li>
		</ul>
	</div>


			<div id="product">
					<%
						Product pr = (Product) (session.getAttribute("FoundProduct"));
					
						out.println(pr.getModel());
						out.println(pr.getProducer());
						out.println(pr.getPrice());
						out.println(pr.getQuantity());
							
					
					%>
					

			</div>
	


<div id="product_properties">
		
			
	<form class="properties" id="properties" action="UpdateProductServlet" method="POST">
				
		<h2>Edit product:</h2>	
				
		<p class="price">
			<input type="text" name="price" id="price" value="enter price" />
		</p>
		
		<p class="quantity">
			<input type="text" name="quantity" id="quantity" value="enter quantity" />
		</p>
		
		<p class="create">
			<input type="submit" id="create" value="edit"/>
		</p>
		
	</form>
</div>




</body>
</html>