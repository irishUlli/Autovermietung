/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beispiel;

import Verbindung.DbVerbindung;
import Klassen.Fahrzeug;
import UI.UiAutovermietung;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author irish
 */
public class Autovermietung {

    private Fahrzeug Fahrzeug = null;

    // Objekte wurden in der Factory erzeugt und hier übergeben
    public Autovermietung(Fahrzeug Fahrzeug) {
        this.Fahrzeug = Fahrzeug;
        Init();
    }

    // Gibt alle Fahrzugspezifischen Daten zurück
    private void gibFahrzeugdaten(String kennzeichen) {

    }

//     Bekommt ein Fahrzeugobjekt welches in der DB gespeichert wird
    private void speichereFahrzeugdaten(String kennzeichen) {

    }

    private void Init() {
        //Abfrage Alle
        final DefaultTableModel ergebnisGesamt = DbVerbindung.Alle();
//       DefaultTableModel model = (DefaultTableModel) UiAutovermietung.

        final Fahrzeug fa = this.Fahrzeug;

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UiAutovermietung(fa).setVisible(true);  //ergebnisGesamt
            }
        });
    }

}
