package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DBProductDAO;
import model.Product;


@WebServlet("/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String modelToSearch = request.getParameter("search_word");
		
		DBProductDAO prdao = DBProductDAO.getDBProductDAO();
		Product pr = prdao.getProduct(modelToSearch);
		
		if (pr == null) {
			request.setAttribute("not_found", true);
			request.getRequestDispatcher("EditProduct.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("last_updated_product", pr);
			response.sendRedirect("EditProduct.jsp");
		}
		
	}

}
