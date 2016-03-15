package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Motherboard extends Product {

	
	private static final String TYPE = "mb";
	private String chipset;
	private int ramSlots;
	private String socket;
	
		
	public Motherboard(String producer, String model, double price, String info, int quantity, String imageUrl, String chipset, int ramSlots2, String socket) {
		super(producer, model, price, info, quantity, imageUrl);
		super.setType(Motherboard.TYPE);
		this.chipset = chipset;
		this.ramSlots = ramSlots2;
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


	@Override
	public Map<String, String> getAdditionalInfo() {
		Map<String, String> info = new HashMap<>();
		info.put("Chip Set: ",this.chipset);
		info.put("Ram Slots: ",String.valueOf(this.ramSlots));
		info.put("Socket: ", this.socket);
		return info;
	}
	
	
	
}
