package database.daos;



import products.Product;

public interface IProductsDAO {

	
	void addProduct(Product product);
	
	
	public Product getProduct(String model);
	
	
	
}
