package Henning.Schicha;

import javax.swing.*;
import java.lang.reflect.Array;

public class CommonFunctions {
    public static int[] RollDice(String dmg){
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
    public static void ComboBoxSelfColor(JComboBox<String> JCB){
        JCB.setBackground(Spellbook.getColorFromComboColor((String) JCB.getSelectedItem()));
    }
    public static <T> T[] concatenateArr(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }
    public static int[] SplitDiceString(String Dice){
        Dice=Dice.replaceAll("\\s","");
        Dice=Dice.replaceAll("-","+-");
        String[] Split;
        String[] more;
        int[] Result=new int[3];
        Split=Dice.split("d");
        if(Split.length!=2){ Result[0]=-1;return Result;}
        more=Split[1].split("\\+");
        if(more.length!=2){ Result[0]=-1;return Result;}
        Result[0]=Integer.parseInt(Split[0]);
        Result[1]=Integer.parseInt(more[0]);
        Result[2]=Integer.parseInt(more[1]);
        return Result;
    }
    static int upTo(int pInt){
        while (pInt>255){
            pInt -= 255;
        }
        return pInt;
    }
    static int downTo(int pInt){
        while (pInt>255){
            pInt -= 255;
        }
        return 255-pInt;
    }
    static int halfWay(int pInt){
        pInt += 128;
        while (pInt>255){
            pInt -= 255;
        }
        return pInt;
    }
    public static String cutWhitespace(String s){
        return s.replaceAll("\\s","");
    }

}
