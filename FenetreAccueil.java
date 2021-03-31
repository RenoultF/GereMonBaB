import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FenetreAccueil {
    private JFrame frame;
    private JPanel global_panel;

	private JTabbedPane onglets;

	private JPanel panAccueil;

    // ***** Variables pour onglet Accueil *****
    private JButton butS_in; // Sign in
    private JButton butS_up; // Sign up
    private JLabel jcomp3;
    private JLabel lab_logo;

    public FenetreAccueil() {
        frame = new JFrame("Gere mon BaB - Accueil");
        global_panel = new JPanel(new GridLayout(1, 1));
		onglets = new JTabbedPane();

		// ===========================================================================
		// ********** Onglet : Accueil **********
        panAccueil = new JPanel(null);
        onglets.add("Accueil", panAccueil);

        // Construction des composants
        butS_in = new JButton(new ActionBtnS_In("Se connecter"));
        butS_up = new JButton(new ActionBtnS_Up("S'inscrire"));
        jcomp3 = new JLabel("GereMonBaB");
        lab_logo = new JLabel(new ImageIcon("LogoBaB.png"), JLabel.CENTER);

        // Ajout des composants
        panAccueil.add(butS_in);
        panAccueil.add(butS_up);
        panAccueil.add(jcomp3);
        panAccueil.add(lab_logo);

        // Positionnement
        butS_in.setBounds(585, 10, 135, 20);
        butS_up.setBounds(585, 35, 135, 20);
        jcomp3.setBounds(10, 10, 100, 25);
        lab_logo.setBounds(130, 50, 500, 500);

        // ********** Onglet : Contact **********
		onglets.add("Contact", PanelContact.get());
        // ===========================================================================

        global_panel.add(onglets);

		frame.getContentPane().add(global_panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(780, 600);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        frame.setResizable(false);
    }

    // ***** Classes internes *****
    class ActionBtnS_In extends AbstractAction {
        public ActionBtnS_In(String nomBtn) {
            super(nomBtn);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
		    new FenetreConnexion();
        }
    }

    class ActionBtnS_Up extends AbstractAction {
        public ActionBtnS_Up(String nomBtn) {
            super(nomBtn);
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            new FenetreInscription();
        }
    }
}

