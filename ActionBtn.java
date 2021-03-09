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
			if(FenetreUI.getSystem().ajouterEmplacement(coord_x, coord_y)) {
				FenetreUI.getSystem().afficherStands();
				if(FenetreUI.getSystem().getType().equals("Stand")){
					//on recupere l'emplacement
					emplacementAjoute = FenetreUI.getSystem().getListeStand().getLast();
					
				}
				else{
					//on recupere l'emplacement
					emplacementAjoute = FenetreUI.getSystem().getListeAutre().getLast();
				}
						
			
				
			
				
				if(emplacementAjoute.getType().equals("Stand")){
					//on met à jour le bouton
					boutonCourant.setBackground(Color.GREEN);
					boutonCourant.setEnabled(false);
					boutonCourant.setText(Integer.toString(emplacementAjoute.getIdType()));
					//ajoute le bouton a la liste
					FenetreUI.listButStands.add(boutonCourant);
					//on remplit le tableau
					Object[] newData = {emplacementAjoute.getIdType(),emplacementAjoute.getCoordonneeX(),emplacementAjoute.getCoordonneeY()};
					FenetreUI.tabStands.addRow(newData);
					//ajoute le bouton supprimer
					FenetreUI.tableStands.getColumn("").setCellRenderer(new MyRendererAndEditor(FenetreUI.tableStands,"Stand"));
					FenetreUI.tableStands.getColumn("").setCellEditor(new MyRendererAndEditor(FenetreUI.tableStands,"Stand"));
				}
				else{
					Autre emplacementAjouteAutre = (Autre) emplacementAjoute;
					//on met à jour le/les boutons
					for(int i = 0;i<emplacementAjouteAutre.getLargeur();i++){
						for(int j = ind;j<ind+emplacementAjouteAutre.getLongueur();j++){
							boutonCourant = FenetreUI.listBut.get(i * FenetreUI.getSystem().getDimX() + j);
							boutonCourant.setBackground(Color.BLACK);
							boutonCourant.setEnabled(false);
							boutonCourant.setText(Integer.toString(emplacementAjouteAutre.getIdType()));
						}
					}
					
					
					FenetreUI.listButAutres.add(boutonCourant);
					Object[] newData = {emplacementAjouteAutre.getIdType(),emplacementAjouteAutre.getType(),emplacementAjouteAutre.getCoordonneeX(),emplacementAjouteAutre.getCoordonneeY(),emplacementAjouteAutre.getLargeur(),emplacementAjouteAutre.getLongueur()};
					FenetreUI.tabAutres.addRow(newData);
					FenetreUI.tableAutres.getColumn("Supprimer").setCellRenderer(new MyRendererAndEditor(FenetreUI.tableAutres,"Autre"));
					FenetreUI.tableAutres.getColumn("Supprimer").setCellEditor(new MyRendererAndEditor(FenetreUI.tableAutres,"Autre"));
				}
			}
		//}

		System.out.println(ind + "=" + coord_x + ":" + coord_y + "(" + clic + ")"); // Pour dev....
	}
	
	public boolean getClic() {
		return clic;
	}
	
}
