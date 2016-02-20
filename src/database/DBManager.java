package database;

import java.sql.*;

public class DBManager {

	//Fields:
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/ITTalents";
	private static final String USERNAME = "pcshop";
	private static final String PASSWORD = "pcshop";
	private static DBManager instance;
	private static String sql;
	
	
	//Constructor:
	private DBManager() throws ClassNotFoundException {
		Class.forName(DBManager.JDBC_DRIVER);
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
	
	
	public static void main(String[] args) {
		
		try {
			DBManager.getDBManager();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		
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
