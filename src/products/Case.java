package products;

import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;

public class Case extends Product{
	
	//Fields:
	private String form;
	private String size;
	
	
	//Constructor:
	public Case(String producer, String model, double price, String info, int quantity, Type type, String form, String size) throws InvalidPriceException, InvalidQuantityException {
		super(producer, model, price, info, quantity, type);
		this.form = form;
		this.size = size;
	}	
	
	
	//Methods:
	@Override
	public void viewProduct() {
		super.viewProduct();
		System.out.println("Type: "+ this.form);
		System.out.println("Size: "+ this.size);
		
	}


}