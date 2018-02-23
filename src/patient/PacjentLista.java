/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient;

/**
 *
 * @author Paweł
 */
public class PacjentLista {

    //private int PacjentID;
    private String Imie;
    private String Nazwisko;
    private String Pesel;
    private String Ulica;
    private String Miejscowosc;
    private String KodPocztowy;
    private String Telefon;
    private String Email;
   // private String DataWizyty;

    public PacjentLista( String Imie, String Nazwisko, String Pesel, String Ulica, String Miejscowosc, String KodPocztowy, String Telefon, String Email) {
       // this.PacjentID = PacjentID;
        this.Imie = Imie;
        this.Nazwisko = Nazwisko;
        this.Pesel = Pesel;
        this.Ulica = Ulica;
        this.Miejscowosc = Miejscowosc;
        this.KodPocztowy = KodPocztowy;
        this.Telefon = Telefon;
        this.Email = Email;
        //this.DataWizyty = DataWizyty;
    }

   

    /**
     * @return the PacjentID
     */
   // public int getPacjentID() {
   //     return PacjentID;
  //  }

    /**
     * @param PacjentID the PacjentID to set
     */
   // public void setPacjentID(int PacjentID) {
   //     this.PacjentID = PacjentID;
   // }

    /**
     * @return the Imie
     */
    public String getImie() {
        return Imie;
    }

    /**
     * @param Imie the Imie to set
     */
    public void setImie(String Imie) {
        this.Imie = Imie;
    }

    /**
     * @return the Nazwisko
     */
    public String getNazwisko() {
        return Nazwisko;
    }

    /**
     * @param Nazwisko the Nazwisko to set
     */
    public void setNazwisko(String Nazwisko) {
        this.Nazwisko = Nazwisko;
    }

    /**
     * @return the Pesel
     */
    public String getPesel() {
        return Pesel;
    }

    /**
     * @param Pesel the Pesel to set
     */
    public void setPesel(String Pesel) {
        this.Pesel = Pesel;
    }

    /**
     * @return the Ulica
     */
    public String getUlica() {
        return Ulica;
    }

    /**
     * @param Ulica the Ulica to set
     */
    public void setUlica(String Ulica) {
        this.Ulica = Ulica;
    }

    /**
     * @return the Miejscowosc
     */
    public String getMiejscowosc() {
        return Miejscowosc;
    }

    /**
     * @param Miejscowosc the Miejscowosc to set
     */
    public void setMiejscowosc(String Miejscowosc) {
        this.Miejscowosc = Miejscowosc;
    }

    /**
     * @return the KodPocztowy
     */
    public String getKodPocztowy() {
        return KodPocztowy;
    }

    /**
     * @param KodPocztowy the KodPocztowy to set
     */
    public void setKodPocztowy(String KodPocztowy) {
        this.KodPocztowy = KodPocztowy;
    }

    /**
     * @return the Telefon
     */
    public String getTelefon() {
        return Telefon;
    }

    /**
     * @param Telefon the Telefon to set
     */
    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    
}
