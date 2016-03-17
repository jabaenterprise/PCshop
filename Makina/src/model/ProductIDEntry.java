package model;

public class ProductIDEntry {
	private Product product;
	private int id;
	public ProductIDEntry(Product product, int id) {
		super();
		this.product = product;
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public int getId() {
		return id;
	}
	
	
}