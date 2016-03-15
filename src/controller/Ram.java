package controller;

import java.util.HashMap;
import java.util.Map;

public class Ram extends Product {

	
	private static final String TYPE = "ram";
	private String ramType;
	private int size;
	
	
	public Ram(String producer, String model, double price, String info, int quantity, String imageUrl, String ramType, int size) {
		super(producer, model, price, info, quantity, imageUrl);
		super.setType(Ram.TYPE);
		this.ramType = ramType;
		this.size = size;
		
	}


	public String getRamType() {
		return this.ramType;
	}
	public int getSize() {
		return size;
	}


	public void setRamType(String ramType) {
		this.ramType = ramType;
	}
	public void setSize(int size) {
		this.size = size;
	}


	@Override
	public Map<String, String> getAdditionalInfo() {
		Map<String, String> info = new HashMap<>();
		
		info.put("Size: ",String.valueOf(this.size).concat("GB"));
		info.put("Type: ", this.ramType);
		return info;
	}
	
	
}
