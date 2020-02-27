package Henning.Schicha;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Weightcalcing implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        Equipment.save();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        Equipment.save();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        Equipment.save();
    }
}
