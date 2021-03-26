
//package GereMonBaB;

import java.util.*;

public class Emplacement {

	//Variable de sauvegarde
	private int idSauvegarde;
	
	//Variables d'identifications
	private int idType;
	private String type;
	
	//Nom de l'emplacement
	private String nom;
	
	//Taille de l'emplacement
	private int tailleX;
	private int tailleY;
	
	//Coordonnée de l'origine de l'emplacement (Coin haut gauche)
	private int coordonneeX;
	private int coordonneeY;
	
	//Status de la réservation et du paiement
	private String reservation;
	private String paiement;
	 
	public Emplacement(int idType, String type, int coordX, int coordY) {
		this.idSauvegarde = 0;
		this.idType = idType;
		this.type = type;
		this.coordonneeX = coordX;
		this.coordonneeY = coordY;
		
		this.nom = type + idType;
		
		this.reservation = "libre";
		this.paiement = "aucun";
	}
	
	/**
	 ** méthode retournant l'identifiant de la sauvegarde de l'emplacement
	 ** @return idType
	 **/
	public int getIdSauvegarde() {
		return this.idSauvegarde;
	}

	/**
	 ** méthode mettant a jour l'id
	 **/
	public void setIdSauvegarde(int nvId) {
		this.idSauvegarde = nvId;
	}
	
	/**
	 ** méthode retournant l'identifiant du type de l'emplacement
	 ** @return idType
	 **/
	public int getIdType() {
		return this.idType;
	}

	/**
	 ** méthode mettant a jour l'idType
	 **/
	public void setIdType(int nvId) {
		this.idType = nvId;
	}
	
	/**
	 ** méthode retournant le type de l'emplacement
	 ** @return type
	 **/
	public String getType() {
		return this.type;
	}
	
	/**
	 ** méthode retournant le nom de l'emplacement
	 ** @return nom
	 **/
	public String getNom() {
		return this.nom;
	}
	
	/**
	 ** méthode retournant la taille X de l'emplacement
	 ** @return tailleX
	 **/
	public int getTailleX() {
		return this.tailleX;
	}
	
	/**
	 ** méthode mettant à jour la taille X de l'emplacement
	 ** @param tailleX
	 **/
	public void setTailleX(int nvTailleX) {
		this.tailleX = nvTailleX;
	}
	
	/**
	 ** méthode retournant la taille Y de l'emplacement
	 ** @return tailleY
	 **/
	public int getTailleY() {
		return this.tailleY;
	}
	
	/**
	 ** méthode mettant à jour la taille Y de l'emplacement
	 ** @param tailleY
	 **/
	public void setTailleY(int nvTailleY) {
		this.tailleY = nvTailleY;
	}
	
	/**
	 ** méthode retournant la coordonnee X de l'emplacement
	 ** @return coordonneeX
	 **/
	public int getCoordonneeX() {
		return this.coordonneeX;
	}
	
	/**
	 ** méthode retournant la coordonnee Y de l'emplacement
	 ** @return coordonneeY
	 **/
	public int getCoordonneeY() {
		return this.coordonneeY;
	}

	/**
	 ** méthode retournant la largeur de l'emplacement
	 ** @return 1
	 **/
	public Double getLargeur() {
		return 1.0;
	}

	/**
	 ** méthode retournant la longueur de l'emplacement
	 ** @return 1
	 **/
	public Double getLongueur() {
		return 1.0;
	}
	
	/**
	 ** méthode permettant de savoir si c'est un stand ou non
	 ** @return false
	 **/
	public boolean estStand() {
		return false;
	}
	
	/**
	 ** méthode retournant le statut de la réservation de l'emplacement
	 ** @return reservation
	 **/
	public String getReservation() {
		return this.reservation;
	}
	
	/**
	 ** méthode mettant à jour le statut de la réservation de l'emplacement
	 ** @param nvStatut
	 **/
	public void setReservation(String nvStatut) {
		this.reservation = nvStatut;
	}
	
	/**
	 ** méthode retournant le statut du paiement de l'emplacement
	 ** @return paiement
	 **/
	public String getPaiement() {
		return this.paiement;
	}
	
	/**
	 ** méthode mettant à jour le statut du paiement de l'emplacement
	 ** @param nvStatut
	 **/
	public void setPaiement(String nvStatut) {
		this.paiement = nvStatut;
	}

	/** DEBUGGAGE
	 ** méthode d'affichage des informations dans le terminal
	 **/
	 public void afficher() {
	 	/*String affichage;
	 	affichage  = "\t" + nom +"\n";
	 	affichage += "\t" + idType +"\n";
	 	affichage += "\t" + type +"\n";
	 	affichage += "\tCoordonnées : " + coordonneeX + "," + coordonneeY +"\n";
	 	return affichage;*/
		System.out.println("nom :"+ nom +" idType : " + idType + " type : " + type + " Coordonnées :" + " ("+coordonneeX +","+ coordonneeY+")");
	 }
}
