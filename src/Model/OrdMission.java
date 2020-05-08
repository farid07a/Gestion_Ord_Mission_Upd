/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Calcule_val;
import Controller.Connection_DB;
import Model.Employeur;
import Model.Info_Ord;
import View.Home;
import View.Ok1;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author horizon
 */
public class OrdMission {

private Date DateGo;
private Date DateBack;
private int Num_OrdMission;
private Date DateOrdMission;
private int id_emp;
private int ID_Voiture;
private int ID_Direct_Zone;
private int Id_task;
private boolean  stat;
private int prctge;
private Date HeurDepart;
private Date HeurRetour;
private int Id_dest;

private int NbrRepat;
private int NbrDecocher;
private double Price_Of_OrdMission;

private double price_Repat;
private double price_Decocher;
private Connection_DB Obj_Cnx=new Connection_DB();
//private int Price;
private Employeur employeur=new  Employeur();
Info_Ord info_Ord=new Info_Ord();
Home home_obj;
    Ok1 ok;
public OrdMission(int Num_OrdMission,Date DateOrdMission,Date DateGo,Date DateBack,int id_emp,
        int ID_Voiture,int Id_task,boolean stat,int prctge,Date HeurDepart,Date HeurRetour,int Id_dest,Home h){
    
//    insert into OrdMission(Num_OrdMission,Date_OrdMission,DateDepart,DateRetour,"
//                + "ID_Emp,ID_Voiture,Id_task,stat,prctge,HeurDepart,HeurRetour,Id_dest)"
//                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

this.Num_OrdMission=Num_OrdMission;
this.DateOrdMission=DateOrdMission;
this.DateGo=DateGo;
this.DateBack=DateBack;
this.id_emp=id_emp;
this.ID_Voiture=ID_Voiture;
this.Id_task=Id_task;
this.stat=stat;
this.prctge=prctge;
this.HeurDepart=HeurDepart;
this.HeurRetour=HeurRetour;
this.Id_dest=Id_dest;

home_obj=h;
}

    public OrdMission() {
        
        //home_obj=h;
        }
 
