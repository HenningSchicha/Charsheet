package Henning.Schicha;

import java.util.TimerTask;

public class Changer extends TimerTask {

    @Override
    public void run() {
        Idle.update();
    }
}
