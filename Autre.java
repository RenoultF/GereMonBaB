/**
 ** Sous classe d'Emplacement qui permet d'indiquer le type d'un emplacement automatiquement
 **/
import java.util.*;

public class Autre extends Emplacement {

	private int largeur;
	private int longueur;

	public Autre(int idType, String type, int coordX, int coordY,int larg, int longueur) {
		super(idType, type, coordX, coordY, larg,longueur);
		this.largeur = larg;
		this.longueur = longueur;
	}

	/**
	 ** méthode permettant de récupérer la largeur
	 ** @return largeur
	 **/
	public int getLargeur(){
		return this.largeur;
	}

	/**
	 ** méthode permettant de récupérer la longueur
	 ** @return longueur
	 **/
	public int getLongueur(){
		return this.longueur;
	}


	/**
	 ** méthode permettant de modifier la largeur
	 ** @param larg la nouvelle largeur
	 **/
	public void setLargeur(int larg){
		this.largeur = larg;
	}

	/**
	 ** méthode permettant de modifier la longueur
	 ** @param lg la nouvelle longueur
	 **/
	public void setLongueur(int lg){
		this.longueur = lg;
	}
}
	
