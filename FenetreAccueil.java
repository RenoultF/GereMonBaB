import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FenetreAccueil extends JFrame {
    // ***** Contenu de la fentre *****
    private JButton butS_in; // Sign in
    private JButton butS_up; // Sign up
    private JLabel jcomp3;

    public FenetreAccueil() {
        // ***** Construction des composants *****
        butS_in = new JButton(new ActionBtnS_In("Se connecter"));
        butS_up = new JButton(new ActionBtnS_Up("S'inscrire"));
        jcomp3 = new JLabel("GereMonBaB");

        // ***** Ajout des composants *****
        add(butS_in);
        add(butS_up);
        add(jcomp3);

        // ***** Positionnement *****
        butS_in.setBounds(585, 10, 135, 20);
        butS_up.setBounds(585, 35, 135, 20);
        jcomp3.setBounds(10, 10, 100, 25);

        // ***** Fenêtre *****
        this.setLayout(null);
        this.setTitle("Gere mon BaB - Accueil");
        this.setSize(new Dimension(730, 490));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void fermerFenetre() {
        this.dispose();
    }

    // Classe interne permettant
    class ActionBtnS_In extends AbstractAction {
        public ActionBtnS_In(String nomBtn) {
            super(nomBtn);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fermerFenetre();
		    new FenetreConnexion();
        }
    }

        // Classe interne permettant
        class ActionBtnS_Up extends AbstractAction {
            public ActionBtnS_Up(String nomBtn) {
                super(nomBtn);
            }
    
            @Override
            public void actionPerformed(ActionEvent e) {
                fermerFenetre();
                new FenetreInscription();
            }
        }
}

