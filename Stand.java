/**
 ** Sous classe d'Emplacement qui permet d'indiquer le type d'un emplacement automatiquement
 **/
import java.util.*;

public class Stand extends Emplacement {

	public Stand(int idType, int coordX, int coordY) {
		super(idType, "stand", coordX, coordY);
	}
}
	
