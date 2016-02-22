package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DBManager {

	//Fields:
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/?useSSL=false&allowMultiQueries=true";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String QUERIES_FILE_ADDRESS = "create_PCShop_database.txt";
	private static DBManager instance;
	private static String sql;
	
	
	//Constructor:
	private DBManager() throws ClassNotFoundException {
		Class.forName(DBManager.JDBC_DRIVER);
		this.readFile();
		createDatabase();
	
	}
		
	//Methods:
	public static DBManager getDBManager() throws ClassNotFoundException {
		if (DBManager.instance == null) {
			DBManager.instance = new DBManager();
			return DBManager.instance;
		} else {
			return DBManager.instance;
		}
						
	}
	
	private void readFile() {
	   
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(DBManager.QUERIES_FILE_ADDRESS));
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

	      DBManager.sql = sb.toString();
	 }

	private void createDatabase() {
		
		Connection conn = null;
		Statement stmt = null;
		
		
		try {
			conn = DriverManager.getConnection(DBManager.DB_URL, DBManager.USERNAME, DBManager.PASSWORD);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		
		
		
	}
	
	
}
