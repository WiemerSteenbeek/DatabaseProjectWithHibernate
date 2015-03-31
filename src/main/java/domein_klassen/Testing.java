
package domein_klassen;



import DAO.*;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;

/**
 *
 * @author Geert
 */
public class Testing {
    
    
    public static void main(String[] args){
        Persoon persoon = new Persoon();
        persoon.setVoornaam("kees2");
        persoon.setAchternaam("Zon");
        persoon.setTussenvoegsel("van der");
        persoon.setGeboortedatum("11-11-1099");
        
        Persoon persoon2 = new Persoon();
        persoon2.setVoornaam("Piet3");
        persoon2.setAchternaam("Zon");
        persoon2.setTussenvoegsel("van der");
        persoon2.setGeboortedatum("11-11-1099");
        
        Resultaat resultaat = new Resultaat();
        resultaat.setModulenaam("OCA");
        resultaat.setResultaat(9.5f);
        
        
        Adres adres = new Adres();
        adres.setHuisnummer(101);
        adres.setPostcode("1234AB");
        adres.setStraatnaam("Lalal");
        adres.setWoonplaats("Hilversum");
        adres.setToevoeging("a");
        
        persoon.setAdres(adres);
        persoon.getResultaten().add(resultaat);
        persoon2.setAdres(adres);
        
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory;
        StandardServiceRegistry serviceRegistry;
        
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session;
        
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(adres);
        session.save(resultaat);
        Persoon persoon3 = new Persoon();
        persoon3 = (Persoon)session.get(Persoon.class, 1);
        persoon3.setVoornaam("Henk");
        session.update(persoon3);
        session.save(persoon2);
        
        
        Adres adres5 = new Adres();
        adres5.setHuisnummer(101);
        adres5.setPostcode("1235AB");
        adres5.setStraatnaam("Laasdasdlal");
        adres5.setWoonplaats("Hilvasdersum");
        adres5.setToevoeging("b");
        
        Persoon persoon5 = new Persoon();
        persoon5.setVoornaam("Blas");
        persoon5.setAchternaam("Blas");
        persoon5.setTussenvoegsel("van des");
        persoon5.setGeboortedatum("11-11-1214");
        persoon5.setAdres(adres5);
      
        
        session.getTransaction().commit();
        
        session.close();
        try {

            DAO_Manager daoManager = new DAO_Manager();
            DAO_Manager.getSessionFactory();
            DAO_Persoon daoPersoon = daoManager.getDAO_Persoon();
            daoPersoon.create(persoon5);
        } catch (SQLException ex) {
            System.out.println("Gaat mis");
        }
        
        try{
            DAO_Manager daoManager = new DAO_Manager();
            DAO_Manager.getSessionFactory();
            DAO_Persoon daoPersoon = daoManager.getDAO_Persoon();
            System.out.println(daoPersoon.getPersoonId("Pietje", "Zon"));
            
        }catch (SQLException ex) {
            System.out.println("Gaat iets anders mis");
        }
        
    }
}
