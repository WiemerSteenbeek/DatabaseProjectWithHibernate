package DAO;

import domein_klassen.Adres;
import java.util.ArrayList;
import domein_klassen.Resultaat;
import domein_klassen.POJO_Interface;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO_Resultaat implements DAOInterface {

    private Connection connection;

    DAO_Resultaat(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(POJO_Interface obj) throws SQLException {
        if (!(obj instanceof Resultaat)) {
            throw new IllegalArgumentException("Geen Resultaat object.");
        }

        String modulenaam = ((Resultaat) obj).getModulenaam();
        float resultaat = ((Resultaat) obj).getResultaat();
        char voldoende = ((Resultaat) obj).isVoldoende() ? 'T' : 'F';
        int persoonId = ((Resultaat) obj).getIdPersoon();
        
        if(modulenaam == null || resultaat == 0.0f || persoonId == 0){
            throw new IllegalArgumentException("Geen volledig resultaat!");
        }
        
        else{
        if (connection == null) {
            connection = DAO_Manager.initializeDB();
        }

        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into Resultaat (modulenaam, resultaat, voldoende, persoonId) values ('"
                + modulenaam + "', " + resultaat + ", '" + voldoende
                + "', " + persoonId + ")");
        }
    }

    @Override
    public void update(POJO_Interface obj) throws SQLException {
        if (!(obj instanceof Resultaat)) {
            throw new IllegalArgumentException("Geen Resultaat object.");
        }

        int id = ((Resultaat) obj).getId();
        String modulenaam = ((Resultaat) obj).getModulenaam();
        float resultaat = ((Resultaat) obj).getResultaat();
        char voldoende = ((Resultaat) obj).isVoldoende() ? 'T' : 'F';
        int persoonId = ((Resultaat) obj).getIdPersoon();
        
        if(modulenaam == null || resultaat == 0.0f || persoonId == 0){
            throw new IllegalArgumentException("Geen volledig resultaat!");
        }
        else{
            if (connection == null) {
                connection = DAO_Manager.initializeDB();
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate("update Resultaat set modulenaam = '" + modulenaam + "', resultaat = " + resultaat + ", voldoende = '" + voldoende + "', persoonId = "
                    + persoonId + " where id = " + id);
        }
    }

    @Override
    public POJO_Interface read(int id) throws SQLException {
        if (connection == null) {
            connection = DAO_Manager.initializeDB();
        }
        Statement statement = connection.createStatement();
        try{
        ResultSet rSet = statement.executeQuery("select modulenaam, resultaat, voldoende, persoonId from Resultaat where id = " + id);

        Resultaat resultaat = new Resultaat();
        rSet.next();
        resultaat.setModulenaam(rSet.getString(1));
        resultaat.setResultaat(rSet.getFloat(2));
        String voldoende = rSet.getString(3); // We hadden de voldoende toch als eeen char weergegeven in de database? Ik kan de getChar methode niet vinden 
        if (voldoende.equals("T")) {
            resultaat.setVoldoende(true);
        } else {
            resultaat.setVoldoende(false);
        }
        resultaat.setIdPersoon(rSet.getInt(4));
        resultaat.setId(id);

        // set adres
        return resultaat;
        }
        catch(Exception ex){
            return null;
        }
    }

    public int getResultaatId(int persoonId, String moduleNaam) throws SQLException {
        if (connection == null) {
            connection = DAO_Manager.initializeDB();
        }
        Statement statement = connection.createStatement();

        ResultSet rSet = statement.executeQuery("select id from Resultaat where persoonId = " + persoonId + " and moduleNaam = '" + moduleNaam + "'");

        Resultaat resultaat = new Resultaat();
        if(rSet.next()){
            return rSet.getInt(1);
        }
        return -1;
    }

    @Override
    public void delete(int id) throws SQLException {

        if (connection == null) {
            connection = DAO_Manager.initializeDB();
        }
        Statement statement = connection.createStatement();
        try{
            Resultaat resultaat = (Resultaat)read(id);
            resultaat.getModulenaam();
            statement.executeUpdate("delete from Resultaat where id = " + id);
        }
        catch(Exception ex){
            throw new IllegalArgumentException("Geen resultaat gevonden op dit ID");
        }
    }

    public Resultaat[] findResultaten(int idPersoon) throws SQLException {
        if (connection == null) {
            connection = DAO_Manager.initializeDB();
        }
        Statement statement = connection.createStatement();

        ResultSet rSet = statement.executeQuery("select id, modulenaam, resultaat, voldoende from Resultaat where persoonId = " + idPersoon);

        //ArrayList<Resultaat> list = new ArrayList<>();
        Resultaat[] lijst = new Resultaat[20];
        int i = 0;
        while (rSet.next()) {
            Resultaat resultaat = new Resultaat();
            resultaat.setId(rSet.getInt(1));
            resultaat.setModulenaam(rSet.getString(2));
            resultaat.setResultaat(rSet.getFloat(3));
            String voldoende = rSet.getString(4); // We hadden de voldoende toch als eeen char weergegeven in de database? Ik kan de getChar methode niet vinden 
            if (voldoende.equals("T")) {
                resultaat.setVoldoende(true);
            } else {
                resultaat.setVoldoende(false);
            }
            resultaat.setIdPersoon(idPersoon);
            lijst[i] = resultaat;
            i++;
            //list.add(resultaat);
        }

//        Resultaat[] resultaten = (Resultaat[])(list.toArray());
//        return resultaten;
        return lijst;

    }
}
