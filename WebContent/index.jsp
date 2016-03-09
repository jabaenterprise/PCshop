<%@page import="database.daos.DBProductsDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="client.Client"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PCShop</title>
<link rel="stylesheet" type="text/css" href="style/homepage.css" />
<script src="javascript/functions.js"></script>
</head>

<%
//loginServlet
//logoutServlet
//registerServlet
//products url
//searchServlet
//sortServlet
	if (session.isNew()) {
		session.setAttribute("isLogged", false);
		session.setAttribute("pageId", 1);
	}

	boolean isLogged = (boolean) session.getAttribute("isLogged");
	//isLogged = true;

	String category = request.getParameter("category");
	if (category == null) {
		category = "none";
	}
%>
<body onload='addAttribute("<%=category%>")'>

	<%
		String pageS = (String) request.getParameter("pageId");
		int pageId = 1;
		if (pageS != null) {
			pageId = Integer.parseInt(pageS);
		} //List<String> products = DBProductsDAO.getProducts(category);
		List<String> products = new ArrayList();
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");

		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");
		products.add("img/PCShop_logo.png");

		products.add("");
		products.add("");
		products.add("");
		products.add("");
		products.add("");
		products.add("");
		products.add("");
		products.add("");
		products.add("");
		products.add("");
		products.add("");

		products.add("");
		products.add("");
		products.add("");
		products.add("");
		products.add("");
		products.add("");
		products.add("");

		int maxPage = 1;
		if (products.size() % 12 != 0) {
			maxPage = products.size() / 12 + 1;
		} else {
			maxPage = products.size() / 12;
		}
		int prevPage = pageId - 1;
		int nextPage = pageId + 1;
	%>

	<div id="container">
		<div id="logo">
			<a href="index.jsp"> <img src="img/PCShop_logo.png" />
			</a>
		</div>
		<div id="search" action="SearchServlet" method="get">
			<form id="search">
				<input type="text" placeholder="search" name="search" /> <input
					type="submit" value="" />
			</form>
		</div>
		<%
			if (!isLogged) {
		%>
		<div id="login">
			<form action="LoginServlet" method="post">
				<label for="email">Email</label> <input id="email" type="text"
					placeholder="email" name="emailLogin" /><br /> <label
					for="password">Password</label> <input id="password"
					type="password" placeholder="password" name="passwordLogin" /><br />
				<input type="submit" value="Login" />
			</form>
		</div>
		<div id="register">
			<span> or <a href="Register.html">Register</a></span>
		</div>
		<%
			} else {
				String name = (String) session.getAttribute("name");
		%>
		<p id="userName">
			Hello,<%=name%>. You can
		</p>
		<div id="logout">
			<form action="LogoutServlet" method="POST">
				<input type="submit" value="Logout" />
			</form>
		</div>

		<div id="cart">
			<span> or <a href="UserPageAndCart.html">Cart</a></span>
		</div>
		<%
			}
		%>
		<div id="main">
			<div id="categories">
				<span>Categories</span>
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

			<div id="product-container">
				<div id="sort">
					<select>
						<option value="Sort by price(Ascending)">Sort by
							price(Ascending)</option>
						<option value="Sort by price(Descending)">Sort by
							price(Descending)</option>
						<option value="Sort by producers">Sort by producers</option>
					</select>
				</div>

				<div id="product-grid">
					<%
						int start = 12 * (pageId - 1);
						int end = 12 * pageId;
						if (pageId == maxPage) {
							end = products.size();
						}
						for (int i = start; i < end; i++) {
					%>
					<div id="product">
					<!-- must be implemented -->
						<a href="productInfo.jsp?productId="> <img src="<%=products.get(i)%>" /></a>
						<button class="buy1" id="buy">BUY</button>
					</div>
					<%	
						}
					%>
					<script>
					var isLogged = <%=isLogged%>;
					setDisplay("buy1", isLogged);
					</script>
					<div id="pages">
						<%
							if (prevPage >= 1) {
						%>
						<a href="index.jsp?category=<%=category%>&pageId=<%=prevPage%>">Prev</a>
						<%
							}
						%>
						<p><%=pageId%></p>
						<%
							if (nextPage <= maxPage) {
						%>
						<a href="index.jsp?category=<%=category%>&pageId=<%=nextPage%>">Next</a>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>