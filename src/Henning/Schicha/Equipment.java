package Henning.Schicha;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class Equipment {
    static Weightcalcing wait;
    static AListener listen;
    static String sBody;
    static String sOneBag;
    static String sTwoBag = "";
    static String sWeight;
    static String sBodyWeight;
    static String sOneBagWeight;
    static String sTwoBagWeight;
    static String sGold;
    static String sSilver;
    static String sCopper;
    static JPanel outer;
    static JPanel inner;
    static JPanel grid;
    static JPanel pOuter;
    static JPanel pInner;
    static JTextArea tBody;
    static JTextArea tOneBag;
    static JTextArea tTwoBag;
    static JTextArea tWeight;
    static JTextArea tGold;
    static JTextArea tSilver;
    static JTextArea tCopper;
    static JFormattedTextField tBodyWeight;
    static JFormattedTextField tOneBagWeight;
    static JFormattedTextField tTwoBagWeight;
    static NumberFormat format=NumberFormat.getNumberInstance();
    static NumberFormatter numfom=new NumberFormatter(format);
    public static void init(){
        wait= new Weightcalcing();
        outer=new JPanel(new BorderLayout());
        inner=new JPanel(new BorderLayout());
        grid=new JPanel(new GridLayout(1,3,2,5));
        pOuter=new JPanel(new GridLayout(1,5,2,5));
        pInner=new JPanel(new GridLayout(1,3,2,5));
        pInner.setVisible(true);
        pOuter.setVisible(true);
        grid.setVisible(true);
        inner.setVisible(true);
        outer.setVisible(true);
        listen=new AListener();
        outer.add(inner,BorderLayout.CENTER);
        inner.add(grid,BorderLayout.CENTER);
        outer.add(pOuter,BorderLayout.SOUTH);
        inner.add(pInner,BorderLayout.SOUTH);
        tBody=new JTextArea(sBody);
        tBody.setPreferredSize(new Dimension(250,500));
        tBody.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Body",TitledBorder.TOP,TitledBorder.TOP));
        tOneBag=new JTextArea(sOneBag);
        tOneBag.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Bag one",TitledBorder.TOP,TitledBorder.TOP));
        tTwoBag=new JTextArea(sTwoBag);
        numfom.setValueClass(Double.class);
        numfom.setAllowsInvalid(true);
        tTwoBag.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Bag two",TitledBorder.TOP,TitledBorder.TOP));
        tBodyWeight=new JFormattedTextField(numfom);
        tBodyWeight.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Weight",TitledBorder.TOP,TitledBorder.TOP));
        tOneBagWeight=new JFormattedTextField(numfom);
        tOneBagWeight.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Weight",TitledBorder.TOP,TitledBorder.TOP));
        tTwoBagWeight=new JFormattedTextField(numfom);
        tBodyWeight.addKeyListener(wait);
        tOneBagWeight.addKeyListener(wait);
        tTwoBagWeight.addKeyListener(wait);
        tOneBagWeight.setText(sOneBagWeight);
        tBodyWeight.setText(sBodyWeight);
        tTwoBagWeight.setText(sTwoBagWeight);
        tTwoBagWeight.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Weight",TitledBorder.TOP,TitledBorder.TOP));
        tWeight=new JTextArea(sWeight);
        tWeight.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Total weight",TitledBorder.TOP,TitledBorder.TOP));
        tGold=new JTextArea(sGold);
        tGold.setBackground(new Color(212,175,55));
        tGold.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Gold",TitledBorder.TOP,TitledBorder.TOP));
        tSilver=new JTextArea(sSilver);
        tSilver.setBackground(new Color(129,129,129));
        tSilver.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Silver",TitledBorder.TOP,TitledBorder.TOP));
        tCopper=new JTextArea(sCopper);
        tCopper.setBackground(new Color(184,115,51));
        tCopper.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Copper",TitledBorder.TOP,TitledBorder.TOP));
        tWeight.setEditable(false);
        grid.add(tBody);
        grid.add(tOneBag);
        grid.add(tTwoBag);
        pOuter.add(tWeight);
        pOuter.add(tGold);
        pOuter.add(tSilver);
        pOuter.add(tCopper);
        pInner.add(tBodyWeight);
        pInner.add(tOneBagWeight);
        pInner.add(tTwoBagWeight);
    }
    public static void save(){
        sBody=tBody.getText();
        sOneBag=tOneBag.getText();
        sTwoBag=tTwoBag.getText();
        sBodyWeight=getsafeText(tBodyWeight);
        sOneBagWeight=getsafeText(tOneBagWeight);
        sTwoBagWeight=getsafeText(tTwoBagWeight);
        combineWeight();
        sWeight=tWeight.getText();
        sGold=tGold.getText();
        sSilver=tSilver.getText();
        sCopper=tCopper.getText();
    }
    public static String getsafeText(JFormattedTextField j){
        if (j.getText().equals("")) return "0";
        else return j.getText();
    }
    static void combineWeight(){
        sOneBagWeight=CommonFunctions.cutWhitespace(sOneBagWeight);
        sTwoBagWeight=CommonFunctions.cutWhitespace(sTwoBagWeight);
        sBodyWeight=CommonFunctions.cutWhitespace(sBodyWeight);
        double one,two,three;
        one=Double.parseDouble(sOneBagWeight);
        two=Double.parseDouble(sTwoBagWeight);
        three=Double.parseDouble(sBodyWeight);
        tWeight.setText(one+two+three+"");
    }

}
