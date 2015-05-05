package avis;

import java.util.LinkedList;


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
	public boolean addNewReview(String comment, float note, String pseudo){
		int nb = reviews.size(); //taille actuelle de la liste
		Review r = new Review(comment, note, pseudo, this);
		this.reviews.add(r);//ajout de la nouvelle review
		
		//Mise à jour de la note
		this.note = (note*nb + note)/(nb+1);//nouvelle note = (note en cours*nb note + note)/(note en cours + 1)
				
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
	 * Update la note du Review passé en paramêtre
	 * 
	 */
	public float updateReview(Review r, String comment, float note){
		
		float oldNote = r.getNote();//l'ancienne note du review qui doit être mise à jour
		
		r.setComment(comment);
		r.setNote(note);//nouvelle note
		
		float oldMoy = this.note; //ancienne Moyenne(note)
		
		float cacheMoy = (oldMoy - note)/(this.reviews.size()-1); //Moyenne tempo
		
		this.note = (cacheMoy*(this.reviews.size()-1)+note)/(this.reviews.size()); //nouvelle Moyenne(note)
		
		return this.note; //retourne la note mise à jour
	}
	
	/**
	 * Méthode qui recherche un <i>Review</i> parmis la liste de <i>Review</i> d'un <i>Item</i> à partir du pseudo du Member
	 * @return null si le <i>Review</i> n'existe par
	 * @return le <i>Review</i> qui a été créé par ce Member
	 */
	public Review findReview(String pseudo){
		
		for(Review r : reviews)
		{
			if(r.getPseudo().trim().equalsIgnoreCase(pseudo.trim()) )//si le pseudo correspond au pseudo du commentaire
			{
				return r; //if true return the member
			}
		}


		return null; //else return null
		
	}
	
	/**
	 * Méthode qui renvoit le titre de l'item
	 * @return titre de l'<i>Item</i>
	 */
	
	public String getTitle(){
		
		return this.title;
	}
	
	/**
	 * Méthode abstraite qui retourne un String de l'objet <i>Item</i>
	 * @return représentation textuelle de l'<i>Item</i>
	 */
	
	public abstract String toString();
	


}
