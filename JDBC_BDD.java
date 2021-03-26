
import java.sql.*;
import java.util.*;

public class JDBC_BDD {
   // JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/GEREMONBAB";
   
   
   //  Database credentials
	static final String USER = "root";
	static final String PASS = "password";
   
	static private Connection conn = null;
	static private Statement  stmt = null;
	static private DatabaseMetaData meta = null;
	static private ResultSet  res  = null;
	static private ResultSet  res2 = null;
	static private ResultSet  res3 = null;
   
	/****************************************************************************************************************************/
	public static void startJDBC() {
	   try{
		  //STEP 2: Register JDBC driver
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  System.out.println("Connecting to database...");
		  conn = DriverManager.getConnection(DB_URL, USER, PASS);
		  System.out.println("Connected successfully");
      	  stmt = conn.createStatement();
      	  meta = conn.getMetaData();

		}catch(SQLException se){
		  //Handle errors for JDBC
		  se.printStackTrace();
		}catch(Exception e){
		  //Handle errors for Class.forName
		  e.printStackTrace();
		}
	}
	
	
	/*************************************************PRIVATE METHODS************************************************************/
	
	/**
	 ** Méthode privée permettant la recherche du prochain IdProfil pour la création d'un nouvel utilisateur
	 **/
	static private int getNewIdUser() {
		int newId = 0;
		String sql = "SELECT max(idProfil) FROM PROFIL";
		try{
			res = stmt.executeQuery(sql);
			while(res.next()) {
				newId = res.getInt("max(idProfil)")+1;
			}
		}catch(SQLException se){
			se.printStackTrace();
		}//end try
		
		return newId;
	}
	
	
	/**
	 ** Méthode privée permettant la recherche du prochain id dans une table dont le nom est passé en paramètre
	 **/
	static private int getNewIdTable(String table) {
		int newId = 0;
		String id;
		
		switch(table) {
			case "BAB" :
				id = "idBab";
				break;
			case "PROFIL" :
				id = "idProfil";
				break;
			case "EMPLACEMENT" :
				id = "idSauvegarde";
				break;
			case "TYPE" :
				id = "idType";
				break;
			default :
				id = "error";
		}
		
		String sql = "SELECT max("+ id +") FROM " + table;
		try{
			res = stmt.executeQuery(sql);
			while(res.next()) {
				newId = res.getInt("max("+ id +")")+1;
			}
		}catch(SQLException se){
			se.printStackTrace();
		}//end try
		
		return newId;
	}
	
