package model;

public class Admin {

	
	private int id;
	private String eMail;
	private String password;
	
	
	public Admin(String eMail, String password) {
		this.eMail = eMail;
		this.password = password;
	}

	
	public int getId() {
		return id;
	}
	public String geteMail() {
		return eMail;
	}
	public String getPassword() {
		return password;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
