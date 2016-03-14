package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/?useSSL=false&allowMultiQueries=true";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String QUERIE_FILE_ADDRESS = "create_eCommerce_database.txt";
	private static DBManager instance;
	private Connection conn;
	private static String databaseDeploymentQueries;
	
	
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
		
	public void deployDatabase() {
		
		readQueryFile();
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
	
	private void readQueryFile() {
		   
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(DBManager.QUERIE_FILE_ADDRESS));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
   
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
