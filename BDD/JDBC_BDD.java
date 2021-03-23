package BDD;

import java.sql.*;

public class JDBC_BDD {
   // JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/GEREMONBAB";
   
   
   //  Database credentials
	static final String USER = "root";
	static final String PASS = "password";
   
	static private Connection conn = null;
	static private Statement  stmt = null;
	static private ResultSet  res  = null;
	static private DatabaseMetaData meta = null;
   
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
	 ** Méthode privée permettant la recherche du prochain idBab pour la sauvegarde d'un nouveau bab
	 **/
	static private int getNewIdBab() {
		int newId = 0;
		String sql = "SELECT max(idBab) FROM BAB";
		try{
			res = stmt.executeQuery(sql);
			while(res.next()) {
				newId = res.getInt("max(idBab)")+1;
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
	 ** Méthode sauvegardant toutes les données d'un BaB
	 **/
	static public void sauvegarderBab(String nomBaB, String adresseBaB, String dateBaB, int prixStand, int dimX, int dimY) {
		String sql;
		sql = "INSERT INTO BAB VALUES(" +
			   getNewIdBab() +", '" +
			   nomBaB + "', '" +
			   adresseBaB + "', '" +
			   dateBaB + "', " +
			   prixStand + ", " +
			   dimX + ", " +
			   dimY + ") ";
		
		try{
			stmt.executeUpdate(sql);
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	
	static public void chargerBab(int id) {
		/*String sql = "SELECT * FROM BAB WHERE idBab = " + id +"";
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
		return false;*/
	}
	
	
	
	public static void main(String[] args) {
		startJDBC();
		//creerProfil("The", "test", "theTest.mail.com", "mdpTest", "exposant");
		if(connexion("theTest.mail.com", "mdpTest") == true)
			System.out.println("Connexion OK");
		sauvegarderBab("tada", "10 Route des 10", "10/10", 5, 10, 10);
		
	}
}
