/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Connection_DB;
import Controller.HelperClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Info_Ord {
//Employeur Emp;
private String FirstName;
private String LastName;
private String DepuisMois;
private String Grade;
private String Job;
private String SemanticNumero;
private String ManagementResident;
private String CCPN_Num;
private String CauseTravel;
private String Depart_Demarer;
private String Destinataire;
private String MoyenTrnsport;
private String DateGo;
private String DateBack;
private String Heur_Go;
private String Heur_Back;
private String Remarque;
private int compensationEat;
private int compensationDrt; 
private int Orientation;
private int Num_OrdMission;
private Date DateOrdMission;
private int id_emp;
private int Id_task;
Connection_DB cnx=new Connection_DB();
//private int Price;

public Info_Ord(
        String FirstName,String LastName,String DepuisMois,String Grade,String Job,String SemanticNumero,String ManagementResident,String CCPN_Num,
        String CauseTravel,String Depart_Demarer,String Destinataire,String MoyenTrnsport,
        String DateGo,String Heur_Go,String DateBack,String Heur_Back,
        int compensationEat,int compensationDrt,int Orientation, 
        String Remarque)
    {
    this.FirstName=FirstName;
    this.LastName=LastName;
    this.DepuisMois=DepuisMois;
    this.Grade=Grade;
    this.Job=Job;
    this.SemanticNumero=SemanticNumero;
    this.ManagementResident=ManagementResident;
    this.CCPN_Num=CCPN_Num;
    this.CauseTravel=CauseTravel;
    this.Depart_Demarer=Depart_Demarer;
    this.Destinataire=Destinataire;
    this.MoyenTrnsport=MoyenTrnsport;
    this.DateGo=DateGo;
    this.Heur_Go=Heur_Go;
    this.DateBack=DateBack;
    this.Heur_Back=Heur_Back;
    this.compensationEat=compensationEat;
    this.compensationDrt=compensationDrt;
    this.Remarque=Remarque;
    this.Orientation=Orientation;
}

public Info_Ord(int Num_OrdMission,Date DateOrdMission,String DateGo,String DateBack,int id_emp,int Orientation){
this.Num_OrdMission=Num_OrdMission;
this.DateOrdMission=DateOrdMission;
this.DateGo=DateGo;
this.DateBack=DateBack;
this.id_emp=id_emp;
this.Orientation=Orientation;

}

    public Info_Ord() {
         //To change body of generated methods, choose Tools | Templates.
    }



    public int getOrientation(){
    
    return Orientation;
    }

    
    
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FullName) {
        this.FirstName = FullName;
    }
    
     public String getLastName() {
        return LastName;
    }

    public void setLastName(String FullName) {
        this.LastName = FullName;
    }
    
    public String getGrade() {
        return Grade;
    }
    public void setGrade(String Grade) {
        this.Grade = Grade;
    }
    public String getJob() {
        return Job;
    }
    public void setJob(String Job) {
        this.Job = Job;
    }
    public String getSemanticNumero() {
        return SemanticNumero;
    }
    public void setSemanticNumero(String SemanticNumero) {
        this.SemanticNumero = SemanticNumero;
    }
    public String getManagementResident() {
        return ManagementResident;
    }
    public void setManagementResident(String ManagementResident) {
        this.ManagementResident = ManagementResident;
    }
    public String getCCPN_Num() {
        return CCPN_Num;
    }
    public void setCCPN_Num(String CCPN_Num) {
        this.CCPN_Num = CCPN_Num;
    }
    public String getCauseTravel() {
        return CauseTravel;
    }
    public void setCauseTravel(String CauseTravel) {
        this.CauseTravel = CauseTravel;
    }
    public String getDestinataire() {
        return Destinataire;
    }
    public void setDestinataire(String Destinataire) {
        this.Destinataire = Destinataire;
    }
    public String getDateGo() {
        return DateGo;
    }
    public void setDateGo(String DateGo) {
        this.DateGo = DateGo;
    }
    
    public String getDateBack() {
        return DateBack;
    }
    public void setDateBack(String DateBack) {
        this.DateBack = DateBack;
    }
    public String getDepuisMois() {
        return DepuisMois;
    }
    public void setDepuisMois(String DepuisTravel) {
        this.DepuisMois = DepuisTravel;
    }
    public String getRemarque() {
        return Remarque;
    }
    public void setRemarque(String Remarque) {
        this.Remarque = Remarque;
    }

    public String getHeur_Go() {
        return Heur_Go;
    }

    public void setHeur_Go(String Heur_Go) {
        this.Heur_Go = Heur_Go;
    }

    public String getHeur_Back() {
        return Heur_Back;
    }
    
    public void setHeur_Back(String Heur_Back) {
        this.Heur_Back = Heur_Back;
    }

    /**
     * @return the Depart_Demarer
     */
    public String getDepart_Demarer() {
        return Depart_Demarer;
    }

    /**
     * @param Depart_Demarer the Depart_Demarer to set
     */
    public void setDepart_Demarer(String Depart_Demarer) {
        this.Depart_Demarer = Depart_Demarer;
    }

    /**
     * @return the MoyenTrnsport
     */
    public String getMoyenTrnsport() {
        return MoyenTrnsport;
    }

    /**
     * @param MoyenTrnsport the MoyenTrnsport to set
     */
    public void setMoyenTrnsport(String MoyenTrnsport) {
        this.MoyenTrnsport = MoyenTrnsport;
    }
    
    public int Calcule_compensationEat(Date d1,Date d2){
    
        
    return 1;
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

    public void Fill(){
        PreparedStatement prstm=null;
        ResultSet res=null;
        String Query="insert into OrdMission(Num_OrdMission,Date_OrdMission,DateDepart,DateRetour,"
                + "ID_Emp,ID_Voiture,ID_Direct_Zone,Id_task,stat,prctge)VALUES (?,?,?,?,?,?,)";
        cnx.Connect();
        try {
            prstm=cnx.getCnx().prepareStatement(Query);
            
            prstm.setInt(1, 5);
            
            
            System.out.println("gestion_ord_mission.Info_Ord.insertOrdMission()");
        } catch (SQLException e) {
        }
    
        
    }
    
    
    
    /**
     * @return the compensationEat
     */
    public int getCompensationEat() {
        return compensationEat;
    }

    /**
     * @param compensationEat the compensationEat to set
     */
    public void setCompensationEat(int compensationEat) {
        this.compensationEat = compensationEat;
    }

    /**
     * @return the compensationDrt
     */
    public int getCompensationDrt() {
        return compensationDrt;
    }

    /**
     * @param compensationDrt the compensationDrt to set
     */
    public void setCompensationDrt(int compensationDrt) {
        this.compensationDrt = compensationDrt;
    }
    
    public static void main(String[] args) {
        new HelperClass().Insert_Grad("متصرف رئيسي", "متصرف رئيسي", 13);
    }
}
