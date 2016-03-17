<%@page import="java.security.KeyStore.Entry"%>
<%@page import="java.util.HashMap"%>

<%@page import="java.sql.DriverManager"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta  charset="UTF-8">
<title>Cart and User Info</title>
<link rel="stylesheet" type="text/css" href="style/UserCartStyles.css" >
<link rel="stylesheet" type="text/css" href="/code_examples/tutorial.css">
<script type="text/javascript" src="/code_examples/passtest.js"></script>

<script type="text/javascript" >
function calculate(form){
	var sum = 0;

	for(var i = 0; i < form.quantity.length; i++)
		sum += (Number(form.quantity[i].value)*Number(form.price[i].value));
	form.total.value = sum;
	}
function toggle_visibility(id) {
    var e = document.getElementById(id);
    if(e.style.display == 'block')
       e.style.display = 'none';
    else
       e.style.display = 'block';
}


	var password = document.getElementsByName("password");

	function checkPass()
	{
	    //Store the password field objects into variables ...
	    var strongPass = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$/;
	    var pass1 = document.getElementById('pass1');
	    var pass2 = document.getElementById('pass2');
	    //Store the Confimation Message Object ...
	    var message = document.getElementById('confirmMessage');
	    var secured = document.getElementById('passSecure');
	    //Set the colors we will be using ...
	    var goodColor = "#66cc66";
	    var badColor = "#ff6666";
	    //Compare the values in the password field 
	    //and the confirmation field
	    if(pass1.value.match(/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{5,10}$/)){
	    	secured.style.color = goodColor;
	   		secured.innerHTML = ":)"
	    }
	    else{
	    	secured.style.color = badColor;
	    	secured.innerHTML = ":/"
	    }
	    if(pass1.value == pass2.value){
	        //The passwords match. 
	        //Set the color to the good color and inform
	        //the user that they have entered the correct password 
	       // pass2.style.backgroundColor = goodColor;
	        message.style.color = goodColor;
	        message.innerHTML = "Passwords Match!"
	    }else{
	        //The passwords do not match.
	        //Set the color to the bad color and
	        //notify the user.
	       // pass2.style.backgroundColor = badColor;
	        message.style.color = badColor;
	        message.innerHTML = "Passwords Do Not Match!"
	    }
	}
	
	$(function()
			{
			    $(".add").click(function(){ var currentVal = parseInt($(this).next(".qty1").val());
			        if (currentVal != NaN)
			        {
			            $(this).next(".qty1").val(currentVal + 1);
			        } 
			    });
			    $(".minus").click(function() {var currentVal = parseInt($(this).prev(".qty1").val());
		    	   if (currentVal ==0)
			        {
			           return;
			        }
			        if (currentVal != NaN)
			        {
			            $(this).prev(".qty1").val(currentVal - 1);
			        }
			    });
			});
</script>
</head>
<body>
	<header>
		<div id="logo">
			<a href="index.jsp"> <img src="img/PCSHOP.png" />
			</a>
		</div>
	</header>
	
	<div id="displayPage">
	<div id="info" >
		<h1>User Information</h1>
		<p>First name:<c:out value="${sessionScope.client.firstName}"/>  </p>
		<p>Surname:  <c:out value="${sessionScope.client.familyName}"/> </p>
		<p>Address:  <c:out value="${sessionScope.client.address}"/> </p>
		<p>Phone number:  <c:out value="${sessionScope.client.phone}"/> </p>
		


		<p>
			<a href="javascript:void(0)" onclick="toggle_visibility('popupEditWindow');"><button id="editInfoButton">Edit info</button></a>
		
			<a href="javascript:void(0)" onclick="toggle_visibility('changePassword');"><button id="changePasswordButton">Change password</button></a>
		</p>



	</div>
	<!-- end of user information -->
	<div id="cart">
