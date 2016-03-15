package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DBProductDAO;
import model.Product;

@WebServlet("/UpdatePriceServlet")
public class UpdatePriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBProductDAO prdao = DBProductDAO.getDBProductDAO();
		Product pr = (Product) request.getSession().getAttribute("last_updated_product");
		double newPrice = Double.parseDouble(request.getParameter("new_price"));
		prdao.updatePrice(pr, newPrice);
		String model = pr.getModel();
		
		Product editedProduct = prdao.getProduct(model);
		request.getSession().setAttribute("last_updated_product", editedProduct);
		response.sendRedirect("EditProduct.jsp");
		
	}

}
