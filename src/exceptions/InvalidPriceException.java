package exceptions;

public class InvalidPriceException extends Exception {

	//Fields:
	private static final long serialVersionUID = 6932141145472640888L;

	//Constructor:
	public InvalidPriceException(String message) {
		super(message);
	}

}
