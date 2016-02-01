package shop;

import java.util.ArrayList;
import java.util.HashMap;

import client.Client;
import products.Product;


public class Shop {

	//Fields:
	private String name;
	private ArrayList<Client> clients;
	//private ArrayList<ArrayList<Product>> products;
	private HashMap<products.Type,ArrayList<Product>> products;
	private static Shop instance; // let's use the Singleton design pattern in order to call the instance of the shop in user class
	
	//Constructor:
	
	
	//Getters: - only the one we need!
	
	//Setters: - only the one we need!
	
	
	//Methods: - should be static, because our Shop instance is static!
	  //public static Shop getShop() - returns the instance of the shop /Singleton!
	
	  //CLIENT-wise:
		//addClient(Client c)
		//isCorrectLogin(String username, String password)
		
	  //PRODUCT-wise:
		//addProduct(Product newProduct)
		//findProduct(String searchWord) - returns list of products containing the input string in their producer or model
		//displayAllProducts()
		//displayProductCategory(Type category) - displays a product category/see the enum!
		//sortCategoryByPrice(Type category) - see the enum!
		//sortCategoryByProducer(Type category) - see the enum!
	
	
}
