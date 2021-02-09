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
	private JButton but;
	private JButton boutonCourant;
	private LinkedList<JButton> caseEmplacement;
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
		but = FenetreUI.listBut.get(ind);

		// ***** Pas sélectionné *****
		if(clic == false) {
			clic = true;
			
			
            type = FenetreUI.type;
			empAjoute = FenetreUI.emplacements.ajouterEmplacement(type, coord_x, coord_y); //longueur et largueur temporaire
			
			caseEmplacement = FenetreUI.listBut;
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					int nb = ind+j+10*i;
					System.out.println(empAjoute.getLongueur());
					System.out.println(nb);
					boutonCourant = FenetreUI.listBut.get(nb);
					
				    boutonCourant.setBackground(Color.GREEN);
					boutonCourant.setEnabled(false);
				}
			}
			
			but.setText(Integer.toString(empAjoute.getNumEmplacement()));
			if(empAjoute.estStand()){
				Object[] newData = {empAjoute.getNumEmplacement(),empAjoute.getLargeur(),empAjoute.getLongueur()};
				FenetreUI.tabStands.addRow(newData);
				empAjoute.afficheToi();
				FenetreUI.tableStands.getColumn("Supprimer").setCellRenderer(new MyRendererAndEditor(FenetreUI.tableStands,empAjoute));
				FenetreUI.tableStands.getColumn("Supprimer").setCellEditor(new MyRendererAndEditor(FenetreUI.tableStands,empAjoute));
			}
			else{
				but.setBackground(Color.BLACK);
				Object[] newData = {empAjoute.getNumEmplacement(),empAjoute.getType()};
				FenetreUI.tabDecos.addRow(newData);
				FenetreUI.tableDecos.getColumn("Supprimer").setCellRenderer(new MyRendererAndEditor(FenetreUI.tableDecos,empAjoute));
				FenetreUI.tableDecos.getColumn("Supprimer").setCellEditor(new MyRendererAndEditor(FenetreUI.tableDecos,empAjoute));
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
	
	public boolean getClic() {
		return clic;
	}
	
}
