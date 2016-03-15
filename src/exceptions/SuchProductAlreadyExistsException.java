package exceptions;

public class SuchProductAlreadyExistsException extends Exception {

	//Fields:
	private static final long serialVersionUID = -1772608828895215412L;

	
	//Constructor:
	public SuchProductAlreadyExistsException(String message) {
		super(message);
	}
}
