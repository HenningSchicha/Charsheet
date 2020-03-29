package Henning.Schicha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class AListener implements ActionListener {
    static String hittext="hits with",dmgtext="hits for",crittext="crits for";
    @Override
    public void actionPerformed(ActionEvent e) {
        Idle.click();
        if(e.getSource()==UI.onetohit){
            UI.saveAttacks();
            UI.RollAttack(UI.AA,UI.AB,UI.AC,UI.weapone,hittext,false);
        }
        else if(e.getSource()==UI.twotohit){
            UI.saveAttacks();
            UI.RollAttack(UI.AG,UI.AH,UI.AI,UI.weaptwo,hittext,false);
        }
        else if(e.getSource()==UI.threetohit){
            UI.saveAttacks();
            UI.RollAttack(UI.AM,UI.AN,UI.AO,UI.weapthree,hittext,false);
        }
        else if(e.getSource()==UI.fourtohit){
            UI.saveAttacks();
            UI.RollAttack(UI.AS,UI.AT,UI.AU,UI.weapfour,hittext,false);
        }
        else if(e.getSource()==UI.oneattack){
            UI.saveAttacks();
            UI.RollAttack(UI.AD,UI.AE,UI.AF,UI.weapone,dmgtext,false);
        }
        else if(e.getSource()==UI.twoattack){
            UI.saveAttacks();
            UI.RollAttack(UI.AJ,UI.AK,UI.AL,UI.weaptwo,dmgtext,false);
        }
        else if(e.getSource()==UI.threeattack){
            UI.saveAttacks();
            UI.RollAttack(UI.AP,UI.AQ,UI.AR,UI.weapthree,dmgtext,false);
        }
        else if(e.getSource()==UI.fourattack){
            UI.saveAttacks();
            UI.RollAttack(UI.AV,UI.AW,UI.AX,UI.weapfour,dmgtext,false);
        }
        else if(e.getSource()==UI.onecrit){
            UI.saveAttacks();
            UI.RollAttack(UI.AD,UI.AE,UI.AF,UI.weapone,crittext,true);
        }
        else if(e.getSource()==UI.twocrit){
            UI.saveAttacks();
            UI.RollAttack(UI.AJ,UI.AK,UI.AL,UI.weaptwo,crittext,true);
        }
        else if(e.getSource()==UI.threecrit){
            UI.saveAttacks();
            UI.RollAttack(UI.AP,UI.AQ,UI.AR,UI.weapthree,crittext,true);
        }
        else if(e.getSource()==UI.fourcrit){
            UI.saveAttacks();
            UI.RollAttack(UI.AV,UI.AW,UI.AX,UI.weapfour,crittext,true);
        }
        else if(e.getSource()==UI.BAttacks){
            UI.collapseAll();
            UI.PAttacks.setVisible(true);
        }
        else if(e.getSource()==UI.BMiscellaneous){
            UI.collapseAll();
            Miscellaneous.main.setVisible(true);
        }
        else if(e.getSource()==UI.BStats){
            UI.collapseAll();
            UI.PStats.setVisible(true);
        }
        else if(e.getSource()==UI.BRolls){
            UI.collapseAll();
            UI.PRolls.setVisible(true);
        }
        else if(e.getSource()==UI.BEquip){
            UI.collapseAll();
            UI.PEquip.setVisible(true);
        }
        else if(e.getSource()==UI.BSpellbook){
            UI.collapseAll();
            UI.PSpellbook.setVisible(true);
        }
        else if(e.getSource()==UI.BNotes){
            UI.collapseAll();
            UI.PNotes.setVisible(true);
        }
        else if(e.getSource()==Spellbook.next){
            Spellbook.goNext();
        }
        else if(e.getSource()==Spellbook.previous){
            Spellbook.goPrevious();
        }
        else if(e.getSource()==Spellbook.searchbutton){
            Spellbook.openFavorites(false);
        }
        else if(e.getSource()==Spellbook.currentsave){
            Spellbook.closeMenu(Spellbook.currentmenu,true);
        }
        else if (e.getSource()==Spellbook.actualMenu.farbwahl){
            CommonFunctions.ComboBoxSelfColor(Spellbook.actualMenu.farbwahl);
            Spellbook.releaseRGBmaybe();
        }
        else if (e.getSource()==Notes.next){
            Notes.goNext();
        }
        else if (e.getSource()==Notes.previous){
            Notes.goPrevious();
        }
        else if (e.getSource()==Miscellaneous.bIdle){
            Miscellaneous.setVisible(Miscellaneous.pIdle);
        }
        else if (e.getSource()==Miscellaneous.bLog){
            Miscellaneous.setVisible(Miscellaneous.pLog);
        }
        else if (e.getSource()==Miscellaneous.bClear){
            Logger.clearLog();
        }
        else if (e.getSource()==Miscellaneous.bGoL){
            Miscellaneous.setVisible(Miscellaneous.pGoL);
        }
        else if (e.getSource()==RollStats.roller){
            RollStats.rollem();
        }
        else if (e.getSource()==RollMagicItem.roll){
            try {
                RollMagicItem.RollItem();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource()==CoreStats.calc){
            CoreStats.calcMods();
        }
        else for(int i = 0; i < Spellbook.SPELL_NO; i++){
            if (e.getSource()==Spellbook.components[i].open){
                Spellbook.openMenu(i);
            }
            else if (e.getSource()==Spellbook.components[i].roll){
                Spellbook.roll(Spellbook.Spells[i].damage,i);
            }
        }
    }
}
