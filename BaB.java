//package GereMonBaB;

import java.util.*;
import java.awt.*;

public class BaB {

	//Variables
	private String nomBaB;
	private String dateBaB;
	private int prixStand;
	private String adresseBaB;
	private int dimX;
	private int dimY;
	
	//nombre de cases d'un Stand
	private Double tailleAutreLongueur;
	private Double tailleAutreLargeur;
	
	//Quadrillage de la carte
	private Quadrillage carte; 
	
	//liste des emplacements dans le bab
	private LinkedList<Emplacement> Stands;
	private LinkedList<Emplacement> autres;

	//type de l'emplacement selectionné
	private String type;
	
	//liste des exposants dans le bab
	//private LinkedList<Exposant> exposants;
	
	public BaB(String nomBaB, String dateBaB, int prixStand, String adresse, int dimX, int dimY) {
	
		this.dimX = dimX; //Valeur par défaut
		this.dimY = dimY;

		this.nomBaB = nomBaB;
		this.dateBaB = dateBaB;
		this.prixStand = prixStand;
		this.adresseBaB = adresse;
		
		//Initialisation des listes
		carte = new Quadrillage(this.dimX, this.dimY);
		Stands = new LinkedList<Emplacement>();
		autres = new LinkedList<Emplacement>();
		type = new String("Stand");
	
	
	}
	
	/************************************** Méthodes **************************************/
	
	public boolean ajouterStand(int coordX, int coordY) {
		int idType;
		Case caseCourante = carte.getCaseXY(coordX, coordY);
		if(caseCourante.estLibre()) {
			
			if(Stands.size() == 0)
				idType = 0;
			else
				idType = Stands.getLast().getIdType() + 1;
				
			Emplacement nouveauStand = new Stand(idType, coordX, coordY);
			Stands.addLast(nouveauStand);
			caseCourante.occuper();
			
			return true;
		}
		else {
			System.out.println("Case occupée");	
			return false;
		}
	}
	
	public void supprimerStand(int id) {
	
		int coordX = Stands.get(id).getCoordonneeX();
		int coordY = Stands.get(id).getCoordonneeY();
		carte.getCaseXY(coordX, coordY).liberer();
		
		Stands.remove(id);
		
	}
	
	public boolean ajouterAutre(int coordX, int coordY) {
		int idType;
		Case caseCourante = carte.getCaseXY(coordX, coordY);
		if(caseCourante.estLibre()) {
			
			if(autres.size() == 0)
				idType = 0;
			else
				idType = autres.getLast().getIdType() + 1;
				
			Emplacement nouveauAutre = new Autre(idType,this.type, coordX, coordY);
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
	
	public void supprimerAutre(int id) {
	
		int coordX = autres.get(id).getCoordonneeX();
		int coordY = autres.get(id).getCoordonneeY();
		carte.getCaseXY(coordX, coordY).liberer();
		
		autres.remove(id);
		
	}
	
	
	/************************************** Méthodes GET/SET **************************************/
	
	/**
	 ** méthode permettant de récupérer les cartes
	 ** @return carte le Quadrillage du BaB
	 **/
	public Quadrillage getCarte() {
		return this.carte;
	}
	
	/**
	 ** méthode permettant de récupérer la taille X des Stands
	 ** @return tailleAutreLongueur la taille X d'un Stand
	 **/
	public Double getTailleAutreLongueur() {
		return this.tailleAutreLongueur;
	}
	
	/**
	 ** méthode permettant de récupérer la taille Y des Stands
	 ** @return tailleAutreLongueur la taille Y d'un Stand
	 **/
	public Double getTailleAutreLargeur() {
		return this.tailleAutreLargeur;
	}
	
	/**
	 ** méthode permettant de modifier la taille X des Stands
	 ** @param nouveauX la nouvelle taille X d'un Stand
	 **/
	public void setTailleAutreLongueur(Double nouveauX) {
		this.tailleAutreLongueur = nouveauX;
	}
	
	/**
	 ** méthode permettant de modifier la taille Y des Stands
	 ** @param nouveauY la nouvelle taille Y d'un Stand
	 **/
	public void setTailleAutreLargeur(Double nouveauY) {
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
	 ** méthode permettant de modifier le Nom du BaB
	 ** @param nvDateBaB la nouvelle date du bab
	 **/
	public void setDateBaB(String nvDateBaB) {
		this.dateBaB = nvDateBaB;
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
	 ** méthode permettant de récupérer la liste des Stands
	 ** @return Stands la liste des Stands
	 **/
	public LinkedList<Emplacement> getListeStand() {
		return this.Stands;
	}
	
	/**
	 ** méthode permettant de récupérer la liste des autres
	 ** @return autres la liste des autres
	 **/
	public LinkedList<Emplacement> getListeAutre() {
		return this.autres;
	}
	
	/************************************** DEBOGGAGE **************************************/
	
	public void afficherStands() {
		System.out.println("Stands : ");
		for(int i = 0; i < Stands.size(); i++) {
			Stands.get(i).afficher();
		}
	}
	
}