    public void calcule_eating_dortoire(String dateStart,String dateStop,String HeurStop,String HeurStart ) throws ParseException{
     
         String format = "dd/MM/yyyy HH:mm";
        
        SimpleDateFormat SmpDatFrmComplete = new SimpleDateFormat(format);
        
        
    Date DepartDayComplete=SmpDatFrmComplete.parse(dateStart+" "+HeurStart);
        
        Date ArriveDayComple=SmpDatFrmComplete.parse(dateStop+" "+HeurStop);
    int DiferenceNbrDay;
        
        long DifferenceDateComplete=ArriveDayComple.getTime()-DepartDayComplete.getTime();
        SimpleDateFormat formatDate=new SimpleDateFormat("dd/MM/yyyy");
          Date DepartDay=formatDate.parse(dateStart);
        
        
             Date ArriveDay=formatDate.parse(dateStop);
             
             Date DepartHour,ArriveHour;
        
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        DepartHour=parser.parse(HeurStart);
        ArriveHour=parser.parse(HeurStop);    
    
         Date TimeLunch1=parser.parse("11:29");
         Date TimeLunch2=parser.parse("14:01");
        Date TimeDinner1=parser.parse("17:59");
         Date TimeDinner2=parser.parse("21:01");
         
         Date TimeDechoche=parser.parse("00:00");
        
        
        int NbrRepat=0,NbrDecocher=0;
         DiferenceNbrDay= (int) (DifferenceDateComplete / (1000 * 60 * 60 * 24));
        long DifrencheHours=DifferenceDateComplete/ (60 * 60 * 1000);               
        int mod=(int) (DifrencheHours%24);
        
        System.out.println("The Days in complete date is:"+DiferenceNbrDay);
        System.out.println("The hours in complete date is:"+DifrencheHours);
        System.out.println("The Mode difrhours 24 :"+mod);
        System.out.println("**********************************************************");
        
        if (DiferenceNbrDay>0) {
         
            NbrRepat=DiferenceNbrDay*2;
            NbrDecocher=DiferenceNbrDay;
            
            if(mod!=0){
            
                     
        if      ( (DepartHour.before(TimeLunch2/*<13:30*/)&& ( ArriveHour.before( TimeDinner1/*<18:30*/ )&&((DepartDay.equals(ArriveDay))))&&(ArriveHour.after(DepartHour))) ) //dep <11 && ret<18:00
                {
            
                    System.out.println("The first if => <11:30 et <18:30   ");
                NbrRepat++;                                                    //nbrRepat =1;
                }
       /*****************************************************************************/
        else 
        
        if      (
                DepartHour.before(TimeLunch2 /*<13:30*/)     //time de depart avant le 13:30
                
                        &&                                   //et 
                
                (ArriveHour.after  (TimeDinner1)             //heur de retour est apres le 18:30
                                            ||               // ou comme mm 
                                    (
                                        ArriveHour.before(DepartHour) //si le heur de depart apres l'heur de arrive donc la date de depart avant la date de retour
                                                        && 
                                        DepartDay.before(ArriveDay)
                                    ) // ce test pour confirmer le cas de heur depart apres le retour 
                                   ) 
                )   //Deprt <11 && retr >
                {
                    System.out.println("The second if => depart < 13:30 et > 18:30 ");
                    
                    NbrRepat=NbrRepat+2;                                        //repat +2; Lunch+Dinner
                    
                    if (ArriveHour.after(TimeDechoche) && (DepartDay.before(ArriveDay)&&ArriveHour.before(DepartHour))  )  //retour >00:00 Nbr Decch++, (si hr_arrive>00:00 ou [herDepart>HrArrive && dateDepa<DateArrive])
                              {
                                                            //ce cas le temp darrive ne depasse pas le 13:30
                                                            //mais peut etre le arrive entre 11:30 & 13:30
                                                            //et comme meme peut etre arrive avant le 11:30
                               NbrDecocher++;   //ajouter 1 pour decocher 
                               
                                  System.out.println("the if n 2 in decocher  time retour> 00:00");
                                  
                                  if((ArriveHour.before(DepartHour) && DepartDay.before(ArriveDay))){
                        
                                //ajouter 1 pour decocher 
                               
                               
                                        if(ArriveHour.after(TimeLunch1) )    //peut etre le  temp de arrive >=11:30
                                        {
                                        NbrRepat++;    

                                        }
                    
                                    }
                                     
                              }
             }
         /*****************************************************************************/ 
        else
        
        if      ( (DepartHour.after(TimeLunch2)  &&  DepartHour.before(TimeDinner1) )   // "dep > 13:30 && dep< 18:30" && 
                &&
                                    (ArriveHour.after(TimeDinner1)  
                                        ||                                 // ou comme mm 
                                        (
                                        ArriveHour.before(DepartHour)      //si le heur de depart apres l'heur de arrive donc la date de depart avant la date de retour
                                                        && 
                                        DepartDay.before(ArriveDay)
                                        ) 
                                    )
                 )                         // retr >18:30
                {
                //dans ce cas le depart a >13:31 && <18:00  donc le retour official <= 18:00 
                //peut etre l'arrive avant 11:30 et avant 18:30
                //quand mm arrive avant >11:30
                    System.out.println("The third if ******************************************");
                NbrRepat++;
                    
                       if (  (ArriveHour.after(TimeDechoche) && (DepartDay.before(ArriveDay)&&(ArriveHour.before(DepartHour))) )   )                           //ret> 00:00 et <<01:00 => NbrDecocher++
                              {
                              //|| (  (DepartHour.after(ArriveHour)&&DepartDay.before(ArriveDay))
                                NbrDecocher++;   //ajouter 1 pour decocher 
                               
                     
                                   if(ArriveHour.after(TimeLunch1) ){ //peut etre le  temp de arrive >=11:30
                                     NbrRepat++;                   
                               }
                               
                              } 
             /*****************************************************************************/
        else 
        
        if      (((DepartHour.after(TimeDinner1))  && //[dep>=18:00 && dep <21:30] &&[arrv >00:00]  
                            (DepartHour.before(TimeDinner2))) 
                   
                &&
                     ((ArriveHour.after(TimeDechoche)&&(DepartDay.before(ArriveDay)))  
                
                                        ||               // ou comme mm 
                                        
                                        (
                                        ArriveHour.before(DepartHour) //si le heur de depart apres l'heur de arrive donc la date de depart avant la date de retour
                                                        && 
                                        DepartDay.before(ArriveDay)
                                        ) 
                                    )
                 )    
                    {
                    NbrRepat++;
                    System.out.println("The fourth if ***********************************");
                    //dans ce cas le depart est >18:30 && <21:30 donc le retour ne depasse pas 21:30
                    //est peut etre retour avat 11:30 et 18:30
                    
                    
                    if(ArriveHour.after(TimeDechoche) && ( DepartDay.before(ArriveDay)&&(ArriveHour.before(DepartHour)) )  )
                    {
                        
                        NbrDecocher++;
                        
                     if((ArriveHour.before(DepartHour) && DepartDay.before(ArriveDay))){
                        
                        if (ArriveHour.after(TimeLunch1)) {
                            
                            NbrRepat++;
                            
                            if (ArriveHour.after(TimeDinner1)) {
                                NbrRepat++;
                            }
                            
                        }    
                    }
                    }
                }
                       
                }
       /**********************************************************************************************************/
        
        else if (DepartHour.after(TimeDinner2)
                
                &&
                (               
                                ((ArriveHour.after(TimeDechoche)&&(DepartDay.before(ArriveDay))))
                                    ||
                                (ArriveHour.before(DepartHour) && DepartDay.before(ArriveDay))
                                
                                )
                
                )
                {
               System.out.println("The five  if ***********************************");
                
                 if(ArriveHour.after(TimeDechoche) && ( DepartDay.before(ArriveDay)&&(ArriveHour.before(DepartHour)) ))
                    {
                        
                        NbrDecocher++;
                        
                     if((ArriveHour.before(DepartHour) && DepartDay.before(ArriveDay))){
                        
                        if (ArriveHour.after(TimeLunch1)) {
                            
                            NbrRepat++;
                            
                            if (ArriveHour.after(TimeDinner1)) {
                                NbrRepat++;
                            }    
                        }     
                    }
                    }
                  }else {
            System.out.println("Any time in condition");
                }
       
            }//ferme { de mod
            
            
            
        }else if (DiferenceNbrDay ==0){
        
        
            System.out.println("I'am in nbre Day is ==0");
            
                 
        if      ( (DepartHour.before(TimeLunch2/*<13:30*/)&& ( ArriveHour.before( TimeDinner1/*<18:30*/ )&&((DepartDay.equals(ArriveDay))))&&(ArriveHour.after(DepartHour))) ) //dep <11 && ret<18:00
                {
            
                    System.out.println("The first if => <11:30 et <18:30   ");
                NbrRepat++;                                                    //nbrRepat =1;
                }
        
       /*****************************************************************************/
        else 
        
        if      (
                DepartHour.before(TimeLunch2 /*<13:30*/)     //time de depart avant le 13:30
                
                        &&                                   //et 
                
                (ArriveHour.after  (TimeDinner1)             //heur de retour est apres le 18:30
                                            ||               // ou comme mm 
                                    (
                                        ArriveHour.before(DepartHour) //si le heur de depart apres l'heur de arrive donc la date de depart avant la date de retour
                                                        && 
                                        DepartDay.before(ArriveDay)
                                    ) // ce test pour confirmer le cas de heur depart apres le retour 
                
                                   ) 
                
                )   //Deprt <11 && retr >
            
                {
   
                    System.out.println("The second if => depart < 13:30 et > 18:30 ");
                    
                    NbrRepat=NbrRepat+2;                                        //repat +2; Lunch+Dinner
                    
                    if (ArriveHour.after(TimeDechoche) && (DepartDay.before(ArriveDay)&& ArriveHour.before(DepartHour))  )  //retour >00:00 Nbr Decch++, (si hr_arrive>00:00 ou [herDepart>HrArrive && dateDepa<DateArrive])
                              {
                                                            //ce cas le temp darrive ne depasse pas le 13:30
                                                            //mais peut etre le arrive entre 11:30 & 13:30
                                                            //et comme meme peut etre arrive avant le 11:30
                               NbrDecocher++;   //ajouter 1 pour decocher 
                               
                                  System.out.println("the if n 2 in decocher  time retour> 00:00");
                                  
                                  if((ArriveHour.before(DepartHour) && DepartDay.before(ArriveDay))){
                        
                                //ajouter 1 pour decocher 
                               
                               
                                        if(ArriveHour.after(TimeLunch1) )    //peut etre le  temp de arrive >=11:30
                                        {
                                        NbrRepat++;    

                                        }
                    
                                    }
                                     
                              }
             }
         /*****************************************************************************/ 
        else
        
        if      ( (DepartHour.after(TimeLunch2)  &&  DepartHour.before(TimeDinner1) )   // "dep > 13:30 && dep< 18:30" && 
                &&
                                    (ArriveHour.after(TimeDinner1)  
                                        ||                                 // ou comme mm 
                                        (
                                        ArriveHour.before(DepartHour)      //si le heur de depart apres l'heur de arrive donc la date de depart avant la date de retour
                                                        && 
                                        DepartDay.before(ArriveDay)
                                        ) 
                                    )
                 )                         // retr >18:30
                {
                //dans ce cas le depart a >13:31 && <18:00  donc le retour official <= 18:00 
                //peut etre l'arrive avant 11:30 et avant 18:30
                //quand mm arrive avant >11:30
                    System.out.println("The third if ******************************************");
                NbrRepat++;
                    
                       if (  (ArriveHour.after(TimeDechoche) && DepartDay.before(ArriveDay) )   )                           //ret> 00:00 et <<01:00 => NbrDecocher++
                              {
                              //|| (  (DepartHour.after(ArriveHour)&&DepartDay.before(ArriveDay))
                                NbrDecocher++;   //ajouter 1 pour decocher 
                               
                     
                                   if(ArriveHour.after(TimeLunch1) ){ //peut etre le  temp de arrive >=11:30
                                     NbrRepat++;                   
                               }
                               
                              } 
             /*****************************************************************************/
        else 
        
        if      (((DepartHour.after(TimeDinner1))  && //[dep>=18:00 && dep <21:30] &&[arrv >00:00]  
                            (DepartHour.before(TimeDinner2))) 
                   
                &&
                     ((ArriveHour.after(TimeDechoche)&&(DepartDay.before(ArriveDay)))  
                
                                        ||               // ou comme mm 
                                        
                                        (
                                        ArriveHour.before(DepartHour) //si le heur de depart apres l'heur de arrive donc la date de depart avant la date de retour
                                                        && 
                                        DepartDay.before(ArriveDay)
                                        ) 
                                    )
                 )    
                    {
                    NbrRepat++;
                    System.out.println("The fourth if ***********************************");
                    //dans ce cas le depart est >18:30 && <21:30 donc le retour ne depasse pas 21:30
                    //est peut etre retour avat 11:30 et 18:30
                    
                    
                    if((ArriveHour.after(TimeDechoche) && DepartDay.before(ArriveDay) ) )
                    {
                        
                        NbrDecocher++;
                        
                     if((ArriveHour.before(DepartHour) && DepartDay.before(ArriveDay))){
                        
                        if (ArriveHour.after(TimeLunch1)) {
                            
                            NbrRepat++;
                            
                            if (ArriveHour.after(TimeDinner1)) {
                                NbrRepat++;
                            }
                            
                        }    
                    }
                    }
                }
                       
                }
       /**********************************************************************************************************/
        
        else if (DepartHour.after(TimeDinner2)
                
                &&
                (               
                                ((ArriveHour.after(TimeDechoche)&&(DepartDay.before(ArriveDay))))
                                    ||
                                (ArriveHour.before(DepartHour) && DepartDay.before(ArriveDay))
                                
                                )
                
                )
                {
               System.out.println("The five  if ***********************************");
                
                 if((ArriveHour.after(TimeDechoche) && DepartDay.before(ArriveDay) ) )
                    {
                        
                        NbrDecocher++;
                        
                     if((ArriveHour.before(DepartHour) && DepartDay.before(ArriveDay))){
                        
                        if (ArriveHour.after(TimeLunch1)) {
                            
                            NbrRepat++;
                            
                            if (ArriveHour.after(TimeDinner1)) {
                                NbrRepat++;
                            }    
                        }     
                    }
                    }
                  }else {
            System.out.println("Any time in condition");
        }
       
        
        }
        
        
        
        
        /************FIN OF PART 1 *****************************************************/    
        
        
        System.out.println("The number repat is :"+NbrRepat);
        System.out.println("The number Decocher is:"+NbrDecocher);
        
        //compensationDrt=NbrDecocher;
        
        
        
    }

