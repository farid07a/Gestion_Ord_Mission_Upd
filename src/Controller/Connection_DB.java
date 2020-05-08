/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author formation
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class Connection_DB {
    private Connection cnx; 
    
    public void Connect(){
        try {
             Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); //pilot 
             String DB="jdbc:ucanaccess://Employeur.accdb"; //connect to dabase DB_Client*/
             //String url = "jdbc:mysql://localhost:3306/DB_Client";
             //Class.forName("com.mysql.jdbc.Driver");
             
        //cnx=DriverManager.getConnection(url,"root","");
          // JOptionPane.showMessageDialog(null, "Pilot downlode");
           
                cnx=DriverManager.getConnection(DB);//cree conction 
                //setCnx(DriverManager.getConnection(DB));
                System.out.println("Connection Etablished....");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "error in Connection.."+ex.getMessage());  
        }
    }
    public  void connectSqlServer(){
    
          try {	
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            System.out.println("The pilot is download");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error in download pilot");
        }
        //28101988 Mot Passe Ancian
        String url="jdbc:sqlserver://localhost:1433;user=sa;password=farid;databaseName=OrdMissionNew";
        //String url="jdbc:sqlserver://172.16.5.100:1433;user=sa;password=Farid/2019;databaseName=OrdMissionNew";
        
        try {
            cnx = DriverManager.getConnection(url);
             System.out.println("The connection is etablished........");
           // JOptionPane.showMessageDialog(null, "Succefull connection ....");
        } catch (SQLException ex) {
            ///Logger.getLogger(ConnectSQLServer.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error in connect dabase"+ex.getMessage());
            System.exit(0);
            
        }
    
    }
           
    public void Deconnect(){
        
        try {
              getCnx().close();
              
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error "+e.getMessage());
        }
  
    }
   
    public Connection getCnx() {
        return cnx;
    }

    
    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }
    
    public static void main(String[] args) {
     Connection_DB obj=   new Connection_DB();
     obj.connectSqlServer();
        //obj.Connect();
        obj.Deconnect();
    }
}

