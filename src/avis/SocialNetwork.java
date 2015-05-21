package avis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;
import exception.SameMember;


/**
 * @author A. Beugnard,
 * @author G. Ouvradou
 * @author B. Prou
 * @date février - mars 2011
 * @version V0.6
 */


/**
 * <p>
 * <b>Système de mutualisation d'opinions portant sur des domaines
 * variés (littérature, cinéma, art, gastronomie, etc.) et non limités.</b>
 * </p>
 * <p>
 * L'accès aux items et aux opinions qui leurs sont associées
 * est public. La création d'item et le dépôt d'opinion nécessite en revanche
 * que l'utilisateur crée son profil au préalable.
 * </p>
 * <p>
 * Lorsqu'une méthode peut lever deux types d'exception, et que les conditions sont réunies
 * pour lever l'une et l'autre, rien ne permet de dire laquelle des deux sera effectivement levée.
 * </p>
 * <p>
 * Dans une version avancée (version 2), une opinion peut également
 * être évaluée. Chaque membre se voit dans cette version décerner un "karma" qui mesure
 * la moyenne des notes portant sur les opinions qu'il a émises.
 * L'impact des opinions entrant dans le calcul de la note moyenne attribuée à un item
 * est pondéré par le karma des membres qui les émettent.
 * </p>
 */

public class SocialNetwork {


	/**
	 * "members"
	 * liste des membres du social network
	 */
	private LinkedList<Member> members;

	/**
	 * "items"
	 * liste des items du social network
	 */
	private LinkedList<Item> items;


	/**
	 * constructeur de <i>SocialNetwok</i>
	 *
	 */

	public SocialNetwork() {

		members = new LinkedList<Member>();
		items = new LinkedList<Item>();
	}

	/**
	 * Obtenir le nombre de membres du <i>SocialNetwork</i>
	 *
	 * @return le nombre de membres
	 */
	public int nbMembers() {
		return members.size();//return number of members
	}

	/**
	 * Obtenir le nombre de films du <i>SocialNetwork</i>
	 *
	 * @return le nombre de films
	 */
	public int nbFilms() {
		int compteur = 0;

		for(Item i : items)
		{
			if(i instanceof Film)//look if the new pseudo is equal to the current member's pseudo
			{
				compteur++; //if true return the member
			}
		}

		return compteur;
	}

	/**
	 * Obtenir le nombre de livres du <i>SocialNetwork</i>
	 *
	 * @return le nombre de livres
	 */
	public int nbBooks() {
		int compteur = 0;

		for(Item i : items)
		{
			if(i instanceof Book)//look if the new pseudo is equal to the current member's pseudo
			{
				compteur++; //if true return the member
			}
		}

		return compteur;
	}



	/**
	 * Ajouter un nouveau membre au <i>SocialNetwork</i>
	 *
	 * @param pseudo son pseudo
	 * @param password son mot de passe
	 * @param profil un slogan choisi par le membre pour se définir
	 *
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le profil n'est pas instancié.  </li>
	 * </ul><br>
	 *
	 * @throws MemberAlreadyExists membre de même pseudo déjà présent dans le <i>SocialNetwork</i> (même pseudo : indifférent à  la casse  et aux leadings et trailings blanks)
	 *
	 */
	public void addMember(String pseudo, String password, String profil) throws BadEntry, MemberAlreadyExists  {

		//Tests de vérification des informations
		//BadEntry
		if(pseudo == null || (pseudo.replaceAll(" ", "")).length() < 1)
		{
			throw new BadEntry("Le pseudo n'est pas correct");
		}

		if(password == null || (password.trim()).length() < 4)
		{
			throw new BadEntry("Le password n'est pas correct");
		}

		if(profil == null)
		{
			throw new BadEntry("Le profil n'est pas instancié");
		}


		//déclaration d'un Member qui correspond au Member à ajouter
		Member newMember;

		//Dans le cas où aucunes exception n'a été levée, le newMember peut être créer
		newMember = new Member(pseudo, password, profil);

		//Test de vérification de l'existance d'un Member avec le même pseudo
		//MemberAlreadyExists

		if(findMember(pseudo) != null) //s'il existe
		{
			throw new MemberAlreadyExists("Un membre avec ce même pseudo");
		}

		//Ajouter à la liste
		members.add(newMember);

	}


