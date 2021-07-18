/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DifferenceDqte {
    public static void main(String[] args) {     
        Date d=new Date();
        Calendar cld=GregorianCalendar.getInstance();
        cld.setTime(d);
        
        int Hour=cld.get(Calendar.HOUR_OF_DAY);
        int Min=cld.get(Calendar.MINUTE);
        
        System.out.println("The hours is :"+Hour);
         System.out.println("The hours is :"+Min);       
    }
 
}
