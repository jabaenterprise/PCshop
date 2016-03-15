package database;

public class DeployDatabase {

	
	public static void main(String[] args) {
				
		DBManager dbm = null;
		try {
			dbm = DBManager.getDBManager();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
			dbm.deployDatabase();
	}


}
