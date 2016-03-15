package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DBClientDAO;
import model.Client;


@WebServlet("/UpdateClientServlet")
public class UpdateClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String UPDATE_TYPE_PASSWORD = "update_password" ;
    private static final String UPDATE_TYPE_ADDRESS = "update_address" ;
    private static final String UPDATE_TYPE_PHONE = "update_phone" ;
    
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Client c = (Client) request.getSession().getAttribute("client");
		String updateType = request.getParameter("new");
		String eMail = c.geteMail();
		switch (updateType) {
			case UpdateClientServlet.UPDATE_TYPE_PASSWORD:
				String newPassword = request.getParameter("new_password");
				DBClientDAO.getDBClientDAO().changePassword(newPassword, c);
				break;
			case UpdateClientServlet.UPDATE_TYPE_ADDRESS:
				String newAddress = request.getParameter("new_address");
				DBClientDAO.getDBClientDAO().changeAddress(newAddress, c);
				break;
				
			case UpdateClientServlet.UPDATE_TYPE_PHONE:
				String newPhone = request.getParameter("new_phone");
				DBClientDAO.getDBClientDAO().changePhone(newPhone, c);
				break;
			
		}
		Client client = DBClientDAO.getDBClientDAO().getClient(eMail);
		request.getSession().setAttribute("client", client);
		response.sendRedirect("edit_client.jsp");
		
	}

}
