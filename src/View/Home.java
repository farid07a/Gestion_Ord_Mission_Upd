package View;

import Controller.Calcule_val;
import Controller.Connection_DB;
import Model.Employeur;
import Controller.HelperClass;
import Model.Info_Ord;
import Model.OrdMission;
import Controller.Remplir_Info;
import Model.Engagement;
import Model.Fonction;
import Model.Task_Mission;
import Model.Voiture;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class Home extends javax.swing.JFrame {

    ResultSet res;
    Connection cnx;
    Statement stm;
    private String Nom;
    ComfirmationUp_Sv confirmation;
    public int NumberORdMission = 0;
    private int ChoicePanel = 0;
    private int ValNord = 0, ValSud = 0;
    Fonction FunctionRemplissage = new Fonction(), Function_Obj = new Fonction();
    //Info_Ord Obj_Info=null;
    DefaultTableModel ModelTable = new DefaultTableModel();
    DefaultTableModel ModelTable2 = new DefaultTableModel();
    DefaultComboBoxModel<String> ModelCombox = new DefaultComboBoxModel<>();
    int Orientation = 0;
    Connection_DB Obj_Cnx = new Connection_DB();
    Remplir_Info Remplir_Info_obj;
    Info_Ord Employeur_Info;
    Voiture voiture = new Voiture();
    Employeur Person, PersonRemplissage;
    OrdMission ordission_obj;
    Task_Mission task_mission = new Task_Mission();
    Calcule_val cal_val_rep_drt;
    HelperClass helper = new HelperClass();
    Engagement Engagement_Opr = new Engagement();

    String TabWilaya[] = {"اختر الولاية", "ولاية أدرار", "ولاية الشلف", "ولاية الأغواط", "ولاية أم البواقي", "ولاية باتنة", "ولاية بجاية", "ولاية بسكرة", "ولاية بشار", "ولاية البليدة", "ولاية البويرة",
         "ولاية تمنراست", "ولاية تبسة",
        "ولاية تلمسان", "ولاية تيارت", "ولاية تيزي وزو", "ولاية الجزائر", "ولاية الجلفة", "ولاية جيجل", "ولاية سطيف", "ولاية سعيدة", "ولاية سكيكدة", "ولاية سيدي بلعباس",
        "ولاية عنابة", "ولاية قالمة", "ولاية قسنطينة", "ولاية المدية", "ولاية مستغانم", "ولاية المسيلة", "ولاية المدية", "ولاية مستغانم", "ولاية المسيلة",
        "ولاية معسكر", "ولاية ورقلة", "ولاية وهران", "ولاية البيض", "ولاية إليزي", "ولاية برج بوعريريج", "ولاية بومرداس", "ولاية الطارف", "ولاية تندوف", "ولاية تيسمسيلت", "ولاية الوادي",
        "ولاية خنشلة", "ولاية سوق أهراس", "ولاية تيبازة", "ولاية ميلة", "ولاية عين الدفلى", "ولاية النعامة", "ولاية عين تموشنت", "ولاية غرداية", " ولاية غليزان"
    };

    public Home() {

        initComponents();
        this.ordission_obj = new OrdMission();
        JTextField[] ListText = {Reg_Name, Reg_LastName, Reg_CategNum, Reg_NumSemt, Reg_CCP, Reg_Residence};
        HintTextField(ListText);
        confirmation = new ComfirmationUp_Sv(this, true);
        New_Mission.setVisible(false);
        // setTitle("Gestion Adminstration");
        GetImageIconApp();
        ButtonGroup Grp = new ButtonGroup();
        Grp.add(RdiPlan);
        Grp.add(rdiTrain);
        Grp.add(rdiCar);
        Grp.add(OtherCars);

        Grp = new ButtonGroup();
        Grp.add(RadioQurt);
        Grp.add(RadioFull);
        //BtnRdSup50
        Grp = new ButtonGroup();
        Grp.add(BtnRdSup50);
        Grp.add(BtnRdInf50);
        Grp = new ButtonGroup();
        Grp.add(sup50km_02);
        Grp.add(info50km_02);
        Grp = new ButtonGroup();
        Grp.add(btnRd100_02);
        Grp.add(btnRd25_02);
        panelCause.setVisible(false);
        PaneCarTravel.setVisible(false);
        RadioFull.setOpaque(true);
        RadioQurt.setOpaque(true);
        ((JLabel) ListDestainataire.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        ((JLabel) Car_Travel.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        ((JLabel) Jcom_CausTrsp.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        ((JLabel) Reg_CombGrade1.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) ListOrdMission.getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        ((JLabel) ListTab_DepPrsn.getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        ((JLabel) ListTabDps.getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);

//        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)ListOrdMission.getCellRenderer();   ListTab_DepPrsn//ListTabDps
//renderer.setHorizontalAlignment(JLabel.CENTER); 
        this.setLocationRelativeTo(this);
        Remplir_Info_obj = new Remplir_Info();          //1- create the object of rc=new Remplir_Info()
        cal_val_rep_drt = new Calcule_val(); // 2- create object of calcule repat and  decochee
        //Remplir_Info_obj.Inisialise_Wrk();               
        //Remplir_Info_obj.Inisialise_Wrk();
        //Remplir_Info_obj.Inisialise_Sheet2();
        //JOptionPane.showMessageDialog(null, Remplir_Info_obj.GetNum_Line());
        InisialiseJspinner();               //3- initialised of jspinner 
        Valid_Lab.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ModelTable.addColumn("الرقم الاستدلالي");
        ModelTable.addColumn("الدرجة");
        ModelTable.addColumn("الوظيفة");
        ModelTable.addColumn("رقم الحساب");
        ModelTable.addColumn("اللقب");
        ModelTable.addColumn("الاسم");
        ModelTable.addColumn("الرقم");
        //Remplir_Table();                   //4- remplire table les employeur 
        // jTable1.setModel(ModelTable);        
        RemlirModeCombox();

        CombMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"جانفي", "فيفري", "مـارس", "أفــريل", "مـــاي", "جــوان", "جويلية", "أوت", "سبتمبر", "أكتوبر", "نوفمبر", "ديسمبر"}));
        //InitialisedChamp();
        ListDestainataire.setModel(ModelCombox);
        remplireTableInf2();
        Num_OrderMission.setText("" + (ordission_obj.GetLastNumOrdMission() + 1));

        PnFdDpnsDtl_InsdPanDateHour.setVisible(false);
    }

    public void HintTextField(JTextField[] TabTextField) {

        for (JTextField TextField : TabTextField) {
            TextField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {

                    if (TextField.getText().equals("اسم الموظف")) {

                        TextField.setText("");
                    } else if (TextField.getText().equals("لقب الموظف")) {
                        TextField.setText("");
                    } else if (TextField.getText().equals("الدرجة")) {
                        TextField.setText("");
                    } else if (TextField.getText().equals("الوظيفة")) {
                        TextField.setText("");
                    } else if (TextField.getText().equals("الرقـــم الاستــدلالي")) {
                        TextField.setText("");
                    } else if (TextField.getText().equals("رقم الحساب الجاري")) {
                        TextField.setText("");
                    } else if (TextField.getText().equals("بسكرة")) {
                        TextField.setText("");
                    }
//الدرجة

                }

                @Override
                public void focusLost(FocusEvent e) {

                    if (TextField.equals(Reg_Name) && TextField.getText().equals("")) {

                        TextField.setText("اسم الموظف");
                    } else if (TextField.equals(Reg_LastName) && TextField.getText().equals("")) {
                        TextField.setText("لقب الموظف");
                    } else if (TextField.equals(Reg_CategNum) && TextField.getText().equals("")) {
                        TextField.setText("الدرجة");

                    } else if (TextField.equals(Reg_NumSemt) && TextField.getText().equals("")) {
                        TextField.setText("الرقـــم الاستــدلالي");

                    } else if (TextField.equals(Reg_CCP) && TextField.getText().equals("")) {
                        TextField.setText("رقم الحساب الجاري");
                    } else if (TextField.equals(Reg_Residence) && TextField.getText().equals("")) {
                        TextField.setText("بسكرة");
                    }

                }
            });
        }

    }

    public void Inistialised() {
        ModelTable2.addColumn("الدرجة");
        ModelTable2.addColumn("اللقب");
        ModelTable2.addColumn("الاسم");
        ModelTable2.addColumn("الرقم");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        Pan_Menu = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        GstEmpl = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        Pan_All_Pan_fr_Tab = new javax.swing.JPanel();
        Pan_TabEmp = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tab_InfoEmp = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TxtSearch = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        Depanse_Tab_Eng = new javax.swing.JTable();
        panBtn_TabDepns = new javax.swing.JPanel();
        jButton36 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        ListTabDps = new javax.swing.JList<>();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        ListTab_DepPrsn = new javax.swing.JList<>();
        jButton47 = new javax.swing.JButton();
        jLabel107 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        panServices = new javax.swing.JPanel();
        Pan_Frm_GetOrdEmpl = new javax.swing.JPanel();
        LabTrav = new javax.swing.JLabel();
        Mission_Name = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        Jcom_CausTrsp = new javax.swing.JComboBox<>();
        New_Mission = new javax.swing.JLabel();
        panelCause = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        ExitClsCause = new javax.swing.JLabel();
        LabTrav1 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        FullNam = new javax.swing.JTextField();
        Grad = new javax.swing.JTextField();
        DepuisMois = new javax.swing.JTextField();
        LabJob = new javax.swing.JLabel();
        Job = new javax.swing.JTextField();
        Num_Semantique = new javax.swing.JTextField();
        LabRes = new javax.swing.JLabel();
        Residence = new javax.swing.JTextField();
        LabCompte = new javax.swing.JLabel();
        Num_CCP = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        CombMonth = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        CombMonth1 = new javax.swing.JComboBox<>();
        jLabel72 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        PanCHoiceRdi = new javax.swing.JPanel();
        RadioFull = new javax.swing.JRadioButton();
        RadioQurt = new javax.swing.JRadioButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        SupPan = new javax.swing.JPanel();
        Car_Travel = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        RemarqueTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jDateChGo1 = new org.jdesktop.swingx.JXDatePicker();
        jDateChBack1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Heur_Go = new javax.swing.JSpinner();
        Heur_Back = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        ListDestainataire = new javax.swing.JComboBox<>();
        Depart = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        LabVoy = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        ChexNord0 = new javax.swing.JCheckBox();
        ChexSud1 = new javax.swing.JCheckBox();
        PaneCarTravel = new javax.swing.JPanel();
        TravelTotalTrans = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        txtNbrKlm = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        rdiTrain = new javax.swing.JRadioButton();
        RdiPlan = new javax.swing.JRadioButton();
        rdiCar = new javax.swing.JRadioButton();
        OtherCars = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        PlaneTravel = new javax.swing.JPanel();
        CarPrivate = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        steamboat = new javax.swing.JPanel();
        CarAdminstrator = new javax.swing.JPanel();
        btn_Imprimre = new javax.swing.JButton();
        Clear_Lab = new javax.swing.JLabel();
        Add_Mission_Lab = new javax.swing.JLabel();
        Valid_Lab = new javax.swing.JLabel();
        panInfr = new javax.swing.JPanel();
        Car_Travel1 = new javax.swing.JComboBox<>();
        jLabel94 = new javax.swing.JLabel();
        RemarqueTxt1 = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        jDateChGo3 = new org.jdesktop.swingx.JXDatePicker();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        Heur_Go1 = new javax.swing.JSpinner();
        Heur_Back1 = new javax.swing.JSpinner();
        jLabel99 = new javax.swing.JLabel();
        ListDestainataireCommune = new javax.swing.JComboBox<>();
        Depart1 = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        LabVoy1 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        ChexNord1 = new javax.swing.JCheckBox();
        ChexSud2 = new javax.swing.JCheckBox();
        PaneCarTravel1 = new javax.swing.JPanel();
        TravelTotalTrans1 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        txtNbrKlm1 = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        txtPrice1 = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        rdiTrain1 = new javax.swing.JRadioButton();
        RdiPlan1 = new javax.swing.JRadioButton();
        rdiCar1 = new javax.swing.JRadioButton();
        OtherCars1 = new javax.swing.JRadioButton();
        jButton4 = new javax.swing.JButton();
        PlaneTravel1 = new javax.swing.JPanel();
        CarPrivate1 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        steamboat1 = new javax.swing.JPanel();
        CarAdminstrator1 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jDateChGo4 = new org.jdesktop.swingx.JXDatePicker();
        jLabel108 = new javax.swing.JLabel();
        BtnRdSup50 = new javax.swing.JRadioButton();
        BtnRdInf50 = new javax.swing.JRadioButton();
        Pan_Employer = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        TabbedPane = new javax.swing.JTabbedPane();
        jPanel24 = new javax.swing.JPanel();
        NamCommune = new javax.swing.JTextField();
        BtnRdioinf50 = new javax.swing.JRadioButton();
        btnRadSup50 = new javax.swing.JRadioButton();
        BtnRdiNrd = new javax.swing.JRadioButton();
        BtnRdiSud = new javax.swing.JRadioButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        TablCommune = new javax.swing.JTable();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_Function = new javax.swing.JTable();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        Nam_Fnct = new javax.swing.JTextField();
        jButton30 = new javax.swing.JButton();
        Funct_NamFR = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jLabel82 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        Grade_Empl_txt = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        choice_catg = new java.awt.Choice();
        jScrollPane8 = new javax.swing.JScrollPane();
        tab_Grad = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tab_Rep_Dec_sup_50_sup_10 = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tab_Rep_Dec_sup_50_inf_10 = new javax.swing.JTable();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPanel39 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel133 = new javax.swing.JLabel();
        NamCommune1 = new javax.swing.JTextField();
        BtnRdioinf51 = new javax.swing.JRadioButton();
        btnRadSup51 = new javax.swing.JRadioButton();
        BtnRdiSud1 = new javax.swing.JRadioButton();
        BtnRdiNrd1 = new javax.swing.JRadioButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        Reg_Name = new javax.swing.JTextField();
        Reg_CCP = new javax.swing.JTextField();
        Reg_Residence = new javax.swing.JTextField();
        Reg_LastName = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        Reg_CategNum = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        Reg_NumSemt = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        ChoiceGrd = new java.awt.Choice();
        Function_Choice = new java.awt.Choice();
        checkFunct = new javax.swing.JCheckBox();
        Reg_CombGrade1 = new javax.swing.JComboBox<>();
        Pan_Acceul = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        choice2 = new java.awt.Choice();
        choice3 = new java.awt.Choice();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        Pan_EditOrd = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        Heur_Back3 = new javax.swing.JSpinner();
        jLabel120 = new javax.swing.JLabel();
        DateBack1 = new javax.swing.JFormattedTextField();
        jPanel32 = new javax.swing.JPanel();
        jLabel117 = new javax.swing.JLabel();
        TaskMission1 = new java.awt.Choice();
        jLabel119 = new javax.swing.JLabel();
        DateGo1 = new javax.swing.JFormattedTextField();
        jLabel118 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        Distinataire1 = new java.awt.Choice();
        MoyenTrsp_Miss1 = new java.awt.Choice();
        Heur_Go3 = new javax.swing.JSpinner();
        jPanel33 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        ResidentAdm1 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        FirstName1 = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        LastName1 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        Num_OrderMission = new javax.swing.JTextField();
        NumOrdLab = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        Table_OrdMissionEdit = new javax.swing.JTable();
        jTextField9 = new javax.swing.JTextField();
        PanOrdMission = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jTextField8 = new javax.swing.JTextField();
        panDetail_TabDepns = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        ListOrdMission = new javax.swing.JList<>();
        jLabel78 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        NbrRepLab = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        NbrDecLabPrc = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        NbrDecLab = new javax.swing.JLabel();
        NbrRepLabPrc = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel123 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        PrcOrMisLab = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel137 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        PrcTltLab = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        info50km_02 = new javax.swing.JRadioButton();
        sup50km_02 = new javax.swing.JRadioButton();
        jLabel126 = new javax.swing.JLabel();
        PnTabOrdMsstoDpns = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        Table_OrdMission1 = new javax.swing.JTable();
        NumItems = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        FinishOrdMission = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        BtnNewMissTbDep = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Table_OrdMission = new javax.swing.JTable();
        jPanel37 = new javax.swing.JPanel();
        PnFildsToDpnsDetaill = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        GradOrdMissionCns = new javax.swing.JTextField();
        FuncOrdMissCons = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        ResidentAdm = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        TaskMission = new java.awt.Choice();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        MoyenTrsp_Miss = new java.awt.Choice();
        jLabel91 = new javax.swing.JLabel();
        Heur_Go2 = new javax.swing.JSpinner();
        jLabel88 = new javax.swing.JLabel();
        Distinataire = new java.awt.Choice();
        DateGo = new javax.swing.JFormattedTextField();
        btnRd100_02 = new javax.swing.JRadioButton();
        btnRd25_02 = new javax.swing.JRadioButton();
        num_ord = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        PnFdDpnsDtl_InsdPanDateHour = new javax.swing.JPanel();
        Heur_Back2 = new javax.swing.JSpinner();
        DateBack = new javax.swing.JFormattedTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        OrdUpd_Lab = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        LastName = new javax.swing.JTextField();
        FirstName = new javax.swing.JTextField();
        btnSvMissTbDep = new javax.swing.JButton();
        BtnUpdTbDep = new javax.swing.JButton();
        BtnCancelTbDep = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        notification = new javax.swing.JPanel();
        jButton23 = new javax.swing.JButton();
        BarMenu = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        awsomIcon = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel6MouseDragged(evt);
            }
        });
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel6MouseReleased(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(160, 204, 204));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel3MouseReleased(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icono-menu.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, 40));

        Pan_Menu.setBackground(new java.awt.Color(51, 33, 51));
        Pan_Menu.setPreferredSize(new java.awt.Dimension(140, 590));
        Pan_Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icono-user.png"))); // NOI18N
        Pan_Menu.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 90, 80));

        jPanel1.setBackground(new java.awt.Color(51, 33, 51));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setBackground(new java.awt.Color(102, 45, 51));
        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("كتابة أمـــــــر بالمهمــة   ");
        jLabel39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel39.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel39MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel39MouseExited(evt);
            }
        });
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 0, 130, 40));

        jLabel55.setBackground(new java.awt.Color(51, 33, 51));
        jLabel55.setOpaque(true);
        jPanel1.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 6, 40));

        Pan_Menu.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 118, 140, 40));

        jPanel2.setBackground(new java.awt.Color(51, 33, 51));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setBackground(new java.awt.Color(51, 33, 51));
        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("جـدول مصاريف الـتـنـقـل");
        jLabel40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel40MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel40MouseExited(evt);
            }
        });
        jPanel2.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 0, 130, 40));

        jLabel62.setBackground(new java.awt.Color(51, 33, 51));
        jLabel62.setOpaque(true);
        jPanel2.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 6, 40));

        Pan_Menu.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 159, 140, 40));

        jPanel4.setBackground(new java.awt.Color(51, 33, 51));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GstEmpl.setBackground(new java.awt.Color(51, 33, 51));
        GstEmpl.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        GstEmpl.setForeground(new java.awt.Color(255, 255, 255));
        GstEmpl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GstEmpl.setText("ادارة المـــــــوظفيــــــــن ");
        GstEmpl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GstEmpl.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        GstEmpl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GstEmplMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GstEmplMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                GstEmplMouseExited(evt);
            }
        });
        jPanel4.add(GstEmpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 0, 130, 40));

        jLabel63.setBackground(new java.awt.Color(51, 33, 51));
        jLabel63.setOpaque(true);
        jPanel4.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 6, 40));

        Pan_Menu.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 140, 40));

        jLabel64.setBackground(new java.awt.Color(85, 150, 200));
        jLabel64.setText("jLabel64");
        jLabel64.setOpaque(true);
        Pan_Menu.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 60, 20));

        jPanel3.add(Pan_Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(-280, 10, -1, -1));

        Pan_All_Pan_fr_Tab.setBackground(new java.awt.Color(255, 255, 255));
        Pan_All_Pan_fr_Tab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Pan_All_Pan_fr_Tab.setForeground(new java.awt.Color(255, 255, 255));
        Pan_All_Pan_fr_Tab.setLayout(new java.awt.CardLayout());

        Pan_TabEmp.setBackground(new java.awt.Color(255, 255, 255));
        Pan_TabEmp.setLayout(null);

        jPanel15.setLayout(new java.awt.CardLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(null);

        Tab_InfoEmp.getTableHeader().setReorderingAllowed(false);
        Tab_InfoEmp.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 15));
        Tab_InfoEmp.getTableHeader().setFont(new java.awt.Font("Times New Roman", Font.BOLD, 16));
        Tab_InfoEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "الرقم", "الاســم", "اللقــب", "الرتبة"
            }
        ));
        Tab_InfoEmp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Tab_InfoEmp.setIntercellSpacing(new java.awt.Dimension(0, 0));
        Tab_InfoEmp.setRowHeight(24);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        Tab_InfoEmp.setDefaultRenderer(String.class, centerRenderer);
        Tab_InfoEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tab_InfoEmpMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Tab_InfoEmp);

        jPanel10.add(jScrollPane4);
        jScrollPane4.setBounds(0, 50, 488, 230);

        TxtSearch.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        TxtSearch.setForeground(new java.awt.Color(204, 204, 204));
        TxtSearch.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtSearch.setText("أدخل كلمة البحث");
        TxtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtSearchFocusGained(evt);
            }
        });
        TxtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtSearchActionPerformed(evt);
            }
        });
        TxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtSearchKeyTyped(evt);
            }
        });
        jPanel10.add(TxtSearch);
        TxtSearch.setBounds(330, 10, 150, 31);

        jPanel15.add(jPanel10, "card2");

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Depanse_Tab_Eng.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Depanse_Tab_Eng.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "التاريخ", "المبلغ", "المستفيد", "الرقم"
            }
        ));
        Depanse_Tab_Eng.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Depanse_Tab_Eng.setRowHeight(24);
        Depanse_Tab_Eng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Depanse_Tab_EngMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(Depanse_Tab_Eng);

        jPanel22.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 488, 213));

        panBtn_TabDepns.setBackground(new java.awt.Color(255, 255, 255));
        panBtn_TabDepns.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton36.setBackground(new java.awt.Color(255, 255, 255));
        jButton36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton36.setText("معاينة جدول المصاريف");
        jButton36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton36.setContentAreaFilled(false);
        jButton36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        panBtn_TabDepns.add(jButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 80, 40));

        jButton22.setBackground(new java.awt.Color(255, 255, 255));
        jButton22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new.jpg"))); // NOI18N
        jButton22.setToolTipText("عملية جديدة");
        jButton22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton22.setContentAreaFilled(false);
        jButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        panBtn_TabDepns.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 90, 40));

        jButton20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excelIcn.png"))); // NOI18N
        jButton20.setToolTipText("طباعة جدول المصاريف");
        jButton20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton20.setContentAreaFilled(false);
        jButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        panBtn_TabDepns.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 110, 40));

        jButton37.setBackground(new java.awt.Color(255, 255, 255));
        jButton37.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton37.setText("حفظ جدول المصاريف");
        jButton37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton37.setContentAreaFilled(false);
        jButton37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        panBtn_TabDepns.add(jButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        jButton42.setText("Mandat");
        jButton42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panBtn_TabDepns.add(jButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 60, 40));

        jPanel22.add(panBtn_TabDepns, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 429, 488, -1));

        jButton44.setText("Print ");
        jButton44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 392, 120, 31));

        jButton43.setText("Edit TabDepense");
        jButton43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 392, 120, 31));

        ListTabDps.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        ListTabDps.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane13.setViewportView(ListTabDps);

        jPanel22.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 40, 160));

        jButton45.setText("Engagement");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 392, 120, 31));

        jButton46.setBackground(new java.awt.Color(255, 255, 255));
        jButton46.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton46.setText("اضافة جدول");
        jButton46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton46.setContentAreaFilled(false);
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 80, 34));

        ListTab_DepPrsn.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "جدول المصاريف:", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        ListTab_DepPrsn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ListTab_DepPrsn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane14.setViewportView(ListTab_DepPrsn);

        jPanel22.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 120, 160));

        jButton47.setText("Update TabDep");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton47, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 100, 30));

        jLabel107.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel107.setText("N° Engagement:");
        jPanel22.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 90, 20));

        jLabel140.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel140.setText("00.00");
        jPanel22.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 284, 60, 20));

        jTextField5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("00");
        jPanel22.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 30, -1));

        jLabel141.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel141.setText("المجموع:");
        jPanel22.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 284, -1, 20));

        jButton48.setText("حفظ التعديلات");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 393, 100, 30));

        jButton49.setText("delete");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton49, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, -1, -1));

        jButton51.setText("NewTabDps");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton51, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, -1, -1));

        jPanel15.add(jPanel22, "card3");

        Pan_TabEmp.add(jPanel15);
        jPanel15.setBounds(0, 40, 490, 470);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jButton31.setBackground(new java.awt.Color(255, 255, 255));
        jButton31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton31.setText("اضافة مهمــة");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setBackground(new java.awt.Color(255, 255, 255));
        jButton32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton32.setText("اضافة موظف");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setBackground(new java.awt.Color(255, 255, 255));
        jButton33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton33.setText("اضافة الالتزام");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jButton33)
                .addGap(30, 30, 30)
                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addComponent(jButton32)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        Pan_TabEmp.add(jPanel14);
        jPanel14.setBounds(0, 508, 490, 140);

        Pan_All_Pan_fr_Tab.add(Pan_TabEmp, "card5");

        jPanel3.add(Pan_All_Pan_fr_Tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 490, 650));

        panServices.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panServices.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panServicesMouseDragged(evt);
            }
        });
        panServices.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panServicesMousePressed(evt);
            }
        });
        panServices.setLayout(new java.awt.CardLayout());

        Pan_Frm_GetOrdEmpl.setBackground(new java.awt.Color(255, 255, 255));
        Pan_Frm_GetOrdEmpl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabTrav.setBackground(new java.awt.Color(255, 255, 255));
        LabTrav.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        LabTrav.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabTrav.setText("أسباب التنقل : ");
        LabTrav.setOpaque(true);
        Pan_Frm_GetOrdEmpl.add(LabTrav, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, -1, 28));

        Mission_Name.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        Mission_Name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Mission_Name.setBorder(null);
        Mission_Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Mission_NameFocusGained(evt);
            }
        });
        Mission_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mission_NameActionPerformed(evt);
            }
        });
        Pan_Frm_GetOrdEmpl.add(Mission_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 0, 0));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("اسم المهمة :");
        jLabel16.setOpaque(true);
        Pan_Frm_GetOrdEmpl.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 10, 0));

        Jcom_CausTrsp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Jcom_CausTrsp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "مهمــــــــــــــــة اداريــــــــــة", "اشغــــــــال طوبغرافيــــــــــة", "مهمــــــــــــــات أخــــــــــرئ" }));
        Jcom_CausTrsp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Jcom_CausTrsp.setBackground(new java.awt.Color(204, 204, 204));
        Jcom_CausTrsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jcom_CausTrspActionPerformed(evt);
            }
        });
        Pan_Frm_GetOrdEmpl.add(Jcom_CausTrsp, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, 240, 28));

        New_Mission.setBackground(new java.awt.Color(255, 255, 255));
        New_Mission.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        New_Mission.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        New_Mission.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/createOrd.png"))); // NOI18N
        New_Mission.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        New_Mission.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        New_Mission.setOpaque(true);
        New_Mission.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        New_Mission.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                New_MissionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                New_MissionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                New_MissionMouseExited(evt);
            }
        });
        Pan_Frm_GetOrdEmpl.add(New_Mission, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 520, 50, 0));

        panelCause.setBackground(new java.awt.Color(255, 255, 255));

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus.png"))); // NOI18N
        jLabel45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ExitClsCause.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ExitClsCause.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExitClsCause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Cancel.png"))); // NOI18N
        ExitClsCause.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ExitClsCause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitClsCauseMouseClicked(evt);
            }
        });

        LabTrav1.setBackground(new java.awt.Color(255, 255, 255));
        LabTrav1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        LabTrav1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabTrav1.setText("سبب التنقل : ");
        LabTrav1.setOpaque(true);

        javax.swing.GroupLayout panelCauseLayout = new javax.swing.GroupLayout(panelCause);
        panelCause.setLayout(panelCauseLayout);
        panelCauseLayout.setHorizontalGroup(
            panelCauseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCauseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ExitClsCause)
                .addGap(6, 6, 6)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LabTrav1)
                .addContainerGap())
        );
        panelCauseLayout.setVerticalGroup(
            panelCauseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCauseLayout.createSequentialGroup()
                .addGroup(panelCauseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCauseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabTrav1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ExitClsCause, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Pan_Frm_GetOrdEmpl.add(panelCause, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 350, 35));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addrow.png"))); // NOI18N
        jLabel48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel48.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });
        Pan_Frm_GetOrdEmpl.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, -1, 28));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("السيد : ");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("الرقم الاستدلالي أو الراتب الأساسي: ");
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("الرتبة:");
        jLabel3.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("خلال شهر : ");
        jLabel4.setOpaque(true);

        FullNam.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        FullNam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FullNam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        FullNam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                FullNamFocusGained(evt);
            }
        });
        FullNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FullNamActionPerformed(evt);
            }
        });

        Grad.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        Grad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Grad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Grad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                GradFocusGained(evt);
            }
        });
        Grad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GradActionPerformed(evt);
            }
        });

        DepuisMois.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        DepuisMois.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        DepuisMois.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DepuisMois.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepuisMoisActionPerformed(evt);
            }
        });

        LabJob.setBackground(new java.awt.Color(255, 255, 255));
        LabJob.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        LabJob.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabJob.setText("الـوظيـفــــــة          :");

        Job.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        Job.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Job.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Job.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JobFocusGained(evt);
            }
        });
        Job.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JobActionPerformed(evt);
            }
        });

        Num_Semantique.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        Num_Semantique.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Num_Semantique.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Num_Semantique.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Num_SemantiqueFocusGained(evt);
            }
        });
        Num_Semantique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Num_SemantiqueActionPerformed(evt);
            }
        });

        LabRes.setBackground(new java.awt.Color(255, 255, 255));
        LabRes.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        LabRes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabRes.setText("الأقـــــامة الأداريــــــة                  : ");
        LabRes.setOpaque(true);

        Residence.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        Residence.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Residence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Residence.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ResidenceFocusGained(evt);
            }
        });
        Residence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResidenceActionPerformed(evt);
            }
        });

        LabCompte.setBackground(new java.awt.Color(255, 255, 255));
        LabCompte.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        LabCompte.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabCompte.setText("رقم الحساب الجاري : ");
        LabCompte.setOpaque(true);

        Num_CCP.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        Num_CCP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Num_CCP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Num_CCP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Num_CCPFocusGained(evt);
            }
        });
        Num_CCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Num_CCPActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 153, 255));

        jSeparator2.setForeground(new java.awt.Color(0, 153, 255));

        jSeparator3.setForeground(new java.awt.Color(0, 153, 255));

        jSeparator4.setForeground(new java.awt.Color(0, 153, 255));

        CombMonth.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CombMonth.setForeground(new java.awt.Color(102, 102, 102));
        CombMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "جانفي", "فيفري", "مـارس", "أفــريل", "مـــاي", "جــوان", "جويلية", "أوت", "سبتمبر", "أكتوبر", "نوفمبر", "ديسمبر" }));
        CombMonth.setBorder(null);
        CombMonth.setBackground(new java.awt.Color(204, 204, 204));
        CombMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CombMonth.setOpaque(true);
        CombMonth.setRenderer(new DefaultListCellRenderer() {
            public void paint(Graphics g) {
                setBackground(Color.white);
                //setForeground(Color.RED);
                super.paint(g);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("+");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });

        jLabel50.setBackground(new java.awt.Color(255, 255, 255));
        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("/");
        jLabel50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel50.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel50.setOpaque(true);
        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel50MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel50MouseEntered(evt);
            }
        });

        CombMonth1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CombMonth1.setForeground(new java.awt.Color(102, 102, 102));
        CombMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "جانفي", "فيفري", "مـارس", "أفــريل", "مـــاي", "جــوان", "جويلية", "أوت", "سبتمبر", "أكتوبر", "نوفمبر", "ديسمبر" }));
        CombMonth1.setBorder(null);
        CombMonth1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CombMonth1.setOpaque(true);
        CombMonth.setRenderer(new DefaultListCellRenderer() {
            public void paint(Graphics g) {
                setBackground(Color.white);
                //setForeground(Color.RED);
                super.paint(g);
            }
        });
        CombMonth1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CombMonth1ActionPerformed(evt);
            }
        });

        jLabel72.setBackground(new java.awt.Color(255, 255, 255));
        jLabel72.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("X");
        jLabel72.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel72.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel72.setOpaque(true);
        jLabel72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel72MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel72MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel72MouseExited(evt);
            }
        });

        jLabel71.setBackground(new java.awt.Color(255, 255, 255));
        jLabel71.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("-");
        jLabel71.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel71.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel71.setOpaque(true);
        jLabel71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel71MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(138, 138, 138)
                        .addComponent(CombMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(Job, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(LabJob, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(Grad, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(DepuisMois, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(130, 130, 130)
                            .addComponent(CombMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7)
                            .addComponent(FullNam, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(117, 117, 117)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(116, 116, 116)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addComponent(Num_Semantique, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(116, 116, 116)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addComponent(Num_CCP, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(LabCompte, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(Residence, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(15, 15, 15)
                            .addComponent(LabRes, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel72))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CombMonth1))
                                .addGap(1, 1, 1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Job, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabJob, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Grad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(DepuisMois, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FullNam, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(6, 6, 6))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(CombMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(41, 41, 41)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(4, 4, 4)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Num_Semantique, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(5, 5, 5)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(11, 11, 11)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Num_CCP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabCompte, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Residence, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabRes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(6, 6, 6)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Pan_Frm_GetOrdEmpl.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 190));

        PanCHoiceRdi.setBackground(new java.awt.Color(255, 255, 255));
        PanCHoiceRdi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanCHoiceRdi.setForeground(new java.awt.Color(0, 153, 153));

        RadioFull.setBackground(new java.awt.Color(255, 255, 255));
        RadioFull.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        RadioFull.setForeground(new java.awt.Color(0, 153, 153));
        RadioFull.setSelected(true);
        RadioFull.setText("100%");
        RadioFull.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        RadioFull.setBorderPainted(true);
        RadioFull.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RadioFull.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RadioFull.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        RadioFull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioFullActionPerformed(evt);
            }
        });

        RadioQurt.setBackground(new java.awt.Color(255, 255, 255));
        RadioQurt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        RadioQurt.setForeground(new java.awt.Color(0, 153, 153));
        RadioQurt.setText("25%");
        RadioQurt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RadioQurt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RadioQurt.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        RadioQurt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioQurtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanCHoiceRdiLayout = new javax.swing.GroupLayout(PanCHoiceRdi);
        PanCHoiceRdi.setLayout(PanCHoiceRdiLayout);
        PanCHoiceRdiLayout.setHorizontalGroup(
            PanCHoiceRdiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RadioFull, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(RadioQurt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanCHoiceRdiLayout.setVerticalGroup(
            PanCHoiceRdiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanCHoiceRdiLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(RadioFull, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(RadioQurt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Pan_Frm_GetOrdEmpl.add(PanCHoiceRdi, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 192, 110, 80));
        Pan_Frm_GetOrdEmpl.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 540, 20, 30));

        jLabel46.setBackground(new java.awt.Color(0, 153, 153));
        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/createOrd.png"))); // NOI18N
        jLabel46.setText("مهمة جديدة");
        jLabel46.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel46.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel46.setOpaque(true);
        jLabel46.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel46MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel46MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel46MouseExited(evt);
            }
        });
        Pan_Frm_GetOrdEmpl.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 90, 90));

        jLabel49.setBackground(new java.awt.Color(255, 255, 255));
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/indiqant_bleu.png"))); // NOI18N
        jLabel49.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel49.setOpaque(true);
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
        });
        Pan_Frm_GetOrdEmpl.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 16, 80));

        jLabel73.setBackground(new java.awt.Color(255, 255, 255));
        jLabel73.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 153, 153));
        Pan_Frm_GetOrdEmpl.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, 80, 30));

        jLabel74.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("00");
        Pan_Frm_GetOrdEmpl.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 260, 20, 20));

        jLabel75.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel75.setText("عدد المهمات :");
        Pan_Frm_GetOrdEmpl.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 260, 70, 20));

        jPanel28.setLayout(new java.awt.CardLayout());

        SupPan.setBackground(new java.awt.Color(255, 255, 255));
        SupPan.setLayout(null);

        Car_Travel.setBackground(new java.awt.Color(204, 204, 204));
        //Car_Travel.getEditor().getEditorComponent().setBackground(Color.YELLOW);
        Car_Travel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Car_Travel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "اختر وسيــلة التنقل", "سيـــارة اداريــة", "سيــارة خاصــة", "كل الوســائـــل" }));
        Car_Travel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Car_Travel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Car_TravelActionPerformed(evt);
            }
        });
        SupPan.add(Car_Travel);
        Car_Travel.setBounds(390, 45, 164, 28);

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("وسيلة التنقل   :");
        jLabel15.setOpaque(true);
        SupPan.add(jLabel15);
        jLabel15.setBounds(558, 45, 100, 28);

        RemarqueTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RemarqueTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                RemarqueTxtFocusGained(evt);
            }
        });
        SupPan.add(RemarqueTxt);
        RemarqueTxt.setBounds(480, 170, 178, 30);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("تاريخ العودة  :");
        jLabel11.setOpaque(true);
        SupPan.add(jLabel11);
        jLabel11.setBounds(690, 124, 73, 30);

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("تاريخ الذهاب :");
        jLabel13.setOpaque(true);
        SupPan.add(jLabel13);
        jLabel13.setBounds(680, 84, 80, 28);

        jDateChGo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChGo1ActionPerformed(evt);
            }
        });
        SupPan.add(jDateChGo1);
        jDateChGo1.setBounds(530, 84, 130, 30);
        SupPan.add(jDateChBack1);
        jDateChBack1.setBounds(530, 124, 130, 30);

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("التوقيت");
        jLabel14.setOpaque(true);
        SupPan.add(jLabel14);
        jLabel14.setBounds(470, 124, 40, 28);

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("التوقيت");
        jLabel12.setOpaque(true);
        SupPan.add(jLabel12);
        jLabel12.setBounds(470, 84, 40, 28);

        Heur_Go.setOpaque(false);
        SupPan.add(Heur_Go);
        Heur_Go.setBounds(390, 84, 70, 28);

        Heur_Back.setOpaque(false);
        SupPan.add(Heur_Back);
        Heur_Back.setBounds(390, 124, 70, 28);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("الملاحظات  :");
        jLabel19.setOpaque(true);
        SupPan.add(jLabel19);
        jLabel19.setBounds(690, 170, 60, 23);

        ListDestainataire.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        ListDestainataire.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "اختر الولاية ..." }));
        ListDestainataire.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListDestainataire.setBackground(new java.awt.Color(204, 204, 204));
        ListDestainataire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListDestainataireActionPerformed(evt);
            }
        });
        SupPan.add(ListDestainataire);
        ListDestainataire.setBounds(414, 11, 126, 28);

        Depart.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        Depart.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Depart.setText("بسكرة");
        Depart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Depart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartActionPerformed(evt);
            }
        });
        SupPan.add(Depart);
        Depart.setBounds(580, 11, 100, 28);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("-");
        SupPan.add(jLabel10);
        jLabel10.setBounds(550, 11, 30, 28);

        LabVoy.setBackground(new java.awt.Color(255, 255, 255));
        LabVoy.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        LabVoy.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabVoy.setText("بيان الرحلة   :");
        LabVoy.setOpaque(true);
        SupPan.add(LabVoy);
        LabVoy.setBounds(700, 11, 80, 28);

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("التعويضات    :");
        jLabel17.setOpaque(true);
        SupPan.add(jLabel17);
        jLabel17.setBounds(330, 10, 80, 28);

        ChexNord0.setBackground(new java.awt.Color(255, 255, 255));
        ChexNord0.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ChexNord0.setText("شمال");
        ChexNord0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ChexNord0.setEnabled(false);
        ChexNord0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChexNord0ActionPerformed(evt);
            }
        });
        SupPan.add(ChexNord0);
        ChexNord0.setBounds(250, 10, 49, 28);

        ChexSud1.setBackground(new java.awt.Color(255, 255, 255));
        ChexSud1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ChexSud1.setText("جنوب");
        ChexSud1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ChexSud1.setEnabled(false);
        ChexSud1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChexSud1ActionPerformed(evt);
            }
        });
        SupPan.add(ChexSud1);
        ChexSud1.setBounds(180, 10, 60, 28);

        PaneCarTravel.setBackground(new java.awt.Color(255, 255, 255));
        PaneCarTravel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PaneCarTravel.setLayout(new java.awt.CardLayout());

        TravelTotalTrans.setBackground(java.awt.Color.white);
        TravelTotalTrans.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("عدد الكيلومترات المقطوعة :");
        TravelTotalTrans.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, 25));
        TravelTotalTrans.add(txtNbrKlm, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 60, 25));

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel52.setText("km");
        TravelTotalTrans.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, 25));

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel53.setText("الثمـــن:");
        TravelTotalTrans.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, 25));
        TravelTotalTrans.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 61, 25));

        jLabel54.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel54.setText("DA");
        TravelTotalTrans.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 24, 25));

        rdiTrain.setBackground(new java.awt.Color(255, 255, 255));
        rdiTrain.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        rdiTrain.setText("القطار");
        rdiTrain.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rdiTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdiTrainActionPerformed(evt);
            }
        });
        TravelTotalTrans.add(rdiTrain, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        RdiPlan.setBackground(new java.awt.Color(255, 255, 255));
        RdiPlan.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        RdiPlan.setText("الطائرة");
        RdiPlan.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        RdiPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdiPlanActionPerformed(evt);
            }
        });
        TravelTotalTrans.add(RdiPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 10, 60, -1));

        rdiCar.setBackground(new java.awt.Color(255, 255, 255));
        rdiCar.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        rdiCar.setText("الحافلة");
        rdiCar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rdiCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdiCarActionPerformed(evt);
            }
        });
        TravelTotalTrans.add(rdiCar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        OtherCars.setBackground(new java.awt.Color(255, 255, 255));
        OtherCars.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        OtherCars.setSelected(true);
        OtherCars.setText("كل الوسائل");
        OtherCars.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        OtherCars.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OtherCars.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        OtherCars.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtherCarsActionPerformed(evt);
            }
        });
        TravelTotalTrans.add(OtherCars, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 10, 90, -1));

        jButton1.setText("حفظ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        TravelTotalTrans.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 100, 30));

        PaneCarTravel.add(TravelTotalTrans, "card3");

        PlaneTravel.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout PlaneTravelLayout = new javax.swing.GroupLayout(PlaneTravel);
        PlaneTravel.setLayout(PlaneTravelLayout);
        PlaneTravelLayout.setHorizontalGroup(
            PlaneTravelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );
        PlaneTravelLayout.setVerticalGroup(
            PlaneTravelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );

        PaneCarTravel.add(PlaneTravel, "card4");

        CarPrivate.setBackground(new java.awt.Color(255, 255, 255));

        jLabel44.setText("لوحة الترقيم");

        javax.swing.GroupLayout CarPrivateLayout = new javax.swing.GroupLayout(CarPrivate);
        CarPrivate.setLayout(CarPrivateLayout);
        CarPrivateLayout.setHorizontalGroup(
            CarPrivateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CarPrivateLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel44)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CarPrivateLayout.setVerticalGroup(
            CarPrivateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CarPrivateLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaneCarTravel.add(CarPrivate, "card4");

        steamboat.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout steamboatLayout = new javax.swing.GroupLayout(steamboat);
        steamboat.setLayout(steamboatLayout);
        steamboatLayout.setHorizontalGroup(
            steamboatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );
        steamboatLayout.setVerticalGroup(
            steamboatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );

        PaneCarTravel.add(steamboat, "card6");

        CarAdminstrator.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout CarAdminstratorLayout = new javax.swing.GroupLayout(CarAdminstrator);
        CarAdminstrator.setLayout(CarAdminstratorLayout);
        CarAdminstratorLayout.setHorizontalGroup(
            CarAdminstratorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );
        CarAdminstratorLayout.setVerticalGroup(
            CarAdminstratorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );

        PaneCarTravel.add(CarAdminstrator, "card7");

        SupPan.add(PaneCarTravel);
        PaneCarTravel.setBounds(21, 48, 345, 106);

        btn_Imprimre.setBackground(new java.awt.Color(255, 255, 255));
        btn_Imprimre.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_Imprimre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excelIcn.png"))); // NOI18N
        btn_Imprimre.setContentAreaFilled(false);
        btn_Imprimre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Imprimre.setFocusPainted(false);
        btn_Imprimre.setInheritsPopupMenu(true);
        btn_Imprimre.setOpaque(true);
        btn_Imprimre.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btn_Imprimre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImprimreActionPerformed(evt);
            }
        });
        SupPan.add(btn_Imprimre);
        btn_Imprimre.setBounds(10, 210, 50, 50);

        Clear_Lab.setBackground(new java.awt.Color(255, 255, 255));
        Clear_Lab.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Clear_Lab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Clear_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close-window.png"))); // NOI18N
        Clear_Lab.setText("الغاء");
        Clear_Lab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Clear_Lab.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Clear_Lab.setOpaque(true);
        Clear_Lab.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Clear_Lab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Clear_LabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Clear_LabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Clear_LabMouseExited(evt);
            }
        });
        SupPan.add(Clear_Lab);
        Clear_Lab.setBounds(310, 210, 50, 71);

        Add_Mission_Lab.setBackground(new java.awt.Color(255, 255, 255));
        Add_Mission_Lab.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Add_Mission_Lab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png"))); // NOI18N
        Add_Mission_Lab.setText("اضافة");
        Add_Mission_Lab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Add_Mission_Lab.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Add_Mission_Lab.setOpaque(true);
        Add_Mission_Lab.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Add_Mission_Lab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Add_Mission_LabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Add_Mission_LabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Add_Mission_LabMouseExited(evt);
            }
        });
        SupPan.add(Add_Mission_Lab);
        Add_Mission_Lab.setBounds(400, 210, 70, 70);

        Valid_Lab.setBackground(new java.awt.Color(255, 255, 255));
        Valid_Lab.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Valid_Lab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png"))); // NOI18N
        Valid_Lab.setText("حفظ");
        Valid_Lab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Valid_Lab.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Valid_Lab.setOpaque(true);
        Valid_Lab.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Valid_Lab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Valid_LabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Valid_LabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Valid_LabMouseExited(evt);
            }
        });
        SupPan.add(Valid_Lab);
        Valid_Lab.setBounds(610, 210, 50, 70);

        jPanel28.add(SupPan, "card2");

        Car_Travel1.setBackground(new java.awt.Color(204, 204, 204));
        Car_Travel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Car_Travel1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "اختر وسيــلة التنقل", "سيـــارة اداريــة", "سيــارة خاصــة", "كل الوســائـــل" }));
        Car_Travel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Car_Travel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Car_Travel1ActionPerformed(evt);
            }
        });

        jLabel94.setBackground(new java.awt.Color(255, 255, 255));
        jLabel94.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel94.setText("وسيلة التنقل   :");
        jLabel94.setOpaque(true);

        RemarqueTxt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RemarqueTxt1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                RemarqueTxt1FocusGained(evt);
            }
        });

        jLabel96.setBackground(new java.awt.Color(255, 255, 255));
        jLabel96.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel96.setText("تاريخ الذهاب :");
        jLabel96.setOpaque(true);

        jDateChGo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChGo3ActionPerformed(evt);
            }
        });

        jLabel97.setBackground(new java.awt.Color(255, 255, 255));
        jLabel97.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel97.setText("التوقيت");
        jLabel97.setOpaque(true);

        jLabel98.setBackground(new java.awt.Color(255, 255, 255));
        jLabel98.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel98.setText("التوقيت");
        jLabel98.setOpaque(true);

        Heur_Go1.setOpaque(false);

        Heur_Back1.setOpaque(false);

        jLabel99.setBackground(new java.awt.Color(255, 255, 255));
        jLabel99.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel99.setText("الملاحظات  :");
        jLabel99.setOpaque(true);

        ListDestainataireCommune.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        ListDestainataireCommune.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "اختر البلدية ..." }));
        ListDestainataireCommune.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListDestainataire.setBackground(new java.awt.Color(204, 204, 204));
        ListDestainataireCommune.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListDestainataireCommuneActionPerformed(evt);
            }
        });

        Depart1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        Depart1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Depart1.setText("بسكرة");
        Depart1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Depart1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Depart1ActionPerformed(evt);
            }
        });

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel100.setText("-");

        LabVoy1.setBackground(new java.awt.Color(255, 255, 255));
        LabVoy1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        LabVoy1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabVoy1.setText("بيان الرحلة   :");
        LabVoy1.setOpaque(true);

        jLabel101.setBackground(new java.awt.Color(255, 255, 255));
        jLabel101.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel101.setText("التعويضات    :");
        jLabel101.setOpaque(true);

        ChexNord1.setBackground(new java.awt.Color(255, 255, 255));
        ChexNord1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ChexNord1.setText("شمال");
        ChexNord1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ChexNord1.setEnabled(false);
        ChexNord1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChexNord1ActionPerformed(evt);
            }
        });

        ChexSud2.setBackground(new java.awt.Color(255, 255, 255));
        ChexSud2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ChexSud2.setText("جنوب");
        ChexSud2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ChexSud2.setEnabled(false);
        ChexSud2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChexSud2ActionPerformed(evt);
            }
        });

        PaneCarTravel1.setBackground(new java.awt.Color(255, 255, 255));
        PaneCarTravel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PaneCarTravel1.setLayout(new java.awt.CardLayout());

        TravelTotalTrans1.setBackground(java.awt.Color.white);
        TravelTotalTrans1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel102.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel102.setText("عدد الكيلومترات المقطوعة :");
        TravelTotalTrans1.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, 25));
        TravelTotalTrans1.add(txtNbrKlm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 60, 25));

        jLabel103.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel103.setText("km");
        TravelTotalTrans1.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, 25));

        jLabel104.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel104.setText("الثمـــن:");
        TravelTotalTrans1.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, 25));
        TravelTotalTrans1.add(txtPrice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 61, 25));

        jLabel105.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel105.setText("DA");
        TravelTotalTrans1.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 24, 25));

        rdiTrain1.setBackground(new java.awt.Color(255, 255, 255));
        rdiTrain1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        rdiTrain1.setText("القطار");
        rdiTrain1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rdiTrain1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdiTrain1ActionPerformed(evt);
            }
        });
        TravelTotalTrans1.add(rdiTrain1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        RdiPlan1.setBackground(new java.awt.Color(255, 255, 255));
        RdiPlan1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        RdiPlan1.setText("الطائرة");
        RdiPlan1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        RdiPlan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdiPlan1ActionPerformed(evt);
            }
        });
        TravelTotalTrans1.add(RdiPlan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 10, 60, -1));

        rdiCar1.setBackground(new java.awt.Color(255, 255, 255));
        rdiCar1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        rdiCar1.setText("الحافلة");
        rdiCar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rdiCar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdiCar1ActionPerformed(evt);
            }
        });
        TravelTotalTrans1.add(rdiCar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        OtherCars1.setBackground(new java.awt.Color(255, 255, 255));
        OtherCars1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        OtherCars1.setSelected(true);
        OtherCars1.setText("كل الوسائل");
        OtherCars1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        OtherCars1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OtherCars1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        OtherCars1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtherCars1ActionPerformed(evt);
            }
        });
        TravelTotalTrans1.add(OtherCars1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 10, 90, -1));

        jButton4.setText("حفظ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        TravelTotalTrans1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 100, 30));

        PaneCarTravel1.add(TravelTotalTrans1, "card3");

        PlaneTravel1.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout PlaneTravel1Layout = new javax.swing.GroupLayout(PlaneTravel1);
        PlaneTravel1.setLayout(PlaneTravel1Layout);
        PlaneTravel1Layout.setHorizontalGroup(
            PlaneTravel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );
        PlaneTravel1Layout.setVerticalGroup(
            PlaneTravel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );

        PaneCarTravel1.add(PlaneTravel1, "card4");

        CarPrivate1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel106.setText("لوحة الترقيم");

        javax.swing.GroupLayout CarPrivate1Layout = new javax.swing.GroupLayout(CarPrivate1);
        CarPrivate1.setLayout(CarPrivate1Layout);
        CarPrivate1Layout.setHorizontalGroup(
            CarPrivate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CarPrivate1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel106)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CarPrivate1Layout.setVerticalGroup(
            CarPrivate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CarPrivate1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaneCarTravel1.add(CarPrivate1, "card4");

        steamboat1.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout steamboat1Layout = new javax.swing.GroupLayout(steamboat1);
        steamboat1.setLayout(steamboat1Layout);
        steamboat1Layout.setHorizontalGroup(
            steamboat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );
        steamboat1Layout.setVerticalGroup(
            steamboat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );

        PaneCarTravel1.add(steamboat1, "card6");

        CarAdminstrator1.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout CarAdminstrator1Layout = new javax.swing.GroupLayout(CarAdminstrator1);
        CarAdminstrator1.setLayout(CarAdminstrator1Layout);
        CarAdminstrator1Layout.setHorizontalGroup(
            CarAdminstrator1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );
        CarAdminstrator1Layout.setVerticalGroup(
            CarAdminstrator1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );

        PaneCarTravel1.add(CarAdminstrator1, "card7");

        jButton8.setText("save");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("add");

        jButton10.setText("cancel");

        jButton11.setText("printer");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jDateChGo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChGo4ActionPerformed(evt);
            }
        });

        jLabel108.setBackground(new java.awt.Color(255, 255, 255));
        jLabel108.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel108.setText("الى غاية :");
        jLabel108.setOpaque(true);

        javax.swing.GroupLayout panInfrLayout = new javax.swing.GroupLayout(panInfr);
        panInfr.setLayout(panInfrLayout);
        panInfrLayout.setHorizontalGroup(
            panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panInfrLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(ChexSud2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ChexNord1)
                .addGap(43, 43, 43)
                .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(467, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panInfrLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panInfrLayout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addGap(93, 93, 93)
                        .addComponent(jButton10)
                        .addGap(79, 79, 79)
                        .addComponent(jButton9)
                        .addGap(38, 38, 38)
                        .addComponent(jButton8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panInfrLayout.createSequentialGroup()
                        .addComponent(Car_Travel1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panInfrLayout.createSequentialGroup()
                        .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Heur_Go1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChGo4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel98))
                        .addGap(18, 18, 18)
                        .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChGo3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Heur_Back1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panInfrLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panInfrLayout.createSequentialGroup()
                            .addGap(459, 459, 459)
                            .addComponent(RemarqueTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panInfrLayout.createSequentialGroup()
                            .addComponent(PaneCarTravel1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(ListDestainataireCommune, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(Depart1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(LabVoy1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panInfrLayout.setVerticalGroup(
            panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panInfrLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChexSud2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ChexNord1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Car_Travel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panInfrLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panInfrLayout.createSequentialGroup()
                                .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Heur_Go1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(62, 62, 62)
                                .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton8)
                                    .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton9)
                                        .addComponent(jButton10)
                                        .addComponent(jButton11))))
                            .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Heur_Back1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jDateChGo3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateChGo4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panInfrLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ListDestainataireCommune, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Depart1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabVoy1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(9, 9, 9)
                    .addComponent(PaneCarTravel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)
                    .addGroup(panInfrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(RemarqueTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel28.add(panInfr, "card3");

        Pan_Frm_GetOrdEmpl.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 790, 330));

        BtnRdSup50.setBackground(new java.awt.Color(255, 255, 255));
        BtnRdSup50.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        BtnRdSup50.setSelected(true);
        BtnRdSup50.setText("فوق 50 كلم");
        BtnRdSup50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnRdSup50.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        BtnRdSup50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRdSup50ActionPerformed(evt);
            }
        });
        Pan_Frm_GetOrdEmpl.add(BtnRdSup50, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 100, -1));

        BtnRdInf50.setBackground(new java.awt.Color(255, 255, 255));
        BtnRdInf50.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        BtnRdInf50.setText("أقل من 50 كلم");
        BtnRdInf50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnRdInf50.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        BtnRdInf50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRdInf50ActionPerformed(evt);
            }
        });
        Pan_Frm_GetOrdEmpl.add(BtnRdInf50, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 110, -1));

        panServices.add(Pan_Frm_GetOrdEmpl, "card2");

        Pan_Employer.setBackground(new java.awt.Color(255, 255, 255));
        Pan_Employer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(780, 500));
        jPanel7.setLayout(null);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel7.add(jLabel6);
        jLabel6.setBounds(30, 460, 50, 40);

        TabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        TabbedPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TabbedPane.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        TabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabbedPaneStateChanged(evt);
            }
        });
        TabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabbedPaneMouseClicked(evt);
            }
        });

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NamCommune.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel24.add(NamCommune, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 157, 29));

        BtnRdioinf50.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        BtnRdioinf50.setSelected(true);
        BtnRdioinf50.setText("اقل من 50 كلم");
        BtnRdioinf50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnRdioinf50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnRdioinf50.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel24.add(BtnRdioinf50, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, -1));

        btnRadSup50.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnRadSup50.setText("أكبر من 50 كلم");
        btnRadSup50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRadSup50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnRadSup50.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel24.add(btnRadSup50, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, -1));

        BtnRdiNrd.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        BtnRdiNrd.setSelected(true);
        BtnRdiNrd.setText("شمال ");
        BtnRdiNrd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnRdiNrd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnRdiNrd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel24.add(BtnRdiNrd, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 50, -1));

        BtnRdiSud.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        BtnRdiSud.setText("جنوب");
        BtnRdiSud.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnRdiSud.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnRdiSud.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel24.add(BtnRdiSud, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 50, -1));

        jButton6.setText("حفظ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel24.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 70, -1));

        jButton7.setText("حذف");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel24.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 70, -1));

        TablCommune.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "البلدية", "الاتجاه", "المسافة"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablCommune.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TablCommune.setRowHeight(22);
        TablCommune.setSelectionBackground(new java.awt.Color(102, 102, 255));
        TablCommune.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablCommuneMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(TablCommune);

        jPanel24.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 160));

        jLabel138.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel138.setText("اسم البلدية");
        jPanel24.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel139.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel139.setText("اسم البلدية");
        jPanel24.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, 20));

        jButton40.setText("الغاء");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jPanel24.add(jButton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 70, -1));

        jButton41.setText("تعديل");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        jPanel24.add(jButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 70, -1));

        TabbedPane.addTab("الاتجاهات", jPanel24);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tab_Function.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(tab_Function);

        jButton28.setText("Add");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setText("Delete ");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        Nam_Fnct.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Nam_Fnct.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButton30.setText("update");

        Funct_NamFR.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Funct_NamFR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel68.setText("الوظيف");

        jLabel69.setText("الوظيف");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Nam_Fnct, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(Funct_NamFR))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68)
                    .addComponent(jLabel69))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton30)
                .addGap(39, 39, 39)
                .addComponent(jButton29)
                .addGap(28, 28, 28)
                .addComponent(jButton28)
                .addGap(106, 106, 106))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Nam_Fnct, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Funct_NamFR, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton28)
                    .addComponent(jButton29)
                    .addComponent(jButton30))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        TabbedPane.addTab("الوظائف", jPanel5);

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "رقم الباب", "الباب"
            }
        ));
        jScrollPane11.setViewportView(jTable1);

        jPanel35.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 256, 147));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("رقـم الباب:");
        jPanel35.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(397, 0, 57, 25));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("نـوع البـاب:");
        jPanel35.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(397, 37, 57, 25));

        jTextField2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel35.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 59, -1));

        jTextField3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel35.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 39, 130, -1));

        jButton34.setText("Save");
        jPanel35.add(jButton34, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 99, 62, -1));

        jButton35.setText("Cancel");
        jPanel35.add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 99, -1, -1));

        jLabel82.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("دج");
        jPanel35.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 30, 20));

        jTextField4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel35.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 100, -1));

        jLabel95.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("المبلغ:");
        jPanel35.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 70, 50, 25));

        TabbedPane.addTab("الابواب", jPanel35);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
        );

        TabbedPane.addTab("المواد", jPanel36);

        jPanel7.add(TabbedPane);
        TabbedPane.setBounds(20, 240, 530, 170);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 111, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel21);
        jPanel21.setBounds(100, 450, 40, 111);

        jPanel38.setLayout(new java.awt.CardLayout());

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton12.setText("حفظ");
        jButton12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton12.setContentAreaFilled(false);
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton13.setText("cancel");
        jButton13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton13.setContentAreaFilled(false);
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        Grade_Empl_txt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Grade_Empl_txt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Grade_Empl_txt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Grade_Empl_txt.setEnabled(false);

        jLabel109.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel109.setText("الرتبــة :");
        jLabel109.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel110.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel110.setText("الصنـف :");
        jLabel110.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        choice_catg.setEnabled(false);

        tab_Grad.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tab_Grad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "الصنــف", "الرتبـــة"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tab_Grad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab_Grad.setRowHeight(22);
        tab_Grad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_GradMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tab_Grad);

        jButton14.setText("حذف");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("تعديل");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("جديد");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(choice_catg, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Grade_Empl_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel109)
                            .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton14)
                            .addComponent(jButton15)
                            .addComponent(jButton16)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Grade_Empl_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(choice_catg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel38.add(jPanel17, "card2");

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Tab_Rep_Dec_sup_50_sup_10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "جنوب", "شمال", "الوجبة"
            }
        ));
        Tab_Rep_Dec_sup_50_sup_10.setRowHeight(25);
        jScrollPane3.setViewportView(Tab_Rep_Dec_sup_50_sup_10);

        jLabel37.setBackground(new java.awt.Color(255, 255, 255));
        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("من الصنف 01 الي الصنف 10");

        jLabel83.setBackground(new java.awt.Color(255, 255, 255));
        jLabel83.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("من الصنف  15  الي الصنف 20 ");

        Tab_Rep_Dec_sup_50_inf_10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "جنوب", "شمال", "الوجبة"
            }
        ));
        Tab_Rep_Dec_sup_50_inf_10.setRowHeight(25);
        jScrollPane2.setViewportView(Tab_Rep_Dec_sup_50_inf_10);

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jRadioButton3.setText("اكبر من 50 كلم");
        jRadioButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jRadioButton3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jRadioButton4.setText("أقل من 50 كلم");
        jRadioButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jRadioButton4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                .addGap(19, 19, 19))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addComponent(jRadioButton4)
                .addGap(30, 30, 30)
                .addComponent(jRadioButton3)
                .addGap(184, 184, 184))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //TabbedPane.addTab("tab2", jPanel25);

        jPanel38.add(jPanel25, "card3");

        jPanel7.add(jPanel38);
        jPanel38.setBounds(22, 31, 530, 190);

        jLabel33.setBackground(new java.awt.Color(0, 153, 153));
        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("اضافة");
        jLabel33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel33.setOpaque(true);
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });

        jLabel36.setBackground(new java.awt.Color(0, 153, 153));
        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("حذف");
        jLabel36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel36.setOpaque(true);
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });

        jLabel35.setBackground(new java.awt.Color(0, 153, 153));
        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("تعديل");
        jLabel35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel35.setOpaque(true);
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        jLabel34.setBackground(new java.awt.Color(0, 153, 153));
        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("الغاء");
        jLabel34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel34.setOpaque(true);
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel39);
        jPanel39.setBounds(0, 600, 90, 40);

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel42);
        jPanel42.setBounds(560, 240, 220, 170);

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel133.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel133.setText("اسم البلدية");

        NamCommune1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        NamCommune1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        BtnRdioinf51.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        BtnRdioinf51.setSelected(true);
        BtnRdioinf51.setText("اقل من 50 كلم");
        BtnRdioinf51.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnRadSup51.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnRadSup51.setText("أكبر من 50 كلم");
        btnRadSup51.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        BtnRdiSud1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        BtnRdiSud1.setText("جنوب");
        BtnRdiSud1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        BtnRdiNrd1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        BtnRdiNrd1.setSelected(true);
        BtnRdiNrd1.setText("شمال ");
        BtnRdiNrd1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jButton38.setBackground(new java.awt.Color(255, 255, 255));
        jButton38.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton38.setText("حفظ");
        jButton38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton38.setContentAreaFilled(false);
        jButton38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setBackground(new java.awt.Color(255, 255, 255));
        jButton39.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton39.setText("الغاء");
        jButton39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton39.setContentAreaFilled(false);
        jButton39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel77.setText("vue");
        jLabel77.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnRdiSud1)
                            .addComponent(btnRadSup51))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnRdioinf51, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnRdiNrd1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(NamCommune1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel133))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel77)
                            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel40Layout.createSequentialGroup()
                                    .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(99, 99, 99))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NamCommune1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(btnRadSup51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnRdiSud1))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(BtnRdioinf51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnRdiNrd1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
        );

        jPanel7.add(jPanel40);
        jPanel40.setBounds(560, 30, 220, 190);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("اللقب ");
        jLabel9.setOpaque(true);
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 40, -1));

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("الاسم");
        jLabel20.setOpaque(true);
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 50, -1));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("الرتبة");
        jLabel21.setOpaque(true);
        jPanel8.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 50, 20));

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("رقم الحساب الجاري   ");
        jLabel24.setOpaque(true);
        jPanel8.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 110, -1));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("الاقامـة الاداريـــة ");
        jLabel25.setOpaque(true);
        jPanel8.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 90, 20));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIc.png"))); // NOI18N
        jPanel8.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, -1, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIc.png"))); // NOI18N
        jPanel8.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, -1));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIc.png"))); // NOI18N
        jPanel8.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIc.png"))); // NOI18N
        jPanel8.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, -1));

        Reg_Name.setBackground(new java.awt.Color(204, 200, 200));
        Reg_Name.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        Reg_Name.setForeground(new java.awt.Color(51, 51, 51));
        Reg_Name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Reg_Name.setText("اسم الموظف");
        Reg_Name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(Reg_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 100, 28));

        Reg_CCP.setBackground(new java.awt.Color(204, 200, 200));
        Reg_CCP.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        Reg_CCP.setForeground(new java.awt.Color(51, 51, 51));
        Reg_CCP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Reg_CCP.setText("رقم الحساب الجاري");
        Reg_CCP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(Reg_CCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 250, 30));

        Reg_Residence.setBackground(new java.awt.Color(204, 200, 200));
        Reg_Residence.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        Reg_Residence.setForeground(new java.awt.Color(51, 51, 51));
        Reg_Residence.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Reg_Residence.setText("بسكرة");
        Reg_Residence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(Reg_Residence, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 250, 30));

        Reg_LastName.setBackground(new java.awt.Color(204, 200, 200));
        Reg_LastName.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        Reg_LastName.setForeground(new java.awt.Color(51, 51, 51));
        Reg_LastName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Reg_LastName.setText("لقب الموظف");
        Reg_LastName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(Reg_LastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 130, 28));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("رقم الصنف");
        jLabel31.setOpaque(true);
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 60, 20));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIc.png"))); // NOI18N
        jPanel8.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, -1, -1));

        Reg_CategNum.setBackground(new java.awt.Color(204, 200, 200));
        Reg_CategNum.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        Reg_CategNum.setForeground(new java.awt.Color(51, 51, 51));
        Reg_CategNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Reg_CategNum.setText("الدرجة");
        Reg_CategNum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(Reg_CategNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 40, 28));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("الرقـــم الاستــدلالي ");
        jLabel22.setOpaque(true);
        jPanel8.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 100, -1));

        Reg_NumSemt.setBackground(new java.awt.Color(204, 200, 200));
        Reg_NumSemt.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        Reg_NumSemt.setForeground(new java.awt.Color(51, 51, 51));
        Reg_NumSemt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Reg_NumSemt.setText("الرقـــم الاستــدلالي");
        Reg_NumSemt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(Reg_NumSemt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 220, 30));

        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIc.png"))); // NOI18N
        jPanel8.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, -1, -1));

        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIc.png"))); // NOI18N
        jPanel8.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, -1, -1));

        ChoiceGrd.setBackground(new java.awt.Color(204, 200, 200));
        ChoiceGrd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ChoiceGrd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ChoiceGrd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChoiceGrdMouseClicked(evt);
            }
        });
        jPanel8.add(ChoiceGrd, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 260, 30));

        Function_Choice.setBackground(new java.awt.Color(204, 200, 200));
        Function_Choice.setEnabled(false);
        Function_Choice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Function_ChoiceMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Function_ChoiceMousePressed(evt);
            }
        });
        jPanel8.add(Function_Choice, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 160, 30));

        checkFunct.setBackground(new java.awt.Color(255, 255, 255));
        checkFunct.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        checkFunct.setText("اختر الوظيفة");
        checkFunct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkFunct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkFunct.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        checkFunct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkFunctActionPerformed(evt);
            }
        });
        jPanel8.add(checkFunct, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 70, 28));

        Reg_CombGrade1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        Reg_CombGrade1.setForeground(new java.awt.Color(51, 51, 51));
        Reg_CombGrade1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Reg_CombGrade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reg_CombGrade1ActionPerformed(evt);
            }
        });
        jPanel8.add(Reg_CombGrade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 220, 28));

        jPanel7.add(jPanel8);
        jPanel8.setBounds(220, 410, 560, 240);

        Pan_Employer.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 0, 780, 650));

        panServices.add(Pan_Employer, "card3");

        jRadioButton1.setText("jRadioButton1");

        jRadioButton2.setText("jRadioButton1");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(choice3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton1)
                .addGap(78, 78, 78)
                .addComponent(jRadioButton2)
                .addGap(48, 48, 48))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choice3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Pan_AcceulLayout = new javax.swing.GroupLayout(Pan_Acceul);
        Pan_Acceul.setLayout(Pan_AcceulLayout);
        Pan_AcceulLayout.setHorizontalGroup(
            Pan_AcceulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pan_AcceulLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(363, Short.MAX_VALUE))
        );
        Pan_AcceulLayout.setVerticalGroup(
            Pan_AcceulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pan_AcceulLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(408, Short.MAX_VALUE))
        );

        panServices.add(Pan_Acceul, "card4");

        Pan_EditOrd.setBackground(new java.awt.Color(255, 255, 255));
        Pan_EditOrd.setLayout(new java.awt.CardLayout());

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton25.setBackground(new java.awt.Color(255, 255, 255));
        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/printerbl.png"))); // NOI18N
        jButton25.setBorder(null);
        jButton25.setContentAreaFilled(false);
        jButton25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel31.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 78, 50));

        jButton24.setBackground(new java.awt.Color(255, 255, 255));
        jButton24.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton24.setText("اضـافة");
        jButton24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton24.setContentAreaFilled(false);
        jButton24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel31.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 100, 30));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel120.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel120.setText("تاريخ وساعــة الايــاب:");

        try {
            DateBack1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        DateBack1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel120)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(Heur_Back3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DateBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Heur_Back3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DateBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel31.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 374, 8, -1));

        jPanel32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel117.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel117.setText("وسيلة التنقل   :");
        jPanel32.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 58, -1, 32));

        TaskMission1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TaskMission1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel32.add(TaskMission1, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 12, 168, 30));

        jLabel119.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel119.setText("متوجــه الي    :");
        jPanel32.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 12, -1, 28));

        try {
            DateGo1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        DateGo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DateGo1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel32.add(DateGo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 60, 90, 30));

        jLabel118.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel118.setText("تاريخ وساعــة الذهــاب:");
        jPanel32.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 60, -1, 28));

        jLabel116.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel116.setText("سبب التنقــل:");
        jPanel32.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 12, 60, 28));

        Distinataire1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Distinataire1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel32.add(Distinataire1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 12, 107, 30));

        MoyenTrsp_Miss1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MoyenTrsp_Miss1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel32.add(MoyenTrsp_Miss1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 60, 107, 30));
        jPanel32.add(Heur_Go3, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 61, 68, 28));

        jPanel31.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 186, 540, 100));

        jPanel33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel113.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel113.setText("الوظيفــــة      :");
        jPanel33.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 53, 80, 28));

        jLabel115.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel115.setText("الاقـامة الاداريـــة:");
        jPanel33.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(567, 93, 80, 28));

        ResidentAdm1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        ResidentAdm1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel33.add(ResidentAdm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(411, 95, 156, 26));

        jTextField17.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel33.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 53, 160, 28));

        jLabel114.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel114.setText("اللقب     :");
        jPanel33.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 12, 60, 30));

        FirstName1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        FirstName1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel33.add(FirstName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 13, 106, 28));

        jLabel112.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel112.setText("الرتبة  :");
        jPanel33.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(601, 53, 40, 28));

        jLabel111.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel111.setText("الاسم   :");
        jPanel33.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(601, 13, 40, 28));

        LastName1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        LastName1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel33.add(LastName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 12, 160, 30));

        jTextField13.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel33.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(411, 53, 156, 28));

        jLabel67.setBackground(new java.awt.Color(255, 255, 255));
        jLabel67.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel67.setText("مهمة رقم :");
        jPanel33.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 6, 50, 30));

        Num_OrderMission.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        Num_OrderMission.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel33.add(Num_OrderMission, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 6, 72, 30));
        jPanel33.add(NumOrdLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 74, 0, -1));

        jPanel31.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 23, 650, 140));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("معلومات الموظــف");
        jPanel31.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 123, 23));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("معلومات المهمــــة");
        jPanel31.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 170, 130, 20));

        jButton26.setBackground(new java.awt.Color(255, 255, 255));
        jButton26.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton26.setText("تعديل");
        jButton26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton26.setContentAreaFilled(false);
        jButton26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel31.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, 100, 30));

        jButton27.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton27.setText("حذف");
        jButton27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton27.setContentAreaFilled(false);
        jButton27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel31.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 100, 30));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        Table_OrdMissionEdit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Table_OrdMissionEdit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "الاجراء", "التوجه الي", "تاريخ الذهاب", "اللقب", "الاسم", "رقم المهمة"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_OrdMissionEdit.setGridColor(new java.awt.Color(0, 0, 0));
        Table_OrdMissionEdit.setRowHeight(24);
        Table_OrdMissionEdit.setSelectionBackground(new java.awt.Color(102, 0, 102));
        Table_OrdMissionEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_OrdMissionEditMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(Table_OrdMissionEdit);

        jTextField9.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(204, 204, 204));
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField9.setText("البحـث");
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField9FocusGained(evt);
            }
        });
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel31.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 770, 230));

        Pan_EditOrd.add(jPanel31, "card2");

        panServices.add(Pan_EditOrd, "card5");

        PanOrdMission.setBackground(new java.awt.Color(255, 255, 255));
        PanOrdMission.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel27.setLayout(new java.awt.CardLayout());

        jPanel16.setLayout(new java.awt.CardLayout());

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setLayout(null);

        jTextField8.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(204, 204, 204));
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setText("البحـث");
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField8FocusGained(evt);
            }
        });
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });
        jPanel30.add(jTextField8);
        jTextField8.setBounds(650, 0, 130, 30);

        panDetail_TabDepns.setBackground(new java.awt.Color(255, 255, 255));
        panDetail_TabDepns.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panDetail_TabDepns.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ListOrdMission.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ListOrdMission.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane9.setViewportView(ListOrdMission);

        panDetail_TabDepns.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 52, 150));

        jLabel78.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("المهمــات ");
        panDetail_TabDepns.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 20));

        jLabel130.setBackground(new java.awt.Color(255, 255, 255));
        jLabel130.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel130.setText("النسبة");
        panDetail_TabDepns.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 40, 20));

        jLabel124.setBackground(new java.awt.Color(255, 255, 255));
        jLabel124.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(51, 153, 255));
        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel124.setText("100");
        panDetail_TabDepns.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 30, 20));

        jLabel76.setBackground(new java.awt.Color(255, 255, 255));
        jLabel76.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("الوجبات:");
        panDetail_TabDepns.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 40, -1));

        NbrRepLab.setBackground(new java.awt.Color(255, 255, 255));
        NbrRepLab.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        NbrRepLab.setForeground(new java.awt.Color(51, 153, 255));
        NbrRepLab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NbrRepLab.setText("00");
        panDetail_TabDepns.add(NbrRepLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 30, -1));

        jLabel129.setBackground(new java.awt.Color(255, 255, 255));
        jLabel129.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel129.setText("السعر:");
        panDetail_TabDepns.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 40, -1));
        panDetail_TabDepns.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 140, -1));

        NbrDecLabPrc.setBackground(new java.awt.Color(255, 255, 255));
        NbrDecLabPrc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        NbrDecLabPrc.setForeground(new java.awt.Color(255, 51, 0));
        NbrDecLabPrc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NbrDecLabPrc.setText("00");
        panDetail_TabDepns.add(NbrDecLabPrc, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 50, -1));

        jLabel128.setBackground(new java.awt.Color(255, 255, 255));
        jLabel128.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel128.setText("السعر:");
        panDetail_TabDepns.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 40, -1));

        jLabel121.setBackground(new java.awt.Color(255, 255, 255));
        jLabel121.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel121.setText("المبيت:");
        panDetail_TabDepns.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 40, -1));

        NbrDecLab.setBackground(new java.awt.Color(255, 255, 255));
        NbrDecLab.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        NbrDecLab.setForeground(new java.awt.Color(51, 153, 255));
        NbrDecLab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NbrDecLab.setText("00");
        panDetail_TabDepns.add(NbrDecLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 30, -1));

        NbrRepLabPrc.setBackground(new java.awt.Color(255, 255, 255));
        NbrRepLabPrc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        NbrRepLabPrc.setForeground(new java.awt.Color(255, 51, 0));
        NbrRepLabPrc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NbrRepLabPrc.setText("00");
        panDetail_TabDepns.add(NbrRepLabPrc, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 50, -1));
        panDetail_TabDepns.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 140, -1));

        jLabel123.setBackground(new java.awt.Color(255, 255, 255));
        jLabel123.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel123.setText("%");
        panDetail_TabDepns.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 20, 20));

        jLabel132.setBackground(new java.awt.Color(255, 255, 255));
        jLabel132.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel132.setText("مبلغ المهمة:");
        panDetail_TabDepns.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 70, -1));

        PrcOrMisLab.setBackground(new java.awt.Color(255, 255, 255));
        PrcOrMisLab.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        PrcOrMisLab.setForeground(new java.awt.Color(51, 153, 255));
        PrcOrMisLab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PrcOrMisLab.setText("00");
        panDetail_TabDepns.add(PrcOrMisLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 70, -1));
        panDetail_TabDepns.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 100, -1));

        jLabel137.setBackground(new java.awt.Color(255, 255, 255));
        jLabel137.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel137.setText("المستفيد:");
        panDetail_TabDepns.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 60, 20));

        jLabel136.setBackground(new java.awt.Color(255, 255, 255));
        jLabel136.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel136.setText("المبلغ الاجمالي:");
        panDetail_TabDepns.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 80, -1));

        PrcTltLab.setBackground(new java.awt.Color(255, 255, 255));
        PrcTltLab.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        PrcTltLab.setForeground(new java.awt.Color(255, 51, 0));
        PrcTltLab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PrcTltLab.setText("00");
        panDetail_TabDepns.add(PrcTltLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 80, 20));

        jButton19.setBackground(new java.awt.Color(255, 255, 255));
        jButton19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delOrdMiswh.png"))); // NOI18N
        jButton19.setText("حذف المهمـة");
        jButton19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton19.setContentAreaFilled(false);
        jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton19.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton19MouseExited(evt);
            }
        });
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        panDetail_TabDepns.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 70, 60));

        jButton21.setBackground(new java.awt.Color(255, 255, 255));
        jButton21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addMsbl.png"))); // NOI18N
        jButton21.setText("اضافة مهمة");
        jButton21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton21.setContentAreaFilled(false);
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton21.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        panDetail_TabDepns.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 70, 60));

        jButton50.setText("حذف المهمات");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });
        panDetail_TabDepns.add(jButton50, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 80, 60));

        jPanel30.add(panDetail_TabDepns);
        panDetail_TabDepns.setBounds(0, 40, 240, 280);

        jButton17.setText("Add");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel30.add(jButton17);
        jButton17.setBounds(280, 310, 0, 23);

        jButton18.setText("Calcule");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel30.add(jButton18);
        jButton18.setBounds(270, 310, 0, 23);

        info50km_02.setBackground(new java.awt.Color(255, 255, 255));
        info50km_02.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        info50km_02.setText("أقل من 50 كلم");
        info50km_02.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        info50km_02.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        info50km_02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                info50km_02ActionPerformed(evt);
            }
        });
        jPanel30.add(info50km_02);
        info50km_02.setBounds(310, 10, 81, 20);

        sup50km_02.setBackground(new java.awt.Color(255, 255, 255));
        sup50km_02.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        sup50km_02.setSelected(true);
        sup50km_02.setText("فوق  50 كلم");
        sup50km_02.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sup50km_02.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        sup50km_02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sup50km_02ActionPerformed(evt);
            }
        });
        jPanel30.add(sup50km_02);
        sup50km_02.setBounds(400, 10, 80, 20);

        jLabel126.setBackground(new java.awt.Color(255, 255, 255));
        jLabel126.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel30.add(jLabel126);
        jLabel126.setBounds(430, 240, 140, 20);

        PnTabOrdMsstoDpns.setBackground(new java.awt.Color(255, 255, 255));

        Table_OrdMission1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Table_OrdMission1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "الاجراء", "التوجه الي", "تاريخ الذهاب", "اللقب", "الاسم", "رقم المهمة"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_OrdMission1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Table_OrdMission1.setRowHeight(24);
        Table_OrdMission1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_OrdMission1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(Table_OrdMission1);

        NumItems.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        NumItems.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel134.setBackground(new java.awt.Color(255, 255, 255));
        jLabel134.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel134.setText("جدول المصاريف");
        jLabel134.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel134.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel134MouseClicked(evt);
            }
        });

        FinishOrdMission.setBackground(new java.awt.Color(255, 255, 255));
        FinishOrdMission.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        FinishOrdMission.setForeground(new java.awt.Color(255, 0, 0));
        FinishOrdMission.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FinishOrdMission.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/editred.jpg"))); // NOI18N
        FinishOrdMission.setToolTipText("انهاء المهمــة");
        FinishOrdMission.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FinishOrdMission.setOpaque(true);
        FinishOrdMission.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FinishOrdMissionMouseClicked(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delapp.jpg"))); // NOI18N
        jButton3.setToolTipText("حذف المهمة");
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        BtnNewMissTbDep.setBackground(new java.awt.Color(255, 255, 255));
        BtnNewMissTbDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/newpap.jpg"))); // NOI18N
        BtnNewMissTbDep.setToolTipText("مهمة جديدة");
        BtnNewMissTbDep.setContentAreaFilled(false);
        BtnNewMissTbDep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnNewMissTbDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNewMissTbDepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnTabOrdMsstoDpnsLayout = new javax.swing.GroupLayout(PnTabOrdMsstoDpns);
        PnTabOrdMsstoDpns.setLayout(PnTabOrdMsstoDpnsLayout);
        PnTabOrdMsstoDpnsLayout.setHorizontalGroup(
            PnTabOrdMsstoDpnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnTabOrdMsstoDpnsLayout.createSequentialGroup()
                .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(NumItems, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FinishOrdMission, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnNewMissTbDep, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );
        PnTabOrdMsstoDpnsLayout.setVerticalGroup(
            PnTabOrdMsstoDpnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnTabOrdMsstoDpnsLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PnTabOrdMsstoDpnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnNewMissTbDep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FinishOrdMission, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel134, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NumItems, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel30.add(PnTabOrdMsstoDpns);
        PnTabOrdMsstoDpns.setBounds(250, 40, 540, 280);

        jPanel16.add(jPanel30, "card2");

        jPanel27.add(jPanel16, "card4");

        Table_OrdMission.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "الاجراء", "السيارة", "نوع المهمة", "التوجه الي", "الاقامة الادارية", "ساعة الذهاب", "تاريخ الذهاب", "الرتبة", "اللقب", "الاسم", "رقم المهمة"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_OrdMission.setRowHeight(22);
        Table_OrdMission.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_OrdMissionMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(Table_OrdMission);
        if (Table_OrdMission.getColumnModel().getColumnCount() > 0) {
            Table_OrdMission.getColumnModel().getColumn(1).setHeaderValue("السيارة");
            Table_OrdMission.getColumnModel().getColumn(2).setHeaderValue("نوع المهمة");
            Table_OrdMission.getColumnModel().getColumn(4).setHeaderValue("الاقامة الادارية");
            Table_OrdMission.getColumnModel().getColumn(5).setHeaderValue("ساعة الذهاب");
            Table_OrdMission.getColumnModel().getColumn(7).setHeaderValue("الرتبة");
        }

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jPanel27.add(jPanel29, "card2");

        PanOrdMission.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 330));

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnFildsToDpnsDetaill.setBackground(new java.awt.Color(255, 255, 255));
        PnFildsToDpnsDetaill.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PnFildsToDpnsDetaill.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel84.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel84.setText("السيـد :");
        PnFildsToDpnsDetaill.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 40, 28));

        jLabel85.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel85.setText("الرتبة  :");
        PnFildsToDpnsDetaill.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 40, 28));

        GradOrdMissionCns.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        GradOrdMissionCns.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        GradOrdMissionCns.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        PnFildsToDpnsDetaill.add(GradOrdMissionCns, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 170, 28));

        FuncOrdMissCons.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        FuncOrdMissCons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FuncOrdMissCons.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        PnFildsToDpnsDetaill.add(FuncOrdMissCons, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 148, 28));

        jLabel86.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel86.setText("الوظيفة:");
        PnFildsToDpnsDetaill.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 40, 28));

        ResidentAdm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ResidentAdm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ResidentAdm.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        PnFildsToDpnsDetaill.add(ResidentAdm, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 60, 28));

        jLabel87.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel87.setText("الاقـامة :");
        PnFildsToDpnsDetaill.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 50, 28));

        TaskMission.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        PnFildsToDpnsDetaill.add(TaskMission, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 120, 30));

        jLabel89.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel89.setText("سبب التنقــل:");
        PnFildsToDpnsDetaill.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 60, 28));

        jLabel90.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel90.setText("وسيلة التنقل :");
        PnFildsToDpnsDetaill.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, 28));

        MoyenTrsp_Miss.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        PnFildsToDpnsDetaill.add(MoyenTrsp_Miss, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 110, 30));

        jLabel91.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel91.setText("تاريخ وساعــة الذهــاب:");
        PnFildsToDpnsDetaill.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, 28));
        PnFildsToDpnsDetaill.add(Heur_Go2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 70, 28));

        jLabel88.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel88.setText("متوجــه الي:");
        PnFildsToDpnsDetaill.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, -1, 28));

        Distinataire.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Distinataire.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        PnFildsToDpnsDetaill.add(Distinataire, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 110, 28));

        try {
            DateGo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        DateGo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DateGo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        PnFildsToDpnsDetaill.add(DateGo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 80, 28));

        btnRd100_02.setBackground(new java.awt.Color(255, 255, 255));
        btnRd100_02.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnRd100_02.setSelected(true);
        btnRd100_02.setText("100%");
        btnRd100_02.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRd100_02.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnRd100_02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRd100_02ActionPerformed(evt);
            }
        });
        PnFildsToDpnsDetaill.add(btnRd100_02, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        btnRd25_02.setBackground(new java.awt.Color(255, 255, 255));
        btnRd25_02.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnRd25_02.setText("25%");
        btnRd25_02.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRd25_02.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnRd25_02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRd25_02ActionPerformed(evt);
            }
        });
        PnFildsToDpnsDetaill.add(btnRd25_02, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 60, -1));

        num_ord.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        num_ord.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        num_ord.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        num_ord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num_ordActionPerformed(evt);
            }
        });
        PnFildsToDpnsDetaill.add(num_ord, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 50, 28));

        jLabel93.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel93.setText("رقم المهمـة:");
        PnFildsToDpnsDetaill.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 60, 28));

        PnFdDpnsDtl_InsdPanDateHour.setBackground(new java.awt.Color(255, 255, 255));
        PnFdDpnsDtl_InsdPanDateHour.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        PnFdDpnsDtl_InsdPanDateHour.add(Heur_Back2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 28));

        try {
            DateBack.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        DateBack.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DateBack.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        PnFdDpnsDtl_InsdPanDateHour.add(DateBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 80, 30));

        jLabel92.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel92.setText("تاريخ وساعــة الايــاب:");
        PnFdDpnsDtl_InsdPanDateHour.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 110, 28));

        PnFildsToDpnsDetaill.add(PnFdDpnsDtl_InsdPanDateHour, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 300, 30));

        jLabel81.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PnFildsToDpnsDetaill.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 20));
        PnFildsToDpnsDetaill.add(OrdUpd_Lab, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 40, 20));

        jLabel131.setBackground(new java.awt.Color(255, 255, 255));
        jLabel131.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PnFildsToDpnsDetaill.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 140, 20));

        jLabel135.setBackground(new java.awt.Color(255, 255, 255));
        jLabel135.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PnFildsToDpnsDetaill.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 100, 20));

        LastName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LastName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        LastName.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        PnFildsToDpnsDetaill.add(LastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 70, 28));

        FirstName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        FirstName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FirstName.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        PnFildsToDpnsDetaill.add(FirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 60, 28));

        jPanel37.add(PnFildsToDpnsDetaill, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 180));

        btnSvMissTbDep.setBackground(new java.awt.Color(255, 255, 255));
        btnSvMissTbDep.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnSvMissTbDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save_wh.png"))); // NOI18N
        btnSvMissTbDep.setText("حفظ");
        btnSvMissTbDep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSvMissTbDep.setContentAreaFilled(false);
        btnSvMissTbDep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSvMissTbDep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSvMissTbDep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSvMissTbDep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSvMissTbDepMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSvMissTbDepMouseExited(evt);
            }
        });
        btnSvMissTbDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSvMissTbDepActionPerformed(evt);
            }
        });
        jPanel37.add(btnSvMissTbDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 60, 60));

        BtnUpdTbDep.setBackground(new java.awt.Color(255, 255, 255));
        BtnUpdTbDep.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        BtnUpdTbDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/editfile.png"))); // NOI18N
        BtnUpdTbDep.setText("تعديل");
        BtnUpdTbDep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnUpdTbDep.setContentAreaFilled(false);
        BtnUpdTbDep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnUpdTbDep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnUpdTbDep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnUpdTbDep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnUpdTbDepMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnUpdTbDepMouseExited(evt);
            }
        });
        BtnUpdTbDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdTbDepActionPerformed(evt);
            }
        });
        jPanel37.add(BtnUpdTbDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 70, 60));

        BtnCancelTbDep.setBackground(new java.awt.Color(255, 255, 255));
        BtnCancelTbDep.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        BtnCancelTbDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/deletwh.png"))); // NOI18N
        BtnCancelTbDep.setText("الغاء");
        BtnCancelTbDep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnCancelTbDep.setContentAreaFilled(false);
        BtnCancelTbDep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCancelTbDep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnCancelTbDep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnCancelTbDep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnCancelTbDepMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnCancelTbDepMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BtnCancelTbDepMousePressed(evt);
            }
        });
        BtnCancelTbDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelTbDepActionPerformed(evt);
            }
        });
        jPanel37.add(BtnCancelTbDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 60, 60));

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel37.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        javax.swing.GroupLayout notificationLayout = new javax.swing.GroupLayout(notification);
        notification.setLayout(notificationLayout);
        notificationLayout.setHorizontalGroup(
            notificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        notificationLayout.setVerticalGroup(
            notificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel37.add(notification, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, 170, 10));

        jButton23.setText("jButton5");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel37.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        PanOrdMission.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 790, 310));

        panServices.add(PanOrdMission, "card6");

        jPanel3.add(panServices, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 790, 650));

        BarMenu.setBackground(new java.awt.Color(160, 204, 204));
        BarMenu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BarMenuMouseDragged(evt);
            }
        });
        BarMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BarMenuMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BarMenuMouseReleased(evt);
            }
        });

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Captions_Min.png"))); // NOI18N
        jLabel43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel43MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel43MouseExited(evt);
            }
        });

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Captions_Close.png"))); // NOI18N
        jLabel42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel42MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel42MouseExited(evt);
            }
        });

        javax.swing.GroupLayout BarMenuLayout = new javax.swing.GroupLayout(BarMenu);
        BarMenu.setLayout(BarMenuLayout);
        BarMenuLayout.setHorizontalGroup(
            BarMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BarMenuLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        BarMenuLayout.setVerticalGroup(
            BarMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarMenuLayout.createSequentialGroup()
                .addGroup(BarMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(BarMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 0, 80, 30));

        awsomIcon.setBackground(new java.awt.Color(255, 255, 255));
        awsomIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setBackground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setOpaque(true);
        jPanel11.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 0, 4, 36));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconsCalcTran.png"))); // NOI18N
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.setOpaque(true);
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel18MouseExited(evt);
            }
        });
        jPanel11.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 39, 36));

        awsomIcon.add(jPanel11);

        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel60.setBackground(new java.awt.Color(255, 255, 255));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setOpaque(true);
        jPanel20.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 0, 4, 36));

        jLabel61.setBackground(new java.awt.Color(255, 255, 255));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ConfigWht.png"))); // NOI18N
        jLabel61.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel61.setOpaque(true);
        jLabel61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel61MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel61MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel61MouseExited(evt);
            }
        });
        jPanel20.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 39, 36));

        awsomIcon.add(jPanel20);

        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel56.setBackground(new java.awt.Color(255, 255, 255));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setOpaque(true);
        jPanel18.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 0, 4, 36));

        jLabel57.setBackground(new java.awt.Color(204, 204, 204));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Ord");
        jLabel57.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel57.setOpaque(true);
        jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel57MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel57MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel57MouseExited(evt);
            }
        });
        jPanel18.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 39, 36));

        awsomIcon.add(jPanel18);

        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel58.setBackground(new java.awt.Color(255, 255, 255));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setOpaque(true);
        jPanel19.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 0, 4, 36));

        jLabel59.setBackground(new java.awt.Color(255, 255, 255));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel59.setOpaque(true);
        jLabel59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel59MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel59MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel59MouseExited(evt);
            }
        });
        jPanel19.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 39, 36));

        awsomIcon.add(jPanel19);

        jPanel3.add(awsomIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 50, 50, 650));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1330, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 701, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FullNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullNamActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_FullNamActionPerformed

    public void RemlirModeCombox() {

        for (String string : TabWilaya) {

            ModelCombox.addElement(string);
        }
    }
    private void GradActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GradActionPerformed


    }//GEN-LAST:event_GradActionPerformed

    private void JobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JobActionPerformed

    }//GEN-LAST:event_JobActionPerformed

    private void Num_SemantiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Num_SemantiqueActionPerformed

    }//GEN-LAST:event_Num_SemantiqueActionPerformed

    private void ResidenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResidenceActionPerformed

    }//GEN-LAST:event_ResidenceActionPerformed

    private void Num_CCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Num_CCPActionPerformed

    }//GEN-LAST:event_Num_CCPActionPerformed
    int ValInitialiseKlmAndPrix = 0;
    private void Car_TravelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Car_TravelActionPerformed
        String Car_Travel_Trs = (String) Car_Travel.getSelectedItem();
        if (Car_Travel_Trs != "اختر وسيـــــــلة التنقل" && Car_Travel_Trs != "سيـــــــــــارة اداريــــــة") {

            PaneCarTravel.setVisible(true);

        } else {
            PaneCarTravel.setVisible(false);

            switch (Car_Travel_Trs) {
                /*  case "سيـــــــــــارة اداريــــــة":
          //PaneCarTravel.setVisible(true);
             //ChoicePanelCars(PaneCarTravel, CarAdminstrator);
            break;*/
                case "القطــــــــــــــــــــــــــار":
                case "الحافلـــــــــــــــــــــــــة":
                case "الباخـــــــــــــــــــــــــرة":
                case "كل الوســــــــــــــائـــل":
                    // JOptionPane.showMessageDialog(null, "Special Care");
                    // PaneCarTravel.setVisible(true);
                    ChoicePanelCars(PaneCarTravel, TravelTotalTrans);

                    //  CarPrivate.setVisible(true);
                    break;
                case "سيـــــــــــارة خاصــــــة":
                    //PaneCarTravel.setVisible(true);
                    // ChoicePanelCars(PaneCarTravel, TrainTravel);
                    ChoicePanelCars(PaneCarTravel, CarPrivate);
                    break;
                /*  case "الحافلـــــــــــــــــــــــــة":
            //PaneCarTravel.setVisible(true);
             ChoicePanelCars(PaneCarTravel, BusTravel);
            break;*/

                //ChoicePanelCars(PaneCarTravel, steamboat);
                //PaneCarTravel.setVisible(true);
                /*  ChoicePanelCars(PaneCarTravel, TotalTransTravel);
            break;*/
            }
        }

    }//GEN-LAST:event_Car_TravelActionPerformed

    public void ChoicePanelCars(JPanel parentpan, JPanel child) {
        int NbrPanCare = parentpan.getComponentCount();

        //JOptionPane.showMessageDialog(null, "The Nbre Of Panel is in External:"+NbrPanCare);
        while ((NbrPanCare - 1) >= 0) {
            //  JOptionPane.showMessageDialog(null, "The Nbre Of Panel is in Internal :"+NbrPanCare);
            if (parentpan.getComponent(NbrPanCare - 1).equals(child)) {
                child.setVisible(true);
                //  JOptionPane.showMessageDialog(null ,"The Panel is Getted id :"+(NbrPanCare-1));
            } else {
                parentpan.getComponent(NbrPanCare - 1).setVisible(false);
                //JOptionPane.showMessageDialog(null ,"The Panel is Falsed  :"+(NbrPanCare-1));
            }
            NbrPanCare--;
        }

    }
    private void DepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DepartActionPerformed

    private void Mission_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mission_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mission_NameActionPerformed

    private void ChexSud1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChexSud1ActionPerformed
        //JOptionPane.showMessageDialog(null, "The check box is selected ");
        ChexNord0.setSelected(false);
        //ValSud=1;

    }//GEN-LAST:event_ChexSud1ActionPerformed

    private void ChexNord0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChexNord0ActionPerformed

        //JOptionPane.showMessageDialog(null, "The check box is selected ");
        ChexSud1.setSelected(false);

        //ValNord=1;

    }//GEN-LAST:event_ChexNord0ActionPerformed

    private void Jcom_CausTrspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jcom_CausTrspActionPerformed


    }//GEN-LAST:event_Jcom_CausTrspActionPerformed
    int ReductionValue = 1;

    private void Valid_LabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Valid_LabMouseClicked

        //jDateChGo1.
        //JOptionPane.showMessageDialog(null, "The Date Is "+jDateChGo1.getDate());
        if (valClickAdd == 2 || valClickAdd == 0) {
            if (ErrorConstroleSaisieOrdMiss()) {

                ok = new Ok1(this, true, "تأكــد من ادخــال المعلومـات");
                ok.setVisible(true);
            } else {
                if (ChexNord0.isSelected()) {
                    ValNord = 1;
                } else {
                    ValSud = 1;
                }
                //Date dt=new Date();
                // SimpleDateFormat smfrm=new SimpleDateFormat("dd/MM/YYYY");
                // String DateChGoString=smfrm.format(jDateChGo1.getDate());
                try {
                    Create_Employeur_Info();// create new Info_Ord(...) 
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Error in create Info_Ord " + ex.getMessage());
                }
            }
            CreateCoreAddMission();
            Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/AddFill.png")));

        } else {
            ok = new Ok1(this, true, "لا بمكنك ادخــال المعلومات قم باضــافة مهمــة");
            ok.setVisible(true);
        }


    }//GEN-LAST:event_Valid_LabMouseClicked
    SuccessAlert1 succ;

    public void SaveOneOrdMission() {
        if (NumberORdMission == 1) {
            //save information Of Missioner
        }
    }

    public void AddNewOrdMissionToCal() {

        if (NumberORdMission == 0) {  // No ordMission Created
            NumberORdMission = 1;     // add +1 to NumberORdMission
            //  Remplir_Info_obj.Inisialise_Wrk(); 
            // First Code 
            Remplir_Info_obj.Remplir_Sheet1(Employeur_Info.getFirstName() + " " + Employeur_Info.getLastName(), //create data  new info_ord(...) in sheet excel 
                    Employeur_Info.getDepuisMois(), Employeur_Info.getGrade(), Employeur_Info.getJob(),
                    Employeur_Info.getSemanticNumero(), Employeur_Info.getManagementResident(),
                    Employeur_Info.getCCPN_Num(), "", "", "", "", new Date(), "", "", 0, 0);

            if (ReservedCaseTravalChoice == 1) {
                CalculValueOfTravel();   // add data for zone of travel cars
            }
            Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName() + " " + Employeur_Info.getLastName());
            Remplir_Info_obj.Inisialise_Sheet2();
            enablePanelInformation(false, jPanel13, 0);

        }
        //Remplir_Info_obj.Inisialise_Wrk();
        // Remplir_Info_obj.Inisialise_Sheet2();
        Remplir_Info_obj.Insialise_ReferenceSh2();
        /* Remplir_Info_obj.InitialiseZoneTravelsCars();
        Remplir_Info_obj.CalculePrixMultNbrKm();*/
        Remplir_Info_obj.Remplir_Sheet2(Employeur_Info.getCauseTravel(), Employeur_Info.getDepart_Demarer(),
                Employeur_Info.getDestinataire(), Employeur_Info.getDateGo(), Employeur_Info.getHeur_Go(),
                Employeur_Info.getDateBack(), Employeur_Info.getHeur_Back(),
                Employeur_Info.getMoyenTrnsport(), 0, 0, 0, 0,
                Employeur_Info.getCompensationEat(), Employeur_Info.getCompensationEat(), Employeur_Info.getCompensationDrt(), Employeur_Info.getCompensationDrt(), Employeur_Info.getRemarque(),
                Employeur_Info.getOrientation());
        if (ReservedCaseTravalChoice == 1) {
            CalculValueOfTravel();
        }
        Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName() + " " + Employeur_Info.getLastName());
        SucceessAlert sc = new SucceessAlert(this);
        sc.setVisible(true);
    }
    int valClickAdd = 2;  // valClickAdd to block add ord Mission by error 1-for block user to clicked 

    /**
     * ****************************************************************
     */
    public void CreateCoreAddMission() {

        if ((ValLastOrientNord100 == -1) && (ValLastOrientSud100 == -1) && // this cnd for first ord Mission add in paper 
                (ValLastOrientNord25 == -1) && (ValLastOrientSud25 == -1)) {  // this condition for cn't add two mission lik 100% & 25% in one feuille ord Mission 
            AddNewOrdMissionToCal(); // fun set first data ord mission in one paper                                                                                                   // because they have't one price price 100 not price 25%
                                                                                                                                         // two case in two ord mission paper diferent
            Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
            valClickAdd = 1;  // changed to 1
            switch (ReductionValue) {
                case 0:
                    if (ValNord == 1) {
                        ValLastOrientNord25 = 1;   // changed to 1 from -1
                    } else {
                        ValLastOrientSud25 = 1;
                    }
                    break;

                case 1:
                    if (ValNord == 1) {
                        ValLastOrientNord100 = 1;
                    } else {
                        ValLastOrientSud100 = 1;
                    }
                    break;
            }

        } else {   // this else for the 2 end ord mission 

            if (ReductionValue == 1 && ValNord == 1) { // this cnd for verify the ord mission with the previeux ord mission is compareble or not 100% or 25% "same price"

                //if (ValLastOrientNord100==1) {
                if (ValLastOrientNord25 == 1) { // the previous OrdMiss is ValNord=1 and redct= 25 now cann't add to valNord=1 and Reduct=100% 
                    JOptionPane.showMessageDialog(null, "لا يمكنك اضافة مهمة 100 لعدم توافقها مع المهمة السابقة 25" + ValLastOrientNord25 + " you are Choice Reduction and Nord");
                } else { // we can to add ord miss 100 because previous OrdMiss Not 25 valnord=1

                    AddNewOrdMissionToCal(); //  add ordMission  100% 
                    ValLastOrientNord100 = 1;// OrdMission 100 is Created to Paper => not allow to Nord=1 prcg=25
                    Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                    valClickAdd = 1; 
                }

            } else if (ReductionValue == 1 && ValSud == 1) {
                //if (ValLastOrientSud100==1) {

                if (ValLastOrientSud25 == 1) {

                    JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientSud25=" + ValLastOrientSud25 + " you are Choice Reduction and sud");
                } else {
                    AddNewOrdMissionToCal();
                    ValLastOrientSud100 = 1;
                    Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                    valClickAdd = 1;
                }

            } else if (ReductionValue == 0 && ValNord == 1) {
                if (ValLastOrientNord100 == 1) {
                    JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientNord100=" + ValLastOrientNord100 + " you are Choice Reduction and Nord");

                } else {
                    AddNewOrdMissionToCal();
                    ValLastOrientNord25 = 1;
                    Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                    valClickAdd = 1;
                }

            } else if (ReductionValue == 0 && ValSud == 1) {
                if (ValLastOrientSud100 == 1) {
                    JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientSud100=" + ValLastOrientSud100 + " you are Choice Reduction and Nord");

                } else {
                    AddNewOrdMissionToCal();
                    ValLastOrientSud25 = 1;
                    Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                    valClickAdd = 1;
                }
            }

        }

    }

    private void DepuisMoisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepuisMoisActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_DepuisMoisActionPerformed

    public int Comp_Etaing() {
        if (ChexNord0.isSelected() == true) {

            return 1;
        } else {
            return 2;
        }
    }

    public int GetHour_Dte(Date D) {
        int Hr = D.getHours();
        return Hr;
    }

    public void InisialiseJspinner() {
        SpinnerDateModel mdl = new SpinnerDateModel(new Date(2017, 12, 21, 06, 00), null, null, Calendar.HOUR_OF_DAY);
        Heur_Go.setModel(mdl);
        Heur_Go1.setModel(mdl);
        Heur_Go2.setModel(mdl);

        JSpinner.DateEditor de = new JSpinner.DateEditor(Heur_Go, "HH:mm");
        Heur_Go.setEditor(de);

        de = new JSpinner.DateEditor(Heur_Go1, "HH:mm");
        Heur_Go1.setEditor(de);

        de = new JSpinner.DateEditor(Heur_Go2, "HH:mm");
        Heur_Go2.setEditor(de);

        Heur_Go.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Heur_Go1.setFont(new java.awt.Font("Times New Roman", 1, 14));
        Heur_Go2.setFont(new java.awt.Font("Times New Roman", 1, 14));

        /**
         * *************************************************************************************
         */
        /**
         * **************************************************************************************
         */
        mdl = new SpinnerDateModel(new Date(2017, 12, 21, 23, 00), null, null, Calendar.HOUR_OF_DAY);
        Heur_Back.setModel(mdl);
        Heur_Back1.setModel(mdl);
        Heur_Back2.setModel(mdl);

        de = new JSpinner.DateEditor(Heur_Back, "HH:mm");
        Heur_Back.setFont(new Font("Times New Roman", 1, 14));
        Heur_Back.setEditor(de);

        de = new JSpinner.DateEditor(Heur_Back1, "HH:mm");
        Heur_Back1.setFont(new Font("Times New Roman", 1, 14));
        Heur_Back1.setEditor(de);

        de = new JSpinner.DateEditor(Heur_Back2, "HH:mm");
        Heur_Back2.setFont(new Font("Times New Roman", 1, 14));
        Heur_Back2.setEditor(de);
        //Heur_Go2 //Heur_Back2
        /**
         * *******************************************************
         */

    }

    public String Hours_To_Str(int heur) {
        String Hrs = null;
        if (heur < 10) {
            Hrs = "0" + heur;

        } else {

            Hrs = "" + heur;
        }
        return Hrs;
    }

    public void Create_Employeur_Info() throws ParseException {
        String Nam_vrr = FullNam.getText();
        String Depui_vr = DepuisMois.getText();
        String Grad_vr = Grad.getText();
        String Job_vr = Job.getText();
        String Num_sem_vr = Num_Semantique.getText();
        String Residance_vr = Residence.getText();
        String Num_ccp_vr = Num_CCP.getText();
        String CauseTrsp_vr = (String) Jcom_CausTrsp.getSelectedItem();
        String Depart_vr = Depart.getText() + " -";
        String destinataire_vr = (String) ListDestainataire.getSelectedItem();

        String moyeTrans_vr = (String) Car_Travel.getSelectedItem();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date Date_Go_vr = jDateChGo1.getDate();
        Date Date_Back_vr = jDateChBack1.getDate();
        /**
         * *****************************************
         */
        //String Copier_Date_Go_vr=df.format(Date_Go_vr);
        //String FullName[]=Nam_vrr.split(" ");
        String Date_Go_vr_String = df.format(Date_Go_vr);
        String Date_Back_vr_String = df.format(Date_Back_vr);
        // JOptionPane.showMessageDialog(null, "The Date to striiiiiiiing:"+Date_Go_vr_String +"----"+Date_Back_vr_String);
        Date Dt_Jsp1 = (Date) Heur_Go.getValue();
        Date Dt_Jsp2 = (Date) Heur_Back.getValue();
        //int testHr=Dt_Jsp1.getHours();

        /**
         * ********Hour and Minute Go**************
         */
        int HourGo_int = GetHour_Dte(Dt_Jsp1);
        HourGo_int = Dt_Jsp1.getHours();
        int MinGo_int = Dt_Jsp1.getMinutes();
        /**
         * ***********************************
         */
        /**
         * *******Hour & Min Back************
         */
        int HourBack_int = GetHour_Dte(Dt_Jsp2);
        HourBack_int = Dt_Jsp2.getHours();
        int MinBack_int = Dt_Jsp2.getMinutes();
        /**
         * **********************************
         */
        //String  HourGo_vr=Hours_To_Str(HourGo_int); //ce value is just Hour
        String HourGo_vr;
        HourGo_vr = "" + Hours_To_Str(HourGo_int) + ":" + Hours_To_Str(MinGo_int) + "";//add the Min the Hour 
        //HourGo_vr=testHr;

        //String HourBack_vr=Hours_To_Str(HourBack_int);
        String HourBack_vr;
        HourBack_vr = "" + Hours_To_Str(HourBack_int) + ":" + Hours_To_Str(MinBack_int) + "";
        //HourBack_vr=testHr;
        // JOptionPane.showMessageDialog(null, "The hourGo...."+HourGo_vr);
        // JOptionPane.showMessageDialog(null,"the hour back..."+HourBack_vr);
        cal_val_rep_drt.calcule_eating_dortoire(Date_Go_vr_String, Date_Back_vr_String, HourGo_vr, HourBack_vr);
        int compensationEat_vr = cal_val_rep_drt.getNbreRepat();// This is Point 
        int compensationDrt_vr = cal_val_rep_drt.getNbreDortoire();
        String Remarque_vr = RemarqueTxt.getText();
        if (ChexNord0.isSelected()) {
            Orientation = 1;
        } else {
            Orientation = 2;
        }
        df = new SimpleDateFormat("yyyy/MM/dd");
        Date_Go_vr_String = df.format(Date_Go_vr);
        Date_Back_vr_String = df.format(Date_Back_vr);

        Employeur_Info = new Info_Ord(
                Nam_vrr, "", Depui_vr, Grad_vr, Job_vr, Num_sem_vr, Residance_vr, Num_ccp_vr,
                CauseTrsp_vr, Depart_vr, destinataire_vr, moyeTrans_vr,
                Date_Go_vr_String, HourGo_vr, Date_Back_vr_String, HourBack_vr,
                compensationEat_vr, compensationDrt_vr, Orientation,
                Remarque_vr);
    }


    private void Valid_LabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Valid_LabMouseEntered
        Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/AddFill.png")));
    }//GEN-LAST:event_Valid_LabMouseEntered


    private void Add_Mission_LabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_Mission_LabMouseClicked
        TaskAttach = "addotherMission";
        ReservedCaseTravalChoice = 0;

        if (NumberORdMission >= 1) {
            if (valClickAdd == 1) {

                confirmation.DisplayMsg("هل تريـــد اضــافة مهمــة اخرى");
                confirmation.setVisible(true);
            } else {
                ok = new Ok1(this, true, "لقد تمت اضافــة المهمة");
                ok.setVisible(true);
                //valClickAdd=2;
            }

        } else {

            ok = new Ok1(this, true, "لا يمكنك اضـافة مهمات يمكنك الاضافة بعد ادخـال المهمة الاولى");
            ok.setVisible(true);
            valClickAdd = 2;

        }


    }//GEN-LAST:event_Add_Mission_LabMouseClicked

    public void AddOtherMission() {

        Valid_Lab.setEnabled(true);
        NumberORdMission = NumberORdMission + 1;
        Mission_Name.setText("");
        jDateChGo1.setDate(null);
        jDateChBack1.setDate(null);
        ChexSud1.setSelected(false);
        ChexNord0.setSelected(false);
        ValNord = 0;
        ValNord = 0;
        RemarqueTxt.setText("");
        ListDestainataire.setSelectedIndex(0);
        int x = Remplir_Info_obj.GetNum_Line();
        x = x + 1;
        Remplir_Info_obj.setNum_Line(x);
        Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list.png")));
        Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));
        valClickAdd = 0;

    }


    private void New_MissionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_New_MissionMouseClicked
        // TODO add your handling code here:
        /*
        if (NumberORdMission!=0) {
             Remplir_Info_obj.Close_Wrbk(); 
        }
        NumberORdMission=0;
        Remplir_Info_obj.setNum_Line(8);
        FullNam.setText("ادخل اسم الموظف");
        FullNam.setForeground(Color.gray);
        DepuisMois.setText("خلال شهر");
        DepuisMois.setForeground(Color.gray);
        Grad.setText("ادخل درجة الموظف");
        Grad.setForeground(Color.gray);
        Job.setForeground(Color.gray);
        Job.setText("ادخل الوظيفة");
        Num_Semantique.setText("ادخل الرقم الاستدلالي");
        Num_Semantique.setForeground(Color.gray);
        Num_CCP.setText("ادخل رقم الحساب الجاري");
        Num_CCP.setForeground(Color.gray);
        Residence.setText("ادخل الاقامة الادارية");
        Residence.setForeground(Color.gray);
        Mission_Name.setText("اسم المهمة ");
        Mission_Name.setForeground(Color.gray);
        RemarqueTxt.setText("أضف ملاحظات .....");
        RemarqueTxt.setForeground(Color.gray);
        Add_Mission_Lab.setVisible(true);
         */
        // New_Mission.setEnabled(false);
        // Valid_Lab.setEnabled(true);//ajouter mission enbl
        //Add_Mission_Lab.setEnabled(false);//Add_Mission_Lab enbl
        // Clear_Lab.setEnabled(true);
    }//GEN-LAST:event_New_MissionMouseClicked

    public void InitialisedChamp() {
        FullNam.setText("ادخل اسم الموظف");
        FullNam.setForeground(Color.gray);
        DepuisMois.setText("خلال شهر");
        DepuisMois.setForeground(Color.gray);
        Grad.setText("ادخل درجة الموظف");
        Grad.setForeground(Color.gray);
        Job.setForeground(Color.gray);
        Job.setText("ادخل الوظيفة");
        Num_Semantique.setText("ادخل الرقم الاستدلالي");
        Num_Semantique.setForeground(Color.gray);
        Num_CCP.setText("ادخل رقم الحساب الجاري");
        Num_CCP.setForeground(Color.gray);
        Residence.setText("ادخل الاقامة الادارية");
        Residence.setForeground(Color.gray);
        Mission_Name.setText("اسم المهمة ");
        Mission_Name.setForeground(Color.gray);
//        Destiantaie.setText("متوجه الي");
        RemarqueTxt.setText("أضف ملاحظات .....");
        RemarqueTxt.setForeground(Color.gray);
    }
    private void FullNamFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FullNamFocusGained
        // TODO add your handling code here:
        if (FullNam.getText().equals("ادخل اسم الموظف")) {
            FullNam.setText("");
            FullNam.setForeground(Color.black);
        }

        /* FullNam.setText("");
        FullNam.setForeground(Color.black);*/
    }//GEN-LAST:event_FullNamFocusGained

    private void GradFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_GradFocusGained
        // TODO add your handling code here:

        if (Grad.getText().equals("ادخل درجة الموظف")) {
            Grad.setText("");
            Grad.setForeground(Color.black);
        }

    }//GEN-LAST:event_GradFocusGained

    private void JobFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JobFocusGained
        // TODO add your handling code here:
        if (Job.getText().equals("ادخل الوظيف")) {
            Job.setText("");
            Job.setForeground(Color.black);
        }

    }//GEN-LAST:event_JobFocusGained

    private void Num_SemantiqueFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Num_SemantiqueFocusGained
        // TODO add your handling code here:
        if (Num_Semantique.getText().equals("ادخل الرقم الاستدلالي")) {
            Num_Semantique.setText("");
            Num_Semantique.setForeground(Color.black);
        }

    }//GEN-LAST:event_Num_SemantiqueFocusGained

    private void ResidenceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ResidenceFocusGained
        // TODO add your handling code here:
        if (Residence.getText().equals("ادخل الاقامة الادارية")) {
            Residence.setText("");
            Residence.setForeground(Color.black);
        }

    }//GEN-LAST:event_ResidenceFocusGained

    private void Num_CCPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Num_CCPFocusGained
        if (Num_CCP.getText().equals("ادخل رقم الحساب الجاري")) {
            Num_CCP.setText("");
            Num_CCP.setForeground(Color.black);
        }
    }//GEN-LAST:event_Num_CCPFocusGained

    private void Mission_NameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Mission_NameFocusGained
        Mission_Name.setText("");
        Mission_Name.setForeground(Color.black);
    }//GEN-LAST:event_Mission_NameFocusGained
    public void CalculateTotalValueForMission() {

        if (NumberORdMission == 1) {// just one Ord Mission
            Remplir_Info_obj.RemplirSomDrt();// calculate Some Number Repat And Some Number Dortoir All Mission
            //Remplir_Info_obj.SumCompensationTransport();  //Calcule Some Of Price Of Transport 
            if (ReductionValue == 1) {        // if Mission is 100%
                if (ValNord == 1) {    //This for Choice Of Nord 
                    //JOptionPane.showMessageDialog(null, "The Nord Orientation "); 
                    Remplir_Info_obj.GetNbrCompensationNrd(); //for deplace Value of Some to sheet 1 to calculate 
                    GetPriceEatANDDecocher(NumberORdMission);
                } else {

                    Remplir_Info_obj.GetNbrCompensationSudForOneMission(1); //This For Deplace Some Repat And Decocher To Calculate : arg =1: Just One OrdMission in p18 / p19 (Repat Sud in p18 and p19) 
                    GetPriceEatANDDecocherForOneMission(0);//                                                       : arg!=1: for 2 Mission Nord And Sude sud in p20 and p21
                }
                Remplir_Info_obj.SumCompensationToujours(NumberORdMission, 0, 0);  // ce methode is calculate product of prix * number  AND Calcul SUM
                Remplir_Info_obj.TotlaSumBenefit();

                Remplir_Info_obj.SumTransport_and_compensationTtl();
                //Remplir_Info_obj.ChangeThisNumber();
                Remplir_Info_obj.Date_Delivred();
                Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));
            } else {
                //JOptionPane.showMessageDialog(null, "The ReductionValue is 25%");
                Remplir_Info_obj.RemplirSomDrt();
                if (ValNord == 1) {

                    //JOptionPane.showMessageDialog(null, "The Orientation Is Nord ");
                    Remplir_Info_obj.GetNbrCompensationNrdWithReduction(1);
                    //GetPriceEatANDDecocherCForReduction(1);
                    GetPriceEatANDDecocherCForReduction(1);
                    Remplir_Info_obj.SumCompensationToujoursForReduction();
                } else {
                    //JOptionPane.showMessageDialog(null, "The Orientation Is Sud ");
                    Remplir_Info_obj.GetNbrCompensationNrdWithReduction(0);
                    // GetPriceEatANDDecocherCForReduction(1);
                    GetPriceEatANDDecocherCForReduction(1);
                    Remplir_Info_obj.SumCompensationToujoursForReduction();
                    //Remplir_Info_obj.CalculePrix_25();
                }

                //Remplir_Info_obj.SumCompensationToujours();  // ce methode is calculate product of prix * number  AND Calcul SUM
                //new Code 
                Remplir_Info_obj.InitialiseCellReductionPrice(NumberORdMission, 0, 0);
                Remplir_Info_obj.TotlaSumBenefit();

                Remplir_Info_obj.SumTransport_and_compensationTtl();
                //Remplir_Info_obj.ChangeThisNumber();
                Remplir_Info_obj.Date_Delivred();
                Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));
            }

        } else {

            Remplir_Info_obj.RemplirSomDrt();

            if ((ValLastOrientSud100 == 1 || ValLastOrientSud25 == 1) && ValLastOrientNord100 == -1 && ValLastOrientNord25 == -1) {

                //deplace sud number to first Cellss 
                if (ValLastOrientSud100 == 1) {
                    Remplir_Info_obj.GetNbrCompensationSudForOneMission(1);
                    GetPriceEatANDDecocherForOneMission(0);
                    //Remplir_Info_obj.SumCompensationToujours(NumberORdMission);  // ce methode is calculate product of prix * number  AND Calcul SUM
                    Remplir_Info_obj.SumCompensationToujours(1, 0, 0); //because just on orientation (1 is condition methode SumCompensationToujours so we dont need N20 and N21)
                    Remplir_Info_obj.TotlaSumBenefit();

                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                    Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                    Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));
                } else {    //ValLastOrientSud25==1
                    Remplir_Info_obj.GetNbrCompensationNrdWithReduction(0);
                    // GetPriceEatANDDecocherCForReduction(1);
                    GetPriceEatANDDecocherForOneMission(0);
                    Remplir_Info_obj.SumCompensationToujoursForReduction();
                    Remplir_Info_obj.InitialiseCellReductionPrice(NumberORdMission, 1, 0);
                    Remplir_Info_obj.TotlaSumBenefit();
                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                    Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                    Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));
                }

                /**
                 * ************************************************************************
                 */
            } else if ((ValLastOrientNord100 == 1 || ValLastOrientNord25 == 1) && ValLastOrientSud100 == -1 && ValLastOrientSud25 == -1) {

                if (ValLastOrientNord100 == 1) {
                    // Remplir_Info_obj.GetNbrCompensationSudForOneMission(1);
                    Remplir_Info_obj.GetNbrCompensationNrd();
                    GetPriceEatANDDecocherForOneMission(1);
                    //Remplir_Info_obj.SumCompensationToujours(NumberORdMission);  // ce methode is calculate product of prix * number  AND Calcul SUM
                    Remplir_Info_obj.SumCompensationToujours(1, 0, 0); //because just on orientation (1 is condition methode SumCompensationToujours so we dont need N20 and N21)
                    Remplir_Info_obj.TotlaSumBenefit();

                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                    Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                    Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));
                } else {    //ValLastOrientNord25==1
                    //Remplir_Info_obj.GetNbrCompensationSudForOneMission(1);
                    Remplir_Info_obj.GetNbrCompensationNrd();
                    GetPriceEatANDDecocherForOneMission(1);

                    Remplir_Info_obj.SumCompensationToujoursForReduction();

                    Remplir_Info_obj.InitialiseCellReductionPrice(NumberORdMission, 1, 0);
                    Remplir_Info_obj.TotlaSumBenefit();

                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                    Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                    Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));
                }
            } else if (ValLastOrientNord100 == 1 && ValLastOrientSud25 == 1) {  //nord 100% and sud 25%

                Remplir_Info_obj.GetNbrCompensationNrd();
                Remplir_Info_obj.GetNbrCompensationSud();
                GetPriceEatANDDecocher(NumberORdMission);
                Remplir_Info_obj.SumCompensationToujours(NumberORdMission, ValLastOrientNord25, ValLastOrientSud25);
                Remplir_Info_obj.InitialiseCellReductionPrice(NumberORdMission, ValLastOrientNord25, ValLastOrientSud25);
                Remplir_Info_obj.TotlaSumBenefit();
                Remplir_Info_obj.SumTransport_and_compensationTtl();

                //Remplir_Info_obj.ChangeThisNumber();
                Remplir_Info_obj.Date_Delivred();
                Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));

            } else if (ValLastOrientSud100 == 1 && ValLastOrientNord25 == 1) { //nord 25% and Sud 100%
                Remplir_Info_obj.GetNbrCompensationNrd();
                Remplir_Info_obj.GetNbrCompensationSud();
                GetPriceEatANDDecocher(NumberORdMission);
                Remplir_Info_obj.SumCompensationToujours(NumberORdMission, ValLastOrientNord25, ValLastOrientSud25);
                Remplir_Info_obj.InitialiseCellReductionPrice(NumberORdMission, ValLastOrientNord25, ValLastOrientSud25);

                Remplir_Info_obj.TotlaSumBenefit();
                Remplir_Info_obj.SumTransport_and_compensationTtl();

                //Remplir_Info_obj.ChangeThisNumber();
                Remplir_Info_obj.Date_Delivred();
                Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));
            } else if (ValLastOrientSud25 == 1 && ValLastOrientNord25 == 1) {    //two oprientation different and 25%

                Remplir_Info_obj.GetNbrCompensationNrd();
                Remplir_Info_obj.GetNbrCompensationSud();
                GetPriceEatANDDecocher(NumberORdMission);
                Remplir_Info_obj.SumCompensationToujours(NumberORdMission, ValLastOrientNord25, ValLastOrientSud25);
                Remplir_Info_obj.InitialiseCellReductionPrice(NumberORdMission, ValLastOrientNord25, ValLastOrientSud25);

                Remplir_Info_obj.TotlaSumBenefit();
                Remplir_Info_obj.SumTransport_and_compensationTtl();

                //Remplir_Info_obj.ChangeThisNumber();
                Remplir_Info_obj.Date_Delivred();
                Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));

            } else {   //Remplir_Info_obj.Inisialise_Sheet1();

                Remplir_Info_obj.GetNbrCompensationNrd();
                Remplir_Info_obj.GetNbrCompensationSud();
                GetPriceEatANDDecocher(NumberORdMission);
                Remplir_Info_obj.SumCompensationToujours(NumberORdMission, ValLastOrientNord25, ValLastOrientSud25);
                Remplir_Info_obj.TotlaSumBenefit();
                Remplir_Info_obj.SumTransport_and_compensationTtl();

                //Remplir_Info_obj.ChangeThisNumber();
                Remplir_Info_obj.Date_Delivred();

                Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
                Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));

                //Remplir_Info_obj.SumCompensationTransport();
                /*Remplir_Info_obj.GetNbrCompensationNrd(); //for deplace Value of Some to sheet 1 to calculate  
            Remplir_Info_obj.GetNbrCompensationSud();//for deplace Value of Some to sheet 1 to calculate  
           GetPriceEatANDDecocher(NumberORdMission);  //select value of prix for direction   Ancian Code
            
               //  GetPriceEatANDDecocherCForReduction(2); //New Code
            //Remplir_Info_obj.RemplirePriceOrdMissionNord(1000, 1200, 1);
            Remplir_Info_obj.SumCompensationToujours(NumberORdMission,0,0);  // ce methode is calculate product of prix * number  AND Calcul SUM
            Remplir_Info_obj.TotlaSumBenefit();
             Remplir_Info_obj.SumTransport_and_compensationTtl();
            
            //Remplir_Info_obj.ChangeThisNumber();
            Remplir_Info_obj.Date_Delivred();*/
            }

        }

    }
    private void btn_ImprimreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImprimreActionPerformed

        if (NumberORdMission != 0) {
            CalculateTotalValueForMission();
            Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName() + " " + Employeur_Info.getLastName());
            Remplir_Info_obj.Close_Wrbk();
            enablePanelInformation(true, jPanel13, 0);
            try {
                Desktop dt = Desktop.getDesktop();
                // dt.open(new File("src\\OurFile\\AppClose.xlsx"));
                dt.open(new File(Employeur_Info.getFirstName() + " " + Employeur_Info.getLastName() + ".xlsx"));
                //  dt.open(new File(""+FullNam.getText()+".xlsx"));

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error in Opened The File");
            }

            //New_Mission.setEnabled(true);
            // Valid_Lab.setEnabled(false);//ajouter mission enbl
            //Add_Mission_Lab.setEnabled(false);//Add_Mission_Lab enbl
            //Clear_Lab.setEnabled(false);
            NumberORdMission = 0;
            ReservedCaseTravalChoice = 0;
        } else {

            //JOptionPane.showMessageDialog(null, "No Ord Mission Created To Display");
            ok = new Ok1(this, true, "لا يوجد مهمـــــة تم كتابتها");
            ok.setVisible(true);
        }

    }//GEN-LAST:event_btn_ImprimreActionPerformed
    Ok1 ok;
    private void Clear_LabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Clear_LabMouseClicked
        TaskAttach = "CancelOrdMission";
        ReservedCaseTravalChoice = 0;

        //CancelAllMission
        confirmation.DisplayMsg("هل تريـــد الغاء المهمات");
        confirmation.setVisible(true);

    }//GEN-LAST:event_Clear_LabMouseClicked
    public void CancelAllMission() {
        FullNam.setText("ادخل اسم الموظف");
        FullNam.setForeground(Color.gray);
        DepuisMois.setText("خلال شهر");
        DepuisMois.setForeground(Color.gray);
        Grad.setText("ادخل درجة الموظف");
        Grad.setForeground(Color.gray);

        Job.setForeground(Color.gray);
        Job.setText("ادخل الوظيفة");
        Num_Semantique.setText("ادخل الرقم الاستدلالي");
        Num_Semantique.setForeground(Color.gray);

        Num_CCP.setText("ادخل رقم الحساب الجاري");
        Num_CCP.setForeground(Color.gray);

        Residence.setText("ادخل الاقامة الادارية");
        Residence.setForeground(Color.gray);

        Mission_Name.setText("اسم المهمة ");
        Mission_Name.setForeground(Color.gray);
//        Destiantaie.setText("متوجه الي");

        RemarqueTxt.setText("أضف ملاحظات .....");
        RemarqueTxt.setForeground(Color.gray);
        ListDestainataire.setSelectedIndex(0);

        CreateNewOrdMission();
        /* if (NumberORdMission!=0) {
            NumberORdMission=0;
            enablePanelInformation(true);
             Remplir_Info_obj.Close_Wrbk();
        }*/

        ok = new Ok1(this, true, "تم الغاء جميع المهمات");
        ok.setVisible(true);
    }
    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        // TODO add your handling code here:
        //   jLabel5.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,204,255),2));
        //   jLabel5.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,204,255),2));
        jLabel5.setForeground(Color.green);
    }//GEN-LAST:event_jLabel5MouseEntered
    String ChoiceChart = "";
    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, "The chart Is "+CharChoiceMonth);
        if (CharChoiceMonth == ' ') {
            DepuisMois.setText((String) CombMonth.getSelectedItem());
        } else {
            DepuisMois.setText(DepuisMois.getText() + " " + CombMonth.getSelectedItem() + " " + ChoiceChart + " " + CombMonth1.getSelectedItem());
        }


    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        String FnctEmp ;

        if (checkFunct.isSelected()) {
            FnctEmp = Function_Choice.getSelectedItem();
        } else {
            FnctEmp = ChoiceGrd.getSelectedItem();
        }

        PersonRemplissage = new Employeur(0, Reg_Name.getText(), Reg_LastName.getText(),
                (String) ChoiceGrd.getSelectedItem(), helper.GetID_Grade(ChoiceGrd.getSelectedItem()),
                /* Reg_Jop.getText()*/ FnctEmp, Reg_CCP.getText(), Reg_NumSemt.getText(), Reg_Residence.getText());
        PersonRemplissage.Add_Employeur();

        PersonRemplissage.AfficheIntable(ModelTable2);
        Tab_InfoEmp.setModel(ModelTable2);
    }//GEN-LAST:event_jLabel33MouseClicked

    public void SideInterne() {
        Thread th;
        th = new Thread() {
            @Override
            public void run() {
                try {
                    // for (int i = 10; i >= -150; i--) {
                    for (int i = 0; i >= -150; i--) {
                        Thread.sleep((long) 0.9);
                        Pan_Menu.setLocation(i, 10);

                        if (i > -140 & jButton2.getX() > 0) {
                            jButton2.setLocation(jButton2.getX() - 1, 10);
                        }
                    }
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, "The Error for thread is :" + ex.getMessage());
                }
            }
        };
        th.start();

        //480-612
        //348-612
        Pan_All_Pan_fr_Tab.setLocation(0, 50);
        //Pan_All_Pan_fr_Tab.setPreferredSize();

        Pan_All_Pan_fr_Tab.setSize(new Dimension(490, 612));
        ResizeTable(0);
        /*jScrollPane4.setPreferredSize(new Dimension(480, 402));
         Tab_InfoEmp.setPreferredScrollableViewportSize(new Dimension(480, 402));
         Tab_InfoEmp.setPreferredSize(new Dimension(480, 402));
         */
        //jScrollPane4.setPreferredSize(new Dimension(490, 402));
        //jScrollPane4.setSize(new Dimension(490, 402));
        //Tab_InfoEmp.setPreferredSize(new Dimension(490, 402));
    }

    public void SideInterne_Update() {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    // for (int i = 10; i >= -150; i--) {
                    for (int i = 0; i >= -150; i--) {
                        Thread.sleep((long) 0.9);
                        Pan_Menu.setLocation(i, 10);

                        if (i > -140 & jButton2.getX() > 0) {
                            jButton2.setLocation(jButton2.getX() - 1, 10);
                        }
                    }
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, "The Error for thread is :" + ex.getMessage());
                }
            }
        };
        th.start();

        //480-612
        //348-612
        panServices.setLocation(0, 50);
        Pan_All_Pan_fr_Tab.setLocation(790, 50);
        //Pan_All_Pan_fr_Tab.setPreferredSize();
        TxtSearch.setLocation(337, 10);
        Pan_All_Pan_fr_Tab.setSize(new Dimension(490, 612));

        // jScrollPane4.setPreferredSize(new Dimension(452, 402));
        ResizeTable(0);
        /*jScrollPane4.setPreferredSize(new Dimension(480, 402));
         Tab_InfoEmp.setPreferredScrollableViewportSize(new Dimension(480, 402));
         Tab_InfoEmp.setPreferredSize(new Dimension(480, 402));
         */
        //jScrollPane4.setPreferredSize(new Dimension(490, 402));
        //jScrollPane4.setSize(new Dimension(490, 402));
        //Tab_InfoEmp.setPreferredSize(new Dimension(490, 402));
    }

    public void ResizeTable(int x) {
        TableColumnModel ColumMdl = Tab_InfoEmp.getColumnModel();
        if (x == 0) {
            ColumMdl.getColumn(0).setPreferredWidth(488 / 4);
            ColumMdl.getColumn(1).setPreferredWidth(488 / 4);
            ColumMdl.getColumn(2).setPreferredWidth(488 / 4);
            ColumMdl.getColumn(3).setPreferredWidth(488 / 4);
            jScrollPane4.setPreferredSize(new Dimension(488, 319));
        } else {
            ColumMdl.getColumn(0).setPreferredWidth(350 / 4);
            ColumMdl.getColumn(1).setPreferredWidth(350 / 4);
            ColumMdl.getColumn(2).setPreferredWidth(350 / 4);
            ColumMdl.getColumn(3).setPreferredWidth(350 / 4);

            jScrollPane4.setPreferredSize(new Dimension(350, 319));
        }

    }

    public void SideExterne() {
        Thread th = new Thread() {
            @Override
            public void run() {

                //JOptionPane.showMessageDialog(null, "I am in Method run of thread ");
                try {
                    // for (int i = (-150); i < 10; i++) {
                    for (int i = (-150); i < 0; i++) {
                        Thread.sleep((long) 0.9);
                        Pan_Menu.setLocation(i, 10);

                        /* if (i<0) {
                            jButton2.setLocation(jButton2.getX()+1, 10);
                        }*/
                        if (i >= -140 && jButton2.getX() != 140) {
                            jButton2.setLocation(jButton2.getX() + 1, 10);
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "The Error for thread is :" + ex.getMessage());
                }
            }
        };
        th.start();
        Pan_All_Pan_fr_Tab.setLocation(140, 50);
        // Pan_All_Pan_fr_Tab.setPreferredSize();
        Pan_All_Pan_fr_Tab.setSize(new Dimension(350, 612));
        //Tab_InfoEmp.setPreferredSize(new Dimension(338, 402));

    }

    public void SideExterne_Update() {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = (-150); i < 0; i++) {
                        Thread.sleep((long) 0.9);
                        Pan_Menu.setLocation(i, 10);
                        if (i >= -140 && jButton2.getX() != 140) {
                            jButton2.setLocation(jButton2.getX() + 1, 10);
                        }
                    }
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, "The Error for thread is :" + ex.getMessage());
                }
            }
        };
        th.start();
        panServices.setLocation(140, 50);
        Pan_All_Pan_fr_Tab.setLocation(930, 50);
        // Pan_All_Pan_fr_Tab.setPreferredSize();
        Pan_All_Pan_fr_Tab.setSize(new Dimension(350, 612));
        TxtSearch.setLocation(200, 10);
//         Tab_InfoEmp.setPreferredSize(new Dimension(300, 402));
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int x = Pan_Menu.getX();
        int y = Pan_Menu.getY();

        int Val = jButton2.getX();

        if (Val > 10) {
            SideInterne_Update();
            /*Thread th=new Thread(){
            @Override
            public void run() {
                try{
                    for (int i = 10; i >= -150; i--) {
                        Thread.sleep((long) 0.9);
                        Pan_Menu.setLocation(i, 10);
                        if(i>-140){
                        jButton2.setLocation(jButton2.getX()-1, 10);
                        }
                    }
                   }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "The Error for thread is :"+ex.getMessage());
                }
            }
         };th.start();
         Pan_All_Pan_fr_Tab.setLocation(0, 50);
         Pan_All_Pan_fr_Tab.setSize(new Dimension(490 , 612));
             */

        } else {
            SideExterne_Update();
            //this.add(jlayer);
            /*Thread th=new Thread(){
            @Override
            public void run() {
                try{
                    for (int i = (-150); i < 10; i++) {
                        Thread.sleep((long) 0.9);
                        Pan_Menu.setLocation(i, 10);
                        if (i>=-140 && jButton2.getX()!=150) {
                            jButton2.setLocation(jButton2.getX()+1, 10);
                        }
                   }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "The Error for thread is :"+ex.getMessage());
                }
            }
        };th.start();
         Pan_All_Pan_fr_Tab.setLocation(150, 50);
         Pan_All_Pan_fr_Tab.setSize(new Dimension(338, 612));
             */
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    int ChoiceTask = 1;
    private void Tab_InfoEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tab_InfoEmpMouseClicked

        if (!Tab_InfoEmp.isEnabled()) {
            //JOptionPane.showMessageDialog(null, "You Can't Select Employe");
            Toolkit.getDefaultToolkit().beep();
            ok = new Ok1(this, true, "لا يمكنك اختيار موظف حتئ انتهاء العملية");
            ok.setVisible(true);
        } else {

            switch (ChoiceTask) {
                case 0:
                    break;
                case 1:

                    //case of Click جدول المصاريف   
                    if (NumberORdMission == 0 && valClickAdd == 2) {
                        if (jButton2.getX() > 10) {
                            SideInterne_Update();
                        }
                        int RowNum = Tab_InfoEmp.getSelectedRow();

                        Valid_Lab.setEnabled(true);

                        //  remplire_Champ_Wt_Tbl();
                        int Id_emp = (int) Tab_InfoEmp.getValueAt(RowNum, 3);

                        Person = new Employeur();

                        //  JOptionPane.showMessageDialog(null, Tab_InfoEmp.getValueAt(RowNum,3));
                        Person.GetInformationAttribut(Id_emp);

                        remplire_Champ_Wt_Tbl(Person.getFirst_Name_Emp() + " " + Person.getLast_Name_Emp(), Person.getCCP_Num_Emp(), Person.getGrad_Emp(), Person.getFun_Emp(), Person.getSem_Num_Emp());

                    } else {

                        if (jButton2.getX() > 10) {
                            SideInterne_Update();
                        }

                        ok = new Ok1(this, true, "لا يمكنك اختيار الموظف لم تكتمل المهمة السابقةِ");
                        ok.setVisible(true);
                        Tab_InfoEmp.getSelectionModel().clearSelection();
                    }

                    break;
                /**
                 * ************************************
                 */
                case 3:
                    int x = (int) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 3);
                    //JOptionPane.showMessageDialog(null, "The value is :"+x);
                    jLabel81.setText(x + "");
                    FirstName.setText((String) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 1));
                    LastName.setText((String) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 2));

                    FirstName.setText((String) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 2));
                    LastName.setText((String) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 1));
                    GradOrdMissionCns.setText((String) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 0));
                    FuncOrdMissCons.setText((String) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 0));

                    break;

                case 4:

                    FirstName1.setText((String) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 2));
                    LastName1.setText((String) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 1));
                    //jTextField13.setText((String) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 0));
                    PersonRemplissage.GetInformationEmployer((int) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 3));
                    jTextField13.setText(PersonRemplissage.getGrad_Emp());
                    jTextField17.setText(PersonRemplissage.getFun_Emp());
                    ResidentAdm1.setText(PersonRemplissage.getResidance_Emp());

                    break;

                case 5:

                    if (Case_Table == 1) {
                        Reg_Name.setText((String) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 2));
                        Reg_LastName.setText((String) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 1));
                        //jTextField13.setText((String) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 0));
                        PersonRemplissage.GetInformationEmployer((int) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 3));
                        ChoiceGrd.select(PersonRemplissage.getGrad_Emp());
                        Function_Choice.select(PersonRemplissage.getFun_Emp());
                        Reg_NumSemt.setText(PersonRemplissage.getSem_Num_Emp());
                        Reg_CCP.setText(PersonRemplissage.getCCP_Num_Emp());
                        Reg_Residence.setText(PersonRemplissage.getResidance_Emp());

                    }
                    break;

                /**
                 * ***************************************
                 */
                default:
                    break;

            }

            /*if (jButton2.getX()>10) {
        SideInterne();
        }
        int RowNum =Tab_InfoEmp.getSelectedRow();
        System.out.println("The Row is Number :"+RowNum);
       // JOptionPane.showMessageDialog(null, "the Row is : "+RowNum );
 System.out.println("ModelTable.getValueAt(RowNum, 3)"+ ModelTable2.getValueAt(RowNum, 3));
        System.out.println("ModelTable.getValueAt(RowNum, 2)"+(String) ModelTable2.getValueAt(RowNum, 2));
        System.out.println("ModelTable.getValueAt(RowNum, 1)"+(String) ModelTable2.getValueAt(RowNum, 1));
        System.out.println("ModelTable.getValueAt(RowNum, 1)"+(String) ModelTable2.getValueAt(RowNum, 0));        
        Valid_Lab.setEnabled(true);
      //  remplire_Champ_Wt_Tbl();
        Person=new Employeur();
        Person.GetInformationAttribut((int)ModelTable2.getValueAt(RowNum,3));
        remplire_Champ_Wt_Tbl(Person.getFirst_Name_Emp()+" "+Person.getLast_Name_Emp(), Person.getCCP_Num_Emp(),Person.getGrad_Emp(), Person.getFun_Emp(), Person.getSem_Num_Emp());
             */
        }


    }//GEN-LAST:event_Tab_InfoEmpMouseClicked

    private void jPanel6MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseDragged
        // TODO add your handling code here:
/*      int x=evt.getXOnScreen();
        int y=evt.getYOnScreen();
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getXOnScreen()"+x);
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getYOnScreen()"+y);
        this.setLocation(x-xx, y-yy);    
        int x=evt.getXOnScreen();
        int y=evt.getYOnScreen();
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getXOnScreen()"+x);
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getYOnScreen()"+y);
        this.setLocation(x-xx, y-yy);
        System.out.println("********************************************************************");
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getXOnScreen()"+x);
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getYOnScreen()"+y);*/

    }//GEN-LAST:event_jPanel6MouseDragged


    private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MousePressed

        /*  
       this.setOpacity((float)0.7);
        xx=evt.getX();
        xy=evt.getY();
         System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getX() Mousepressed"+xx);
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getY()Mousepressed"+xy);*/
    }//GEN-LAST:event_jPanel6MousePressed

    private void jPanel6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseReleased
        // TODO add your handling code here:
        //  this.setOpacity((float)1.0);

    }//GEN-LAST:event_jPanel6MouseReleased

    private void jLabel42MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseEntered
        // TODO add your handling code here:
        //ImageIcon icn=new ImageIcon("src\\Image\\ImgCls\\Captions_Close_Hot.png");
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Captions_Close_Hot.png")));


    }//GEN-LAST:event_jLabel42MouseEntered

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel42MouseClicked

    private void jLabel42MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseExited
        // TODO add your handling code here:
        //jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ImgCls/Captions_Close.png")));
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Captions_Close.png")));
    }//GEN-LAST:event_jLabel42MouseExited

    private void panServicesMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panServicesMouseDragged
        // TODO add your handling code here:
        /* 
        int x=evt.getXOnScreen();
        
        int y=evt.getYOnScreen();
        
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getXOnScreen()"+x);
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getYOnScreen()"+y);
        this.setLocation(x-xx, y-xy);       
        System.out.println("********************************************************************");
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getXOnScreen()"+x);
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getYOnScreen()"+y);
         */
    }//GEN-LAST:event_panServicesMouseDragged

    private void panServicesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panServicesMousePressed
        // TODO add your handling code here:
        /* this.setOpacity((float)0.7);
        xx=evt.getX();
        xy=evt.getY();
        
         System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getX() Mousepressed"+xx);
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getY()Mousepressed"+xy);*/

    }//GEN-LAST:event_panServicesMousePressed

    private void jLabel43MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseEntered
        // TODO add your handling code here:
        //jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ImgCls/Captions_Min_Hot.png")));
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Captions_Min_Hot.png")));
    }//GEN-LAST:event_jLabel43MouseEntered

    private void jLabel43MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseExited
        // TODO add your handling code here:
        //jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ImgCls/Captions_Min.png")));
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Captions_Min.png")));
    }//GEN-LAST:event_jLabel43MouseExited

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
        // TODO add your handling code here:

        this.setState(ICONIFIED);
    }//GEN-LAST:event_jLabel43MouseClicked
//
    private void ListDestainataireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListDestainataireActionPerformed
        // TODO add your handling code here:
        String Wilaye = (String) ListDestainataire.getSelectedItem();
        // JOptionPane.showMessageDialog(null, "I am select Sud Item"+Wilaye);

        switch (Wilaye) {

            case "ولاية أدرار":
            case "ولاية الأغواط":
            case "ولاية بسكرة":
            case "ولاية تمنراست":
            case "ولاية ورقلة":
            case "ولاية إليزي":
            case "ولاية تندوف":
            case "ولاية الوادي":
            case "ولاية غرداية":
            case "ولاية البيض":
            case "ولاية النعامة":
                // JOptionPane.showMessageDialog(null, "I am select Sud Item");
                System.out.println("gestion_ord_mission.Home.Jcom_CausTrspActionPerformed()");

                ChexSud1.setSelected(true);
                //ValLastOrientSud=1;
                ChexNord0.setSelected(false);
                break;
            case "اختر الولاية":
                //JOptionPane.showMessageDialog(null, "Please Select Wilaya ");
                break;
            default:
                ChexNord0.setSelected(true);
                //ValLastOrientNord=1;
                ChexSud1.setSelected(false);
                break;
        }

    }//GEN-LAST:event_ListDestainataireActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        // New_Mission.setEnabled(false);
        // Add_Mission_Lab.setEnabled(false);
        //this.MaximaizeMinimize(0);
        // PanCHoiceRdi.setSize(0, 0);
    }//GEN-LAST:event_formWindowOpened

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
//jPanel12.setVisible(true);
        ChoiceTask = 4;

        ChoixPanSrvdetaille(Pan_All_Pan_fr_Tab, Pan_TabEmp);
        ChoixPanSrvdetaille(panServices, Pan_EditOrd);

        PersonRemplissage.FillChoiceDestinataire(Distinataire1, 1, 2);
        PersonRemplissage.RemplirCombobox(TaskMission1, "Tasktype", "DescriptionTask_AR", 's');
        PersonRemplissage.RemplirCombobox(MoyenTrsp_Miss1, "Moyen_Transport", "Nom_Voiture", 's');

        SpinnerDateModel mdl = new SpinnerDateModel(new Date(2019, 12, 21, 06, 00), null, null, Calendar.HOUR_OF_DAY);
        Heur_Go3.setModel(mdl);

        JSpinner.DateEditor de = new JSpinner.DateEditor(Heur_Go3, "HH:mm");
        Heur_Go3.setEditor(de);
        Heur_Go3.setFont(new java.awt.Font("Times New Roman", 1, 14));
        InitialiseDate(DateGo1);
        Num_OrderMission.setText("" + (ordission_obj.GetLastNumOrdMission() + 1));

        ordission_obj.FillOrdMissionNoProcess(Table_OrdMissionEdit, 1, 2);

        TitledBorder title;
        SideInterne_Update();

    }//GEN-LAST:event_jLabel39MouseClicked
    int xx, yy;
    private void BarMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarMenuMousePressed
//        this.setOpacity((float) 0.7);
//        xx = evt.getX();
//        yy = evt.getY();
    }//GEN-LAST:event_BarMenuMousePressed

    private void BarMenuMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarMenuMouseDragged
// int x=evt.getXOnScreen();
//        int y=evt.getYOnScreen();
//        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getXOnScreen()"+x);
//        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getYOnScreen()"+y);
//        this.setLocation(xx-x, yy-y);

        // TODO add your handling code here:
/*       int x = evt.getXOnScreen();
        
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xx, y - yy);
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getXOnScreen()"+x);
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getYOnScreen()"+y);
        //this.setLocation(x-xx, y-xy);
        System.out.println("********************************************************************");
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getXOnScreen()"+x);
        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getYOnScreen()"+y);*/

    }//GEN-LAST:event_BarMenuMouseDragged

    private void BarMenuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarMenuMouseReleased
        // TODO add your handling code here:
        this.setOpacity((float) 1.0);
    }//GEN-LAST:event_BarMenuMouseReleased

    private void TxtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSearchActionPerformed

    private void TxtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtSearchFocusGained
        if (TxtSearch.getText().equals("أدخل كلمة البحث")) {
            TxtSearch.setText("");
        }
        TxtSearch.setForeground(Color.black);
    }//GEN-LAST:event_TxtSearchFocusGained

    private void TxtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSearchKeyTyped
        //  FilterEmployer(TxtSearch.getText(), Tab_InfoEmp, (DefaultTableModel) Tab_InfoEmp.getModel());
    }//GEN-LAST:event_TxtSearchKeyTyped

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        ChoiceTask = 1;
        SideInterne_Update();

        ChoixPanSrvdetaille(Pan_All_Pan_fr_Tab, Pan_TabEmp);
        ChoixPanSrvdetaille(panServices, Pan_Frm_GetOrdEmpl);

        /*Pan_All_Pan_fr_Tab
        Pan_TabEmp.setVisible(true);
        Pan_Sh_TabOrdMission.setVisible(false);
        Pan_Initialiser.setVisible(false);*/
 /*        
        Pan_Frm_GetOrdEmpl.setVisible(true);
        Pan_Employer.setVisible(false);*/
    }//GEN-LAST:event_jLabel40MouseClicked

    private void GstEmplMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GstEmplMouseClicked
        ChoiceTask = 2;
        SideInterne_Update();
        /* int posicion = jButton2.getX();
        if(posicion > 10)
        {
            Animacion.Animacion.mover_izquierda(290, 5, 4, 4, jButton2);
            Animacion.Animacion.mover_izquierda(10, -280, 4, 4, Pan_Menu);
        }
    else{
            Animacion.Animacion.mover_derecha(10, 290, 0, 0, jButton2);
            Animacion.Animacion.mover_derecha(-280, 10, 0, 0, Pan_Menu);
        }*/

 /*
        Pan_Employer.setVisible(true);
        Pan_Frm_GetOrdEmpl.setVisible(false);
        Pan_Acceul.setVisible(true);
        PersonRemplissage=new Employeur();
        PersonRemplissage.RemplirCombobox(Reg_CombGrade1, "Grade", "Desc_Grade");
        PersonRemplissage.RemplirCombobox(choice1, "Grade", "Desc_Grade");  //Last Coding .....*/
    }//GEN-LAST:event_GstEmplMouseClicked

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        //jLabel18.setBackground(Color.red);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconsCalcBlack.png")));
        jLabel41.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed

        this.setOpacity((float) 0.7);
        xx = evt.getX();
        yy = evt.getY();
    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - yy);

    }//GEN-LAST:event_jPanel3MouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:   

    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
//           int x=evt.getXOnScreen();
//        int y=evt.getYOnScreen();
//        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getXOnScreen()"+x);
//        System.out.println("gestion_ord_mission.Home.jPanel6MouseDragged() getYOnScreen()"+y);
//        this.setLocation(x-xx, y-yy);
    }//GEN-LAST:event_formMouseDragged

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iconsCalcTran.png")));
        jLabel41.setBackground(new Color(255, 255, 255));

    }//GEN-LAST:event_jLabel18MouseExited

    private void jLabel57MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseEntered

        jLabel56.setBackground(new Color(145, 142, 165));
    }//GEN-LAST:event_jLabel57MouseEntered

    private void jLabel57MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseExited
        jLabel56.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jLabel57MouseExited

    private void jLabel59MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel59MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel59MouseEntered

    private void jLabel59MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel59MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel59MouseExited

    private void jLabel61MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseEntered
        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/configbl.png")));
        jLabel60.setBackground(new java.awt.Color(153, 153, 153));

        //jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/configbl.png")));
        //configbl
    }//GEN-LAST:event_jLabel61MouseEntered

    private void jLabel61MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseExited
        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ConfigWht.png")));
        jLabel60.setBackground(new java.awt.Color(255, 255, 255));//ConfigWht
    }//GEN-LAST:event_jLabel61MouseExited

    private void New_MissionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_New_MissionMouseEntered
//        New_Mission.setBorder( BorderFactory.createLineBorder(new java.awt.Color(0,102,255),2));
//New_Mission.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/createdOrdFill.png")));

    }//GEN-LAST:event_New_MissionMouseEntered

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        panelCause.setVisible(true);
    }//GEN-LAST:event_jLabel48MouseClicked

    private void ExitClsCauseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitClsCauseMouseClicked
        panelCause.setVisible(false);
    }//GEN-LAST:event_ExitClsCauseMouseClicked


    private void jLabel61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseClicked
        ChoixPanSrvdetaille(panServices, Pan_Employer);
        /*Pan_Employer.setVisible(true);
        Pan_Frm_GetOrdEmpl.setVisible(false);
        
        Pan_Acceul.setVisible(true);*/
        UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.getDefaults().put("TabbedPane.tabAreaInsets", new Insets(0, 0, 0, 0));
        UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);

        PersonRemplissage = new Employeur();
        PersonRemplissage.RemplirCombobox(Reg_CombGrade1, "Grade", "Desc_Grade");
        PersonRemplissage.RemplirCombobox(ChoiceGrd, "Grade", "Desc_Grade", 's');
        PersonRemplissage.RemplirCombobox(Function_Choice, "Fonction", "Nm_FonctionAR", 's');

        helper.Fill_repat_decocher(1, 2, 10, "<=", Tab_Rep_Dec_sup_50_inf_10);
        helper.Fill_repat_decocher(1, 2, 10, ">", Tab_Rep_Dec_sup_50_sup_10);
        helper.FillTab_Commune(TablCommune);
        //TabbedPane.addTab("<html><h3 style='padding:20px;color:red;text-align:center;border-color: green;'><i>TEST</i></h3></html>", new JPanel());

        //TabbedPane.setForegroundAt(0, Color.RED);
        ChoiceTask = 5;
    }//GEN-LAST:event_jLabel61MouseClicked

    private void Add_Mission_LabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_Mission_LabMouseEntered
        Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list.png")));
    }//GEN-LAST:event_Add_Mission_LabMouseEntered

    private void Add_Mission_LabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_Mission_LabMouseExited
        if (valClickAdd == 0) {  //this  task already add stayed blck icon   
            Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list.png")));
        } else {
            Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
        }

    }//GEN-LAST:event_Add_Mission_LabMouseExited

    private void Clear_LabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Clear_LabMouseEntered
        Clear_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close-window-filled.png")));
    }//GEN-LAST:event_Clear_LabMouseEntered

    private void Clear_LabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Clear_LabMouseExited
        Clear_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close-window.png"))); // NOI18N
    }//GEN-LAST:event_Clear_LabMouseExited

    private void jLabel39MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseEntered
        jPanel1.setBackground(new Color(85, 65, 118));
        //jLabel55.setOpaque(true);
        jLabel55.setBackground(new Color(85, 150, 200));
    }//GEN-LAST:event_jLabel39MouseEntered

    private void jLabel39MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseExited
        // TODO add your handling code here:
        jPanel1.setBackground(new Color(51, 33, 51));
        jLabel55.setBackground(new Color(51, 33, 51));
    }//GEN-LAST:event_jLabel39MouseExited

    public void GetImageIconApp() {

        //setIconImage(new ImageIcon("‪C:\\Users\\horizon\\Desktop\\téléchargement - Copie.png").getImage());
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/growth.png")));
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage("growth.png"));
//this.setIconImage(new ImageIcon(getClass().getResource("growth.png")).getImage());
    }

    private void jLabel40MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseEntered
        jPanel2.setBackground(new Color(85, 65, 118));
        jLabel62.setBackground(new Color(85, 150, 200));
    }//GEN-LAST:event_jLabel40MouseEntered

    private void jLabel40MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseExited
        jPanel2.setBackground(new Color(51, 33, 51));
        //jLabel55.setOpaque(true);
        jLabel62.setBackground(new Color(51, 33, 51));
    }//GEN-LAST:event_jLabel40MouseExited

    private void GstEmplMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GstEmplMouseEntered
        jPanel4.setBackground(new Color(85, 65, 118));
        jLabel63.setBackground(new Color(85, 150, 200));
    }//GEN-LAST:event_GstEmplMouseEntered

    private void GstEmplMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GstEmplMouseExited
        jPanel4.setBackground(new Color(51, 33, 51));
        jLabel63.setBackground(new Color(51, 33, 51));
    }//GEN-LAST:event_GstEmplMouseExited

    public void InitialisePanTransport() {
        txtNbrKlm.setText("");
        txtPrice.setText("");
        Car_Travel.setSelectedIndex(0);

    }

    int ValRadioChoice = 0;
    int valInitialseTravel = 0;//no repeated the initilaise  
    int NbrKmt = 0;
    float Price = 0;

    public void CalculValueOfTravel() {
        if (txtNbrKlm.getText().equals("") || txtPrice.getText().equals("")) {
            if (txtNbrKlm.getText().equals("") && txtPrice.getText().equals("")) {
                NbrKmt = 0;
                Price = 0;
            } else if (txtPrice.getText().equals("") && !txtNbrKlm.getText().equals("")) {
                NbrKmt = Integer.parseInt(txtNbrKlm.getText());
                Price = 0;
            } else if (!txtPrice.getText().equals("") && txtNbrKlm.getText().equals("")) {
                Price = Integer.parseInt(txtPrice.getText());
                NbrKmt = 0;
            }
        } else {
            NbrKmt = Integer.parseInt(txtNbrKlm.getText());
            Price = Integer.parseInt(txtPrice.getText());
        }
        Remplir_Info_obj.SumKlmAnsianValueAddNew(ValRadioChoice, NbrKmt, Price);
        //valInitialseTravel=1;

    }
    int XpanSld = 0;
    private void RadioQurtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioQurtActionPerformed
        if (RadioQurt.isSelected()) {
            ReductionValue = 0;
            MaximaizeMinimize(0);
            //Add_Mission_Lab.setVisible(false);

        }
    }//GEN-LAST:event_RadioQurtActionPerformed

    private void rdiCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdiCarActionPerformed
        if (rdiCar.isSelected()) {
            ValRadioChoice = 2;
        }
    }//GEN-LAST:event_rdiCarActionPerformed

    private void rdiTrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdiTrainActionPerformed
        if (rdiTrain.isSelected()) {
            ValRadioChoice = 1;
        }
    }//GEN-LAST:event_rdiTrainActionPerformed

    private void RdiPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdiPlanActionPerformed
        if (RdiPlan.isSelected()) {
            ValRadioChoice = 3;
        }
        //JOptionPane.showMessageDialog(null, "The RadioButton Is Selected and "+ValRadioChoice);
    }//GEN-LAST:event_RdiPlanActionPerformed

    private void jLabel59MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel59MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel59MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked

    }//GEN-LAST:event_jLabel18MouseClicked

    private void RadioFullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioFullActionPerformed
        if (RadioFull.isSelected()) {
            ReductionValue = 1;      // 1 for 100% 0 for 25% 

            MaximaizeMinimize(0);
            Add_Mission_Lab.setVisible(true);
        }
    }//GEN-LAST:event_RadioFullActionPerformed

    private void Valid_LabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Valid_LabMouseExited

        if (valClickAdd == 1) {
            Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/AddFill.png")));
        } else {
            Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));
        }
    }//GEN-LAST:event_Valid_LabMouseExited

    private void New_MissionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_New_MissionMouseExited
//         New_Mission.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/createdOrdFill.png")));
    }//GEN-LAST:event_New_MissionMouseExited

    int xSizePanChoice = 120;
    int ySizePanChoice = 75;

    public void MaximaizeMinimize(int x) {
        if (x == 0) {
            PanCHoiceRdi.setSize(0, 0);
        } else {
            PanCHoiceRdi.setSize(120, 80);
        }
    }
    String TaskAttach = "";

    public String GetTaskAttach() {
        return TaskAttach;
    }

    private void jLabel46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseClicked
        //MaximaizeMinimize(1);

        TaskAttach = "NewMission";
        if (NumberORdMission == 0) {
            //confirmation=new ComfirmationUp_Sv(this, true);
            confirmation.DisplayMsg("هل تريد كتابة مهمـــة جديدة");
            confirmation.setVisible(true);
            //CreateNewOrdMission();
        } else {
            //confirmation=new ComfirmationUp_Sv(this, true, );
            confirmation.DisplayMsg("هل تريد الغاء المهمات السابقة ");
            confirmation.setVisible(true);

        }


    }//GEN-LAST:event_jLabel46MouseClicked

    private void jLabel46MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseExited
        // MaximaizeMinimize(0);
        //jLabel49.setVisible(false);
    }//GEN-LAST:event_jLabel46MouseExited

    private void jLabel46MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseEntered
        // TODO add your handling code here:
        //  jLabel49.setVisible(true);
    }//GEN-LAST:event_jLabel46MouseEntered

    public void CreateNewOrdMission() {
        jLabel49.setVisible(true);

        if (NumberORdMission != 0) {
            enablePanelInformation(true, jPanel13, 0);
            Remplir_Info_obj.Close_Wrbk();
        }
        NumberORdMission = 0;
        ReservedCaseTravalChoice = 0;
        Remplir_Info_obj.setNum_Line(8);

        InitialiseAllInformationCom();

    }

    public void InitialiseAllInformationCom() {

        FullNam.setText("ادخل اسم الموظف");
        FullNam.setForeground(Color.gray);
        /*DepuisMois.setText("خلال شهر");
        DepuisMois.setForeground(Color.gray);*/
        Grad.setText("ادخل درجة الموظف");
        Grad.setForeground(Color.gray);
        Job.setForeground(Color.gray);
        Job.setText("ادخل الوظيفة");
        Num_Semantique.setText("ادخل الرقم الاستدلالي");
        Num_Semantique.setForeground(Color.gray);
        Num_CCP.setText("ادخل رقم الحساب الجاري");
        Num_CCP.setForeground(Color.gray);
        Residence.setText("ادخل الاقامة الادارية");
        Residence.setForeground(Color.gray);
        Mission_Name.setText("اسم المهمة ");
        Mission_Name.setForeground(Color.gray);
        RemarqueTxt.setText("أضف ملاحظات .....");
        RemarqueTxt.setForeground(Color.gray);
        Add_Mission_Lab.setVisible(true);
        ListDestainataire.setSelectedIndex(0);
        Car_Travel.setSelectedIndex(0);
        jDateChGo1.setDate(null);
        jDateChBack1.setDate(null);
        ChexSud1.setSelected(false);
        ChexNord0.setSelected(false);
        txtPrice.setText("");
        txtNbrKlm.setText("");
        Tab_InfoEmp.getSelectionModel().clearSelection();
        ValNord = 0;
        ValSud = 0;
        ValLastOrientNord100 = -1;
        ValLastOrientSud100 = -1;
        ValLastOrientNord25 = -1;
        ValLastOrientSud25 = -1;
        valClickAdd = 2;
        Add_Mission_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list-emp.png")));
        Valid_Lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addEmp.png")));
    }
    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked

        if (XpanSld == 0) {

            jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/indiqant_ble.png")));

            XpanSld = 1;
            MaximaizeMinimize(1);

        } else {
            MaximaizeMinimize(0);
            jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/indiqant_bleu.png")));
            XpanSld = 0;
        }


    }//GEN-LAST:event_jLabel49MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated
    char CharChoiceMonth = ' ';
    private void jLabel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MouseClicked
        CharChoiceMonth = jLabel50.getText().charAt(0);
        //9JOptionPane.showMessageDialog(null, "The CharChoiceMonth IS"+CharChoiceMonth);
        ChoiceChart = jLabel50.getText();
        //JOptionPane.showMessageDialog(null, CombMonth.getItemCount());

        if (CombMonth.getSelectedIndex() == CombMonth.getItemCount() - 1) {
            CombMonth1.setSelectedIndex(0);
        } else {
            CombMonth1.setSelectedIndex(CombMonth.getSelectedIndex() + 1);
        }

        // CombMonth1.showPopup();
    }//GEN-LAST:event_jLabel50MouseClicked

    private void jLabel50MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MouseEntered
        jLabel50.setOpaque(rootPaneCheckingEnabled);
    }//GEN-LAST:event_jLabel50MouseEntered

    private void CombMonth1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CombMonth1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CombMonth1ActionPerformed

    private void jLabel71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel71MouseClicked

        CharChoiceMonth = jLabel71.getText().charAt(0);

        ChoiceChart = jLabel71.getText();
        CombMonth1.showPopup();
    }//GEN-LAST:event_jLabel71MouseClicked

    private void jLabel72MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel72MouseClicked
        jLabel72.setForeground(Color.green);
        ChoiceChart = "";
        CharChoiceMonth = ' ';
        DepuisMois.setText("");
    }//GEN-LAST:event_jLabel72MouseClicked

    private void jLabel72MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel72MouseExited
        jLabel72.setForeground(Color.black);
    }//GEN-LAST:event_jLabel72MouseExited

    private void jLabel72MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel72MouseEntered
        jLabel72.setForeground(Color.red);
    }//GEN-LAST:event_jLabel72MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        jLabel5.setForeground(Color.black);
    }//GEN-LAST:event_jLabel5MouseExited


    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked

    }//GEN-LAST:event_jLabel34MouseClicked

    private void OtherCarsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtherCarsActionPerformed
        if (OtherCars.isSelected()) {
            ValRadioChoice = 4;
        }
    }//GEN-LAST:event_OtherCarsActionPerformed
    int ReservedCaseTravalChoice = 0;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (txtNbrKlm.getText().equals("") && txtPrice.getText().equals("")) {

            //JOptionPane.showMessageDialog(null, "ادخل عدد الكيلومترات و السعر");
            txtNbrKlm.requestFocus();
        } else if (txtNbrKlm.getText().equals("") || txtPrice.getText().equals("")) {
            String Emptyfield = null;

            if (txtNbrKlm.getText().equals("")) {
                Emptyfield = txtNbrKlm.getName();
                //JOptionPane.showMessageDialog(null, "ادخل عدد الكليمترات");
                txtNbrKlm.requestFocus();
            } else {
                Emptyfield = txtPrice.getName();
                //JOptionPane.showMessageDialog(null, "ادخل السعر");
            }

        } else {

        }

        if (ReservedCaseTravalChoice == 0) {
            Remplir_Info_obj.InitialiseZoneTravelsCars();
            ReservedCaseTravalChoice = 1;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void RemarqueTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RemarqueTxtFocusGained
        RemarqueTxt.setText("");
        RemarqueTxt.setForeground(Color.black);
    }//GEN-LAST:event_RemarqueTxtFocusGained

    private void jDateChGo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChGo1ActionPerformed
        if (jDateChGo1.getDate() != null) {
            jDateChBack1.setDate(jDateChGo1.getDate());
        }

    }//GEN-LAST:event_jDateChGo1ActionPerformed

    private void Reg_CombGrade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reg_CombGrade1ActionPerformed
        /*  if ( !Reg_CombGrade1.getSelectedItem().equals("...اختر الدرجة")) {
            Reg_Jop.setText((String) Reg_CombGrade1.getSelectedItem());
            Reg_CategNum.setText(""+PersonRemplissage.GetCategorie( Reg_Jop.getText()));
        }*/
    }//GEN-LAST:event_Reg_CombGrade1ActionPerformed

    private void Car_Travel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Car_Travel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Car_Travel1ActionPerformed

    private void RemarqueTxt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RemarqueTxt1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_RemarqueTxt1FocusGained

    private void jDateChGo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChGo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChGo3ActionPerformed

    private void ListDestainataireCommuneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListDestainataireCommuneActionPerformed

    }//GEN-LAST:event_ListDestainataireCommuneActionPerformed

    private void Depart1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Depart1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Depart1ActionPerformed

    private void ChexNord1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChexNord1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChexNord1ActionPerformed

    private void ChexSud2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChexSud2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChexSud2ActionPerformed

    private void rdiTrain1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdiTrain1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdiTrain1ActionPerformed

    private void RdiPlan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdiPlan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RdiPlan1ActionPerformed

    private void rdiCar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdiCar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdiCar1ActionPerformed

    private void OtherCars1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtherCars1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OtherCars1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnSvMissTbDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSvMissTbDepActionPerformed
        TaskAttach = "SaveNewOrdMission";
        confirmation.DisplayMsg("هل تريـــد حفظ المهمـــة");
        confirmation.setVisible(true);
    }//GEN-LAST:event_btnSvMissTbDepActionPerformed

    public void Save_OrdMissionPanDeta() {

        try {
            SimpleDateFormat formt = new SimpleDateFormat("dd/MM/yyyy");
            Date dateGo = formt.parse(DateGo.getText());
            Date dateBack = formt.parse(DateBack.getText());
            Calendar calendarGo = Calendar.getInstance();
            Calendar calendarBack = Calendar.getInstance();
            calendarGo.setTime(dateGo);
            calendarGo.set(Calendar.HOUR_OF_DAY, ((Date) Heur_Go2.getValue()).getHours());
            calendarGo.set(Calendar.MINUTE, ((Date) Heur_Go2.getValue()).getMinutes());
            Date dateGo_hour = calendarGo.getTime();
            calendarBack.setTime(dateBack);
            calendarBack.set(Calendar.HOUR_OF_DAY, ((Date) Heur_Back2.getValue()).getHours());
            calendarBack.set(Calendar.MINUTE, ((Date) Heur_Back2.getValue()).getMinutes());
            Date dateBack_hour = calendarBack.getTime();

            ordission_obj = new OrdMission(
                    Integer.parseInt(num_ord.getText()),
                    dateGo,
                    dateGo,
                    dateBack,
                    PersonRemplissage.GetIdEmployer(FirstName.getText(), LastName.getText()),
                    voiture.GetId_Voiture(MoyenTrsp_Miss.getSelectedItem()),
                    task_mission.GetId_Task(TaskMission.getSelectedItem()),
                    false,
                    valprctg,
                    dateGo_hour,
                    dateBack_hour,
                    voiture.GetId_Distinataire(Distinataire.getSelectedItem()),
                    this);//GetId_Distinataire

            ordission_obj.insertOrdMission();
            // ordission_obj.FillTableOrdMission(Table_OrdMission,1,2);//old code 
            ordission_obj.FillTableOrdMission(Table_OrdMission1, 1, 2);
            InitialisePanCalOrdMiss();
            btnSvMissTbDep.setEnabled(false); // disable btn SaveMiss after save The Mission 
        } catch (ParseException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void InitialisePanCalOrdMiss() {
        FirstName.setText("");
        LastName.setText("");
        GradOrdMissionCns.setText("");
        FuncOrdMissCons.setText("");
        ResidentAdm.setText("");
        Distinataire.select(0);
        TaskMission.select(0);
        MoyenTrsp_Miss.select(0);
        num_ord.setText("");
        btnRd100_02.setSelected(true);

    }
    private void BtnRdSup50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRdSup50ActionPerformed
        ChoicePanelCars(jPanel28, SupPan);
    }//GEN-LAST:event_BtnRdSup50ActionPerformed

    private void BtnRdInf50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRdInf50ActionPerformed
        ChoicePanelCars(jPanel28, panInfr);
        helper.FillCommune(ListDestainataireCommune, "Nam_Des", "destination", "ID_TypDis", 0, 2, "اختر البلدية");
        helper.FillCommune(Car_Travel1, "DescriptionTask_AR", "Tasktype", "ID_TypDis", 1, 2, "اختر نوع المهمة");


    }//GEN-LAST:event_BtnRdInf50ActionPerformed

    private void num_ordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num_ordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_num_ordActionPerformed
    public void InitialiseDate(JFormattedTextField DateCrtMission) {
        DateCrtMission.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    }

    public void Centre_Text_Tab(JTable table) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
    }

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked
        Centre_Text_Tab(tab_Grad);
//        Centre_Text_Tab(jTable1); 

//tab_Grad.getTableHeader().setVisible(false); 
//tab_Grad.setTableHeader(null);
        btnSvMissTbDep.setEnabled(false);
        BtnUpdTbDep.setEnabled(false);
        FinishOrdMission.setVisible(false);
//jPanel34.setVisible(false);
//Enable_TabDepens_OrdMission(1);

        enablePanelInformation(false, panDetail_TabDepns, 1);//panBtn_TabDepns
        enablePanelInformation(false, panBtn_TabDepns, 1);
        enablePanelInformation(false, PnFildsToDpnsDetaill, 1);//jPanel34
        enablePanelInformation(false, PnFdDpnsDtl_InsdPanDateHour, 1);

        ValLastOrientNord100 = -1;
        ValLastOrientSud100 = -1;
        ValLastOrientNord25 = -1;
        ValLastOrientSud25 = -1;
        ValSud = 0;
        ValNord = 0;
        //PanOrdMission
        //ChoicePanelCars(jPanel5, PanOrdMission);

        ChoiceTask = 3;
        ChoixPanSrvdetaille(panServices, PanOrdMission);
        ordission_obj.FillTableOrdMission(Table_OrdMission, 1, 2);
        NumItems.setText(Table_OrdMission.getRowCount() + "");
        PersonRemplissage = new Employeur();
        PersonRemplissage.FillChoiceDestinataire(Distinataire, 1, 2);
        PersonRemplissage.RemplirCombobox(TaskMission, "Tasktype", "DescriptionTask_AR", 's');
        PersonRemplissage.RemplirCombobox(MoyenTrsp_Miss, "Moyen_Transport", "Nom_Voiture", 's');
//        InitialiseDate(DateCrtMission);
        InitialiseDate(DateGo);
        InitialiseDate(DateBack);

        ordission_obj.FillOrdMissionNoProcess(Table_OrdMission1, 1, 2);


    }//GEN-LAST:event_jLabel57MouseClicked

    private void sup50km_02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sup50km_02ActionPerformed

        if (sup50km_02.isSelected()) {
            ordission_obj.FillTableOrdMission(Table_OrdMission, 1, 2);
            NumItems.setText(Table_OrdMission.getRowCount() + "");
        }

    }//GEN-LAST:event_sup50km_02ActionPerformed

    private void info50km_02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_info50km_02ActionPerformed
        ordission_obj.FillTableOrdMission(Table_OrdMission, 3, 4);
        NumItems.setText(Table_OrdMission.getRowCount() + "");
    }//GEN-LAST:event_info50km_02ActionPerformed
    int valprctg = 100;
    private void btnRd100_02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRd100_02ActionPerformed
        if (btnRd100_02.isSelected()) {
            valprctg = 100;
        } else {
            valprctg = 25;
        }
    }//GEN-LAST:event_btnRd100_02ActionPerformed

    private void btnRd25_02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRd25_02ActionPerformed
        if (btnRd25_02.isSelected()) {
            valprctg = 25;
        } else {
            valprctg = 100;
        }
    }//GEN-LAST:event_btnRd25_02ActionPerformed
    public void Filter(String Query) {
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>((DefaultTableModel) Table_OrdMission.getModel());
        Table_OrdMission.setRowSorter(rowSorter);
        rowSorter.setRowFilter(RowFilter.regexFilter(Query));
    }


    private void Table_OrdMissionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_OrdMissionMouseClicked

    }//GEN-LAST:event_Table_OrdMissionMouseClicked

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        String Query = jTextField8.getText();
        // Filter(Query);
        FilterEmployer(jTextField8.getText(), Table_OrdMission1, (DefaultTableModel) Table_OrdMission1.getModel());

    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusGained
        jTextField8.setText("");
    }//GEN-LAST:event_jTextField8FocusGained

    private void Table_OrdMission1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_OrdMission1MouseClicked

        BtnUpdTbDep.setEnabled(true);

        //  enablePanelInformation(rootPaneCheckingEnabled, jPanel13, WIDTH);
        //enablePanelInformation(true,jPanel26,1);//jPanel34
        //enablePanelInformation(true,jPanel34,1);      
        ordission_obj.GetAllInformation((int) Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5));
        PersonRemplissage.GetInformationEmployer(ordission_obj.getId_emp());
        num_ord.setText((int) Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
        OrdUpd_Lab.setText((int) Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
        FirstName.setText((String) Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 4));
        LastName.setText((String) Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 3));
        GradOrdMissionCns.setText(PersonRemplissage.getGrad_Emp());
        FuncOrdMissCons.setText(PersonRemplissage.getFun_Emp());
        ResidentAdm.setText(PersonRemplissage.getResidance_Emp());
        Distinataire.select(helper.GetDistinataire(ordission_obj.getId_dest()));
        TaskMission.select(new Task_Mission().Get_Task(ordission_obj.getId_task()));

        if (ordission_obj.getPrctge() == 100) {
            btnRd100_02.setSelected(true);
            btnRd25_02.setSelected(false);
        } else {
            btnRd25_02.setSelected(true);
            btnRd100_02.setSelected(false);
        }

        MoyenTrsp_Miss.select(new Voiture().getNameMoyTrans(ordission_obj.getID_Voiture()));
        DateGo.setText(formatDate.format(ordission_obj.getDateGo()));

        jLabel81.setText("" + ordission_obj.getId_emp());

        Date Hour_Go_OrdMis = ordission_obj.getHeurDepart();
        Date Hour_Back_OrdMis = ordission_obj.getHeurRetour();

        SpinnerDateModel mdl = new SpinnerDateModel(new Date(2019, 12, 21, Hour_Go_OrdMis.getHours(), Hour_Go_OrdMis.getMinutes()), null, null, Calendar.HOUR_OF_DAY);
        Heur_Go2.setModel(mdl);

        JSpinner.DateEditor de = new JSpinner.DateEditor(Heur_Go2, "HH:mm");
        Heur_Go2.setEditor(de);

        if (Hour_Back_OrdMis != null) {

            DateBack.setText(formatDate.format(ordission_obj.getDateBack()));
            mdl = new SpinnerDateModel(new Date(2019, 12, 21, Hour_Back_OrdMis.getHours(), Hour_Back_OrdMis.getMinutes()), null, null, Calendar.HOUR_OF_DAY);
            Heur_Back2.setModel(mdl);

            de = new JSpinner.DateEditor(Heur_Back2, "HH:mm");
            Heur_Back2.setEditor(de);

            PnFdDpnsDtl_InsdPanDateHour.setVisible(true);

//        JOptionPane.showMessageDialog(null, "Table Click"+FinishOrdMission.getLocation().getX() +" "+  FinishOrdMission.getLocation().getY());
//        JOptionPane.showMessageDialog(null, "Event  Click"+evt.getX() +" "+  evt.getY());
//         JOptionPane.showMessageDialog(null, "Event  Click"+FinishOrdMission.getX() +" "+  FinishOrdMission.getY());
//         
//          JOptionPane.showMessageDialog(null, "Event  Click"+evt.getXOnScreen() +" "+  evt.getYOnScreen());
            if (FinishOrdMission.isVisible()) {
                FinishOrdMission.setVisible(false);
            }
            int NumOrdMission = (int) Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5);
            ordission_obj.Calcule_Price_OrdMission(NumOrdMission);
            NbrRepLab.setText(ordission_obj.GetNbrRepat() + "");
            NbrDecLab.setText(ordission_obj.GetNbrDecoche() + "");
            jLabel124.setText(ordission_obj.getPrctge() + "");
            NbrRepLabPrc.setText(ordission_obj.getPrice_Repat() + "");
            NbrDecLabPrc.setText(ordission_obj.getPrice_Decocher() + "");
            PrcOrMisLab.setText(ordission_obj.Get_PriceOrd_Mission() + "");
            jButton21.setEnabled(true); //btn add Num OrdmISSION iN jlist
        } else {
            PnFdDpnsDtl_InsdPanDateHour.setVisible(false);
            FinishOrdMission.setVisible(true);
            IntiPriceStatOrd();
            jButton21.setEnabled(false);

        }

//        
//        JOptionPane.showMessageDialog(null, "The Time is :"+formatTime.format(ordission_obj.getHeurDepart()));
//         JOptionPane.showMessageDialog(null, "The Time is :"+formatTime.format(ordission_obj.getHeurRetour()));
        //  Heur_Go2.setValue(formatTime.format(ordission_obj.getHeurDepart()));
        //  Heur_Back2.setValue(formatTime.format(ordission_obj.getHeurRetour()));
        //  new SpinnerDateModel(ordission_obj.getDateGo(), ABORT, Nom, ICONIFIED);
        //Heur_Go2.setValue("08:00");
        /*
        SpinnerDateModel mdl=new SpinnerDateModel(ordission_obj.getDateGo(),null, null, Calendar.HOUR_OF_DAY);
       Heur_Go2.setModel(mdl);
       Heur_Back2.setModel(mdl);
       
        JSpinner.DateEditor de=new JSpinner.DateEditor(Heur_Go2,"HH:mm");
       Heur_Go2.setEditor(de);
       Heur_Back2.setEditor(de); 
         */
    }//GEN-LAST:event_Table_OrdMission1MouseClicked
    public void IntiPriceStatOrd() {
        NbrRepLab.setText("00");
        NbrDecLab.setText("00");
        NbrRepLabPrc.setText("00.00");
        NbrDecLabPrc.setText("00.00");
        PrcOrMisLab.setText("00.00");
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int Zone;

        if (NamCommune.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "تأكد من تعبئة الحقول");
        } else {
            if (BtnRdioinf50.isSelected()) {
                if (BtnRdiNrd.isSelected()) {
                    Zone = 3;
                } else {
                    Zone = 4;
                }
            } else {
                if (BtnRdiNrd.isSelected()) {
                    Zone = 1;
                } else {
                    Zone = 2;
                }
            }
            helper.InsertCommune(NamCommune.getText(), NamCommune.getText(), Zone, 2);
            helper.FillTab_Commune(TablCommune);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jDateChGo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChGo4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChGo4ActionPerformed

    private void ChoiceGrdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChoiceGrdMouseClicked
        //  JOptionPane.showMessageDialog(null  , "The coice is clicked");

        //Reg_Jop.setText(choice1.getSelectedItem());
    }//GEN-LAST:event_ChoiceGrdMouseClicked

    private void TabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabbedPaneMouseClicked

    }//GEN-LAST:event_TabbedPaneMouseClicked

    private void TabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TabbedPaneStateChanged
        PersonRemplissage = new Employeur();

        if (TabbedPane.getSelectedIndex() == 0) {
            //JOptionPane.showMessageDialog(null  , "empl"+jTabbedPane1.getSelectedIndex());

            PersonRemplissage.RemplirCombobox(choice_catg, "Categorie", "Num_Categorie", 'i');
            helper.Fill_Grade_(tab_Grad);

        } else if (TabbedPane.getSelectedIndex() == 1) {
            //JOptionPane.showMessageDialog(null  , "Tab 2"+TabbedPane.getSelectedIndex());
        } else if (TabbedPane.getSelectedIndex() == 2) {
            //JOptionPane.showMessageDialog(null  , "Tab Commune"+TabbedPane.getSelectedIndex());
        } else if (TabbedPane.getSelectedIndex() == 3) {

            FunctionRemplissage.FillTableFunction(tab_Function);
        }
    }//GEN-LAST:event_TabbedPaneStateChanged

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        if (Grade_Empl_txt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "please fill the field");

        } else {

            if (jButton12.getText().equals("حفظ")) {
                helper.Insert_Grad(Grade_Empl_txt.getText(), Grade_Empl_txt.getText(), Integer.valueOf(choice_catg.getSelectedItem()));
                DefaultTableModel dfm = (DefaultTableModel) tab_Grad.getModel();
                //dfm.ad

                Object Tab[] = {Integer.valueOf(choice_catg.getSelectedItem()), Grade_Empl_txt.getText()};

                dfm.addRow(Tab);
                Grade_Empl_txt.setText("");
                choice_catg.select(0);

            } else {

            }

        }

        if (Grade_Empl_txt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "please fill the field");

        } else {

        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        Grade_Empl_txt.setText("");
        choice_catg.select(0);
        Grade_Empl_txt.setEnabled(false);
        choice_catg.setEnabled(false);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        if (tab_Grad.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "الرجاء اختيار الرتبـة");
        } else {

            TaskAttach = "Update_Grade";
            confirmation.DisplayMsg("هل تريد الغاء تعديل الدرجة ");
            confirmation.setVisible(true);

        }
    }//GEN-LAST:event_jButton15ActionPerformed

    public void Update_Comp_Grad() {
        Grade_Empl_txt.setEnabled(true);
        choice_catg.setEnabled(true);
        jButton12.setText("حفظ التعديل");

    }

    public void update_Grade(int id_grade, String Desc_Grade, String Detaiil_Grade, int ID_Categorie) {
        String QueryUpd = "UPDATE Grade SET Desc_Grade=?,Detaiil_Grade=?,ID_Categorie=? WHERE ID_Grade=" + id_grade;

        PreparedStatement prs = null;

        Obj_Cnx.connectSqlServer();

        try {
            prs.setInt(1, xx);

        } catch (Exception e) {
        }

    }

    private void tab_GradMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_GradMouseClicked
        int SelectRowGrade = tab_Grad.getSelectedRow();

        JOptionPane.showMessageDialog(null, "RowSelect in tab Grade" + SelectRowGrade);

        Grade_Empl_txt.setText("" + tab_Grad.getValueAt(SelectRowGrade, 1));
        choice_catg.select("" + tab_Grad.getValueAt(SelectRowGrade, 0));

    }//GEN-LAST:event_tab_GradMouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if (tab_Grad.getSelectedRow() != -1) {
            tab_Grad.getSelectionModel().clearSelection();
        }

        Grade_Empl_txt.setText("");
        choice_catg.select(0);
        Grade_Empl_txt.setEnabled(true);
        choice_catg.setEnabled(true);

    }//GEN-LAST:event_jButton16ActionPerformed

    public void Delete_Grade() {

        helper.Delete_Grade((String) tab_Grad.getValueAt(tab_Grad.getSelectedRow(), 1));
        helper.Fill_Grade_(tab_Grad);
        Grade_Empl_txt.setText("");
        choice_catg.select(0);
    }

    public void Update_Employer() {

        String FunString = "";
        if (checkFunct.isSelected()) {
            FunString = Function_Choice.getSelectedItem();
        } else {
            FunString = ChoiceGrd.getSelectedItem();
        }

        int x = (int) Tab_InfoEmp.getValueAt(Tab_InfoEmp.getSelectedRow(), 3);
        PersonRemplissage = new Employeur(0, Reg_Name.getText(), Reg_LastName.getText(), ChoiceGrd.getSelectedItem(),
                helper.GetID_Grade(ChoiceGrd.getSelectedItem()), FunString, Reg_CCP.getText(),
                Reg_NumSemt.getText(), Reg_Residence.getText());
        PersonRemplissage.UpdateEmployer(x);
    }

    public void Insert_Employer() {

        String FnctEmp = "";

        if (checkFunct.isSelected()) {
            FnctEmp = Function_Choice.getSelectedItem();
        } else {
            FnctEmp = ChoiceGrd.getSelectedItem();
        }

        PersonRemplissage = new Employeur(0, Reg_Name.getText(), Reg_LastName.getText(),
                (String) ChoiceGrd.getSelectedItem(), helper.GetID_Grade(ChoiceGrd.getSelectedItem()),
                /* Reg_Jop.getText()*/ FnctEmp, Reg_CCP.getText(), Reg_NumSemt.getText(), Reg_Residence.getText());
        PersonRemplissage.Add_Employeur();

    }
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        if (tab_Grad.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "الرجاء اختيار الرتبة");
        } else {
            TaskAttach = "Delete_Grade";
            confirmation.DisplayMsg("هل انت متأكد من عملية الحذف");
            confirmation.setVisible(true);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        helper.Fill_repat_decocher(3, 4, 10, "<=", Tab_Rep_Dec_sup_50_inf_10);
        helper.Fill_repat_decocher(3, 4, 10, ">", Tab_Rep_Dec_sup_50_sup_10);

    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        helper.Fill_repat_decocher(1, 2, 10, "<=", Tab_Rep_Dec_sup_50_inf_10);
        helper.Fill_repat_decocher(1, 2, 10, ">", Tab_Rep_Dec_sup_50_sup_10);
    }//GEN-LAST:event_jRadioButton3ActionPerformed
    Vector<String> vectOrd = new Vector<String>();
    DefaultListModel<String> dflist = new DefaultListModel<>();
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed

        ValNord = 0;
        ValSud = 0;
        int ValNordZone = ordission_obj.GetZONE_Stat((int) Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5));
        ReductionValue = ordission_obj.Get_Porcentage((int) Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5));

        if (ValNordZone == 1) {
            ValNord = 1;
        } else {
            ValSud = 1;
        }

        // JOptionPane.showMessageDialog(null , "Nord : "+ValNord+" AND ValSud : "+ValSud+" ReductionValue "+ReductionValue);
        if ((ValLastOrientNord100 == -1) && (ValLastOrientSud100 == -1) && (ValLastOrientNord25 == -1) && (ValLastOrientSud25 == -1)) {
            vectOrd.add(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
            dflist.addElement(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
            ListOrdMission.setModel(dflist);

            //ListOrdMission.setListData(vectOrd);
            //JOptionPane.showMessageDialog(null,"The Add is Executed");
            switch (ReductionValue) {
                case 0:
                    if (ValNord == 1) {
                        ValLastOrientNord25 = 1;
                    } else {
                        ValLastOrientSud25 = 1;
                    }
                    break;
                case 1:
                    if (ValNord == 1) {
                        ValLastOrientNord100 = 1;
                    } else {
                        ValLastOrientSud100 = 1;
                    }
                    break;
            }

        } else {
            if (ReductionValue == 1 && ValNord == 1) {
                if (ValLastOrientNord25 == 1) {
                    JOptionPane.showMessageDialog(null, "لا يمكنك اضافة المهمة في الشمال و بنسبة  100%");
                } else {
                    ValLastOrientNord100 = 1;
                    vectOrd.add(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                    //dflist.addElement(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5)+"");

                    dflist.addElement(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                    ListOrdMission.setModel(dflist);

                    //ListOrdMission.setListData(vectOrd);
                    JOptionPane.showMessageDialog(null, "The Add is Executed");
                }

            } else if (ReductionValue == 1 && ValSud == 1) {
                //if (ValLastOrientSud100==1) {

                if (ValLastOrientSud25 == 1) {
                    JOptionPane.showMessageDialog(null, "لا يمكنك اضافةمهمة جنوب 100%");
                } else {
                    ValLastOrientSud100 = 1;
                    vectOrd.add(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                    dflist.addElement(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                    ListOrdMission.setModel(dflist);
                    //ListOrdMission.setListData(vectOrd); 
                    //JOptionPane.showMessageDialog(null,"The Add is Executed");
                }
            } else if (ReductionValue == 0 && ValNord == 1) {
                if (ValLastOrientNord100 == 1) {
                    JOptionPane.showMessageDialog(null, "لا يمكنك اضافة مهمة  شمال بنسبة 25%");

                } else {
                    ValLastOrientNord25 = 1;
                    vectOrd.add(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                    dflist.addElement(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                    ListOrdMission.setModel(dflist);
                    //ListOrdMission.setListData(vectOrd);
                    //   JOptionPane.showMessageDialog(null,"The Add is Executed");
                }
            } else if (ReductionValue == 0 && ValSud == 1) {
                if (ValLastOrientSud100 == 1) {
                    JOptionPane.showMessageDialog(null, "لا يمكنك اضافة مهمة جنوب 25%");
                } else {
                    ValLastOrientSud25 = 1;
                    vectOrd.add(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                    dflist.addElement(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                    ListOrdMission.setModel(dflist);
                    //ListOrdMission.setListData(vectOrd);
                    //  JOptionPane.showMessageDialog(null,"The Add is Executed");
                }
            }
        }


    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

        int Nord = 0;
        int Sud = 0;

        Calcule_val cl = null;
        int nbrrepat = 0, nbrdecoch = 0;

        ordission_obj = new OrdMission();
        Employeur employer_obj = new Employeur();
        cl = new Calcule_val();
        /**
         * **********************************************************
         */
        int i = 0;

        employer_obj = new Employeur();
        employer_obj.GetInformationEmployer(Integer.parseInt(jLabel81.getText()));
        employer_obj.setId_Emp(Integer.parseInt(jLabel81.getText()));
        //employer_obj.setId_Emp(ordission_obj.getId_emp());
        Person = employer_obj;

        System.out.println("new Info_Ord");

        Remplir_Info_obj.Remplir_Sheet1(employer_obj.getFirst_Name_Emp() + " " + employer_obj.getLast_Name_Emp(), "10/10/2019", employer_obj.getGrad_Emp(), employer_obj.getFun_Emp(),
                employer_obj.getSem_Num_Emp(), employer_obj.getResidance_Emp(),
                employer_obj.getCCP_Num_Emp(), "", "", "", "", new Date(), "", "", 0, 0);
        System.out.println("Remplir_Sheet1");

        JOptionPane.showMessageDialog(null, employer_obj.getFirst_Name_Emp() + " " + employer_obj.getLast_Name_Emp() + " " + employer_obj.getGrad_Emp() + " " + employer_obj.getFun_Emp());
        JOptionPane.showMessageDialog(null, employer_obj.getSem_Num_Emp() + " " + employer_obj.getResidance_Emp() + ""
                + employer_obj.getCCP_Num_Emp());
        //Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName());
        Remplir_Info_obj.Write_In_WorkBook("FileCalcule");
        System.out.println("Remplir_Info_obj.Write_In_WorkBook");

        for (String string : vectOrd) { //for loop to calculate nbr eat

            ordission_obj.GetAllInformation(Integer.parseInt(string));

            /**
             * ***************-----------------------------------------------*******
             */
            System.out.println("View.Home.jButton18ActionPerformed()");
            //JOptionPane.showMessageDialog(null, "The Value is "+string);
            try {
                cl.calcule_eating_dortoire(formatDate.format(ordission_obj.getDateGo()), formatDate.format(ordission_obj.getDateBack()),
                        formatTime.format(ordission_obj.getHeurDepart()), formatTime.format(ordission_obj.getHeurRetour()));
                nbrrepat = cl.getNbreRepat();
                nbrdecoch = cl.getNbreDortoire();

            } catch (ParseException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, FirstName.getText() + "" + FirstName.getText() + " " + employer_obj.getGrad_Emp() + " " + employer_obj.getFun_Emp() + " " + employer_obj.getSem_Num_Emp());

            JOptionPane.showMessageDialog(null, new Task_Mission().Get_Task(ordission_obj.getId_task()) + " " + employer_obj.getResidance_Emp() + " " + helper.GetDistinataire(ordission_obj.getId_dest()));

            JOptionPane.showMessageDialog(null, new Voiture().getNameMoyTrans(ordission_obj.getID_Voiture()) + " " + formatDate.format(ordission_obj.getDateGo()) + " " + formatTime.format(ordission_obj.getHeurDepart()));

            JOptionPane.showMessageDialog(null, "" + formatDate.format(ordission_obj.getDateBack()) + " " + formatTime.format(ordission_obj.getHeurRetour()) + " " + nbrrepat + " " + nbrdecoch + " " + 1 + " " + ordission_obj.getNum_OrdMission() + "");

            Employeur_Info = new Info_Ord(FirstName.getText(), FirstName.getText(),
                    "10/10/2009", employer_obj.getGrad_Emp(), employer_obj.getFun_Emp(), employer_obj.getSem_Num_Emp(), employer_obj.getResidance_Emp(),
                    employer_obj.getCCP_Num_Emp(), new Task_Mission().Get_Task(ordission_obj.getId_task()), employer_obj.getResidance_Emp(), helper.GetDistinataire(ordission_obj.getId_dest()),
                    new Voiture().getNameMoyTrans(ordission_obj.getID_Voiture()), formatDate.format(ordission_obj.getDateGo()), formatTime.format(ordission_obj.getHeurDepart()),
                    formatDate.format(ordission_obj.getDateBack()), formatTime.format(ordission_obj.getHeurRetour()), nbrrepat, nbrdecoch, ordission_obj.ID_Direct_Zone(ordission_obj.getId_dest()), ordission_obj.getNum_OrdMission() + "");

//            JOptionPane.showMessageDialog(null,Nam_Emp_PanClc.getText()+""+Last_Nam_Emp_PanClc.getText()+" "+employer_obj.getGrad_Emp()+" "+employer_obj.getFun_Emp()+" "+employer_obj.getSem_Num_Emp());    
//            
//        JOptionPane.showMessageDialog(null,new Task_Mission().Get_Task(ordission_obj.getId_task())+" "+employer_obj.getResidance_Emp()+" "+helper.GetDistinataire(ordission_obj.getId_dest()));    
//            
//        
//        JOptionPane.showMessageDialog(null,new Voiture().getNameMoyTrans(ordission_obj.getID_Voiture())+" "+formatDate.format(ordission_obj.getDateGo())+" "+ formatTime.format(ordission_obj.getHeurDepart()));    
//        
//        JOptionPane.showMessageDialog(null,""+formatDate.format(ordission_obj.getDateBack())+" "+formatTime.format(ordission_obj.getHeurRetour())+" "+nbrrepat+" "+nbrdecoch+" "+1+" "+ordission_obj.getNum_OrdMission()+"");
//        
            Remplir_Info_obj.Inisialise_Sheet2();
            Remplir_Info_obj.Insialise_ReferenceSh2();
            System.out.println("Remplir_Info_obj.Inisialise_Sheet2()");

            Remplir_Info_obj.Remplir_Sheet2(Employeur_Info.getCauseTravel(), Employeur_Info.getDepart_Demarer(),
                    Employeur_Info.getDestinataire(), Employeur_Info.getDateGo(), Employeur_Info.getHeur_Go(),
                    Employeur_Info.getDateBack(), Employeur_Info.getHeur_Back(),
                    Employeur_Info.getMoyenTrnsport(), 0, 0, 0, 0,
                    Employeur_Info.getCompensationEat(), Employeur_Info.getCompensationEat(), Employeur_Info.getCompensationDrt(), Employeur_Info.getCompensationDrt(), Employeur_Info.getRemarque(),
                    Employeur_Info.getOrientation());
            System.out.println("Remplir_Info_obj.Remplir_Sheet2");

            // Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName());
            Remplir_Info_obj.Write_In_WorkBook("FileCalcule");

            Remplir_Info_obj.setNum_Line((Remplir_Info_obj.GetNum_Line() + 1));
        }

        /**
         * ****************************************************
         */
        NumberORdMission = vectOrd.size();

        JOptionPane.showMessageDialog(null, "The Size is NumberORdMission :" + NumberORdMission);
        if (NumberORdMission == 1) {// just one Ord Mission
            Remplir_Info_obj.RemplirSomDrt();// calculate Some Number Repat And Some Number Dortoir All Mission
            //Remplir_Info_obj.SumCompensationTransport();  //Calcule Some Of Price Of Transport 
            if (ReductionValue == 1) {        // if Mission is 100%
                if (ValNord == 1) {    //This for Choice Of Nord 
                    //JOptionPane.showMessageDialog(null, "The Nord Orientation "); 
                    Remplir_Info_obj.GetNbrCompensationNrd(); //for deplace Value of Some to sheet 1 to calculate 
                    GetPriceEatANDDecocher(NumberORdMission);
                } else {

                    Remplir_Info_obj.GetNbrCompensationSudForOneMission(1); //This For Deplace Some Repat And Decocher To Calculate : arg =1: Just One OrdMission in p18 / p19 (Repat Sud in p18 and p19) 
                    GetPriceEatANDDecocherForOneMission(0);//                                                       : arg!=1: for 2 Mission Nord And Sude sud in p20 and p21
                }
                Remplir_Info_obj.SumCompensationToujours(NumberORdMission, 0, 0);  // ce methode is calculate product of prix * number  AND Calcul SUM
                Remplir_Info_obj.TotlaSumBenefit();

                Remplir_Info_obj.SumTransport_and_compensationTtl();
                //Remplir_Info_obj.ChangeThisNumber();
                Remplir_Info_obj.Date_Delivred();

            } else {
                //JOptionPane.showMessageDialog(null, "The ReductionValue is 25%");
                Remplir_Info_obj.RemplirSomDrt();
                if (ValNord == 1) {

                    //JOptionPane.showMessageDialog(null, "The Orientation Is Nord ");
                    Remplir_Info_obj.GetNbrCompensationNrdWithReduction(1);
                    //GetPriceEatANDDecocherCForReduction(1);
                    GetPriceEatANDDecocherCForReduction(1);
                    Remplir_Info_obj.SumCompensationToujoursForReduction();
                } else {
                    //JOptionPane.showMessageDialog(null, "The Orientation Is Sud ");
                    Remplir_Info_obj.GetNbrCompensationNrdWithReduction(0);
                    // GetPriceEatANDDecocherCForReduction(1);
                    GetPriceEatANDDecocherCForReduction(1);
                    Remplir_Info_obj.SumCompensationToujoursForReduction();
                    //Remplir_Info_obj.CalculePrix_25();
                }

                //Remplir_Info_obj.SumCompensationToujours();  // ce methode is calculate product of prix * number  AND Calcul SUM
                //new Code 
                Remplir_Info_obj.InitialiseCellReductionPrice(NumberORdMission, 0, 0);
                Remplir_Info_obj.TotlaSumBenefit();

                Remplir_Info_obj.SumTransport_and_compensationTtl();
                //Remplir_Info_obj.ChangeThisNumber();
                Remplir_Info_obj.Date_Delivred();

            }

        } else {

            /**
             * *****************************************************
             */
            Remplir_Info_obj.RemplirSomDrt();

            if ((ValLastOrientSud100 == 1 || ValLastOrientSud25 == 1) && ValLastOrientNord100 == -1 && ValLastOrientNord25 == -1) {

                JOptionPane.showMessageDialog(null, "ValLastOrientSud100==1||ValLastOrientSud25==1) &&ValLastOrientNord100==-1 && ValLastOrientNord25==-1 ");

                //deplace sud number to first Cellss 
                if (ValLastOrientSud100 == 1) {

                    JOptionPane.showMessageDialog(null, "The ValLastOrientSud100==1 ");
                    Remplir_Info_obj.GetNbrCompensationSudForOneMission(1);
                    GetPriceEatANDDecocherForOneMission(0);
                    //Remplir_Info_obj.SumCompensationToujours(NumberORdMission);  // ce methode is calculate product of prix * number  AND Calcul SUM
                    Remplir_Info_obj.SumCompensationToujours(1, 0, 0); //because just on orientation (1 is condition methode SumCompensationToujours so we dont need N20 and N21)
                    Remplir_Info_obj.TotlaSumBenefit();

                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                } else {    //ValLastOrientSud25==1

                    JOptionPane.showMessageDialog(null, "the ValLastOrientSud25==1 ");

                    Remplir_Info_obj.GetNbrCompensationNrdWithReduction(0);
                    // GetPriceEatANDDecocherCForReduction(1);
                    GetPriceEatANDDecocherForOneMission(0);
                    Remplir_Info_obj.SumCompensationToujoursForReduction();
                    Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), 1, 0);
                    Remplir_Info_obj.TotlaSumBenefit();
                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                }
                /**
                 * ************************************************************************
                 */
            } else if ((ValLastOrientNord100 == 1 || ValLastOrientNord25 == 1) && ValLastOrientSud100 == -1 && ValLastOrientSud25 == -1) {

                JOptionPane.showMessageDialog(null, "ValLastOrientNord100==1||ValLastOrientNord25==1) &&ValLastOrientSud100==-1 && ValLastOrientSud25==-1");

                if (ValLastOrientNord100 == 1) {

                    JOptionPane.showMessageDialog(null, "the  ValLastOrientNord100==1");

                    // Remplir_Info_obj.GetNbrCompensationSudForOneMission(1);
                    Remplir_Info_obj.GetNbrCompensationNrd();
                    GetPriceEatANDDecocherForOneMission(1);
                    //Remplir_Info_obj.SumCompensationToujours(NumberORdMission);  // ce methode is calculate product of prix * number  AND Calcul SUM
                    Remplir_Info_obj.SumCompensationToujours(1, 0, 0); //because just on orientation (1 is condition methode SumCompensationToujours so we dont need N20 and N21)
                    Remplir_Info_obj.TotlaSumBenefit();

                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();

                } else {    //ValLastOrientNord25==1
                    //Remplir_Info_obj.GetNbrCompensationSudForOneMission(1);
                    JOptionPane.showMessageDialog(null, "the  ValLastOrientSud100==1");

                    Remplir_Info_obj.GetNbrCompensationNrd();
                    GetPriceEatANDDecocherForOneMission(1);
                    Remplir_Info_obj.SumCompensationToujoursForReduction();
                    Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), 1, 0);
                    Remplir_Info_obj.TotlaSumBenefit();
                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                }
            } else if (ValLastOrientNord100 == 1 && ValLastOrientSud25 == 1) {  //nord 100% and sud 25%

                JOptionPane.showMessageDialog(null, "the ValLastOrientNord100==1 && ValLastOrientSud25==1 ");

                Remplir_Info_obj.GetNbrCompensationNrd();
                Remplir_Info_obj.GetNbrCompensationSud();
                GetPriceEatANDDecocher(vectOrd.size());
                Remplir_Info_obj.SumCompensationToujours(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                Remplir_Info_obj.TotlaSumBenefit();
                Remplir_Info_obj.SumTransport_and_compensationTtl();

                //Remplir_Info_obj.ChangeThisNumber();
                Remplir_Info_obj.Date_Delivred();
            } else if (ValLastOrientSud100 == 1 && ValLastOrientNord25 == 1) { //nord 25% and Sud 100%

                JOptionPane.showMessageDialog(null, "the ValLastOrientSud100==1 && ValLastOrientNord25==1 ");

                Remplir_Info_obj.GetNbrCompensationNrd();
                Remplir_Info_obj.GetNbrCompensationSud();
                GetPriceEatANDDecocher(vectOrd.size());
                Remplir_Info_obj.SumCompensationToujours(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);

                Remplir_Info_obj.TotlaSumBenefit();
                Remplir_Info_obj.SumTransport_and_compensationTtl();

                //Remplir_Info_obj.ChangeThisNumber();
                Remplir_Info_obj.Date_Delivred();
            } else if (ValLastOrientSud25 == 1 && ValLastOrientNord25 == 1) {    //two oprientation different and 25%

                JOptionPane.showMessageDialog(null, "ValLastOrientSud25==1 && ValLastOrientNord25==1");

                Remplir_Info_obj.GetNbrCompensationNrd();
                Remplir_Info_obj.GetNbrCompensationSud();
                GetPriceEatANDDecocher(vectOrd.size());
                Remplir_Info_obj.SumCompensationToujours(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);

                Remplir_Info_obj.TotlaSumBenefit();
                Remplir_Info_obj.SumTransport_and_compensationTtl();
                //Remplir_Info_obj.ChangeThisNumber();
                Remplir_Info_obj.Date_Delivred();
            } else {   //Remplir_Info_obj.Inisialise_Sheet1();
                JOptionPane.showMessageDialog(null, "else ********************** else");
                Remplir_Info_obj.GetNbrCompensationNrd();
                Remplir_Info_obj.GetNbrCompensationSud();
                GetPriceEatANDDecocher(vectOrd.size());
                Remplir_Info_obj.SumCompensationToujours(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                Remplir_Info_obj.TotlaSumBenefit();
                Remplir_Info_obj.SumTransport_and_compensationTtl();
                //Remplir_Info_obj.ChangeThisNumber();
                Remplir_Info_obj.Date_Delivred();
            }
        }

        Remplir_Info_obj.Write_In_WorkBook("FileCalcule");
        try {
            Desktop dt = Desktop.getDesktop();
            // dt.open(new File("src\\OurFile\\AppClose.xlsx"));
            dt.open(new File("FileCalcule" + ".xlsx"));
            //  dt.open(new File(""+FullNam.getText()+".xlsx"));

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in Opened The File");
        }

        /**
         * ***********************************************************
         */
        //ancian Code  ordission_obj.GetAllInformation(48);
        /* try {
            
            
           JOptionPane.showMessageDialog(null, "ordission_obj.getDateGo() :"+ordission_obj.getDateGo()+"ordission_obj.getDateBack() "+ordission_obj.getDateBack());
            
       cl.calcule_eating_dortoire(formatDate.format(ordission_obj.getDateGo()),formatDate.format(ordission_obj.getDateBack()), 
                    formatTime.format(ordission_obj.getHeurDepart()),formatTime.format(ordission_obj.getHeurRetour()));
            nbrrepat=cl.getNbreRepat();
            nbrdecoch=cl.getNbreDortoire();
            
            
            
             JOptionPane.showMessageDialog(null, "ordission_obj.getDateGo() :"+formatDate.format(ordission_obj.getDateGo())+"ordission_obj.getDateBack() "+formatDate.format(ordission_obj.getDateBack()));
        } catch (ParseException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
 /*employer_obj=new Employeur();
        employer_obj.GetInformationEmployer(Integer.parseInt(jLabel81.getText()));
        employer_obj.setId_Emp(ordission_obj.getId_emp());
        Person=employer_obj;*/
        //Person.setId_Emp(employer_obj.getId_Emp());
        System.out.println("employer_obj.GetInformationEmployer");

        /*   Info_Ord Employeur_Info=new Info_Ord(Nam_Emp_PanClc.getText(), Last_Nam_Emp_PanClc.getText(),
                "10/10/2009", employer_obj.getGrad_Emp(), employer_obj.getFun_Emp(), employer_obj.getSem_Num_Emp(), employer_obj.getResidance_Emp(),
                employer_obj.getCCP_Num_Emp(),new Task_Mission().Get_Task(ordission_obj.getId_task()) ,employer_obj.getResidance_Emp() , helper.GetDistinataire(ordission_obj.getId_dest()), 
                new Voiture().getNameMoyTrans(ordission_obj.getID_Voiture()),formatDate.format(ordission_obj.getDateGo()), formatTime.format(ordission_obj.getHeurDepart()),
                formatDate.format(ordission_obj.getDateBack()), formatTime.format(ordission_obj.getHeurRetour()), nbrrepat, nbrdecoch, 1, ordission_obj.getNum_OrdMission()+"");   
            
               System.out.println("new Info_Ord"); 
                               
                Remplir_Info_obj.Remplir_Sheet1(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName(),Employeur_Info.getDepuisMois(),Employeur_Info.getGrade(),Employeur_Info.getJob(),Employeur_Info.getSemanticNumero(),Employeur_Info.getManagementResident(),
                Employeur_Info.getCCPN_Num(), "", "", "", "", new Date(), "", "", 0 , 0);
             System.out.println("Remplir_Sheet1"); 
                Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName());
                
                System.out.println("Remplir_Info_obj.Write_In_WorkBook"); */
 /*   
                Remplir_Info_obj.Inisialise_Sheet2();
                Remplir_Info_obj.Insialise_ReferenceSh2();
                 System.out.println("Remplir_Info_obj.Inisialise_Sheet2()");
                
                 
                 
                Remplir_Info_obj.Remplir_Sheet2(Employeur_Info.getCauseTravel(),Employeur_Info.getDepart_Demarer(),
                    Employeur_Info.getDestinataire(),Employeur_Info.getDateGo(),Employeur_Info.getHeur_Go(),
                    Employeur_Info.getDateBack(),Employeur_Info.getHeur_Back(),
                    Employeur_Info.getMoyenTrnsport(),0,0,0,0,
                    Employeur_Info.getCompensationEat(),Employeur_Info.getCompensationEat(),Employeur_Info.getCompensationDrt(),Employeur_Info.getCompensationDrt(),Employeur_Info.getRemarque(),
                    Employeur_Info.getOrientation());
                
                System.out.println("Remplir_Info_obj.Remplir_Sheet2");
             
               Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName());*/
 /*Remplir_Info_obj.RemplirSomDrt();
                Remplir_Info_obj.GetNbrCompensationNrd();
                GetPriceEatANDDecocher(3);
                Remplir_Info_obj.SumCompensationToujours(1,0,0); 
                Remplir_Info_obj.TotlaSumBenefit();
                Remplir_Info_obj.SumTransport_and_compensationTtl();
                Remplir_Info_obj.Date_Delivred();*/
 /* Remplir_Info_obj.RemplirSomDrt();
               if (ReductionValue==100) {
            
            if (ValNord==1) {    //This for Choice Of Nord 
                      //JOptionPane.showMessageDialog(null, "The Nord Orientation "); 
                        Remplir_Info_obj.GetNbrCompensationNrd(); //for deplace Value of Some to sheet 1 to calculate 
                       GetPriceEatANDDecocher(1);
                    }else{    
                     
                      Remplir_Info_obj.GetNbrCompensationSudForOneMission(1); //This For Deplace Some Repat And Decocher To Calculate : arg =1: Just One OrdMission in p18 / p19 (Repat Sud in p18 and p19) 
                      GetPriceEatANDDecocherForOneMission(0);//                                                       : arg!=1: for 2 Mission Nord And Sude sud in p20 and p21
                           }
            Remplir_Info_obj.SumCompensationToujours(1,0,0);  // ce methode is calculate product of prix * number  AND Calcul SUM
            Remplir_Info_obj.TotlaSumBenefit();
            
            Remplir_Info_obj.SumTransport_and_compensationTtl();
            //Remplir_Info_obj.ChangeThisNumber();
            Remplir_Info_obj.Date_Delivred();
               
               
                Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName());
              try{   
          Desktop dt = Desktop.getDesktop();
                   // dt.open(new File("src\\OurFile\\AppClose.xlsx"));
                    dt.open(new File(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName()+".xlsx")); 
                  //  dt.open(new File(""+FullNam.getText()+".xlsx"));
                    
                } catch (IOException ex) {
                   JOptionPane.showMessageDialog(null, "Error in Opened The File");
                }
               
               }else {
               
                Remplir_Info_obj.RemplirSomDrt();
                    if (ValNord==1) {
                       
                        //JOptionPane.showMessageDialog(null, "The Orientation Is Nord ");
                        Remplir_Info_obj.GetNbrCompensationNrdWithReduction(1);
                        //GetPriceEatANDDecocherCForReduction(1);
                        GetPriceEatANDDecocherCForReduction(1);
                        Remplir_Info_obj.SumCompensationToujoursForReduction();
                    }else {
                        //JOptionPane.showMessageDialog(null, "The Orientation Is Sud ");
                        Remplir_Info_obj.GetNbrCompensationNrdWithReduction(0);   
                       // GetPriceEatANDDecocherCForReduction(1);
                       GetPriceEatANDDecocherCForReduction(1);
                       Remplir_Info_obj.SumCompensationToujoursForReduction();
                       //Remplir_Info_obj.CalculePrix_25();
                           }
                    
                    //Remplir_Info_obj.SumCompensationToujours();  // ce methode is calculate product of prix * number  AND Calcul SUM
                      //new Code 
                      Remplir_Info_obj.InitialiseCellReductionPrice(1,0,0);
                    Remplir_Info_obj.TotlaSumBenefit();
                    
                     Remplir_Info_obj.SumTransport_and_compensationTtl();
            //Remplir_Info_obj.ChangeThisNumber();
            Remplir_Info_obj.Date_Delivred();
                
                     try{   
          Desktop dt = Desktop.getDesktop();
                   // dt.open(new File("src\\OurFile\\AppClose.xlsx"));
                    dt.open(new File(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName()+".xlsx")); 
                  //  dt.open(new File(""+FullNam.getText()+".xlsx"));
                    
                } catch (IOException ex) {
                   JOptionPane.showMessageDialog(null, "Error in Opened The File");
                }
               
               }
         */

    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
//( ListOrdMission.getModel()).remove(ListOrdMission.getSelectedIndex());       
        vectOrd.remove(ListOrdMission.getSelectedValue());
        dflist.removeElement(ListOrdMission.getSelectedValue());
        ListOrdMission.setModel(dflist);

        ValLastOrientNord100 = -1;
        ValLastOrientSud100 = -1;
        ValLastOrientNord25 = -1;
        ValLastOrientSud25 = -1;

        for (String NumOrdMission : vectOrd) {

            ValNord = 0;
            ValSud = 0;

            int ValNordZone = ordission_obj.GetZONE_Stat(Integer.parseInt(NumOrdMission));
            ReductionValue = ordission_obj.Get_Porcentage(Integer.parseInt(NumOrdMission));
            if (ValNordZone == 1) {
                ValNord = 1;
            } else {
                ValSud = 1;
            }
            if ((ValLastOrientNord100 == -1) && (ValLastOrientSud100 == -1) && (ValLastOrientNord25 == -1) && (ValLastOrientSud25 == -1)) {

                switch (ReductionValue) {
                    case 0:
                        if (ValNord == 1) {
                            ValLastOrientNord25 = 1;

                        } else {
                            ValLastOrientSud25 = 1;
                        }
                        break;
                    case 1:
                        if (ValNord == 1) {
                            ValLastOrientNord100 = 1;
                        } else {
                            ValLastOrientSud100 = 1;
                        }
                        break;
                }

            } else {
                if (ReductionValue == 1 && ValNord == 1) {
                    if (ValLastOrientNord25 == 1) {
                        JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientNord25=" + ValLastOrientNord25 + " you are Choice Reduction and Nord");
                    } else {
                        ValLastOrientNord100 = 1;
                    }
                } else if (ReductionValue == 1 && ValSud == 1) {
                    if (ValLastOrientSud25 == 1) {
                        JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientSud25=" + ValLastOrientSud25 + " you are Choice Reduction and sud");
                    } else {
                        ValLastOrientSud100 = 1;
                    }
                } else if (ReductionValue == 0 && ValNord == 1) {
                    if (ValLastOrientNord100 == 1) {
                        JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientNord100=" + ValLastOrientNord100 + " you are Choice Reduction and Nord");
                    } else {
                        ValLastOrientNord25 = 1;
                    }
                } else if (ReductionValue == 0 && ValSud == 1) {
                    if (ValLastOrientSud100 == 1) {
                        JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientSud100=" + ValLastOrientSud100 + " you are Choice Reduction and Nord");
                    } else {
                        ValLastOrientSud25 = 1;
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    int ID_Emp_Controle = 0;

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed

        if (Table_OrdMission1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Select Ord Mission please");
        } else {
            ValNord = 0;
            ValSud = 0;
            int ValNordZone = ordission_obj.GetZONE_Stat((int) Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5));
            ReductionValue = ordission_obj.Get_Porcentage((int) Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5));

            if (ValNordZone == 1) {
                ValNord = 1;
            } else {
                ValSud = 1;
            }

            JOptionPane.showMessageDialog(null, "Nord : " + ValNord + " AND ValSud : " + ValSud + " ReductionValue " + ReductionValue);
            if ((ValLastOrientNord100 == -1) && (ValLastOrientSud100 == -1) && (ValLastOrientNord25 == -1) && (ValLastOrientSud25 == -1)) {
                vectOrd.add(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                dflist.addElement(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                ListOrdMission.setModel(dflist);

                ID_Emp_Controle = Integer.parseInt(jLabel81.getText());
                //ListOrdMission.setListData(vectOrd);
                //JOptionPane.showMessageDialog(null,"The Add is Executed");
                switch (ReductionValue) {
                    case 0:
                        if (ValNord == 1) {
                            ValLastOrientNord25 = 1;

                        } else {
                            ValLastOrientSud25 = 1;
                        }
                        break;
                    case 1:
                        if (ValNord == 1) {
                            ValLastOrientNord100 = 1;
                        } else {
                            ValLastOrientSud100 = 1;
                        }
                        break;
                }

            } else {
                if (ReductionValue == 1 && ValNord == 1) {
                    if (ValLastOrientNord25 == 1) {
                        JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientNord25=" + ValLastOrientNord25 + " you are Choice Reduction and Nord");
                    } else {
                        ValLastOrientNord100 = 1;
                        vectOrd.add(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                        //dflist.addElement(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5)+"");

                        dflist.addElement(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                        ListOrdMission.setModel(dflist);
                        //JOptionPane.showMessageDialog(null,"The Add is Executed");
                    }

                } else if (ReductionValue == 1 && ValSud == 1) {
                    //if (ValLastOrientSud100==1) {

                    if (ValLastOrientSud25 == 1) {
                        JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientSud25=" + ValLastOrientSud25 + " you are Choice Reduction and sud");
                    } else {
                        ValLastOrientSud100 = 1;
                        vectOrd.add(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                        dflist.addElement(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                        ListOrdMission.setModel(dflist);
                        //ListOrdMission.setListData(vectOrd); 
                        //JOptionPane.showMessageDialog(null,"The Add is Executed");
                    }
                } else if (ReductionValue == 0 && ValNord == 1) {
                    if (ValLastOrientNord100 == 1) {
                        JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientNord100=" + ValLastOrientNord100 + " you are Choice Reduction and Nord");

                    } else {
                        ValLastOrientNord25 = 1;
                        vectOrd.add(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                        dflist.addElement(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                        ListOrdMission.setModel(dflist);
                        //ListOrdMission.setListData(vectOrd);
                        //   JOptionPane.showMessageDialog(null,"The Add is Executed");
                    }
                } else if (ReductionValue == 0 && ValSud == 1) {
                    if (ValLastOrientSud100 == 1) {
                        JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientSud100=" + ValLastOrientSud100 + " you are Choice Reduction and Nord");
                    } else {
                        ValLastOrientSud25 = 1;
                        vectOrd.add(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                        dflist.addElement(Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5) + "");
                        ListOrdMission.setModel(dflist);

                        //ListOrdMission.setListData(vectOrd);
                        // JOptionPane.showMessageDialog(null,"The Add is Executed");
                    }
                }
            }

        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed

        DefaultListModel dflistofOrd = (DefaultListModel) ListOrdMission.getModel();
        int sizeList = dflistofOrd.size();
        vectOrd.clear();
        System.out.println("The size Of dflist :" + sizeList);
        int i = 0;
        while (i < sizeList) {
            vectOrd.add(dflistofOrd.get(i) + "");
            System.out.println("NumOrd Mission is :" + (String) dflistofOrd.get(i));
            i++;
        }

        if (vectOrd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "لم يتم الحصول علي اية مهمة");
        } else {

            ValLastOrientNord100 = -1;
            ValLastOrientSud100 = -1;
            ValLastOrientNord25 = -1;
            ValLastOrientSud25 = -1;

            for (String NumOrdMission : vectOrd) {

                ValNord = 0;
                ValSud = 0;

                int ValNordZone = ordission_obj.GetZONE_Stat(Integer.parseInt(NumOrdMission));
                ReductionValue = ordission_obj.Get_Porcentage(Integer.parseInt(NumOrdMission));
                if (ValNordZone == 1) {
                    ValNord = 1;
                } else {
                    ValSud = 1;
                }

                //JOptionPane.showMessageDialog(null , "Nord : "+ValNord+" AND ValSud : "+ValSud+" ReductionValue "+ReductionValue);
                if ((ValLastOrientNord100 == -1) && (ValLastOrientSud100 == -1) && (ValLastOrientNord25 == -1) && (ValLastOrientSud25 == -1)) {

                    switch (ReductionValue) {
                        case 0:
                            if (ValNord == 1) {
                                ValLastOrientNord25 = 1;

                            } else {
                                ValLastOrientSud25 = 1;
                            }
                            break;
                        case 1:
                            if (ValNord == 1) {
                                ValLastOrientNord100 = 1;
                            } else {
                                ValLastOrientSud100 = 1;
                            }
                            break;
                    }

                } else {
                    if (ReductionValue == 1 && ValNord == 1) {
                        if (ValLastOrientNord25 == 1) {
                            JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientNord25=" + ValLastOrientNord25 + " you are Choice Reduction and Nord");
                        } else {
                            ValLastOrientNord100 = 1;

                        }

                    } else if (ReductionValue == 1 && ValSud == 1) {
                        //if (ValLastOrientSud100==1) {

                        if (ValLastOrientSud25 == 1) {
                            JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientSud25=" + ValLastOrientSud25 + " you are Choice Reduction and sud");
                        } else {
                            ValLastOrientSud100 = 1;

                        }
                    } else if (ReductionValue == 0 && ValNord == 1) {
                        if (ValLastOrientNord100 == 1) {
                            JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientNord100=" + ValLastOrientNord100 + " you are Choice Reduction and Nord");

                        } else {
                            ValLastOrientNord25 = 1;
                        }
                    } else if (ReductionValue == 0 && ValSud == 1) {
                        if (ValLastOrientSud100 == 1) {
                            JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientSud100=" + ValLastOrientSud100 + " you are Choice Reduction and Nord");
                        } else {
                            ValLastOrientSud25 = 1;
                        }
                    }
                }

            }

            Calcule_val cl = null;
            int nbrrepat = 0, nbrdecoch = 0;
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
            ordission_obj = new OrdMission();
            Employeur employer_obj = new Employeur();
            cl = new Calcule_val();
            /**
             * **********************************************************
             */
            //  int i=0;

            employer_obj = new Employeur();
            employer_obj.GetInformationEmployer(Integer.parseInt(jLabel81.getText()));
            employer_obj.setId_Emp(Integer.parseInt(jLabel81.getText()));
            //employer_obj.setId_Emp(ordission_obj.getId_emp());
            Person = employer_obj;
            System.out.println("new Info_Ord");

            Remplir_Info_obj.Remplir_Sheet1(employer_obj.getFirst_Name_Emp() + " " + employer_obj.getLast_Name_Emp(), "10/10/2019", employer_obj.getGrad_Emp(), employer_obj.getFun_Emp(),
                    employer_obj.getSem_Num_Emp(), employer_obj.getResidance_Emp(),
                    employer_obj.getCCP_Num_Emp(), "", "", "", "", new Date(), "", "", 0, 0);
            System.out.println("Remplir_Sheet1");

            Remplir_Info_obj.Write_In_WorkBook("FileCalcule");
            System.out.println("Remplir_Info_obj.Write_In_WorkBook");

            for (String string : vectOrd) { //for loop to calculate nbr eat
                ordission_obj.GetAllInformation(Integer.parseInt(string));
                /**
                 * ***************-----------------------------------------------*******
                 */
                System.out.println("View.Home.jButton18ActionPerformed()");
                //JOptionPane.showMessageDialog(null, "The Value is "+string);
                try {
                    cl.calcule_eating_dortoire(formatDate.format(ordission_obj.getDateGo()), formatDate.format(ordission_obj.getDateBack()),
                            formatTime.format(ordission_obj.getHeurDepart()), formatTime.format(ordission_obj.getHeurRetour()));
                    nbrrepat = cl.getNbreRepat();
                    nbrdecoch = cl.getNbreDortoire();
                } catch (ParseException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
                Employeur_Info = new Info_Ord(FirstName.getText(), LastName.getText(),
                        "10/10/2009", employer_obj.getGrad_Emp(), employer_obj.getFun_Emp(), employer_obj.getSem_Num_Emp(), employer_obj.getResidance_Emp(),
                        employer_obj.getCCP_Num_Emp(), new Task_Mission().Get_Task(ordission_obj.getId_task()), employer_obj.getResidance_Emp(), helper.GetDistinataire(ordission_obj.getId_dest()),
                        new Voiture().getNameMoyTrans(ordission_obj.getID_Voiture()), formatDate.format(ordission_obj.getDateGo()), formatTime.format(ordission_obj.getHeurDepart()),
                        formatDate.format(ordission_obj.getDateBack()), formatTime.format(ordission_obj.getHeurRetour()), nbrrepat, nbrdecoch, ordission_obj.ID_Direct_Zone(ordission_obj.getId_dest()), ordission_obj.getNum_OrdMission() + "");

                //   JOptionPane.showMessageDialog(null,Nam_Emp_PanClc.getText()+""+Last_Nam_Emp_PanClc.getText()+" "+employer_obj.getGrad_Emp()+" "+employer_obj.getFun_Emp()+" "+employer_obj.getSem_Num_Emp());    
                // JOptionPane.showMessageDialog(null,new Task_Mission().Get_Task(ordission_obj.getId_task())+" "+employer_obj.getResidance_Emp()+" "+helper.GetDistinataire(ordission_obj.getId_dest()));    
                // JOptionPane.showMessageDialog(null,new Voiture().getNameMoyTrans(ordission_obj.getID_Voiture())+" "+formatDate.format(ordission_obj.getDateGo())+" "+ formatTime.format(ordission_obj.getHeurDepart()));    
                // JOptionPane.showMessageDialog(null,""+formatDate.format(ordission_obj.getDateBack())+" "+formatTime.format(ordission_obj.getHeurRetour())+" "+nbrrepat+" "+nbrdecoch+" "+1+" "+ordission_obj.getNum_OrdMission()+"");
                Remplir_Info_obj.Inisialise_Sheet2();
                Remplir_Info_obj.Insialise_ReferenceSh2();
                System.out.println("Remplir_Info_obj.Inisialise_Sheet2()");

                Remplir_Info_obj.Remplir_Sheet2(Employeur_Info.getCauseTravel(), Employeur_Info.getDepart_Demarer(),
                        Employeur_Info.getDestinataire(), Employeur_Info.getDateGo(), Employeur_Info.getHeur_Go(),
                        Employeur_Info.getDateBack(), Employeur_Info.getHeur_Back(),
                        Employeur_Info.getMoyenTrnsport(), 0, 0, 0, 0,
                        Employeur_Info.getCompensationEat(), Employeur_Info.getCompensationEat(), Employeur_Info.getCompensationDrt(), Employeur_Info.getCompensationDrt(), Employeur_Info.getRemarque(),
                        Employeur_Info.getOrientation());
                System.out.println("Remplir_Info_obj.Remplir_Sheet2");
                // Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName());
                Remplir_Info_obj.Write_In_WorkBook("FileCalcule");
                Remplir_Info_obj.setNum_Line((Remplir_Info_obj.GetNum_Line() + 1));
            }
            /**
             * ****************************************************
             */
            NumberORdMission = vectOrd.size();
            //JOptionPane.showMessageDialog(null, "The Size is NumberORdMission :"+NumberORdMission);
            if (NumberORdMission == 1) {// just one Ord Mission
                Remplir_Info_obj.RemplirSomDrt();// calculate Some Number Repat And Some Number Dortoir All Mission
                //Remplir_Info_obj.SumCompensationTransport();  //Calcule Some Of Price Of Transport 
                if (ReductionValue == 1) {        // if Mission is 100%
                    if (ValNord == 1) {    //This for Choice Of Nord 
                        //JOptionPane.showMessageDialog(null, "The Nord Orientation "); 
                        Remplir_Info_obj.GetNbrCompensationNrd(); //for deplace Value of Some to sheet 1 to calculate 
                        GetPriceEatANDDecocher(NumberORdMission);
                    } else {

                        Remplir_Info_obj.GetNbrCompensationSudForOneMission(1); //This For Deplace Some Repat And Decocher To Calculate : arg =1: Just One OrdMission in p18 / p19 (Repat Sud in p18 and p19) 
                        GetPriceEatANDDecocherForOneMission(0);//                                                       : arg!=1: for 2 Mission Nord And Sude sud in p20 and p21
                    }
                    Remplir_Info_obj.SumCompensationToujours(NumberORdMission, 0, 0);  // ce methode is calculate product of prix * number  AND Calcul SUM
                    Remplir_Info_obj.TotlaSumBenefit();

                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();

                } else {
                    //JOptionPane.showMessageDialog(null, "The ReductionValue is 25%");
                    Remplir_Info_obj.RemplirSomDrt();
                    if (ValNord == 1) {

                        //JOptionPane.showMessageDialog(null, "The Orientation Is Nord ");
                        Remplir_Info_obj.GetNbrCompensationNrdWithReduction(1);
                        //GetPriceEatANDDecocherCForReduction(1);
                        GetPriceEatANDDecocherCForReduction(1);
                        Remplir_Info_obj.SumCompensationToujoursForReduction();
                    } else {
                        //JOptionPane.showMessageDialog(null, "The Orientation Is Sud ");
                        Remplir_Info_obj.GetNbrCompensationNrdWithReduction(0);
                        // GetPriceEatANDDecocherCForReduction(1);
                        GetPriceEatANDDecocherCForReduction(1);
                        Remplir_Info_obj.SumCompensationToujoursForReduction();
                        //Remplir_Info_obj.CalculePrix_25();
                    }

                    //Remplir_Info_obj.SumCompensationToujours();  // ce methode is calculate product of prix * number  AND Calcul SUM
                    //new Code 
                    Remplir_Info_obj.InitialiseCellReductionPrice(NumberORdMission, 0, 0);
                    Remplir_Info_obj.TotlaSumBenefit();

                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();

                }

            } else {

                /**
                 * *****************************************************
                 */
                Remplir_Info_obj.RemplirSomDrt();

                if ((ValLastOrientSud100 == 1 || ValLastOrientSud25 == 1) && ValLastOrientNord100 == -1 && ValLastOrientNord25 == -1) {

                    JOptionPane.showMessageDialog(null, "ValLastOrientSud100==1||ValLastOrientSud25==1) &&ValLastOrientNord100==-1 && ValLastOrientNord25==-1 ");

                    //deplace sud number to first Cellss 
                    if (ValLastOrientSud100 == 1) {

                        JOptionPane.showMessageDialog(null, "The ValLastOrientSud100==1 ");
                        Remplir_Info_obj.GetNbrCompensationSudForOneMission(1);
                        GetPriceEatANDDecocherForOneMission(0);
                        //Remplir_Info_obj.SumCompensationToujours(NumberORdMission);  // ce methode is calculate product of prix * number  AND Calcul SUM
                        Remplir_Info_obj.SumCompensationToujours(1, 0, 0); //because just on orientation (1 is condition methode SumCompensationToujours so we dont need N20 and N21)
                        Remplir_Info_obj.TotlaSumBenefit();

                        Remplir_Info_obj.SumTransport_and_compensationTtl();
                        //Remplir_Info_obj.ChangeThisNumber();
                        Remplir_Info_obj.Date_Delivred();
                    } else {    //ValLastOrientSud25==1

                        JOptionPane.showMessageDialog(null, "the ValLastOrientSud25==1 ");

                        Remplir_Info_obj.GetNbrCompensationNrdWithReduction(0);
                        // GetPriceEatANDDecocherCForReduction(1);
                        GetPriceEatANDDecocherForOneMission(0);
                        Remplir_Info_obj.SumCompensationToujoursForReduction();
                        Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), 1, 0);
                        Remplir_Info_obj.TotlaSumBenefit();
                        Remplir_Info_obj.SumTransport_and_compensationTtl();
                        //Remplir_Info_obj.ChangeThisNumber();
                        Remplir_Info_obj.Date_Delivred();
                    }
                    /**
                     * ************************************************************************
                     */
                } else if ((ValLastOrientNord100 == 1 || ValLastOrientNord25 == 1) && ValLastOrientSud100 == -1 && ValLastOrientSud25 == -1) {

                    JOptionPane.showMessageDialog(null, "ValLastOrientNord100==1||ValLastOrientNord25==1) &&ValLastOrientSud100==-1 && ValLastOrientSud25==-1");

                    if (ValLastOrientNord100 == 1) {

                        JOptionPane.showMessageDialog(null, "the  ValLastOrientNord100==1");

                        // Remplir_Info_obj.GetNbrCompensationSudForOneMission(1);
                        Remplir_Info_obj.GetNbrCompensationNrd();
                        GetPriceEatANDDecocherForOneMission(1);
                        //Remplir_Info_obj.SumCompensationToujours(NumberORdMission);  // ce methode is calculate product of prix * number  AND Calcul SUM
                        Remplir_Info_obj.SumCompensationToujours(1, 0, 0); //because just on orientation (1 is condition methode SumCompensationToujours so we dont need N20 and N21)
                        Remplir_Info_obj.TotlaSumBenefit();

                        Remplir_Info_obj.SumTransport_and_compensationTtl();
                        //Remplir_Info_obj.ChangeThisNumber();
                        Remplir_Info_obj.Date_Delivred();

                    } else {    //ValLastOrientNord25==1
                        //Remplir_Info_obj.GetNbrCompensationSudForOneMission(1);
                        JOptionPane.showMessageDialog(null, "the  ValLastOrientSud100==1");

                        Remplir_Info_obj.GetNbrCompensationNrd();
                        GetPriceEatANDDecocherForOneMission(1);
                        Remplir_Info_obj.SumCompensationToujoursForReduction();
                        Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), 1, 0);
                        Remplir_Info_obj.TotlaSumBenefit();
                        Remplir_Info_obj.SumTransport_and_compensationTtl();
                        //Remplir_Info_obj.ChangeThisNumber();
                        Remplir_Info_obj.Date_Delivred();
                    }
                } else if (ValLastOrientNord100 == 1 && ValLastOrientSud25 == 1) {  //nord 100% and sud 25%

                    JOptionPane.showMessageDialog(null, "the ValLastOrientNord100==1 && ValLastOrientSud25==1 ");

                    Remplir_Info_obj.GetNbrCompensationNrd();
                    Remplir_Info_obj.GetNbrCompensationSud();
                    GetPriceEatANDDecocher(vectOrd.size());
                    Remplir_Info_obj.SumCompensationToujours(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                    Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                    Remplir_Info_obj.TotlaSumBenefit();
                    Remplir_Info_obj.SumTransport_and_compensationTtl();

                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                } else if (ValLastOrientSud100 == 1 && ValLastOrientNord25 == 1) { //nord 25% and Sud 100%

                    JOptionPane.showMessageDialog(null, "the ValLastOrientSud100==1 && ValLastOrientNord25==1 ");

                    Remplir_Info_obj.GetNbrCompensationNrd();
                    Remplir_Info_obj.GetNbrCompensationSud();
                    GetPriceEatANDDecocher(vectOrd.size());
                    Remplir_Info_obj.SumCompensationToujours(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                    Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);

                    Remplir_Info_obj.TotlaSumBenefit();
                    Remplir_Info_obj.SumTransport_and_compensationTtl();

                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                } else if (ValLastOrientSud25 == 1 && ValLastOrientNord25 == 1) {    //two oprientation different and 25%

                    JOptionPane.showMessageDialog(null, "ValLastOrientSud25==1 && ValLastOrientNord25==1");

                    Remplir_Info_obj.GetNbrCompensationNrd();
                    Remplir_Info_obj.GetNbrCompensationSud();
                    GetPriceEatANDDecocher(vectOrd.size());
                    Remplir_Info_obj.SumCompensationToujours(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                    Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);

                    Remplir_Info_obj.TotlaSumBenefit();
                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                } else {   //Remplir_Info_obj.Inisialise_Sheet1();
                    JOptionPane.showMessageDialog(null, "else ********************** else");
                    Remplir_Info_obj.GetNbrCompensationNrd();
                    Remplir_Info_obj.GetNbrCompensationSud();
                    GetPriceEatANDDecocher(vectOrd.size());
                    Remplir_Info_obj.SumCompensationToujours(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                    Remplir_Info_obj.TotlaSumBenefit();
                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                }
            }

            Remplir_Info_obj.Write_In_WorkBook("FileCalcule");
            try {
                Desktop dt = Desktop.getDesktop();
                // dt.open(new File("src\\OurFile\\AppClose.xlsx"));
                dt.open(new File("FileCalcule" + ".xlsx"));
                //  dt.open(new File(""+FullNam.getText()+".xlsx"));

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error in Opened The File");
            }

            Remplir_Info_obj.setNum_Line(8);
            ValLastOrientNord100 = -1;
            ValLastOrientSud100 = -1;
            ValLastOrientNord25 = -1;
            ValLastOrientSud25 = -1;
            ValSud = 0;
            ValNord = 0;
            vectOrd.clear();
            dflist.clear();
            ListOrdMission.setModel(dflist);
            Remplir_Info_obj.setNum_Line(8);
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    public void Enable_TabDepens_OrdMission(int Choice_pan) {
        if (Choice_pan == 1) {
            panDetail_TabDepns.setVisible(false);
            panBtn_TabDepns.setVisible(false);

        } else {
            panDetail_TabDepns.setVisible(true);
            panBtn_TabDepns.setVisible(true);
        }
    }

    public void Disable_Comp_PanMissionTab() {

    }

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        ValLastOrientNord100 = -1;
        ValLastOrientSud100 = -1;
        ValLastOrientNord25 = -1;
        ValLastOrientSud25 = -1;
        ValSud = 0;
        ValNord = 0;
        vectOrd.clear();
        dflist.clear();
        ListOrdMission.setModel(dflist);
        Remplir_Info_obj.setNum_Line(8);

    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed

        Calendar calendarGo = Calendar.getInstance();
        Calendar calendarBack = Calendar.getInstance();

        Date dateGo_hour = null;

        Date dateGo = null;
        try {
            dateGo = formatDate.parse(DateGo1.getText());
            calendarGo.setTime(dateGo);
            calendarGo.set(Calendar.HOUR_OF_DAY, ((Date) Heur_Go3.getValue()).getHours());
            calendarGo.set(Calendar.MINUTE, ((Date) Heur_Go3.getValue()).getMinutes());
            dateGo_hour = calendarGo.getTime();

        } catch (ParseException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        ordission_obj = new OrdMission(
                Integer.parseInt(Num_OrderMission.getText()),
                new Date(),
                dateGo,
                null,
                PersonRemplissage.GetIdEmployer(FirstName1.getText(), LastName1.getText()),
                voiture.GetId_Voiture(MoyenTrsp_Miss1.getSelectedItem()),
                task_mission.GetId_Task(TaskMission1.getSelectedItem()),
                false,
                valprctg,
                dateGo_hour,
                null,
                voiture.GetId_Distinataire(Distinataire1.getSelectedItem()),
                this);//GetId_Distinataire

        ordission_obj.insertOrdMission();

        JOptionPane.showMessageDialog(null, "Success Save Mission ");
    }//GEN-LAST:event_jButton24ActionPerformed

    private void Table_OrdMissionEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_OrdMissionEditMouseClicked
        ordission_obj.GetAllInformation((int) Table_OrdMissionEdit.getValueAt(Table_OrdMissionEdit.getSelectedRow(), 5));
        NumOrdLab.setText("" + (int) Table_OrdMissionEdit.getValueAt(Table_OrdMissionEdit.getSelectedRow(), 5));

        PersonRemplissage.GetInformationEmployer(ordission_obj.getId_emp());

        Num_OrderMission.setText((int) Table_OrdMissionEdit.getValueAt(Table_OrdMissionEdit.getSelectedRow(), 5) + "");
        FirstName1.setText((String) Table_OrdMissionEdit.getValueAt(Table_OrdMissionEdit.getSelectedRow(), 4));
        LastName1.setText((String) Table_OrdMissionEdit.getValueAt(Table_OrdMissionEdit.getSelectedRow(), 3));
        jTextField13.setText(PersonRemplissage.getGrad_Emp());
        jTextField17.setText(PersonRemplissage.getFun_Emp());
        ResidentAdm1.setText(PersonRemplissage.getResidance_Emp());
        Distinataire1.select(helper.GetDistinataire(ordission_obj.getId_dest()));
        TaskMission1.select(new Task_Mission().Get_Task(ordission_obj.getId_task()));
        MoyenTrsp_Miss1.select(new Voiture().getNameMoyTrans(ordission_obj.getID_Voiture()));
        DateGo1.setText(formatDate.format(ordission_obj.getDateGo()));
        Date Hour_Go_OrdMis = ordission_obj.getHeurDepart();
        //Date Hour_Back_OrdMis=ordission_obj.getHeurRetour();

        SpinnerDateModel mdl = new SpinnerDateModel(new Date(2019, 12, 21, Hour_Go_OrdMis.getHours(), Hour_Go_OrdMis.getMinutes()), null, null, Calendar.HOUR_OF_DAY);
        Heur_Go3.setModel(mdl);

        JSpinner.DateEditor de = new JSpinner.DateEditor(Heur_Go3, "HH:mm");
        Heur_Go3.setEditor(de);

//        if (Hour_Back_OrdMis!=null) {
//            
//            DateBack.setText(formatDate.format(ordission_obj.getDateBack()));
//             mdl=new SpinnerDateModel(new Date(2019, 12, 21, Hour_Back_OrdMis.getHours(), Hour_Back_OrdMis.getMinutes()),null, null, Calendar.HOUR_OF_DAY);
//       Heur_Back2.setModel(mdl);
//       
//        de=new JSpinner.DateEditor(Heur_Back2,"HH:mm");
//       Heur_Back2.setEditor(de);
//       
//       jPanel34.setVisible(true);
//        }else {
//        JOptionPane.showMessageDialog(null, "Date is Null");
//        
//        
//        
//        
//        }

    }//GEN-LAST:event_Table_OrdMissionEditMouseClicked

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed

        Date d1 = (Date) Heur_Go3.getValue();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        String TimeMiss = format.format(d1);
        new OrdMission().printing_OrdMission(Integer.parseInt(Num_OrderMission.getText()), FirstName1.getText(), LastName1.getText(), jTextField13.getText(),
                jTextField17.getText(), ResidentAdm1.getText(), Distinataire1.getSelectedItem(), MoyenTrsp_Miss1.getSelectedItem(), TaskMission1.getSelectedItem(), "", DateGo1.getText() + " " + TimeMiss);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void btnSvMissTbDepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSvMissTbDepMouseEntered

        btnSvMissTbDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save_bl.png")));
    }//GEN-LAST:event_btnSvMissTbDepMouseEntered

    private void btnSvMissTbDepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSvMissTbDepMouseExited
        btnSvMissTbDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save_wh.png")));
    }//GEN-LAST:event_btnSvMissTbDepMouseExited

    private void BtnUpdTbDepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnUpdTbDepMouseEntered
        // TODO add your handling code here: editfile_Bl

        BtnUpdTbDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/editfile_Bl.png")));
    }//GEN-LAST:event_BtnUpdTbDepMouseEntered

    private void BtnUpdTbDepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnUpdTbDepMouseExited
        BtnUpdTbDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/editfile.png")));
    }//GEN-LAST:event_BtnUpdTbDepMouseExited

    private void BtnUpdTbDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdTbDepActionPerformed

        if (BtnUpdTbDep.getText().equals("تعديل")) {
            TaskAttach = "UpdateOrdMission";
            confirmation.DisplayMsg("هل تريـــد تعديل المهمـــة");
            confirmation.setVisible(true);

        } else {
            UpdateOrMission();
        }
        BtnUpdTbDep.setText("حفظ التعديل");


    }//GEN-LAST:event_BtnUpdTbDepActionPerformed

    int Case_Table = 0;// Table_OrdMission1 and Tab_InfoEmp is Activate

    public void Enable_TO_Update(int ch) {// the choice to Update and nEW OrdMission
        if (ch == 0) {
            BtnNewMissTbDep.setEnabled(false);//BtnNewMissTbDep
            btnSvMissTbDep.setEnabled(true);
            BtnUpdTbDep.setEnabled(false);
            PnFdDpnsDtl_InsdPanDateHour.setVisible(true);//to show panel back date
        }

        enablePanelInformation(true, PnFildsToDpnsDetaill, 1);
        enablePanelInformation(true, PnFdDpnsDtl_InsdPanDateHour, 1);
    }

    public void UpdateOrMission() { //enable panele26 and update data 

        try {
            SimpleDateFormat formt = new SimpleDateFormat("dd/MM/yyyy");
            Date dateGo = formt.parse(DateGo.getText());

            Date dateBack = formt.parse(DateBack.getText());
            Calendar calendarGo = Calendar.getInstance();
            Calendar calendarBack = Calendar.getInstance();
            calendarGo.setTime(dateGo);
            calendarGo.set(Calendar.HOUR_OF_DAY, ((Date) Heur_Go2.getValue()).getHours());
            calendarGo.set(Calendar.MINUTE, ((Date) Heur_Go2.getValue()).getMinutes());
            Date dateGo_hour = calendarGo.getTime();
            calendarBack.setTime(dateBack);
            calendarBack.set(Calendar.HOUR_OF_DAY, ((Date) Heur_Back2.getValue()).getHours());
            calendarBack.set(Calendar.MINUTE, ((Date) Heur_Back2.getValue()).getMinutes());
            Date dateGo_Back = calendarBack.getTime();

            ordission_obj = new OrdMission(
                    Integer.parseInt(num_ord.getText()),
                    dateGo,
                    dateGo,
                    dateBack,
                    PersonRemplissage.GetIdEmployer(FirstName.getText(), LastName.getText()),
                    voiture.GetId_Voiture(MoyenTrsp_Miss.getSelectedItem()),
                    task_mission.GetId_Task(TaskMission.getSelectedItem()),
                    false,
                    valprctg,
                    dateGo_hour,
                    dateGo_Back,
                    voiture.GetId_Distinataire(Distinataire.getSelectedItem()),
                    this);//GetId_Distinataire

            ordission_obj.Update_OrdMission(Integer.parseInt(num_ord.getText()));
            ordission_obj.FillOrdMissionNoProcess(Table_OrdMission1, 1, 2);
            InitialisePanCalOrdMiss();
        } catch (ParseException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void UpdateOrMissionEdit() {
        try {
            SimpleDateFormat formt = new SimpleDateFormat("dd/MM/yyyy");
            Date dateGo = formt.parse(DateGo1.getText());

            //Date dateBack=formt.parse(DateBack.getText());
            Calendar calendarGo = Calendar.getInstance();
            // Calendar calendarBack=Calendar.getInstance();

            calendarGo.setTime(dateGo);
            calendarGo.set(Calendar.HOUR_OF_DAY, ((Date) Heur_Go3.getValue()).getHours());
            calendarGo.set(Calendar.MINUTE, ((Date) Heur_Go3.getValue()).getMinutes());
            Date dateGo_hour = calendarGo.getTime();

//            calendarBack.setTime(dateBack);
//            calendarBack.set(Calendar.HOUR_OF_DAY, ((Date)Heur_Back2.getValue()).getHours());
//            calendarBack.set(Calendar.MINUTE, ((Date)Heur_Back2.getValue()).getMinutes());
//            Date dateGo_Back=calendarBack.getTime();
            ordission_obj = new OrdMission(
                    Integer.parseInt(OrdUpd_Lab.getText()),
                    dateGo,
                    dateGo,
                    null,
                    PersonRemplissage.GetIdEmployer(FirstName1.getText(), LastName1.getText()),
                    voiture.GetId_Voiture(MoyenTrsp_Miss1.getSelectedItem()),
                    task_mission.GetId_Task(TaskMission1.getSelectedItem()),
                    false,
                    valprctg,
                    dateGo_hour,
                    null,
                    voiture.GetId_Distinataire(Distinataire1.getSelectedItem()),
                    this);//GetId_Distinataire

            ordission_obj.Update_OrdMission(Integer.parseInt(NumOrdLab.getText()));
            ordission_obj.FillOrdMissionNoProcess(Table_OrdMissionEdit, 1, 2);
            InitialisePanCalOrdMiss();
        } catch (ParseException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BtnCancelTbDepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCancelTbDepMouseEntered
        BtnCancelTbDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/deletbl.png"))); // NOI18N
    }//GEN-LAST:event_BtnCancelTbDepMouseEntered

    private void BtnCancelTbDepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCancelTbDepMouseExited
        BtnCancelTbDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/deletwh.png"))); // NOI18N
    }//GEN-LAST:event_BtnCancelTbDepMouseExited

    private void BtnCancelTbDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelTbDepActionPerformed
        InitialisePanCalOrdMiss();
        BtnNewMissTbDep.setEnabled(true);
        BtnUpdTbDep.setEnabled(false);
        btnSvMissTbDep.setEnabled(false);
        enablePanelInformation(false, PnFildsToDpnsDetaill, 1);
        enablePanelInformation(false, PnFdDpnsDtl_InsdPanDateHour, 1);
        Table_OrdMission1.getSelectionModel().clearSelection();
        BtnUpdTbDep.setEnabled(false);
    }//GEN-LAST:event_BtnCancelTbDepActionPerformed

    private void BtnCancelTbDepMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCancelTbDepMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCancelTbDepMousePressed

    private void jButton19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseEntered
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delOrdBl.png"))); // NOI18N
    }//GEN-LAST:event_jButton19MouseEntered

    private void jButton19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseExited
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delOrdMiswh.png"))); // NOI18N
    }//GEN-LAST:event_jButton19MouseExited

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed

    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        Function_Obj = new Fonction(Nam_Fnct.getText(), Funct_NamFR.getText());
        Function_Obj.Add_Function();
        FunctionRemplissage.FillTableFunction(tab_Function);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void checkFunctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkFunctActionPerformed
        if (checkFunct.isSelected()) {
            Function_Choice.setEnabled(true);
        } else {
            Function_Choice.setEnabled(false);
        }
    }//GEN-LAST:event_checkFunctActionPerformed

    private void Function_ChoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Function_ChoiceMouseClicked

    }//GEN-LAST:event_Function_ChoiceMouseClicked

    private void Function_ChoiceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Function_ChoiceMousePressed
        //Reg_Jop.setText(Function_Choice.getSelectedItem());
    }//GEN-LAST:event_Function_ChoiceMousePressed

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        TaskAttach = "updateEmpl";
        confirmation.DisplayMsg("هل تريـــد تعديل المهمات");
        confirmation.setVisible(true);
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
//jPanel26.setLocation(0, 0);
//jPanel27.setLocation(0, 300);

//         TaskAttach="InsertEmpl";
//        
//         confirmation.DisplayMsg("هل تريـــد اضافة موظف جديد ");
//        confirmation.setVisible(true);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusGained
        if (jTextField9.getText().equals("البحـث")) {
            jTextField9.setText("");
            jTextField9.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextField9FocusGained

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed

    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
        FilterEmployer(jTextField9.getText(), Table_OrdMissionEdit, (DefaultTableModel) Table_OrdMissionEdit.getModel());
    }//GEN-LAST:event_jTextField9KeyReleased

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        jPanel22.setVisible(false);
        jPanel10.setVisible(true);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        jPanel22.setVisible(true);
        jPanel10.setVisible(false);
        Engagement_Opr.Fill_Table_Depense(Depanse_Tab_Eng);
        /**
         * ******************************************************
         */ //to display pan Off Ord Mission 
        btnSvMissTbDep.setEnabled(false);
        BtnUpdTbDep.setEnabled(false);
        FinishOrdMission.setVisible(false);
//jPanel34.setVisible(false);
//Enable_TabDepens_OrdMission(1);

        enablePanelInformation(false, panDetail_TabDepns, 1);//panBtn_TabDepns
        enablePanelInformation(false, panBtn_TabDepns, 1);
        enablePanelInformation(false, PnFildsToDpnsDetaill, 1);//jPanel34
        enablePanelInformation(false, PnFdDpnsDtl_InsdPanDateHour, 1);

        ValLastOrientNord100 = -1;
        ValLastOrientSud100 = -1;
        ValLastOrientNord25 = -1;
        ValLastOrientSud25 = -1;
        ValSud = 0;
        ValNord = 0;
        //PanOrdMission
        //ChoicePanelCars(jPanel5, PanOrdMission);

        ChoiceTask = 3;
        ChoixPanSrvdetaille(panServices, PanOrdMission);
        ordission_obj.FillTableOrdMission(Table_OrdMission, 1, 2);
        NumItems.setText(Table_OrdMission.getRowCount() + "");
        PersonRemplissage = new Employeur();
        PersonRemplissage.FillChoiceDestinataire(Distinataire, 1, 2);
        PersonRemplissage.RemplirCombobox(TaskMission, "Tasktype", "DescriptionTask_AR", 's');
        PersonRemplissage.RemplirCombobox(MoyenTrsp_Miss, "Moyen_Transport", "Nom_Voiture", 's');
//        InitialiseDate(DateCrtMission);
        InitialiseDate(DateGo);
        InitialiseDate(DateBack);

        ordission_obj.FillOrdMissionNoProcess(Table_OrdMission1, 1, 2);

        /**
         * *****************************************************
         */
        panDetail_TabDepns.setLocation(550, 40);
        PnTabOrdMsstoDpns.setLocation(0, 40);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        TaskAttach = "UpdateOrdMissionEd";
        confirmation.DisplayMsg("هل تريـــد تعديل المهمـــة");
        confirmation.setVisible(true);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void FinishOrdMissionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FinishOrdMissionMouseClicked
        PnFdDpnsDtl_InsdPanDateHour.setVisible(true);
        BtnUpdTbDep.setEnabled(true);
        enablePanelInformation(true, PnFildsToDpnsDetaill, 1);
        enablePanelInformation(true, PnFdDpnsDtl_InsdPanDateHour, 1);
        BtnUpdTbDep.setText("حفظ التعديل");

        //JOptionPane.showMessageDialog(null, "tHE lABAL"+evt.getXOnScreen()+" "+evt.getYOnScreen());

    }//GEN-LAST:event_FinishOrdMissionMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    public void Initialise_VectorList() {

        DefaultListModel dflistofOrd = (DefaultListModel) ListOrdMission.getModel();
        int sizeList = dflistofOrd.size();
        vectOrd.clear();
        Engagement_Opr.getList_OrdMission().clear();
        System.out.println("The size Of dflist :" + sizeList);
        int i = 0;
        while (i < sizeList) {
            vectOrd.add(dflistofOrd.get(i) + "");
            System.out.println("NumOrd Mission is :" + (String) dflistofOrd.get(i));
            i++;
        }
    }

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        Initialise_VectorList();

        for (String Num_Ord : vectOrd) {
            Engagement_Opr.getList_OrdMission().add(Integer.parseInt(Num_Ord));
        }

        Engagement_Opr.Calcule_prix_Engagement();

        PrcTltLab.setText(Engagement_Opr.getMontant() + "");
        Engagement_Opr.setMontant(0);
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jPanel3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseReleased
        this.setOpacity((float) 1.0);
    }//GEN-LAST:event_jPanel3MouseReleased

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        DefaultListModel dflistofOrd = (DefaultListModel) ListOrdMission.getModel();
        int sizeList = dflistofOrd.size();

        JOptionPane.showMessageDialog(null, "Size Of Vector :" + vectOrd.size());
        JOptionPane.showMessageDialog(null, "Size Of Engagement_Opr.getList_OrdMission() :" + Engagement_Opr.getList_OrdMission().size());
        JOptionPane.showMessageDialog(null, "Size Of dflistofOrd :" + dflistofOrd.size());

        vectOrd.clear();
        Engagement_Opr.getList_OrdMission().clear();

        JOptionPane.showMessageDialog(null, "After Clear Size Of Vector :" + vectOrd.size());
        JOptionPane.showMessageDialog(null, "After Clear Size Of Engagement_Opr.getList_OrdMission() :" + Engagement_Opr.getList_OrdMission());
        JOptionPane.showMessageDialog(null, "After Clear Size Of dflistofOrd :" + dflistofOrd.size());

        /**
         * ***********************************************
         */
        int i = 0;
        while (i < sizeList) {
            vectOrd.add(dflistofOrd.get(i) + "");
            System.out.println("NumOrd Mission is :" + (String) dflistofOrd.get(i));
            i++;
        }

        JOptionPane.showMessageDialog(null, "After Adding Size Of Vector :" + vectOrd.size());

        for (String Num_Ord : vectOrd) {
            Engagement_Opr.getList_OrdMission().add(Integer.parseInt(Num_Ord));
        }

        JOptionPane.showMessageDialog(null, "After Loop Size Of Engagement_Opr.getList_OrdMission() :" + Engagement_Opr.getList_OrdMission());

        Engagement_Opr.Save_Depense(1, 0);

        //JOptionPane.showMessageDialog(null, "Save Depense Is Succeful");
    }//GEN-LAST:event_jButton37ActionPerformed

    public void Step_Save_Cancel_OrdMission(int sav) {
        //0:cancel 1:NewOrdMission 2:SaveMission 3:UpdateMission

        switch (sav) {
            case 0:
                BtnNewMissTbDep.setEnabled(true);
                btnSvMissTbDep.setEnabled(false);
                BtnUpdTbDep.setEnabled(false);
                BtnCancelTbDep.setEnabled(true);
                break;

            case 1:

                BtnNewMissTbDep.setEnabled(false);
                BtnUpdTbDep.setEnabled(false);
                enablePanelInformation(true, PnFildsToDpnsDetaill, 1);//jPanel34
                enablePanelInformation(true, PnFdDpnsDtl_InsdPanDateHour, 1);
//          BtnCancelTbDep.setEnabled(true);
//          btnSvMissTbDep.setEnabled(true);
//          BtnUpdTbDep.setEnabled(false);
//          BtnNewMissTbDep.setEnabled(false);
                break;
            case 2:
                BtnCancelTbDep.setEnabled(true);
                btnSvMissTbDep.setEnabled(false);
                BtnUpdTbDep.setEnabled(false);
                BtnNewMissTbDep.setEnabled(true);
                break;
                
            case 3:
                BtnCancelTbDep.setEnabled(true);
                btnSvMissTbDep.setEnabled(false);
                BtnUpdTbDep.setEnabled(false);
                BtnCancelTbDep.setEnabled(true);
                break;
        }
    }

    private void jLabel134MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel134MouseClicked

        if (jLabel134.getText().equals("جدول المصاريف")) {
            enablePanelInformation(false, PnFildsToDpnsDetaill, 1);//jPanel34
            enablePanelInformation(false, PnFdDpnsDtl_InsdPanDateHour, 1);
            enablePanelInformation(true, panDetail_TabDepns, 1);//jPanel34
            enablePanelInformation(true, panBtn_TabDepns, 1);
            jLabel134.setText("المهمات");
        } else {
            enablePanelInformation(false, panDetail_TabDepns, 1);//jPanel34
            enablePanelInformation(false, panBtn_TabDepns, 1);
            jLabel134.setText("جدول المصاريف");
        }
        /**
         * *********************************************************
         */
        if (jLabel134.getText().equals("جدول المصاريف")) {
            enablePanelInformation(true, PnFildsToDpnsDetaill, 1);//jPanel34
            enablePanelInformation(true, PnFdDpnsDtl_InsdPanDateHour, 1);
            enablePanelInformation(true, panDetail_TabDepns, 1);//jPanel34
            enablePanelInformation(true, panBtn_TabDepns, 1);
            jLabel134.setText("المهمات");
        } else {
            enablePanelInformation(true, panDetail_TabDepns, 1);//jPanel34
            enablePanelInformation(true, panBtn_TabDepns, 1);
            jLabel134.setText("جدول المصاريف");
        }
    }//GEN-LAST:event_jLabel134MouseClicked

    public void DeleteOrDMission() {
        // JOptionPane.showMessageDialog(null, "The Num Ord Mission"+(int) Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5));
        ordission_obj.Delete_OrdMission((int) Table_OrdMission1.getValueAt(Table_OrdMission1.getSelectedRow(), 5));
        System.out.println("View.Home.The Deleted Is Done");
        InitialisePanCalOrdMiss();
        ordission_obj.FillOrdMissionNoProcess(Table_OrdMission1, 1, 2);

    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Delete_OrdMission
        confirmation.DisplayMsg("هل تريـــد حذف المهمـة ");
        confirmation.setVisible(true);
        TaskAttach = "DeleteOrdMission";
        Tab_InfoEmp.disable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void BtnNewMissTbDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNewMissTbDepActionPerformed

        confirmation.DisplayMsg("هل تريـــد اضــافة مهمــة جديدة");
        confirmation.setVisible(true);
        TaskAttach = "addotherMissionAddNew_OdMs";
        Case_Table = 1;
                btnSvMissTbDep.setEnabled(true);
                BtnUpdTbDep.setEnabled(false);
                BtnNewMissTbDep.setEnabled(false);
    }//GEN-LAST:event_BtnNewMissTbDepActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jPanel15.setLocation(0, 318);
        jPanel14.setLocation(0, 40);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed

        Thread th;
        th = new Thread() {
            @Override
            public void run() {
                try {
                    // for (int i = 10; i >= -150; i--) {
                    for (int i = 0; i <= 70; i++) {
                        Thread.sleep((long) 0.9);
                        notification.setPreferredSize(new Dimension(170, i));
                        System.out.println("Threaaaaaaaaaaaaaaaad");
//                        if(i>-140 &jButton2.getX()>0){
//                        jButton2.setLocation(jButton2.getX()-1, 10);
//                        }
                    }
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, "The Error for thread is :" + ex.getMessage());
                }
            }
        };
        th.start();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        NamCommune.setText("");
        BtnRdioinf50.setSelected(true);
        BtnRdiNrd.setSelected(true);
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton41ActionPerformed

    private void TablCommuneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablCommuneMouseClicked
        NamCommune.setText((String) TablCommune.getValueAt(0, TablCommune.getSelectedRow()));
        int Dist = (int) TablCommune.getValueAt(2, TablCommune.getSelectedRow());
    }//GEN-LAST:event_TablCommuneMouseClicked

    private void Depanse_Tab_EngMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Depanse_Tab_EngMouseClicked

//        if (evt.getClickCount()==2) {
//            
//        }
//        
        int NumIdOp = (int) Depanse_Tab_Eng.getValueAt(Depanse_Tab_Eng.getSelectedRow(), 3);
        // Engagement_Opr.GetListOrdMission(jList1,NumIdOp);ListOrdMission
        Engagement_Opr.GetListOrdMission(ListOrdMission, NumIdOp);

//      ListTableDepense_NumDep.addElement(""+ Depanse_Tab_Eng.getValueAt( Depanse_Tab_Eng.getSelectedRow(),3));
//      ListTabDps.setModel(ListTableDepense_NumDep);
//      
//      ListTableDepense_Person.addElement(Depanse_Tab_Eng.getValueAt( Depanse_Tab_Eng.getSelectedRow(),3)+":"+ Depanse_Tab_Eng.getValueAt( Depanse_Tab_Eng.getSelectedRow(),2));
//       ListTab_DepPrsn.setModel(ListTableDepense_Person);
        //GetListOrdMission

    }//GEN-LAST:event_Depanse_Tab_EngMouseClicked
    DefaultListModel<String> ListTableDepense_NumDep = new DefaultListModel<String>();
    Vector<Integer> VectTableDepense_NumDep = new Vector<>();

    DefaultListModel<String> ListTableDepense_Person = new DefaultListModel<>();
    Vector<String> VectTableDepense_Person = new Vector<>();
    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        panDetail_TabDepns.setLocation(550, 40);
        PnTabOrdMsstoDpns.setLocation(0, 40);


    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
//vectOrd

        DefaultListModel dflistofOrd = (DefaultListModel) ListOrdMission.getModel();
        int sizeList = dflistofOrd.size();
        vectOrd.clear();
        System.out.println("The size Of dflist :" + sizeList);
        int i = 0;
        while (i < sizeList) {
            vectOrd.add(dflistofOrd.get(i) + "");
            System.out.println("NumOrd Mission is :" + (String) dflistofOrd.get(i));
            i++;
        }

        System.out.println("Fill vector ord mission is skip");
        if (vectOrd.isEmpty()) {

            JOptionPane.showMessageDialog(null, "لم يتم الحصول علي اية مهمة");
        } else {

            ValLastOrientNord100 = -1;
            ValLastOrientSud100 = -1;
            ValLastOrientNord25 = -1;
            ValLastOrientSud25 = -1;

            for (String NumOrdMission : vectOrd) {

                ValNord = 0;
                ValSud = 0;

                int ValNordZone = ordission_obj.GetZONE_Stat(Integer.parseInt(NumOrdMission));
                ReductionValue = ordission_obj.Get_Porcentage(Integer.parseInt(NumOrdMission));
                if (ValNordZone == 1) {
                    ValNord = 1;
                } else {
                    ValSud = 1;
                }

                //JOptionPane.showMessageDialog(null , "Nord : "+ValNord+" AND ValSud : "+ValSud+" ReductionValue "+ReductionValue);
                if ((ValLastOrientNord100 == -1) && (ValLastOrientSud100 == -1) && (ValLastOrientNord25 == -1) && (ValLastOrientSud25 == -1)) {

                    switch (ReductionValue) {
                        case 0:
                            if (ValNord == 1) {
                                ValLastOrientNord25 = 1;

                            } else {
                                ValLastOrientSud25 = 1;
                            }
                            break;
                        case 1:
                            if (ValNord == 1) {
                                ValLastOrientNord100 = 1;
                            } else {
                                ValLastOrientSud100 = 1;
                            }
                            break;
                    }

                } else {
                    if (ReductionValue == 1 && ValNord == 1) {
                        if (ValLastOrientNord25 == 1) {
                            JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientNord25=" + ValLastOrientNord25 + " you are Choice Reduction and Nord");
                        } else {
                            ValLastOrientNord100 = 1;

                        }

                    } else if (ReductionValue == 1 && ValSud == 1) {
                        //if (ValLastOrientSud100==1) {

                        if (ValLastOrientSud25 == 1) {
                            JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientSud25=" + ValLastOrientSud25 + " you are Choice Reduction and sud");
                        } else {
                            ValLastOrientSud100 = 1;

                        }
                    } else if (ReductionValue == 0 && ValNord == 1) {
                        if (ValLastOrientNord100 == 1) {
                            JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientNord100=" + ValLastOrientNord100 + " you are Choice Reduction and Nord");

                        } else {
                            ValLastOrientNord25 = 1;
                        }
                    } else if (ReductionValue == 0 && ValSud == 1) {
                        if (ValLastOrientSud100 == 1) {
                            JOptionPane.showMessageDialog(null, "You Cannot add Ord Mission Because ValLastOrientSud100=" + ValLastOrientSud100 + " you are Choice Reduction and Nord");
                        } else {
                            ValLastOrientSud25 = 1;
                        }
                    }
                }

            }

            Calcule_val cl = null;
            int nbrrepat = 0, nbrdecoch = 0;
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
            ordission_obj = new OrdMission();
            Employeur employer_obj = new Employeur();
            cl = new Calcule_val();
            /**
             * **********************************************************
             */
            //  int i=0;

            employer_obj = new Employeur();
            employer_obj.GetInformationEmployer(Integer.parseInt(jLabel81.getText()));
            employer_obj.setId_Emp(Integer.parseInt(jLabel81.getText()));
            //employer_obj.setId_Emp(ordission_obj.getId_emp());
            Person = employer_obj;
            System.out.println("new Info_Ord");

            Remplir_Info_obj.Remplir_Sheet1(employer_obj.getFirst_Name_Emp() + " " + employer_obj.getLast_Name_Emp(), "10/10/2019", employer_obj.getGrad_Emp(), employer_obj.getFun_Emp(),
                    employer_obj.getSem_Num_Emp(), employer_obj.getResidance_Emp(),
                    employer_obj.getCCP_Num_Emp(), "", "", "", "", new Date(), "", "", 0, 0);
            System.out.println("Remplir_Sheet1");

            Remplir_Info_obj.Write_In_WorkBook("FileCalcule");
            System.out.println("Remplir_Info_obj.Write_In_WorkBook");

            for (String string : vectOrd) { //for loop to calculate nbr eat
                ordission_obj.GetAllInformation(Integer.parseInt(string));
                /**
                 * ***************-----------------------------------------------*******
                 */
                System.out.println("View.Home.jButton18ActionPerformed()");
                //JOptionPane.showMessageDialog(null, "The Value is "+string);
                try {
                    cl.calcule_eating_dortoire(formatDate.format(ordission_obj.getDateGo()), formatDate.format(ordission_obj.getDateBack()),
                            formatTime.format(ordission_obj.getHeurDepart()), formatTime.format(ordission_obj.getHeurRetour()));
                    nbrrepat = cl.getNbreRepat();
                    nbrdecoch = cl.getNbreDortoire();
                } catch (ParseException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
                Employeur_Info = new Info_Ord(FirstName.getText(), LastName.getText(),
                        "10/10/2009", employer_obj.getGrad_Emp(), employer_obj.getFun_Emp(), employer_obj.getSem_Num_Emp(), employer_obj.getResidance_Emp(),
                        employer_obj.getCCP_Num_Emp(), new Task_Mission().Get_Task(ordission_obj.getId_task()), employer_obj.getResidance_Emp(), helper.GetDistinataire(ordission_obj.getId_dest()),
                        new Voiture().getNameMoyTrans(ordission_obj.getID_Voiture()), formatDate.format(ordission_obj.getDateGo()), formatTime.format(ordission_obj.getHeurDepart()),
                        formatDate.format(ordission_obj.getDateBack()), formatTime.format(ordission_obj.getHeurRetour()), nbrrepat, nbrdecoch, ordission_obj.ID_Direct_Zone(ordission_obj.getId_dest()), ordission_obj.getNum_OrdMission() + "");

                //   JOptionPane.showMessageDialog(null,Nam_Emp_PanClc.getText()+""+Last_Nam_Emp_PanClc.getText()+" "+employer_obj.getGrad_Emp()+" "+employer_obj.getFun_Emp()+" "+employer_obj.getSem_Num_Emp());    
                // JOptionPane.showMessageDialog(null,new Task_Mission().Get_Task(ordission_obj.getId_task())+" "+employer_obj.getResidance_Emp()+" "+helper.GetDistinataire(ordission_obj.getId_dest()));    
                // JOptionPane.showMessageDialog(null,new Voiture().getNameMoyTrans(ordission_obj.getID_Voiture())+" "+formatDate.format(ordission_obj.getDateGo())+" "+ formatTime.format(ordission_obj.getHeurDepart()));    
                // JOptionPane.showMessageDialog(null,""+formatDate.format(ordission_obj.getDateBack())+" "+formatTime.format(ordission_obj.getHeurRetour())+" "+nbrrepat+" "+nbrdecoch+" "+1+" "+ordission_obj.getNum_OrdMission()+"");
                Remplir_Info_obj.Inisialise_Sheet2();
                Remplir_Info_obj.Insialise_ReferenceSh2();
                System.out.println("Remplir_Info_obj.Inisialise_Sheet2()");

                Remplir_Info_obj.Remplir_Sheet2(Employeur_Info.getCauseTravel(), Employeur_Info.getDepart_Demarer(),
                        Employeur_Info.getDestinataire(), Employeur_Info.getDateGo(), Employeur_Info.getHeur_Go(),
                        Employeur_Info.getDateBack(), Employeur_Info.getHeur_Back(),
                        Employeur_Info.getMoyenTrnsport(), 0, 0, 0, 0,
                        Employeur_Info.getCompensationEat(), Employeur_Info.getCompensationEat(), Employeur_Info.getCompensationDrt(), Employeur_Info.getCompensationDrt(), Employeur_Info.getRemarque(),
                        Employeur_Info.getOrientation());
                System.out.println("Remplir_Info_obj.Remplir_Sheet2");
                // Remplir_Info_obj.Write_In_WorkBook(Employeur_Info.getFirstName()+" "+Employeur_Info.getLastName());
                Remplir_Info_obj.Write_In_WorkBook("FileCalcule");
                Remplir_Info_obj.setNum_Line((Remplir_Info_obj.GetNum_Line() + 1));
            }
            /**
             * ****************************************************
             */
            NumberORdMission = vectOrd.size();
            //JOptionPane.showMessageDialog(null, "The Size is NumberORdMission :"+NumberORdMission);
            if (NumberORdMission == 1) {// just one Ord Mission
                Remplir_Info_obj.RemplirSomDrt();// calculate Some Number Repat And Some Number Dortoir All Mission
                //Remplir_Info_obj.SumCompensationTransport();  //Calcule Some Of Price Of Transport 
                if (ReductionValue == 1) {        // if Mission is 100%
                    if (ValNord == 1) {    //This for Choice Of Nord 
                        //JOptionPane.showMessageDialog(null, "The Nord Orientation "); 
                        Remplir_Info_obj.GetNbrCompensationNrd(); //for deplace Value of Some to sheet 1 to calculate 
                        GetPriceEatANDDecocher(NumberORdMission);
                    } else {

                        Remplir_Info_obj.GetNbrCompensationSudForOneMission(1); //This For Deplace Some Repat And Decocher To Calculate : arg =1: Just One OrdMission in p18 / p19 (Repat Sud in p18 and p19) 
                        GetPriceEatANDDecocherForOneMission(0);//                                                       : arg!=1: for 2 Mission Nord And Sude sud in p20 and p21
                    }
                    Remplir_Info_obj.SumCompensationToujours(NumberORdMission, 0, 0);  // ce methode is calculate product of prix * number  AND Calcul SUM
                    Remplir_Info_obj.TotlaSumBenefit();

                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();

                } else {
                    //JOptionPane.showMessageDialog(null, "The ReductionValue is 25%");
                    Remplir_Info_obj.RemplirSomDrt();
                    if (ValNord == 1) {

                        //JOptionPane.showMessageDialog(null, "The Orientation Is Nord ");
                        Remplir_Info_obj.GetNbrCompensationNrdWithReduction(1);
                        //GetPriceEatANDDecocherCForReduction(1);
                        GetPriceEatANDDecocherCForReduction(1);
                        Remplir_Info_obj.SumCompensationToujoursForReduction();
                    } else {
                        //JOptionPane.showMessageDialog(null, "The Orientation Is Sud ");
                        Remplir_Info_obj.GetNbrCompensationNrdWithReduction(0);
                        // GetPriceEatANDDecocherCForReduction(1);
                        GetPriceEatANDDecocherCForReduction(1);
                        Remplir_Info_obj.SumCompensationToujoursForReduction();
                        //Remplir_Info_obj.CalculePrix_25();
                    }

                    //Remplir_Info_obj.SumCompensationToujours();  // ce methode is calculate product of prix * number  AND Calcul SUM
                    //new Code 
                    Remplir_Info_obj.InitialiseCellReductionPrice(NumberORdMission, 0, 0);
                    Remplir_Info_obj.TotlaSumBenefit();

                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();

                }

            } else {

                /**
                 * *****************************************************
                 */
                Remplir_Info_obj.RemplirSomDrt();

                if ((ValLastOrientSud100 == 1 || ValLastOrientSud25 == 1) && ValLastOrientNord100 == -1 && ValLastOrientNord25 == -1) {

                    JOptionPane.showMessageDialog(null, "ValLastOrientSud100==1||ValLastOrientSud25==1) &&ValLastOrientNord100==-1 && ValLastOrientNord25==-1 ");

                    //deplace sud number to first Cellss 
                    if (ValLastOrientSud100 == 1) {

                        JOptionPane.showMessageDialog(null, "The ValLastOrientSud100==1 ");
                        Remplir_Info_obj.GetNbrCompensationSudForOneMission(1);
                        GetPriceEatANDDecocherForOneMission(0);
                        //Remplir_Info_obj.SumCompensationToujours(NumberORdMission);  // ce methode is calculate product of prix * number  AND Calcul SUM
                        Remplir_Info_obj.SumCompensationToujours(1, 0, 0); //because just on orientation (1 is condition methode SumCompensationToujours so we dont need N20 and N21)
                        Remplir_Info_obj.TotlaSumBenefit();

                        Remplir_Info_obj.SumTransport_and_compensationTtl();
                        //Remplir_Info_obj.ChangeThisNumber();
                        Remplir_Info_obj.Date_Delivred();
                    } else {    //ValLastOrientSud25==1

                        JOptionPane.showMessageDialog(null, "the ValLastOrientSud25==1 ");

                        Remplir_Info_obj.GetNbrCompensationNrdWithReduction(0);
                        // GetPriceEatANDDecocherCForReduction(1);
                        GetPriceEatANDDecocherForOneMission(0);
                        Remplir_Info_obj.SumCompensationToujoursForReduction();
                        Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), 1, 0);
                        Remplir_Info_obj.TotlaSumBenefit();
                        Remplir_Info_obj.SumTransport_and_compensationTtl();
                        //Remplir_Info_obj.ChangeThisNumber();
                        Remplir_Info_obj.Date_Delivred();
                    }
                    /**
                     * ************************************************************************
                     */
                } else if ((ValLastOrientNord100 == 1 || ValLastOrientNord25 == 1) && ValLastOrientSud100 == -1 && ValLastOrientSud25 == -1) {

                    JOptionPane.showMessageDialog(null, "ValLastOrientNord100==1||ValLastOrientNord25==1) &&ValLastOrientSud100==-1 && ValLastOrientSud25==-1");

                    if (ValLastOrientNord100 == 1) {

                        JOptionPane.showMessageDialog(null, "the  ValLastOrientNord100==1");

                        // Remplir_Info_obj.GetNbrCompensationSudForOneMission(1);
                        Remplir_Info_obj.GetNbrCompensationNrd();
                        GetPriceEatANDDecocherForOneMission(1);
                        //Remplir_Info_obj.SumCompensationToujours(NumberORdMission);  // ce methode is calculate product of prix * number  AND Calcul SUM
                        Remplir_Info_obj.SumCompensationToujours(1, 0, 0); //because just on orientation (1 is condition methode SumCompensationToujours so we dont need N20 and N21)
                        Remplir_Info_obj.TotlaSumBenefit();

                        Remplir_Info_obj.SumTransport_and_compensationTtl();
                        //Remplir_Info_obj.ChangeThisNumber();
                        Remplir_Info_obj.Date_Delivred();

                    } else {    //ValLastOrientNord25==1
                        //Remplir_Info_obj.GetNbrCompensationSudForOneMission(1);
                        JOptionPane.showMessageDialog(null, "the  ValLastOrientSud100==1");

                        Remplir_Info_obj.GetNbrCompensationNrd();
                        GetPriceEatANDDecocherForOneMission(1);
                        Remplir_Info_obj.SumCompensationToujoursForReduction();
                        Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), 1, 0);
                        Remplir_Info_obj.TotlaSumBenefit();
                        Remplir_Info_obj.SumTransport_and_compensationTtl();
                        //Remplir_Info_obj.ChangeThisNumber();
                        Remplir_Info_obj.Date_Delivred();
                    }
                } else if (ValLastOrientNord100 == 1 && ValLastOrientSud25 == 1) {  //nord 100% and sud 25%

                    JOptionPane.showMessageDialog(null, "the ValLastOrientNord100==1 && ValLastOrientSud25==1 ");

                    Remplir_Info_obj.GetNbrCompensationNrd();
                    Remplir_Info_obj.GetNbrCompensationSud();
                    GetPriceEatANDDecocher(vectOrd.size());
                    Remplir_Info_obj.SumCompensationToujours(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                    Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                    Remplir_Info_obj.TotlaSumBenefit();
                    Remplir_Info_obj.SumTransport_and_compensationTtl();

                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                } else if (ValLastOrientSud100 == 1 && ValLastOrientNord25 == 1) { //nord 25% and Sud 100%

                    JOptionPane.showMessageDialog(null, "the ValLastOrientSud100==1 && ValLastOrientNord25==1 ");

                    Remplir_Info_obj.GetNbrCompensationNrd();
                    Remplir_Info_obj.GetNbrCompensationSud();
                    GetPriceEatANDDecocher(vectOrd.size());
                    Remplir_Info_obj.SumCompensationToujours(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                    Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);

                    Remplir_Info_obj.TotlaSumBenefit();
                    Remplir_Info_obj.SumTransport_and_compensationTtl();

                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                } else if (ValLastOrientSud25 == 1 && ValLastOrientNord25 == 1) {    //two oprientation different and 25%

                    JOptionPane.showMessageDialog(null, "ValLastOrientSud25==1 && ValLastOrientNord25==1");

                    Remplir_Info_obj.GetNbrCompensationNrd();
                    Remplir_Info_obj.GetNbrCompensationSud();
                    GetPriceEatANDDecocher(vectOrd.size());
                    Remplir_Info_obj.SumCompensationToujours(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                    Remplir_Info_obj.InitialiseCellReductionPrice(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);

                    Remplir_Info_obj.TotlaSumBenefit();
                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                } else {   //Remplir_Info_obj.Inisialise_Sheet1();
                    JOptionPane.showMessageDialog(null, "else ********************** else");
                    Remplir_Info_obj.GetNbrCompensationNrd();
                    Remplir_Info_obj.GetNbrCompensationSud();
                    GetPriceEatANDDecocher(vectOrd.size());
                    Remplir_Info_obj.SumCompensationToujours(vectOrd.size(), ValLastOrientNord25, ValLastOrientSud25);
                    Remplir_Info_obj.TotlaSumBenefit();
                    Remplir_Info_obj.SumTransport_and_compensationTtl();
                    //Remplir_Info_obj.ChangeThisNumber();
                    Remplir_Info_obj.Date_Delivred();
                }
            }

            Remplir_Info_obj.Write_In_WorkBook("FileCalcule");
            try {
                Desktop dt = Desktop.getDesktop();
                // dt.open(new File("src\\OurFile\\AppClose.xlsx"));
                dt.open(new File("FileCalcule" + ".xlsx"));
                //  dt.open(new File(""+FullNam.getText()+".xlsx"));

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error in Opened The File");
            }

            Remplir_Info_obj.setNum_Line(8);
            ValLastOrientNord100 = -1;
            ValLastOrientSud100 = -1;
            ValLastOrientNord25 = -1;
            ValLastOrientSud25 = -1;
            ValSud = 0;
            ValNord = 0;
            vectOrd.clear();
            dflist.clear();
            ListOrdMission.setModel(dflist);
            Remplir_Info_obj.setNum_Line(8);
        }

    }//GEN-LAST:event_jButton44ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed

    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        ListTableDepense_NumDep.addElement("" + Depanse_Tab_Eng.getValueAt(Depanse_Tab_Eng.getSelectedRow(), 3));
        ListTabDps.setModel(ListTableDepense_NumDep);

        ListTableDepense_Person.addElement(Depanse_Tab_Eng.getValueAt(Depanse_Tab_Eng.getSelectedRow(), 3) + ":" + Depanse_Tab_Eng.getValueAt(Depanse_Tab_Eng.getSelectedRow(), 2));
        ListTab_DepPrsn.setModel(ListTableDepense_Person);


    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        DefaultListModel dflistofOrd = (DefaultListModel) ListOrdMission.getModel();
        int sizeList = dflistofOrd.size();
        vectOrd.clear();
        Engagement_Opr.getList_OrdMission().clear();

        /**
         * ***********************************************
         */
        int i = 0;
        while (i < sizeList) {
            vectOrd.add(dflistofOrd.get(i) + "");
            System.out.println("NumOrd Mission is :" + (String) dflistofOrd.get(i));
            i++;
        }

        Engagement_Opr.Initialise_OrdMission_to_TabDepense((int) Depanse_Tab_Eng.getValueAt(Depanse_Tab_Eng.getSelectedRow(), 3));
        /**
         * ************************************************
         */
        for (String Num_Ord : vectOrd) {
            Engagement_Opr.getList_OrdMission().add(Integer.parseInt(Num_Ord));
        }
        Engagement_Opr.UpdateTab_Depense((int) Depanse_Tab_Eng.getValueAt(Depanse_Tab_Eng.getSelectedRow(), 3));

    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed

    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        Engagement_Opr.Initialise_OrdMission_to_TabDepense((int) Depanse_Tab_Eng.getValueAt(Depanse_Tab_Eng.getSelectedRow(), 3));
        Engagement_Opr.Delete_TabDep((int) Depanse_Tab_Eng.getValueAt(Depanse_Tab_Eng.getSelectedRow(), 3));
        Engagement_Opr.Fill_Table_Depense(Depanse_Tab_Eng);
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        dflist.clear();
        ValLastOrientNord100 = -1;
        ValLastOrientSud100 = -1;
        ValLastOrientNord25 = -1;
        ValLastOrientSud25 = -1;
        ListOrdMission.setModel(dflist);
        Engagement_Opr.getList_OrdMission().clear();
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        dflist.clear();
        ValLastOrientNord100 = -1;
        ValLastOrientSud100 = -1;
        ValLastOrientNord25 = -1;
        ValLastOrientSud25 = -1;
        IntiPriceStatOrd();
        vectOrd.clear();
        Engagement_Opr.getList_OrdMission().clear();
        dflist.clear();
        ListOrdMission.setModel(dflist);


    }//GEN-LAST:event_jButton51ActionPerformed

    private void TxtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSearchKeyReleased
        FilterEmployer(TxtSearch.getText(), Tab_InfoEmp, (DefaultTableModel) Tab_InfoEmp.getModel());
    }//GEN-LAST:event_TxtSearchKeyReleased
    int ValLastOrientNord100 = -1;
    int ValLastOrientSud100 = -1;
    int ValLastOrientNord25 = -1;
    int ValLastOrientSud25 = -1;

    public void InistialiseInformationEmployer() {
        Reg_Name.setText("اسم الموظف");
        Reg_Name.setForeground(new Color(153, 153, 153));
        //لقب الموظف
        Reg_LastName.setText("لقب الموظف");
        Reg_LastName.setForeground(new Color(153, 153, 153));
        //
        Reg_CategNum.setText("درجة الموظف");
        Reg_CategNum.setForeground(new Color(153, 153, 153));
        //
        Reg_NumSemt.setText("الرقـــم الاستــدلالي");
        Reg_NumSemt.setForeground(new Color(153, 153, 153));
        //الوظيفة
        /*Reg_Jop.setText("الوظيفة");
    Reg_Jop.setForeground(new Color(153, 153, 153));*/
        //
        Reg_CCP.setText("رقم الحساب الجاري");
        Reg_CCP.setForeground(new Color(153, 153, 153));
        //
        Reg_Residence.setText("بسكرة");
        Reg_Residence.setForeground(new Color(153, 153, 153));
        ChoiceGrd.select(0);

    }

    public void remplire_Champ_Wt_Tbl(String Nm_And_Prm, String CCp_Emp, String GradeEmp, String FunctionEmp, String Num_SemantiquePr) {
        FullNam.setText(Nm_And_Prm);
        FullNam.setForeground(Color.black);
        Num_CCP.setText(CCp_Emp);
        Num_CCP.setForeground(Color.black);
        Grad.setText(GradeEmp);
        Grad.setForeground(Color.black);
        Job.setText(FunctionEmp);
        Job.setForeground(Color.black);
        Num_Semantique.setText(Num_SemantiquePr);
        Num_Semantique.setForeground(Color.black);
        Residence.setText("بسكرة");
        Residence.setForeground(Color.black);
        //this.MaximaizeMinimize(0);

    }

    public void FilterEmployer(String Query, JTable tab, DefaultTableModel dm) {  //filtrer dans le tableau fournisseur
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        tab.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(Query));

    }

    public void Remplir_FileExcel() {
        Nom = FullNam.getText();
        Workbook wb = new HSSFWorkbook();
        HSSFSheet feuille = (HSSFSheet) wb.getSheet("j");
    }

    //public void Remplir_
    public void remplireTableInf2() {
        Inistialised();
        PersonRemplissage = new Employeur();
        PersonRemplissage.AfficheIntable(ModelTable2);
        Tab_InfoEmp.setModel(ModelTable2);

    }

    public void Remplir_Table() {
        Obj_Cnx.connectSqlServer();
        try {
            stm = Obj_Cnx.getCnx().createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error in create Statment ...");
        }

        try {
            res = stm.executeQuery("SELECT * FROM Employeur ");
            while (res.next()) {
                Object arg[] = {res.getString("Num_Sem"), res.getString("Grad_Emp"), res.getString("Fun_Emp"), res.getString("CCP_Emp"),
                    res.getString("Last_Nm_Emp"), res.getString("Name_Emp"), res.getInt("ID_Emp")};
                ModelTable.addRow(arg);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in execut Query Function Remplir_Table()" + e.getMessage());
        }
        try {
            Obj_Cnx.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in deconnect Database ...");
        }

    }

    public void GetPriceEatANDDecocherDCW() {

        Connection_DB cnx = new Connection_DB();
        Statement stm = null;
        ResultSet res = null;
        int NbrRpNrd = Remplir_Info_obj.GetNumberRepatNord(); //get number repat of nord sheet 2
        if (NbrRpNrd > 0) {
            String requette = " SELECT Prix_Repat.valeur_Repat,Employeur.Name_Emp \n"
                    + "FROM Employeur,Categorie,Prix_Repat,Prix_Repat_Categorie,Direction\n"
                    + "\n"
                    + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=1 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                    + "      Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat AND\n"
                    + "	 Prix_Repat.ID_Direct_Zone=Direction.ID_Direct_Zone";

//nouveau requette                    
            requette = "select valeur_Repat \n"
                    + "from Prix_Repat,Prix_Repat_Categorie,Categorie,Direction,Employeur,Grade\n"
                    + "where \n"
                    + "Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n"
                    + "AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n"
                    + "AND Prix_Repat_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                    + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                    + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                    + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                    + "AND Direction.ID_Direct_Zone=3";

            try {
                cnx.connectSqlServer();
                stm = cnx.getCnx().createStatement();
                res = stm.executeQuery(requette);
                res.next();
                //double valeurInt= res.getDouble("valeur_Repat");

                // System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+NbrRpNrd  +"AND Price :"+valeurInt );
                // Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
                Remplir_Info_obj.RemplirePriceOrdMissionNordEat(res.getDouble("valeur_Repat")); //for insert price of 
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher Eat nORD" + ex.getMessage());

                ex.printStackTrace();
            }
            try {
                stm.close();
                res.close();
                cnx.Deconnect();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error in Close Operation :" + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    /**
     * *************************************************************************
     */
    public void GetPriceEatANDDecocher(int Nord_Sud) {

        Connection_DB cnx = new Connection_DB();
        Statement stm = null;
        ResultSet res = null;
        int NbrRpNrd = Remplir_Info_obj.GetNumberRepatNord(); //get number repat of nord sheet 2
        if (NbrRpNrd > 0) {
            String requette = " SELECT Prix_Repat.valeur_Repat,Employeur.Name_Emp \n"
                    + "FROM Employeur,Categorie,Prix_Repat,Prix_Repat_Categorie,Direction\n"
                    + "\n"
                    + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=1 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                    + "      Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat AND\n"
                    + "	 Prix_Repat.ID_Direct_Zone=Direction.ID_Direct_Zone";

//nouveau requette                    
            requette = "select valeur_Repat \n"
                    + "from Prix_Repat,Prix_Repat_Categorie,Categorie,Direction,Employeur,Grade\n"
                    + "where \n"
                    + "Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n"
                    + "AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n"
                    + "AND Prix_Repat_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                    + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                    + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                    + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                    + "AND Direction.ID_Direct_Zone=1";

            //JOptionPane.showMessageDialog(null, "The Id Employer is "+Person.getId_Emp());
            try {
                cnx.connectSqlServer();
                stm = cnx.getCnx().createStatement();
                res = stm.executeQuery(requette);

                if (res.next()) {
                    Remplir_Info_obj.RemplirePriceOrdMissionNordEat(res.getDouble(1)/*res.getDouble("valeur_Repat")*/); //for insert price of 
                } else {

                    JOptionPane.showMessageDialog(null, "Error In Result");
                }

                //double valeurInt= res.getDouble("valeur_Repat");
                // System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+NbrRpNrd  +"AND Price :"+valeurInt );
                // Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher Eat nord" + ex.getMessage());

                ex.printStackTrace();
            }
            try {
                stm.close();
                res.close();
                cnx.Deconnect();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error in Close Operation :" + e.getMessage());
                e.printStackTrace();
            }
        }
        /**
         * *************************************************************************************
         */
        if (Remplir_Info_obj.GetNumberDecocherNord() > 0) {
            String requette = "SELECT Prix_Decocher.valeur_decocher,Employeur.Name_Emp \n"
                    + "FROM Employeur,Categorie,Prix_Decocher,Prix_Decocher_Categorie,Direction\n"
                    + "\n"
                    + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=1 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                    + "      Categorie.ID_Categorie=Prix_Decocher_Categorie.ID_Categorie AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher AND\n"
                    + "	 Prix_Decocher.ID_Direct_Zone=Direction.ID_Direct_Zone";

            requette = "select valeur_decocher\n"
                    + "from Prix_Decocher,\n"
                    + "Prix_Decocher_Categorie,Categorie,Direction,Employeur,Grade\n"
                    + "where\n"
                    + "Direction.ID_Direct_Zone=Prix_Decocher.ID_Direct_Zone\n"
                    + "AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher\n"
                    + "AND Prix_Decocher_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                    + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                    + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                    + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                    + "AND Direction.ID_Direct_Zone=1";

            try {
                cnx.connectSqlServer();
                stm = cnx.getCnx().createStatement();
                res = stm.executeQuery(requette);
                res.next();
                //double valeurInt= res.getDouble("valeur_decocher");
                //res.getDouble("valeur_Repat");
                //Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
                Remplir_Info_obj.RemplirePriceOrdMissionNordDecocher(res.getDouble("valeur_decocher"));//For enter Price Decochrer

                //System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+Remplir_Info_obj.GetNumberDecocherNord()  +"AND Price :"+valeurInt );
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher  DECOCHER nord " + ex.getMessage());
            }
            try {
                stm.close();
                res.close();

                cnx.Deconnect();
            } catch (Exception e) {
            }
        }
        /**
         * ***********************************************************************
         */

        /**
         * **The New Code **********23/04/2019*
         */
        if (Nord_Sud != 1) {

            if (Remplir_Info_obj.GetNumberRepatSud() > 0) {
                String requette = "SELECT Prix_Repat.valeur_Repat,Employeur.Name_Emp \n"
                        + "FROM Employeur,Categorie,Prix_Repat,Prix_Repat_Categorie,Direction\n"
                        + "\n"
                        + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=2 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                        + "      Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat AND\n"
                        + "	 Prix_Repat.ID_Direct_Zone=Direction.ID_Direct_Zone";

                requette = "select valeur_Repat \n"
                        + "from Prix_Repat,Prix_Repat_Categorie,Categorie,Direction,Employeur,Grade\n"
                        + "where \n"
                        + "Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n"
                        + "AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n"
                        + "AND Prix_Repat_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                        + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                        + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                        + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                        + "AND Direction.ID_Direct_Zone=2";

                try {
                    cnx.connectSqlServer();
                    stm = cnx.getCnx().createStatement();
                    res = stm.executeQuery(requette);
                    res.next();
                    // double valeurInt= res.getDouble("valeur_Repat");
                    //res.getDouble("valeur_Repat");
                    //Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
                    Remplir_Info_obj.RemplirePriceOrdMissionSudEat(res.getDouble("valeur_Repat"));

                    //System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+Remplir_Info_obj.GetNumberRepatSud()  +"AND Price :");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher eAT sud " + ex.getMessage());
                }
                try {
                    stm.close();
                    res.close();

                    cnx.Deconnect();
                } catch (Exception e) {
                }
            }
            /**
             * **********************************************************************
             */
            if (Remplir_Info_obj.GetNumberDecocherSud() > 0) {
                String requette = "SELECT Prix_Decocher.valeur_decocher,Employeur.Name_Emp \n"
                        + "FROM Employeur,Categorie,Prix_Decocher,Prix_Decocher_Categorie,Direction\n"
                        + "\n"
                        + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=2 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                        + "      Categorie.ID_Categorie=Prix_Decocher_Categorie.ID_Categorie AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher AND\n"
                        + "	 Prix_Decocher.ID_Direct_Zone=Direction.ID_Direct_Zone";

                requette = "select valeur_decocher\n"
                        + "from Prix_Decocher,\n"
                        + "Prix_Decocher_Categorie,Categorie,Direction,Employeur,Grade\n"
                        + "where\n"
                        + "Direction.ID_Direct_Zone=Prix_Decocher.ID_Direct_Zone\n"
                        + "AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher\n"
                        + "AND Prix_Decocher_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                        + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                        + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                        + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                        + "AND Direction.ID_Direct_Zone=2";

                try {
                    cnx.connectSqlServer();
                    stm = cnx.getCnx().createStatement();
                    res = stm.executeQuery(requette);
                    res.next();
                    //double valeurInt= res.getFloat("valeur_decocher");
                    //res.getDouble("valeur_Repat");
                    // Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
                    Remplir_Info_obj.RemplirePriceOrdMissionSudDecocher(res.getDouble("valeur_decocher"));
                    //System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+Remplir_Info_obj.GetNumberDecocherSud()  +"AND Price :" );

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher DecocheSUD" + ex.getMessage());
                }
                try {
                    stm.close();
                    res.close();

                    cnx.Deconnect();
                } catch (Exception e) {
                }

            }

        }

    }

    /**
     * ***************************************************************************************
     */
    public void GetPriceEatANDDecocherCForReduction(int Reduction) {
        Connection_DB cnx = new Connection_DB();
        Statement stm = null;
        ResultSet res = null;

        if (ValNord == 1) {
            int NbrRpNrd = Remplir_Info_obj.GetNumberRepatNord();
            if (NbrRpNrd > 0) {
                String requette = "SELECT Prix_Repat.valeur_Repat,Employeur.Name_Emp \n"
                        + "FROM Employeur,Categorie,Prix_Repat,Prix_Repat_Categorie,Direction\n"
                        + "\n"
                        + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=1 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                        + "      Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat AND\n"
                        + "	 Prix_Repat.ID_Direct_Zone=Direction.ID_Direct_Zone";

                requette = "select valeur_Repat \n"
                        + "from Prix_Repat,Prix_Repat_Categorie,Categorie,Direction,Employeur,Grade\n"
                        + "where \n"
                        + "Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n"
                        + "AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n"
                        + "AND Prix_Repat_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                        + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                        + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                        + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                        + "AND Direction.ID_Direct_Zone=1";

                try {
                    cnx.connectSqlServer();
                    stm = cnx.getCnx().createStatement();
                    res = stm.executeQuery(requette);
                    if (res.next()) {
                        Remplir_Info_obj.RemplirePriceOrdMissionNordEat(res.getDouble("valeur_Repat"));
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error Sql Number Repat Nord" + ex.getMessage());
                }
                try {
                    stm.close();
                    res.close();
                    cnx.Deconnect();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error in Close Operation :" + e.getMessage());
                }
            }
            /**
             * *************************************************************************************
             */
            if (Remplir_Info_obj.GetNumberDecocherNord() > 0) {
                String requette = "SELECT Prix_Decocher.valeur_decocher,Employeur.Name_Emp FROM Employeur,Categorie,Prix_Decocher,Prix_Decocher_Categorie,Direction\n"
                        + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=1 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                        + "      Categorie.ID_Categorie=Prix_Decocher_Categorie.ID_Categorie AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher AND\n"
                        + "	 Prix_Decocher.ID_Direct_Zone=Direction.ID_Direct_Zone";

                requette = "select valeur_decocher\n"
                        + "from Prix_Decocher,\n"
                        + "Prix_Decocher_Categorie,Categorie,Direction,Employeur,Grade\n"
                        + "where\n"
                        + "Direction.ID_Direct_Zone=Prix_Decocher.ID_Direct_Zone\n"
                        + "AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher\n"
                        + "AND Prix_Decocher_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                        + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                        + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                        + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                        + "AND Direction.ID_Direct_Zone=1";

                try {
                    cnx.connectSqlServer();
                    stm = cnx.getCnx().createStatement();
                    res = stm.executeQuery(requette);
                    if (res.next()) {
                        Remplir_Info_obj.RemplirePriceOrdMissionNordDecocher(res.getDouble("valeur_decocher"));
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error Sql Number Decocher Nord" + ex.getMessage());
                }
                try {
                    stm.close();
                    res.close();
                    cnx.Deconnect();
                } catch (SQLException e) {
                }
            }
        } else {
            //JOptionPane.showMessageDialog(null, "The NumberRepatSudForReduction"+Remplir_Info_obj.GetNumberRepatSudForReduction());
            if (Remplir_Info_obj.GetNumberRepatSudForReduction() > 0) {
                String requette = "SELECT Prix_Repat.valeur_Repat,Employeur.Name_Emp \n"
                        + "FROM Employeur,Categorie,Prix_Repat,Prix_Repat_Categorie,Direction\n"
                        + "\n"
                        + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=2 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                        + "      Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat AND\n"
                        + "	 Prix_Repat.ID_Direct_Zone=Direction.ID_Direct_Zone";

                requette = "select valeur_Repat \n"
                        + "from Prix_Repat,Prix_Repat_Categorie,Categorie,Direction,Employeur,Grade\n"
                        + "where \n"
                        + "Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n"
                        + "AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n"
                        + "AND Prix_Repat_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                        + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                        + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                        + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                        + "AND Direction.ID_Direct_Zone=2";

                try {
                    cnx.connectSqlServer();
                    stm = cnx.getCnx().createStatement();
                    res = stm.executeQuery(requette);
                    if (res.next()) {
                        Remplir_Info_obj.RemplirePriceOrdMissionNordEatForReduction(res.getFloat("valeur_Repat"));
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error Sql Number Repat Sud" + ex.getMessage());
                }
                try {
                    stm.close();
                    res.close();
                    cnx.Deconnect();
                } catch (SQLException e) {
                }
            }
            /**
             * **********************************************************************
             */
            if (Remplir_Info_obj.GetNumberDecocherSudForReduction() > 0) {
                String requette = "SELECT Prix_Decocher.valeur_decocher,Employeur.Name_Emp \n"
                        + "FROM Employeur,Categorie,Prix_Decocher,Prix_Decocher_Categorie,Direction\n"
                        + "\n"
                        + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=2 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                        + "      Categorie.ID_Categorie=Prix_Decocher_Categorie.ID_Categorie AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher AND\n"
                        + "	 Prix_Decocher.ID_Direct_Zone=Direction.ID_Direct_Zone";

                requette = "select valeur_decocher\n"
                        + "from Prix_Decocher,\n"
                        + "Prix_Decocher_Categorie,Categorie,Direction,Employeur,Grade\n"
                        + "where\n"
                        + "Direction.ID_Direct_Zone=Prix_Decocher.ID_Direct_Zone\n"
                        + "AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher\n"
                        + "AND Prix_Decocher_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                        + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                        + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                        + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                        + "AND Direction.ID_Direct_Zone=2";

                try {
                    cnx.connectSqlServer();
                    stm = cnx.getCnx().createStatement();
                    res = stm.executeQuery(requette);
                    if (res.next()) {
                        Remplir_Info_obj.RemplirePriceOrdMissionNordDecocherForReduction(res.getDouble("valeur_decocher"));
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error Sql Number Decocher Sud" + ex.getMessage());
                }
                try {
                    stm.close();
                    res.close();
                    cnx.Deconnect();
                } catch (SQLException e) {
                }
            }
        }
    }

    /**
     * ***************************Inisialisee
     *
     * @param Case
     * @param jPanel13
     * @param ch_pa
     */
    public void enablePanelInformation(boolean Case, JPanel jPanel13, int ch_pa) {
        for (Component Comp : jPanel13.getComponents()) {

            if (ch_pa == 0) {
                if (Comp instanceof JTextField) {
                    Comp.setEnabled(Case);
                }
            } else {
                Comp.setEnabled(Case);
            }

        }
    }

    public boolean ErrorConstroleSaisieOrdMiss() {

        if ((!ChexNord0.isSelected() && !ChexSud1.isSelected())
                || Car_Travel.getSelectedItem().equals("اختر وسيـــــــلة التنقل")
                || ListDestainataire.getSelectedItem().equals("اختر الولاية ...")
                || jDateChGo1.getDate() == null
                || jDateChBack1.getDate() == null
                || ((jDateChGo1.getDate() != null && jDateChBack1.getDate() != null)) && !(jDateChBack1.getDate().after(jDateChGo1.getDate()))
                || RemarqueTxt.getText().equals("")
                || FullNam.getText().equals("")
                || FullNam.getText().equals("ادخل اسم الموظف")) {

            //JOptionPane.showMessageDialog(null, "I Catch A Error");
            return true;
            //return false;
        } else {
            return false;
        }

    }

    public void ChoixPanSrvdetaille(JPanel parent, JPanel fils) {
        int i = 0;
        fils.setVisible(true);
        while (i < parent.getComponentCount()) {

            if (!parent.getComponent(i).equals(fils)) {

                parent.getComponent(i).setVisible(false);
            }
            i++;
        }

    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Home().setVisible(true);

            }
        });
    }

    public void GetPriceEatANDDecocherForOneMission(int c) {
        Connection_DB cnx = new Connection_DB();
        Statement stm = null;
        ResultSet res = null;

        if (c != 0) {  //0for nord 
            int NbrRpNrd = Remplir_Info_obj.GetNumberRepatNord();
            if (NbrRpNrd > 0) {
                String requette = "SELECT Prix_Repat.valeur_Repat,Employeur.Name_Emp \n"
                        + "FROM Employeur,Categorie,Prix_Repat,Prix_Repat_Categorie,Direction\n"
                        + "\n"
                        + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=1 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                        + "      Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat AND\n"
                        + "	 Prix_Repat.ID_Direct_Zone=Direction.ID_Direct_Zone";

                requette = "select valeur_Repat \n"
                        + "from Prix_Repat,Prix_Repat_Categorie,Categorie,Direction,Employeur,Grade\n"
                        + "where \n"
                        + "Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n"
                        + "AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n"
                        + "AND Prix_Repat_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                        + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                        + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                        + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                        + "AND Direction.ID_Direct_Zone=1";

                try {
                    cnx.connectSqlServer();
                    stm = cnx.getCnx().createStatement();
                    res = stm.executeQuery(requette);
                    if (res.next()) {
                        Remplir_Info_obj.RemplirePriceOrdMissionNordEat(res.getDouble("valeur_Repat"));
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher Eat nORD" + ex.getMessage());
                }
                try {
                    stm.close();
                    res.close();
                    cnx.Deconnect();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error in Close Operation :" + e.getMessage());
                }
            }
            /**
             * *************************************************************************************
             */
            if (Remplir_Info_obj.GetNumberDecocherNord() > 0) {
                String requette = "SELECT Prix_Decocher.valeur_decocher,Employeur.Name_Emp \n"
                        + "FROM Employeur,Categorie,Prix_Decocher,Prix_Decocher_Categorie,Direction\n"
                        + "\n"
                        + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=1 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                        + "      Categorie.ID_Categorie=Prix_Decocher_Categorie.ID_Categorie AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher AND\n"
                        + "	 Prix_Decocher.ID_Direct_Zone=Direction.ID_Direct_Zone";

                requette = "select valeur_decocher\n"
                        + "from Prix_Decocher,\n"
                        + "Prix_Decocher_Categorie,Categorie,Direction,Employeur,Grade\n"
                        + "where\n"
                        + "Direction.ID_Direct_Zone=Prix_Decocher.ID_Direct_Zone\n"
                        + "AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher\n"
                        + "AND Prix_Decocher_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                        + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                        + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                        + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                        + "AND Direction.ID_Direct_Zone=1";

                try {
                    cnx.connectSqlServer();
                    stm = cnx.getCnx().createStatement();
                    res = stm.executeQuery(requette);
                    res.next();
                    //double valeurInt= res.getDouble("valeur_decocher");
                    //res.getDouble("valeur_Repat");
                    //Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
                    Remplir_Info_obj.RemplirePriceOrdMissionNordDecocher(res.getDouble("valeur_decocher"));

                    //System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+Remplir_Info_obj.GetNumberDecocherNord()  +"AND Price :"+valeurInt );
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher  DECOCHER nord " + ex.getMessage());
                }
                try {
                    stm.close();
                    res.close();

                    cnx.Deconnect();
                } catch (Exception e) {
                }
            }
        }

        if (c != 1) {  //!1 for sud 
            if (Remplir_Info_obj.GetNumberRepatSud() > 0) {
                String requette = "SELECT Prix_Repat.valeur_Repat,Employeur.Name_Emp \n"
                        + "FROM Employeur,Categorie,Prix_Repat,Prix_Repat_Categorie,Direction\n"
                        + "\n"
                        + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=2 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                        + "      Categorie.ID_Categorie=Prix_Repat_Categorie.ID_Categorie AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat AND\n"
                        + "	 Prix_Repat.ID_Direct_Zone=Direction.ID_Direct_Zone";

                requette = "select valeur_Repat \n"
                        + "from Prix_Repat,Prix_Repat_Categorie,Categorie,Direction,Employeur,Grade\n"
                        + "where \n"
                        + "Direction.ID_Direct_Zone=Prix_Repat.ID_Direct_Zone\n"
                        + "AND Prix_Repat_Categorie.ID_Prix_Repat=Prix_Repat.ID_Prix_Repat\n"
                        + "AND Prix_Repat_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                        + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                        + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                        + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                        + "AND Direction.ID_Direct_Zone=2";

                try {
                    cnx.connectSqlServer();
                    stm = cnx.getCnx().createStatement();
                    res = stm.executeQuery(requette);
                    res.next();
                    // double valeurInt= res.getDouble("valeur_Repat");
                    //res.getDouble("valeur_Repat");
                    //Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
                    Remplir_Info_obj.RemplirePriceOrdMissionSudEatForOneMission(res.getFloat("valeur_Repat"));

                    //System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+Remplir_Info_obj.GetNumberRepatSud()  +"AND Price :");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher eAT sud " + ex.getMessage());
                }
                try {
                    stm.close();
                    res.close();

                    cnx.Deconnect();
                } catch (Exception e) {
                }
            }
            /**
             * **********************************************************************
             */
            if (Remplir_Info_obj.GetNumberDecocherSud() > 0) {
                String requette = "SELECT Prix_Decocher.valeur_decocher,Employeur.Name_Emp \n"
                        + "FROM Employeur,Categorie,Prix_Decocher,Prix_Decocher_Categorie,Direction\n"
                        + "\n"
                        + "WHERE Employeur.ID_Emp=" + Person.getId_Emp() + " AND Direction.ID_Direct_Zone=2 AND Employeur.ID_Categorie=Categorie.ID_Categorie AND \n"
                        + "      Categorie.ID_Categorie=Prix_Decocher_Categorie.ID_Categorie AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher AND\n"
                        + "	 Prix_Decocher.ID_Direct_Zone=Direction.ID_Direct_Zone";

                requette = "select valeur_decocher\n"
                        + "from Prix_Decocher,\n"
                        + "Prix_Decocher_Categorie,Categorie,Direction,Employeur,Grade\n"
                        + "where\n"
                        + "Direction.ID_Direct_Zone=Prix_Decocher.ID_Direct_Zone\n"
                        + "AND Prix_Decocher_Categorie.ID_Prix_Decocher=Prix_Decocher.ID_Prix_Decocher\n"
                        + "AND Prix_Decocher_Categorie.ID_Categorie=Categorie.ID_Categorie\n"
                        + "AND Categorie.ID_Categorie=Grade.ID_Categorie\n"
                        + "AND Employeur.ID_Grade=Grade.ID_Grade\n"
                        + "AND Employeur.ID_Emp=" + Person.getId_Emp() + "\n"
                        + "AND Direction.ID_Direct_Zone=2";

                try {
                    cnx.connectSqlServer();
                    stm = cnx.getCnx().createStatement();
                    res = stm.executeQuery(requette);
                    res.next();
                    //double valeurInt= res.getFloat("valeur_decocher");
                    //res.getDouble("valeur_Repat");
                    // Remplir_Info_obj.RemplirePriceOrdMissionNord(valeurInt, valeurInt, Orientation);
                    Remplir_Info_obj.RemplirePriceOrdMissionSudDecocherForOneMission(res.getDouble("valeur_decocher"));
                    //System.out.println("gestion_ord_mission.Home.GetPriceEatANDDecocher() NbrRepat :"+Remplir_Info_obj.GetNumberDecocherSud()  +"AND Price :" );

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "error in Fun GetPrice AND GetDococher DecocheSUD" + ex.getMessage());
                    ex.printStackTrace();
                }
                try {
                    stm.close();
                    res.close();
                    cnx.Deconnect();
                } catch (SQLException e) {
                }

            }
        }
        /**
         * ***********************************************************************
         */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Add_Mission_Lab;
    private javax.swing.JPanel BarMenu;
    private javax.swing.JButton BtnCancelTbDep;
    private javax.swing.JButton BtnNewMissTbDep;
    private javax.swing.JRadioButton BtnRdInf50;
    private javax.swing.JRadioButton BtnRdSup50;
    private javax.swing.JRadioButton BtnRdiNrd;
    private javax.swing.JRadioButton BtnRdiNrd1;
    private javax.swing.JRadioButton BtnRdiSud;
    private javax.swing.JRadioButton BtnRdiSud1;
    private javax.swing.JRadioButton BtnRdioinf50;
    private javax.swing.JRadioButton BtnRdioinf51;
    private javax.swing.JButton BtnUpdTbDep;
    private javax.swing.JPanel CarAdminstrator;
    private javax.swing.JPanel CarAdminstrator1;
    private javax.swing.JPanel CarPrivate;
    private javax.swing.JPanel CarPrivate1;
    private javax.swing.JComboBox<String> Car_Travel;
    private javax.swing.JComboBox<String> Car_Travel1;
    private javax.swing.JCheckBox ChexNord0;
    private javax.swing.JCheckBox ChexNord1;
    private javax.swing.JCheckBox ChexSud1;
    private javax.swing.JCheckBox ChexSud2;
    private java.awt.Choice ChoiceGrd;
    private javax.swing.JLabel Clear_Lab;
    private javax.swing.JComboBox<String> CombMonth;
    private javax.swing.JComboBox<String> CombMonth1;
    private javax.swing.JFormattedTextField DateBack;
    private javax.swing.JFormattedTextField DateBack1;
    private javax.swing.JFormattedTextField DateGo;
    private javax.swing.JFormattedTextField DateGo1;
    private javax.swing.JTable Depanse_Tab_Eng;
    private javax.swing.JTextField Depart;
    private javax.swing.JTextField Depart1;
    private javax.swing.JTextField DepuisMois;
    private java.awt.Choice Distinataire;
    private java.awt.Choice Distinataire1;
    private javax.swing.JLabel ExitClsCause;
    private javax.swing.JLabel FinishOrdMission;
    private javax.swing.JTextField FirstName;
    private javax.swing.JTextField FirstName1;
    private javax.swing.JTextField FullNam;
    private javax.swing.JTextField FuncOrdMissCons;
    private javax.swing.JTextField Funct_NamFR;
    private java.awt.Choice Function_Choice;
    private javax.swing.JTextField Grad;
    private javax.swing.JTextField GradOrdMissionCns;
    private javax.swing.JTextField Grade_Empl_txt;
    private javax.swing.JLabel GstEmpl;
    private javax.swing.JSpinner Heur_Back;
    private javax.swing.JSpinner Heur_Back1;
    private javax.swing.JSpinner Heur_Back2;
    private javax.swing.JSpinner Heur_Back3;
    private javax.swing.JSpinner Heur_Go;
    private javax.swing.JSpinner Heur_Go1;
    private javax.swing.JSpinner Heur_Go2;
    private javax.swing.JSpinner Heur_Go3;
    private javax.swing.JComboBox<String> Jcom_CausTrsp;
    private javax.swing.JTextField Job;
    private javax.swing.JLabel LabCompte;
    private javax.swing.JLabel LabJob;
    private javax.swing.JLabel LabRes;
    private javax.swing.JLabel LabTrav;
    private javax.swing.JLabel LabTrav1;
    private javax.swing.JLabel LabVoy;
    private javax.swing.JLabel LabVoy1;
    private javax.swing.JTextField LastName;
    private javax.swing.JTextField LastName1;
    private javax.swing.JComboBox<String> ListDestainataire;
    private javax.swing.JComboBox<String> ListDestainataireCommune;
    private javax.swing.JList<String> ListOrdMission;
    private javax.swing.JList<String> ListTabDps;
    private javax.swing.JList<String> ListTab_DepPrsn;
    private javax.swing.JTextField Mission_Name;
    private java.awt.Choice MoyenTrsp_Miss;
    private java.awt.Choice MoyenTrsp_Miss1;
    private javax.swing.JTextField NamCommune;
    private javax.swing.JTextField NamCommune1;
    private javax.swing.JTextField Nam_Fnct;
    private javax.swing.JLabel NbrDecLab;
    private javax.swing.JLabel NbrDecLabPrc;
    private javax.swing.JLabel NbrRepLab;
    private javax.swing.JLabel NbrRepLabPrc;
    private javax.swing.JLabel New_Mission;
    private javax.swing.JLabel NumItems;
    private javax.swing.JLabel NumOrdLab;
    private javax.swing.JTextField Num_CCP;
    private javax.swing.JTextField Num_OrderMission;
    private javax.swing.JTextField Num_Semantique;
    private javax.swing.JLabel OrdUpd_Lab;
    private javax.swing.JRadioButton OtherCars;
    private javax.swing.JRadioButton OtherCars1;
    private javax.swing.JPanel PanCHoiceRdi;
    private javax.swing.JPanel PanOrdMission;
    private javax.swing.JPanel Pan_Acceul;
    private javax.swing.JPanel Pan_All_Pan_fr_Tab;
    private javax.swing.JPanel Pan_EditOrd;
    private javax.swing.JPanel Pan_Employer;
    private javax.swing.JPanel Pan_Frm_GetOrdEmpl;
    private javax.swing.JPanel Pan_Menu;
    private javax.swing.JPanel Pan_TabEmp;
    private javax.swing.JPanel PaneCarTravel;
    private javax.swing.JPanel PaneCarTravel1;
    private javax.swing.JPanel PlaneTravel;
    private javax.swing.JPanel PlaneTravel1;
    private javax.swing.JPanel PnFdDpnsDtl_InsdPanDateHour;
    private javax.swing.JPanel PnFildsToDpnsDetaill;
    private javax.swing.JPanel PnTabOrdMsstoDpns;
    private javax.swing.JLabel PrcOrMisLab;
    private javax.swing.JLabel PrcTltLab;
    private javax.swing.JRadioButton RadioFull;
    private javax.swing.JRadioButton RadioQurt;
    private javax.swing.JRadioButton RdiPlan;
    private javax.swing.JRadioButton RdiPlan1;
    private javax.swing.JTextField Reg_CCP;
    private javax.swing.JTextField Reg_CategNum;
    private javax.swing.JComboBox<String> Reg_CombGrade1;
    private javax.swing.JTextField Reg_LastName;
    private javax.swing.JTextField Reg_Name;
    private javax.swing.JTextField Reg_NumSemt;
    private javax.swing.JTextField Reg_Residence;
    private javax.swing.JTextField RemarqueTxt;
    private javax.swing.JTextField RemarqueTxt1;
    private javax.swing.JTextField Residence;
    private javax.swing.JTextField ResidentAdm;
    private javax.swing.JTextField ResidentAdm1;
    private javax.swing.JPanel SupPan;
    private javax.swing.JTable Tab_InfoEmp;
    private javax.swing.JTable Tab_Rep_Dec_sup_50_inf_10;
    private javax.swing.JTable Tab_Rep_Dec_sup_50_sup_10;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JTable TablCommune;
    private javax.swing.JTable Table_OrdMission;
    private javax.swing.JTable Table_OrdMission1;
    private javax.swing.JTable Table_OrdMissionEdit;
    private java.awt.Choice TaskMission;
    private java.awt.Choice TaskMission1;
    private javax.swing.JPanel TravelTotalTrans;
    private javax.swing.JPanel TravelTotalTrans1;
    private javax.swing.JTextField TxtSearch;
    private javax.swing.JLabel Valid_Lab;
    private javax.swing.JPanel awsomIcon;
    private javax.swing.JRadioButton btnRadSup50;
    private javax.swing.JRadioButton btnRadSup51;
    private javax.swing.JRadioButton btnRd100_02;
    private javax.swing.JRadioButton btnRd25_02;
    private javax.swing.JButton btnSvMissTbDep;
    private javax.swing.JButton btn_Imprimre;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox checkFunct;
    private java.awt.Choice choice2;
    private java.awt.Choice choice3;
    private java.awt.Choice choice_catg;
    private javax.swing.JRadioButton info50km_02;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private org.jdesktop.swingx.JXDatePicker jDateChBack1;
    private org.jdesktop.swingx.JXDatePicker jDateChGo1;
    private org.jdesktop.swingx.JXDatePicker jDateChGo3;
    private org.jdesktop.swingx.JXDatePicker jDateChGo4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel notification;
    private javax.swing.JTextField num_ord;
    private javax.swing.JPanel panBtn_TabDepns;
    private javax.swing.JPanel panDetail_TabDepns;
    private javax.swing.JPanel panInfr;
    private javax.swing.JPanel panServices;
    private javax.swing.JPanel panelCause;
    private javax.swing.JRadioButton rdiCar;
    private javax.swing.JRadioButton rdiCar1;
    private javax.swing.JRadioButton rdiTrain;
    private javax.swing.JRadioButton rdiTrain1;
    private javax.swing.JPanel steamboat;
    private javax.swing.JPanel steamboat1;
    private javax.swing.JRadioButton sup50km_02;
    private javax.swing.JTable tab_Function;
    private javax.swing.JTable tab_Grad;
    private javax.swing.JTextField txtNbrKlm;
    private javax.swing.JTextField txtNbrKlm1;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtPrice1;
    // End of variables declaration//GEN-END:variables
}
