package DAO;

import domein_klassen.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metamodel.domain.Component;

public class DAO_Persoon implements DAOInterface {

    DAO_Persoon() {

    }

    @Override
    public void create(POJO_Interface obj) throws SQLException {
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        if (obj instanceof Persoon) {
            Persoon persoon = (Persoon) obj;
            if (persoon.getVoornaam() == null || persoon.getAchternaam() == null || persoon.getGeboortedatum() == null) {
                throw new IllegalArgumentException("Geen volledig persoon!");
            } else if (persoon.getAdres() == null) {
                throw new IllegalArgumentException("Adres is null");
            } else {
                try {

                    session.save(persoon);
                    session.save(persoon.getAdres());
                } finally {
                    DAO_Manager.commitAndCloseSession(session);
                }
            }

        } else {
            throw new IllegalArgumentException("Geen Persoon object.");
        }

    }

    @Override
    public void update(POJO_Interface obj) throws SQLException {
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        if (obj instanceof Persoon) {
            Persoon persoon = (Persoon) obj;
            if (persoon.getVoornaam() == null || persoon.getAchternaam() == null || persoon.getGeboortedatum() == null) {
                throw new IllegalArgumentException("Geen volledig persoon!");
            } else if (persoon.getAdres() == null) {
                throw new IllegalArgumentException("Adres is null");
            } else {
                try {

                    session.merge(persoon);
                    session.merge(persoon.getAdres());
                } finally {
                    DAO_Manager.commitAndCloseSession(session);
                }
            }
        } else {
            throw new IllegalArgumentException("Geen Persoon object.");
        }
    }

    @Override
    public POJO_Interface read(int id) throws SQLException {
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        try {

            return (Persoon) session.get(Persoon.class, id);
        } catch (NullPointerException ex) {
            return null;
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    public int getPersoonId(String voornaam, String achternaam) throws SQLException {

        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        try {

            String sql = "SELECT p FROM Persoon p WHERE p.voornaam = :voornaam AND p.achternaam = :achternaam";
            Query query = session.createQuery(sql).setParameter("voornaam", voornaam).setParameter("achternaam", achternaam);
            try {
                Persoon persoon = (Persoon) query.uniqueResult();
                if (persoon != null) {
                    return persoon.getId();
                } else {
                    return -1;
                }
            } catch (Exception ex) {
                List <Persoon>list = query.list();
                return list.get(list.size()-1).getId();               
            }

        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    public Persoon getPersoon(String voornaam, String achternaam) throws SQLException {
        Persoon persoon;
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        try {

            String sql = "SELECT p FROM Persoon p WHERE p.voornaam = :voornaam AND p.achternaam = :achternaam";
            Query query = session.createQuery(sql).setParameter("voornaam", voornaam).setParameter("achternaam", achternaam);
            try {
                persoon = (Persoon) query.uniqueResult();

                if (persoon != null) {
                    return persoon;
                } else {
                    return null;
                }
            } catch (Exception ex) {
                // Zou in principe niet moeten kunnen voorkomen (quick hack)
                return ((Persoon) query.list().get(query.list().size()-1));

            }

        } finally {
            DAO_Manager.commitAndCloseSession(session);

        }
    }

    @Override
    public void delete(Object obj) throws Exception {
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        try {
            if (obj instanceof Persoon) {
                Persoon persoon = (Persoon) obj;
                session.delete(persoon);
            }
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    public List<Persoon> getAll() throws SQLException {
        List<Persoon> personen = new ArrayList<Persoon>();
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        try {

            Criteria crit = session.createCriteria(Persoon.class);
            personen = crit.list();
        } finally {
            DAO_Manager.commitAndCloseSession(session);
            return personen;
        }
    }

}
