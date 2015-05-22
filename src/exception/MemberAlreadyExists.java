package exception;

/**
 * L'exception <i>MemberAlreadyExists</i> est levée lorsque l'on essaye de créer un <i>Member</i> qui aurait un pseudo qui existe déjà
 */

public class MemberAlreadyExists extends Exception {

	public MemberAlreadyExists(String message) {
		super(message);
	}
	
}
