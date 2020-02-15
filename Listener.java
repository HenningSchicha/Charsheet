package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Listener implements ActionListener {
    boolean beginning= true;
    public static ArrayList<MainThread> things = new ArrayList<>();
    private static ExecutorService pool= Executors.newFixedThreadPool(10);
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== Idle.main) {
            if(beginning){
                MainThread clientThread = new MainThread();
                things.add(clientThread);
                pool.execute(clientThread);
                beginning=false;
                Idle.firstB.setText("Buy Click");
                Idle.secondB.setText("Buy Person");
                Idle.thirdB.setText("         Buy Company         ");
                Idle.fourthB.setText("Buy System");
                Idle.firstU.setText("Upgrade Click");
                Idle.secondU.setText("Upgrade People");
                Idle.thirdU.setText("      Upgrade Companies      ");
                Idle.fourthU.setText("Upgrade Systems");
                Idle.main.setText("                  This is fun i swear                  ");
                Idle.maindesc.setBackground(new Color(179, 134, 0));
                Idle.main.setBackground(new Color(0, 179, 89));
            }
            Idle.click();
        }
        if (e.getSource()== Idle.firstB){
            Idle.buyclick();
        }
        if(e.getSource()== Idle.secondB){
            Idle.buyperson();
        }
        if(e.getSource()== Idle.thirdB){
            Idle.buycompany();
        }
        if(e.getSource()== Idle.fourthB){
            Idle.buysystem();
        }
        if(e.getSource()== Idle.firstU){
            Idle.buyClickUpgrade();
        }
        if(e.getSource()== Idle.secondU){
            Idle.buyPersonUpgrade();
        }
        if(e.getSource()== Idle.thirdU){
            Idle.buyCompanyUpgrade();
        }
        if(e.getSource()== Idle.fourthU){
            Idle.buySystemUpgrade();
        }
    }
}
