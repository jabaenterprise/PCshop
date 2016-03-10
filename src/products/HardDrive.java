package products;

import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;

public class HardDrive extends Product{
	
	//Fields:
	private String type;
	private int size;
	private int capacity;


	//Constructor:
	public HardDrive(String producer, String model, double price, String info, int quantity, String img,String hdType, int size, int capacity) throws InvalidPriceException, InvalidQuantityException {
		super(producer, model, price, info, quantity, img, Type.HD);
		this.type = hdType;
		this.size = size;
		this.capacity = capacity;
					
	}	
	
	//Methods:
	@Override
	public void viewProduct() {
		super.viewProduct();
		System.out.println("Size:" + this.size);
		System.out.println("Type: " + this.type);
		System.out.println("Capacity: " + this.capacity + " GB");
		
	}

	public String getHdType() {
		return type;
	}

	public int getSize() {
		return size;
	}

	public int getCapacity() {
		return capacity;
	}
	

}
