package test;

import avis.SocialNetwork;
import avis.Member;
import test.TestsAddItemFilm;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;
import exception.SameMember;
/** 
 * @author J. Tiron, P. Chovelon
 * @date avril 2015
 * @version V1.0
 */

public class TestsReviewOpinion {

	public static int reviewOpinionBadEntryTest(SocialNetwork sn, String pseudo, String password, String titre, String pseudoMember, float karma, boolean bookOrNot, String idTest, String messErreur){
		//vérifie que l'ajout de l'opinion d'un membre est refusée (levée de BadEntry et pas de modification de sn)
		//si c'est bien le cas, ne fait rien
		//sinon, affiche le message d'erreur passé en paramètre
		float karmaRetourne = 0.0f;
		try{
			karmaRetourne = sn.reviewOpinion(pseudo, password, titre, pseudoMember, karma, bookOrNot);	
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			//CAS OÙ IL N'Y A QU'UNE SEULE NOTE POUR LE KARMA

			if(karmaRetourne == karma){//si le karma retourné est égal au seul karma rajouté --> pas normal : cela veut dire que le karma a été modifié 
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le karma semble avoir été modifié");				
				return 1;
			}
			else{ 
				//si le karma n'est pas égal au karma ajouté --> OK
				return 0;
			}
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}

	}

	public static int reviewOpinionNotMemberTest(SocialNetwork sn, String pseudo, String password, String titre, String pseudoMember, float karma, boolean bookOrNot, String idTest, String messErreur){
		//vérifie que le pseudo a été instancié et que le mot de passe et le pseudo correspondent bien 
		//(levée de NotMember et l'opinion n'est pas ajouté)
		//Si c'est le cas, rien n'est fait
		//sinon, affiche le message d'erreur passé en paramètre

		float karmaRetourne = 0.0f;

		try{
			karmaRetourne = sn.reviewOpinion(pseudo, password, titre, pseudoMember, karma, bookOrNot);		
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			if(karmaRetourne == karma){
				//si le karma retourné est égal au seul karma rajouté --> pas normale : cela veut dire que le karma a été modifié 
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le karma semble avoir été modifié");				
				return 1;
			}
			else
			{//si le karma retourné n'est pas égal au seul karma ajouté --> OK
				return 0;
			}
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}

	}


	public static int reviewOpinionNotItemTest(SocialNetwork sn, String pseudo, String password, String titre, String pseudoMember, float karma, boolean bookOrNot, String idTest, String messErreur){
		//vérifie que le titre est bien le titre d'un film ou d'un livre et la levée d'exception NotItem
		//si c'est le cas rien n'est fait,
		//sinon affichage du message d'erreur passé en paramêtre
		float karmaRetourne = 0.0f;

		try{
			karmaRetourne = sn.reviewOpinion(pseudo, password, titre, pseudoMember, karma, bookOrNot);		
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotItem e) {
			//si le karma retourné est égal au seul karma rajouté --> pas normale : cela veut dire que le karma a été modifié 
			if(karmaRetourne == karma)
			{
				System.out.println("Test " + idTest + " : l'exception NotItem bien été levée mais le karma semble avoir été modifié");				
				return 1;
			}
			else
			{ //si le karma retourné n'est pas égal au seul karma rajouté : OK
				return 0;
			}

		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}

	}

	public static int reviewOpinionOKTest(SocialNetwork sn, String pseudo, String password, String titre, String pseudoMember, float karma, boolean bookOrNot, String idTest, String messErreur){
		//vérifie le bon fonctionnement de la méthode dans le cas ou les paramêtres passés sont corrects
		//c'est à dire que l'opinion est bien prise en compte dans le membre
		float karmaRetourne = 0.0f;
		try{
			karmaRetourne = sn.reviewOpinion(pseudo, password, titre, pseudoMember, karma, bookOrNot);		

			if(karmaRetourne != karma) //si le karma ajouté correspond à la moyenne
			{
				return 0 ;
			}
			else //sinon
			{
				System.out.println ("Test " + idTest + " : " + messErreur);
				return 1;

			}

		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}

	}

