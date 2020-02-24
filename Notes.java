package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Notes {
    static JPanel main, top, center;
    static JPanel[] pages;
    static AListener listen;
    static Searching search;
    static JTextArea[] areas, searchedArray;
    static String[] texts;
    static String searchString;
    static JButton next, previous;
    static JTextField searchBar;
    final static int PAGE_NO = 300;
    static int head, searchSize;
    static JLabel page;
    public static void init(){
        head = 0;
        searchSize = 0;
        page = new JLabel("Page: "+(head+1));
        center = new JPanel(new FlowLayout());
        searchedArray = new JTextArea[PAGE_NO];
        texts = new String[PAGE_NO];
        listen = new AListener();
        search= new Searching();
        main = new JPanel(new BorderLayout());
        pages = new JPanel[PAGE_NO];
        areas = new JTextArea[PAGE_NO];
        next = new JButton("",new ImageIcon("src/com/company/rightArrow.png"));
        previous = new JButton("",new ImageIcon("src/com/company/leftArrow.png"));
        next.setMargin(new Insets(0,0,0,0));
        previous.setMargin(new Insets(0,0,0,0));
        next.addActionListener(listen);
        previous.addActionListener(listen);
        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(400,50));
        top = new JPanel(new FlowLayout());
        top.add(previous);
        top.add(searchBar);
        top.add(next);
        main.add(top, BorderLayout.NORTH);
        main.add(center,BorderLayout.CENTER);
        main.add(page,BorderLayout.SOUTH);
        searchBar.addKeyListener(search);
        initPageAreas();
        changePage();
    }
    static void search(){
        updateSearchString();
        updateSearchArray();
    }
    static void updateSearchString(){
        searchString = searchBar.getText();
    }
    static void updateSearchArray(){
        searchSize=0;
        for (int i = 0; i < PAGE_NO; i++){
            if (texts[i].toLowerCase().contains(searchString.toLowerCase())){
                searchedArray[searchSize]=areas[i];
                searchSize++;
            }
        }
    }
    static void save(){
        for (int i = 0; i < PAGE_NO; i++){
            texts[i] = areas[i].getText();
        }
    }
    static void initPageAreas(){
        for (int i = 0; i < PAGE_NO; i++){
            pages[i]=new JPanel();
            areas[i]=new JTextArea();
            pages[i].add(areas[i]);
            pages[i].setVisible(false);
            center.add(pages[i], BorderLayout.CENTER);
            areas[i].setText(texts[i]);
            areas[i].setPreferredSize(new Dimension(600,600));
            areas[i].setBorder(prettyBorder(i));
        }
    }
    static int upTo(int pInt){
        return CommonFunctions.upTo(pInt);
    }
    static int downTo(int pInt){
        return CommonFunctions.downTo(pInt);
    }
    static int halfWay(int pInt){
        return CommonFunctions.halfWay(pInt);
    }
    static Border prettyBorder(int colorInt){
        Color prettyColor = new Color(upTo(colorInt*5),downTo(colorInt*5),halfWay(colorInt*5));
        return BorderFactory.createLineBorder(prettyColor,5);
    }
    static void changePage(){
        save();
        search();
        for (int i = 0; i < PAGE_NO; i++){
            pages[i].setVisible(false);
        }
        searchedArray[head].getParent().setVisible(true);
        page.setText("Page: "+(head+1));
    }
    static void goNext(){
        if (head<(searchSize-1)){
            head++;
        }
        changePage();
    }
    static void goPrevious(){
        if (head>0){
            head--;
        }
        changePage();
    }
}
