/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klassen;

import Klassen.Bauart;
import Interfaces.I_Fahrzeug;
import Verbindung.DbVerbindung;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author irish
 */
public class Fahrzeug extends Bauart implements I_Fahrzeug {

    DefaultTableModel tabelle;

    public void EigenschaftenNeuLaden(String kennzeichen) throws ParseException {
        String kz = kennzeichen.toUpperCase().trim();
        this.setKennzeichen(kz);

        DefaultTableModel fd = this.GibFahrzeugTabelle(kz);

////      Get all the table data
//     Vector data = fd.getDataVector();
//     
//     
//     for(Object x : data.toArray())
//     {
//         System.out.println(x);
//     }
        for (int row = 0; row < fd.getRowCount(); row++) {
            try {
//	    for(int col = 0;col < fd.getColumnCount();col++) {
                // Hier sollt über die Spaltennamen zugegriffen werden nicht über die Position!!  vielleicht Später
                this.setKennzeichen(fd.getValueAt(0, 0).toString());
                this.setSitzplaetze(Integer.parseInt(fd.getValueAt(0, 1).toString()));
                this.setBauart(fd.getValueAt(0, 2).toString());
                this.setMarke(fd.getValueAt(0, 3).toString());
                this.setKm1(Integer.parseInt(fd.getValueAt(0, 4).toString()));
                this.setKm2(Integer.parseInt(fd.getValueAt(0, 5).toString()));
                this.setInspInterv(Integer.parseInt(fd.getValueAt(0, 6).toString()));

                this.setInspKm(Integer.parseInt(fd.getValueAt(0, 7).toString()));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date convertedCurrentDate = (Date) sdf.parse(fd.getValueAt(0, 8).toString());
                this.setInspDat(convertedCurrentDate);

                convertedCurrentDate = (Date) sdf.parse(fd.getValueAt(0, 9).toString());
                this.setMietbeginn(convertedCurrentDate);

                convertedCurrentDate = (Date) sdf.parse(fd.getValueAt(0, 10).toString());
                this.setVorrRueckgabe(convertedCurrentDate);

                this.setKmRueckgabe(Integer.parseInt(fd.getValueAt(0, 11).toString()));

                convertedCurrentDate = (Date) sdf.parse(fd.getValueAt(0, 12).toString());
                this.setUpdate(convertedCurrentDate);
//                  this.setGeloescht(Boolean.parseBoolean(fd.getValueAt(0, 16).toString()));
                this.setNutzlast(Integer.parseInt(fd.getValueAt(0, 13).toString()));
                this.setMietePTag(Double.parseDouble(fd.getValueAt(0, 14).toString()));
                this.setZustand(Boolean.parseBoolean(fd.getValueAt(0, 15).toString()));
//	    }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Test Titel", JOptionPane.OK_CANCEL_OPTION);
            }
        }

        this.setBauart(this.BauartausDB(kz));
//     this.setNutzlast();

        // Fahrzeug history laden
    }

    public String BauartausDB(String kz) {
        String sql = String.format("SELECT BAUART.B_BAUART FROM FAHRZEUGDATEN"
                + " INNER JOIN FAHRZEUGIDENTIVIKATION on FAHRZEUGDATEN.F_KENNZEICHEN = FAHRZEUGIDENTIVIKATION.F_ID"
                + " INNER JOIN BAUART on FAHRZEUGDATEN.F_BAUART = BAUART.B_ID"
                + " WHERE FAHRZEUGIDENTIVIKATION.F_KENNZEICHEN = '%s'", kz);

        return DbVerbindung.SQLMultiReturnString(sql);
    }

