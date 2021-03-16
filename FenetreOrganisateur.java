import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FenetreOrganisateur extends AbstractAction {
    private JFrame frame;
    private JPanel global_panel;

	private JTabbedPane onglets;
	
	// ***** Panels correspondant aux onglets *****
	private JPanel panAccueil;
	private JPanel panCreerBaB;
	private JPanel panMesBaB;
	private JPanel panListe;
	private JPanel panContact;

	// ***** Variables permettant l'édition d'un BaB *****
	private JButton butCreerBaB; // Permet de confirmer la création

	public FenetreOrganisateur() {
		frame = new JFrame("GereMonBaB - Organisateur");
        global_panel = new JPanel(new GridLayout(1, 1));
		onglets = new JTabbedPane();

		// ===========================================================================
		// ********** Onglet : Accueil **********
		panAccueil = new JPanel(); 
		onglets.add("Accueil", panAccueil);

		// ********** Onglet : Creer son BaB **********
		panCreerBaB = new JPanel(); 
		onglets.add("Créer son BaB", panCreerBaB);
		butCreerBaB = new JButton("Créer un BaB");
		butCreerBaB.addActionListener(this);
		panCreerBaB.add(butCreerBaB);

		// ********** Onglet : Mes BaB **********
		panMesBaB = new JPanel();
		onglets.add("Mes BaB", panMesBaB);

		// ********** Onglet : Liste **********
		panListe = new JPanel();
		onglets.add("Liste", panListe);

		// ********** Onglet : Contact **********
		panContact = new JPanel();
		onglets.add("Contact", panContact);
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