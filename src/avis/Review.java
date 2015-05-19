package avis;




public class Review {

	/**
	 * @uml.property  name="pseudo"
	 */
	private String pseudo;

	/**
	 * @uml.property  name="note"
	 */
	private float note = 0.0f;
	
	/**
	 * @uml.property  name="karmaMembre"
	 * Correspond au karma du membre lors de l'ajout du commentaire
	 */
	private float karmaMembre = 0.0f; 

	/**
	 * @uml.property  name="comment"
	 */
	private String comment;

	/**
	 * @uml.property  name="item"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="reviews:avis.Item"
	 */
	private Item item = null;

	/**
	 * constructeur de <i>Review</i> 
	 * 
	 */

	public Review(String comment, float note, String pseudo, Item item, float karmaMembre){
		this.pseudo = pseudo;
		this.note = note;
		this.comment = comment;
		this.item = item;
		this.karmaMembre = karmaMembre;
	}



	/**
	 * Obtenir le pseudo du <i>Review></i>
	 * @return le pseudo
	 */
	public String getPseudo(){

		return this.pseudo;
	}
	
	/**
	 * Obtenir le pseudo du <i>Review></i>
	 * @return le pseudo
	 */
	public float getNote(){

		return this.note;
	}
	
	/**
	 * Obtenir le karmaMambre du <i>Review></i>
	 * @return le karmaMembre
	 */
	public int getKarmaMembre(){

		return (int)(this.karmaMembre) ;
	}

	/**
	 * Set le comment du <i>Review></i>
	 * 
	 */
	public void setComment(String comment){

		this.comment = comment;
	}
	
	/**
	 * Set la note du <i>Review></i>
	 * 
	 */
	public void setNote(float note){

		this.note = note;
	}



	/**
	 * Obtenir une représentation textuelle du <i>Review</i>.
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>Review</i> 
	 */
	public String toString(){
		String phrase;
		phrase = pseudo+"/"+ note+"/"+ karmaMembre +"/"+  comment +"\n";
		return phrase;	
	}

}
