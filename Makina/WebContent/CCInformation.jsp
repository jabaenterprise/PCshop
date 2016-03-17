<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Credit Card Credentials</title>
</head>
<body>
<form type="radio" action="BuyProductsServlet" method="post" >
<br>Card Number</br>
<input type="text" name="ccNumber">
<br>Enter the number without spaces or hyphens.</br>
<br>CVC</br>
<input type="text" name="cvc" size="2">
<br>Expiration (MM/YY)</br>
<input type="text" name="month" size="2" >/<input type="text" name="year" size="2">
<br>Password</br>
<input type="password" name="password">
<input type="hidden" value='<c:out value="${requestScope.client}"></c:out>'>
<br><input type="submit" value="Confirm">
</form>
</body>
</html>