/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author formation
 */
public class Remplir_Cell {
    private File fileExcel;
    File excel = new File("C:\\App1.xlsx");
    private FileInputStream f_in;
    private FileOutputStream F_out;
    private XSSFWorkbook workbook;
    private XSSFSheet Sheet1,Sheet2;
    private Row rowSheet2;
    private int FirstLine=8;
    private CellReference CellCauseDeplasse,CellDepart,CellDistinataire,CellDateDepart,CellDateArrive,CellMoyenTrans,//Sheet2
                          CellTrain,CellCar,CellAvion,CellNbrKm,CellcompensationNord,CellcompensationSud,CellDortoirNord,
                          CellDortoirSud,CellRemarque;
    private CellReference CellName,CellDateTravel,CellGrade,CellJob,CellSemNum,CellResAdmstr,CellCCP,CellCapacite,Cellpermis,CellZoneRegion,
                          CellTax,CellKlmAEmplye,CellKlmAused;
 
    private CellReference CellNumMoy;
    private CellReference CellDateDecision;
    
    private CellReference CellNumTrsp;// Cell For calcule 
    
    public Remplir_Cell(){
        try {
            this.f_in = new FileInputStream(excel);
            this.F_out=new FileOutputStream(excel);
        } catch (FileNotFoundException ex) {
           JOptionPane.showMessageDialog(null, "eroooooor in inisialise stream");
        }
        
    }
    
    
      public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    
    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    
    public XSSFSheet getSheet1() {
        return Sheet1;
    }

   
    public void setSheet1(XSSFSheet Sheet) {
        this.Sheet1 = Sheet;
    }
    
      public XSSFSheet getSheet2() {
        return Sheet2;
    }

   
    public void setSheet2(XSSFSheet Sheet) {
        this.Sheet2 = Sheet;
    }
    
    
    public void SetFileInput(FileInputStream f){
    this.f_in=f;
    }
    
    public FileInputStream GetFileIput(){
    return f_in;
    }
    
    public void SetFileOutput(FileOutputStream fout){
    F_out=fout;
    }
    
    public FileOutputStream getFileOutput(){
    return  F_out;
    }
    
    /***********************************************************/
    public void Inisialise2(){
        // SetFileInput(  new FileInputStream(excel) );
        
        System.out.println("The inputStreamfile is Created ");
        
        workbook=new XSSFWorkbook();
        System.out.println("The woorkbook is created ");
        
        System.out.println("The sheet 1 is geted");
        Sheet2=workbook.getSheetAt(1);
        System.out.println("The sheet 2 is geted");
         CellCauseDeplasse = new CellReference("R9");
         CellDepart =new CellReference("Q9");
         CellDistinataire=new CellReference("O9");
         CellDateDepart =new CellReference("N9");
         CellDateArrive=new CellReference("M9");
         CellMoyenTrans=new CellReference("L9");
         CellTrain=new CellReference("J9");
         CellCar=new CellReference("I9");
         CellAvion=new CellReference("H9");
         CellNbrKm=new CellReference("G9");
         CellcompensationNord=new CellReference("F9");
         CellcompensationSud=new CellReference("E9");
         CellDortoirNord=new CellReference("D9");
         CellDortoirSud=new CellReference("C9");
         
         System.out.println("The inistialisation is created");
        
        try {
            SetFileOutput(new FileOutputStream(excel));
            
            //F_out=new FileOutputStream(excel);
            
        } catch (FileNotFoundException ex) {
           JOptionPane.showMessageDialog(null, "ERROR IN FILEOUTPUTSTREAM"+ex.getMessage());
        }
     
        
        Close_FileInput_Stream();
        Close_FileOut_Stream();
    }
    /***************************************************/
    public void Close_FileOut_Stream(){
        try {
            F_out.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in close File_Ip_Op_Stream"+e.getMessage());
        }
    }
/******************************************************/
    
    public void Close_FileInput_Stream(){
        try {
            f_in.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in close File_Ipnput_Stream"+e.getMessage());
        }
    }
    
    /***************************************************/
    public void Inisialise1(){
      try {
            SetFileInput(  new FileInputStream(excel) );
            
            System.out.println("The inputStreamfile is Created ");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Eror in File Excel"+ex.getMessage());
            
        }
        try {
            workbook=new XSSFWorkbook(GetFileIput());
            System.out.println("The woorkbook is created ");
        } catch (IOException ex) {
         JOptionPane.showMessageDialog(null, "Eror in Workbook "+ex.getMessage());

        }
    Sheet1=workbook.getSheetAt(0);
    
    /*CellName,CellDateTravel,CellGrade,CellJob,CellSemNum,CellResAdmstr,CellCCP,CellCapacite,Cellpermis,CellZoneRegion,
                          CellTax,CellKlmAEmplye,CellKlmAused;*/
    CellName=new CellReference("F12");
    CellDateTravel=new CellReference("A12");
    CellGrade=new CellReference("F15");
    CellJob=new CellReference("B15");
    CellSemNum=new CellReference("B17");
    CellResAdmstr=new CellReference("F19");
    CellCCP=new CellReference("A19");
    CellCapacite=new CellReference("C26");
    CellZoneRegion=new CellReference("B33");
    CellTax=new CellReference("B35");
    CellKlmAEmplye=new CellReference("B37");
    CellKlmAused=new CellReference("B39");
    CellDateDecision=new CellReference("B31");
    
    }
    
    /********************************************************/
    public int GetColum(CellReference CellRef){
    return CellRef.getCol();
    }
    public int GetRow(CellReference CellRef){
    return CellRef.getRow();
    }
    
