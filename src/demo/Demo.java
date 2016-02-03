package demo;

import client.Client;
import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;
import products.Cpu;
import products.Product;
import products.Ram;
import products.Type;
import shop.Shop;

public class Demo {

	public static void main(String[] args) throws InvalidPriceException, InvalidQuantityException {

		Shop emag =  Shop.getShop();
		Product p = new Cpu("", "",10,  "",5, Type.CPU, 5, 5, "");
		Product p2 = new Ram("", "",5,  "",5, Type.RAM, "", 5);
		emag.addProductToShop(p);
		emag.addProductToShop(p2);
		Client x = new Client("", "", "", "","", "","");
		x.addProductToCart(p);
		x.setQuantityOfAProductInCart(p, 2);
		x.buyProductsInCart();
		x.showTotalPriceOfProductsInCart();
		x.addProductToCart(p2);
		x.buyProductsInCart();
		x.showTotalPriceOfProductsInCart();
	}

}
