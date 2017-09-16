package Verbindung;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package examples;
import java.sql.*;
import java.sql.DriverManager;
/**
 *
 * @author irish
 */
public class DbTreiber 
{
    
    public static void main (String[] args)
    {
        Connection verbindung;

        
        
        try
        {
            System.out.printf("%s%n", "Hallo");
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         System.out.printf("%s%n", "Treiber geladen");
        
         verbindung = DriverManager.getConnection("jdbc:mysql://localhost?user=root");
         System.out.printf("%s%n", "Verbindung erzeugt");
         
         verbindung.close();
         System.out.printf("%s%n", "Verbindung geschlossen");
        }
        catch(Exception ex)
        {
            System.out.printf("%s%n", ex.getMessage());
            ex.getMessage();
        }
        
        System.out.printf("%s%n", "Tschü");
    }
}
