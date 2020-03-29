package Henning.Schicha;

import java.util.TimerTask;

class Changer extends TimerTask {

    @Override
    public void run() {
        Idle.update();
    }
}
