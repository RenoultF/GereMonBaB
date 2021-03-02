import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;


public class FenetreUI extends AbstractAction{
	
	/** Déclaration des variables du système **/
	private static BaB system;
	private Quadrillage carte;
	private int dimX;
	private int dimY;
	
	private static LinkedList<Emplacement> listeStand = new LinkedList<Emplacement>();
	private static LinkedList<Emplacement> listeAutre = new LinkedList<Emplacement>();
	
	/** Déclaration des variables de la fenêtre **/
	static String type;
	
	static LinkedList<JButton> listBut = new LinkedList<>();
	private JButton btnTypeStand;
	private JButton btnTypeAutre;
	
	private JFrame frame;
	private JFrame fenInfo;
	
	private JPanel panFenetre;
	private JPanel panGrille;
	private JPanel panBtnType;
	
	private String[] nomColStands = {"Numero","X","Y",""};
	private String[] nomColAutres = {"Numero","Nom_deco","X","Y","Supprimer"};
	
	public static DefaultTableModel tabStands;
	public static DefaultTableModel tabAutres;
	public static JTable tableStands;
	public static JTable tableAutres;
	
	private JScrollPane scrollStands;
	private JScrollPane scrollAutres;


	public FenetreUI(BaB system){
		
		/** Variables du système **/
		this.system = system;
		carte = system.getCarte();
		dimX = carte.getDimensionX();
		dimY = carte.getDimensionY();
		
		listeStand = system.getListeStand();
		listeAutre = system.getListeAutre();
		
		
		/** Variables de la fenêtre **/
		frame = new JFrame("Quadrillage");
		panFenetre = new JPanel(new GridLayout(2,2));
		panGrille = new JPanel(new GridLayout(dimX, dimY));
		panBtnType = new JPanel(new GridLayout(1,3));
		
		btnTypeStand = new JButton("Stand");
		btnTypeStand.addActionListener(this);
		btnTypeAutre = new JButton("Autre");
		btnTypeAutre.addActionListener(this);
		
		tabStands = new DefaultTableModel(nomColStands,0);
		tabAutres = new DefaultTableModel(nomColAutres,0);
		tableStands = new JTable(tabStands);
		tableAutres = new JTable(tabAutres);
		scrollStands = new JScrollPane(tableStands);
		scrollAutres = new JScrollPane(tableAutres);
		
		
		
		/**
		 ** Creation des boutons pour la carte
		 **/
		int ind = 0; // Indice du bouton dans la liste
 
		for(int i = 0; i < dimX; i++)
			for(int j = 0; j < dimY; j++) {
				JButton but = new JButton(new ActionBtn(ind, i, j));
				but.setBackground(Color.WHITE);
				ind += 1;
				listBut.add(but);
				panGrille.add(but);
			}
		
	
		panBtnType.add(btnTypeStand);
		panBtnType.add(btnTypeAutre);
		
		// ***** Fenêtre *****
		panFenetre.add(panGrille);
		panFenetre.add(panBtnType);
		panFenetre.add(scrollStands);
		panFenetre.add(scrollAutres);
		frame.getContentPane().add(panFenetre);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Emplacement temp;
		if(e.getSource() == btnTypeStand){
			system.setType("Stand");
			System.out.println("on passe au type :" + system.getType());
		
		}
		else if(e.getSource() == btnTypeAutre){
			system.setType("autre");
			System.out.println("on passe au type :" + system.getType());
		}
	}

	public static void actualiserTab(String type){
		int i;
		if(type!="Stand"){
			System.out.println("taille emplacement :" + listeStand.size());
			for(i = 0; i < listeStand.size(); i++){
				System.out.println("i = "+ i);
				tableAutres.setValueAt(listeStand.get(i).getIdType(), i, 0);
			}
		}
		else{
			System.out.println("taille emplacement :" + listeAutre.size());
			for(i = 0; i < listeAutre.size(); i++){
				System.out.println("i = "+ i);
				tableStands.setValueAt(listeAutre.get(i).getIdType(), i, 0);
			}
		}
		
	}
	
	public static BaB getSystem() {
		return system;
	}

}

