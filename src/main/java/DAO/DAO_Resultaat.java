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

        try {
            session.beginTransaction();
            if ((obj instanceof Resultaat)) {
                session.save((Resultaat) obj);
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

            if ((obj instanceof Resultaat)) {
                session.merge((Resultaat) obj);
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
            return (Resultaat) session.get(Resultaat.class, id);
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    public int getResultaatId(int persoonId, String moduleNaam) throws SQLException {

        Session session = DAO_Manager.getSession();

        try {
            session.beginTransaction();
            String sql = "SELECT r FROM Resultaat r WHERE r.persoonId = :persoonId AND r.moduleNaam = :moduleNaam";
            Query query = session.createQuery(sql).setParameter("persoonId", persoonId).setParameter("moduleNaam", moduleNaam);
            Resultaat resultaat = (Resultaat) query.uniqueResult();

            if (resultaat != null) {
                return resultaat.getId();
            } else {
                return -1;
            }
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    public Resultaat getResultaat(int persoonId, String moduleNaam) throws SQLException {

        Session session = DAO_Manager.getSession();

        try {
            session.beginTransaction();
            String sql = "SELECT r FROM Resultaat r WHERE r.persoonId = :persoonId AND r.moduleNaam = :moduleNaam";
            Query query = session.createQuery(sql).setParameter("persoonId", persoonId).setParameter("moduleNaam", moduleNaam);
            Resultaat resultaat = (Resultaat) query.uniqueResult();

            if (resultaat != null) {
                return resultaat;
            } else {
                return null;
            }
        } finally {
            DAO_Manager.commitAndCloseSession(session);
        }
    }

    @Override
    public void delete(Object resultaat) throws Exception {
        Session session = DAO_Manager.getSession();
        try {
            if (resultaat instanceof Resultaat) {
                session.beginTransaction();
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
        
        /*
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
*/
    }
                
}
