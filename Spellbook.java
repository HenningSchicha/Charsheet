package com.company;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.io.IOException;


public class Spellbook {
    static JPanel top, outer, center;
    static JTextField searchbar;
    static JButton searchbutton, SAVEbutton, next, previous, currentsave;
    static JLabel result;
    static MenuComponent[] components;
    static Spell[] Spells;
    static SpellMenu actualMenu;
    static AListener listen;
    static final int SpellNo = 255;
    static int head,screenFittingSpells,componentHeight,currentmenu;
    static Searching searchListener;
    static Boolean inMenu;

    public static void init() throws IOException {
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
        center.setPreferredSize(new Dimension(540,(componentHeight+18)*screenFittingSpells));
        outer.setLayout(new BorderLayout());
        outer.add(center,BorderLayout.CENTER);
        outer.setOpaque(true);
        addATonOfComponents();
        top = new JPanel(new FlowLayout());
        searchbar = new JTextField();
        previous= new JButton("", new ImageIcon("src/com/company/leftArrow.png"));
        top.add(previous);
        searchbar.setPreferredSize(new Dimension(400, 50));
        top.add(searchbar);
        next = new JButton("",new ImageIcon("src/com/company/rightArrow.png"));
        searchbutton = new JButton("", new ImageIcon("src/com/company/searchMonocle.jpg"));
        top.add(searchbutton);
        searchbutton.setMargin(new Insets(0, 0, 0, 0));
        searchbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        SAVEbutton = new JButton("SAVEEE");
        //top.add(SAVEbutton);
        outer.add(top,BorderLayout.NORTH);
        SAVEbutton.setPreferredSize(new Dimension(150, 50));
        result=new JLabel("Next Result will appear here");
        outer.add(result,BorderLayout.SOUTH);
        top.add(next);
        next.setMargin(new Insets(0,0,0,0));
        previous.setMargin(new Insets(0,0,0,0));
        next.addActionListener(listen);
        previous.addActionListener(listen);
        SAVEbutton.addActionListener(listen);
        searchbar.addKeyListener(searchListener);
        addATonOfSpells();
        changePage();
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
    static void roll(String input){
        int[] results = CommonFunctions.RollDice(input);
        String Result="You rolled "+results[0]+" (";
        for(int i=1;i<results.length;i++){
            Result=Result+results[i]+" ";
        }
        Result=Result.stripTrailing();
        Result=Result+")";
        Result=Result+" + "+CommonFunctions.SplitDiceString(input)[CommonFunctions.SplitDiceString(input).length-1];
        Result=Result+" with "+Spells[currentmenu].name;
        result.setText(Result);
    }
    static void openMenu(int current){
        for (int i = 0; i < SpellNo; i++){
            components[i].main.setVisible(false);
        }
        inMenu=true;
        SpellMenu menu= new SpellMenu();
        menu.fillFromSpell(Spells[current]);
        center.add(menu.main);
        currentsave=menu.save;
        currentmenu=current;
        actualMenu=menu;
    }
    static void closeMenu(int current,boolean fromMenu){
        actualMenu.main.setVisible(false);
        changePage();
        if (fromMenu)actualMenu.saveToSpell(Spells[current]);
        components[current].setText(actualMenu.name.getText());
        inMenu=false;
    }
    static void addATonOfSpells(){
        Spells = new Spell[SpellNo];
        for (int i = 0; i < SpellNo; i++){
            Spells[i]=new Spell("A New Spelldescription","0","New Spell "+(i+1),"1","None","0","0d0+0",false,false);
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
   JButton open,roll;
   JLabel name;
   AListener listen;
   MenuComponent(String pname) {
        main=new JPanel(new FlowLayout());
        open=new JButton("View");
        roll=new JButton("Roll");
        listen=new AListener();
        main.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        open.addActionListener(listen);
        roll.addActionListener(listen);
        name=new JLabel(pname);
        name.setPreferredSize(new Dimension(400,Spellbook.componentHeight));
        open.setPreferredSize(new Dimension(70,Spellbook.componentHeight));
        roll.setPreferredSize(new Dimension(70,Spellbook.componentHeight));
        main.add(open);
        main.add(name);
        main.add(roll);
    }
    void setText(String pname){
        name.setText(pname);
    }

}

class Spell{
    String desc,range,name,level,school,speed,damage;
    Boolean ritual,concentration;
    Spell(String pdesc, String prange, String pname, String plevel, String pschool, String pspeed, String pdamage, Boolean pritual, Boolean pconcentration){
        if (pdesc=="") desc="No Description"; else desc=pdesc;
        if (prange=="") range="Special";else range=prange;
        if (pname=="") name="Magic"; else name=pname;
        if (plevel=="") level="1"; else level=plevel;
        if (pschool=="") school="General magic";else school=pschool;
        if (pspeed=="") speed="0";else speed=pspeed;
        if (pdamage=="") damage="0d0+0"; else damage=pdamage;
        ritual=pritual;
        concentration=pconcentration;
    }
    Spell defaultSpell(){
        return new Spell("","","","","","","",false,false);
    }
    private int[] rolldmg(){
        return CommonFunctions.RollDice(damage);
    }
}
class SpellMenu{
    JComboBox farbwahl;
    JPanel main,submain;
    Border b;
    JTextField range,name,level,school,speed,damage;
    JTextArea desc;
    JCheckBox ritual,concentration;
    JButton save;
    AListener listen;
    SpellMenu(){
        String[] farben={"Red","Blue","Yellow","Green","Gold","Silver","Bronze",};
        main= new JPanel(new FlowLayout());
        submain=new JPanel(new GridLayout(10,1));
        farbwahl=new JComboBox(farben);
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
        ritual=new JCheckBox("Ritual?");
        concentration=new JCheckBox("Concentration?");
        submain.add(name);
        submain.add(level);
        submain.add(damage);
        submain.add(school);
        submain.add(speed);
        submain.add(ritual);
        submain.add(concentration);
        main.add(submain);
        main.add(desc);
        submain.add(farbwahl);
        submain.add(save);
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
    }

     Spell CreateSpell(){
        return new Spell(desc.getText(),range.getText(),name.getText(),level.getText(),school.getText(),speed.getText(),damage.getText(),ritual.isSelected(),concentration.isSelected());
    }

    CompoundBorder tborder(String title){
        var m=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(title),b);
        return m;
    }
}
