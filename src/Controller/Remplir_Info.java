/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.OrdMission;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author formation
 */
public class Remplir_Info {
    

   
    //private File file=new File("src\\OurFile\\App11.xlsx");
   // private File file=new File("App11.xlsx");
    private File file=new File("D:\\FileDRC.xlsx");
    
    FileInputStream file1;
    private String OutNameFile;
    
    private XSSFWorkbook workbook=null;
    private XSSFSheet Sheet1,Sheet2;
    private int Num_Line=8;
    private int LineSome=40;
    private CellReference CellCauseDeplasse,CellDepart,CellDistinataire,CellDateDepart,CellHeurDepart,CellDateArrive,CellHeurArrive,CellMoyenTrans,//Sheet2
                          CellTrain,CellCar,CellAvion,CellNbrKm,CellcompensationNord,CellcompensationSud,CellDortoirNord,
                          CellDortoirSud,CellRemarque;
    private CellReference CellName,CellDateTravel,CellGrade,CellJob,CellSemNum,CellResAdmstr,CellCCP,CellMy_Capacity,Cellpermis,CellZoneRegion,
                          CellTax,CellKlmAEmplye,CellKlmAused;
 
    
    private CellReference CellBenificeMinus,CellPrix_Benificie,CellNotBenificier;
    private CellReference CellNumMoy;
    private CellReference CellDateDecision;
    
    private CellReference CellNumTrsp;// Cell For calcule 
    /***************************************************************/
    private CellReference SomeDrtoirNord;
    private CellReference SomeDrtoirSud;
    private CellReference SomeEatNord;
    private CellReference SomeEatSud;
    
    
     /********SumCompensationTransport************/
    private CellReference SumCompTransport =new CellReference("N15");;
    
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
    private CellReference prixCompEatNord;
    private CellReference prixCompDrtNord;
    
    
    private CellReference prixCompEatSud;
    private CellReference prixCompDrtSud;
    /*************************************************/
    
    
    public  Remplir_Info(){
        try {
            this.file1 = new FileInputStream("App11.xlsx");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Remplir_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
     
    
    
    } 
    
   
    
/********************************************************************************/    
    public void Inisialise_Wrk(){
        try {
            workbook=new XSSFWorkbook(new FileInputStream(file)); //first Code 
            
           
            //workbook=new XSSFWorkbook(file1); //Trial Code 
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "1- Error InisialiseWrkb"+ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "2- Error InisialiseWrkb"+ex.getCause());
        }
    }
 /******************************************************************************/   
    public void Inisialise_Sheet1(){
    Sheet1=workbook.getSheetAt(0);
    }
    public void Inisialise_Sheet2(){
    Sheet2=workbook.getSheetAt(1);
    }
    
