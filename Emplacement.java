import java.util.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Emplacement{

    private int numEmplacement;
    private int longueur;
    private int largeur;

    private String type;

    private String statut;

    private String proprietaire;

    private int coordX;
    private int coordY;

    private JButton btnSuppr;

    public Emplacement(int longueur, int largeur, String type, int x, int y, int numEmplacement){
        Icon icon = new ImageIcon("./img_btn_suppr.jpg");
        btnSuppr = new JButton(icon);
        this.longueur = longueur;
        this.largeur = largeur;
        this.statut = "LIBRE";
        this.proprietaire = "NaN";
        this.type = type;
        this.coordX = x;
        this.coordY = y;
        this.numEmplacement = numEmplacement;
    }

    public void attribuerProPrietaire(String prop){
        this.proprietaire = prop;
    }

    public void attribuerNumEmplacement(int num){
        this.numEmplacement = num;
    }

    public void statutLibre(){
        this.statut = "LIBRE";
    }

    public void statutEnCours(){
        this.statut = "EN COURS";
    }

    public void statutReserve(){
        this.statut = "RESERVE";
    }

    public boolean estReverse(){
        return (this.statut == "RESERVE");
    }

    public boolean estLibre(){
        return (this.statut == "LIBRE");
    }

    public boolean estEnCours(){
        return (this.statut == "EN COURS");
    }

    public void modifierEmplacement(int nb){
        this.numEmplacement += nb;
    }

    public boolean estStand(){
        return (this.type == "STAND");
    }

    public boolean estDecoration(){
        return (this.type != "STAND");
    }

    public int getNumEmplacement(){
        return this.numEmplacement;
    }

    public int getLongueur(){
        return this.longueur;
    }

    public int getLargeur(){
        return this.largeur;
    }

    public String getStatut(){
        return this.statut;
    }

    public String getProp(){
        return this.proprietaire;
    }

    public String getType(){
        return this.type;
    }

    public int getX(){
        return coordX;
    }

    public int getY(){
        return coordY;
    }

    public void afficheToi(){
        System.out.println("Je suis de type :"+this.type+" mon num est " + this.numEmplacement + " et mon statut est  " + this.statut);
    }

    public Emplacement getEmpl(){
        return this;
    }
}