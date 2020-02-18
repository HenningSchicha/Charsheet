package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Searching implements KeyListener {

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        Spellbook.search((JTextField) keyEvent.getSource());
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        Spellbook.search((JTextField) keyEvent.getSource());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        Spellbook.search((JTextField) keyEvent.getSource());
    }
}
