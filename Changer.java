package com.company;

import java.util.TimerTask;

public class Changer extends TimerTask {

    @Override
    public void run() {
        Idle.update();
    }
}
