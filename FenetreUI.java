import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Cst {
	// Dimension de la grille
	public static final int NB_LIGNE = 30;
	public static final int NB_COL = 100;
}

public class FenetreUI extends AbstractAction{
	static String type;
	static LinkedList<JButton> listBut = new LinkedList<>();
	private JButton btnTypeStand;
	private JButton btnTypeBuvette;
	private JButton btnTypeBoulangerie;
	private JButton btnTest;
	private JButton btnTest2;
	private JFrame frame;
	private JPanel panFenetre;
	private JPanel panGrille;
	private JPanel panBtnType;
	static Emplacements emplacements;
	static TabQuadrillage tabStands;
	static TabQuadrillage tabDecos;
	private String[] nomColStands = {"Numero","Longueur","Largeur","Supprimer"};
	private String[] nomColDecos  = {"Numero","Nom_deco","Supprimer"};
	private JTable tableStands;
	private JTable tableDecos;
	private JScrollPane scrollStands;
	private JScrollPane scrollDecos;
	


	public FenetreUI(){
		frame = new JFrame("Quadrillage");
		panFenetre = new JPanel(new GridLayout(2,2));
		panGrille = new JPanel(new GridLayout(Cst.NB_LIGNE, Cst.NB_COL));
		panBtnType = new JPanel(new GridLayout(1,3));
		btnTest = new JButton("C UN TEST");
		btnTest2 = new JButton("C UN TEST 2");
		btnTypeStand = new JButton("Faire Stand");
		btnTypeStand.addActionListener(this);
		btnTypeBuvette = new JButton("Faire Buvette");
		btnTypeBuvette.addActionListener(this);
		btnTypeBoulangerie = new JButton("Faire Boulangerie");
		btnTypeBoulangerie.addActionListener(this);
		emplacements = new Emplacements();
		type = "STAND";
		tabStands = new TabQuadrillage(null, nomColStands);
		tabDecos = new TabQuadrillage(null, nomColDecos);
		tableStands = new JTable(tabStands);
		tableDecos = new JTable(tabDecos);
		scrollStands = new JScrollPane(tableStands);
		scrollDecos = new JScrollPane(tableDecos);
		
		
		int ind = 0; // Indice du bouton dans la liste
 
		for(int i = 0; i < Cst.NB_LIGNE; i++)
			for(int j = 0; j < Cst.NB_COL; j++) {
				JButton but = new JButton(new ActionBtn(i, j, ind));
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
		panFenetre.add(tableStands);
		panFenetre.add(tableDecos);
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

	
	public static void main(String [] args) {
		
		FenetreUI fenete = new FenetreUI();
		
	}

}
