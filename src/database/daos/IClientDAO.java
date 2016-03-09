package database.daos;

import client.Client;

	public interface IClientDAO {
		
		
		
		public Client getClient(String eMailAddress);
		
			
		public static IClientDAO getDAO(){
			
			return new DBClientDAO();
		}
		
}
