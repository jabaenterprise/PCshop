package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/?useSSL=false&allowMultiQueries=true";
	private static final String USERNAME = "ittstudent";
	private static final String PASSWORD = "ittstudent-123";
	public static final String DBNAME  = "Season5_mashina";
	private static final String QUERIE_FILE_ADDRESS = ".\\createDatabase.txt";
	private static DBManager instance;
	private static Connection conn;
	private static String databaseDeploymentQueries = " CREATE DATABASE IF NOT EXISTS Season5_mashina;"
			+ " CREATE TABLE IF NOT EXISTS Season5_mashina.clients (client_id INT auto_increment, first_name VARCHAR(45) NOT NULL, family_name VARCHAR(45) NOT NULL, email VARCHAR(45) NOT NULL, password VARCHAR(45) NOT NULL, address VARCHAR(100) NOT NULL, phone VARCHAR(45) NOT NULL, money DOUBLE, CONSTRAINT client_id_PK PRIMARY KEY(client_id));"
			+ " CREATE TABLE IF NOT EXISTS Season5_mashina.product_types (type_id INT auto_increment, type VARCHAR(45) NOT NULL, CONSTRAINT type_id_PK PRIMARY KEY(type_id));"
			+ " CREATE TABLE IF NOT EXISTS Season5_mashina.products (product_id INT auto_increment, producer VARCHAR(45) NOT NULL, model VARCHAR(45) NOT NULL, price DOUBLE, info MEDIUMTEXT, quantity INT, img_url VARCHAR(100), type_id INT, CONSTRAINT product_id_PK PRIMARY KEY(product_id), CONSTRAINT type_id_FK FOREIGN KEY(type_id) REFERENCES Season5_mashina.product_types(type_id));"
			+ " CREATE TABLE IF NOT EXISTS Season5_mashina.products_in_carts (client_id INT, product_id INT, quantity_in_cart INT, CONSTRAINT products_in_carts_PK PRIMARY KEY(client_id, product_id), CONSTRAINT client_id_cart_FK FOREIGN KEY(client_id) REFERENCES Season5_mashina.clients(client_id), CONSTRAINT product_id_cart_FK FOREIGN KEY(product_id) REFERENCES Season5_mashina.products(product_id));"
			+ "CREATE TABLE IF NOT EXISTS Season5_mashina.cases (product_id INT, case_form VARCHAR(45) NOT NULL, case_size VARCHAR(45) NOT NULL, CONSTRAINT product_id_cases_PK PRIMARY KEY(product_id), CONSTRAINT product_id_cases_FK FOREIGN KEY(product_id) REFERENCES Season5_mashina.products(product_id));"
			+ " CREATE TABLE IF NOT EXISTS Season5_mashina.cpus (product_id INT, number_of_cores INT NOT NULL, clock_speed DOUBLE NOT NULL, cpu_socket VARCHAR(45) NOT NULL, CONSTRAINT product_id_cpus_PK PRIMARY KEY(product_id), CONSTRAINT product_id_cpus_FK FOREIGN KEY(product_id) REFERENCES Season5_mashina.products(product_id));"
			+ " CREATE TABLE IF NOT EXISTS Season5_mashina.gpus (product_id INT, memory_size INT NOT NULL, max_resolution VARCHAR(45) NOT NULL, output_interface VARCHAR(45) NOT NULL, CONSTRAINT product_id_gpus_PK PRIMARY KEY(product_id), CONSTRAINT product_id_gpus_FK FOREIGN KEY(product_id) REFERENCES Season5_mashina.products(product_id));"
			+ " CREATE TABLE IF NOT EXISTS Season5_mashina.hard_drives (product_id INT, hd_type VARCHAR(45), size DOUBLE NOT NULL, capacity INT NOT NULL, CONSTRAINT product_id_hds_PK PRIMARY KEY(product_id), CONSTRAINT product_id_hds_FK FOREIGN KEY(product_id) REFERENCES Season5_mashina.products(product_id));"
			+ " CREATE TABLE IF NOT EXISTS Season5_mashina.monitors (product_id INT, size DOUBLE NOT NULL, refresh_rate INT NOT NULL, matrix_type VARCHAR(45) NOT NULL, CONSTRAINT product_id_mons_PK PRIMARY KEY(product_id), CONSTRAINT product_id_mons_FK FOREIGN KEY(product_id) REFERENCES Season5_mashina.products(product_id));"
			+ " CREATE TABLE IF NOT EXISTS Season5_mashina.mother_boards (product_id INT, chipset VARCHAR(45) NOT NULL, ram_slots INT NOT NULL, socket VARCHAR(45) NOT NULL, CONSTRAINT product_id_mbs_PK PRIMARY KEY(product_id), CONSTRAINT product_id_mbs_FK FOREIGN KEY(product_id) REFERENCES Season5_mashina.products(product_id));"
			+ " CREATE TABLE IF NOT EXISTS Season5_mashina.rams (product_id INT, ram_type VARCHAR(45) NOT NULL, size INT NOT NULL, CONSTRAINT product_id_rams_PK PRIMARY KEY(product_id), CONSTRAINT product_id_rams_FK FOREIGN KEY(product_id) REFERENCES Season5_mashina.products(product_id));"
			+ " CREATE TABLE IF NOT EXISTS Season5_mashina.admins (admin_id INT auto_increment, email VARCHAR(45) NOT NULL, password VARCHAR(45) NOT NULL, CONSTRAINT admin_id_PK PRIMARY KEY(admin_id)); USE Season5_mashina;"
			+ "INSERT INTO product_types(type) VALUE(\"case\");"
			+ "INSERT INTO product_types(type) VALUE(\"cpu\");"
			+ "INSERT INTO product_types(type) VALUE(\"gpu\");"
			+ "INSERT INTO product_types(type) VALUE(\"hard drive\");"
			+ "INSERT INTO product_types(type) VALUE(\"monitor\");"
			+ "INSERT INTO product_types(type) VALUE(\"mother board\");"
			+ "INSERT INTO product_types(type) VALUE(\"ram\");"
			+ "INSERT INTO admins(email,password)VALUES(\"admin@admin.com\",\"admin1\"); ";	
	
	private DBManager() {
		try {
			Class.forName(DBManager.JDBC_DRIVER);
			this.conn = DriverManager.getConnection(DBManager.DB_URL, DBManager.USERNAME, DBManager.PASSWORD);
		
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load database driver: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database: " + e.getMessage());
		}
	}
		
	
	public static synchronized DBManager getDBManager() throws ClassNotFoundException {
		if (DBManager.instance == null) {
		
			DBManager.instance = new DBManager();
			deployDatabase();
		} 
		return DBManager.instance;
	}
	
	public Connection getConnection() {
		return this.conn;
	}
	
	public void closeConnection() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e.getMessage());
		}
	}
		
	private static void deployDatabase() {
		
		//readQueryFile();
		Statement stmt = null;
				
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(DBManager.databaseDeploymentQueries);
			System.out.println("eCommerce database successfully deployed.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("Coonection to eCommerce database is: " + e.getMessage());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static void readQueryFile() {
		   
		File f = new File(DBManager.QUERIE_FILE_ADDRESS);
		try {
			System.out.println();
			System.out.println();
			System.out.println("Attempting to read from file in: "+f.getCanonicalPath());
			System.out.println();
			System.out.println();
		} catch (IOException e2) {
			System.out.println();
			System.out.println();
			System.out.println("BAAAAAAAAAAA: " + e2.getMessage());
			System.out.println();
			System.out.println();
		}
		FileReader fr = null;
		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader br = null;
		br = new BufferedReader(fr);
   
        StringBuilder sb = new StringBuilder();
        String line;
		try {
			line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

      DBManager.databaseDeploymentQueries = sb.toString();
 }


}
