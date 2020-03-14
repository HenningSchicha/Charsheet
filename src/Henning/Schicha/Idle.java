package Henning.Schicha;

import javax.swing.*;
import java.awt.*;

public class Idle {
    static Listener listen;
    static JPanel field;
    static JPanel leftfield;
    static JPanel rightfield;
    static JPanel midfield;
    static JPanel midleft;
    static JPanel midright;
    static JPanel midmid;
    static JPanel sysfield;
    static int rowno;
    static int colno;
    static JButton main;
    static JButton firstB;
    static JButton secondB;
    static JButton thirdB;
    static JButton fourthB;
    static JButton firstU;
    static JButton secondU;
    static JButton thirdU;
    static JButton fourthU;
    static JButton sellsys;
    static GridLayout sublays;
    static GridLayout sysnew;
    static JTextArea fB;
    static JTextArea sB;
    static JTextArea tB;
    static JTextArea yB;
    static JTextArea fU;
    static JTextArea sU;
    static JTextArea tU;
    static JTextArea yU;
    static JTextArea maindesc;
    static int clicklvl;
    static int personlvl;
    static int complvl;
    static int systemlvl;
    static Dimension d;
    static float money;
    static float personmps;
    static float compmps;
    static float systemmps;
    static float clickcost;
    static float personcost;
    static float compcost;
    static float syscost;
    static float cmultcost;
    static float pmultcost;
    static float compmultcost;
    static float smultcost;
    static float clickmult;
    static float personmult;
    static float compmult;
    static float systemmult;
    static float inc;
    static float uInc;
    static float clickbase;
    static float personbase;
    static float compbase;
    static float sysbase;
    static float cmultbase;
    static float pmultbase;
    static float compmultbase;
    static float smultbase;
    static float fps;
    static float sysboost;
    public static void init(){
        setConstants();
        listen=new Listener();
        initLayouts();
        initPanels();
        initTextAreas();
        setDefaultTextFieldEditable();
        initButtons();
        CreatePanelStructure();
        putButtonsOnPanels();
        setActionsListeners();
        putTextFieldsOnPanels();
        setDefaultPanelVisibilities();
        makeItLookSexy();
    }
    static void setConstants() {
        //balanced at inc=1.15,uInc=5,clickbase=1,personbase=1,personcost=100,compbase=8,compcost=1100,sysbase=50,syscost=12000
        d=new Dimension(400,300);
        sysboost = 1;
        rowno = 4;
        colno = 3;
        fps = 30;
        clickbase = 1;
        personbase = (float) 1;
        compbase = 8;
        sysbase = 50;
        cmultbase = (float) 1;
        pmultbase = (float) 1;
        compmultbase = (float) 1;
        smultbase = (float) 1;
        clicklvl = 0;
        personlvl = 0;
        complvl = 0;
        systemlvl = 0;
        clickmult = 1;
        personmult = 1;
        compmult = 1;
        systemmult = 1;
        money = 0;
        personmps = 0;
        compmps = 0;
        systemmps = 0;
        clickcost = 100;
        personcost = 100;
        compcost = 1100;
        syscost = 12000;
        cmultcost = 1000;
        pmultcost = 1000;
        compmultcost = 11000;
        smultcost = 120000;
        inc = (float) 1.15;
        uInc = (float) 5;
    }
    static void initLayouts() {
        sublays = new GridLayout(rowno, colno);
        sysnew = new GridLayout(1, 2);
    }
    static void initPanels() {
        sysfield = new JPanel(sysnew, true);
        field = new JPanel(new BorderLayout(), true);
        leftfield = new JPanel(sublays, true);
        rightfield = new JPanel(sublays, true);
        midfield = new JPanel(new BorderLayout(), true);
        midleft = new JPanel(sublays, true);
        midright = new JPanel(sublays, true);
        midmid = new JPanel(new BorderLayout(), true);
    }
    static void initTextAreas() {
        fB = new JTextArea();
        sB = new JTextArea();
        tB = new JTextArea();
        yB = new JTextArea();
        fU = new JTextArea();
        sU = new JTextArea();
        tU = new JTextArea();
        yU = new JTextArea();
        maindesc = new JTextArea();
    }
    static void setDefaultTextFieldEditable() {
        fB.setEditable(false);
        sB.setEditable(false);
        tB.setEditable(false);
        yB.setEditable(false);
        fU.setEditable(false);
        sU.setEditable(false);
        tU.setEditable(false);
        yU.setEditable(false);
        maindesc.setEditable(false);
    }
    static void initButtons() {
        sellsys = new JButton("Sell Systems, boost people");
        main = new JButton("START GAME");
        firstB = new JButton();
        secondB = new JButton();
        thirdB = new JButton();
        fourthB = new JButton();
        firstU = new JButton();
        secondU = new JButton();
        thirdU = new JButton();
        fourthU = new JButton();
    }
    static void putButtonsOnPanels() {
        leftfield.add(firstB);
        leftfield.add(secondB);
        leftfield.add(thirdB);
        leftfield.add(fourthB);
        rightfield.add(firstU);
        rightfield.add(secondU);
        rightfield.add(thirdU);
        rightfield.add(fourthU);
    }
    static void CreatePanelStructure() {
        field.add(leftfield, BorderLayout.WEST);
        field.add(rightfield, BorderLayout.EAST);
        field.add(midfield, BorderLayout.CENTER);
        midfield.add(midmid, BorderLayout.CENTER);
        midfield.add(midleft, BorderLayout.WEST);
        midfield.add(midright, BorderLayout.EAST);
        midmid.add(main, BorderLayout.CENTER);
        midmid.add(maindesc, BorderLayout.SOUTH);
    }
    static void setActionsListeners() {
        main.addActionListener(listen);
        firstB.addActionListener(listen);
        secondB.addActionListener(listen);
        thirdB.addActionListener(listen);
        fourthB.addActionListener(listen);
        firstU.addActionListener(listen);
        secondU.addActionListener(listen);
        thirdU.addActionListener(listen);
        fourthU.addActionListener(listen);
    }
    static void putTextFieldsOnPanels() {
        midleft.add(fB);
        midleft.add(sB);
        midleft.add(tB);
        midleft.add(yB);
        midright.add(fU);
        midright.add(sU);
        midright.add(tU);
        midright.add(yU);
    }
    static void setDefaultPanelVisibilities() {
        field.setVisible(true);
        leftfield.setVisible(true);
        rightfield.setVisible(true);
        midfield.setVisible(true);
        midright.setVisible(true);
        midleft.setVisible(true);
        midmid.setVisible(true);
    }
    static void makeItLookSexy() {
        ColorTextAreas();
        ColorButtons();
        removeButtonBorders();
        main.setBorder(BorderFactory.createRaisedBevelBorder());
    }
    static void ColorTextAreas() {
        fB.setBackground(new Color(179, 179, 0));
        sB.setBackground(new Color(179, 179, 0));
        tB.setBackground(new Color(179, 179, 0));
        yB.setBackground(new Color(179, 179, 0));
        fU.setBackground(new Color(179, 179, 0));
        sU.setBackground(new Color(179, 179, 0));
        tU.setBackground(new Color(179, 179, 0));
        yU.setBackground(new Color(179, 179, 0));
    }
    static void ColorButtons() {
        firstB.setBackground(new Color(128, 255, 128));
        secondB.setBackground(new Color(51, 255, 51));
        thirdB.setBackground(new Color(0, 230, 0));
        fourthB.setBackground(new Color(0, 153, 0));
        firstU.setBackground(new Color(128, 255, 128));
        secondU.setBackground(new Color(51, 255, 51));
        thirdU.setBackground(new Color(0, 230, 0));
        fourthU.setBackground(new Color(0, 153, 0));
    }
    static void removeButtonBorders() {
        firstB.setBorder(null);
        secondB.setBorder(null);
        thirdB.setBorder(null);
        fourthB.setBorder(null);
        firstU.setBorder(null);
        secondU.setBorder(null);
        thirdU.setBorder(null);
        fourthU.setBorder(null);
    }
    public static void buyclick(){
        if (money>=clickcost){
            money=money-clickcost;
            clicklvl++;
            clickcost=  (clickcost*inc);
        }
    }
    public static void click(){
        money=money+1+(clicklvl*clickmult*clickbase);
    }
    public static void update(){
        money=money+((personmps+compmps+systemmps)/fps);
        if(money>=2147483647){
            main.setText("you won gj");
        }
        updatetext();
    }
    public static void updatetext(){
        String templvl=clicklvl+"";
        String tempclick="Fingers";
        String tempppl="People";
        String tempcomp="Companies";
        String tempsys="Systems";
        if (clicklvl==1){
            tempclick="Finger";
        }
        if (clicklvl>9){
            tempclick="Hands";
        }
        if (clicklvl>19){
            tempclick="Computer mice";
        }
        if (clicklvl>29){
            tempclick="Autoclickers";
        }
        if (clicklvl>39){
            tempclick="Purpose";
            templvl="";
        }
        if (personlvl==1){
            tempppl="Person";
        }
        if(personlvl>9){
            tempppl="Hard Workers";
        }
        if (personlvl>19){
            tempppl="Middle Managers";
        }
        if (personlvl>29){
            tempppl="CEOs";
        }
        if (personlvl >39){
            tempppl="literal Gods";
        }
        if(complvl==1){
            tempcomp="Company";
        }
        if (complvl>9){
            tempcomp="Businesses";
        }
        if (complvl>19){
            tempcomp="Monopolies";
        }
        if (complvl>29){
            tempcomp="Worlds";
        }
        if (complvl>39){
            tempcomp="Timelines";
        }
        if (systemlvl==1){
            tempsys="System";
        }
        if (systemlvl>9){
            tempsys="Concepts";
        }
        if (systemlvl>19){
            tempsys="Neural\nNetworks";
        }
        if (systemlvl>29){
            tempsys="Religions";
        }
        if (systemlvl>39){
            tempsys="Facets\nof your own mind";
        }
        fB.setText("\n\nYou click with "+templvl+" "+tempclick+"\n                                                        \n"+Math.round(clickcost)+" Cost\n\n"+Math.round(1+clicklvl*clickmult*clickbase)+" Click Value                      \n\n\n");
        sB.setText("\n\nYou employ "+ personlvl+" "+tempppl+"\n\n"+Math.round(personcost)+" Cost\n\n"+Math.round(personmps)+" MpS                             \n");
        tB.setText("\n\nYou manage "+ complvl+" "+tempcomp+"\n\n"+Math.round(compcost)+" Cost\n\n"+Math.round(compmps)+" MpS                             \n");
        yB.setText("\n\nYou implement "+ systemlvl+" "+tempsys+"\n\n"+Math.round(syscost)+" Cost\n\n"+Math.round(systemmps)+" MpS                             \n");
        fU.setText("\n\n\n"+Math.round(cmultcost)+" Cost\n\n"+Math.round(clickmult*100)+"% multiplier                     ");
        sU.setText("\n\n\n"+Math.round(pmultcost)+" Cost\n\n"+Math.round(personmult*100)+"% multiplier                     ");
        tU.setText("\n\n\n"+Math.round(compmultcost)+" Cost\n\n"+Math.round(compmult*100)+"% multiplier                     ");
        yU.setText("\n\n\n"+Math.round(smultcost)+" Cost\n\n"+Math.round(systemmult*100)+"% multiplier                     ");
        maindesc.setText("Money:  "+Math.round(money)+"€");
    }
    public static void buyperson(){
        if (money>=personcost){
            money=money-personcost;
            personlvl++;
            personmps=personlvl*personmult*personbase;
            personcost=(personcost*inc);
        }
    }
    public static void buycompany(){
        if (money>=compcost){
            money=money-compcost;
            complvl++;
            compmps=(complvl*compbase)*compmult;
            compcost=(compcost*inc);
        }
    }
    public static void buysystem(){
        if (money>= syscost){
            money=money- syscost;
            systemlvl++;
            systemmps=(systemlvl*sysbase)*systemmult;
            syscost =(syscost *inc);
        }
    }
    public static void buyClickUpgrade(){
        if (money>=cmultcost){
            money=money-cmultcost;
            clickmult=(clickmult*(1+cmultbase));
            cmultcost=(cmultcost*uInc);
        }
    }
    public static void buyPersonUpgrade(){
        if (money>=pmultcost){
            money=money-pmultcost;
            personmult=(personmult*(1+pmultbase));
            pmultcost=(pmultcost*uInc);
            personmps=personlvl*personmult*personbase;
        }
    }
    public static void buyCompanyUpgrade(){
        if (money>=compmultcost){
            money=money-compmultcost;
            compmult=(compmult*(1+compmultbase));
            compmultcost=(compmultcost*uInc);
            compmps=complvl*compmult*compbase;
        }
    }
    public static void buySystemUpgrade(){
        if (money>=smultcost){
            money=money-smultcost;
            systemmult=(systemmult*(1+smultbase));
            smultcost=(smultcost*uInc);
            systemmps=systemlvl*systemmult*sysbase;
        }
    }
}