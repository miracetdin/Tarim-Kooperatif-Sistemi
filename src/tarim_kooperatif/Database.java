/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarim_kooperatif;

import java.io.File;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miracetdin
 */

public class Database {
    static String url = "jdbc:postgresql://localhost:5432/Tarim_Koop";
    static Connection connection = null;
    
    // database connection function
    static void connect(){
        try {
            connection = DriverManager.getConnection(url,"postgres","password");
            System.out.println("Database connection is established.");
        } catch (SQLException ex) {
            System.out.println("Error: Database connection is not established!");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static boolean isUserExists(String kullanici_adi,String sifre){
        String SQL = "select * from kullanıcı where kullanici_adi='"+kullanici_adi+"' and sifre='"+sifre+"'";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    
        static String logIn(String kullanici_adi, String sifre){
        String isim = null, soyisim = null, yetki = null;
        
        if(isUserExists(kullanici_adi, sifre)){
            String SQL = "select isim from kullanici where id='"+kullanici_adi+"'";
            Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()){
                isim = resultSet.getString("isim");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ("Hoşgeldiniz " + isim);
            
        }
        else{
            System.out.println("Giriş Başarısız!");
        }
        return null;
    }
        
    static ResultSet list(String query){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
