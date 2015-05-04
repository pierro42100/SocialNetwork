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
	 * @uml.property name="reviews"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="item:avis.Review"
	 */
	private LinkedList<Review> reviews;

	/**
	 * Méthode qui ajoute un nouveau commentaire <i>Review</i> à un <i>Item</i>
	 * @return un booléen qui indique si le <i>Review</i> a bien été ajouté
	 */
	public boolean addNewReview(String comment, float note, String pseudo){
		int nb = reviews.size(); //taille actuelle de la liste
		Review r = new Review(comment, note, pseudo, this);
		this.reviews.add(r);//ajout de la nouvelle review
		
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
