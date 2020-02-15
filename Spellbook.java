package com.company;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class Spellbook {
}

class MenuComponent{
    JPanel main;
    JButton open,roll;
    JLabel name;
    AListener listen;
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
    String desc,range,name,level,school,speed,damage;
    Boolean ritual,concentration;
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
    Spell defaultSpell(){
        return new Spell("","","","","","","",false,false);
    }
    private int[] rolldmg(){
        return CommonFunctions.RollDice(damage);
    }
}
class SpellMenu{
    JPanel main;
    Border b;
    JTextField range,name,level,school,speed,damage;
    JTextArea desc;
    JCheckBox ritual,concentration;
    JButton save;
    AListener listen;
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
    private void FillFromSpell(Spell filler){
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
    private void SaveToSpell(Spell filled){
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

    private Spell CreateSpell(){
        return new Spell(desc.getText(),range.getText(),name.getText(),level.getText(),school.getText(),speed.getText(),damage.getText(),ritual.isSelected(),concentration.isSelected());
    }

    private CompoundBorder tborder(String title){
        var m=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(title),b);
        return m;
    }
}
