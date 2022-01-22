package exceptions;

@SuppressWarnings("serial")
public class UtilityException extends Exception {
	String msg;

	public UtilityException(String msg) {
		this.msg = msg;
	}

	public String toString() {
		return msg;
	}
}
