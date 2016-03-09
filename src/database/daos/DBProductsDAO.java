package database.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DBManager;
import products.Product;

public class DBProductsDAO {
	private static final String db = "pcshop1";
	void addProduct(Product product){
		
	}
	//sort clause must be added
	//select everything form products joined with product type + 
	public static List<String>  getProducts(String categoryType){
		String query = "SELECT p.img_url as url "
				+ "FROM "+db+".products p"
				+ "JOIN "+db+".product_types t ON p.type_id=t.type_id"
				+ "WHERE t.type_name LIKE " + categoryType; 
		if(categoryType.equals("none")){
			query = "SELECT p.img_url as url "
					+ "FROM "+db+".products p"
					+ "JOIN "+db+".product_types t ON p.type_id=t.type_id"; 
		}
		List<String> products = new ArrayList<String>();
		try(Statement st = DBManager.getDBManager().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				products.add(rs.getString("url"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	
	//method for productInfo.jsp
	//all info for specific product
}
