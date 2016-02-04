package shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import client.Client;
import exceptions.LoginException;
import exceptions.SearchException;
import exceptions.SuchProductAlreadyExistsException;
import exceptions.SuchUserAlreadyExistsException;
import products.Product;
import products.Type;


public class Shop {

	
	//Fields:
	private HashSet<Client> clients;
	private HashMap<products.Type,ArrayList<Product>> products;
	private static Shop instance;
	private String eMail;
	private String password;
	
	
	//Constructor:
	private Shop() {
		this.clients = new HashSet<Client>();
		this.products = new HashMap<products.Type,ArrayList<Product>>();
		this.password = "password"; //initial default values that could be changed by the admin with the setters:
		this.eMail = "someEmail@address";
	}
	
	public HashMap<products.Type,ArrayList<Product>>getProducts(){
		return products;
	}
	//Getters: - only the one we need!
	
	//Setters: - only the one we need!
	//available to the admin only:
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	//Methods:
	public static Shop getShop() {
		if (Shop.instance == null) {
			Shop.instance = new Shop();
			return Shop.instance;
		} else {
			return Shop.instance;
		}
	}
		
	//CLIENT-wise:
	public Client createNewClient(String firstName, String familyName, String eMail, String password, String city, String address, String postalCode) throws SuchUserAlreadyExistsException {
		if (!this.checkIfSuchClientExists(eMail)) {
			Client newClient = new Client(firstName, familyName, eMail, password, city, address, postalCode);
			this.clients.add(newClient);
			return newClient;
		} 
//		else {
//			try {
				throw new SuchUserAlreadyExistsException("Can't register new client. Client with such e-mail address already exists.");
//			} catch (SuchUserAlreadyExistsException e) {
//				System.out.println(e.getMessage());
//			}
//		}
	}
	
	private boolean checkIfSuchClientExists(String eMail) {
		if (findClient(eMail)!= null)
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
	
	public void resetForgottenPassword(String email) throws LoginException {
		if(checkIfSuchClientExists(email))//{
//			try {
				throw new LoginException("No account has been registered with this e-mail");
//			} catch (LoginException e) {
//				System.out.println(e.getMessage());
//			}
//		}
		String newPassword = Integer.toString(email.hashCode());
		findClient(email).setPassword(newPassword);
	
		System.out.println("New password sent to " + email);
		//some method which can send e - mails....
	}
		
	
	
	
	//if the entered e-mail and password match the shop's ones the user enters as admin and can use the admin methods.
	//if they don't match they are used as arguments in the isCorrectLogin(String eMali, String password) method.
	//if the user is registered he goes to the wellcome page.
	private boolean isAdmin(String eMail, String password) {
		if (eMail.equalsIgnoreCase(this.eMail) && password.equals(this.password)) {
			return true;
		}
		return false;
	}
	
	public boolean isCorrectLogin(String eMail, String password) throws LoginException {
		if(isAdmin(eMail, password)){
			//open admin page 
		}
		else{
			for (Client clt : this.clients) {
				if (clt.getEmail().equalsIgnoreCase(eMail) && clt.getPassword().equals(password)){ //{
					clt.setIsLoggedIn(true);
					return true;
				}
					
			}
		}
		throw new LoginException("Invalid eMail or password.");
//		int counter = 0;
		
//			} else {
//				counter++;
//			}
	
//		if (counter == this.clients.size()) {
//			try {
				
//			} catch (LoginException e) {
//				System.out.println(e.getMessage());
//				
//			}
//		}
//		return false;
	}
	
	
	//PRODUCT-wise:
	//Used only by admin:
	//The admin page will have a scroll with different product types. When he clicks on 
	//one of them it invokes the corresponding constructor - a page with fields for entering the values of the corresponding item.
	//then he can use the bellow method to add the product to the shop:
	public void addProductToShop(Product p) {
		try {
			if(p!=null){
				if(products.get(p.getType())==null){//if product is added for first time create new list
					products.put(p.getType(), new ArrayList<Product>());
				}
				if (!doWeHaveSuchProductInShop(p)) {
					products.get(p.getType()).add(p);
				}
			}
		} catch (SuchProductAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean doWeHaveSuchProductInShop(Product p) throws SuchProductAlreadyExistsException {
		if(!this.products.isEmpty()){
//			int counter = 0;
			for (Product product : this.products.get(p.getType())) {
				if (product.equals(p)) {
					
						throw new SuchProductAlreadyExistsException(p.getProducer() + " " + p.getModel() + " already exists.");
				}
			}
//				} else {
//					counter++;
//				}
//			}
//			if (counter == this.products.get(p.getType()).size()) {
				return false;
//			}
//			return true;
		}
		return false;//there is no such product
	}
	
	
	//Usable both by admin and users:
	public ArrayList<Product> searchProducts(String searchWord) { //the returned ArrayList will be the argument for the soting methods
		ArrayList<Product> foundProducts = new ArrayList<>();
		for(Entry<products.Type, ArrayList<Product>> entry: products.entrySet()){		//if someone can think about a better way ot find a product please change this
			if(entry.getValue().size()>0){
				for(int i =0;i<entry.getValue().size();i++){
					if(entry.getValue().get(i).getProducer().equalsIgnoreCase(searchWord) || entry.getValue().get(i).getModel().equalsIgnoreCase(searchWord))
						foundProducts.add(entry.getValue().get(i));		
				}
			}
		}
		if(foundProducts.size()>0){
			return foundProducts;
		} else {
			try {
				throw new SearchException("Sorry, your search "+searchWord+" did not match any products. Please, try again.");
			} catch (SearchException e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
	}
	
	public ArrayList<Product> displayAllProducts(){	//the returned ArrayList will be the argument for the soting methods
		ArrayList<Product> allProducts = new ArrayList<Product>();
		for(Entry<products.Type, ArrayList<Product>> entry: products.entrySet()){
			ArrayList<Product> typeProducts = displayProductCategory(entry.getKey());
			allProducts.addAll(typeProducts);
		}
		return allProducts;
	}
	  	
	public ArrayList<Product> displayProductCategory(Type category){ //the returned ArrayList will be the argument for the soting methods
		  if(products.get(category).size()>0){
			  for(Product p: products.get(category)){
				  p.viewProduct();
				 
			  }
		  } else {
			  try {
				throw new SearchException("Currently no products in " + category + " category.");
			} catch (SearchException e) {
				System.out.println(e.getMessage());
			}
		  }
		  
		  return products.get(category); 
		  
	  }
	
	private void displayProducts(ArrayList<Product> products)  {
		for(Product p: products){
			  p.viewProduct();
			 
		  }
	}
	
	public void sortAndDisplayByPriceAscending(ArrayList<Product> products){
		  if(products!=null) {
			   Collections.sort(products, (Product p1,Product p2)-> ((Double)p1.getPrice()).compareTo(((Double)p2.getPrice())));
		  }
		  displayProducts(products);
	  
	 }
	
	public void sortAndDisplayByPriceDescending(ArrayList<Product> products){
		 if(products!=null) {
			 Collections.sort(products, (Product p1,Product p2)-> -((Double)p1.getPrice()).compareTo(((Double)p2.getPrice())));
		 }
		 displayProducts(products);
	}
	 
	public void sortAndDisplayByProducer(ArrayList<Product> products){
		if(products!=null) {
			Collections.sort(products,(Product p1,Product p2)-> p1.getProducer().compareTo(p2.getProducer()));
		}
		displayProducts(products);
	}
	
	
	
}
