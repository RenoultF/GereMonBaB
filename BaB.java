//package GereMonBaB;

import java.util.*;
import java.awt.*;

public class BaB {

	//Variables
	private int idBaB;
	private String nomBaB;
	private String dateBaB;
	private int prixStand;
	private String adresseBaB;
	private int dimX;
	private int dimY;
	
	//nombre de cases d'un Stand
	private int tailleAutreLongueur;
	private int tailleAutreLargeur;
	
	//Quadrillage de la carte
	private Quadrillage carte; 
	
	//liste des emplacements dans le bab
	private LinkedList<Emplacement> stands;
	private LinkedList<Emplacement> autres;

	//liste des emplacements supprimé durant la session
	private LinkedList<Emplacement> standsSuppr;
	private LinkedList<Emplacement> autresSuppr;
	
	//liste des reservations sur les stands dans le bab
	private LinkedList<Reservation> reservations;
	private LinkedList<Reservation> reservationsSuppr;

	private String utilisateur;

	private Profil profil;

	//type de l'emplacement selectionné
	private String type;
	
	//liste des exposants dans le bab
	//private LinkedList<Exposant> exposants;
	
	public BaB(String nomBaB, String dateBaB, int prixStand, String adresse, int dimX, int dimY) {
	
		this.idBaB = 0;		//Valeur par défaut quand non sauvegardé
		this.dimX = dimX;	//Valeur par défaut
		this.dimY = dimY;

		this.nomBaB = nomBaB;
		this.dateBaB = dateBaB;
		this.prixStand = prixStand;
		this.adresseBaB = adresse;
		
		//Initialisation des listes
		carte = new Quadrillage(this.dimX, this.dimY);
		stands = new LinkedList<Emplacement>();
		autres = new LinkedList<Emplacement>();
		standsSuppr = new LinkedList<Emplacement>();
		autresSuppr = new LinkedList<Emplacement>();
		reservations = new LinkedList<Reservation>();
		reservationsSuppr = new LinkedList<Reservation>();

		type = new String("Stand");
	
	}
	
	/************************************** Méthodes **************************************/
	
	/**** Méthodes de chargement ****/
	public void chargerStand(Emplacement emp) {
		stands.addLast(emp);
	}
	
	public void chargerAutre(Emplacement emp) {
		autres.addLast(emp);
	}
	
	public boolean ajouterStand(int coordX, int coordY) {
		int idType;
		Case caseCourante = carte.getCaseXY(coordX, coordY);
		if(caseCourante.estLibre()) {
			
			if(stands.size() == 0)
				idType = 0;
			else
				idType = stands.getLast().getIdType() + 1;
				
			Emplacement nouveauStand = new Stand(idType, coordX, coordY,1,1);
			stands.addLast(nouveauStand);
			caseCourante.occuper();
			
			return true;
		}
		else {
			System.out.println("Case occupée");	
			return false;
		}
	}
	
	public void supprimerStand(int id) {
	
		int coordX = stands.get(id).getCoordonneeX();
		int coordY = stands.get(id).getCoordonneeY();
		carte.getCaseXY(coordX, coordY).liberer();
		
		stands.remove(id);
		
	}
	
	public boolean ajouterAutre(int coordX, int coordY) {
		int idType;
		Case caseCourante = carte.getCaseXY(coordX, coordY);
		if(caseCourante.estLibre()) {
			
			if(autres.size() == 0)
				idType = 0;
			else
				idType = autres.getLast().getIdType() + 1;
				
			Emplacement nouveauAutre = new Autre(idType,this.type, coordX, coordY,tailleAutreLargeur,tailleAutreLongueur);
			autres.addLast(nouveauAutre);
			caseCourante.occuper();
			
			return true;
		}
		else {
			System.out.println("Case occupée");	
			return false;
		}
	}

	public boolean ajouterEmplacement(int coordX, int coordY){
		if(!this.type.equals("Stand")){
			return ajouterAutre(coordX,coordY);
		}
		return ajouterStand(coordX,coordY);
	}

	public void ajouterReservation(Reservation resa){
		int id_reservation;
		if(reservations.size()==0){
            id_reservation = 0;
        }
        else{
            id_reservation = reservations.getLast().getIdReservation()+1;
        }
		resa.setIdReservation(id_reservation);
		reservations.add(resa);
	}
	
	public void supprimerAutre(int id) {
	
		int coordX = autres.get(id).getCoordonneeX();
		int coordY = autres.get(id).getCoordonneeY();
		carte.getCaseXY(coordX, coordY).liberer();
		
		autres.remove(id);
		
	}

