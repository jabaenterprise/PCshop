package database.daos;

import client.Client;
import products.Product;

public interface IShopDAO {
		
	
	
	
	enum DataSource {DB,JSON};
	void addProductToShop(Product p);
	void addNewClient(Client client);
	
	static IShopDAO getDAO(DataSource dataSource){
		switch (dataSource) {
		case DB:
			return new DBShopDAO();
		case JSON:
			//return new JSONShopDAO();
		break;	
		}
		throw new IllegalArgumentException();
	}
}
