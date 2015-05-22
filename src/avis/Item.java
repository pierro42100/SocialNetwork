package avis;

import java.util.LinkedList;
/**
 * <p>
 * Un <i>Item</i> est une classe abstraite qui permet de factoriser certaines méthodes et certains attributs.
 * Les classes <i>Book</i> et <i>Film</i> héritent de cette classe.
 * </p>
 * 
 * <p>Les attributs de <b>Item</b> sont : </p>
 * <p>
 * <i>title</i> : String qui correspond au nom de l'<i>Item</i>
 * </p>
 * <p>
 * <i>type</i> : String qui correspond au type de l'<i>Item</i>
 * </p>
 * 
 * <p>
 * <i>note</i> : float qui correspond à la note moyenne l'<i>Item</i>
 * </p>
 * 
 * <p>
 * <i>review</i> : LinkedList qui contient tous les commentaires associés à cet <i>Item</i>
 * </p>
 * 
 * <p>Les méthodes de <b>Item</b> permetttent d'ajouter un nouveau commentaire à l'<i>Item</i> en cours, de mettre à jour un commentaire et de trouver un commentaire parmi la liste.</p> 
 * </p>
 * 
 * <p>Il existe également des méthodes permettant de récupérer certains attributs (get)</p>
 * <p>La méthode toString est déclarée. Elle devra être redéfinie par les classes qui héritent de <i>Item</i>
 */

public abstract class Item {

	/** 
	 * @uml.property name="title"
	 */
	protected String title;
	/** 
	 * @uml.property name="type"
	 */
	protected String type;
	/** 
	 * @uml.property name="note"
	 */
	protected float note;
	/** 
	 * @uml.property name="reviews"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="item:avis.Review"
	 */
	protected LinkedList<Review> reviews;

	/**
	 * Méthode qui ajoute un nouveau commentaire <i>Review</i> à un <i>Item</i>
	 * @return un booléen qui indique si le <i>Review</i> a bien été ajouté
	 */
	public boolean addNewReview(String comment, float note, String pseudo, float karma){
		int nb = 0; //taille actuelle de la liste qui correspond aux karmas
		
		for(Review r : reviews)
		{
			nb += r.getKarmaMembre(); 
		}
		
		Review r = new Review(comment, note, pseudo, this, karma);
		this.reviews.add(r);//ajout de la nouvelle review

		//Mise à jour de la note
		this.note = (this.note*nb + note*(int)karma)/(nb+(int)karma);//nouvelle note = (note en cours*nb note + note)/(note en cours + 1)

		//test de l'ajout
		if(nb == reviews.size() + 1)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * Update la note du Review passée en paramêtre
	 * 
	 */
	public float updateReview(Review r, String comment, float note, float karma){
			
		if(this.reviews.size() != 1)
		{
			int nb = 0; //nb de karma pour ce review
			
			for(Review rev : reviews)
			{
				nb += rev.getKarmaMembre(); 
			}
			
			//on récupère la note du review en cours
			float oldNote = r.getNote();//l'ancienne note du review qui doit être mise à jour
			//on récupère le karma du review en cours
			int oldKarma = r.getKarmaMembre();
			
			//on change la note du review et le commentaire et le karma
			r.setComment(comment);
			r.setNote(note);//nouvelle note
			r.setKarmaMembre(karma);//nouveau karma de la personne qui update le review
			
			float oldMoy = this.note; //ancienne Moyenne(note)

			float cacheMoy = (oldMoy*(nb)-oldNote*oldKarma)/(nb-oldKarma); //Moyenne tempo
			
			this.note = (cacheMoy*(nb-oldKarma)+note*(int)karma)/(nb-oldKarma+(int)karma); //nouvelle Moyenne(note)
			
			return this.note; //retourne la note mise à jour
		}
		else
		{
			r.setComment(comment);
			r.setNote(note);//nouvelle note
			this.note = note;
			return this.note;
		}
		
	}

	/**
	 * Méthode qui recherche un <i>Review</i> parmis la liste de <i>Review</i> d'un <i>Item</i> à partir du pseudo du Member
	 * @return null si le <i>Review</i> n'existe par
	 * @return le <i>Review</i> qui a été créé par ce Member
	 */
	public Review findReview(String pseudo){

		for(int i = 0; i < this.reviews.size(); i++)
		{
			if(reviews.get(i).getPseudo().equalsIgnoreCase(pseudo))//si le pseudo correspond au pseudo du commentaire
			{

				return reviews.get(i); //if true return the member

			}	
		}

		return null; //else return null

	}

	/**
	 * Méthode qui renvoit le titre de l'item
	 * @return String : titre de l'<i>Item</i>
	 */

	public String getTitle(){

		return this.title;
	}
	/**
	 * Méthode qui renvoit la note
	 * @return float note
	 */

	public float getNote(){

		return this.note;
	}

	/**
	 * Méthode abstraite qui retourne un String de l'objet <i>Item</i> (Book ou Film)
	 * @return String : représentation textuelle de l'<i>Item</i>
	 */

	public abstract String toString();


}
