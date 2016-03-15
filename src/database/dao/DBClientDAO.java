package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import database.DBManager;
import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;
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
	
	
	private DBClientDAO() {
		
	}
		
	
	
	public static synchronized DBClientDAO getDBClientDAO() {
		if (DBClientDAO.instance == null) {
			DBClientDAO.instance = new DBClientDAO();
		}
		return DBClientDAO.instance;
	}


	
	@Override
	public Client getClient(String eMailAddress) {
		HashMap<Product, Integer> cart = new HashMap<Product, Integer>();
		Client client = null;
		Statement st = null;
		Statement st2= null;

		try {
			Connection conn = DBManager.getDBManager().getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT client_id, first_name, family_name, email, password, phone, money, address FROM ecommerce.clients WHERE email='" + eMailAddress + "';");
			
			rs.next();
			int userId = rs.getInt("client_id");
			String firstName = rs.getString("first_name");
			String familyName = rs.getString("family_name");
			String eMail = rs.getString("email");
			String password = rs.getString("password");
			String phoneNumber = rs.getString("phone");
			double money = rs.getDouble("money");
		
			String address = rs.getString("address");
			
			client = new Client(firstName, familyName, eMail, password, address, phoneNumber);
			client.setId(userId);
			
			client.setMoney(money);;
			System.out.println("resutl");
			
			st2 = conn.createStatement();
			
			ResultSet rs2 = st2.executeQuery("SELECT C.product_id, P.producer, P.model, P.price, P.info, P.img_url, C.quantity_in_cart, T.type FROM ecommerce.products_in_carts C JOIN ecommerce.products P ON (C.product_id=P.product_id) JOIN ecommerce.product_types T ON (T.type_id=P.type_id) WHERE client_id="+client.getId()+";");/*" + userId + ";");*/
			
			System.out.println("resutl"+rs2);
			Product pr = null;
			int quantity = 0;
			while(rs2.next()) {
				
				int productId = rs2.getInt("product_id");
				String producer = rs2.getString("producer");
				String model = rs2.getString("model");
				double price = rs2.getDouble("price");
				System.out.println(price);
				quantity = rs2.getInt("quantity_in_cart");
				String info = rs2.getString("info");
				String img = rs2.getString("img_url");
				String type = rs2.getString("type");
				ResultSet rs3 = null;
				switch (type) {
				
				case "case": 
					System.out.println("1");
					rs3 = st.executeQuery("SELECT case_form, case_size FROM ecommerce.cases WHERE product_id=" + productId + ";");
					rs3.next();
					String form = rs3.getString("case_form");
					String size = rs3.getString("case_size");
					pr = new Case(producer, model, price, info, quantity, img, form, size);
					pr.setId(productId);
					break;
				case "cpu": 
					rs3 = st.executeQuery("SELECT number_of_cores, clock_speed, socket FROM ecommerce.cpus WHERE product_id=" + productId + ";");
					rs3.next();
					int numberOfCores = rs3.getInt("number_of_cores");
					double clockSpeed = rs3.getDouble("clock_speed");
					String socket = rs3.getString("socket");
					pr = new Cpu(producer, model, price, info, quantity, img, numberOfCores, clockSpeed, socket);
					pr.setId(productId);
					break;
				case "gpu": 
					rs3 = st.executeQuery("SELECT memory_size, output_interface, max_resolution FROM ecommerce.gpus WHERE product_id=" + productId + ";");
					rs3.next();
					int memorySize = rs3.getInt("memory_size");
					String maxResolution = rs3.getString("max_resolution");
					String outputInterface = rs3.getString("output_interface");
					pr = new Gpu(producer, model, price, info, quantity, img, memorySize, maxResolution, outputInterface);
					pr.setId(productId);
					break;
				case "hd": 
					rs3 = st.executeQuery("SELECT hd_type, drive_size, drive_capacity FROM ecommerce.hard_drives WHERE product_id=" + productId + ";");
					rs3.next();
					String hdType = rs3.getString("hd_type");
					int driveSize = rs3.getInt("drive_size");
					int driveCapacity = rs3.getInt("drive_size");
					pr = new HardDrive(producer, model, price, info, quantity, img, hdType, driveSize, driveCapacity);
					pr.setId(productId);
					break;
				case "mon": 
					rs3 = st.executeQuery("SELECT screen_size, refresh_rate, matrix_type FROM ecommerce.monitors WHERE product_id=" + productId + ";");
					rs3.next();
					double screenSize = rs3.getDouble("screen_size");
					int refreshRate = rs3.getInt("refresh_rate");
					String matrixType = rs3.getString("matrix_type");
					pr = new Monitor(producer, model, price, info, quantity, img, screenSize, refreshRate, matrixType);
					pr.setId(productId);
					break;
				case "mb": 
					rs3 = st.executeQuery("SELECT chipset, ram_slots, socket_type FROM ecommerce.mother_boards WHERE product_id=" + productId + ";");
					rs3.next();
					String chipset = rs3.getString("case_form");
					int ramSlots = rs3.getInt("ram_slots");
					String socketType = rs3.getString("socket_type");
					pr = new Motherboard(producer, model, price, info, quantity, img, chipset, ramSlots, socketType);
					pr.setId(productId);
					break;
				case "ram": 
					rs3 = st.executeQuery("SELECT ram_type, ram_size FROM ecommerce.rams WHERE product_id=" + productId + ";");
					rs3.next();
					String ramType = rs3.getString("ram_type");
					int ramSize = rs3.getInt("ram_size");
					pr = new Ram(producer, model, price, info, quantity, img, ramType, ramSize);
					pr.setId(productId);
					break;
				}
							System.out.println(pr.getId());
				cart.put(pr, quantity);
				System.out.println(cart.toString());
				rs3.close();
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				st.close();
				st2.close();
			} catch (SQLException e) {
				System.out.println("QSL: "+e.getMessage());
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
			Connection conn = DBManager.getDBManager().getConnection();
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
	public void updateClientInfo(Client client) {
		String updateInfo = "UPDATE ecommerce.clients SET first_name = ?, family_name = ? , phone = ?, address = ? "
				+ "WHERE client_id =  ?";
		
		try(PreparedStatement st = DBManager.getDBManager().getConnection().prepareStatement(updateInfo)){
			st.setString(1, client.getFirstName());
			st.setString(2, client.getFamilyName());			
			st.setString(3, client.getPhone() );
			st.setString(4,	client.getAddress());
			st.setInt(5, client.getId());
			System.out.println(st.toString());
			st.execute();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
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
	public void addProductToCart(Product p, Client c, int quantity) {
		int clientId = c.getId();
		int productId = p.getId();
		String query = "INSERT INTO ecommerce.products_in_carts (client_id, product_id, quantity_in_cart) VALUES (?,?,?);";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			prst.setInt(1, clientId);
			prst.setInt(2, productId);
			prst.setInt(3, quantity);
			prst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void removeProductFromCart(Product p, Client c) {
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
		System.out.println(productId);
		String query = "UPDATE ecommerce.products_in_carts SET quantity_in_cart= ? WHERE client_id=" + clientId + " AND product_id=" + productId + ";";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			prst.setInt(1, newQuantity);
			prst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void changePassword(String newPassword, Client client) {
		String updateInfo = "UPDATE ecommerce.clients SET  password = ? WHERE client_id =  ?";
		
		try(PreparedStatement st = DBManager.getDBManager().getConnection().prepareStatement(updateInfo)){
	
			st.setString(1, client.getPassword());
			st.setInt(2, client.getId());
			System.out.println(st.toString());
			st.execute();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


	
}
