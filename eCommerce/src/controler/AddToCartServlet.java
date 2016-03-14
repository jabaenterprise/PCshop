package controler;

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


@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static int QUANTITY_ON_ADD = 1; //Every click on add to cart incremets the quantity by 1
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String modelToAdd = request.getParameter("model_to_add");
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
		
		
	}

}
