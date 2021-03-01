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
			if(FenetreUI.getSystem().ajouterEmplacement(coord_x, coord_y)) {
				clic = true;
				
				if(FenetreUI.getSystem().getType().equals("Stand")){
					emplacementAjoute = FenetreUI.getSystem().getListeStand().getLast();
				}
				else{
					emplacementAjoute = FenetreUI.getSystem().getListeAutre().getLast();
				}
						
			
				boutonCourant.setBackground(Color.GREEN);
				boutonCourant.setEnabled(false);
			
				boutonCourant.setText(Integer.toString(emplacementAjoute.getIdType()));
				if(emplacementAjoute.getType().equals("Stand")){
					Object[] newData = {emplacementAjoute.getIdType(),emplacementAjoute.getTailleX(),emplacementAjoute.getTailleY()};
					FenetreUI.tabStands.addRow(newData);
					//emplacementAjoute.afficheToi();
					FenetreUI.tableStands.getColumn("").setCellRenderer(new MyRendererAndEditor(FenetreUI.tableStands,emplacementAjoute));
					FenetreUI.tableStands.getColumn("").setCellEditor(new MyRendererAndEditor(FenetreUI.tableStands,emplacementAjoute));
				}
				else{
					boutonCourant.setBackground(Color.BLACK);
					Object[] newData = {emplacementAjoute.getIdType(),emplacementAjoute.getType()};
					FenetreUI.tabAutres.addRow(newData);
					FenetreUI.tableAutres.getColumn("Supprimer").setCellRenderer(new MyRendererAndEditor(FenetreUI.tableAutres,emplacementAjoute));
					FenetreUI.tableAutres.getColumn("Supprimer").setCellEditor(new MyRendererAndEditor(FenetreUI.tableAutres,emplacementAjoute));
				}
			}
		}

		System.out.println(ind + "=" + coord_x + ":" + coord_y + "(" + clic + ")"); // Pour dev....
	}
	
	public boolean getClic() {
		return clic;
	}
	
}
