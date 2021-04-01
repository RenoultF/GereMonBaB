/**
 ** Sous classe d'Emplacement qui permet d'indiquer le type d'un emplacement automatiquement
 **/
import java.util.*;

public class Stand extends Emplacement {

	public Stand(int idType, int coordX, int coordY, int tailleAutreLargeur,int tailleAutreLongueur) {
		super(idType, "Stand", coordX, coordY, tailleAutreLargeur,tailleAutreLongueur);
	}

	/**
	 ** méthode permettant de savoir si c'est un stand ou non
	 ** @return true
	 **/
	@Override
	public boolean estStand() {
		return true;
	}
}
	
