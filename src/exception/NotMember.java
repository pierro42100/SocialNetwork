package exception;

/**
 * L'exception <i>NotMember</i> est levée lorsque un membre utilisé n'existe pas 
 */

public class NotMember extends Exception {
	
	public NotMember(String message) {
		super(message);
	}

}
