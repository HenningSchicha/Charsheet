package Henning.Schicha;

import java.util.Timer;

public class MainThread implements Runnable{
    public void run(){
        Timer timer= new Timer();
        timer.schedule(new Changer(), 0, (long) (1000/ Idle.fps));
    }
}
