package Henning.Schicha;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    static final String VERY_EQUAL = "==========================================";
    static final String FILE_PATH = "src/Henning/Schicha/log.txt";
    public static void log(String toLog){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(FILE_PATH,true));
            out.append(toLog + "\n");
            out.close();
            Miscellaneous.updateLog();
        }catch (IOException io){
            clearLog();
        }
    }
    public static void clearLog(){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(FILE_PATH, false));
            out.append("");
            out.close();
            Miscellaneous.updateLog();
        } catch (IOException IOE){
            log("<Error in Logger>");
        }
        onStartup();
    }
    public static void onStartup(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        log("\n"+VERY_EQUAL+"\n<CharSheet log start: "+dtf.format(now)+">\n"+VERY_EQUAL+"\n");
    }

    public static void onClose(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        log("\n"+VERY_EQUAL+"\n <CharSheet log end: "+dtf.format(now)+">\n"+VERY_EQUAL+"\n");
    }
}
