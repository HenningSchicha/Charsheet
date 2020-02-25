package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public static void log(String toLog){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/com/company/log.txt",true));
            out.append(toLog + "\n");
            out.close();
        }catch (IOException io){
            Logger.log("<Error in Logger>");
        }
    }

    public static void onStartup(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        log("\n\n<CharSheet log start: "+dtf.format(now)+">\n\n");
    }

    public static void onClose(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        log("\n\n<CharSheet log end: "+dtf.format(now)+">\n\n");
    }
}
