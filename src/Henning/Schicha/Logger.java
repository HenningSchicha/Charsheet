package Henning.Schicha;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    static final String VERY_EQUAL = "==========================================";
    public static void log(String toLog){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(FilePaths.LOG,true));
            out.append(toLog).append("\n");
            out.close();
            Miscellaneous.updateLog();
        }catch (IOException io){
            showError();
        }
    }
    public static void showError(){
        JOptionPane.showMessageDialog(null,
                "ERROR: Log file not found",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
    public static void clearLog(){
        try{
            if (JOptionPane.showConfirmDialog(UI.main,
                    "Are you sure you want to clear the Log?",
                    "Confirm clear Log",
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                BufferedWriter out = new BufferedWriter(new FileWriter(FilePaths.LOG, false));
                out.append("");
                out.close();
                Miscellaneous.updateLog();
                onStartup();
            }
        } catch (IOException IOE){
            showError();
        }
    }
    public static void onStartup(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        log("\n"+VERY_EQUAL+"\n<CharSheet log start: "+dtf.format(now)+">\n"+VERY_EQUAL+"\n");
    }

    public static void onClose(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        log("\n"+VERY_EQUAL+"\n<CharSheet log end: "+dtf.format(now)+">\n"+VERY_EQUAL+"\n");
    }
}
