/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Controller.Connection_DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Fonction {
    
    Connection_DB cnx=new Connection_DB();
    Statement stm=null;
    ResultSet res=null;
    int id_Fonction;
    String NameFunctionAR;
    String NameFunctionFR;
    public Fonction(String NameFunctionAR,String NameFunctionFR){
    this.NameFunctionAR=NameFunctionAR;
    this.NameFunctionFR=NameFunctionFR;
    
    }
    
    public Fonction(){
    
    }
    
    public void Add_Function(){
    
      String Query="INSERT INTO Fonction (Nm_FonctionAR,Nm_FonctionFR)VALUES(?,?)";
        PreparedStatement prstm=null;
        cnx.connectSqlServer();
        try {
            prstm=cnx.getCnx().prepareStatement(Query);
            prstm.setString(1, NameFunctionAR);
            prstm.setString(2, NameFunctionFR);
        int x=prstm.executeUpdate();
            if (x>0) {
                JOptionPane.showMessageDialog(null, "Success Add Function ");
            }else JOptionPane.showMessageDialog(null, "Cannot Add Function ");
        } catch (SQLException e) {
        e.printStackTrace();
        
        }        
        try {
            prstm.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    
    }
    
    public String Get_NameFunction(int ID_Function){
        String Query="SELECT Nm_FonctionAR FROM Fonction WHERE Id_Fonction= "+ID_Function;
        //Statement stm=null;
       cnx.connectSqlServer();
       String NamFonction="";
        try {
            stm=cnx.getCnx().createStatement();
            res=stm.executeQuery(Query);
            if (res.next()) {
                NamFonction=res.getString("Nm_FonctionAR");
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException e) {
        }
    return NamFonction;
    }
    
    public int Get_ID_Function(String NameFunc){
    
        String Query="SELECT Id_Fonction from Fonction WHERE Nm_FonctionAR = '"+NameFunc+"'";
    cnx.connectSqlServer();
       int  Id_Function=0;
        try {
            stm=cnx.getCnx().createStatement();
            res=stm.executeQuery(Query);
            if (res.next()) {
                Id_Function=res.getInt("Id_Fonction");
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException e) {
        }
    
    return Id_Function;}
    
 
    
    
    
    public void FillTableFunction(JTable tab){
    String Query ="Select * FROM Fonction";
        DefaultTableModel dfm=(DefaultTableModel) tab.getModel();
        dfm.setRowCount(0);
        cnx.connectSqlServer();
        try {
            stm=cnx.getCnx().createStatement();
            res=stm.executeQuery(Query);
            int i=0,j=0;
            while (res.next()) {
               
                Object arg[]={res.getInt(1),res.getString(2),res.getString(3)};
                
//                dfm.setValueAt(res.getInt(1), i, j);
//                break;
            /*    j++;
                dfm.setValueAt(res.getString(2), i,j );
                j++;
                dfm.setValueAt(res.getString(2), i,j );
                j=0;
                i++;*/
            dfm.addRow(arg);
            }
                tab.setModel(dfm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    
    public void Delete_Function(){
    
    }
}
