package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import database.dao.DBProductDAO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchWord = request.getParameter("search_word");
		List<Product> products = DBProductDAO.getDBProductDAO().serchProducts(searchWord);

		if (products == null) {
			products = new LinkedList<Product>();
		}
		/*for(Product p:products){
			System.out.println("Model: " + p.getModel() + "Producer " + p.getProducer() + "Info " + p.getInfo());
		}*/
		request.getSession().setAttribute("searchedProducts",products);
		response.sendRedirect("index.jsp?search_word=" + searchWord);
	}
}
