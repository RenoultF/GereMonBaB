import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

class Cst {
	// Dimension de la grille
	public static final int NB_LIGNE = 5;
	public static final int NB_COL = 10;
}

public class FenetreUI extends AbstractAction{
	
	static String type;
	static LinkedList<JButton> listBut = new LinkedList<>();
	private JButton btnTypeStand;
	private JButton btnTypeBuvette;
	private JButton btnTypeBoulangerie;
	private JFrame frame;
	private JFrame fenInfo;
	private int tailleStand;
	private JPanel panFenetre;
	private JPanel panGrille;
	private JPanel panBtnType;
	static Emplacements emplacements;
	private String[] nomColStands = {"Numero","Longueur","Largeur","Supprimer"};
	private String[] nomColDecos  = {"Numero","Nom_deco","Supprimer"};
	public static DefaultTableModel tabStands;
	public static DefaultTableModel tabDecos;
	public static JTable tableStands;
	public static JTable tableDecos;
	private JScrollPane scrollStands;
	private JScrollPane scrollDecos;
	private Dimensions dim;
	


	public FenetreUI(Dimensions dim){
		this.dim = dim;
		frame = new JFrame("Quadrillage");
		panFenetre = new JPanel(new GridLayout(2,2));
		panGrille = new JPanel(new GridLayout(Cst.NB_LIGNE, Cst.NB_COL));
		panBtnType = new JPanel(new GridLayout(1,3));
		btnTypeStand = new JButton("Faire Stand");
		btnTypeStand.addActionListener(this);
		btnTypeBuvette = new JButton("Faire Buvette");
		btnTypeBuvette.addActionListener(this);
		btnTypeBoulangerie = new JButton("Faire Boulangerie");
		btnTypeBoulangerie.addActionListener(this);
		emplacements = new Emplacements(dim.getLargeur(),dim.getLongueur());
		type = "STAND";
		tabStands = new DefaultTableModel(nomColStands,0);
		tabDecos = new DefaultTableModel(nomColDecos,0);
		tableStands = new JTable(tabStands);
		tableDecos = new JTable(tabDecos);
		scrollStands = new JScrollPane(tableStands);
		scrollDecos = new JScrollPane(tableDecos);
		
		
		int ind = 0; // Indice du bouton dans la liste
 
		for(int i = 0; i < Cst.NB_LIGNE; i++)
			for(int j = 0; j < Cst.NB_COL; j++) {
				JButton but = new JButton(new ActionBtn(i, j, ind));
				but.setBackground(Color.WHITE);
				ind += 1;
				listBut.add(but);
				panGrille.add(but);
			}
		
	
		panBtnType.add(btnTypeStand);
		panBtnType.add(btnTypeBuvette);
		panBtnType.add(btnTypeBoulangerie);
		
		// ***** Fenêtre *****
		panFenetre.add(panGrille);
		panFenetre.add(panBtnType);
		panFenetre.add(scrollStands);
		panFenetre.add(scrollDecos);
		frame.getContentPane().add(panFenetre);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Emplacement temp;
		if(e.getSource() == btnTypeStand){
			this.type = "STAND";
			System.out.println("on passe au type :" + this.type);
		
		}
		else if(e.getSource() == btnTypeBuvette){
			this.type = "Buvette";
			System.out.println("on passe au type :" + this.type);
		}
		else if(e.getSource() == btnTypeBoulangerie){
			this.type = "Boulangerie";
			System.out.println("on passe au type :" + this.type);
		}
	}

	public static void actualiserTab(String type){
		int i;
		if(type!="STAND"){
			System.out.println("taille emplacement :" +emplacements.getDecos().size());
			for(i = 0;i<emplacements.getDecos().size();i++){
				System.out.println("i = "+ i);
				tableDecos.setValueAt(emplacements.getDecos().get(i).getNumEmplacement(), i, 0);
			}
		}
		else{
			System.out.println("taille emplacement :" +emplacements.getStands().size());
			for(i = 0;i<emplacements.getStands().size();i++){
				System.out.println("i = "+ i);
				tableStands.setValueAt(emplacements.getStands().get(i).getNumEmplacement(), i, 0);
			}
		}
		
	}

}

