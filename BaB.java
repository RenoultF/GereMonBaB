//package GereMonBaB;

import java.util.*;
import java.awt.*;

public class BaB {

	//Variables
	
	//nombre de cases d'un stand
	private int tailleStandX;
	private int tailleStandY;
	
	//Quadrillage de la carte
	private Quadrillage carte; 
	
	//liste des emplacements dans le bab
	private LinkedList<Emplacement> stands;
	private LinkedList<Emplacement> autres;
	
	//liste des exposants dans le bab
	//private LinkedList<Exposant> exposants;
	
	public BaB() {
	
		int dimensionX = 10; //Valeur par défaut
		int dimensionY = 10;
		
		//Initialisation des listes
		carte = new Quadrillage(dimensionX, dimensionY);
		stands = new LinkedList<Emplacement>();
		autres = new LinkedList<Emplacement>();
	
	
	}
	
	/************************************** Méthodes **************************************/
	
	public boolean ajouterStand(int coordX, int coordY) {
		int idType;
		Case caseCourante = carte.getCaseXY(coordX, coordY);
		if(caseCourante.estLibre() == true) {
			
			if(stands.size() == 0)
				idType = 0;
			else
				idType = stands.getLast().getIdType() + 1;
				
			Emplacement nouveauStand = new Stand(idType, coordX, coordY);
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
		if(caseCourante.estLibre() == true) {
			
			if(stands.size() == 0)
				idType = 0;
			else
				idType = stands.getLast().getIdType() + 1;
				
			Emplacement nouveauAutre = new Autre(idType, coordX, coordY);
			autres.addLast(nouveauAutre);
			caseCourante.occuper();
			
			return true;
		}
		else {
			System.out.println("Case occupée");	
			return false;
		}
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
	 ** méthode permettant de récupérer la taille X des stands
	 ** @return tailleStandX la taille X d'un stand
	 **/
	public int getTailleStandX() {
		return this.tailleStandX;
	}
	
	/**
	 ** méthode permettant de récupérer la taille Y des stands
	 ** @return tailleStandX la taille Y d'un stand
	 **/
	public int getTailleStandY() {
		return this.tailleStandY;
	}
	
	/**
	 ** méthode permettant de modifier la taille X des stands
	 ** @param nouveauX la nouvelle taille X d'un stand
	 **/
	public void setTailleStandX(int nouveauX) {
		this.tailleStandX = nouveauX;
	}
	
	/**
	 ** méthode permettant de modifier la taille Y des stands
	 ** @param nouveauY la nouvelle taille Y d'un stand
	 **/
	public void setTailleStandY(int nouveauY) {
		this.tailleStandY = nouveauY;
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
	
	/************************************** DEBOGGAGE **************************************/
	
	public void afficherStands() {
		System.out.println("Stands : ");
		for(int i = 0; i < stands.size(); i++) {
			System.out.println(stands.get(i).afficher());
		}
	}
	
}

