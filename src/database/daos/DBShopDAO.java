package database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

public class DBShopDAO implements IShopDAO {

	@Override
	public void addProductToShop(Product p) {
		
		String addType ="INSERT INTO IF NOT EXISTS pcshop.product_types (type_name) VALUES (?);";
		String addProduct ="INSERT INTO pcshop.products (producer_name,model_name,price,product_info,quantity,type_id)"
				+ "VALUES(?,?,?,?,?,SELECT type_id FROM pcshop.product_types WHERE type_name=?)";
		
		
		
		try {
			PreparedStatement type = DBManager.getDBManager().getConnection().prepareStatement(addType.toString());
			type.setString(1, p.getType().toString());
			type.execute();
			type.close();
			PreparedStatement prod = DBManager.getDBManager().getConnection().prepareStatement(addProduct);
			prod.setString(1, p.getProducer());
			prod.setString(2, p.getModel());
			prod.setDouble(3, p.getPrice());
			prod.setString(4, p.getInfo());
			prod.setInt(5, p.getQuantity());
			prod.setString(6, p.getType().toString());
			prod.execute();
			prod.close();
			String forType = new String();
			PreparedStatement pType = DBManager.getDBManager().getConnection().prepareStatement(forType);
			
			switch (p.getType()) {
			case CASE:
				forType = "INSERT INTO pcshop.cases (product_id,case_form,case_size)"
						+ "VALUES((SELECT prduct_id FROM pcshop.products WHERE model_name = ?),?,?)";
				pType.setString(1, ((Case)p).getModel());	
				pType.setString(2, ((Case)p).getForm());	
				pType.setString(3, ((Case)p).getSize());	
				pType.execute();
				break;

			case CPU:
				forType = "INSERT INTO pcshop.cpus (product_id,number_of_cores,clock_speed,socket)"
						+ "VALUES((SELECT prduct_id FROM pcshop.products WHERE model_name = ?),?,?,?)";
				pType.setString(1, ((Cpu)p).getModel());	
				pType.setInt(2,((Cpu)p).getNumberOfCores());	
				pType.setDouble(3,((Cpu)p).getClockSpeed());
				pType.setString(4,((Cpu)p).getSocket());
				pType.execute();

				break;
			case GPU:
				forType = "INSERT INTO pcshop.gpus (product_id,memory_size,output_interface)"
						+ "VALUES((SELECT prduct_id FROM pcshop.products WHERE model_name = ?),?,?,?)";
				pType.setString(1, ((Gpu)p).getModel());	
				pType.setInt(2,((Gpu)p).getMemorySize());	
				pType.setString(3,((Gpu)p).getOutputInterface());
				pType.setString(4,((Gpu)p).getMaxResolution());
				pType.execute();
				break;
			case HD:
				forType = "INSERT INTO pcshop.hard_drives (product_id,drive_size,drive_capacity,drive_type)"
						+ "VALUES((SELECT prduct_id FROM pcshop.products WHERE model_name = ?),?,?,?)";
				pType.setString(1, ((HardDrive)p).getModel());	
				pType.setInt(2,((HardDrive)p).getSize());	
				pType.setInt(3,((HardDrive)p).getCapacity());
				pType.setString(4,((HardDrive)p).getHdType());
				pType.execute();

				break;
			case MB:
				forType = "INSERT INTO pcshop.mother_boards (product_id,chipset,ram_slots,socket_type)"
						+ "VALUES((SELECT prduct_id FROM pcshop.products WHERE model_name = ?),?,?,?)";
				pType.setString(1, ((MotherBoard)p).getModel());	
				pType.setString(2,((MotherBoard)p).getChipset());	
				pType.setString(3,((MotherBoard)p).getRamSlots());
				pType.setString(4,((MotherBoard)p).getSocketType());
				pType.execute();

				break;

			case MON:
				forType = "INSERT INTO pcshop.monitors (product_id,screen_size,refresh_rate,matrix_type)"
						+ "VALUES((SELECT prduct_id FROM pcshop.products WHERE model_name = ?),?,?,?)";
				pType.setString(1, ((Monitor)p).getModel());	
				pType.setDouble(2,((Monitor)p).getSize());	
				pType.setInt(3,((Monitor)p).getRefreshRate());
				pType.setString(4,((Monitor)p).getMatrixType());
				pType.execute();
				break;

			case RAM:
				forType = "INSERT INTO pcshop.rams (product_id,ram_type,ram_size)"
						+ "VALUES((SELECT prduct_id FROM pcshop.products WHERE model_name = ?),?,?)";
				pType.setString(1, ((Ram)p).getModel());	
				pType.setString(2,((Ram)p).getRamType());	
				pType.setInt(3,((Ram)p).getSize());

				pType.execute();
			

				break;

			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	/*
	 * INSERT INTO `pcshop`.`products` (`product_id`, `producer_name`,
	 * `model_name`, `price`, `product_info`, `quantity`, `type_id`) VALUES
	 * (<{product_id: }>, <{producer_name: }>, <{model_name: }>, <{price: }>,
	 * <{product_info: }>, <{quantity: }>, <{type_id: }>); ent)
	 */

	@Override
	public void addNewClient(Client client) {

		String addClient = "INSER INTO pcshop.clients (first_name,family_name,email,pass_word,phone_number,money, city, address)VALUES(?,?,?,?,?,?,?,?)";

		try {

			PreparedStatement clientDB = DBManager.getDBManager().getConnection().prepareStatement(addClient);
			clientDB.setString(1, client.getFirstName());
			clientDB.setString(2, client.getLastName());
			clientDB.setString(3, client.getEmail());
			clientDB.setString(4, client.getPassword());
			clientDB.setString(5, client.getPhoneNumber());
			clientDB.setString(6, String.valueOf(client.getMoney()));
			clientDB.setString(7, client.getCity());
			clientDB.setString(8, client.getAddress());

			clientDB.execute();

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Something went wrong" + e.getMessage());
		}

	}

	@Override
	public ArrayList<Product> serchProducts(String searchWord) {
		
		ArrayList<Product> results = new ArrayList<Product>();
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DBManager.getDBManager().getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT product_id, producer_name, model_name, price, product_info, quantity_in_cart, type FROM pcshop.products WHERE producer='" + searchWord + "' OR model='" + searchWord + "';");
			Product pr = null;
			int quantity = 0;
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String producer = rs.getString("producer_name");
				String model = rs.getString("model_name");
				double price = rs.getDouble("price");
				quantity = rs.getInt("quantity_in_cart");
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
							
				results.add(pr);
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
	return results;
}
	

}
