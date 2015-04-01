package DAO;

import domein_klassen.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metamodel.domain.Component;

public class DAO_Adres implements DAOInterface {

    private Session session;

    DAO_Adres() {

    }

    @Override
    public void create(POJO_Interface obj) throws SQLException {
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        if (obj instanceof Adres) {
            Adres adres = (Adres) obj;
            if (adres.getPostcode() == null || adres.getHuisnummer() == 0) {
                throw new IllegalArgumentException("Geen volledig adres!");
            } else {
                try {
                    session.save(adres);
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
        if (obj instanceof Adres) {
            Adres adres = (Adres) obj;
            if (adres.getPostcode() == null || adres.getHuisnummer() == 0) {
                throw new IllegalArgumentException("Geen volledig persoon!");
            } else {
                try {

                    session.merge(adres);

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
            return (Adres) session.get(Adres.class, id);
        } catch (NullPointerException ex) {
            return null;
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    public int getAdresId(String postcode, int huisnummer) throws SQLException {

        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        try {

            String sql = "SELECT a FROM Adres a WHERE a.postcode = :postcode AND a.huisnummer = :huisnummer";
            Query query = session.createQuery(sql).setParameter("postcode", postcode).setParameter("huisnummer", huisnummer);
            try {
                Adres adres = (Adres) query.uniqueResult();
                if (adres != null) {
                    return adres.getId();
                } else {
                    return -1;
                }
            } catch (Exception ex) {
                List<Adres> list = query.list();
                return list.get(list.size() - 1).getId();
            }

        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }

    }

    public Adres getAdres(String postcode, int huisnummer) throws SQLException {
        Adres adres;
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        try {
            String sql = "SELECT a FROM Adres a WHERE a.postcode = :postcode AND a.huisnummer = :huisnummer";
            Query query = session.createQuery(sql).setParameter("postcode", postcode).setParameter("huisnummer", huisnummer);
            try {
                adres = (Adres) query.uniqueResult();

                if (adres != null) {
                    return adres;
                } else {
                    return null;
                }
            } catch (Exception ex) {
                // Zou in principe niet moeten kunnen voorkomen (quick hack)
                return ((Adres) query.list().get(query.list().size() - 1));
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
            if (obj instanceof Adres) {
                Adres adres = (Adres) obj;
                session.delete(adres);
            }
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    public List<Adres> getAll() throws SQLException {
        List<Adres> adressen = new ArrayList<Adres>();
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        try {

            Criteria crit = session.createCriteria(Adres.class);
            adressen = crit.list();
        } finally {
            DAO_Manager.commitAndCloseSession(session);
            return adressen;
        }
    }
}
