package exceptions;

public class InvalidQuantityException extends Exception {

	//Fields:
	private static final long serialVersionUID = -8796232460137164724L;

	//Constructor:
	public InvalidQuantityException(String message) {
		super(message);
	}
	
}
