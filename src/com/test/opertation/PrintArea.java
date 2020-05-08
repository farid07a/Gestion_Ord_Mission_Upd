/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.opertation;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class PrintArea 
{
   public static void main(String[] args)throws Exception 
   {
      XSSFWorkbook workbook = new XSSFWorkbook(); 
      XSSFSheet spreadsheet = workbook
      .createSheet("Print Area");
      //set print area with indexes
      workbook.setPrintArea(
      0, //sheet index
      0, //start column
     20, //end column
      0, //start row
     7 //end row
      );
      //set paper size
      spreadsheet.getPrintSetup().setPaperSize(
      XSSFPrintSetup.A4_PAPERSIZE);
      //set display grid lines or not
      spreadsheet.setDisplayGridlines(true);
     //set print grid lines or not
     spreadsheet.setPrintGridlines(true);
     FileOutputStream out = new FileOutputStream(
     new File("D:\\FileDRC.xlsx"));
     workbook.write(out);
     out.close();
     System.out.println("printarea.xlsx written successfully"); 
   }
}