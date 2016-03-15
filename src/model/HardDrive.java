package model;

import java.util.HashMap;
import java.util.Map;

public class HardDrive extends Product {

	
	private static final String TYPE = "hd";
	private String hdType;
	private double size;
	private int capacity;
	
	
	public HardDrive(String producer, String model, double price, String info, int quantity, String imageUrl, String hdType, double size, int capacity) {
		super(producer, model, price, info, quantity, imageUrl);
		super.setType(HardDrive.TYPE);
		this.hdType = hdType;
		this.size = size;
		this.capacity = capacity;
	}

	
	public String gethdType() {
		return this.hdType;
	}
	public double getSize() {
		return size;
	}
	public int getCapacity() {
		return capacity;
	}

	
	public void sethdType(String hdType) {
		this.hdType = hdType;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	@Override
	public Map<String, String> getAdditionalInfo() {
		Map<String, String> info = new HashMap<>();
		info.put("Memory Size: ",String.valueOf(this.capacity).concat("GB"));
		info.put("Size: ",String.valueOf(this.size).concat("\""));
		info.put("Type: ", this.hdType);
		return info;
	}
	
	
}
