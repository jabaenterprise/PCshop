package model;

import java.util.HashMap;

public class Client {

	
	private int id;
	private String firstName;
	private String familyName;
	private String eMail;
	private String password;
	private String address;
	private String phone;
	private double money;
	private HashMap<Product, Integer> cart;
	
	
	public Client(String firstName, String familyName, String eMail, String password, String address, String phone) {
		this.firstName = firstName;
		this.familyName = familyName;
		this.eMail = eMail;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.cart = new HashMap<Product, Integer>();
	}


	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public String geteMail() {
		return eMail;
	}
	public String getPassword() {
		return password;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public double getMoney() {
		return money;
	}
	public HashMap<Product, Integer> getCart() {
		return cart;
	}


	public void setId(int id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public void setCart(HashMap<Product, Integer> cart) {
		this.cart = cart;
	}

	
	//Clients differ by their e-mail:
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (eMail == null) {
			if (other.eMail != null)
				return false;
		} else if (!eMail.equals(other.eMail))
			return false;
		return true;
	}
	
	
	
}
