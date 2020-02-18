package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class AListener implements ActionListener {
    static String hittext="hits with",dmgtext="hits for",crittext="crits for";
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("YEP COCK");
        Idle.click();
        if (e.getSource()==UI.saveAttacks){
            UI.saveAttacks();
        }
        if(e.getSource()==UI.onetohit){
            UI.saveAttacks();
            UI.RollAttack(UI.AA,UI.AB,UI.AC,UI.weapone,hittext,false);
        }
        if(e.getSource()==UI.twotohit){
            UI.saveAttacks();
            UI.RollAttack(UI.AG,UI.AH,UI.AI,UI.weaptwo,hittext,false);
        }
        if(e.getSource()==UI.threetohit){
            UI.saveAttacks();
            UI.RollAttack(UI.AM,UI.AN,UI.AO,UI.weapthree,hittext,false);
        }
        if(e.getSource()==UI.fourtohit){
            UI.saveAttacks();
            UI.RollAttack(UI.AS,UI.AT,UI.AU,UI.weapfour,hittext,false);
        }
        if(e.getSource()==UI.oneattack){
            UI.saveAttacks();
            UI.RollAttack(UI.AD,UI.AE,UI.AF,UI.weapone,dmgtext,false);
        }
        if(e.getSource()==UI.twoattack){
            UI.saveAttacks();
            UI.RollAttack(UI.AJ,UI.AK,UI.AL,UI.weaptwo,dmgtext,false);
        }
        if(e.getSource()==UI.threeattack){
            UI.saveAttacks();
            UI.RollAttack(UI.AP,UI.AQ,UI.AR,UI.weapthree,dmgtext,false);
        }
        if(e.getSource()==UI.fourattack){
            UI.saveAttacks();
            UI.RollAttack(UI.AV,UI.AW,UI.AX,UI.weapfour,dmgtext,false);
        }
        if(e.getSource()==UI.onecrit){
            UI.saveAttacks();
            UI.RollAttack(UI.AD,UI.AE,UI.AF,UI.weapone,crittext,true);
        }
        if(e.getSource()==UI.twocrit){
            UI.saveAttacks();
            UI.RollAttack(UI.AJ,UI.AK,UI.AL,UI.weaptwo,crittext,true);
        }
        if(e.getSource()==UI.threecrit){
            UI.saveAttacks();
            UI.RollAttack(UI.AP,UI.AQ,UI.AR,UI.weapthree,crittext,true);
        }
        if(e.getSource()==UI.fourcrit){
            UI.saveAttacks();
            UI.RollAttack(UI.AV,UI.AW,UI.AX,UI.weapfour,crittext,true);
        }
        if(e.getSource()==UI.BAttacks){
            UI.collapseAll();
            UI.PAttacks.setVisible(true);
        }
        if(e.getSource()==UI.BIdle){
            UI.collapseAll();
            Idle.field.setVisible(true);
        }
        if(e.getSource()==UI.BStats){
            UI.collapseAll();
            UI.PStats.setVisible(true);
        }
        if(e.getSource()==UI.BRolls){
            UI.collapseAll();
            UI.PRolls.setVisible(true);
        }
        if(e.getSource()==UI.BEquip){
            UI.collapseAll();
            UI.PEquip.setVisible(true);
        }
        if(e.getSource()==UI.BSpellbook){
            UI.collapseAll();
            UI.PSpellbook.setVisible(true);
        }
        if(e.getSource()==Equipment.saveEquip){
            Equipment.save();
        }
        if(e.getSource()==Spellbook.next){
            Spellbook.goNext();
        }
        if(e.getSource()==Spellbook.previous){
            Spellbook.goPrevious();
        }
        for(int i = 0;i < Spellbook.SpellNo;i++){
            if (e.getSource()==Spellbook.components[i].open){
                Spellbook.openMenu(i);
            }
            if (e.getSource()==Spellbook.components[i].roll){
                Spellbook.roll(Spellbook.Spells[i].damage);
            }
        }
        if(e.getSource()==Spellbook.currentsave){
            Spellbook.closeMenu(Spellbook.currentmenu,true);
        }
    }
}
