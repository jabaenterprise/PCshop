package database.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

public class DBProductsDAO implements IProductsDAO {
	private static final String db = "pcshop1";
	

	public void addProduct(Product product){
		
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
	
	
	@Override
	public Product getProduct(String modelToSearch) {
		Statement st = null;
		String query = "SELECT product_id, producer_name, model_name, price, product_info, type FROM pcshop1.products JOIN pcshop1.product_types ON (pcshop1.products.type_id=pcshop1.product_types.type_id) WHERE model_name=" + modelToSearch + ";";
		Product pr = null;
		try {
			Connection conn = DBManager.getDBManager().getConnection();
			
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if (!rs.isBeforeFirst()) {
				return null;
			}
			
			rs.next();
			
				int productId = rs.getInt("product_id");
				String producer = rs.getString("producer_name");
				String model = rs.getString("model_name");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				String info = rs.getString("product_info");		
				String type = rs.getString("type");
				ResultSet rs2 = null;
				switch (type) {
				case "case": 
					rs2 = st.executeQuery("SELECT case_form, case_size FROM pcshop.cases WHERE product_id=" + productId + ";");
					rs2.next();
					String form = rs2.getString("case_form");
					String size = rs2.getString("case_size");
					pr = new Case(producer, model, price, info, quantity, form, size);
					break;
				case "cpu": 
					rs2 = st.executeQuery("SELECT number_of_cores, clock_speed, socket FROM pcshop.cpus WHERE product_id=" + productId + ";");
					rs2.next();
					int numberOfCores = rs2.getInt("number_of_cores");
					double clockSpeed = rs2.getDouble("clock_speed");
					String socket = rs2.getString("socket");
					pr = new Cpu(producer, model, price, info, quantity, numberOfCores, clockSpeed, socket);
					break;
				case "gpu": 
					rs2 = st.executeQuery("SELECT memory_size, output_interface, max_resolution FROM pcshop.gpus WHERE product_id=" + productId + ";");
					rs2.next();
					int memorySize = rs2.getInt("memory_size");
					String maxResolution = rs2.getString("max_resolution");
					String outputInterface = rs2.getString("output_interface");
					pr = new Gpu(producer, model, price, info, quantity, memorySize, maxResolution, outputInterface);
					break;
				case "hd": 
					rs2 = st.executeQuery("SELECT hd_type, drive_size, drive_capacity FROM pcshop.hard_drives WHERE product_id=" + productId + ";");
					rs2.next();
					String hdType = rs2.getString("hd_type");
					int driveSize = rs2.getInt("drive_size");
					int driveCapacity = rs2.getInt("drive_size");
					pr = new HardDrive(producer, model, price, info, quantity, hdType, driveSize, driveCapacity);
					break;
				case "mon": 
					rs2 = st.executeQuery("SELECT screen_size, refresh_rate, matrix_type FROM pcshop.monitors WHERE product_id=" + productId + ";");
					rs2.next();
					double screenSize = rs2.getDouble("screen_size");
					int refreshRate = rs2.getInt("refresh_rate");
					String matrixType = rs2.getString("matrix_type");
					pr = new Monitor(producer, model, price, info, quantity, screenSize, refreshRate, matrixType);
					break;
				case "mb": 
					rs2 = st.executeQuery("SELECT chipset, ram_slots, socket_type FROM pcshop.mother_boards WHERE product_id=" + productId + ";");
					rs2.next();
					String chipset = rs2.getString("case_form");
					String ramSlots = rs2.getString("ram_slots");
					String socketType = rs2.getString("socket_type");
					pr = new MotherBoard(producer, model, price, info, quantity, chipset, ramSlots, socketType);
					break;
				case "ram": 
					rs2 = st.executeQuery("SELECT ram_type, ram_size FROM pcshop.rams WHERE product_id=" + productId + ";");
					rs2.next();
					String ramType = rs2.getString("ram_type");
					int ramSize = rs2.getInt("ram_size");
					pr = new Ram(producer, model, price, info, quantity, ramType, ramSize);
					break;
				}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (InvalidPriceException e) {
			System.out.println(e.getMessage());
		} catch (InvalidQuantityException e) {
			System.out.println(e.getMessage());
		}

		
		return pr;
	}
	
	
	//method for productInfo.jsp
	//all info for specific product
}
