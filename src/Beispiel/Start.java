/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beispiel;

import Klassen.Fahrzeug;
import Verbindung.DbVerbindung;

/**
 *
 * @author irish
 */
public class Start {
    
    public static void main(String args[])
    {
        //Splashscreen
        //Vesionierung / Datenbankaufbau?
        
        //*** NEUE DB ANLEGEN *****
        //DbVerbindung.NeueDbErzeugen();
        
        
        Fahrzeug Fahrzeug;
        Fahrzeug = new Fahrzeug();
        
        //Objekte aus der Factory übergeben wenn UI verwendet werden soll
       Autovermietung Av = new Autovermietung(Fahrzeug);
       
    }
    
}