    /**********************Insialise_ReferenceSh1*****************************/
public void Insialise_ReferenceSh1(){

    
//CellName,CellDateTravel,CellGrade,CellJob,CellSemNum,CellResAdmstr,CellCCP,CellCapacite,Cellpermis,CellZoneRegion,
//CellTax,CellKlmAEmplye,CellKlmAused;

CellName=new CellReference("F12");
CellDateTravel=new CellReference("A12");
CellGrade=new CellReference("F15");
CellJob=new CellReference("B15");
CellSemNum=new CellReference("B17");
CellResAdmstr=new CellReference("F19");
CellCCP=new CellReference("A19");
//CellBenificeMinus=new CellReference("F24");
//CellPrix_Benificie=new CellReference("A24");
//CellMy_Capacity=new CellReference("C26");
//CellNotBenificier=new CellReference("A28");
/*CellDateDecision=new CellReference("B31");
CellZoneRegion=new CellReference("B33");
CellTax=new CellReference("B35");
CellKlmAEmplye=new CellReference("B37");
CellKlmAused=new CellReference("B39");*/
}
/***********************************Remplir Sheet1***************************/
public void Remplir_Sheet1(String FullName,String DateTravel,String Grade,String Job,String SemNum,String ResAdmstr,String CCP,
                              String BenificeMinus,String Prix_Benificie,String My_Capacity,String NotBenificier,
                              Date DateDecision,String ZoneRegion,String Tax,int klmAemp,int KlmAused 
                                ){
Inisialise_Wrk();
    
    System.out.println("Inisialise_Wrk();");
    Inisialise_Sheet1();
     System.out.println("Inisialise_Sheet1();");
    Insialise_ReferenceSh1();
     System.out.println("Insialise_ReferenceSh1();");
   // JOptionPane.showMessageDialog(null, "The inisialsed is created");
   /**************************/

    
    
    
    /**********************/
   
   
   
    InsertData_1(Sheet1, CellName, FullName);
    System.out.println("Controller.Remplir_Info.Remplir_Sheet1() CellName");
    InsertData_1(Sheet1, CellDateTravel, DateTravel);
    System.out.println("Controller.Remplir_Info.Remplir_Sheet1() CellDateTravel");
    InsertData_1(Sheet1, CellGrade, Grade);
    System.out.println("Controller.Remplir_Info.Remplir_Sheet1() CellGrade");
    InsertData_1(Sheet1, CellJob, Job);
    System.out.println("Controller.Remplir_Info.Remplir_Sheet1() CellJob");
    InsertData_1(Sheet1, CellSemNum,SemNum);
    System.out.println("Controller.Remplir_Info.Remplir_Sheet1() CellSemNum");
    InsertData_1(Sheet1, CellResAdmstr,ResAdmstr);
    System.out.println("Controller.Remplir_Info.Remplir_Sheet1() CellResAdmstr");
    InsertData_1(Sheet1, CellCCP,CCP);
    //InsertData_1(Sheet1, CellBenificeMinus,BenificeMinus);
    //InsertData_1(Sheet1, CellPrix_Benificie,Prix_Benificie);
    //InsertData_1(Sheet1, CellMy_Capacity,My_Capacity);
    //InsertData_1(Sheet1, CellNotBenificier,NotBenificier);
    //InsertData_1(Sheet1, CellDateDecision,DateDecision);
    //InsertData_1(Sheet1, CellZoneRegion,ZoneRegion);
    //InsertData_1(Sheet1, CellTax,Tax);
    //InsertData_1(Sheet1, CellKlmAEmplye,klmAemp);
    //InsertData_1(Sheet1, CellKlmAused,KlmAused);
   // Write_In_WorkBook();
    System.out.println("The Sheet1 Inistialised  .....");
    //Close_Wrbk();
   }
public void RemplirSomDrt(){
    
   
        //Insialise_ReferenceSh2();
      
        /* SomeEatNord=new CellReference("D41");
         SomeEatSud=new CellReference("C41");
             SomeDrtoirNord=new CellReference("F41");
         SomeDrtoirSud=new CellReference("E41");*/
        
        SomeDrtoirNord=new CellReference("D41");
       SomeDrtoirSud  =new CellReference("C41");
       SomeEatNord      =new CellReference("F41");
     SomeEatSud    =new CellReference("E41");
         
  XSSFCell CelSmDrtNrd,CelSmDrtSud,CelSmEatNrd,CelSmEatSud;
       XSSFRow  RowUsd;
        RowUsd=Sheet2.getRow(40);
        
        //RowUsd=sheetPr.getRow(Num_Line);
        /*
         Inisialise_Wrk();
        Inisialise_Sheet2();
        
         SomeDrtoirNord=new CellReference("F41");
         SomeDrtoirSud=new CellReference("E41");
         SomeEatNord=new CellReference("D41");
         SomeEatSud=new CellReference("C41");*/
        CelSmDrtNrd=RowUsd.getCell(SomeDrtoirNord.getCol());
        
        
        
        CelSmDrtSud=RowUsd.getCell(SomeDrtoirSud.getCol());
        CelSmEatNrd=RowUsd.getCell(SomeEatNord.getCol());
        CelSmEatSud=RowUsd.getCell(SomeEatSud.getCol());
        
        
        //JOptionPane.showMessageDialog(null, "After GetColone ");
        
        CelSmDrtNrd.setCellType(CelSmDrtNrd.CELL_TYPE_FORMULA);
        String Formula=("SUM(D9:D39)");
        CelSmDrtNrd.setCellFormula(Formula);
        
        CelSmDrtSud.setCellFormula("SUM(C9:C39)");
        
        CelSmEatNrd.setCellFormula("SUM(F9:F39)");
        
        CelSmEatSud.setCellFormula("SUM(E9:E39)");
        
        System.out.println("THE VALUE IS inserted ...............");
        System.out.println("The end of methode ");
        
}

/*********************Insialise_ReferenceSh2****************************/
    public void Insialise_ReferenceSh2(/*int Num_Line_Ref*/){
        int Num_Line_Ref=Num_Line+1;
        
      /********
        CellCauseDeplasse = new CellReference("T9");
         CellDepart =new CellReference("S9");
         CellDistinataire=new CellReference("Q9");
         CellDateDepart =new CellReference("P9");
         CellHeurDepart=new CellReference("O9");
         CellDateArrive=new CellReference("N9");
         CellHeurArrive=new CellReference("M9");
         CellMoyenTrans=new CellReference("L9");
         CellTrain=new CellReference("J9");
         CellCar=new CellReference("I9");
         CellAvion=new CellReference("H9");
         CellNbrKm=new CellReference("G9");
         CellcompensationNord=new CellReference("F9");
         CellcompensationSud=new CellReference("E9");
         CellDortoirNord=new CellReference("D9");
         CellDortoirSud=new CellReference("C9");
         CellRemarque=new CellReference("B9");
         *                                  ***********/
      CellCauseDeplasse = new CellReference("T"+Num_Line_Ref);
         CellDepart =new CellReference("S"+Num_Line_Ref);
         CellDistinataire=new CellReference("R"+Num_Line_Ref);
         CellDateDepart =new CellReference("P"+Num_Line_Ref);
         CellHeurDepart=new CellReference("O"+Num_Line_Ref);
         CellDateArrive=new CellReference("N"+Num_Line_Ref);
      
         CellHeurArrive=new CellReference("M"+Num_Line_Ref);
         CellMoyenTrans=new CellReference("L"+Num_Line_Ref);
         //CellTrain=new CellReference("J"+Num_Line_Ref);
         //CellCar=new CellReference("I"+Num_Line_Ref);
         //CellAvion=new CellReference("H"+Num_Line_Ref);
         //CellNbrKm=new CellReference("G"+Num_Line_Ref);
         CellcompensationNord=new CellReference("F"+Num_Line_Ref);
         CellcompensationSud=new CellReference("E"+Num_Line_Ref);
         CellDortoirNord=new CellReference("D"+Num_Line_Ref);
         CellDortoirSud=new CellReference("C"+Num_Line_Ref);
         CellRemarque=new CellReference("B"+Num_Line_Ref);
         
        
         
    }      
    /************************************/
    CellReference CellPrix_Train;
    CellReference CellPrix_Bus;
    CellReference CellPrix_Boats_Plan;
    CellReference CellNbrKlm_Train;
    CellReference CellNbrKlm_Bus;
    CellReference CellNbrklm_Boats_Plan;
    CellReference Cell_1000_Klm;
    CellReference Cell_apres_1000_Klm;
    CellReference Cell_Prix_apres_1000_Klm;
    CellReference Cell_Prix_1000_Klm;
    /**********************************/
    CellReference CellNumTicket_Train,CellNumTicket_Bus,CellNumTicket_Boat_plan;
    /*************************************************/
    public void InitialiseZoneTravelsCars(){
        
    CellPrix_Train=new CellReference("O8");
    CellPrix_Bus=new CellReference("O10");
    CellPrix_Boats_Plan=new CellReference("O11");
    /*********************************/
    CellNbrKlm_Train=new CellReference("P8");
    CellNbrKlm_Bus=new CellReference("P10");
    CellNbrklm_Boats_Plan=new CellReference("P11");
    /************************************/
    Cell_apres_1000_Klm=new CellReference("Q14");
    Cell_1000_Klm=new CellReference("Q12");
    /************************************/
    Cell_Prix_apres_1000_Klm=new CellReference("O12");
    Cell_Prix_1000_Klm=new CellReference("O14");
    }
    public void CalculePrixMultNbrKm(){
    double v=Sheet1.getRow(CellNbrKlm_Train.getRow()).getCell(CellNbrKlm_Train.getCol()).getNumericCellValue();
    //JOptionPane.showMessageDialog(null, "The value is ::::"+v);
    }
    
    
    public void InitialiseCellReductionPrice(int NumberOrientation,int valNrd25,int ValSud25){
    //U18 U19
    
    
   CellReference NoPriseEnchargeEatNrd =new CellReference("U18");
    CellReference NoPriseEnchargedrtNrd =new CellReference("U19"); 
        if (NumberOrientation==1) {
             Sheet1.getRow(NoPriseEnchargeEatNrd.getRow()).getCell(NoPriseEnchargeEatNrd.getCol()).setCellValue("متكفل بها");
              Sheet1.getRow(NoPriseEnchargedrtNrd.getRow()).getCell(NoPriseEnchargedrtNrd.getCol()).setCellValue("متكفل بها");
            
        }else{
        
             CellReference NoPriseEnchargeEatSud =new CellReference("U20");
    CellReference NoPriseEnchargedrtSud =new CellReference("T21"); 
            
            if (valNrd25==1 && ValSud25==1) {
               Sheet1.getRow(NoPriseEnchargeEatNrd.getRow()).getCell(NoPriseEnchargeEatNrd.getCol()).setCellValue("متكفل بها");
              Sheet1.getRow(NoPriseEnchargedrtNrd.getRow()).getCell(NoPriseEnchargedrtNrd.getCol()).setCellValue("متكفل بها");
            
              Sheet1.getRow(NoPriseEnchargeEatSud.getRow()).getCell(NoPriseEnchargeEatSud.getCol()).setCellValue("متكفل بها");
              Sheet1.getRow(NoPriseEnchargedrtSud.getRow()).getCell(NoPriseEnchargedrtSud.getCol()).setCellValue("متكفل بها");
            
                
                
            }else if(valNrd25==1) {
            Sheet1.getRow(NoPriseEnchargeEatNrd.getRow()).getCell(NoPriseEnchargeEatNrd.getCol()).setCellValue("متكفل بها");
              Sheet1.getRow(NoPriseEnchargedrtNrd.getRow()).getCell(NoPriseEnchargedrtNrd.getCol()).setCellValue("متكفل بها");
        
            }else {
            Sheet1.getRow(NoPriseEnchargeEatSud.getRow()).getCell(NoPriseEnchargeEatSud.getCol()).setCellValue("متكفل بها");
              Sheet1.getRow(NoPriseEnchargedrtSud.getRow()).getCell(NoPriseEnchargedrtSud.getCol()).setCellValue("متكفل بها");
            
            }
        
        
        }
    }
public void CalculePrix_25(){
    
CellReference CellPrice_Bnf_Repat_Nord=new CellReference("N18");

//JOptionPane.showMessageDialog(null, "The Value is For "+Sheet1.getRow(CellPrice_Bnf_Repat_Nord.getRow()).getCell(CellPrice_Bnf_Repat_Nord.getCol()).getNumericCellValue());
Sheet1.getRow(CellPrice_Bnf_Repat_Nord.getRow()).getCell(CellPrice_Bnf_Repat_Nord.getCol()).
        setCellValue(500 );
           
  //      setCellValue( Sheet1.getRow(CellPrice_Bnf_Repat_Nord.getRow()).getCell(CellPrice_Bnf_Repat_Nord.getCol()).getNumericCellValue());
            


XSSFCell CellUsedPrice;
XSSFRow RowUsd;
/*XSSFRow RowUsd=Sheet1.getRow(CellPrice_Bnf_Repat_Nord.getRow());
CellUsedPrice=RowUsd.getCell(CellPrice_Bnf_Repat_Nord.getCol());

JOptionPane.showMessageDialog(null, "The Value Geted :"+CellUsedPrice.getNumericCellValue());
CellUsedPrice.setCellValue(200);
*/
CellReference Price_Bnf_Dort_Nord=new CellReference("N19");
RowUsd=Sheet1.getRow(Price_Bnf_Dort_Nord.getRow());
CellUsedPrice=RowUsd.getCell(Price_Bnf_Dort_Nord.getCol());
//JOptionPane.showMessageDialog(null, "The Value Geted :"+CellUsedPrice.getNumericCellValue());
CellUsedPrice.setCellValue(CellUsedPrice.getNumericCellValue()*0.25);

    }
    
