import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FenetreInscription extends JFrame {
    // ***** Contenu de la fentre *****
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JLabel jcomp7;
    private JButton jcomp6;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtCourriel;
    private JPasswordField txtMDP;

    private ButtonGroup radButtGrp = new ButtonGroup();

    private JRadioButton radButExposant;
    private JRadioButton radButOrganisateur;

    public FenetreInscription() {
        // ***** Construction des composants *****
        jcomp1 = new JLabel("Compte");
        jcomp2 = new JLabel("Nom");
        jcomp3 = new JLabel("Prénom");
        jcomp4 = new JLabel("Courriel");
        jcomp5 = new JLabel("Mot de passe");
        jcomp7 = new JLabel("Rôle");
        jcomp6 = new JButton(new ActionBtnOk("Valider"));
        txtNom = new JTextField(5);
        txtPrenom = new JTextField(5);
        txtCourriel = new JTextField(5);
        txtMDP = new JPasswordField(5);

        radButExposant = new JRadioButton("Exposant");
        radButOrganisateur = new JRadioButton("Organisateur");

        radButExposant.setSelected(true);
        radButtGrp.add(radButExposant);
        radButtGrp.add(radButOrganisateur);

        // ***** Ajout des composants *****
        add(jcomp1);
        add(jcomp2);
        add(jcomp3);
        add(jcomp4);
        add(jcomp5);
        add(jcomp6);
        add(jcomp7);
        add(txtNom);
        add(txtPrenom);
        add(txtCourriel);
        add(txtMDP);
        add(radButExposant);
        add(radButOrganisateur);

        // ***** Positionnement *****
        jcomp1.setBounds(205, 0, 100, 25);
        jcomp2.setBounds(45, 50, 100, 25);
        jcomp3.setBounds(45, 75, 100, 25);
        jcomp4.setBounds(45, 100, 100, 25);
        jcomp5.setBounds(45, 125, 100, 25);
        jcomp6.setBounds(205, 315, 100, 25);
        jcomp7.setBounds(45, 150, 100, 25);
        txtNom.setBounds(145, 50, 100, 25);
        txtPrenom.setBounds(145, 75, 100, 25);
        txtCourriel.setBounds(145, 100, 240, 25);
        txtMDP.setBounds(145, 125, 135, 25);
        radButExposant.setBounds(145, 150, 130, 25);
        radButOrganisateur.setBounds(280, 150, 150, 25);

        // ***** Fenêtre *****
        this.setLayout(null);
        this.setTitle("Gere mon BaB - Inscription");
        this.setSize(new Dimension(500, 380));
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
            // TODO...

            //if(radButExposant.isSelected())
            //else

            fermerFenetre();
            new FenetreConnexion();
        }
    }
}