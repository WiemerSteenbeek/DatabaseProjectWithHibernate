import DAO.DAO_Manager;
import java.sql.SQLException;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domein_klassen.Adres;
import domein_klassen.Persoon;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class DAO_AdresTest {

    static DAO_Manager manager;

    public DAO_AdresTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        manager = new DAO_Manager();

    }

    @AfterClass
    public static void tearDownClass() {
     
        }
    

    @Test
    public void testCreate() throws SQLException {
        Adres adres = new Adres();

        adres.setStraatnaam("Bloemensingel");
        adres.setHuisnummer(1312);
        adres.setToevoeging("b");
        adres.setPostcode("2353AC");
        adres.setWoonplaats("Utreg");

        manager.getDAO_Adres().create(adres);

        int adresId = manager.getDAO_Adres().getAdresId(adres.getPostcode(), adres.getHuisnummer(), adres.getToevoeging());
        System.out.println(adresId);
        Adres adres2 = (Adres) manager.getDAO_Adres().read(adresId);
        assertNotNull("Address from database not null?", adres2);
        System.out.println(adres2.getStraatnaam());
        assertEquals(adres, adres2);

    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void testCreateIllegalArgument() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Geen Adres object.");
        try {
            manager.getDAO_Adres().create(new Persoon());
        } catch (SQLException ex) {
            Logger.getLogger(DAO_AdresTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    @Test
    public void testCreateNull() throws SQLException{
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Geen volledig adres!");
        Adres adres = new Adres();
        manager.getDAO_Adres().create(adres);
    }

    @Test
    public void testUpdate() throws SQLException {
        Adres adres = new Adres();

        adres.setStraatnaam("Bloemensingel");
        adres.setHuisnummer(1312);
        adres.setToevoeging("b");
        adres.setPostcode("2353AC");
        adres.setWoonplaats("Utreg");

        manager.getDAO_Adres().create(adres);

        int adresId = manager.getDAO_Adres().getAdresId(adres.getPostcode(), adres.getHuisnummer(), adres.getToevoeging());
        Adres adres2 = (Adres) manager.getDAO_Adres().read(adresId);
        assertNotNull("Address from database not null?", adres2);
        adres2.setHuisnummer(666);
        adres2.setStraatnaam("Oeleboelestraat");
        adres2.setWoonplaats("Schubbekutteveen");
        adres2.setToevoeging("");
        adres2.setPostcode("1234AB");
        manager.getDAO_Adres().update(adres2);
        Adres adres3 = (Adres)manager.getDAO_Adres().read(adresId);
        assertTrue(adres != adres3);
        
    }
    
    @Test
    public void testUpdateIllegalArgument() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Geen Adres object.");
        try {
            manager.getDAO_Adres().update(new Persoon());
        } catch (SQLException ex) {
            Logger.getLogger(DAO_AdresTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      @Test
    public void testUpdateNull() throws SQLException{
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Geen volledig adres!");
        Adres adres = new Adres();
        manager.getDAO_Adres().update(adres);
    }
    
    @Test
    public void testRead() throws SQLException {
        Adres adres = new Adres();

        adres.setStraatnaam("Bloemensingel");
        adres.setHuisnummer(1312);
        adres.setToevoeging("b");
        adres.setPostcode("2353AC");
        adres.setWoonplaats("Utreg");

        manager.getDAO_Adres().create(adres);

        int adresId = manager.getDAO_Adres().getAdresId(adres.getPostcode(), adres.getHuisnummer(), adres.getToevoeging());
        Adres adres2 = (Adres) manager.getDAO_Adres().read(adresId);
        assertNotNull("Straatnaam from database not null?", adres2.getStraatnaam());
        assertNotNull("Huisnummer  from database not null?", adres2.getHuisnummer());
        assertNotNull("Toevoeging from database not null?", adres2.getToevoeging());
        assertNotNull("Postcode from database not null?", adres2.getPostcode());
        assertNotNull("Woonplaats from database not null?", adres2.getWoonplaats());
        
        assertEquals(adres, adres2);
        
    }
    
    @Test
    public void testReadNotFound() throws SQLException {
        Adres adres = (Adres)manager.getDAO_Adres().read(-100);
        assertNull(adres);
    }
    
    @Test
    public void testDelete() throws Exception {
        Adres adres = new Adres();

        adres.setStraatnaam("Bloemensingel");
        adres.setHuisnummer(1312);
        adres.setToevoeging("b");
        adres.setPostcode("2353AC");
        adres.setWoonplaats("Utreg");

        manager.getDAO_Adres().create(adres);

        int adresId = manager.getDAO_Adres().getAdresId(adres.getPostcode(), adres.getHuisnummer(), adres.getToevoeging());
        assertTrue("adresId is positive", adresId >= 0);
        
        manager.getDAO_Adres().delete(adres);
        Adres adres2 = (Adres)manager.getDAO_Adres().read(adresId);
        assertNull(adres2);
        
    }
    /*
    @Test
    public void testDeleteNotFound() {
        thrown.expect(IllegalArgumentException.class);
        
        thrown.expectMessage("Geen adres gevonden op dit ID");
        try {
            manager.getDAO_Adres().delete(-100);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_AdresTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
}
