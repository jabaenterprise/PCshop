package products;

import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;

public class Ram extends Product{

	
	//Fields:
	private String type;
	private int size;
	
	
	//Constructor:
	public Ram (String producer, String model, double price, String info, int quantity, Type type, String ramType, int size) throws InvalidPriceException, InvalidQuantityException {
		super(producer, model, price, info, quantity, type);
		this.type = ramType;
		this.size = size;
	}	
		
	
	//Methods:
	@Override
	public void viewProduct() {
		super.viewProduct();
		System.out.println("Type: " + this.type);
		System.out.println("Size: " + this.size + " GB");
		
	}
	
	
	
}
