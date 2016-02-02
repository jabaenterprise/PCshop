package exceptions;

public class SuchUserAlreadyExistsException extends Exception {

	//Fields:
	private static final long serialVersionUID = -4454606699366077812L;

	//Constructor:
	public SuchUserAlreadyExistsException(String message) {
		super(message);
	}
	
	
}