    public void insertOrdMission(){
        PreparedStatement prstm=null;
        ResultSet res=null;
        String Query="insert into OrdMission(Num_OrdMission,Date_OrdMission,DateDepart,DateRetour,"
                + "ID_Emp,ID_Voiture,Id_task,stat,prctge,HeurDepart,HeurRetour,Id_dest)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        getCnx().connectSqlServer();
        try {
            prstm=getCnx().getCnx().prepareStatement(Query);
            prstm.setInt(1, Num_OrdMission);
            prstm.setDate(2, new java.sql.Date(DateOrdMission.getTime()));
            
            prstm.setDate(3, new java.sql.Date(DateGo.getTime()));
            
            if (DateBack==null) {
                 prstm.setDate(4, null);
            }else {
            
             prstm.setDate(4, new java.sql.Date(DateBack.getTime()));
            }
            prstm.setInt(5,id_emp);
            prstm.setInt(6,ID_Voiture);
            prstm.setInt(7,Id_task);
            prstm.setBoolean(8, stat);
            prstm.setInt(9,prctge);
            SimpleDateFormat format=new SimpleDateFormat("HH:mm");
             // Date d=new Date("10/10/2009 23:24");
            prstm.setTimestamp(10, new java.sql.Timestamp(HeurDepart.getTime()));
              //Date T=new Date("10/10/2009 23:24");
            
              if (HeurRetour==null) {
                 prstm.setTimestamp(11, null);
            }else {
              
               prstm.setTimestamp(11, new java.sql.Timestamp(HeurRetour.getTime()));
              }
              
             
            
            prstm.setInt(12, Id_dest);
              int x=prstm.executeUpdate();
              if (x>0) {
                  //JOptionPane.showMessageDialog(null, "Successful add");
                  ok=new Ok1(home_obj, true, "تمت اضـافة المهمة بنجاح");
                    ok.setVisible(true);
                  home_obj.InitialisePanCalOrdMiss();
            }else {
              JOptionPane.showMessageDialog(null, "Error in Add");
              }
              prstm.close();
            System.out.println("gestion_ord_mission.Info_Ord.insertOrdMission()");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null  , e.getMessage());
        }
        try {
            getCnx().Deconnect();
            
        } catch (Exception e) {
        }
        
    }
    
    public void Update_OrdMission(int Num_OrdMiss){
    
     PreparedStatement prstm=null;
     String Query="UPDATE OrdMission SET Num_OrdMission=?,Date_OrdMission=?,DateDepart=?,DateRetour=?,ID_Emp=?,ID_Voiture=?,"
             + " Id_task=? , stat=? ,prctge=? ,HeurDepart=? ,HeurRetour=? ,Id_dest=?  WHERE Num_OrdMission= "+Num_OrdMiss;
        getCnx().connectSqlServer();
        try {
            prstm=Obj_Cnx.getCnx().prepareStatement(Query);
            
            
             prstm.setInt(1, Num_OrdMission);
            prstm.setDate(2, new java.sql.Date(DateOrdMission.getTime()));
            
            prstm.setDate(3, new java.sql.Date(DateGo.getTime()));
            
            if (DateBack==null) {
                 prstm.setDate(4, null);
            }else {
            
             prstm.setDate(4, new java.sql.Date(DateBack.getTime()));
            }
             prstm.setInt(5,id_emp);
            prstm.setInt(6,ID_Voiture);
            prstm.setInt(7,Id_task);
            prstm.setBoolean(8, stat);
            prstm.setInt(9,prctge);
            SimpleDateFormat format=new SimpleDateFormat("HH:mm");
             // Date d=new Date("10/10/2009 23:24");
            prstm.setTimestamp(10, new java.sql.Timestamp(HeurDepart.getTime()));
              //Date T=new Date("10/10/2009 23:24");
            
              if (HeurRetour==null) {
                 prstm.setTimestamp(11, null);
            }else {
              
               prstm.setTimestamp(11, new java.sql.Timestamp(HeurRetour.getTime()));
              }
            prstm.setInt(12, Id_dest);
              int x=prstm.executeUpdate();
              if (x>0) {
                  ok=new Ok1(home_obj, true, "تمت تعديــل المهمة بنجاح");
                    ok.setVisible(true);
                  home_obj.InitialisePanCalOrdMiss();
            }else {
              JOptionPane.showMessageDialog(null, "No Update");
              }
            
        prstm.close();
            
        } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Error In update OrdMission"+e.getMessage());
        }
     
        try {
            Obj_Cnx.Deconnect();
            
        } catch (Exception e) {
        }
     
     
