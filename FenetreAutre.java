import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class FenetreAutre extends JFrame {
    // Dimension d'un stand en metres
    private int largeur;
    private int longueur;
    private String type;

    // Composants graphiques
    private JLabel labTitre;
    private JLabel labLarg;
    private JLabel labLong;
    private JLabel labType;
    private JTextField txtLarg;
    private JTextField txtLong;
    private JTextField txtType;
    private JButton butOk;

    private BaB system;

    private Semaphore sem; // Mutex

    public FenetreAutre(int largDefaut, int longDefaut, String typeDefault, BaB system) {
        // ***** Valeurs par défaut *****
        largeur = largDefaut;
        longueur = longDefaut;
        type = typeDefault;
        this.system = system;
        sem = new Semaphore(1);
    }

    public void saisir() {
        sem.P(); // Prise du semaphore

        labTitre = new JLabel("Dimension d'un stand");
        labLarg = new JLabel("Largeur :");
        labLong = new JLabel("Longeur :");
        labType = new JLabel("Type :");
        txtLarg = new JTextField(5);
        txtLong = new JTextField(5);
        txtType = new JTextField(15);
        butOk = new JButton(new ActionBtnOk("OK"));

        // ***** Absolute Positioning *****
        labTitre.setBounds(5, 5, 160, 25);
        labLarg.setBounds(30, 35, 120, 25);
        labLong.setBounds(30, 65, 120, 25);
        labType.setBounds(30, 95, 120, 25);
        txtLarg.setBounds(155, 35, 100, 25);
        txtLong.setBounds(155, 65, 100, 25);
        txtType.setBounds(155, 95, 100, 25);
        butOk.setBounds(265, 35, 65, 55);

        // ***** Valeurs par défaut *****
        txtLarg.setText(Integer.toString(largeur));
        txtLong.setText(Integer.toString(longueur));
        txtType.setText(type);
        
        add(labTitre);
        add(labLarg);
        add(labLong);
        add(labType);
        add(txtLarg);
        add(txtLong);
        add(txtType);
        add(butOk);

        // ***** Fenêtre *****
        this.setLayout(null);
        this.setSize(350, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    // ********** Getters **********
    // Synchronisation (attendre fin de la saisie) par mutex
    public int getLargeur() {
        sem.V(); sem.P();
        return largeur;
    }

    public int getLongueur() {
        sem.V(); sem.P();
        return longueur;
    }

    public String getTypeTString() {
        sem.V(); sem.P();
        return type;
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
            largeur = Integer.valueOf(txtLarg.getText());
            longueur = Integer.valueOf(txtLong.getText());
            type = txtType.getText();
			system.setTailleAutreLargeur(largeur);
			system.setTailleAutreLongueur(longueur);
            system.setType(type);
            fermerFenetre();
        }
    }
}
