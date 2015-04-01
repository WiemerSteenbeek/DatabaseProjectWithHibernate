package DAO;

import domein_klassen.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metamodel.domain.Component;

public class DAO_Resultaat implements DAOInterface {

    private Session session;

    DAO_Resultaat() {

    }

    @Override
    public void create(POJO_Interface obj) throws SQLException {
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        if (obj instanceof Resultaat) {
            Resultaat resultaat = (Resultaat) obj;
            if (resultaat.getModulenaam() == null || resultaat.getResultaat() <= 0) {
                throw new IllegalArgumentException("Geen volledig resultaat!");
            } else {
                try {
                    session.save(resultaat);
                } finally {
                    DAO_Manager.commitAndCloseSession(session);
                }
            }
        } else {
            throw new IllegalArgumentException("Geen Resultaat object.");
        }
    }

    @Override
    public void update(POJO_Interface obj) throws SQLException {
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        if (obj instanceof Resultaat) {
            Resultaat resultaat = (Resultaat) obj;
            if (resultaat.getModulenaam() == null || resultaat.getResultaat() <= 0) {
                throw new IllegalArgumentException("Geen volledig resultaat!");
            } else {
                try {

                    session.merge(resultaat);

                } finally {
                    DAO_Manager.commitAndCloseSession(session);
                }
            }
        } else {
            throw new IllegalArgumentException("Geen Resultaat object.");
        }
    }

    @Override
    public POJO_Interface read(int id) throws SQLException {
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        try {
            return (Resultaat) session.get(Resultaat.class, id);
        } catch (NullPointerException ex) {
            return null;
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    public int getResultaatId(int persoonId, String moduleNaam) throws SQLException {

        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        try {

            String sql = "SELECT r FROM Resultaat r WHERE r.persoonid = :persoonid AND r.modulenaam = :modulenaam";
            Query query = session.createQuery(sql).setParameter("persoonid", persoonId).setParameter("modulenaam", moduleNaam);
            try {
                Resultaat resultaat = (Resultaat) query.uniqueResult();
                if (resultaat != null) {
                    return resultaat.getId();
                } else {
                    return -1;
                }
            } catch (Exception ex) {
                List<Resultaat> list = query.list();
                return list.get(list.size() - 1).getId();
            }

        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    public Resultaat getResultaat(int persoonId, String moduleNaam) throws SQLException {

        Resultaat resultaat;
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        try {
             String sql = "SELECT r FROM Resultaat r WHERE r.persoonid = :persoonid AND r.modulenaam = :modulenaam";
            Query query = session.createQuery(sql).setParameter("persoonid", persoonId).setParameter("modulenaam", moduleNaam);
            try {
                resultaat = (Resultaat) query.uniqueResult();

                if (resultaat != null) {
                    return resultaat;
                } else {
                    return null;
                }
            } catch (Exception ex) {
                // Zou in principe niet moeten kunnen voorkomen (quick hack)
                return ((Resultaat) query.list().get(query.list().size() - 1));
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
            if (obj instanceof Resultaat) {
                Resultaat resultaat = (Resultaat) obj;
                session.delete(resultaat);
            }
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    public List<Resultaat> findResultaten(int persoonId) throws SQLException {

        Session session = DAO_Manager.getSession();

        try {
            session.beginTransaction();
            Criteria crit = session.createCriteria(Resultaat.class)
                    .add(Restrictions.eq("persoonid", persoonId));

            List<Resultaat> resultaten = crit.list();

            return resultaten;
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }
    
    public List<Resultaat> getAll() throws SQLException {
        List<Resultaat> resultaten = new ArrayList<Resultaat>();
        Session session = DAO_Manager.getSession();
        session.beginTransaction();
        try {

            Criteria crit = session.createCriteria(Resultaat.class);
            resultaten = crit.list();
        } finally {
            DAO_Manager.commitAndCloseSession(session);
            return resultaten;
        }
    }

}
