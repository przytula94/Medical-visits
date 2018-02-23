/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visit;

/**
 *
 * @author Paweł
 */
public class VisitList {
    
    private String Imie;
    private String Nazwisko;
    private String Pesel;
    private String DataWizyty;
    private String GodzinaWizyty;
    private String Lekarz;

    public VisitList(String Imie, String Nazwisko, String Pesel, String DataWizyty, String GodzinaWizyty, String Lekarz) {
        this.Imie = Imie;
        this.Nazwisko = Nazwisko;
        this.Pesel = Pesel;
        this.DataWizyty = DataWizyty;
        this.GodzinaWizyty = GodzinaWizyty;
        this.Lekarz = Lekarz;
    }

    public String getImie() {
        return Imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public String getPesel() {
        return Pesel;
    }

    public String getDataWizyty() {
        return DataWizyty;
    }

    public String getGodzinaWizyty() {
        return GodzinaWizyty;
    }

    public String getLekarz() {
        return Lekarz;
    }

    public void setImie(String Imie) {
        this.Imie = Imie;
    }

    public void setNazwisko(String Nazwisko) {
        this.Nazwisko = Nazwisko;
    }

    public void setPesel(String Pesel) {
        this.Pesel = Pesel;
    }

    public void setDataWizyty(String DataWizyty) {
        this.DataWizyty = DataWizyty;
    }

    public void setGodzinaWizyty(String GodzinaWizyty) {
        this.GodzinaWizyty = GodzinaWizyty;
    }

    public void setLekarz(String Lekarz) {
        this.Lekarz = Lekarz;
    }
    
    
    
    
}
