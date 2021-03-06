package test;


import avis.SocialNetwork;
import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;
/** 
 * @author J. Tiron, P. Chovelon
 * @date avril 2015
 * @version V1.0
 */

public class TestsReviewItemBook {

	public static int reviewItemBookBadEntryTest(SocialNetwork sn, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur){
		//vérifie que l'ajout de l'opinion d'un membre est refusée (levée de BadEntry et pas de modification de sn)
		//si c'est bien le cas, ne fait rien
		//sinon, affiche le message d'erreur passé en paramètre
		float noteRetournee = 0.0f;
		try{
			noteRetournee = sn.reviewItemBook(pseudo, password, titre, note, commentaire);	
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			//CAS OÙ IL N'Y A QU'UNE SEULE NOTE POUR LE LIVRE

			//---->VRAIMENT PAS SUR DE LA CONDITION<----- : PAR EXEMPLE SI LA NOTE NE MODIFIE QUE TRES PEU LA MOYENNE ???
			if(noteRetournee == note){
				//si la note retournée est égale à la seule note rajoutée --> pas normale : cela veut dire que la note à été modifiée 

				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais la note semble avoir été modifiée");				
				return 1;
			}
			else{ //si la note retournee n'est pas égale à la seule note ajoutée --> OK
				return 0;
			}
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}

	}

	public static int reviewItemBookNotMemberTest(SocialNetwork sn, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur){
		//vérifie que le pseudo a été instancié et que le mot de passe et le pseudo correspondent bien 
		//(levée de NotMember et le commentaire n'est pas ajouté)
		//Si c'est le cas, rien n'est fait
		//sinon, affiche le message d'erreur passé en paramètre
		float noteRetournee = 0.0f;

		try{
			noteRetournee = sn.reviewItemBook(pseudo, password, titre, note, commentaire);		
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			if(noteRetournee == note){
				//si la note retournée est égale à la seule note rajoutée --> pas normale : cela veut dire que la note à été modifiée 
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais la note semble avoir été modifiée");				
				return 1;

			}
			else
			{//si la note retournee n'est pas égale à la seule note ajoutée --> OK
				return 0;
			}
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}

	}


	public static int reviewItemBookNotItemTest(SocialNetwork sn, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur){
		//vérifie que le titre est bien le titre d'un livre et levée d'exception NotItem
		//si c'est le cas rien n'est fait,
		//sinon affichage du message d'erreur passé en paramêtre
		float noteRetournee = 0.0f;
		try{
			noteRetournee = sn.reviewItemBook(pseudo, password, titre, note, commentaire);		
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotItem e) {
			//si la note retournée est égale à la seule note rajouté --> pas normale : cela veut dire que la note à été modifiée 
			if(noteRetournee == note)
			{
				System.out.println("Test " + idTest + " : l'exception NotItem bien été levée mais la note semble avoir été modifiée");				
				return 1;
			}
			else
			{ //si la note retournée n'est pas égale à la seule note rajoutée : OK
				return 0;
			}

		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}

	}

