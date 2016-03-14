		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Register</title>
	</head>


<body>

							<%-- LOGO --%>
	<div id="logo">
			<a href="index.jsp"><img src="img/PCSHOP.png" /></a>
	</div>
	
	<c:if test="${requestScope.retry_register == true}">
	<h5 style=color:red>There is a user registered with this email, already!</h5>	
	</c:if>	
		
		
	<h5>To create account fill all the fields:</h5>	
	<form class="form" method="POST" action="RegisterServlet">
	
		<p class="first name">
			<input type="text" name="first_name" id="first name" value="enter first name" />
		</p>
	
		<p class="family name">
			<input type="text" name="family_name" id="family name" value="enter family name" />
		</p>
		
		<p class="email">
			<input type="text" name="email" id="email" value="enter email" />
		</p>
		
		<p class="password">
			<input type="text" name="password" id="password" value="enter password" />
		</p>
				
		
		<p class="address">
			<input type="text" name="address" id="address" value="enter address" />
		</p>
		
		<p class="phone">
			<input type="text" name="phone" id="phone" value="enter phone" />
		</p>
		
		<p class="submit">
			<input type="submit" value="register" />
		</p>
		
	</form>
	
	
	
	
	
</body>
</html>