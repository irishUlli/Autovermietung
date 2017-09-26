/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Verbindung;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
//import java.sql.DriverManager;

/**
 *
 * @author root
 */
public class DbVerbindung {

    private static String pfat = "jdbc:mysql://localhost?autoReconnect=true&useSSL=false";
    private static String pfat2 = "jdbc:mysql://localhost/AUTOVERMIETUNG?autoReconnect=true&useSSL=false";
    private static String benutzer = "root";
    private static String password = "irish";
    private static String driver = "com.mysql.jdbc.Driver";

    //Erzeugt eine Neue Datenbank mit Testdaten 
    public static void NeueDbErzeugen() {
        Connection verbindung;
        Statement sqlAnweisung;
        String sqlCode;

        try {

            Class.forName(driver).newInstance();
//         verbindung = DriverManager.getConnection("jdbc:mysql://localhost?user=root");
            verbindung = DriverManager.getConnection(pfat, benutzer, password);
            sqlAnweisung = verbindung.createStatement();

            sqlCode = "DROP DATABASE IF EXISTS AUTOVERMIETUNG";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "CREATE DATABASE IF NOT EXISTS AUTOVERMIETUNG";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "USE AUTOVERMIETUNG";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "CREATE TABLE IF NOT EXISTS FAHRZEUGDATEN(F_ID INT NOT NULL AUTO_INCREMENT, F_KENNZEICHEN INT, F_SITZPLAETZE INT, F_BAUART INT,"
                    + " F_ZUSTAND INT, F_MARKE INT, F_KM_1 INT, F_KM_2_RUEKG INT, F_INSPEKTION_INTERVALL INT, F_INSPEKTION_KM INT, F_INSPEKTION_DAT DATE,"
                    + " F_MIETBEGINN DATE, F_VORR_RUECKGABE DATE, F_KM_RUEKGABE INT, F_PREIS_P_TAG DOUBLE, F_Update DATE, F_GELOESCHT INT, F_NUTZLAST INT,"
                    + " PRIMARY KEY (F_ID))";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "CREATE TABLE IF NOT EXISTS FAHRZEUGIDENTIVIKATION (F_ID INT NOT NULL AUTO_INCREMENT, F_KENNZEICHEN VARCHAR (25),"
                    + " PRIMARY KEY (F_ID))";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "CREATE TABLE IF NOT EXISTS BAUART(B_ID INT NOT NULL AUTO_INCREMENT, B_BAUART VARCHAR (25),"
                    + " PRIMARY KEY (B_ID))";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "CREATE TABLE IF NOT EXISTS ZUSTAND(Z_ID INT NOT NULL AUTO_INCREMENT, Z_ZUSTAND VARCHAR (25),"
                    + " PRIMARY KEY (Z_ID))";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "CREATE TABLE IF NOT EXISTS MIETZINS(M_ID INT NOT NULL AUTO_INCREMENT, M_MIETZINS_P_TAG DOUBLE,"
                    + " PRIMARY KEY (M_ID))";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "CREATE TABLE IF NOT EXISTS INSPEKTIONSINTERVALL(I_ID INT NOT NULL AUTO_INCREMENT, I_INSPEKTIONSINTERVALL INT,"
                    + " PRIMARY KEY (I_ID))";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "CREATE TABLE IF NOT EXISTS MARKE(MA_ID INT NOT NULL AUTO_INCREMENT, M_MARKE VARCHAR (25),"
                    + " PRIMARY KEY (MA_ID))";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "CREATE TABLE IF NOT EXISTS VERSION(V_ID INT NOT NULL AUTO_INCREMENT, V_VERSION INT,"
                    + " PRIMARY KEY (V_ID))";
            sqlAnweisung.executeUpdate(sqlCode);

            // INSERT
            sqlCode = "INSERT FAHRZEUGDATEN (F_KENNZEICHEN, F_SITZPLAETZE, F_BAUART, F_ZUSTAND, F_MARKE, F_KM_1,"
                    + " F_KM_2_RUEKG, F_INSPEKTION_INTERVALL, F_INSPEKTION_KM,"
                    + " F_INSPEKTION_DAT, F_MIETBEGINN, F_VORR_RUECKGABE, F_KM_RUEKGABE, F_PREIS_P_TAG, F_Update, F_NUTZLAST, F_GELOESCHT)"
                    + "VALUES(1, 4, 1, 1, 1, 220000, 0, 2, 0, '2017-08-12', '2017-08-12', '2017-08-16', 0, 1, '2017-08-12', 0, 0),"
                    + " (1, 4, 1, 1, 1, 220001, 220115, 2, 0, '2017-08-12', '2017-08-12', '2017-08-16', 0, 1, '2017-08-24', 0, 0),"
                    + " (2, 2, 1, 1, 2, 30001, 30223, 3, 0, '2017-08-13', '2017-08-13', '2017-08-16', 0, 1, '2017-08-14', 0, 0),"
                    + " (2, 2, 1, 1, 2, 30223, 30465, 3, 0, '2017-08-13', '2017-08-14', '2017-08-17', 0, 1, '2017-08-17', 0, 0)";  //16
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "INSERT FAHRZEUGIDENTIVIKATION (F_KENNZEICHEN)"
                    + "VALUES('ME664BF'),('W5432TT'),('PL555AA')";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "INSERT BAUART (B_BAUART)"
                    + "VALUES('PWK')";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "INSERT BAUART (B_BAUART)"
                    + "VALUES('LKW')";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "INSERT ZUSTAND (Z_ZUSTAND)"
                    + "VALUES('frei')";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "INSERT ZUSTAND (Z_ZUSTAND)"
                    + "VALUES('vermietet')";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "INSERT MIETZINS (M_MIETZINS_P_TAG)"
                    + "VALUES(40)";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "INSERT MIETZINS (M_MIETZINS_P_TAG)"
                    + "VALUES(60)";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "INSERT INSPEKTIONSINTERVALL (I_INSPEKTIONSINTERVALL)"
                    + "VALUES(30000)";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "INSERT INSPEKTIONSINTERVALL (I_INSPEKTIONSINTERVALL)"
                    + "VALUES(60000)";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "INSERT MARKE (M_MARKE)"
                    + "VALUES('Audi')";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "INSERT MARKE (M_MARKE)"
                    + "VALUES('VW')";
            sqlAnweisung.executeUpdate(sqlCode);

            sqlCode = "INSERT VERSION (V_VERSION)"
                    + "VALUES(1)";
            sqlAnweisung.executeUpdate(sqlCode);

            verbindung.close();

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Allg.: " + ex.getMessage());
            ex.getMessage();
        }

        //System.out.printf("%s%n", "Tschü");
    }

