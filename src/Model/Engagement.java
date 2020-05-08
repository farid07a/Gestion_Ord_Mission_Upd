/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Connection_DB;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author horizon
 */
public class Engagement {
    
    private Connection_DB Obj_Cnx=new Connection_DB();
    File excel = new File("‪D:\\Engagement.xlsx");
    private FileInputStream f_in;
    private FileOutputStream F_out;
    private XSSFWorkbook workbook;
    private XSSFSheet Sheet1;
    private Row rowSheet2;
    
    CellReference yearCell,Old_Sold,amount_Process,New_Sold;
    public Engagement(){
    
    
    }
    
    public void Initialise_ReferenceCell(){
    yearCell=new CellReference("B10");
    Old_Sold=new CellReference("C28");
    amount_Process=new CellReference("E28");
    New_Sold=new CellReference("G28");
    
    
    }
    
    
    public Connection_DB getObj_Cnx() {
        return Obj_Cnx;
    }

    public void setObj_Cnx(Connection_DB Obj_Cnx) {
        this.Obj_Cnx = Obj_Cnx;
    }

    public int getId_Operation() {
        return id_Operation;
    }

    public void setId_Operation(int id_Operation) {
        this.id_Operation = id_Operation;
    }

    public double getMontant() {
        return Montant;
    }

    public void setMontant(double Montant) {
        this.Montant = Montant;
    }

    public Date getDate_Operation() {
        return Date_Operation;
    }

    public void setDate_Operation(Date Date_Operation) {
        this.Date_Operation = Date_Operation;
    }

    public Vector<Integer> getList_OrdMission() {
        return List_OrdMission;
    }


    public void setList_OrdMission(Vector<Integer> List_OrdMission) {
        this.List_OrdMission = List_OrdMission;
    }


    public OrdMission getOrd_Engagement() {
        return ord_Engagement;
    }

  
    public void setOrd_Engagement(OrdMission ord_Engagement) {
        this.ord_Engagement = ord_Engagement;
    }
    
   private int id_Operation;
   private double Montant;
   private Date Date_Operation;
    
    private Vector<Integer> List_OrdMission=new Vector<>();
 
 
 public void Save_Operation(double Montant, Date Date_Operation,int Id_Engagement,int Cond){
 
       String Query;
     if (Cond==0) {
          Query="insert into Tab_Depense (Montant,Date_Operation) values (?,?)";
     }else Query="insert into Tab_Depense (Montant,Date_Operation,Id_Engagement) values (?,?,?)";
     
 PreparedStatement prstm=null;
        getObj_Cnx().connectSqlServer();
     try {
         prstm=getObj_Cnx().getCnx().prepareStatement(Query);
         prstm.setDouble(1, Montant);
         prstm.setDate(2,new java.sql.Date (Date_Operation.getTime()));
         if (Cond!=0) {
             prstm.setInt(3, Id_Engagement); 
         }
        int x= prstm.executeUpdate();
         if (x>0) {
             JOptionPane.showMessageDialog(null, "Save Depense Success");
         }else{
         JOptionPane.showMessageDialog(null, "No Record Saving In database");
         
         }
     } catch (SQLException e) {
         
         e.printStackTrace();
         JOptionPane.showMessageDialog(null, "Error In Saving Depense "+e.getMessage());
     }
 
     try {
         prstm.close();
            getObj_Cnx().Deconnect();
     } catch (SQLException e) {
         e.printStackTrace();
     }
 
 }
private OrdMission ord_Engagement=  new OrdMission();




 public void Calcule_prix_Engagement(){
 
     for (int ordMission : getList_OrdMission()) {
            getOrd_Engagement().Calcule_Price_OrdMission(ordMission);
//         System.out.println("Number of  Ord Mission "+ordMission);
//         System.out.println("Nbr Repat "+getOrd_Engagement().GetNbrRepat());
//          System.out.println("Nbr Decocher "+getOrd_Engagement().GetNbrDecoche());
          double price_OrdMission=getOrd_Engagement().Get_PriceOrd_Mission();
          System.out.println("price of Ord Mission: "+ordMission+" = "+price_OrdMission);
          setMontant(getMontant() + price_OrdMission);
     }
     
     System.out.println("Moantant of depense Table is "+getMontant());
     
 }
 
 
 public void Save_Depense(int Id_Enga,int cnd){
      Calcule_prix_Engagement();
     Save_Operation(getMontant(), new Date(), Id_Enga,cnd);
     
     JOptionPane.showMessageDialog(null, "The Montant isssssss"+getMontant());
     int NumOperation=GetLast_ID_Depense();
     for (Integer Num_Ord : getList_OrdMission()) {
            getOrd_Engagement().UpdateNumEngagement(Num_Ord, NumOperation);    
     }
 }
 
 public int GetLast_ID_Depense(){
 
    String Query="SELECT MAX(Id_Operation) FROM Tab_Depense";
     Statement stm=null;
     ResultSet res=null;
     int lastID = 1;
     getObj_Cnx().connectSqlServer();
     try {
         stm=getObj_Cnx().getCnx().createStatement();
         res=stm.executeQuery(Query);
         if (res.next()) {
             lastID=res.getInt(1);
         }
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Error in Get Last Id In table Depense"+e.getMessage());
     e.printStackTrace();
     }
     try {
         stm.close();
         res.close();
            getObj_Cnx().Deconnect();
     } catch (SQLException e) {
     }
 return lastID;
 }
 