    public void CloseFileInput(){
        try {
            f_in.close();
                F_out.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ERROR In Closed File"+ex.getMessage());
        }
    }
    
    
    public void CreateCell(XSSFSheet Sheet,CellReference cellREF,String Valeur){
   
      /*  Cell cell1;
        Row rowusd=null;
        //rowusd=Sheet.createRow(FirstLine);
        //rowusd=Sheet.getRow(FirstLine);
        System.out.println("The Row number is :"+ FirstLine+"  and The Colone is : "+GetColum(cellREF));
        
        //cell1=rowusd.createCell(GetColum(cellREF));
        
        rowusd=Sheet.getRow(cellREF.getRow());
        cell1=rowusd.getCell(cellREF.getCol());
        //cellREF.getRow();
        
        
        System.out.println("The Cellule Row :"+FirstLine +"  Col :"+GetColum(cellREF)+"is created ..");
        
        try {
           cell1.setCellValue(Valeur); 
            System.out.println("The values is created in File ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eroor insert Data IN Cell"+ e.getMessage());
        }
        */
            Cell cell = null;
             Sheet=Sheet2;
            //int a=worksheet.getLastRowNum();
             cell=Sheet.getRow(1).getCell(1);
            System.out.println("error in initiale Cell");
            
            CellReference cellReference = new CellReference("C9");
            cellReference=CellCauseDeplasse;
            System.out.println("Thr of reference B1 Row ="+cellReference.getRow()+"Colum ="+cellReference.getCol());
            
            
            Row row = Sheet.getRow(cellReference.getRow());
            
            
            
           // JOptionPane.showMessageDialog(null, "The Rowwwwwwwww is :"+cellReference.getRow()+"The colonnnnnnnnnnne:"+cellReference.getCol());
            cell = row.getCell(cellReference.getCol());
            
            System.out.println("The file is existing ");
            
            cell.setCellValue("hellllllllo");
    
        
    }
    
    
    /*******************************************************/
    public void Remplir_Sheet1(){
            
            
        
        
                                
                                
    }
    
    
    public void Remplir_Sheet2(/*int Line,XSSFSheet sheet,String Cause,String Dpt,String dst,String DateDpt,String DateArr,String MoyTrsp,
                                String Train,String Car,String Avion,int Nbrkm,int comNord,int comSud,int DrtNord,int DrtSud*/){
       XSSFSheet sheet2=getSheet2();
      
       Inisialise2(); //Appel Inisialise Sheet_2
       
        int i=0;
        
        CreateCell(sheet2, CellCar, "السلام");
        
        
         HSSFCellStyle my_style;
         
         
         
         /* while(i<5){
        CreateCell(sheet2, CellCar, "A");
        FirstLine++;
       i++;
       }*/
        try {
            workbook.write(F_out);
            CloseFileInput();
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(null, "ERROR WRITE IN EXCEL"+ex.getMessage());
        }
        
        
         Desktop dt = Desktop.getDesktop();
        try {
            dt.open(fileExcel);
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(null, "Erro In opned File Excel" +ex.getMessage());
        }
    }
    public Row  GetRowSheet2(){
    return rowSheet2;
    }
    
    
    
    public void Write_In_File(){
    
        File excel = new File("C:\\App1.xlsx");
        try { 
            FileInputStream fis = new FileInputStream(excel);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error in file input Stream ");
        }
            XSSFWorkbook book=null;
            try {
                book = new XSSFWorkbook(new FileInputStream(excel));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error in XSSFWORKBOOK ");
            }
            System.out.println("the workbook is walking");
            XSSFSheet sheet1 =  book.getSheetAt(0);
            
            System.out.println("The sheet is walking");
            FormulaEvaluator evaluator = book.getCreationHelper().createFormulaEvaluator();
            Cell cell = null;
    
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Remplir_Cell rc=new Remplir_Cell();
      rc.Inisialise2();
      /* rc.Remplir_Sheet2();
 
            File excel = new File("C:\\App1.xlsx");
            FileInputStream fis = new FileInputStream(excel); 
            XSSFWorkbook book = new XSSFWorkbook(fis);
            System.out.println("the workbook is walking");
            XSSFSheet worksheet =  book.getSheetAt(1);
             Cell cell = null;
              
            //int a=worksheet.getLastRowNum();
             cell=worksheet.getRow(1).getCell(1);
            System.out.println("error in initiale Cell");
            
            CellReference cellReference = new CellReference("C9");
            System.out.println("Thr of reference B1 Row ="+cellReference.getRow()+"Colum ="+cellReference.getCol());
            
            
            Row row = worksheet.getRow(cellReference.getRow());
            
            
            
            JOptionPane.showMessageDialog(null, "The Rowwwwwwwww is :"+cellReference.getRow()+"The colonnnnnnnnnnne:"+cellReference.getCol());
            cell = row.getCell(cellReference.getCol());
            
            System.out.println("The file is existing ");
            
            cell.setCellValue("hellllllllo");
            System.out.println("the text inerted in cell");
            fis.close();
            
                        book.setPrintArea(0,0 , 8,0 ,6);
            
             worksheet.getPrintSetup().setPaperSize(
      XSSFPrintSetup.A4_PAPERSIZE);
      //set display grid lines or not
      worksheet.setDisplayGridlines(true);
     //set print grid lines or not
     worksheet.setPrintGridlines(true);
            
            
            FileOutputStream fio=new FileOutputStream(new File("C:\\App1.xlsx"));
            book.write(fio);
            
            

            
            System.out.println("The workbook is created ....");
            fio.close();
            System.out.println("the file is closed");    
            
            
            
      
            //JOptionPane.showMessageDialog(null, "errrrrrrrrrrrrrrrrrrrrrrr"+e.getMessage());
     
        
        Desktop dt = Desktop.getDesktop();
dt.open(new File("E:\\App1.xlsx"));
        
            



*/
      
      
    }

  
    
    
}
