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
	 * @uml.property name="karma"
	 */
	private float karma = 2.5f;
	
	/**
	 * @uml.property name="nbKarma"
	 */
	private int nbKarma = 1;
	
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
	 * Permet d'ajouter un note de karma au membre
	 * Le karma sera mise à jour en faisant une moyenne. 
	 * Pour le moment, il n'est pas possible de modifier le karma ajouté avant
	 */
	public void addKarma(float newKarma)
	{
		
		this.karma= ((karma*nbKarma)+newKarma)/(nbKarma+1);
		nbKarma++;
		
	}
	
	/**
	 * Permet de récupérer le karma d'un Member
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
