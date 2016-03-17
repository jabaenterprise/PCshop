package database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBManager;
import model.Admin;

public class DBAdminDAO implements IAdminDAO {

	private static final int ADMIN_ID =1;
	private static DBAdminDAO instance;
		
	private DBAdminDAO(){
		
	}
		
	public static DBAdminDAO getDBAdminDAO(){
		if (DBAdminDAO.instance == null) {
			DBAdminDAO.instance = new DBAdminDAO();
		}
		return DBAdminDAO.instance;
	}
	
	
	//Methods:
	@Override
	public Admin getAdmin() {
		Admin admin = null;
		String query = "SELECT admin_id, email, password FROM "+DBManager.DBNAME+".admins WHERE admin_id=1;";
		
		try (Statement st = DBManager.getDBManager().getConnection().createStatement()){
			ResultSet rs = st.executeQuery(query);
			rs.next();
			int id = rs.getInt("admin_id");
			String eMail = rs.getString("email");
			String password = rs.getString("password");
			admin = new Admin(eMail, password);
			admin.setId(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return admin;
	}


	
	@Override
	public void changeEmail(String newEMail) {
		String query = "UPDATE "+DBManager.DBNAME+".admins SET email= ? WHERE product_id=" + DBAdminDAO.ADMIN_ID + ";";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			prst.setString(1, newEMail);
			prst.execute();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	


	@Override
	public void changePassword(String newPassword) {
		String query = "UPDATE "+DBManager.DBNAME+".admins SET password= ? WHERE product_id=" + DBAdminDAO.ADMIN_ID + ";";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			prst.setString(1, newPassword);
			prst.execute();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
