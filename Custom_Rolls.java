package Henning.Schicha;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class Custom_Rolls {
    static JPanel main;
    static void init(){
        RollStats.init();
        main = new JPanel();
        main.add(RollStats.main);
    }
}

class RollStats{
    static JPanel main,center, left, right;
    static JComboBox<String> method;
    static JButton roller, translate;
    static AListener listen;
    static JLabel str, dex, con, inT, wis, chr;
    static JTextArea list;
    static JLabel[] all;
    static void init(){
        listen = new AListener();
        list = new JTextArea();
        String[] methods = {"3d6","4d6d1","18d6"};
        main = new JPanel(new BorderLayout());
        translate= new JButton("Apply");
        main.add(translate,BorderLayout.SOUTH);
        main.setBorder(BorderFactory.createTitledBorder("RfC"));
        center = new JPanel(new GridLayout(1,2));
        main.add(center, BorderLayout.CENTER);
        left = new JPanel(new BorderLayout());
        right = new JPanel(new GridLayout(6,1));
        center.add(left);
        center.add(right);
        method = new JComboBox<>(methods);
        roller = new JButton("Roll 'em");
        roller.addActionListener(listen);
        left.add(method,BorderLayout.NORTH);
        left.add(roller,BorderLayout.SOUTH);
        left.add(list,BorderLayout.CENTER);
        list.setEditable(false);
        list.setRows(3);
        initLabels();
        for (int i = 0; i < 6; i++){
            right.add(all[i]);
        }
    }
    static void initLabels(){
        str = new JLabel("Strength");
        dex = new JLabel("Dexterity");
        con = new JLabel("Constitution");
        inT = new JLabel("Intelligence");
        wis = new JLabel("Wisdom");
        chr = new JLabel("Charisma");
        all = new JLabel[6];
        all[0] = str;
        all[1] = dex;
        all[2] = con;
        all[3] = inT;
        all[4] = wis;
        all[5] = chr;
    }
    static int do4d6d1(){
        int[] values = CommonFunctions.RollDice("4d6+0");
        int one = values[1];
        int two = values[2];
        int thr = values[3];
        int fou = values[4];
        if ((one <= two) && (one<= thr) && (one <= fou)){
            return two+thr+fou;
        }
        if ((two <= one) && (two<= thr) && (two <= fou)){
            return one+thr+fou;
        }
        if ((thr <= two) && (thr<= one) && (thr <= fou)){
            return two+one+fou;
        }
        if ((fou <= two) && (fou<= thr) && (fou <= one)){
            return two+thr+one;
        }
        return 0;
    }
    static void rollem(){
        String[] prefixes = {"Str: ","Dex: ","Con: ","Int: ","Wis: ","Chr: "};
        switch ((String) Objects.requireNonNull(method.getSelectedItem())){
            case "3d6":
                for (int i=0;i<6;i++){
                    String prefix = prefixes[i];
                    String suffix = (CommonFunctions.RollDice("3d6+0")[0]+"");
                    all[i].setText(prefix+suffix);
                }
                break;
            case "4d6d1":
                for (int i = 0; i < 6; i++){
                    String prefix = prefixes[i];
                    String suffix =(do4d6d1()+"");
                    all[i].setText(prefix+suffix);
                }
                break;
            case "18d6":
                int[] biglist = CommonFunctions.RollDice("18d6+0");
                String total = "";
                for (int i = 0; i < 18; i++){
                    if (i!=17)total = total + (biglist[i+1]+", ");
                    else total = total + (biglist[i+1]+"");
                    if(i%6==5&&i!=17) total = total +"\n";
                }
                list.setText(total);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + (String) Objects.requireNonNull(method.getSelectedItem()));
        }
    }
}