package Henning.Schicha;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.io.IOException;
import java.text.NumberFormat;

public class UI {
    static closeWindowCustom customCloser;
    static AListener listen;
    static Searching search;
    static JFrame frame;
    static JPanel main;
    static JPanel PTop;
    static JPanel PLabels;
    static JPanel PMenu;
    static JPanel PAttacks;
    static JPanel PEquip;
    static JPanel PRolls;
    static JPanel PStats;
    static JPanel PSpellbook;
    static JPanel PNotes;
    static JPanel PMain;
    static JButton BAttacks;
    static JButton BEquip;
    static JButton BRolls;
    static JButton BStats;
    static JButton BMiscellaneous;
    static JButton BSpellbook;
    static JButton BNotes;
    static JLabel LName;
    static JLabel LClass;
    static JLabel LLvl;
    static String charname;
    static String classname;
    static String lvl;
    static JButton onetohit;
    static JButton oneattack;
    static JButton twotohit;
    static JButton twoattack;
    static JButton threetohit;
    static JButton threeattack;
    static JButton fourtohit;
    static JButton fourattack;
    static JButton onecrit;
    static JButton twocrit;
    static JButton threecrit;
    static JButton fourcrit;
    static String AA;
    static String AB;
    static String AC;
    static String AD;
    static String AE;
    static String AF;
    static String AG;
    static String AH;
    static String AI;
    static String AJ;
    static String AK;
    static String AL;
    static String AM;
    static String AN;
    static String AO;
    static String AP;
    static String AQ;
    static String AR;
    static String AS;
    static String AT;
    static String AU;
    static String AV;
    static String AW;
    static String AX;
    static String weapone;
    static String weaptwo;
    static String weapthree;
    static String weapfour;
    static JTextArea wepone;
    static JTextArea weptwo;
    static JTextArea wepthree;
    static JTextArea wepfour;
    static JTextArea cwepone;
    static JTextArea cweptwo;
    static JTextArea cwepthree;
    static JTextArea cwepfour;
    static JFormattedTextField AAT;
    static JFormattedTextField ABT;
    static JFormattedTextField ACT;
    static JFormattedTextField ADT;
    static JFormattedTextField AET;
    static JFormattedTextField AFT;
    static JFormattedTextField AGT;
    static JFormattedTextField AHT;
    static JFormattedTextField AIT;
    static JFormattedTextField AJT;
    static JFormattedTextField AKT;
    static JFormattedTextField ALT;
    static JFormattedTextField AMT;
    static JFormattedTextField ANT;
    static JFormattedTextField AOT;
    static JFormattedTextField APT;
    static JFormattedTextField AQT;
    static JFormattedTextField ART;
    static JFormattedTextField AST;
    static JFormattedTextField ATT;
    static JFormattedTextField AUT;
    static JFormattedTextField AVT;
    static JFormattedTextField AWT;
    static JFormattedTextField AXT;
    static JPanel TopA;
    static JPanel MainA;
    static JPanel DownA;
    static JPanel weapnames;
    static JPanel weone;
    static JPanel wetwo;
    static JPanel wethree;
    static JPanel wefour;
    static JPanel cweone;
    static JPanel cwetwo;
    static JPanel cwethree;
    static JPanel cwefour;
    static JPanel APlaceholder;
    static JPanel ATriple;
    static JLabel AResult;
    static JLabel AnResult;
    static JLabel AndResult;
    static Border dBorder;
    static Border noBorder;
    static Border plusBorder;
    static JFormattedTextField[] Areas;
    static String[] Strings;
    static JButton[] Buttons;
    static NumberFormat format=NumberFormat.getNumberInstance();
    static NumberFormatter numfom=new NumberFormatter(format);
    public static void main(String [] args) throws IOException {
        init();
        Idle.init();
        Equipment.init();
        Spellbook.init();
        Notes.init();
        Miscellaneous.init();
        Custom_Rolls.init();
        Logger.onStartup();
        Miscellaneous.updateLog();
        initListeners();
        initPanels();
        initFormat();
        initWeaponJTextAreas();
        addWeapons();
        createTitleBorders();
        initFrame();
        setDefaultPanelLayouts();
        initializeJFormattedTextFields();
        initButtons();
        addActionListeners();
        addGridComponents();
        initLabels();
        finalizeMenuButtons();
        createJPanelStructure();
        setDefaultVisibilities();
    }
    static void initListeners(){
        listen=new AListener();
        customCloser=new closeWindowCustom();
        search=new Searching();
    }
    static void initFormat(){
        numfom.setValueClass(Integer.class);
        numfom.setAllowsInvalid(true);
    }

