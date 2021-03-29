import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PanelParamBaB {
    static JPanel pan;

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

    public PanelParamBaB(String nomBaB, String dateBaB, int prixStand, String adresse, int dimX, int dimY) {
        this.pan = new JPanel(null); // Absolute Positioning

        // ***** Valeurs par défaut *****
        this.nomBaB = nomBaB;
        this.dateBaB = dateBaB;
        this.prixStand = prixStand;
        this.adresse = adresse;
        this.dimX = dimX;
        this.dimY = dimY;

        sem = new Semaphore(1);
    }

    public JPanel getPannel() {
        sem.P(); // Prise du semaphore

        // ***** Construction des composants *****
        labTitre = new JLabel("Parametre du BaB :");

        labNomBaB = new JLabel("Nom du BaB :");
        labDateBaB = new JLabel("Date du BaB (JJ/MM/AAAA) :");
        labAdresse = new JLabel("Adresse du BaB :");
        labDimX = new JLabel("Dimension X du BaB :");
        labDimY = new JLabel("Dimension Y du BaB :");
        labPrixStand = new JLabel("Prix d'un Stand :");

        txtNomBaB = new JTextField(15);
        txtNomBaB.setText(this.nomBaB);
        txtDateBaB = new JTextField(10);
        txtDateBaB.setText(this.dateBaB);
        txtAdresse = new JTextField(30);
        txtAdresse.setText(this.adresse);
        txtDimX = new JTextField(5);
        txtDimX.setText(Integer.toString(this.dimX));
        txtDimY = new JTextField(5);
        txtDimY.setText(Integer.toString(this.dimY));
        txtPrixStand = new JTextField(5);
        txtPrixStand.setText(Integer.toString(this.prixStand));

        butOk = new JButton(new ActionBtnOk("Valider"));


        // ***** Ajout des composants *****
        pan.add(labTitre);
        pan.add(labNomBaB);
        pan.add(txtNomBaB);
        pan.add(labDateBaB);
        pan.add(txtDateBaB);
        pan.add(labAdresse);
        pan.add(txtAdresse);
        pan.add(labDimX);
        pan.add(txtDimX);
        pan.add(labDimY);
        pan.add(txtDimY);
        pan.add(labPrixStand);
        pan.add(txtPrixStand);
        pan.add(butOk);


        // ***** Positionnement *****
        labTitre.setBounds(5, 5, 200, 25);

        labNomBaB.setBounds(30, 85, 200, 25);
        txtNomBaB.setBounds(210, 85, 200, 25);

        labDateBaB.setBounds(30, 135, 200, 25);
        txtDateBaB.setBounds(210, 135, 200, 25);

        labAdresse.setBounds(30, 185, 200, 25);
        txtAdresse.setBounds(210, 185, 200, 25);

        labDimX.setBounds(30, 235, 200, 25);
        txtDimX.setBounds(210, 235, 100, 25);

        labDimY.setBounds(30, 285, 200, 25);
        txtDimY.setBounds(210, 285, 100, 25);

        labPrixStand.setBounds(30, 335, 200, 25);
        txtPrixStand.setBounds(210, 335, 100, 25);

        butOk.setBounds(350, 500, 300, 80);

        return pan;
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
        //this.dispose();
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
