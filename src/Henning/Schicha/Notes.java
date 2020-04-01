package Henning.Schicha;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class Notes {
    static JPanel main;
    static JPanel top;
    static JPanel center;
    static JPanel bot ;
    static JPanel[] pages;
    static AListener listen;
    static Searching search;
    static JTextArea[] areas;
    static JTextArea[] searchedArray;
    static String[] texts;
    static String searchString;
    static JButton next;
    static JButton previous;
    static JTextField searchBar;
    final static int PAGE_NO = 300;
    static int head;
    static int searchSize;
    static JTextField page;
    static JLabel prefix;
    static void init(){
        head = 0;
        searchSize = 0;
        prefix = new JLabel("Page: ");
        bot = new JPanel();
        page = new JTextField(""+(head+1));
        page.setColumns(2);
        center = new JPanel(new FlowLayout());
        searchedArray = new JTextArea[PAGE_NO];
        texts = new String[PAGE_NO];
        listen = new AListener();
        search= new Searching();
        main = new JPanel(new BorderLayout());
        pages = new JPanel[PAGE_NO];
        areas = new JTextArea[PAGE_NO];
        next = new JButton("",new ImageIcon(FilePaths.RIGHT_ARROW));
        previous = new JButton("",new ImageIcon(FilePaths.LEFT_ARROW));
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
        main.add(bot, BorderLayout.SOUTH);
        bot.add(prefix);
        bot.add(page);
        searchBar.addKeyListener(search);
        page.addKeyListener(search);
        initPageAreas();
        changePage();
        labelPages();
    }
    static void labelPages(){
        for (int i = 0; i < PAGE_NO; i++){ 
            areas[i].setText("{Page "+(i+1)+"}");
        }
    }
    static void gotoPage(){
        String input = page.getText();
        try {
            int inputt = Integer.parseInt(input)-1;
            if (inputt < PAGE_NO && inputt > -1) {
                int savethis = head;
                try {
                    head = inputt;
                    changePage();
                } catch (NullPointerException NPE) {
                    head = savethis;
                }
            }
        }catch (IllegalArgumentException e){
            page.setText(input);
        }
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
        return BorderFactory.createLineBorder(prettyColor,5, true);
    }
    static void changePage(){
        save();
        search();
        for (int i = 0; i < PAGE_NO; i++){
            pages[i].setVisible(false);
        }
        if (head>searchSize) head=searchSize-1;
        if (head == -1) head = 0;
        searchedArray[head].getParent().setVisible(true);
        page.setText(""+(head+1));
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
