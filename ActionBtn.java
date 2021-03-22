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

				//Pour creer les emplacements stands
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

				//Pour créer les emplacements autres
				else{
					Autre emplacementAjouteAutre = (Autre) emplacementAjoute;
					//On vérifie si on peut mettre l'emplacement
					int erreurEmplacement;
					erreurEmplacement = FenetreUI.getSystem().emplacementPossible(emplacementAjouteAutre);
					System.out.print("\n erreur = " + erreurEmplacement + "\n");
					//l'emplacement peut être créé
					if(erreurEmplacement == 0){
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
						//on met a jour le tableau
						Object[] newData = {emplacementAjouteAutre.getIdType(),emplacementAjouteAutre.getType(),emplacementAjouteAutre.getCoordonneeX(),emplacementAjouteAutre.getCoordonneeY(),emplacementAjouteAutre.getLargeur(),emplacementAjouteAutre.getLongueur()};
						FenetreUI.tabAutres.addRow(newData);
						FenetreUI.tableAutres.getColumn("Supprimer").setCellRenderer(new MyRendererAndEditor(FenetreUI.tableAutres,"Autre"));
						FenetreUI.tableAutres.getColumn("Supprimer").setCellEditor(new MyRendererAndEditor(FenetreUI.tableAutres,"Autre"));
					}
					else if(erreurEmplacement == 1){
						int retour = JOptionPane.showConfirmDialog(FenetreUI.getJFrame(), "Vous allez recouvrir un Stand existant, vous-vous continuer ?","Avertissement Creation Autre",JOptionPane.OK_CANCEL_OPTION);
						//on veut quand meme créer l'emplacement Autre
						if(retour!=2){
							for(int i = 0;i<emplacementAjouteAutre.getLargeur();i++){
								for(int j = ind;j<ind+emplacementAjouteAutre.getLongueur();j++){
									boutonCourant = FenetreUI.listBut.get(i * FenetreUI.getSystem().getDimX() + j);
									boutonCourant.setBackground(Color.BLACK);
									boutonCourant.setEnabled(false);
									boutonCourant.setText(Integer.toString(emplacementAjouteAutre.getIdType()));
								}
							}
							FenetreUI.listButAutres.add(boutonCourant);
							//on met a jour le tableau
							Object[] newData = {emplacementAjouteAutre.getIdType(),emplacementAjouteAutre.getType(),emplacementAjouteAutre.getCoordonneeX(),emplacementAjouteAutre.getCoordonneeY(),emplacementAjouteAutre.getLargeur(),emplacementAjouteAutre.getLongueur()};
							FenetreUI.tabAutres.addRow(newData);
							FenetreUI.tableAutres.getColumn("Supprimer").setCellRenderer(new MyRendererAndEditor(FenetreUI.tableAutres,"Autre"));
							FenetreUI.tableAutres.getColumn("Supprimer").setCellEditor(new MyRendererAndEditor(FenetreUI.tableAutres,"Autre"));
						}
						else{
							FenetreUI.getSystem().supprimerAutre(emplacementAjoute.getIdType());
						}		
					}
					else if(erreurEmplacement == 2){
						JOptionPane.showMessageDialog(FenetreUI.getJFrame(), "Vous depassez les limitations du BaB", "Probleme Depassement",JOptionPane.WARNING_MESSAGE);
						FenetreUI.getSystem().supprimerAutre(emplacementAjoute.getIdType());
						System.out.println("\nErreur dépassement !!\n");
					}
					else{
						System.out.println("\nProbleme Erreur emplacement Possible !!\n");
					}		
				}
			}
		//}

		System.out.println(ind + "=" + coord_x + ":" + coord_y + "(" + clic + ")"); // Pour dev....
	}
	
	public boolean getClic() {
		return clic;
	}
	
}