    public List<String> LadeAlleKennzeichen() {
        ArrayList kennzeichen;
        kennzeichen = new ArrayList();
        DefaultTableModel tm = new DefaultTableModel();
        String sqlAbfrage = String.format("SELECT FAHRZEUGIDENTIVIKATION.F_KENNZEICHEN FROM `FAHRZEUGIDENTIVIKATION`");
        tm = DbVerbindung.SQLMulti(sqlAbfrage);

        if (tm.getRowCount() > 0) {
            for (int i = 0; i < tm.getRowCount(); i++) {
                kennzeichen.add(tm.getValueAt(i, 0));
            }
        }
        return kennzeichen;
    }

    @Override
    public String gibBauart() {
        // Frag die Bauart aus der DB ab
        this.setBauart("Bauart");  //Test!
        return "bauart";
    }

    @Override
    public boolean speichereBauart(String bauart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Fahrzeug LadeFahrzeugdaten(String kennzeichen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean SpeicherFahrzeugdaten() {
        //FrageBauart von der DB ab
        //this.getBauart   to DB

        return true;
    }

    @Override
    public DefaultTableModel GibFahrzeugTabelle() {
        //mit UI
        DefaultTableModel tabelle;
        tabelle = new DefaultTableModel();
        // frage alle Fahrzeug spezifischen Daten ab; 
        return tabelle;
    }

    @Override
    public DefaultTableModel GibAlleFahrzeugeTabelle() {
        //Mit UI
        String sqlAbfrage = String.format("SELECT FAHRZEUGIDENTIVIKATION.F_KENNZEICHEN AS Kennzeichen,\n"
                + "F_SITZPLAETZE AS Sitzplätze,\n"
                + "BAUART.B_BAUART AS Bauart,\n"
                + "MARKE.M_MARKE,\n"
                + "F_KM_1 AS Km1,\n"
                + "F_KM_2_RUEKG AS KM_Rückgabe,\n"
                + "F_INSPEKTION_INTERVALL AS Insp_Intervall,\n"
                + "F_INSPEKTION_KM AS Inspek_km,\n"
                + "F_INSPEKTION_DAT AS Insp_Datum,\n"
                + "F_MIETBEGINN AS Mietbeginn,\n"
                + "F_VORR_RUECKGABE AS vor_Rückgabe,\n"
                + "F_KM_RUEKGABE AS km_Rückgabe,\n"
                + "F_Update AS Aktualisierung,\n"
                + "F_NUTZLAST AS Nutzlast,\n"
                + "MIETZINS.M_MIETZINS_P_TAG, \n"
                + "ZUSTAND.Z_ZUSTAND\n"
                + "\n"
                + "             FROM FAHRZEUGDATEN\n"
                + "\n"
                + "            LEFT JOIN FAHRZEUGIDENTIVIKATION on FAHRZEUGDATEN.F_KENNZEICHEN = FAHRZEUGIDENTIVIKATION.F_ID\n"
                + "            LEFT JOIN BAUART on FAHRZEUGDATEN.F_BAUART = BAUART.B_ID\n"
                + "            LEFT JOIN MARKE on FAHRZEUGDATEN.F_MARKE = MARKE.MA_ID\n"
                + "           LEFT JOIN MIETZINS on FAHRZEUGDATEN.F_PREIS_P_TAG = MIETZINS.M_ID\n"
                + "            LEFT JOIN ZUSTAND on FAHRZEUGDATEN.F_ZUSTAND = ZUSTAND.Z_ID\n"
                + "             WHERE F_GELOESCHT = 0\n"
                + "             ORDER BY FAHRZEUGDATEN.F_Update");

        return DbVerbindung.SQLMulti(sqlAbfrage);
    }

    @Override
    public DefaultTableModel GibBauartTabelle() {
        //Abfrage über die Baurart
        String sqlAbfrage = "SELECT * FROM FAHRZEUGDATEN";

        return DbVerbindung.SQLMulti(sqlAbfrage);
    }

    @Override
    public DefaultTableModel GibFahrzeugTabelle(String kennzeichen) {
        //nimmt nur den neuesten Eintrag der FahrzeugGeschichteTabelle

        DefaultTableModel model = this.GibFahrzuegGeschichteTabelle(kennzeichen);

        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 2; i > -1; i--) {
                model.removeRow(i);
            }
        }
        return model;
    }

