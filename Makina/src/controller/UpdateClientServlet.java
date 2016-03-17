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


@WebServlet("/UpdateClientServlet")
public class UpdateClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("client", request.getParameter("client"));
		Client c = (Client)request.getSession().getAttribute("client");
		System.out.println(request.getParameter("First name"));
		if(request.getParameter("firstName")!=null){
			if(request.getParameter("firstName")!="")
			c.setFirstName(request.getParameter("firstName"));
		}
		if(request.getParameter("lastName")!=null){
			if(request.getParameter("lastName")!="")
			c.setFamilyName(request.getParameter("lastName"));
		}
		
		if(request.getParameter("address")!=null){
			if(request.getParameter("address")!="")
			c.setAddress(request.getParameter("address"));
		}
		if(request.getParameter("phoneNumber")!=null){
			if(request.getParameter("phoneNumber")!="")
			c.setPhone(request.getParameter("phoneNumber"));
		}
		
		IClientDAO dbCli =DBClientDAO.getDBClientDAO();
		
		dbCli.updateClientInfo(c);
	
		request.getRequestDispatcher("/CartAndUser.jsp").forward(request, response);
	}

}