     public void SumKlmAnsianValueAddNew(int CarKind,int NbrKlm,double PrixTravel){
    
        switch(CarKind){
        case 1:
            Sheet1.getRow(CellNbrKlm_Train.getRow()).getCell(CellNbrKlm_Train.getCol()).setCellValue( Sheet1.getRow(CellNbrKlm_Train.getRow()).getCell(CellNbrKlm_Train.getCol()).getNumericCellValue()+NbrKlm);
            Sheet1.getRow(CellPrix_Train.getRow()).getCell(CellPrix_Train.getCol()).setCellValue(Sheet1.getRow(CellPrix_Train.getRow()).getCell(CellPrix_Train.getCol()).getNumericCellValue()+PrixTravel);
            
            break;
        case 2:
            Sheet1.getRow(CellNbrKlm_Bus.getRow()).getCell(CellNbrKlm_Bus.getCol()).setCellValue(Sheet1.getRow(CellNbrKlm_Bus.getRow()).getCell(CellNbrKlm_Bus.getCol()).getNumericCellValue()+NbrKlm);
            
            Sheet1.getRow(CellPrix_Bus.getRow()).getCell(CellPrix_Bus.getCol()).setCellValue(Sheet1.getRow(CellPrix_Bus.getRow()).getCell(CellPrix_Bus.getCol()).getNumericCellValue()+PrixTravel);
            break;
        case 3:
            Sheet1.getRow(CellNbrklm_Boats_Plan.getRow()).getCell(CellNbrklm_Boats_Plan.getCol()).setCellValue(Sheet1.getRow(CellNbrklm_Boats_Plan.getRow()).getCell(CellNbrklm_Boats_Plan.getCol()).getNumericCellValue()+NbrKlm);
            Sheet1.getRow(CellPrix_Boats_Plan.getRow()).getCell(CellPrix_Boats_Plan.getCol()).setCellValue(Sheet1.getRow(CellPrix_Boats_Plan.getRow()).getCell(CellPrix_Boats_Plan.getCol()).getNumericCellValue()+PrixTravel);
            break;
        case 4:
            break;    
        }
        
        
    }
    
    
    
    
    
/***************************INSERt Data sheet  *********************************/    
     public void Insert_Data(XSSFSheet sheetPr,CellReference Ref,Object val){
      XSSFCell CellUsd;
       XSSFRow  RowUsd;
        RowUsd=sheetPr.getRow(Ref.getRow());
       // RowUsd=sheetPr.getRow(Num_Line);
        CellUsd=RowUsd.getCell(Ref.getCol());
        
  if(val instanceof Integer) {
         int b=(int) val;
            CellUsd.setCellValue(b);    
        }else if(val instanceof java.util.Date)
        {
            
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
	    //short dateFormat = createHelper.createDataFormat().getFormat("dd/MM/yyyy");
            short dateFormat = createHelper.createDataFormat().getFormat("yyyy/MM/dd");
            
	    cellStyle.setDataFormat(dateFormat);
            
            CellUsd.setCellValue((Date) val);    
            //CellUsd.setCellStyle(cellStyle);
        }else if(val instanceof java.lang.String)
        {
          CellUsd.setCellValue((String) val);
        }
    }
     
public void InsertData_1(XSSFSheet sheetPr,CellReference Ref,Object val){
 XSSFCell CellUsd;
       XSSFRow  RowUsd;
        RowUsd=sheetPr.getRow(Ref.getRow()); //this line is used Before Changing
       //  RowUsd=sheetPr.getRow(Num_Line); //this lis testing for work
        System.out.println("RowUsd=sheetPr.getRow(Ref.getRow()) ");
          System.out.println("RowUsd= "+Ref.getRow());
           System.out.println("RowUsd= "+Ref.getCol());
          System.out.println("RowUsd= "+RowUsd.getRowNum());
        CellUsd=RowUsd.getCell(Ref.getCol());
        
        System.out.println("CellUsd=RowUsd.getCell(Ref.getCol()); ");
        
  if(val instanceof Integer) {
         int b=(int) val;
            CellUsd.setCellValue(b);    
        }else if(val instanceof java.util.Date)
        {
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
	    short dateFormat = createHelper.createDataFormat().getFormat("dd/MM/yyyy");
	    cellStyle.setDataFormat(dateFormat);
            CellUsd.setCellValue(Calendar.getInstance());    
            CellUsd.setCellStyle(cellStyle);
        }else if(val instanceof java.lang.String)
        {
          CellUsd.setCellValue((String)val);
        }
  
}
     
     
/************************Remplir_Information**************/
    public void Write_In_WorkBook(String Name){
    
        try {
            //workbook.write(new FileOutputStream("App11.xlsx"));
             workbook.write(new FileOutputStream(Name+".xlsx"));
           //workbook.write(new FileOutputStream("TousOrdMission\\"+Name+".xlsx"));
            
            
            
        } catch (FileNotFoundException ex) {
           JOptionPane.showMessageDialog(null, "1- Error in WRITE IN WORKBOOK"+ex.getMessage());
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(null, "2- Error in WRITE IN WORKBOOK"+ex.getMessage());
        }
    }

   
    
/************************Remplir Sheet 2*******************/    
    public void Remplir_Sheet2(
        String CauseDeplasse,String Depart,String Distinataire,String DateDepart,String HeurDepart,String DateArrive,String HeurArrive,String MoyenTrans,
                                int Train,int Car,int Avion,int NbrKm,
                                int compensationNord,int compensationSud,int DortoirNord,int DortoirSud,String Remarque,int choix){
        /*
        Inisialise_Wrk();
        Inisialise_Sheet2();
        Insialise_ReferenceSh2();
      */
        System.out.println("The cell returned .....");
        
        /**CellCauseDeplasse,CellDepart,CellDistinataire,CellDateDepart,CellDateArrive,CellMoyenTrans,//Sheet2
                          CellTrain,CellCar,CellAvion,CellNbrKm,CellcompensationNord,CellcompensationSud,CellDortoirNord,
                          CellDortoirSud,CellRemarque;**/
        
        Insert_Data(Sheet2, CellCauseDeplasse,CauseDeplasse );
        Insert_Data(Sheet2, CellDepart,Depart );
        Insert_Data(Sheet2, CellDistinataire,Distinataire );
        

        Insert_Data(Sheet2, CellDateDepart,DateDepart );//AncianCode
        //Insert_Data(Sheet2, CellDateDepart,DateDepartS );
        Insert_Data(Sheet2, CellHeurDepart,HeurDepart );
        
        Insert_Data(Sheet2, CellDateArrive,DateArrive );
        Insert_Data(Sheet2, CellHeurArrive,HeurArrive );
        
        Insert_Data(Sheet2, CellMoyenTrans,MoyenTrans );
        /*Insert_Data(Sheet2, CellTrain,Train );
        Insert_Data(Sheet2, CellCar,Car );
        Insert_Data(Sheet2, CellAvion,Avion );
        Insert_Data(Sheet2, CellNbrKm,NbrKm );*/
        
      
       
        //RemplirSomDrt();
        if(choix==1){
        Insert_Data(Sheet2, CellcompensationNord,compensationNord );
        Insert_Data(Sheet2, CellDortoirNord,DortoirNord );    
        }else
        {
          Insert_Data(Sheet2, CellcompensationSud,compensationSud );
        Insert_Data(Sheet2, CellDortoirSud,DortoirSud );
        }
        Insert_Data(Sheet2, CellRemarque,Remarque ); 
        
        
    /*    
    Write_In_WorkBook();
        Close_Wrbk();     */
    }
    
    
    public void Close_Wrbk()
    {
        try {
            workbook.close();
        } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error in close Wrkb"+e.getMessage());
        }
}   
public int GetNum_Line(){
return Num_Line;
}

