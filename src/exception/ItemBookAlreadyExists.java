package exception;

/**
 * L'exception <i>ItemBookAlreadyExists</i> est levée lorsque l'on essaye d'ajouter un <i>Book</i> qui existe déjà
 */

public class ItemBookAlreadyExists extends Exception {

	public ItemBookAlreadyExists(String message) {
		super(message);
	}
}
