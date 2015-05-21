package avis;

import java.util.LinkedList;

/**
 * <p>
 * Un <i>Film</i> est une classe qui hérite de la classe <i>Item</i>. Elle possède donc les attributs de la classe abstraite <i>Item</i>
 * </p>
 * 
 * <p>Les attributs de <b>Film</b> sont : </p>
 * <p>
 * <i>director</i> : String qui correspond au nom du directeur du <i>Book</i>
 * </p>
 * <p>
 * <i>duration</i> : int qui correspond à la durée du film en minutes
 * </p>
 * 
 * <p>
 * <i>scenartist</i> : entier qui correspond au nom du scénariste du <i>Book</i>
 * </p>
 * 
 * <p>La méthode toString permet d'obtenir une représentation textuelle du film en cours.
 * Pour le moment, aucunes autres méthodes n'existe pour modifier le <i>Book</i>. Tout est fait dans le constructeur.
 * </p>
 */

public class Film extends Item {

	/**
	 * @uml.property  name="director"
	 */
	private String director;

	/**
	 * @uml.property  name="scenarist"
	 */
	private String scenarist;

	/**
	 * @uml.property  name="duration"
	 */
	private int duration;


	/**
	 * constructeur de <i>Film</i> 
	 */
	public Film(String title, String type, String director, String scenarist, int duration){
		
		reviews = new LinkedList<Review>();
		
		this.title= title;
		this.type = type;
		this.director = director;
		this.scenarist = scenarist;
		this.duration = duration;
		
	}

	/**
	 * Obtenir une représentation textuelle du <i>Film</i>.
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>Film</i> 
	 */
	@Override
	public String toString(){
		String phrase;
		phrase = title + "/" + type + "/" + director + "/"+ scenarist +"/"+ duration +"/" + this.note + "/";
		
		//On parcours tous les reviews du film
		for(Review r : reviews)
		{
			phrase+= r.toString();
		}
		
		return phrase;	 
	}


}
