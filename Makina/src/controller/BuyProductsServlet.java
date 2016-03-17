package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DBClientDAO;
import database.dao.DBProductDAO;
import model.Client;
import model.Product;
import javax.mail.*;

/**
 * Servlet implementation class BuyProductsServlet
 */
@WebServlet("/BuyProductsServlet")
public class BuyProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyProductsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client client = (Client) request.getSession().getAttribute("client");
		if(hasProtuctsToBuy(client)){
			request.getRequestDispatcher("CCInformation.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("CartAndUser.jsp").forward(request, response);
		}
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client client = (Client) request.getSession().getAttribute("client");
		HashMap<Product, Integer> cart = client.getCart();
		System.out.println(cart.isEmpty());
		ArrayList<String> unavailableProducts = new  ArrayList<>();
		for(Entry<Product, Integer>e:cart.entrySet()){
			Product p = e.getKey();
			int amount= e.getValue();
			System.out.println(DBProductDAO.getDBProductDAO().getQuantity(p));
			if(amount>DBProductDAO.getDBProductDAO().getQuantity(p)){
				StringBuilder sb = new StringBuilder();
				sb.append("ID :").append(String.valueOf(p.getId())).append(" product "+p.getProducer()+" "+p.getModel());
				unavailableProducts.add(sb.toString());
				System.out.println(sb.toString());
			}
			else{
				DBProductDAO.getDBProductDAO().updateQuantity(p, DBProductDAO.getDBProductDAO().getQuantity(p)-amount);
				cart.remove(p);
				DBClientDAO.getDBClientDAO().removeProductFromCart(p, client);
			}
		}
	
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "itttaletnsshop@gmail.com";
		String password = "qazwsx!2";
		String toAddress = client.geteMail();
		String subject = "Order Confirmed";
		StringBuilder message = new StringBuilder();
		message.append("Hello "+client.getFamilyName()+ ",\n you order was confirmed and will arrive on this address "+client.getAddress()+" in 24 to 48 hours!\n ");
		if(!unavailableProducts.isEmpty()){
			for(String s : unavailableProducts){
				message.append(s+"\n");
			}
			message.append("not avalable in stock at the moment please contact us to confirm when we can deliver them.");
		}
		message.append("\nBest Regards,\n ITTALENTS MID PROJECT PCSHOP");
		try {
			EmailUtility.sendEmail(host , port , userName, password, toAddress, subject, message.toString());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		response.sendRedirect("index.jsp");
		
	}

	private boolean hasProtuctsToBuy(Client client) {
	
		for(Entry<Product, Integer>e : client.getCart().entrySet()){
			if(e.getValue()>0){
				return true;
			}
		}
		return false;
	}
	
}
