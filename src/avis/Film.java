package avis;


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
	}

	/**
	 * Obtenir une représentation textuelle du <i>Film</i>.
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>Film</i> 
	 */
	@Override
	public String toString(){
		return "";	
	}




}
