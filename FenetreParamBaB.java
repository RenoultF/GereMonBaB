import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class FenetreParamBaB extends JFrame {
    // Dimension d'un stand en metres
    private String nomBaB;
    private String dateBaB;
    private int prixStand;
    private String adresse;
    private int dimX;
    private int dimY;

    // Composants graphiques
    private JLabel labTitre;

    private JLabel labNomBaB;
    private JLabel labDateBaB;
    private JLabel labPrixStand;
    private JLabel labAdresse;
    private JLabel labDimX;
    private JLabel labDimY;

    private JTextField txtNomBaB;
    private JTextField txtDateBaB;
    private JTextField txtPrixStand;
    private JTextField txtAdresse;
    private JTextField txtDimX;
    private JTextField txtDimY;

    private JButton butOk;

    private Semaphore sem; // Mutex

    public FenetreParamBaB(String nomBaB, String dateBaB, int prixStand, String adresse, int dimX, int dimY) {

        // ***** Valeurs par défaut *****
        this.nomBaB = nomBaB;
        this.dateBaB = dateBaB;
        this.prixStand = prixStand;
        this.adresse = adresse;
        this.dimX = dimX;
        this.dimY = dimY;

        sem = new Semaphore(1);
    }

    public void saisir() {
        sem.P(); // Prise du semaphore
        System.out.println("Semaphore pris");

        labTitre = new JLabel("Parametre du BaB :");

        labNomBaB = new JLabel("Nom du BaB :");
        labDateBaB = new JLabel("Date du BaB (JJ/MM/AAAA):");
        labAdresse = new JLabel("Adresse du BaB :");
        labDimX = new JLabel("Dimension X du BaB :");
        labDimY = new JLabel("Dimension Y du BaB :");
        labPrixStand = new JLabel("Prix d'un Stand :");

        txtNomBaB = new JTextField(15);
        txtDateBaB = new JTextField(10);
        txtAdresse = new JTextField(30);
        txtDimX = new JTextField(5);
        txtDimY = new JTextField(5);
        txtPrixStand = new JTextField(5);

        butOk = new JButton(new ActionBtnOk("OK"));

        // ***** Absolute Positioning *****
        labTitre.setBounds(5, 5, 160, 25);

        labNomBaB.setBounds(30, 35, 120, 25);
        txtNomBaB.setBounds(155, 35, 100, 25);

        labDateBaB.setBounds(30, 65, 120, 25);
        txtDateBaB.setBounds(155, 65, 100, 25);

        labAdresse.setBounds(30, 95, 120, 25);
        txtAdresse.setBounds(155, 95, 100, 25);

        labDimX.setBounds(30, 125, 120, 25);
        txtDimX.setBounds(155, 125, 100, 25);

        labDimY.setBounds(30, 155, 120, 25);
        txtDimY.setBounds(155, 155, 100, 25);

        labPrixStand.setBounds(30, 185, 120, 25);
        txtPrixStand.setBounds(155, 185, 100, 25);


        butOk.setBounds(280, 105, 65, 55);

        // ***** Valeurs par défaut *****
        txtNomBaB.setText(this.nomBaB);
        txtDateBaB.setText(this.dateBaB);
        txtAdresse.setText(this.adresse);
        txtDimX.setText(Integer.toString(this.dimX));
        txtDimY.setText(Integer.toString(this.dimY));
        txtPrixStand.setText(Integer.toString(this.prixStand));

        add(labTitre);

        add(labNomBaB);
        add(txtNomBaB);

        add(labDateBaB);
        add(txtDateBaB);

        add(labAdresse);
        add(txtAdresse);

        add(labDimX);
        add(txtDimX);

        add(labDimY);
        add(txtDimY);

        add(labPrixStand);
        add(txtPrixStand);

        add(butOk);

        // ***** Fenêtre *****
        this.setLayout(null);
        this.setTitle("Parametre BaB");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // ********** Getters **********
    // Synchronisation (attendre fin de la saisie) par mutex

    	/**
	 ** méthode permettant de retourner le nom du BaB
	 ** @return le nom du bab
	 **/
	public String getNomBaB() {
		return this.nomBaB;
	}

	/**
	 ** méthode permettant de retourner la date du BaB
	 ** @return la date du BaB
	 **/
	public String getDateBaB() {
		return this.dateBaB;
	}

	/**
	 ** méthode permettant de retourner le prix d'un stand
	 ** @return le prix du stand
	 **/
	public int getPrixStand() {
		return this.prixStand;
	}

    /**
	 ** méthode permettant de retourner la dimension X du BaB
	 ** @return la dimension X
	 **/
	public int getDimensionX() {
		return this.dimX;
	}

    /**
	 ** méthode permettant de retourner la dimension X du BaB
	 ** @return la dimension Y
	 **/
	public int getDimensionY() {
		return this.dimY;
	}
	
	/**
	 ** méthode permettant de retourner l'adresse du BaB
	 ** @return l'adresse du BaB
	 **/
	public String getAdresseBaB() {
		return this.adresse;
	}

    public void fermerFenetre() {
        sem.V(); // Relachement du semaphore
        this.dispose();
        BaB nvBaB = new BaB(this.getNomBaB(), this.getDateBaB(), this.getPrixStand(), this.getAdresseBaB(), this.getDimensionX(), this.getDimensionY());
;	    new FenetreUI(nvBaB);
    }

    // Classe interne permettant de gérer le bouton OK
    class ActionBtnOk extends AbstractAction {
        public ActionBtnOk(String nomBtn) {
            super(nomBtn);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            nomBaB = txtNomBaB.getText();
            dateBaB = txtDateBaB.getText();
            adresse = txtAdresse.getText();
            dimX = Integer.valueOf(txtDimX.getText());
            dimY = Integer.valueOf(txtDimY.getText());
            prixStand = Integer.valueOf(txtPrixStand.getText());
            fermerFenetre();
        }
    }
}
