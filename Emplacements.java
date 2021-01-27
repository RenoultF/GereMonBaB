import java.util.*;

public class Emplacements{
    LinkedList<Emplacement> stands ;
    LinkedList<Emplacement> decorations;
    private int nbStand;
    private int nbDeco;

    public Emplacements(){
        stands = new LinkedList<>();
        decorations = new LinkedList<> ();
        nbStand = 0;
        nbDeco = 0;
    }


    public Emplacement ajouterEmplacement(String type, int x, int y, int longueur, int largeur){
        Emplacement empCourant = null;
        if(type == "STAND"){
            this.nbStand++;
            empCourant = new Emplacement(longueur,largeur,type,x,y,this.nbStand);
            stands.add(empCourant);
        }
        else{
            this.nbDeco++;
            empCourant = new Emplacement(longueur,largeur,type,x,y,this.nbDeco);       
            decorations.add(empCourant);
        }
        empCourant.afficheToi();
        return empCourant;
    }

    public void supprimerEmplacement(Emplacement emp){
        if(emp.estStand()){
            if(stands.size()>0){
                for(int i = (stands.indexOf(emp));i<stands.size();i++){
                   stands.get(i).modifierEmplacement(-1);
                }
                this.nbStand--;
                stands.remove(emp);
            }
       }
        else{
            if(decorations.size()>0){
                for(int i = (decorations.indexOf(emp));i<decorations.size();i++){
                    decorations.get(i).modifierEmplacement(-1);
                }
                this.nbDeco--;
                decorations.remove(emp);
            }
        }
    }

    public void toutAfficher(){
        System.out.println("Pour les Stands :");
        for(int i = 0;i < stands.size();i++){
            stands.get(i).afficheToi();
        }

        System.out.println("Pour les Decorations :");
        for(int i = 0;i < decorations.size();i++){
            decorations.get(i).afficheToi();
        }
    }

    /* // test des listes
    public static void main(String[] args) {
        Emplacements tabEmplacement = new Emplacements();
        
        Emplacement emp1 = tabEmplacement.ajouterEmplacement("STAND", 1, 1, 3, 2);
        Emplacement emp2 = tabEmplacement.ajouterEmplacement("STAND", 1, 2, 3, 2);
        Emplacement emp3 = tabEmplacement.ajouterEmplacement("STAND", 1, 3, 3, 2);
        Emplacement emp4 = tabEmplacement.ajouterEmplacement("STAND", 1, 4, 3, 2);

        Emplacement emp5 = tabEmplacement.ajouterEmplacement("BOULANGERIE", 2, 1, 3, 2);
        Emplacement emp6 = tabEmplacement.ajouterEmplacement("BUVETTE", 2, 2, 3, 2);
        Emplacement emp7 = tabEmplacement.ajouterEmplacement("POMPIER", 2, 3, 3, 2);
        Emplacement emp8 = tabEmplacement.ajouterEmplacement("MAIRIE", 2, 4, 3, 2);
        
        tabEmplacement.toutAfficher();

        tabEmplacement.supprimerEmplacement(emp2);
        tabEmplacement.supprimerEmplacement(emp6);

        tabEmplacement.toutAfficher();
    }*/
}