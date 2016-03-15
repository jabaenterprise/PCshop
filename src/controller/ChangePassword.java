package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DBClientDAO;
import database.dao.IClientDAO;
import model.Client;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client c = null;
		try {
			 c = (Client) request.getSession().getAttribute("client");
				System.out.println(c.getPassword());
			 System.out.println("client is ok " + c.getPassword());
		} catch (Exception e) {
			System.out.println("0");
			response.sendRedirect("index.jsp");
		}
		request.getSession().setAttribute("passwordCheck", "NOTOK");
		
		System.out.println(request.getParameter("oldPassword"));
		System.out.println(request.getParameter("pass1"));
		System.out.println(request.getParameter("pass2"));
	
		if(request.getParameter("oldPassword")!=null&&request.getParameter("pass1")!=null&&request.getParameter("pass2")!=null){
			System.out.println("1");
			String old = request.getParameter("oldPassword").toString();
			String newPass = request.getParameter("pass1");
			String confPass = request.getParameter("pass2");
			if(c.getPassword().equals(old)){
				System.out.println("2");
				if(newPass.equals(confPass)){
					System.out.println("3");
					
					if(newPass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}")){
						System.out.println("match");
					
					c.setPassword(request.getParameter("pass1"));
					IClientDAO dbCli = DBClientDAO.getDBClientDAO();
					dbCli.changePassword(newPass, c);
					request.getSession().invalidate();
					request.getSession().setAttribute("passwordCheck", "OK");
					}
				}
				
			}
		}
		
	
		response.sendRedirect("PassChange.jsp");
	}

}
