import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class ActionBtn extends AbstractAction  {

	private int ind;
	private int coord_x;
	private int coord_y;
	
    private boolean clic = false;
    
	private JButton boutonCourant;
	
	private Emplacement emplacementAjoute;
	

	public ActionBtn(int i, int x, int y) {
		ind = i;
		coord_x = x;
		coord_y = y;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boutonCourant = FenetreUI.listBut.get(ind);

		// ***** Pas sélectionné *****
		if(clic == false) {
			
            //type = FenetreUI.type;
			if(FenetreUI.getSystem().ajouterStand(coord_x, coord_y) == true) {
				clic = true;
				
				emplacementAjoute = FenetreUI.getSystem().getListeStand().getLast();
						
			
				boutonCourant.setBackground(Color.GREEN);
				boutonCourant.setEnabled(false);
			
				boutonCourant.setText(Integer.toString(emplacementAjoute.getIdType()));
				//if(emplacementAjoute.estStand()){
					Object[] newData = {emplacementAjoute.getIdType(),emplacementAjoute.getTailleX(),emplacementAjoute.getTailleY()};
					FenetreUI.tabStands.addRow(newData);
					//emplacementAjoute.afficheToi();
					FenetreUI.tableStands.getColumn("Supprimer").setCellRenderer(new MyRendererAndEditor(FenetreUI.tableStands,emplacementAjoute));
					FenetreUI.tableStands.getColumn("Supprimer").setCellEditor(new MyRendererAndEditor(FenetreUI.tableStands,emplacementAjoute));
				//}
				/**
				else{
					but.setBackground(Color.BLACK);
					Object[] newData = {empAjoute.getNumEmplacement(),empAjoute.getType()};
					FenetreUI.tabDecos.addRow(newData);
					FenetreUI.tableDecos.getColumn("Supprimer").setCellRenderer(new MyRendererAndEditor(FenetreUI.tableDecos,empAjoute));
					FenetreUI.tableDecos.getColumn("Supprimer").setCellEditor(new MyRendererAndEditor(FenetreUI.tableDecos,empAjoute));
				}**/
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
