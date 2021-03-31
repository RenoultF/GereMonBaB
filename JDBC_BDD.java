//import com.mysql.jdbc.Driver;
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
	static private DatabaseMetaData meta = null;
	static private Statement  stmt  = null;
	static private Statement  stmt2 = null;
	static private Statement  stmt3 = null;
	static private ResultSet  res   = null;
	static private ResultSet  res2  = null;
	static private ResultSet  res3  = null;
   
	/****************************************************************************************************************************/
	public static void startJDBC() {
	   try{
		  //STEP 2: Register JDBC driver
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  System.out.println("Connecting to database...");
		  conn = DriverManager.getConnection(DB_URL, USER, PASS);
		  System.out.println("Connected successfully");
      	  stmt  = conn.createStatement();
      	  stmt2 = conn.createStatement();
      	  stmt3 = conn.createStatement();
      	  meta  = conn.getMetaData();

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
	 ** Méthode privée permettant la recherche du prochain id dans une table
	 ** @param  table un String contenant le nom de la table
	 ** @return newId un entier contenant le prochain id de la table passée en paramètre
	 **/
	static public int getNewIdTable(String table) {
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
	 ** @param
	 ** @param
	 ** @param
	 ** @param
	 ** @param
	 ** ATTENTION LES MOTS DE PASSE NE SONT PAS CRYPTES
	 **/
	static private void creerProfil(int id, String nom, String prenom, String mail, String mdp, String typeProfil) {
		String sql;
		sql = "INSERT INTO PROFIL VALUES(" +
			   id  						+ ", '"  +
			   nom 						+ "', '" +
			   prenom 					+ "', '" +
			   typeProfil 				+ "', '" +
			   mail 					+ "', '" +
			   mdp 						+ "') ";
		
		try{
			stmt.executeUpdate(sql);
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	
	/**
	 ** Méthode sauvegardant toutes les données d'un Emplacement en créant une nouvelle sauvegarde si l'emplacement n'est pas dans la bdd, sinon il modifie les informations déjà présentes
	 **/
	static private void sauvegarderEmplacement(Emplacement emp, BaB bab) {
	
		int idEmp = emp.getIdSauvegarde();
		int idBab = bab.getIdBaB();
		int idType = 0, size;
		boolean contient = false;
		
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
			   emp.getType() 		+ "', '"+
			   emp.getReservation() + "', '"+
			   emp.getPaiement() 	+ "', " +
			   emp.getCoordonneeX() + ", " 	+
			   emp.getCoordonneeY() + ", " 	+
			   idType 				+ ") ";
			
			// Creation des données dans la table d'association
			contient = true;
			sqlContient = "INSERT INTO CONTIENT VALUES(" +
			   idBab + ", " +
			   idEmp + ") " ;
		}
		else {	
			// Update de l'ancien emplacement		   
			System.out.println("Old Emplacement");
			sqlEmplacement = "UPDATE EMPLACEMENT SET " +
			   "nom = '"				+ emp.getType()			+ "', " +
			   "statutReservation = '"	+ emp.getReservation() 	+ "', " +
			   "statutPaiment = '"		+ emp.getPaiement() 	+ "' " 	+
			   "WHERE idEmplacement = "	+ idEmp;		
		}
		
		try{
			stmt.executeUpdate(sqlEmplacement);
			if(contient)
				stmt.executeUpdate(sqlContient);
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	
	/**
	 ** Méthode sauvegardant toutes les données d'une réservation en créant une nouvelle sauvegarde si la réservation n'est pas dans la bdd, sinon il modifie les informations déjà présentes
	 **/
	static private void sauvegarderReservation(Reservation rsv) {
		
		String sqlGetProfilSpecial, sqlProfilSpecial, sqlParticipe = "", sqlGetParticipe;
		
		int idProfil, idEmp = rsv.getEmplacement().getIdSauvegarde();
		System.out.println("Emplacement : " + idEmp);
		System.out.println("Reservation : " + rsv.getIdReservant());
		
			if(rsv.getIdReservant() == 0) {
				System.out.println("Reservation par organisateur");
				
				// Recherche de l'existence d'un possible type qui correspondrait au profil, sinon création d'un nouveau profil
				String mailReservation =  rsv.getNom()+"."+rsv.getPrenom()+"@reservation";
				sqlGetProfilSpecial	   = "SELECT * FROM PROFIL WHERE mail = '" + mailReservation+"'";
				try{
					res = stmt.executeQuery(sqlGetProfilSpecial);
					if(res.next()) {
						idProfil = res.getInt("idProfil");
					}
					else {
						idProfil = getNewIdTable("PROFIL");
						sqlProfilSpecial = "INSERT INTO PROFIL VALUES(" +
							idProfil 			+ ", '"	+
							rsv.getNom() 		+ "', '"+
							rsv.getPrenom() 	+ "', '"+
							"exposant"			+ "', '"+
							mailReservation		+ "', '"+
							"12345678"		 	+ "') ";
						stmt.executeUpdate(sqlProfilSpecial);
					}
					
					sqlGetParticipe	   = "SELECT * FROM PARTICIPE WHERE idProfil = " + idProfil +" AND idSauvegarde = " + idEmp + "";
					try{
						res = stmt.executeQuery(sqlGetParticipe);
						if(!(res.next())) {
							// Creation des données dans la table d'association
							sqlParticipe = "INSERT INTO PARTICIPE VALUES(" +
							   idProfil		 		  + ", "  +
							   idEmp		 		  + ", '" +
							   rsv.getMoyenPaiement() + "')";
							stmt.executeUpdate(sqlParticipe);
						}
					}catch(SQLException se){
						se.printStackTrace();
					}   
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
		
	}
	
	/*************************************************PUBLIC METHODS*************************************************************/
	
	/**
	 ** Méthode appelant la méthode privée pour créer un nouveau profil exposant
	 **/
	static public void creerExposant(int id, String nom, String prenom, String mail, String mdp) {
		creerProfil(id, nom, prenom, mail, mdp, "exposant");
	}
	
	/**
	 ** Méthode appelant la méthode privée pour créer un nouveau profil organisateur
	 **/
	static public void creerOrganisateur(int id, String nom, String prenom, String mail, String mdp) {
		creerProfil(id, nom, prenom, mail, mdp, "organisateur");
	}
	
	/**
	 ** Méthode qui renvois le profil et le résultat du test 
	 **/
	static public void chargerProfil(String mail, String mdp) {
		
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
	 ** Méthode renvoyant un boolean True si c'est un organisateur, False sinon. (On considere que l'utilisateur existe)
	 */
	static public boolean estOrganisateur(String mail) {
		String sql = "SELECT type FROM PROFIL WHERE mail = '" + mail +"'";
		try{
			res = stmt.executeQuery(sql);
			while(res.next()) {
				String type = res.getString("type");
				if(type.equals("organisateur"))
					return true;
				else 
					return false;
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
		LinkedList<Reservation> listeReservation = bab.getListeReservation();
		
		Emplacement emplacementCourant;
		Reservation reservationCourante;
		
		int size, sizeR;
		
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
			sauvegarderEmplacement(emplacementCourant, bab);
		}
			
		// Sauvegarder les données des emplacements autres
		size = listeAutres.size();
		for(int i = 0; i < size; i++) {
			emplacementCourant = listeAutres.get(i);
			sauvegarderEmplacement(emplacementCourant, bab);
		}
		
		// Sauvegarder les données des réservations
		sizeR = listeReservation.size();
		for(int j = 0; j < sizeR; j++) {
			System.out.println("");
			reservationCourante = listeReservation.get(j);
			sauvegarderReservation(reservationCourante);
			System.out.println("listeReservation n°"+j);
		}

	}
	
	/**
	 ** Méthode publique chargeant toutes les données d'un bab et sa liste d'emplacements
	 ** @return un BaB
	 **/
	static public BaB chargerBab(int id) {
		//requete SQL
		String sqlBab, sqlContient, sqlEmplacement, sqlType, sqlParticipe, sqlProfil;
		
		//variables du BaB
		int		idBaB = 0, prixBaB, dimXBaB, dimYBaB;
		String	nomBaB, dateBaB, adreBaB;
		
		//variables de l'emplacement
		Emplacement emp;
		int 	idSauvegarde, idEmplacement = 0, coordonneeX = 0, coordonneeY = 0, idType = 0;
		String	nomEmplacement = "", statutReservation = "", statutPaiement = "";
		
		//variables du Profil
		Reservation rsv;
		int 	idProfil = 0;
		String	nomProfil = "", prenomProfil = "", statutPaiementReservation = "";
		
		//variables du type de l'emplacement
		int		longueurType = 0, largeurType = 0;
		boolean	reservableType = false;
	
		sqlBab = "SELECT * FROM BAB WHERE idBab = " + id +"";
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
				babLoaded.setIdBaB(id);
			}
		}catch(SQLException se){
			se.printStackTrace();
		}
		
		sqlContient = "SELECT * FROM CONTIENT WHERE idBab = "+ id +"";
		try{
			res = stmt.executeQuery(sqlContient);
			while(res.next()) {
				// Récupération de la liste des emplacements
				idSauvegarde = res.getInt("idSauvegarde");
				sqlEmplacement = "SELECT * FROM EMPLACEMENT WHERE idSauvegarde = "+ idSauvegarde +"";
				res2 = stmt2.executeQuery(sqlEmplacement);
				while(res2.next()) {
					idEmplacement		= res2.getInt("idEmplacement");
					nomEmplacement		= res2.getString("nom");
					statutReservation	= res2.getString("statutReservation");
					statutPaiement		= res2.getString("statutPaiment");
					coordonneeX			= res2.getInt("coordonneeX");
					coordonneeY			= res2.getInt("coordonneeY");
					idType				= res2.getInt("idType");
				}
				
				// Récupération du type associé à l'emplacement
				sqlType = "SELECT * FROM TYPE WHERE idType = "+ idType +"";
				res3 = stmt3.executeQuery(sqlType);
				while(res3.next()) {
					longueurType		= res3.getInt("longueur");
					largeurType			= res3.getInt("largeur");
					reservableType		= res3.getBoolean("Reservable");
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
					
				// Récupération de la liste des réservations
				sqlParticipe = "SELECT * FROM PARTICIPE WHERE idSauvegarde = "+ idSauvegarde +"";
				try {
					res2 = stmt2.executeQuery(sqlParticipe);
					while(res2.next()) {
						idProfil 					= res2.getInt("idProfil");
						statutPaiementReservation 	= res2.getString("moyenPaiement");
						
						sqlProfil = "SELECT * FROM PROFIL WHERE idProfil = "+ idProfil +"";
						res3 = stmt3.executeQuery(sqlProfil);
						while(res3.next()) {
							nomProfil		= res3.getString("nom");
							prenomProfil	= res3.getString("prenom");
							System.out.println("chargement d'un profil");
						}
						rsv = new Reservation(nomProfil, prenomProfil, idProfil, emp, statutPaiementReservation);
						babLoaded.ajouterReservation(rsv);
						emp.setReservation("semi_reserve");
					}
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
		}catch(SQLException se){
			se.printStackTrace();
		}
		
		return babLoaded;
	}
	
	/**
	 ** Méthode publique retournant le nombre de babs sauvegardé dans la base de données
	 ** @reutnr nbBab un entier contenant le nombre de babs
	 **/
	public int compterBab() {
		//requete SQL
		String sqlNbBab;
		//Variable à retourner
		int nbBab = 0;
		
		sqlNbBab = "SELECT COUNT(idBab) FROM BAB";
		try{
			res = stmt.executeQuery(sqlNbBab);
			while(res.next()) {
				nbBab   = res.getInt("COUNT(idBab)");
			}
		}catch(SQLException se){
			se.printStackTrace();
		}
		return nbBab;
	}
	
	/**
	 ** Méthode publique retournant les infos générales de tous les babs dans la base de données
	 ** @return infosBabs un String contenant l'id, le nom, la date et l'adresse de tous les babs
	 **/
	public String[][] chargerToutesInfos() {
		//requete SQL
		String sqlBab;
		//Variable à retourner
		String[][] infos = new String[99999][2];
		
		int i = 0;
		
		sqlBab = "SELECT idBab, nom FROM BAB";
		try{
			res = stmt.executeQuery(sqlBab);
			while(res.next()) {
				infos[i][0] = String.valueOf(res.getInt("idBab"));
				infos[i][1] = res.getString("nom");
				i++;
			}
		}catch(SQLException se){
			se.printStackTrace();
		}
		return infos;
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
