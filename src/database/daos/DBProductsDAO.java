package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import products.ProductIDEntry;
import products.Ram;

public class DBProductsDAO implements IProductsDAO {
	private static final String db = "pcshop";

	public void addProduct(Product product) {

	}

	// sort clause must be added
	// select everything form products joined with product type +
	public static List<Map.Entry<String, String>> getProducts(String categoryType) {
		String query = "SELECT product_id,img_url as url " + "FROM " + db
				+ ".products " + "JOIN " + db + ".product_types ON " + db
				+ ".products.type_id=" + db + ".product_types.type_id "
				+ "WHERE " + db + ".product_types.type_name LIKE '"
				+ categoryType + "';";
		if (categoryType.equals("none")) {
			query = "SELECT product_id, img_url as url " + "FROM " + db
					+ ".products;";
		}
		HashMap<String, String> products = new HashMap<String, String>();
		try (Statement st = DBManager.getDBManager().getConnection()
				.createStatement()) {
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				products.put(rs.getString("product_id"), rs.getString("url"));
				System.out.println(rs.getString("url"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Map.Entry<String, String>> result = new LinkedList<Map.Entry<String, String>>(
				products.entrySet());
		return result;
	}

	public List<ProductIDEntry> getAllProducts(String categoryType, String sortType) {
		String sortQuery = "";
		switch (sortType) {
		case "priceAscending":
			sortQuery = "ORDER BY price";
			break;
		case "priceDescending":
			sortQuery = "ORDER BY price DESC";
			break;
		case "producer":
			sortQuery = "ORDER BY producer_name";
			break;
		default:
			sortQuery = "ORDER BY price";break;
		}
		// none
		String query = "SELECT product_id, producer_name, model_name, price, product_info, quantity, img_url, type_name "
				+ "FROM "+ db+ ".products "
				+ "JOIN "+ db+ ".product_types ON "+ db+ ".products.type_id="+ db+ ".product_types.type_id "
				+sortQuery +";";
		// with category
		if (!categoryType.equals("none")) {
			query = "SELECT product_id, producer_name, model_name, price, product_info, quantity, img_url, type_name "
					+ "FROM "+ db+ ".products "
					+ "JOIN "+ db+ ".product_types ON "+ db+ ".products.type_id="+ db+ ".product_types.type_id "
					+ "WHERE "+ db+ ".product_types.type_name LIKE '" + categoryType + "' "
					+ sortQuery + ";";
		}

		Product pr = null;
		List<ProductIDEntry> products = new LinkedList<ProductIDEntry>();
		try (Statement st = DBManager.getDBManager().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int productId = rs.getInt("product_id");
				String producer = rs.getString("producer_name");
				String model = rs.getString("model_name");
				double price = rs.getDouble("price");
				String info = rs.getString("product_info");
				int quantity = rs.getInt("quantity");
				String img = rs.getString("img_url");
				String type = rs.getString("type_name");
				
				
				try(Statement st1 = DBManager.getDBManager().getConnection().createStatement()){
					ResultSet rs2 = null;
					switch (type) {
					case "case":
						rs2 = st1.executeQuery("SELECT case_form, case_size FROM pcshop.cases WHERE product_id="
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
						rs2 = st1.executeQuery("SELECT number_of_cores, clock_speed, socket FROM pcshop.cpus WHERE product_id="
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
						rs2 = st1.executeQuery("SELECT memory_size, output_interface, max_resolution FROM pcshop.gpus WHERE product_id="
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
						rs2 = st1.executeQuery("SELECT hd_type, drive_size, drive_capacity FROM pcshop.hard_drives WHERE product_id="
								+ productId + ";");
						String hdType = "";
						int driveSize = 0;
						int driveCapacity = 0;
						if (rs2.next()) {
							hdType = rs2.getString("hd_type");
							driveSize = rs2.getInt("drive_size");
							driveCapacity = rs2.getInt("drive_size");
						}
						pr = new HardDrive(producer, model, price, info, quantity,
								img, hdType, driveSize, driveCapacity);
						break;
					case "mon":
						rs2 = st1.executeQuery("SELECT screen_size, refresh_rate, matrix_type FROM pcshop.monitors WHERE product_id="
								+ productId + ";");
						double screenSize = 0;
						int refreshRate = 0;
						String matrixType = "";
						if (rs2.next()) {
							screenSize = rs2.getDouble("screen_size");
							refreshRate = rs2.getInt("refresh_rate");
							matrixType = rs2.getString("matrix_type");
						}
						pr = new Monitor(producer, model, price, info, quantity,
								img, screenSize, refreshRate, matrixType);
						break;
					case "mb":
						rs2 = st1.executeQuery("SELECT chipset, ram_slots, socket_type FROM pcshop.mother_boards WHERE product_id="
								+ productId + ";");
						String chipset = "";
						String ramSlots = "";
						String socketType = "";
						if (rs2.next()) {
							chipset = rs2.getString("case_form");
							ramSlots = rs2.getString("ram_slots");
							socketType = rs2.getString("socket_type");
						}
						pr = new MotherBoard(producer, model, price, info,
								quantity, img, chipset, ramSlots, socketType);
						break;
					case "ram":
						rs2 = st1.executeQuery("SELECT ram_type, ram_size FROM pcshop.rams WHERE product_id="
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
				}
				
				if (pr != null) {
					products.add(new ProductIDEntry(pr, productId));
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPriceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidQuantityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	public Product getProductById(int id) {
		String query = "SELECT product_id, producer_name, model_name, price, product_info, quantity, img_url, type_name "
				+ "FROM "
				+ db
				+ ".products "
				+ "JOIN "
				+ db
				+ ".product_types ON products.type_id=product_types.type_id "
				+ "WHERE " + db + ".products.product_id=" + id + ";";
		Product pr = null;
		try (Statement st = DBManager.getDBManager().getConnection()
				.createStatement()) {
			ResultSet rs = st.executeQuery(query);

			int productId = 0;
			String producer = "";
			String model = "";
			double price = 0;
			String info = "";
			int quantity = 0;
			String img = "";
			String type = "";

			while (rs.next()) {
				productId = rs.getInt("product_id");
				producer = rs.getString("producer_name");
				model = rs.getString("model_name");
				price = rs.getDouble("price");
				info = rs.getString("product_info");
				quantity = rs.getInt("quantity");
				img = rs.getString("img_url");
				type = rs.getString("type_name");
			}

			System.out.println("product: " + producer + " " + price);

			ResultSet rs2 = null;
			switch (type) {
			case "case":
				rs2 = st.executeQuery("SELECT case_form, case_size FROM pcshop.cases WHERE product_id="
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
				rs2 = st.executeQuery("SELECT number_of_cores, clock_speed, socket FROM pcshop.cpus WHERE product_id="
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
				rs2 = st.executeQuery("SELECT memory_size, output_interface, max_resolution FROM pcshop.gpus WHERE product_id="
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
				rs2 = st.executeQuery("SELECT hd_type, drive_size, drive_capacity FROM pcshop.hard_drives WHERE product_id="
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
				rs2 = st.executeQuery("SELECT screen_size, refresh_rate, matrix_type FROM pcshop.monitors WHERE product_id="
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
				rs2 = st.executeQuery("SELECT chipset, ram_slots, socket_type FROM pcshop.mother_boards WHERE product_id="
						+ productId + ";");
				String chipset = "";
				String ramSlots = "";
				String socketType = "";
				if (rs2.next()) {
					chipset = rs2.getString("case_form");
					ramSlots = rs2.getString("ram_slots");
					socketType = rs2.getString("socket_type");
				}
				pr = new MotherBoard(producer, model, price, info, quantity,
						img, chipset, ramSlots, socketType);
				break;
			case "ram":
				rs2 = st.executeQuery("SELECT ram_type, ram_size FROM pcshop.rams WHERE product_id="
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
		} catch (InvalidPriceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidQuantityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pr;
	}

	@Override
	public Product getProduct(String modelToSearch) {
		Statement st = null;
		String query = "SELECT product_id, producer_name, model_name, price, product_info,img_url, type FROM pcshop1.products JOIN pcshop1.product_types ON (pcshop1.products.type_id=pcshop1.product_types.type_id) WHERE model_name="
				+ modelToSearch + ";";
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
			String img = rs.getString("img_url");
			String type = rs.getString("type");
			ResultSet rs2 = null;
			switch (type) {
			case "case":
				rs2 = st.executeQuery("SELECT case_form, case_size FROM pcshop.cases WHERE product_id="
						+ productId + ";");
				rs2.next();
				String form = rs2.getString("case_form");
				String size = rs2.getString("case_size");
				pr = new Case(producer, model, price, info, quantity, img,
						form, size);
				break;
			case "cpu":
				rs2 = st.executeQuery("SELECT number_of_cores, clock_speed, socket FROM pcshop.cpus WHERE product_id="
						+ productId + ";");
				rs2.next();
				int numberOfCores = rs2.getInt("number_of_cores");
				double clockSpeed = rs2.getDouble("clock_speed");
				String socket = rs2.getString("socket");
				pr = new Cpu(producer, model, price, info, quantity, img,
						numberOfCores, clockSpeed, socket);
				pr.setId(productId);
				break;
			case "gpu":
				rs2 = st.executeQuery("SELECT memory_size, output_interface, max_resolution FROM pcshop.gpus WHERE product_id="
						+ productId + ";");
				rs2.next();
				int memorySize = rs2.getInt("memory_size");
				String maxResolution = rs2.getString("max_resolution");
				String outputInterface = rs2.getString("output_interface");
				pr = new Gpu(producer, model, price, info, quantity, img,
						memorySize, maxResolution, outputInterface);
				pr.setId(productId);
				break;
			case "hd":
				rs2 = st.executeQuery("SELECT hd_type, drive_size, drive_capacity FROM pcshop.hard_drives WHERE product_id="
						+ productId + ";");
				rs2.next();
				String hdType = rs2.getString("hd_type");
				int driveSize = rs2.getInt("drive_size");
				int driveCapacity = rs2.getInt("drive_size");
				pr = new HardDrive(producer, model, price, info, quantity, img,
						hdType, driveSize, driveCapacity);
				pr.setId(productId);
				break;
			case "mon":
				rs2 = st.executeQuery("SELECT screen_size, refresh_rate, matrix_type FROM pcshop.monitors WHERE product_id="
						+ productId + ";");
				rs2.next();
				double screenSize = rs2.getDouble("screen_size");
				int refreshRate = rs2.getInt("refresh_rate");
				String matrixType = rs2.getString("matrix_type");
				pr = new Monitor(producer, model, price, info, quantity, img,
						screenSize, refreshRate, matrixType);
				pr.setId(productId);
				break;
			case "mb":
				rs2 = st.executeQuery("SELECT chipset, ram_slots, socket_type FROM pcshop.mother_boards WHERE product_id="
						+ productId + ";");
				rs2.next();
				String chipset = rs2.getString("case_form");
				String ramSlots = rs2.getString("ram_slots");
				String socketType = rs2.getString("socket_type");
				pr = new MotherBoard(producer, model, price, info, quantity,
						img, chipset, ramSlots, socketType);
				pr.setId(productId);
				break;
			case "ram":
				rs2 = st.executeQuery("SELECT ram_type, ram_size FROM pcshop.rams WHERE product_id="
						+ productId + ";");
				rs2.next();
				String ramType = rs2.getString("ram_type");
				int ramSize = rs2.getInt("ram_size");
				pr = new Ram(producer, model, price, info, quantity, img,
						ramType, ramSize);
				pr.setId(productId);
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

	@Override
	public void updatePrice(Product p, double newPrice) {

		int productId = p.getId();
		String query = "UPDATE pcshop.products SET price= ? WHERE product_id=" + productId + ";";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			
			prst.setDouble(1, newPrice);
			prst.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public void updateQuantity(Product p, int newQuantity) {
		int productId = p.getId();
		String query = "UPDATE pcshop.products SET quantity= ? WHERE product_id=" + productId + ";";
		try (PreparedStatement prst = DBManager.getDBManager().getConnection().prepareStatement(query)){
			
			prst.setInt(1, newQuantity);
			prst.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
	
		
	}
	
	// method for productInfo.jsp
	// all info for specific product
}
