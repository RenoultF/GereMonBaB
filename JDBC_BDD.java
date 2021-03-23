
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
	 ** Méthode sauvegardant toutes les données d'un BaB en créant une nouvelle sauvegarde si le bab n'est pas dans la bdd, sinon il modifie les informations déjà présentes
	 **/
	static public void sauvegarderBab(BaB bab) {
		String sql;
		
		if(bab.getIdBaB() != 0) {
			System.out.println("Old BaB");
			sql = "UPDATE BAB SET " +
			   "nom = '"			+ bab.getNomBaB() + "', " +
			   "adresse = '"		+ bab.getAdresseBaB() + "', " +
			   "date = '"			+ bab.getDateBaB() + "', " +
			   "prixStand =  "		+ bab.getPrixStand() + ", " +
			   "dimensionCarteX = "	+ bab.getDimX() + ", " +
			   "dimensionCarteX = "	+ bab.getDimY() + " " +
			   "WHERE idBab = "		+ bab.getIdBaB();
		}
		else {
			System.out.println("New BaB	");
			sql = "INSERT INTO BAB VALUES(" +
			   getNewIdBab() +", '"+
			   bab.getNomBaB() + "', '" +
			   bab.getAdresseBaB() + "', '" +
			   bab.getDateBaB() + "', " +
			   bab.getPrixStand() + ", " +
			   bab.getDimX() + ", " +
			   bab.getDimY() + ") ";
		}
		try{
			stmt.executeUpdate(sql);
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	
	static public BaB chargerBab(int id) {
		String sql = "SELECT * FROM BAB WHERE idBab = " + id +"";
		BaB babLoaded = null;
		try{
			res = stmt.executeQuery(sql);
			while(res.next()) {
				int 	idBaB   = res.getInt("idBab");
				String 	nomBaB  = res.getString("nom");
				String 	dateBaB = res.getString("date");
				String	adreBaB = res.getString("adresse");
				int 	prixBaB = res.getInt("prixStand");
				int 	dimXBaB = res.getInt("dimensionCarteX");
				int 	dimYBaB = res.getInt("dimensionCarteY");
				
				babLoaded = new BaB(nomBaB, dateBaB, prixBaB, adreBaB, dimXBaB, dimYBaB);
				babLoaded.setIdBaB(idBaB);
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
