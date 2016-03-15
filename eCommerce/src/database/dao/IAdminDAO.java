package database.dao;

import model.Admin;

public interface IAdminDAO {

	public Admin getAdmin();
	
	public void changeEmail(String newEMail);
	
	public void changePassword(String newPassword);
}
