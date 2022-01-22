package exceptions;

@SuppressWarnings("serial")
public class PageObjectException extends Exception {
	String msg;

	public PageObjectException(String msg) {
		this.msg = msg;
	}

	public String toString() {
		return msg;
	}
}