//    Num_OrdMission,Date_OrdMission,DateDepart,DateRetour,"
//                + "ID_Emp,ID_Voiture,Id_task,stat,prctge,HeurDepart,HeurRetour,Id_dest)"
    }
    
    
  public void GetPrice(){
      
      Statement stm=null;
      ResultSet res=null;
      Obj_Cnx.connectSqlServer();
  String requette="select valeur_Repat \n" +
"from Prix_Repat,Prix_Repat_Categorie,Categorie,Direction,Employeur,Grade\n" +
"where \n" +
"Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n" +
"AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n" +
"AND Prix_Repat_Categorie.ID_Categorie=Categorie.ID_Categorie\n" +
"AND Categorie.ID_Categorie=Grade.ID_Categorie\n" +
"AND Employeur.ID_Grade=Grade.ID_Grade\n" +
"AND Employeur.ID_Emp=12 \n" +
"AND Direction.ID_Direct_Zone=1";
  
      try {
          stm=Obj_Cnx.getCnx().createStatement();
          res=stm.executeQuery(requette);
          
          if (res.next()) {
              System.out.println("gestion_ord_mission.OrdMission.GetPrice()"+res.getDouble(1));
              
          }
          
          
      } catch (SQLException e) {
          e.printStackTrace();
      }
      try {
          stm.close();
          res.close();
          
      } catch (SQLException e) {
      }
      Obj_Cnx.Deconnect();
  }
  
  public void DifferenceDate(String Date1,String Date2){
  
      Calendar clendar=Calendar.getInstance();
  }
  public void Delete_OrdMission(int NumOrdMiss){
  String Query="delete from OrdMission where Num_OrdMission="+NumOrdMiss;
  PreparedStatement prstm=null;
  Obj_Cnx.connectSqlServer();
      try {
          prstm=Obj_Cnx.getCnx().prepareStatement(Query);
          int x=prstm.executeUpdate();
          System.out.println("Model.OrdMission.Delete_OrdMission. Execute Query done");
          if (x>0) {
              ok=new Ok1(home_obj, true, "تمت حذف المهمة بنجاح");
              ok.setVisible(true);
          }else {
          JOptionPane.showMessageDialog(null, "No element Deleted ");
          }
      } catch (SQLException e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(null, "Error in Delete ORD Mission "+e.getMessage());
      }
      
      try {
          prstm.close();
          Obj_Cnx.Deconnect();
      } catch (Exception e) {
      }
  
  }
  public static void main(String[] args) {
     
      //new OrdMission().GetPrice();
      
          Calendar cl=Calendar.getInstance();
          
          
          SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");
          
    try {
        cl.setTime(format.parse("14/10/2019"));
        System.out.println("gestion_ord_mission.OrdMission.main()"+cl.getTime());
        
        Date dateStart =cl.getTime();
        Date DateEnd=format.parse("19/10/2019");
        
        long  interval = 24*1000 * 60 * 60;
        
        long firstTime=dateStart.getTime();
        long endTime=DateEnd.getTime();
        while (firstTime<=endTime) {            
            
            Date daf=new Date(firstTime);
            System.out.println("The Date is "+format.format(daf));
            firstTime+=interval;
        }
        
        
        
        
        
    } catch (ParseException ex) {
        Logger.getLogger(OrdMission.class.getName()).log(Level.SEVERE, null, ex);
    }
          
          
          
          
          
          
          System.out.println("*******"+cl.getTime());
          
         // GregorianCalendar gcal=GregorianCalendar.from(zdt)
          
      Date dp=new Date();
      dp.setHours(10);
      dp.setHours(30);
      
      
      
      
      //    new OrdMission(6, dp, dp, dp, 1, 1, 1, true, 100, new Date(), new Date(), 1).insertOrdMission();
        //new OrdMission().DisplayAllInfOrdMission();
        
          System.out.println("The Values is :"+new Date("08/09/2009 12:13").getMinutes());
          SimpleDateFormat frm=new SimpleDateFormat("dd/MM/yyyy HH:mm");
    try {
        Date d=frm.parse("10/08/2009 23:50");
        System.out.println("gestion_ord_mission.OrdMission.main()"+ dp);
    } catch (ParseException ex) {
        Logger.getLogger(OrdMission.class.getName()).log(Level.SEVERE, null, ex);
    }
    
//    new OrdMission().Calcule_Price_OrdMission(101);
    
    }

