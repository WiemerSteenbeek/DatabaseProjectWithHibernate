package domein_klassen;

import java.util.*;
import javax.persistence.*;

@Entity
public class Persoon implements POJO_Interface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String voornaam;
    private String achternaam;
    private String tussenvoegsel;
    private String geboortedatum;
    @ManyToOne() @JoinColumn
    private Adres adres;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) @JoinColumn(name = "persoonid")
    private List<Resultaat> resultaten = new ArrayList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<Resultaat> getResultaten() {
        return resultaten;
    }

    public void setResultaten(List<Resultaat> resultaten) {
        this.resultaten = resultaten;
    }
    
    public String toString(){
        return(voornaam + " " + achternaam);
    }
    
    /*
     public int getIdAdres() {
     return idAdres;
     }

     public void setIdAdres(int idAdres) {
     this.idAdres = idAdres;
     }
     */
}
