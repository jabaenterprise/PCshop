package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import model.Client;
import database.dao.DBProductDAO;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			// isLogged, client, category, sort, page, products;
			HttpSession session = request.getSession();
			List<Product> products = new ArrayList<Product>();
			
			//moje da se mahne
			if (session.isNew()) {
				session.setAttribute("isLogged", false);
			}
			Client client = (Client) session.getAttribute("client");
			if (client == null) {
				session.setAttribute("isLogged", false);
			}
			//***
			
			// isValidPage
			String pageString = (String) request.getParameter("pageId");
			int pageId = 1;
			if (pageString != null && pageString.matches("\\d+")) {
				pageId = Integer.parseInt(pageString);
			}
			
			// isValidCategory
			String category = request.getParameter("category");
			if (category == null) {
				category = "none";
			}
			request.setAttribute("category", "none");
			
						
			// isValidSortType
			String sortType = request.getParameter("sortType");
			request.setAttribute("sortType", sortType);
			if (sortType == null) {
				sortType = "priceAscending";
			}
			request.setAttribute("sortType", "priceAscending");

			
			String searchWord = request.getParameter("search_word");
			if(searchWord == null){
				searchWord = "none";
			}
			
			if(searchWord.equals("none")){
				products = DBProductDAO.getDBProductDAO().getAllProducts(category,sortType);
				session.removeAttribute("searchedProducts");
			}else{
				products = (List<Product>)session.getAttribute("searchedProducts");
			}
						
		int maxPage = 1;
		if (products.size() > 0) {
			if (products.size() % 12 != 0) {
				maxPage = products.size() / 12 + 1;
			} else {
				maxPage = products.size() / 12;
			}

			if (pageId > maxPage) {
				pageId = maxPage;
			}
		} else {
			pageId = 1;
		}
		List<Product> sublist;
		if(pageId*12>=products.size()){
			sublist = products.subList((pageId-1)*12, products.size());
		}else{
			sublist = products.subList((pageId-1)*12, pageId*12);
		}
		
		request.setAttribute("products", sublist);
		session.setAttribute("products", sublist);
		
		int prevPage = pageId - 1;
		int nextPage = pageId + 1;
		
		String queryString;
		String prevQueryString;
		String nextQueryString;
		if(searchWord=="none"){
			queryString = "category=" + category + "&sortType=" + sortType;
		}else{
			queryString = "search_word=" + searchWord;
		}
		
		prevQueryString = queryString + "&pageId="+prevPage;
		nextQueryString = queryString + "&pageId="+nextPage;
		request.setAttribute("category", category);
		request.setAttribute("sortType", sortType);
		request.setAttribute("pageId", pageId);
		request.setAttribute("prevQueryString", prevQueryString);
		request.setAttribute("nextQueryString", nextQueryString);

		if (prevPage >= 1) {
			request.setAttribute("hasPrev", true);
		} else {
			request.setAttribute("hasPrev", false);
		}
		if (nextPage <= maxPage) {
			request.setAttribute("hasNext", true);
		} else {
			request.setAttribute("hasNext", false);
		}
	}
}
