package database.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import client.Client;
import database.DBManager;
import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;
import products.Case;
import products.Cpu;
import products.Gpu;
import products.HardDrive;
import products.Monitor;
import products.MotherBoard;
import products.Product;
import products.Ram;



public class ClientDAO implements IClientDAO {
	
	@Override
	public Client getClient(String eMailAddress) {
		HashMap<Product, Integer> cart = new HashMap<Product, Integer>();
		Client client = null;
		Statement st = null;
		try {
			Connection conn = DBManager.getDBManager().getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT user_id, first_name, family_name, email_address, pass_word, phone_number, money, city, address FROM pcshop.clients JOIN pcshop.adresses ON (pcshop.clients.address_id=pcshop.adresses.address_id) WHERE email_address='" + eMailAddress + "';");
			rs.next();
			int userId = rs.getInt("user_id");
			String firstName = rs.getString("first_name");
			String familyName = rs.getString("family_name");
			String eMail = rs.getString("email_address");
			String password = rs.getString("pass_word");
			String phoneNumber = rs.getString("phone_number");
			double money = rs.getDouble("money");
			String city = rs.getString("city");
			String address = rs.getString("address");
			
			client = new Client(firstName, familyName, eMail, password, city, address);
			client.setUserId(userId);
			client.setPhoneNumber(phoneNumber);
			client.addMoneyToAccount(money);
			
			ResultSet rs2 = st.executeQuery("SELECT product_id, producer_name, model_name, price, product_info, quantity_in_cart, type FROM pcshop.products_in_carts JOIN pcshop.products ON (pcshop.products_in_cart.product_id=pcshop.products.product_id) JOIN pcshop.product_types ON (pcshop.products.type_id=pcshop.product_types.type_id) WHERE user_id=" + userId + ";");
			Product pr = null;
			int quantity = 0;
			while(rs2.next()) {
				int productId = rs2.getInt("product_id");
				String producer = rs2.getString("producer_name");
				String model = rs2.getString("model_name");
				double price = rs2.getDouble("price");
				quantity = rs2.getInt("quantity_in_cart");
				String info = rs2.getString("product_info");		
				String type = rs2.getString("type");
				ResultSet rs3 = null;
				switch (type) {
				case "case": 
					rs3 = st.executeQuery("SELECT case_form, case_size FROM pcshop.cases WHERE product_id=" + productId + ";");
					String form = rs3.getString("case_form");
					String size = rs3.getString("case_size");
					pr = new Case(producer, model, price, info, quantity, "img",form, size);
					break;
				case "cpu": 
					rs3 = st.executeQuery("SELECT number_of_cores, clock_speed, socket FROM pcshop.cpus WHERE product_id=" + productId + ";");
					int numberOfCores = rs3.getInt("number_of_cores");
					double clockSpeed = rs3.getDouble("clock_speed");
					String socket = rs3.getString("socket");
					pr = new Cpu(producer, model, price, info, quantity, "img",numberOfCores, clockSpeed, socket);
					break;
				case "gpu": 
					rs3 = st.executeQuery("SELECT memory_size, output_interface, max_resolution FROM pcshop.gpus WHERE product_id=" + productId + ";");
					int memorySize = rs3.getInt("memory_size");
					String maxResolution = rs3.getString("max_resolution");
					String outputInterface = rs3.getString("output_interface");
					pr = new Gpu(producer, model, price, info, quantity,"img", memorySize, maxResolution, outputInterface);
					break;
				case "hd": 
					rs3 = st.executeQuery("SELECT hd_type, drive_size, drive_capacity FROM pcshop.hard_drives WHERE product_id=" + productId + ";");
					String hdType = rs3.getString("hd_type");
					int driveSize = rs3.getInt("drive_size");
					int driveCapacity = rs3.getInt("drive_size");
					pr = new HardDrive(producer, model, price, info, quantity, "img",hdType, driveSize, driveCapacity);
					break;
				case "mon": 
					rs3 = st.executeQuery("SELECT screen_size, refresh_rate, matrix_type FROM pcshop.monitors WHERE product_id=" + productId + ";");
					double screenSize = rs3.getDouble("screen_size");
					int refreshRate = rs3.getInt("refresh_rate");
					String matrixType = rs3.getString("matrix_type");
					pr = new Monitor(producer, model, price, info, quantity, "img",screenSize, refreshRate, matrixType);
					break;
				case "mb": 
					rs3 = st.executeQuery("SELECT chipset, ram_slots, socket_type FROM pcshop.mother_boards WHERE product_id=" + productId + ";");
					String chipset = rs3.getString("case_form");
					String ramSlots = rs3.getString("ram_slots");
					String socketType = rs3.getString("socket_type");
					pr = new MotherBoard(producer, model, price, info, quantity,"img", chipset, ramSlots, socketType);
					break;
				case "ram": 
					rs3 = st.executeQuery("SELECT ram_type, ram_size FROM pcshop.rams WHERE product_id=" + productId + ";");
					String ramType = rs3.getString("ram_type");
					int ramSize = rs3.getInt("ram_size");
					pr = new Ram(producer, model, price, info, quantity, "img",ramType, ramSize);
					break;
				}
							
				cart.put(pr, quantity);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (InvalidPriceException e) {
			System.out.println(e.getMessage());
		} catch (InvalidQuantityException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		client.setCart(cart);
		return client;
		
	}

	@Override
	public HashSet<String> getClientEmails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getEMailsAndPasswords() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
		
}
