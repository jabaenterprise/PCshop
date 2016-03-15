package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


import database.DBManager;
import model.Case;
import model.Client;
import model.Cpu;
import model.Gpu;
import model.HardDrive;
import model.Monitor;
import model.Motherboard;
import model.Product;
import model.Ram;

public class DBClientDAO implements IClientDAO {

	
	private static DBClientDAO instance;
	private static final int ADD_TO_CART_QUANTITY = 1;
	
	private DBClientDAO() {
		
	}
		
	
	
	public static synchronized DBClientDAO getDBClientDAO() {
		if (DBClientDAO.instance == null) {
			DBClientDAO.instance = new DBClientDAO();
		}
		return DBClientDAO.instance;
	}


	
	
	@Override
	public void addClient(Client newClient) {
		String query = "INSERT INTO ecommerce.clients (first_name, family_name, email, password, address, phone) VALUES (?,?,?,?,?,?);";
		
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			prst.setString(1, newClient.getFirstName());
			prst.setString(2, newClient.getFamilyName());
			prst.setString(3, newClient.geteMail());
			prst.setString(4, newClient.getPassword());
			prst.setString(5, newClient.getAddress());
			prst.setString(6, newClient.getPhone());
			prst.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Client getClient(String eMail) {
		HashMap<Product, Integer> cart = new HashMap<Product, Integer>();
		Client client = null;
		
		Statement st1 = null;
		Statement st2 = null;
		Statement st3 = null;
		try {
			Connection conn = DBManager.getDBManager().getConnection();
			
			st1 = conn.createStatement();
			String query1 = "SELECT client_id, first_name, family_name, email, password, address, phone, money FROM ecommerce.clients WHERE email='" + eMail + "';";
			ResultSet rs1 = st1.executeQuery(query1);
			rs1.next();
			
			int id = rs1.getInt("client_id");
			String firstName = rs1.getString("first_name");
			String familyName = rs1.getString("family_name");
			String theEMail = rs1.getString("email");
			String password = rs1.getString("password");
			String address = rs1.getString("address");
			String phone = rs1.getString("phone");
			double money = rs1.getDouble("money");
						
			client = new Client(firstName, familyName, theEMail, password, address, phone);
			client.setId(id);
			client.setMoney(money);
			
			
			st2 = conn.createStatement();
			String query2 = "SELECT products.product_id, producer, model, price, info, quantity_in_cart, type, img_url FROM ecommerce.products_in_carts JOIN ecommerce.products ON (ecommerce.products_in_carts.product_id=ecommerce.products.product_id) JOIN ecommerce.product_types ON (ecommerce.products.type_id=ecommerce.product_types.type_id) WHERE client_id=" + id + ";";
			ResultSet rs2 = st2.executeQuery(query2);
			
			Product pr = null;
			int quantityInCart = 0;
			
			while(rs2.next()) {
				
				int productId = rs2.getInt("product_id");
				String producer = rs2.getString("producer");
				String model = rs2.getString("model");
				double price = rs2.getDouble("price");
				quantityInCart = rs2.getInt("quantity_in_cart");
				String info = rs2.getString("info");		
				String type = rs2.getString("type");
				String imgUrl = rs2.getString("img_url");
				
				st3 = conn.createStatement();
				ResultSet rs3 = null;
				switch (type) {
				case "case": 
					rs3 = st3.executeQuery("SELECT case_form, case_size FROM ecommerce.cases WHERE cases.product_id=" + productId + ";");
					rs3.next();
					String form = rs3.getString("case_form");
					String size = rs3.getString("case_size");
					pr = new Case(producer, model, price, info, quantityInCart, imgUrl, form, size);
					pr.setId(productId);
					break;
					
				case "cpu": 
					rs3 = st3.executeQuery("SELECT number_of_cores, clock_speed, cpu_socket FROM ecommerce.cpus WHERE cpus.product_id=" + productId + ";");
					rs3.next();
					int numberOfCores = rs3.getInt("number_of_cores");
					double clockSpeed = rs3.getDouble("clock_speed");
					String socket = rs3.getString("cpu_socket");
					pr = new Cpu(producer, model, price, info, quantityInCart, imgUrl, numberOfCores, clockSpeed, socket);
					pr.setId(productId);
					break;
					
				case "gpu": 
					rs3 = st3.executeQuery("SELECT memory_size, output_interface, max_resolution FROM ecommerce.gpus WHERE gpus.product_id=" + productId + ";");
					rs3.next();
					int memorySize = rs3.getInt("memory_size");
					String maxResolution = rs3.getString("max_resolution");
					String outputInterface = rs3.getString("output_interface");
					pr = new Gpu(producer, model, price, info, quantityInCart, imgUrl, memorySize, maxResolution, outputInterface);
					pr.setId(productId);
					break;
					
				case "hd": 
					rs3 = st3.executeQuery("SELECT hd_type, drive_size, drive_capacity FROM ecommerce.hard_drives WHERE hard_drives.product_id=" + productId + ";");
					rs3.next();
					String hdType = rs3.getString("type");
					double driveSize = rs3.getDouble("size");
					int driveCapacity = rs3.getInt("drive_size");
					pr = new HardDrive(producer, model, price, info, quantityInCart, imgUrl, hdType, driveSize, driveCapacity);
					pr.setId(productId);
					break;
					
				case "monitor": 
					rs3 = st3.executeQuery("SELECT size, refresh_rate, matrix_type FROM ecommerce.monitors WHERE monitors.product_id=" + productId + ";");
					rs3.next();
					double screenSize = rs3.getDouble("size");
					int refreshRate = rs3.getInt("refresh_rate");
					String matrixType = rs3.getString("matrix_type");
					pr = new Monitor(producer, model, price, info, quantityInCart, imgUrl, screenSize, refreshRate, matrixType);
					pr.setId(productId);
					break;
					
				case "mb": 
					rs3 = st3.executeQuery("SELECT chipset, ram_slots, socket FROM ecommerce.mother_boards WHERE mother_boards.product_id=" + productId + ";");
					rs3.next();
					String chipset = rs3.getString("chipset");
					int ramSlots = rs3.getInt("ram_slots");
					String socketType = rs3.getString("socket");
					pr = new Motherboard(producer, model, price, info, quantityInCart, imgUrl, chipset, ramSlots, socketType);
					pr.setId(productId);
					break;
					
				case "ram": 
					rs3 = st3.executeQuery("SELECT type, size FROM ecommerce.rams WHERE rams.product_id=" + productId + ";");
					rs3.next();
					String ramType = rs3.getString("type");
					int ramSize = rs3.getInt("size");
					pr = new Ram(producer, model, price, info, quantityInCart, imgUrl, ramType, ramSize);
					pr.setId(productId);
					break;
				}
							
				cart.put(pr, quantityInCart);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				st1.close();
				st2.close();
				if (st3 != null) {
					st3.close();
				}
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		client.setCart(cart);
		
		return client;
	}
		
	@Override
	public HashMap<String, String> getEMailsAndPasswords() {
		HashMap<String, String> eMailsAndPasswords = new HashMap<String, String>();
		Statement st = null;
		
		try {
			DBManager dbm = DBManager.getDBManager();
			Connection conn = dbm.getConnection();
			st = conn.createStatement();
			String query = "SELECT email, password FROM ecommerce.clients;";
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				String eMail = rs.getString("email");
				String password = rs.getString("password");
				eMailsAndPasswords.put(eMail, password);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
				
		return eMailsAndPasswords;
	}



	
	
	@Override
	public void addProductToCart(Product p, Client c) {
		int clientId = c.getId();
		int productId = p.getId();
		String query = "INSERT INTO ecommerce.products_in_carts (client_id, product_id, quantity_in_cart) VALUES (?,?,?);";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			prst.setInt(1, clientId);
			prst.setInt(2, productId);
			prst.setInt(3, DBClientDAO.ADD_TO_CART_QUANTITY);
			prst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}


	@Override
	public void removeProductFromCart(Product p, Client c) { //Every time this method is used in the servlet getClient METHOD SHOULD BE CALLED AND SET IN THE SESSION TO UPDATE THE CHANGES
		int clientId = c.getId();
		int productId = p.getId();
		String query = "DELETE FROM ecommerce.products_in_carts WHERE client_id=" + clientId + " AND product_id=" + productId + ";";
		try (Statement st = DBManager.getDBManager().getConnection().createStatement()){
			st.executeUpdate(query);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}


	@Override
	public void setQuantityOfProductInCart(Product p, int newQuantity, Client c) {
		int clientId = c.getId();
		int productId = p.getId();
		String query = "UPDATE ecommerce.products_in_carts SET quantity_in_cart= ? WHERE client_id=" + clientId + " AND product_id=" + productId + ";";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			prst.setInt(1, newQuantity);
			prst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}


	@Override
	public void changePassword(String newPassword, Client c) {
		int id = c.getId();
		String query = "UPDATE ecommerce.clients SET password= ? WHERE client_id=" + id + ";";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			prst.setString(1, newPassword);
			prst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}


	@Override
	public void changeAddress(String newAddress, Client c) {
		int id = c.getId();
		String query = "UPDATE ecommerce.clients SET address= ? WHERE client_id=" + id + ";";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			prst.setString(1, newAddress);
			prst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}


	@Override
	public void changePhone(String newPhone, Client c) {
		int id = c.getId();
		String query = "UPDATE ecommerce.clients SET phone= ? WHERE client_id=" + id + ";";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			prst.setString(1, newPhone);
			prst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
		

	
	
}
