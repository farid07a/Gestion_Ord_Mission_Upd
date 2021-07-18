/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Connection_DB;
import View.bourmal;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author horizon
 */
public class test {
    public static void main(String[] args) {
    
     
       String Query=  "BACKUP DATABASE OrdMissionNew TO DISK = 'D:\\OrdMissionNew.bak'";
         Connection_DB Obj=new Connection_DB();
         Obj.connectSqlServer();
        Statement  stm=null;
        try {
             Obj.getCnx().setAutoCommit(true);
             stm=Obj.getCnx().createStatement();
            
            stm.executeUpdate(Query);
            
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Obj.Deconnect();
                stm.close();
        } catch (SQLException e) {
        }
       
       
    }
}
