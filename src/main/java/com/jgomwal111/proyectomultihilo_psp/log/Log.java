package com.jgomwal111.proyectomultihilo_psp.log;

import com.jgomwal111.proyectomultihilo_psp.utils.message.ErrorMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {

    /**
     * Attributes of this
     */
    static Logger logger = Logger.getLogger(Log.class.getName());

    /**
     * It generates an Info Log and shows the information
     * @param message Message to show
     */
    public static void infoLogging(String message) {
        saveLog();
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO,message);
    }

    /**
     * Genera el log con nivel Warning y muestra la información
     * @param message Mensaje a mostrar
     */
    public static void warningLogging(String message) {
        saveLog();
        logger.setLevel(Level.WARNING);
        logger.log(Level.WARNING,message);
    }

    private static void saveLog() {
        try {
            InputStream configFile= Log.class.getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(configFile);
        }catch(SecurityException | IOException | NullPointerException e) {
            new ErrorMessage("Logging system not initialized").showMessage();
        }
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }
}
