import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class FenetreOrganisateur extends AbstractAction {
    private JFrame frame;
    private JPanel global_panel;

	private JTabbedPane onglets;
	
	// ***** Panels correspondant aux onglets *****
	private JPanel panCreerBaB;
	private JPanel panMesBaBs;

	// ***** Variables pour onglet CreerBaB *****
	private JButton butCreerBaB; // Permet de confirmer la création

	// ***** Variables pour onglet MesBaB *****
	private JScrollPane 		scrollMesBabs;
	public static DefaultTableModel	tabMesBabs;
	private JTable				tableMesBabs;
	private JDBC_BDD 	baseDeDonnees;
	//private Object[] 	nvBab;
	private String[][]	infosBabs;
	private String[] 	header = {"Id","NomBab","Charger","Publier"};

	public FenetreOrganisateur() {
		frame = new JFrame("GereMonBaB - Organisateur");
		global_panel = new JPanel(new GridLayout(1, 1));
		onglets = new JTabbedPane();

		// ===========================================================================
		// ********** Onglet : Creer son BaB **********
		panCreerBaB = new JPanel(); 
		onglets.add("Créer son BaB", panCreerBaB);
		butCreerBaB = new JButton("Créer un BaB");
		butCreerBaB.addActionListener(this);
		panCreerBaB.add(butCreerBaB);

		// ********** Onglet : Mes BaB **********
		panMesBaBs 		= new JPanel();
		onglets.add("Mes BaBs", panMesBaBs);
		tabMesBabs 		= new DefaultTableModel(header, 0);
		tableMesBabs 	= new JTable(tabMesBabs);
		scrollMesBabs 	= new JScrollPane(tableMesBabs);
		
		
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
		frame.setSize(1000, 700);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == butCreerBaB) {
			FenetreParamBaB paramBaB = new FenetreParamBaB("nomBaB", "01/01/2021", 10, "rue des ...", 10, 10);
			paramBaB.saisir(); // Permet de saisir les parametres du BaB puis le lance...
		}
	}
}