public void setNum_Line(int NumLine){

Num_Line=NumLine;
}
public String GetOutNamFile(){
return OutNameFile;
}
public void SetOutNameFile(String NameFileOut){
OutNameFile=NameFileOut;
}
/********************************** 1 **********************************/    
 public void SumCompensationTransport(){
    XSSFCell CellSumCompTransport;
        XSSFRow  RowUsd=Sheet1.getRow(SumCompTransport.getRow());
        ///RowUsd=sheet1.getRow(14);
        CellSumCompTransport=RowUsd.getCell(SumCompTransport.getCol());
        CellSumCompTransport.setCellFormula("SUM(N8:N14)");
    }
/*********************************** 2 *******************************/
public void SumCompensationToujours(int NumbOrderMission,int valNrd25,int valSud25){
    SumCompToujours=new CellReference("N23");
    XSSFCell CellSumCompToujours;
    XSSFRow RowUsd=Sheet1.getRow(SumCompToujours.getRow());
    ///RowUsd=sheet1.getRow(23);
    CellSumCompToujours=RowUsd.getCell(SumCompToujours.getCol());
    Produit_Price_AND_NbrCompensation(NumbOrderMission,valNrd25,valSud25); //change at 14:11 18/05/2019
    CellSumCompToujours.setCellFormula("SUM(N18:N22)");
    }
public void SumCompensationToujoursForReduction(){
    SumCompToujours=new CellReference("N23");
    XSSFCell CellSumCompToujours;
    XSSFRow RowUsd=Sheet1.getRow(SumCompToujours.getRow());
    ///RowUsd=sheet1.getRow(23);
    CellSumCompToujours=RowUsd.getCell(SumCompToujours.getCol());
    Produit_Price_AND_NbrCompensationForReductionOneOrientation();
    CellSumCompToujours.setCellFormula("SUM(N18:N19)");
    }

/********************************** 3 *******************************/
public void  TotlaSumBenefit(){
CellReference CellTotal=new CellReference("N31");
XSSFRow Rowusd=Sheet1.getRow(CellTotal.getRow());
XSSFCell CellUsd=Rowusd.getCell(CellTotal.getCol());
CellUsd.setCellFormula("SUM(N25:N29)");
}

/*******************************************************************/



public void Produit_Price_AND_NbrCompensation(int NumberOrientation,int ValNord25,int valSud25){
CellReference CellPrice_Bnf_Repat_Nord=new CellReference("N18");
CellReference Price_Bnf_Dort_Nord=new CellReference("N19");
XSSFCell CellUsedPrice;
XSSFRow RowUsd;
if (NumberOrientation==1) {
RowUsd=Sheet1.getRow(CellPrice_Bnf_Repat_Nord.getRow());
CellUsedPrice=RowUsd.getCell(CellPrice_Bnf_Repat_Nord.getCol());
CellUsedPrice.setCellFormula("PRODUCT(O18:P18)");
/****************************/
RowUsd=Sheet1.getRow(Price_Bnf_Dort_Nord.getRow());
CellUsedPrice=RowUsd.getCell(Price_Bnf_Dort_Nord.getCol());
CellUsedPrice.setCellFormula("PRODUCT(O19:P19)");
}else 
{
 CellReference Price_Bnf_Repat_Sud=new CellReference("N20");
 CellReference Price_Bnf_Dort_Sud=new CellReference("N21");
if (valSud25==1 && ValNord25==1) {
         double ValuePrixMltNbrNordRepat=Sheet1.getRow(NumberEatResNrd.getRow()).getCell(NumberEatResNrd.getCol()).getNumericCellValue()* 
        Sheet1.getRow(prixCompEatNord.getRow()).getCell(prixCompEatNord.getCol()).getNumericCellValue();
        double ValuePrixMltNbrNordDrt=Sheet1.getRow(NumberDrtResNrd.getRow()).getCell(NumberDrtResNrd.getCol()).getNumericCellValue()* 
        Sheet1.getRow(prixCompDrtNord.getRow()).getCell(prixCompDrtNord.getCol()).getNumericCellValue();
Sheet1.getRow(CellPrice_Bnf_Repat_Nord.getRow()).getCell(CellPrice_Bnf_Repat_Nord.getCol()).setCellValue(ValuePrixMltNbrNordRepat*0.25);
Sheet1.getRow(Price_Bnf_Dort_Nord.getRow()).getCell(Price_Bnf_Dort_Nord.getCol()).setCellValue(ValuePrixMltNbrNordDrt*0.25);
        double ValuePrixMltNbrSudRepat=Sheet1.getRow(NumberEatResSud.getRow()).getCell(NumberEatResSud.getCol()).getNumericCellValue()* 
        Sheet1.getRow(prixCompEatSud.getRow()).getCell(prixCompEatSud.getCol()).getNumericCellValue();
        double ValuePrixMltNbrSudDrtoir=Sheet1.getRow(NumberDrtResSud.getRow()).getCell(NumberDrtResSud.getCol()).getNumericCellValue()* 
        Sheet1.getRow(prixCompDrtSud.getRow()).getCell(prixCompDrtSud.getCol()).getNumericCellValue();
Sheet1.getRow(Price_Bnf_Repat_Sud.getRow()).getCell(Price_Bnf_Repat_Sud.getCol()).setCellValue( ValuePrixMltNbrSudRepat*0.25 );
Sheet1.getRow(Price_Bnf_Dort_Sud.getRow()).getCell(Price_Bnf_Dort_Sud.getCol()).setCellValue(ValuePrixMltNbrSudDrtoir*0.25);
    }else if (valSud25==1) {
        RowUsd=Sheet1.getRow(CellPrice_Bnf_Repat_Nord.getRow());
        CellUsedPrice=RowUsd.getCell(CellPrice_Bnf_Repat_Nord.getCol());
        CellUsedPrice.setCellFormula("PRODUCT(O18:P18)");
        RowUsd=Sheet1.getRow(Price_Bnf_Dort_Nord.getRow());
        CellUsedPrice=RowUsd.getCell(Price_Bnf_Dort_Nord.getCol());
        CellUsedPrice.setCellFormula("PRODUCT(O19:P19)");
        double ValuePrixMltNbrSudRepat=Sheet1.getRow(NumberEatResSud.getRow()).getCell(NumberEatResSud.getCol()).getNumericCellValue()* 
        Sheet1.getRow(prixCompEatSud.getRow()).getCell(prixCompEatSud.getCol()).getNumericCellValue();
        double ValuePrixMltNbrSudDrtoir=Sheet1.getRow(NumberDrtResSud.getRow()).getCell(NumberDrtResSud.getCol()).getNumericCellValue()* 
        Sheet1.getRow(prixCompDrtSud.getRow()).getCell(prixCompDrtSud.getCol()).getNumericCellValue();
        /* double valRudRepaSud =   Sheet1.getRow(Price_Bnf_Repat_Sud.getRow()).getCell(Price_Bnf_Repat_Sud.getCol()).getNumericCellValue();
      double valRudDrtSud =   Sheet1.getRow(Price_Bnf_Dort_Sud.getRow()).getCell(Price_Bnf_Dort_Sud.getCol()).getNumericCellValue();*/
     //JOptionPane.showMessageDialog(null, "Caclcule  Repat sud 25 "+ValuePrixMltNbrSudRepat +" and Caclcule  Dertoir  sud 25 "+ValuePrixMltNbrSudDrtoir);
        Sheet1.getRow(Price_Bnf_Repat_Sud.getRow()).getCell(Price_Bnf_Repat_Sud.getCol()).setCellValue( ValuePrixMltNbrSudRepat*0.25 );
        Sheet1.getRow(Price_Bnf_Dort_Sud.getRow()).getCell(Price_Bnf_Dort_Sud.getCol()).setCellValue(ValuePrixMltNbrSudDrtoir*0.25);
        //JOptionPane.showMessageDialog(null, "price Repat sud 25 ");
        }else if(ValNord25==1){
        double ValuePrixMltNbrNordRepat=Sheet1.getRow(NumberEatResNrd.getRow()).getCell(NumberEatResNrd.getCol()).getNumericCellValue()* 
        Sheet1.getRow(prixCompEatNord.getRow()).getCell(prixCompEatNord.getCol()).getNumericCellValue();
        double ValuePrixMltNbrNordDrt=Sheet1.getRow(NumberDrtResNrd.getRow()).getCell(NumberDrtResNrd.getCol()).getNumericCellValue()* 
        Sheet1.getRow(prixCompDrtNord.getRow()).getCell(prixCompDrtNord.getCol()).getNumericCellValue();
        //JOptionPane.showMessageDialog(null, "The Value is Getted is "+ValuePrixMltNbrNord+"The second is "+ValuePrixMltNbrSud);
        Sheet1.getRow(CellPrice_Bnf_Repat_Nord.getRow()).getCell(CellPrice_Bnf_Repat_Nord.getCol()).setCellValue(ValuePrixMltNbrNordRepat*0.25);
        Sheet1.getRow(Price_Bnf_Dort_Nord.getRow()).getCell(Price_Bnf_Dort_Nord.getCol()).setCellValue(ValuePrixMltNbrNordDrt*0.25);
        RowUsd=Sheet1.getRow(Price_Bnf_Repat_Sud.getRow());
        CellUsedPrice=RowUsd.getCell(Price_Bnf_Repat_Sud.getCol());
        CellUsedPrice.setCellFormula("PRODUCT(O20:P20)");
        RowUsd=Sheet1.getRow(Price_Bnf_Dort_Sud.getRow());
        CellUsedPrice=RowUsd.getCell(Price_Bnf_Dort_Sud.getCol());
        CellUsedPrice.setCellFormula("PRODUCT(O21:P21)");
        }else {
        RowUsd=Sheet1.getRow(CellPrice_Bnf_Repat_Nord.getRow());
        CellUsedPrice=RowUsd.getCell(CellPrice_Bnf_Repat_Nord.getCol());
        CellUsedPrice.setCellFormula("PRODUCT(O18:P18)");
        RowUsd=Sheet1.getRow(Price_Bnf_Dort_Nord.getRow());
        CellUsedPrice=RowUsd.getCell(Price_Bnf_Dort_Nord.getCol());
        CellUsedPrice.setCellFormula("PRODUCT(O19:P19)");
        RowUsd=Sheet1.getRow(Price_Bnf_Repat_Sud.getRow());
        CellUsedPrice=RowUsd.getCell(Price_Bnf_Repat_Sud.getCol());
        CellUsedPrice.setCellFormula("PRODUCT(O20:P20)");
        RowUsd=Sheet1.getRow(Price_Bnf_Dort_Sud.getRow());
        CellUsedPrice=RowUsd.getCell(Price_Bnf_Dort_Sud.getCol());
        CellUsedPrice.setCellFormula("PRODUCT(O21:P21)");
     }
     }
    }
