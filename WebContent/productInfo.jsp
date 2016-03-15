<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="database.dao.DBProductDAO"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PC SHop</title>

<style type="text/css">
body {
	background:#afdede;
	margin: 10;
	padding: 10;
	font-family: "Helvetica", "Arial";
}
html {
  height: 100%;
  background: #919191;
  background: -moz-linear-gradient(top, #f3f3f3 0%, #ffffff 50%);
  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#f3f3f3), color-stop(50%,#ffffff));
  background: -webkit-linear-gradient(top, #f3f3f3 0%,#ffffff 50%);
  background: -o-linear-gradient(top, #f3f3f3 0%,#ffffff 50%);
  background: -ms-linear-gradient(top, #f3f3f3 0%,#ffffff 50%);
  background: linear-gradient(top, #f3f3f3 0%,#ffffff 50%);
  filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#f3f3f3', endColorstr='#ffffff',GradientType=0 );
}
.wrapper {
  width: 650px;
  margin: auto;
  padding: 25px 0px;
}
h1, h4 {
  font-family: Helvetica Neue, Arial, sans-serif;
  font-weight: normal;
  margin: 0; 
}
h1 { 
  font-size: 24pt;
}
h4 {
  font-size: 16pt;
  color: #aaa; 
}
header {
	width: 100%;
	height: auto;
	border-bottom: 3px solid  #000000;
 	 margin-bottom: 20px;
}
header {
  margin-bottom: 20px;
 <!-- background: url('http://iconshow.me/media/images/logo/brand-logo-icon/png/256/asus-256.png') no-repeat right center; --> 
}
figure {
  float: left;
}
figure img {
  max-width: 350px;
}
section {
  font-family: Tahoma, Arial, sans-serif;
  line-height: 150%;
  float: right;
  width: 300px;
  color: #333;
}
button {
  background: #36a9ea;
  background: -moz-linear-gradient(top, #36a9ea 0%, #127fd2 100%);
  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#36a9ea), color-stop(100%,#127fd2));
  background: -webkit-linear-gradient(top, #36a9ea 0%,#127fd2 100%);
  background: -o-linear-gradient(top, #36a9ea 0%,#127fd2 100%);
  background: -ms-linear-gradient(top, #36a9ea 0%,#127fd2 100%);
  background: linear-gradient(top, #36a9ea 0%,#127fd2 100%);
  filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#36a9ea', endColorstr='#127fd2',GradientType=0 );
  border: 1px solid #00599d;
  color: #fff;
  padding: 8px 20px;
  -webkit-border-radius: 6px;
  border-radius: 3px;
  -webkit-box-shadow: 0px 1px 1px 0px rgba(0, 0, 0, .1), inset 0px 1px 0px 0px rgba(250, 250, 250, .3);
  box-shadow: 0px 1px 1px 0px rgba(0, 0, 0, .1), inset 0px 1px 0px 0px rgba(250, 250, 250, .3);
  text-shadow: 0px 1px 1px #156cc4;
  filter: dropshadow(color=#156cc4, offx=0, offy=1);
  font-size: 10pt; 
}
 
button:hover {
  background: #2f90d5;
  background: -moz-linear-gradient(top, #2f90d5 0%, #0351b7 100%);
  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#2f90d5), color-stop(100%,#0351b7));
  background: -webkit-linear-gradient(top, #2f90d5 0%,#0351b7 100%);
  background: -o-linear-gradient(top, #2f90d5 0%,#0351b7 100%);
  background: -ms-linear-gradient(top, #2f90d5 0%,#0351b7 100%);
  background: linear-gradient(top, #2f90d5 0%,#0351b7 100%);
  filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#2f90d5', endColorstr='#0351b7',GradientType=0 );
}
 
button:active {
  background: #127fd2;
  -webkit-box-shadow: inset 0px 2px 1px 0px rgba(0, 47, 117, .5), 0px 1px 1px 0px rgba(0, 0, 0, 0);
  box-shadow: inset 0px 2px 1px 0px rgba(0, 47, 117, .5), 0px 1px 1px 0px rgba(0, 0, 0, 0); 
}
summary {
  cursor: pointer;
  font-size: 12pt;
  outline: 0; 
}
details > summary::-webkit-details-marker { 
  display: none;
}
details > summary:before {
  width: 16px;
  height: 16px;
  display: inline-block;
  content: '' !important;
  background:  no-repeat center top;
  margin-right: 5px;
  position: relative;
  top: 2px; 
}
details[open] > summary:before,
details.open > summary:before {
  background:  no-repeat center bottom;
}
details > summary::-webkit-details-marker { 
  display: inline;
}
</style>
</head>



<body>
<div id = "wrapper">
	<div id = "header" >
			<div id="logo">
				<a href = "index.jsp"> <img src="http://pc-stop.co.uk/img/logo.png" width  = 330 height=120 alt = "Logo" title="PCShop"></a>
			</div><!-- end of logo -->
<div class="product">
 
  <header>
    <hgroup>
      <h1><c:out value="${requestScope.product.model}"/></h1>
      <h4>Product code: <c:out value="${requestScope.product.id}"/></h4>
    </hgroup>
  </header>
 
  <figure>
    <img src="<c:out value="${requestScope.product.imageUrl}"></c:out>"  >
  <var>Price <c:out value="${requestScope.product.price}"/>BGN</var>
  </figure>
 
  <section>
 
  <p><c:out value="${requestScope.product.info}"/></p>
 
  <details>
   <summary>Product Features</summary>
      <ul>
    <c:forEach var="list" items="${requestScope.additionalInfo}">
        
		<li><c:out value="${list.key}"/><c:out value="${list.value}"/></li>
        </c:forEach>
      </ul>
  </details>
<form action="addProduct" method="post">
<input type="hidden" name="prodID" value="<%=request.getParameter("productId")%>"<%System.out.println("form product"+request.getParameter("productId")); %>>
<button type="submit">Add to cart</button>

</form>
  
 
  </section>
 </div></div>
</div>
</body></html>