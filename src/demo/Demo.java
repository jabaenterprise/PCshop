package demo;

import client.Client;
import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;

import exceptions.LoginException;
import exceptions.NotEnoughMoneyException;
import exceptions.SuchUserAlreadyExistsException;
import products.Cpu;
import products.Product;
import products.Ram;

import shop.Shop;

public class Demo {

	public static void main(String[] args) throws InvalidPriceException, InvalidQuantityException {

		Shop emag =  Shop.getShop();
		Product p = new Cpu("", "",10,  "",5,  5, 5, "");
		Product p2 = new Ram("", "",5,  "",5,  "", 5);
		emag.addProductToShop(p);
		emag.addProductToShop(p);
		emag.addProductToShop(p2);
		Client 	x =null;
		try {
			x= emag.createNewClient("name", "lastname", "aaa@aaa.com", "xxxx","city", "address","PO BOX");
		} catch (SuchUserAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		x.setQuantityOfAProductInCart(p2, 10);
		if(x!=null){
			x.addProductToCart(p);
			
			x.addProductToCart(p);
			try {
				emag.isCorrectLogin("aaa@aaa.com", "xxxx");
			} catch (LoginException e1) {
				System.out.println(e1.getMessage());
				
			}
			x.setQuantityOfAProductInCart(p, 2);
			try {
				x.buyProductsInCart();
			} catch (NotEnoughMoneyException e) {
				System.out.println(e.getMessage());
				x.addMoneyToAccount(500);
			}
			x.showTotalPriceOfProductsInCart();
		
			x.addProductToCart(p2);
			
//			x.buyProductsInCart();
			x.showTotalPriceOfProductsInCart();
			System.out.println(x.money);;
		}
		
	}

}
