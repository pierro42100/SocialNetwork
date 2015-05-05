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

public class TestsAddItemBook {
	
	public static int addItemBookBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		// vérifie que l'ajout d'un livre (pseudo, pwd, titre...) est refusé (levée de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passé en paramètre

		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de livres a été modifié");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int addItemBookOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest){
		int nbBooks = sn.nbBooks();
		try{
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPages);
			if (sn.nbBooks() != nbBooks+1) {
				System.out.println("Test " + idTest + " :  le nombre de livres n'a pas été correctement incrémenté");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int addItemBookAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest,String messErreur){
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (ItemBookAlreadyExists e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception ItemBookAlreadyExists a bien été levée mais le nombre de livres a été modifié");
				return 1;
			}
			else
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addItemBookNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest,String messErreur){
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPages);
			return 0;
		}
		catch (NotMember e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de livres a été modifié");
				return 1;
			}
			else
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static void main(String[] args) {

		int nbBooks = 0;
		int nbTests = 0;
		int nbErreurs = 0;
		
		System.out.println("Tests  ajouter des livres au réseau social");
		
		SocialNetwork sn = new SocialNetwork();	
		
		//ajout d'un membre Paul
		try{
		sn.addMember("Paul", "paul", "sexy");
		}
		catch(MemberAlreadyExists e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE MemberAlreadyExists: Impossible");
		}
		catch(BadEntry e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE BadEntry: Impossible");
		}
		
		// <=> fiche numéro 1
		// tentative d'ajout de livres avec entrées "incorrectes"
		
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, null, "paul", "ouioui", "Documentaire", "Henris dès", 120, "3.1B", "L'ajout d'un livre dont le pseudo n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "", "paul", "ouioui", "Documentaire", "Edward Snowden", 120, "3.2B", "L'ajout d'un livre dont le pseudo fait moins de 1 caractère est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Paul", null, "ouioui", "Documentaire", "Edward Snowden", 120, "3.3B", "L'ajout d'un livre dont le password n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Paul", "pa", "ouioui", "Documentaire", "Edward Snowden", 120, "3.4B", "L'ajout d'un livre dont le password fait moins de 4 caractères est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Paul", "paul", null, "Documentaire", "Edward Snowden", 120, "3.5B", "L'ajout d'un livre dont le titre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Paul", "paul", "", "Documentaire", "Edward Snowden", 120, "3.6B", "L'ajout d'un livre dont le livre fait moins de 1 caractère est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Paul", "paul", "ouioui", null, "Edward Snowden", 120, "3.7B", "L'ajout d'un livre dont le genre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Paul", "paul", "ouioui", "Documentaire", null, 120, "3.8B", "L'ajout d'un livre dont le réalisateur n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Paul", "paul", "ouioui", "Documentaire", "Edward Snowden", -120, "3.9B", "L'ajout d'un livre dont le nombre de page n'est pas positif est accepté");
		
		// <=> fiche numéro 2
		// ajout de 2 livres avec des paramètres "correctes"
		nbTests++;
		nbErreurs += addItemBookOKTest ( sn, "Paul", "paul", "ouioui", "Documentaire", "Edward Snowden", 120, "4.1.a");
		nbTests++;
		nbErreurs += addItemBookOKTest ( sn, "Paul", "paul", "Babar", "Horreur", "Julien Tiron", 120, "4.1.b");
		
		// tentative d'ajout par un non-membre
		nbTests++;
		nbErreurs += addItemBookNotMemberTest ( sn, "Vanessa", "paul", "Le fabuleux Destin d'Amélie Poulain", "Fiction", "Yann Tiersen", 120, "4.2", "L'ajout d'un livre par un non-membre est accepté");
		
		// tentative d'ajout par un membre avec un password erroné
		nbTests++;
		nbErreurs += addItemBookNotMemberTest ( sn, "Paul", "patate", "Le fabuleux Destin d'Amélie Poulain", "Fiction", "Yann Tiersen", 120, "4.3", "L'ajout d'un livre par un membre dont le password est erroné est accepté");

		// tentative d'ajout d'un livre déjà existant
		nbTests++;
		nbErreurs += addItemBookNotMemberTest ( sn, "Paul", "paul", "La Terre Vue Du Ciel", "Documentaire", "Edward Snowden", 120, "4.4", "L'ajout d'un livre déjà existant est accepté");

		// bilan du test de addItemBook
		System.out.println("TestsAddItemBook :   " + nbErreurs + 
                       " erreur(s) / " +  nbTests + " tests effectués");
 
		// ajouts au bilan en cours si le bilan est passé en paramètre
		if ((args != null) && (args.length == 2)) {        
			nbTests = nbTests + new Integer(args[0]);
			nbErreurs = nbErreurs + new Integer(args[1]);       
			args[0] = "" + nbTests;
			args[1] = "" + nbErreurs;
		}
	}
}