    public static DefaultTableModel SQLMulti(String abfrage) {
        Connection verbindung;
        Statement sqlAnweisung;
        String sqlCode;
        ResultSet ergebnisGesamt = null;
        DefaultTableModel table = null;

        try {
            Class.forName(driver).newInstance();
            verbindung = DriverManager.getConnection(pfat2 , "root","irish");
            sqlAnweisung = verbindung.createStatement();

            sqlCode = abfrage;
            ergebnisGesamt = sqlAnweisung.executeQuery(sqlCode);

            table = buildTableModel(ergebnisGesamt); //new JTable(
            //JOptionPane.showMessageDialog(null, new JScrollPane(table));
            verbindung.close();

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Allg.: " + ex.getMessage());
            ex.getMessage();
        }
        return table;
    }

    public static String SQLMultiReturnString(String abfrage) {
        DefaultTableModel model;
        String ergebnis = null;
        model = SQLMulti(abfrage);
        if (model.getRowCount() > 0) {
            ergebnis = model.getValueAt(0, 0).toString();
        }

        return ergebnis;
    }

    public static DefaultTableModel Alle() {
        Connection verbindung;
        Statement sqlAnweisung;
        String sqlCode;
        ResultSet ergebnisGesamt = null;
        DefaultTableModel table = null;

        String kennzeichen, marke;
        int sitzplaetze, zustand, kmStand1, kmStandRuekg, inspektionInterfall, inspektionkm;
        float nutzlast;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate inspektinDate;//,mietbeginn, datVorRuekgabe;

        try {
            Class.forName(driver).newInstance();
            verbindung = DriverManager.getConnection(pfat2 , benutzer, password);
            sqlAnweisung = verbindung.createStatement();

            sqlCode = "SELECT * FROM FAHRZEUGDATEN";
            ergebnisGesamt = sqlAnweisung.executeQuery(sqlCode);

//         if(ergebnisGesamt.next())
//             do
//             {
//                 kennzeichen = ergebnisGesamt.getString("F_KENNZEICHEN");
//                 marke = ergebnisGesamt.getString("F_MARKE");
//                 sitzplaetze = ergebnisGesamt.getInt("F_SITZPLAETZE");
//                 zustand = ergebnisGesamt.getInt("F_ZUSTAND");
//                 nutzlast = ergebnisGesamt.getFloat("F_NUTZLAST");
//                 kmStand1 = ergebnisGesamt.getInt("F_KM_1");
//                 kmStandRuekg = ergebnisGesamt.getInt("F_KM_2_RUEKG");
//                 inspektionInterfall = ergebnisGesamt.getInt("F_INSPEKTION_INTERVALL");
//                 inspektionkm = ergebnisGesamt.getInt("F_INSPEKTION_KM");
//                 inspektinDate = LocalDate.parse(ergebnisGesamt.getString("F_INSPEKTION_DAT"), dtf);
//                 mietbeginn = LocalDate.parse(ergebnisGesamt.getString("F_MIETBEGINN"), dtf);
//                 datVorRuekgabe = LocalDate.parse(ergebnisGesamt.getString("F_VORR_RUECKGABE"), dtf);
//                 
//                
//                 System.out.printf("%s # %s # %d # %.2f #td.tm.tY%n", 
//                         kennzeichen, marke, sitzplaetze, nutzlast, inspektinDate, inspektinDate ,inspektinDate);
//             }         
//             while(ergebnisGesamt.next());
//         else
//             System.out.printf("Keine Datensätze im Ergebnis%n");
//         
            table = buildTableModel(ergebnisGesamt); //new JTable(
            //JOptionPane.showMessageDialog(null, new JScrollPane(table));
            verbindung.close();

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Allg.: " + ex.getMessage());
            ex.getMessage();
        }
        System.out.printf("%s%n", "Tschü");
        return table;
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }

    public static boolean VerbindungstestDb() {
        Connection verbindung;
        boolean rueckgabewert = false;
        try {
            System.out.printf("%s%n", "Hallo");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
//         System.out.printf("%s%n", "Treiber geladen");

            verbindung = DriverManager.getConnection(pfat, benutzer, password);
//         System.out.printf("%s%n", "Verbindung erzeugt");
            rueckgabewert = true;

            verbindung.close();
//         System.out.printf("%s%n", "Verbindung geschlossen");
        } catch (Exception ex) {
//            System.out.printf("%s%n", ex.getMessage());
            rueckgabewert = false;
        }
        return rueckgabewert;
    }
}
