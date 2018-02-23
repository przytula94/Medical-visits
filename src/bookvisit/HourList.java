/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookvisit;

/**
 *
 * @author Paweł
 */
class HourList {
    
    private String IdGodzina;
    private String GodzinaWizyty;

    public HourList(String IdGodzina, String WyborGodzina) {
        this.IdGodzina = IdGodzina;
        this.GodzinaWizyty = WyborGodzina;
    }

    public String getIdGodzina() {
        return IdGodzina;
    }

    public String getWyborGodzina() {
        return GodzinaWizyty;
    }

    public void setIdGodzina(String IdGodzina) {
        this.IdGodzina = IdGodzina;
    }

    public void setWyborGodzina(String WyborGodzina) {
        this.GodzinaWizyty = WyborGodzina;
    }
    
    
    
    
    
}
