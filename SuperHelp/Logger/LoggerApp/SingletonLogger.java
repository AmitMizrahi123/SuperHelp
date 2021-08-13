package LoggerApp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SingletonLogger {
    private static final SingletonLogger firstInstance = new SingletonLogger();
    private final Logger _logger = Logger.getLogger(SingletonLogger.class.getName());
    private static boolean createTemplate = true;

    private SingletonLogger() { }

    public static SingletonLogger getInstance() {
        return firstInstance;
    }

    public Logger getLogger() {
        if (createTemplate) {
            FileHandler fh = null;
            SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");

            try {
                fh = new FileHandler("C:\\Users\\97250\\IdeaProjects\\SuperHelp\\SuperHelp\\Logger\\Logs\\Logs_"
                        + format.format(Calendar.getInstance().getTime()) + ".log");
            } catch (IOException e) {
                e.printStackTrace();
            }

            fh.setFormatter(new SimpleFormatter());
            _logger.addHandler(fh);
            _logger.setUseParentHandlers(false);

            createTemplate = false;
        }
        return _logger;
    }
}
