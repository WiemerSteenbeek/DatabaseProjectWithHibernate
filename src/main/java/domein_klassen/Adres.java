package domein_klassen;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Adres implements POJO_Interface, Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String straatnaam;
    private int huisnummer;
    private String toevoeging;
    private String postcode;
    private String woonplaats;

    @Override
    public boolean equals(Object adres2) {
        if (this.getStraatnaam().equals(((Adres) adres2).getStraatnaam()) && this.getHuisnummer().equals(((Adres) adres2).getHuisnummer())
                && this.getToevoeging().equals(((Adres) adres2).getToevoeging()) && this.getPostcode().equals(((Adres) adres2).getPostcode())
                && this.getWoonplaats().equals(((Adres) adres2).getWoonplaats())) {
            return true;
        } else {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public Integer getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

}