    static void initWeaponJTextAreas(){
        wepone=new JTextArea(weapone);
        weptwo=new JTextArea(weaptwo);
        wepthree=new JTextArea(weapthree);
        wepfour=new JTextArea(weapfour);
        cwepone=new JTextArea(weapone);
        cweptwo=new JTextArea(weaptwo);
        cwepthree=new JTextArea(weapthree);
        cwepfour=new JTextArea(weapfour);
        setKeyListeners();
    }
    static void setKeyListeners(){
        wepone.addKeyListener(search);
        weptwo.addKeyListener(search);
        wepthree.addKeyListener(search);
        wepfour.addKeyListener(search);

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
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(1440, 810);
        frame.add(main);
        frame.addWindowListener(customCloser);
    }
    static void CustomExit(){

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
        PNotes = new JPanel();
    }
    static void setDefaultVisibilities() {
        TopA.setVisible(true);
        MainA.setVisible(true);
        frame.setVisible(true);
        main.setVisible(true);
        PTop.setVisible(true);
        PLabels.setVisible(true);
        Miscellaneous.main.setVisible(false);
        PAttacks.setVisible(true);
        PEquip.setVisible(false);
        PRolls.setVisible(false);
        PStats.setVisible(false);
        PSpellbook.setVisible(false);
        PNotes.setVisible(false);
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
        PRolls.add(Custom_Rolls.main);
        PSpellbook.add(Spellbook.outer);
        PNotes.add(Notes.main);
        createMenuStructure();
    }
    static void createMenuStructure() {
        PMain.add(PEquip);
        PMain.add(PAttacks, 0);
        PMain.add(PRolls);
        PMain.add(PStats);
        PMain.add(Miscellaneous.main);
        PMain.add(PSpellbook);
        PMain.add(PNotes);
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
        labelStyle();
    }
    static void labelStyle(){
        AResult.setFont(new Font("Sans Serif", 0, 25));
        AnResult.setFont(new Font("Sans Serif", 0, 25));
        AndResult.setFont(new Font("Sans Serif", 0, 25));
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
        PMenu.add(BNotes);
        PMenu.add(BMiscellaneous);
    }
    static void initMenuListeners() {
        BMiscellaneous.addActionListener(listen);
        BAttacks.addActionListener(listen);
        BEquip.addActionListener(listen);
        BRolls.addActionListener(listen);
        BStats.addActionListener(listen);
        BSpellbook.addActionListener(listen);
        BNotes.addActionListener(listen);
    }
    static void initMenuButtons() {
        BAttacks = new JButton("Attacks");
        BEquip = new JButton("Equipment");
        BRolls = new JButton("Custom rolls");
        BStats = new JButton("Stats");
        BMiscellaneous = new JButton("Miscellaneous");
        BSpellbook = new JButton("Spellbook");
        BNotes = new JButton("Notes");
    }
    static void createTitleBorders() {
        noBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),
                BorderFactory.createTitledBorder(noBorder, "Number of Dice", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
        dBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),
                BorderFactory.createTitledBorder(dBorder, "Rolled Dice", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
        plusBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),
                BorderFactory.createTitledBorder(plusBorder, "Modifier", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
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
    static void initializeJFormattedTextFields() {
        AAT = new JFormattedTextField(numfom);
        ABT = new JFormattedTextField(numfom);
        ACT = new JFormattedTextField(numfom);
        AST = new JFormattedTextField(numfom);
        AET = new JFormattedTextField(numfom);
        AJT = new JFormattedTextField(numfom);
        AOT = new JFormattedTextField(numfom);
        ATT = new JFormattedTextField(numfom);
        AFT = new JFormattedTextField(numfom);
        AKT = new JFormattedTextField(numfom);
        APT = new JFormattedTextField(numfom);
        AUT = new JFormattedTextField(numfom);
        AGT = new JFormattedTextField(numfom);
        ALT = new JFormattedTextField(numfom);
        AQT = new JFormattedTextField(numfom);
        AVT = new JFormattedTextField(numfom);
        AHT = new JFormattedTextField(numfom);
        AMT = new JFormattedTextField(numfom);
        ART = new JFormattedTextField(numfom);
        AWT = new JFormattedTextField(numfom);
        AIT = new JFormattedTextField(numfom);
        ANT = new JFormattedTextField(numfom);
        ADT = new JFormattedTextField(numfom);
        AXT = new JFormattedTextField(numfom);
        putAreasInArray();
        initJFormattedTextFieldTexts();
        setAllFontsAndStyles();
    }
    static void initJFormattedTextFieldTexts(){
        putStringsInArray();
        for (int i = 0; i < 24 ; i++){
            Areas[i].setText(Strings[i]);
        }
        putArrayInStrings();
    }

    static void putAreasInArray(){
        Areas=new JFormattedTextField[100];
        Areas[0]=AAT;
        Areas[1]=ABT;
        Areas[2]=ACT;
        Areas[3]=ADT;
        Areas[4]=AET;
        Areas[5]=AFT;
        Areas[6]=AGT;
        Areas[7]=AHT;
        Areas[8]=AIT;
        Areas[9]=AJT;
        Areas[10]=AKT;
        Areas[11]=ALT;
        Areas[12]=AMT;
        Areas[13]=ANT;
        Areas[14]=AOT;
        Areas[15]=APT;
        Areas[16]=AQT;
        Areas[17]=ART;
        Areas[18]=AST;
        Areas[19]=ATT;
        Areas[20]=AUT;
        Areas[21]=AVT;
        Areas[22]=AWT;
        Areas[23]=AXT;
    }
    static void setAllFontsAndStyles(){
        for (int i = 0; i<24;i++) {
            Areas[i].setFont(new Font("Segoe Script",0, 45));
            if (i%3==0) Areas[i].setBorder(noBorder);
            if (i%3==1) Areas[i].setBorder(dBorder);
            if (i%3==2) Areas[i].setBorder(plusBorder);
            Areas[i].setColumns(2);
        }
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
    static void initButtons() {
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
        putButtonsInArray();
        styleButtons();
    }
    static void styleButtons(){
        for(int i=0;i<Buttons.length;i++){
            Buttons[i].setFont(new Font("Arial Black",Font.BOLD, 23));
            Buttons[i].updateUI();
        }
    }
    static void putButtonsInArray(){
        Buttons=new JButton[12];
        Buttons[0]=onetohit;
        Buttons[1]=twotohit;
        Buttons[2]=threetohit;
        Buttons[3]=fourtohit;
        Buttons[4]=oneattack;
        Buttons[5]=twoattack;
        Buttons[6]=threeattack;
        Buttons[7]=fourattack;
        Buttons[8]=onecrit;
        Buttons[9]=twocrit;
        Buttons[10]=threecrit;
        Buttons[11]=fourcrit;
    }
    static void addActionListeners() {
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
        Miscellaneous.main.setVisible(false);
        PAttacks.setVisible(false);
        PEquip.setVisible(false);
        PRolls.setVisible(false);
        PStats.setVisible(false);
        PSpellbook.setVisible(false);
        PNotes.setVisible(false);
    }
    static void putStringsInArray(){
        Strings= new String[100];
        Strings[0]=AA;
        Strings[1]=AB;
        Strings[2]=AC;
        Strings[3]=AD;
        Strings[4]=AE;
        Strings[5]=AF;
        Strings[6]=AG;
        Strings[7]=AH;
        Strings[8]=AI;
        Strings[9]=AJ;
        Strings[10]=AK;
        Strings[11]=AL;
        Strings[12]=AM;
        Strings[13]=AN;
        Strings[14]=AO;
        Strings[15]=AP;
        Strings[16]=AQ;
        Strings[17]=AR;
        Strings[18]=AS;
        Strings[19]=AT;
        Strings[20]=AU;
        Strings[21]=AV;
        Strings[22]=AW;
        Strings[23]=AX;
    }
    static void putArrayInStrings(){
        AA=Strings[0];
        AB=Strings[1];
        AC=Strings[2];
        AD=Strings[3];
        AE=Strings[4];
        AF=Strings[5];
        AG=Strings[6];
        AH=Strings[7];
        AI=Strings[8];
        AJ=Strings[9];
        AK=Strings[10];
        AL=Strings[11];
        AM=Strings[12];
        AN=Strings[13];
        AO=Strings[14];
        AP=Strings[15];
        AQ=Strings[16];
        AR=Strings[17];
        AS=Strings[18];
        AT=Strings[19];
        AU=Strings[20];
        AV=Strings[21];
        AW=Strings[22];
        AX=Strings[23];
    }
    public static void saveAttacks(){
        System.out.println("saved");
        putStringsInArray();
        for (int i = 0; i < 24; i++) {
            Strings[i] =getsafeText(Areas[i]);
        }
        putArrayInStrings();
        weapone=wepone.getText();
        weaptwo=weptwo.getText();
        weapthree=wepthree.getText();
        weapfour=wepfour.getText();
        cwepone.setText(wepone.getText());
        cweptwo.setText(weptwo.getText());
        cwepthree.setText(wepthree.getText());
        cwepfour.setText(wepfour.getText());
    }
    public static String getsafeText(JFormattedTextField j){
        if (j.getText().equals("")) return "0";
        else return j.getText();
    }
    public static void RollAttack(String ex, String dee, String and,String Weapon,String action,Boolean crit){
        System.out.println("hitting");
        try {
            String xe=ex.replaceAll("\\s","");
            String eed=dee.replaceAll("\\s","");
            String dna=and.replaceAll("\\s","");
            int x;
            if (crit) x = 2 * Integer.parseInt(xe);
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
            Logger.log("<Rolled Attack> "+building+res);
        } catch (NumberFormatException | NullPointerException e) {
            AResult.setText(String.valueOf(e.getCause()));
        }
    }
}
