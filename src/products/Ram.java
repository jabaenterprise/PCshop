package products;

import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;

public class Ram extends Product{

	
	//Fields:
	private String ramType;
	private int size;
	
	
	//Constructor:
	public Ram (String producer, String model, double price, String info, int quantity, String img,String ramType, int size) throws InvalidPriceException, InvalidQuantityException {
		super(producer, model, price, info, quantity, img,Type.RAM);
		this.ramType = ramType;
		this.size = size;
	}	
		
	
	//Methods:
	@Override
	public void viewProduct() {
		super.viewProduct();
		System.out.println("Type: " + this.ramType);
		System.out.println("Size: " + this.size + " GB");
		
	}


	public String getRamType() {
		return ramType;
	}


	public int getSize() {
		return size;
	}
	
	
	
}
