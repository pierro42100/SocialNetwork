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
	}
	
	/**
	 * Obtenir une représentation textuelle du <i>Book</i>.
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>Book</i> 
	 */
	@Override
	public String toString(){
		return "";	
	}
}
