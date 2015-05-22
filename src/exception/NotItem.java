package exception;

/**
 * L'exception <i>NotItem</i> est levée lorsque l'<i>Item</i> n'existe pas
 */

public class NotItem extends Exception {

	public NotItem(String message) {
		super(message);
	}
}
