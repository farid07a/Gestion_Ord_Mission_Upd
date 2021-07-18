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
public class Task_Mission {
    
    
       Connection_DB cnx=new Connection_DB();
       
 public int GetId_Task(String NamTask){
    
    Statement stm=null;
ResultSet res=null;
int Id_task=0;
String Query="Select Id_task FROM Tasktype where  DescriptionTask_AR=N'"+NamTask+"' " ;
cnx.connectSqlServer();
    try {
        stm=cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        if (res.next()) {
            //System.out.println("The Values is :"+res.getInt(1));
            Id_task=res.getInt(1);
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

return Id_task;
    
    }
 
 public String Get_Task(int id_task){
    
    Statement stm=null;
ResultSet res=null;
String DescriptionTask_AR="";
String Query="Select DescriptionTask_AR FROM Tasktype where  Id_task="+id_task+" " ;
cnx.connectSqlServer();
    try {
        stm=cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        if (res.next()) {
             DescriptionTask_AR=res.getString(1);
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
return DescriptionTask_AR;
    
    }
}
