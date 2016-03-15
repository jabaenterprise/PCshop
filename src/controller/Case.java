package controller;

import java.util.HashMap;
import java.util.Map;

public class Case extends Product {

	
	private static final String TYPE = "case";
	private String form;
	private String size;
		
	
	public Case(String producer, String model, double price, String info, int quantity, String imageUrl, String form, String size) {
		super(producer, model, price, info, quantity, imageUrl);
		super.setType(Case.TYPE);
		this.form = form;
		this.size = size;
	}
	
	
	public String getForm() {
		return form;
	}
	public String getSize() {
		return size;
	}

	
	public void setForm(String form) {
		this.form = form;
	}
	public void setSize(String size) {
		this.size = size;
	}


	@Override
	public Map<String, String> getAdditionalInfo() {
		Map<String, String> info = new HashMap<>();
		info.put("Form: ",this.form);
		info.put("Size: ",this.size);
		return info;
	}


	

		
	
}
