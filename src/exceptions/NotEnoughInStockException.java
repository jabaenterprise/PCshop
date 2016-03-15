package exceptions;

public class NotEnoughInStockException extends Exception {

	//Fields:
	private static final long serialVersionUID = -739538912467752124L;

	
	//Constructor:
	public NotEnoughInStockException(String message){
		super(message);
		
	}
	
	
}
