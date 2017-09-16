/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klassen;

import Interfaces.I_PKW;
import Interfaces.I_LKW;
import Interfaces.I_Bauart;

/**
 *
 * @author irish
 */
public abstract class Bauart extends FahrzeugGrundausstattung implements I_Bauart, I_PKW, I_LKW {

    // Gibt entweder PKW oder LKW zurück
    
    //Speichere Bauart
    
    private String bauart;

    /**
     * @return the bauart
     */
    public String getBauart() {
        return bauart;
    }

    /**
     * @param bauart the bauart to set
     */
    public void setBauart(String bauart) {
        this.bauart = bauart;
    }
}
