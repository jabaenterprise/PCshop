package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DBProductDAO;
import model.Product;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product pr = (Product) (request.getSession().getAttribute("FoundProduct"));
		DBProductDAO prdao = DBProductDAO.getDBProductDAO();
		String newPriceString = request.getParameter("price");
		String newQuantityString = request.getParameter("quantity");
		//It needs a chech if the request parameters ar not valid numbers (contain other symbols)!!!
		double newPrice = Double.parseDouble(request.getParameter(newPriceString));
		int newQuantity = Integer.parseInt(request.getParameter(newQuantityString));
		
		
		prdao.updatePrice(pr, newPrice);
		prdao.updateQuantity(pr,newQuantity);
		response.sendRedirect("SuccessfullProductUpdate.jsp");
	}

}
