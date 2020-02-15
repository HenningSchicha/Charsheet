package com.company;

public class CommonFunctions {
    public static int[] RollDice(String dmg){
        int[] dice=SplitDiceString(dmg);
        int[] res=new int[4];
        for (int i = 0; i < dice[0]; i++) {
            int a=(int) (Math.random() * (dice[1]) + 1);
            res[i+1]=a;
            res[0] = res[0]+a;
        }
        res[0] = res[0] + dice[2];
        return res;
    }
    public static int[] SplitDiceString(String Dice){
        Dice=Dice.replaceAll("\\s","");
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
    public static String cutWhitespace(String s){
        return s.replaceAll("\\s","");
    }

}
