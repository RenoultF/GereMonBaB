import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FenetreConnexion extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JTextField jcomp3;
    private JPasswordField jcomp4;
    private JButton jcomp5;

    public FenetreConnexion() {
        // ***** Construction des composants *****
        jcomp1 = new JLabel ("Identifiant");
        jcomp2 = new JLabel ("Mot de passe");
        jcomp3 = new JTextField (5);
        jcomp4 = new JPasswordField (5);
        jcomp5 = new JButton ("Valider");

        setPreferredSize (new Dimension (367, 116));
        setLayout (null);

        // ***** Ajout des composants *****
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);

        // ***** Positionnement *****
        jcomp1.setBounds (5, 30, 120, 25);
        jcomp2.setBounds (5, 59, 120, 25);
        jcomp3.setBounds (130, 30, 204, 25);
        jcomp4.setBounds (130, 60, 100, 25);
        jcomp5.setBounds (235, 60, 100, 25);
    }

    // ***** Temporaire : Permet de test la fenetre *****
    public static void main (String[] args) {
        JFrame frame = new JFrame ("GereMonBaB - Connexion");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new FenetreConnexion());
        frame.pack();
        frame.setResizable (false); 
        frame.setVisible (true);
    }
}
