package controller;

import java.util.List;
import java.util.Map;

public abstract class Product {

	
	private int id;
	private String producer;
	private String model;
	private String type;
	private double price;
	private String info;
	private int quantity;
	private String imageUrl;
	
		
	public Product(String producer, String model, double price, String info, int quantity, String imageUrl) {
		this.producer = producer;
		this.model = model;
		this.price = price;
		this.info = info;
		this.quantity = quantity;
		this.imageUrl = imageUrl;
	}
	

	public int getId() {
		return id;
	}
	public String getProducer() {
		return producer;
	}
	public String getModel() {
		return model;
	}
	public String getType() {
		return type;
	}
	public double getPrice() {
		return price;
	}
	public String getInfo() {
		return info;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	
	//Products differ by their model
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}


	public abstract Map<String,String> getAdditionalInfo();
		
	
}