 public void Fill_Table_Depense(JTable tab){
 String Query ="Select distinct Date_Operation,Montant,Name_Emp,Last_Nm_Emp, Tab_Depense.Id_Operation \n" +
"FROM Tab_Depense,OrdMission,Employeur \n" +
"WHERE \n" +
"Tab_Depense.Id_Operation=OrdMission.Id_Operation\n" +
"AND OrdMission.ID_Emp=Employeur.ID_Emp ";
 DefaultTableModel df=(DefaultTableModel) tab.getModel();
 df.setRowCount(0);
 Statement stm=null;
 ResultSet res=null;
 Obj_Cnx.connectSqlServer();
     try {
         stm=Obj_Cnx.getCnx().createStatement();
         res=stm.executeQuery(Query);
          System.out.println("testiiiiiiiiiiiiiiiiiiiiing");
         while (res.next()) {             
             System.out.println(res.getDate("Date_Operation")+" "+res.getDouble("Montant")+" "+(res.getString("Last_Nm_Emp")+res.getString("Name_Emp"))+" "+res.getInt("Id_Operation"));
     // Object arc[]={1,2,3};
             Object arg[]={res.getDate("Date_Operation"),res.getDouble("Montant"),(res.getString("Name_Emp") +" "+res.getString("Last_Nm_Emp")),res.getInt("Id_Operation")};     
             
        df.addRow(arg);
       // JComboBox cmbx=new JComboBox(arc);
        //tab.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cmbx));
       // cmbx.setSelectedIndex(0);
         }
         tab.setModel(df);
         
         
     } catch (SQLException e) {
    e.printStackTrace();
    //JOptionPane.showMessageDialog(null, "Error In fill List Depense"+e.getMessage());
         
     }
     try {
         stm.close();
         res.close();
         Obj_Cnx.Deconnect();         
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
 
 
 
 public void GetListOrdMission_Vector(int NumTabDep){
 
  String Query="SELECT Num_OrdMission from OrdMission WHERE Id_Operation="+NumTabDep;
     Obj_Cnx.connectSqlServer();
     Statement stm = null;
     ResultSet res = null;
        try {
         stm=Obj_Cnx.getCnx().createStatement();
         res=stm.executeQuery(Query);
         while (res.next()) {             
             List_OrdMission.add(res.getInt("Num_OrdMission"));
         } 
        
        } catch (SQLException e) {
         e.printStackTrace();
     }
     try {
         stm.close();
         res.close();
     } catch (SQLException e) {
         e.printStackTrace();
     }
 Obj_Cnx.Deconnect();
 
 }
 
 public void GetListOrdMission(JList list,int NumTabDep){
 
     String Query="SELECT Num_OrdMission from OrdMission WHERE Id_Operation="+NumTabDep;
     Obj_Cnx.connectSqlServer();
     Statement stm = null;
     ResultSet res = null;
     DefaultListModel<String> dflist=new DefaultListModel<>();
     //dflist.clear();
     try {
         stm=Obj_Cnx.getCnx().createStatement();
         res=stm.executeQuery(Query);
         while (res.next()) {             
             dflist.addElement(res.getInt(1)+"");
         }
         
         list.setModel(dflist);
     } catch (SQLException e) {
         e.printStackTrace();
     }
     try {
         stm.close();
         res.close();
     } catch (SQLException e) {
         e.printStackTrace();
     }
 Obj_Cnx.Deconnect();
 }
 
 public void Initialise_OrdMission_to_TabDepense(int Dep){
 
     GetListOrdMission_Vector(Dep);  //fill Vector of Ord Mission to dep
     for (int NumOrdMission : List_OrdMission) 
        {
         getOrd_Engagement().UpdateNumEngagement(NumOrdMission);  //update to null 
        }
         
 }
  
 
 public void UpdateTab_Depense(int NumDep){
 
     //Initialise_OrdMission_to_TabDepense(NumDep); //update to null all ordmission about NumDep
      for (Integer Num_Ord : getList_OrdMission()) {
            getOrd_Engagement().UpdateNumEngagement(Num_Ord, NumDep);    
     }
  //getOrd_Engagement().UpdateNumEngagement(Num_Ord, null);
 
 }
 
 
 public  void Delete_TabDep(int IdTabDep){
 String Query="DELETE FROM Tab_Depense WHERE Id_Operation= "+IdTabDep;
 
 PreparedStatement prstm=null;
 Obj_Cnx.connectSqlServer();
     try {
         prstm=Obj_Cnx.getCnx().prepareStatement(Query);
         int x=prstm.executeUpdate();
         if (x>0) {
         JOptionPane.showMessageDialog(null, "Successful Delete");
         }else{
         JOptionPane.showMessageDialog(null, Montant);
         }
         
     } catch (SQLException e) {
         e.printStackTrace();
     }
     try {
         prstm.close();
         Obj_Cnx.Deconnect();
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
 
 

    public static void main(String[] args) {
        new Engagement().Fill_Table_Depense(null);
    }
}
