/**
 ** Sous classe d'Emplacement qui permet d'indiquer le type d'un emplacement automatiquement
 **/
import java.util.*;

public class Autre extends Emplacement {

	private Double largeur;
	private Double longueur;

	public Autre(int idType, String type, int coordX, int coordY,Double larg, Double longueur) {
		super(idType, type, coordX, coordY);
		this.largeur = larg;
		this.longueur = longueur;
	}

	/**
	 ** méthode permettant de récupérer la largeur
	 ** @return largeur
	 **/
	public Double getLargeur(){
		return this.largeur;
	}

	/**
	 ** méthode permettant de récupérer la longueur
	 ** @return longueur
	 **/
	public Double getLongueur(){
		return this.longueur;
	}


	/**
	 ** méthode permettant de modifier la largeur
	 ** @param larg la nouvelle largeur
	 **/
	public void setLargeur(Double larg){
		this.largeur = larg;
	}

	/**
	 ** méthode permettant de modifier la longueur
	 ** @param lg la nouvelle longueur
	 **/
	public void setLongueur(Double lg){
		this.longueur = lg;
	}
}
	
