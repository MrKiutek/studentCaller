

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Bdd {

    private static Connection conn;
    private static Statement stmt;

    public Bdd() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException {

        File temp;

        Class driverClass = Class.forName("org.apache.derby.jdbc.ClientDriver") ;
		DriverManager.registerDriver((Driver)driverClass.newInstance());
        
        try {
            temp = new File("Java/libraries/Derby_10_14_2/bin/BddEce/db.lck");

            if(temp.exists()){
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/bddEce;user=admin;password=admin") ;
                stmt = conn.createStatement();

            }else{
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/bddEce;create=true");
                turnOnBuiltInUsers(conn);
                stmt = conn.createStatement();
                createBdd();
            }
            
        } catch (Exception e) {
            //TODO: handle exception
        }



        

	}	



    private static void createBdd() throws SQLException {

        
		
        stmt.execute("CREATE TABLE ADMIN.EMPLOYE (" + 
                 "	NUMERO INTEGER NOT NULL," + 
                 "	NOM VARCHAR(255)," + 
                 "	PRENOM VARCHAR(255)," + 	
                 "	ADRESSE VARCHAR(255)," + 
                 "	TEL VARCHAR(255)," + 
                 "	CONSTRAINT SQL200110010909360 PRIMARY KEY (NUMERO)" + 
                 ")");
         System.out.println("Table created");
         
         
         stmt.execute("CREATE TABLE ADMIN.MALADE (" + 
                 "	NO_MALADE INTEGER NOT NULL," + 
                 "	NOM VARCHAR(255)," + 
                 "	PRENOM VARCHAR(255)," + 
                 "	ADRESSE VARCHAR(255)," + 
                 "	TEL VARCHAR(255)," + 
                 "	MUTUELLE VARCHAR(225)," + 
                 "	CONSTRAINT SQL200110011901590 PRIMARY KEY (NO_MALADE)" + 
                 ")");
         System.out.println("Table created");
         
         stmt.execute("CREATE TABLE ADMIN.SERVICE (" + 
                 "	CODE_SERVICE VARCHAR(255) NOT NULL," + 
                 "	NOM VARCHAR(255)," + 
                 "	BATIMENT VARCHAR(255)," + 
                 "	DIRECTEUR INTEGER NOT NULL," + 
                 "	CONSTRAINT SQL200110010348530 PRIMARY KEY (CODE_SERVICE)" + 
                 ")");
         System.out.println("Table created");
         
         stmt.execute("CREATE TABLE ADMIN.CHAMBRE (" + 
                 "	CODE_SERVICE VARCHAR(255) NOT NULL," + 
                 "	NO_CHAMBRE INTEGER NOT NULL," + 
                 "	SURVEILLANT INTEGER NOT NULL," + 
                 "	NB_LITS INTEGER NOT NULL," + 
                 "	CONSTRAINT SQL200110010621030 PRIMARY KEY (NO_CHAMBRE,CODE_SERVICE)," + 
                 "	CONSTRAINT CHAMBRE_FK FOREIGN KEY (CODE_SERVICE) REFERENCES ADMIN.SERVICE(CODE_SERVICE)" + 
                 ")");
         System.out.println("Table created");
         
         stmt.execute("CREATE TABLE ADMIN.DOCTEUR (" + 
                 "	NUMERO INTEGER NOT NULL," + 
                 "	SPECIALITE VARCHAR(255)," + 
                 "	CONSTRAINT SQL200110011019050 PRIMARY KEY (NUMERO)," + 
                 "	CONSTRAINT DOCTEUR_FK FOREIGN KEY (NUMERO) REFERENCES ADMIN.EMPLOYE(NUMERO)" + 
                 ")");
         System.out.println("Table created");
         
         stmt.execute("CREATE TABLE ADMIN.HOSPITALISATION (" + 
                 "	NO_MALADE INTEGER NOT NULL," + 
                 "	CODE_SERVICE VARCHAR(255)," + 
                 "	NO_CHAMBRE INTEGER NOT NULL," + 
                 "	LIT INTEGER NOT NULL," + 
                 "	CONSTRAINT HOSPITALISATION_PK PRIMARY KEY (NO_MALADE)," + 
                 "	CONSTRAINT HOSPITALISATION_FK FOREIGN KEY (CODE_SERVICE) REFERENCES ADMIN.SERVICE(CODE_SERVICE)," + 
                 "	CONSTRAINT HOSPITALISATION_FK_2 FOREIGN KEY (NO_CHAMBRE,CODE_SERVICE) REFERENCES ADMIN.CHAMBRE(NO_CHAMBRE,CODE_SERVICE)," + 
                 "	CONSTRAINT HOSPITALISATION_FK_3 FOREIGN KEY (NO_MALADE) REFERENCES ADMIN.MALADE(NO_MALADE)" +
                 ")");
         System.out.println("Table created");
         
         stmt.execute("CREATE TABLE ADMIN.INFIRMIER (" + 
                 "	NUMERO INTEGER NOT NULL," + 
                 "	CODE_SERVICE VARCHAR(255)," + 
                 "	ROTATION VARCHAR(255)," + 
                 "	SALAIRE DOUBLE NOT NULL," + 
                 "	CONSTRAINT SQL200110011248450 PRIMARY KEY (NUMERO)," + 
                 "	CONSTRAINT INFIRMIER_FK FOREIGN KEY (NUMERO) REFERENCES ADMIN.EMPLOYE(NUMERO)," + 
                 "	CONSTRAINT INFIRMIER_FK_1 FOREIGN KEY (CODE_SERVICE) REFERENCES ADMIN.SERVICE(CODE_SERVICE)" + 
                 ")");
         System.out.println("Table created");
         
         stmt.execute("CREATE TABLE ADMIN.SOIGNE (" + 
                 "	NUMERO INTEGER NOT NULL," + 
                 "	NO_MALADE INTEGER NOT NULL," + 
                 "	CONSTRAINT SOIGNE_FK FOREIGN KEY (NO_MALADE) REFERENCES ADMIN.MALADE(NO_MALADE)," + 
                 "	CONSTRAINT SOIGNE_FK_3 FOREIGN KEY (NUMERO) REFERENCES ADMIN.DOCTEUR(NUMERO)" + 
                 ")");
         System.out.println("Table created");
         
         
         
         String query;
        
    }
    
    
    private static void turnOnBuiltInUsers(Connection conn) 
                throws SQLException {
    
            String setProperty = 
                "CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(";
            String getProperty = 
                "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(";
            String requireAuth = "'derby.connection.requireAuthentication'";
            String defaultConnMode =
                "'derby.database.defaultConnectionMode'";
            String fullAccessUsers = "'derby.database.fullAccessUsers'";
            String readOnlyAccessUsers =
                "'derby.database.readOnlyAccessUsers'";
            String provider = "'derby.authentication.provider'";
            String propertiesOnly = "'derby.database.propertiesOnly'";
    
            System.out.println("Turning on authentication.");
            Statement s = conn.createStatement();
    
            // Set and confirm requireAuthentication
            s.executeUpdate(setProperty + requireAuth + ", 'true')");
            ResultSet rs = s.executeQuery(getProperty + requireAuth + ")");
            rs.next();
            System.out.println("Value of requireAuthentication is " +
                rs.getString(1));
    
            // Set authentication scheme to Derby builtin
            s.executeUpdate(setProperty + provider + ", 'BUILTIN')");
    
            // Create some sample users
            s.executeUpdate(
                setProperty + "'derby.user.admin', 'admin')");
            s.executeUpdate(
                setProperty + "'derby.user.guest', '1234')");
     
            // Define noAccess as default connection mode
            s.executeUpdate(
                setProperty + defaultConnMode + ", 'noAccess')");
    
            // Confirm default connection mode
            rs = s.executeQuery(getProperty + defaultConnMode + ")");
            rs.next();
            System.out.println("Value of defaultConnectionMode is " +
                rs.getString(1));
    
            // Define read-write user
            s.executeUpdate(
                setProperty + fullAccessUsers + ", 'admin')");
    
            // Define read-only user
            s.executeUpdate(
                setProperty + readOnlyAccessUsers + ", 'guest')");
    
            // Confirm full-access users
            rs = s.executeQuery(getProperty + fullAccessUsers + ")");
            rs.next();
            System.out.println(
                "Value of fullAccessUsers is " + rs.getString(1));
    
            // Confirm read-only users
            rs = s.executeQuery(getProperty + readOnlyAccessUsers + ")");
            rs.next();
            System.out.println(
                "Value of readOnlyAccessUsers is " + rs.getString(1));
    
            // We would set the following property to TRUE only when we were
            // ready to deploy. Setting it to FALSE means that we can always
            // override using system properties if we accidentally paint
            // ourselves into a corner.
            s.executeUpdate(setProperty + propertiesOnly + ", 'false')");
            s.close();
        }		
    



        public Statement getStatement(){
            return stmt;
        }
        
    }
