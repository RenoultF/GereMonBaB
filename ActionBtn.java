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
		JButton but = FenetreUI.listBut.get(ind);

		// ***** Pas sélectionné *****
		if(clic == false) {
			clic = true;
            but.setBackground(Color.GREEN);
			but.setEnabled(false);
			
            type = FenetreUI.type;
			empAjoute = FenetreUI.emplacements.ajouterEmplacement(type, coord_x, coord_y, 1, 1); //longueur et largueur temporaire
			but.setText(Integer.toString(empAjoute.getNumEmplacement()));
			if(empAjoute.estStand()){
				Object[] newData = {empAjoute.getNumEmplacement(),empAjoute.getLargeur(),empAjoute.getLongueur(),empAjoute.getBtn()};
				FenetreUI.tabStands.addRow(newData);
			}
			else{
				but.setBackground(Color.BLACK);
				Object[] newData = {empAjoute.getNumEmplacement(),empAjoute.getType(),empAjoute.getBtn()};
				FenetreUI.tabDecos.addRow(newData);
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