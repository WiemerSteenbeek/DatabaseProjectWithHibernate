
import DAO.DAO_Manager;
import DAO.DAO_Persoon;
import domein_klassen.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class DAO_PersoonTest {

    static DAO_Manager manager;
    static Persoon persoon;

    public DAO_PersoonTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        persoon = new Persoon();
        persoon.setVoornaam("VoorNaam");
        persoon.setAchternaam("AchterNaam");
        persoon.setTussenvoegsel("TV");
        persoon.setGeboortedatum("11-11-1911");
        Adres adres = new Adres();
        adres.setStraatnaam("StraatNaam");
        adres.setPostcode("2344LL");
        adres.setHuisnummer(11);
        adres.setToevoeging("B");
        adres.setWoonplaats("Woonplaats");
        persoon.setAdres(adres);
        manager = new DAO_Manager();
        try {

            manager.getDAO_Persoon().create(persoon);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @AfterClass
    public static void tearDownClass() {
        try {
            manager.getDAO_Persoon().delete(persoon.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /*@Before
     public void setUp() {
        
     }*/
    //   @After
    //  public void tearDown() {
    //  }
    /**
     * Test of create method, of class DAO_Persoon.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Persoon persoon = new Persoon();
        persoon.setVoornaam("VoorNaam2");
        persoon.setAchternaam("AchterNaam2");
        persoon.setTussenvoegsel("TV2");
        persoon.setGeboortedatum("11-11-1912");
        Adres adres = new Adres();
        adres.setStraatnaam("StraatNaam2");
        adres.setPostcode("2344LM");
        adres.setHuisnummer(12);
        adres.setToevoeging("B2");
        adres.setWoonplaats("Woonplaats2");
        persoon.setAdres(adres);
        DAO_Persoon instance = manager.getDAO_Persoon();
        instance.create(persoon);

        Persoon result = (Persoon) instance.read(instance.getPersoonId("VoorNaam2", "AchterNaam2"));
        int persoonId = result.getId();
        assertNotNull("persoon, must not be null", persoon);
        assertNotNull("Result, must not be null", result);
        assertTrue("id, must be positive", persoonId >= 0);
        assertEquals("geboortedatum, must be equal", persoon.getGeboortedatum(), result.getGeboortedatum());
        assertEquals("tussenvoegsel, must be equal", persoon.getTussenvoegsel(), result.getTussenvoegsel());

        instance.delete(result);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCreateIllegalArgument() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Geen Persoon object.");
        try {
            manager.getDAO_Persoon().create(new Adres());
        } catch (SQLException ex) {
            Logger.getLogger(DAO_PersoonTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testCreateNull() throws SQLException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Geen volledig persoon!");
        Persoon persoon = new Persoon();
        manager.getDAO_Persoon().create(persoon);
    }

    /**
     * Test of update method, of class DAO_Persoon.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        persoon.setAchternaam("AchterNaam3");
        persoon.setVoornaam("Voornaam3");
        manager.getDAO_Persoon().update(persoon);

        Persoon result = (Persoon) manager.getDAO_Persoon().read(persoon.getId());

        assertNotNull("persoon, must not be null", persoon);
        assertNotNull("Result, must not be null", result);
        assertEquals("geboortedatum, must be equal", persoon.getGeboortedatum(), result.getGeboortedatum());
        assertEquals("tussenvoegsel, must be equal", persoon.getTussenvoegsel(), result.getTussenvoegsel());
        assertEquals("Voornaam must be equal", persoon.getVoornaam(), result.getVoornaam());
        assertEquals("Achternaam must be equel", persoon.getAchternaam(), result.getAchternaam());
        assertEquals("Adres must be equal", persoon.getAdres(), result.getAdres());
    }

    @Test
    public void testUpdateIllegalArgument() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Geen Persoon object.");
        try {
            manager.getDAO_Persoon().update(new Adres());
        } catch (SQLException ex) {
            Logger.getLogger(DAO_PersoonTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testUpdateNull() throws SQLException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Geen volledig persoon!");
        Persoon persoon2 = new Persoon();
        persoon2.setAdres((Adres) manager.getDAO_Adres().read(3));
        manager.getDAO_Persoon().update(persoon2);
    }

    /**
     * Test of read method, of class DAO_Persoon.
     */
    @Test
    public void testRead_int() throws Exception {
        System.out.println("read");

        Persoon expResult = persoon;
        Persoon result = (Persoon) manager.getDAO_Persoon().read(persoon.getId());
        assertNotNull("expResult, must not be null", expResult);
        assertNotNull("Result, must not be null", result);
        assertEquals("geboortedatum, must be equal", expResult.getGeboortedatum(), result.getGeboortedatum());
        assertEquals("tussenvoegsel, must be equal", expResult.getTussenvoegsel(), result.getTussenvoegsel());
        assertEquals("Voornaam must be equal", expResult.getVoornaam(), result.getVoornaam());
        assertEquals("Achternaam must be equel", expResult.getAchternaam(), result.getAchternaam());
        assertEquals("Adres must be equal", expResult.getAdres(), result.getAdres());

    }

    /**
     * Test of read method, of class DAO_Persoon.
     */
    @Test
    public void testRead_String_String() throws Exception {
        System.out.println("read2");
        Persoon expResult = persoon;
        Persoon result = (Persoon) manager.getDAO_Persoon().getPersoon(persoon.getVoornaam(), persoon.getAchternaam());
        assertNotNull("expResult, must not be null", expResult);
        assertNotNull("Result, must not be null", result);
        assertEquals("geboortedatum, must be equal", expResult.getGeboortedatum(), result.getGeboortedatum());
        assertEquals("tussenvoegsel, must be equal", expResult.getTussenvoegsel(), result.getTussenvoegsel());
        assertEquals("Voornaam must be equal", expResult.getVoornaam(), result.getVoornaam());
        assertEquals("Achternaam must be equel", expResult.getAchternaam(), result.getAchternaam());
        assertEquals("Adres must be equal", expResult.getAdres(), result.getAdres());
    }

    @Test
    public void testReadNotFound() throws SQLException {
        Persoon persoon = (Persoon) manager.getDAO_Persoon().read(-100);
        assertNull(persoon);
    }

    @Test
    public void testReadStringNotFound() throws SQLException {
        Persoon persoon = (Persoon) manager.getDAO_Persoon().getPersoon("Bob", "Klas");
        assertNull(persoon);
    }

    /**
     * Test of getPersoonId method, of class DAO_Persoon.
     */
    @Test
    public void testGetPersoonId() throws Exception {
        System.out.println("getPersoonId");
        int expResult = persoon.getId();
        int result = manager.getDAO_Persoon().getPersoonId(persoon.getVoornaam(), persoon.getAchternaam());
        assertEquals("PersoonId must be the same", expResult, result);
    }

    /**
     * Test of delete method, of class DAO_Persoon.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");

        Persoon persoon = new Persoon();
        persoon.setVoornaam("VoorNaam4");
        persoon.setAchternaam("AchterNaam4");
        persoon.setTussenvoegsel("TV4");
        persoon.setGeboortedatum("11-11-1914");
        Adres adres = new Adres();
        adres.setStraatnaam("StraatNaam4");
        adres.setPostcode("2344LP");
        adres.setHuisnummer(14);
        adres.setToevoeging("B4");
        adres.setWoonplaats("Woonplaats4");
        persoon.setAdres(adres);
        DAO_Persoon instance = manager.getDAO_Persoon();
        instance.create(persoon);

        int persoonId = instance.getPersoonId("VoorNaam4", "AchterNaam4");
        assertTrue("persoonId is positive", persoonId >= 0);
        instance.delete(persoon);
        persoonId = instance.getPersoonId("VoorNaam4", "AchterNaam4");
        assertEquals("persoonId = -1; persoon is deleted", persoonId, -1);

    }

   

}