public void FillTableOrdMission(JTable Tab,int Zone,int Dist){
    DefaultTableModel df=new DefaultTableModel();
    String Choice=" ";
    String Destination=" ";
  /*  if (Zone==0) {
        Choice=" AND ID_Direct_Zone >=2 ";
        
    }else {
    Choice=" AND ID_Direct_Zone <2 ";
    }*/
    String Query="SELECT ID_OrdMission,Num_OrdMission,Name_Emp,Last_Nm_Emp,"
            + " Desc_Grade,DateDepart,DateRetour,Residance_Mng,"
            + " Nom_Voiture,DescriptionTask_AR,stat"
            + " FROM OrdMission,Employeur,Grade,Tasktype,Categorie,Moyen_Transport"
            + " WHERE OrdMission.ID_Emp=Employeur.ID_Emp "
            + " AND Moyen_Transport.ID_Voiture=OrdMission.ID_Voiture "
            + " AND Categorie.ID_Categorie=Grade.ID_Categorie "
            + " AND Employeur.ID_Grade=Grade.ID_Grade "
            + " AND OrdMission.Id_task=Tasktype.Id_task";
    
    
    String QuerySup_50=
" SELECT ID_OrdMission,Num_OrdMission,Name_Emp,Last_Nm_Emp,\n" +
" Desc_Grade,DateDepart,DateRetour,Residance_Mng,Nam_Des,\n" +
" Nom_Voiture,DescriptionTask_AR,stat\n" +
" FROM OrdMission,Employeur,Grade,Tasktype,Categorie,Moyen_Transport,destination\n" +
" WHERE OrdMission.ID_Emp=Employeur.ID_Emp\n" +
" AND Moyen_Transport.ID_Voiture=OrdMission.ID_Voiture\n" +
" AND Categorie.ID_Categorie=Grade.ID_Categorie\n" +
" AND Employeur.ID_Grade=Grade.ID_Grade\n" +
" AND OrdMission.Id_task=Tasktype.Id_task\n" +
" AND OrdMission.Id_dest=destination.Id_dest"+
//" AND destination.ID_TypDis="+Zone;
" AND (destination.ID_Direct_Zone ="+Zone+" OR destination.ID_Direct_Zone ="+Dist+")" ;   
  Query=Query+Choice;
  df=(DefaultTableModel)Tab.getModel();
  df.setRowCount(0);
    Statement stm=null;
    ResultSet Res=null;
        getCnx().connectSqlServer();
    try {
        stm=getCnx().getCnx().createStatement();
        Res=stm.executeQuery(QuerySup_50);
        
//        Object arg[]={5};
//        Object[] intArray = new Object[]{ 1,2,3,4,5,6,7,8,9,10 };
        //int[] intArray = new int[]{ 1,2,3,4,5,6,7,8,9,10 };
        int i=1;
        
        while (Res.next()) {            
          Object arg[]={true,Res.getString("Nom_Voiture"),Res.getString("DescriptionTask_AR"),Res.getString("Nam_Des"),Res.getString("Residance_Mng"),
                        Res.getDate("DateRetour"),Res.getDate("DateDepart"),Res.getString("Desc_Grade"),Res.getString("Last_Nm_Emp"),Res.getString("Name_Emp"),Res.getInt("Num_OrdMission")};
            System.out.println("The Values is:"+Res.getInt(1));  
            System.out.println(Res.getInt("Num_OrdMission"));
            System.out.println( Res.getString("Name_Emp"));
            System.out.println( Res.getString("Last_Nm_Emp"));
            System.out.println( Res.getString("Desc_Grade"));
            System.out.println( Res.getDate("DateDepart"));
            
            
            System.out.println( Res.getDate("DateRetour"));
            
            System.out.println( Res.getString("Residance_Mng"));
            System.out.println( Res.getString("Nom_Voiture"));
            System.out.println( Res.getString("DescriptionTask_AR"));
            System.out.println( Res.getBoolean("stat"));
            System.out.println("***************************************");
            df.addRow(arg);
            System.out.println("Number Item is :"+i);
            i++;
        }
        Tab.setModel(df);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error in Fill Table "+e.getMessage());
    }
    
    try {
        stm.close();
        Res.close();
            getCnx().Deconnect();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error in Fill Table "+e.getMessage());
    }
}
public void FillOrdMissionNoProcess(JTable Tab,int Zone,int Dist){

    DefaultTableModel df=new DefaultTableModel();
    String Choice=" ";
    String Destination=" ";

    String Query="SELECT ID_OrdMission,Num_OrdMission,Name_Emp,Last_Nm_Emp,"
            + " Desc_Grade,DateDepart,DateRetour,Residance_Mng,"
            + " Nom_Voiture,DescriptionTask_AR,stat"
            + " FROM OrdMission,Employeur,Grade,Tasktype,Categorie,Moyen_Transport"
            + " WHERE OrdMission.ID_Emp=Employeur.ID_Emp "
            + " AND Moyen_Transport.ID_Voiture=OrdMission.ID_Voiture "
            + " AND Categorie.ID_Categorie=Grade.ID_Categorie "
            + " AND Employeur.ID_Grade=Grade.ID_Grade "
            + " AND OrdMission.Id_task=Tasktype.Id_task";
    
    
    String QuerySup_50=
" SELECT ID_OrdMission,Num_OrdMission,Name_Emp,Last_Nm_Emp,\n" +
" Desc_Grade,DateDepart,DateRetour,Residance_Mng,Nam_Des,\n" +
" Nom_Voiture,DescriptionTask_AR,stat\n" +
" FROM OrdMission,Employeur,Grade,Tasktype,Categorie,Moyen_Transport,destination\n" +
" WHERE OrdMission.ID_Emp=Employeur.ID_Emp\n" +
" AND Moyen_Transport.ID_Voiture=OrdMission.ID_Voiture\n" +
" AND Categorie.ID_Categorie=Grade.ID_Categorie\n" +
" AND Employeur.ID_Grade=Grade.ID_Grade\n" +
" AND OrdMission.Id_task=Tasktype.Id_task\n" +
" AND OrdMission.Id_dest=destination.Id_dest"+
//" AND destination.ID_TypDis="+Zone;
" AND (destination.ID_Direct_Zone ="+Zone+" OR destination.ID_Direct_Zone ="+Dist+")" ;   
  Query=Query+Choice;
  df=(DefaultTableModel)Tab.getModel();
  df.setRowCount(0);
    Statement stm=null;
    ResultSet Res=null;
        getCnx().connectSqlServer();
    try {
        stm=getCnx().getCnx().createStatement();
        Res=stm.executeQuery(QuerySup_50);
        
//        Object arg[]={5};
//        Object[] intArray = new Object[]{ 1,2,3,4,5,6,7,8,9,10 };
        //int[] intArray = new int[]{ 1,2,3,4,5,6,7,8,9,10 };
        int i=1;
        
        while (Res.next()) {            
          Object arg[]={true,Res.getString("Nam_Des"),
                        Res.getDate("DateDepart"),Res.getString("Last_Nm_Emp"),Res.getString("Name_Emp"),Res.getInt("Num_OrdMission")};
          
            System.out.println("The Values is:"+Res.getInt(1));  
            System.out.println(Res.getInt("Num_OrdMission"));
            System.out.println( Res.getString("Name_Emp"));
            System.out.println( Res.getString("Last_Nm_Emp"));
           // System.out.println( Res.getString("Desc_Grade"));
           // System.out.println( Res.getDate("DateDepart"));
            
            
            System.out.println( Res.getDate("DateRetour"));
            
            System.out.println( Res.getString("Residance_Mng"));
            System.out.println( Res.getString("Nom_Voiture"));
            System.out.println( Res.getString("DescriptionTask_AR"));
            System.out.println( Res.getBoolean("stat"));
            System.out.println("***************************************");
            df.addRow(arg);
            System.out.println("Number Item is :"+i);
            i++;
        }
        Tab.setModel(df);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error in Fill Table "+e.getMessage());
    }
    
    try {
        stm.close();
        Res.close();
            getCnx().Deconnect();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error in Fill Table "+e.getMessage());
    }

}



