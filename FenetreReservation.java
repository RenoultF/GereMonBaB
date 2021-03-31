import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class FenetreReservation extends JFrame {
    // Dimension d'un stand en metres
    private String prenom;
    private String nom;
    private String typePaiement;

    // Composants graphiques
    private JLabel labTitre;
    private JLabel labPrenom;
    private JLabel labNom;
    private JLabel labTypePaiement;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtTypePaiement;
    private JButton butOk;

    private Emplacement empReserve;

    private Reservation reservation;

    private BaB system;

    private Semaphore sem; // Mutex

    private JButton btnReserve;

    public FenetreReservation(String prenomBase, String nomBase, String typePaiementDefault, Emplacement empReserve,BaB system, JButton btnReserve) {
        // ***** Valeurs par défaut *****
        prenom = prenomBase;
        nom = nomBase;
        typePaiement = typePaiementDefault;
        this.empReserve = empReserve;
        this.system = system;
        this.btnReserve = btnReserve;
        sem = new Semaphore(1);
    }

    public void saisir() {
        sem.P(); // Prise du semaphore

        labTitre = new JLabel("Demande de Reservation");
        labPrenom = new JLabel("Prenom :");
        labNom = new JLabel("Nom :");
        labTypePaiement = new JLabel("Type Paiement :");
        txtNom = new JTextField(5);
        txtPrenom = new JTextField(5);
        txtTypePaiement = new JTextField(15);
        butOk = new JButton(new ActionBtnOk("OK"));

        // ***** Absolute Positioning *****
        labTitre.setBounds(5, 5, 160, 25);
        labPrenom.setBounds(30, 35, 120, 25);
        labNom.setBounds(30, 65, 120, 25);
        labTypePaiement.setBounds(30, 95, 120, 25);
        txtNom.setBounds(155, 35, 100, 25);
        txtPrenom.setBounds(155, 65, 100, 25);
        txtTypePaiement.setBounds(155, 95, 100, 25);
        butOk.setBounds(265, 35, 65, 55);

        // ***** Valeurs par défaut *****
        txtNom.setText(prenom);
        txtPrenom.setText(nom);
        txtTypePaiement.setText(typePaiement);

        add(labTitre);
        add(labPrenom);
        add(labNom);
        add(labTypePaiement);
        add(txtNom);
        add(txtPrenom);
        add(txtTypePaiement);
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
    public String getprenom() {
        return prenom;
    }

    public String getnom() {
        return nom;
    }

    public String getTypePaiementTString() {
        return typePaiement;
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
            prenom = String.valueOf(txtNom.getText());
            nom = String.valueOf(txtPrenom.getText());
            typePaiement = txtTypePaiement.getText();
            int idReservant = 0;
            empReserve.setReservation("semi_reserve");
            reservation = new Reservation(nom, prenom, idReservant, empReserve,typePaiement);
            //Maj du status dans le tableaux
            if(system.getUtilisateur().equals("Organisateur")){
                FenetreUI.tabStands.setValueAt(empReserve.getReservation(), empReserve.getIdType(), 2);
                system.ajouterReservation(reservation);
                btnReserve.setBackground(Color.ORANGE);
                String coordTemp = "( "+empReserve.getCoordonneeX()+ " ; "+ empReserve.getCoordonneeY() + " )";
                Object[] newData = {reservation.getIdReservation(), nom, prenom, empReserve.getIdType(),coordTemp,reservation.getMoyenPaiement()};
                FenetreUI.tabReservation.addRow(newData);
                FenetreUI.tableReservation.getColumn("Accepter").setCellRenderer(new MyRendererAndEditorResaVal(FenetreUI.tableReservation));
                FenetreUI.tableReservation.getColumn("Accepter").setCellEditor(new MyRendererAndEditorResaVal(FenetreUI.tableReservation));
                FenetreUI.tableReservation.getColumn("Refuser").setCellRenderer(new MyRendererAndEditorResaSuppr(FenetreUI.tableReservation));
                FenetreUI.tableReservation.getColumn("Refuser").setCellEditor(new MyRendererAndEditorResaSuppr(FenetreUI.tableReservation));
            }
            else if(system.getUtilisateur().equals("Exposant")){
                btnReserve.setBackground(Color.ORANGE);
                FenetreUIExposant.ajouterReservationExpo(reservation);
                String coordTemp = "( "+empReserve.getCoordonneeX()+ " ; "+ empReserve.getCoordonneeY() + " )";
                Object[] newData = {reservation.getIdReservation(), nom, prenom, empReserve.getIdType(),coordTemp,reservation.getMoyenPaiement()};
                FenetreUIExposant.tabReservation.addRow(newData);
                FenetreUIExposant.tableReservation.getColumn("Supprimer").setCellRenderer(new MyRendererAndEditorResaSupprExpo(FenetreUIExposant.tableReservation));
                FenetreUIExposant.tableReservation.getColumn("Supprimer").setCellEditor(new MyRendererAndEditorResaSupprExpo(FenetreUIExposant.tableReservation));
            }
            fermerFenetre();
        }
    }

    public Reservation getReservation(){
        return this.reservation;
    }
}
