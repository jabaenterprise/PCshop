package database.dao;

import java.util.HashMap;


import model.Client;
import model.Product;

public interface IClientDAO {

	
	public void addClient(Client newClient);
	
	public Client getClient(String eMail);
	
	public HashMap<String, String> getEMailsAndPasswords(); 
			
	public void addProductToCart(Product p, Client c); //Adds 1 item
	
	public void removeProductFromCart(Product p, Client c); //Removes the whole quantity of this product from cart
	
	public void setQuantityOfProductInCart(Product p, int newQuantity, Client c); //If quantity == 0 it removes the product from the cart
	
	public void changePassword(String newPassword, Client c);
	
	public void changeAddress(String newAddress, Client c);
	
	public void changePhone(String newPhone, Client c);
	
	
}
