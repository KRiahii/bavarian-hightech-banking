package bavaria.hightech.exceptions;

/**
 * 
 * throws MoneyExceptions
 *
 */
@SuppressWarnings("serial")
public class MoneyException extends Exception {
	public MoneyException() {
		super();
	}

	public MoneyException(String s) {
		super(s);
	}
}