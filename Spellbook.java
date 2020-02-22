package com.company;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.function.Predicate;


public class Spellbook {
    static JPanel top, outer, center;
    static JTextField searchbar;
    static JButton searchbutton, next, previous, currentsave;
    static JLabel result, nextPlaceHolder, previousPlaceHolder;
    static MenuComponent[] components;
    static Spell[] Spells;
    static SpellMenu actualMenu;
    static AListener listen;
    static final int SpellNo = 260;
    static int head,screenFittingSpells,componentHeight,currentmenu;
    static Searching searchListener;
    static Boolean inMenu;
    static Dimension buttondim,xddim;

    public static void init() throws IOException {
        buttondim=new Dimension(51,51);     //width everywhere is WEIRD change with caution!!! should be odd/prime
        xddim=new Dimension(1,60);
        head=0;
        inMenu=false;
        searchListener=new Searching();
        currentmenu=-1;
        componentHeight=35;
        listen=new AListener();
        screenFittingSpells=10;
        outer = new JPanel();
        center=new JPanel();
        center.setLayout(new FlowLayout());
        center.setPreferredSize(new Dimension(541,(componentHeight+18)*screenFittingSpells));
        outer.setLayout(new BorderLayout());
        outer.add(center,BorderLayout.CENTER);
        outer.setOpaque(true);
        addATonOfComponents();
        top = new JPanel(new FlowLayout());
        searchbar = new JTextField();
        previous= new JButton("", new ImageIcon("src/com/company/leftArrow.png"));
        previous.setPreferredSize(buttondim);
        nextPlaceHolder= new JLabel();
        previousPlaceHolder=new JLabel();
        previousPlaceHolder.setPreferredSize(buttondim);
        top.add(previousPlaceHolder);
        previousPlaceHolder.setVisible(false);
        nextPlaceHolder.setVisible(false);
        top.add(previous);
        searchbar.setPreferredSize(new Dimension(401, 50));
        top.add(searchbar);
        next = new JButton("",new ImageIcon("src/com/company/rightArrow.png"));
        nextPlaceHolder.setPreferredSize(buttondim);
        searchbutton = new JButton("", new ImageIcon("src/com/company/searchMonocle.jpg"));
        top.add(searchbutton);
        next.setPreferredSize(buttondim);
        searchbutton.setMargin(new Insets(0, 0, 0, 0));
        searchbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        outer.add(top,BorderLayout.NORTH);
        result=new JLabel("Next Result will appear here");
        outer.add(result,BorderLayout.SOUTH);
        top.add(next);
        top.add(nextPlaceHolder);
        next.setMargin(new Insets(0,0,0,0));
        previous.setMargin(new Insets(0,0,0,0));
        next.addActionListener(listen);
        previous.addActionListener(listen);
        searchbar.addKeyListener(searchListener);
        addATonOfSpells();
        actualMenu=new SpellMenu();
        changePage();
    }
    static void removeButtonIfEndOfMenu(){
        previous.setVisible(true);
        previousPlaceHolder.setVisible(false);
        next.setVisible(true);
        nextPlaceHolder.setVisible(false);
        if(head==0){
            previous.setVisible(false);
            previousPlaceHolder.setVisible(true);
        }
        else if (head + screenFittingSpells >= SpellNo) {
            next.setVisible(false);
            nextPlaceHolder.setVisible(true);
        }
    }
    static void releaseRGBmaybe(){
        if ((String) Objects.requireNonNull(actualMenu.farbwahl.getSelectedItem())=="Custom"){
            releaseRGBdefinite(true);
        }
        else releaseRGBdefinite(false);
    }
    static void releaseRGBdefinite(Boolean dowe){
        actualMenu.R.setEditable(dowe);
        actualMenu.G.setEditable(dowe);
        actualMenu.B.setEditable(dowe);
    }
    static void search(JTextField input){
        if (!inMenu){
        String temp = input.getText();
        if (temp.equals("")) changePage();
        else {
            for (int i = 0; i < SpellNo; i++) {
                if (Spells[i].name.toLowerCase().contains(temp.toLowerCase())) {
                    components[i].main.setVisible(true);
                } else components[i].main.setVisible(false);
            }
        }
        }
    }
    static void roll(String input,int curr){
        int[] results = CommonFunctions.RollDice(input);
        String Result="You rolled "+results[0]+" (";
        for(int i=1;i<results.length;i++){
            Result=Result+results[i]+" ";
        }
        Result=Result.stripTrailing();
        Result=Result+")";
        Result=Result+" + "+CommonFunctions.SplitDiceString(input)[CommonFunctions.SplitDiceString(input).length-1];
        Result=Result+" with "+Spells[curr].name;
        result.setText(Result);
    }
    static void openMenu(int current){
        for (int i = 0; i < SpellNo; i++){
            components[i].main.setVisible(false);
        }
        inMenu=true;
        SpellMenu menu= new SpellMenu();
        menu.fillFromSpell(Spells[current]);
        if (menu.stringIsColor(components[current].colorStr)) {
            menu.farbwahl.setSelectedItem(components[current].colorStr);
        }
        CommonFunctions.ComboBoxSelfColor(menu.farbwahl);
        center.add(menu.main);
        top.setVisible(false);
        result.setVisible(false);
        actualMenu.setRGBsaves(components[current].myColorRGB);
        currentsave=menu.save;
        currentmenu=current;
        actualMenu=menu;
    }
    static Color getColorFromComboColor(String combo){
        switch(combo) {
            case "Ruby":
                return new Color(237, 83, 83);
            case "Aquamarine":
                return new Color(128, 255, 212);
            case "Honey":
                return new Color(255, 195, 110);
            case "Snake Green":
                return new Color(0, 141, 76);
            case "Very Default":
                return Color.WHITE;
            case "Silver":
                return new Color(129, 129, 129);
            case "Gold":
                return new Color(212, 175, 55);
            case "Copper":
                return new Color(184, 115, 51);
            case "Lapis":
                return new Color(38, 97, 156);
            case "Double Special Surprise":
                return new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        }
        String tempo=actualMenu.getStringRGB();
        String[] tempoo=tempo.split(",");
        if(tempoo.length==3) {
            return new Color(Integer.parseInt(tempoo[0]), Integer.parseInt(tempoo[1]), Integer.parseInt(tempoo[2]));
        }
        return Color.WHITE;
    }
    static void closeMenu(int current,boolean fromMenu){
        if((String) Objects.requireNonNull(actualMenu.farbwahl.getSelectedItem())!="Custom") {
            Color temp = getColorFromComboColor((String) Objects.requireNonNull(actualMenu.farbwahl.getSelectedItem()));
            components[current].setColor(temp);
            components[current].colorStr = (String) Objects.requireNonNull(actualMenu.farbwahl.getSelectedItem());
        }
        else{
            Color temp = getColorFromComboColor((String) Objects.requireNonNull(actualMenu.getStringRGB()));
            components[current].setColor(temp);
            components[current].colorStr = (String) Objects.requireNonNull(actualMenu.getStringRGB());
            components[current].myColorRGB = (String) Objects.requireNonNull(actualMenu.getStringRGB());
        }
        //actualMenu.main.setVisible(false);
        center.remove(actualMenu.main);
        top.setVisible(true);
        result.setVisible(true);
        changePage();
        if (fromMenu)actualMenu.saveToSpell(Spells[current]);
        components[current].setText(actualMenu.name.getText());
        inMenu=false;
    }
    static void addATonOfSpells(){
        Spells = new Spell[SpellNo];
        for (int i = 0; i < SpellNo; i++){
            Spells[i]=new Spell("A New Spelldescription","0","New Spell "+(i+1),"1","None","0","0d0+0",false,false,false,"0,0,0");
        }
    }
    static void goNext(){
        if (!inMenu) {
            if (!(currentmenu == -1)) closeMenu(currentmenu, false);
            if (!(head + screenFittingSpells > SpellNo)) {
                head = head + screenFittingSpells;
            }
            changePage();
        }
    }
    static void goPrevious(){
        if(!inMenu) {
            if (!(currentmenu == -1)) closeMenu(currentmenu, false);
            if (!(head == 0)) {
                head = head - screenFittingSpells;
            }
            changePage();
        }
    }
    static void changePage(){
        removeButtonIfEndOfMenu();
        System.out.println(components[0].main.getAlignmentY());
        for (int i = 0; i < (SpellNo); i++){
            components[i].main.setVisible(false);
        }
        for (int i = head;i <(head+screenFittingSpells)&&i<SpellNo;i++){
            components[i].main.setVisible(true);
        }
    }
    static void addATonOfComponents(){
        components = new MenuComponent[SpellNo];
        for (int i=0;i<SpellNo;i++){
            components[i]=new MenuComponent("New Spell "+(i+1));
            center.add(components[i].main);
        }
    }
}
class MenuComponent{
   JPanel main;
   Color mycolor;
   String myColorRGB;
   String colorStr;
   JButton open,roll;
   JLabel name;
   AListener listen;
   MenuComponent(String pname) {
        main=new JPanel(new FlowLayout());
        open=new JButton("View");
        roll=new JButton("Roll");
        colorStr="Default";
        myColorRGB="0,0,0";
        listen=new AListener();
        main.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        open.addActionListener(listen);
        roll.addActionListener(listen);
        name=new JLabel(pname);
        name.setPreferredSize(new Dimension(401,Spellbook.componentHeight));
        open.setPreferredSize(new Dimension(70,Spellbook.componentHeight));
        roll.setPreferredSize(new Dimension(70,Spellbook.componentHeight));
        main.add(open);
        main.add(name);
        main.add(roll);
        setColor(Color.WHITE);
    }
    void setText(String pname){
        name.setText(pname);
    }
    void setColor(Color pColor){
       mycolor=pColor;
       main.setBackground(mycolor);
    }
    Color getColor(){
       return mycolor;
    }
}

