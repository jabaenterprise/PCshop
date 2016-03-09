package database.daos;




import java.util.HashMap;
import java.util.HashSet;

import client.Client;

	public interface IClientDAO {
		
		
		
		public Client getClient(String eMailAddress);
		
		public HashSet<String> getClientEmails();
		
		public HashMap<String, String> getEMailsAndPasswords();
		
		public static IClientDAO getDAO(){
			
			return new DBClientDAO();
		}
		
}
