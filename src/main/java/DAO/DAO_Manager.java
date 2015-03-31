
package DAO;

import java.sql.DriverManager;
import java.sql.*;

public class DAO_Manager {
    private static Connection connection;
    private DAO_Persoon daoPersoon;
    private DAO_Adres daoAdres;
    private DAO_Resultaat daoResultaat;
    
   static Connection initializeDB(){
       
        try{
            if (connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost/studenten", "root", "");
            System.out.println("Database connected");
            }
            return connection;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
   
    public void closeConnection() throws SQLException {
       if (connection != null) {

            connection.close();
            connection = null;
        }
    }
   
   
   public DAO_Persoon getDAO_Persoon(){
    if(this.daoPersoon == null){
      this.daoPersoon = new DAO_Persoon(this.connection);
    }
    return this.daoPersoon;
   }
   
   public DAO_Adres getDAO_Adres(){
       if(this.daoAdres == null){
           this.daoAdres = new DAO_Adres(this.connection);
       }
       return daoAdres;
   }
   
   public DAO_Resultaat getDAO_Resultaat(){
       if(this.daoResultaat == null){
           this.daoResultaat = new DAO_Resultaat(this.connection);
       }
       return daoResultaat;
   }
}
