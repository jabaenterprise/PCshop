package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


	@Override
	public Map<String, String> getAdditionalInfo() {
		Map<String, String> info = new HashMap<>();
		info.put("Number of Cores: ",String.valueOf(this.numberOfCores));
		info.put("Clock speed : ",String.valueOf(this.clockSpeed));
		info.put("Socket : ", this.socket);
		return info;
	}
	
	
	
}