public void Produit_Price_AND_NbrCompensationForReductionOneOrientation(){
CellReference CellPrice_Bnf_Repat_Nord=new CellReference("N18");
CellReference Price_Bnf_Dort_Nord=new CellReference("N19");
XSSFCell CellUsedPrice;
/*

XSSFRow RowUsd=Sheet1.getRow(CellPrice_Bnf_Repat_Nord.getRow());
CellUsedPrice=RowUsd.getCell(CellPrice_Bnf_Repat_Nord.getCol());
CellUsedPrice.setCellFormula("PRODUCT(O18:P18)");

CellReference Price_Bnf_Dort_Nord=new CellReference("N19");

RowUsd=Sheet1.getRow(Price_Bnf_Dort_Nord.getRow());
CellUsedPrice=RowUsd.getCell(Price_Bnf_Dort_Nord.getCol());
CellUsedPrice.setCellFormula("PRODUCT(O19:P19)");*/ //The Ancian Code


//NumberEatResNrd     NumberDrtResNrd

//prixCompEatNord     prixCompDrtNord    prixCompEatNord


double ValuePrixMltNbrNord=Sheet1.getRow(NumberEatResNrd.getRow()).getCell(NumberEatResNrd.getCol()).getNumericCellValue()* 
        Sheet1.getRow(prixCompEatNord.getRow()).getCell(prixCompEatNord.getCol()).getNumericCellValue();

double ValuePrixMltNbrSud=Sheet1.getRow(NumberDrtResNrd.getRow()).getCell(NumberDrtResNrd.getCol()).getNumericCellValue()* 
        Sheet1.getRow(prixCompDrtNord.getRow()).getCell(prixCompDrtNord.getCol()).getNumericCellValue();

//JOptionPane.showMessageDialog(null, "The Value is Getted is "+ValuePrixMltNbrNord+"The second is "+ValuePrixMltNbrSud);


Sheet1.getRow(CellPrice_Bnf_Repat_Nord.getRow()).getCell(CellPrice_Bnf_Repat_Nord.getCol()).setCellValue(ValuePrixMltNbrNord*0.25);


Sheet1.getRow(Price_Bnf_Dort_Nord.getRow()).getCell(Price_Bnf_Dort_Nord.getCol()).setCellValue(ValuePrixMltNbrSud*0.25);




//JOptionPane.showMessageDialog(null, "The Value is X:"+ValuePrixMltNbrNord+"  AND Second Value is :"+ValuePrixMltNbrSud);

//Price_Bnf_Dort_Nord
}




public void ChangeThisNumber(){

    CellReference Price_In_Ltr=new CellReference("L36");
    
    XSSFRow rowUsd=Sheet1.getRow(Price_In_Ltr.getRow());
    XSSFCell CellUsd=rowUsd.getCell(Price_In_Ltr.getCol());
    CellUsd.setCellFormula("ChangeThisNumber(10)");
    
}

