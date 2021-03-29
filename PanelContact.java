import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PanelContact {
    static JPanel pan;

    public static JPanel get() {
        pan = new JPanel(null); // Absolute Positioning

        // ***** Construction des composants *****
        // --- Nom ---
        JLabel lab_Nom_Assoc = new JLabel("Assoc'2000");
        
        // --- Descrition ---
        JTextArea txt_Descr = new JTextArea(5, 5);
        txt_Descr.setEnabled(false);
        txt_Descr.setText("Cette association à pour \nbut d'aider les gens");
        
        // --- Contact ---
        JLabel lab_tel = new JLabel("02 43 43 43 43");
        JLabel lab_mel = new JLabel("contact@mail.com");
        
        // --- Adresse ---
        JLabel lab_Rue = new JLabel("1 rue Labelle");
        JLabel lab_Ville = new JLabel("12 345 Paris");
        JLabel lab_Pays = new JLabel("France");
        
        // --- Responsable ---
        JLabel lab_Nom_Resp = new JLabel("NOM");
        JLabel lab_Prenom_Resp = new JLabel("Prenom");

        // --- Composants à ne pas changer ---
        JLabel jcomp1 = new JLabel("Description");
        JLabel jcomp2 = new JLabel("Coordonnées");
        JLabel jcomp3 = new JLabel("Adresse de l'association");
        JLabel jcomp4 = new JLabel("Responsable");

        // ***** Ajout des composants *****
        pan.add(lab_Nom_Assoc);
        pan.add(jcomp1);
        pan.add(txt_Descr);
        pan.add(jcomp2);
        pan.add(lab_tel);
        pan.add(lab_mel);
        pan.add(jcomp3);
        pan.add(lab_Rue);
        pan.add(lab_Ville);
        pan.add(lab_Pays);
        pan.add(jcomp4);
        pan.add(lab_Nom_Resp);
        pan.add(lab_Prenom_Resp);

        // ***** Positionnement *****
        lab_Nom_Assoc.setBounds(20, 25, 255, 25);
        jcomp1.setBounds(20, 50, 100, 75);
        txt_Descr.setBounds(120, 50, 155, 75);
        jcomp2.setBounds(500, 25, 200, 25);
        lab_tel.setBounds(530, 50, 140, 25);
        lab_mel.setBounds(530, 75, 200, 25);
        jcomp3.setBounds(20, 250, 250, 25);
        lab_Rue.setBounds(80, 275, 100, 25);
        lab_Ville.setBounds(80, 300, 100, 25);
        lab_Pays.setBounds(80, 325, 100, 25);
        jcomp4.setBounds(500, 250, 100, 25);
        lab_Nom_Resp.setBounds(530, 275, 150, 25);
        lab_Prenom_Resp.setBounds(530, 300, 150, 25);

        return pan;
    }
}