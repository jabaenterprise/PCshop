package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DBClientDAO;
import database.dao.DBProductDAO;
import model.Client;
import model.Product;

@WebServlet("/SetQuantityInCartServlet")
public class SetQuantityInCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client c = (Client) request.getSession().getAttribute("client");
		String eMail = c.geteMail();
		String model = request.getParameter("product_to_set_quantity");
		Product p = DBProductDAO.getDBProductDAO().getProduct(model);
		//Product p = (Product) request.getAttribute("product_to_set_quantity");
		//Product p = (Product) ((Map.Entry<Product, Integer>) request.getSession().getAttribute("selected_product")).getKey();
		int newQuantity = Integer.parseInt(request.getParameter("new_quantity"));
		DBClientDAO cldao = DBClientDAO.getDBClientDAO();
		if (newQuantity > 0) {
			cldao.setQuantityOfProductInCart(p, newQuantity, c);
			
		} else {
			cldao.removeProductFromCart(p, c);
		}
		Client client = cldao.getClient(eMail);
		request.getSession().setAttribute("client", client);
		response.sendRedirect("client.jsp");
		
		
	}

}
