package database;

public class DBdemo {
	public static void main(String[] args) {
		try {
			DBManager.getDBManager();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
