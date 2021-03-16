import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FenetreExposant extends AbstractAction {
    private JFrame frame;
    private JPanel global_panel;

	private JTabbedPane onglets;
	
	// ***** Panels correspondant aux onglets *****
	private JPanel panAccueil;
	private JPanel panListe;
	private JPanel panContact;

	// ***** Variables pour onglet Accueil *****
    // todo

	// ***** Variables pour onglet Liste *****
	// todo

	// ***** Variables pour onglet Contact *****
	private JLabel labContact;

	public FenetreExposant() {
		frame = new JFrame("GereMonBaB - Organisateur");
        global_panel = new JPanel(new GridLayout(1, 1));
		onglets = new JTabbedPane();

		// ===========================================================================
		// ********** Onglet : Accueil **********
		panAccueil = new JPanel(); 
		onglets.add("Accueil", panAccueil);

		// ********** Onglet : Liste **********
		panListe = new JPanel();
		onglets.add("Liste des BaB", panListe);

		// ********** Onglet : Contact **********
		panContact = new JPanel();
		onglets.add("Contact", panContact);
		labContact = new JLabel("Coordonnées du support technique : support@bab.com");
		panContact.add(labContact);
		// ===========================================================================

		global_panel.add(onglets);

		frame.getContentPane().add(global_panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// todo
	}
}