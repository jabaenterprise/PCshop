package products;

import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;

public class MotherBoard extends Product{
	
	//Fields:
	private String chipset;
	private String ramSlots;
	private String socketType;
	
		
	//Constructor:
	public MotherBoard(String producer, String model, double price, String info, int quantity, String img,String chipset, String ramSlots, String socketType) throws InvalidPriceException, InvalidQuantityException {
		super(producer, model, price, info, quantity, img,Type.MB);
		this.chipset = chipset;
		this.ramSlots = ramSlots;
		this.socketType = socketType;
	}	
		
	//Methods:	
	@Override
	public void viewProduct() {
		super.viewProduct();
		System.out.println("Chipset: " + this.chipset);
		System.out.println("Ram Slots: " + this.ramSlots);
		System.out.println("Socket Type: " + this.socketType);
	}

	public String getChipset() {
		return chipset;
	}

	public String getRamSlots() {
		return ramSlots;
	}

	public String getSocketType() {
		return socketType;
	}

	
	
}
