/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author formation
 */

    
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
/**
 *
 * @author admin
 */
public class AddFunction {
    
    
    /********SumCompensationTransport************/
    private CellReference SumCompTransport;
    
    /*******************************************/
    
    /*********SumCompensationToujours********/
    private CellReference SumCompToujours;
    /***************************************/
    
    
    private CellReference NumberEatResNrd;
    private CellReference NumberDrtResNrd;
    
    private CellReference NumberEatResSud;
    private CellReference NumberDrtResSud;
    
    
    private CellReference SomEatNord;
    
    /*************************************************/
    private CellReference CalprixCompEatNord;
    private CellReference CalprixCompDrtNord;
    
    
    private CellReference CalprixCompEatSud;
    private CellReference CalprixCompDrtSud;
    /*************************************************/
    XSSFSheet sheet1,sheet2;
    
    public void SumCompensationTransport(){
    
        
    SumCompTransport=new CellReference("N15");
    
    XSSFCell CellSumCompTransport;
        XSSFRow  RowUsd=sheet1.getRow(SumCompTransport.getRow());
        
        ///RowUsd=sheet1.getRow(14);
        
        CellSumCompTransport=RowUsd.getCell(SumCompTransport.getCol());
        CellSumCompTransport.setCellFormula("SUM(N8:N13)");
        
    }
    
    
    
    public void SumCompensationToujours(){
    SumCompToujours=new CellReference("N23");
    XSSFCell CellSumCompToujours;
    XSSFRow RowUsd=sheet1.getRow(SumCompToujours.getRow());
    ///RowUsd=sheet1.getRow(23);
    
    CellSumCompToujours=RowUsd.getCell(SumCompToujours.getCol());
    CellSumCompToujours.setCellFormula("SUM(N18:N22)");
    }
    
    
    
    public void GetNbrCompensationNrd(){
        
        CellReference RefSomeEatNord = null;   //ce cellule pour gtrouver la result de somme et copier dans les champ 
        CellReference RefSomeDrtNord=null;
        
        
        int x_NumberDrtResNrd=(int) sheet2.getRow(RefSomeEatNord.getRow()).getCell(RefSomeEatNord.getCol()).getNumericCellValue(); //sheet 2 a de sheet1
        int y_NumberEatResNrd=(int) sheet2.getRow(RefSomeDrtNord.getRow()).getCell(RefSomeDrtNord.getCol()).getNumericCellValue();
        
         NumberEatResNrd =new CellReference("P18");
         NumberDrtResNrd=new CellReference("P19");
        
        sheet1.getRow(NumberDrtResNrd.getRow()).getCell(NumberDrtResNrd.getCol()).setCellValue(x_NumberDrtResNrd);
        sheet1.getRow(NumberEatResNrd.getRow()).getCell(NumberEatResNrd.getCol()).setCellValue(y_NumberEatResNrd);
               
    }
    
    public void GetNbrCompensationSud(){
        
        CellReference RefSomeEatSud = null;
        CellReference RefSomeDrtSud=null;
        
        
        NumberEatResSud=new CellReference("P20");         
        NumberDrtResSud=new CellReference("P21");
        
         int x_NumberDrtResSud=(int) sheet2.getRow(RefSomeEatSud.getRow()).getCell(RefSomeEatSud.getCol()).getNumericCellValue(); //sheet 2 a de sheet1
        int y_NumberEatResSud=(int) sheet2.getRow(RefSomeDrtSud.getRow()).getCell(RefSomeDrtSud.getCol()).getNumericCellValue();
        
        
        sheet1.getRow(NumberDrtResSud.getRow()).getCell(NumberDrtResSud.getCol()).setCellValue(x_NumberDrtResSud);
        sheet1.getRow(NumberEatResSud.getRow()).getCell(NumberEatResSud.getCol()).setCellValue(y_NumberEatResSud);
        
    }
    
    public void PrixMission(){
    
    
    }
    
    
    public void CalculateCompensationToujour(){
        
        CalprixCompEatNord =new CellReference("N18");
        CalprixCompDrtNord=new CellReference("N19");
        
        CalprixCompEatSud=new CellReference("");
        CalprixCompDrtSud=new CellReference("");
        
        
        //Calcule The prix After create Cell
        
        sheet1.getRow(CalprixCompEatNord.getRow()).getCell(CalprixCompEatNord.getCol()).setCellFormula("PRODUCT(P18,O18,)");
        sheet1.getRow(CalprixCompDrtNord.getRow()).getCell(CalprixCompDrtNord.getCol()).setCellFormula("PRODUCT(P19,O19,)");
        
    }
    
    
}

