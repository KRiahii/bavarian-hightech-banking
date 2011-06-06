package bavaria.hightech.exceptions;

@SuppressWarnings("serial")
public class FormaterFailException extends RuntimeException {
	public FormaterFailException() {
		super();
	}

	public FormaterFailException(String s) {
		super(s);
	}
}
