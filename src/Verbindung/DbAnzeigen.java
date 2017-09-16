/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Verbindung;
import java.sql.*;
//import java.time.format.DateTimeFormatter;
import java.time.*;
import java.time.format.*;
/**
 *
 * @author root
 */
public class DbAnzeigen 
{
    
    public static void main (String[] args)
    {
        Connection verbindung;
        Statement sqlAnweisung;
        String sqlCode;
        ResultSet ergebnisGesamt;
         
        String kennzeichen, marke;
        int sitzplaetze, zustand, kmStand1, kmStandRuekg, inspektionInterfall, inspektionkm;
        float nutzlast;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate inspektinDate;//,mietbeginn, datVorRuekgabe;
        
        try
        {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         verbindung = DriverManager.getConnection("jdbc:mysql://localhost/AUTOVERMIETUNG?user=root");
         sqlAnweisung = verbindung.createStatement();
         
         sqlCode = "SELECT * FROM 'FAHRZEUGDATEN'";
         ergebnisGesamt = sqlAnweisung.executeQuery(sqlCode);
         
         if(ergebnisGesamt.next())
             do
             {
                 kennzeichen = ergebnisGesamt.getString("F_KENNZEICHEN");
                 marke = ergebnisGesamt.getString("F_MARKE");
                 sitzplaetze = ergebnisGesamt.getInt("F_SITZPLAETZE");
                 zustand = ergebnisGesamt.getInt("F_ZUSTAND");
                 nutzlast = ergebnisGesamt.getFloat("F_NUTZLAST");
                 kmStand1 = ergebnisGesamt.getInt("F_KM_1");
                 kmStandRuekg = ergebnisGesamt.getInt("F_KM_2_RUEKG");
                 inspektionInterfall = ergebnisGesamt.getInt("F_INSPEKTION_INTERVALL");
                 inspektionkm = ergebnisGesamt.getInt("F_INSPEKTION_KM");
                 inspektinDate = LocalDate.parse(ergebnisGesamt.getString("F_INSPEKTION_DAT"), dtf);
//                 mietbeginn = LocalDate.parse(ergebnisGesamt.getString("F_MIETBEGINN"), dtf);
//                 datVorRuekgabe = LocalDate.parse(ergebnisGesamt.getString("F_VORR_RUECKGABE"), dtf);
                 
                
                 System.out.printf("%s # %s # %d # %.2f #td.tm.tY%n", 
                         kennzeichen, marke, sitzplaetze, nutzlast, inspektinDate, inspektinDate ,inspektinDate);
             }         
             while(ergebnisGesamt.next());
         else
             System.out.printf("Keine Datensätze im Ergebnis%n");
         
         verbindung.close();
         
        }
        catch(SQLException ex)
        {
          System.out.println("SQL: "+ ex.getMessage());
        }
        catch(Exception ex)
        {
            System.out.println("Allg.: "+ ex.getMessage());
            ex.getMessage();
        }
        
        System.out.printf("%s%n", "Tschü");
    }
}
