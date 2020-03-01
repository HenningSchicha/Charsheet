package Henning.Schicha;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Miscellaneous {
    static JPanel main, top, center, pIdle, pLog, pGoL;
    static JButton bIdle, bLog, bGoL, bClear;
    static AListener listen;
    static TextArea log;
    static TButton tButton;



    static void init(){
        bClear = new JButton("Clear log");
        log = new TextArea();
        log.setPreferredSize(new Dimension(600,600));
        log.setEditable(false);
        listen = new AListener();
        main = new JPanel(new BorderLayout());
        top = new JPanel(new GridLayout(1,10));
        center = new JPanel(new FlowLayout());
        pIdle = new JPanel();
        pLog = new JPanel();
        pGoL = new JPanel();
        bIdle = new JButton("Idle game");
        bLog = new JButton("Log");
        bGoL = new JButton("coming soon");
        main.add(top,BorderLayout.NORTH);
        main.add(center,BorderLayout.CENTER);
        center.add(pIdle);
        center.add(pLog);
        center.add(pGoL);
        collapseAll();
        top.add(bIdle);
        top.add(bLog);
        top.add(bGoL);
        bGoL.setPreferredSize(new Dimension(300,30));
        bIdle.addActionListener(listen);
        bLog.addActionListener(listen);
        bGoL.addActionListener(listen);
        pIdle.add(Idle.field);
        pLog.add(log);
        pLog.add(bClear);
        bClear.addActionListener(listen);
        tButton=new TButton();
        tButton.init();

        pGoL.add(tButton.main);
        tButton.setTButtonStyle(105,105);
        tButton.setTButtonFont(new Font("serif",Font.BOLD,50));
    }
    static void setVisible(JPanel remainer){
        collapseAll();
        remainer.setVisible(true);
    }
    static void collapseAll(){
        pIdle.setVisible(false);
        pLog.setVisible(false);
        pGoL.setVisible(false);
    }
    static void updateLog() throws IOException {
        File file = new File(Logger.FILE_PATH);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String totalLog = "";
        while ((st = br.readLine()) != null){
            totalLog=totalLog+st+"\n";
        }
        log.setText(totalLog);
    }
}
