
//package GereMonBaB;

import java.util.*;

public class Quadrillage {

	//Dimension du quadrillage
	private int dimensionX;
	private int dimensionY;
	
	//Liste des cases dans le quadrillage
	private LinkedList<Case> cases;
	
	public Quadrillage(int dimX, int dimY) {
	
		this.dimensionX = dimX;
		this.dimensionY = dimY;
		
		creerQuadrillage();
	
	}
	
	/************************************** Méthodes **************************************/
	
	private void creerQuadrillage() {
	
	cases = new LinkedList<Case>();
	
	for(int i = 0; i < dimensionX; i++) {
		for(int j = 0; j < dimensionY; j++) {
			//Création d'une case avec un identifiant limitant à 9999 la taille de la carte
			cases.add(new Case(i*10000+j, i, j));
			}
		}
	}
	
	/**
	 ** méthode retournant la dimension X du quadrillage
	 ** @return dimensionX
	 **/
	public int getDimensionX() {
		return this.dimensionX;
	}
	
	/**
	 ** méthode retournant la dimension Y du quadrillage
	 ** @return dimensionY
	 **/
	public int getDimensionY() {
		return this.dimensionY;
	}
	
	/**
	 ** méthode retournant la case X Y du quadrillage
	 ** @param coordX la coordonnée en X 
	 ** @param coordY la coordonnée en Y 
	 ** @return Case
	 **/
	public Case getCaseXY(int coordX, int coordY) {
		for(int i = 0; i < cases.size(); i++) {
			if(cases.get(i).getId() == coordX*10000+coordY)
				return cases.get(i);
		}
		
		return cases.get(0);
	}
	
}
