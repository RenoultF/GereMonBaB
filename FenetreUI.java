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
	public static LinkedList<JButton> listButStands = new LinkedList<>();
	public static LinkedList<JButton> listButAutres = new LinkedList<>();
	private JButton btnTypeStand;
	private JButton btnTypeAutre;
	
	private static JFrame frame;
	private JFrame fenInfo;

	private JTabbedPane ongletBab;
	
	private JPanel panFenetre;
	private JPanel panGrille;

	private JPanel panBtnType;
	private JPanel panInfoBaB;
	private JPanel panListeReservation;

	/*variable onglet information BaB*/
	private JLabel labNomBaB;
    private JLabel labDateBaB;
    private JLabel labPrixStand;
    private JLabel labAdresse;
    private JLabel labDimX;
    private JLabel labDimY;

	private JTextField txtNomBaB;
    private JTextField txtDateBaB;
    private JTextField txtPrixStand;
    private JTextField txtAdresse;
    private JTextField txtDimX;
    private JTextField txtDimY;

	private JButton btnSaveBaB;
	private JButton btnSupprimerBaB;

	private JButton btnVal;

	/*variable des tableaux*/
	private String[] nomColStands = {"Numero","Coordonnees","status","Paiement",""};
	private String[] nomColAutres = {"Numero","Nom_deco","Coordonnees","Largeur","Longueur","Supprimer"};

	private String[] nomColReservation = {"ID","Nom","Prenom","ID Stand","Coordonnees","Paiement","Accepter","Refuser"};
	public static DefaultTableModel tabReservation;
	public static JTable tableReservation;
	private JScrollPane scrollReservation;
	
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
		panInfoBaB = new JPanel(new GridLayout(8,2));
		panListeReservation = new JPanel();

		/*variable onglet info BaB*/
		labNomBaB = new JLabel("Nom du BaB :");
        labDateBaB = new JLabel("Date du BaB (JJ/MM/AAAA):");
        labAdresse = new JLabel("Adresse du BaB :");
        labDimX = new JLabel("Dimension X :");
        labDimY = new JLabel("Dimension Y :");
        labPrixStand = new JLabel("Prix d'un Stand :");

		txtNomBaB = new JTextField(15);
        txtDateBaB = new JTextField(10);
        txtAdresse = new JTextField(30);
        txtDimX = new JTextField(5);
        txtDimY = new JTextField(5);
        txtPrixStand = new JTextField(5);


		// ***** Valeurs par défaut *****
        txtNomBaB.setText(system.getNomBaB());
        txtDateBaB.setText(system.getDateBaB());
        txtAdresse.setText(system.getAdresseBaB());
        txtDimX.setText(Integer.toString(system.getDimX()));
        txtDimY.setText(Integer.toString(system.getDimY()));
        txtPrixStand.setText(Integer.toString(system.getPrixStand()));
		btnSaveBaB = new JButton("Sauvegarder BaB");
		btnSaveBaB.addActionListener(this);
		btnSupprimerBaB = new JButton("Supprimer BaB");
		btnSupprimerBaB.addActionListener(this);
		btnVal = new JButton("Valider informations");
		btnVal.addActionListener(this);

		
		btnTypeStand = new JButton("Stand");
		btnTypeStand.addActionListener(this);
		btnTypeAutre = new JButton("Autre");
		btnTypeAutre.addActionListener(this);
		
		tabReservation = new DefaultTableModel(nomColReservation,0);
		tableReservation = new JTable(tabReservation);
		scrollReservation = new JScrollPane(tableReservation);
		
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
				but.setText("");
				ind += 1;
				listBut.add(but);
				panGrille.add(but);
			}
		
	
		panBtnType.add(btnTypeStand);
		panBtnType.add(btnTypeAutre);

		
		panInfoBaB.add(labNomBaB);
        panInfoBaB.add(txtNomBaB);

        panInfoBaB.add(labDateBaB);
        panInfoBaB.add(txtDateBaB);

        panInfoBaB.add(labAdresse);
        panInfoBaB.add(txtAdresse);

        panInfoBaB.add(labDimX);
        panInfoBaB.add(txtDimX);

        panInfoBaB.add(labDimY);
        panInfoBaB.add(txtDimY);

        panInfoBaB.add(labPrixStand);
        panInfoBaB.add(txtPrixStand);

		panInfoBaB.add(btnVal);
		panInfoBaB.add(btnSupprimerBaB);
		panInfoBaB.add(btnSaveBaB);

		panListeReservation.add(scrollReservation);
		
		// ***** Fenêtre *****
		panFenetre.add(panGrille);
		ongletBab.add("Creation emplacement",panBtnType);
		ongletBab.add("Infomation BaB",panInfoBaB);
		ongletBab.add("Liste Reservation",panListeReservation);
		panFenetre.add(ongletBab);
		panFenetre.add(scrollStands);
		panFenetre.add(scrollAutres);
		actualiseFenetre();
		frame.getContentPane().add(panFenetre);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //frame.dispose(); si on veux juste quitter la fenetre et non aps l'appli.
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
			FenetreAutre fenAutre = new FenetreAutre(1.0,1.0,"Autre",system);
			fenAutre.saisir();
		}
		else if(e.getSource() == btnSaveBaB){
			JDBC_BDD baseDeDonnees = new JDBC_BDD();
			baseDeDonnees.startJDBC();
			System.out.println("Début de la sauvegarde...");
			baseDeDonnees.sauvegarderBab(system);
			System.out.println("Sauvegarde réussie");
		}
		else if(e.getSource() == btnSupprimerBaB){
			frame.dispose();
		}
		else if(e.getSource() == btnVal){
			//MAJ des variable du system
			system.setAdresseBaB(txtAdresse.getText());
			system.setNomBaB(txtNomBaB.getText());
			system.setDateBaB(txtDateBaB.getText());
			system.setPrixStand(Integer.valueOf(txtPrixStand.getText()));
			system.setDimX(Integer.valueOf(txtDimX.getText()));
			system.setDimY(Integer.valueOf(txtDimY.getText()));
		}
	}

	public static void actualiserTab(String type, int index){
		int i;
		if(type.equals("Stand")){
			for(i = index; i < listeStand.size(); i++){
				//on met a jour l'id de tous les autres stands
				listeStand.get(i).setIdType(listeStand.get(i).getIdType()-1);
				//mis a jour des valeur des boutons
				listButStands.get(i).setText(String.valueOf(listeStand.get(i).getIdType()));
				//on change la valeur dans le tableau
				tableStands.setValueAt(listeStand.get(i).getIdType(), i, 0);
			}
		}
		else{
			for(i = index; i < listeAutre.size(); i++){
				Emplacement empCourant = listeAutre.get(i);
				Autre emplacementAutre = (Autre) empCourant;
				int indBase = empCourant.getCoordonneeX()*dimX + empCourant.getCoordonneeY();
				//on met a jour l'id de tous les autres stands
				emplacementAutre.setIdType(listeAutre.get(i).getIdType()-1);
				//mis a jour des valeur des boutons
				for(int iTmp = 0;iTmp<emplacementAutre.getLargeur();iTmp++){
					for(int jTmp = indBase;jTmp<indBase+emplacementAutre.getLongueur();jTmp++){
						//on va cherche les boutons voulu pour les mettre à jour
					  listBut.get(iTmp * getSystem().getDimX() + jTmp).setText(String.valueOf(listeAutre.get(i).getIdType()));
					}
				}
				//on change la valeur dans le tableau
				tableAutres.setValueAt(listeAutre.get(i).getIdType(), i, 0);
			}
		}
		
	}

	public static void actualiserTabValResa(Reservation resa,int index){
		int i;
		int idEmplacement = resa.getEmplacement().getIdType();
		Iterator<Reservation> itResa = system.getListeReservation().iterator();
		while(itResa.hasNext()){
			Reservation resaTmp = itResa.next();
			int indexTmp;
			
				if(resaTmp.getEmplacement().getIdType()==idEmplacement){
					indexTmp = resaTmp.getIdReservation();
					actualiserTabSupprResa(indexTmp);
					itResa.remove();
					tabReservation.removeRow(indexTmp);
				}
			
		}
	}
	
	public static void actualiserTabSupprResa(int index){
		int i;
		for(i = index; i<system.getListeReservation().size();i++){
			system.getListeReservation().get(i).setIdReservation(system.getListeReservation().get(i).getIdReservation()-1);	
			//on change la valeur dans le tableau
			tableReservation.setValueAt(system.getListeReservation().get(i).getIdReservation(), i, 0);
		}
	}


	public static JFrame getJFrame(){
		return frame;
	}

	public static BaB getSystem() {
		return system;
	}

	public void actualiseFenetre(){
		int indBase;
		JButton btnCourant;
		if(!system.getListeStand().isEmpty()){
			for(Emplacement e : system.getListeStand()){
				indBase = e.getCoordonneeX()*dimX + e.getCoordonneeY();
				btnCourant = listBut.get(indBase);
				switch(e.getReservation()){
					case "libre":
						btnCourant.setBackground(Color.GREEN);
						break;
					case "semi_reserve":
						btnCourant.setBackground(Color.ORANGE);
						break;
					case "reserve":
						btnCourant.setBackground(Color.RED);
						btnCourant.setEnabled(false);
						break;
					default:
						System.out.println("Reservation non identitifé");
						btnCourant.setBackground(Color.GREEN);
				}
				btnCourant.setText(Integer.toString(e.getIdType()));
				listButStands.add(btnCourant);
				//on remplit le tableau
				String coordonneEmp = "( "+ e.getCoordonneeX() + " ; " + e.getCoordonneeY() + " )";
				Object[] newData = {e.getIdType(),coordonneEmp,e.getReservation(),e.getPaiement()};
				tabStands.addRow(newData);
				//ajoute le bouton supprimer
				tableStands.getColumn("").setCellRenderer(new MyRendererAndEditor(tableStands,"Stand"));
				tableStands.getColumn("").setCellEditor(new MyRendererAndEditor(tableStands,"Stand"));
			}
		}

		if(!system.getListeAutre().isEmpty()){
			Autre emplacementAutre;
			JButton btnAutreColor;
			for(Emplacement e : system.getListeAutre()){
				emplacementAutre = (Autre) e;
				indBase = emplacementAutre.getCoordonneeX()*dimX + emplacementAutre.getCoordonneeY();
				btnCourant = listBut.get(indBase);
				for(int i = 0;i<emplacementAutre.getLargeur();i++){
					for(int j = indBase;j<indBase+emplacementAutre.getLongueur();j++){
						btnAutreColor = listBut.get(i * getSystem().getDimX() + j);
						btnAutreColor.setBackground(Color.BLACK);
						btnAutreColor.setEnabled(false);
						btnAutreColor.setText(Integer.toString(emplacementAutre.getIdType()));
					}
				}
				listButAutres.add(btnCourant);
				String coordonneEmp = "( "+ e.getCoordonneeX() + " ; " + e.getCoordonneeY() + " )";
				Object[] newData = {e.getIdType(),e.getType(),coordonneEmp,e.getLargeur(),e.getLongueur()};
				tabAutres.addRow(newData);
				tableAutres.getColumn("Supprimer").setCellRenderer(new MyRendererAndEditor(tableAutres,"Autre"));
				tableAutres.getColumn("Supprimer").setCellEditor(new MyRendererAndEditor(tableAutres,"Autre"));
			}
		}

		if(!system.getListeReservation().isEmpty()){
			for(Reservation resa : system.getListeReservation()){
				Emplacement empReserve = resa.getEmplacement();
				String coordTemp = "( "+empReserve.getCoordonneeX()+ " ; "+ empReserve.getCoordonneeY() + " )";
            	Object[] newData = {resa.getIdReservation(), resa.getNom(), resa.getPrenom(), empReserve.getIdType(),coordTemp,resa.getMoyenPaiement()};
				tabReservation.addRow(newData);
            	tableReservation.getColumn("Accepter").setCellRenderer(new MyRendererAndEditorResaVal(tableReservation));
				tableReservation.getColumn("Accepter").setCellEditor(new MyRendererAndEditorResaVal(tableReservation));
            	tableReservation.getColumn("Refuser").setCellRenderer(new MyRendererAndEditorResaSuppr(tableReservation));
				tableReservation.getColumn("Refuser").setCellEditor(new MyRendererAndEditorResaSuppr(tableReservation));
			}
		}
	}

}

