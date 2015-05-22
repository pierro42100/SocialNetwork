package exception;

/**
 * L'exception <i>SameMember</i> est levée lorsque un utilisateur essaye de noter ses propres avis.
 */

public class SameMember extends Exception {

	public SameMember(String message) {
		super(message);
	}
}
