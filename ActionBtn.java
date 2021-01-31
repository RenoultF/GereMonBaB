import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class ActionBtn extends AbstractAction  {
	private int coord_x;
	private int coord_y;
	private int ind;
    private boolean clic = false;
    private String type;
	private Emplacement empAjoute;
	//private TabQuadrillage tabStands;
	//private TabQuadrillage tabDecos;

	public ActionBtn(int x, int y, int i) {
		coord_x = x;
		coord_y = y;
		ind = i;
		//this.tabStands = tStand;
		//this.tabDecos = tDecos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton but = Quadrillage.listBut.get(ind);

		// ***** Pas sélectionné *****
		if(clic == false) {
			clic = true;
            but.setBackground(Color.BLACK);
            but.setEnabled(false);
            type = Quadrillage.type;
			empAjoute = Quadrillage.emplacements.ajouterEmplacement(type, coord_x, coord_y, 1, 1); //longueur et largueur temporaire
			if(empAjoute.estStand()){
				Object[] newData = {empAjoute.getNumEmplacement(),empAjoute.getLargeur(),empAjoute.getLongueur(),empAjoute.getBtn()};
				Quadrillage.tabStands.addRow(newData);
			}
			else{
				Object[] newData = {empAjoute.getNumEmplacement(),empAjoute.getType(),empAjoute.getBtn()};
				Quadrillage.tabDecos.addRow(newData);
			}
		}

        // ***** Deja sélectionné *****
        /*
		else {
			clic = false;
			but.setBackground(Color.GRAY); // Couleur par défaut
		}*/

		System.out.println(ind + "=" + coord_x + ":" + coord_y + "(" + clic + ")"); // Pour dev....
	}
}