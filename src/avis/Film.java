package avis;

import java.util.LinkedList;

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
		phrase = title + " est un film " + type + ". Le directeur du film est " + director + ". Le scénariste du film est : "+ scenarist +". Il dure "+ duration + " minutes.";
		phrase += "\n Commentaires du film :\n";
		
		//On parcours tous les reviews du film
		for(Review r : reviews)
		{
			phrase+= r.toString();
		}
		
		return phrase;	 
	}


}
