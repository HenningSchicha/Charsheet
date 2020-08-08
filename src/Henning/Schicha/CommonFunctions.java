package Henning.Schicha;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.Objects;

class CommonFunctions {
    static int[] RollDice(String dmg){
        int[] dice=SplitDiceString(dmg);
        int[] res=new int[dice[0]+1];
        for (int i = 0; i < dice[0]; i++) {
            int a=(int) (Math.random() * (dice[1]) + 1);
            res[i+1]=a;
            res[0] = res[0]+a;
        }
        res[0] = res[0] + dice[2];
        return res;
    }
    static void ComboBoxSelfColor(JComboBox<String> JCB){
        JCB.setBackground(Spellbook.getColorFromComboColor((String) Objects.requireNonNull(JCB.getSelectedItem())));
    }

    static int[] SplitDiceString(String Dice){
        Dice = cutWhitespace(Dice);
        Dice = Dice.replaceAll("-","+-");
        String[] Split;
        String[] more;
        int[] Result=new int[3];
        Split = Dice.split("d");
        if(Split.length!=2){
            Result[0]=-1;
            return Result;
        }
        more=Split[1].split("\\+");
        if(more.length!=2){
            Result[0]=-1;
            return Result;
        }
        Result[0] = Integer.parseInt(Split[0]);
        Result[1] = Integer.parseInt(more[0]);
        Result[2] = Integer.parseInt(more[1]);
        return Result;
    }
    static int upTo(int pInt){
        return pInt % 256;
    }
    static int downTo(int pInt){
        return 255 - (pInt % 256);
    }
    static int halfWay(int pInt){
        pInt += 128;
        return pInt % 256;
    }
    static String cutWhitespace(String o){
        String s = o.replaceAll("\\.","");
        return s.replaceAll("\\s","");
    }
    static String repeatString(String s, long times){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++){
            result.append(s);
        }
        return result.toString();
    }
    static int charInString(char c, String s){
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == c)
                count++;
        }
        return count;
    }

}
