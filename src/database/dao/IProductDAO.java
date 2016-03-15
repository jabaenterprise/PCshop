package database.dao;

import java.util.HashSet;
import java.util.List;

import model.Product;

public interface IProductDAO {

	
	
	public void addProduct(Product newProduct);
	
	public void updatePrice(Product p, double newPrice);
	
	public void updateQuantity(Product p, int newQuantity);
	
	
	public Product getProduct(String model);
	
	public HashSet<Product> getAllProducts();
	
	public HashSet<String> getAllModels();
	
	
	public List<Product> serchProducts(String searchWord);
	
	public List<Product> getAllProducts(String categoryType, String sortType);
	
	Product getProductById(int id);
	
}
