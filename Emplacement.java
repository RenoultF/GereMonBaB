
//package GereMonBaB;

import java.util.*;

public class Emplacement {
	
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
	 
	public Emplacement(int idType, String type, int coordX, int coordY) {
		this.idType = idType;
		this.type = type;
		this.coordonneeX = coordX;
		this.coordonneeY = coordY;
		
		this.nom = type + idType;
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
		this.idType = this.idType + nvId;
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
	 ** méthode retournant la taille Y de l'emplacement
	 ** @return tailleY
	 **/
	public int getTailleY() {
		return this.tailleY;
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
	 ** méthode permettant de savoir si c'est un stand ou non
	 ** @return false
	 **/
	public boolean estStand() {
		return false;
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
