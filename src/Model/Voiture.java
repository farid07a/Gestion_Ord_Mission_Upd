/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Connection_DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author horizon
 */
public class Voiture {
    Connection_DB cnx=new Connection_DB();
    public Voiture(){
    
    }
    
    public void InsertVoitur(){
    String Query="INSERT INTO ";
        Statement stm=null;
        ResultSet res=null;
        
        
    
    }
    
    public int GetId_Voiture(String NamVoiture){
    
    Statement stm=null;
ResultSet res=null;
int ID_Voiture=0;
String Query="Select ID_Voiture FROM Moyen_Transport where  Nom_Voiture=N'"+NamVoiture+"' " ;
cnx.connectSqlServer();
    try {
        stm=cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        if (res.next()) {
            //System.out.println("The Values is :"+res.getInt(1));
            ID_Voiture=res.getInt(1);
        }
        
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

return ID_Voiture;
    
    }
    
        public int GetId_Distinataire(String NamDistinataire){
    
    Statement stm=null;
ResultSet res=null;
int Id_dest=0;
String Query="Select Id_dest FROM destination where  Nam_Des=N'"+NamDistinataire+"' " ;
cnx.connectSqlServer();
    try {
        stm=cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        if (res.next()) {
            //System.out.println("The Values is :"+res.getInt(1));
            Id_dest=res.getInt(1);
        }
        
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

return Id_dest;
    
    }
        
        public String  getNameMoyTrans(int id_MoyTrs){
        
        String Query="SELECT DescriptionTask_AR FROM Tasktype WHERE Id_task= "+id_MoyTrs;
        Statement stm=null;
        ResultSet res=null;
        cnx.connectSqlServer();
        String MoyenTrs="";
            try {
            stm=cnx.getCnx().createStatement();
            res=stm.executeQuery(Query);
                if (res.next()) {
                 MoyenTrs=res.getString("DescriptionTask_AR");
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stm.close();
                res.close();
                cnx.Deconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return MoyenTrs;}
}
