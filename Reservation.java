public class Reservation {

    private Emplacement empReserve;

    private int id_reservation;

    private String nom;
    private String prenom;
    private int id_reservant;

    private String moyenPaiement;

    public Reservation(String nom, String prenom,int id_reservant, Emplacement empReserve, String moyenPaiement){
        this.nom = nom;
        this.prenom = prenom;
        this.empReserve = empReserve;
        this.idReservant = idReservant;
        this.moyenPaiement = moyenPaiement;
    }

    /*****************************Setter & getter ******************************/


    /**
	 ** méthode permettant de récupérer le nom qui a reservé l'emplacement
	 ** @return nom 
	 **/
    public String getNom(){
        return this.nom;
    }

    /**
	 ** méthode permettant de récupérer le moyen de paiement
	 ** @return moyenPaiement 
	 **/
    public String getMoyenPaiement(){
        return this.moyenPaiement;
    }

      /**
	 ** méthode permettant de récupérer le prenom qui a reservé l'emplacement
	 ** @return prenom 
	 **/
    public String getPrenom(){
        return this.prenom;
    }

     /**
	 ** méthode permettant de récupérer le empReserve qui a été réservé
	 ** @return empReserve 
	 **/
    public Emplacement getEmplacement(){
        return this.empReserve;
    }

      /**
	 ** méthode permettant de récupérer le id de la réservation
	 ** @return id_reservation 
	 **/
    public int getIdReservation(){
        return this.idReservant;
    }

      /**
	 ** méthode permettant de récupérer le id du reservant
	 ** @return id_reservant 
	 **/
    public int getIdReservant(){
        return this.idReservant;
    }

    /**
	 ** méthode permettant de modifier le nom de la reservation
	 ** @param nvNom le nouveau nom
	 **/
    public void setNom(String nvNom){
        this.nom = nvNom;
    }

     /**
	 ** méthode permettant de modifier le prenom de la reservation
	 ** @param nvPrenom le nouveau prenom
	 **/
    public void setPrenom(String nvPrenom){
        this.prenom = nvPrenom;
    }

     /**
	 ** méthode permettant de modifier l'emplacement de la reservation
	 ** @param nvEmplacement le nouveau emplacement
	 **/
    public void setEmplacement(Emplacement nvEmplacement){
        this.empReserve = nvEmplacement;
    }

    /**
	 ** méthode permettant de modifier l'id de la reservation
	 ** @param nvId le nouveau emplacement
	 **/
    public void setIdReservation(int nvId){
        this.id_reservation = nvId;
    }

    /**
	 ** méthode permettant de modifier l'id du reservant
	 ** @param nvIdReservant le nouveau emplacement
	 **/
    public void setIdReservant(int nvIdReservant){
        this.id_reservant = nvIdReservant;
    }

}
