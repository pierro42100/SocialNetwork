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

public class TestsAddItemFilm {
	
	public static int addItemFilmBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){
		// vérifie que l'ajout d'un film (pseudo, pwd, titre...) est refusé (levée de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passé en paramètre

		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de films a été modifié");
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
	
	public static int addItemFilmOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree, String idTest){
		int nbFilms = sn.nbFilms();
		try{
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			if (sn.nbFilms() != nbFilms+1) {
				System.out.println("Test " + idTest + " :  le nombre de films n'a pas été correctement incrémenté");
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
	
	public static int addItemFilmAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree, String idTest,String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (ItemFilmAlreadyExists e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception ItemFilmAlreadyExists a bien été levée mais le nombre de films a été modifié");
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

	public static int addItemFilmNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree, String idTest,String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			return 0;
		}
		catch (NotMember e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de films a été modifié");
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

		int nbFilms = 0;
		int nbTests = 0;
		int nbErreurs = 0;
		
		System.out.println("Tests  ajouter des films au réseau social");
		
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
		// tentative d'ajout de films avec entrées "incorrectes"
		
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, null, "paul", "Citizenfour", "Documentaire", "Edward Snowden", "Marvel", 120, "3.1", "L'ajout d'un film dont le pseudo n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "", "paul", "Citizenfour", "Documentaire", "Edward Snowden", "Marvel", 120, "3.2", "L'ajout d'un film dont le pseudo fait moins de 1 caractère est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Paul", null, "Citizenfour", "Documentaire", "Edward Snowden", "Marvel", 120, "3.3", "L'ajout d'un film dont le password n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Paul", "pa", "Citizenfour", "Documentaire", "Edward Snowden", "Marvel", 120, "3.4", "L'ajout d'un film dont le password fait moins de 4 caractères est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Paul", "paul", null, "Documentaire", "Edward Snowden", "Marvel", 120, "3.5", "L'ajout d'un film dont le titre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Paul", "paul", "", "Documentaire", "Edward Snowden", "Marvel", 120, "3.6", "L'ajout d'un film dont le film fait moins de 1 caractère est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Paul", "paul", "Citizenfour", null, "Edward Snowden", "Marvel", 120, "3.7", "L'ajout d'un film dont le genre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Paul", "paul", "Citizenfour", "Documentaire", null, "Marvel", 120, "3.8", "L'ajout d'un film dont le réalisateur n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Paul", "paul", "Citizenfour", "Documentaire", "Edward Snowden", null, 120, "3.9", "L'ajout d'un film dont le scénariste n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Paul", "paul", "Citizenfour", "Documentaire", "Edward Snowden", "Marvel", -120, "3.10", "L'ajout d'un film dont la durée n'est pas positive est accepté");
		
		// <=> fiche numéro 2
		// ajout de 2 films avec des paramètres "correctes"
		nbTests++;
		nbErreurs += addItemFilmOKTest ( sn, "Paul", "paul", "Citizenfour", "Documentaire", "Edward Snowden", "Marvel", 120, "4.1.a");
		nbTests++;
		nbErreurs += addItemFilmOKTest ( sn, "Paul", "paul", "Toys Story", "Animation", "Disney", "Julien Tiron", 120, "4.1.b");
		
		// tentative d'ajout par un non-membre
		nbTests++;
		nbErreurs += addItemFilmNotMemberTest ( sn, "Vanessa", "paul", "Le fabuleux Destin d'Amélie Poulain", "Fiction", "Jamel Debouze", "Yann Tiersen", 120, "4.2", "L'ajout d'un film par un non-membre est accepté");

		// tentative d'ajout par un membre avec un password erroné
		nbTests++;
		nbErreurs += addItemFilmNotMemberTest ( sn, "Paul", "patate", "Le fabuleux Destin d'Amélie Poulain", "Fiction", "Jamel Debouze", "Yann Tiersen", 120, "4.3", "L'ajout d'un film par un membre dont le password est erroné est accepté");

		// tentative d'ajout d'un film déjà existant
		nbTests++;
		nbErreurs += addItemFilmNotMemberTest ( sn, "Paul", "paul", "La Terre Vue Du Ciel", "Documentaire", "Edward Snowden", "Marvel", 120, "4.4", "L'ajout d'un film déjà existant est accepté");

		// bilan du test de addItemFilm
		System.out.println("TestsAddItemFilm :   " + nbErreurs + 
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