	public static int reviewItemBookOKTest(SocialNetwork sn, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur){
		//vérifie le bon fonctionnement de la méthode dans le cas ou les paramêtres passés sont corrects
		//c'est à dire que le commentaire et la note sont bien pris en compte pour le livre

		float noteRetournee = 0.0f;
		
		try{
			noteRetournee = sn.reviewItemBook(pseudo, password, titre, note, commentaire);		

			if(noteRetournee == note) //si la seule note ajoutée correspond à la moyenne
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


	public static void main(String[] args) {

		int nbTests = 0;
		int nbErreurs = 0;

		System.out.println("Tests  ajouter des commentaires de livre au réseau social");

		SocialNetwork sn = new SocialNetwork();	

		// ajout de 1 membre avec entrées "correctes" qui servira pour les tests
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


		//Il faut ajouter un livre pour tester la méthode d'après	
		try{
			sn.addItemBook("Paul", "paul", "L'Etranger", "fiction", "Albert Camus", 132);
		}
		catch(NotMember e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE NotMember: Impossible");
		}
		catch(BadEntry e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE BadEntry: Impossible");
		}
		catch(ItemBookAlreadyExists e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE ItemBookAlreadyExits: Impossible");
		}

		// <=> fiche numéro 1
		// tentative d'ajout de commentaires de livre avec entrées "incorrectes"

		nbTests++;
		nbErreurs += reviewItemBookBadEntryTest(sn, null, "paul", "titre", 3.0f, "Ce livre est bien", "5.1", "L'ajout d'un commentaire avec un pseudo non instancié est accepté");

		nbTests++;
		nbErreurs += reviewItemBookBadEntryTest(sn, " ", "paul", "titre", 3.0f, "Ce livre est bien", "5.2", "L'ajout commentaire d'un membre dont le pseudo ne contient pas un caractere, autre que des espaces, est accepté");

		nbTests++;
		nbErreurs += reviewItemBookBadEntryTest(sn, "Paul", null, "titre", 3.0f, "Ce livre est bien", "5.3", "L'ajout d'un commentaire avec un mot de passe non instancié est accepté");

		nbTests++;
		nbErreurs += reviewItemBookBadEntryTest(sn, "Paul", "12", "titre", 3.0f, "Ce livre est bien", "5.4", "L'ajout commentaire d'un avec un mot de passe qui a moins de 4 caractères est accepté");

		nbTests++;
		nbErreurs += reviewItemBookBadEntryTest(sn, "Paul", "paul", null, 3.0f, "Ce livre est bien", "5.5", "L'ajout d'un commentaire avec un titre non instancié est accepté");

		nbTests++;
		nbErreurs += reviewItemBookBadEntryTest(sn, "Paul", "paul", " ", 3.0f, "Ce livre est bien", "5.6", "L'ajout d'un commentaire avec un titre qui ne contient pas un caractere, autre que des espaces, est accepté");

		nbTests++;
		nbErreurs += reviewItemBookBadEntryTest(sn, "Paul", "paul", "titre", -1.0f, "Ce livre est bien", "5.7", "L'ajout d'un commentaire avec une note non comprise entre 0.0 et 5.0 est accepté");

		nbTests++;
		nbErreurs += reviewItemBookBadEntryTest(sn, "Paul", "paul", "titre", 6.0f, "Ce livre est bien", "5.8", "L'ajout d'un commentaire avec une note non comprise entre 0.0 et 5.0 est accepté");

		nbTests++;
		nbErreurs += reviewItemBookBadEntryTest(sn, "Paul", "paul", "titre", 3.0f, null, "5.9", "L'ajout d'un commentaire avec un commentaire non instancié est accepté");

		// <=> fiche numéro 2
		// tentative d'ajout de commentaires de livre avec entrées "correctes"
		// mais avec les levées d'exceptions NotMember et NotItem
		// METTRE UN PSEUDO DIFFÉRENT À CHAQUE FOIS POUR LE PARAM pour pouvoir tester la diff entre les deux notes 	

		nbTests++;
		nbErreurs += reviewItemBookNotMemberTest(sn, "Pierre", "paul", "titre", 2.0f, "commentaire", "6.1", "L'ajout d'un commentaire avec un pseudo qui n'existe pas a fonctionné");

		nbTests++;
		nbErreurs += reviewItemBookNotMemberTest(sn, "Paul", "mdpmauvais", "titre", 2.0f, "commentaire", "6.2", "L'ajout d'un commentaire avec un pseudo et un mot de passe qui ne correspondent pas a fonctionné");

		nbTests++;
		nbErreurs += reviewItemBookOKTest(sn, "Paul", "paul", "L'Etranger", 2.5f, "ce livre est vraiment bien", "6.3", "L'ajout d'un commentaire avec des paramêtres corrects a fonctionné");

		nbTests++;
		nbErreurs += reviewItemBookNotItemTest(sn, "Paul", "paul", "La petite patrouille", 2.5f, "ce livre est vraiment bien", "6.4", "L'ajout d'un commentaire avec un titre de livre qui n'existe pas a fonctionné");

		//Ajout du livre La Bible pour tester :

		//Il faut ajouter un livre pour tester la méthode d'après	
		try{
			sn.addItemBook("Paul", "paul", "La Bible", "religion", "plusieurs auteurs", 2000);
		}
		catch(NotMember e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE NotMember: Impossible");
		}
		catch(BadEntry e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE BadEntry: Impossible");
		}
		catch(ItemBookAlreadyExists e)
		{
			System.out.println("TEST AJOUT NOUVEAU MEMBRE ItemBookAlreadyExits: Impossible");
		}


		//OK Test
		nbTests++;
		nbErreurs += reviewItemBookOKTest(sn, "Paul", "paul", "La Bible", 2.5f, "ce livre est vraiment bien", "6.5", "L'ajout d'un commentaire avec un titre de livre qui existe n'a pas fonctionné");
		//Update Test
		nbTests++;
		nbErreurs += reviewItemBookOKTest(sn, "Paul", "paul", "La Bible", 2.0f, "ce livre est moins bien", "6.6", "La modification d'un commentaire n'a pas fonctionné");

		// bilan du test de reviewItemBook
		System.out.println("TestsReviewItemBook :   " + nbErreurs + 
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
