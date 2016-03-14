package model;

public class Motherboard extends Product {

	
	private static final String TYPE = "mb";
	private String chipset;
	private int ramSlots;
	private String socket;
	
		
	public Motherboard(String producer, String model, double price, String info, int quantity, String imageUrl, String chipset, int ramSlots, String socket) {
		super(producer, model, price, info, quantity, imageUrl);
		super.setType(Motherboard.TYPE);
		this.chipset = chipset;
		this.ramSlots = ramSlots;
		this.socket = socket;
		
	}
	
	
	public String getChipset() {
		return chipset;
	}
	public int getRamSlots() {
		return ramSlots;
	}
	public String getSocket() {
		return socket;
	}
	
	
	public void setChipset(String chipset) {
		this.chipset = chipset;
	}
	public void setRamSlots(int ramSlots) {
		this.ramSlots = ramSlots;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	
	
}
