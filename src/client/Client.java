package client;

import java.util.HashMap;
import java.util.Map;
import exceptions.IsInCartException;
import exceptions.NotEnoughInStockException;
import products.Product;



public class Client {

	
	//Fields:
	private static int ID = 1;
	private final int userID;
	private String firstName;
	private String familyName;
	private String eMail;
	private String password;
	private String city;
	private String address;
	private String postalCode;
	private String phoneNumber;
	private double money;
	private HashMap<Product, Integer> cart;
	
	
	
	//Constructor:
	public Client(String firstName, String familyName, String eMail, String password, String city, String address, String postalCode) {
		this.userID = Client.ID++;
		this.firstName = firstName;
		this.familyName = familyName;
		this.eMail = eMail;
		this.password = password;
		this.city = city;
		this.address = address;
		this.postalCode = postalCode;
		this.cart = new HashMap<Product, Integer>();
	}
	
	
	
	//Overriden methods - users differ by their e-mail address:
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
		} else if (!eMail.equalsIgnoreCase(other.eMail))
			return false;
		return true;
	}
	
	
	
	//Getters: - only the one we need:
	public String getEmail() {
		return this.eMail;
	}
	public String getPassword() {
		return this.password;
	}
	
	
	
	//Setters to be used by the client for changing some of his/her profile info, if needed:
//	public void setFirstName(String newFirstName) {
//		this.firstName = newFirstName;
//	}
//	public void setFamilyName(String newFamilyName) {
//		this.familyName = newFamilyName;
//	}
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
//	public void setCity(String newCity) {
//		this.city = newCity;
//	}
//	public void setAddress(String newAddress) {
//		this.address = newAddress;
//	}
//	public void setPostalCode(String newPostalCode) {
//		this.postalCode = newPostalCode;
//	}
//	public void setPhoneNumber(String newPhoneNumber) {
//		this.phoneNumber = newPhoneNumber;
//	}
	public void editBasicInfo(String firstName,String familyName, String address,String postalCode,String phoneNumber){
		this.firstName = firstName;
		this.familyName = familyName;
		this.address = address;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		
		
	}
	
	
	
	
	
	//Methods for viewing the client info, managing the cart and buying products from the cart:
	public void viewClient() {
		System.out.println("Firts name: " + this.firstName);
		System.out.println("Family name: " + this.familyName);
		System.out.println("Address:");
		System.out.println(this.city);
		System.out.println(this.address);
		System.out.println("Postal code: " + this.postalCode);
		System.out.println("Phone number: " + this.phoneNumber);
		
	}

	public void viewCart() {
		for(Map.Entry<Product, Integer> entry : this.cart.entrySet()){
			entry.getKey().viewProduct();
			System.out.println("Quantity: " + entry.getValue());
		}
	}

	public void addProductToCart(Product p){ 
		if(!cart.containsKey(p)){
			cart.put(p, 0);
		} else
			try {
				throw new IsInCartException(p.getProducer() + " " + p.getModel() + " is already in your cart.");
			} catch (IsInCartException e) {
				System.out.println(e.getMessage());
			}
	}

	public void setQuantityOfAProductInCart(Product p, int quantity) {
		this.cart.put(p, quantity);
	}
		
	public void showTotalPriceOfProductsInCart() {
		double cost = 0;
		for(Map.Entry<Product, Integer> entry : this.cart.entrySet()){
			cost+= entry.getKey().getPrice()*entry.getValue();
		}
		this.money-=cost;
		System.out.println("Total cost: " + cost + " BGL");
	}

	public void removeProductFromCart(Product p){
		if(this.cart.containsKey(p))
			this.cart.remove(p);
	}
			
	public void buyProductsInCart(){
		double cost = 0;
		for(Map.Entry<Product, Integer> entry : this.cart.entrySet()){
			cost += entry.getKey().getPrice()*entry.getValue();
			try {
				entry.getKey().decreaseQuantity(entry.getValue());
			} catch (NotEnoughInStockException e) {
				System.out.println(e.getMessage());
			}
		}
		this.money-=cost;
	}
		
	
		
		
}
