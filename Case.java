
//package GereMonBaB;

import java.util.*;

public class Case {

	private int id;
	
	//Coordonnées de la case dans le quadrillage
	private int coordonneeX;
	private int coordonneeY;
	
	private boolean etat; 
	
	public Case(int id, int coordX, int coordY) {
	
		this.id = id;
		this.coordonneeX = coordX;
		this.coordonneeY = coordY;
		this.etat = true;
		
	}
	
	/**
	 ** méthode retournant l'identifiant de la case
	 ** @return id
	 **/
	public int getId() {
		return this.id;
	}
	
	/**
	 ** méthode retournant la coordonnée X de la case
	 ** @return coordonneeX
	 **/
	public int getCoordonneeX() {
		return this.coordonneeX;
	}
	
	/**
	 ** méthode retournant la coordonnée Y de la case
	 ** @return coordonneeY
	 **/
	public int getCoordonneeY() {
		return this.coordonneeY;
	}
	
	/**
	 ** méthode retournant l'etat d'occupation de la case
	 ** @return etat
	 **/
	public boolean estLibre() {
		return this.etat;
	}
	
	/**
	 ** méthode changeant l'occupation de la case à false
	 **/
	public void occuper() {
		this.etat = false;
	}
	
	/**
	 ** méthode changeant l'occupation de la case à true
	 **/
	public void liberer() {
		this.etat = true;
	}
}
