import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.*;

/*
I assume single threaded environment, if it's multithreaded we can use synchronized
 */
public class LoggerSingleton {
    public static final Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    private static LoggerSingleton instance = null;

    public static LoggerSingleton getInstance(String logg) throws IOException {
        if (instance == null) {
            prepareLogger(logg);
            instance = new LoggerSingleton();
        }
        return instance;
    }

    public static void prepareLogger(String logg) throws IOException {
        FileHandler myFileHandler = new FileHandler(logg);
        myFileHandler.setFormatter(new SimpleFormatter());
        myLogger.addHandler(myFileHandler);
        myLogger.setUseParentHandlers(false);
        myLogger.setLevel(Level.ALL);
    }
}
