/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Klassen.Fahrzeug;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author irish
 */
public interface I_Fahrzeug {
    
    Fahrzeug LadeFahrzeugdaten(String kennzeichen);
    
    Boolean SpeicherFahrzeugdaten();
    
    DefaultTableModel GibFahrzeugTabelle();
    
    DefaultTableModel GibAlleFahrzeugeTabelle();
    
    DefaultTableModel GibBauartTabelle();
    
    DefaultTableModel GibFahrzuegGeschichteTabelle(String kennzeichen);
    
    DefaultTableModel GibFahrzeugTabelle(String kennzeichen);
    
}
