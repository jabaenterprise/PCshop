package model;

public class Monitor extends Product {

	
	private static final String TYPE = "monitor";
	private double size;
	private int refreshRate;
	private String matrixType;
	
	
	public Monitor(String producer, String model, double price, String info, int quantity, String imageUrl, double size, int refreshRate, String matrixType) {
		super(producer, model, price, info, quantity, imageUrl);
		super.setType(Monitor.TYPE);
		this.size = size;
		this.refreshRate = refreshRate;
		this.matrixType = matrixType;
	}

		
	public double getSize() {
		return size;
	}
	public int getRefreshRate() {
		return refreshRate;
	}
	public String getMatrixType() {
		return matrixType;
	}

	
	public void setSize(double size) {
		this.size = size;
	}
	public void setRefreshRate(int refreshRate) {
		this.refreshRate = refreshRate;
	}
	public void setMatrixType(String matrixType) {
		this.matrixType = matrixType;
	}
	
	
	
}
