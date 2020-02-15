package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class UI {
    static AListener listen;
    static JFrame frame;
    static JPanel main, PTop,PLabels,PMenu,PAttacks,PEquip,PRolls,PStats,PSpellbook,PMain;
    static JButton BAttacks, BEquip, BRolls, BStats,BIdle,BSpellbook;
    static JLabel LName,LClass,LLvl;
    static BorderLayout outside,inside;
    static String charname,classname,lvl;

    static JButton saveAttacks, onetohit, oneattack, twotohit,twoattack,threetohit,threeattack,fourtohit,fourattack,onecrit,twocrit,threecrit,fourcrit;
    static String AA,AB,AC,AD,AE,AF,AG,AH,AI,AJ,AK,AL,AM,AN,AO,AP,AQ,AR,AS,AT,AU,AV,AW,AX,weapone,weaptwo,weapthree,weapfour;
    static JTextArea AAT,ABT,ACT,ADT,AET,AFT,AGT,AHT,AIT,AJT,AKT,ALT,AMT,ANT,AOT,APT,AQT,ART,AST,ATT,AUT,AVT,AWT,AXT,wepone,weptwo,wepthree,wepfour,cwepone,cweptwo,cwepthree,cwepfour;
    static JPanel TopA, MainA,DownA,weapnames,weone,wetwo,wethree,wefour,cweone,cwetwo,cwethree,cwefour,APlaceholder,ATriple;
    static JLabel AResult,AnResult,AndResult;
    static Border dBorder,noBorder,plusBorder;
    public static void main(String [] args){
        init();
        Idle.init();
        Equipment.init();
        listen=new AListener();
        initPanels();
        initJTextAreas();
        addWeapons();
        createTitleBorders();
        initFrame();
        setDefaultPanelLayouts();
        initializeJTextAreas();
        initJTextAreaBorders();
        initButtonText();
        addActionListeners();
        addGridComponents();
        initLabels();
        TopA.add(saveAttacks);
        finalizeMenuButtons();
        createJPanelStructure();
        setDefaultVisibilities();
    }
    static void initJTextAreas(){
        wepone=new JTextArea(weapone);
        weptwo=new JTextArea(weaptwo);
        wepthree=new JTextArea(weapthree);
        wepfour=new JTextArea(weapfour);
        cwepone=new JTextArea(weapone);
        cweptwo=new JTextArea(weaptwo);
        cwepthree=new JTextArea(weapthree);
        cwepfour=new JTextArea(weapfour);
    }
    public static void init(){
        charname="ExName";
        classname="ExClass";
        lvl="ExLevel";
        weapone="Weapon 1";
        weaptwo="Weapon 2";
        weapthree="Weapon 3";
        weapfour="Weapon 4";
    }
    static void setDefaultPanelLayouts() {
        main.setLayout(new BorderLayout());
        PTop.setLayout(new BorderLayout());
        PAttacks.setLayout(new BorderLayout());
        MainA.setLayout(new GridLayout(4, 9, 1, 10));
        TopA.setLayout(new FlowLayout());
        PMenu.setLayout(new GridLayout(1, 8));
        PLabels.setLayout(new GridLayout(1, 3));
    }
    static void initFrame() {
        frame = new JFrame("DnD Character sheet");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(1200, 820);
        frame.add(main);
    }
    static void initPanels() {
        ATriple = new JPanel(new GridLayout(3, 1));
        APlaceholder = new JPanel(new GridLayout(4, 1));
        weapnames = new JPanel(new GridLayout(4, 1));
        main = new JPanel();
        PTop = new JPanel();
        PLabels = new JPanel();
        PMenu = new JPanel();
        PAttacks = new JPanel();
        TopA = new JPanel();
        MainA = new JPanel();
        DownA = new JPanel();
        PMain = new JPanel();
        PEquip = new JPanel();
        PRolls = new JPanel();
        PStats = new JPanel();
        PSpellbook = new JPanel();
    }
    static void setDefaultVisibilities() {
        TopA.setVisible(true);
        MainA.setVisible(true);
        frame.setVisible(true);
        main.setVisible(true);
        PTop.setVisible(true);
        PLabels.setVisible(true);
        Idle.field.setVisible(false);
        PAttacks.setVisible(true);
        PEquip.setVisible(false);
        PRolls.setVisible(false);
        PStats.setVisible(false);
        PSpellbook.setVisible(false);
    }
    static void createJPanelStructure() {
        PAttacks.add(APlaceholder, BorderLayout.EAST);
        PAttacks.add(weapnames, BorderLayout.WEST);
        PAttacks.add(TopA, BorderLayout.NORTH);
        PAttacks.add(MainA, BorderLayout.CENTER);
        PAttacks.add(DownA, BorderLayout.SOUTH);
        PLabels.add(LLvl, SwingConstants.CENTER);
        PLabels.add(LClass, SwingConstants.CENTER);
        PLabels.add(LName, SwingConstants.CENTER);
        PEquip.add(Equipment.outer);
        main.add(PTop, BorderLayout.NORTH);
        main.add(PMain, BorderLayout.CENTER);
        PTop.add(PLabels, BorderLayout.NORTH);
        PTop.add(PMenu, BorderLayout.CENTER);
        createMenuStructure();
    }
    static void createMenuStructure() {
        PMain.add(PEquip);
        PMain.add(PAttacks, 0);
        PMain.add(PRolls);
        PMain.add(PStats);
        PMain.add(Idle.field);
        PMain.add(PSpellbook);
    }
    static void initLabels(){
        initializeResultLabels();
        initTopLabels();
        addLabelsToPanels();
    }

    static void initTopLabels() {
        LLvl = new JLabel(lvl, SwingConstants.CENTER);
        LClass = new JLabel(classname, SwingConstants.CENTER);
        LName = new JLabel(charname, SwingConstants.CENTER);
    }
    static void initializeResultLabels() {
        AResult = new JLabel("Next result will appear here");
        AnResult = new JLabel();
        AndResult = new JLabel();
    }
    static void addLabelsToPanels() {
        ATriple.add(AResult);
        ATriple.add(AnResult);
        ATriple.add(AndResult);
        DownA.add(ATriple);
    }
    static void finalizeMenuButtons() {
        initMenuButtons();
        initMenuListeners();
        addMenuButtonsToMainPanel();
    }
    static void addMenuButtonsToMainPanel() {
        PMenu.add(BAttacks);
        PMenu.add(BEquip);
        PMenu.add(BRolls);
        PMenu.add(BStats);
        PMenu.add(BSpellbook);
        PMenu.add(BIdle);
    }
    static void initMenuListeners() {
        BIdle.addActionListener(listen);
        BAttacks.addActionListener(listen);
        BEquip.addActionListener(listen);
        BRolls.addActionListener(listen);
        BStats.addActionListener(listen);
        BSpellbook.addActionListener(listen);
    }
    static void initMenuButtons() {
        BAttacks = new JButton("Attacks");
        BEquip = new JButton("Equipment");
        BRolls = new JButton("Custom rolls");
        BStats = new JButton("Stats");
        BIdle = new JButton("Idle Game");
        BSpellbook = new JButton("Spellbook");
    }
    static void createTitleBorders() {
        noBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),
                BorderFactory.createTitledBorder(noBorder, "Number of", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
        dBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),
                BorderFactory.createTitledBorder(dBorder, "Dice", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
        plusBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),
                BorderFactory.createTitledBorder(plusBorder, "Plus", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
    }
    static void addWeapons(){
        initWeaponPanels();
        addWeaponsToPanels();
        combineWeaponPanels();
    }
    static void initWeaponPanels() {
        weone = new JPanel();
        wetwo = new JPanel();
        wethree = new JPanel();
        wefour = new JPanel();
        cweone = new JPanel();
        cwetwo = new JPanel();
        cwethree = new JPanel();
        cwefour = new JPanel();
        cwepone.setEditable(false);
        cweptwo.setEditable(false);
        cwepthree.setEditable(false);
        cwepfour.setEditable(false);
    }
    static void addWeaponsToPanels() {
        cweone.add(cwepone);
        cwetwo.add(cweptwo);
        cwethree.add(cwepthree);
        cwefour.add(cwepfour);
        weone.add(wepone);
        wetwo.add(weptwo);
        wethree.add(wepthree);
        wefour.add(wepfour);
        weapnames.add(weone);
        weapnames.add(wetwo);
        weapnames.add(wethree);
        weapnames.add(wefour);
    }
    static void combineWeaponPanels() {
        APlaceholder.add(cweone);
        APlaceholder.add(cwetwo);
        APlaceholder.add(cwethree);
        APlaceholder.add(cwefour);
    }
    static void initializeJTextAreas() {
        AAT = new JTextArea(AA + "\n\n\n\n\n\n");
        ABT = new JTextArea(AB);
        ACT = new JTextArea(AC);
        AST = new JTextArea(AS);
        AET = new JTextArea(AE);
        AJT = new JTextArea(AJ);
        AOT = new JTextArea(AO);
        ATT = new JTextArea(AT);
        AFT = new JTextArea(AF);
        AKT = new JTextArea(AK);
        APT = new JTextArea(AP);
        AUT = new JTextArea(AU);
        AGT = new JTextArea(AG);
        ALT = new JTextArea(AL);
        AQT = new JTextArea(AQ);
        AVT = new JTextArea(AV);
        AHT = new JTextArea(AH);
        AMT = new JTextArea(AM);
        ART = new JTextArea(AR);
        AWT = new JTextArea(AW);
        AIT = new JTextArea(AI);
        ANT = new JTextArea(AN);
        ADT = new JTextArea(AD);
        AXT = new JTextArea(AX);
    }
    static void initJTextAreaBorders() {
        AAT.setBorder(noBorder);
        ABT.setBorder(dBorder);
        ACT.setBorder(plusBorder);
        ADT.setBorder(noBorder);
        AET.setBorder(dBorder);
        AFT.setBorder(plusBorder);
        AGT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AHT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AIT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AJT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AKT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ALT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AMT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ANT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AOT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        APT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AQT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ART.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AST.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ATT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AUT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AVT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AWT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AXT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    static void addGridComponents() {
        MainA.add(AAT);
        MainA.add(ABT);
        MainA.add(ACT);
        MainA.add(onetohit);
        MainA.add(onecrit);
        MainA.add(oneattack);
        MainA.add(ADT);
        MainA.add(AET);
        MainA.add(AFT);
        MainA.add(AGT);
        MainA.add(AHT);
        MainA.add(AIT);
        MainA.add(twotohit);
        MainA.add(twocrit);
        MainA.add(twoattack);
        MainA.add(AJT);
        MainA.add(AKT);
        MainA.add(ALT);
        MainA.add(AMT);
        MainA.add(ANT);
        MainA.add(AOT);
        MainA.add(threetohit);
        MainA.add(threecrit);
        MainA.add(threeattack);
        MainA.add(APT);
        MainA.add(AQT);
        MainA.add(ART);
        MainA.add(AST);
        MainA.add(ATT);
        MainA.add(AUT);
        MainA.add(fourtohit);
        MainA.add(fourcrit);
        MainA.add(fourattack);
        MainA.add(AVT);
        MainA.add(AWT);
        MainA.add(AXT);
    }
    static void initButtonText() {
        onetohit = new JButton("To hit");
        twotohit = new JButton("To hit");
        threetohit = new JButton("To hit");
        fourtohit = new JButton("To hit");
        oneattack = new JButton("Damage");
        twoattack = new JButton("Damage");
        threeattack = new JButton("Damage");
        fourattack = new JButton("Damage");
        onecrit = new JButton("Crit");
        twocrit = new JButton("Crit");
        threecrit = new JButton("Crit");
        fourcrit = new JButton("Crit");
        saveAttacks=new JButton("Save all");
    }
    static void addActionListeners() {
        saveAttacks.addActionListener(listen);
        onecrit.addActionListener(listen);
        twocrit.addActionListener(listen);
        threecrit.addActionListener(listen);
        fourcrit.addActionListener(listen);
        onetohit.addActionListener(listen);
        threetohit.addActionListener(listen);
        fourtohit.addActionListener(listen);
        twotohit.addActionListener(listen);
        oneattack.addActionListener(listen);
        twoattack.addActionListener(listen);
        threeattack.addActionListener(listen);
        fourattack.addActionListener(listen);
    }
    public static void collapseAll(){
        Idle.field.setVisible(false);
        PAttacks.setVisible(false);
        PEquip.setVisible(false);
        PRolls.setVisible(false);
        PStats.setVisible(false);
        PSpellbook.setVisible(false);
    }
    public static void saveAttacks(){
        System.out.println("saved");
        AA=AAT.getText();
        AB=ABT.getText();
        AC=ACT.getText();
        AD=ADT.getText();
        AE=AET.getText();
        AF=AFT.getText();
        AG=AGT.getText();
        AH=AHT.getText();
        AI=AIT.getText();
        AJ=AJT.getText();
        AK=AKT.getText();
        AL=ALT.getText();
        AM=AMT.getText();
        AN=ANT.getText();
        AO=AOT.getText();
        AP=APT.getText();
        AQ=AQT.getText();
        AR=ART.getText();
        AS=AST.getText();
        AT=ATT.getText();
        AU=AUT.getText();
        AV=AVT.getText();
        AW=AWT.getText();
        AX=AXT.getText();
        weapone=wepone.getText();
        weaptwo=weptwo.getText();
        weapthree=wepthree.getText();
        weapfour=wepfour.getText();
        cwepone.setText(wepone.getText());
        cweptwo.setText(weptwo.getText());
        cwepthree.setText(wepthree.getText());
        cwepfour.setText(wepfour.getText());
    }

    public static void RollAttack(String ex, String dee, String and,String Weapon,String action,Boolean crit){
        System.out.println("hitting");
        try {
            String xe=ex.replaceAll("\\s","");
            String eed=dee.replaceAll("\\s","");
            String dna=and.replaceAll("\\s","");
            int x;
            if(crit)x = 2*Integer.parseInt(xe);
            else x = Integer.parseInt(xe);
            int d = Integer.parseInt(eed);
            int plus = Integer.parseInt(dna);
            int res = 0;
            String building=Weapon+" "+action+" (";
            for (int i = 0; i < x; i++) {
                int a=(int) (Math.random() * (d) + 1);
                res = res+a;
                building=building+a;
                if(i!=x-1)building=building +" ";
            }
            building=building+") + "+plus+" = ";
            res = res + plus;

            StringBuilder thr= new StringBuilder();
            thr.append(AnResult.getText().replace("2)    ",""));
            AndResult.setText("3)    "+thr);
            thr.delete(0,thr.length());
            thr.append(AResult.getText().replace("1)    ",""));
            AnResult.setText("2)    "+thr);
            AResult.setText("1)    "+building+res+"");
        } catch (NumberFormatException | NullPointerException e) {
            AResult.setText(String.valueOf(e.getCause()));
        }
    }
}