	/**
	 ** Méthode privée permettant de créer un profil en faisant passer les informations en paramètres
	 ** param
	 ** param
	 ** param
	 ** param
	 ** param
	 ** ATTENTION LES MOTS DE PASSE NE SONT PAS CRYPTES
	 **/
	static private void creerProfil(String nom, String prenom, String mail, String mdp, String typeProfil) {
		String sql;
		sql = "INSERT INTO PROFIL VALUES(" +
			   getNewIdUser() +", '" +
			   nom + "', '" +
			   prenom + "', '" +
			   typeProfil + "', '" +
			   mail + "', '" +
			   mdp + "') ";
		
		try{
			stmt.executeUpdate(sql);
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	
	/**
	 ** Méthode sauvegardant toutes les données d'un Emplacement en créant une nouvelle sauvegarde si l'emplacement n'est pas dans la bdd, sinon il modifie les informations déjà présentes
	 
		if(!this.type.equals("Stand")){
	 **/
	static private void sauvegarderEmplacement(Emplacement emp, int idBab) {
		int idEmp = emp.getIdSauvegarde();
		int idType = 0;
		
		String sqlEmplacement, sqlGetType, sqlType, sqlContient = "";
		
		if(idEmp == 0) {
			System.out.println("New Emplacement	");
			
			// Recherche de l'existence d'un possible type qui correspondrait au type de l'emplacement, sinon création d'un nouveau type			   
			sqlGetType = "SELECT * FROM TYPE WHERE longueur = "+ emp.getLongueur()+" AND largeur = "+ emp.getLargeur() +" AND reservable = "+ emp.estStand()+"";
			try{
				res = stmt.executeQuery(sqlGetType);
				if(res.next()) {
					idType = res.getInt("idType");
				}
				else {
					idType = getNewIdTable("TYPE");
					sqlType = "INSERT INTO TYPE VALUES(" +
						idType 				+ ", " 	+
						emp.getLongueur() 	+ ", " 	+
						emp.getLargeur() 	+ ", " 	+
						emp.estStand()	 	+ ") ";
					stmt.executeUpdate(sqlType);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
			
			// Création du nouvel emplacement
			idEmp = getNewIdTable("EMPLACEMENT");
			sqlEmplacement = "INSERT INTO EMPLACEMENT VALUES(" +
			   idEmp 				+ ", " 	+
			   emp.getIdType() 		+ ", '" +
			   emp.getNom() 		+ "', '"+
			   emp.getReservation() + "', '"+
			   emp.getPaiement() 	+ "', " +
			   emp.getCoordonneeX() + ", " 	+
			   emp.getCoordonneeY() + ", " 	+
			   idType + ") ";
			
			// Creation des données dans la table d'association
			sqlContient = "INSERT INTO CONTIENT VALUES(" +
			   idBab + ", " +
			   idEmp + ") " ;
		}
		else {	
			// Update de l'ancien emplacement		   
			System.out.println("Old Emplacement");
			sqlEmplacement = "UPDATE EMPLACEMENT SET " +
			   "nom = "					+ emp.getNom() 			+ ", " 	+
			   "statutReservation = '"	+ emp.getReservation() 	+ "', " +
			   "statutPaiment = '"		+ emp.getPaiement() 	+ "' " 	+
			   "WHERE idEmplacement = "	+ idEmp;		
		}
		
		try{
			stmt.executeUpdate(sqlEmplacement);
			stmt.executeUpdate(sqlContient);
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	
	
	/*************************************************PUBLIC METHODS*************************************************************/
	
	/**
	 ** Méthode appelant la méthode privée pour créer un nouveau profil exposant
	 **/
	static public void creerExposant(String nom, String prenom, String mail, String mdp) {
		creerProfil(nom, prenom, mail, mdp, "exposant");
	}
	
	/**
	 ** Méthode appelant la méthode privée pour créer un nouveau profil organisateur
	 **/
	static public void creerOrganisateur(String nom, String prenom, String mail, String mdp) {
		creerProfil(nom, prenom, mail, mdp, "organisateur");
	}
	
	static public boolean connexion(String mail, String mdp) {
		String sql = "SELECT mdp FROM PROFIL WHERE mail = '" + mail +"'";
		try{
			res = stmt.executeQuery(sql);
			while(res.next()) {
				String mdpBDD = res.getString("mdp");
				if(mdp.equals(mdpBDD))
					return true;
				else {
					return false;
				}
			}
		}catch(SQLException se){
			se.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 ** Méthode sauvegardant toutes les données d'un BaB en créant une nouvelle sauvegarde si le bab n'est pas dans la bdd, sinon il modifie les informations déjà présentes
	 **/
	static public void sauvegarderBab(BaB bab) {
		int idBab = bab.getIdBaB();
		
		LinkedList<Emplacement> listeStands = bab.getListeStand();
		LinkedList<Emplacement> listeAutres = bab.getListeAutre();
		int size;
		Emplacement emplacementCourant;
		
		String sqlBaB;
		
		if(idBab == 0) {
			System.out.println("New BaB	");
			idBab = getNewIdTable("BAB");
			sqlBaB = "INSERT INTO BAB VALUES(" 	+
			   idBab 				+ ", '"		+
			   bab.getNomBaB() 		+ "', '" 	+
			   bab.getAdresseBaB() 	+ "', '" 	+
			   bab.getDateBaB() 	+ "', " 	+
			   bab.getPrixStand() 	+ ", " 		+
			   bab.getDimX() 		+ ", " 		+
			   bab.getDimY() 		+ ") "		;				
		}
		else {			   
			System.out.println("Old BaB");
			sqlBaB = "UPDATE BAB SET " +
			   "nom = '"			+ bab.getNomBaB() 		+ "', " +
			   "adresse = '"		+ bab.getAdresseBaB() 	+ "', " +
			   "date = '"			+ bab.getDateBaB() 		+ "', " +
			   "prixStand = "		+ bab.getPrixStand() 	+ ", " 	+
			   "dimensionCarteX = "	+ bab.getDimX() 		+ ", " 	+
			   "dimensionCarteX = "	+ bab.getDimY() 		+ " " 	+
			   "WHERE idBab = "		+ bab.getIdBaB();		
		}
		
		//Sauvegarde du BaB
		try{
			stmt.executeUpdate(sqlBaB);
		}catch(SQLException se){
			se.printStackTrace();
		}
		
		// Sauvegarder les données des stands
		size = listeStands.size();
		for(int i = 0; i < size; i++) {
			emplacementCourant = listeStands.get(i);
			sauvegarderEmplacement(emplacementCourant, idBab);
		}
		// Sauvegarder les données des emplacements autres
		size = listeAutres.size();
		for(int i = 0; i < size; i++) {
			emplacementCourant = listeAutres.get(i);
			sauvegarderEmplacement(emplacementCourant, idBab);
		}

	}
	
	/**
	 ** Méthode publique chargeant les données d'un bab et les renvois
	 ** @return un BaB
	 **/
	static public BaB chargerBab(int id) {
		//requete SQL
		String sqlBab, sqlContient, sqlEmplacement, sqlType;
		
		//variables du BaB
		int		idBaB = 0, prixBaB, dimXBaB, dimYBaB;
		String	nomBaB, dateBaB, adreBaB;
		
		//variables de l'emplacement
		Emplacement emp;
		int 	idSauvegarde, idEmplacement = 0, coordonneeX = 0, coordonneeY = 0, idType = 0;
		String	nomEmplacement = "", statutReservation = "", statutPaiement = "";
		
		//variables du type de l'emplacement
		int		longueurType = 0, largeurType = 0;
		boolean	reservableType = false;
	
		sqlBab = "SELECT * FROM BAB WHERE idBab = " + idBaB +"";
		BaB babLoaded = null;
		try{
			res = stmt.executeQuery(sqlBab);
			while(res.next()) {
				idBaB   = res.getInt("idBab");
				nomBaB  = res.getString("nom");
				dateBaB = res.getString("date");
				adreBaB = res.getString("adresse");
				prixBaB = res.getInt("prixStand");
				dimXBaB = res.getInt("dimensionCarteX");
				dimYBaB = res.getInt("dimensionCarteY");
				
				babLoaded = new BaB(nomBaB, dateBaB, prixBaB, adreBaB, dimXBaB, dimYBaB);
				babLoaded.setIdBaB(idBaB);
			}
		}catch(SQLException se){
			se.printStackTrace();
		}
		
		sqlContient = "SELECT * FROM CONTIENT WHERE idBab = "+ idBaB +"";
		try{
			res = stmt.executeQuery(sqlContient);
			while(res.next()) {
				// Récupération de la liste des emplacements
				idSauvegarde = res.getInt("idSauvegarde");
				sqlEmplacement = "SELECT * FROM EMPLACEMENT WHERE idSauvegarde = "+ idSauvegarde +"";
				res2 = stmt.executeQuery(sqlEmplacement);
				while(res2.next()) {
					idEmplacement		= res2.getInt("idEmplacement");
					nomEmplacement		= res2.getString("nomEmplacement");
					statutReservation	= res2.getString("statutReservation");
					statutPaiement		= res2.getString("statutPaiement");
					coordonneeX			= res2.getInt("coordonneeX");
					coordonneeY			= res2.getInt("coordonneeY");
					idType				= res2.getInt("idType");
				}
				
				// Récupération du type associé à l'emplacement
				sqlType = "SELECT * FROM TYPE WHERE idType = "+ idType +"";
				res3 = stmt.executeQuery(sqlType);
				while(res3.next()) {
					longueurType		= res3.getInt("longueurType");
					largeurType			= res3.getInt("largeurType");
					reservableType		= res3.getBoolean("reservableType");
				}
				
				//Paramètrage de l'emplacement avec les données sauvegardées
				emp = new Emplacement(idEmplacement, nomEmplacement, coordonneeX, coordonneeY);
				emp.setIdSauvegarde(idSauvegarde);
				emp.setReservation(statutReservation);
				emp.setPaiement(statutPaiement);
				emp.setTailleX(longueurType);
				emp.setTailleY(largeurType);
				
				if(reservableType) 
					babLoaded.chargerStand(emp);
				else 
					babLoaded.chargerAutre(emp);
			}
		}catch(SQLException se){
			se.printStackTrace();
		}
		
		return babLoaded;
	}
	
	
	
	public static void main(String[] args) {
		startJDBC();
		//creerProfil("The", "test", "theTest.mail.com", "mdpTest", "exposant");
		if(connexion("theTest.mail.com", "mdpTest") == true)
			System.out.println("Connexion OK");
		//BaB bab = new BaB("tada", "10/10", 5, "10 Route des 10", 10, 10);
		//sauvegarderBab(bab);
		BaB babLoaded = chargerBab(1);
		babLoaded.setNomBaB("BAB chargé");
		sauvegarderBab(babLoaded);
		
	}
}
