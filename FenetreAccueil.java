import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FenetreAccueil extends JPanel {
    private JButton jcomp1;
    private JButton jcomp2;
    private JButton jcomp3;
    private JButton jcomp4;
    private JButton jcomp5;
    private JButton jcomp6;
    private JLabel jcomp7;
    private JLabel jcomp8;
    private JLabel jcomp9;

    public FenetreAccueil() {
        //construct components
        jcomp1 = new JButton ("Accueil");
        jcomp2 = new JButton ("Créer son BaB");
        jcomp3 = new JButton ("Mes BaB");
        jcomp4 = new JButton ("Liste");
        jcomp5 = new JButton ("Contact");
        jcomp6 = new JButton ("Connecté en tant qu'organisateur");
        jcomp7 = new JLabel ("GereMonBaB");
        jcomp8 = new JLabel ("Footer");
        jcomp9 = new JLabel ("newLabel");

        //adjust size and set layout
        setPreferredSize (new Dimension (667, 392));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);

        //set component bounds
        jcomp1.setBounds (0, 50, 100, 25);
        jcomp2.setBounds (210, 50, 140, 25);
        jcomp3.setBounds (355, 50, 100, 25);
        jcomp4.setBounds (460, 50, 100, 25);
        jcomp5.setBounds (565, 50, 100, 25);
        jcomp6.setBounds (385, 0, 280, 25);
        jcomp7.setBounds (0, 0, 100, 25);
        jcomp8.setBounds (0, 365, 665, 25);
        jcomp9.setBounds (0, 75, 665, 285);
    }

    // ***** Temporaire : Permet de test la fenetre *****
    public static void main (String[] args) {
        JFrame frame = new JFrame ("GereMonBaB - Accueil");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new FenetreAccueil());
        frame.pack();
        frame.setResizable (false); 
        frame.setVisible (true);
    }
}
