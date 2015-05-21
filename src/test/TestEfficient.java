package test;


import avis.SocialNetwork;

import exception.BadEntry;
import exception.MemberAlreadyExists;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.NotMember;
import exception.NotItem;
import exception.SameMember;

public class TestEfficient {

	public static void main(String[] args) 
	{

		//creation d'un SocialNetwork
		SocialNetwork sn = new SocialNetwork();	

		String pseudo = "Pseudo";
		String password = "pseudo";
		String profil = "Membre numéro ";

		for(int i=0; i<500 ; i++)
		{
			try{
				sn.addMember(pseudo+i , password+i, profil+i);
			}
			catch(BadEntry e)
			{
				System.out.println("BadEntry");
			}
			catch(MemberAlreadyExists e)
			{
				System.out.println("MemberAlreadyExists");
			}

		}		


		for(int i=0; i<5000 ; i++)
		{
			try{
				sn.addItemFilm("Pseudo0", "pseudo0", "FastAndFurious"+i, "Action", "Realisateur", "Scenariste", 120);
			}
			catch(BadEntry e)
			{
				System.out.println("BadEntry");
			}
			catch(NotMember e)
			{
				System.out.println("NotMember");
			}
			catch(ItemFilmAlreadyExists e)
			{
				System.out.println("ItemFilmAlreadyExists");
			}


		}

		//////////////////////////////////
		//UTILISER POUR TOUS LES TESTS	//
		//////////////////////////////////
		//Result
		double duree;

		//////////////////////////
		//TEST AJOUT D'UN MEMBRE//
		//////////////////////////
		//Timer
		double start;


		start = System.nanoTime();

		try{
			sn.addMember("toto", "password", "nouveau membre");
		}
		catch(BadEntry e)
		{
			System.out.println("BadEntry");
		}
		catch(MemberAlreadyExists e)
		{
			System.out.println("MemberAlreadyExists");
		}
		duree = System.nanoTime() - start;
		System.out.println("Durée pour ajouter un nouveau membre : " + duree/1000000000 + " secondes\n");


		///////////////////////////////////////////////////
		// TEST AJOUT D'UN FILM AVEC LE MEMBRE JUSTE CRÉE//
		///////////////////////////////////////////////////
		//Timer
		double start1;
		start1 = System.nanoTime();
		try{
			sn.addItemFilm("toto", "password", "Film1", "Action", "Realisateur1", "Scenariste2", 130);
		}
		catch(BadEntry e)
		{
			System.out.println("BadEntry");
		}
		catch(NotMember e)
		{
			System.out.println("NotMember");
		}
		catch(ItemFilmAlreadyExists e)
		{
			System.out.println("ItemFilmAlreadyExists");
		}
		duree = System.nanoTime() - start1;
		System.out.println("Durée pour ajouter un film : "+duree/1000000000 + " secondes\n");

		////////////////////////////////////////////////////
		// TEST AJOUT D'UN LIVRE AVEC LE MEMBRE JUSTE CRÉE//
		////////////////////////////////////////////////////
		//Timer
		double start2;
		start2 = System.nanoTime();
		try{

			sn.addItemBook("toto", "password", "Book1", "fiction", "moi", 400);
		}
		catch(BadEntry e)
		{
			System.out.println("BadEntry");
		}
		catch(NotMember e)
		{
			System.out.println("NotMember");
		}
		catch(ItemBookAlreadyExists e)
		{
			System.out.println("ItemBookAlreadyExists");
		}
		duree = System.nanoTime() - start2;
		System.out.println("Durée pour ajouter un livre : "+duree/1000000000 + " secondes\n");

		/////////////////////////////////
		// TEST POUR RECHERCHER UN ITEM//
		/////////////////////////////////
		//Timer
		double start3;
		start3 = System.nanoTime();
		try{
			sn.consultItems("Book1");
		}
		catch(BadEntry e)
		{
			System.out.println("BadEntry");
		}
		duree = System.nanoTime() - start3;
		System.out.println("Durée pour rechercher un item : "+duree/1000000000 + " secondes\n");

		//////////////////////////////////////////
		// TEST POUR AJOUTER UN REVIEW A UN FILM//
		//////////////////////////////////////////
		//Timer
		double start4;
		start4 = System.nanoTime();
		try{
			sn.reviewItemFilm("toto", "password", "Film1", 3.0f, "Ce film est bien");
		}
		catch(BadEntry e)
		{
			System.out.println("BadEntry");
		}
		catch(NotItem e)
		{
			System.out.println("NotItem");
		}
		catch(NotMember e)
		{
			System.out.println("NotMember");
		}
		duree = System.nanoTime() - start4;
		System.out.println("Durée pour ajouter un avis sur un Film : "+duree/1000000000 + " secondes\n");

		//////////////////////////////////////////
		// TEST POUR AJOUTER UN REVIEW A UN BOOK//
		//////////////////////////////////////////
		//Timer
		double start5;
		start5 = System.nanoTime();
		try{
			sn.reviewItemBook("toto", "password", "Book1", 1.0f, "Ce livre est nul");
		}
		catch(BadEntry e)
		{
			System.out.println("BadEntry");
		}
		catch(NotItem e)
		{
			System.out.println("NotItem");
		}
		catch(NotMember e)
		{
			System.out.println("NotMember");
		}
		duree = System.nanoTime() - start5;
		System.out.println("Durée pour ajouter un avis sur un Book : "+duree/1000000000 + " secondes\n");

		////////////////////////////////////////////////////////
		// TEST POUR AJOUTER (OU ENLEVER) DU KARMA A UN MEMBRE//
		////////////////////////////////////////////////////////
		//Timer
		double start6;
		start6 = System.nanoTime();
		try{
			sn.reviewOpinion("Pseudo0", "pseudo0", "Book1", "toto", 1.0f, true); // dans notre cas c'est un livre
		}
		catch(BadEntry e)
		{
			System.out.println("BadEntry");
		}
		catch(NotItem e)
		{
			System.out.println("NotItem");
		}
		catch(NotMember e)
		{
			System.out.println("NotMember");
		}
		catch(SameMember e)
		{
			System.out.println("SameMember");
		}
		duree = System.nanoTime() - start6;
		System.out.println("Durée pour ajouter un opinion(karma) à un membre : "+duree/1000000000 + " secondes\n");


	}

}
