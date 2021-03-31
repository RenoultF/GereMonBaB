public class Profil {
    private String prenom;
    private String nom;

    private int id_profil;

    private String type;

    private String mail;

    private String password;

    public Profil(int id_profil,String prenom,String nom,String type,String mail,String password){
        this.id_profil = id_profil;
        this.prenom = prenom;
        this.nom = nom;
        this.type = type;
        this.mail = mail;
        this.password = password;
    }



    /**********  Setter & Getter ***************/


    /**
	 ** méthode permettant de récupérer l'id du profil
	 ** @return l'id du profil
	 **/
    public int getIdProfil(){
        return this.id_profil;
    }

    /**
	 ** méthode permettant de récupérer le nom du profil
	 ** @return le nom du profil
	 **/
    public String getNom(){
        return this.nom;
    }

    /**
	 ** méthode permettant de récupérer le prenom du profil
	 ** @return le prenom du profil
	 **/
    public String getPrenom(){
        return this.prenom;
    }

    /**
	 ** méthode permettant de récupérer le mail du profil
	 ** @return le mail du profil
	 **/
    public String getMail(){
        return this.mail;
    }

    /**
	 ** méthode permettant de récupérer le type du profil
	 ** @return le type du profil
	 **/
    public String getType(){
        return this.type;
    }

    /**
	 ** méthode permettant de récupérer le password du profil
	 ** @return le password du profil
	 **/
    public String getPassword(){
        return this.password;
    }


    /**
	 ** méthode permettant de modifier l'id du profil
	 ** @param nvID le nouvel id
	 **/
    public void setIdProfil(int nvID){
        this.id_profil = nvID;
    }

    /**
	 ** méthode permettant de modifier le nom du profil
	 ** @param nvNom le nouveau nom
	 **/
    public void setNom(String nvNom){
        this.nom = nvNom;
    }

    /**
	 ** méthode permettant de modifier le prenom du profil
	 ** @param nvPrenom le nouveau prenom
	 **/
    public void setPrenom(String nvPrenom){
        this.prenom = nvPrenom;
    }

    /**
	 ** méthode permettant de modifier le mail du profil
	 ** @param nvMail le nouveau mail
	 **/
    public void setMail(String nvMail){
        this.mail = nvMail;
    }

    /**
	 ** méthode permettant de modifier le Type du profil
	 ** @param nvType le nouveau Type
	 **/
    public void setType(String nvType){
        this.type = nvType;
    }

    /**
	 ** méthode permettant de modifier le Password du profil
	 ** @param nvPassword le nouveau Password
	 **/
    public void setPassword(String nvPassword){
       this.password = nvPassword;
    }

}
