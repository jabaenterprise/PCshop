package shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import client.Client;
import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;
import exceptions.LoginException;
import exceptions. SearchFailException;
import exceptions.SuchUserAlreadyExistsException;
import products.Product;
import products.Type;


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
		if (findClient(eMail)!=null)
				return true;
		return false;
	}
	private Client findClient(String eMail){
		for(Client clt : this.clients) {
			if (clt.getEmail().equalsIgnoreCase(eMail)) {
				return clt;
			}
		}
		return null;
	}
	//reseting client password returns pair with email and new password so the site can send the user his new password
	public HashMap<String,String> forgotenPassword(String email) throws LoginException{//email,new password
		if(checkIfSuchClientExists(email)){
			throw new LoginException("No account has been registered with this e-mail");
		}
		String newPassword = Integer.toString(email.hashCode());
		findClient(email).setPassword(newPassword);
	
		HashMap<String, String> credentials = new HashMap<>();
		credentials.put(email, newPassword);
		return credentials;
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
	public void createProduct(String producer, String model, double price, String info, int quantity, Type type){
		Product p = null;
		try {
			p = new Product(producer, model, price, info, quantity);
		} catch (InvalidPriceException e) {
			e.toString();
		} catch (InvalidQuantityException e) {
			
			e.toString();
		}
		
		products.get(type).add(p);		
	}
	
	
	
	  //SEARCHING FOR A PRODUCT:
	  	//ArrayList<Product> findProducts(String searchWord) - returns list of products containing the input string in their producer or model.
	ArrayList<Product> findProducts(String searchWord) throws SearchFailException{
		ArrayList<Product> foundProducts = new ArrayList<>();
		for(Entry<products.Type, ArrayList<Product>> entry: products.entrySet()){		//if someone can think about a better way ot find a product please change this
			if(entry.getValue().size()>0){
				for(int i =0;i<entry.getValue().size();i++){
					if(entry.getValue().get(i).getProducer().equalsIgnoreCase(searchWord)
							||
							entry.getValue().get(i).getModel().equalsIgnoreCase(searchWord))
						foundProducts.add(entry.getValue().get(i));		
				}
			}
		}
		if(foundProducts.size()>0){
			return foundProducts;
		}
		else
			throw new SearchFailException("Sorry, your search "+searchWord+" did not match any products. Please try again.");
		
		
	}

	  //DISPLAYING PRODUCTS:
	  	//displayAllProducts()  //not sure if we don't have to return ArrayList of Product that we have to give to web page to display
	public void displayAllProducts(){
		for(Entry<products.Type, ArrayList<Product>> entry: products.entrySet()){
			displayProductCategory(entry.getKey());
		}
		
	}
	  	//displayProductCategory(Type category) - displays a product category/see the enum!
	  public void displayProductCategory(Type category){
		  if(products.get(category).size()>0){
			  sortCategoryByPriceAscending(products.get(category));
			  for(Product p: products.get(category)){
				  p.viewProduct();
			  }
		  }
		 
	  }
	  //SORTING: - with comparators - This methods can be applied after search or when displaying all products:
	  	//sortCategoryByPriceAscending(ArrayList<Product> products)
	  public void sortCategoryByPriceAscending(ArrayList<Product> products){
		  if(products!=null)
		  Collections.sort(products, (Product p1,Product p2)-> ((Double)p1.getPrice()).compareTo(((Double)p2.getPrice())));
	 
	  
	  }
		//sortCategoryByPriceDescending(ArrayList<Product> products)
	 public void sortCategoryByPriceDescending(ArrayList<Product> products){
		 if(products!=null)
		 Collections.sort(products, (Product p1,Product p2)-> -((Double)p1.getPrice()).compareTo(((Double)p2.getPrice())));
	 }
	  	//sortCategoryByProducer(ArrayList<Product> products)
	 public void sortCategoryByProducer(ArrayList<Product> products){
		 Collections.sort(products,(Product p1,Product p2)-> p1.getProducer().compareTo(p2.getProducer()));
	 }
	
}
