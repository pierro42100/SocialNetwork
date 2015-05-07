package test;

import avis.SocialNetwork;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;

/** 
 * @author J. Tiron, P. Chovelon
 * @date avril 2015
 * @version V1.0
 */

public class TestSocialNetwork {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	    String nbTests = "" + 0;
	    String nbErreurs = "" + 0;
	    String [] resultats = new String[] {"0", "0"};
	      
	    TestsInitialisation.main(resultats);
	      
	    System.out.println("\n\n ***************************************\n");

	    TestsAddMember.main(resultats);
	      
	    System.out.println("\n\n ***************************************\n");

	    TestsAddItemFilm.main(resultats);
	      
	    System.out.println("\n\n ***************************************\n");

	    TestsReviewItemFilm.main(resultats);
	    
	    System.out.println("\n\n ***************************************\n");

	    TestsAddItemBook.main(resultats);
	      
	    System.out.println("\n\n ***************************************\n");

	    TestsReviewItemBook.main(resultats);
	      
	    System.out.println("\n\n ***************************************\n");
	      
	    // .... d'autres appels à des tests
	      
	    System.out.println("Bilan des Tests :   " + resultats[1] + 
	                       " erreur(s) / " +  resultats[0] + " tests effectués");
	      
	}
}
