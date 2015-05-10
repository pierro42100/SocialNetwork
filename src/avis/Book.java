package avis;

import java.util.LinkedList;

/**
 * <p>
 * Un <i>Book</i> est une classe qui hérite de la classe <i>Item</i>. Elle possède donc les attributs de la classe abstraite <i>Item</i>
 * </p>
 * 
 * <p>Les attributs de <b>Book</b> sont : </p>
 * <p>
 * <i>author</i> : String qui correspond au nom de l'auteur du <i>Book</i>
 * </p>
 * <p>
 * <i>pageNumber</i> : entier qui contient le nombre de pages du <i>Book</i>
 * </p>
 * 
 * <p>La méthode toString permet d'obtenir une représentation textuelle du livre en cours.
 * Pour le moment, aucunes autres méthodes n'existe pour modifier le <i>Book</i>. Tout est fait dans le constructeur.
 * </p>
 */


public class Book extends Item {

	/**
	 * @uml.property  name="author"
	 */
	private String author;
	
	/**
	 * @uml.property  name="pageNumber"
	 */
	private int pageNumber;

	/**
	 * constructeur de <i>Book</i> 
	 */
	public Book(String title, String type, String author, int pageNumber){
		
		reviews = new LinkedList<Review>();
		
		this.title = title;
		this.type = type;
		this.author = author;
		this.pageNumber = pageNumber;
	}
	
	/**
	 * Obtenir une représentation textuelle du <i>Book</i>.
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>Book</i> 
	 */
	@Override
	public String toString(){
		String phrase;
		phrase = title + " est un livre écrit par " + author + ". Le type du livre est : "+ type +". Il contient "+ pageNumber + " pages ";
		phrase += "\n Commentaires du livre :\n";
		
		//On parcours tous les reviews du livre
		for(Review r : reviews)
		{
			phrase+= r.toString();
		}
		
		return phrase;	
	}
}
