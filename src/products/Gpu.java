package products;

import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;

public class Gpu extends Product{
	
	//Fields:
	private int memorySize;
	private String maxResolution;
	private String outputInterface;
	
	
	//Constructor:
	public Gpu(String producer, String model, double price, String info, int quantity, Type type, int memorySize, String maxResolution, String outputInterface) throws InvalidPriceException, InvalidQuantityException {
		super(producer, model, price, info, quantity, type);
		this.memorySize = memorySize;
		this.maxResolution = maxResolution;
		this.outputInterface = outputInterface;
				
	}	
	
	//Methods:
	@Override
	public void viewProduct() {
		super.viewProduct();
		System.out.println("Memory size: " + this.memorySize + "GB");
		System.out.println("Max Resolution: " + this.maxResolution);
		System.out.println("Output Interface: " + this.outputInterface);
	}
	
	
	
	
}
