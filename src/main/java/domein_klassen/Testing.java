
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
            Persoon persoonRead = (Persoon) daoManager.getDAO_Persoon().getPersoon("keessie", "Blablablaasdasdasdasd");
            System.out.println(persoonRead);
            persoonRead.setAchternaam("Blablablaasdasdasdasd");
            daoManager.getDAO_Persoon().delete(persoonRead);
            
            System.out.println(daoManager.getDAO_Persoon().getAll().get(0).getAdres().getStraatnaam());
        } 
        catch (Exception ex){
            ex.printStackTrace();
        }  
    }
}