<!--  	<form action="CartServlet" method="get" >-->
		<h1 id="cartH1" > Products in cart</h1>
		<table id = "table" title="" > 
			<tr >
				<th></th>
				<th>Component maker</th>
				<th>Component model</th>
				<th>Price in BGN</th>
				<th>Quantity</th>
			</tr>
				<c:set var="total" scope="session" value="${0}"></c:set>
				<c:forEach var="list" items="${sessionScope.client.cart}">
				<form action="AddToCartServlet" method="post" >
			<tr>
				<td><a href="ShowInfo?productId=<c:out value="${list.key.id}" />" ><img alt="Monitor" src="<c:out value="${list.key.imageUrl}"/>" height="150px"	width="150px"  /></a></td>
				<td><c:out value="${list.key.producer}" /></td>
				<td><c:out value="${list.key.model} "/></td>
				<td><input disabled="disabled" name = "price" value = "<c:out value="${list.key.price} "/>" class = "price"/></td>
				<td >
					<input type="hidden" name="key" value="<c:out value="${list.key.id}" />" >
					<input type="hidden" name="prodQuantity" value="<c:out value="${list.value}" />" >
				 	<input type="submit" name="change" value="+" id="add1" class="add" /> 
					<input type="text" id="qty1"  name="quantity" value="<c:out value="${list.value} "/>" class="qty" />
	       			<input type="submit" name="change" value="-" id="minus1" class="minus" />  
       			</td>
       			
       			<td><a href="AddToCartServlet?productId=<c:out value="${list.key.id}" />" name="remove">X</a>
			</tr>	
					<c:set var = "quantity" value="${list.value}"></c:set>
					<c:set var = "price" value="${list.key.price}"></c:set>
					<c:set var="total" value="${total + price* quantity}"></c:set>
		</form>
		</c:forEach>
		<c:if test="${total>0 }">
			<tr>
		
 			<td></td>
 			<td></td>
 			<td>Total sum:</td>
 		
 			<td class="total" >
 			<output name="total" class="output"  ><c:out value="${total}"/></output>BGN
 			</td>
 			<td>
 			<form action="BuyProductsServlet" method="get" >
 			<input type="submit" id = buyButton value = "Buy Products" size ="4">
 			</form>
 			</td>
 			</tr>
			<tr>
				</c:if>
		</table>

 		 	
 		
	</div>
	<!-- end of cart -->
	</div>
	<!-- end of display -->
	
	
	
	<div id="popupEditWindow">
		<div class="popupBoxWrapper">
			<a href="javascript:void(0)" onclick="toggle_visibility('popupEditWindow');" class="close" ><img  alt="Close" src="http://vignette4.wikia.nocookie.net/dynastywarriors/images/a/a5/X.png/revision/latest?cb=20131129190405" height="20px" width="20px"></a>
			<div class="popupBoxContent">
				<h3>Edit information</h3>
				<form action="UpdateClientServlet" method="post">
					<p >
						<input type="text" name="firstName" id="firstName" placeholder='<c:out value="${sessionScope.client.firstName}"></c:out>' />
					</p>
					<p>
						<input type="text" name="lastName" id="lastName" placeholder="<c:out value="${sessionScope.client.familyName}"/> " />
					</p>
					
					<p >
						<input type="text" name="address" id="address" placeholder="<c:out value="${sessionScope.client.address}"/>" />
					</p>
					<p>
						<input type="text" name="phoneNumber" id="phoneNumber"	placeholder="<c:out value="${sessionScope.client.phone}"/>" />
					</p>
					<p>
					<a href="javascript:void(0)" onclick="toggle_visibility('popupEditWindow');"><input type="submit" value="Save" /></a>
					</p>
				</form>
				
				
				
			</div>
		</div>
	</div>

	<div id="changePassword">
		<div class="popupBoxWrapper">
		<a href="javascript:void(0)" onclick="toggle_visibility('changePassword');"class="close" ><img  alt="Close" src="http://vignette4.wikia.nocookie.net/dynastywarriors/images/a/a5/X.png/revision/latest?cb=20131129190405" height="20px" width="20px"></a>
			<div class="popupBoxContent">
			<form action="ChangePassword" method="post">
				
				<p><input type="password"	name="oldPassword" id="oldPassword" placeholder="Old password"  /></p>
					
			 <div class="fieldWrapper">
          	
               	 <p> 
               	 	 <input type="password" placeholder="New password" name="pass1" id="pass1" onkeyup="checkPass(); return false;">
               	 	 <span id="passSecure" class="passSecure"></span>
               	 </p>
       		 </div>
        	<div class="fieldWrapper">
          		
                <p>  
                	<input type="password" placeholder="Confirm password" name="pass2" id="pass2" onkeyup="checkPass(); return false;">
        			<span id="confirmMessage" class="confirmMessage"></span>
        		 </p>
        	</div>
						

				<input  type="submit" value="Save" />
				</form>
			</div>
		</div>
	</div>

</body>
</html>

