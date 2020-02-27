package Henning.Schicha;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Objects;


public class Spellbook {
    static JPanel top;
    static JPanel outer;
    static JPanel center;
    static JTextField searchbar;
    static JButton searchbutton;
    static JButton next;
    static JButton previous;
    static JButton currentsave;
    static JLabel result;
    static JLabel nextPlaceHolder;
    static JLabel previousPlaceHolder;
    static MenuComponent[] components;
    static Spell[] Spells;
    static SpellMenu actualMenu;
    static AListener listen;
    static final int SPELL_NO = 260;
    static int head;
    static int screenFittingSpells;
    static int componentHeight;
    static int currentmenu;
    static Searching searchListener;
    static Boolean inMenu;
    static Boolean inFavorites;
    static Dimension buttondim;
    static Dimension xddim;

    public static void init(){
        buttondim=new Dimension(51,51);     //width everywhere is WEIRD change with caution!!! should be odd/prime
        xddim=new Dimension(1,60);
        head=0;
        inMenu=false;
        inFavorites=false;
        searchListener=new Searching();
        currentmenu=-1;
        componentHeight=35;
        listen=new AListener();
        screenFittingSpells=10;
        outer = new JPanel();
        center=new JPanel();
        center.setLayout(new FlowLayout());
        center.setPreferredSize(new Dimension(541,(componentHeight+21)*screenFittingSpells));
        outer.setLayout(new BorderLayout());
        outer.add(center,BorderLayout.CENTER);
        outer.setOpaque(true);
        addATonOfComponents();
        top = new JPanel(new FlowLayout());
        searchbar = new JTextField();
        previous= new JButton("", new ImageIcon("src/Henning/Schicha/leftArrow.png"));
        previous.setPreferredSize(buttondim);
        nextPlaceHolder= new JLabel();
        previousPlaceHolder=new JLabel();
        previousPlaceHolder.setPreferredSize(buttondim);
        top.add(previousPlaceHolder);
        previousPlaceHolder.setVisible(false);
        nextPlaceHolder.setVisible(false);
        top.add(previous);
        searchbar.setPreferredSize(new Dimension(401, 50));
        top.add(searchbar);
        next = new JButton("",new ImageIcon("src/Henning/Schicha/rightArrow.png"));
        nextPlaceHolder.setPreferredSize(buttondim);
        searchbutton = new JButton("", new ImageIcon("src/Henning/Schicha/searchMonocle.jpg"));
        top.add(searchbutton);
        next.setPreferredSize(buttondim);
        searchbutton.setMargin(new Insets(0, 0, 0, 0));
        searchbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        outer.add(top,BorderLayout.NORTH);
        result=new JLabel("Next Result will appear here");
        outer.add(result,BorderLayout.SOUTH);
        top.add(next);
        top.add(nextPlaceHolder);
        next.setMargin(new Insets(0,0,0,0));
        previous.setMargin(new Insets(0,0,0,0));
        next.addActionListener(listen);
        previous.addActionListener(listen);
        searchbar.addKeyListener(searchListener);
        addATonOfSpells();
        actualMenu=new SpellMenu();
        searchbutton.addActionListener(listen);
        changePage();
    }
    static void removeButtonIfEndOfMenu(){
        previous.setVisible(true);
        previousPlaceHolder.setVisible(false);
        next.setVisible(true);
        nextPlaceHolder.setVisible(false);
        if(head==0){
            previous.setVisible(false);
            previousPlaceHolder.setVisible(true);
        }
        else if (head + screenFittingSpells >= SPELL_NO) {
            next.setVisible(false);
            nextPlaceHolder.setVisible(true);
        }
    }
    static void updateColors(){
        actualMenu.updateColors();
    }
    static void releaseRGBmaybe(){
        if (Objects.requireNonNull(actualMenu.farbwahl.getSelectedItem()) =="Custom"){
            releaseRGBdefinite(true);
        }
        else releaseRGBdefinite(false);
    }
    static void releaseRGBdefinite(Boolean dowe){
        actualMenu.R.setEditable(dowe);
        actualMenu.G.setEditable(dowe);
        actualMenu.B.setEditable(dowe);
    }
    static void search(JTextField input){
        if (!inMenu){
        String temp = input.getText();
        if (temp.equals("")) changePage();
        else {
            for (int i = 0; i < SPELL_NO; i++) {
                if (Spells[i].name.toLowerCase().contains(temp.toLowerCase())) {
                    components[i].main.setVisible(true);
                } else components[i].main.setVisible(false);
            }
        }
        }
    }
    static void roll(String input,int curr){
        int[] results = CommonFunctions.RollDice(input);
        String Result="You rolled "+results[0]+" (";
        for(int i=1;i<results.length;i++){
            Result=Result+results[i]+" ";
        }
        Result=Result.stripTrailing();
        Result=Result+")";
        Result=Result+" + "+CommonFunctions.SplitDiceString(input)[CommonFunctions.SplitDiceString(input).length-1];
        Result=Result+" with "+Spells[curr].name;
        Logger.log("<Rolled Spell> "+Result);
        result.setText(Result);
    }
    static void openMenu(int current){
        for (int i = 0; i < SPELL_NO; i++){
            components[i].main.setVisible(false);
        }
        inMenu=true;
        SpellMenu menu= new SpellMenu();
        menu.fillFromSpell(Spells[current]);
        if (menu.stringIsColor(components[current].colorStr)) {
            menu.farbwahl.setSelectedItem(components[current].colorStr);
        }else{
            menu.setRGBsaves(components[current].colorStr);
        }
        CommonFunctions.ComboBoxSelfColor(menu.farbwahl);
        center.add(menu.main);
        top.setVisible(false);
        result.setVisible(false);
        actualMenu.setRGBsaves(components[current].myColorRGB);
        setStaticReferences(menu, current);
        releaseRGBmaybe();
        updateColors();
    }
    static void setStaticReferences(SpellMenu pMenu, int pCurrent){
        currentsave=pMenu.save;
        currentmenu=pCurrent;
        actualMenu=pMenu;
    }
    static Color getColorFromComboColor(String combo){
        switch(combo) {
            case "Ruby":
                return new Color(237, 83, 83);
            case "Aquamarine":
                return new Color(128, 255, 212);
            case "Honey":
                return new Color(255, 195, 110);
            case "Snake Green":
                return new Color(0, 141, 76);
            case "Very Default":
                return Color.WHITE;
            case "Silver":
                return new Color(129, 129, 129);
            case "Gold":
                return new Color(212, 175, 55);
            case "Copper":
                return new Color(184, 115, 51);
            case "Lapis":
                return new Color(38, 97, 156);
            case "Double Special Surprise":
                return new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        }
        String tempo=actualMenu.getStringRGB();
        String[] tempoo=tempo.split(",");
        if(tempoo.length==3) {
            try {
                int cone = Integer.parseInt(tempoo[0]);
                int ctwo = Integer.parseInt(tempoo[1]);
                int cthree = Integer.parseInt(tempoo[2]);
                return new Color(cone, ctwo, cthree);
            }catch (IllegalArgumentException e){
                return Color.WHITE;
            }
        }
        return Color.WHITE;
    }
    static void closeMenu(int current,boolean fromMenu){
        if(!Objects.requireNonNull(actualMenu.farbwahl.getSelectedItem()).equals("Custom")) {
            Color temp = getColorFromComboColor((String) Objects.requireNonNull(actualMenu.farbwahl.getSelectedItem()));
            components[current].setColor(temp);
            components[current].colorStr = (String) Objects.requireNonNull(actualMenu.farbwahl.getSelectedItem());
        }
        else{
            Color temp = getColorFromComboColor(Objects.requireNonNull(actualMenu.getStringRGB()));
            components[current].setColor(temp);
            components[current].colorStr = Objects.requireNonNull(actualMenu.getStringRGB());
            components[current].myColorRGB = Objects.requireNonNull(actualMenu.getStringRGB());
        }
        center.remove(actualMenu.main);
        top.setVisible(true);
        result.setVisible(true);
        if (!inFavorites) changePage();
        else openFavorites(true);
        if (fromMenu)actualMenu.saveToSpell(Spells[current]);
        updateFavoriteBorders();
        components[current].setText(actualMenu.name.getText());
        if (fromMenu) Logger.log("<Edited Spell> "+Spells[current].name);
        inMenu=false;
    }
    static void addATonOfSpells(){
        Spells = new Spell[SPELL_NO];
        for (int i = 0; i < SPELL_NO; i++){
            Spells[i]=new Spell("A New Spelldescription","0","New Spell "+(i+1),"1","None","0","0d0+0",false,false,false,"0,0,0");
        }
    }
    static void goNext(){
        if (!inMenu) {
            if (!(currentmenu == -1)) closeMenu(currentmenu, false);
            if (!(head + screenFittingSpells > SPELL_NO)) {
                head = head + screenFittingSpells;
            }
            changePage();
        }
    }
    static void goPrevious(){
        if(!inMenu) {
            if (!(currentmenu == -1)) closeMenu(currentmenu, false);
            if (!(head == 0)) {
                head = head - screenFittingSpells;
            }
            changePage();
        }
    }
    static void changePage(){
        removeButtonIfEndOfMenu();
        updateFavoriteBorders();
        for (int i = 0; i < (SPELL_NO); i++){
            components[i].main.setVisible(false);
        }
        for (int i = head; i <(head+screenFittingSpells)&&i< SPELL_NO; i++){
            components[i].main.setVisible(true);
        }
    }
    static void addATonOfComponents(){
        components = new MenuComponent[SPELL_NO];
        for (int i = 0; i< SPELL_NO; i++){
            components[i]=new MenuComponent("New Spell "+(i+1));
            center.add(components[i].main);
        }
    }
    static void openFavorites(Boolean fromMenu){
        if (fromMenu) {
            inFavorites=false;
            inMenu=false;
        }
        if (inFavorites) {
            inFavorites=false;
            inMenu=false;
            changePage();
        }
        else if (!inMenu) {
            inFavorites=true;
            inMenu = true;
            for (int i = 0; i < SPELL_NO; i++){
                components[i].main.setVisible(false);
                if (Spells[i].favorite){
                    components[i].main.setVisible(true);
                }
            }
            updateFavoriteBorders();
        }
    }
    static void updateFavoriteBorders(){
        for (int i = 0; i < SPELL_NO; i ++){
            components[i].main.setBorder(BorderFactory.createEtchedBorder());
            if (Spells[i].favorite){
                components[i].main.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }
    }
}
class MenuComponent{
   JPanel main;
   Color mycolor;
   String myColorRGB;
   String colorStr;
   JButton open;
    JButton roll;
   JLabel name;
   AListener listen;
   Boolean isFavorite;
   MenuComponent(String pname) {
        main=new JPanel(new FlowLayout());
        open=new JButton("View");
        roll=new JButton("Roll");
        colorStr="Default";
        myColorRGB="0,0,0";
        listen=new AListener();
        main.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        open.addActionListener(listen);
        roll.addActionListener(listen);
        name=new JLabel(pname);
        name.setPreferredSize(new Dimension(401,Spellbook.componentHeight));
        open.setPreferredSize(new Dimension(70,Spellbook.componentHeight));
        roll.setPreferredSize(new Dimension(70,Spellbook.componentHeight));
        main.add(open);
        main.add(name);
        main.add(roll);
        setColor(Color.WHITE);
    }
    void setText(String pname){
        name.setText(pname);
    }
    void setColor(Color pColor){
       mycolor=pColor;
       main.setBackground(mycolor);
    }
    Color getColor(){
       return mycolor;
    }
}

class Spell{
    String desc;
    String range;
    String name;
    String level;
    String school;
    String speed;
    String damage;
    String RGBcolor;
    Boolean ritual;
    Boolean concentration;
    Boolean favorite;
    Spell(String pdesc, String prange, String pname, String plevel, String pschool, String pspeed, String pdamage, Boolean pritual, Boolean pconcentration, Boolean pfavorite,String pRGBcolor){
        if (pdesc.equals("")) desc="No Description"; else desc=pdesc;
        if (prange.equals("")) range="Special";else range=prange;
        if (pname.equals("")) name="Magic"; else name=pname;
        if (plevel.equals("")) level="1"; else level=plevel;
        if (pschool.equals("")) school="General magic";else school=pschool;
        if (pspeed.equals("")) speed="0";else speed=pspeed;
        if (pdamage.equals("")) damage="0d0+0"; else damage=pdamage;
        ritual=pritual;
        concentration=pconcentration;
        favorite=pfavorite;
        if(pRGBcolor.equals(",,")) RGBcolor="0,0,0"; else RGBcolor=pRGBcolor;
    }
    Spell defaultSpell(){
        return new Spell("","","","","","","",false,false,false,"0,0,0");
    }
    private int[] rolldmg(){
        return CommonFunctions.RollDice(damage);
    }
}
class SpellMenu{
    JComboBox farbwahl;
    ColorUpdateListener update;
    JPanel main;
    JPanel submain;
    JPanel colorPanel;
    Border b;
    JTextField range;
    JTextField name;
    JTextField level;
    JTextField school;
    JTextField speed;
    JTextField damage;
    JTextArea desc;
    JFormattedTextField R;
    JFormattedTextField G;
    JFormattedTextField B;
    JPanel currentColor;
    JCheckBox ritual;
    JCheckBox concentration;
    JCheckBox favorite;
    JButton save;
    AListener listen;
    static NumberFormat format=NumberFormat.getNumberInstance();
    static NumberFormatter numfom=new NumberFormatter(format);
    SpellMenu(){
        update=new ColorUpdateListener();
        String[] farben={"Very Default","Ruby","Aquamarine","Honey","Snake Green","Gold","Silver","Copper","Lapis","Double Special Surprise","Custom"};
        main= new JPanel(new GridLayout(1,2 ));
        submain=new JPanel(new GridLayout(13,1));
        numfom.setValueClass(Integer.class);
        numfom.setAllowsInvalid(false);
        R=new JFormattedTextField(numfom);
        G=new JFormattedTextField(numfom);
        B=new JFormattedTextField(numfom);
        currentColor=new JPanel();
        currentColor.setVisible(true);
        colorPanel=new JPanel(new GridLayout(1,4));
        colorPanel.add(R);
        R.setBorder(tborder("R"));
        colorPanel.add(G);
        G.setBorder(tborder("G"));
        colorPanel.add(B);
        B.setBorder(tborder("B"));
        R.setEditable(false);
        G.setEditable(false);
        B.setEditable(false);
        R.setText("0");
        G.setText("0");
        B.setText("0");
        R.addKeyListener(update);
        G.addKeyListener(update);
        B.addKeyListener(update);
        colorPanel.add(currentColor);
        farbwahl=new JComboBox(farben);
        favorite=new JCheckBox("Set as Favorite");
        b=BorderFactory.createLineBorder(Color.BLACK);
        save=new JButton("Return");
        listen=new AListener();
        save.addActionListener(listen);
        range=new JTextField("");
        range.setBorder(tborder("Range"));
        name=new JTextField("");
        name.setBorder(tborder("Name"));
        level=new JTextField("");
        level.setBorder(tborder("Level"));
        school=new JTextField("");
        school.setBorder(tborder("School"));
        speed=new JTextField("");
        speed.setBorder(tborder("Speed"));
        damage=new JTextField("");
        damage.setBorder(tborder("Damage"));
        desc=new JTextArea("");
        desc.setBorder(tborder("Description"));
        desc.setLineWrap(true);
        ritual=new JCheckBox("Ritual?");
        concentration=new JCheckBox("Concentration?");
        farbwahl.addActionListener(listen);
        submain.add(name);
        submain.add(level);
        submain.add(damage);
        submain.add(school);
        submain.add(speed);
        submain.add(ritual);
        submain.add(concentration);
        submain.add(favorite);
        main.add(submain);
        main.add(desc);
        submain.add(farbwahl);
        submain.add(colorPanel);
        submain.add(save);
    }
    void updateColors(){
        if(R.getText().equals("")) R.setText("0");
        if(G.getText().equals("")) G.setText("0");
        if(B.getText().equals("")) B.setText("0");
        try{
        if( (Integer.parseInt(R.getText()) >= 0) && (Integer.parseInt(R.getText()) <= 255) &&
                (Integer.parseInt(R.getText()) >= 0) && (Integer.parseInt(R.getText()) <= 255) &&
                (Integer.parseInt(R.getText()) >= 0) && (Integer.parseInt(R.getText()) <= 255) ) {
            CommonFunctions.ComboBoxSelfColor(farbwahl);
                currentColor.setBackground(new Color(Integer.parseInt(R.getText()), Integer.parseInt(G.getText()), Integer.parseInt(B.getText())));
            }
            }
        catch (IllegalArgumentException e){
            currentColor.setBackground(Color.BLACK);
        }
    }

