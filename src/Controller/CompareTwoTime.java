/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;

public class CompareTwoTime {
    
    
    public static void main(String[] args) throws ParseException {
        
        String format = "dd/MM/yyyy HH:mm";
        
        SimpleDateFormat SmpDatFrmComplete = new SimpleDateFormat(format);
        
       
        
        String dateStart="21/10/2001",
                
               dateStop="22/10/2001";
        
        String HeurStart="06:35",   
                
                
        HeurStop="05:35";
        Date DepartDayComplete=SmpDatFrmComplete.parse(dateStart+" "+HeurStart);
        
        Date ArriveDayComple=SmpDatFrmComplete.parse(dateStop+" "+HeurStop);
        
         
         
         long DifferenceDateComplete=ArriveDayComple.getTime()-DepartDayComplete.getTime();
        
         int DiferenceNbrDay;
    SimpleDateFormat formatDate=new SimpleDateFormat("dd/MM/yyyy");
        
        Date DepartDay=formatDate.parse(dateStart);
        
        
        Date ArriveDay=formatDate.parse(dateStop);
        
        
        
        
        
        
        
        //Date DepartHour=sdf.parse(dateStart+" "+HeurStart);
        //Date ArriveHour=sdf.parse(dateStop+" "+HeurStop);
        
        
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        Date DepartHour,ArriveHour;
        
        
        DepartHour=parser.parse(HeurStart);
        ArriveHour=parser.parse(HeurStop);
        
        long diff=DepartDay.getTime()-ArriveDay.getTime();
        
        
        diff=ArriveDay.getTime()-DepartDay.getTime();
        
        
        long diffHours = diff / (60 * 60 * 1000);
        
        int diffInDays = (int) diff / (1000 * 60 * 60 * 24);
        
        
        
        System.out.println("The hours is :"+diffHours);
        
        System.out.println("The Days is :"+diffInDays);
        
     
        
        
        
        //String FirstHours="00:00",LastHours="";
        
        //Date DepartHour1=parser.parse(FirstHours);
        
        
        
        
  
       
        
        
        Date ten=parser.parse("00:00");
        
        Date siHeur=parser.parse("23:00");
        
        if (ten.after(siHeur)) {
            System.out.println("FirstDate is Super SecondDat ten >sjheur ");
        }
        if(ten.before(siHeur)){
            System.out.println("FirstDate is infr SecondDat   ten< sihour");
        }
        
        Date dtr=new Date();
        String formatHours="HH:mm";
        
        
        String s=parser.format(dtr);
        
        
        
        System.out.println("The hours issssssssssssssssssssssss:"+s);
        
        int NumberHouer,NumberDay;
        
        Date TimeLunch1=parser.parse("11:29");
         Date TimeLunch2=parser.parse("14:01");
        Date TimeDinner1=parser.parse("17:59");
         Date TimeDinner2=parser.parse("21:01");
         
         Date TimeDechoche=parser.parse("00:00");
        
        
        int NbrRepat=0,NbrDecocher=0;
        
        /******************Debut pgm**************************************/
       
       
        
        
    
        /*****************{Main Class}************ - 1 *********/  
        
        
        
      /***********The Part 1*************************************************************/
       
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
        
        
    }
    
    /*****************{ CLASS }*************************/
}
