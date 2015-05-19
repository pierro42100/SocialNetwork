package test;


import avis.SocialNetwork;

import exception.BadEntry;
import exception.MemberAlreadyExists;
import exception.ItemFilmAlreadyExists;
import exception.NotMember;

public class TestEfficient {

	public static void main(String[] args) 
	{
		//Timer
		double start;
		
		
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
		
		start = System.nanoTime();
		
		System.out.println(sn.toString());
		double duree = System.nanoTime() - start;
		System.out.println(duree/1000000000 + " secondes");

		
	}
	
}
