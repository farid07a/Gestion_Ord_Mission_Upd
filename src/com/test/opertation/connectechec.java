/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.opertation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author horizon
 */
public class connectechec {

    private Connection cnx;
    private int valcon=1;
     public  void connectSqlServer(){
    
           
        //28101988 Mot Passe Ancian
       // String url="jdbc:sqlserver://localhost:1433;user=sa;password=farid;databaseName=OrdMissionNew";
       String url="jdbc:sqlserver://172.16.5.100:1433;user=sa;password=Farid/2019;databaseName=OrdMissionNew";
        
         if (valcon==1) {
             
              try {
                  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             cnx = DriverManager.getConnection(url);
             System.out.println("The connection is etablished........");
           
        } catch (SQLException ex) {
            ///Logger.getLogger(ConnectSQLServer.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Error in connect dabase"+ex.getMessage());
           
            String url1="jdbc:sqlserver://localhost:1433;user=sa;password=farid;databaseName=OrdMissionNew";
                  try {
                      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                  } catch (ClassNotFoundException ex1) {
                      Logger.getLogger(connectechec.class.getName()).log(Level.SEVERE, null, ex1);
                  }
             try {
            cnx = DriverManager.getConnection(url1);
            
                 System.out.println("Connecting to localhost is passed");
        }catch(SQLException exc){
            
            exc.printStackTrace();
        }
             
         } catch (ClassNotFoundException ex) {
               Logger.getLogger(connectechec.class.getName()).log(Level.SEVERE, null, ex);
           }
         }
    
    }
     
     
     public static void main(String[] args) {
        connectechec cnech=new connectechec();
        cnech.connectSqlServer();
        
         
    }
}
