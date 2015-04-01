package DAO;

import domein_klassen.*;

import java.sql.*;
import java.util.ArrayList;
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

        try {
            session.beginTransaction();
            if ((obj instanceof Adres)) {
                session.save((Adres) obj);
            }
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    @Override
    public void update(POJO_Interface obj) throws SQLException {
        Session session = DAO_Manager.getSession();

        try {
            session.beginTransaction();

            if ((obj instanceof Adres)) {
                session.merge((Adres) obj);
            }
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    @Override
    public POJO_Interface read(int id) throws SQLException {
        Session session = DAO_Manager.getSession();

        try {
            session.beginTransaction();
            return (Adres) session.get(Adres.class, id);
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    public int getAdresId(String postcode, int huisnummer) throws SQLException {

        Session session = DAO_Manager.getSession();

        try {
            session.beginTransaction();
            String sql = "SELECT a FROM Adres p WHERE p.postcode = :postcode AND a.huisnummer = :huisnummer";
            Query query = session.createQuery(sql).setParameter("postcode", postcode).setParameter("huisnummer", huisnummer);
            Adres adres = (Adres) query.uniqueResult();

            if (adres != null) {
                return adres.getId();
            } else {
                return -1;
            }
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    public Adres getAdres(String postcode, int huisnummer) throws SQLException {

        Session session = DAO_Manager.getSession();

        try {
            session.beginTransaction();
            String sql = "SELECT a FROM Adres p WHERE p.postcode = :postcode AND a.huisnummer = :huisnummer";
            Query query = session.createQuery(sql).setParameter("postcode", postcode).setParameter("huisnummer", huisnummer);
            Adres adres = (Adres) query.uniqueResult();

            if (adres != null) {
                return adres;
            } else {
                return null;
            }
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    @Override
    public void delete(Object adres) throws Exception {
        Session session = DAO_Manager.getSession();
        try {
            if (adres instanceof Adres) {
                session.beginTransaction();
                session.delete(adres);
            }
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }
}
