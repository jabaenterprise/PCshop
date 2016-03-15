package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DBAdminDAO;
import database.dao.DBClientDAO;
import model.Admin;
import model.Client;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBClientDAO cldao = DBClientDAO.getDBClientDAO();
		HashMap<String, String> clientsEmailsAndPasswords = cldao.getEMailsAndPasswords();
		String registerEMail = request.getParameter("email");
		System.out.println(registerEMail);
		DBAdminDAO addao = DBAdminDAO.getDBAdminDAO();
		Admin ad = addao.getAdmin();
		if (registerEMail.equalsIgnoreCase(ad.geteMail())) {
			request.setAttribute("retry_register", true);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		} else {
			if (clientsEmailsAndPasswords.containsKey(registerEMail)) {
				request.setAttribute("retry_register", true);
				request.getRequestDispatcher("register.jsp").forward(request, response);
			} else {
				String firstName = request.getParameter("first_name");
				String familyName = request.getParameter("family_name");
				String eMail = request.getParameter("email");
				String password = request.getParameter("password");
				String address = request.getParameter("address");
				String phone = request.getParameter("phone");
				Client newClient = new Client(firstName, familyName, eMail, password, address, phone);
				cldao.addClient(newClient);
				Client registeredClient = cldao.getClient(eMail);
				request.getSession().setAttribute("client", registeredClient);
				response.sendRedirect("index.jsp");
			}
		}
	}
	
	
}