/**********************************4********************************/
public void GetNbrCompensationNrd(){
        
        CellReference RefSomeEatNord = null;   //ce cellule pour gtrouver la result de somme et copier dans les champ 
        CellReference RefSomeDrtNord=null;
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
        CellValue cellValue;
        cellValue = evaluator.evaluate(Sheet2.getRow(SomeEatNord.getRow()).getCell(SomeEatNord.getCol()));
        
         int X_NumberEatResNrd =(int) cellValue.getNumberValue();
         
         cellValue=evaluator.evaluate(Sheet2.getRow(SomeDrtoirNord.getRow()).getCell(SomeDrtoirNord.getCol()));
         
         int Y_NumberDrtResNrd=(int) cellValue.getNumberValue();
         
        //int X_NumberEatResNrd=(int) Sheet2.getRow(SomeEatNord.getRow()).getCell(SomeEatNord.getCol()).getNumericCellValue(); //sheet 2 a de sheet1
        //int Y_NumberDrtResNrd=(int) Sheet2.getRow(SomeDrtoirNord.getRow()).getCell(SomeDrtoirNord.getCol()).getNumericCellValue();
        
         NumberEatResNrd =new CellReference("P18");
         NumberDrtResNrd=new CellReference("P19");
        
      //   JOptionPane.showMessageDialog(null, "the Nubmer Total Number is :"+ X_NumberEatResNrd +" --"+Y_NumberDrtResNrd);
         
         //Sheet1.getRow(NumberEatResNrd.getRow()).getCell(NumberEatResNrd.getCol()).getcell
         
        Sheet1.getRow(NumberEatResNrd.getRow()).getCell(NumberEatResNrd.getCol()).setCellValue(X_NumberEatResNrd);
        Sheet1.getRow(NumberDrtResNrd.getRow()).getCell(NumberDrtResNrd.getCol()).setCellValue(Y_NumberDrtResNrd);
    }

public void GetNbrCompensationNrdWithReduction(int ChoiceOrientation){
            NumberEatResNrd =new CellReference("P18");
         NumberDrtResNrd=new CellReference("P19");
    
        CellReference RefSomeEatNord = null;   //ce cellule pour gtrouver la result de somme et copier dans les champ 
        CellReference RefSomeDrtNord=null;
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
        CellValue cellValue;
        
        if (ChoiceOrientation==1) {
        cellValue = evaluator.evaluate(Sheet2.getRow(SomeEatNord.getRow()).getCell(SomeEatNord.getCol()));
        int X_NumberEatResNrd =(int) cellValue.getNumberValue();
        
         cellValue=evaluator.evaluate(Sheet2.getRow(SomeDrtoirNord.getRow()).getCell(SomeDrtoirNord.getCol()));
         int Y_NumberDrtResNrd=(int) cellValue.getNumberValue();
         
       Sheet1.getRow(NumberEatResNrd.getRow()).getCell(NumberEatResNrd.getCol()).setCellValue(X_NumberEatResNrd);
        Sheet1.getRow(NumberDrtResNrd.getRow()).getCell(NumberDrtResNrd.getCol()).setCellValue(Y_NumberDrtResNrd);
    }else {
        cellValue=evaluator.evaluate(Sheet2.getRow(SomeEatSud.getRow()).getCell(SomeEatSud.getCol()));
     int X_NumberEatResSud=(int) cellValue.getNumberValue();
     cellValue=evaluator.evaluate(Sheet2.getRow(SomeDrtoirSud.getRow()).getCell(SomeDrtoirSud.getCol()));
     int Y_NumberDrtResSud=(int) cellValue.getNumberValue();
     //JOptionPane.showMessageDialog(null, "The X_NumberEatResSud :"+X_NumberEatResSud+"et  Y_NumberDrtResSud"+Y_NumberDrtResSud);
//     NumberEatResSud=new CellReference("P20");         
//        NumberDrtResSud=new CellReference("P21");   
        Sheet1.getRow(NumberEatResNrd.getRow()).getCell(NumberEatResNrd.getCol()).setCellValue(X_NumberEatResSud);
        Sheet1.getRow(NumberDrtResNrd.getRow()).getCell(NumberDrtResNrd.getCol()).setCellValue(Y_NumberDrtResSud);
         }
}

