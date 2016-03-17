package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import database.dao.DBProductDAO;
import model.Case;
import model.Product;

/**
 * Servlet implementation class ShowInfo
 */
@WebServlet("/ShowInfo")
public class ShowInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int productID =Integer.parseInt(request.getParameter("prodID"));
		System.out.println("From ShowInfo servlet"+productID);
		Product product = DBProductDAO.getDBProductDAO().getProductById(productID);
	
		Map<String, String>  additionalInfo = null;
		
		additionalInfo = product.getAdditionalInfo();
		
		
		
		request.setAttribute("additionalInfo", additionalInfo);
		request.setAttribute("product", product);
		request.getRequestDispatcher("productInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
