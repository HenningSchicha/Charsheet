package com.company;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class Equipment {
    static AListener listen;
    static String sBody,sOneBag,sTwoBag = "",sWeight,sBodyWeight,sOneBagWeight,sTwoBagWeight,sGold,sSilver,sCopper;
    static JButton saveEquip;
    static JPanel outer,inner,grid,pOuter,pInner;
    static JTextArea tBody,tOneBag,tTwoBag,tWeight,tGold,tSilver,tCopper;
    static JFormattedTextField tBodyWeight,tOneBagWeight,tTwoBagWeight;
    static NumberFormat format=NumberFormat.getNumberInstance();
    static NumberFormatter numfom=new NumberFormatter(format);
    public static void init(){
        if (sTwoBag.equals("")) sTwoBag="\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n                                                                                    ";
        saveEquip=new JButton("Save all");
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
        saveEquip.addActionListener(listen);
        tWeight.setEditable(false);
        grid.add(tBody);
        grid.add(tOneBag);
        grid.add(tTwoBag);
        pOuter.add(tWeight);
        pOuter.add(tGold);
        pOuter.add(tSilver);
        pOuter.add(tCopper);
        pOuter.add(saveEquip);
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
