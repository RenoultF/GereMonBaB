import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FenetreOrganisateur extends AbstractAction {
    private JFrame frame;
    private JPanel global_panel;

	private JTabbedPane onglets;
	
	// ***** Panels correspondant aux onglets *****
	private JPanel panCreerBaB;
	private JPanel panMesBaB;

	// ***** Variables pour onglet CreerBaB *****
	private JButton butCreerBaB; // Permet de confirmer la création

	// ***** Variables pour onglet MesBaB *****
	// todo

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
		panMesBaB = new JPanel();
		onglets.add("Mes BaB", panMesBaB);

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