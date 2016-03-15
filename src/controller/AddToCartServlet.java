package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DBClientDAO;
import database.dao.DBProductDAO;
import database.dao.IClientDAO;
import model.Client;
import model.Product;


/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	public static final int QUANTITY_ON_ADD = 1;

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Client c = (Client)req.getSession().getAttribute("client");
		HashMap<Product, Integer> cart = c.getCart();
		Product p =null;
		System.out.println(req.getParameter("key"));
		for(Entry<Product, Integer> e :cart.entrySet()){
			if(e.getKey().getId()==Integer.parseInt(req.getParameter("key"))){
				p=e.getKey();
				break;
			}
		}
		
		if(req.getParameter("change").equals("+")){
			cart.put(p,(Integer) cart.get(p)+1);
			
		}
		else{
			if(cart.get(p)>=1){
			cart.put(p,(Integer) cart.get(p)-1);
		
			}
		}
		
		c.setCart(cart);
		req.getSession().setAttribute("client", c);
		DBClientDAO.getDBClientDAO().setQuantityOfProductInCart(p, cart.get(p), c);
		req.getRequestDispatcher("/CartAndUser.jsp").forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Client c = (Client)req.getSession().getAttribute("client");
		HashMap<Product, Integer> cart = c.getCart();
		Product p =null;
		System.out.println(req.getParameter("productId"));
		for(Entry<Product, Integer> e :cart.entrySet()){
			if(e.getKey().getId()==Integer.parseInt(req.getParameter("productId"))){
				p=e.getKey();
				break;
			}
		}
		DBClientDAO.getDBClientDAO().removeProductFromCart(p, (Client) req.getSession().getAttribute("client"));
		cart.remove(p);
		c.setCart(cart);
		req.getSession().setAttribute("client", c);
		req.getRequestDispatcher("/CartAndUser.jsp").forward(req, resp);
	}
	
	
}
