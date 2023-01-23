package ProyectoMultihilo.utils.log;


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
     * It generates a Warning Log and shows the information
     * @param message Message to show
     */
    public static void warningLogging(String message) {
        saveLog();
        logger.setLevel(Level.WARNING);
        logger.log(Level.WARNING,message);
    }

    /**
     * It read log properties and then it use log configuration
     */
    private static void saveLog() {
        try {
            InputStream configFile= Log.class.getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(configFile);
        }catch(SecurityException | IOException | NullPointerException e) {
            e.printStackTrace();
        }
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }
}
