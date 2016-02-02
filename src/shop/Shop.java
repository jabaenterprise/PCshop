package shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import client.Client;
import exceptions.LoginException;
import exceptions.SuchUserAlreadyExistsException;
import products.Product;


public class Shop {

	
	//Fields:
	private HashSet<Client> clients;
	private HashMap<products.Type,ArrayList<Product>> products;
	private static Shop instance;
	
	
	//Constructor:
	private Shop() {
		this.clients = new HashSet<Client>();
		this.products = new HashMap<products.Type,ArrayList<Product>>();
	}
	
	
	//Getters: - only the one we need!
		
	
	//Setters: - only the one we need!
	
	
	
	//Methods:
	//SHOP-wise:
	public static Shop getShop() {
		if (Shop.instance == null) {
			Shop.instance = new Shop();
			return Shop.instance;
		} else {
			return Shop.instance;
		}
	}
		
	
	//CLIENT-wise:
	private boolean checkIfSuchClientExists(String eMail) {
		for(Client clt : this.clients) {
			if (clt.getEmail().equalsIgnoreCase(eMail)) {
				return true;
			}
		}
		return false;
	}
	
	//This is the Client factory method:
	public void createNewClient(String firstName, String familyName, String eMail, String password, String city, String address, String postalCode) {
		if (!this.checkIfSuchClientExists(eMail)) {
			Client newClient = new Client(firstName, familyName, eMail, password, city, address, postalCode);
			this.clients.add(newClient);
		} else {
			try {
				throw new SuchUserAlreadyExistsException("Can't register new client. Client with such e-mail address already exists.");
			} catch (SuchUserAlreadyExistsException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public boolean isCorrectLogin(String eMail, String password) {
			int counter = 0;
			for (Client clt : this.clients) {
				if (clt.getEmail().equalsIgnoreCase(eMail) && clt.getPassword().equals(password)) {
					return true;
				} else {
					counter++;
				}
			} 
			if (counter == this.clients.size()) {
				try {
					throw new LoginException("Invalid eMail or password.");
				} catch (LoginException e) {
					System.out.println(e.getMessage());
					
				}
			}
			return false;
		}
		
	
	//PRODUCT-wise:
	  //CREATING AND ADDING NEW PRODUCT: This is the Product factory method:
		//public Product createProduct((String producer, String model, double price, String info, int quantity) - creates and adds the product, throws SuchProductAlreadyExistsException
	  
	  //SEARCHING FOR A PRODUCT:
	  	//ArrayList<Product> findProducts(String searchWord) - returns list of products containing the input string in their producer or model.
  		

	  //DISPLAYING PRODUCTS:
	  	//displayAllProducts()
	  	//displayProductCategory(Type category) - displays a product category/see the enum!
	  
	  //SORTING: - with comparators - This methods can be applied after search or when displaying all products:
	  	//sortCategoryByPriceAscending(ArrayList<Product> products)
		//sortCategoryByPriceDescending(ArrayList<Product> products)
	  	//sortCategoryByProducer(ArrayList<Product> products)
	
	
}
