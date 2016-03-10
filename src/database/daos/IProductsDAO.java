package database.daos;



import products.Product;

public interface IProductsDAO {

	
	void addProduct(Product product);
	
	Product getProduct(String model);

	Product getProductById(int id);
	
	public void updatePrice(Product p, double newPrice);
	
	public void updateQuantity(Product p, int newQuantity);
}
