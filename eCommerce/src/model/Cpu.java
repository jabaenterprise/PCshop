package model;

public class Cpu extends Product {

	
	private static final String TYPE = "cpu";
	private int numberOfCores;
	private double clockSpeed;
	private String socket;
	
	
	public Cpu(String producer, String model, double price, String info, int quantity, String imageUrl, int numberOfCores, double clockSpeed, String socket) {
		super(producer, model, price, info, quantity, imageUrl);
		super.setType(Cpu.TYPE);
		this.numberOfCores = numberOfCores;
		this.clockSpeed = clockSpeed;
		this.socket = socket;
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


	public void setNumberOfCores(int numberOfCores) {
		this.numberOfCores = numberOfCores;
	}
	public void setClockSpeed(double clockSpeed) {
		this.clockSpeed = clockSpeed;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	
	
}