public  void Date_Delivred(){
CellReference DateDilivred =new CellReference("L38");
 Sheet1.getRow(DateDilivred.getRow()).getCell(DateDilivred.getCol()).setCellValue(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
}
 public void GetNbrCompensationSud(){
     FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
     CellValue cellValue;
     cellValue=evaluator.evaluate(Sheet2.getRow(SomeEatSud.getRow()).getCell(SomeEatSud.getCol()));
     int X_NumberEatResSud=(int) cellValue.getNumberValue();
     cellValue=evaluator.evaluate(Sheet2.getRow(SomeDrtoirSud.getRow()).getCell(SomeDrtoirSud.getCol()));
     int Y_NumberDrtResSud=(int) cellValue.getNumberValue();
     
        NumberEatResSud=new CellReference("P20");         
        NumberDrtResSud=new CellReference("P21");   
        Sheet1.getRow(NumberEatResSud.getRow()).getCell(NumberEatResSud.getCol()).setCellValue(X_NumberEatResSud);
        Sheet1.getRow(NumberDrtResSud.getRow()).getCell(NumberDrtResSud.getCol()).setCellValue(Y_NumberDrtResSud);
   }
 public void GetNbrCompensationSudForOneMission(int NumberOrdMiss){
     
     //JOptionPane.showMessageDialog(null, "I am in GetNbrCompensationSudForOneMission Remplir Info class");
     FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
     CellValue cellValue;
     cellValue=evaluator.evaluate(Sheet2.getRow(SomeEatSud.getRow()).getCell(SomeEatSud.getCol()));
     int X_NumberEatResSud=(int) cellValue.getNumberValue();
     cellValue=evaluator.evaluate(Sheet2.getRow(SomeDrtoirSud.getRow()).getCell(SomeDrtoirSud.getCol()));
     int Y_NumberDrtResSud=(int) cellValue.getNumberValue();
     if (NumberOrdMiss==1) {
         //JOptionPane.showMessageDialog(null, "We are Input In if (NumberOrdMiss==1) { Zone Sud");
          NumberEatResSud=new CellReference("P18");    //      
        NumberDrtResSud=new CellReference("P19");   
        Sheet1.getRow(NumberEatResSud.getRow()).getCell(NumberEatResSud.getCol()).setCellValue(X_NumberEatResSud);
        Sheet1.getRow(NumberDrtResSud.getRow()).getCell(NumberDrtResSud.getCol()).setCellValue(Y_NumberDrtResSud);
         
     }else{
     NumberEatResSud=new CellReference("P20");         
        NumberDrtResSud=new CellReference("P21");   
        Sheet1.getRow(NumberEatResSud.getRow()).getCell(NumberEatResSud.getCol()).setCellValue(X_NumberEatResSud);
        Sheet1.getRow(NumberDrtResSud.getRow()).getCell(NumberDrtResSud.getCol()).setCellValue(Y_NumberDrtResSud);
     }
     
        
   }

 
 
 
 public double GetPriceEat(String Wilaya,int Orientaion){
    int price=0;
    String TabWilaya[]={"ولاية أدرار", "ولاية الشلف" ,"ولاية الأغواط" ,"ولاية أم البواقي" ,"ولاية باتنة" ,"ولاية بجاية" ,"ولاية بسكرة" ,"ولاية بشار" ,"ولاية البويرة" 
            ,"ولاية تمنراست" ,"ولاية تبسة" ,
"ولاية تلمسان","ولاية تيارت" ,"ولاية تيزي وزو" ,"ولاية الجزائر" ,"ولاية الجلفة" ,"ولاية جيجل" ,"ولاية سطيف" ,"ولاية سعيدة" ,"ولاية سكيكدة" ,"ولاية سيدي بلعباس" ,
"ولاية عنابة" ,"ولاية قالمة","ولاية قسنطينة" ,"ولاية المدية" ,"ولاية مستغانم" ,"ولاية المسيلة" ,"ولاية معسكر" ,"ولاية ورقلة","ولاية وهران","ولاية البيض","ولاية إليزي",
"ولاية برج بوعريريج","ولاية بومرداس","ولاية الطارف","ولاية تندوف","ولاية المدية","ولاية مستغانم","ولاية المسيلة",
"ولاية معسكر","ولاية ورقلة","ولاية وهران","ولاية البيض","ولاية إليزي","ولاية برج بوعريريج","ولاية بومرداس","ولاية الطارف","ولاية تندوف","ولاية تيسمسيلت","ولاية الوادي",
"ولاية خنشلة","ولاية سوق أهراس","ولاية تيبازة","ولاية ميلة","ولاية عين الدفلى","ولاية النعامة","ولاية عين تموشنت","ولاية غرداية"," ولاية غليزان"
     };
  
        
        
    return 0.1;
    }
    
    public double GetPriceDrt(){
    
    return 0.1;
    }
    
    
    public void RemplirePriceOrdMissionNord(double prix_Repat,double prix_Decocher,int choix_Orientation){
    
         prixCompEatNord=new CellReference("O18");   
         
        prixCompDrtNord=new CellReference("O19");
        
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
        CellValue cellValue;
        cellValue = evaluator.evaluate(Sheet2.getRow(SomeEatNord.getRow()).getCell(SomeEatNord.getCol()));
        int X_NumberEatResNrd =(int) cellValue.getNumberValue();
        cellValue=evaluator.evaluate(Sheet2.getRow(SomeDrtoirNord.getRow()).getCell(SomeDrtoirNord.getCol()));       
        int Y_NumberDrtResNrd=(int) cellValue.getNumberValue();
         
        if (X_NumberEatResNrd >0) {
            
             Sheet1.getRow(prixCompEatNord.getRow()).getCell(prixCompEatNord.getCol()).setCellValue(prix_Repat);    
        }
         if (Y_NumberDrtResNrd>0) {
            Sheet1.getRow(prixCompDrtNord.getRow()).getCell(prixCompDrtNord.getCol()).setCellValue(prix_Decocher);
        }
    }
    
    public void RemplirePriceOrdMissionSud(double prix_Repat,double prix_Decocher,int choix_Orientation){
    
        prixCompEatSud=new CellReference("O20");         
        prixCompDrtSud=new CellReference("O21");
        
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
        CellValue cellValue;
        
         cellValue=evaluator.evaluate(Sheet2.getRow(SomeEatSud.getRow()).getCell(SomeEatSud.getCol()));
     
     int X_NumberEatResSud=(int) cellValue.getNumberValue();
     
     
     
     
     
     cellValue=evaluator.evaluate(Sheet2.getRow(SomeDrtoirSud.getRow()).getCell(SomeDrtoirSud.getCol()));
     
     int Y_NumberDrtResSud=(int) cellValue.getNumberValue();   
        
        if (X_NumberEatResSud>0) {
            Sheet1.getRow(prixCompEatSud.getRow()).getCell(prixCompEatSud.getCol()).setCellValue(prix_Repat);
        }
        if (Y_NumberDrtResSud>0) {
            
            Sheet1.getRow(prixCompDrtSud.getRow()).getCell(prixCompDrtSud.getCol()).setCellValue(prix_Decocher);
        }
    }
    
    //public int[] Get
    /***************************************************/
    public int GetNumberRepatNord(){
     
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
        CellValue cellValue;
        cellValue = evaluator.evaluate(Sheet1.getRow(NumberEatResNrd.getRow()).getCell(NumberEatResNrd.getCol()));
        int X_NumberEatResNrd =(int) cellValue.getNumberValue();
        
        return X_NumberEatResNrd;
    }
    /***************************************************/
     public int GetNumberDecocherNord(){
         FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();   
        CellValue cellValue;
          cellValue=evaluator.evaluate(Sheet1.getRow(NumberDrtResNrd.getRow()).getCell(NumberDrtResNrd.getCol()));       
        int Y_NumberDrtResNrd=(int) cellValue.getNumberValue();
        return Y_NumberDrtResNrd;
    }
     /***************************************************/
    public int GetNumberRepatSud(){
     FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();   
        CellValue cellValue;
        System.out.println("gestion_ord_mission.Remplir_Info.GetNumberRepatSud()");
          cellValue=evaluator.evaluate(Sheet1.getRow(NumberEatResSud.getRow()).getCell(NumberEatResSud.getCol()));
          System.out.println("gestion_ord_mission.Remplir_Info.GetNumberRepatSud() After Cell Value ");
     int X_NumberEatResSud=(int) cellValue.getNumberValue();
      System.out.println("gestion_ord_mission.Remplir_Info.GetNumberRepatSud() After X_NumberEatResSud: "+X_NumberEatResSud);
        return X_NumberEatResSud;
    }
    
      public int GetNumberRepatSudForReduction(){
 FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
        CellValue cellValue;
        cellValue = evaluator.evaluate(Sheet1.getRow(NumberEatResNrd.getRow()).getCell(NumberEatResNrd.getCol()));
        int X_NumberEatResNrd =(int) cellValue.getNumberValue();
        //JOptionPane.showMessageDialog(null, "Number Repat Geted From GetNumberRepatSudForReduction"+X_NumberEatResNrd);
        return X_NumberEatResNrd;
    }
    /***************************************************/
    
    public int GetNumberDecocherSud(){
    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();   
    CellValue cellValue;
    cellValue=evaluator.evaluate(Sheet1.getRow(NumberDrtResSud.getRow()).getCell(NumberDrtResSud.getCol()));
     
     int Y_NumberDrtResSud=(int) cellValue.getNumberValue();
        return Y_NumberDrtResSud;
    }
      public int GetNumberDecocherSudForReduction(){
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();   
        CellValue cellValue;
          cellValue=evaluator.evaluate(Sheet1.getRow(NumberDrtResNrd.getRow()).getCell(NumberDrtResNrd.getCol()));       
        int Y_NumberDrtResNrd=(int) cellValue.getNumberValue();
        return Y_NumberDrtResNrd;
    }
    /*****************************************************/
    
    public void RemplirePriceOrdMissionNordEat(double prix_Repatnrd){
    
        prixCompEatNord=new CellReference("O18");   
         FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
        CellValue cellValue;
        
        cellValue = evaluator.evaluate(Sheet2.getRow(SomeEatNord.getRow()).getCell(SomeEatNord.getCol()));
        int X_NumberEatResNrd =(int) cellValue.getNumberValue();
         if (X_NumberEatResNrd >0) {
            
             Sheet1.getRow(prixCompEatNord.getRow()).getCell(prixCompEatNord.getCol()).setCellValue(prix_Repatnrd);    
        }
    }
    public void RemplirePriceOrdMissionNordEatForReduction(double prix_Repatnrd){
    
        prixCompEatNord=new CellReference("O18");   
         FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
        CellValue cellValue;
        //sd
        cellValue = evaluator.evaluate(Sheet1.getRow(NumberEatResNrd.getRow()).getCell(NumberEatResNrd.getCol()));
        int X_NumberEatResNrd =(int) cellValue.getNumberValue();
         if (X_NumberEatResNrd >0) {
            
             Sheet1.getRow(prixCompEatNord.getRow()).getCell(prixCompEatNord.getCol()).setCellValue(prix_Repatnrd);    
        }
    }
    
///////////////////////****************************************************/////////////////    
    public void RemplirePriceOrdMissionNordDecocher(double prix_Decoche){
    prixCompDrtNord=new CellReference("O19");
     FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
     CellValue cellValue;
     cellValue=evaluator.evaluate(Sheet2.getRow(SomeDrtoirNord.getRow()).getCell(SomeDrtoirNord.getCol()));       
        int Y_NumberDrtResNrd=(int) cellValue.getNumberValue();
        if (Y_NumberDrtResNrd>0) {
            Sheet1.getRow(prixCompDrtNord.getRow()).getCell(prixCompDrtNord.getCol()).setCellValue(prix_Decoche);
        }
    }
     public void RemplirePriceOrdMissionNordDecocherForReduction(double prix_Decoche){
    prixCompDrtNord=new CellReference("O19");
     FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
     CellValue cellValue;
     
     cellValue=evaluator.evaluate(Sheet1.getRow(NumberDrtResNrd.getRow()).getCell(NumberDrtResNrd.getCol()));       
        int Y_NumberDrtResNrd=(int) cellValue.getNumberValue();
        if (Y_NumberDrtResNrd>0) {
            Sheet1.getRow(prixCompDrtNord.getRow()).getCell(prixCompDrtNord.getCol()).setCellValue(prix_Decoche);
        }
    }
    /**************************************************************************************/
     public void RemplirePriceOrdMissionSudEat(double prix_Repat){
    
           prixCompEatSud=new CellReference("O20");     
       FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
        CellValue cellValue;
        
         cellValue=evaluator.evaluate(Sheet2.getRow(SomeEatSud.getRow()).getCell(SomeEatSud.getCol()));
     
     int X_NumberEatResSud=(int) cellValue.getNumberValue();
      if (X_NumberEatResSud>0) {
            Sheet1.getRow(prixCompEatSud.getRow()).getCell(prixCompEatSud.getCol()).setCellValue(prix_Repat);
        }
     }
        public void RemplirePriceOrdMissionSudEatForOneMission(double prix_Repat){
    
           prixCompEatSud=new CellReference("O18");     
       FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
        CellValue cellValue;
        
         cellValue=evaluator.evaluate(Sheet2.getRow(SomeEatSud.getRow()).getCell(SomeEatSud.getCol()));
     
     int X_NumberEatResSud=(int) cellValue.getNumberValue();
      if (X_NumberEatResSud>0) {
            Sheet1.getRow(prixCompEatSud.getRow()).getCell(prixCompEatSud.getCol()).setCellValue(prix_Repat);
        }
     }
        
    public void RemplirePriceOrdMissionSudDecocher(double prix_Decocher){
    prixCompDrtSud=new CellReference("O21");
     FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
     CellValue cellValue;
     
     cellValue=evaluator.evaluate(Sheet2.getRow(SomeDrtoirSud.getRow()).getCell(SomeDrtoirSud.getCol()));
     
     int Y_NumberDrtResSud=(int) cellValue.getNumberValue(); 
      if (Y_NumberDrtResSud>0) {
            
            Sheet1.getRow(prixCompDrtSud.getRow()).getCell(prixCompDrtSud.getCol()).setCellValue(prix_Decocher);
        }
    }
    public void RemplirePriceOrdMissionSudDecocherForOneMission(double prix_Decocher){
    prixCompDrtSud=new CellReference("O19");
     FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
     CellValue cellValue;
     
     cellValue=evaluator.evaluate(Sheet2.getRow(SomeDrtoirSud.getRow()).getCell(SomeDrtoirSud.getCol()));
     
     int Y_NumberDrtResSud=(int) cellValue.getNumberValue(); 
      if (Y_NumberDrtResSud>0) {
            
            Sheet1.getRow(prixCompDrtSud.getRow()).getCell(prixCompDrtSud.getCol()).setCellValue(prix_Decocher);
        }
    }
    
    /*****************************************************/
    
    
    public void SumTransport_and_compensationTtl(){
    
        
        
         
     CellReference Transport_and_compensationTtl=new CellReference("N25");  
        
    
    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
        
     CellValue cellValue;
        
         cellValue=evaluator.evaluate(Sheet1.getRow(SumCompTransport.getRow()).getCell(SumCompTransport.getCol()));
     
         float SumTransComp=(float) cellValue.getNumberValue(); 
         
         
         cellValue=evaluator.evaluate(Sheet1.getRow(SumCompToujours.getRow()).getCell(SumCompToujours.getCol()));
     
         float SumCompTtl=(float) cellValue.getNumberValue(); 
         
         float SumTransComp_and_SumCompTtl=SumTransComp+SumCompTtl;
         
     Sheet1.getRow(Transport_and_compensationTtl.getRow()).getCell(Transport_and_compensationTtl.getCol()).setCellValue(SumTransComp_and_SumCompTtl);     
     /*CellReference NoPriseEncharge =new CellReference("U18");
     Sheet1.getRow(NoPriseEncharge.getRow()).getCell(NoPriseEncharge.getCol()).setCellValue("متكفل بها");     
     */
    }
    
    public static void main(String[] args) {
        
      
        XSSFSheet sheet=null;
        XSSFRow   Row=null;
        XSSFCell cell=null;
                
        Remplir_Info Ri=new Remplir_Info();
        Ri.FillTablePrice(null);
       // Ri.GetNum_Line();
        
       // JOptionPane.showMessageDialog(null, Ri.GetNum_Line());
          /*
        Ri.Remplir_Sheet1("قويدر يخلف", "22/10/2001", "مراقب", "مراقب", "14987", "بسكرة", "12664578", 
                "لبابتتن", "نعم", "NotBenificier", "هبببنن", new Date(), "بسكرة", "20135", 356, 456);*/
           
        //Ri.Remplir_Sheet2(CauseDeplasse, Depart, Distinataire, DateDepart, HeurDepart, DateArrive, HeurArrive, MoyenTrans, 0, 0, 0, 0, 0, 0, 0, 0, Remarque)
     
       /* try {
        //new File("D:\\App11.xlsx")    
             //wb=new XSSFWorkbook(new FileInputStream(file));
             System.out.println("The workook is created ...");
             new Remplir_Info().Inisialise_Wrk();
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error in Excel "+e.getMessage());
        }
        
        try {
          //sheet=workbook.getSheetAt(0);
            System.out.println("The Sheet is got ");
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in get Sheet  :"+e);
        }*/
    }
    
     
    public void FillTablePrice(javax.swing.JTable tab){
        
     // DefaultTableModel df=new DefaultTableModel();
      
      //df=(DefaultTableModel) tab.getModel();
      Connection_DB cnx=new Connection_DB();
      String QueryRepatSud="select  DISTINCT  valeur_Repat from \n" +
"Prix_Repat,Prix_Repat_Categorie,Categorie,Direction \n" +
"where  Direction.ID_Direct_Zone=2 \n" +
"AND (Num_Categorie<=10 and Num_Categorie>=1)\n" +
"AND Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n" +
"AND Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie \n" +
"AND Prix_Repat.ID_Prix_Repat=Prix_Repat_Categorie.ID_Prix_Repat";
      
      String QueryDecocherSud="select  DISTINCT  valeur_Repat from \n" +
"Prix_Repat,Prix_Repat_Categorie,Categorie,Direction \n" +
"where  Direction.ID_Direct_Zone=2 \n" +
"AND (Num_Categorie<=10 and Num_Categorie>=1)\n" +
"AND Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n" +
"AND Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie \n" +
"AND Prix_Repat.ID_Prix_Repat=Prix_Repat_Categorie.ID_Prix_Repat";
      
      double prix_repatLunch_sud_inf_10=0;
      double prix_repatDinner_sud_inf_10;
      double prix_Decocher_sud_inf_10;
      
      double prix_repatLunch_Nord_inf_10;
      double prix_repatDinner_Nord_inf_10;
      double prix_Decocher_Nord_inf_10;
      
      
      
      double prix_repat_sud_inf_10;
      Statement stm=null;
        ResultSet res=null;
        cnx.connectSqlServer();
      try {
            
          stm=cnx.getCnx().createStatement();
          res=stm.executeQuery(QueryRepatSud);
          
          if (res.next()) {
              prix_repatLunch_sud_inf_10=res.getDouble(1);
              
          }
          
          System.out.println("gestion_ord_mission.Remplir_Info.FillTablePrice()"+prix_repatLunch_sud_inf_10);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in sql "+e.getMessage());
        }
        try {
            res.close();
            stm.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The value is :"+e.getMessage());
        }
    }
    
    
    
}
