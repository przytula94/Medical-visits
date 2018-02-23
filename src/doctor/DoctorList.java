/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor;

/**
 *
 * @author Paweł
 */
public class DoctorList {
    
   // private String ID;
    private String Imie;
    private String Nazwisko;
    private String NazwaSpecjalisty;
    private String NrGabinetu;
    private String Telefon;
    private String Email;

    public DoctorList( String Imie, String Nazwisko, String NazwaSpecjalisty, String NrGabinetu, String Telefon, String Email) {
       // this.ID = ID;
        this.Imie = Imie;
        this.Nazwisko = Nazwisko;
        this.NazwaSpecjalisty = NazwaSpecjalisty;
        this.NrGabinetu = NrGabinetu;
        this.Telefon = Telefon;
        this.Email = Email;
    }
    
    public String getImie() {
        return Imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public String getNazwaSpecjalisty() {
        return NazwaSpecjalisty;
    }

    public String getNrGabinetu() {
        return NrGabinetu;
    }

    public String getTelefon() {
        return Telefon;
    }

    public String getEmail() {
        return Email;
    }

    public void setImie(String Imie) {
        this.Imie = Imie;
    }

    public void setNazwisko(String Nazwisko) {
        this.Nazwisko = Nazwisko;
    }

    public void setNazwaSpecjalisty(String NazwaSpecjalisty) {
        this.NazwaSpecjalisty = NazwaSpecjalisty;
    }

    public void setNrGabinetu(String NrGabinetu) {
        this.NrGabinetu = NrGabinetu;
    }

    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}
