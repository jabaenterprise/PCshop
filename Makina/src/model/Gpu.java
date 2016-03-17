package model;

import java.util.HashMap;
import java.util.Map;

public class Gpu extends Product {

	
	private static final String TYPE = "gpu";
	private int memorySize;
	private String maxResolution;
	private String outputInterface;
	
	
	public Gpu(String producer, String model, double price, String info, int quantity, String imageUrl, int memorySize, String maxResolution, String outputInterface) {
		super(producer, model, price, info, quantity, imageUrl);
		super.setType(Gpu.TYPE);
		this.memorySize = memorySize;
		this.maxResolution = maxResolution;
		this.outputInterface = outputInterface;
	}
	
	
	public int getMemorySize() {
		return memorySize;
	}
	public String getMaxResolution() {
		return maxResolution;
	}
	public String getOutputInterface() {
		return outputInterface;
	}

	
	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
	}
	public void setMaxResolution(String maxResolution) {
		this.maxResolution = maxResolution;
	}
	public void setOutputInterface(String outputInterface) {
		this.outputInterface = outputInterface;
	}


	@Override
	public Map<String, String> getAdditionalInfo() {
		Map<String, String> info = new HashMap<>();
		info.put("Memory Size: ",String.valueOf(this.memorySize));
		info.put("Max Resolution: ",this.maxResolution);
		info.put("Output Interface : ", this.outputInterface);
		return info;
	}
		
	
}
