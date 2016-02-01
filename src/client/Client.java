package client;

import java.util.HashMap;
import java.util.Map;

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
	private Client(String firstName, String familyName, String eMail, String password, String city, String address, String postalCode) {
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
	
	
	
	//Getters: - only the one we need!
	
	
	//Setters: - only the one we need!

	
	//Methods:
	  //CART-wise:
	public	void addProductToCart(Product p, int quantity){
		if(cart.containsKey(p)){
			cart.put(p, cart.get(p)+quantity);
		}
		else
			cart.put(p, quantity);
	}


	public void removeProductFromCart(Product p){
		if(cart.containsKey(p))
			cart.remove(p);
	}
	
	
	
	  //SHOP-wise:
		//buyProductsInCart() - the amounts of products in shop drop and the money of the client too
		public void buyProductsInCart(){
			double cost = 0;
			for(Map.Entry<Product, Integer> entry:cart.entrySet()){
				cost += entry.getKey().getPrice()*entry.getValue();
			}
			//printing total cost and confirming the buy
			
			this.money-=cost;
		
			
		}
	
		//he uses the shop methods by calling the getShop() method and applying the Shop methods on it - searches for products, etc.
		


}
