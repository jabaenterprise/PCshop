package database.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import client.Client;
import database.DBManager;
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

		String addClient = "INSER INTO pcshop.clients (first_name,last_name,email,password,phone_number,city, address)VALUES(?,?,?,?,?,?,?)";

		try {

			PreparedStatement clientDB = DBManager.getDBManager().getConnection().prepareStatement(addClient);
			clientDB.setString(1, client.getFirstName());
			clientDB.setString(2, client.getLastName());
			clientDB.setString(3, client.getEmail());
			clientDB.setString(4, client.getPassword());
			clientDB.setString(5, client.getPhoneNumber());
			clientDB.setString(6, client.getCity());
			clientDB.setString(7, client.getAddress());

			clientDB.execute();

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Something went wrong" + e.getMessage());
		}

	}

}
