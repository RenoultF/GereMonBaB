//package GereMonBaB;

public class GereMonBaB {
		
	public static void main(String [] args) {
		FenetreParamBaB paramBaB = new FenetreParamBaB("nomBaB", "01/01/2021", 10, "rue des ...", 10, 10);
		paramBaB.saisir();
		BaB bab = new BaB(paramBaB.getNomBaB(),paramBaB.getDateBaB(),paramBaB.getPrixStand(),paramBaB.getAdresseBaB(),paramBaB.getDimensionX(),paramBaB.getDimensionY());
		FenetreUI fen = new FenetreUI(bab);
	}
}

