		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="init.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<script src="javascript/functions.js"></script>
	</head>

<body onload='setCurrentCategory("<c:out value="${requestScope.category}"></c:out>");setSelectedSortType("<c:out value="${requestScope.sortType}"></c:out>");'>


							<%-- LOGO --%>
	<div id="logo">
			<a href="index.jsp"><img src="img/PCSHOP.png" /></a>
	</div>
	
		
							<%-- USER --%>
	<div>
	
	<c:if test="${sessionScope.is_admin == true}">
		<h4 style=color:green>Hello, Admin!</h4>
		<a href="LogoutServlet">Logout</a>
	</c:if>
	
	
	<c:if test="${sessionScope.client == null && sessionScope.is_admin != true}">
							<%-- REGISTER --%>
		<div id="register">
				<a href="register.jsp">register</a>
		</div>
		
							<%-- LOGIN --%>
		<div id="login">
				<form action="LoginServlet" method="POST">
					<label for="email">e-mail:</label> 
					<input id="email" type="text" name="email" />
			  		<label for="password">password:</label> 
					<input id="password" type="password" name="password" />
			  		<input type="submit" value="Login" />
				</form>
		</div>
		<c:if test="${requestScope.no_such_client == true}">
			<h4 style=color:red>No client with such email or/and password!</h4>
		</c:if>
		
	</c:if>
	
	
	<c:if test="${sessionScope.client != null}">
						<%-- LOGOUT --%>
		<div id="logout" style=color:green>
				<c:out value="Hello, ${sessionScope.client.firstName} ${sessionScope.client.familyName}!"></c:out>
				<a href="LogoutServlet">Logout</a>
		</div>
						<%-- TO PROFILE --%>
		<div id="to_profile" style=color:green>
				<a href="client.jsp">To profile</a>
		</div>
		
		<div id="cart">
			<span> or <a href="CartAndUser.jsp">Cart</a></span>
		</div>
		
		
	</c:if>
	
	</div>
	
	
	
						<%-- CATEGORIES --%>
	<div id="categories" style=color:blue>
				<h3>Categories:</h3>
				<ul>
					<li><a id="case" href="index.jsp?category=case">Case</a></li>
					<li><a id="cpu" href="index.jsp?category=cpu">CPU</a></li>
					<li><a id="gpu" href="index.jsp?category=gpu">GPU</a></li>
					<li><a id="hd" href="index.jsp?category=hd">Hard Drive</a></li>
					<li><a id="monitor" href="index.jsp?category=monitor">Monitor</a></li>
					<li><a id="mb" href="index.jsp?category=mb">Mother Board</a></li>
					<li><a id="ram" href="index.jsp?category=ram">RAM</a></li>
				</ul>
	</div>


						<%-- PRODUCT CONTAINER --%>
	<div id="product-container">
				<h3 style=color:red>Products:</h3>
						
						
						<%-- SEARCH --%>
			<div id="search" >
					<form action="SearchServlet" method="GET">
						<input type="text" name="search_word" />
						<input type="submit" value="search" />
					</form>
			</div>		
											
						
						<%-- SORT --%>
				<!-- <div id="sort">
					<form action="SortingServlet" method="GET">
						<select name="sort_type">
							<option value="priceAsc">sort by price(asc)</option>
							<option value="priceDesc">sort by price(desc)</option>
							<option value="producer">sort by producer</option>
						</select>
						<input type="submit" value="sort">
					</form>
				</div>-->
				
				<div id="sort">
					<select id="selectSort" onchange='getSelectedSortType("selectSort", "<c:out value="${requestScope.category}"></c:out>")'>
						<option value="priceAscending">Sort by price(Ascending)</option>
						<option value="priceDescending">Sort by price(Descending)</option>
						<option value="producer">Sort by producers</option>
					</select>
				</div>
				

						<%-- PRODUCTS TABLE --%>
				<table border=1>
				<c:forEach var="product" items="${sessionScope.products}"> <%-- when you select category or search in the servelt this attribute is set (a collection) --%>
					<c:set var="selected_product" value="${product}" scope="request"/> <%-- session keeps the last selected product --%>
						<tr>
							<td>
								<%@ include file="product.jsp" %>
							</td>
						</tr>
				</c:forEach>
				</table>

					<div id="pages">
						<c:if test="${requestScope.hasPrev}">
							<a
								href="index.jsp?<c:out value="${requestScope.prevQueryString}"></c:out>">Prev</a>
						</c:if>
						<p>
							<c:out value="${requestScope.pageId}"></c:out>
						</p>
						<c:if test="${requestScope.hasNext}">
							<a
								href="index.jsp?<c:out value="${requestScope.nextQueryString}"></c:out>">Next</a>
						</c:if>
					</div>				
						
				
	</div>


</body>
</html>