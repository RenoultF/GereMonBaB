import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;


public class FenetreUIExposant extends AbstractAction{

    /** Déclaration des variables du système **/
    private static BaB system;

	public static int dimX;
	private int dimY;
	
	private static LinkedList<Emplacement> listeStand = new LinkedList<Emplacement>();
	private static LinkedList<Emplacement> listeAutre = new LinkedList<Emplacement>();
	
	/** Déclaration des variables de la fenêtre **/
	static String type;
	
	public static LinkedList<JButton> listBut = new LinkedList<>();
	public static LinkedList<JButton> listButStands = new LinkedList<>();
	public static LinkedList<JButton> listButAutres = new LinkedList<>();

	public static LinkedList<Reservation> reservationsTmp = new LinkedList<Reservation>();
	private static JFrame frame;


	private JTabbedPane ongletBab;
	
	private JPanel panFenetre;
	private JPanel panGrille;

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

	private JButton btnVal;

	/*variable des tableaux*/
	private String[] nomColStands = {"Numero","Coordonnees","status","Paiement"};
	private String[] nomColAutres = {"Numero","Nom_deco","Coordonnees","Largeur","Longueur"};

	private String[] nomColReservation = {"ID","Nom","Prenom","ID Stand","Coordonnees","Paiement","Supprimer"};
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

    public FenetreUIExposant(BaB system){
        /** Variables du système **/
		this.system = system;
		system.setUtilisateur("Exposant");
		dimX = system.getDimX();
		dimY = system.getDimY();
		
		listeStand = system.getListeStand();
		listeAutre = system.getListeAutre();

		sem = new Semaphore(1);
		
		/** Variables de la fenêtre **/
		frame = new JFrame("GereMonBaB - Editeur");
		panFenetre = new JPanel(new GridLayout(2,2));
		panGrille = new JPanel(new GridLayout(dimX, dimY));

		ongletBab = new JTabbedPane();
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

		btnVal = new JButton("Valider Réservation");
		btnVal.addActionListener(this);

		
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
				JButton but = new JButton(new ActionBtn(ind, i, j,system));
				but.setBackground(Color.WHITE);
				but.setText("");
                but.setEnabled(false);
				ind += 1;
				listBut.add(but);
				panGrille.add(but);
			}
		

		
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

		
		panListeReservation.add(scrollReservation);

        panListeReservation.add(btnVal);
		
		// ***** Fenêtre *****
		panFenetre.add(panGrille);
        ongletBab.add("Liste Reservation",panListeReservation);
		ongletBab.add("Infomation BaB",panInfoBaB);
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
		if(e.getSource() == btnVal){
			for(Reservation resa : reservationsTmp){
                tabReservation.removeRow(0);
                system.ajouterReservation(resa);
            }
            //TO-DO tristan Mettre a jour la liste des reservation dans le system
            reservationsTmp.clear();
		}
	}

    public static BaB getSystem() {
		return system;
	}

	public static JFrame getJFrame(){
		return frame;
	}

    public static void ajouterReservationExpo(Reservation resa){
		int id_reservation;
		if(reservationsTmp.size()==0){
            id_reservation = 0;
        }
        else{
            id_reservation = reservationsTmp.getLast().getIdReservation()+1;
        }
		resa.setIdReservation(id_reservation);
		reservationsTmp.add(resa);
	}


    public void actualiseFenetre(){
		int indBase;
		JButton btnCourant;
		if(!system.getListeStand().isEmpty()){
			for(Emplacement e : system.getListeStand()){
				indBase = e.getCoordonneeX()*dimX + e.getCoordonneeY();
				btnCourant = listBut.get(indBase);
                btnCourant.setEnabled(true);
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
			}
		}

		if(!system.getListeAutre().isEmpty()){
			JButton btnAutreColor;
			for(Emplacement e : system.getListeAutre()){
				indBase = e.getCoordonneeX()*dimX + e.getCoordonneeY();
				btnCourant = listBut.get(indBase);
				for(int i = 0;i<e.getLargeur();i++){
					for(int j = indBase;j<indBase+e.getLongueur();j++){
						btnAutreColor = listBut.get(i * getSystem().getDimX() + j);
						btnAutreColor.setBackground(Color.BLACK);
						btnAutreColor.setEnabled(false);
						btnAutreColor.setText(Integer.toString(e.getIdType()));
					}
				}
				listButAutres.add(btnCourant);
				String coordonneEmp = "( "+ e.getCoordonneeX() + " ; " + e.getCoordonneeY() + " )";
				Object[] newData = {e.getIdType(),e.getType(),coordonneEmp,e.getLargeur(),e.getLongueur()};
				tabAutres.addRow(newData);
			}
		}
	}

    public static void actualiserTabSupprResa(int index){
		int i;
		for(i = index; i<reservationsTmp.size();i++){
			reservationsTmp.get(i).setIdReservation(reservationsTmp.get(i).getIdReservation()-1);	
			//on change la valeur dans le tableau
			tableReservation.setValueAt(reservationsTmp.get(i).getIdReservation(), i, 0);
		}
	}
}