import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class FenetreExposant {
	private Profil profil;

    private JFrame frame;
    private JPanel global_panel;

	private JTabbedPane onglets;
	
	// ***** Panels correspondant aux onglets *****
	private JPanel panAccueil;
	private JPanel panListe;

	// ***** Variables pour onglet Accueil *****
	private JButton btnDeconnecter;
	private JLabel jcomp3;
	private JLabel lab_logo;

	// ***** Variables pour onglet Liste *****
	private JScrollPane 		scrollMesBabs;
	public static DefaultTableModel	tabMesBabs;
	private JTable				tableMesBabs;
	private JDBC_BDD 	baseDeDonnees;
	//private Object[] 	nvBab;
	private String[][]	infosBabs;

	private String[] header = {"Id","NomBab","Visiter"};

	public FenetreExposant(Profil profil) {
		this.profil = profil;

		frame = new JFrame("GereMonBaB - Exposant");
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

		// ********** Onglet : Liste **********
		panListe = new JPanel();
		onglets.add("Liste des BaB", panListe);
		tabMesBabs 		= new DefaultTableModel(header, 0);
		tableMesBabs 	= new JTable(tabMesBabs);
		scrollMesBabs 	= new JScrollPane(tableMesBabs);
		baseDeDonnees = new JDBC_BDD();
		baseDeDonnees.startJDBC();
		infosBabs = baseDeDonnees.chargerToutesInfos();
		for(int i = 0; i < baseDeDonnees.compterBab(); i++) {
			Object[] nvBab = {Integer.parseInt(infosBabs[i][0]), infosBabs[i][1]};
			tabMesBabs.addRow(nvBab);
			tableMesBabs.getColumn("Visiter").setCellRenderer	(new MyRendererAndEditorChargerBab(tableMesBabs, "Visiter"));
			tableMesBabs.getColumn("Visiter").setCellEditor		(new MyRendererAndEditorChargerBab(tableMesBabs, "Visiter"));
			
			//TO DO parametré le bouton charger dans MyRendererAndEditorChargerBab.java
		}
		panListe.add(scrollMesBabs);
		
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