class Spell{
    String desc,range,name,level,school,speed,damage,RGBcolor;
    Boolean ritual,concentration,favorite;
    Spell(String pdesc, String prange, String pname, String plevel, String pschool, String pspeed, String pdamage, Boolean pritual, Boolean pconcentration, Boolean pfavorite,String pRGBcolor){
        if (pdesc=="") desc="No Description"; else desc=pdesc;
        if (prange=="") range="Special";else range=prange;
        if (pname=="") name="Magic"; else name=pname;
        if (plevel=="") level="1"; else level=plevel;
        if (pschool=="") school="General magic";else school=pschool;
        if (pspeed=="") speed="0";else speed=pspeed;
        if (pdamage=="") damage="0d0+0"; else damage=pdamage;
        ritual=pritual;
        concentration=pconcentration;
        favorite=pfavorite;
        if(pRGBcolor=="") RGBcolor="0,0,0"; else RGBcolor=pRGBcolor;
    }
    Spell defaultSpell(){
        return new Spell("","","","","","","",false,false,false,"0,0,0");
    }
    private int[] rolldmg(){
        return CommonFunctions.RollDice(damage);
    }
}
class SpellMenu{
    JComboBox farbwahl;
    JPanel main,submain,colorPanel;
    Border b;
    JTextField range,name,level,school,speed,damage;
    JTextArea desc;
    JFormattedTextField R,G,B;
    JLabel currentColor;
    JCheckBox ritual,concentration,favorite;
    JButton save;
    AListener listen;
    static NumberFormat format=NumberFormat.getNumberInstance();
    static NumberFormatter numfom=new NumberFormatter(format);
    SpellMenu(){
        String[] farben={"Very Default","Ruby","Aquamarine","Honey","Snake Green","Gold","Silver","Copper","Lapis","Double Special Surprise","Custom"};
        main= new JPanel(new GridLayout(1,2 ));
        submain=new JPanel(new GridLayout(13,1));
        numfom.setValueClass(Integer.class);
        numfom.setAllowsInvalid(false);
        R=new JFormattedTextField(numfom);
        G=new JFormattedTextField(numfom);
        B=new JFormattedTextField(numfom);
        currentColor=new JLabel();
        colorPanel=new JPanel(new GridLayout(1,4));
        colorPanel.add(R);
        R.setBorder(tborder("R"));
        colorPanel.add(G);
        G.setBorder(tborder("G"));
        colorPanel.add(B);
        B.setBorder(tborder("B"));
        R.setEditable(false);
        G.setEditable(false);
        B.setEditable(false);
        colorPanel.add(currentColor);
        farbwahl=new JComboBox(farben);
        favorite=new JCheckBox("Set as Favorite");
        b=BorderFactory.createLineBorder(Color.BLACK);
        save=new JButton("Return");
        listen=new AListener();
        save.addActionListener(listen);
        range=new JTextField("");
        range.setBorder(tborder("Range"));
        name=new JTextField("");
        name.setBorder(tborder("Name"));
        level=new JTextField("");
        level.setBorder(tborder("Level"));
        school=new JTextField("");
        school.setBorder(tborder("School"));
        speed=new JTextField("");
        speed.setBorder(tborder("Speed"));
        damage=new JTextField("");
        damage.setBorder(tborder("Damage"));
        desc=new JTextArea("");
        desc.setBorder(tborder("Description"));
        desc.setLineWrap(true);
        ritual=new JCheckBox("Ritual?");
        concentration=new JCheckBox("Concentration?");
        farbwahl.addActionListener(listen);
        submain.add(name);
        submain.add(level);
        submain.add(damage);
        submain.add(school);
        submain.add(speed);
        submain.add(ritual);
        submain.add(concentration);
        submain.add(favorite);
        main.add(submain);
        main.add(desc);
        submain.add(farbwahl);
        submain.add(colorPanel);
        submain.add(save);
    }
   String getStringRGB(){
            return R.getText()+","+G.getText()+","+B.getText();
   }
   Boolean stringIsColor(String test){
       String[] farben={"Very Default","Ruby","Aquamarine","Honey","Snake Green","Gold","Silver","Copper","Lapis","Double Special Surprise","Custom"};
        for (int i = 0;i<11;i++){
            if (test==farben[i]){
                return true;
            }
       }
      return false;
   }
    void setRGBsaves(String RGB){
        String[] tempoo=RGB.split(",");
        if(tempoo.length==3) {
            farbwahl.setSelectedItem("Custom");
            Spellbook.releaseRGBmaybe();
            R.setText(tempoo[0]);
            G.setText(tempoo[1]);
            B.setText(tempoo[2]);
        }
    }
   void fillFromSpell(Spell filler){
        name.setText(filler.name);
        range.setText(filler.range);
        desc.setText(filler.desc);
        concentration.setSelected(filler.concentration);
        level.setText(filler.level);
        school.setText(filler.school);
        speed.setText(filler.speed);
        damage.setText(filler.damage);
        ritual.setSelected(filler.ritual);
        favorite.setSelected(filler.favorite);
        currentColor.setBackground(Spellbook.getColorFromComboColor(filler.RGBcolor));
    }
     void saveToSpell(Spell filled){
       filled.name=name.getText();
       filled.range=range.getText();
       filled.desc=desc.getText();
       filled.concentration=concentration.isSelected();
       filled.level=level.getText();
       filled.school=school.getText();
       filled.speed=speed.getText();
       filled.damage=damage.getText();
       filled.ritual=ritual.isSelected();
       filled.favorite=favorite.isSelected();
       filled.RGBcolor=getStringRGB();
    }

     Spell CreateSpell(){
        return new Spell(desc.getText(),range.getText(),name.getText(),level.getText(),school.getText(),speed.getText(),damage.getText(),ritual.isSelected(),concentration.isSelected(),favorite.isSelected(),getStringRGB());
    }

    TitledBorder tborder(String title){
        var m=BorderFactory.createTitledBorder(b,title,TitledBorder.CENTER,TitledBorder.ABOVE_TOP);
        return m;
    }
}
