package database.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import database.DBManager;
import model.Product;
import model.Case;
import model.Cpu;
import model.Gpu;
import model.HardDrive;
import model.Monitor;
import model.Motherboard;
import model.Ram;


public class DBProductDAO implements IProductDAO {

	private static final String DB = "ecommerce";

	private static DBProductDAO instance;
		
	private DBProductDAO(){
		
	}
	
	
	public static DBProductDAO getDBProductDAO(){
		if (DBProductDAO.instance == null) {
			DBProductDAO.instance = new DBProductDAO();
		}
		return DBProductDAO.instance;
	}


	//Database methods:
	@Override
	public void addProduct(Product newProduct) {
		//1.Get the type id:
		String productType = newProduct.getType();
		System.out.println();
		System.out.println();
		System.out.println(productType);
		System.out.println();
		System.out.println();
		String query1 = "SELECT type_id FROM ecommerce.product_types WHERE type='" + productType + "';";
		int typeId = 0;
		ResultSet rs1 = null;
		try {
			rs1 = DBManager.getDBManager().getConnection().createStatement().executeQuery(query1);
			rs1.next();
			typeId = rs1.getInt("type_id");
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//2.Insert into products table:
		String query2 = "INSERT INTO ecommerce.products (producer, model, price, info, quantity, img_url, type_id) VALUES (?,?,?,?,?,?,?);";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query2)){
			prst.setString(1, newProduct.getProducer());
			prst.setString(2, newProduct.getModel());
			prst.setDouble(3, newProduct.getPrice());
			prst.setString(4, newProduct.getInfo());
			prst.setInt(5, newProduct.getQuantity());
			prst.setString(6, newProduct.getImageUrl());
			prst.setInt(7, typeId);
			prst.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3. Get the product_id:
		int productId = 0;
		String query3 = "SELECT product_id FROM ecommerce.products WHERE model='" + newProduct.getModel() + "';";
		ResultSet rs2 = null;
		
		try (Statement st1 = DBManager.getDBManager().getConnection().createStatement()){
			rs2 = st1.executeQuery(query3);
			rs2.next();
			productId = rs2.getInt("product_id");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//4. Insert the additional info into the corresponding type's table:
			String query4 = new String();
		switch (newProduct.getType()) {
			case "case":
				Case cas = (Case) newProduct;
				query4 = "INSERT INTO ecommerce.cases (product_id, case_form, case_size) VALUES (?,?,?);";
				try (PreparedStatement prst2 = DBManager.getDBManager().getConnection().prepareStatement(query4)){
					prst2.setInt(1, productId);
					prst2.setString(2, cas.getForm());
					prst2.setString(3, cas.getSize());
					prst2.execute();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			break;
		
			case "cpu":
				Cpu cpu = (Cpu) newProduct;
				query4 = "INSERT INTO ecommerce.cpus (product_id, number_of_cores, clock_speed, cpu_socket) VALUES (?,?,?,?);";
				try (PreparedStatement prst2 = DBManager.getDBManager().getConnection().prepareStatement(query4)){
					prst2.setInt(1, productId);
					prst2.setInt(2, cpu.getNumberOfCores());
					prst2.setDouble(3, cpu.getClockSpeed());
					prst2.setString(4, cpu.getSocket());
					prst2.execute();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			break;
		
			case "gpu":
				Gpu gpu = (Gpu) newProduct;
				query4 = "INSERT INTO ecommerce.gpus (product_id, memory_size, max_resolution, output_interface) VALUES (?,?,?,?);";
				try (PreparedStatement prst2 = DBManager.getDBManager().getConnection().prepareStatement(query4)){
					prst2.setInt(1, productId);
					prst2.setInt(2, gpu.getMemorySize());
					prst2.setString(3, gpu.getMaxResolution());
					prst2.setString(4, gpu.getOutputInterface());
					prst2.execute();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			break;
			
			case "hd":
				HardDrive hd = (HardDrive) newProduct;
				query4 = "INSERT INTO ecommerce.hard_drives (product_id, hd_type, size, capacity) VALUES (?,?,?,?);";
				try (PreparedStatement prst2 = DBManager.getDBManager().getConnection().prepareStatement(query4)){
					prst2.setInt(1, productId);
					prst2.setString(2, hd.gethdType());
					prst2.setDouble(3, hd.getSize());
					prst2.setInt(4, hd.getCapacity());
					prst2.execute();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			break;
			
			case "monitor":
				Monitor mon = (Monitor) newProduct;
				query4 = "INSERT INTO ecommerce.monitors (product_id, size, refresh_rate, matrix_type) VALUES (?,?,?,?);";
				try (PreparedStatement prst2 = DBManager.getDBManager().getConnection().prepareStatement(query4)){
					prst2.setInt(1, productId);
					prst2.setDouble(2,mon.getSize());
					prst2.setInt(3, mon.getRefreshRate());
					prst2.setString(4, mon.getMatrixType());
					prst2.execute();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			break;
			
			case "mb":
				Motherboard mb = (Motherboard) newProduct;
				query4 = "INSERT INTO ecommerce.mother_boards (product_id, chipset, ram_slots, socket) VALUES (?,?,?,?);";
				try (PreparedStatement prst2 = DBManager.getDBManager().getConnection().prepareStatement(query4)){
					prst2.setInt(1, productId);
					prst2.setString(2, mb.getChipset());
					prst2.setInt(3, mb.getRamSlots());
					prst2.setString(4, mb.getSocket());
					prst2.execute();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			break;
			
			case "ram":
				Ram ram = (Ram) newProduct;
				query4 = "INSERT INTO ecommerce.rams (product_id, ram_type, size) VALUES (?,?,?);";
				try (PreparedStatement prst2 = DBManager.getDBManager().getConnection().prepareStatement(query4)){
					prst2.setInt(1, productId);
					prst2.setString(2, ram.getRamType());
					prst2.setInt(3, ram.getSize());
					prst2.execute();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			break;
		}
		
	}
	
	@Override
	public void updatePrice(Product p, double newPrice) {
		int id = p.getId();
		String query = "UPDATE ecommerce.products SET price= ? WHERE product_id=" + id + ";";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			
			prst.setDouble(1, newPrice);
			prst.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	@Override
	public void updateQuantity(Product p, int newQuantity) {
		int id = p.getId();
		String query = "UPDATE ecommerce.products SET quantity= ? WHERE product_id=" + id + ";";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			
			prst.setInt(1, newQuantity);
			prst.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public Product getProduct(String model) {
		Product pr = null;
		
		Statement st1 = null;
		Statement st2 = null;
		String query1 = "SELECT product_id, producer, model, price, info, quantity, img_url, type FROM ecommerce.products JOIN ecommerce.product_types ON (ecommerce.products.type_id=ecommerce.product_types.type_id) WHERE ecommerce.products.model='" + model + "';";
						
		try {
			st1 = DBManager.getDBManager().getConnection().createStatement();
			ResultSet rs1 = st1.executeQuery(query1);
			
			//If there is no such product, return null:
			if (!rs1.isBeforeFirst()) {
				return null;
			}
			
			//Else get the product characteristics from the database, according to product's type:
			rs1.next();
				int id = rs1.getInt("product_id");
				String producer = rs1.getString("producer");
				String productModel = rs1.getString("model");
				double price = rs1.getDouble("price");
				String info = rs1.getString("info");	
				int quantity = rs1.getInt("quantity");
				String imgUrl = rs1.getString("img_url");
				String type = rs1.getString("type");
				
				
				ResultSet rs2 = null;
				st2 = DBManager.getDBManager().getConnection().createStatement();
				switch (type) {
				case "case": 
					rs2 = st2.executeQuery("SELECT case_form, case_size FROM ecommerce.cases WHERE product_id=" + id + ";");
					rs2.next();
					String form = rs2.getString("case_form");
					String size = rs2.getString("case_size");
					pr = new Case(producer, productModel, price, info, quantity, imgUrl, form, size);
					pr.setId(id);
					break;
					
				case "cpu": 
					rs2 = st2.executeQuery("SELECT number_of_cores, clock_speed, cpu_socket FROM ecommerce.cpus WHERE product_id=" + id + ";");
					rs2.next();
					int numberOfCores = rs2.getInt("number_of_cores");
					double clockSpeed = rs2.getDouble("clock_speed");
					String cpuSocket = rs2.getString("cpu_socket");
					pr = new Cpu(producer, productModel, price, info, quantity, imgUrl, numberOfCores, clockSpeed, cpuSocket);
					pr.setId(id);
					break;
					
				case "gpu": 
					rs2 = st2.executeQuery("SELECT memory_size, output_interface, max_resolution FROM ecommerce.gpus WHERE product_id=" + id + ";");
					rs2.next();
					int memorySize = rs2.getInt("memory_size");
					String maxResolution = rs2.getString("max_resolution");
					String outputInterface = rs2.getString("output_interface");
					pr = new Gpu(producer, productModel, price, info, quantity, imgUrl, memorySize, maxResolution, outputInterface);
					pr.setId(id);
					break;
					
				case "hd": 
					rs2 = st2.executeQuery("SELECT hd_type, size, capacity FROM ecommerce.hard_drives WHERE product_id=" + id + ";");
					rs2.next();
					String hdType = rs2.getString("hd_type");
					double driveSize = rs2.getDouble("size");
					int driveCapacity = rs2.getInt("capacity");
					pr = new HardDrive(producer, productModel, price, info, quantity, imgUrl, hdType, driveSize, driveCapacity);
					pr.setId(id);
					break;
					
				case "monitor": 
					rs2 = st2.executeQuery("SELECT size, refresh_rate, matrix_type FROM ecommerce.monitors WHERE product_id=" + id + ";");
					rs2.next();
					double screenSize = rs2.getDouble("size");
					int refreshRate = rs2.getInt("refresh_rate");
					String matrixType = rs2.getString("matrix_type");
					pr = new Monitor(producer, productModel, price, info, quantity, imgUrl,screenSize, refreshRate, matrixType);
					pr.setId(id);
					break;
					
				case "mb": 
					rs2 = st2.executeQuery("SELECT chipset, ram_slots, socket FROM ecommerce.mother_boards WHERE product_id=" + id + ";");
					rs2.next();
					String chipset = rs2.getString("chipset");
					int ramSlots = rs2.getInt("ram_slots");
					String socketType = rs2.getString("socket");
					pr = new Motherboard(producer, productModel, price, info, quantity, imgUrl, chipset, ramSlots, socketType);
					pr.setId(id);
					break;
					
				case "ram": 
					rs2 = st2.executeQuery("SELECT ram_type, size FROM ecommerce.rams WHERE product_id=" + id + ";");
					rs2.next();
					String ramType = rs2.getString("ram_type");
					int ramSize = rs2.getInt("size");
					pr = new Ram(producer, productModel, price, info, quantity, imgUrl, ramType, ramSize);
					pr.setId(id);
					break;
				}
						
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
		return pr;
	}

	@Override
	public HashSet<Product> getAllProducts() {
		HashSet<Product> results = new HashSet<Product>();
		
		Statement st = null;
		Statement st2 = null;
		Statement st3 = null;
		
		try {
			st = DBManager.getDBManager().getConnection().createStatement();
			String query1 = "SELECT product_id, producer, model, price, info, quantity, img_url, type_id FROM ecommerce.products;";
			ResultSet rs = st.executeQuery(query1);
			Product pr = null;
			int quantity = 0;
			String productType = new String();
			
			while(rs.next()) {
				//1. Get the generic characteristics:
				int productId = rs.getInt("product_id");
				String producer = rs.getString("producer");
				String model = rs.getString("model");
				double price = rs.getDouble("price");
				String info = rs.getString("info");
				quantity = rs.getInt("quantity");
				String imgUrl = rs.getString("img_url");
				int typeId = rs.getInt("type_id");
				
				//2.Get the type:
				ResultSet rs2 = null;
				st2 = DBManager.getDBManager().getConnection().createStatement();
				String query2 = "SELECT type FROM ecommerce.product_types WHERE type_id=" + typeId + ";";
				rs2 = st2.executeQuery(query2);
				rs2.next();
				productType = rs2.getString("type");
								
				
				//3. Get the type specific characteristics of the current product from the database, according to the product's type:
				ResultSet rs3 = null;
				st3 = DBManager.getDBManager().getConnection().createStatement();
				String query3 = new String();
				switch (productType) {
					
					case "case": 
						query3 = "SELECT case_form, case_size FROM ecommerce.cases WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						String form = rs3.getString("case_form");
						String size = rs3.getString("case_size");
						pr = new Case(producer, model, price, info, quantity, imgUrl, form, size);
					break;
					
					case "cpu": 
						query3 = "SELECT number_of_cores, clock_speed, cpu_socket FROM ecommerce.cpus WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						int numberOfCores = rs3.getInt("number_of_cores");
						double clockSpeed = rs3.getDouble("clock_speed");
						String cpuSocket = rs3.getString("cpu_socket");
						pr = new Cpu(producer, model, price, info, quantity, imgUrl, numberOfCores, clockSpeed, cpuSocket);
					break;
					
					case "gpu": 
						query3 = "SELECT memory_size, max_resolution, output_interface FROM ecommerce.gpus WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						int memorySize = rs3.getInt("memory_size");
						String maxResolution = rs3.getString("max_resolution");
						String outputInterface = rs3.getString("output_interface");
						pr = new Gpu(producer, model, price, info, quantity, imgUrl, memorySize, maxResolution, outputInterface);
					break;
					
					case "hd": 
						query3 = "SELECT hd_type, size, capacity FROM ecommerce.hard_drives WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						String hdType = rs3.getString("hd_type");
						double driveSize = rs3.getDouble("size");
						int driveCapacity = rs3.getInt("capacity");
						pr = new HardDrive(producer, model, price, info, quantity, imgUrl, hdType, driveSize, driveCapacity);
					break;
					
					case "monitor": 
						query3 = "SELECT size, refresh_rate, matrix_type FROM ecommerce.monitors WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						double screenSize = rs3.getDouble("size");
						int refreshRate = rs3.getInt("refresh_rate");
						String matrixType = rs3.getString("matrix_type");
						pr = new Monitor(producer, model, price, info, quantity, imgUrl, screenSize, refreshRate, matrixType);
					break;
					
					case "mb": 
						query3 = "SELECT chipset, ram_slots, socket FROM ecommerce.mother_boards WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						String chipset = rs3.getString("chipset");
						int ramSlots = rs3.getInt("ram_slots");
						String socketType = rs3.getString("socket");
						pr = new Motherboard(producer, model, price, info, quantity, imgUrl, chipset, ramSlots, socketType);
					break;
					
					case "ram": 
						query3 = "SELECT ram_type, size FROM ecommerce.rams WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						String ramType = rs3.getString("ram_type");
						int ramSize = rs3.getInt("size");
						pr = new Ram(producer, model, price, info, quantity, imgUrl, ramType, ramSize);
					break;
				}
							
				results.add(pr);
			}
						
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				st2.close();
				st3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return results;
	}

	@Override
	public HashSet<String> getAllModels() {
		HashSet<String> allModels = new HashSet<String>();
		String query = "SELECT model FROM ecommerce.products;";
		
		try (Statement st = DBManager.getDBManager().getConnection().createStatement()){
			
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String model = rs.getString("model");
				allModels.add(model);
			}
						
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return allModels;
	}

	@Override
	public List<Product> serchProducts(String searchWord) {
		List<Product> results = new ArrayList<Product>();
		
		Statement st = null;
		Statement st2 = null;
		Statement st3 = null;
		
		try {
			st = DBManager.getDBManager().getConnection().createStatement();
			String query1 = "SELECT product_id, producer, model, price, info, quantity, img_url, type_id FROM ecommerce.products WHERE producer LIKE'%" + searchWord + "%' OR model LIKE '%" + searchWord + "%';";
			ResultSet rs = st.executeQuery(query1);
			Product pr = null;
			int quantity = 0;
			String productType = new String();
			
			while(rs.next()) {
				//1. Get the generic characteristics:
				int productId = rs.getInt("product_id");
				String producer = rs.getString("producer");
				String model = rs.getString("model");
				double price = rs.getDouble("price");
				String info = rs.getString("info");
				quantity = rs.getInt("quantity");
				String imgUrl = rs.getString("img_url");
				int typeId = rs.getInt("type_id");

				//2.Get the type:
				ResultSet rs2 = null;
				st2 = DBManager.getDBManager().getConnection().createStatement();
				String query2 = "SELECT type FROM ecommerce.product_types WHERE type_id=" + typeId + ";";
				rs2 = st2.executeQuery(query2);
				rs2.next();
				productType = rs2.getString("type");
				
				//3. Get the type specific characteristics of the current product from the database, according to the product's type:
				ResultSet rs3 = null;
				st3 = DBManager.getDBManager().getConnection().createStatement();
				String query3 = new String();
				switch (productType) {
					
					case "case": 
						query3 = "SELECT case_form, case_size FROM ecommerce.cases WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						String form = rs3.getString("case_form");
						String size = rs3.getString("case_size");
						pr = new Case(producer, model, price, info, quantity, imgUrl, form, size);
					break;
					
					case "cpu": 
						query3 = "SELECT number_of_cores, clock_speed, cpu_socket FROM ecommerce.cpus WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						int numberOfCores = rs3.getInt("number_of_cores");
						double clockSpeed = rs3.getDouble("clock_speed");
						String cpuSocket = rs3.getString("cpu_socket");
						pr = new Cpu(producer, model, price, info, quantity, imgUrl, numberOfCores, clockSpeed, cpuSocket);
					break;
					
					case "gpu": 
						query3 = "SELECT memory_size, max_resolution, output_interface FROM ecommerce.gpus WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						int memorySize = rs3.getInt("memory_size");
						String maxResolution = rs3.getString("max_resolution");
						String outputInterface = rs3.getString("output_interface");
						pr = new Gpu(producer, model, price, info, quantity, imgUrl, memorySize, maxResolution, outputInterface);
					break;
					
					case "hd": 
						query3 = "SELECT hd_type, size, capacity FROM ecommerce.hard_drives WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						String hdType = rs3.getString("hd_type");
						double driveSize = rs3.getDouble("size");
						int driveCapacity = rs3.getInt("capacity");
						pr = new HardDrive(producer, model, price, info, quantity, imgUrl, hdType, driveSize, driveCapacity);
					break;
					
					case "monitor": 
						query3 = "SELECT size, refresh_rate, matrix_type FROM ecommerce.monitors WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						double screenSize = rs3.getDouble("size");
						int refreshRate = rs3.getInt("refresh_rate");
						String matrixType = rs3.getString("matrix_type");
						pr = new Monitor(producer, model, price, info, quantity, imgUrl, screenSize, refreshRate, matrixType);
					break;
					
					case "mb": 
						query3 = "SELECT chipset, ram_slots, socket FROM ecommerce.mother_boards WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						String chipset = rs3.getString("chipset");
						int ramSlots = rs3.getInt("ram_slots");
						String socketType = rs3.getString("socket");
						pr = new Motherboard(producer, model, price, info, quantity, imgUrl, chipset, ramSlots, socketType);
					break;
					
					case "ram": 
						query3 = "SELECT ram_type, size FROM ecommerce.rams WHERE product_id=" + productId + ";";
						rs3 = st3.executeQuery(query3);
						rs3.next();
						String ramType = rs3.getString("ram_type");
						int ramSize = rs3.getInt("size");
						pr = new Ram(producer, model, price, info, quantity, imgUrl, ramType, ramSize);
					break;
				}
							
				results.add(pr);
			}
						
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(st2!=null)
					st2.close();
				if(st3!=null)
					st3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return results;
	}
	
	public List<Product> getAllProducts(String categoryType, String sortType) {
		String db = "ecommerce";
		String sortQuery = "";
		switch (sortType) {
		case "priceAscending":
			sortQuery = "ORDER BY price";
			break;
		case "priceDescending":
			sortQuery = "ORDER BY price DESC";
			break;
		case "producer":
			sortQuery = "ORDER BY producer";
			break;
		default:
			sortQuery = "ORDER BY price";break;
		}
		// none
		String query = "SELECT product_id, producer, model, price, info, quantity, img_url, type "
				+ "FROM "+ db+ ".products "
				+ "JOIN "+ db+ ".product_types ON "+ db+ ".products.type_id="+ db+ ".product_types.type_id "
				+sortQuery +";";
		// with category
		if (!categoryType.equals("none")) {
			query = "SELECT product_id, producer, model, price, info, quantity, img_url, type "
					+ "FROM "+ db+ ".products "
					+ "JOIN "+ db+ ".product_types ON "+ db+ ".products.type_id="+ db+ ".product_types.type_id "
					+ "WHERE "+ db+ ".product_types.type LIKE '" + categoryType + "' "
					+ sortQuery + ";";
		}

		Product pr = null;
		List<Product> products = new LinkedList<Product>();
		try (Statement st = DBManager.getDBManager().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int productId = rs.getInt("product_id");
				String producer = rs.getString("producer");
				String model = rs.getString("model");
				double price = rs.getDouble("price");
				String info = rs.getString("info");
				int quantity = rs.getInt("quantity");
				String img = rs.getString("img_url");
				String type = rs.getString("type");
				
				
				try(Statement st1 = DBManager.getDBManager().getConnection().createStatement()){
					ResultSet rs2 = null;
					switch (type) {
					case "case":
						rs2 = st1.executeQuery("SELECT case_form, case_size FROM "+db+".cases WHERE product_id="
								+ productId + ";");
						String form;
						String size;
						if (!rs2.next()) {
							form = "";
							size = "";
						} else {
							form = rs2.getString("case_form");
							size = rs2.getString("case_size");
						}
						pr = new Case(producer, model, price, info, quantity, img,
								form, size);
						pr.setId(productId);
						break;
					case "cpu":
						rs2 = st1.executeQuery("SELECT number_of_cores, clock_speed, cpu_socket FROM "+db+".cpus WHERE product_id="
								+ productId + ";");
						int numberOfCores;
						double clockSpeed;
						String socket;
						if (!rs2.next()) {
							numberOfCores = 0;
							clockSpeed = 0;
							socket = "";
						} else {
							numberOfCores = rs2.getInt("number_of_cores");
							clockSpeed = rs2.getDouble("clock_speed");
							socket = rs2.getString("cpu_socket");
						}
						pr = new Cpu(producer, model, price, info, quantity, img,
								numberOfCores, clockSpeed, socket);
						pr.setId(productId);
						break;
					case "gpu":
						rs2 = st1.executeQuery("SELECT memory_size, output_interface, max_resolution FROM "+db+".gpus WHERE product_id="
								+ productId + ";");
						int memorySize = 0;
						String maxResolution = "";
						String outputInterface = "";
						if (!rs2.next()) {
							memorySize = 0;
							maxResolution = "";
							outputInterface = "";
						} else {
							memorySize = rs2.getInt("memory_size");
							maxResolution = rs2.getString("max_resolution");
							outputInterface = rs2.getString("output_interface");
						}
						pr = new Gpu(producer, model, price, info, quantity, img,
								memorySize, maxResolution, outputInterface);
						pr.setId(productId);
						break;
					case "hd":
						rs2 = st1.executeQuery("SELECT hd_type, size, capacity FROM "+db+".hard_drives WHERE product_id="
								+ productId + ";");
						String hdType = "";
						int driveSize = 0;
						int driveCapacity = 0;
						if (rs2.next()) {
							hdType = rs2.getString("hd_type");
							driveSize = rs2.getInt("size");
							driveCapacity = rs2.getInt("size");
						}
						pr = new HardDrive(producer, model, price, info, quantity,
								img, hdType, driveSize, driveCapacity);
						pr.setId(productId);
						break;
					case "mon":
						rs2 = st1.executeQuery("SELECT size, refresh_rate, matrix_type FROM "+db+".monitors WHERE product_id="
								+ productId + ";");
						double screenSize = 0;
						int refreshRate = 0;
						String matrixType = "";
						if (rs2.next()) {
							screenSize = rs2.getDouble("size");
							refreshRate = rs2.getInt("refresh_rate");
							matrixType = rs2.getString("matrix_type");
						}
						pr = new Monitor(producer, model, price, info, quantity,
								img, screenSize, refreshRate, matrixType);
						pr.setId(productId);
						break;
					case "mb":
						rs2 = st1.executeQuery("SELECT chipset, ram_slots, socket FROM "+db+".mother_boards WHERE product_id="
								+ productId + ";");
						String chipset = "";
						int ramSlots = 0;
						String socketType = "";
						if (rs2.next()) {
							chipset = rs2.getString("case_form");
							ramSlots = rs2.getInt("ram_slots");
							socketType = rs2.getString("socket");
						}
						pr = new Motherboard(producer, model, price, info,
								quantity, img, chipset, ramSlots, socketType);
						pr.setId(productId);
						break;
					case "ram":
						rs2 = st1.executeQuery("SELECT ram_type, size FROM "+db+".rams WHERE product_id="
								+ productId + ";");
						String ramType = "";
						int ramSize = 0;

						if (rs2.next()) {
							ramType = rs2.getString("ram_type");
							ramSize = rs2.getInt("size");
						}
						pr = new Ram(producer, model, price, info, quantity, img,
								ramType, ramSize);
						pr.setId(productId);
						break;
					}
				}
				
				if (pr != null) {
					products.add(pr);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return products;
	}

	@Override
	public Product getProductById(int id) {
		int productId = 0;
		String producer = "";
		String model = "";
		double price = 0;
		String info = "";
		int quantity = 0;
		String img = "";
		String type = "";

		String query = "SELECT P.product_id, P.producer, P.model, P.price, P.info, P.quantity, P.img_url, type "
				+ "FROM "
				+ DB+ ".products P "
				+ "JOIN "
				+ DB
				+ ".product_types T ON P.type_id=T.type_id "
				+ "WHERE P.product_id=" + id + ";";
		Product pr = null;
		try (Statement st = DBManager.getDBManager().getConnection()
				.createStatement()) {
			ResultSet rs = st.executeQuery(query);


			while (rs.next()) {
				productId = rs.getInt("product_id");
				producer = rs.getString("producer");
				model = rs.getString("model");
				price = rs.getDouble("price");
				info = rs.getString("info");
				quantity = rs.getInt("quantity");
				img = rs.getString("img_url");
				type = rs.getString("type");
			}

			System.out.println("product: " + producer + " " + price);

			ResultSet rs2 = null;
			switch (type) {
			case "case":
				rs2 = st.executeQuery("SELECT case_form, case_size FROM ecommerce.cases WHERE product_id="
						+ productId + ";");
				String form;
				String size;
				if (!rs2.next()) {
					form = "";
					size = "";
				} else {
					form = rs2.getString("case_form");
					size = rs2.getString("case_size");
				}
				pr = new Case(producer, model, price, info, quantity, img,
						form, size);
				break;
			case "cpu":
				rs2 = st.executeQuery("SELECT number_of_cores, clock_speed, socket FROM ecommerce.cpus WHERE product_id="
						+ productId + ";");
				int numberOfCores;
				double clockSpeed;
				String socket;
				if (!rs2.next()) {
					numberOfCores = 0;
					clockSpeed = 0;
					socket = "";
				} else {
					numberOfCores = rs2.getInt("number_of_cores");
					clockSpeed = rs2.getDouble("clock_speed");
					socket = rs2.getString("socket");
				}
				pr = new Cpu(producer, model, price, info, quantity, img,
						numberOfCores, clockSpeed, socket);
				break;
			case "gpu":
				rs2 = st.executeQuery("SELECT memory_size, output_interface, max_resolution FROM ecommerce.gpus WHERE product_id="
						+ productId + ";");
				int memorySize = 0;
				String maxResolution = "";
				String outputInterface = "";
				if (!rs2.next()) {
					memorySize = 0;
					maxResolution = "";
					outputInterface = "";
				} else {
					memorySize = rs2.getInt("memory_size");
					maxResolution = rs2.getString("max_resolution");
					outputInterface = rs2.getString("output_interface");
				}
				pr = new Gpu(producer, model, price, info, quantity, img,
						memorySize, maxResolution, outputInterface);
				break;
			case "hd":
				rs2 = st.executeQuery("SELECT hd_type, drive_size, drive_capacity FROM ecommerce.hard_drives WHERE product_id="
						+ productId + ";");
				String hdType = "";
				int driveSize = 0;
				int driveCapacity = 0;
				if (rs2.next()) {
					hdType = rs2.getString("hd_type");
					driveSize = rs2.getInt("drive_size");
					driveCapacity = rs2.getInt("drive_size");
				}
				pr = new HardDrive(producer, model, price, info, quantity, img,
						hdType, driveSize, driveCapacity);
				break;
			case "mon":
				rs2 = st.executeQuery("SELECT screen_size, refresh_rate, matrix_type FROM ecommerce.monitors WHERE product_id="
						+ productId + ";");
				double screenSize = 0;
				int refreshRate = 0;
				String matrixType = "";
				if (rs2.next()) {
					screenSize = rs2.getDouble("screen_size");
					refreshRate = rs2.getInt("refresh_rate");
					matrixType = rs2.getString("matrix_type");
				}
				pr = new Monitor(producer, model, price, info, quantity, img,
						screenSize, refreshRate, matrixType);
				break;
			case "mb":
				rs2 = st.executeQuery("SELECT chipset, ram_slots, socket_type FROM ecommerce.mother_boards WHERE product_id="
						+ productId + ";");
				String chipset = "";
				int ramSlots =0;
				String socketType = "";
				if (rs2.next()) {
					chipset = rs2.getString("case_form");
					ramSlots = rs2.getInt("ram_slots");
					socketType = rs2.getString("socket_type");
				}
				pr = new Motherboard(producer, model, price, info, quantity,
						img, chipset, ramSlots, socketType);
				break;
			case "ram":
				rs2 = st.executeQuery("SELECT ram_type, ram_size FROM ecommerce.rams WHERE product_id="
						+ productId + ";");
				String ramType = "";
				int ramSize = 0;

				if (rs2.next()) {
					ramType = rs2.getString("ram_type");
					ramSize = rs2.getInt("ram_size");
				}
				pr = new Ram(producer, model, price, info, quantity, img,
						ramType, ramSize);
				break;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Excetion");
			e.printStackTrace();
		}
		pr.setId(productId);
		return pr;
	}
}








