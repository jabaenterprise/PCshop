<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Client profile</title>
	</head>
	
	
<body>

						<%-- LOGO --%>
	<div id="logo">
			<a href="index.jsp"><img src="img/PCSHOP.png" /></a>
	</div>

						<%-- CLIENT OPTIONS --%>
	<div id="client_options">
		<ul>
			<li><a href="client.jsp">To cart</a></li>
		</ul>
	</div>

	<table >
  			<tr>
   		 		<td>Client ID:</td>
    	 		<td><c:out value="${sessionScope.client.id}"/></td>
    		</tr>
  			
  			<tr>
   		 		<td>First name:</td>
    	 		<td><c:out value="${sessionScope.client.firstName}"/></td>
    		</tr>
  			<tr>
   		 		<td>Family name:</td>
    	 		<td><c:out value="${sessionScope.client.familyName}"/></td>		
    		</tr>
    		<tr>
   		 		<td>e-mail:</td>
    			 <td><c:out value="${sessionScope.client.eMail}"/></td>
    		</tr>
    		<tr>
   		 		<td>Password:</td>
    			 <td><c:out value="${sessionScope.client.password}"/></td>
    		 	<td> 
    			 	<form id="update_client_password" action="UpdateClientServlet" method="POST">
						<input type="text" name="new_password"/>
						<input type="hidden" name="new" value="update_password"/>
						<input type="submit" value="update"/>
					</form>
    		 	</td>			
    		</tr>
    		<tr>
   		 		<td>Address:</td>
    			 <td><c:out value="${sessionScope.client.address}"/></td>
    		 	<td> 
    			 	<form id="update_client_address" action="UpdateClientServlet" method="POST">
						<input type="text" name="new_address"/>
						<input type="hidden" name="new" value="update_address"/>
						<input type="submit" value="update"/>
					</form>
    		 	</td>			
    		</tr>
    		<tr>
   		 		<td>Phone:</td>
    			 <td><c:out value="${sessionScope.client.phone}"/></td>
    		 	<td> 
    			 	<form id="update_client_password" action="UpdateClientServlet" method="POST">
						<input type="text" name="new_phone"/>
						<input type="hidden" name="new" value="update_phone"/>
						<input type="submit" value="update"/>
					</form>
    		 	</td>			
    		</tr>
	</table>





</body>
</html>