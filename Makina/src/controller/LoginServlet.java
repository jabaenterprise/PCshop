package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DBAdminDAO;
import database.dao.DBClientDAO;
import database.dao.DBProductDAO;
import model.Admin;
import model.Client;
import model.Product;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBClientDAO cldao = DBClientDAO.getDBClientDAO();
		HashMap<String, String> clientsEmailsAndPasswords = cldao.getEMailsAndPasswords();
		String loginEMail = request.getParameter("email");
		String loginPassword = request.getParameter("password");
		
		DBAdminDAO addao = DBAdminDAO.getDBAdminDAO();
		Admin ad = addao.getAdmin();
		if (loginEMail.equalsIgnoreCase(ad.geteMail()) && loginPassword.equals(ad.getPassword())) {
			request.getSession().setAttribute("is_admin", true);
			response.sendRedirect("admin.jsp");
		} else {
			if (clientsEmailsAndPasswords.containsKey(loginEMail) && clientsEmailsAndPasswords.get(loginEMail).equals(loginPassword)) {
				Client cl = cldao.getClient(loginEMail);
				System.out.println(cl.toString());
				request.getSession().setAttribute("client", cl);
				DBProductDAO prdao = DBProductDAO.getDBProductDAO();
				HashSet<Product> allProductsInShop = prdao.getAllProducts();
				request.getSession().setAttribute("products", allProductsInShop);
				response.sendRedirect("index.jsp");
			} else {
				request.setAttribute("no_such_client", true);
				request.getRequestDispatcher("index.jsp").forward(request,  response);
			}
		}
		
		
		
	}

}
