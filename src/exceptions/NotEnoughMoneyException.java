package exceptions;

public class NotEnoughMoneyException extends Exception {

	
	private static final long serialVersionUID = 1783017797965554864L;
	public  NotEnoughMoneyException(String message){
		super(message);
	}
}
