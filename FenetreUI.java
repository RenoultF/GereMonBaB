import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;


public class FenetreUI extends AbstractAction{
	
	/** Déclaration des variables du système **/
	private static BaB system;
	private Quadrillage carte;
	public static int dimX;
	private int dimY;
	
	private static LinkedList<Emplacement> listeStand = new LinkedList<Emplacement>();
	private static LinkedList<Emplacement> listeAutre = new LinkedList<Emplacement>();
	
	/** Déclaration des variables de la fenêtre **/
	static String type;
	
	public static LinkedList<JButton> listBut = new LinkedList<>();
	private JButton btnTypeStand;
	private JButton btnTypeAutre;
	
	private JFrame frame;
	private JFrame fenInfo;

	private JTabbedPane ongletBab;
	
	private JPanel panFenetre;
	private JPanel panGrille;

	private JPanel panBtnType;
	private JPanel panInfoBaB;
	private JPanel panListeReservation;

	
	private String[] nomColStands = {"Numero","X","Y",""};
	private String[] nomColAutres = {"Numero","Nom_deco","X","Y","Supprimer"};
	
	public static DefaultTableModel tabStands;
	public static DefaultTableModel tabAutres;
	public static JTable tableStands;
	public static JTable tableAutres;
	
	private JScrollPane scrollStands;
	private JScrollPane scrollAutres;

	private Semaphore sem;


	public FenetreUI(BaB system){
		
		/** Variables du système **/
		this.system = system;
		carte = system.getCarte();
		dimX = carte.getDimensionX();
		dimY = carte.getDimensionY();
		
		listeStand = system.getListeStand();
		listeAutre = system.getListeAutre();
		sem = new Semaphore(1);
		
		/** Variables de la fenêtre **/
		frame = new JFrame("Quadrillage");
		panFenetre = new JPanel(new GridLayout(2,2));
		panGrille = new JPanel(new GridLayout(dimX, dimY));

		ongletBab = new JTabbedPane();
		panBtnType = new JPanel(new GridLayout(1,3));
		panInfoBaB = new JPanel();
		panListeReservation = new JPanel();


		
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
		ongletBab.add("Creation emplacement",panBtnType);
		ongletBab.add("Infomation BaB",panInfoBaB);
		ongletBab.add("Liste Reservation",panListeReservation);
		panFenetre.add(ongletBab);
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
			FenetreAutre fenAutre = new FenetreAutre(1.5,1.5,"Autre",system);
			fenAutre.saisir();
		}
	}

	public static void actualiserTab(String type, int index){
		int i;
		if(type.equals("Stand")){
			for(i = index; i < listeStand.size(); i++){
				//on met a jour l'id de tous les autres stands
				listeStand.get(i).setIdType(listeStand.get(i).getIdType()-1);
				//on change la valeur dans le tableau
				tableStands.setValueAt(listeStand.get(i).getIdType(), i, 0);
			}
		}
		else{
			for(i = index; i < listeAutre.size(); i++){
				//on met a jour l'id de tous les autres stands
				listeAutre.get(i).setIdType(listeAutre.get(i).getIdType()-1);
				//on change la valeur dans le tableau
				tableAutres.setValueAt(listeAutre.get(i).getIdType(), i, 0);
			}
		}
		
	}
	
	public static BaB getSystem() {
		return system;
	}

}

