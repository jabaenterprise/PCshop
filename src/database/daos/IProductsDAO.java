package database.daos;



import products.Product;

public interface IProductsDAO {

	
	void addProduct(Product product);
	
	
	public Product getProduct(String model);
	
	public void updatePrice(Product p, double newPrice);
	
	public void updateQuantity(Product p, int newQuantity);
	
}
