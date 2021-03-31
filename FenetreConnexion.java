import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FenetreConnexion extends JFrame {
    // ***** Contenu de la fentre *****
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JTextField identifiant;
    private JPasswordField motDePasse;
    private JButton jcomp5;

    public FenetreConnexion() {
        // ***** Construction des composants *****
        jcomp1 = new JLabel("Identifiant");
        jcomp2 = new JLabel("Mot de passe");
        identifiant = new JTextField(5);
        motDePasse = new JPasswordField(5);
        jcomp5 = new JButton(new ActionBtnOk("Valider"));

        // ***** Ajout des composants *****
        add(jcomp1);
        add(jcomp2);
        add(identifiant);
        add(motDePasse);
        add(jcomp5);

        // ***** Positionnement *****
        jcomp1.setBounds(5, 30, 120, 25);
        jcomp2.setBounds(5, 59, 120, 25);
        identifiant.setBounds(130, 30, 204, 25);
        motDePasse.setBounds(130, 60, 100, 25);
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

    public void fermerFenetre() {
        this.dispose();
    }

    // Classe interne permettant de gérer le bouton OK
    class ActionBtnOk extends AbstractAction {
        public ActionBtnOk(String nomBtn) {
            super(nomBtn);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            baseDeDonnees = new JDBC_BDD();
            baseDeDonnees.startJDBC();
            if(baseDeDonnees.connexion(identifiant.getText(), motDePasse.getText())) {
                // ***** Ouverture de l'UI correspondante *****
                //if(baseDeDonnees.estOrganisateur(identifiant.getText(), motDePasse.getText()))
		            new FenetreOrganisateur();
                //else
		            new FenetreExposant();
                fermerFenetre();
            }
            else {
                System.out.println("Utilisateur inconnu");
                identifiant.setText("");
                motDePasse.setText("");
            }
        }
    }
}
