package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Spellbook {
    static JPanel top, outer;
    static JTextField searchbar;
    static JButton searchbutton, SAVEbutton;

    public static void init() throws IOException {
        outer = new JPanel();
        outer.setLayout(new BorderLayout());
        outer.setOpaque(true);
        top = new JPanel(new FlowLayout());
        searchbar = new JTextField();
        searchbar.setPreferredSize(new Dimension(400, 50));
        top.add(searchbar);
        searchbutton = new JButton("", new ImageIcon("src/com/company/searchMonocle.jpg"));
        top.add(searchbutton);
        searchbutton.setMargin(new Insets(0, 0, 0, 0));
        searchbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        SAVEbutton = new JButton("SAVEEE");
        top.add(SAVEbutton);
        outer.add(top,BorderLayout.NORTH);
        SAVEbutton.setPreferredSize(new Dimension(150, 50));
        outer.add(searchbar,BorderLayout.CENTER);
    }
}
class MenuComponent{
   static JPanel main;
   static JButton open,roll;
   static JLabel name;
   static AListener listen;
   MenuComponent(String pname) {
        main=new JPanel(new FlowLayout());
        open=new JButton("Edit");
        roll=new JButton("Roll");
        listen=new AListener();
        open.addActionListener(listen);
        roll.addActionListener(listen);
        name=new JLabel(pname);
    }
    private void setText(String pname){
        name.setText(pname);
    }
}

class Spell{
   static String desc,range,name,level,school,speed,damage;
   static Boolean ritual,concentration;
    Spell(String pdesc, String prange, String pname, String plevel, String pschool, String pspeed, String pdamage, Boolean pritual, Boolean pconcentration){
        if (pdesc=="") desc="No Description"; else desc=pdesc;
        if (prange=="") range="Special";else range=prange;
        if (pname=="") name="Magic"; else name=pname;
        if (plevel=="") level="1"; else level=plevel;
        if (pschool=="") school="General magic";else school=pschool;
        if (pspeed=="") speed="0";else speed=pspeed;
        if (pdamage=="") damage="0"; else damage=pdamage;
        ritual=pritual;
        concentration=pconcentration;
    }
   static Spell defaultSpell(){
        return new Spell("","","","","","","",false,false);
    }
   static private int[] rolldmg(){
        return CommonFunctions.RollDice(damage);
    }
}
class SpellMenu{
   static JPanel main;
   static Border b;
   static JTextField range,name,level,school,speed,damage;
   static JTextArea desc;
   static JCheckBox ritual,concentration;
   static JButton save;
   static AListener listen;
    private SpellMenu(){
        main=new JPanel(new FlowLayout());
        b=BorderFactory.createLineBorder(Color.BLACK);
        save=new JButton("Return");
        listen=new AListener();
        save.addActionListener(listen);
        range=new JTextField("");
        range.setBorder(tborder("range"));
        name=new JTextField("");
        name.setBorder(tborder("name"));
        level=new JTextField("");
        level.setBorder(tborder("level"));
        school=new JTextField("");
        school.setBorder(tborder("school"));
        speed=new JTextField("");
        speed.setBorder(tborder("speed"));
        damage=new JTextField("");
        damage.setBorder(tborder("damage"));
        desc=new JTextArea("");
        desc.setBorder(tborder("description"));
        ritual=new JCheckBox("Ritual?");
        concentration=new JCheckBox("Concentration?");
        main.add(name);
        main.add(level);
        main.add(damage);
        main.add(school);
        main.add(speed);
        main.add(ritual);
        main.add(concentration);
        main.add(desc);
        main.add(save);
    }
  static private void FillFromSpell(Spell filler){
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
    static private void SaveToSpell(Spell filled){
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

    static private Spell CreateSpell(){
        return new Spell(desc.getText(),range.getText(),name.getText(),level.getText(),school.getText(),speed.getText(),damage.getText(),ritual.isSelected(),concentration.isSelected());
    }

    static private CompoundBorder tborder(String title){
        var m=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(title),b);
        return m;
    }
}
