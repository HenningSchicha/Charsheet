package Henning.Schicha;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ColorUpdateListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        Spellbook.updateColors();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        Spellbook.updateColors();

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        Spellbook.updateColors();

    }
}
