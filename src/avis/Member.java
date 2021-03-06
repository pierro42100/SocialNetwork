package avis;


/**
 * @uml.dependency   supplier="avis.C"
 * @uml.dependency   supplier="avis.ConnectedUser"
 */

/**
 * La classe <i>Member</i> est la classe qui correspond à un membre du <i>SocialNetwork</i>.
 * Les attributs de cette classes sont :
 * 
 * - pseudo : String correspondant au pseudo du membre
 * - password : String correspondant au password du membre
 * - karma : float correspondant au karma du membre
 * - nbKarma : int correspondant au nombre de karma que le membre à reçu
 * - profil : String correspondant au profil du membre
 *
 */
public class Member {

	/** 
	 * @uml.property name ="pseudo"
	 */
	private String pseudo;

	/** 
	 * @uml.property name = "password"
	 */
	private String password;
	
	/**
	 *  @uml.property name="karma"
	 */
	private float karma = 2.5f;
	
	/**
	 * @uml.property  name="nbKarma"
	 */
	private int nbKarma = 1;
	
	/**
	 *  @uml.property  name="profil"
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
	 * Permet d'ajouter un note de karma au membre
	 * Le karma sera mise à jour en faisant une moyenne. 
	 * @param newKarma : le nouveau karma du membre
	 */
	public void addKarma(float newKarma)
	{
		//Moyenne du karma
		this.karma= ((karma*nbKarma)+newKarma)/(nbKarma+1);
		nbKarma++;
		
	}
	
	/**
	 * Permet de récupérer le karma d'un Member
	 * @return : le karma du <i>Member</i> (float)
	 */

	public float getKarma(){
		return this.karma;
	}
	
	/**
	 * Obtenir une représentation textuelle du <i>Member</i>
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>Member</i> 
	 */
	public String toString(){
		String phrase;
		phrase = pseudo+"("+karma+")./"+password+"/"+profil +"\n";
		return phrase;
		
	}


}
