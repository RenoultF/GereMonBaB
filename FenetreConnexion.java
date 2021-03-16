import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FenetreConnexion extends JFrame {
    // ***** Contenu de la fentre *****
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JTextField jcomp3;
    private JPasswordField jcomp4;
    private JButton jcomp5;

    public FenetreConnexion() {
        // ***** Construction des composants *****
        jcomp1 = new JLabel("Identifiant");
        jcomp2 = new JLabel("Mot de passe");
        jcomp3 = new JTextField(5);
        jcomp4 = new JPasswordField(5);
        jcomp5 = new JButton(new ActionBtnOk("Valider"));

        // ***** Ajout des composants *****
        add(jcomp1);
        add(jcomp2);
        add(jcomp3);
        add(jcomp4);
        add(jcomp5);

        // ***** Positionnement *****
        jcomp1.setBounds(5, 30, 120, 25);
        jcomp2.setBounds(5, 59, 120, 25);
        jcomp3.setBounds(130, 30, 204, 25);
        jcomp4.setBounds(130, 60, 100, 25);
        jcomp5.setBounds(235, 60, 100, 25);

        // ***** Fenêtre *****
        this.setLayout(null);
        this.setTitle("Gere mon BaB - Connexion");
        this.setSize(370, 140);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    // Classe interne permettant de gérer le bouton OK
    class ActionBtnOk extends AbstractAction {
        public ActionBtnOk(String nomBtn) {
            super(nomBtn);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // ***** Ouverture de l'UI correspondante *****
            // !! TEMPORAIRE !!
		    new FenetreOrganisateur();
		    // new FenetreExposant();
        }
    }
}