/****************************************************************/
public void DisplayAllInfOrdMission(){
Statement stm=null;
ResultSet res=null;
String Query="SELECT ID_OrdMission,Num_OrdMission,Name_Emp,Last_Nm_Emp,\n" +
"Desc_Grade,DateDepart,DateRetour,Residance_Mng,Nam_Des,Fun_Emp,\n" +
" Nom_Voiture,DescriptionTask_AR,stat,ID_Direct_Zone,Type_Voiture,Num_Categorie\n" +
" FROM OrdMission,Employeur,Grade,Tasktype,Categorie,Moyen_Transport,destination,TypeMoyen_Transport\n" +
" WHERE OrdMission.ID_Emp=Employeur.ID_Emp\n" +
" AND Moyen_Transport.ID_Voiture=OrdMission.ID_Voiture\n" +
" AND Categorie.ID_Categorie=Grade.ID_Categorie\n" +
" AND Employeur.ID_Grade=Grade.ID_Grade\n" +
" AND OrdMission.Id_task=Tasktype.Id_task\n" +
" AND OrdMission.Id_dest=destination.Id_dest\n" +
" AND TypeMoyen_Transport.ID_TypeVoiture=Moyen_Transport.ID_TypeVoiture\n" +
" AND OrdMission.Num_OrdMission=4";
    try {
        Obj_Cnx.connectSqlServer();
        stm=Obj_Cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        //System.out.println("Number Row Is "+res.getMetaData());
        if (res.next()) {
             System.out.println( res.getInt("ID_OrdMission"));
             System.out.println( res.getInt("Num_OrdMission"));
             System.out.println( res.getString("Name_Emp"));
             System.out.println( res.getString("Last_Nm_Emp"));
             System.out.println( res.getString("Desc_Grade"));
             System.out.println( res.getDate("DateDepart"));
             System.out.println( res.getDate("DateRetour"));
             System.out.println( res.getString("Residance_Mng"));
             System.out.println( res.getString("Nam_Des"));
             System.out.println( res.getString("Fun_Emp"));
             System.out.println( res.getString("Nom_Voiture"));
             System.out.println( res.getString("DescriptionTask_AR"));
             System.out.println( res.getBoolean("stat"));
            res.getInt("ID_Direct_Zone");
            System.out.println( res.getInt("ID_Direct_Zone"));
            res.getString("Type_Voiture");
            System.out.println(  res.getString("Type_Voiture"));
            res.getInt("Num_Categorie");
            System.out.println( res.getInt("Num_Categorie"));
            //Num_OrdMission

            
        }
    
    } catch (SQLException e) {
        e.printStackTrace();
    }

    try {
        stm.close();
        res.close();
        Obj_Cnx.Deconnect();
    } catch (Exception e) {
        e.printStackTrace();
    }
 

}
public void DisplayAllInfOrdMission(JTable tab){
Statement stm=null;
ResultSet res=null;
String Query="SELECT ID_OrdMission,Num_OrdMission,Name_Emp,Last_Nm_Emp,\n" +
"Desc_Grade,DateDepart,DateRetour,Residance_Mng,Nam_Des,Fun_Emp,\n" +
" Nom_Voiture,DescriptionTask_AR,stat,ID_Direct_Zone,Type_Voiture,Num_Categorie\n" +
" FROM OrdMission,Employeur,Grade,Tasktype,Categorie,Moyen_Transport,destination,TypeMoyen_Transport\n" +
" WHERE OrdMission.ID_Emp=Employeur.ID_Emp\n" +
" AND Moyen_Transport.ID_Voiture=OrdMission.ID_Voiture\n" +
" AND Categorie.ID_Categorie=Grade.ID_Categorie\n" +
" AND Employeur.ID_Grade=Grade.ID_Grade\n" +
" AND OrdMission.Id_task=Tasktype.Id_task\n" +
" AND OrdMission.Id_dest=destination.Id_dest\n" +
" AND TypeMoyen_Transport.ID_TypeVoiture=Moyen_Transport.ID_TypeVoiture\n" +
" AND OrdMission.Num_OrdMission=4";
    try {
        Obj_Cnx.connectSqlServer();
        stm=Obj_Cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        //System.out.println("Number Row Is "+res.getMetaData());
        if (res.next()) {
             System.out.println( res.getInt("ID_OrdMission"));
             System.out.println( res.getInt("Num_OrdMission"));
             System.out.println( res.getString("Name_Emp"));
             System.out.println( res.getString("Last_Nm_Emp"));
             System.out.println( res.getString("Desc_Grade"));
             System.out.println( res.getDate("DateDepart"));
             System.out.println( res.getDate("DateRetour"));
             System.out.println( res.getString("Residance_Mng"));
             System.out.println( res.getString("Nam_Des"));
             System.out.println( res.getString("Fun_Emp"));
             System.out.println( res.getString("Nom_Voiture"));
             System.out.println( res.getString("DescriptionTask_AR"));
             System.out.println( res.getBoolean("stat"));
            res.getInt("ID_Direct_Zone");
            System.out.println( res.getInt("ID_Direct_Zone"));
            res.getString("Type_Voiture");
            System.out.println(  res.getString("Type_Voiture"));
            res.getInt("Num_Categorie");
            System.out.println( res.getInt("Num_Categorie"));
            //Num_OrdMission

            
        }
    
    } catch (SQLException e) {
        e.printStackTrace();
    }

    try {
        stm.close();
        res.close();
        Obj_Cnx.Deconnect();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    
    
 

}


public int GetLastNumOrdMission(){
String Query="SELECT MAX (Num_OrdMission) FROM OrdMission";

Statement stm=null;
    ResultSet res=null;
    Obj_Cnx.connectSqlServer();
    int Max=0;
    try {
        stm=Obj_Cnx.getCnx().createStatement();
        res=stm.executeQuery(Query);
        
        if (res.next()) {
            Max=res.getInt(1);
        }
        
    } catch (SQLException e) {
    e.printStackTrace();
    }

return Max;
}
/****************************************************************/

public void Calcule_Price_OrdMission(int NumOrdMs){
    
     SimpleDateFormat formatDate=new    SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatTime=new    SimpleDateFormat("HH:mm");  
        
         double valueRepat=0;
         double valueDecocher=0;
     Calcule_val  cl=new Calcule_val();
     OrdMission ordission_obj=    new OrdMission();
        ordission_obj.GetAllInformation(NumOrdMs);
        
        int distZone=ordission_obj.getId_dest();
        int Id_Employer=ordission_obj.getId_emp();
        int prstg=ordission_obj.getPrctge();
        
        
        
    try {
        cl.calcule_eating_dortoire(formatDate.format(ordission_obj.getDateGo()),formatDate.format(ordission_obj.getDateBack()),
                formatTime.format(ordission_obj.getHeurDepart()),formatTime.format(ordission_obj.getHeurRetour()));
    } catch (ParseException ex) {
        Logger.getLogger(OrdMission.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    int  nbrrepat=cl.getNbreRepat();
    int  nbrdecoch=cl.getNbreDortoire();
    NbrRepat=nbrrepat;
    NbrDecocher=nbrdecoch;
     System.out.println("NbrRepat :"+nbrrepat);
     System.out.println("NbrDecocher :"+nbrdecoch);
    double priceOrdMission=0,Price_Repat=0,Price_Decocher=0;  
   // String QueryGetIdDirection="SELECT ID_Direct_Zone FROM destination WHERE Num_OrdMission="+NumOrdMs;
  
    if (nbrrepat!=0) {
    
         String QueryRepatValue="select valeur_Repat\n" +
"from Prix_Repat,Prix_Repat_Categorie,Categorie,Direction,Employeur,Grade,destination\n" +
"where \n" +
"Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n" +
"AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n" +
"AND Prix_Repat_Categorie.ID_Categorie=Categorie.ID_Categorie\n" +
"AND Categorie.ID_Categorie=Grade.ID_Categorie\n" +
"AND Employeur.ID_Grade=Grade.ID_Grade\n" +
"AND Employeur.ID_Emp= "+Id_Employer+"\n" +
"AND destination.ID_Direct_Zone= Direction.ID_Direct_Zone\n" +
"AND destination.Id_dest="+distZone;
         
    Obj_Cnx.connectSqlServer();
    Statement stm=null;
    ResultSet res=null;
    
   try {
         stm=Obj_Cnx.getCnx().createStatement();
         res=stm.executeQuery(QueryRepatValue);
             if (res.next()) {
            Price_Repat=res.getDouble("valeur_Repat");
            this.price_Repat=Price_Repat;
            System.out.println("The Price of Price_Repat is :"+Price_Repat);
            }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         try {
             Obj_Cnx.Deconnect();
             stm.close();
             res.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         
         
     }
    /**************************************************************/
     if (nbrdecoch!=0) {
         
         String QueryRepatValue="select valeur_decocher\n" +
"from Prix_Decocher,\n" +
"Prix_Decocher_Categorie,Categorie,Direction,Employeur,Grade,destination\n" +
"where\n" +
"Direction.ID_Direct_Zone=Prix_Decocher.ID_Direct_Zone\n" +
"AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher\n" +
"AND Prix_Decocher_Categorie.ID_Categorie=Categorie.ID_Categorie\n" +
"AND Categorie.ID_Categorie=Grade.ID_Categorie\n" +
"AND Employeur.ID_Grade=Grade.ID_Grade\n" +
"AND Employeur.ID_Emp="+Id_Employer+"\n" +
"AND destination.ID_Direct_Zone= Direction.ID_Direct_Zone\n" +                 
"AND destination.Id_dest="+distZone;
         
         
    Obj_Cnx.connectSqlServer();
    Statement stm=null;
    ResultSet res=null;
    
   
    try {
         stm=Obj_Cnx.getCnx().createStatement();
         res=stm.executeQuery(QueryRepatValue);
             if (res.next()) {
            Price_Decocher=res.getDouble("valeur_decocher");
            
            this.price_Decocher=Price_Decocher;
            
             System.out.println("The Price of Decocher is Price_Decocher:"+Price_Decocher);
            }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         try {
             Obj_Cnx.Deconnect();
             stm.close();
             res.close();
         } catch (SQLException e) {
         }
     
     }
 /*************************************************************************/
       
      valueRepat=Price_Repat*nbrrepat;
      if (prstg==25) {
        valueRepat=valueRepat*0.25;
    }
      
      valueDecocher=Price_Decocher*nbrdecoch;
        if (prstg==25) {
        valueDecocher=valueDecocher*0.25;
    }  
      
        this.Price_Of_OrdMission=valueDecocher+valueRepat;
     System.out.println("Value Of Repat :"+valueRepat);
     System.out.println("Value Of Decocher :"+valueDecocher);
     
     System.out.println("Value Of Decocher :"+(valueDecocher+valueRepat));
     
     
    }
 

/********************************************************************/

public void UpdateNumEngagement(int Num_Ord,int Num_Eng){
 String QueryUpd="UPDATE OrdMission SET  Id_Operation = ?  WHERE Num_OrdMission = "+Num_Ord;
    PreparedStatement prs=null;
    Obj_Cnx.connectSqlServer();
    try {
        prs=Obj_Cnx.getCnx().prepareStatement(QueryUpd);
        prs.setInt(1, Num_Eng);
        int x=prs.executeUpdate();
        if (x>0) {
            JOptionPane.showMessageDialog(null, "Success Update");
                    
        }else {
        JOptionPane.showMessageDialog(null, "No Update");
        }
        
    } catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Error in Update Record "+e.getMessage());
    }
    try {
        prs.close();
        Obj_Cnx.Deconnect();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
}
public void UpdateNumEngagement(int Num_Ord){
 String QueryUpd="UPDATE OrdMission SET  Id_Operation = ?  WHERE Num_OrdMission = "+Num_Ord;
    PreparedStatement prs=null;
    Obj_Cnx.connectSqlServer();
    try {
        prs=Obj_Cnx.getCnx().prepareStatement(QueryUpd);
        prs.setObject(1, null);
        int x=prs.executeUpdate();
        if (x>0) {
            JOptionPane.showMessageDialog(null, "Success Update");
                    
        }else {
        JOptionPane.showMessageDialog(null, "No Update");
        }
        
    } catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Error in Update Record "+e.getMessage());
    }
    try {
        prs.close();
        Obj_Cnx.Deconnect();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
}


public void GetNameEmployer_OrdMission(int Num_Ord){


}

    public Date getDateGo() {
        return DateGo;
    }

    /**
     * @param DateGo the DateGo to set
     */
    public void setDateGo(Date DateGo) {
        this.DateGo = DateGo;
    }

    /**
     * @return the DateBack
     */
    public Date getDateBack() {
        return DateBack;
    }

    /**
     * @param DateBack the DateBack to set
     */
    public void setDateBack(Date DateBack) {
        this.DateBack = DateBack;
    }

    /**
     * @return the Num_OrdMission
     */
    public int getNum_OrdMission() {
        return Num_OrdMission;
    }

    /**
     * @param Num_OrdMission the Num_OrdMission to set
     */
    public void setNum_OrdMission(int Num_OrdMission) {
        this.Num_OrdMission = Num_OrdMission;
    }

    /**
     * @return the DateOrdMission
     */
    public Date getDateOrdMission() {
        return DateOrdMission;
    }

    /**
     * @param DateOrdMission the DateOrdMission to set
     */
    public void setDateOrdMission(Date DateOrdMission) {
        this.DateOrdMission = DateOrdMission;
    }

    /**
     * @return the id_emp
     */
    public int getId_emp() {
        return id_emp;
    }

    /**
     * @param id_emp the id_emp to set
     */
    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    /**
     * @return the ID_Voiture
     */
    public int getID_Voiture() {
        return ID_Voiture;
    }

    /**
     * @param ID_Voiture the ID_Voiture to set
     */
    public void setID_Voiture(int ID_Voiture) {
        this.ID_Voiture = ID_Voiture;
    }

    /**
     * @return the Id_task
     */
    public int getId_task() {
        return Id_task;
    }

    /**
     * @param Id_task the Id_task to set
     */
    public void setId_task(int Id_task) {
        this.Id_task = Id_task;
    }

    /**
     * @return the stat
     */
    public boolean isStat() {
        return stat;
    }

    /**
     * @param stat the stat to set
     */
    public void setStat(boolean stat) {
        this.stat = stat;
    }

    /**
     * @return the prctge
     */
    public int getPrctge() {
        return prctge;
    }

    /**
     * @param prctge the prctge to set
     */
    public void setPrctge(int prctge) {
        this.prctge = prctge;
    }

    /**
     * @return the HeurDepart
     */
    public Date getHeurDepart() {
        return HeurDepart;
    }

    /**
     * @param HeurDepart the HeurDepart to set
     */
    public void setHeurDepart(Date HeurDepart) {
        this.HeurDepart = HeurDepart;
    }

    /**
     * @return the HeurRetour
     */
    public Date getHeurRetour() {
        return HeurRetour;
    }

    /**
     * @param HeurRetour the HeurRetour to set
     */
    public void setHeurRetour(Date HeurRetour) {
        this.HeurRetour = HeurRetour;
    }

    /**
     * @return the Id_dest
     */
    public int getId_dest() {
        return Id_dest;
    }

    /**
     * @param Id_dest the Id_dest to set
     */
    public void setId_dest(int Id_dest) {
        this.Id_dest = Id_dest;
    }

    /**
     * @return the cnx
     */
    public Connection_DB getCnx() {
        return Obj_Cnx;
    }

    /**
     * @param cnx the cnx to set
     */
    public void setCnx(Connection_DB cnx) {
        this.Obj_Cnx = cnx;
    }
    
    public int GetNbrRepat(){
    return NbrRepat;
    }
    public int GetNbrDecoche(){
    return NbrDecocher;
    }
    public double Get_PriceOrd_Mission(){
    return Price_Of_OrdMission;
    }
    
    public int GetZONE_Stat(int NumOrdMission){
     String Query="SELECT destination.ID_Direct_Zone \n" +
"FROM Direction,destination,OrdMission \n" +
"WHERE \n" +
"OrdMission.Num_OrdMission= "+ NumOrdMission+" \n" +
"AND OrdMission.Id_dest=destination.Id_dest\n" +
"AND Direction.ID_Direct_Zone=destination.ID_Direct_Zone";
 int id_zone=0;
 Statement   stm = null;
 ResultSet  resst=null;
 //int tab[]=new int[2];
 Obj_Cnx.connectSqlServer();
     try {
         stm=Obj_Cnx.getCnx().createStatement();
         resst=stm.executeQuery(Query);
         if (resst.next()) {
         id_zone=resst.getInt(1);         
         }
         
     } catch (SQLException e) {
         e.printStackTrace();
     }
     
     try {
         stm.close();
         resst.close();
         Obj_Cnx.Deconnect();
     } catch (SQLException e) {
     }
 
 return id_zone;
 }
 
 public int Get_Porcentage(int NumOrdMission){
 String Query="Select prctge FROM OrdMission WHERE Num_OrdMission="+NumOrdMission;
 int prctg=100;
 Obj_Cnx.connectSqlServer();
ResultSet resst=null;
Statement stm=null;
      try {
  stm=Obj_Cnx.getCnx().createStatement();
 resst=stm.executeQuery(Query);
    if (resst.next()) {
              prctg=resst.getInt(1);
          }
  } catch (SQLException e) {
      e.printStackTrace();
  }
     try {
         stm.close();
         resst.close();
         Obj_Cnx.Deconnect();
     } catch (SQLException e) {
     }
     
     if (prctg==100) {
         prctg=1;
     }else prctg=0;
 return prctg;
 }
 
 public void GetAllInformation(int NumOrdMission){
 String Query="SELECT Num_OrdMission,Date_OrdMission,DateDepart,DateRetour,ID_Emp,ID_Voiture,Id_task,stat,"
         + "prctge,HeurDepart,HeurRetour,Id_dest FROM OrdMission WHERE Num_OrdMission = "+NumOrdMission;
 Statement stm=null;
 ResultSet res=null;
 Obj_Cnx.connectSqlServer();
     try {
       stm=Obj_Cnx.getCnx().createStatement();
       res=stm.executeQuery(Query);
         if (res.next()) {
             
             Num_OrdMission=res.getInt("Num_OrdMission");
             DateGo=res.getDate("DateDepart");
             DateBack=res.getDate("DateRetour");
             DateOrdMission=res.getDate("Date_OrdMission");
             prctge=res.getInt("prctge");
             id_emp=res.getInt("ID_Emp");
             ID_Voiture=res.getInt("ID_Voiture");
             Id_task=res.getInt("Id_task");
             HeurDepart=res.getTimestamp("HeurDepart");
             HeurRetour=res.getTimestamp("HeurRetour");
             Id_dest=res.getInt("Id_dest");
             stat=res.getBoolean("stat");
             
             
             
             /*private Date DateGo;
private Date DateBack;
private int Num_OrdMission;
private Date DateOrdMission;
private int id_emp;
private int ID_Voiture;
private int ID_Direct_Zone;
private int Id_task;
private boolean  stat;
private int prctge;
private Date HeurDepart;
private Date HeurRetour;
private int Id_dest;*/
             
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     try {
         stm.close();
         res.close();
         Obj_Cnx.Deconnect();
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
 
 
 public int ID_Direct_Zone(int Id_dest){
 String Query="SELECT ID_Direct_Zone FROM destination WHERE Id_dest= "+Id_dest;
  Statement stm=null;
 ResultSet res=null;
 Obj_Cnx.connectSqlServer();
 int id_Zone=0;
  try {
        stm=Obj_Cnx.getCnx().createStatement();
       res=stm.executeQuery(Query);
         if (res.next()) {
         id_Zone=res.getInt(1);
         }
     } catch (SQLException e) {
         
         JOptionPane.showMessageDialog(null, "Error SQL "+e.getMessage());
     }
     
     
 return id_Zone;
 }
 
 
 public void printing_OrdMission(int NumOrdMiss,String NameEmp,String LastNameEmp, 
         String Grade,String Function,String Residence,String Destinataire,String MoyenTrsp,String CauseOrdMiss,String DateGo,String HourGo){
 
     
JasperReport jasperreport;
        
    try {
        
         InputStream file=getClass().getResourceAsStream("/Reports/OrdMission.jrxml");
        JasperDesign jasperdesign=JRXmlLoader.load(file);
        
        jasperreport=JasperCompileManager.compileReport(jasperdesign);
         Map parametre=new HashMap<String, Object>();
         parametre.put("NumOrdMiss",NumOrdMiss+"");
         parametre.put("Nam_LastNam", NameEmp+" "+LastNameEmp);
         parametre.put("Grade", Grade);
          parametre.put("Function", Function);
         parametre.put("Residence", Residence);
         parametre.put("Destinataire", Destinataire);
         parametre.put("MoyenTrsp", MoyenTrsp);
         parametre.put("CauseOrdMiss", CauseOrdMiss);
         parametre.put("Dur_OrdMiss", DateGo); 
         parametre.put("Date_Time_Go", HourGo);
         
         parametre.put("Date_Time_Back", "");
         
         
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametre, new JREmptyDataSource());
           JasperViewer jasperviewer=new JasperViewer(jasperprint, false);
           
          // jasperviewer.show();
         jasperviewer.viewReport(jasperprint,false);
    } catch (JRException ex) {
        Logger.getLogger(OrdMission.class.getName()).log(Level.SEVERE, null, ex);
    }
     
     
 
 }
 

 
 
 public int GetMaxNumOrdMission(){
 return 0;
 }

    /**
     * @return the price_Repat
     */
    public double getPrice_Repat() {
        return price_Repat;
    }

    /**
     * @param price_Repat the price_Repat to set
     */
    public void setPrice_Repat(double price_Repat) {
        this.price_Repat = price_Repat;
    }

    /**
     * @return the price_Decocher
     */
    public double getPrice_Decocher() {
        return price_Decocher;
    }

    /**
     * @param price_Decocher the price_Decocher to set
     */
    public void setPrice_Decocher(double price_Decocher) {
        this.price_Decocher = price_Decocher;
    }

}
