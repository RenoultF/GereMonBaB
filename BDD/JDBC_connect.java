import java.sql.*;

public class JDBC_connect {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/GEREMONBAB";
   
   
   //  Database credentials
   static final String USER = "root";
   static final String PASS = "password";
   
   
   public static void main(String[] args) {
	   Connection conn = null;
	   Statement  stmt = null;
	   ResultSet  res  = null;
	   DatabaseMetaData meta = null;
	   try{
		  //STEP 2: Register JDBC driver
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  System.out.println("Connecting to database...");
		  conn = DriverManager.getConnection(DB_URL, USER, PASS);
		  System.out.println("Connected successfully");
      	  stmt = conn.createStatement();
      	  meta = conn.getMetaData();
		  
		  System.out.println("Creating database......");
		  //STEP 3: Execute query
		  
		  /****       TABLES        ****/
		  String tableTYPE = 		"CREATE TABLE TYPE " 		+
		  							"(idType INTEGER not null, "+
		  							"longueur INTEGER, " 		+
		  							"largeur INTEGER, " 		+
		  							"Reservable BOOLEAN, " 		+
		  							"PRIMARY KEY (idType))";
		  res = meta.getTables(null, null, "TYPE", null);
		  if(!res.next()) {
		  	  stmt.executeUpdate(tableTYPE);
		  }
		  
		  String tableBAB  = 		"CREATE TABLE BAB " 		+
		  							"(idBab INTEGER not null, " +
		  							"nom VARCHAR(20), "			+
		  							"adresse VARCHAR(50), " 	+
		  							"date VARCHAR(20), " 		+
		  							"prixStand INTEGER, " 		+
		  							"dimensionCarteX INTEGER, "	+
		  							"dimensionCarteY INTEGER, "	+
		  							"PRIMARY KEY (idBab))";
		  res = meta.getTables(null, null, "BAB", null);
		  if(!res.next()) {
		  	  stmt.executeUpdate(tableBAB);
		  }
		  
		  String tableEMPLACEMENT =	"CREATE TABLE EMPLACEMENT " 		+
		  							"(idSauvegarde INTEGER not null, " 	+
		  							"idEmplacement INTEGER not null, " 	+
		  							"nom VARCHAR(20), "					+
		  							"statutReservation VARCHAR(20), "	+
		  							"statutPaiment VARCHAR(20), "		+
		  							"coordonneeX INTEGER, "				+
		  							"coordonneeY INTEGER, "				+
		  							"idType INTEGER not null,"			+
		  							"PRIMARY KEY (idSauvegarde),"		+
		  							"FOREIGN KEY (idType) REFERENCES TYPE (idType)) ";
		  res = meta.getTables(null, null, "EMPLACEMENT", null);
		  if(!res.next()) {
		  	  stmt.executeUpdate(tableEMPLACEMENT);
		  }
		  
		  String tablePROFIL	 =	"CREATE TABLE PROFIL "		 			+
		  							"(idProfil INTEGER not null, " 			+
		  							"nom VARCHAR(20), "						+
		  							"prenom VARCHAR(20), "					+
		  							"type ENUM('exposant','organisateur'), "+
		  							"mail VARCHAR(30) UNIQUE , "			+
		  							"mdp VARCHAR(60), "						+
		  							"PRIMARY KEY (idProfil))";
		  res = meta.getTables(null, null, "PROFIL", null);
		  if(!res.next()) {
		  	  stmt.executeUpdate(tablePROFIL);
		  }
		  
		  
		  /****  ASSOCIATION TABLES ****/
		  String tablePARTICIPE	 =	"CREATE TABLE PARTICIPE "		 						+
		  							"(idProfil INTEGER not null, " 							+
		  							"idSauvegarde INTEGER not null, "						+
		  							"moyenPaiement VARCHAR(20) not null, "					+
		  							"PRIMARY KEY (idProfil, idSauvegarde), "				+
		  							"FOREIGN KEY (idProfil) REFERENCES PROFIL (idProfil), "	+
		  							"FOREIGN KEY (idSauvegarde) REFERENCES EMPLACEMENT (idSauvegarde))";
		  res = meta.getTables(null, null, "PARTICIPE", null);
		  if(!res.next()) {
		  	  stmt.executeUpdate(tablePARTICIPE);
		  }
		  
		  String tableORGANISE	 =	"CREATE TABLE ORGANISE "		 						+
		  							"(idProfil INTEGER not null, " 							+
		  							"idBab INTEGER not null, "								+
		  							"PRIMARY KEY (idProfil, idBab), "						+
		  							"FOREIGN KEY (idProfil) REFERENCES PROFIL (idProfil),"	+
		  							"FOREIGN KEY (idBab) REFERENCES BAB (idBab))";
		  res = meta.getTables(null, null, "ORGANISE", null);
		  if(!res.next()) {
		  	  stmt.executeUpdate(tableORGANISE);
		  }
		  
		  String tableCONTIENT	 =	"CREATE TABLE CONTIENT "		 				+
		  							"(idBab INTEGER not null, " 					+
		  							"idSauvegarde INTEGER not null, "				+
		  							"PRIMARY KEY (idBab, idSauvegarde), "			+
		  							"FOREIGN KEY (idBab) REFERENCES BAB (idBab),"	+
		  							"FOREIGN KEY (idSauvegarde) REFERENCES EMPLACEMENT (idSauvegarde))";
		  res = meta.getTables(null, null, "CONTIENT", null);
		  if(!res.next()) {
		  	  stmt.executeUpdate(tableCONTIENT);
		  }
		  
		  
		  
		  System.out.println("Database created");	  
	   }catch(SQLException se){
		  //Handle errors for JDBC
		  se.printStackTrace();
	   }catch(Exception e){
		  //Handle errors for Class.forName
		  e.printStackTrace();
	   }finally{
		  //finally block used to close resources
		  try{
		     if(conn!=null)
		        conn.close();
		  }catch(SQLException se){
		     se.printStackTrace();
		  }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
}
