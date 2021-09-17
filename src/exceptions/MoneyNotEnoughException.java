package exceptions;

public class MoneyNotEnoughException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage(int price, int money) {
		return ("What you want to buy costs " + price
		+ ", And you only have " + money);		
	}

}
