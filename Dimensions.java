import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Dimensions extends JFrame {
    // Dimension d'un stand en metres
    private Double largeur;
    private Double longueur;

    // Composants graphiques
    private JLabel labTitre;
    private JLabel labLarg;
    private JLabel labLong;
    private JTextField txtLarg;
    private JTextField txtLong;
    private JButton butOk;

    private Semaphore sem;

    public Dimensions(Double largDefaut, Double longDefaut) {
        // ***** Valeurs par défaut *****
        largeur = largDefaut;
        longueur = longDefaut;

        sem = new Semaphore(1);
    }

    public void saisir() {
        sem.P(); // Prise du semaphore

        labTitre = new JLabel("Dimension d'un stand");
        labLarg = new JLabel("Largeur (en m) :");
        labLong = new JLabel("Longeur (en m) :");
        txtLarg = new JTextField(5);
        txtLong = new JTextField(5);
        butOk = new JButton(new ActionBtnOk("OK"));

        // ***** Absolute Positioning *****
        labTitre.setBounds(5, 5, 160, 25);
        labLarg.setBounds(30, 35, 120, 25);
        labLong.setBounds(30, 65, 120, 25);
        txtLarg.setBounds(155, 35, 100, 25);
        txtLong.setBounds(155, 65, 100, 25);
        butOk.setBounds(265, 35, 65, 55);

        // ***** Valeurs par défaut *****
        txtLarg.setText(Double.toString(largeur));
        txtLong.setText(Double.toString(longueur));

        add(labTitre);
        add(labLarg);
        add(labLong);
        add(txtLarg);
        add(txtLong);
        add(butOk);

        // ***** Fenêtre *****
        this.setLayout(null);
        this.setTitle("Dimensions");
        this.setSize(350, 140);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // ********** Getters **********
    // ATTENTION : Penser à la synchronisation (attendre fin de la saisie)
    public Double getLargeur() {
        sem.P(); sem.V();
        return largeur;
    }

    public Double getLongueur() {
        sem.P(); sem.V();
        return longueur;
    }

    public void fermerFenetre() {
        sem.V(); // Relachement du semaphore
        this.dispose();
    }

    // Classe interne permettant de gérer le bouton OK
    class ActionBtnOk extends AbstractAction {
        public ActionBtnOk(String nomBtn) {
            super(nomBtn);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            largeur = Double.valueOf(txtLarg.getText());
            longueur = Double.valueOf(txtLong.getText());
            fermerFenetre();
        }
    }
}