    @Override  //Alle gespeicherten Einträge des Fahrzeuges anhand des Kennzeichzens
    public DefaultTableModel GibFahrzuegGeschichteTabelle(String kennzeichen) {
        //Abfrage über das Kennzeichen
        String kz = kennzeichen.toUpperCase().trim();
        String sqlAbfrage = String.format("SELECT FAHRZEUGIDENTIVIKATION.F_KENNZEICHEN AS Kennzeichen,\n"
                + "F_SITZPLAETZE AS Sitzplätze,\n"
                + "BAUART.B_BAUART AS Bauart,\n"
                + "MARKE.M_MARKE,\n"
                + "F_KM_1 AS Km1,\n"
                + "F_KM_2_RUEKG AS KM_Rückgabe,\n"
                + "F_INSPEKTION_INTERVALL AS Insp_Intervall,\n"
                + "F_INSPEKTION_KM AS Inspek_km,\n"
                + "F_INSPEKTION_DAT AS Insp_Datum,\n"
                + "F_MIETBEGINN AS Mietbeginn,\n"
                + "F_VORR_RUECKGABE AS vor_Rückgabe,\n"
                + "F_KM_RUEKGABE AS km_Rückgabe,\n"
                + "F_Update AS Aktualisierung,\n"
                + "F_NUTZLAST AS Nutzlast,\n"
                + "MIETZINS.M_MIETZINS_P_TAG, \n"
                + "ZUSTAND.Z_ZUSTAND\n"
                + "\n"
                + "             FROM FAHRZEUGDATEN\n"
                + "\n"
                + "            LEFT JOIN FAHRZEUGIDENTIVIKATION on FAHRZEUGDATEN.F_KENNZEICHEN = FAHRZEUGIDENTIVIKATION.F_ID\n"
                + "            LEFT JOIN BAUART on FAHRZEUGDATEN.F_BAUART = BAUART.B_ID\n"
                + "            LEFT JOIN MARKE on FAHRZEUGDATEN.F_MARKE = MARKE.MA_ID\n"
                + "           LEFT JOIN MIETZINS on FAHRZEUGDATEN.F_PREIS_P_TAG = MIETZINS.M_ID\n"
                + "            LEFT JOIN ZUSTAND on FAHRZEUGDATEN.F_ZUSTAND = ZUSTAND.Z_ID\n"
                + "             WHERE FAHRZEUGIDENTIVIKATION.F_KENNZEICHEN = '%s' AND F_GELOESCHT = 0\n"
                + "             ORDER BY FAHRZEUGDATEN.F_Update", kz);
        return DbVerbindung.SQLMulti(sqlAbfrage);
    }
}
/*

     String sqlAbfrage = String.format("SELECT *, BAUART.B_BAUART, MARKE.M_MARKE, MIETZINS.M_MIETZINS_P_TAG, ZUSTAND.Z_ZUSTAND"
            + " FROM FAHRZEUGDATEN"
            + " INNER JOIN FAHRZEUGIDENTIVIKATION on FAHRZEUGDATEN.F_KENNZEICHEN = FAHRZEUGIDENTIVIKATION.F_ID"
            + " INNER JOIN BAUART on FAHRZEUGDATEN.F_BAUART = BAUART.B_ID"
            + " INNER JOIN MARKE on FAHRZEUGDATEN.F_MARKE = MARKE.MA_ID"
            + " INNER JOIN MIETZINS on FAHRZEUGDATEN.F_PREIS_P_TAG = MIETZINS.M_ID"
            + " INNER JOIN ZUSTAND on FAHRZEUGDATEN.F_ZUSTAND = ZUSTAND.Z_ID"
            + " WHERE FAHRZEUGIDENTIVIKATION.F_KENNZEICHEN = '%s'"
            + " ORDER BY FAHRZEUGDATEN.F_Update", kz);
 */
