package products;

import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;

public class Monitor extends Product{
	
	//Fields:
	private double size;
	private int refreshRate;
	private String matrixType;
	
	
	//Constructor:
	public Monitor(String producer, String model, double price, String info, int quantity, Type type, double size, int refreshRate, String matrixType) throws InvalidPriceException, InvalidQuantityException {
		super(producer, model, price, info, quantity, type);
		this.size = size;
		this.refreshRate = refreshRate;
		this.matrixType = matrixType;
	}	
		
	//Methods:
	@Override
	public void viewProduct() {
		super.viewProduct();
		System.out.println("Size: " + this.size + "in");
		System.out.println("Matrix type: " + this.matrixType);
		System.out.println("Refresh Rate: " + this.refreshRate + "ms");
	}

}
