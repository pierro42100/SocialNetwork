package avis;


/**
 * @uml.dependency   supplier="avis.C"
 * @uml.dependency   supplier="avis.ConnectedUser"
 */
public class Member {

	/** 
	 * @uml.property name="pseudo"
	 */
	private String pseudo;

	/** 
	 * @uml.property name="password"
	 */
	private String password;
	

	/**
	 * @uml.property  name="profil"
	 */
	private String profil;
	
	/**
	 * constructeur de <i>Member</i> 
	 * 
	 */

	public Member(String pseudo, String password, String profil){
		this.password = password;
		this.pseudo = pseudo;
		this.profil = profil;
	}

	/**
	 * Obtenir le <i>pseudo</i> du <i>Member</i>
	 * 
	 * @return le String pseudo 
	 */
	
	public String getPseudo(){
	
		return this.pseudo;
	}
	
	/**
	 * Obtenir le <i>password</i> du <i>Member</i>
	 * 
	 * @return le String correspondant au password 
	 */
	
	public String getPassword(){
	
		return this.password;
	}
	
	/**
	 * Obtenir une représentation textuelle du <i>Member</i>
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>Member</i> 
	 */
	public String toString(){
		String phrase;
		phrase = "Pseudo "+pseudo+".Password :"+password+". Profil :"+profil +"\n";
		return phrase;
		
	}


}
