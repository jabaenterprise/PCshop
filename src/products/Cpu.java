package products;

import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;

public class Cpu extends Product {
	
	//Fields:
	private int numberOfCores;
	private double clockSpeed;
	private String socket;
	
	//Constructor:
	public Cpu(String producer, String model, double price, String info, int quantity, String img ,int numberOfCores, double clockSpeed, String socket) throws InvalidPriceException, InvalidQuantityException {
		super(producer, model, price, info, quantity, img, Type.CPU);
		this.numberOfCores = numberOfCores;
		this.clockSpeed = clockSpeed;
		this.socket = socket;
	}	
	
	
	//Methods:
	@Override
	public void viewProduct() {
		super.viewProduct();
		System.out.println("Number Of Cores: " + numberOfCores);
		System.out.println("Clock Speed: "+clockSpeed+"GHz");
		System.out.println("socket: " + socket);
	}


	public int getNumberOfCores() {
		return numberOfCores;
	}


	public double getClockSpeed() {
		return clockSpeed;
	}


	public String getSocket() {
		return socket;
	}
	

	

}
