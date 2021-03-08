import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FenetreAccueilOrganisateur extends JPanel {
    private JButton jcomp1;
    private JButton jcomp2;
    private JButton jcomp3;
    private JButton jcomp4;
    private JButton jcomp5;
    private JLabel jcomp6;
    private JLabel jcomp7;
    private JLabel jcomp8;
    private JLabel jcomp9;

    public FenetreAccueilOrganisateur() {
        // ***** Construction des composants *****
        jcomp1 = new JButton ("Accueil");
        jcomp2 = new JButton ("Créer son BaB");
        jcomp3 = new JButton ("Mes BaB");
        jcomp4 = new JButton ("Liste");
        jcomp5 = new JButton ("Contact");
        jcomp6 = new JLabel ("GereMonBaB");
        jcomp7 = new JLabel ("Footer");
        jcomp8 = new JLabel ("newLabel");
        jcomp9 = new JLabel ("Connecté en tant qu'organisateur");

        setPreferredSize (new Dimension (919, 569));
        setLayout (null);

        // ***** Ajout des composants *****
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);

        // ***** Positionnement *****
        jcomp1.setBounds (0, 55, 100, 25);
        jcomp2.setBounds (469, 55, 140, 25);
        jcomp3.setBounds (612, 55, 100, 25);
        jcomp4.setBounds (715, 55, 100, 25);
        jcomp5.setBounds (818, 55, 100, 25);
        jcomp6.setBounds (0, 0, 100, 25);
        jcomp7.setBounds (0, 525, 920, 40);
        jcomp8.setBounds (0, 84, 920, 440);
        jcomp9.setBounds (655, 0, 265, 25);
    }

    // ***** Temporaire : Permet de test la fenetre *****
    public static void main (String[] args) {
        JFrame frame = new JFrame ("GereMonBaB - Accueil");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new FenetreAccueilOrganisateur());
        frame.pack();
        frame.setResizable (false); 
        frame.setVisible (true);
    }
}