	public int emplacementPossible(Autre empCourant){
		//indice de base de l'emplacement
		int indBase = empCourant.getCoordonneeX()*dimX + empCourant.getCoordonneeY();
		//On regarde si on dépase la taille du Bric à Brac en longueur
		if(indBase%dimX + empCourant.getLongueur() > dimX){
			//erreur de dépassement du bric à brac
			return 2;
		}

		//On regarde si on dépase la taille du Bric à Brac en largeur
		for(int iTmp = 0;iTmp<empCourant.getLargeur();iTmp++){
			for(int jTmp = indBase;jTmp<indBase+empCourant.getLongueur();jTmp++){
				if((iTmp * getDimX() + jTmp >= dimX*dimY) ){
					//erreur de dépassement du bric à brac
					return 2;
				}
			}
		}

		
		//On regarde si on est est déja sur un stand déja créé
		for(int iTmp1 = 0;iTmp1<empCourant.getLargeur();iTmp1++){
			for(int jTmp1 = indBase;jTmp1<indBase+empCourant.getLongueur();jTmp1++){
				for(Emplacement e : getListeStand()){
      				if(e.getCoordonneeX() * getDimX() + e.getCoordonneeY() == iTmp1 * getDimX() + jTmp1){
						  //on est sur un stand déja pris
						  return 1;
					  }
				}
			}
		}

		/* // Pas possible car trop de "for"
		int indTemp;
		//On regarde si on est est déja sur un emplacement autre
		for(int iBase = 0;iBase<empCourant.getLargeur();iBase++){
			for(int jBase = indBase;jBase<indBase+empCourant.getLongueur();jBase++){
				for(Emplacement e : getListeAutre()){
					Autre e1 = (Autre) e;
					indTemp = e1.getCoordonneeX()*getDimX() + e1.getCoordonneeY();
					for(int i = 0;i<e1.getLargeur();i++){
					  	for(int j = indTemp;j<indTemp+e1.getLongueur();j++){
							if(i * getDimX() + j == iBase * getDimX() + jBase){
								//on est sur un emplacement déja pris
								return 3;
							}
						}
					}
				}
			}
		}
		*/
		//tout est ok
		return 0;
	}
	
	
	/************************************** Méthodes GET/SET **************************************/
	
	/**
	 ** méthode permettant de récupérer l'identifiant du BaB
	 ** @return idBaB l'identifiant du BaB
	 **/
	public int getIdBaB() {
		return this.idBaB;
	}
	
	/**
	 ** méthode permettant de modifier l'identifiant du BaB
	 ** @param idBaB le nouvel identifiant du BaB
	 **/
	public void setIdBaB(int nouvelIdBaB) {
		this.idBaB = nouvelIdBaB;
	}
	
	/**
	 ** méthode permettant de récupérer les cartes
	 ** @return carte le Quadrillage du BaB
	 **/
	public Quadrillage getCarte() {
		return this.carte;
	}
	
	/**
	 ** méthode permettant de récupérer la taille X des stands
	 ** @return tailleAutreLongueur la taille X d'un Stand
	 **/
	public int getTailleAutreLongueur() {
		return this.tailleAutreLongueur;
	}
	
	/**
	 ** méthode permettant de récupérer la taille Y des stands
	 ** @return tailleAutreLongueur la taille Y d'un Stand
	 **/
	public int getTailleAutreLargeur() {
		return this.tailleAutreLargeur;
	}
	
	/**
	 ** méthode permettant de modifier la taille X des stands
	 ** @param nouveauX la nouvelle taille X d'un Stand
	 **/
	public void setTailleAutreLongueur(int nouveauX) {
		this.tailleAutreLongueur = nouveauX;
	}
	
	/**
	 ** méthode permettant de modifier la taille Y des stands
	 ** @param nouveauY la nouvelle taille Y d'un Stand
	 **/
	public void setTailleAutreLargeur(int nouveauY) {
		this.tailleAutreLargeur = nouveauY;
	}

	/**
	 ** méthode permettant de modifier le Nom du BaB
	 ** @param nvNomBaB le nouveau nom du bab
	 **/
	public void setNomBaB(String nvNomBaB) {
		this.nomBaB = nvNomBaB;
	}

	
	/**
	 ** méthode permettant de modifier le profil sur le bab
	 ** @param nvProfil le nouveau profil
	 **/
	public void setProfil(Profil nvProfil) {
		this.profil = nvProfil;
	}

	/**
	 ** méthode permettant de récupérer le profil sur le bab
	 ** @return le nouveau profil
	 **/
	public Profil getProfil() {
		return this.profil;
	}


