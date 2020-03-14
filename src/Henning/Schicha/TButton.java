package Henning.Schicha;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class TButton {
    enum Variant {
        DEFAULT,
        UPDOWN,
        LEFTRIGHT,
    }

    TButtonListenerUP listenUP;
    TButtonListenerDOWN listenDOWN;
    SpecialTButtonListenerUP listenSpecialUP;
    SpecialTButtonListenerDOWN listenSpecialDOWN;
    JPanel main;
    JFormattedTextField numberField;
    JButton leftButtom, rightButtom, upButtom, downButtom;
    JPanel leftTopHolder, rightTopHolder, leftBottomHolder, rightBottomHolder,upDownPanel,leftRightPanel;
    static NumberFormat format = NumberFormat.getNumberInstance();
    static NumberFormatter numfom = new NumberFormatter(format);

    public void init() {
        listenUP = new TButtonListenerUP();
        listenDOWN = new TButtonListenerDOWN();
        listenSpecialUP=new SpecialTButtonListenerUP();
        listenSpecialDOWN =new SpecialTButtonListenerDOWN();
        main = new JPanel(new GridLayout(3, 3));
        upDownPanel = new JPanel(new GridLayout(2,1));
        leftRightPanel = new JPanel(new GridLayout(1,2));
        numfom.setValueClass(Integer.class);
        numfom.setAllowsInvalid(true);
        numberField = new JFormattedTextField(numfom);
        numberField.setText("0");
        numberField.setBorder(null);
        leftTopHolder = new JPanel();
        rightTopHolder = new JPanel();
        leftBottomHolder = new JPanel();
        rightBottomHolder = new JPanel();
        leftButtom = new JButton();
        leftButtom.addActionListener(listenDOWN);
        leftButtom.setBorder(null);
        leftButtom.setMargin(new Insets(0,0,0,0));
        rightButtom = new JButton();
        rightButtom.addActionListener(listenUP);
        rightButtom.setBorder(null);
        rightButtom.setMargin(new Insets(0,0,0,0));
        upButtom = new JButton();
        upButtom.addActionListener(listenUP);
        upButtom.setBorder(null);
        upButtom.setMargin(new Insets(0,0,0,0));
        downButtom = new JButton();
        downButtom.addActionListener(listenDOWN);
        downButtom.setBorder(null);
        downButtom.setMargin(new Insets(0,0,0,0));
        main.add(leftTopHolder);
        main.add(upButtom);
        main.add(rightTopHolder);
        main.add(leftButtom);
        main.add(numberField);
        main.add(rightButtom);
        main.add(leftBottomHolder);
        main.add(downButtom);
        main.add(rightBottomHolder);
    }
    /**height of Textfield,width of Textfield, oben, unten, rechts, links, komplette Font, obenImage,untenImage,linksImage,rechtsImage, Variante:default:0
     */
    public void setTButtonDesign(boolean left, boolean right, boolean down, boolean up){
    }

    public void setTButtonImages(String leftImage, String rightImage, String downImage, String upImage){
        if(leftImage!="")leftButtom.setIcon(new ImageIcon(leftImage));
        if(rightImage!="")rightButtom.setIcon(new ImageIcon(rightImage));
        if(downImage!="")downButtom.setIcon(new ImageIcon(downImage));
        if(upImage!="")upButtom.setIcon(new ImageIcon(upImage));
    }

    public void setTButtonFont(Font a){
        numberField.setFont(a);
    }

    public void setTButtonStyle(int width, int height) {
       setTButtonStyle(width,height, Variant.DEFAULT);
    }

    public void setTButtonStyle(int width, int height, Variant Style) {
        main.setPreferredSize(new Dimension(width, height));
        switch(Style) {
            case DEFAULT:

                break;
            case UPDOWN:
                removeAll();
                upDownPanel.add(upButtom);
                upDownPanel.add(downButtom);
                main.setLayout(new BorderLayout());
                main.add(upDownPanel,BorderLayout.EAST);
                main.add(numberField,BorderLayout.CENTER);
                upButtom.removeActionListener(listenUP);
                upButtom.addActionListener(listenSpecialUP);
                downButtom.removeActionListener(listenDOWN);
                downButtom.addActionListener(listenSpecialDOWN);
                break;
            case LEFTRIGHT:
                removeAll();
                leftRightPanel.add(leftButtom);
                leftRightPanel.add(rightButtom);
                main.setLayout(new BorderLayout());
                main.add(leftRightPanel,BorderLayout.SOUTH);
                main.add(numberField,BorderLayout.CENTER);
                rightButtom.removeActionListener(listenUP);
                rightButtom.addActionListener(listenSpecialUP);
                leftButtom.removeActionListener(listenDOWN);
                leftButtom.addActionListener(listenSpecialDOWN);
                break;
        }
    }
    public void removeAll(){
        main.remove(leftTopHolder);
        main.remove(upButtom);
        main.remove(rightTopHolder);
        main.remove(leftButtom);
        main.remove(numberField);
        main.remove(rightButtom);
        main.remove(leftBottomHolder);
        main.remove(downButtom);
        main.remove(rightBottomHolder);
    }
}
class TButtonListenerUP implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        JButton temp;
        temp=(JButton) e.getSource();
        JPanel tempTwo;
        tempTwo=(JPanel) temp.getParent();
        JFormattedTextField tempThree;
        tempThree=(JFormattedTextField) tempTwo.getComponent(4);
        if (tempThree.getText().equals("")) tempThree.setText("0");
        int i = Integer.parseInt(tempThree.getText());
        i++;
        tempThree.setText(i+"");
    }
}


class TButtonListenerDOWN implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        JButton temp;
        temp=(JButton) e.getSource();
        JPanel tempTwo;
        tempTwo=(JPanel) temp.getParent();
        JFormattedTextField tempThree;
        tempThree=(JFormattedTextField) tempTwo.getComponent(4);
        if (tempThree.getText().equals("")) tempThree.setText("0");
        int i = Integer.parseInt(tempThree.getText());
        i--;
        tempThree.setText(i+"");
    }
}

class SpecialTButtonListenerUP implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        JButton temp;
        temp=(JButton) e.getSource();
        JPanel tempTwo;
        tempTwo=(JPanel) temp.getParent().getParent();
        JFormattedTextField tempThree;
        tempThree=(JFormattedTextField) tempTwo.getComponent(1);
        if (tempThree.getText().equals("")) tempThree.setText("0");
        int i = Integer.parseInt(tempThree.getText());
        i++;
        tempThree.setText(i+"");
    }
}

class SpecialTButtonListenerDOWN implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        JButton temp;
        temp=(JButton) e.getSource();
        JPanel tempTwo;
        tempTwo=(JPanel) temp.getParent().getParent();
        JFormattedTextField tempThree;
        tempThree=(JFormattedTextField) tempTwo.getComponent(1);
        if (tempThree.getText().equals("")) tempThree.setText("0");
        int i = Integer.parseInt(tempThree.getText());
        i--;
        tempThree.setText(i+"");
    }
}