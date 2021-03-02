//package GereMonBaB;

import java.util.*;
import java.awt.*;

public class BaB {

	//Variables
	
	//nombre de cases d'un Stand
	private int tailleStandX;
	private int tailleStandY;
	
	//Quadrillage de la carte
	private Quadrillage carte; 
	
	//liste des emplacements dans le bab
	private LinkedList<Emplacement> Stands;
	private LinkedList<Emplacement> autres;

	//type de l'emplacement selectionné
	private String type;
	
	//liste des exposants dans le bab
	//private LinkedList<Exposant> exposants;
	
	public BaB() {
	
		int dimensionX = 10; //Valeur par défaut
		int dimensionY = 10;
		
		//Initialisation des listes
		carte = new Quadrillage(dimensionX, dimensionY);
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
	 ** @return tailleStandX la taille X d'un Stand
	 **/
	public int getTailleStandX() {
		return this.tailleStandX;
	}
	
	/**
	 ** méthode permettant de récupérer la taille Y des Stands
	 ** @return tailleStandX la taille Y d'un Stand
	 **/
	public int getTailleStandY() {
		return this.tailleStandY;
	}
	
	/**
	 ** méthode permettant de modifier la taille X des Stands
	 ** @param nouveauX la nouvelle taille X d'un Stand
	 **/
	public void setTailleStandX(int nouveauX) {
		this.tailleStandX = nouveauX;
	}
	
	/**
	 ** méthode permettant de modifier la taille Y des Stands
	 ** @param nouveauY la nouvelle taille Y d'un Stand
	 **/
	public void setTailleStandY(int nouveauY) {
		this.tailleStandY = nouveauY;
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

