package DAO;

import domein_klassen.*;

import java.sql.*;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;

public class DAO_Persoon{
    private Session session;

    DAO_Persoon() {
        
    }

   
    public void create(POJO_Interface obj) throws SQLException {
        if ((obj instanceof Persoon)) {
            session = DAO_Manager.getSessionFactory().openSession();
            Persoon persoon = (Persoon) obj;

            if (persoon.getVoornaam() == null || persoon.getAchternaam() == null || persoon.getGeboortedatum() == null) {
                throw new IllegalArgumentException("Geen volledig persoon!");
            } else if (persoon.getAdres() == null) {
                throw new IllegalArgumentException("Adres is null");
            } else {
                Adres adres = persoon.getAdres();
                session.beginTransaction();
                session.save(adres);
                session.save(persoon);
                session.getTransaction().commit();
                session.close();
            }
        } else {
            throw new IllegalArgumentException("Geen Persoon object.");

        }
    } 

        
        
    
   
    
  
    public void update(POJO_Interface obj) throws SQLException {

        if ((obj instanceof Persoon)) {
            session = DAO_Manager.getSessionFactory().openSession();
            Persoon persoon = (Persoon) obj;

            if (persoon.getVoornaam() == null || persoon.getAchternaam() == null || persoon.getGeboortedatum() == null) {
                throw new IllegalArgumentException("Geen volledig persoon!");
            } else if (persoon.getAdres() == null) {
                throw new IllegalArgumentException("Adres is null");
            } else {
                session.beginTransaction();
                int persoonsId = getPersoonId(persoon.getVoornaam(), persoon.getAchternaam());
                
                
                
                
                session.update(persoon2);
                
                
                session.getTransaction().commit();
                session.close();
            }
        } else {
            throw new IllegalArgumentException("Geen Persoon object.");

        }
    }
        
        
         
         
         
        /*
        if (!(obj instanceof Persoon)) {
            throw new IllegalArgumentException("Geen Persoon object.");
        }
        
        int id = ((Persoon) obj).getId();
        String voorNaam = ((Persoon) obj).getVoornaam();
        String achterNaam = ((Persoon) obj).getAchternaam();
        String tussenVoegsel = ((Persoon) obj).getTussenvoegsel();
        String geboorteDatum = ((Persoon) obj).getGeboortedatum();
        int adresId = ((Persoon) obj).getAdres().getId();
              
       
        if (voorNaam == null || achterNaam == null || geboorteDatum == null) {
            throw new IllegalArgumentException("Geen volledig persoon!");
        } else {
            if (connection == null) {
                connection = DAO_Manager.initializeDB();
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate("update Persoon set voorNaam = '" + voorNaam + "', achterNaam = '" + achterNaam + "', tussenvoegsel = '" + tussenVoegsel + "', geboortedatum = '"
                    + geboorteDatum + "', adresId = '" + adresId + "' where id = " + id);

        }
        }
    
    
    @Override
    public POJO_Interface read(int id) throws SQLException {
        if (connection == null) {
            connection = DAO_Manager.initializeDB();
        }
        Statement statement = connection.createStatement();

        try {
            ResultSet rSet = statement.executeQuery("select voorNaam, achterNaam, tussenvoegsel, geboortedatum, adresId from Persoon where id = " + id);

            Persoon persoon = new Persoon();
            rSet.next();
            persoon.setVoornaam(rSet.getString(1));
            persoon.setAchternaam(rSet.getString(2));
            persoon.setId(id);
            persoon.setTussenvoegsel(rSet.getString(3));
            persoon.setGeboortedatum(rSet.getString(4));
            DAO_Adres daoAdres = new DAO_Adres(connection);
            persoon.setAdres((Adres) (daoAdres.read(rSet.getInt(5))));
            DAO_Resultaat daoResultaat = new DAO_Resultaat(connection);
            //persoon.setResultaten(daoResultaat.findResultaten(id));

            return persoon;
        } catch (Exception ex) {
            return null;
        }

    }

    public POJO_Interface read(String voorNaam, String achterNaam) throws SQLException {
        if (connection == null) {
            connection = DAO_Manager.initializeDB();
        }
        Statement statement = connection.createStatement();
        try {

            ResultSet rSet = statement.executeQuery("select id, tussenvoegsel, geboortedatum, adresId from Persoon where voorNaam = '" + voorNaam + "' and achterNaam = '" + achterNaam + "'");

            Persoon persoon = new Persoon();
            rSet.next();
            persoon.setId(rSet.getInt(1));

            persoon.setTussenvoegsel(rSet.getString(2));
            persoon.setGeboortedatum(rSet.getString(3));
            DAO_Adres daoAdres = new DAO_Adres(connection);
            persoon.setAdres((Adres) (daoAdres.read(rSet.getInt(4))));
            DAO_Resultaat daoResultaat = new DAO_Resultaat(connection);
           // persoon.setResultaten(daoResultaat.findResultaten(persoon.getId()));
            persoon.setVoornaam(voorNaam);
            persoon.setAchternaam(achterNaam);
            return persoon;

        } catch (Exception ex) {
            return null;
        }

    }
    */

  
    public int getPersoonId(String voornaam, String achternaam) throws SQLException {
        
        Persoon persoon = null;
        String sql = "SELECT p FROM Persoon p WHERE p.voornaam = :voornaam AND p.achternaam = :achternaam";
        Query query = DAO_Manager.getSessionFactory().openSession().createQuery(sql).setParameter("voornaam", voornaam).setParameter("achternaam", achternaam);
        persoon = (Persoon) query.uniqueResult();

        if (persoon != null) {
            return persoon.getId();
        } else {
            return -1;
        }
        
        
     
 }
    public Persoon getPersoon(String voornaam, String achternaam) throws SQLException {
        Persoon persoon = null;
        String sql = "SELECT p FROM Persoon p WHERE p.voornaam = :voornaam AND p.achternaam = :achternaam";
        Query query = DAO_Manager.getSessionFactory().openSession().createQuery(sql).setParameter("voornaam", voornaam).setParameter("achternaam", achternaam);
        persoon = (Persoon) query.uniqueResult();

        if (persoon != null) {
            return persoon;
        } else {
            return null;
        }
    }
    /*
    
    public ArrayList<Persoon> getAll() throws SQLException {
        ArrayList<Persoon> personen = new ArrayList<Persoon>();
        if (connection == null) {
            connection = DAO_Manager.initializeDB();
        }

        Statement statement = connection.createStatement();
        ResultSet rSet = statement.executeQuery("select id, voorNaam, achterNaam, tussenvoegsel, geboortedatum, adresId from Persoon ");
        while (rSet.next()) {
            Persoon persoon = new Persoon();
            persoon.setId(rSet.getInt(1));
            persoon.setVoornaam(rSet.getString(2));
            persoon.setAchternaam(rSet.getString(3));

            persoon.setTussenvoegsel(rSet.getString(4));
            persoon.setGeboortedatum(rSet.getString(5));
            DAO_Adres daoAdres = new DAO_Adres(connection);
            persoon.setAdres((Adres) (daoAdres.read(rSet.getInt(6))));
            personen.add(persoon);
        }
        return personen;

    }
    
    @Override
    public void delete(int id) throws SQLException {

        if (connection == null) {
            connection = DAO_Manager.initializeDB();
        }
        Statement statement = connection.createStatement();
        try {
            Persoon persoon = (Persoon) read(id);
            persoon.getAchternaam();
            statement.executeUpdate("delete from Persoon where id = " + id);

        } catch (Exception ex) {
            throw new IllegalArgumentException("Geen persoon gevonden op dit ID");
        }

    }
*/
    }
