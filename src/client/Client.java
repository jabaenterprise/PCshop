package client;


import java.util.HashMap;
import java.util.Map;
import exceptions.IsInCartException;
import exceptions.NotEnoughInStockException;
import exceptions.NotEnoughMoneyException;
import products.Product;
import shop.Shop;



public class Client {

	
	//Fields:
	private static final int MIN_PRODUCT_QUANTITY = 1;
	private int userID;
	private String firstName;
	private String lastName;
	private String eMail;
	private String password;
	private String city;
	private String address;
	private String phoneNumber;
	public/*private*/ double money;
	private HashMap<Product, Integer> cart;
	private boolean isLoggedIn = false;
	private Shop shop;

	
	//Constructor:
	public Client(String firstName, String familyName, String eMail, String password, String city, String address) {
		this.firstName = firstName;
		this.lastName = familyName;
		this.eMail = eMail;
		this.password = password;
		this.city = city;
		this.address = address;
		this.cart = new HashMap<Product, Integer>();
		this.shop = Shop.getShop();
		
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
	public int getUserID() {
		return userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String geteMail() {
		return eMail;
	}
	public String getCity() {
		return city;
	}
	public String getAddress() {
		return address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public double getMoney() {
		return money;
	}
	public HashMap<Product, Integer> getCart() {
		return cart;
	}
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public Shop getShop() {
		return shop;
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
		this.lastName = familyName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		
		
	}
	
	
	
	
	
	//Methods for viewing the client info, managing the cart and buying products from the cart:
	public void viewClient() {
		System.out.println("Firts name: " + this.firstName);
		System.out.println("Family name: " + this.lastName);
		System.out.println("Address:");
		System.out.println(this.city);
		System.out.println(this.address);
		System.out.println("Phone number: " + this.phoneNumber);
		
	}

	public void viewCart() {
		for(Map.Entry<Product, Integer> entry : this.cart.entrySet()){
			entry.getKey().viewProduct();
			System.out.println("Quantity: " + entry.getValue());
		}
	}

	public void addProductToCart(Product p) { 
		for(Product products:shop.getProducts().get(p.getType()))
			if(products.equals(p)&&products.getQuantity()>=MIN_PRODUCT_QUANTITY){
				if(!cart.containsKey(p)){
					cart.put(p, MIN_PRODUCT_QUANTITY);
				} else
					try {
						throw new IsInCartException(p.getProducer() + " " + p.getModel() + " is already in your cart.");
					} catch (IsInCartException e) {
						System.out.println(e.getMessage());
					}
			}
		
//			} catch (IsInCartException e) {
//				System.out.println(e.getMessage());
//			}
	}
//fixed this because the products still had to be paid even if there was not enough in stock 
	public void setQuantityOfAProductInCart(Product p, int quantity) {
		for(Product products:shop.getProducts().get(p.getType()))
			if(products.equals(p)&&products.getQuantity()>=quantity){
				this.cart.put(p, quantity);
				break;
			}
			else{
				this.cart.put(p, products.getQuantity());
				try {
					products.decreaseQuantity(products.getQuantity());
				} catch (NotEnoughInStockException e) {
					System.out.println("something is very wrong if this happens");
				}
				break;
			}
				
		
	}
		
	public void showTotalPriceOfProductsInCart() {
		double cost = 0;
		for(Map.Entry<Product, Integer> entry : this.cart.entrySet()){
			cost+= entry.getKey().getPrice()*entry.getValue();
		}
		this.money-=cost;
		System.out.println("Total cost: " + cost + " BGN");
	}

	public void removeProductFromCart(Product p){
		if(this.cart.containsKey(p))
			this.cart.remove(p);
	}
//fixed buying without money problem 			
	public void buyProductsInCart() throws NotEnoughMoneyException{
		double cost = 0;
		
		if(isLoggedIn){
			System.out.println("?");
			for(Map.Entry<Product, Integer> entry : this.cart.entrySet()){
				cost += entry.getKey().getPrice()*entry.getValue();
				try {
					entry.getKey().decreaseQuantity(entry.getValue());
				} catch (NotEnoughInStockException e) {
					System.out.println(e.getMessage());
				}
			}
			if(money<cost){
				throw new NotEnoughMoneyException("Not enough money in you ballance!");
			
			}
			this.money-=cost;	
		}
		
		
	}

	public void addMoneyToAccount(int money) {
		if(money>0){
			this.money= money;
			System.out.println(money+"BGN added to account");
		}
			
		
	}

	public void setIsLoggedIn(boolean b) {
		isLoggedIn = b;
		
	}

	public void setUserId(int userId) {
		this.userID = userId;
		
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		
	}

	public void setCart(HashMap<Product, Integer> cart) {
		this.cart = cart;
		
	}
}
