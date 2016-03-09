package controler;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.Client;
import database.daos.DBClientDAO;
import database.daos.DBShopDAO;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	//Fields:
	private static final long serialVersionUID = 1L;
       
   
	//Overriden methods:
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DBClientDAO cldao= new DBClientDAO();
		
		HashSet<String> eMails = cldao.getClientEmails();
		String eMail = request.getParameter("email");
		if (eMails.contains(eMail)) {
			response.sendRedirect("RetryRegister.jsp");
		} else {
			
			String firstName = request.getParameter("first_name");
			String familyName = request.getParameter("family_name");
			String password = request.getParameter("pass_word");
			String city = request.getParameter("city");
			String address = request.getParameter("address");

			Client newClient = new Client(firstName, familyName, eMail, password, city, address);
			
			DBShopDAO shdao= new DBShopDAO();
			shdao.addNewClient(newClient);
			request.getSession().setAttribute("Loged_User", newClient);
			boolean isLogged = true;
			request.getSession().setAttribute("isLogged", isLogged);
			response.sendRedirect("index.jsp");
		}
		
	}

}
