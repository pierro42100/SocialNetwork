package avis;


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
		phrase += "Commentaires du livre :\n";
		
		//On parcours tous les reviews du livre
		for(Review r : reviews)
		{
			phrase+= r.toString();
		}
		
		return phrase;	
	}
}
