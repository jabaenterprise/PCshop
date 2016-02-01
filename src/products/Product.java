package products;

public abstract class Product {

	private String producer;
	private String model;
	private double price;
	private String info;
	private int quantity;
	private boolean isAvailable;
	private Type type; //See the enum file!
	

	
	//Constructor:
	
	
	//Getters: - only the one needed!
	public double getPrice() {
		return price;
	}
	
	//Setters: - only the one needed!
	
	//Overriden methods:
	//hashCode() - for the cart HashTable 
	//equals() - for the cart HashTable
	//toString() - for displaying the product characteristics (producer, model, price, info, isAwailable)
	
	//Other methods:
	
}