   String getStringRGB(){
       if(R.getText().equals("")) R.setText("0");
       if(G.getText().equals("")) G.setText("0");
       if(B.getText().equals("")) B.setText("0");
            return R.getText()+","+G.getText()+","+B.getText();
   }
   Boolean stringIsColor(String test){
       String[] farben={"Very Default","Ruby","Aquamarine","Honey","Snake Green","Gold","Silver","Copper","Lapis","Double Special Surprise","Custom"};
        for (int i = 0;i<11;i++){
            if (test.equals(farben[i])){
                return true;
            }
       }
      return false;
   }
    void setRGBsaves(String RGB){
        String[] tempoo=RGB.split(",");
        if(tempoo.length==3 && !(RGB.equals("0,0,0"))) {
            farbwahl.setSelectedItem("Custom");
            Spellbook.releaseRGBmaybe();
            R.setText(tempoo[0]);
            G.setText(tempoo[1]);
            B.setText(tempoo[2]);
        }
    }
   void fillFromSpell(Spell filler){
        name.setText(filler.name);
        range.setText(filler.range);
        desc.setText(filler.desc);
        concentration.setSelected(filler.concentration);
        level.setText(filler.level);
        school.setText(filler.school);
        speed.setText(filler.speed);
        damage.setText(filler.damage);
        ritual.setSelected(filler.ritual);
        favorite.setSelected(filler.favorite);
        currentColor.setBackground(Spellbook.getColorFromComboColor(filler.RGBcolor));
    }
     void saveToSpell(Spell filled){
       filled.name=name.getText();
       filled.range=range.getText();
       filled.desc=desc.getText();
       filled.concentration=concentration.isSelected();
       filled.level=level.getText();
       filled.school=school.getText();
       filled.speed=speed.getText();
       filled.damage=damage.getText();
       filled.ritual=ritual.isSelected();
       filled.favorite=favorite.isSelected();
       filled.RGBcolor=getStringRGB();
    }

     Spell CreateSpell(){
        return new Spell(desc.getText(),range.getText(),name.getText(),level.getText(),school.getText(),speed.getText(),damage.getText(),ritual.isSelected(),concentration.isSelected(),favorite.isSelected(),getStringRGB());
    }

    TitledBorder tborder(String title){
        return BorderFactory.createTitledBorder(b,title,TitledBorder.CENTER,TitledBorder.ABOVE_TOP);
    }
}
