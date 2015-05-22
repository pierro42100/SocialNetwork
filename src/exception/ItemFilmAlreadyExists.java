package exception;

/**
 * L'exception <i>ItemFilmAlreadExists</i> est levée lorsque l'on essaye d'ajouter un <i>Film</i> lorsque le Film existe déjà
 */

public class ItemFilmAlreadyExists extends Exception {

	public ItemFilmAlreadyExists(String message) {
		super(message);
	}
	
}
