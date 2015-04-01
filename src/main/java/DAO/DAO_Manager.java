/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DAO_Manager {

    private DAO_Persoon daoPersoon;
    private DAO_Adres daoAdres;
    private DAO_Resultaat daoResultaat;
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry serviceRegistry;

    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory != null) {
            return sessionFactory;
        } else {
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }
    }

    public static Session getSession() {
        Session session;
        
        if (sessionFactory != null) {
            session = sessionFactory.openSession();
            return session;
        } else {
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            session = sessionFactory.openSession();
            return session;
        }
    }

    public static void commitAndCloseSession(Session session) {
        if(session.isOpen()){
            session.getTransaction().commit();
            session.close();
        }
    }

    public DAO_Persoon getDAO_Persoon() {
        daoPersoon = new DAO_Persoon();
        return daoPersoon;
    }

    public DAO_Adres getDAO_Adres() {
        daoAdres = new DAO_Adres();
        return daoAdres;
    }

    public DAO_Resultaat getDAO_Resultaat() {
        daoResultaat = new DAO_Resultaat();
        return daoResultaat;
    }
}