	public static int reviewOpinionSameMemberTest(SocialNetwork sn, String pseudo, String password, String titre, String pseudoMember, float karma, boolean bookOrNot, String idTest, String messErreur){
		//vérifie le bon fonctionnement de la méthode dans le cas ou les paramêtres passés sont corrects
		//c'est à dire que l'opinion est bien prise en compte dans le membre
		//vérifie que le titre est bien le titre d'un film ou d'un livre et la levée d'exception NotItem
		//si c'est le cas rien n'est fait,
		//sinon affichage du message d'erreur passé en paramêtre
		float karmaRetourne = 0.0f;

		try{
			karmaRetourne = sn.reviewOpinion(pseudo, password, titre, pseudoMember, karma, bookOrNot);		
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (SameMember e) {
			//si le karma retourné est égal au seul karma rajouté --> pas normale : cela veut dire que le karma a été modifié 
			if(karmaRetourne == karma)
			{
				System.out.println("Test " + idTest + " : l'exception SameMember bien été levée mais le karma semble avoir été modifié");				
				return 1;
			}
			else
			{ //si le karma retourné n'est pas égal au seul karma rajouté : OK
				return 0;
			}

		}
		catch (Exception e)
		{
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}


	}


	public static void main(String[] args) {

		int nbTests = 0;
		int nbErreurs = 0;

		System.out.println("Tests  ajouter des commentaires de film au réseau social");

		SocialNetwork sn = new SocialNetwork();	

		// ajout de 2 membre avec entrées "correctes" qui servira pour les tests
		try{
			sn.addMember("Paul", "paul", "lecteur impulsif");
		}
		catch(MemberAlreadyExists e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE MemberAlreadyExists: Impossible");
		}
		catch(BadEntry e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE BadEntry: Impossible");
		}

		try{
			sn.addMember("Pierrick", "pierrick", "lit les livres de François");
		}
		catch(MemberAlreadyExists e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE MemberAlreadyExists: Impossible");
		}
		catch(BadEntry e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE BadEntry: Impossible");
		}



		//Il faut ajouter un film pour tester la méthode d'après	
		try{
			sn.addItemFilm("Paul", "paul", "up", "comique", "Gérard Oury", "Gérard Oury", 132);
		}
		catch(NotMember e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE NotMember: Impossible");
		}
		catch(BadEntry e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE BadEntry: Impossible");
		}
		catch(ItemFilmAlreadyExists e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE ItemFilmAlreadyExists : Impossible");
		}

		// <=> fiche numéro 1
		// tentative d'ajout d'opinions de commentaire avec entrées "incorrectes"

		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, null, "paul", "up", "Pierrick", 3.0f, false, "7.1", "L'ajout d'une opinion avec un pseudo non instancié est accepté");

		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, " ", "paul", "up", "Pierrick", 3.0f, false, "7.2", "L'ajout d'une opinion d'un membre dont le pseudo ne contient pas un caractere, autre que des espaces, est accepté");

		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, "Paul", null, "up", "Pierrick", 3.0f, false, "7.3", "L'ajout d'une opinion avec un mot de passe non instancié est accepté");

		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, "Paul", "12", "up", "Pierrick", 3.0f, false, "7.4", "L'ajout d'une opinion avec un mot de passe qui a moins de 4 caractères est accepté");

		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, "Paul", "paul", null, "Pierrick", 3.0f, false, "7.5", "L'ajout d'une opinion avec un titre non instancié est accepté");

		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, "Paul", "paul", " ", "Pierrick", 3.0f, false, "7.6", "L'ajout d'une opinion avec un titre qui ne contient pas un caractere, autre que des espaces, est accepté");

		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, "Paul", "paul", "up", "Pierrick", -1.0f, false, "7.7", "L'ajout d'une opinion avec une note non comprise entre 0.0 et 5.0 est accepté");

		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, "Paul", "paul", "up", "Pierrick", 6.0f, false, "7.8", "L'ajout d'une opinion avec une note non comprise entre 0.0 et 5.0 est accepté");

		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, "Paul", "paul", "up", null, 3.0f, false, "7.9", "L'ajout d'une opinion avec un membre dont le pseudo cible non instancé");

		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, "Paul", "paul", "up", " ", 3.0f, false, "7.10", "L'ajout d'une opinion avec un membre dont le pseudo ne contient pas un caractere, autre que des espaces,");

		// <=> fiche numéro 2
		// tentative d'ajout d'une opinion d'un commentaire avec entrées "correctes"
		// mais avec les levées d'exceptions NotMember et NotItem
		// METTRE UN PSEUDO DIFFÉRENT À CHAQUE FOIS POUR LE PARAM pour pouvoir tester la diff entre les deux notes 	

		nbTests++;
		nbErreurs += reviewOpinionNotMemberTest(sn, "Pierre", "paul", "up", "Pierrick", 2.0f, false, "8.1", "L'ajout d'une opinion avec un pseudo qui n'existe pas a fonctionné");

		nbTests++;
		nbErreurs += reviewOpinionNotMemberTest(sn, "Paul", "mdpmauvais", "up", "Pierrick", 2.0f, false, "8.2", "L'ajout d'une opinion avec un pseudo et un mot de passe qui ne correspondent pas a fonctionné");


		nbTests++;
		nbErreurs += reviewOpinionOKTest(sn, "Paul", "paul", "up", "Pierrick", 1.0f, false, "8.3", "L'ajout d'une opinion avec des paramêtres corrects n'a pas fonctionné");

		nbTests++;
		nbErreurs += reviewOpinionSameMemberTest(sn, "Paul", "paul", "up", "Paul", 1.0f, false, "8.4", "L'ajout d'une opinion avec le même pseudo a fonctionné");

		nbTests++;
		nbErreurs += reviewOpinionNotItemTest(sn, "Paul", "paul", "down", "Pierrick", 2.5f, false, "8.5", "L'ajout d'une opinion avec un titre de film qui n'existe pas a fonctionné");

		nbTests++;
		nbErreurs += reviewOpinionNotItemTest(sn, "Paul", "paul", "up", "Pierrick", 2.5f, true, "8.6", "L'ajout d'une opinion avec un titre de film qui n'existe pas a fonctionné");


		// bilan du test de reviewOpinion
		System.out.println("TestsReviewOpinion :   " + nbErreurs + 
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

