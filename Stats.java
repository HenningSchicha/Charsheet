package Henning.Schicha;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Stats {
    static JPanel main;
    static void init(){
        CoreStats.init();
        main = new JPanel(new BorderLayout());
        main.add(CoreStats.main,BorderLayout.WEST);
    }
}
class CoreStats{
    static JPanel main;
    static JTextField[] stats;
    static JButton calc;
    final static String[] info = {"Strength","Dexterity","Constitution","Intelligence","Wisdom","Charisma"};
    static AListener listen;
    static void init(){
        listen = new AListener();
        main = new JPanel(new GridLayout(7,1));
        stats = new JTextField[6];
        calc = new JButton("Calculate");
        calc.addActionListener(listen);
        for (int i = 0; i < 6; i++){
            stats[i]=new JTextField("0");
            stats[i].setBorder(doubleTitle(info[i],"0"));
            main.add(stats[i]);
            stats[i].setPreferredSize(new Dimension(100,100));
            stats[i].setFont(new Font("Sans Serif", 0, 25));
            main.add(calc);
        }
    }
    static void calcMods(){
        for (int i = 0; i < 6 ; i++){
            int mod = -5;
            int base = Integer.parseInt(stats[i].getText());
            mod += Math.floor(base/2);
            stats[i].setBorder(doubleTitle(info[i],mod+""));
        }
    }
    static Border doubleTitle(String top, String mod){
        return BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.BLACK),top, TitledBorder.CENTER,TitledBorder.ABOVE_TOP),
                BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.BLACK),mod, TitledBorder.CENTER,TitledBorder.BELOW_BOTTOM));
    }
}