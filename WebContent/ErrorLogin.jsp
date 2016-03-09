<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="javascript/functions.js"></script>

</head>
<body>
	<%
		String error = request.getParameter("error");
		String message = "";
		if(error.equals("wrongEmailOrPassword")){
			message = "Wrong email or password. Please try again";
		}
		if(error.equals("invalidEmailOrPassword")){
			message = "Invalid email or password. Please try again";
		}
		
	%>
	
	<p><%=message%></p>
	<p>You will be redirect to index page after <span id="timer" style="color:red">5</span> seconds</p>
	<script>
	startRedirectTimer();		
	</script>
</body>
</html>