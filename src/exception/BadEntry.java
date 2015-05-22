package exception;

/**
 * L'exception <i>BadEntry</i> est levée lorsque les informations demandées (pseudo, password,...) ne sont pas correctes
 */

public class BadEntry extends Exception {

	public BadEntry(String message) {
		super(message);
	}
}
