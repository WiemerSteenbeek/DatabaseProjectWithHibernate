
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
        persoon.setVoornaam("keesasdsie");
        persoon.setAchternaam("Zon");
        persoon.setTussenvoegsel("van der");
        persoon.setGeboortedatum("11-11-1099");
        
       
        
        Resultaat resultaat = new Resultaat();
        resultaat.setModulenaam("OCAB");
        resultaat.setResultaat(9.5f);
        persoon.getResultaten().add(resultaat);
        
        Adres adres = new Adres();
        adres.setHuisnummer(101);
        adres.setPostcode("1234AB");
        adres.setStraatnaam("Lalasdal");
        adres.setWoonplaats("Hilversum");
        adres.setToevoeging("a");
        
        persoon.setAdres(adres);
        persoon.getResultaten().add(resultaat);
        
        DAO_Manager daoManager = new DAO_Manager();
        try {
            DAO_Persoon daoPersoon = daoManager.getDAO_Persoon();
            daoPersoon.create(persoon);
            DAO_Resultaat daoResultaat = daoManager.getDAO_Resultaat();
 
            Resultaat readResultaat = daoResultaat.getResultaat(persoon.getId(), "OCAB");
            readResultaat.setModulenaam("ACAB");
            daoResultaat.update(readResultaat);
            System.out.println(daoResultaat.getAll());
            daoResultaat.delete(readResultaat);
        } 
        catch (Exception ex){
            ex.printStackTrace();
        }  
    }
}
