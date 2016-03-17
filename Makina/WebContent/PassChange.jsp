<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
     prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"
     prefix="fmt" %>
<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PCShop</title>
<link rel="stylesheet" type="text/css" href="style/homepage.css" />
<script src="javascript/functions.js"></script>
<style>
header {
	max-width:1664px;
    background-color:black;
    color:white;
    text-align:center;
    padding:5px;	 
}
nav {
	max-width:1664px;
    line-height:30px;
    background-color:#eeeeee;
    height:300px;
    width:100px;
    float:left;
    padding:5px;	      
}
section {

    max-width:1664px;
    float:inherit;
    padding:10px;	 	 
}
footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
    padding:5px;	 	 
}
</style>
</head>
<body>
	<header>
				<a href="index.jsp"> <img src="img/PCShop_logo.png" /></a>
	</header>
		<section>
		<c:set var="passCheck" value="${sessionScope.passwordCheck}"></c:set>
		
		
		<c:if test="${passCheck == 'OK'}">
			<div>
				<h1 align="center">You Have Successfully Changed Your Password, Please Log in Again To Continue Shopping</h1>
				<img style="position: relative;    left: 48%;     float:inherit;" alt="OK" src="img\green-ok-icon.png">
			</div>
		</c:if>
		
		<c:if test="${passCheck == 'NOTOK'}">
			<div>
				<h1 align="center">Password Not Changed!</h1>
			<img style="position: relative;    left: 46%;     float:inherit;" alt="NOT" src="img\red-cross-icon.png">
			</div>
		</c:if>
	</section>
</body>
</html>