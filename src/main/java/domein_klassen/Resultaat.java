package domein_klassen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Resultaat implements POJO_Interface {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String modulenaam;
    private float resultaat;
    private boolean voldoende;
    @JoinColumn
    private Integer persoonid;

   /* @Override
    public boolean equals(Object resultaat2) {
        if (this.getModulenaam().equals(((Resultaat) resultaat2).getModulenaam()) && this.getResultaat().equals(((Resultaat) resultaat2).getResultaat())
                && this.getIdPersoon().equals(((Resultaat) resultaat2).getIdPersoon())) {
            return true;
        } else {
            return false;
        }
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModulenaam() {
        return modulenaam;
    }

    public void setModulenaam(String modulenaam) {
        this.modulenaam = modulenaam;
    }

    public Float getResultaat() {
        return resultaat;
    }

    public void setResultaat(float resultaat) {
        this.resultaat = resultaat;
        if(resultaat >= 5.5f){
            setVoldoende(true);
        }
        else{
            setVoldoende(false);
        }
    }

    public boolean isVoldoende() {
        return voldoende;
    }

    public void setVoldoende(boolean voldoende) {
        this.voldoende = voldoende;
    }

    public Integer getIdPersoon() {
        return persoonid;
    }

    public void setIdPersoon(int idPersoon) {
        this.persoonid = idPersoon;
    }
    
    public String toString(){
        return("Resultaat: " + modulenaam + ", " + resultaat);
    }
    
}
