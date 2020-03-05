package Henning.Schicha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Objects;

public class Custom_Rolls {
    static JPanel main;
    static void init(){
        RollStats.init();
        RollMagicItem.init();
        QuickRoll.init();
        main = new JPanel(new BorderLayout());
        main.add(RollStats.main, BorderLayout.WEST);
        main.add(RollMagicItem.main,BorderLayout.EAST);
        main.add(QuickRoll.main,BorderLayout.NORTH);
    }
}
class QuickRoll{
    static QuickRollListener listen;
    static JPanel main, center;
    static JTextArea[] history;
    static JTextField roll;
    static final int HISTORY_LENGTH = 10;
    static void init(){
        listen = new QuickRollListener();
        main = new JPanel(new BorderLayout());
        main.setBorder(BorderFactory.createTitledBorder("Quick Roll"));
        center = new JPanel(new GridLayout(HISTORY_LENGTH,1));
        history = new JTextArea[HISTORY_LENGTH];
        roll = new JTextField("0d0+0");
        roll.addKeyListener(listen);
        main.add(roll,BorderLayout.NORTH);
        main.add(center,BorderLayout.CENTER);
        for (int i = 0;i <HISTORY_LENGTH; i++){
            history[i] = new JTextArea();
            center.add(history[i]);
            history[i].setEditable(false);
            history[i].setBorder(BorderFactory.createEtchedBorder());
        }
    }
    static void roll(){
        int[] inter = CommonFunctions.RollDice(roll.getText());
        for (int i = (HISTORY_LENGTH-1);i>0;i--){
            history[i].setText(history[i-1].getText());
        }
        String inBracket = "";
        for (int i = 1; i < inter.length;i++){
            inBracket = inBracket + inter[i] + " ";
        }
        inBracket=inBracket.stripTrailing();
        history[0].setText("Rolled "+roll.getText()+": "+inter[0]+" ("+inBracket+")");
        Logger.log("<Quick rolled> "+roll.getText()+": "+inter[0]+" ("+inBracket+")");
    }
}
class QuickRollListener implements KeyListener{

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode()==KeyEvent.VK_ENTER){
            QuickRoll.roll();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
class RollMagicItem{
    static AListener listen;
    static JPanel main;
    static JPanel center;
    static JPanel bot;
    static JLabel name;
    static JLabel type;
    static JLabel rarity;
    static JLabel attune;
    static JLabel attune_res;
    static JLabel cursed;
    static JButton roll;
    static final int ROW_NO = 1138;

    static void init(){
        listen = new AListener();
        main = new JPanel(new BorderLayout());
        main.setBorder(BorderFactory.createTitledBorder("Random Magic Item"));
        center = new JPanel();
        bot = new JPanel(new GridLayout(6,1));
        name = new JLabel("Name");
        type = new JLabel("Type");
        rarity = new JLabel("Rarity");
        attune = new JLabel("Attunement");
        attune_res = new JLabel("AttunementRestr.");
        cursed = new JLabel("Cursed?");
        roll = new JButton("Get Magic Item");
        main.add(center, BorderLayout.CENTER);
        main.add(bot,BorderLayout.SOUTH);
        roll.addActionListener(listen);
        main.setPreferredSize(new Dimension(300,main.getHeight()));
        center.add(roll);
        bot.add(name);
        bot.add(type);
        bot.add(rarity);
        bot.add(attune);
        bot.add(attune_res);
        bot.add(cursed);
    }

    static void RollItem() throws IOException {
        File file = new File("resources/MagicItems.CSV");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String[] allTheLines = new String[ROW_NO];
        for (int i = 0; i < (ROW_NO-1); i++) {
            allTheLines[i] = br.readLine();
        }
        int selector = (int) (Math.random()*(ROW_NO-1));
        String[] lines = allTheLines[selector].split(";");
        name.setText("Name: "+lines[0]);
        type.setText("Type: "+lines[1]);
        rarity.setText("Rarity: "+lines[2]);
        attune.setText("Attune: "+((lines[3].equals("yes")) ? "Yes":"No"));
        attune_res.setText("Att_Res: "+lines[4]);
        cursed.setText("Cursed: "+ ((lines.length==7) ? "Yes":"No" ));
        Logger.log("<Rolled Magic Item> "+lines[0]+" : "+lines[1]+" : "+lines[2]+" : "+((lines[3].equals("yes")) ? "Yes":"No")+" : "+lines[4]+" : "+((lines.length==7) ? "Yes":"No" ));
    }
}
class RollStats{
    static JPanel main;
    static JPanel center;
    static JPanel left;
    static JPanel right;
    static JComboBox<String> method;
    static JButton roller;
    static JButton translate;
    static AListener listen;
    static JLabel str;
    static JLabel dex;
    static JLabel con;
    static JLabel inT;
    static JLabel wis;
    static JLabel chr;
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
                    Logger.log("<Rolled Character by 3d6> "+all[i].getText());
                }
                break;
            case "4d6d1":
                for (int i = 0; i < 6; i++){
                    String prefix = prefixes[i];
                    String suffix =(do4d6d1()+"");
                    all[i].setText(prefix+suffix);
                    Logger.log("<Rolled Character by 4d6d1> "+all[i].getText());
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
                Logger.log("<Rolled Character by 18d6> "+total.replace("\n",""));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + (String) Objects.requireNonNull(method.getSelectedItem()));
        }
    }
}