import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class FenetreOrganisateur {
	private Profil profil;
	
    private JFrame frame;
    private JPanel global_panel;

	private JTabbedPane onglets;

	// ***** Panels correspondant aux onglets *****
	private JPanel panCreerBaB;
	private JPanel panMesBaBs;
	private JPanel panAccueil;

	// ***** Variables pour onglet Accueil *****
	private JButton btnDeconnecter;
	private JLabel jcomp3;
	private JLabel lab_logo;

	// ***** Variables pour onglet MesBaB *****
	private JScrollPane 		scrollMesBabs;
	public static DefaultTableModel	tabMesBabs;
	private JTable				tableMesBabs;
	private JDBC_BDD 	baseDeDonnees;
	//private Object[] 	nvBab;
	private String[][]	infosBabs;
	private String[] 	header = {"Id","NomBab","Charger","Publier"};

	// ***** Variables pour onglet CreerBaB *****
	PanelParamBaB paramBaB;

	public FenetreOrganisateur(Profil profil) {
		this.profil = profil;

		btnDeconnecter = new JButton("Deconnecter");

		frame = new JFrame("GereMonBaB - Organisateur");
		global_panel = new JPanel(new GridLayout(1, 1));
		onglets = new JTabbedPane();
		
		// ===========================================================================
		// ********** Onglet : Accueil **********
        panAccueil = new JPanel(null);
        onglets.add("Accueil", panAccueil);

        // Construction des composants
        btnDeconnecter = new JButton(new ActionBtn_Deconnexion("Deconnexion"));
        jcomp3 = new JLabel("GereMonBaB");
        lab_logo = new JLabel(new ImageIcon("LogoBaB.png"), JLabel.CENTER);

        // Ajout des composants
        panAccueil.add(btnDeconnecter);
        panAccueil.add(jcomp3);
        panAccueil.add(lab_logo);

        // Positionnement
        btnDeconnecter.setBounds(585, 10, 135, 20);
        jcomp3.setBounds(10, 10, 100, 25);
        lab_logo.setBounds(130, 50, 500, 500);


		// ===========================================================================
		// ********** Onglet : Mes BaB **********
		panMesBaBs 		= new JPanel();
		onglets.add("Mes BaBs", panMesBaBs);
		tabMesBabs 		= new DefaultTableModel(header, 0);
		tableMesBabs 	= new JTable(tabMesBabs);
		scrollMesBabs 	= new JScrollPane(tableMesBabs);

		// ********** Onglet : Creer son BaB **********
		paramBaB = new PanelParamBaB("nomBaB", "01/01/2021", 10, "rue des fleurs", 10, 10);
		onglets.add("Créer son BaB", paramBaB.getPannel());
		
		baseDeDonnees = new JDBC_BDD();
		baseDeDonnees.startJDBC();
		infosBabs = baseDeDonnees.chargerToutesInfos();
		for(int i = 0; i < baseDeDonnees.compterBab(); i++) {
			Object[] nvBab = {Integer.parseInt(infosBabs[i][0]), infosBabs[i][1]};
			tabMesBabs.addRow(nvBab);
			tableMesBabs.getColumn("Charger").setCellRenderer	(new MyRendererAndEditorChargerBab(tableMesBabs, "Charger"));
			tableMesBabs.getColumn("Charger").setCellEditor		(new MyRendererAndEditorChargerBab(tableMesBabs, "Charger"));
			tableMesBabs.getColumn("Publier").setCellRenderer	(new MyRendererAndEditorChargerBab(tableMesBabs, "Publier"));
			tableMesBabs.getColumn("Publier").setCellEditor		(new MyRendererAndEditorChargerBab(tableMesBabs, "Publier"));
			//TO DO parametré le bouton charger dans MyRendererAndEditorChargerBab.java
		}
		panMesBaBs.add(scrollMesBabs);

		// ********** Onglet : Contact **********
		onglets.add("Contact", PanelContact.get());
		// ===========================================================================

		global_panel.add(onglets);

		frame.getContentPane().add(global_panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //frame.setUndecorated(true);
	}

	// ***** Classes internes *****
    class ActionBtn_Deconnexion extends AbstractAction {
        public ActionBtn_Deconnexion(String nomBtn) {
            super(nomBtn);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
		    frame.dispose();
			new FenetreAccueil();
        }
    }
}
