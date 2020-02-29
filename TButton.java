package Henning.Schicha;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import javax.swing.text.TabableView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class TButton {
    TButtonListenerUP listenUP;
    TButtonListenerDOWN listenDOWN;
    JPanel main;
    JFormattedTextField zahl;
    JButton links,rechts,oben,unten;
    JPanel leer1,leer2,leer3,leer4;
    static NumberFormat format=NumberFormat.getNumberInstance();
    static NumberFormatter numfom=new NumberFormatter(format);
    public void init() {

        listenUP=new TButtonListenerUP();
        listenDOWN=new TButtonListenerDOWN();
        main = new JPanel(new GridLayout(3, 3, 1, 1));
        numfom.setValueClass(Integer.class);
        numfom.setAllowsInvalid(true);
        zahl = new JFormattedTextField(numfom);
        zahl.setText("0");
        leer1 = new JPanel();
        leer2 = new JPanel();
        leer3 = new JPanel();
        leer4 = new JPanel();
        links = new JButton();
        links.addActionListener(listenDOWN);
        rechts = new JButton();
        rechts.addActionListener(listenUP);
        oben = new JButton();
        oben.addActionListener(listenUP);
        unten = new JButton();
        unten.addActionListener(listenDOWN);
        main.add(leer1);
        main.add(oben);
        main.add(leer2);
        main.add(links);
        main.add(zahl);
        main.add(rechts);
        main.add(leer3);
        main.add(unten);
        main.add(leer4);
    }
    public void TButtonStyle(){
        /**height of Textfield,width of Textfield, oben, unten, rechts, links,komplette Font, obenImage,untenImage,linksImage,rechtsImage, Variante:default:0

         */
    }
}

class TButtonListenerUP implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        JButton temp=new JButton();
        temp=(JButton) e.getSource();
        JPanel tempTwo=new JPanel();
        tempTwo=(JPanel) temp.getParent();
        JFormattedTextField tempThree=new JFormattedTextField();
        tempThree=(JFormattedTextField) tempTwo.getComponent(4);
        if (tempThree.getText().equals("")) tempThree.setText("0");
        int i = Integer.parseInt(tempThree.getText());
        i++;
        tempThree.setText(i+"");


    }
}


class TButtonListenerDOWN implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        JButton temp=new JButton();
        temp=(JButton) e.getSource();
        JPanel tempTwo=new JPanel();
        tempTwo=(JPanel) temp.getParent();
        JFormattedTextField tempThree=new JFormattedTextField();
        tempThree=(JFormattedTextField) tempTwo.getComponent(4);
        if (tempThree.getText().equals("")) tempThree.setText("0");
        int i = Integer.parseInt(tempThree.getText());
        i--;
        tempThree.setText(i+"");


    }
}