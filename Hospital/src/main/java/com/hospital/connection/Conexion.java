/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public class Conexion {
    
   private  static Connection connection = null;
   private  static String url;
   private  static String driver;
   private static String usuario;
   private static String password;
   protected Conexion(){ }
   
   public static Connection getInstancia(){
       if (connection == null) {
           try {
               try {
                   Class.forName("com.mysql.jdbc.Driver");
              
               connection = DriverManager.getConnection(
                       "jdbc:mysql://localhost:3306/tienda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                       , "root","Usuario_Root_3");
               //leerArchivo("."+File.separatorChar+"data"+File.separatorChar+"conect.txt");
            } catch (ClassNotFoundException ex) {
               Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
           }
           } catch (SQLException ex) {
               Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
        return connection;
    }
   public  static void leerArchivo(String path){
       FileReader leer = null;
       
       try {
           leer = new FileReader(path);
           BufferedReader br = new BufferedReader(leer);
           
           dataConection(br.readLine(), br.readLine(), br.readLine(), br.readLine(), br.readLine());
       } catch (Exception ex ) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   private static void  dataConection(String ipServer, String puerto,String dataBase,String usr,String pass){
       
        url = "jdbc:mysql://"+ipServer+":"+puerto+"/"+dataBase+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        
        driver = "com.mysql.jdbc.Driver";
        usuario = usr;
        password = pass;
        String url2 = "jdbc:mysql://localhost/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       try {
           System.out.println("passwrodddf====="+password);
           if (password == null) {
           connection = DriverManager.getConnection(url, usuario,"");    
           }else{
               connection = DriverManager.getConnection(url, usuario, password);
           }
           
           
       } catch (SQLException ex) {
           System.out.println("error");
           //JOptionPane.showMessageDialog(null, "Servidor no Encontrado\n "+ex.getMessage(), "Fallo la Conexion", JOptionPane.ERROR);
           Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
}