	/**
	 * Ajouter un nouvel item de film au <i>SocialNetwork</i>
	 *
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre
	 * @param titre le titre du film
	 * @param genre son genre (aventure, policier, etc.)
	 * @param realisateur le réalisateur
	 * @param scenariste le scénariste
	 * @param duree sa durée en minutes
	 *
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si le réalisateur n'est pas instancié. </li>
	 *  <li>  si le scénariste n'est pas instancié. </li>
	 *  <li>  si la durée n'est pas positive.  </li>
	 * </ul><br>
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemFilmAlreadyExists : item film de même titre  déjà présent (même titre : indifférent à  la casse  et aux leadings et trailings blanks)
	 *
	 */
	public void addItemFilm(String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree) throws BadEntry, NotMember, ItemFilmAlreadyExists {
		//Test de validité du pseudo
		if(pseudo==null || pseudo.replaceAll(" ", "").length()<1){
			throw new BadEntry("Le pseudo n'est pas correct");
		}
		//Test de validité du password
		if(password==null || password.trim().length()<4){
			throw new BadEntry("Le password n'est pas correct");
		}
		//Test de validité du titre
		if(titre==null || titre.replaceAll(" ", "").length()<1){
			throw new BadEntry("Le titre n'est pas correct");
		}
		//Test de validité du genre et de l'auteur
		if(genre==null || realisateur==null || scenariste==null){
			throw new BadEntry("Le genre, l'auteur ou le scénariste n'est pas correct");
		}
		//Test de validité de la durée
		if(duree <= 0){
			throw new BadEntry("La durée est négative");
		}

		//Test d'existence du membre
		Member member;
		member = findMember(pseudo);
		if (member!= null){
			if(!password.equalsIgnoreCase(member.getPassword())){
				throw new NotMember("les identifiants sont incorrects");
			}
		}
		else
		{
			throw new NotMember("les identifiants sont incorrects");
		}

		//Test d'existence
		Item item;
		item = new Film(titre, genre, realisateur, scenariste, duree);
		if(findItem(titre, false) != null){
			throw new ItemFilmAlreadyExists("le film existe déjà");
		}

		items.add(item);
	}

	/**
	 * Ajouter un nouvel item de livre au <i>SocialNetwork</i>
	 *
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre
	 * @param titre le titre du livre
	 * @param genre son genre (roman, essai, etc.)
	 * @param auteur l'auteur
	 * @param nbPages le nombre de pages
	 *
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si l'auteur n'est pas instancié. </li>
	 *  <li>  si le nombre de pages n'est pas positif.  </li>
	 * </ul><br>
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemBookAlreadyExists item livre de même titre  déjà présent (même titre : indifférent à la casse  et aux leadings et trailings blanks)
	 *
	 *
	 */
	public void addItemBook(String pseudo, String password, String titre, String genre, String auteur, int nbPages) throws  BadEntry, NotMember, ItemBookAlreadyExists{
		//Test de validité du pseudo
		if(pseudo==null || pseudo.replaceAll(" ", "").length()<1){
			throw new BadEntry("Le pseudo n'est pas correct");
		}
		//Test de validité du password
		if(password==null || password.trim().length()<4){
			throw new BadEntry("Le password n'est pas correct");
		}
		//Test de validité du titre
		if(titre==null || titre.replaceAll(" ", "").length()<1){
			throw new BadEntry("Le titre n'est pas correct");
		}
		//Test de validité du nombre de pages
		if(nbPages <= 0){
			throw new BadEntry("Le nombre de pages n'est pas correct");
		}
		//Test de validité du genre et de l'auteur
		if(genre==null || auteur==null){
			throw new BadEntry("Le genre ou l'auteur n'est pas correct");
		}
		//Test d'existence du membre
		Member member;
		member = findMember(pseudo);
		if(member != null)
		{

			if(!password.equals(member.getPassword()))
			{
				throw new NotMember("les identifiants sont incorrects 1");
			}
		}
		else
		{
			throw new NotMember("les identifiants sont incorrects 2");
		}

		//Test d'existence
		Item item;
		item = new Book(titre, genre, auteur, nbPages);
		if(findItem(titre, true) != null){
			throw new ItemBookAlreadyExists("le livre existe déjà");
		}
		items.add(item);
	}

	/**
	 * Consulter les items du <i>SocialNetwork</i> par nom
	 *
	 * @param nom son nom (eg. titre d'un film, d'un livre, etc.)
	 *
	 * @throws BadEntry : si le nom n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *
	 * @return LinkedList <String> : la liste des représentations de tous les items ayant ce nom
	 * Cette représentation contiendra la note de l'item s'il a été noté.
	 * (une liste vide si aucun item ne correspond)
	 */
	public LinkedList <String> consultItems(String nom) throws BadEntry {
		if(nom == null||nom.trim().length()<1){
			throw new BadEntry("La recherche n'est pas valide");
		}
		LinkedList <String> res = new LinkedList <String>();
		for(Item i: items){
			if(nom.trim().equalsIgnoreCase((i.getTitle().trim()))){
				res.add(i.toString());
			}
		}
		return res;
	}




