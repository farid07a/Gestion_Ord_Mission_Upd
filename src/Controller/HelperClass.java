/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Employeur;
import Model.Info_Ord;
import java.awt.Choice;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author horizon
 */
public class HelperClass {
    
    Statement stm;
    ResultSet resst;
    public HelperClass(){
    
    
    }
    
    public void FillCommune(JComboBox ch,String FildFill,String NamTab,String FilCond,int Cndi,Object value,String FirstItem){
    
    ch.removeAllItems();
    ch.addItem(FirstItem);
    String Queryv=" SELECT "+FildFill+" FROM "+NamTab+" ";
    
        if (Cndi==0) {
            if (value instanceof Integer) {
            
           Queryv= Queryv.concat(" WHERE "+FilCond+" = "+value);
            
        }else if(value instanceof String){
           Queryv= Queryv.concat(" WHERE "+FilCond+" = '"+value+"'");
        }else if (value instanceof Double) {
           Queryv= Queryv.concat(" WHERE "+FilCond+" = "+value);
        }
        }
        
     // String Query="  SELECT Nam_Des FROM destination WHERE ID_TypDis=2";
    
        System.out.println("The Contact is :"+Queryv);
     
     
    cnx.connectSqlServer();
        try {
            stm=cnx.getCnx().createStatement();
            resst=stm.executeQuery(Queryv);
            while (resst.next()) {                
                ch.addItem(resst.getString(FildFill));
                
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        try {
            stm.close();
            resst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
  
        
    }
    
    public int GetIdZone(String NameCom,int typeZone){
        String Query="select ID_Direct_Zone FROM destination WHERE Nam_Des=N'"+NameCom+"' AND ID_TypDis="+typeZone;
        int idZone=0;
        cnx.connectSqlServer();
        try {
            stm=cnx.getCnx().createStatement();
            resst=stm.executeQuery(Query);
            if (resst.next()) {
                idZone=resst.getInt(1);
                System.out.println("gestion_ord_mission.HelperClass.GetIdZone()"+idZone);
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            stm.close();
            resst.close();
            cnx.Deconnect();
        } catch (SQLException e) {
        }
    return idZone;
    }
    
    
    
    
    Connection_DB cnx=new Connection_DB();
    
    public void InsertCommune(String Ncomm,String Local,int TypDis,int TypWl){
        PreparedStatement prs=null;
        String Query="insert into destination (Nam_Des,LoaclGeo,ID_Direct_Zone,ID_TypDis) VALUES (?,?,?,?)";
        cnx.connectSqlServer();
        try {
        prs=cnx.getCnx().prepareStatement(Query);
            prs.setString(1, Ncomm);
            prs.setString(2, Local);
            prs.setInt(3, TypDis);
            prs.setInt(4, TypWl);
            
            int x=prs.executeUpdate();
            if (x>0) {
                JOptionPane.showMessageDialog(null, "success added ");
            }else{
            JOptionPane.showMessageDialog(null, "Error in  Added ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
    
    public void Insert_Grad(String Desc_Grade,String Detaiil_Grade,int ID_Categorie){
    String Query="insert into Grade (Desc_Grade,Detaiil_Grade,ID_Categorie)VALUES(?,?,?)";
    PreparedStatement prst=null;
    cnx.connectSqlServer();
        try {
            prst=cnx.getCnx().prepareStatement(Query);
            
            prst.setString(1, Desc_Grade);
            prst.setString(2, Detaiil_Grade);
            prst.setInt(3, ID_Categorie);
            
            int x=prst.executeUpdate();
            if (x>0) {
                JOptionPane.showMessageDialog(null, "Successful Add Grad");
            }else JOptionPane.showMessageDialog(null, "Error Add Grad");
            
        prst.close();
        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
        
    cnx.Deconnect();
    
    }
    
    public void Fill_repat_decocher(int ZoneNrd,int ZoneSud,int Categorie,String Operator,JTable tab){
    /*String Query="select distinct valeur_Repat,valeur_decocher \n" +
"from Prix_Repat,Prix_Decocher,Categorie,Direction,Prix_Repat_Categorie,Prix_Decocher_Categorie \n" +
"where \n" +
"Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n" +
"AND Direction.ID_Direct_Zone=Prix_Decocher.ID_Direct_Zone\n" +
"\n" +
"AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher\n" +
"AND Categorie.ID_Categorie=Prix_Decocher_Categorie.ID_Categorie\n" +
"\n" +
"AND Direction.ID_Direct_Zone="+Zone+" \n" +
"AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n" +
"AND Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie\n" +
"AND Categorie.Num_Categorie "+Operator+" "+Categorie;*/ //Ce code to get Repat And Decocher

DefaultTableModel dfm=new DefaultTableModel();
dfm=(DefaultTableModel) tab.getModel();
dfm.setRowCount(0);
String Query="select distinct valeur_Repat \n" +
"from Prix_Repat,Categorie,Direction,Prix_Repat_Categorie \n" +
"where \n" +
"Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n" +
"--AND Direction.ID_Direct_Zone=Prix_Decocher.ID_Direct_Zone\n" +
"\n" +
"--AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher\n" +
"--AND Categorie.ID_Categorie=Prix_Decocher_Categorie.ID_Categorie\n" +
"\n" +
"AND (Direction.ID_Direct_Zone= "+ZoneNrd+" or Direction.ID_Direct_Zone= "+ZoneSud+ ")\n" +
"AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n" +
"AND Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie\n" +
"AND Categorie.Num_Categorie  "+Operator+" "+Categorie;
    
    cnx.connectSqlServer();
    
    float valRepatNord=0;
    float valRepatSud = 0;
        try {
            stm=cnx.getCnx().createStatement();
            resst=stm.executeQuery(Query);
            while (resst.next()) {
                
                //System.out.println("Controller.HelperClass.Fill_repat_decocher()"+resst.getFloat("valeur_Repat"));
               // System.out.println("Controller.HelperClass.Fill_repat_decocher()"+resst.getFloat(2));
                
               
               valRepatNord=resst.getFloat("valeur_Repat");
               resst.next();
               valRepatSud=resst.getFloat("valeur_Repat");
            }
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        try {
            stm.close();
            resst.close();
           // cnx.Deconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        System.out.println("valRepatNord="+valRepatNord);
        System.out.println("valRepatSud="+valRepatSud);
        
        String QueryDecocher="select distinct valeur_decocher \n" +
"from Prix_Decocher,Prix_Decocher_Categorie,Categorie,Direction \n" +
"where \n" +
"Direction.ID_Direct_Zone=Prix_Decocher.ID_Direct_Zone\n" +
"AND (Direction.ID_Direct_Zone= "+ZoneNrd +" or Direction.ID_Direct_Zone="+ZoneSud +")\n" +
"AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher\n" +
"AND Categorie.ID_Categorie=Prix_Decocher_Categorie.ID_Categorie\n" +
"AND Categorie.Num_Categorie  "+Operator+" "+Categorie;
        float valDecochNord=0;
        float valDecochSud=0;
        try {
            stm=cnx.getCnx().createStatement();
            resst=stm.executeQuery(QueryDecocher);
            while (resst.next()) {                
                valDecochNord=resst.getFloat(1);
                resst.next();
                valDecochSud=resst.getFloat(1);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stm.close();
            resst.close();
            cnx.Deconnect();
        } catch (Exception e) {
        }
        System.out.println("valDecochNord="+valDecochNord);
        System.out.println("valDecochNord="+valDecochSud);
        
        Object arg2[]={valRepatSud,valRepatNord,"الغداء"};
        Object arg3[]={valDecochSud,valDecochNord,"العشاء"};
        dfm.addRow(arg2);
        
        if (ZoneNrd!=3 && ZoneSud!=4) {
            dfm.addRow(arg3); 
        }

       tab.setModel(dfm);
       
    }
    
    
    public void Fill_Grade_(JTable tab_Grade){
    String Query="select * from Grade";
        DefaultTableModel dfm=new DefaultTableModel();
        
        dfm=(DefaultTableModel) tab_Grade.getModel();
        dfm.setRowCount(0);
        
    
    cnx.connectSqlServer();
        try {
            stm=cnx.getCnx().createStatement();
            
            resst=stm.executeQuery(Query);
            int i=1;
            while (resst.next()) {                
                
            Object arg[]={resst.getString("ID_Categorie"),resst.getString("Desc_Grade")};
            dfm.addRow(arg);
                i++;
            }
            tab_Grade.setModel(dfm);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            stm.close();
            resst.close();
            cnx.Deconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void Update_Grade(String Grade,int i){
    String QueryUpdt="UPDATE Grade SET Desc_Grade=? where ID_Grade="+i;
    
    
    }
    
    public static void main(String[] args) {
        HelperClass hel=new HelperClass();
        
        hel.Fill_repat_decocher(1,2, 10, "<=",null);
       
    /*    new HelperClass().GetIdZone(" بلدية جمورة", 2);
        Date d1=new Date("10/10/2009");
        Date d2=new Date("14/10/2009");
        
        
        try {
            Date d3=new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2009");
            Date d4= new SimpleDateFormat("dd/MM/yyyy").parse("10/14/2009");
            long Ddif=d4.getTime()-d3.getTime();
            
            
             int   DiferenceNbrDa= (int) (Ddif / (1000 * 60 * 60 * 24));
            System.out.println("gestion_ord_mission.HelperClass.main()"+DiferenceNbrDa);
            
            
        } catch (ParseException ex) {
            Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        long DifferenceDateComplete=d2.getTime()-d1.getTime();
        
       int   DiferenceNbrDay= (int) (DifferenceDateComplete / (1000 * 60 * 60 * 24));
       
       
        
        System.out.println("Nbr Day is :"+DiferenceNbrDay);
       Calcule_val cl=new Calcule_val();
        try {
            cl.calcule_eating_dortoire("10/10/2010", "10/10/2010", "08:00", "22:00");
        } catch (ParseException ex) {
            Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Nbr repat"+cl.getNbreRepat());
        System.out.println("Nbr Decocher"+cl.getNbreDortoire());
        
        HelperClass.CreateOrdMissionDCW();*/
    }
    
    
    
    
    
    static Remplir_Info Remplir_Info_obj=new Remplir_Info();
 public static void CreateOrdMissionDCW(){
     
     SimpleDateFormat formatDate=new SimpleDateFormat("dd/MM/yyyy");
Calcule_val cl = null;
     int nbrrepat = 0,nbrdecoch = 0;
          try {
            Date dfrst=formatDate.parse("10/10/2009");
            Date dlast= formatDate.parse("10/14/2009");
           
           /* long Ddif=dlast.getTime()-dfrst.getTime();
            
            
             int   DiferenceNbrDa= (int) (Ddif / (1000 * 60 * 60 * 24));
            System.out.println("gestion_ord_mission.HelperClass.main()"+DiferenceNbrDa);*/
            
            
             cl=new Calcule_val();
            cl.calcule_eating_dortoire("10/10/2009", "16/10/2009", "06:00", "23:00");
            
            nbrrepat=cl.getNbreRepat();
            nbrdecoch=cl.getNbreDortoire();
                    
        } catch (ParseException ex) {
            Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     int i=0;
     
     
     
          
    Info_Ord Employeur_Info=new Info_Ord("لخضر", "خباش", "10/10/2009", "ing", "ingenieur", "1245", "biskra",
             "14521", "mission adm", "biskra", "alger", "ada", "10/10/2009", "08:00",
             "15/10/2009", "23:00", nbrrepat, nbrdecoch, 1, "145");
        /* cl.calcule_eating_dortoire(Employeur_Info.getDateGo(), Employeur_Info.getDateBack(),
        Employeur_Info.getHeur_Go(), Employeur_Info.getHeur_Back());
        Employeur_Info.setCompensationEat(cl.getNbreRepat());
        Employeur_Info.setCompensationDrt(cl.getNbreDortoire());*/
     
     System.out.println("Initialised of workbook***************************");
     
     Remplir_Info_obj.Remplir_Sheet1(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName(),Employeur_Info.getDepuisMois(),Employeur_Info.getGrade(),Employeur_Info.getJob(),Employeur_Info.getSemanticNumero(),Employeur_Info.getManagementResident(),
                    Employeur_Info.getCCPN_Num(), "", "", "", "", new Date(), "", "", 0 , 0);
     System.out.println("Fill Sheet1 ***********************");
     
    Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName());
    
       System.out.println("Write in workBook*********************");   
       Remplir_Info_obj.Inisialise_Sheet2();
       System.out.println("Initialise Sheet two***********************");    
       long firstTime = 0;
       long endTime = 0;
        try {
             firstTime=formatDate.parse(Employeur_Info.getDateGo()).getTime();
            endTime=formatDate.parse(Employeur_Info.getDateBack()).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        long interval= 24*1000 * 60 * 60;
        
        while (firstTime<=endTime) 
        {         
        
            
       // JOptionPane.showMessageDialog(null, "Iteration in date :"+formatDate.format(new Date(firstTime)));
        
        Employeur_Info.setDateGo(formatDate.format(new Date(firstTime)));
            
        
        Employeur_Info.setDateBack(Employeur_Info.getDateGo());
        
         try {
             cl.calcule_eating_dortoire(Employeur_Info.getDateGo(), Employeur_Info.getDateBack(),
                     Employeur_Info.getHeur_Go(), Employeur_Info.getHeur_Back());
         } catch (ParseException ex) {
             Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
         }
            
            Employeur_Info.setCompensationEat(cl.getNbreRepat());
             Employeur_Info.setCompensationDrt(cl.getNbreDortoire());
        
       // JOptionPane.showMessageDialog(null, "Iteration in date Go:"+Employeur_Info.getDateGo());
       // JOptionPane.showMessageDialog(null, "Iteration in date  Back:"+Employeur_Info.getDateBack());
        
           Remplir_Info_obj.Insialise_ReferenceSh2();
           
           
            System.out.println("Initialise Reference Cell two*******************");    
       
            Remplir_Info_obj.Remplir_Sheet2(Employeur_Info.getCauseTravel(),Employeur_Info.getDepart_Demarer(),
                    Employeur_Info.getDestinataire(),Employeur_Info.getDateGo(),Employeur_Info.getHeur_Go(),
                    Employeur_Info.getDateBack(),Employeur_Info.getHeur_Back(),
                    Employeur_Info.getMoyenTrnsport(),0,0,0,0,
                    Employeur_Info.getCompensationEat(),Employeur_Info.getCompensationEat(),Employeur_Info.getCompensationDrt(),Employeur_Info.getCompensationDrt(),Employeur_Info.getRemarque(),
                    Employeur_Info.getOrientation());
            Remplir_Info_obj.setNum_Line((Remplir_Info_obj.GetNum_Line()+1));    
            firstTime+=interval; 
            
            //JOptionPane.showMessageDialog(null, "Iteration in date  Back:"+formatDate.format(new Date(firstTime)));
            
        }
       
   
            Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName());
      
 System.out.println("Write in workBook***********************************");
            
            try {
                Remplir_Info_obj.RemplirSomDrt();
                JOptionPane.showMessageDialog(null, "After Some ");
                System.out.println("Write in workBook***************RemplirSomDrt()********************");
                Remplir_Info_obj.GetNbrCompensationNrd();
                
                System.out.println("Write in workBook***************GetNbrCompensationNrd()********************");
                 //GetPriceEatANDDecocher(1);
                 GetPriceEatANDDecocherDCW(3);
                 
                 System.out.println("Write in workBook***************GetPriceEatANDDecocher()********************");
                 
                 Remplir_Info_obj.SumCompensationToujours(1,0,0); 
                 System.out.println("Write in workBook***************SumCompensationToujours()********************");
                 Remplir_Info_obj.TotlaSumBenefit();
                 System.out.println("Write in workBook***************TotlaSumBenefit()********************");
                 Remplir_Info_obj.SumTransport_and_compensationTtl();
                 System.out.println("Write in workBook***************SumTransport_and_compensationTtl()********************");
                 Remplir_Info_obj.Date_Delivred();
                 System.out.println("Write in workBook***************Date_Delivred()********************");
                Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName());
     } catch (Exception e) {
         e.printStackTrace();
     }
 
             try{   
          Desktop dt = Desktop.getDesktop();
                   // dt.open(new File("src\\OurFile\\AppClose.xlsx"));
                    dt.open(new File(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName()+".xlsx")); 
                  //  dt.open(new File(""+FullNam.getText()+".xlsx"));
                    
                } catch (IOException ex) {
                   JOptionPane.showMessageDialog(null, "Error in Opened The File");
                }
            
            
 } 
      static  Employeur Person=new Employeur();
        
            public static void GetPriceEatANDDecocherDCW(int Zone){
        
   Connection_DB cnx=new Connection_DB();
        Statement stm=null;
        ResultSet res=null;
        int NbrRpNrd=Remplir_Info_obj.GetNumberRepatNord(); //get number repat of nord sheet 2
       if(NbrRpNrd>0 ){     
                   String requette=" SELECT Prix_Repat.valeur_Repat,Employeur.Name_Emp \n" +
"FROM Employeur,Categorie,Prix_Repat,Prix_Repat_Categorie,Direction\n" +
"\n" +
"WHERE Employeur.ID_Emp="+Person.getId_Emp()+" AND Direction.ID_Direct_Zone=1 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n" +
"      Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat AND\n" +
"	 Prix_Repat.ID_Direct_Zone=Direction.ID_Direct_Zone";
                   
                   
//nouveau requette                    
requette="select valeur_Repat \n" +
"from Prix_Repat,Prix_Repat_Categorie,Categorie,Direction,Employeur,Grade\n" +
"where \n" +
"Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n" +
"AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n" +
"AND Prix_Repat_Categorie.ID_Categorie=Categorie.ID_Categorie\n" +
"AND Categorie.ID_Categorie=Grade.ID_Categorie\n" +
"AND Employeur.ID_Grade=Grade.ID_Grade\n" +
//"AND Employeur.ID_Emp="+Person.getId_Emp()+ "\n" +  //correct values
"AND Employeur.ID_Emp="+13+ "\n" +        
"AND Direction.ID_Direct_Zone=3";
                   
                   
        try {
            cnx.connectSqlServer();
            stm=cnx.getCnx().createStatement();
            res=stm.executeQuery(requette);
            if (res.next()) {
                JOptionPane.showMessageDialog(null, "The Result is :"+res.getDouble("valeur_Repat"));
                Remplir_Info_obj.RemplirePriceOrdMissionNordEat(res.getDouble("valeur_Repat"));
            }else
            {
            JOptionPane.showMessageDialog(null, "No Person here ");
            
            }

            //double valeurInt= res.getDouble("valeur_Repat");
            
           // System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+NbrRpNrd  +"AND Price :"+valeurInt );
            
           // Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
            //for insert price of 
           
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher Eat nORD"+ex.getMessage());
            
            ex.printStackTrace();
        } 
            try {
                stm.close();
                res.close();
                cnx.Deconnect();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error in Close Operation :"+e.getMessage() );   
                e.printStackTrace();
            }
        }
    
    }
         public static void GetPriceEatANDDecocher(int Nord_Sud){
        Person.GetInformationAttribut(10);
    Connection_DB cnx=new Connection_DB();
        Statement stm=null;
        ResultSet res=null;
       // Remplir_Info Remplir_Info_obj=new Remplir_Info();
        int NbrRpNrd=Remplir_Info_obj.GetNumberRepatNord(); //get number repat of nord sheet 2
       if(NbrRpNrd>0 ){     
                   String requette=" SELECT Prix_Repat.valeur_Repat,Employeur.Name_Emp \n" +
"FROM Employeur,Categorie,Prix_Repat,Prix_Repat_Categorie,Direction\n" +
"\n" +
"WHERE Employeur.ID_Emp="+Person.getId_Emp()+" AND Direction.ID_Direct_Zone=1 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n" +
"      Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat AND\n" +
"	 Prix_Repat.ID_Direct_Zone=Direction.ID_Direct_Zone";
                   
                   
//nouveau requette                    
requette="select valeur_Repat \n" +
"from Prix_Repat,Prix_Repat_Categorie,Categorie,Direction,Employeur,Grade\n" +
"where \n" +
"Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n" +
"AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n" +
"AND Prix_Repat_Categorie.ID_Categorie=Categorie.ID_Categorie\n" +
"AND Categorie.ID_Categorie=Grade.ID_Categorie\n" +
"AND Employeur.ID_Grade=Grade.ID_Grade\n" +
"AND Employeur.ID_Emp="+Person.getId_Emp()+ "\n" +
"AND Direction.ID_Direct_Zone=1";
                   
                   
        try {
            cnx.connectSqlServer();
            stm=cnx.getCnx().createStatement();
            res=stm.executeQuery(requette);
            res.next();
            //double valeurInt= res.getDouble("valeur_Repat");
            
           // System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+NbrRpNrd  +"AND Price :"+valeurInt );
            
           // Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
           Remplir_Info_obj.RemplirePriceOrdMissionNordEat(res.getDouble("valeur_Repat")); //for insert price of 
        
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher Eat nORD"+ex.getMessage());
            
            ex.printStackTrace();
        } 
            try {
                stm.close();
                res.close();
                cnx.Deconnect();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error in Close Operation :"+e.getMessage() );   
                e.printStackTrace();
            }
        }
        /****************************************************************************************/
        if (Remplir_Info_obj.GetNumberDecocherNord()>0) {
                        String requette="SELECT Prix_Decocher.valeur_decocher,Employeur.Name_Emp \n" +
"FROM Employeur,Categorie,Prix_Decocher,Prix_Decocher_Categorie,Direction\n" +
"\n" +
"WHERE Employeur.ID_Emp="+Person.getId_Emp()+" AND Direction.ID_Direct_Zone=1 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n" +
"      Categorie.ID_Categorie=Prix_Decocher_Categorie.ID_Categorie AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher AND\n" +
"	 Prix_Decocher.ID_Direct_Zone=Direction.ID_Direct_Zone";
                        
requette="select valeur_decocher\n" +
"from Prix_Decocher,\n" +
"Prix_Decocher_Categorie,Categorie,Direction,Employeur,Grade\n" +
"where\n" +
"Direction.ID_Direct_Zone=Prix_Decocher.ID_Direct_Zone\n" +
"AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher\n" +
"AND Prix_Decocher_Categorie.ID_Categorie=Categorie.ID_Categorie\n" +
"AND Categorie.ID_Categorie=Grade.ID_Categorie\n" +
"AND Employeur.ID_Grade=Grade.ID_Grade\n" +
"AND Employeur.ID_Emp="+Person.getId_Emp()+"\n" +
"AND Direction.ID_Direct_Zone=1";
                        
                   
        try {
            cnx.connectSqlServer();
            stm=cnx.getCnx().createStatement();
            res=stm.executeQuery(requette);
            res.next();
            //double valeurInt= res.getDouble("valeur_decocher");
            //res.getDouble("valeur_Repat");
            //Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
            Remplir_Info_obj.RemplirePriceOrdMissionNordDecocher(res.getDouble("valeur_decocher"));//For enter Price Decochrer
            
            //System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+Remplir_Info_obj.GetNumberDecocherNord()  +"AND Price :"+valeurInt );
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher  DECOCHER nord "+ex.getMessage());
        } 
            try {
                stm.close();
                res.close();
                
                cnx.Deconnect();
            } catch (Exception e) {
            }
        }
        /**************************************************************************/
        
        
        /****The New Code **********23/04/2019**/
        if (Nord_Sud!=1) {
            
            
        if (Remplir_Info_obj.GetNumberRepatSud()>0) {
                                 String requette="SELECT Prix_Repat.valeur_Repat,Employeur.Name_Emp \n" +
"FROM Employeur,Categorie,Prix_Repat,Prix_Repat_Categorie,Direction\n" +
"\n" +
"WHERE Employeur.ID_Emp="+Person.getId_Emp()+" AND Direction.ID_Direct_Zone=2 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n" +
"      Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat AND\n" +
"	 Prix_Repat.ID_Direct_Zone=Direction.ID_Direct_Zone";
                   
     requette="select valeur_Repat \n" +
"from Prix_Repat,Prix_Repat_Categorie,Categorie,Direction,Employeur,Grade\n" +
"where \n" +
"Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n" +
"AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n" +
"AND Prix_Repat_Categorie.ID_Categorie=Categorie.ID_Categorie\n" +
"AND Categorie.ID_Categorie=Grade.ID_Categorie\n" +
"AND Employeur.ID_Grade=Grade.ID_Grade\n" +
"AND Employeur.ID_Emp="+Person.getId_Emp()+ "\n" +
"AND Direction.ID_Direct_Zone=2";                        
                                 
                                 
        try {
            cnx.connectSqlServer();
            stm=cnx.getCnx().createStatement();
            res=stm.executeQuery(requette);
            res.next();
           // double valeurInt= res.getDouble("valeur_Repat");
            //res.getDouble("valeur_Repat");
            //Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
            Remplir_Info_obj.RemplirePriceOrdMissionSudEat(res.getFloat("valeur_Repat"));
            
             //System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+Remplir_Info_obj.GetNumberRepatSud()  +"AND Price :");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher eAT sud "+ex.getMessage());
        } 
            try {
                stm.close();
                res.close();
                
                cnx.Deconnect();
            } catch (Exception e) {
            }
        }
        /*************************************************************************/
        if (Remplir_Info_obj.GetNumberDecocherSud()>0) {
                                 String requette="SELECT Prix_Decocher.valeur_decocher,Employeur.Name_Emp \n" +
"FROM Employeur,Categorie,Prix_Decocher,Prix_Decocher_Categorie,Direction\n" +
"\n" +
"WHERE Employeur.ID_Emp="+Person.getId_Emp()+" AND Direction.ID_Direct_Zone=2 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n" +
"      Categorie.ID_Categorie=Prix_Decocher_Categorie.ID_Categorie AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher AND\n" +
"	 Prix_Decocher.ID_Direct_Zone=Direction.ID_Direct_Zone";
                   
         requette="select valeur_decocher\n" +
"from Prix_Decocher,\n" +
"Prix_Decocher_Categorie,Categorie,Direction,Employeur,Grade\n" +
"where\n" +
"Direction.ID_Direct_Zone=Prix_Decocher.ID_Direct_Zone\n" +
"AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher\n" +
"AND Prix_Decocher_Categorie.ID_Categorie=Categorie.ID_Categorie\n" +
"AND Categorie.ID_Categorie=Grade.ID_Categorie\n" +
"AND Employeur.ID_Grade=Grade.ID_Grade\n" +
"AND Employeur.ID_Emp="+Person.getId_Emp()+"\n" +
"AND Direction.ID_Direct_Zone=2";
                                 
        try {
            cnx.connectSqlServer();
            stm=cnx.getCnx().createStatement();
            res=stm.executeQuery(requette);
            res.next();
            //double valeurInt= res.getFloat("valeur_decocher");
            //res.getDouble("valeur_Repat");
           // Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
           Remplir_Info_obj.RemplirePriceOrdMissionSudDecocher(res.getDouble("valeur_decocher"));
            //System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+Remplir_Info_obj.GetNumberDecocherSud()  +"AND Price :" );
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher DecocheSUD"+ex.getMessage());
        } 
            try {
                stm.close();
                res.close();
                
                cnx.Deconnect();
            } catch (Exception e) {
            }
            
        }
            
        }
  }
   public int GetID_Grade(String NameGrad){
   String Query ="SELECT ID_Grade from Grade  where Desc_Grade=N'"+NameGrad+"'";
   //Statement stm=null;
   int id_grade=0;
   cnx.connectSqlServer();
   try {
   stm=cnx.getCnx().createStatement();
   resst=stm.executeQuery(Query);
       if (resst.next()) {
           id_grade=resst.getInt("ID_Grade");
       }
    } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Error in Get Id Grade "+e.getMessage());
       }
       try {
           stm.close();
           resst.close();
           cnx.Deconnect();
       } catch (Exception e) {
           e.printStackTrace();
       }
   return id_grade;
   } 
   
   
   public String GetNameGrade(int Id_Grade)
   {
   String Query="SELECT Desc_Grade from Grade  where ID_Grade ="+Id_Grade;
   
    String Name_grade="";
   cnx.connectSqlServer();
   try {
   stm=cnx.getCnx().createStatement();
   resst=stm.executeQuery(Query);
       if (resst.next()) {
           Name_grade=resst.getString("Desc_Grade");
       }
    } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Error in Get Id Grade "+e.getMessage());
       }
       try {
           stm.close();
           resst.close();
           cnx.Deconnect();
       } catch (Exception e) {
           e.printStackTrace();
       }
   return Name_grade;
   
   
  }
   
   
   
 public void Delete_Grade(String Desc_Grade){
     String Query="Delete FROM Grade WHERE Desc_Grade=N'"+Desc_Grade+"'";
     PreparedStatement prs=null;
     
     cnx.connectSqlServer();
     try {
        prs=cnx.getCnx().prepareStatement(Query);
        int x=prs.executeUpdate();
         if (x>0) {
             JOptionPane.showMessageDialog(null,  "Successful in delete");
         }else {
         JOptionPane.showMessageDialog(null, "Error in deleting ");
         }
     
     } catch (SQLException e) {
         e.printStackTrace();
     }
    
     try {
         prs.close();
         cnx.getCnx();
     } catch (SQLException e) {
     }
     
    }
 
 
 public String GetDistinataire(int Id_dest){
 String Query="SELECT Nam_Des FROM destination where Id_dest= "+Id_dest;
 cnx.connectSqlServer();
 String Dest="";
     try {
         stm=cnx.getCnx().createStatement();
         resst=stm.executeQuery(Query);
         if (resst.next()) {
           Dest=resst.getString("Nam_Des");
             
         }
     } catch (Exception e) {
         e.printStackTrace();
     }
     try {
         stm.close();
         resst.close();
         cnx.Deconnect();
     } catch (Exception e) {
     }
 return Dest;
 }
 
 
 public void FillTab_Commune(JTable tabCom){
     
  DefaultTableModel df=(DefaultTableModel) tabCom.getModel();
  df.setRowCount(0);
  
 String Query="SELECT Nam_Des,ID_Direct_Zone FROM destination WHERE destination.ID_TypDis=2 ";
 cnx.connectSqlServer();
 try {
     stm=cnx.getCnx().createStatement();
     resst=stm.executeQuery(Query);
     String Zone_Dir="";
     String Distance="";
     while (resst.next()) {   
         
         if (resst.getInt("ID_Direct_Zone")==1) {
             Zone_Dir="شمال";Distance="فوق 50 كلم" ;
         }else if (resst.getInt("ID_Direct_Zone")==2) {
             Zone_Dir="جنـوب";Distance="فوق 50 كلم" ;
         }else if (resst.getInt("ID_Direct_Zone")==3) {
              Zone_Dir="شمال";Distance="أقل من 50 كلم" ;
         }else{
         Zone_Dir="جنـوب";Distance="أقل من 50 كلم" ;
         }
         
      String arg[]={Distance,Zone_Dir,resst.getString("Nam_Des")};   
         df.addRow(arg);
     }
     tabCom.setModel(df);
     } catch (SQLException e) {
     e.printStackTrace();
     }
 
     try {
        stm.close();
        resst.close();
        cnx.Deconnect();
     } catch (SQLException e) {
     e.printStackTrace();
     }
 }
 /******************************************************************************/
 
 
}
