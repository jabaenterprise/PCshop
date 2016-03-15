package database.dao;

import java.util.HashSet;

import model.Product;

public interface IProductDAO {

	
	
	public void addProduct(Product newProduct);
	
	public void updatePrice(Product p, double newPrice);
	
	public void updateQuantity(Product p, int newQuantity);
	
	
	public Product getProduct(String model);
	
	public HashSet<Product> getAllProducts();
	
	public HashSet<String> getAllModels();
	
	
	public HashSet<Product> serchProducts(String searchWord);
	
}