	/**
	 * Donner son opinion sur un item film.
	 * Ajoute l'opinion de ce membre sur ce film au <i>SocialNetwork</i>
	 * Si une opinion de ce membre sur ce film  préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 *
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du film  concerné
	 * @param note la note qu'il donne au film
	 * @param commentaire ses commentaires
	 *
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un film.
	 *
	 * @return la note moyenne des notes sur ce film
	 */
	public float reviewItemFilm(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {


		//Test de validité du pseudo
		if(pseudo==null || pseudo.replaceAll(" ", "").length()<1){
			throw new BadEntry("Le pseudo n'est pas correct");
		}
		//Test de validité du password
		if(password==null || password.trim().length()<4){
			throw new BadEntry("Le password n'est pas correct");
		}
		//Test de validité du titre
		if(titre==null || titre.replaceAll(" ", "").length()<1){
			throw new BadEntry("Le titre n'est pas correct");
		}
		//Test de validité de la note
		if(note > 5.0 || note < 0.0)//Si pas comprise entre 0.0 et 5.0
		{
			throw new BadEntry("La note n'est pas correcte");
		}
		//Test de validité du commentaire
		if(commentaire == null)
		{
			throw new BadEntry("Le commentaire n'est pas instancié");
		}

		//Test d'existence du membre
		Member member;
		member = findMember(pseudo);
		if(member != null)
		{

			if(!password.equals(member.getPassword())){
				throw new NotMember("les identifiants sont incorrects");
			}
		}
		else
		{
			throw new NotMember("les identifiants sont incorrects");
		}

		Item f;
		f = findItem(titre, false);
		//Test de l'existance du film
		if(f == null){ //Si aucun film ne possède ce titre
			throw new NotItem("Le film n'existe pas");
		}

		//Dans le cas où le film existe on peut l'ajouter 
		float newNote = 0.0f;
		//Test si un commentaire existe pour ce membre
		Review r;
		r = f.findReview(pseudo);
		if(r == null)
		{

			//Dans le cas où aucun commentaire n'existe pas pour ce pseudo
			f.addNewReview(commentaire, note, pseudo, member.getKarma());
			newNote = f.getNote();

		}
		else//sinon, dans le cas où le commentaire existe déjà
		{

			newNote = f.updateReview(r, commentaire, note, member.getKarma());

		}


		return newNote;
	}



	/**
	 * Donner son opinion sur un item livre.
	 * Ajoute l'opinion de ce membre sur ce livre au <i>SocialNetwork</i>
	 * Si une opinion de ce membre sur ce livre  préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 *
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du livre  concerné
	 * @param note la note qu'il donne au livre
	 * @param commentaire ses commentaires
	 *
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un livre.
	 *
	 * @return la note moyenne des notes sur ce livre
	 */
	public float reviewItemBook(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {

		//Test de validité du pseudo
		if(pseudo==null || pseudo.replaceAll(" ", "").length()<1){
			throw new BadEntry("Le pseudo n'est pas correct");
		}
		//Test de validité du password
		if(password==null || password.trim().length()<4){
			throw new BadEntry("Le password n'est pas correct");
		}
		//Test de validité du titre
		if(titre==null || titre.replaceAll(" ", "").length()<1){
			throw new BadEntry("Le titre n'est pas correct");
		}
		//Test de validité de la note
		if(note > 5.0 || note < 0.0)//Si pas comprise entre 0.0 et 5.0
		{
			throw new BadEntry("La note n'est pas correcte");
		}
		//Test de validité du commentaire
		if(commentaire == null)
		{
			throw new BadEntry("Le commentaire n'est pas instancié");
		}

		//Test d'existence du membre
		Member member;
		member = findMember(pseudo);
		if(member != null)
		{

			if(!password.equals(member.getPassword())){
				throw new NotMember("les identifiants sont incorrects 1");
			}

		}
		else
		{
			throw new NotMember("les identifiants sont incorrects 2");
		}

		Item b;
		b = findItem(titre, true);//c'est un livre
		//Test de l'existance du livre
		if(b == null){ //Si aucun livre ne possède ce titre
			throw new NotItem("Le livre n'existe pas");
		}

		//Dans le cas où le livre existe on peut l'ajouter 
		float newNote = 0.0f;
		//Test si un commentaire existe pour ce membre
		Review r;
		r = b.findReview(pseudo);

		if(r == null)
		{
			//Dans le cas où aucun commentaire n'existe pas pour ce pseudo
			b.addNewReview(commentaire, note, pseudo, member.getKarma());
			newNote = b.getNote();
		}
		else//sinon, dans le cas où le commentaire existe déjà
		{

			newNote = b.updateReview(r, commentaire, note, member.getKarma());

		}

		return newNote;
	}

	/**
	 *
	 * Obtenir l'<i>Item</i> correspondant à la recherche
	 *
	 * @param titre de l'<i>Item</i> rechercher
	 * @param bookOrNot permet d'indiquer si le titre correspond à un <i>Book</i> ou à un <i>Film</i>
	 * @return l'<i>Item</i>
	 */

	public Item findItem(String titre, boolean bookOrNot){

		if(bookOrNot) //Si c'est un livre
		{
			for(Item i : items) //
			{
				if(i.getTitle().trim().equalsIgnoreCase(titre.trim()) && i instanceof Book )
				{
					return i;
				}
			}
		}
		else //si c'est un film
		{
			for(Item i : items) //
			{
				if(i.getTitle().trim().equalsIgnoreCase(titre.trim()) && i instanceof Film )
				{
					return i;
				}
			}	
		}

		return null; //retourne null si l'item n'existe pas
	}




	/**
	 * Savoir si un membre existe déjà
	 *
	 * @return null si le member n'existe pas ou le <i>Member</i> s'il existe
	 */
	public Member findMember(String pseudo){

		for(Member m : members)
		{
			//trim : no leadings or trailings blanks
			//equalsIgnoreCase : without case
			if(m.getPseudo().trim().equalsIgnoreCase(pseudo.trim()))//look if the new pseudo is equal to the current member's pseudo
			{
				return m; //if true return the member
			}
		}


		return null; //else return null
	}


	/**
	 * Donner son opinion sur un membre.
	 * Ajoute l'opinion sur membre par rapport à un <i>Item</i> du <i>SocialNetwork</i>
	 *
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param pseudoMember du membre qui va être noté
	 * @param titre titre de l'item
	 * @param karma note à ajouter
	 * @param BookOrNot un booleen qui indique si c'est le l'opinion doit se faire sur un avis d'un livre ou d'un film 
	 *
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas, ou si le membre que l'on veut noter n'existe pas
	 * @throws NotItem : si le titre n'est pas le titre d'un item
	 * @throws SameMembre : si le pseudo est le même que le pseudo du membre à noter
	 * @return le nouveau karma du membre
	 */
	public float reviewOpinion(String pseudo, String password, String titre, String pseudoMember, float karma, boolean BookOrNot) throws BadEntry, NotItem, NotMember, SameMember{


		//Test de la validité du pseudo 1
		if(pseudo==null || pseudo.replaceAll(" ", "").length()<1){
			throw new BadEntry("Le pseudo n'est pas correct");
		}
		//Test de validité du password
		if(password==null || password.trim().length()<4){
			throw new BadEntry("Le password n'est pas correct");
		}
		//Test de validité du titre
		if(titre==null || titre.replaceAll(" ", "").length()<1){
			throw new BadEntry("Le titre n'est pas correct");
		}
		//Test de validité du pseudoMember
		if(pseudoMember==null || pseudoMember.replaceAll(" ", "").length()<1){
			throw new BadEntry("Le pseudo du member n'est pas correct");
		}
		//Test de la validité du pseudo 2, : les pseudo doivent être différents
		if(pseudo.equalsIgnoreCase(pseudoMember)){
			throw new SameMember("Le pseudo est le même");
		}
		//Test de validité du karma
		if(karma > 5.0 || karma < 0.0)//Si pas compris entre 0.0 et 5.0
		{
			throw new BadEntry("Le karma n'est pas correct");
		}
		//Test d'existence du membre
		Member member;
		member = findMember(pseudo);
		if(member != null)
		{

			if(!password.equals(member.getPassword())){
				throw new NotMember("les identifiants sont incorrects ");
			}

		}
		else
		{
			throw new NotMember("les identifiants sont incorrects");
		}

		//Test d'existence du membre qu'on veut noter
		Member pMember;
		pMember = findMember(pseudoMember);
		if(pMember == null)
		{
			throw new NotMember("Ce membre n'existe pas");
		}
		//Ici on est certains que le membre qui souhaite noter existe et que le membre qui doit être noté existe aussi

		//Test du type d'Item
		Item i;
		i = findItem(titre, BookOrNot);
		if(i == null)//Si l'Item n'existe pas
		{
			throw new NotItem("Aucun Item ne correspond au titre demandé");
		}

		//Si tous les paramêtres sont bons :
		pMember.addKarma(karma);  //On note le membre

		return pMember.getKarma();
	}

	/**
	 * Obtenir une représentation textuelle du <i>SocialNetwork</i>.
	 *
	 * @return la chaîne de caractères représentation textuelle du <i>SocialNetwork</i>
	 */
	public String toString() {
		String phrase;
		phrase = "Membres :\n";
		for(Member m : members)
		{
			phrase+= m.toString();
		}


		phrase += "Items :\n";
		for(Item i : items)
		{
			phrase+= i.toString() + "\n";
		}

		return phrase;
	}

}
