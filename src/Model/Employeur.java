/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Connection_DB;
import Controller.HelperClass;
import java.awt.Choice;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Employeur {
    
    private int Id_Emp;
    private String First_Name_Emp;
    private String Last_Name_Emp;
    private String CCP_Num_Emp;
    private String Sem_Num_Emp;
    private String Grad_Emp;
    
    private String Residance_Emp;
//    private int Categorie_Emp;
    private String Fun_Emp;
    private int Id_Grade;
   
    
    private int choicePanel=1;
    
    Connection_DB  cnx=new Connection_DB();
    //private 
           
    public Employeur(int Id_Emp,String First_Name_Emp,String Last_Name_Emp,  String Grad_Emp,
                     int Id_Grade,String Fun_Emp,String CCP_Num_Emp,String Sem_Num_Emp,
            String Residance_Emp){
    this.Id_Emp=Id_Emp;
    this.First_Name_Emp=First_Name_Emp;
    this.Last_Name_Emp=Last_Name_Emp;
    
    
    this.CCP_Num_Emp=CCP_Num_Emp;
    this.Sem_Num_Emp=Sem_Num_Emp;
    this.Grad_Emp=Grad_Emp;
    
    this.Residance_Emp=Residance_Emp;
    this.Id_Grade=Id_Grade;
    this.Fun_Emp=Fun_Emp;
    
    
    
    }
    public Employeur(){
    
    }
    
  public void UpdateEmployer(int IdEmp){
    
     PreparedStatement prstm=null;
        
       cnx.connectSqlServer();
       String Query="UPDATE Employeur SET Name_Emp=? ,Last_Nm_Emp=?, Grad_Emp=?, ID_Grade=? ,Fun_Emp=?,"
               + "CCP_Emp=?,Num_Sem_Emp=?,Residance_Mng=? WHERE ID_Emp ="+IdEmp;
        try {
            prstm=cnx.getCnx().prepareStatement(Query);
            prstm.setString(1, this.getFirst_Name_Emp());
            prstm.setString(2, this.getLast_Name_Emp());
            prstm.setString(3, this.getGrad_Emp());
            prstm.setInt(4, this.getId_Grade());
            
            
            prstm.setString(5, getFun_Emp());
            prstm.setString(6, this.getCCP_Num_Emp());
            prstm.setString(7, this.getSem_Num_Emp());
                prstm.setString(8, this.getResidance_Emp());
                
                
                int x =prstm.executeUpdate();
                
                if (x>0) {
                JOptionPane.showMessageDialog(null, "The Update is Executed");
            }else {
                JOptionPane.showMessageDialog(null, "Error in Update ");
                }
           // prstm.
            try {
                prstm.close();
                cnx.Deconnect();
            } catch (SQLException e) {
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error In Execute Query"+e.getMessage());
        }
    
    }
    
    
    public int GetID_Categorie(){
    Statement stm=null;
    ResultSet res=null;
    String Query="SELECT ";
    cnx.connectSqlServer();
        try {
            
            
        } catch (Exception e) {
        }
        
    return 0;}
    /*****************************************************************************/
public void Add_Employeur(){ // Correct Function
        PreparedStatement stm=null;
       cnx.connectSqlServer();
       /* try {
            stm=cnx.getCnx().prepareStatement("");
        } catch (SQLException ex) {
            Logger.getLogger(Employeur.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
      // String sql = "INSERT INTO Employeur(ID_Emp,Name_Emp ,Last_Nm_Emp, Grad_Emp, Id_Categorie,Fun_Emp,CCP_Emp,Num_Sem_Emp,Residance_Mng) VALUES (?,?,?,?,?,?,?,?,?)"; 
       //Connection conn = cnx.getCnx();GetInformationAttribut
               //String sql1 = "INSERT INTO Employeur(Name_Emp ,Last_Nm_Emp, Grad_Emp, ID_Categorie,Fun_Emp,CCP_Emp,Num_Sem_Emp,Residance_Mng) VALUES (?,?,?,?,?,?,?,?)"; 
 String sql = "INSERT INTO Employeur(Name_Emp ,Last_Nm_Emp, Grad_Emp, ID_Grade,Fun_Emp,CCP_Emp,Num_Sem_Emp,Residance_Mng) VALUES (?,?,?,?,?,?,?,?)"; 
      
       try{
            
            PreparedStatement pstmt = cnx.getCnx().prepareStatement(sql);
            System.out.println("Paasse le atape tde create statment ");
             // pstmt.setInt(1, 5);
                System.out.println("The is insert");
                pstmt.setString(1, this.getFirst_Name_Emp());
                pstmt.setString(2, this.getLast_Name_Emp());
                pstmt.setString(3, this.getGrad_Emp());
                pstmt.setInt(4, this.getId_Grade());
                pstmt.setString(5, getFun_Emp());
                pstmt.setString(6, this.getCCP_Num_Emp());
                pstmt.setString(7, this.getSem_Num_Emp());
                pstmt.setString(8, this.getResidance_Emp());
                
                int x=pstmt.executeUpdate();
                
                
                if (x>0) {
                    System.out.println("The data is saved ");
                    JOptionPane.showMessageDialog(null, "The employer is added !");
                         }else
                {
                    System.out.println("Error in insert data ..");
                    JOptionPane.showMessageDialog(null, " Error in added the Employer !");
                }
                
                pstmt.close();
                
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error in execut Query"+ex.getMessage());
        }
       
       cnx.Deconnect();
}
/**********************************************************************************/    
public void AfficheIntable(DefaultTableModel MdTab){
cnx.connectSqlServer();
Statement stm=null;
    ResultSet res=null;
    try {
       stm=cnx.getCnx().createStatement();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error in create Statement Class Employeur Fun AfficheIntable() ");
    }
    try {
     res=stm.executeQuery("SELECT  * FROM Employeur");
     
        while (res.next()) {
            
            
           Object arg[]={res.getString("Fun_Emp"),res.getString("Last_Nm_Emp"),res.getString("Name_Emp"),res.getInt("ID_Emp")};
            System.out.println("the value is :"+arg[0]);
             System.out.println("the value is :"+arg[1]);
              System.out.println("the value is :"+arg[2]);
               System.out.println("the value is :"+arg[3]);
            MdTab.addRow(arg);
        }
     
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error in execute Query Select");
    }

        try {
            stm.close();
            
            cnx.Deconnect();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error in close stm"+ex.getMessage());
                   
        }
}
/*********************GetInformation1*****************************/
public  String[] GetInformation(int val){
    
    cnx.connectSqlServer();
    
 // Object  TabInform[] = null; 
String [] TabInform=new String[7];
Statement stm=null;
    ResultSet res=null;
    
    try {
       stm=cnx.getCnx().createStatement();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in create Statement Class Employeur Fun AfficheIntable() ");
    }
    
    try {
     res=stm.executeQuery("SELECT *  FROM Employeur where ID_Emp="+val+"");
      while (res.next()) {
          
            //System.out.println("gestion_ord_mission.Employeur.GetInformation1()"+res.getString("Name_Emp")+res.getString("Last_Nm_Emp")+res.getString("CCP_Emp"));
            TabInform[0]=""+val;
            TabInform[1]=res.getString("Name_Emp");
            TabInform[2]=res.getString("Last_Nm_Emp");
            TabInform[3]=res.getString("CCP_Emp");
            TabInform[4]=res.getString("Grad_Emp");
            TabInform[5]=res.getString("Fun_Emp");
            TabInform[6]=res.getString("Num_Sem_Emp");
            
             
            System.out.println("Information "+TabInform[0]);
            System.out.println("Information "+TabInform[1]);
            System.out.println("Information "+TabInform[2]);
            System.out.println("Information "+TabInform[3]);
            System.out.println("Information "+TabInform[4]);
            System.out.println("Information "+TabInform[5]);
            System.out.println("Information "+TabInform[6]);
        }
     
     
    }catch(SQLException e){
    e.printStackTrace();
    }
    
    
        try {
            res.close();
            stm.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            //Logger.getLogger(Employeur.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error in GetInformation1 "+ex.getMessage());
        }
    
return  TabInform;
}

/*****************************************************************************************/
public void GetInformationAttribut(int val){
 cnx.connectSqlServer();
    
 // Object  TabInform[] = null; 
//String [] TabInform=new String[7];
Statement stm=null;
    ResultSet res=null;
    
    try {
       stm=cnx.getCnx().createStatement();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in create Statement Class Employeur Fun AfficheIntable() ");
    }
    try {
     res=stm.executeQuery("SELECT *  FROM Employeur where ID_Emp="+val+"");
     //String [6] TabInformation;
      while (res.next()) { 
            //System.out.println("gestion_ord_mission.Employeur.GetInformation1()"+res.getString("Name_Emp")+res.getString("Last_Nm_Emp")+res.getString("CCP_Emp"));
                this.setId_Emp(val);
                this.setFirst_Name_Emp(res.getString("Name_Emp"));
                this.setLast_Name_Emp(res.getString("Last_Nm_Emp"));
                this.setCCP_Num_Emp(res.getString("CCP_Emp"));
                this.setGrad_Emp(res.getString("Grad_Emp"));
                this.setFun_Emp(res.getString("Fun_Emp"));
                this.setSem_Num_Emp(res.getString("Num_Sem_Emp"));
            
            System.out.println("Information "+getId_Emp()); 
            System.out.println("Information "+getFirst_Name_Emp());
            System.out.println("Information "+this.getLast_Name_Emp());
            System.out.println("Information "+ this.getCCP_Num_Emp());
            System.out.println("Information "+ this.getGrad_Emp());
            System.out.println("Information "+ this.getFun_Emp());
            System.out.println("Information "+ this.getSem_Num_Emp());
            
        }
     
     
    }catch(SQLException e){
    e.printStackTrace();
    }
    
    
        try {
            res.close();
            stm.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            //Logger.getLogger(Employeur.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error in GetInformation1 "+ex.getMessage());
        }
}
/*********************************************************************************/
public int GetLastIdEmpleur(){
Statement stm=null;
ResultSet res=null;
String Query="SELECT MAX(ID_Emp) FROM Employeur";
int last = 0;
cnx.connectSqlServer();
    try {
        stm=cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        if (res.next()) {
            last=res.getInt(1);
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in Get Max ID ");
    }
    
    try {
        cnx.Deconnect();
        stm.close();
        res.close();
    } catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Error in close Stm AND Can't Deconnect ");
    }

return last;
}
/************************************************************************************/
public void RemplirCombobox(JComboBox cmb,String Table ,String Key_Rs){
Statement stm=null;
 ResultSet res=null;
 String Query="SELECT "+Key_Rs+" FROM "+Table+" ";
 cnx.connectSqlServer();
    try {
        stm=cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        
        while (res.next()) {            
            //JOptionPane.showMessageDialog(null, "The value is :"+res.getString(1));
           cmb.addItem(res.getString(1));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
    }
    
    try {
        cnx.Deconnect();
        stm.close();
        res.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in close :"+e.getMessage());
    }
}

public void RemplirCombobox(Choice cmb,String Table ,String Field_01,char c){
Statement stm=null;
 ResultSet res=null;
 cmb.removeAll();
 String Query="SELECT "+Field_01+" FROM "+Table+" ";
   
    //String q="Select Nam_Des FROM destination WHERE (ID_Direct_Zone= ";
        
  
 cnx.connectSqlServer();
    try {
        stm=cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        
        while (res.next()) {            
            //JOptionPane.showMessageDialog(null, "The value is :"+res.getString(1));
            
            if (c=='i') {
                cmb.addItem(res.getInt(1)+"");
            }else cmb.addItem(res.getString(1));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
    }
    
    try {
        cnx.Deconnect();
        stm.close();
        res.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in close :"+e.getMessage());
    }
}

public void FillChoiceDestinataire(Choice cmb,int frst,int secd){

//String Query="Select Nam_Des FROM destination WHERE (ID_Direct_Zone="+cmb+" OR ID_Direct_Zone="+secd+")";
String Query="Select Nam_Des FROM destination WHERE (ID_Direct_Zone=1  OR  ID_Direct_Zone=2)";

Statement stm=null;
ResultSet res=null;
cmb.removeAll();
//    String q="Select Nam_Des FROM destination WHERE (ID_Direct_Zone= ";
        
  
 cnx.connectSqlServer();
    try {
        stm=cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        
        while (res.next()) {            
            //JOptionPane.showMessageDialog(null, "The value is :"+res.getString(1));
          cmb.addItem(res.getString("Nam_Des"));
            System.out.println("gestion_ord_mission.Employeur.FillChoiceDestinataire()"+res.getString(1));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
        e.printStackTrace();
    }
    
    try {
        cnx.Deconnect();
        stm.close();
        res.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in close :"+e.getMessage());
    }

}
/*********************************************************************************/
public int GetCategorie(String grade){

    Statement stm=null;
    ResultSet res=null;
    cnx.connectSqlServer();
    int Catg = 0;
    String Query="SELECT ID_Categorie FROM Grade WHERE Desc_Grade= N'"+grade+"'";
    try {
        stm=cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        
        if(res.next()) Catg=res.getInt(1);
     
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error in GetCategorie"+e.getMessage());
    }
    
    try {
        stm.close();
        res.close();
        cnx.Deconnect();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in close ");
    }
    return Catg;
}
public int GetCategorie(int Id_Grade){
      Statement stm=null;
    ResultSet res=null;
    cnx.connectSqlServer();
    int Catg = 0;
    String Query="SELECT ID_Categorie FROM Grade WHERE ID_Grade= "+Id_Grade;
    try {
        stm=cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        if(res.next()) Catg=res.getInt(1);
    
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error in GetCategorie"+e.getMessage());
    }
    
    try {
        stm.close();
        res.close();
        cnx.Deconnect();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in close ");
    }
    return Catg;
}

public int GetIdEmployer(String FirstNm,String LastNm){
Statement stm=null;
ResultSet res=null;
int id_Emp=0;
String Query="Select ID_Emp FROM Employeur where  Name_Emp=N'"+FirstNm+"' AND Last_Nm_Emp=N'"+LastNm+"'" ;
cnx.connectSqlServer();
    try {
        stm=cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        if (res.next()) {
            //System.out.println("The Values is :"+res.getInt(1));
            id_Emp=res.getInt(1);
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

return id_Emp;
}
public void GetInformationEmployer(int id_emp){
String Query="SELECT  Name_Emp,Last_Nm_Emp,Grad_Emp,ID_Grade,Fun_Emp,CCP_Emp,Num_Sem_Emp,Residance_Mng\n" +
"FROM Employeur\n" +
"WHERE \n" +
"Employeur.ID_Emp= "+id_emp;
Statement stm=null;
ResultSet res=null;
cnx.connectSqlServer();
    try {
        stm=cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        /**private int Id_Emp;
    private String First_Name_Emp;
    private String Last_Name_Emp;
    private String CCP_Num_Emp;
    private String Sem_Num_Emp;
    private String Grad_Emp;
    private String Residance_Emp;
    private int Categorie_Emp;
    private String Fun_Emp;
    **/
        
        if (res.next()) {
            
            First_Name_Emp=res.getString("Name_Emp");
            Last_Name_Emp=res.getString("Last_Nm_Emp");
            
            
            Grad_Emp=new HelperClass().GetNameGrade(res.getInt("ID_Grade"));
            Id_Grade=res.getInt("ID_Grade");
            
            CCP_Num_Emp=res.getString("CCP_Emp");
            Sem_Num_Emp=res.getString("Num_Sem_Emp");
            Fun_Emp=res.getString("Fun_Emp");
            Residance_Emp=res.getString("Residance_Mng");
             }
        
        
    } catch (SQLException e) {
        e.printStackTrace();
    }

        try {
            stm.close();
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(Employeur.class.getName()).log(Level.SEVERE, null, ex);
        }


}


    public static void main(String[] args) {
        
       Employeur emp=new Employeur(5, "رشيد", "عثمان", "رئيس مصلحة", 13, "رئيس مصلحة", "012345678",
               "012345678", "biskra");
    //emp.Add_Employeur();
    emp=new Employeur(0, "Test", "Test", "Test", 10, "Test", "Test", "Test", "Test"
   );
    emp.FillChoiceDestinataire(null,1,2);
  //  emp.UpdateEmployer(8);
        
        //emp.AfficheIntable(new DefaultTableModel());
  //  new Employeur().GetInformation(1);
    //new Employeur().GetInformationAttribut(2);
   // new Employeur().RemplirCombobox(null, "Grade", "Desc_Grade");
        //System.out.println("gestion_ord_mission.Employeur.main()"+emp.GetCategorie("مراقب"));
      //  System.out.println("gestion_ord_mission.Employeur.main()"+emp.GetLastIdEmpleur());
    }

    /**
     * @return the Id_Emp
     */
    public int getId_Emp() {
        return Id_Emp;
    }

    /**
     * @param Id_Emp the Id_Emp to set
     */
    public void setId_Emp(int Id_Emp) {
        this.Id_Emp = Id_Emp;
    }

    /**
     * @return the First_Name_Emp
     */
    public String getFirst_Name_Emp() {
        return First_Name_Emp;
    }

    /**
     * @param First_Name_Emp the First_Name_Emp to set
     */
    public void setFirst_Name_Emp(String First_Name_Emp) {
        this.First_Name_Emp = First_Name_Emp;
    }

    /**
     * @return the Last_Name_Emp
     */
    public String getLast_Name_Emp() {
        return Last_Name_Emp;
    }

    /**
     * @param Last_Name_Emp the Last_Name_Emp to set
     */
    public void setLast_Name_Emp(String Last_Name_Emp) {
        this.Last_Name_Emp = Last_Name_Emp;
    }

    /**
     * @return the CCP_Num_Emp
     */
    public String getCCP_Num_Emp() {
        return CCP_Num_Emp;
    }

    /**
     * @param CCP_Num_Emp the CCP_Num_Emp to set
     */
    public void setCCP_Num_Emp(String CCP_Num_Emp) {
        this.CCP_Num_Emp = CCP_Num_Emp;
    }

    /**
     * @return the Sem_Num_Emp
     */
    public String getSem_Num_Emp() {
        return Sem_Num_Emp;
    }

    /**
     * @param Sem_Num_Emp the Sem_Num_Emp to set
     */
    public void setSem_Num_Emp(String Sem_Num_Emp) {
        this.Sem_Num_Emp = Sem_Num_Emp;
    }

    /**
     * @return the Grad_Emp
     */
    public String getGrad_Emp() {
        return Grad_Emp;
    }

    /**
     * @param Grad_Emp the Grad_Emp to set
     */
    public void setGrad_Emp(String Grad_Emp) {
        this.Grad_Emp = Grad_Emp;
    }

    /**
     * @return the Residance_Emp
     */
    public String getResidance_Emp() {
        return Residance_Emp;
    }

    /**
     * @param Residance_Emp the Residance_Emp to set
     */
    public void setResidance_Emp(String Residance_Emp) {
        this.Residance_Emp = Residance_Emp;
    }

    /**
     * @return the Categorie_Emp
     */
   

    /**
     * @param Categorie_Emp the Categorie_Emp to set
     */
  

    /**
     * @return the Fun_Emp
     */
    public String getFun_Emp() {
        return Fun_Emp;
    }

    /**
     * @param Fun_Emp the Fun_Emp to set
     */
    public void setFun_Emp(String Fun_Emp) {
        this.Fun_Emp = Fun_Emp;
    }

    private int getId_Grade() {
        return Id_Grade;
    }
    
}
