package my.application.helper;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class LoggingHelper {
    private static final String LOG_FILE_NAME = "planner.log";
    private static FileHandler fileHandler;
    private static SimpleFormatter simpleFormatter;

    static {
        try {
            fileHandler = new FileHandler(LOG_FILE_NAME);
            simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static FileHandler getFileHandler(){
        return fileHandler;
    }
}
