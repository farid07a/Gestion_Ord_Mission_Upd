/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author formation
 */
public class Calcule_val {
    
    private int NbreRepatCal;
    private int NbreDortoireCal;
    
    public  Calcule_val(){
    
    
    }
    
    
     public void calcule_eating_dortoire(String dateStart,String dateStop,String HeurStart ,String HeurStop ) throws ParseException{
     
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
            
                     
        if      ( (DepartHour.before(TimeLunch2/*<13:30*/)&& ( ArriveHour.before( TimeDinner1/*<18:30*/ )&&((DepartDay.before(ArriveDay))))&&(ArriveHour.after(DepartHour))&&(mod<=14)) ) //dep <11 && ret<18:00
                {
            
                    System.out.println("The first if => <11:30 et <18:30   ");
                    
                        if(ArriveHour.after(TimeLunch1)){
                        NbrRepat++;                      
                        
                        }                              //nbrRepat =1;
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
        NbreRepatCal=NbrRepat;
        NbreDortoireCal=NbrDecocher;
        
        
    }

    /**
     * @return the NbreRepat
     */
    public int getNbreRepat() {
        return NbreRepatCal;
    }

    /**
     * @param NbreRepat the NbreRepat to set
     */
    public void setNbreRepat(int NbreRepat) {
        this.NbreRepatCal = NbreRepat;
    }

    /**
     * @return the NbreDortoire
     */
    public int getNbreDortoire() {
        return NbreDortoireCal;
    }

    /**
     * @param NbreDortoire the NbreDortoire to set
     */
    public void setNbreDortoire(int NbreDortoire) {
        this.NbreDortoireCal = NbreDortoire;
    }
    
    public static void main(String[] args) throws ParseException {
        
        Calcule_val clv=new Calcule_val();
        clv.calcule_eating_dortoire("10/10/2001", "12/10/2001", "11:00", "23:00");
        
        System.out.println("the eating nbr :"+clv.getNbreRepat());
        System.out.println("the drtoire nbr:"+clv.getNbreDortoire());
    }
     
     
}
