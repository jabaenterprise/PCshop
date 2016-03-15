package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.dao.DBClientDAO;
import model.Client;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.isNew()){
			session.setAttribute("isLogged", false);
		}
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("emailLogin");
		String password = request.getParameter("passwordLogin");
		if(validateEmail(email)&&validatePassword(password)){
			HashMap<String, String> emails =  DBClientDAO.getDBClientDAO().getEMailsAndPasswords();
			if(!emails.containsKey(email)){
				response.sendRedirect("register.jsp");
			}else{
				if(password.equals(emails.get(email))){
					Client client = DBClientDAO.getDBClientDAO().getClient(email);
					
					HttpSession session = request.getSession();
					session.setAttribute("client", client);
					session.setAttribute("isLogged", true);
					response.sendRedirect("index.jsp");
					return;
				}else{
					response.sendRedirect("ErrorLogin.jsp?error=wrongEmailOrPassword");
				}
			}
		}else{
			response.sendRedirect("ErrorLogin.jsp?error=invalidEmailOrPassword");
			return;
		}
	}

	
	private boolean validateEmail(String email){
		if(email.matches("(([a-zA-Z])+([0-9]*))+@[a-zA-Z]{2,5}\\.[a-zA-Z]{2,5}")){
			return true;
		}
		return false;
	}
	
	private boolean validatePassword(String password){
		return true;
	}
}
