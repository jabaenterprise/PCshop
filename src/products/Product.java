package products;

import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;
import exceptions.NotEnoughInStockException;

public class Product {

	
	//Fields:
	private String producer;
	private String model;
	private double price;
	private String info;
	private int quantity;
	private boolean isAvailable;
	private Type type; //See the enum file! 
	//Georgi: I'm not sure we need this here if we are to have "Product" as non abstract class otherwise we need to add "Type" to constructor
	

	
	//Constructor:
	public Product(String producer, String model, double price, String info, int quantity) throws InvalidPriceException, InvalidQuantityException {
		this.producer = producer;
		this.model = model;
		this.info = info;
		if (price > 0) {
			this.price = price;
		} else {
			throw new InvalidPriceException("Invalid price entered for " + this.producer + " " + this.model);
		}
		if (quantity >= 0) {
			this.quantity = quantity;
		} else {
			throw new InvalidQuantityException("Invalid quantity entered for " + this.producer + " " + this.model);
		}
		if (this.quantity > 0) {
			this.isAvailable = true;
		} else {
			this.isAvailable = false;
		}
	}
	
	
	
	//Getters: - only the one needed!
	public double getPrice() {
		return price;
	}
	public String getProducer() {
		return this.producer;
	}
	public String getModel() {
		return this.model;
	}
	
	
	
	//Setters: - only the one needed!
	
	
	
	
	//Overriden methods - product differ by their model:
	@Override
 	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equalsIgnoreCase(other.model))
			return false;
		return true;
	}
		
	
	
	
	//Other methods:
	public void viewProduct() {
		System.out.println("Producer: " + this.producer);
		System.out.println("Model: " + this.model);
		System.out.println("Price: " + this.price + " BGL");
		System.out.println("Description: " + this.info);
		System.out.println(this.isAvailable ? "In stock" : "Out of stock");
	}
	
	public void decreaseQuantity(Integer amount) throws NotEnoughInStockException {
		if (this.quantity >= amount) {
			if (this.quantity == amount) {
				this.isAvailable = false;
			}
			this.quantity-=amount;
		} else {
			throw new NotEnoughInStockException("Sorry, but the shop doesn't have " + amount + " " + this.producer + " " + this.model + " in stock at this moment.");
		}
	}
	
	
}