	/**
	 ** méthode permettant de modifier le Nom du BaB
	 ** @param nvDateBaB la nouvelle date du bab
	 **/
	public void setDateBaB(String nvDateBaB) {
		this.dateBaB = nvDateBaB;
	}

	/**
	 ** méthode permettant de modifier l'utilisateur du bab
	 ** @param nvUtilisateur le nouvelle utilisateur
	 **/
	public void setUtilisateur(String nvUtilisateur) {
		this.utilisateur = nvUtilisateur;
	}

	/**
	 ** méthode permettant de récupérer l'utilisateur courant
	 ** @return le nouvelle utilisateur
	 **/
	public String getUtilisateur() {
		return this.utilisateur;
	}

	/**
	 ** méthode permettant de modifier l'adresse du BaB
	 ** @param nvPrixStand la nouvelle adresse du bab
	 **/
	public void setPrixStand(int nvPrixStand) {
		this.prixStand = nvPrixStand;
	}
	
	/**
	 ** méthode permettant de modifier l'adresse du BaB
	 ** @param nvAdresseBaB la nouvelle adresse du bab
	 **/
	public void setAdresseBaB(String nvAdresseBaB) {
		this.adresseBaB = nvAdresseBaB;
	}

	/**
	 ** méthode permettant de modifier la taille X du BaB
	 ** @param nvDimX la nouvelle taille X du BaB
	 **/
	public void setDimX(int nvDimX) {
		this.dimX = nvDimX;
	}

	/**
	 ** méthode permettant de modifier la taille Y du BaB
	 ** @param nvDimY la nouvelle taille Y du BaB
	 **/
	public void setDimY(int nvDimY) {
		this.dimY = nvDimY;
	}

	/**
	 ** méthode permettant de modifier le type des emplacements
	 ** @param nvType la nouvelle taille Y d'un Stand
	 **/
	public void setType(String type) {
		this.type = type;
	}

	/**
	 ** méthode permettant de retourner le type
	 ** @return type la nouvelle taille Y d'un Stand
	 **/
	public String getType() {
		return this.type;
	}


	/**
	 ** méthode permettant de retourner le nom du BaB
	 ** @return le nom du bab
	 **/
	public String getNomBaB() {
		return this.nomBaB;
	}

	/**
	 ** méthode permettant de retourner la date du BaB
	 ** @return la date du BaB
	 **/
	public String getDateBaB() {
		return this.dateBaB;
	}

	/**
	 ** méthode permettant de retourner le prix d'un stand
	 ** @return le prix du stand
	 **/
	public int getPrixStand() {
		return this.prixStand;
	}

	/**
	 ** méthode permettant de retourner la taille X du BaB
	 ** @return la taille X du BaB
	 **/
	public int getDimX() {
		return this.dimX;
	}

	/**
	 ** méthode permettant de retourner la taille Y du BaB
	 ** @return la taille Y du BaB
	 **/
	public int getDimY() {
		return this.dimY;
	}
	
	
	/**
	 ** méthode permettant de retourner l'adresse du BaB
	 ** @return l'adresse du BaB
	 **/
	public String getAdresseBaB() {
		return this.adresseBaB;
	}

	/**
	 ** méthode permettant de récupérer la liste des stands
	 ** @return stands la liste des stands
	 **/
	public LinkedList<Emplacement> getListeStand() {
		return this.stands;
	}
	
	/**
	 ** méthode permettant de récupérer la liste des autres
	 ** @return autres la liste des autres
	 **/
	public LinkedList<Emplacement> getListeAutre() {
		return this.autres;
	}

	/**
	 ** méthode permettant de récupérer la liste des stands supprimés
	 ** @return standsSuppr la liste des stands
	 **/
	public LinkedList<Emplacement> getListeStandSuppr() {
		return this.standsSuppr;
	}
	
	/**
	 ** méthode permettant de récupérer la liste des autres supprimés
	 ** @return autresSuppr la liste des autres
	 **/
	public LinkedList<Emplacement> getListeAutreSuppr() {
		return this.autresSuppr;
	}
	
	/**
	 ** méthode permettant de récupérer la liste des reservations
	 ** @return reservations la liste des reservation
	 **/
	public LinkedList<Reservation> getListeReservation() {
		return this.reservations;
	}
	
	/**
	 ** méthode permettant de récupérer la liste des reservations supprimés
	 ** @return reservationsSuppr la liste des reservation
	 **/
	public LinkedList<Reservation> getListeReservationSuppr() {
		return this.reservationsSuppr;
	}
	/************************************** DEBOGGAGE **************************************/
	
	public void afficherStands() {
		System.out.println("stands : ");
		for(int i = 0; i < stands.size(); i++) {
			stands.get(i).afficher();
		}
	}
	
}

