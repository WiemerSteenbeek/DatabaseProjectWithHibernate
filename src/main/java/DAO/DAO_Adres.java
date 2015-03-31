package DAO;

import domein_klassen.*;

import java.sql.*;

import DAO.DAO_Manager;

public class DAO_Adres implements DAOInterface {

    private Connection connection;

    DAO_Adres(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(POJO_Interface obj) throws SQLException {
        if (!(obj instanceof Adres)) {
            throw new IllegalArgumentException("Geen Adres object.");
        }

        String straatnaam = ((Adres) obj).getStraatnaam();
        int huisnummer = ((Adres) obj).getHuisnummer();
        String toevoeging = ((Adres) obj).getToevoeging();
        String postcode = ((Adres) obj).getPostcode();
        String woonplaats = ((Adres) obj).getWoonplaats();
        
        if(straatnaam == null || huisnummer == 0 || postcode == null || woonplaats == null){
            throw new IllegalArgumentException("Geen volledig adres!");
            
        }
        else{
            if (connection == null) {
                connection = DAO_Manager.initializeDB();
            }

            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into Adres (straatnaam, huisnummer, toevoeging, postcode, woonplaats) values ('"
                    + straatnaam + "', '" + huisnummer + "', '" + toevoeging
                    + "', '" + postcode + "', '" + woonplaats + "')");
        }
    }

    @Override
    public void update(POJO_Interface obj) throws SQLException {
        if (!(obj instanceof Adres)) {
            throw new IllegalArgumentException("Geen Adres object.");
        }

        int id = ((Adres) obj).getId();
        String straatnaam = ((Adres) obj).getStraatnaam();
        int huisnummer = ((Adres) obj).getHuisnummer();
        String toevoeging = ((Adres) obj).getToevoeging();
        String postcode = ((Adres) obj).getPostcode();
        String woonplaats = ((Adres) obj).getWoonplaats();

        if(straatnaam == null || huisnummer == 0 || postcode == null || woonplaats == null){
            throw new IllegalArgumentException("Geen volledig adres!");
            
        }
        else{
            if (connection == null) {
                connection = DAO_Manager.initializeDB();
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate("update Adres set straatnaam = '" + straatnaam + "', huisnummer = '" + huisnummer + "', toevoeging = '" + toevoeging + "', postcode = '"
                    + postcode + "', woonplaats = '" + woonplaats + "' where id = " + id);
        }
    }

    @Override
    public POJO_Interface read(int id) throws SQLException {
        if (connection == null) {
            connection = DAO_Manager.initializeDB();
        }
        Statement statement = connection.createStatement();

        try {
            ResultSet rSet = statement.executeQuery("select straatnaam, huisnummer, toevoeging, postcode, woonplaats from Adres where id = " + id);

            Adres adres = new Adres();
            rSet.next();
            adres.setStraatnaam(rSet.getString(1));
            adres.setHuisnummer(rSet.getInt(2));
            adres.setToevoeging(rSet.getString(3));
            adres.setPostcode(rSet.getString(4));
            adres.setWoonplaats(rSet.getString(5));
            adres.setId(id); // toegevoegd

            return adres;
        } catch (Exception ex) {
            return null;
        }

    }

    public int getAdresId(String postcode, int huisnummer) throws SQLException {
        if (connection == null) {
            connection = DAO_Manager.initializeDB();
        }
        Statement statement = connection.createStatement();

        ResultSet rSet = statement
                .executeQuery("select id from Adres where postcode = '"
                        + postcode + "' and huisnummer = " + huisnummer);

        if (rSet.next()) {
            return rSet.getInt(1);
        }
        return -1;
    }


    public int getAdresId(String postcode, int huisnummer, String toevoeging) throws SQLException {

        if (connection == null) {
            connection = DAO_Manager.initializeDB();
        }
        Statement statement = connection.createStatement();

        ResultSet rSet = statement
                .executeQuery("select id from Adres where postcode = '"
                        + postcode + "' and huisnummer = " + huisnummer + " and toevoeging = '" + toevoeging + "'");

        if (rSet.next()) {
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
            Adres adres = (Adres)read(id);
            adres.getHuisnummer();
            statement.executeUpdate("delete from Adres where id = " + id);
        }
        catch(Exception ex){
            throw new IllegalArgumentException("Geen adres gevonden op dit ID");
        }
    }

}
