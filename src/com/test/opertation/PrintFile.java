/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.opertation;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author formation
 */
public class PrintFile {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        try { 
            File excel = new File("E:\\App1.xlsx");
            fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            System.out.println("the workbook is walking");
            XSSFSheet worksheet =  book.getSheetAt(0);
            
            //PageSetup pageSetup = worksheet.getPageSetup(); 
      
    Sheet sheet = book.createSheet("Sheet1");
           Desktop dt = Desktop.getDesktop();
dt.open(new File("E:\\App1.xlsx"));
    //sets the print area for the first sheet
    book.setPrintArea(0, "$A$1:$C$2");
     
    /*setPrintArea(
            0, //sheet index
            0, //start column
            1, //end column
            0, //start row
            0  //end row
    );*/

    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
    book.write(fileOut);
    fileOut.close();
              
            System.out.println("SUCCEFULE PRINTING");
     
     
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
           
                
            } catch (IOException ex) {
                Logger.getLogger(PrintFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
