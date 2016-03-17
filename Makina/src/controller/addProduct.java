package controller;

import java.io.IOException;

import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DBClientDAO;
import database.dao.DBProductDAO;
import model.Client;
import model.Product;


/**
 * Servlet implementation class addProduct
 */
@WebServlet("/addProduct")
public class addProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("From add:"+request.getParameter("prodID"));
		Product p = DBProductDAO.getDBProductDAO().getProductById(Integer.parseInt(request.getParameter("prodID")));
		Client c = (Client) request.getSession().getAttribute("client");
		
	
		HashMap<Product, Integer> cart = c.getCart();
		System.out.println(cart.containsKey(p));
		if (cart.containsKey(p)) {
			int quantityInCart = cart.get(p);
			int newQuantity = quantityInCart + AddToCartServlet.QUANTITY_ON_ADD;
			
			DBClientDAO.getDBClientDAO().setQuantityOfProductInCart(p, newQuantity, c);
			c.getCart().put(p, cart.get(p)+1);
		} else {
			DBClientDAO.getDBClientDAO().addProductToCart(p, c, 1);
			c.getCart().put(p,1);
		}
		

		response.sendRedirect("index.jsp");

		
	}

}

/*
 * String modelToAdd = request.getParameter("model_to_add");
		Product p = DBProductDAO.getDBProductDAO().getProduct(modelToAdd);
		Client c = (Client) request.getSession().getAttribute("client");
		HashMap<Product, Integer> cart = c.getCart();
		if (cart.containsKey(p)) {
			int quantityInCart = cart.get(p);
			int newQuantity = quantityInCart + AddToCartServlet.QUANTITY_ON_ADD;
			DBClientDAO.getDBClientDAO().setQuantityOfProductInCart(p, newQuantity, c);
		} else {
			DBClientDAO.getDBClientDAO().addProductToCart(p, c);
		}
		String eMail = c.geteMail();
		Client client = DBClientDAO.getDBClientDAO().getClient(eMail);
		request.getSession().setAttribute("client", client);
		response.sendRedirect("index.jsp");
*/