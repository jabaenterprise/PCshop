<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>RetryRegister</title>
	<link rel="stylesheet" href="style/Register.css" type="text/css" media="all" />
</head>

<body>
	
	<div id = "logo">
		<a href="index.html">
			<img src = "img/PCSHOP.png"/>
		</a>
	</div>
	
	<%
	
	String eMail = request.getParameter("email");
	
	
	%>
	
	<h2 style=color:red>Can't register with email address: <%= eMail %>!</h2>
	<h2>Please, register with another email:</h2>		
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
			<input type="text" name="password" id="password" value="choose password" />
		</p>
		
		<p class="city">
			<input type="text" name="city" id="city" value="enter city" />
		</p>
		
		<p class="address">
			<input type="text" name="address" id="address" value="enter address" />
		</p>
		
		<p class="submit">
			<input type="submit" value="send" />
		</p>
		
	</form>
	
</body>

</html>