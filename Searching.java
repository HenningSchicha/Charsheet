package Henning.Schicha;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Searching implements KeyListener {


    @Override
    public void keyTyped(KeyEvent k) {
        if (k.getSource()==Spellbook.searchbar) {
            Spellbook.search((JTextField) k.getSource());
        }
        if ((k.getSource()==UI.wepone)||(k.getSource()==UI.weptwo)||(k.getSource()==UI.wepthree)||(k.getSource()==UI.wepfour)){
            UI.saveAttacks();
        }
        if (k.getSource()==Notes.searchBar){
            Notes.changePage();
        }
        if (k.getSource()==Notes.page){
            Notes.gotoPage();
        }
    }

    @Override
    public void keyPressed(KeyEvent k) {
        if (k.getSource()==Spellbook.searchbar) {
            Spellbook.search((JTextField) k.getSource());
        }
        if ((k.getSource()==UI.wepone)||(k.getSource()==UI.weptwo)||(k.getSource()==UI.wepthree)||(k.getSource()==UI.wepfour)){
            UI.saveAttacks();
        }
        if (k.getSource()==Notes.searchBar){
            Notes.changePage();
        }
        if (k.getSource()==Notes.page){
            Notes.gotoPage();
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
        if (k.getSource()==Spellbook.searchbar) {
            Spellbook.search((JTextField) k.getSource());
        }
        if ((k.getSource()==UI.wepone)||(k.getSource()==UI.weptwo)||(k.getSource()==UI.wepthree)||(k.getSource()==UI.wepfour)){
            UI.saveAttacks();
        }
        if (k.getSource()==Notes.searchBar){
            Notes.changePage();
        }
        if (k.getSource()==Notes.page){
            Notes.gotoPage();
        }
    }
}
