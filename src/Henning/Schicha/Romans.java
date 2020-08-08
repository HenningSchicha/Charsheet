package Henning.Schicha;

import javax.swing.*;
import java.awt.*;

public class Romans {
    static JPanel main, mid, bot;
    static JButton convert;
    static JTextField input, output;
    static JLabel description;
    static final int depth = 15;
    static final String[] numerals = {"I","V","X","L","C","D","M","V̅","X̅","L̅","C̅","D̅","M̅","V̅̅","X̅̅"};
    static final long[] numbers = {1,5,10,50,100,500,1000,5000,10000,50000,100000,500000,1000000,5000000,10000000};
    static void init(){
        bot = new JPanel(new GridLayout(depth,2));
        main = new JPanel(new BorderLayout());
        mid = new JPanel(new BorderLayout());
        convert = new JButton("Calculate");
        input = new JTextField(20);
        output = new JTextField(20);
        output.setEditable(false);
        description = new JLabel("Convert Decimal numbers and roman Numerals", SwingConstants.CENTER);
        convert.addActionListener(e -> chooseConversion());
        main.add(description,BorderLayout.NORTH);
        main.add(mid,BorderLayout.CENTER);
        main.add(bot,BorderLayout.SOUTH);
        mid.add(input,BorderLayout.WEST);
        mid.add(convert,BorderLayout.CENTER);
        mid.add(output,BorderLayout.EAST);
        fillInfoGrid();
    }
    static void fillInfoGrid(){
        for (int i = 0; i < depth; i++){
            JLabel RomanLabel = new JLabel(numerals[i]);
            JLabel NumberLabel = new JLabel(numbers[i]+"");
            Color borderColor = Color.BLACK;
            if (i % 2 == 1) borderColor = Color.WHITE;
            RomanLabel.setBorder(BorderFactory.createLineBorder(borderColor));
            NumberLabel.setBorder(BorderFactory.createLineBorder(borderColor));
            RomanLabel.setPreferredSize(new Dimension(RomanLabel.getPreferredSize().width, RomanLabel.getPreferredSize().height+7));
            bot.add(RomanLabel);
            bot.add(NumberLabel);
        }
    }
    static void chooseConversion(){
        String in = input.getText().toUpperCase();
        boolean isRoman = false;
        for (String str : numerals){
            if (in.contains(str)){
                isRoman = true;
                break;
            }
        }
        try {
            if (!isRoman) convertToRoman(in);
            else convertToDecimal(in);
        }
        catch (NumberFormatException NFE){
            output.setText("invalid input");
        }
    }

    static void convertToRoman(String in){
        long input = Long.parseLong(in);
        int Is=0, Xs=0, Cs=0, Ms=0, XXs=0, CCs=0, MMs=0;
        for (; input >= 1000000; input -= 1000000, MMs++){}
        for (; input >= 100000; input -= 100000, CCs++){}
        for (; input >= 10000; input -= 10000, XXs++){}
        for (; input >= 1000; input -= 1000, Ms++){}
        for (; input >= 100; input -= 100, Cs++){}
        for (; input >= 10; input -= 10, Xs++){}
        for (; input >= 1; input -= 1, Is++){}
        String out =
                convertOne("M̅","V̅̅","X̅̅",MMs)+
                        convertOne("C̅","D̅","M̅",CCs)+
                        convertOne("X̅","L̅","C̅",XXs)+
                        convertOne("M","V̅","X̅",Ms)+
                        convertOne("C","D","M",Cs)+
                        convertOne("X","L","C",Xs)+
                        convertOne("I","V","X",Is);
        output.setText(out);
    }

    static String convertOne(String one, String five, String ten, long amount){
        if (amount == 0) return "";
        else if (amount < 4) return CommonFunctions.repeatString(one, amount);
        else if (amount == 4) return one+five;
        else if (amount < 9) return five + CommonFunctions.repeatString(one, amount-5);
        else if (amount == 9) return one + ten;
        else return "?";
    }
    static void convertToDecimal(String in){
        String Ms = "";
        if (in.contains("M")) {
            Ms = in.substring(0, in.lastIndexOf("M")+1);
            in = in.substring(in.lastIndexOf("M")+1);
        }
        String Ds = "";
        if (in.contains("D")) {
            Ds = in.substring(0, in.lastIndexOf("D")+1);
            in = in.substring(in.lastIndexOf("D")+1);
        }
        String Cs = "";
        if (in.contains("C")) {
            Cs = in.substring(0, in.lastIndexOf("C")+1);
            in = in.substring(in.lastIndexOf("C")+1);
        }
        String Ls = "";
        if (in.contains("L")) {
            Ls = in.substring(0, in.lastIndexOf("L")+1);
            in = in.substring(in.lastIndexOf("L")+1);
        }
        String Xs = "";
        if (in.contains("X")) {
            Xs = in.substring(0, in.lastIndexOf("X")+1);
            in = in.substring(in.lastIndexOf("X")+1);
        }
        String Vs = "";
        if (in.contains("V")) {
            Vs = in.substring(0, in.lastIndexOf("V")+1);
            in = in.substring(in.lastIndexOf("V")+1);
        }
        String Is = "";
        if (in.contains("I")) {
            Is = in.substring(0, in.lastIndexOf("I")+1);
        }
        long result = 0;
        result += addInNumbers(Ms, 'M', 'D', 'C', 1000, 500, 100);
        result += addInNumbers(Ds, 'D', 'C', 'L', 500, 100, 50);
        result += addInNumbers(Cs, 'C', 'L', 'X', 100, 50, 10);
        result += addInNumbers(Ls, 'L', 'X', 'V', 50, 10, 5);
        result += addInNumbers(Xs, 'X', 'V', 'I', 10, 5, 1);
        result += addInNumbers(Vs, 'V', 'I', '?', 5, 1, 1);
        result += addInNumbers(Is, 'I', '?', '?', 1, 1, 1);
        output.setText(result+"");
    }
    static long addInNumbers(String total, char one, char two, char three, long onev, long twov, long threev){
        long result = 0;
        result += CommonFunctions.charInString(one, total) * onev;
        result -= CommonFunctions.charInString(two, total) * twov;
        result -= CommonFunctions.charInString(three, total) * threev;
        return result;
    }
}
