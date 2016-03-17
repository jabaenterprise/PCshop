		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin home</title>
	</head>
	
	
<body>

						<%-- LOGO --%>
	<div id="logo">
			<a href="index.jsp"><img src="img/PCSHOP.png" /></a>
	</div>

						<%-- LOGUT --%>
	<div id="admin_logout">
	<c:if test="${sessionScope.is_admin == true}">
		<h4 style=color:green>Hello, Admin!</h4>
		<a href="LogoutServlet">Logout</a>
	</c:if>
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
		</ul>
	</div>




</body>
</html>