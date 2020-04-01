package Henning.Schicha;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Stats {
    static JPanel main;
    static JPanel invis;
    static void init(){
        CoreStats.init();
        Naming.init();
        main = new JPanel(new BorderLayout());
        main.add(CoreStats.main,BorderLayout.WEST);
        main.add(Naming.main,BorderLayout.NORTH);
        invis = new JPanel(new FlowLayout());
        main.add(invis,BorderLayout.CENTER);
        invis.add(ExpMenu.main);
    }
    static void collapseAll(){
        CoreStats.main.setVisible(false);
        Naming.main.setVisible(false);
        ExpMenu.main.setVisible(false);
    }
}
class ExpMenu{
    static JPanel main;
    static JPanel submain;
    static JPanel bot;
    static JTextField[] steps;
    static JCheckBox xpToLvl;
    static JButton back;
    static JLabel errorLabel;
    static final String[] LVL_UP_DEFAULT_BREAKPOINTS = {"300","900","2700","6500","14000","23000","34000","48000",
            "64000","85000","100000","120000","140000","165000","195000","225000","265000","305000","350000"};
    static void makeError(Boolean error){
        if (error)errorLabel.setText("Please enter good Breakpoints");
        else errorLabel.setText("");
    }
    static Boolean legitBreakpoints(){
        for (int i = 0; i < 18;i++){
            if (Integer.parseInt(steps[i].getText())>Integer.parseInt(steps[i+1].getText())) return false;
        }
        return true;
    }
    static void init(){
        main = new JPanel(new BorderLayout());
        bot  = new JPanel(new BorderLayout());
        main.add(bot,BorderLayout.SOUTH);
        submain = new JPanel(new GridLayout(10,2));
        main.add(submain,BorderLayout.CENTER);
        steps = new JTextField[19];
        errorLabel= new JLabel();
        for (int i = 2; i <= 20; i++){
            steps[i-2] = new JTextField(LVL_UP_DEFAULT_BREAKPOINTS[i-2]);
            steps[i-2].setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                    i+"",TitledBorder.CENTER,TitledBorder.LEFT));
            submain.add(steps[i-2]);
        }
        xpToLvl = new JCheckBox("Use Exp. Steps");
        xpToLvl.setSelected(true);
        submain.add(xpToLvl);
        back = new JButton("go back");
        bot.add(back,BorderLayout.CENTER);
        bot.add(errorLabel,BorderLayout.SOUTH);
        back.addActionListener(actionEvent -> Naming.closeMenu());
        main.setBorder(BorderFactory.createTitledBorder("Configure Experience breaks"));
    }
}
class Naming{
    static Boolean doXpToLvl;
    static JPanel main;
    static NamingKeyListener listen;
    static JTextField[] fields;
    static final String[] INFO = {"Name","Race","Class","Level","Experience"};
    static JButton conf;
    static void openMenu(){
        Stats.collapseAll();
        ExpMenu.main.setVisible(true);
    }
    static void closeMenu(){
        if (ExpMenu.legitBreakpoints()) {
            onCloseMenu();
            ExpMenu.makeError(false);
            Stats.collapseAll();
            Naming.main.setVisible(true);
            CoreStats.main.setVisible(true);
        }
        else ExpMenu.makeError(true);
    }
    static void onCloseMenu(){
        doXpToLvl=ExpMenu.xpToLvl.isSelected();
    }
    static void translateXpToLvl(){
        if (!doXpToLvl) return;
        int i = 0;
        try {
            while (Integer.parseInt(fields[4].getText()) >= Integer.parseInt(ExpMenu.steps[i].getText())&&i<19) {
                i++;
            }
        }catch (NumberFormatException|ArrayIndexOutOfBoundsException e){
            fields[3].setText("0");
        }
        fields[3].setText((i+1)+"");
    }
    static void init(){
        ExpMenu.init();
        listen = new NamingKeyListener();
        main   = new JPanel(new GridLayout(1,6));
        fields = new JTextField[5];
        for (int i = 0; i < 5; i++){
            fields[i] = new JTextField(INFO[i]);
            main.add(fields[i]);
            fields[i].setBorder(BorderFactory.createTitledBorder(INFO[i]));
            fields[i].addKeyListener(listen);
        }
        conf = new JButton("Conf. Exp.");
        conf.addActionListener(actionEvent -> openMenu());
        main.add(conf);
        closeMenu();
    }
    static void transferToUI(){
        translateXpToLvl();
        UI.LLvl.setText("Level: "+fields[3].getText());
        UI.LClass.setText(fields[2].getText());
        UI.LName.setText(fields[0].getText());
    }
}
class NamingKeyListener implements KeyListener{
    public void keyTyped(KeyEvent keyEvent) {
        Naming.transferToUI();
    }
    public void keyPressed(KeyEvent keyEvent) {
        Naming.transferToUI();
    }
    public void keyReleased(KeyEvent keyEvent) {
        Naming.transferToUI();
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
        main   = new JPanel(new GridLayout(7,1));
        stats  = new JTextField[6];
        calc   = new JButton("Calculate");
        calc.addActionListener(listen);
        for (int i = 0; i < 6; i++){
            stats[i]=new JTextField("0");
            stats[i].setBorder(doubleTitle(info[i],"0"));
            main.add(stats[i]);
            stats[i].setPreferredSize(new Dimension(95,95));
            stats[i].addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent keyEvent) {
                    calcMods();
                }
                public void keyPressed(KeyEvent keyEvent) {
                    calcMods();
                }
                public void keyReleased(KeyEvent keyEvent) {
                    calcMods();
                }
            });
            stats[i].setFont(new Font("Sans Serif", Font.PLAIN, 25));
            main.add(calc);
        }
    }
    static void setAll(String[] pstats){
        for (int i = 0; i < 6; i++){
            stats[i].setText(pstats[i]);
        }
        calcMods();
    }
    static void calcMods(){
        for (int i = 0; i < 6 ; i++){
            int mod = -5;
            int base;
            try {
                base = Integer.parseInt(stats[i].getText());
            }catch (NumberFormatException e){
                base = 0;
            }
            mod += Math.floor(base/2.0);
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